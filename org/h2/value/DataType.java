package org.h2.value;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.h2.engine.Constants;
import org.h2.engine.SessionInterface;
import org.h2.engine.SysProperties;
import org.h2.jdbc.JdbcBlob;
import org.h2.jdbc.JdbcClob;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.store.DataHandler;
import org.h2.store.LobStorageInterface;
import org.h2.util.JdbcUtils;
import org.h2.util.New;
import org.h2.util.Utils;

public class DataType
{
  public static final int TYPE_RESULT_SET = -10;
  public static final Class<?> GEOMETRY_CLASS;
  private static final String GEOMETRY_CLASS_NAME = "com.vividsolutions.jts.geom.Geometry";
  private static final ArrayList<DataType> TYPES = ;
  private static final HashMap<String, DataType> TYPES_BY_NAME = New.hashMap();
  private static final ArrayList<DataType> TYPES_BY_VALUE_TYPE = New.arrayList();
  public int type;
  public String name;
  public int sqlType;
  public String jdbc;
  public int sqlTypePos;
  public long maxPrecision;
  public int minScale;
  public int maxScale;
  public boolean decimal;
  public String prefix;
  public String suffix;
  public String params;
  public boolean autoIncrement;
  public boolean caseSensitive;
  public boolean supportsPrecision;
  public boolean supportsScale;
  public long defaultPrecision;
  public int defaultScale;
  public int defaultDisplaySize;
  public boolean hidden;
  public int memory;
  
  static
  {
    Class<?> g;
    try
    {
      g = JdbcUtils.loadUserClass("com.vividsolutions.jts.geom.Geometry");
    }
    catch (Exception e)
    {
      g = null;
    }
    GEOMETRY_CLASS = g;
    for (int i = 0; i < 23; i++) {
      TYPES_BY_VALUE_TYPE.add(null);
    }
    add(0, 0, "Null", new DataType(), new String[] { "NULL" }, 0);
    
    add(13, 12, "String", createString(true), new String[] { "VARCHAR", "VARCHAR2", "NVARCHAR", "NVARCHAR2", "VARCHAR_CASESENSITIVE", "CHARACTER VARYING", "TID" }, 48);
    
    add(13, -1, "String", createString(true), new String[] { "LONGVARCHAR", "LONGNVARCHAR" }, 48);
    
    add(21, 1, "String", createString(true), new String[] { "CHAR", "CHARACTER", "NCHAR" }, 48);
    
    add(14, 12, "String", createString(false), new String[] { "VARCHAR_IGNORECASE" }, 48);
    
    add(1, 16, "Boolean", createDecimal(1, 1, 0, 5, false, false), new String[] { "BOOLEAN", "BIT", "BOOL" }, 0);
    
    add(2, -6, "Byte", createDecimal(3, 3, 0, 4, false, false), new String[] { "TINYINT" }, 1);
    
    add(3, 5, "Short", createDecimal(5, 5, 0, 6, false, false), new String[] { "SMALLINT", "YEAR", "INT2" }, 20);
    
    add(4, 4, "Int", createDecimal(10, 10, 0, 11, false, false), new String[] { "INTEGER", "INT", "MEDIUMINT", "INT4", "SIGNED" }, 20);
    
    add(4, 4, "Int", createDecimal(10, 10, 0, 11, false, true), new String[] { "SERIAL" }, 20);
    
    add(5, -5, "Long", createDecimal(19, 19, 0, 20, false, false), new String[] { "BIGINT", "INT8", "LONG" }, 24);
    
    add(5, -5, "Long", createDecimal(19, 19, 0, 20, false, true), new String[] { "IDENTITY", "BIGSERIAL" }, 24);
    
    add(6, 3, "BigDecimal", createDecimal(Integer.MAX_VALUE, 65535, 32767, 65535, true, false), new String[] { "DECIMAL", "DEC" }, 64);
    
    add(6, 2, "BigDecimal", createDecimal(Integer.MAX_VALUE, 65535, 32767, 65535, true, false), new String[] { "NUMERIC", "NUMBER" }, 64);
    
    add(8, 7, "Float", createDecimal(7, 7, 0, 15, false, false), new String[] { "REAL", "FLOAT4" }, 24);
    
    add(7, 8, "Double", createDecimal(17, 17, 0, 24, false, false), new String[] { "DOUBLE", "DOUBLE PRECISION" }, 24);
    
    add(7, 6, "Double", createDecimal(17, 17, 0, 24, false, false), new String[] { "FLOAT", "FLOAT8" }, 24);
    
    add(9, 92, "Time", createDate(6, "TIME", 0, 8), new String[] { "TIME" }, 56);
    
    add(10, 91, "Date", createDate(8, "DATE", 0, 10), new String[] { "DATE" }, 56);
    
    add(11, 93, "Timestamp", createDate(23, "TIMESTAMP", 10, 23), new String[] { "TIMESTAMP", "DATETIME", "DATETIME2", "SMALLDATETIME" }, 56);
    
    add(12, -3, "Bytes", createString(false), new String[] { "VARBINARY" }, 32);
    
    add(12, -2, "Bytes", createString(false), new String[] { "BINARY", "RAW", "BYTEA", "LONG RAW" }, 32);
    
    add(12, -4, "Bytes", createString(false), new String[] { "LONGVARBINARY" }, 32);
    
    add(20, -2, "Bytes", createString(false), new String[] { "UUID" }, 32);
    
    add(19, 1111, "Object", createString(false), new String[] { "OTHER", "OBJECT", "JAVA_OBJECT" }, 24);
    
    add(15, 2004, "Blob", createLob(), new String[] { "BLOB", "TINYBLOB", "MEDIUMBLOB", "LONGBLOB", "IMAGE", "OID" }, 104);
    
    add(16, 2005, "Clob", createLob(), new String[] { "CLOB", "TINYTEXT", "TEXT", "MEDIUMTEXT", "LONGTEXT", "NTEXT", "NCLOB" }, 104);
    
    add(22, 1111, "Geometry", createString(false), new String[] { "GEOMETRY" }, 32);
    
    DataType dataType = new DataType();
    dataType.prefix = "(";
    dataType.suffix = "')";
    add(17, 2003, "Array", dataType, new String[] { "ARRAY" }, 32);
    
    dataType = new DataType();
    add(18, -10, "ResultSet", dataType, new String[] { "RESULT_SET" }, 400);
    
    int i = 0;
    for (int size = TYPES_BY_VALUE_TYPE.size(); i < size; i++)
    {
      DataType dt = (DataType)TYPES_BY_VALUE_TYPE.get(i);
      if (dt == null) {
        DbException.throwInternalError("unmapped type " + i);
      }
      Value.getOrder(i);
    }
  }
  
