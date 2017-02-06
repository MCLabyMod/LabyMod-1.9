package org.h2.jdbc;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.h2.engine.SessionInterface;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.tools.SimpleResultSet;
import org.h2.value.Value;

public class JdbcArray
  extends TraceObject
  implements Array
{
  private Value value;
  private final JdbcConnection conn;
  
  JdbcArray(JdbcConnection conn, Value value, int id)
  {
    setTrace(conn.getSession().getTrace(), 16, id);
    this.conn = conn;
    this.value = value;
  }
  
  public Object getArray()
    throws SQLException
  {
    try
    {
      debugCodeCall("getArray");
      checkClosed();
      return get();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Object getArray(Map<String, Class<?>> map)
    throws SQLException
  {
    try
    {
      debugCode("getArray(" + quoteMap(map) + ");");
      JdbcConnection.checkMap(map);
      checkClosed();
      return get();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Object getArray(long index, int count)
    throws SQLException
  {
    try
    {
      debugCode("getArray(" + index + ", " + count + ");");
      checkClosed();
      return get(index, count);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Object getArray(long index, int count, Map<String, Class<?>> map)
    throws SQLException
  {
    try
    {
      debugCode("getArray(" + index + ", " + count + ", " + quoteMap(map) + ");");
      checkClosed();
      JdbcConnection.checkMap(map);
      return get(index, count);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getBaseType()
    throws SQLException
  {
    try
    {
      debugCodeCall("getBaseType");
      checkClosed();
      return 0;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      debugCodeCall("getBaseTypeName");
      checkClosed();
      return "NULL";
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      debugCodeCall("getResultSet");
      checkClosed();
      return getResultSet(get(), 0L);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getResultSet(Map<String, Class<?>> map)
    throws SQLException
  {
    try
    {
      debugCode("getResultSet(" + quoteMap(map) + ");");
      checkClosed();
      JdbcConnection.checkMap(map);
      return getResultSet(get(), 0L);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getResultSet(long index, int count)
    throws SQLException
  {
    try
    {
      debugCode("getResultSet(" + index + ", " + count + ");");
      checkClosed();
      return getResultSet(get(index, count), index - 1L);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map)
    throws SQLException
  {
    try
    {
      debugCode("getResultSet(" + index + ", " + count + ", " + quoteMap(map) + ");");
      checkClosed();
      JdbcConnection.checkMap(map);
      return getResultSet(get(index, count), index - 1L);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void free()
  {
    debugCodeCall("free");
    this.value = null;
  }
  
  private static ResultSet getResultSet(Object[] array, long offset)
  {
    SimpleResultSet rs = new SimpleResultSet();
    rs.addColumn("INDEX", -5, 0, 0);
    
    rs.addColumn("VALUE", 0, 0, 0);
    for (int i = 0; i < array.length; i++) {
      rs.addRow(new Object[] { Long.valueOf(offset + i + 1L), array[i] });
    }
    return rs;
  }
  
  private void checkClosed()
  {
    this.conn.checkClosed();
    if (this.value == null) {
      throw DbException.get(90007);
    }
  }
  
  private Object[] get()
  {
    return (Object[])this.value.convertTo(17).getObject();
  }
  
  private Object[] get(long index, int count)
  {
    Object[] array = get();
    if ((count < 0) || (count > array.length)) {
      throw DbException.getInvalidValueException("count (1.." + array.length + ")", Integer.valueOf(count));
    }
    if ((index < 1L) || (index > array.length)) {
      throw DbException.getInvalidValueException("index (1.." + array.length + ")", Long.valueOf(index));
    }
    Object[] subset = new Object[count];
    System.arraycopy(array, (int)(index - 1L), subset, 0, count);
    return subset;
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": " + this.value.getTraceSQL();
  }
}
