package org.h2.result;

import java.io.IOException;
import java.util.ArrayList;
import org.h2.engine.SessionRemote;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.util.New;
import org.h2.value.Transfer;
import org.h2.value.Value;

public class ResultRemote
  implements ResultInterface
{
  private int fetchSize;
  private SessionRemote session;
  private Transfer transfer;
  private int id;
  private final ResultColumn[] columns;
  private Value[] currentRow;
  private final int rowCount;
  private int rowId;
  private int rowOffset;
  private ArrayList<Value[]> result;
  private final Trace trace;
  
  public ResultRemote(SessionRemote session, Transfer transfer, int id, int columnCount, int fetchSize)
    throws IOException
  {
    this.session = session;
    this.trace = session.getTrace();
    this.transfer = transfer;
    this.id = id;
    this.columns = new ResultColumn[columnCount];
    this.rowCount = transfer.readInt();
    for (int i = 0; i < columnCount; i++) {
      this.columns[i] = new ResultColumn(transfer);
    }
    this.rowId = -1;
    this.result = New.arrayList();
    this.fetchSize = fetchSize;
    fetchRows(false);
  }
  
  public String getAlias(int i)
  {
    return this.columns[i].alias;
  }
  
  public String getSchemaName(int i)
  {
    return this.columns[i].schemaName;
  }
  
  public String getTableName(int i)
  {
    return this.columns[i].tableName;
  }
  
  public String getColumnName(int i)
  {
    return this.columns[i].columnName;
  }
  
  public int getColumnType(int i)
  {
    return this.columns[i].columnType;
  }
  
  public long getColumnPrecision(int i)
  {
    return this.columns[i].precision;
  }
  
  public int getColumnScale(int i)
  {
    return this.columns[i].scale;
  }
  
  public int getDisplaySize(int i)
  {
    return this.columns[i].displaySize;
  }
  
  public boolean isAutoIncrement(int i)
  {
    return this.columns[i].autoIncrement;
  }
  
  public int getNullable(int i)
  {
    return this.columns[i].nullable;
  }
  
  public void reset()
  {
    this.rowId = -1;
    this.currentRow = null;
    if (this.session == null) {
      return;
    }
    synchronized (this.session)
    {
      this.session.checkClosed();
      try
      {
        this.session.traceOperation("RESULT_RESET", this.id);
        this.transfer.writeInt(6).writeInt(this.id).flush();
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, null);
      }
    }
  }
  
  public Value[] currentRow()
  {
    return this.currentRow;
  }
  
  public boolean next()
  {
    if (this.rowId < this.rowCount)
    {
      this.rowId += 1;
      remapIfOld();
      if (this.rowId < this.rowCount)
      {
        if (this.rowId - this.rowOffset >= this.result.size()) {
          fetchRows(true);
        }
        this.currentRow = ((Value[])this.result.get(this.rowId - this.rowOffset));
        return true;
      }
      this.currentRow = null;
    }
    return false;
  }
  
  public int getRowId()
  {
    return this.rowId;
  }
  
  public int getVisibleColumnCount()
  {
    return this.columns.length;
  }
  
  public int getRowCount()
  {
    return this.rowCount;
  }
  
  private void sendClose()
  {
    if (this.session == null) {
      return;
    }
    try
    {
      synchronized (this.session)
      {
        this.session.traceOperation("RESULT_CLOSE", this.id);
        this.transfer.writeInt(7).writeInt(this.id);
      }
    }
    catch (IOException e)
    {
      this.trace.error(e, "close");
    }
    finally
    {
      this.transfer = null;
      this.session = null;
    }
  }
  
  public void close()
  {
    this.result = null;
    sendClose();
  }
  
  private void remapIfOld()
  {
    if (this.session == null) {
      return;
    }
    try
    {
      if (this.id <= this.session.getCurrentId() - SysProperties.SERVER_CACHED_OBJECTS / 2)
      {
        int newId = this.session.getNextId();
        this.session.traceOperation("CHANGE_ID", this.id);
        this.transfer.writeInt(9).writeInt(this.id).writeInt(newId);
        this.id = newId;
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  private void fetchRows(boolean sendFetch)
  {
    synchronized (this.session)
    {
      this.session.checkClosed();
      try
      {
        this.rowOffset += this.result.size();
        this.result.clear();
        int fetch = Math.min(this.fetchSize, this.rowCount - this.rowOffset);
        if (sendFetch)
        {
          this.session.traceOperation("RESULT_FETCH_ROWS", this.id);
          this.transfer.writeInt(5).writeInt(this.id).writeInt(fetch);
          
          this.session.done(this.transfer);
        }
        for (int r = 0; r < fetch; r++)
        {
          boolean row = this.transfer.readBoolean();
          if (!row) {
            break;
          }
          int len = this.columns.length;
          Value[] values = new Value[len];
          for (int i = 0; i < len; i++)
          {
            Value v = this.transfer.readValue();
            values[i] = v;
          }
          this.result.add(values);
        }
        if (this.rowOffset + this.result.size() >= this.rowCount) {
          sendClose();
        }
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, null);
      }
    }
  }
  
  public String toString()
  {
    return "columns: " + this.columns.length + " rows: " + this.rowCount + " pos: " + this.rowId;
  }
  
  public int getFetchSize()
  {
    return this.fetchSize;
  }
  
  public void setFetchSize(int fetchSize)
  {
    this.fetchSize = fetchSize;
  }
  
  public boolean needToClose()
  {
    return true;
  }
}