  private static void add(int type, int sqlType, String jdbc, DataType dataType, String[] names, int memory)
  {
    for (int i = 0; i < names.length; i++)
    {
      DataType dt = new DataType();
      dt.type = type;
      dt.sqlType = sqlType;
      dt.jdbc = jdbc;
      dt.name = names[i];
      dt.autoIncrement = dataType.autoIncrement;
      dt.decimal = dataType.decimal;
      dt.maxPrecision = dataType.maxPrecision;
      dt.maxScale = dataType.maxScale;
      dt.minScale = dataType.minScale;
      dt.params = dataType.params;
      dt.prefix = dataType.prefix;
      dt.suffix = dataType.suffix;
      dt.supportsPrecision = dataType.supportsPrecision;
      dt.supportsScale = dataType.supportsScale;
      dt.defaultPrecision = dataType.defaultPrecision;
      dt.defaultScale = dataType.defaultScale;
      dt.defaultDisplaySize = dataType.defaultDisplaySize;
      dt.caseSensitive = dataType.caseSensitive;
      dt.hidden = (i > 0);
      dt.memory = memory;
      for (DataType t2 : TYPES) {
        if (t2.sqlType == dt.sqlType) {
          dt.sqlTypePos += 1;
        }
      }
      TYPES_BY_NAME.put(dt.name, dt);
      if (TYPES_BY_VALUE_TYPE.get(type) == null) {
        TYPES_BY_VALUE_TYPE.set(type, dt);
      }
      TYPES.add(dt);
    }
  }
  
  private static DataType createDecimal(int maxPrecision, int defaultPrecision, int defaultScale, int defaultDisplaySize, boolean needsPrecisionAndScale, boolean autoInc)
  {
    DataType dataType = new DataType();
    dataType.maxPrecision = maxPrecision;
    dataType.defaultPrecision = defaultPrecision;
    dataType.defaultScale = defaultScale;
    dataType.defaultDisplaySize = defaultDisplaySize;
    if (needsPrecisionAndScale)
    {
      dataType.params = "PRECISION,SCALE";
      dataType.supportsPrecision = true;
      dataType.supportsScale = true;
    }
    dataType.decimal = true;
    dataType.autoIncrement = autoInc;
    return dataType;
  }
  
  private static DataType createDate(int precision, String prefix, int scale, int displaySize)
  {
    DataType dataType = new DataType();
    dataType.prefix = (prefix + " '");
    dataType.suffix = "'";
    dataType.maxPrecision = precision;
    dataType.supportsScale = (scale != 0);
    dataType.maxScale = scale;
    dataType.defaultPrecision = precision;
    dataType.defaultScale = scale;
    dataType.defaultDisplaySize = displaySize;
    return dataType;
  }
  
