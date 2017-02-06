package org.h2.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.h2.command.CommandInterface;
import org.h2.engine.SessionInterface;
import org.h2.expression.ParameterInterface;
import org.h2.message.DbException;
import org.h2.util.BitField;
import org.h2.util.New;
import org.h2.value.ValueNull;

public class JdbcCallableStatement
  extends JdbcPreparedStatement
  implements CallableStatement
{
  private BitField outParameters;
  private int maxOutParameters;
  private HashMap<String, Integer> namedParameters;
  
  JdbcCallableStatement(JdbcConnection conn, String sql, int id, int resultSetType, int resultSetConcurrency)
  {
    super(conn, sql, id, resultSetType, resultSetConcurrency, false);
    setTrace(this.session.getTrace(), 0, id);
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      checkClosed();
      if (this.command.isQuery())
      {
        super.executeQuery();
        return 0;
      }
      return super.executeUpdate();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void registerOutParameter(int parameterIndex, int sqlType)
    throws SQLException
  {
    registerOutParameter(parameterIndex);
  }
  
  public void registerOutParameter(int parameterIndex, int sqlType, String typeName)
    throws SQLException
  {
    registerOutParameter(parameterIndex);
  }
  
  public void registerOutParameter(int parameterIndex, int sqlType, int scale)
    throws SQLException
  {
    registerOutParameter(parameterIndex);
  }
  
  public void registerOutParameter(String parameterName, int sqlType, String typeName)
    throws SQLException
  {
    registerOutParameter(getIndexForName(parameterName), sqlType, typeName);
  }
  
  public void registerOutParameter(String parameterName, int sqlType, int scale)
    throws SQLException
  {
    registerOutParameter(getIndexForName(parameterName), sqlType, scale);
  }
  
  public void registerOutParameter(String parameterName, int sqlType)
    throws SQLException
  {
    registerOutParameter(getIndexForName(parameterName), sqlType);
  }
  
  public boolean wasNull()
    throws SQLException
  {
    return getOpenResultSet().wasNull();
  }
  
  public URL getURL(int parameterIndex)
    throws SQLException
  {
    throw unsupported("url");
  }
  
  public String getString(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getString(parameterIndex);
  }
  
  public boolean getBoolean(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getBoolean(parameterIndex);
  }
  
  public byte getByte(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getByte(parameterIndex);
  }
  
  public short getShort(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getShort(parameterIndex);
  }
  
  public int getInt(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getInt(parameterIndex);
  }
  
  public long getLong(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getLong(parameterIndex);
  }
  
  public float getFloat(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getFloat(parameterIndex);
  }
  
  public double getDouble(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getDouble(parameterIndex);
  }
  
  /**
   * @deprecated
   */
  public BigDecimal getBigDecimal(int parameterIndex, int scale)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getBigDecimal(parameterIndex, scale);
  }
  
  public byte[] getBytes(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getBytes(parameterIndex);
  }
  
  public Date getDate(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getDate(parameterIndex);
  }
  
  public Time getTime(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getTime(parameterIndex);
  }
  
  public Timestamp getTimestamp(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getTimestamp(parameterIndex);
  }
  
  public Object getObject(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getObject(parameterIndex);
  }
  
  public BigDecimal getBigDecimal(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getBigDecimal(parameterIndex);
  }
  
  public Object getObject(int parameterIndex, Map<String, Class<?>> map)
    throws SQLException
  {
    throw unsupported("map");
  }
  
  public Ref getRef(int parameterIndex)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public Blob getBlob(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getBlob(parameterIndex);
  }
  
  public Clob getClob(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getClob(parameterIndex);
  }
  
  public Array getArray(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getArray(parameterIndex);
  }
  
  public Date getDate(int parameterIndex, Calendar cal)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getDate(parameterIndex, cal);
  }
  
  public Time getTime(int parameterIndex, Calendar cal)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getTime(parameterIndex, cal);
  }
  
  public Timestamp getTimestamp(int parameterIndex, Calendar cal)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getTimestamp(parameterIndex, cal);
  }
  
  public URL getURL(String parameterName)
    throws SQLException
  {
    throw unsupported("url");
  }
  
  public Timestamp getTimestamp(String parameterName, Calendar cal)
    throws SQLException
  {
    return getTimestamp(getIndexForName(parameterName), cal);
  }
  
  public Time getTime(String parameterName, Calendar cal)
    throws SQLException
  {
    return getTime(getIndexForName(parameterName), cal);
  }
  
  public Date getDate(String parameterName, Calendar cal)
    throws SQLException
  {
    return getDate(getIndexForName(parameterName), cal);
  }
  
  public Array getArray(String parameterName)
    throws SQLException
  {
    return getArray(getIndexForName(parameterName));
  }
  
  public Clob getClob(String parameterName)
    throws SQLException
  {
    return getClob(getIndexForName(parameterName));
  }
  
  public Blob getBlob(String parameterName)
    throws SQLException
  {
    return getBlob(getIndexForName(parameterName));
  }
  
  public Ref getRef(String parameterName)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public Object getObject(String parameterName, Map<String, Class<?>> map)
    throws SQLException
  {
    throw unsupported("map");
  }
  
  public BigDecimal getBigDecimal(String parameterName)
    throws SQLException
  {
    return getBigDecimal(getIndexForName(parameterName));
  }
  
  public Object getObject(String parameterName)
    throws SQLException
  {
    return getObject(getIndexForName(parameterName));
  }
  
  public Timestamp getTimestamp(String parameterName)
    throws SQLException
  {
    return getTimestamp(getIndexForName(parameterName));
  }
  
  public Time getTime(String parameterName)
    throws SQLException
  {
    return getTime(getIndexForName(parameterName));
  }
  
  public Date getDate(String parameterName)
    throws SQLException
  {
    return getDate(getIndexForName(parameterName));
  }
  
  public byte[] getBytes(String parameterName)
    throws SQLException
  {
    return getBytes(getIndexForName(parameterName));
  }
  
  public double getDouble(String parameterName)
    throws SQLException
  {
    return getDouble(getIndexForName(parameterName));
  }
  
  public float getFloat(String parameterName)
    throws SQLException
  {
    return getFloat(getIndexForName(parameterName));
  }
  
  public long getLong(String parameterName)
    throws SQLException
  {
    return getLong(getIndexForName(parameterName));
  }
  
  public int getInt(String parameterName)
    throws SQLException
  {
    return getInt(getIndexForName(parameterName));
  }
  
  public short getShort(String parameterName)
    throws SQLException
  {
    return getShort(getIndexForName(parameterName));
  }
  
  public byte getByte(String parameterName)
    throws SQLException
  {
    return getByte(getIndexForName(parameterName));
  }
  
  public boolean getBoolean(String parameterName)
    throws SQLException
  {
    return getBoolean(getIndexForName(parameterName));
  }
  
  public String getString(String parameterName)
    throws SQLException
  {
    return getString(getIndexForName(parameterName));
  }
  
  public RowId getRowId(int parameterIndex)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public RowId getRowId(String parameterName)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public NClob getNClob(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getNClob(parameterIndex);
  }
  
  public NClob getNClob(String parameterName)
    throws SQLException
  {
    return getNClob(getIndexForName(parameterName));
  }
  
  public SQLXML getSQLXML(int parameterIndex)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public SQLXML getSQLXML(String parameterName)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public String getNString(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getNString(parameterIndex);
  }
  
  public String getNString(String parameterName)
    throws SQLException
  {
    return getNString(getIndexForName(parameterName));
  }
  
  public Reader getNCharacterStream(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getNCharacterStream(parameterIndex);
  }
  
  public Reader getNCharacterStream(String parameterName)
    throws SQLException
  {
    return getNCharacterStream(getIndexForName(parameterName));
  }
  
  public Reader getCharacterStream(int parameterIndex)
    throws SQLException
  {
    checkRegistered(parameterIndex);
    return getOpenResultSet().getCharacterStream(parameterIndex);
  }
  
  public Reader getCharacterStream(String parameterName)
    throws SQLException
  {
    return getCharacterStream(getIndexForName(parameterName));
  }
  
  public void setNull(String parameterName, int sqlType, String typeName)
    throws SQLException
  {
    setNull(getIndexForName(parameterName), sqlType, typeName);
  }
  
  public void setNull(String parameterName, int sqlType)
    throws SQLException
  {
    setNull(getIndexForName(parameterName), sqlType);
  }
  
  public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
    throws SQLException
  {
    setTimestamp(getIndexForName(parameterName), x, cal);
  }
  
  public void setTime(String parameterName, Time x, Calendar cal)
    throws SQLException
  {
    setTime(getIndexForName(parameterName), x, cal);
  }
  
  public void setDate(String parameterName, Date x, Calendar cal)
    throws SQLException
  {
    setDate(getIndexForName(parameterName), x, cal);
  }
  
  public void setCharacterStream(String parameterName, Reader x, int length)
    throws SQLException
  {
    setCharacterStream(getIndexForName(parameterName), x, length);
  }
  
  public void setObject(String parameterName, Object x)
    throws SQLException
  {
    setObject(getIndexForName(parameterName), x);
  }
  
  public void setObject(String parameterName, Object x, int targetSqlType)
    throws SQLException
  {
    setObject(getIndexForName(parameterName), x, targetSqlType);
  }
  
  public void setObject(String parameterName, Object x, int targetSqlType, int scale)
    throws SQLException
  {
    setObject(getIndexForName(parameterName), x, targetSqlType, scale);
  }
  
  public void setBinaryStream(String parameterName, InputStream x, int length)
    throws SQLException
  {
    setBinaryStream(getIndexForName(parameterName), x, length);
  }
  
  public void setAsciiStream(String parameterName, InputStream x, long length)
    throws SQLException
  {
    setAsciiStream(getIndexForName(parameterName), x, length);
  }
  
  public void setTimestamp(String parameterName, Timestamp x)
    throws SQLException
  {
    setTimestamp(getIndexForName(parameterName), x);
  }
  
  public void setTime(String parameterName, Time x)
    throws SQLException
  {
    setTime(getIndexForName(parameterName), x);
  }
  
  public void setDate(String parameterName, Date x)
    throws SQLException
  {
    setDate(getIndexForName(parameterName), x);
  }
  
  public void setBytes(String parameterName, byte[] x)
    throws SQLException
  {
    setBytes(getIndexForName(parameterName), x);
  }
  
  public void setString(String parameterName, String x)
    throws SQLException
  {
    setString(getIndexForName(parameterName), x);
  }
  
  public void setBigDecimal(String parameterName, BigDecimal x)
    throws SQLException
  {
    setBigDecimal(getIndexForName(parameterName), x);
  }
  
  public void setDouble(String parameterName, double x)
    throws SQLException
  {
    setDouble(getIndexForName(parameterName), x);
  }
  
  public void setFloat(String parameterName, float x)
    throws SQLException
  {
    setFloat(getIndexForName(parameterName), x);
  }
  
  public void setLong(String parameterName, long x)
    throws SQLException
  {
    setLong(getIndexForName(parameterName), x);
  }
  
  public void setInt(String parameterName, int x)
    throws SQLException
  {
    setInt(getIndexForName(parameterName), x);
  }
  
  public void setShort(String parameterName, short x)
    throws SQLException
  {
    setShort(getIndexForName(parameterName), x);
  }
  
  public void setByte(String parameterName, byte x)
    throws SQLException
  {
    setByte(getIndexForName(parameterName), x);
  }
  
  public void setBoolean(String parameterName, boolean x)
    throws SQLException
  {
    setBoolean(getIndexForName(parameterName), x);
  }
  
  public void setURL(String parameterName, URL val)
    throws SQLException
  {
    throw unsupported("url");
  }
  
  public void setRowId(String parameterName, RowId x)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public void setNString(String parameterName, String x)
    throws SQLException
  {
    setNString(getIndexForName(parameterName), x);
  }
  
  public void setNCharacterStream(String parameterName, Reader x, long length)
    throws SQLException
  {
    setNCharacterStream(getIndexForName(parameterName), x, length);
  }
  
  public void setNClob(String parameterName, NClob x)
    throws SQLException
  {
    setNClob(getIndexForName(parameterName), x);
  }
  
  public void setClob(String parameterName, Reader x, long length)
    throws SQLException
  {
    setClob(getIndexForName(parameterName), x, length);
  }
  
  public void setBlob(String parameterName, InputStream x, long length)
    throws SQLException
  {
    setBlob(getIndexForName(parameterName), x, length);
  }
  
  public void setNClob(String parameterName, Reader x, long length)
    throws SQLException
  {
    setNClob(getIndexForName(parameterName), x, length);
  }
  
  public void setBlob(String parameterName, Blob x)
    throws SQLException
  {
    setBlob(getIndexForName(parameterName), x);
  }
  
  public void setClob(String parameterName, Clob x)
    throws SQLException
  {
    setClob(getIndexForName(parameterName), x);
  }
  
  public void setAsciiStream(String parameterName, InputStream x)
    throws SQLException
  {
    setAsciiStream(getIndexForName(parameterName), x);
  }
  
  public void setAsciiStream(String parameterName, InputStream x, int length)
    throws SQLException
  {
    setAsciiStream(getIndexForName(parameterName), x, length);
  }
  
  public void setBinaryStream(String parameterName, InputStream x)
    throws SQLException
  {
    setBinaryStream(getIndexForName(parameterName), x);
  }
  
  public void setBinaryStream(String parameterName, InputStream x, long length)
    throws SQLException
  {
    setBinaryStream(getIndexForName(parameterName), x, length);
  }
  
  public void setBlob(String parameterName, InputStream x)
    throws SQLException
  {
    setBlob(getIndexForName(parameterName), x);
  }
  
  public void setCharacterStream(String parameterName, Reader x)
    throws SQLException
  {
    setCharacterStream(getIndexForName(parameterName), x);
  }
  
  public void setCharacterStream(String parameterName, Reader x, long length)
    throws SQLException
  {
    setCharacterStream(getIndexForName(parameterName), x, length);
  }
  
  public void setClob(String parameterName, Reader x)
    throws SQLException
  {
    setClob(getIndexForName(parameterName), x);
  }
  
  public void setNCharacterStream(String parameterName, Reader x)
    throws SQLException
  {
    setNCharacterStream(getIndexForName(parameterName), x);
  }
  
  public void setNClob(String parameterName, Reader x)
    throws SQLException
  {
    setNClob(getIndexForName(parameterName), x);
  }
  
  public void setSQLXML(String parameterName, SQLXML x)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  private ResultSetMetaData getCheckedMetaData()
    throws SQLException
  {
    ResultSetMetaData meta = getMetaData();
    if (meta == null) {
      throw DbException.getUnsupportedException("Supported only for calling stored procedures");
    }
    return meta;
  }
  
  private void checkIndexBounds(int parameterIndex)
  {
    checkClosed();
    if ((parameterIndex < 1) || (parameterIndex > this.maxOutParameters)) {
      throw DbException.getInvalidValueException("parameterIndex", Integer.valueOf(parameterIndex));
    }
  }
  
  private void registerOutParameter(int parameterIndex)
    throws SQLException
  {
    try
    {
      checkClosed();
      if (this.outParameters == null)
      {
        this.maxOutParameters = Math.min(getParameterMetaData().getParameterCount(), getCheckedMetaData().getColumnCount());
        
        this.outParameters = new BitField();
      }
      checkIndexBounds(parameterIndex);
      ParameterInterface param = (ParameterInterface)this.command.getParameters().get(--parameterIndex);
      if (!param.isValueSet()) {
        param.setValue(ValueNull.INSTANCE, false);
      }
      this.outParameters.set(parameterIndex);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private void checkRegistered(int parameterIndex)
    throws SQLException
  {
    try
    {
      checkIndexBounds(parameterIndex);
      if (!this.outParameters.get(parameterIndex - 1)) {
        throw DbException.getInvalidValueException("parameterIndex", Integer.valueOf(parameterIndex));
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private int getIndexForName(String parameterName)
    throws SQLException
  {
    try
    {
      checkClosed();
      if (this.namedParameters == null)
      {
        ResultSetMetaData meta = getCheckedMetaData();
        int columnCount = meta.getColumnCount();
        HashMap<String, Integer> map = New.hashMap(columnCount);
        for (int i = 1; i <= columnCount; i++) {
          map.put(meta.getColumnLabel(i), Integer.valueOf(i));
        }
        this.namedParameters = map;
      }
      Integer index = (Integer)this.namedParameters.get(parameterName);
      if (index == null) {
        throw DbException.getInvalidValueException("parameterName", parameterName);
      }
      return index.intValue();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private JdbcResultSet getOpenResultSet()
    throws SQLException
  {
    try
    {
      checkClosed();
      if (this.resultSet == null) {
        throw DbException.get(2000);
      }
      if (this.resultSet.isBeforeFirst()) {
        this.resultSet.next();
      }
      return this.resultSet;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void closeOnCompletion()
    throws SQLException
  {}
  
  public boolean isCloseOnCompletion()
    throws SQLException
  {
    return false;
  }
  
  public <T> T getObject(int parameterIndex, Class<T> type)
    throws SQLException
  {
    return null;
  }
  
  public <T> T getObject(String parameterName, Class<T> type)
    throws SQLException
  {
    return null;
  }
}
