package org.h2.server;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.command.Command;
import org.h2.engine.ConnectionInfo;
import org.h2.engine.Engine;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.expression.Parameter;
import org.h2.expression.ParameterInterface;
import org.h2.expression.ParameterRemote;
import org.h2.jdbc.JdbcSQLException;
import org.h2.message.DbException;
import org.h2.result.ResultColumn;
import org.h2.result.ResultInterface;
import org.h2.store.DataHandler;
import org.h2.store.LobStorageInterface;
import org.h2.util.IOUtils;
import org.h2.util.SmallLRUCache;
import org.h2.util.SmallMap;
import org.h2.util.StringUtils;
import org.h2.value.Transfer;
import org.h2.value.Value;
import org.h2.value.ValueLobDb;

public class TcpServerThread
  implements Runnable
{
  protected final Transfer transfer;
  private final TcpServer server;
  private Session session;
  private boolean stop;
  private Thread thread;
  private Command commit;
  private final SmallMap cache = new SmallMap(SysProperties.SERVER_CACHED_OBJECTS);
  private final SmallLRUCache<Long, CachedInputStream> lobs = SmallLRUCache.newInstance(Math.max(SysProperties.SERVER_CACHED_OBJECTS, SysProperties.SERVER_RESULT_SET_FETCH_SIZE * 5));
  private final int threadId;
  private int clientVersion;
  private String sessionId;
  
  TcpServerThread(Socket socket, TcpServer server, int id)
  {
    this.server = server;
    this.threadId = id;
    this.transfer = new Transfer(null);
    this.transfer.setSocket(socket);
  }
  
  private void trace(String s)
  {
    this.server.trace(this + " " + s);
  }
  
  public void run()
  {
    try
    {
      this.transfer.init();
      trace("Connect");
      try
      {
        if (!this.server.allow(this.transfer.getSocket())) {
          throw DbException.get(90117);
        }
        int minClientVersion = this.transfer.readInt();
        if (minClientVersion < 6) {
          throw DbException.get(90047, new String[] { "" + this.clientVersion, "6" });
        }
        if (minClientVersion > 15) {
          throw DbException.get(90047, new String[] { "" + this.clientVersion, "15" });
        }
        int maxClientVersion = this.transfer.readInt();
        if (maxClientVersion >= 15) {
          this.clientVersion = 15;
        } else {
          this.clientVersion = minClientVersion;
        }
        this.transfer.setVersion(this.clientVersion);
        String db = this.transfer.readString();
        String originalURL = this.transfer.readString();
        if ((db == null) && (originalURL == null))
        {
          String targetSessionId = this.transfer.readString();
          int command = this.transfer.readInt();
          this.stop = true;
          if (command == 13)
          {
            int statementId = this.transfer.readInt();
            this.server.cancelStatement(targetSessionId, statementId);
          }
          else if (command == 14)
          {
            db = this.server.checkKeyAndGetDatabaseName(targetSessionId);
            if (!targetSessionId.equals(db)) {
              this.transfer.writeInt(1);
            } else {
              this.transfer.writeInt(0);
            }
          }
        }
        String baseDir = this.server.getBaseDir();
        if (baseDir == null) {
          baseDir = SysProperties.getBaseDir();
        }
        db = this.server.checkKeyAndGetDatabaseName(db);
        ConnectionInfo ci = new ConnectionInfo(db);
        ci.setOriginalURL(originalURL);
        ci.setUserName(this.transfer.readString());
        ci.setUserPasswordHash(this.transfer.readBytes());
        ci.setFilePasswordHash(this.transfer.readBytes());
        int len = this.transfer.readInt();
        for (int i = 0; i < len; i++) {
          ci.setProperty(this.transfer.readString(), this.transfer.readString());
        }
        if (baseDir != null) {
          ci.setBaseDir(baseDir);
        }
        if (this.server.getIfExists()) {
          ci.setProperty("IFEXISTS", "TRUE");
        }
        this.transfer.writeInt(1);
        this.transfer.writeInt(this.clientVersion);
        this.transfer.flush();
        if ((this.clientVersion >= 13) && 
          (ci.getFilePasswordHash() != null)) {
          ci.setFileEncryptionKey(this.transfer.readBytes());
        }
        this.session = Engine.getInstance().createSession(ci);
        this.transfer.setSession(this.session);
        this.server.addConnection(this.threadId, originalURL, ci.getUserName());
        trace("Connected");
      }
      catch (Throwable e)
      {
        sendError(e);
        this.stop = true;
      }
      while (!this.stop) {
        try
        {
          process();
        }
        catch (Throwable e)
        {
          sendError(e);
        }
      }
      trace("Disconnect");
    }
    catch (Throwable e)
    {
      this.server.traceError(e);
    }
    finally
    {
      close();
    }
  }
  
  private void closeSession()
  {
    if (this.session != null)
    {
      RuntimeException closeError = null;
      try
      {
        Command rollback = this.session.prepareLocal("ROLLBACK");
        rollback.executeUpdate();
      }
      catch (RuntimeException e)
      {
        closeError = e;
        this.server.traceError(e);
      }
      catch (Exception e)
      {
        this.server.traceError(e);
      }
      try
      {
        this.session.close();
        this.server.removeConnection(this.threadId);
      }
      catch (RuntimeException e)
      {
        if (closeError == null)
        {
          closeError = e;
          this.server.traceError(e);
        }
      }
      catch (Exception e)
      {
        this.server.traceError(e);
      }
      finally
      {
        this.session = null;
      }
      if (closeError != null) {
        throw closeError;
      }
    }
  }
  
  void close()
  {
    try
    {
      this.stop = true;
      closeSession();
    }
    catch (Exception e)
    {
      this.server.traceError(e);
    }
    finally
    {
      this.transfer.close();
      trace("Close");
      this.server.remove(this);
    }
  }
  
  private void sendError(Throwable t)
  {
    try
    {
      SQLException e = DbException.convert(t).getSQLException();
      StringWriter writer = new StringWriter();
      e.printStackTrace(new PrintWriter(writer));
      String trace = writer.toString();
      String sql;
      String message;
      String sql;
      if ((e instanceof JdbcSQLException))
      {
        JdbcSQLException j = (JdbcSQLException)e;
        String message = j.getOriginalMessage();
        sql = j.getSQL();
      }
      else
      {
        message = e.getMessage();
        sql = null;
      }
      this.transfer.writeInt(0).writeString(e.getSQLState()).writeString(message).writeString(sql).writeInt(e.getErrorCode()).writeString(trace).flush();
    }
    catch (Exception e2)
    {
      if (!this.transfer.isClosed()) {
        this.server.traceError(e2);
      }
      this.stop = true;
    }
  }
  
  private void setParameters(Command command)
    throws IOException
  {
    int len = this.transfer.readInt();
    ArrayList<? extends ParameterInterface> params = command.getParameters();
    for (int i = 0; i < len; i++)
    {
      Parameter p = (Parameter)params.get(i);
      p.setValue(this.transfer.readValue());
    }
  }
  
  private void process()
    throws IOException
  {
    int operation = this.transfer.readInt();
    switch (operation)
    {
    case 0: 
    case 11: 
      int id = this.transfer.readInt();
      String sql = this.transfer.readString();
      int old = this.session.getModificationId();
      Command command = this.session.prepareLocal(sql);
      boolean readonly = command.isReadOnly();
      this.cache.addObject(id, command);
      boolean isQuery = command.isQuery();
      ArrayList<? extends ParameterInterface> params = command.getParameters();
      this.transfer.writeInt(getState(old)).writeBoolean(isQuery).writeBoolean(readonly).writeInt(params.size());
      if (operation == 11) {
        for (ParameterInterface p : params) {
          ParameterRemote.writeMetaData(this.transfer, p);
        }
      }
      this.transfer.flush();
      break;
    case 1: 
      this.stop = true;
      closeSession();
      this.transfer.writeInt(1).flush();
      close();
      break;
    case 8: 
      if (this.commit == null) {
        this.commit = this.session.prepareLocal("COMMIT");
      }
      int old = this.session.getModificationId();
      this.commit.executeUpdate();
      this.transfer.writeInt(getState(old)).flush();
      break;
    case 10: 
      int id = this.transfer.readInt();
      int objectId = this.transfer.readInt();
      Command command = (Command)this.cache.getObject(id, false);
      ResultInterface result = command.getMetaData();
      this.cache.addObject(objectId, result);
      int columnCount = result.getVisibleColumnCount();
      this.transfer.writeInt(1).writeInt(columnCount).writeInt(0);
      for (int i = 0; i < columnCount; i++) {
        ResultColumn.writeColumn(this.transfer, result, i);
      }
      this.transfer.flush();
      break;
    case 2: 
      int id = this.transfer.readInt();
      int objectId = this.transfer.readInt();
      int maxRows = this.transfer.readInt();
      int fetchSize = this.transfer.readInt();
      Command command = (Command)this.cache.getObject(id, false);
      setParameters(command);
      int old = this.session.getModificationId();
      ResultInterface result;
      synchronized (this.session)
      {
        result = command.executeQuery(maxRows, false);
      }
      this.cache.addObject(objectId, result);
      int columnCount = result.getVisibleColumnCount();
      int state = getState(old);
      this.transfer.writeInt(state).writeInt(columnCount);
      int rowCount = result.getRowCount();
      this.transfer.writeInt(rowCount);
      for (int i = 0; i < columnCount; i++) {
        ResultColumn.writeColumn(this.transfer, result, i);
      }
      int fetch = Math.min(rowCount, fetchSize);
      for (int i = 0; i < fetch; i++) {
        sendRow(result);
      }
      this.transfer.flush();
      break;
    case 3: 
      int id = this.transfer.readInt();
      Command command = (Command)this.cache.getObject(id, false);
      setParameters(command);
      int old = this.session.getModificationId();
      int updateCount;
      synchronized (this.session)
      {
        updateCount = command.executeUpdate();
      }
      int status;
      int status;
      if (this.session.isClosed()) {
        status = 2;
      } else {
        status = getState(old);
      }
      this.transfer.writeInt(status).writeInt(updateCount).writeBoolean(this.session.getAutoCommit());
      
      this.transfer.flush();
      break;
    case 4: 
      int id = this.transfer.readInt();
      Command command = (Command)this.cache.getObject(id, true);
      if (command != null)
      {
        command.close();
        this.cache.freeObject(id);
      }
      break;
    case 5: 
      int id = this.transfer.readInt();
      int count = this.transfer.readInt();
      ResultInterface result = (ResultInterface)this.cache.getObject(id, false);
      this.transfer.writeInt(1);
      for (int i = 0; i < count; i++) {
        sendRow(result);
      }
      this.transfer.flush();
      break;
    case 6: 
      int id = this.transfer.readInt();
      ResultInterface result = (ResultInterface)this.cache.getObject(id, false);
      result.reset();
      break;
    case 7: 
      int id = this.transfer.readInt();
      ResultInterface result = (ResultInterface)this.cache.getObject(id, true);
      if (result != null)
      {
        result.close();
        this.cache.freeObject(id);
      }
      break;
    case 9: 
      int oldId = this.transfer.readInt();
      int newId = this.transfer.readInt();
      Object obj = this.cache.getObject(oldId, false);
      this.cache.freeObject(oldId);
      this.cache.addObject(newId, obj);
      break;
    case 12: 
      this.sessionId = this.transfer.readString();
      this.transfer.writeInt(1);
      this.transfer.writeBoolean(this.session.getAutoCommit());
      this.transfer.flush();
      break;
    case 15: 
      boolean autoCommit = this.transfer.readBoolean();
      this.session.setAutoCommit(autoCommit);
      this.transfer.writeInt(1).flush();
      break;
    case 16: 
      this.transfer.writeInt(1).writeInt(this.session.hasPendingTransaction() ? 1 : 0).flush();
      
      break;
    case 17: 
      long lobId = this.transfer.readLong();
      boolean verifyMac;
      byte[] hmac;
      CachedInputStream in;
      if (this.clientVersion >= 11)
      {
        boolean verifyMac;
        boolean verifyMac;
        if (this.clientVersion >= 12)
        {
          byte[] hmac = this.transfer.readBytes();
          verifyMac = true;
        }
        else
        {
          byte[] hmac = null;
          verifyMac = false;
        }
        CachedInputStream in = (CachedInputStream)this.lobs.get(Long.valueOf(lobId));
        if ((in == null) && (verifyMac))
        {
          in = new CachedInputStream(null);
          this.lobs.put(Long.valueOf(lobId), in);
        }
      }
      else
      {
        verifyMac = false;
        hmac = null;
        in = (CachedInputStream)this.lobs.get(Long.valueOf(lobId));
      }
      long offset = this.transfer.readLong();
      int length = this.transfer.readInt();
      if (verifyMac) {
        this.transfer.verifyLobMac(hmac, lobId);
      }
      if (in == null) {
        throw DbException.get(90007);
      }
      if (in.getPos() != offset)
      {
        LobStorageInterface lobStorage = this.session.getDataHandler().getLobStorage();
        
        ValueLobDb lob = ValueLobDb.create(15, null, -1, lobId, hmac, -1L);
        InputStream lobIn = lobStorage.getInputStream(lob, hmac, -1L);
        in = new CachedInputStream(lobIn);
        this.lobs.put(Long.valueOf(lobId), in);
        lobIn.skip(offset);
      }
      length = Math.min(65536, length);
      byte[] buff = new byte[length];
      length = IOUtils.readFully(in, buff, length);
      this.transfer.writeInt(1);
      this.transfer.writeInt(length);
      this.transfer.writeBytes(buff, 0, length);
      this.transfer.flush();
      break;
    case 13: 
    case 14: 
    default: 
      trace("Unknown operation: " + operation);
      closeSession();
      close();
    }
  }
  
  private int getState(int oldModificationId)
  {
    if (this.session.getModificationId() == oldModificationId) {
      return 1;
    }
    return 3;
  }
  
  private void sendRow(ResultInterface result)
    throws IOException
  {
    if (result.next())
    {
      this.transfer.writeBoolean(true);
      Value[] v = result.currentRow();
      for (int i = 0; i < result.getVisibleColumnCount(); i++) {
        if (this.clientVersion >= 12) {
          this.transfer.writeValue(v[i]);
        } else {
          writeValue(v[i]);
        }
      }
    }
    else
    {
      this.transfer.writeBoolean(false);
    }
  }
  
  private void writeValue(Value v)
    throws IOException
  {
    if (((v.getType() == 16) || (v.getType() == 15)) && 
      ((v instanceof ValueLobDb)))
    {
      ValueLobDb lob = (ValueLobDb)v;
      if (lob.isStored())
      {
        long id = lob.getLobId();
        this.lobs.put(Long.valueOf(id), new CachedInputStream(null));
      }
    }
    this.transfer.writeValue(v);
  }
  
  void setThread(Thread thread)
  {
    this.thread = thread;
  }
  
  Thread getThread()
  {
    return this.thread;
  }
  
  void cancelStatement(String targetSessionId, int statementId)
  {
    if (StringUtils.equals(targetSessionId, this.sessionId))
    {
      Command cmd = (Command)this.cache.getObject(statementId, false);
      cmd.cancel();
    }
  }
  
  static class CachedInputStream
    extends FilterInputStream
  {
    private static final ByteArrayInputStream DUMMY = new ByteArrayInputStream(new byte[0]);
    private long pos;
    
    CachedInputStream(InputStream in)
    {
      super();
      if (in == null) {
        this.pos = -1L;
      }
    }
    
    public int read(byte[] buff, int off, int len)
      throws IOException
    {
      len = super.read(buff, off, len);
      if (len > 0) {
        this.pos += len;
      }
      return len;
    }
    
    public int read()
      throws IOException
    {
      int x = this.in.read();
      if (x >= 0) {
        this.pos += 1L;
      }
      return x;
    }
    
    public long skip(long n)
      throws IOException
    {
      n = super.skip(n);
      if (n > 0L) {
        this.pos += n;
      }
      return n;
    }
    
    public long getPos()
    {
      return this.pos;
    }
  }
}