  private static DataType createString(boolean caseSensitive)
  {
    DataType dataType = new DataType();
    dataType.prefix = "'";
    dataType.suffix = "'";
    dataType.params = "LENGTH";
    dataType.caseSensitive = caseSensitive;
    dataType.supportsPrecision = true;
    dataType.maxPrecision = 2147483647L;
    dataType.defaultPrecision = 2147483647L;
    dataType.defaultDisplaySize = Integer.MAX_VALUE;
    return dataType;
  }
  
  private static DataType createLob()
  {
    DataType t = createString(true);
    t.maxPrecision = Long.MAX_VALUE;
    t.defaultPrecision = Long.MAX_VALUE;
    return t;
  }
  
  public static ArrayList<DataType> getTypes()
  {
    return TYPES;
  }
  
  public static Value readValue(SessionInterface session, ResultSet rs, int columnIndex, int type)
  {
    try
    {
      Value v;
      Value v;
      Value v;
      Value v;
      switch (type)
      {
      case 0: 
        return ValueNull.INSTANCE;
      case 12: 
        byte[] buff = rs.getBytes(columnIndex);
        v = buff == null ? ValueNull.INSTANCE : ValueBytes.getNoCopy(buff);
        
        break;
      case 20: 
        byte[] buff = rs.getBytes(columnIndex);
        v = buff == null ? ValueNull.INSTANCE : ValueUuid.get(buff);
        
        break;
      case 1: 
        boolean value = rs.getBoolean(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueBoolean.get(value);
        
        break;
      case 2: 
        byte value = rs.getByte(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueByte.get(value);
        
        break;
      case 10: 
        java.sql.Date value = rs.getDate(columnIndex);
        v = value == null ? ValueNull.INSTANCE : ValueDate.get(value);
        
        break;
      case 9: 
        Time value = rs.getTime(columnIndex);
        v = value == null ? ValueNull.INSTANCE : ValueTime.get(value);
        
        break;
      case 11: 
        Timestamp value = rs.getTimestamp(columnIndex);
        v = value == null ? ValueNull.INSTANCE : ValueTimestamp.get(value);
        
        break;
      case 6: 
        BigDecimal value = rs.getBigDecimal(columnIndex);
        v = value == null ? ValueNull.INSTANCE : ValueDecimal.get(value);
        
        break;
      case 7: 
        double value = rs.getDouble(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueDouble.get(value);
        
        break;
      case 8: 
        float value = rs.getFloat(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueFloat.get(value);
        
        break;
      case 4: 
        int value = rs.getInt(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueInt.get(value);
        
        break;
      case 5: 
        long value = rs.getLong(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueLong.get(value);
        
        break;
      case 3: 
        short value = rs.getShort(columnIndex);
        v = rs.wasNull() ? ValueNull.INSTANCE : ValueShort.get(value);
        
        break;
      case 14: 
        String s = rs.getString(columnIndex);
        v = s == null ? ValueNull.INSTANCE : ValueStringIgnoreCase.get(s);
        
        break;
      case 21: 
        String s = rs.getString(columnIndex);
        v = s == null ? ValueNull.INSTANCE : ValueStringFixed.get(s);
        
        break;
      case 13: 
        String s = rs.getString(columnIndex);
        v = s == null ? ValueNull.INSTANCE : ValueString.get(s);
        
        break;
      case 16: 
        if (session == null)
        {
          v = ValueLobDb.createSmallLob(16, rs.getString(columnIndex).getBytes(Constants.UTF8));
        }
        else
        {
          Reader in = rs.getCharacterStream(columnIndex);
          Value v;
          if (in == null) {
            v = ValueNull.INSTANCE;
          } else {
            v = session.getDataHandler().getLobStorage().createClob(new BufferedReader(in), -1L);
          }
        }
        break;
      case 15: 
        if (session == null)
        {
          v = ValueLobDb.createSmallLob(15, rs.getBytes(columnIndex));
        }
        else
        {
          InputStream in = rs.getBinaryStream(columnIndex);
          v = in == null ? ValueNull.INSTANCE : session.getDataHandler().getLobStorage().createBlob(in, -1L);
        }
        break;
      case 19: 
        if (SysProperties.serializeJavaObject)
        {
          byte[] buff = rs.getBytes(columnIndex);
          v = buff == null ? ValueNull.INSTANCE : ValueJavaObject.getNoCopy(null, buff, session.getDataHandler());
        }
        else
        {
          Object o = rs.getObject(columnIndex);
          v = o == null ? ValueNull.INSTANCE : ValueJavaObject.getNoCopy(o, null, session.getDataHandler());
        }
        break;
      case 17: 
        Array array = rs.getArray(columnIndex);
        if (array == null) {
          return ValueNull.INSTANCE;
        }
        Object[] list = (Object[])array.getArray();
        if (list == null) {
          return ValueNull.INSTANCE;
        }
        int len = list.length;
        Value[] values = new Value[len];
        for (int i = 0; i < len; i++) {}
        v = ValueArray.get(values);
        break;
      case 18: 
        ResultSet x = (ResultSet)rs.getObject(columnIndex);
        if (x == null) {
          return ValueNull.INSTANCE;
        }
        return ValueResultSet.get(rs);
      case 22: 
        Object x = rs.getObject(columnIndex);
        if (x == null) {
          return ValueNull.INSTANCE;
        }
        break;
      }
      throw DbException.throwInternalError("type=" + type);
      
      return v;
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  public static String getTypeClassName(int type)
  {
    switch (type)
    {
    case 1: 
      return Boolean.class.getName();
    case 2: 
      return Byte.class.getName();
    case 3: 
      return Short.class.getName();
    case 4: 
      return Integer.class.getName();
    case 5: 
      return Long.class.getName();
    case 6: 
      return BigDecimal.class.getName();
    case 9: 
      return Time.class.getName();
    case 10: 
      return java.sql.Date.class.getName();
    case 11: 
      return Timestamp.class.getName();
    case 12: 
    case 20: 
      return byte[].class.getName();
    case 13: 
    case 14: 
    case 21: 
      return String.class.getName();
    case 15: 
      return Blob.class.getName();
    case 16: 
      return Clob.class.getName();
    case 7: 
      return Double.class.getName();
    case 8: 
      return Float.class.getName();
    case 0: 
      return null;
    case 19: 
      return Object.class.getName();
    case -1: 
      return Object.class.getName();
    case 17: 
      return Array.class.getName();
    case 18: 
      return ResultSet.class.getName();
    case 22: 
      return "com.vividsolutions.jts.geom.Geometry";
    }
    throw DbException.throwInternalError("type=" + type);
  }
  
  public static DataType getDataType(int type)
  {
    if (type == -1) {
      throw DbException.get(50004, "?");
    }
    DataType dt = (DataType)TYPES_BY_VALUE_TYPE.get(type);
    if (dt == null) {
      dt = (DataType)TYPES_BY_VALUE_TYPE.get(0);
    }
    return dt;
  }
  
  public static int convertTypeToSQLType(int type)
  {
    return getDataType(type).sqlType;
  }
  
  private static int convertSQLTypeToValueType(int sqlType, String sqlTypeName)
  {
    switch (sqlType)
    {
    case 1111: 
    case 2000: 
      if (sqlTypeName.equalsIgnoreCase("geometry")) {
        return 22;
      }
      break;
    }
    return convertSQLTypeToValueType(sqlType);
  }
  
  public static int getValueTypeFromResultSet(ResultSetMetaData meta, int columnIndex)
    throws SQLException
  {
    return convertSQLTypeToValueType(meta.getColumnType(columnIndex), meta.getColumnTypeName(columnIndex));
  }
  
  public static int convertSQLTypeToValueType(int sqlType)
  {
    switch (sqlType)
    {
    case -15: 
    case 1: 
      return 21;
    case -16: 
    case -9: 
    case -1: 
    case 12: 
      return 13;
    case 2: 
    case 3: 
      return 6;
    case -7: 
    case 16: 
      return 1;
    case 4: 
      return 4;
    case 5: 
      return 3;
    case -6: 
      return 2;
    case -5: 
      return 5;
    case 7: 
      return 8;
    case 6: 
    case 8: 
      return 7;
    case -4: 
    case -3: 
    case -2: 
      return 12;
    case 1111: 
    case 2000: 
      return 19;
    case 91: 
      return 10;
    case 92: 
      return 9;
    case 93: 
      return 11;
    case 2004: 
      return 15;
    case 2005: 
    case 2011: 
      return 16;
    case 0: 
      return 0;
    case 2003: 
      return 17;
    case -10: 
      return 18;
    }
    throw DbException.get(50004, "" + sqlType);
  }
  
  public static int getTypeFromClass(Class<?> x)
  {
    if ((x == null) || (Void.TYPE == x)) {
      return 0;
    }
    if (x.isPrimitive()) {
      x = Utils.getNonPrimitiveClass(x);
    }
    if (String.class == x) {
      return 13;
    }
    if (Integer.class == x) {
      return 4;
    }
    if (Long.class == x) {
      return 5;
    }
    if (Boolean.class == x) {
      return 1;
    }
    if (Double.class == x) {
      return 7;
    }
    if (Byte.class == x) {
      return 2;
    }
    if (Short.class == x) {
      return 3;
    }
    if (Character.class == x) {
      throw DbException.get(22018, "char (not supported)");
    }
    if (Float.class == x) {
      return 8;
    }
    if (byte[].class == x) {
      return 12;
    }
    if (UUID.class == x) {
      return 20;
    }
    if (Void.class == x) {
      return 0;
    }
    if (BigDecimal.class.isAssignableFrom(x)) {
      return 6;
    }
    if (ResultSet.class.isAssignableFrom(x)) {
      return 18;
    }
    if (Value.ValueBlob.class.isAssignableFrom(x)) {
      return 15;
    }
    if (Value.ValueClob.class.isAssignableFrom(x)) {
      return 16;
    }
    if (java.sql.Date.class.isAssignableFrom(x)) {
      return 10;
    }
    if (Time.class.isAssignableFrom(x)) {
      return 9;
    }
    if (Timestamp.class.isAssignableFrom(x)) {
      return 11;
    }
    if (java.util.Date.class.isAssignableFrom(x)) {
      return 11;
    }
    if (Reader.class.isAssignableFrom(x)) {
      return 16;
    }
    if (Clob.class.isAssignableFrom(x)) {
      return 16;
    }
    if (InputStream.class.isAssignableFrom(x)) {
      return 15;
    }
    if (Blob.class.isAssignableFrom(x)) {
      return 15;
    }
    if (Object[].class.isAssignableFrom(x)) {
      return 17;
    }
    if (isGeometryClass(x)) {
      return 22;
    }
    return 19;
  }
  
  public static boolean isGeometryClass(Class<?> x)
  {
    if ((x == null) || (GEOMETRY_CLASS == null)) {
      return false;
    }
    return GEOMETRY_CLASS.isAssignableFrom(x);
  }
  
  public static boolean isGeometry(Object x)
  {
    if (x == null) {
      return false;
    }
    return isGeometryClass(x.getClass());
  }
  
  public static DataType getTypeByName(String s)
  {
    return (DataType)TYPES_BY_NAME.get(s);
  }
  
  public static boolean isLargeObject(int type)
  {
    if ((type == 15) || (type == 16)) {
      return true;
    }
    return false;
  }
  
  public static boolean isStringType(int type)
  {
    if ((type == 13) || (type == 21) || (type == 14)) {
      return true;
    }
    return false;
  }
  
  public static boolean supportsAdd(int type)
  {
    switch (type)
    {
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
      return true;
    }
    return false;
  }
  
  public static int getAddProofType(int type)
  {
    switch (type)
    {
    case 2: 
      return 5;
    case 8: 
      return 7;
    case 4: 
      return 5;
    case 5: 
      return 6;
    case 3: 
      return 5;
    }
    return type;
  }
  
  public static Object getDefaultForPrimitiveType(Class<?> clazz)
  {
    if (clazz == Boolean.TYPE) {
      return Boolean.FALSE;
    }
    if (clazz == Byte.TYPE) {
      return Byte.valueOf((byte)0);
    }
    if (clazz == Character.TYPE) {
      return Character.valueOf('\000');
    }
    if (clazz == Short.TYPE) {
      return Short.valueOf((short)0);
    }
    if (clazz == Integer.TYPE) {
      return Integer.valueOf(0);
    }
    if (clazz == Long.TYPE) {
      return Long.valueOf(0L);
    }
    if (clazz == Float.TYPE) {
      return Float.valueOf(0.0F);
    }
    if (clazz == Double.TYPE) {
      return Double.valueOf(0.0D);
    }
    throw DbException.throwInternalError("primitive=" + clazz.toString());
  }
  
  public static Object convertTo(JdbcConnection conn, Value v, Class<?> paramClass)
  {
    if (paramClass == Blob.class) {
      return new JdbcBlob(conn, v, 0);
    }
    if (paramClass == Clob.class) {
      return new JdbcClob(conn, v, 0);
    }
    if (v.getType() == 19)
    {
      Object o = SysProperties.serializeJavaObject ? JdbcUtils.deserialize(v.getBytes(), conn.getSession().getDataHandler()) : v.getObject();
      if (paramClass.isAssignableFrom(o.getClass())) {
        return o;
      }
    }
    throw DbException.getUnsupportedException("converting to class " + paramClass.getName());
  }
}
