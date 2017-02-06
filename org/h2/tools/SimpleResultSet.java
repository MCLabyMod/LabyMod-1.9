package org.h2.tools;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import org.h2.message.DbException;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.value.DataType;

public class SimpleResultSet
  implements ResultSet, ResultSetMetaData
{
  private ArrayList<Object[]> rows;
  private Object[] currentRow;
  private int rowId = -1;
  private boolean wasNull;
  private SimpleRowSource source;
  private ArrayList<Column> columns = New.arrayList();
  private boolean autoClose = true;
  
  public SimpleResultSet()
  {
    this.rows = New.arrayList();
  }
  
  public SimpleResultSet(SimpleRowSource source)
  {
    this.source = source;
  }
  
  public void addColumn(String name, int sqlType, int precision, int scale)
  {
    int valueType = DataType.convertSQLTypeToValueType(sqlType);
    addColumn(name, sqlType, DataType.getDataType(valueType).name, precision, scale);
  }
  
  public void addColumn(String name, int sqlType, String sqlTypeName, int precision, int scale)
  {
    if ((this.rows != null) && (this.rows.size() > 0)) {
      throw new IllegalStateException("Cannot add a column after adding rows");
    }
    if (name == null) {
      name = "C" + (this.columns.size() + 1);
    }
    Column column = new Column();
    column.name = name;
    column.sqlType = sqlType;
    column.precision = precision;
    column.scale = scale;
    column.sqlTypeName = sqlTypeName;
    this.columns.add(column);
  }
  
  public void addRow(Object... row)
  {
    if (this.rows == null) {
      throw new IllegalStateException("Cannot add a row when using RowSource");
    }
    this.rows.add(row);
  }
  
  public int getConcurrency()
  {
    return 1007;
  }
  
  public int getFetchDirection()
  {
    return 1000;
  }
  
  public int getFetchSize()
  {
    return 0;
  }
  
  public int getRow()
  {
    return this.currentRow == null ? 0 : this.rowId + 1;
  }
  
  public int getType()
  {
    if (this.autoClose) {
      return 1003;
    }
    return 1004;
  }
  
  public void close()
  {
    this.currentRow = null;
    this.rows = null;
    this.columns = null;
    this.rowId = -1;
    if (this.source != null)
    {
      this.source.close();
      this.source = null;
    }
  }
  
  public boolean next()
    throws SQLException
  {
    if (this.source != null)
    {
      this.rowId += 1;
      this.currentRow = this.source.readRow();
      if (this.currentRow != null) {
        return true;
      }
    }
    else if ((this.rows != null) && (this.rowId < this.rows.size()))
    {
      this.rowId += 1;
      if (this.rowId < this.rows.size())
      {
        this.currentRow = ((Object[])this.rows.get(this.rowId));
        return true;
      }
      this.currentRow = null;
    }
    if (this.autoClose) {
      close();
    }
    return false;
  }
  
  public void beforeFirst()
    throws SQLException
  {
    if (this.autoClose) {
      throw DbException.get(90128);
    }
    this.rowId = -1;
    if (this.source != null) {
      this.source.reset();
    }
  }
  
  public boolean wasNull()
  {
    return this.wasNull;
  }
  
  public int findColumn(String columnLabel)
    throws SQLException
  {
    if ((columnLabel != null) && (this.columns != null))
    {
      int i = 0;
      for (int size = this.columns.size(); i < size; i++) {
        if (columnLabel.equalsIgnoreCase(getColumn(i).name)) {
          return i + 1;
        }
      }
    }
    throw DbException.get(42122, columnLabel).getSQLException();
  }
  
  public ResultSetMetaData getMetaData()
  {
    return this;
  }
  
  public SQLWarning getWarnings()
  {
    return null;
  }
  
  public Statement getStatement()
  {
    return null;
  }
  
  public void clearWarnings() {}
  
  public Array getArray(int columnIndex)
    throws SQLException
  {
    Object[] o = (Object[])get(columnIndex);
    return o == null ? null : new SimpleArray(o);
  }
  
  public Array getArray(String columnLabel)
    throws SQLException
  {
    return getArray(findColumn(columnLabel));
  }
  
  public InputStream getAsciiStream(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public InputStream getAsciiStream(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public BigDecimal getBigDecimal(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof BigDecimal))) {
      o = new BigDecimal(o.toString());
    }
    return (BigDecimal)o;
  }
  
  public BigDecimal getBigDecimal(String columnLabel)
    throws SQLException
  {
    return getBigDecimal(findColumn(columnLabel));
  }
  
  /**
   * @deprecated
   */
  public BigDecimal getBigDecimal(int columnIndex, int scale)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  /**
   * @deprecated
   */
  public BigDecimal getBigDecimal(String columnLabel, int scale)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public InputStream getBinaryStream(int columnIndex)
    throws SQLException
  {
    return asInputStream(get(columnIndex));
  }
  
  private static InputStream asInputStream(Object o)
    throws SQLException
  {
    if (o == null) {
      return null;
    }
    if ((o instanceof Blob)) {
      return ((Blob)o).getBinaryStream();
    }
    return (InputStream)o;
  }
  
  public InputStream getBinaryStream(String columnLabel)
    throws SQLException
  {
    return getBinaryStream(findColumn(columnLabel));
  }
  
  public Blob getBlob(int columnIndex)
    throws SQLException
  {
    return (Blob)get(columnIndex);
  }
  
  public Blob getBlob(String columnLabel)
    throws SQLException
  {
    return getBlob(findColumn(columnLabel));
  }
  
  public boolean getBoolean(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Boolean))) {
      o = Boolean.valueOf(o.toString());
    }
    return o == null ? false : ((Boolean)o).booleanValue();
  }
  
  public boolean getBoolean(String columnLabel)
    throws SQLException
  {
    return getBoolean(findColumn(columnLabel));
  }
  
  public byte getByte(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Number))) {
      o = Byte.decode(o.toString());
    }
    return o == null ? 0 : ((Number)o).byteValue();
  }
  
  public byte getByte(String columnLabel)
    throws SQLException
  {
    return getByte(findColumn(columnLabel));
  }
  
  public byte[] getBytes(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o == null) || ((o instanceof byte[]))) {
      return (byte[])o;
    }
    return JdbcUtils.serialize(o, null);
  }
  
  public byte[] getBytes(String columnLabel)
    throws SQLException
  {
    return getBytes(findColumn(columnLabel));
  }
  
  public Reader getCharacterStream(int columnIndex)
    throws SQLException
  {
    return asReader(get(columnIndex));
  }
  
  private static Reader asReader(Object o)
    throws SQLException
  {
    if (o == null) {
      return null;
    }
    if ((o instanceof Clob)) {
      return ((Clob)o).getCharacterStream();
    }
    return (Reader)o;
  }
  
  public Reader getCharacterStream(String columnLabel)
    throws SQLException
  {
    return getCharacterStream(findColumn(columnLabel));
  }
  
  public Clob getClob(int columnIndex)
    throws SQLException
  {
    Clob c = (Clob)get(columnIndex);
    return c == null ? null : c;
  }
  
  public Clob getClob(String columnLabel)
    throws SQLException
  {
    return getClob(findColumn(columnLabel));
  }
  
  public Date getDate(int columnIndex)
    throws SQLException
  {
    return (Date)get(columnIndex);
  }
  
  public Date getDate(String columnLabel)
    throws SQLException
  {
    return getDate(findColumn(columnLabel));
  }
  
  public Date getDate(int columnIndex, Calendar cal)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Date getDate(String columnLabel, Calendar cal)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public double getDouble(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Number))) {
      return Double.parseDouble(o.toString());
    }
    return o == null ? 0.0D : ((Number)o).doubleValue();
  }
  
  public double getDouble(String columnLabel)
    throws SQLException
  {
    return getDouble(findColumn(columnLabel));
  }
  
  public float getFloat(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Number))) {
      return Float.parseFloat(o.toString());
    }
    return o == null ? 0.0F : ((Number)o).floatValue();
  }
  
  public float getFloat(String columnLabel)
    throws SQLException
  {
    return getFloat(findColumn(columnLabel));
  }
  
  public int getInt(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Number))) {
      o = Integer.decode(o.toString());
    }
    return o == null ? 0 : ((Number)o).intValue();
  }
  
  public int getInt(String columnLabel)
    throws SQLException
  {
    return getInt(findColumn(columnLabel));
  }
  
  public long getLong(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Number))) {
      o = Long.decode(o.toString());
    }
    return o == null ? 0L : ((Number)o).longValue();
  }
  
  public long getLong(String columnLabel)
    throws SQLException
  {
    return getLong(findColumn(columnLabel));
  }
  
  public Reader getNCharacterStream(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Reader getNCharacterStream(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public NClob getNClob(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public NClob getNClob(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public String getNString(int columnIndex)
    throws SQLException
  {
    return getString(columnIndex);
  }
  
  public String getNString(String columnLabel)
    throws SQLException
  {
    return getString(columnLabel);
  }
  
  public Object getObject(int columnIndex)
    throws SQLException
  {
    return get(columnIndex);
  }
  
  public Object getObject(String columnLabel)
    throws SQLException
  {
    return getObject(findColumn(columnLabel));
  }
  
  public Object getObject(int columnIndex, Map<String, Class<?>> map)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Object getObject(String columnLabel, Map<String, Class<?>> map)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Ref getRef(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Ref getRef(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public RowId getRowId(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public RowId getRowId(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public short getShort(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if ((o != null) && (!(o instanceof Number))) {
      o = Short.decode(o.toString());
    }
    return o == null ? 0 : ((Number)o).shortValue();
  }
  
  public short getShort(String columnLabel)
    throws SQLException
  {
    return getShort(findColumn(columnLabel));
  }
  
  public SQLXML getSQLXML(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public SQLXML getSQLXML(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public String getString(int columnIndex)
    throws SQLException
  {
    Object o = get(columnIndex);
    if (o == null) {
      return null;
    }
    switch (((Column)this.columns.get(columnIndex - 1)).sqlType)
    {
    case 2005: 
      Clob c = (Clob)o;
      return c.getSubString(1L, MathUtils.convertLongToInt(c.length()));
    }
    return o.toString();
  }
  
  public String getString(String columnLabel)
    throws SQLException
  {
    return getString(findColumn(columnLabel));
  }
  
  public Time getTime(int columnIndex)
    throws SQLException
  {
    return (Time)get(columnIndex);
  }
  
  public Time getTime(String columnLabel)
    throws SQLException
  {
    return getTime(findColumn(columnLabel));
  }
  
  public Time getTime(int columnIndex, Calendar cal)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Time getTime(String columnLabel, Calendar cal)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Timestamp getTimestamp(int columnIndex)
    throws SQLException
  {
    return (Timestamp)get(columnIndex);
  }
  
  public Timestamp getTimestamp(String columnLabel)
    throws SQLException
  {
    return getTimestamp(findColumn(columnLabel));
  }
  
  public Timestamp getTimestamp(int columnIndex, Calendar cal)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public Timestamp getTimestamp(String columnLabel, Calendar cal)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  /**
   * @deprecated
   */
  public InputStream getUnicodeStream(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  /**
   * @deprecated
   */
  public InputStream getUnicodeStream(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public URL getURL(int columnIndex)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public URL getURL(String columnLabel)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void updateArray(int columnIndex, Array x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateArray(String columnLabel, Array x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateAsciiStream(int columnIndex, InputStream x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateAsciiStream(String columnLabel, InputStream x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateAsciiStream(int columnIndex, InputStream x, int length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateAsciiStream(String columnLabel, InputStream x, int length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateAsciiStream(int columnIndex, InputStream x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateAsciiStream(String columnLabel, InputStream x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBigDecimal(int columnIndex, BigDecimal x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBigDecimal(String columnLabel, BigDecimal x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBinaryStream(int columnIndex, InputStream x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBinaryStream(String columnLabel, InputStream x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBinaryStream(int columnIndex, InputStream x, int length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBinaryStream(String columnLabel, InputStream x, int length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBinaryStream(int columnIndex, InputStream x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBinaryStream(String columnLabel, InputStream x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBlob(int columnIndex, Blob x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBlob(String columnLabel, Blob x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBlob(int columnIndex, InputStream x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBlob(String columnLabel, InputStream x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBlob(int columnIndex, InputStream x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBlob(String columnLabel, InputStream x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateBoolean(int columnIndex, boolean x)
    throws SQLException
  {
    update(columnIndex, Boolean.valueOf(x));
  }
  
  public void updateBoolean(String columnLabel, boolean x)
    throws SQLException
  {
    update(columnLabel, Boolean.valueOf(x));
  }
  
  public void updateByte(int columnIndex, byte x)
    throws SQLException
  {
    update(columnIndex, Byte.valueOf(x));
  }
  
  public void updateByte(String columnLabel, byte x)
    throws SQLException
  {
    update(columnLabel, Byte.valueOf(x));
  }
  
  public void updateBytes(int columnIndex, byte[] x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateBytes(String columnLabel, byte[] x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateCharacterStream(int columnIndex, Reader x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateCharacterStream(String columnLabel, Reader x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateCharacterStream(int columnIndex, Reader x, int length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateCharacterStream(String columnLabel, Reader x, int length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateCharacterStream(int columnIndex, Reader x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateCharacterStream(String columnLabel, Reader x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateClob(int columnIndex, Clob x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateClob(String columnLabel, Clob x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateClob(int columnIndex, Reader x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateClob(String columnLabel, Reader x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateClob(int columnIndex, Reader x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateClob(String columnLabel, Reader x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateDate(int columnIndex, Date x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateDate(String columnLabel, Date x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateDouble(int columnIndex, double x)
    throws SQLException
  {
    update(columnIndex, Double.valueOf(x));
  }
  
  public void updateDouble(String columnLabel, double x)
    throws SQLException
  {
    update(columnLabel, Double.valueOf(x));
  }
  
  public void updateFloat(int columnIndex, float x)
    throws SQLException
  {
    update(columnIndex, Float.valueOf(x));
  }
  
  public void updateFloat(String columnLabel, float x)
    throws SQLException
  {
    update(columnLabel, Float.valueOf(x));
  }
  
  public void updateInt(int columnIndex, int x)
    throws SQLException
  {
    update(columnIndex, Integer.valueOf(x));
  }
  
  public void updateInt(String columnLabel, int x)
    throws SQLException
  {
    update(columnLabel, Integer.valueOf(x));
  }
  
  public void updateLong(int columnIndex, long x)
    throws SQLException
  {
    update(columnIndex, Long.valueOf(x));
  }
  
  public void updateLong(String columnLabel, long x)
    throws SQLException
  {
    update(columnLabel, Long.valueOf(x));
  }
  
  public void updateNCharacterStream(int columnIndex, Reader x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateNCharacterStream(String columnLabel, Reader x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateNCharacterStream(int columnIndex, Reader x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateNCharacterStream(String columnLabel, Reader x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateNClob(int columnIndex, NClob x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateNClob(String columnLabel, NClob x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateNClob(int columnIndex, Reader x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateNClob(String columnLabel, Reader x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateNClob(int columnIndex, Reader x, long length)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateNClob(String columnLabel, Reader x, long length)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateNString(int columnIndex, String x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateNString(String columnLabel, String x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateNull(int columnIndex)
    throws SQLException
  {
    update(columnIndex, null);
  }
  
  public void updateNull(String columnLabel)
    throws SQLException
  {
    update(columnLabel, null);
  }
  
  public void updateObject(int columnIndex, Object x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateObject(String columnLabel, Object x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateObject(int columnIndex, Object x, int scale)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateObject(String columnLabel, Object x, int scale)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateRef(int columnIndex, Ref x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateRef(String columnLabel, Ref x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateRowId(int columnIndex, RowId x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateRowId(String columnLabel, RowId x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateShort(int columnIndex, short x)
    throws SQLException
  {
    update(columnIndex, Short.valueOf(x));
  }
  
  public void updateShort(String columnLabel, short x)
    throws SQLException
  {
    update(columnLabel, Short.valueOf(x));
  }
  
  public void updateSQLXML(int columnIndex, SQLXML x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateSQLXML(String columnLabel, SQLXML x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateString(int columnIndex, String x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateString(String columnLabel, String x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateTime(int columnIndex, Time x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateTime(String columnLabel, Time x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public void updateTimestamp(int columnIndex, Timestamp x)
    throws SQLException
  {
    update(columnIndex, x);
  }
  
  public void updateTimestamp(String columnLabel, Timestamp x)
    throws SQLException
  {
    update(columnLabel, x);
  }
  
  public int getColumnCount()
  {
    return this.columns.size();
  }
  
  public int getColumnDisplaySize(int columnIndex)
  {
    return 15;
  }
  
  public int getColumnType(int columnIndex)
    throws SQLException
  {
    return getColumn(columnIndex - 1).sqlType;
  }
  
  public int getPrecision(int columnIndex)
    throws SQLException
  {
    return getColumn(columnIndex - 1).precision;
  }
  
  public int getScale(int columnIndex)
    throws SQLException
  {
    return getColumn(columnIndex - 1).scale;
  }
  
  public int isNullable(int columnIndex)
  {
    return 2;
  }
  
  public boolean isAutoIncrement(int columnIndex)
  {
    return false;
  }
  
  public boolean isCaseSensitive(int columnIndex)
  {
    return true;
  }
  
  public boolean isCurrency(int columnIndex)
  {
    return false;
  }
  
  public boolean isDefinitelyWritable(int columnIndex)
  {
    return false;
  }
  
  public boolean isReadOnly(int columnIndex)
  {
    return true;
  }
  
  public boolean isSearchable(int columnIndex)
  {
    return true;
  }
  
  public boolean isSigned(int columnIndex)
  {
    return true;
  }
  
  public boolean isWritable(int columnIndex)
  {
    return false;
  }
  
  public String getCatalogName(int columnIndex)
  {
    return null;
  }
  
  public String getColumnClassName(int columnIndex)
    throws SQLException
  {
    int type = DataType.getValueTypeFromResultSet(this, columnIndex);
    return DataType.getTypeClassName(type);
  }
  
  public String getColumnLabel(int columnIndex)
    throws SQLException
  {
    return getColumn(columnIndex - 1).name;
  }
  
  public String getColumnName(int columnIndex)
    throws SQLException
  {
    return getColumnLabel(columnIndex);
  }
  
  public String getColumnTypeName(int columnIndex)
    throws SQLException
  {
    return getColumn(columnIndex - 1).sqlTypeName;
  }
  
  public String getSchemaName(int columnIndex)
  {
    return null;
  }
  
  public String getTableName(int columnIndex)
  {
    return null;
  }
  
  public void afterLast()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void cancelRowUpdates()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void deleteRow()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void insertRow()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void moveToCurrentRow()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void moveToInsertRow()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void refreshRow()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void updateRow()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean first()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean isAfterLast()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean isBeforeFirst()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean isFirst()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean isLast()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean last()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean previous()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean rowDeleted()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean rowInserted()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean rowUpdated()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void setFetchDirection(int direction)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void setFetchSize(int rows)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean absolute(int row)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean relative(int offset)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public String getCursorName()
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  private void update(int columnIndex, Object obj)
    throws SQLException
  {
    checkColumnIndex(columnIndex);
    this.currentRow[(columnIndex - 1)] = obj;
  }
  
  private void update(String columnLabel, Object obj)
    throws SQLException
  {
    this.currentRow[(findColumn(columnLabel) - 1)] = obj;
  }
  
  static SQLException getUnsupportedException()
  {
    return DbException.get(50100).getSQLException();
  }
  
  private void checkColumnIndex(int columnIndex)
    throws SQLException
  {
    if ((columnIndex < 1) || (columnIndex > this.columns.size())) {
      throw DbException.getInvalidValueException("columnIndex", Integer.valueOf(columnIndex)).getSQLException();
    }
  }
  
  private Object get(int columnIndex)
    throws SQLException
  {
    if (this.currentRow == null) {
      throw DbException.get(2000).getSQLException();
    }
    checkColumnIndex(columnIndex);
    columnIndex--;
    Object o = columnIndex < this.currentRow.length ? this.currentRow[columnIndex] : null;
    
    this.wasNull = (o == null);
    return o;
  }
  
  private Column getColumn(int i)
    throws SQLException
  {
    checkColumnIndex(i + 1);
    return (Column)this.columns.get(i);
  }
  
  public int getHoldability()
  {
    return 1;
  }
  
  public boolean isClosed()
  {
    return (this.rows == null) && (this.source == null);
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    throw getUnsupportedException();
  }
  
  public void setAutoClose(boolean autoClose)
  {
    this.autoClose = autoClose;
  }
  
  public boolean getAutoClose()
  {
    return this.autoClose;
  }
  
  public static class SimpleArray
    implements Array
  {
    private final Object[] value;
    
    SimpleArray(Object[] value)
    {
      this.value = value;
    }
    
    public Object getArray()
    {
      return this.value;
    }
    
    public Object getArray(Map<String, Class<?>> map)
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public Object getArray(long index, int count)
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public Object getArray(long index, int count, Map<String, Class<?>> map)
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public int getBaseType()
    {
      return 0;
    }
    
    public String getBaseTypeName()
    {
      return "NULL";
    }
    
    public ResultSet getResultSet()
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public ResultSet getResultSet(Map<String, Class<?>> map)
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public ResultSet getResultSet(long index, int count)
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map)
      throws SQLException
    {
      throw SimpleResultSet.getUnsupportedException();
    }
    
    public void free() {}
  }
  
  public <T> T getObject(int columnIndex, Class<T> type)
    throws SQLException
  {
    return null;
  }
  
  public <T> T getObject(String columnLabel, Class<T> type)
    throws SQLException
  {
    return null;
  }
  
  static class Column
  {
    String name;
    String sqlTypeName;
    int sqlType;
    int precision;
    int scale;
  }
}
