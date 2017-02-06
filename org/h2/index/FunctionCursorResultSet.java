package org.h2.index;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.value.DataType;
import org.h2.value.Value;

public class FunctionCursorResultSet
  implements Cursor
{
  private final Session session;
  private final ResultSet result;
  private final ResultSetMetaData meta;
  private Value[] values;
  private Row row;
  
  FunctionCursorResultSet(Session session, ResultSet result)
  {
    this.session = session;
    this.result = result;
    try
    {
      this.meta = result.getMetaData();
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  public Row get()
  {
    if (this.values == null) {
      return null;
    }
    if (this.row == null) {
      this.row = new Row(this.values, 1);
    }
    return this.row;
  }
  
  public SearchRow getSearchRow()
  {
    return get();
  }
  
  public boolean next()
  {
    this.row = null;
    try
    {
      if ((this.result != null) && (this.result.next()))
      {
        int columnCount = this.meta.getColumnCount();
        this.values = new Value[columnCount];
        for (int i = 0; i < columnCount; i++)
        {
          int type = DataType.getValueTypeFromResultSet(this.meta, i + 1);
          this.values[i] = DataType.readValue(this.session, this.result, i + 1, type);
        }
      }
      else
      {
        this.values = null;
      }
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    return this.values != null;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
