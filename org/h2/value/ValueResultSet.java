package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.tools.SimpleResultSet;
import org.h2.util.StatementBuilder;

public class ValueResultSet
  extends Value
{
  private final ResultSet result;
  
  private ValueResultSet(ResultSet rs)
  {
    this.result = rs;
  }
  
  public static ValueResultSet get(ResultSet rs)
  {
    ValueResultSet val = new ValueResultSet(rs);
    return val;
  }
  
  public static ValueResultSet getCopy(ResultSet rs, int maxrows)
  {
    try
    {
      ResultSetMetaData meta = rs.getMetaData();
      int columnCount = meta.getColumnCount();
      SimpleResultSet simple = new SimpleResultSet();
      simple.setAutoClose(false);
      ValueResultSet val = new ValueResultSet(simple);
      for (int i = 0; i < columnCount; i++)
      {
        String name = meta.getColumnLabel(i + 1);
        int sqlType = meta.getColumnType(i + 1);
        int precision = meta.getPrecision(i + 1);
        int scale = meta.getScale(i + 1);
        simple.addColumn(name, sqlType, precision, scale);
      }
      for (int i = 0; (i < maxrows) && (rs.next()); i++)
      {
        Object[] list = new Object[columnCount];
        for (int j = 0; j < columnCount; j++) {
          list[j] = rs.getObject(j + 1);
        }
        simple.addRow(list);
      }
      return val;
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  public int getType()
  {
    return 18;
  }
  
  public long getPrecision()
  {
    return 2147483647L;
  }
  
  public int getDisplaySize()
  {
    return Integer.MAX_VALUE;
  }
  
  public String getString()
  {
    try
    {
      StatementBuilder buff = new StatementBuilder("(");
      this.result.beforeFirst();
      ResultSetMetaData meta = this.result.getMetaData();
      int columnCount = meta.getColumnCount();
      for (int i = 0; this.result.next(); i++)
      {
        if (i > 0) {
          buff.append(", ");
        }
        buff.append('(');
        buff.resetCount();
        for (int j = 0; j < columnCount; j++)
        {
          buff.appendExceptFirst(", ");
          int t = DataType.getValueTypeFromResultSet(meta, j + 1);
          Value v = DataType.readValue(null, this.result, j + 1, t);
          buff.append(v.getString());
        }
        buff.append(')');
      }
      this.result.beforeFirst();
      return buff.append(')').toString();
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  protected int compareSecure(Value v, CompareMode mode)
  {
    return this == v ? 0 : super.toString().compareTo(v.toString());
  }
  
  public boolean equals(Object other)
  {
    return other == this;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public Object getObject()
  {
    return this.result;
  }
  
  public ResultSet getResultSet()
  {
    return this.result;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
  {
    throw throwUnsupportedExceptionForType("PreparedStatement.set");
  }
  
  public String getSQL()
  {
    return "";
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if (!force) {
      return this;
    }
    SimpleResultSet rs = new SimpleResultSet();
    rs.setAutoClose(false);
    return get(rs);
  }
}
