package org.h2.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.util.New;
import org.h2.util.ValueHashMap;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;

public class LocalResult
  implements ResultInterface, ResultTarget
{
  private int maxMemoryRows;
  private Session session;
  private int visibleColumnCount;
  private Expression[] expressions;
  private int rowId;
  private int rowCount;
  private ArrayList<Value[]> rows;
  private SortOrder sort;
  private ValueHashMap<Value[]> distinctRows;
  private Value[] currentRow;
  private int offset;
  private int limit = -1;
  private ResultExternal external;
  private int diskOffset;
  private boolean distinct;
  private boolean randomAccess;
  private boolean closed;
  private boolean containsLobs;
  
  public LocalResult() {}
  
  public LocalResult(Session session, Expression[] expressions, int visibleColumnCount)
  {
    this.session = session;
    if (session == null)
    {
      this.maxMemoryRows = Integer.MAX_VALUE;
    }
    else
    {
      Database db = session.getDatabase();
      if ((db.isPersistent()) && (!db.isReadOnly())) {
        this.maxMemoryRows = session.getDatabase().getMaxMemoryRows();
      } else {
        this.maxMemoryRows = Integer.MAX_VALUE;
      }
    }
    this.rows = New.arrayList();
    this.visibleColumnCount = visibleColumnCount;
    this.rowId = -1;
    this.expressions = expressions;
  }
  
  public void setMaxMemoryRows(int maxValue)
  {
    this.maxMemoryRows = maxValue;
  }
  
  public static LocalResult read(Session session, ResultSet rs, int maxrows)
  {
    Expression[] cols = Expression.getExpressionColumns(session, rs);
    int columnCount = cols.length;
    LocalResult result = new LocalResult(session, cols, columnCount);
    try
    {
      for (int i = 0; ((maxrows == 0) || (i < maxrows)) && (rs.next()); i++)
      {
        Value[] list = new Value[columnCount];
        for (int j = 0; j < columnCount; j++)
        {
          int type = result.getColumnType(j);
          list[j] = DataType.readValue(session, rs, j + 1, type);
        }
        result.addRow(list);
      }
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    result.done();
    return result;
  }
  
  public LocalResult createShallowCopy(Session targetSession)
  {
    if ((this.external == null) && ((this.rows == null) || (this.rows.size() < this.rowCount))) {
      return null;
    }
    if (this.containsLobs) {
      return null;
    }
    ResultExternal e2 = null;
    if (this.external != null)
    {
      e2 = this.external.createShallowCopy();
      if (e2 == null) {
        return null;
      }
    }
    LocalResult copy = new LocalResult();
    copy.maxMemoryRows = this.maxMemoryRows;
    copy.session = targetSession;
    copy.visibleColumnCount = this.visibleColumnCount;
    copy.expressions = this.expressions;
    copy.rowId = -1;
    copy.rowCount = this.rowCount;
    copy.rows = this.rows;
    copy.sort = this.sort;
    copy.distinctRows = this.distinctRows;
    copy.distinct = this.distinct;
    copy.randomAccess = this.randomAccess;
    copy.currentRow = null;
    copy.offset = 0;
    copy.limit = -1;
    copy.external = e2;
    copy.diskOffset = this.diskOffset;
    return copy;
  }
  
  public void setSortOrder(SortOrder sort)
  {
    this.sort = sort;
  }
  
  public void setDistinct()
  {
    this.distinct = true;
    this.distinctRows = ValueHashMap.newInstance();
  }
  
  public void setRandomAccess()
  {
    this.randomAccess = true;
  }
  
  public void removeDistinct(Value[] values)
  {
    if (!this.distinct) {
      DbException.throwInternalError();
    }
    if (this.distinctRows != null)
    {
      ValueArray array = ValueArray.get(values);
      this.distinctRows.remove(array);
      this.rowCount = this.distinctRows.size();
    }
    else
    {
      this.rowCount = this.external.removeRow(values);
    }
  }
  
  public boolean containsDistinct(Value[] values)
  {
    if (this.external != null) {
      return this.external.contains(values);
    }
    if (this.distinctRows == null)
    {
      this.distinctRows = ValueHashMap.newInstance();
      for (Value[] row : this.rows)
      {
        if (row.length > this.visibleColumnCount)
        {
          Value[] r2 = new Value[this.visibleColumnCount];
          System.arraycopy(row, 0, r2, 0, this.visibleColumnCount);
          row = r2;
        }
        ValueArray array = ValueArray.get(row);
        this.distinctRows.put(array, row);
      }
    }
    ValueArray array = ValueArray.get(values);
    return this.distinctRows.get(array) != null;
  }
  
  public void reset()
  {
    this.rowId = -1;
    if (this.external != null)
    {
      this.external.reset();
      if (this.diskOffset > 0) {
        for (int i = 0; i < this.diskOffset; i++) {
          this.external.next();
        }
      }
    }
  }
  
  public Value[] currentRow()
  {
    return this.currentRow;
  }
  
  public boolean next()
  {
    if ((!this.closed) && (this.rowId < this.rowCount))
    {
      this.rowId += 1;
      if (this.rowId < this.rowCount)
      {
        if (this.external != null) {
          this.currentRow = this.external.next();
        } else {
          this.currentRow = ((Value[])this.rows.get(this.rowId));
        }
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
  
  private void cloneLobs(Value[] values)
  {
    for (int i = 0; i < values.length; i++)
    {
      Value v = values[i];
      Value v2 = v.copyToResult();
      if (v2 != v)
      {
        this.containsLobs = true;
        this.session.addTemporaryLob(v2);
        values[i] = v2;
      }
    }
  }
  
  public void addRow(Value[] values)
  {
    cloneLobs(values);
    if (this.distinct)
    {
      if (this.distinctRows != null)
      {
        ValueArray array = ValueArray.get(values);
        this.distinctRows.put(array, values);
        this.rowCount = this.distinctRows.size();
        if (this.rowCount > this.maxMemoryRows)
        {
          this.external = new ResultTempTable(this.session, this.expressions, true, this.sort);
          this.rowCount = this.external.addRows(this.distinctRows.values());
          this.distinctRows = null;
        }
      }
      else
      {
        this.rowCount = this.external.addRow(values);
      }
      return;
    }
    this.rows.add(values);
    this.rowCount += 1;
    if (this.rows.size() > this.maxMemoryRows)
    {
      if (this.external == null) {
        this.external = new ResultTempTable(this.session, this.expressions, false, this.sort);
      }
      addRowsToDisk();
    }
  }
  
  private void addRowsToDisk()
  {
    this.rowCount = this.external.addRows(this.rows);
    this.rows.clear();
  }
  
  public int getVisibleColumnCount()
  {
    return this.visibleColumnCount;
  }
  
  public void done()
  {
    if (this.distinct) {
      if (this.distinctRows != null)
      {
        this.rows = this.distinctRows.values();
      }
      else if ((this.external != null) && (this.sort != null))
      {
        ResultExternal temp = this.external;
        this.external = null;
        temp.reset();
        this.rows = New.arrayList();
        for (;;)
        {
          Value[] list = temp.next();
          if (list == null) {
            break;
          }
          if (this.external == null) {
            this.external = new ResultTempTable(this.session, this.expressions, true, this.sort);
          }
          this.rows.add(list);
          if (this.rows.size() > this.maxMemoryRows)
          {
            this.rowCount = this.external.addRows(this.rows);
            this.rows.clear();
          }
        }
        temp.close();
      }
    }
    if (this.external != null)
    {
      addRowsToDisk();
      this.external.done();
    }
    else if (this.sort != null)
    {
      if ((this.offset > 0) || (this.limit > 0)) {
        this.sort.sort(this.rows, this.offset, this.limit < 0 ? this.rows.size() : this.limit);
      } else {
        this.sort.sort(this.rows);
      }
    }
    applyOffset();
    applyLimit();
    reset();
  }
  
  public int getRowCount()
  {
    return this.rowCount;
  }
  
  public void setLimit(int limit)
  {
    this.limit = limit;
  }
  
  private void applyLimit()
  {
    if (this.limit < 0) {
      return;
    }
    if (this.external == null)
    {
      if (this.rows.size() > this.limit)
      {
        this.rows = New.arrayList(this.rows.subList(0, this.limit));
        this.rowCount = this.limit;
      }
    }
    else if (this.limit < this.rowCount) {
      this.rowCount = this.limit;
    }
  }
  
  public boolean needToClose()
  {
    return this.external != null;
  }
  
  public void close()
  {
    if (this.external != null)
    {
      this.external.close();
      this.external = null;
      this.closed = true;
    }
  }
  
  public String getAlias(int i)
  {
    return this.expressions[i].getAlias();
  }
  
  public String getTableName(int i)
  {
    return this.expressions[i].getTableName();
  }
  
  public String getSchemaName(int i)
  {
    return this.expressions[i].getSchemaName();
  }
  
  public int getDisplaySize(int i)
  {
    return this.expressions[i].getDisplaySize();
  }
  
  public String getColumnName(int i)
  {
    return this.expressions[i].getColumnName();
  }
  
  public int getColumnType(int i)
  {
    return this.expressions[i].getType();
  }
  
  public long getColumnPrecision(int i)
  {
    return this.expressions[i].getPrecision();
  }
  
  public int getNullable(int i)
  {
    return this.expressions[i].getNullable();
  }
  
  public boolean isAutoIncrement(int i)
  {
    return this.expressions[i].isAutoIncrement();
  }
  
  public int getColumnScale(int i)
  {
    return this.expressions[i].getScale();
  }
  
  public void setOffset(int offset)
  {
    this.offset = offset;
  }
  
  private void applyOffset()
  {
    if (this.offset <= 0) {
      return;
    }
    if (this.external == null)
    {
      if (this.offset >= this.rows.size())
      {
        this.rows.clear();
        this.rowCount = 0;
      }
      else
      {
        int remove = Math.min(this.offset, this.rows.size());
        this.rows = New.arrayList(this.rows.subList(remove, this.rows.size()));
        this.rowCount -= remove;
      }
    }
    else if (this.offset >= this.rowCount)
    {
      this.rowCount = 0;
    }
    else
    {
      this.diskOffset = this.offset;
      this.rowCount -= this.offset;
    }
  }
  
  public String toString()
  {
    return super.toString() + " columns: " + this.visibleColumnCount + " rows: " + this.rowCount + " pos: " + this.rowId;
  }
  
  public boolean isClosed()
  {
    return this.closed;
  }
  
  public int getFetchSize()
  {
    return 0;
  }
  
  public void setFetchSize(int fetchSize) {}
}
