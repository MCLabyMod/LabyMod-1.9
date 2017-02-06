package org.h2.engine;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import org.h2.api.DatabaseEventListener;
import org.h2.api.JavaObjectSerializer;
import org.h2.command.CommandInterface;
import org.h2.command.CommandRemote;
import org.h2.jdbc.JdbcSQLException;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceSystem;
import org.h2.result.ResultInterface;
import org.h2.store.DataHandler;
import org.h2.store.FileStore;
import org.h2.store.LobStorageFrontend;
import org.h2.store.LobStorageInterface;
import org.h2.store.fs.FileUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.NetUtils;
import org.h2.util.New;
import org.h2.util.SmallLRUCache;
import org.h2.util.StringUtils;
import org.h2.util.TempFileDeleter;
import org.h2.value.Transfer;
import org.h2.value.Value;

public class SessionRemote
  extends SessionWithState
  implements DataHandler
{
  public static final int SESSION_PREPARE = 0;
  public static final int SESSION_CLOSE = 1;
  public static final int COMMAND_EXECUTE_QUERY = 2;
  public static final int COMMAND_EXECUTE_UPDATE = 3;
  public static final int COMMAND_CLOSE = 4;
  public static final int RESULT_FETCH_ROWS = 5;
  public static final int RESULT_RESET = 6;
  public static final int RESULT_CLOSE = 7;
  public static final int COMMAND_COMMIT = 8;
  public static final int CHANGE_ID = 9;
  public static final int COMMAND_GET_META_DATA = 10;
  public static final int SESSION_PREPARE_READ_PARAMS = 11;
  public static final int SESSION_SET_ID = 12;
  public static final int SESSION_CANCEL_STATEMENT = 13;
  public static final int SESSION_CHECK_KEY = 14;
  public static final int SESSION_SET_AUTOCOMMIT = 15;
  public static final int SESSION_HAS_PENDING_TRANSACTION = 16;
  public static final int LOB_READ = 17;
  public static final int STATUS_ERROR = 0;
  public static final int STATUS_OK = 1;
  public static final int STATUS_CLOSED = 2;
  public static final int STATUS_OK_STATE_CHANGED = 3;
  private static SessionFactory sessionFactory;
  private TraceSystem traceSystem;
  private Trace trace;
  private ArrayList<Transfer> transferList = New.arrayList();
  private int nextId;
  private boolean autoCommit = true;
  private CommandInterface autoCommitFalse;
  private CommandInterface autoCommitTrue;
  private ConnectionInfo connectionInfo;
  private String databaseName;
  private String cipher;
  private byte[] fileEncryptionKey;
  private final Object lobSyncObject = new Object();
  private String sessionId;
  private int clientVersion;
  private boolean autoReconnect;
  private int lastReconnect;
  private SessionInterface embedded;
  private DatabaseEventListener eventListener;
  private LobStorageFrontend lobStorage;
  private boolean cluster;
  private TempFileDeleter tempFileDeleter;
  private JavaObjectSerializer javaObjectSerializer;
  private volatile boolean javaObjectSerializerInitialized;
  
  public SessionRemote(ConnectionInfo ci)
  {
    this.connectionInfo = ci;
  }
  
  public ArrayList<String> getClusterServers()
  {
    ArrayList<String> serverList = new ArrayList();
    for (int i = 0; i < this.transferList.size(); i++)
    {
      Transfer transfer = (Transfer)this.transferList.get(i);
      serverList.add(transfer.getSocket().getInetAddress().getHostAddress() + ":" + transfer.getSocket().getPort());
    }
    return serverList;
  }
  
  private Transfer initTransfer(ConnectionInfo ci, String db, String server)
    throws IOException
  {
    Socket socket = NetUtils.createSocket(server, 9092, ci.isSSL());
    
    Transfer trans = new Transfer(this);
    trans.setSocket(socket);
    trans.setSSL(ci.isSSL());
    trans.init();
    trans.writeInt(6);
    trans.writeInt(15);
    trans.writeString(db);
    trans.writeString(ci.getOriginalURL());
    trans.writeString(ci.getUserName());
    trans.writeBytes(ci.getUserPasswordHash());
    trans.writeBytes(ci.getFilePasswordHash());
    String[] keys = ci.getKeys();
    trans.writeInt(keys.length);
    for (String key : keys) {
      trans.writeString(key).writeString(ci.getProperty(key));
    }
    try
    {
      done(trans);
      this.clientVersion = trans.readInt();
      trans.setVersion(this.clientVersion);
      if ((this.clientVersion >= 14) && 
        (ci.getFileEncryptionKey() != null)) {
        trans.writeBytes(ci.getFileEncryptionKey());
      }
      trans.writeInt(12);
      trans.writeString(this.sessionId);
      done(trans);
      if (this.clientVersion >= 15) {
        this.autoCommit = trans.readBoolean();
      } else {
        this.autoCommit = true;
      }
      return trans;
    }
    catch (DbException e)
    {
      trans.close();
      throw e;
    }
  }
  
  public boolean hasPendingTransaction()
  {
    if (this.clientVersion < 10) {
      return true;
    }
    int i = 0;
    for (int count = 0; i < this.transferList.size(); i++)
    {
      Transfer transfer = (Transfer)this.transferList.get(i);
      try
      {
        traceOperation("SESSION_HAS_PENDING_TRANSACTION", 0);
        transfer.writeInt(16);
        
        done(transfer);
        return transfer.readInt() != 0;
      }
      catch (IOException e)
      {
        removeServer(e, i--, ++count);
      }
    }
    return true;
  }
  
  public void cancel() {}
  
  public void cancelStatement(int id)
  {
    for (Transfer transfer : this.transferList) {
      try
      {
        Transfer trans = transfer.openNewConnection();
        trans.init();
        trans.writeInt(this.clientVersion);
        trans.writeInt(this.clientVersion);
        trans.writeString(null);
        trans.writeString(null);
        trans.writeString(this.sessionId);
        trans.writeInt(13);
        trans.writeInt(id);
        trans.close();
      }
      catch (IOException e)
      {
        this.trace.debug(e, "could not cancel statement");
      }
    }
  }
  
  private void checkClusterDisableAutoCommit(String serverList)
  {
    if ((this.autoCommit) && (this.transferList.size() > 1))
    {
      setAutoCommitSend(false);
      CommandInterface c = prepareCommand("SET CLUSTER " + serverList, Integer.MAX_VALUE);
      
      c.executeUpdate();
      
      this.autoCommit = true;
      this.cluster = true;
    }
  }
  
  public boolean getAutoCommit()
  {
    return this.autoCommit;
  }
  
  public void setAutoCommit(boolean autoCommit)
  {
    if (!this.cluster) {
      setAutoCommitSend(autoCommit);
    }
    this.autoCommit = autoCommit;
  }
  
  public void setAutoCommitFromServer(boolean autoCommit)
  {
    if (this.cluster)
    {
      if (autoCommit)
      {
        setAutoCommitSend(false);
        this.autoCommit = true;
      }
    }
    else {
      this.autoCommit = autoCommit;
    }
  }
  
  private void setAutoCommitSend(boolean autoCommit)
  {
    if (this.clientVersion >= 8)
    {
      int i = 0;
      for (int count = 0; i < this.transferList.size(); i++)
      {
        Transfer transfer = (Transfer)this.transferList.get(i);
        try
        {
          traceOperation("SESSION_SET_AUTOCOMMIT", autoCommit ? 1 : 0);
          transfer.writeInt(15).writeBoolean(autoCommit);
          
          done(transfer);
        }
        catch (IOException e)
        {
          removeServer(e, i--, ++count);
        }
      }
    }
    else if (autoCommit)
    {
      if (this.autoCommitTrue == null) {
        this.autoCommitTrue = prepareCommand("SET AUTOCOMMIT TRUE", Integer.MAX_VALUE);
      }
      this.autoCommitTrue.executeUpdate();
    }
    else
    {
      if (this.autoCommitFalse == null) {
        this.autoCommitFalse = prepareCommand("SET AUTOCOMMIT FALSE", Integer.MAX_VALUE);
      }
      this.autoCommitFalse.executeUpdate();
    }
  }
  
  public void autoCommitIfCluster()
  {
    if ((this.autoCommit) && (this.cluster))
    {
      int i = 0;
      for (int count = 0; i < this.transferList.size(); i++)
      {
        Transfer transfer = (Transfer)this.transferList.get(i);
        try
        {
          traceOperation("COMMAND_COMMIT", 0);
          transfer.writeInt(8);
          done(transfer);
        }
        catch (IOException e)
        {
          removeServer(e, i--, ++count);
        }
      }
    }
  }
  
  private String getFilePrefix(String dir)
  {
    StringBuilder buff = new StringBuilder(dir);
    buff.append('/');
    for (int i = 0; i < this.databaseName.length(); i++)
    {
      char ch = this.databaseName.charAt(i);
      if (Character.isLetterOrDigit(ch)) {
        buff.append(ch);
      } else {
        buff.append('_');
      }
    }
    return buff.toString();
  }
  
  public int getPowerOffCount()
  {
    return 0;
  }
  
  public void setPowerOffCount(int count)
  {
    throw DbException.getUnsupportedException("remote");
  }
  
  public SessionInterface connectEmbeddedOrServer(boolean openNew)
  {
    ConnectionInfo ci = this.connectionInfo;
    if (ci.isRemote())
    {
      connectServer(ci);
      return this;
    }
    boolean autoServerMode = Boolean.parseBoolean(ci.getProperty("AUTO_SERVER", "false"));
    
    ConnectionInfo backup = null;
    try
    {
      if (autoServerMode)
      {
        backup = ci.clone();
        this.connectionInfo = ci.clone();
      }
      if (openNew) {
        ci.setProperty("OPEN_NEW", "true");
      }
      if (sessionFactory == null) {
        sessionFactory = (SessionFactory)Class.forName("org.h2.engine.Engine").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
      }
      return sessionFactory.createSession(ci);
    }
    catch (Exception re)
    {
      DbException e = DbException.convert(re);
      if ((e.getErrorCode() == 90020) && 
        (autoServerMode))
      {
        String serverKey = ((JdbcSQLException)e.getSQLException()).getSQL();
        if (serverKey != null)
        {
          backup.setServerKey(serverKey);
          
          backup.removeProperty("OPEN_NEW", null);
          connectServer(backup);
          return this;
        }
      }
      throw e;
    }
  }
  
  private void connectServer(ConnectionInfo ci)
  {
    String name = ci.getName();
    if (name.startsWith("//")) {
      name = name.substring("//".length());
    }
    int idx = name.indexOf('/');
    if (idx < 0) {
      throw ci.getFormatException();
    }
    this.databaseName = name.substring(idx + 1);
    String server = name.substring(0, idx);
    this.traceSystem = new TraceSystem(null);
    String traceLevelFile = ci.getProperty(10, null);
    if (traceLevelFile != null)
    {
      int level = Integer.parseInt(traceLevelFile);
      String prefix = getFilePrefix(SysProperties.CLIENT_TRACE_DIRECTORY);
      try
      {
        this.traceSystem.setLevelFile(level);
        if ((level > 0) && (level < 4))
        {
          String file = FileUtils.createTempFile(prefix, ".trace.db", false, false);
          
          this.traceSystem.setFileName(file);
        }
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, prefix);
      }
    }
    String traceLevelSystemOut = ci.getProperty(9, null);
    if (traceLevelSystemOut != null)
    {
      int level = Integer.parseInt(traceLevelSystemOut);
      this.traceSystem.setLevelSystemOut(level);
    }
    this.trace = this.traceSystem.getTrace("jdbc");
    String serverList = null;
    if (server.indexOf(',') >= 0)
    {
      serverList = StringUtils.quoteStringSQL(server);
      ci.setProperty("CLUSTER", "TRUE");
    }
    this.autoReconnect = Boolean.parseBoolean(ci.getProperty("AUTO_RECONNECT", "false"));
    
    boolean autoServer = Boolean.parseBoolean(ci.getProperty("AUTO_SERVER", "false"));
    if ((autoServer) && (serverList != null)) {
      throw DbException.getUnsupportedException("autoServer && serverList != null");
    }
    this.autoReconnect |= autoServer;
    if (this.autoReconnect)
    {
      String className = ci.getProperty("DATABASE_EVENT_LISTENER");
      if (className != null)
      {
        className = StringUtils.trim(className, true, true, "'");
        try
        {
          this.eventListener = ((DatabaseEventListener)JdbcUtils.loadUserClass(className).newInstance());
        }
        catch (Throwable e)
        {
          throw DbException.convert(e);
        }
      }
    }
    this.cipher = ci.getProperty("CIPHER");
    if (this.cipher != null) {
      this.fileEncryptionKey = MathUtils.secureRandomBytes(32);
    }
    String[] servers = StringUtils.arraySplit(server, ',', true);
    int len = servers.length;
    this.transferList.clear();
    this.sessionId = StringUtils.convertBytesToHex(MathUtils.secureRandomBytes(32));
    
    boolean switchOffCluster = false;
    try
    {
      for (int i = 0; i < len; i++)
      {
        String s = servers[i];
        try
        {
          Transfer trans = initTransfer(ci, this.databaseName, s);
          this.transferList.add(trans);
        }
        catch (IOException e)
        {
          if (len == 1) {
            throw DbException.get(90067, e, new String[] { e + ": " + s });
          }
          switchOffCluster = true;
        }
      }
      checkClosed();
      if (switchOffCluster) {
        switchOffCluster();
      }
      checkClusterDisableAutoCommit(serverList);
    }
    catch (DbException e)
    {
      this.traceSystem.close();
      throw e;
    }
  }
  
  private void switchOffCluster()
  {
    CommandInterface ci = prepareCommand("SET CLUSTER ''", Integer.MAX_VALUE);
    ci.executeUpdate();
  }
  
  public void removeServer(IOException e, int i, int count)
  {
    this.trace.debug(e, "removing server because of exception");
    this.transferList.remove(i);
    if ((this.transferList.size() == 0) && (autoReconnect(count))) {
      return;
    }
    checkClosed();
    switchOffCluster();
  }
  
  public synchronized CommandInterface prepareCommand(String sql, int fetchSize)
  {
    checkClosed();
    return new CommandRemote(this, this.transferList, sql, fetchSize);
  }
  
  private boolean autoReconnect(int count)
  {
    if (!isClosed()) {
      return false;
    }
    if (!this.autoReconnect) {
      return false;
    }
    if ((!this.cluster) && (!this.autoCommit)) {
      return false;
    }
    if (count > SysProperties.MAX_RECONNECT) {
      return false;
    }
    this.lastReconnect += 1;
    for (;;)
    {
      try
      {
        this.embedded = connectEmbeddedOrServer(false);
      }
      catch (DbException e)
      {
        if (e.getErrorCode() != 90135) {
          throw e;
        }
        try
        {
          Thread.sleep(500L);
        }
        catch (Exception e2) {}
      }
    }
    if (this.embedded == this) {
      this.embedded = null;
    } else {
      connectEmbeddedOrServer(true);
    }
    recreateSessionState();
    if (this.eventListener != null) {
      this.eventListener.setProgress(4, this.databaseName, count, SysProperties.MAX_RECONNECT);
    }
    return true;
  }
  
  public void checkClosed()
  {
    if (isClosed()) {
      throw DbException.get(90067, "session closed");
    }
  }
  
  public void close()
  {
    RuntimeException closeError = null;
    if (this.transferList != null)
    {
      synchronized (this)
      {
        for (Transfer transfer : this.transferList) {
          try
          {
            traceOperation("SESSION_CLOSE", 0);
            transfer.writeInt(1);
            done(transfer);
            transfer.close();
          }
          catch (RuntimeException e)
          {
            this.trace.error(e, "close");
            closeError = e;
          }
          catch (Exception e)
          {
            this.trace.error(e, "close");
          }
        }
      }
      this.transferList = null;
    }
    this.traceSystem.close();
    if (this.embedded != null)
    {
      this.embedded.close();
      this.embedded = null;
    }
    if (closeError != null) {
      throw closeError;
    }
  }
  
  public Trace getTrace()
  {
    return this.traceSystem.getTrace("jdbc");
  }
  
  public int getNextId()
  {
    return this.nextId++;
  }
  
  public int getCurrentId()
  {
    return this.nextId;
  }
  
  public void done(Transfer transfer)
    throws IOException
  {
    transfer.flush();
    int status = transfer.readInt();
    if (status == 0)
    {
      String sqlstate = transfer.readString();
      String message = transfer.readString();
      String sql = transfer.readString();
      int errorCode = transfer.readInt();
      String stackTrace = transfer.readString();
      JdbcSQLException s = new JdbcSQLException(message, sql, sqlstate, errorCode, null, stackTrace);
      if (errorCode == 90067)
      {
        IOException e = new IOException(s.toString(), s);
        throw e;
      }
      throw DbException.convert(s);
    }
    if (status == 2) {
      this.transferList = null;
    } else if (status == 3) {
      this.sessionStateChanged = true;
    } else if (status != 1) {
      throw DbException.get(90067, "unexpected status " + status);
    }
  }
  
  public boolean isClustered()
  {
    return this.cluster;
  }
  
  public boolean isClosed()
  {
    return (this.transferList == null) || (this.transferList.size() == 0);
  }
  
  public void traceOperation(String operation, int id)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} {1}", new Object[] { operation, Integer.valueOf(id) });
    }
  }
  
  public void checkPowerOff() {}
  
  public void checkWritingAllowed() {}
  
  public String getDatabasePath()
  {
    return "";
  }
  
  public String getLobCompressionAlgorithm(int type)
  {
    return null;
  }
  
  public int getMaxLengthInplaceLob()
  {
    return SysProperties.LOB_CLIENT_MAX_SIZE_MEMORY;
  }
  
  public FileStore openFile(String name, String mode, boolean mustExist)
  {
    if ((mustExist) && (!FileUtils.exists(name))) {
      throw DbException.get(90124, name);
    }
    FileStore store;
    FileStore store;
    if (this.cipher == null) {
      store = FileStore.open(this, name, mode);
    } else {
      store = FileStore.open(this, name, mode, this.cipher, this.fileEncryptionKey, 0);
    }
    store.setCheckedWriting(false);
    try
    {
      store.init();
    }
    catch (DbException e)
    {
      store.closeSilently();
      throw e;
    }
    return store;
  }
  
  public DataHandler getDataHandler()
  {
    return this;
  }
  
  public Object getLobSyncObject()
  {
    return this.lobSyncObject;
  }
  
  public SmallLRUCache<String, String[]> getLobFileListCache()
  {
    return null;
  }
  
  public int getLastReconnect()
  {
    return this.lastReconnect;
  }
  
  public TempFileDeleter getTempFileDeleter()
  {
    if (this.tempFileDeleter == null) {
      this.tempFileDeleter = TempFileDeleter.getInstance();
    }
    return this.tempFileDeleter;
  }
  
  public boolean isReconnectNeeded(boolean write)
  {
    return false;
  }
  
  public SessionInterface reconnect(boolean write)
  {
    return this;
  }
  
  public void afterWriting() {}
  
  public LobStorageInterface getLobStorage()
  {
    if (this.lobStorage == null) {
      this.lobStorage = new LobStorageFrontend(this);
    }
    return this.lobStorage;
  }
  
  public synchronized int readLob(long lobId, byte[] hmac, long offset, byte[] buff, int off, int length)
  {
    checkClosed();
    int i = 0;
    for (int count = 0; i < this.transferList.size(); i++)
    {
      Transfer transfer = (Transfer)this.transferList.get(i);
      try
      {
        traceOperation("LOB_READ", (int)lobId);
        transfer.writeInt(17);
        transfer.writeLong(lobId);
        if (this.clientVersion >= 12) {
          transfer.writeBytes(hmac);
        }
        transfer.writeLong(offset);
        transfer.writeInt(length);
        done(transfer);
        length = transfer.readInt();
        if (length <= 0) {
          return length;
        }
        transfer.readBytes(buff, off, length);
        return length;
      }
      catch (IOException e)
      {
        removeServer(e, i--, ++count);
      }
    }
    return 1;
  }
  
  public JavaObjectSerializer getJavaObjectSerializer()
  {
    initJavaObjectSerializer();
    return this.javaObjectSerializer;
  }
  
  private void initJavaObjectSerializer()
  {
    if (this.javaObjectSerializerInitialized) {
      return;
    }
    synchronized (this)
    {
      if (this.javaObjectSerializerInitialized) {
        return;
      }
      String serializerFQN = readSerializationSettings();
      if (serializerFQN != null)
      {
        serializerFQN = serializerFQN.trim();
        if ((!serializerFQN.isEmpty()) && (!serializerFQN.equals("null"))) {
          try
          {
            this.javaObjectSerializer = ((JavaObjectSerializer)JdbcUtils.loadUserClass(serializerFQN).newInstance());
          }
          catch (Exception e)
          {
            throw DbException.convert(e);
          }
        }
      }
      this.javaObjectSerializerInitialized = true;
    }
  }
  
  private String readSerializationSettings()
  {
    String javaObjectSerializerFQN = null;
    CommandInterface ci = prepareCommand("SELECT VALUE FROM INFORMATION_SCHEMA.SETTINGS  WHERE NAME='JAVA_OBJECT_SERIALIZER'", Integer.MAX_VALUE);
    try
    {
      ResultInterface result = ci.executeQuery(0, false);
      if (result.next())
      {
        Value[] row = result.currentRow();
        javaObjectSerializerFQN = row[0].getString();
      }
    }
    finally
    {
      ci.close();
    }
    return javaObjectSerializerFQN;
  }
  
  public void addTemporaryLob(Value v) {}
}
