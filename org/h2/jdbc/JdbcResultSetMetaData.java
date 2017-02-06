package org.h2.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceObject;
import org.h2.result.ResultInterface;
import org.h2.util.MathUtils;
import org.h2.value.DataType;

public class JdbcResultSetMetaData
  extends TraceObject
  implements ResultSetMetaData
{
  private final String catalog;
  private final JdbcResultSet rs;
  private final JdbcPreparedStatement prep;
  private final ResultInterface result;
  private final int columnCount;
  
  JdbcResultSetMetaData(JdbcResultSet rs, JdbcPreparedStatement prep, ResultInterface result, String catalog, Trace trace, int id)
  {
    setTrace(trace, 5, id);
    this.catalog = catalog;
    this.rs = rs;
    this.prep = prep;
    this.result = result;
    this.columnCount = result.getVisibleColumnCount();
  }
  
  public int getColumnCount()
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnCount");
      checkClosed();
      return this.columnCount;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getColumnLabel(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnLabel", column);
      checkColumnIndex(column);
      return this.result.getAlias(--column);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getColumnName(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnName", column);
      checkColumnIndex(column);
      return this.result.getColumnName(--column);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getColumnType(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnType", column);
      checkColumnIndex(column);
      int type = this.result.getColumnType(--column);
      return DataType.convertTypeToSQLType(type);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getColumnTypeName(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnTypeName", column);
      checkColumnIndex(column);
      int type = this.result.getColumnType(--column);
      return DataType.getDataType(type).name;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getSchemaName(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getSchemaName", column);
      checkColumnIndex(column);
      String schema = this.result.getSchemaName(--column);
      return schema == null ? "" : schema;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getTableName(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getTableName", column);
      checkColumnIndex(column);
      String table = this.result.getTableName(--column);
      return table == null ? "" : table;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getCatalogName(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getCatalogName", column);
      checkColumnIndex(column);
      return this.catalog == null ? "" : this.catalog;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isAutoIncrement(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isAutoIncrement", column);
      checkColumnIndex(column);
      return this.result.isAutoIncrement(--column);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isCaseSensitive(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isCaseSensitive", column);
      checkColumnIndex(column);
      return true;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isSearchable(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isSearchable", column);
      checkColumnIndex(column);
      return true;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isCurrency(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isCurrency", column);
      checkColumnIndex(column);
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int isNullable(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isNullable", column);
      checkColumnIndex(column);
      return this.result.getNullable(--column);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isSigned(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isSigned", column);
      checkColumnIndex(column);
      return true;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isReadOnly(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isReadOnly", column);
      checkColumnIndex(column);
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isWritable(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isWritable", column);
      checkColumnIndex(column);
      return true;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isDefinitelyWritable(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("isDefinitelyWritable", column);
      checkColumnIndex(column);
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getColumnClassName(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnClassName", column);
      checkColumnIndex(column);
      int type = this.result.getColumnType(--column);
      return DataType.getTypeClassName(type);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getPrecision(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getPrecision", column);
      checkColumnIndex(column);
      long prec = this.result.getColumnPrecision(--column);
      return MathUtils.convertLongToInt(prec);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getScale(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getScale", column);
      checkColumnIndex(column);
      return this.result.getColumnScale(--column);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getColumnDisplaySize(int column)
    throws SQLException
  {
    try
    {
      debugCodeCall("getColumnDisplaySize", column);
      checkColumnIndex(column);
      return this.result.getDisplaySize(--column);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private void checkClosed()
  {
    if (this.rs != null) {
      this.rs.checkClosed();
    }
    if (this.prep != null) {
      this.prep.checkClosed();
    }
  }
  
  private void checkColumnIndex(int columnIndex)
  {
    checkClosed();
    if ((columnIndex < 1) || (columnIndex > this.columnCount)) {
      throw DbException.getInvalidValueException("columnIndex", Integer.valueOf(columnIndex));
    }
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    if (isWrapperFor(iface)) {
      return this;
    }
    throw DbException.getInvalidValueException("iface", iface);
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    return (iface != null) && (iface.isAssignableFrom(getClass()));
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": columns=" + this.columnCount;
  }
}
