package org.h2.jdbc;

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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.h2.engine.SessionInterface;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.result.ResultInterface;
import org.h2.result.UpdatableRow;
import org.h2.util.DateTimeUtils;
import org.h2.util.IOUtils;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueByte;
import org.h2.value.ValueBytes;
import org.h2.value.ValueDate;
import org.h2.value.ValueDecimal;
import org.h2.value.ValueDouble;
import org.h2.value.ValueFloat;
import org.h2.value.ValueInt;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueShort;
import org.h2.value.ValueString;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;

public class JdbcResultSet
  extends TraceObject
  implements ResultSet
{
  private final boolean closeStatement;
  private final boolean scrollable;
  private final boolean updatable;
  private ResultInterface result;
  private JdbcConnection conn;
  private JdbcStatement stat;
  private int columnCount;
  private boolean wasNull;
  private Value[] insertRow;
  private Value[] updateRow;
  private HashMap<String, Integer> columnLabelMap;
  private HashMap<Integer, Value[]> patchedRows;
  private JdbcPreparedStatement preparedStatement;
  
  JdbcResultSet(JdbcConnection conn, JdbcStatement stat, ResultInterface result, int id, boolean closeStatement, boolean scrollable, boolean updatable)
  {
    setTrace(conn.getSession().getTrace(), 4, id);
    this.conn = conn;
    this.stat = stat;
    this.result = result;
    this.columnCount = result.getVisibleColumnCount();
    this.closeStatement = closeStatement;
    this.scrollable = scrollable;
    this.updatable = updatable;
  }
  
  JdbcResultSet(JdbcConnection conn, JdbcPreparedStatement preparedStatement, ResultInterface result, int id, boolean closeStatement, boolean scrollable, boolean updatable, HashMap<String, Integer> columnLabelMap)
  {
    this(conn, preparedStatement, result, id, closeStatement, scrollable, updatable);
    
    this.columnLabelMap = columnLabelMap;
    this.preparedStatement = preparedStatement;
  }
  
  public boolean next()
    throws SQLException
  {
    try
    {
      debugCodeCall("next");
      checkClosed();
      return nextRow();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      int id = getNextId(5);
      if (isDebugEnabled()) {
        debugCodeAssign("ResultSetMetaData", 5, id, "getMetaData()");
      }
      checkClosed();
      String catalog = this.conn.getCatalog();
      return new JdbcResultSetMetaData(this, null, this.result, catalog, this.conn.getSession().getTrace(), id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      debugCodeCall("wasNull");
      checkClosed();
      return this.wasNull;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int findColumn(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("findColumn", columnLabel);
      return getColumnIndex(columnLabel);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      debugCodeCall("close");
      closeInternal();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  void closeInternal()
    throws SQLException
  {
    if (this.result != null) {
      try
      {
        this.result.close();
        if ((this.closeStatement) && (this.stat != null)) {
          this.stat.close();
        }
      }
      finally
      {
        this.columnCount = 0;
        this.result = null;
        this.stat = null;
        this.conn = null;
        this.insertRow = null;
        this.updateRow = null;
      }
    }
  }
  
  public Statement getStatement()
    throws SQLException
  {
    try
    {
      debugCodeCall("getStatement");
      checkClosed();
      if (this.closeStatement) {
        return null;
      }
      return this.stat;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      debugCodeCall("getWarnings");
      checkClosed();
      return null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      debugCodeCall("clearWarnings");
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getString(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getString", columnIndex);
      return get(columnIndex).getString();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getString(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getString", columnLabel);
      return get(columnLabel).getString();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getInt(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getInt", columnIndex);
      return get(columnIndex).getInt();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getInt(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getInt", columnLabel);
      return get(columnLabel).getInt();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public BigDecimal getBigDecimal(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBigDecimal", columnIndex);
      return get(columnIndex).getBigDecimal();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Date getDate(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getDate", columnIndex);
      return get(columnIndex).getDate();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Time getTime(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getTime", columnIndex);
      return get(columnIndex).getTime();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Timestamp getTimestamp(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getTimestamp", columnIndex);
      return get(columnIndex).getTimestamp();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public BigDecimal getBigDecimal(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBigDecimal", columnLabel);
      return get(columnLabel).getBigDecimal();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Date getDate(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getDate", columnLabel);
      return get(columnLabel).getDate();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Time getTime(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getTime", columnLabel);
      return get(columnLabel).getTime();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Timestamp getTimestamp(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getTimestamp", columnLabel);
      return get(columnLabel).getTimestamp();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Object getObject(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getObject", columnIndex);
      Value v = get(columnIndex);
      return this.conn.convertToDefaultObject(v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Object getObject(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getObject", columnLabel);
      Value v = get(columnLabel);
      return this.conn.convertToDefaultObject(v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean getBoolean(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBoolean", columnIndex);
      Boolean v = get(columnIndex).getBoolean();
      return v == null ? false : v.booleanValue();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean getBoolean(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBoolean", columnLabel);
      Boolean v = get(columnLabel).getBoolean();
      return v == null ? false : v.booleanValue();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public byte getByte(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getByte", columnIndex);
      return get(columnIndex).getByte();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public byte getByte(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getByte", columnLabel);
      return get(columnLabel).getByte();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public short getShort(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getShort", columnIndex);
      return get(columnIndex).getShort();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public short getShort(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getShort", columnLabel);
      return get(columnLabel).getShort();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public long getLong(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getLong", columnIndex);
      return get(columnIndex).getLong();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public long getLong(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getLong", columnLabel);
      return get(columnLabel).getLong();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public float getFloat(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getFloat", columnIndex);
      return get(columnIndex).getFloat();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public float getFloat(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getFloat", columnLabel);
      return get(columnLabel).getFloat();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public double getDouble(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getDouble", columnIndex);
      return get(columnIndex).getDouble();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public double getDouble(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getDouble", columnLabel);
      return get(columnLabel).getDouble();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /**
   * @deprecated
   */
  public BigDecimal getBigDecimal(String columnLabel, int scale)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getBigDecimal(" + StringUtils.quoteJavaString(columnLabel) + ", " + scale + ");");
      }
      if (scale < 0) {
        throw DbException.getInvalidValueException("scale", Integer.valueOf(scale));
      }
      BigDecimal bd = get(columnLabel).getBigDecimal();
      return bd == null ? null : ValueDecimal.setScale(bd, scale);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /**
   * @deprecated
   */
  public BigDecimal getBigDecimal(int columnIndex, int scale)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getBigDecimal(" + columnIndex + ", " + scale + ");");
      }
      if (scale < 0) {
        throw DbException.getInvalidValueException("scale", Integer.valueOf(scale));
      }
      BigDecimal bd = get(columnIndex).getBigDecimal();
      return bd == null ? null : ValueDecimal.setScale(bd, scale);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /**
   * @deprecated
   */
  public InputStream getUnicodeStream(int columnIndex)
    throws SQLException
  {
    throw unsupported("unicodeStream");
  }
  
  /**
   * @deprecated
   */
  public InputStream getUnicodeStream(String columnLabel)
    throws SQLException
  {
    throw unsupported("unicodeStream");
  }
  
  public Object getObject(int columnIndex, Map<String, Class<?>> map)
    throws SQLException
  {
    throw unsupported("map");
  }
  
  public Object getObject(String columnLabel, Map<String, Class<?>> map)
    throws SQLException
  {
    throw unsupported("map");
  }
  
  public Ref getRef(int columnIndex)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public Ref getRef(String columnLabel)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public Date getDate(int columnIndex, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getDate(" + columnIndex + ", calendar)");
      }
      return DateTimeUtils.convertDate(get(columnIndex), calendar);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Date getDate(String columnLabel, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getDate(" + StringUtils.quoteJavaString(columnLabel) + ", calendar)");
      }
      return DateTimeUtils.convertDate(get(columnLabel), calendar);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Time getTime(int columnIndex, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getTime(" + columnIndex + ", calendar)");
      }
      return DateTimeUtils.convertTime(get(columnIndex), calendar);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Time getTime(String columnLabel, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getTime(" + StringUtils.quoteJavaString(columnLabel) + ", calendar)");
      }
      return DateTimeUtils.convertTime(get(columnLabel), calendar);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Timestamp getTimestamp(int columnIndex, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getTimestamp(" + columnIndex + ", calendar)");
      }
      Value value = get(columnIndex);
      return DateTimeUtils.convertTimestamp(value, calendar);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Timestamp getTimestamp(String columnLabel, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getTimestamp(" + StringUtils.quoteJavaString(columnLabel) + ", calendar)");
      }
      Value value = get(columnLabel);
      return DateTimeUtils.convertTimestamp(value, calendar);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Blob getBlob(int columnIndex)
    throws SQLException
  {
    try
    {
      int id = getNextId(9);
      debugCodeAssign("Blob", 9, id, "getBlob(" + columnIndex + ")");
      
      Value v = get(columnIndex);
      return v == ValueNull.INSTANCE ? null : new JdbcBlob(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Blob getBlob(String columnLabel)
    throws SQLException
  {
    try
    {
      int id = getNextId(9);
      debugCodeAssign("Blob", 9, id, "getBlob(" + quote(columnLabel) + ")");
      
      Value v = get(columnLabel);
      return v == ValueNull.INSTANCE ? null : new JdbcBlob(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public byte[] getBytes(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBytes", columnIndex);
      return get(columnIndex).getBytes();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public byte[] getBytes(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBytes", columnLabel);
      return get(columnLabel).getBytes();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public InputStream getBinaryStream(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBinaryStream", columnIndex);
      return get(columnIndex).getInputStream();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public InputStream getBinaryStream(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getBinaryStream", columnLabel);
      return get(columnLabel).getInputStream();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Clob getClob(int columnIndex)
    throws SQLException
  {
    try
    {
      int id = getNextId(10);
      debugCodeAssign("Clob", 10, id, "getClob(" + columnIndex + ")");
      Value v = get(columnIndex);
      return v == ValueNull.INSTANCE ? null : new JdbcClob(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Clob getClob(String columnLabel)
    throws SQLException
  {
    try
    {
      int id = getNextId(10);
      debugCodeAssign("Clob", 10, id, "getClob(" + quote(columnLabel) + ")");
      
      Value v = get(columnLabel);
      return v == ValueNull.INSTANCE ? null : new JdbcClob(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Array getArray(int columnIndex)
    throws SQLException
  {
    try
    {
      int id = getNextId(16);
      debugCodeAssign("Clob", 16, id, "getArray(" + columnIndex + ")");
      Value v = get(columnIndex);
      return v == ValueNull.INSTANCE ? null : new JdbcArray(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Array getArray(String columnLabel)
    throws SQLException
  {
    try
    {
      int id = getNextId(16);
      debugCodeAssign("Clob", 16, id, "getArray(" + quote(columnLabel) + ")");
      
      Value v = get(columnLabel);
      return v == ValueNull.INSTANCE ? null : new JdbcArray(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public InputStream getAsciiStream(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getAsciiStream", columnIndex);
      String s = get(columnIndex).getString();
      return s == null ? null : IOUtils.getInputStreamFromString(s);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public InputStream getAsciiStream(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getAsciiStream", columnLabel);
      String s = get(columnLabel).getString();
      return IOUtils.getInputStreamFromString(s);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Reader getCharacterStream(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getCharacterStream", columnIndex);
      return get(columnIndex).getReader();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Reader getCharacterStream(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getCharacterStream", columnLabel);
      return get(columnLabel).getReader();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public URL getURL(int columnIndex)
    throws SQLException
  {
    throw unsupported("url");
  }
  
  public URL getURL(String columnLabel)
    throws SQLException
  {
    throw unsupported("url");
  }
  
  public void updateNull(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("updateNull", columnIndex);
      update(columnIndex, ValueNull.INSTANCE);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateNull(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("updateNull", columnLabel);
      update(columnLabel, ValueNull.INSTANCE);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBoolean(int columnIndex, boolean x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBoolean(" + columnIndex + ", " + x + ");");
      }
      update(columnIndex, ValueBoolean.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBoolean(String columnLabel, boolean x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBoolean(" + quote(columnLabel) + ", " + x + ");");
      }
      update(columnLabel, ValueBoolean.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateByte(int columnIndex, byte x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateByte(" + columnIndex + ", " + x + ");");
      }
      update(columnIndex, ValueByte.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateByte(String columnLabel, byte x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateByte(" + columnLabel + ", " + x + ");");
      }
      update(columnLabel, ValueByte.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBytes(int columnIndex, byte[] x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBytes(" + columnIndex + ", x);");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueBytes.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBytes(String columnLabel, byte[] x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBytes(" + quote(columnLabel) + ", x);");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueBytes.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateShort(int columnIndex, short x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateShort(" + columnIndex + ", (short) " + x + ");");
      }
      update(columnIndex, ValueShort.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateShort(String columnLabel, short x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateShort(" + quote(columnLabel) + ", (short) " + x + ");");
      }
      update(columnLabel, ValueShort.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateInt(int columnIndex, int x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateInt(" + columnIndex + ", " + x + ");");
      }
      update(columnIndex, ValueInt.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateInt(String columnLabel, int x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateInt(" + quote(columnLabel) + ", " + x + ");");
      }
      update(columnLabel, ValueInt.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateLong(int columnIndex, long x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateLong(" + columnIndex + ", " + x + "L);");
      }
      update(columnIndex, ValueLong.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateLong(String columnLabel, long x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateLong(" + quote(columnLabel) + ", " + x + "L);");
      }
      update(columnLabel, ValueLong.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateFloat(int columnIndex, float x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateFloat(" + columnIndex + ", " + x + "f);");
      }
      update(columnIndex, ValueFloat.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateFloat(String columnLabel, float x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateFloat(" + quote(columnLabel) + ", " + x + "f);");
      }
      update(columnLabel, ValueFloat.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateDouble(int columnIndex, double x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateDouble(" + columnIndex + ", " + x + "d);");
      }
      update(columnIndex, ValueDouble.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateDouble(String columnLabel, double x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateDouble(" + quote(columnLabel) + ", " + x + "d);");
      }
      update(columnLabel, ValueDouble.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBigDecimal(int columnIndex, BigDecimal x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBigDecimal(" + columnIndex + ", " + quoteBigDecimal(x) + ");");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueDecimal.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBigDecimal(String columnLabel, BigDecimal x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBigDecimal(" + quote(columnLabel) + ", " + quoteBigDecimal(x) + ");");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueDecimal.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateString(int columnIndex, String x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateString(" + columnIndex + ", " + quote(x) + ");");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueString.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateString(String columnLabel, String x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateString(" + quote(columnLabel) + ", " + quote(x) + ");");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueString.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateDate(int columnIndex, Date x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateDate(" + columnIndex + ", x);");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueDate.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateDate(String columnLabel, Date x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateDate(" + quote(columnLabel) + ", x);");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueDate.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateTime(int columnIndex, Time x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateTime(" + columnIndex + ", x);");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueTime.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateTime(String columnLabel, Time x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateTime(" + quote(columnLabel) + ", x);");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueTime.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateTimestamp(int columnIndex, Timestamp x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateTimestamp(" + columnIndex + ", x);");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueTimestamp.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateTimestamp(String columnLabel, Timestamp x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateTimestamp(" + quote(columnLabel) + ", x);");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueTimestamp.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateAsciiStream(int columnIndex, InputStream x, int length)
    throws SQLException
  {
    updateAsciiStream(columnIndex, x, length);
  }
  
  public void updateAsciiStream(int columnIndex, InputStream x)
    throws SQLException
  {
    updateAsciiStream(columnIndex, x, -1);
  }
  
  public void updateAsciiStream(int columnIndex, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateAsciiStream(" + columnIndex + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(IOUtils.getAsciiReader(x), length);
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateAsciiStream(String columnLabel, InputStream x, int length)
    throws SQLException
  {
    updateAsciiStream(columnLabel, x, length);
  }
  
  public void updateAsciiStream(String columnLabel, InputStream x)
    throws SQLException
  {
    updateAsciiStream(columnLabel, x, -1);
  }
  
  public void updateAsciiStream(String columnLabel, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateAsciiStream(" + quote(columnLabel) + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(IOUtils.getAsciiReader(x), length);
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBinaryStream(int columnIndex, InputStream x, int length)
    throws SQLException
  {
    updateBinaryStream(columnIndex, x, length);
  }
  
  public void updateBinaryStream(int columnIndex, InputStream x)
    throws SQLException
  {
    updateBinaryStream(columnIndex, x, -1);
  }
  
  public void updateBinaryStream(int columnIndex, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBinaryStream(" + columnIndex + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createBlob(x, length);
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBinaryStream(String columnLabel, InputStream x)
    throws SQLException
  {
    updateBinaryStream(columnLabel, x, -1);
  }
  
  public void updateBinaryStream(String columnLabel, InputStream x, int length)
    throws SQLException
  {
    updateBinaryStream(columnLabel, x, length);
  }
  
  public void updateBinaryStream(String columnLabel, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBinaryStream(" + quote(columnLabel) + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createBlob(x, length);
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateCharacterStream(int columnIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateCharacterStream(" + columnIndex + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(x, length);
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateCharacterStream(int columnIndex, Reader x, int length)
    throws SQLException
  {
    updateCharacterStream(columnIndex, x, length);
  }
  
  public void updateCharacterStream(int columnIndex, Reader x)
    throws SQLException
  {
    updateCharacterStream(columnIndex, x, -1);
  }
  
  public void updateCharacterStream(String columnLabel, Reader x, int length)
    throws SQLException
  {
    updateCharacterStream(columnLabel, x, length);
  }
  
  public void updateCharacterStream(String columnLabel, Reader x)
    throws SQLException
  {
    updateCharacterStream(columnLabel, x, -1);
  }
  
  public void updateCharacterStream(String columnLabel, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateCharacterStream(" + quote(columnLabel) + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(x, length);
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateObject(int columnIndex, Object x, int scale)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateObject(" + columnIndex + ", x, " + scale + ");");
      }
      update(columnIndex, convertToUnknownValue(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateObject(String columnLabel, Object x, int scale)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateObject(" + quote(columnLabel) + ", x, " + scale + ");");
      }
      update(columnLabel, convertToUnknownValue(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateObject(int columnIndex, Object x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateObject(" + columnIndex + ", x);");
      }
      update(columnIndex, convertToUnknownValue(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateObject(String columnLabel, Object x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateObject(" + quote(columnLabel) + ", x);");
      }
      update(columnLabel, convertToUnknownValue(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateRef(int columnIndex, Ref x)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public void updateRef(String columnLabel, Ref x)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public void updateBlob(int columnIndex, InputStream x)
    throws SQLException
  {
    updateBlob(columnIndex, x, -1L);
  }
  
  public void updateBlob(int columnIndex, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBlob(" + columnIndex + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createBlob(x, length);
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBlob(int columnIndex, Blob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBlob(" + columnIndex + ", x);");
      }
      checkClosed();
      Value v;
      Value v;
      if (x == null) {
        v = ValueNull.INSTANCE;
      } else {
        v = this.conn.createBlob(x.getBinaryStream(), -1L);
      }
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBlob(String columnLabel, Blob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBlob(" + quote(columnLabel) + ", x);");
      }
      checkClosed();
      Value v;
      Value v;
      if (x == null) {
        v = ValueNull.INSTANCE;
      } else {
        v = this.conn.createBlob(x.getBinaryStream(), -1L);
      }
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateBlob(String columnLabel, InputStream x)
    throws SQLException
  {
    updateBlob(columnLabel, x, -1L);
  }
  
  public void updateBlob(String columnLabel, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateBlob(" + quote(columnLabel) + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createBlob(x, -1L);
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateClob(int columnIndex, Clob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateClob(" + columnIndex + ", x);");
      }
      checkClosed();
      Value v;
      Value v;
      if (x == null) {
        v = ValueNull.INSTANCE;
      } else {
        v = this.conn.createClob(x.getCharacterStream(), -1L);
      }
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateClob(int columnIndex, Reader x)
    throws SQLException
  {
    updateClob(columnIndex, x, -1L);
  }
  
  public void updateClob(int columnIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateClob(" + columnIndex + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(x, length);
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateClob(String columnLabel, Clob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateClob(" + quote(columnLabel) + ", x);");
      }
      checkClosed();
      Value v;
      Value v;
      if (x == null) {
        v = ValueNull.INSTANCE;
      } else {
        v = this.conn.createClob(x.getCharacterStream(), -1L);
      }
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateClob(String columnLabel, Reader x)
    throws SQLException
  {
    updateClob(columnLabel, x, -1L);
  }
  
  public void updateClob(String columnLabel, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateClob(" + quote(columnLabel) + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(x, length);
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateArray(int columnIndex, Array x)
    throws SQLException
  {
    throw unsupported("setArray");
  }
  
  public void updateArray(String columnLabel, Array x)
    throws SQLException
  {
    throw unsupported("setArray");
  }
  
  public String getCursorName()
    throws SQLException
  {
    throw unsupported("cursorName");
  }
  
  public int getRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("getRow");
      checkClosed();
      int rowId = this.result.getRowId();
      if (rowId >= this.result.getRowCount()) {
        return 0;
      }
      return rowId + 1;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getConcurrency()
    throws SQLException
  {
    try
    {
      debugCodeCall("getConcurrency");
      checkClosed();
      if (!this.updatable) {
        return 1007;
      }
      UpdatableRow row = new UpdatableRow(this.conn, this.result);
      return row.isUpdatable() ? 1008 : 1007;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      debugCodeCall("getFetchDirection");
      checkClosed();
      return 1000;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      debugCodeCall("getFetchSize");
      checkClosed();
      return this.result.getFetchSize();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setFetchSize(int rows)
    throws SQLException
  {
    try
    {
      debugCodeCall("setFetchSize", rows);
      checkClosed();
      if (rows < 0) {
        throw DbException.getInvalidValueException("rows", Integer.valueOf(rows));
      }
      if (rows > 0)
      {
        if (this.stat != null)
        {
          int maxRows = this.stat.getMaxRows();
          if ((maxRows > 0) && (rows > maxRows)) {
            throw DbException.getInvalidValueException("rows", Integer.valueOf(rows));
          }
        }
      }
      else {
        rows = SysProperties.SERVER_RESULT_SET_FETCH_SIZE;
      }
      this.result.setFetchSize(rows);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setFetchDirection(int direction)
    throws SQLException
  {
    throw unsupported("setFetchDirection");
  }
  
  public int getType()
    throws SQLException
  {
    try
    {
      debugCodeCall("getType");
      checkClosed();
      return this.stat == null ? 1003 : this.stat.resultSetType;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isBeforeFirst()
    throws SQLException
  {
    try
    {
      debugCodeCall("isBeforeFirst");
      checkClosed();
      int row = this.result.getRowId();
      int count = this.result.getRowCount();
      return (count > 0) && (row < 0);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isAfterLast()
    throws SQLException
  {
    try
    {
      debugCodeCall("isAfterLast");
      checkClosed();
      int row = this.result.getRowId();
      int count = this.result.getRowCount();
      return (count > 0) && (row >= count);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isFirst()
    throws SQLException
  {
    try
    {
      debugCodeCall("isFirst");
      checkClosed();
      int row = this.result.getRowId();
      return (row == 0) && (row < this.result.getRowCount());
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isLast()
    throws SQLException
  {
    try
    {
      debugCodeCall("isLast");
      checkClosed();
      int row = this.result.getRowId();
      return (row >= 0) && (row == this.result.getRowCount() - 1);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void beforeFirst()
    throws SQLException
  {
    try
    {
      debugCodeCall("beforeFirst");
      checkClosed();
      if (this.result.getRowId() >= 0) {
        resetResult();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void afterLast()
    throws SQLException
  {
    try
    {
      debugCodeCall("afterLast");
      checkClosed();
      while (nextRow()) {}
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean first()
    throws SQLException
  {
    try
    {
      debugCodeCall("first");
      checkClosed();
      if (this.result.getRowId() < 0) {
        return nextRow();
      }
      resetResult();
      return nextRow();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean last()
    throws SQLException
  {
    try
    {
      debugCodeCall("last");
      checkClosed();
      return absolute(-1);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean absolute(int rowNumber)
    throws SQLException
  {
    try
    {
      debugCodeCall("absolute", rowNumber);
      checkClosed();
      if (rowNumber < 0) {
        rowNumber = this.result.getRowCount() + rowNumber + 1;
      } else if (rowNumber > this.result.getRowCount() + 1) {
        rowNumber = this.result.getRowCount() + 1;
      }
      if (rowNumber <= this.result.getRowId()) {
        resetResult();
      }
      while (this.result.getRowId() + 1 < rowNumber) {
        nextRow();
      }
      int row = this.result.getRowId();
      return (row >= 0) && (row < this.result.getRowCount());
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean relative(int rowCount)
    throws SQLException
  {
    try
    {
      debugCodeCall("relative", rowCount);
      checkClosed();
      int row = this.result.getRowId() + 1 + rowCount;
      if (row < 0) {
        row = 0;
      } else if (row > this.result.getRowCount()) {
        row = this.result.getRowCount() + 1;
      }
      return absolute(row);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean previous()
    throws SQLException
  {
    try
    {
      debugCodeCall("previous");
      checkClosed();
      return relative(-1);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void moveToInsertRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("moveToInsertRow");
      checkUpdatable();
      this.insertRow = new Value[this.columnCount];
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void moveToCurrentRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("moveToCurrentRow");
      checkUpdatable();
      this.insertRow = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean rowUpdated()
    throws SQLException
  {
    try
    {
      debugCodeCall("rowUpdated");
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean rowInserted()
    throws SQLException
  {
    try
    {
      debugCodeCall("rowInserted");
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean rowDeleted()
    throws SQLException
  {
    try
    {
      debugCodeCall("rowDeleted");
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void insertRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("insertRow");
      checkUpdatable();
      if (this.insertRow == null) {
        throw DbException.get(90029);
      }
      getUpdatableRow().insertRow(this.insertRow);
      this.insertRow = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("updateRow");
      checkUpdatable();
      if (this.insertRow != null) {
        throw DbException.get(90029);
      }
      checkOnValidRow();
      if (this.updateRow != null)
      {
        UpdatableRow row = getUpdatableRow();
        Value[] current = new Value[this.columnCount];
        for (int i = 0; i < this.updateRow.length; i++) {
          current[i] = get(i + 1);
        }
        row.updateRow(current, this.updateRow);
        for (int i = 0; i < this.updateRow.length; i++) {
          if (this.updateRow[i] == null) {
            this.updateRow[i] = current[i];
          }
        }
        Value[] patch = row.readRow(this.updateRow);
        patchCurrentRow(patch);
        this.updateRow = null;
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void deleteRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("deleteRow");
      checkUpdatable();
      if (this.insertRow != null) {
        throw DbException.get(90029);
      }
      checkOnValidRow();
      getUpdatableRow().deleteRow(this.result.currentRow());
      this.updateRow = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void refreshRow()
    throws SQLException
  {
    try
    {
      debugCodeCall("refreshRow");
      checkClosed();
      if (this.insertRow != null) {
        throw DbException.get(2000);
      }
      checkOnValidRow();
      patchCurrentRow(getUpdatableRow().readRow(this.result.currentRow()));
      this.updateRow = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void cancelRowUpdates()
    throws SQLException
  {
    try
    {
      debugCodeCall("cancelRowUpdates");
      checkClosed();
      if (this.insertRow != null) {
        throw DbException.get(2000);
      }
      this.updateRow = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private UpdatableRow getUpdatableRow()
    throws SQLException
  {
    UpdatableRow row = new UpdatableRow(this.conn, this.result);
    if (!row.isUpdatable()) {
      throw DbException.get(90127);
    }
    return row;
  }
  
  private int getColumnIndex(String columnLabel)
  {
    checkClosed();
    if (columnLabel == null) {
      throw DbException.getInvalidValueException("columnLabel", null);
    }
    if (this.columnCount >= 3)
    {
      if (this.columnLabelMap == null)
      {
        HashMap<String, Integer> map = New.hashMap(this.columnCount);
        for (int i = 0; i < this.columnCount; i++)
        {
          String c = StringUtils.toUpperEnglish(this.result.getAlias(i));
          mapColumn(map, c, i);
        }
        for (int i = 0; i < this.columnCount; i++)
        {
          String colName = this.result.getColumnName(i);
          if (colName != null)
          {
            colName = StringUtils.toUpperEnglish(colName);
            mapColumn(map, colName, i);
            String tabName = this.result.getTableName(i);
            if (tabName != null)
            {
              colName = StringUtils.toUpperEnglish(tabName) + "." + colName;
              mapColumn(map, colName, i);
            }
          }
        }
        this.columnLabelMap = map;
        if (this.preparedStatement != null) {
          this.preparedStatement.setCachedColumnLabelMap(this.columnLabelMap);
        }
      }
      Integer index = (Integer)this.columnLabelMap.get(StringUtils.toUpperEnglish(columnLabel));
      if (index == null) {
        throw DbException.get(42122, columnLabel);
      }
      return index.intValue() + 1;
    }
    for (int i = 0; i < this.columnCount; i++) {
      if (columnLabel.equalsIgnoreCase(this.result.getAlias(i))) {
        return i + 1;
      }
    }
    int idx = columnLabel.indexOf('.');
    if (idx > 0)
    {
      String table = columnLabel.substring(0, idx);
      String col = columnLabel.substring(idx + 1);
      for (int i = 0; i < this.columnCount; i++) {
        if ((table.equalsIgnoreCase(this.result.getTableName(i))) && (col.equalsIgnoreCase(this.result.getColumnName(i)))) {
          return i + 1;
        }
      }
    }
    else
    {
      for (int i = 0; i < this.columnCount; i++) {
        if (columnLabel.equalsIgnoreCase(this.result.getColumnName(i))) {
          return i + 1;
        }
      }
    }
    throw DbException.get(42122, columnLabel);
  }
  
  private static void mapColumn(HashMap<String, Integer> map, String label, int index)
  {
    Integer old = (Integer)map.put(label, Integer.valueOf(index));
    if (old != null) {
      map.put(label, old);
    }
  }
  
  private void checkColumnIndex(int columnIndex)
  {
    checkClosed();
    if ((columnIndex < 1) || (columnIndex > this.columnCount)) {
      throw DbException.getInvalidValueException("columnIndex", Integer.valueOf(columnIndex));
    }
  }
  
  void checkClosed()
  {
    if (this.result == null) {
      throw DbException.get(90007);
    }
    if (this.stat != null) {
      this.stat.checkClosed();
    }
    if (this.conn != null) {
      this.conn.checkClosed();
    }
  }
  
  private void checkOnValidRow()
  {
    if ((this.result.getRowId() < 0) || (this.result.getRowId() >= this.result.getRowCount())) {
      throw DbException.get(2000);
    }
  }
  
  private Value get(int columnIndex)
  {
    checkColumnIndex(columnIndex);
    checkOnValidRow();
    Value[] list;
    Value[] list;
    if (this.patchedRows == null)
    {
      list = this.result.currentRow();
    }
    else
    {
      list = (Value[])this.patchedRows.get(Integer.valueOf(this.result.getRowId()));
      if (list == null) {
        list = this.result.currentRow();
      }
    }
    Value value = list[(columnIndex - 1)];
    this.wasNull = (value == ValueNull.INSTANCE);
    return value;
  }
  
  private Value get(String columnLabel)
  {
    int columnIndex = getColumnIndex(columnLabel);
    return get(columnIndex);
  }
  
  private void update(String columnLabel, Value v)
  {
    int columnIndex = getColumnIndex(columnLabel);
    update(columnIndex, v);
  }
  
  private void update(int columnIndex, Value v)
  {
    checkUpdatable();
    checkColumnIndex(columnIndex);
    if (this.insertRow != null)
    {
      this.insertRow[(columnIndex - 1)] = v;
    }
    else
    {
      if (this.updateRow == null) {
        this.updateRow = new Value[this.columnCount];
      }
      this.updateRow[(columnIndex - 1)] = v;
    }
  }
  
  private boolean nextRow()
  {
    boolean next = this.result.next();
    if ((!next) && (!this.scrollable)) {
      this.result.close();
    }
    return next;
  }
  
  private void resetResult()
  {
    if (!this.scrollable) {
      throw DbException.get(90128);
    }
    this.result.reset();
  }
  
  public RowId getRowId(int columnIndex)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public RowId getRowId(String columnLabel)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public void updateRowId(int columnIndex, RowId x)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public void updateRowId(String columnLabel, RowId x)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      debugCodeCall("getHoldability");
      checkClosed();
      return this.conn.getHoldability();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      debugCodeCall("isClosed");
      return this.result == null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateNString(int columnIndex, String x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateNString(" + columnIndex + ", " + quote(x) + ");");
      }
      update(columnIndex, x == null ? ValueNull.INSTANCE : ValueString.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateNString(String columnLabel, String x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateNString(" + quote(columnLabel) + ", " + quote(x) + ");");
      }
      update(columnLabel, x == null ? ValueNull.INSTANCE : ValueString.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateNClob(int columnIndex, NClob x)
    throws SQLException
  {
    throw unsupported("NClob");
  }
  
  public void updateNClob(int columnIndex, Reader x)
    throws SQLException
  {
    updateClob(columnIndex, x, -1L);
  }
  
  public void updateNClob(int columnIndex, Reader x, long length)
    throws SQLException
  {
    updateClob(columnIndex, x, length);
  }
  
  public void updateNClob(String columnLabel, Reader x)
    throws SQLException
  {
    updateClob(columnLabel, x, -1L);
  }
  
  public void updateNClob(String columnLabel, Reader x, long length)
    throws SQLException
  {
    updateClob(columnLabel, x, length);
  }
  
  public void updateNClob(String columnLabel, NClob x)
    throws SQLException
  {
    throw unsupported("NClob");
  }
  
  public NClob getNClob(int columnIndex)
    throws SQLException
  {
    try
    {
      int id = getNextId(10);
      debugCodeAssign("NClob", 10, id, "getNClob(" + columnIndex + ")");
      Value v = get(columnIndex);
      return v == ValueNull.INSTANCE ? null : new JdbcClob(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public NClob getNClob(String columnLabel)
    throws SQLException
  {
    try
    {
      int id = getNextId(10);
      debugCodeAssign("NClob", 10, id, "getNClob(" + columnLabel + ")");
      Value v = get(columnLabel);
      return v == ValueNull.INSTANCE ? null : new JdbcClob(this.conn, v, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public SQLXML getSQLXML(int columnIndex)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public SQLXML getSQLXML(String columnLabel)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public void updateSQLXML(int columnIndex, SQLXML xmlObject)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public void updateSQLXML(String columnLabel, SQLXML xmlObject)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public String getNString(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getNString", columnIndex);
      return get(columnIndex).getString();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getNString(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getNString", columnLabel);
      return get(columnLabel).getString();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Reader getNCharacterStream(int columnIndex)
    throws SQLException
  {
    try
    {
      debugCodeCall("getNCharacterStream", columnIndex);
      return get(columnIndex).getReader();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Reader getNCharacterStream(String columnLabel)
    throws SQLException
  {
    try
    {
      debugCodeCall("getNCharacterStream", columnLabel);
      return get(columnLabel).getReader();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateNCharacterStream(int columnIndex, Reader x)
    throws SQLException
  {
    updateNCharacterStream(columnIndex, x, -1L);
  }
  
  public void updateNCharacterStream(int columnIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateNCharacterStream(" + columnIndex + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(x, length);
      update(columnIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void updateNCharacterStream(String columnLabel, Reader x)
    throws SQLException
  {
    updateNCharacterStream(columnLabel, x, -1L);
  }
  
  public void updateNCharacterStream(String columnLabel, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("updateNCharacterStream(" + quote(columnLabel) + ", x, " + length + "L);");
      }
      checkClosed();
      Value v = this.conn.createClob(x, length);
      update(columnLabel, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
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
    return getTraceObjectName() + ": " + this.result;
  }
  
  private void patchCurrentRow(Value[] row)
  {
    boolean changed = false;
    Value[] current = this.result.currentRow();
    CompareMode mode = this.conn.getCompareMode();
    for (int i = 0; i < row.length; i++) {
      if (row[i].compareTo(current[i], mode) != 0)
      {
        changed = true;
        break;
      }
    }
    if (this.patchedRows == null) {
      this.patchedRows = New.hashMap();
    }
    Integer rowId = Integer.valueOf(this.result.getRowId());
    if (!changed) {
      this.patchedRows.remove(rowId);
    } else {
      this.patchedRows.put(rowId, row);
    }
  }
  
  private Value convertToUnknownValue(Object x)
  {
    checkClosed();
    
    return null;
  }
  
  private void checkUpdatable()
  {
    checkClosed();
    if (!this.updatable) {
      throw DbException.get(90140);
    }
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
}
