package org.h2.command;

import java.io.IOException;
import java.util.ArrayList;
import org.h2.engine.SessionRemote;
import org.h2.engine.SysProperties;
import org.h2.expression.ParameterInterface;
import org.h2.expression.ParameterRemote;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.ResultInterface;
import org.h2.result.ResultRemote;
import org.h2.util.New;
import org.h2.value.Transfer;
import org.h2.value.Value;

public class CommandRemote
  implements CommandInterface
{
  private final ArrayList<Transfer> transferList;
  private final ArrayList<ParameterInterface> parameters;
  private final Trace trace;
  private final String sql;
  private final int fetchSize;
  private SessionRemote session;
  private int id;
  private boolean isQuery;
  private boolean readonly;
  private final int created;
  
  public CommandRemote(SessionRemote session, ArrayList<Transfer> transferList, String sql, int fetchSize)
  {
    this.transferList = transferList;
    this.trace = session.getTrace();
    this.sql = sql;
    this.parameters = New.arrayList();
    prepare(session, true);
    
    this.session = session;
    this.fetchSize = fetchSize;
    this.created = session.getLastReconnect();
  }
  
  private void prepare(SessionRemote s, boolean createParams)
  {
    this.id = s.getNextId();
    int i = 0;
    for (int count = 0; i < this.transferList.size(); i++) {
      try
      {
        Transfer transfer = (Transfer)this.transferList.get(i);
        if (createParams)
        {
          s.traceOperation("SESSION_PREPARE_READ_PARAMS", this.id);
          transfer.writeInt(11).writeInt(this.id).writeString(this.sql);
        }
        else
        {
          s.traceOperation("SESSION_PREPARE", this.id);
          transfer.writeInt(0).writeInt(this.id).writeString(this.sql);
        }
        s.done(transfer);
        this.isQuery = transfer.readBoolean();
        this.readonly = transfer.readBoolean();
        int paramCount = transfer.readInt();
        if (createParams)
        {
          this.parameters.clear();
          for (int j = 0; j < paramCount; j++)
          {
            ParameterRemote p = new ParameterRemote(j);
            p.readMetaData(transfer);
            this.parameters.add(p);
          }
        }
      }
      catch (IOException e)
      {
        s.removeServer(e, i--, ++count);
      }
    }
  }
  
  public boolean isQuery()
  {
    return this.isQuery;
  }
  
  public ArrayList<ParameterInterface> getParameters()
  {
    return this.parameters;
  }
  
  private void prepareIfRequired()
  {
    if (this.session.getLastReconnect() != this.created) {
      this.id = Integer.MIN_VALUE;
    }
    this.session.checkClosed();
    if (this.id <= this.session.getCurrentId() - SysProperties.SERVER_CACHED_OBJECTS) {
      prepare(this.session, false);
    }
  }
  
  public ResultInterface getMetaData()
  {
    synchronized (this.session)
    {
      if (!this.isQuery) {
        return null;
      }
      int objectId = this.session.getNextId();
      ResultRemote result = null;
      int i = 0;
      for (int count = 0; i < this.transferList.size(); i++)
      {
        prepareIfRequired();
        Transfer transfer = (Transfer)this.transferList.get(i);
        try
        {
          this.session.traceOperation("COMMAND_GET_META_DATA", this.id);
          transfer.writeInt(10).writeInt(this.id).writeInt(objectId);
          
          this.session.done(transfer);
          int columnCount = transfer.readInt();
          result = new ResultRemote(this.session, transfer, objectId, columnCount, Integer.MAX_VALUE);
        }
        catch (IOException e)
        {
          this.session.removeServer(e, i--, ++count);
        }
      }
      this.session.autoCommitIfCluster();
      return result;
    }
  }
  
  public ResultInterface executeQuery(int maxRows, boolean scrollable)
  {
    checkParameters();
    synchronized (this.session)
    {
      int objectId = this.session.getNextId();
      ResultRemote result = null;
      int i = 0;
      for (int count = 0; i < this.transferList.size(); i++)
      {
        prepareIfRequired();
        Transfer transfer = (Transfer)this.transferList.get(i);
        try
        {
          this.session.traceOperation("COMMAND_EXECUTE_QUERY", this.id);
          transfer.writeInt(2).writeInt(this.id).writeInt(objectId).writeInt(maxRows);
          int fetch;
          int fetch;
          if ((this.session.isClustered()) || (scrollable)) {
            fetch = Integer.MAX_VALUE;
          } else {
            fetch = this.fetchSize;
          }
          transfer.writeInt(fetch);
          sendParameters(transfer);
          this.session.done(transfer);
          int columnCount = transfer.readInt();
          if (result != null)
          {
            result.close();
            result = null;
          }
          result = new ResultRemote(this.session, transfer, objectId, columnCount, fetch);
          if (this.readonly) {
            break;
          }
        }
        catch (IOException e)
        {
          this.session.removeServer(e, i--, ++count);
        }
      }
      this.session.autoCommitIfCluster();
      this.session.readSessionState();
      return result;
    }
  }
  
  public int executeUpdate()
  {
    checkParameters();
    synchronized (this.session)
    {
      int updateCount = 0;
      boolean autoCommit = false;
      int i = 0;
      for (int count = 0; i < this.transferList.size(); i++)
      {
        prepareIfRequired();
        Transfer transfer = (Transfer)this.transferList.get(i);
        try
        {
          this.session.traceOperation("COMMAND_EXECUTE_UPDATE", this.id);
          transfer.writeInt(3).writeInt(this.id);
          sendParameters(transfer);
          this.session.done(transfer);
          updateCount = transfer.readInt();
          autoCommit = transfer.readBoolean();
        }
        catch (IOException e)
        {
          this.session.removeServer(e, i--, ++count);
        }
      }
      this.session.setAutoCommitFromServer(autoCommit);
      this.session.autoCommitIfCluster();
      this.session.readSessionState();
      return updateCount;
    }
  }
  
  private void checkParameters()
  {
    for (ParameterInterface p : this.parameters) {
      p.checkSet();
    }
  }
  
  private void sendParameters(Transfer transfer)
    throws IOException
  {
    int len = this.parameters.size();
    transfer.writeInt(len);
    for (ParameterInterface p : this.parameters) {
      transfer.writeValue(p.getParamValue());
    }
  }
  
  public void close()
  {
    if ((this.session == null) || (this.session.isClosed())) {
      return;
    }
    synchronized (this.session)
    {
      this.session.traceOperation("COMMAND_CLOSE", this.id);
      for (Transfer transfer : this.transferList) {
        try
        {
          transfer.writeInt(4).writeInt(this.id);
        }
        catch (IOException e)
        {
          this.trace.error(e, "close");
        }
      }
    }
    this.session = null;
    try
    {
      for (ParameterInterface p : this.parameters)
      {
        Value v = p.getParamValue();
        if (v != null) {
          v.close();
        }
      }
    }
    catch (DbException e)
    {
      this.trace.error(e, "close");
    }
    this.parameters.clear();
  }
  
  public void cancel()
  {
    this.session.cancelStatement(this.id);
  }
  
  public String toString()
  {
    return this.sql + Trace.formatParams(getParameters());
  }
  
  public int getCommandType()
  {
    return 0;
  }
}
