package org.h2.value;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.DataHandler;
import org.h2.tools.SimpleResultSet;
import org.h2.util.DateTimeUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public abstract class Value
{
  public static final int UNKNOWN = -1;
  public static final int NULL = 0;
  public static final int BOOLEAN = 1;
  public static final int BYTE = 2;
  public static final int SHORT = 3;
  public static final int INT = 4;
  public static final int LONG = 5;
  public static final int DECIMAL = 6;
  public static final int DOUBLE = 7;
  public static final int FLOAT = 8;
  public static final int TIME = 9;
  public static final int DATE = 10;
  public static final int TIMESTAMP = 11;
  public static final int BYTES = 12;
  public static final int STRING = 13;
  public static final int STRING_IGNORECASE = 14;
  public static final int BLOB = 15;
  public static final int CLOB = 16;
  public static final int ARRAY = 17;
  public static final int RESULT_SET = 18;
  public static final int JAVA_OBJECT = 19;
  public static final int UUID = 20;
  public static final int STRING_FIXED = 21;
  public static final int GEOMETRY = 22;
  public static final int TYPE_COUNT = 23;
  private static SoftReference<Value[]> softCache = new SoftReference(null);
  private static final BigDecimal MAX_LONG_DECIMAL = BigDecimal.valueOf(Long.MAX_VALUE);
  private static final BigDecimal MIN_LONG_DECIMAL = BigDecimal.valueOf(Long.MIN_VALUE);
  
  public abstract String getSQL();
  
  public abstract int getType();
  
  public abstract long getPrecision();
  
  public abstract int getDisplaySize();
  
  public int getMemory()
  {
    return DataType.getDataType(getType()).memory;
  }
  
  public abstract String getString();
  
  public abstract Object getObject();
  
  public abstract void set(PreparedStatement paramPreparedStatement, int paramInt)
    throws SQLException;
  
  protected abstract int compareSecure(Value paramValue, CompareMode paramCompareMode);
  
  public abstract int hashCode();
  
  public abstract boolean equals(Object paramObject);
  
  static int getOrder(int type)
  {
    switch (type)
    {
    case -1: 
      return 1;
    case 0: 
      return 2;
    case 13: 
      return 10;
    case 16: 
      return 11;
    case 21: 
      return 12;
    case 14: 
      return 13;
    case 1: 
      return 20;
    case 2: 
      return 21;
    case 3: 
      return 22;
    case 4: 
      return 23;
    case 5: 
      return 24;
    case 6: 
      return 25;
    case 8: 
      return 26;
    case 7: 
      return 27;
    case 9: 
      return 30;
    case 10: 
      return 31;
    case 11: 
      return 32;
    case 12: 
      return 40;
    case 15: 
      return 41;
    case 20: 
      return 42;
    case 19: 
      return 43;
    case 22: 
      return 44;
    case 17: 
      return 50;
    case 18: 
      return 51;
    }
    throw DbException.throwInternalError("type:" + type);
  }
  
  public static int getHigherOrder(int t1, int t2)
  {
    if ((t1 == -1) || (t2 == -1))
    {
      if (t1 == t2) {
        throw DbException.get(50004, "?, ?");
      }
      if (t1 == 0) {
        throw DbException.get(50004, "NULL, ?");
      }
      if (t2 == 0) {
        throw DbException.get(50004, "?, NULL");
      }
    }
    if (t1 == t2) {
      return t1;
    }
    int o1 = getOrder(t1);
    int o2 = getOrder(t2);
    return o1 > o2 ? t1 : t2;
  }
  
  static Value cache(Value v)
  {
    if (SysProperties.OBJECT_CACHE)
    {
      int hash = v.hashCode();
      if (softCache == null) {
        softCache = new SoftReference(null);
      }
      Value[] cache = (Value[])softCache.get();
      if (cache == null)
      {
        cache = new Value[SysProperties.OBJECT_CACHE_SIZE];
        softCache = new SoftReference(cache);
      }
      int index = hash & SysProperties.OBJECT_CACHE_SIZE - 1;
      Value cached = cache[index];
      if ((cached != null) && 
        (cached.getType() == v.getType()) && (v.equals(cached))) {
        return cached;
      }
      cache[index] = v;
    }
    return v;
  }
  
  public static void clearCache()
  {
    softCache = null;
  }
  
  public Boolean getBoolean()
  {
    return ((ValueBoolean)convertTo(1)).getBoolean();
  }
  
  public Date getDate()
  {
    return ((ValueDate)convertTo(10)).getDate();
  }
  
  public Time getTime()
  {
    return ((ValueTime)convertTo(9)).getTime();
  }
  
  public Timestamp getTimestamp()
  {
    return ((ValueTimestamp)convertTo(11)).getTimestamp();
  }
  
  public byte[] getBytes()
  {
    return ((ValueBytes)convertTo(12)).getBytes();
  }
  
  public byte[] getBytesNoCopy()
  {
    return ((ValueBytes)convertTo(12)).getBytesNoCopy();
  }
  
  public byte getByte()
  {
    return ((ValueByte)convertTo(2)).getByte();
  }
  
  public short getShort()
  {
    return ((ValueShort)convertTo(3)).getShort();
  }
  
  public BigDecimal getBigDecimal()
  {
    return ((ValueDecimal)convertTo(6)).getBigDecimal();
  }
  
  public double getDouble()
  {
    return ((ValueDouble)convertTo(7)).getDouble();
  }
  
  public float getFloat()
  {
    return ((ValueFloat)convertTo(8)).getFloat();
  }
  
  public int getInt()
  {
    return ((ValueInt)convertTo(4)).getInt();
  }
  
  public long getLong()
  {
    return ((ValueLong)convertTo(5)).getLong();
  }
  
  public InputStream getInputStream()
  {
    return new ByteArrayInputStream(getBytesNoCopy());
  }
  
  public Reader getReader()
  {
    return new StringReader(getString());
  }
  
  public Value add(Value v)
  {
    throw throwUnsupportedExceptionForType("+");
  }
  
  public int getSignum()
  {
    throw throwUnsupportedExceptionForType("SIGNUM");
  }
  
  public Value negate()
  {
    throw throwUnsupportedExceptionForType("NEG");
  }
  
  public Value subtract(Value v)
  {
    throw throwUnsupportedExceptionForType("-");
  }
  
  public Value divide(Value v)
  {
    throw throwUnsupportedExceptionForType("/");
  }
  
  public Value multiply(Value v)
  {
    throw throwUnsupportedExceptionForType("*");
  }
  
  public Value modulus(Value v)
  {
    throw throwUnsupportedExceptionForType("%");
  }
  
  public Value convertTo(int targetType)
  {
    if (getType() == targetType) {
      return this;
    }
    try
    {
      switch (targetType)
      {
      case 1: 
        switch (getType())
        {
        case 2: 
        case 3: 
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
          return ValueBoolean.get(getSignum() != 0);
        case 9: 
        case 10: 
        case 11: 
        case 12: 
        case 19: 
        case 20: 
          throw DbException.get(22018, getString());
        }
        break;
      case 2: 
        switch (getType())
        {
        case 1: 
          return ValueByte.get((byte)(getBoolean().booleanValue() ? 1 : 0));
        case 3: 
          return ValueByte.get(convertToByte(getShort()));
        case 4: 
          return ValueByte.get(convertToByte(getInt()));
        case 5: 
          return ValueByte.get(convertToByte(getLong()));
        case 6: 
          return ValueByte.get(convertToByte(convertToLong(getBigDecimal())));
        case 7: 
          return ValueByte.get(convertToByte(convertToLong(getDouble())));
        case 8: 
          return ValueByte.get(convertToByte(convertToLong(getFloat())));
        case 12: 
          return ValueByte.get((byte)Integer.parseInt(getString(), 16));
        }
        break;
      case 3: 
        switch (getType())
        {
        case 1: 
          return ValueShort.get((short)(getBoolean().booleanValue() ? 1 : 0));
        case 2: 
          return ValueShort.get((short)getByte());
        case 4: 
          return ValueShort.get(convertToShort(getInt()));
        case 5: 
          return ValueShort.get(convertToShort(getLong()));
        case 6: 
          return ValueShort.get(convertToShort(convertToLong(getBigDecimal())));
        case 7: 
          return ValueShort.get(convertToShort(convertToLong(getDouble())));
        case 8: 
          return ValueShort.get(convertToShort(convertToLong(getFloat())));
        case 12: 
          return ValueShort.get((short)Integer.parseInt(getString(), 16));
        }
        break;
      case 4: 
        switch (getType())
        {
        case 1: 
          return ValueInt.get(getBoolean().booleanValue() ? 1 : 0);
        case 2: 
          return ValueInt.get(getByte());
        case 3: 
          return ValueInt.get(getShort());
        case 5: 
          return ValueInt.get(convertToInt(getLong()));
        case 6: 
          return ValueInt.get(convertToInt(convertToLong(getBigDecimal())));
        case 7: 
          return ValueInt.get(convertToInt(convertToLong(getDouble())));
        case 8: 
          return ValueInt.get(convertToInt(convertToLong(getFloat())));
        case 12: 
          return ValueInt.get((int)Long.parseLong(getString(), 16));
        }
        break;
      case 5: 
        switch (getType())
        {
        case 1: 
          return ValueLong.get(getBoolean().booleanValue() ? 1L : 0L);
        case 2: 
          return ValueLong.get(getByte());
        case 3: 
          return ValueLong.get(getShort());
        case 4: 
          return ValueLong.get(getInt());
        case 6: 
          return ValueLong.get(convertToLong(getBigDecimal()));
        case 7: 
          return ValueLong.get(convertToLong(getDouble()));
        case 8: 
          return ValueLong.get(convertToLong(getFloat()));
        case 12: 
          byte[] d = getBytes();
          if (d.length == 8) {
            return ValueLong.get(Utils.readLong(d, 0));
          }
          return ValueLong.get(Long.parseLong(getString(), 16));
        }
        break;
      case 6: 
        switch (getType())
        {
        case 1: 
          return ValueDecimal.get(BigDecimal.valueOf(getBoolean().booleanValue() ? 1L : 0L));
        case 2: 
          return ValueDecimal.get(BigDecimal.valueOf(getByte()));
        case 3: 
          return ValueDecimal.get(BigDecimal.valueOf(getShort()));
        case 4: 
          return ValueDecimal.get(BigDecimal.valueOf(getInt()));
        case 5: 
          return ValueDecimal.get(BigDecimal.valueOf(getLong()));
        case 7: 
          double d = getDouble();
          if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
            throw DbException.get(22018, "" + d);
          }
          return ValueDecimal.get(BigDecimal.valueOf(d));
        case 8: 
          float f = getFloat();
          if ((Float.isInfinite(f)) || (Float.isNaN(f))) {
            throw DbException.get(22018, "" + f);
          }
          return ValueDecimal.get(new BigDecimal(Float.toString(f)));
        }
        break;
      case 7: 
        switch (getType())
        {
        case 1: 
          return ValueDouble.get(getBoolean().booleanValue() ? 1.0D : 0.0D);
        case 2: 
          return ValueDouble.get(getByte());
        case 3: 
          return ValueDouble.get(getShort());
        case 4: 
          return ValueDouble.get(getInt());
        case 5: 
          return ValueDouble.get(getLong());
        case 6: 
          return ValueDouble.get(getBigDecimal().doubleValue());
        case 8: 
          return ValueDouble.get(getFloat());
        }
        break;
      case 8: 
        switch (getType())
        {
        case 1: 
          return ValueFloat.get(getBoolean().booleanValue() ? 1.0F : 0.0F);
        case 2: 
          return ValueFloat.get(getByte());
        case 3: 
          return ValueFloat.get(getShort());
        case 4: 
          return ValueFloat.get(getInt());
        case 5: 
          return ValueFloat.get((float)getLong());
        case 6: 
          return ValueFloat.get(getBigDecimal().floatValue());
        case 7: 
          return ValueFloat.get((float)getDouble());
        }
        break;
      case 10: 
        switch (getType())
        {
        case 9: 
          return ValueDate.fromDateValue(DateTimeUtils.dateValue(1970L, 1, 1));
        case 11: 
          return ValueDate.fromDateValue(((ValueTimestamp)this).getDateValue());
        }
        break;
      case 9: 
        switch (getType())
        {
        case 10: 
          return ValueTime.fromNanos(0L);
        case 11: 
          return ValueTime.fromNanos(((ValueTimestamp)this).getTimeNanos());
        }
        break;
      case 11: 
        switch (getType())
        {
        case 9: 
          return DateTimeUtils.normalizeTimestamp(0L, ((ValueTime)this).getNanos());
        case 10: 
          return ValueTimestamp.fromDateValueAndNanos(((ValueDate)this).getDateValue(), 0L);
        }
        break;
      case 12: 
        switch (getType())
        {
        case 15: 
        case 19: 
          return ValueBytes.getNoCopy(getBytesNoCopy());
        case 20: 
        case 22: 
          return ValueBytes.getNoCopy(getBytes());
        case 2: 
          return ValueBytes.getNoCopy(new byte[] { getByte() });
        case 3: 
          int x = getShort();
          return ValueBytes.getNoCopy(new byte[] { (byte)(x >> 8), (byte)x });
        case 4: 
          int x = getInt();
          return ValueBytes.getNoCopy(new byte[] { (byte)(x >> 24), (byte)(x >> 16), (byte)(x >> 8), (byte)x });
        case 5: 
          long x = getLong();
          return ValueBytes.getNoCopy(new byte[] { (byte)(int)(x >> 56), (byte)(int)(x >> 48), (byte)(int)(x >> 40), (byte)(int)(x >> 32), (byte)(int)(x >> 24), (byte)(int)(x >> 16), (byte)(int)(x >> 8), (byte)(int)x });
        }
        break;
      case 19: 
        switch (getType())
        {
        case 12: 
        case 15: 
          return ValueJavaObject.getNoCopy(null, getBytesNoCopy(), getDataHandler());
        }
        break;
      case 15: 
        switch (getType())
        {
        case 12: 
          return ValueLobDb.createSmallLob(15, getBytesNoCopy());
        }
        break;
      case 20: 
        switch (getType())
        {
        case 12: 
          return ValueUuid.get(getBytesNoCopy());
        }
      case 22: 
        switch (getType())
        {
        case 12: 
          return ValueGeometry.get(getBytesNoCopy());
        case 19: 
          Object object = JdbcUtils.deserialize(getBytesNoCopy(), getDataHandler());
          if (!DataType.isGeometry(object)) {}
          break;
        }
        break;
      }
      String s = getString();
      switch (targetType)
      {
      case 0: 
        return ValueNull.INSTANCE;
      case 1: 
        if ((s.equalsIgnoreCase("true")) || (s.equalsIgnoreCase("t")) || (s.equalsIgnoreCase("yes")) || (s.equalsIgnoreCase("y"))) {
          return ValueBoolean.get(true);
        }
        if ((s.equalsIgnoreCase("false")) || (s.equalsIgnoreCase("f")) || (s.equalsIgnoreCase("no")) || (s.equalsIgnoreCase("n"))) {
          return ValueBoolean.get(false);
        }
        return ValueBoolean.get(new BigDecimal(s).signum() != 0);
      case 2: 
        return ValueByte.get(Byte.parseByte(s.trim()));
      case 3: 
        return ValueShort.get(Short.parseShort(s.trim()));
      case 4: 
        return ValueInt.get(Integer.parseInt(s.trim()));
      case 5: 
        return ValueLong.get(Long.parseLong(s.trim()));
      case 6: 
        return ValueDecimal.get(new BigDecimal(s.trim()));
      case 9: 
        return ValueTime.parse(s.trim());
      case 10: 
        return ValueDate.parse(s.trim());
      case 11: 
        return ValueTimestamp.parse(s.trim());
      case 12: 
        return ValueBytes.getNoCopy(StringUtils.convertHexToBytes(s.trim()));
      case 19: 
        return ValueJavaObject.getNoCopy(null, StringUtils.convertHexToBytes(s.trim()), getDataHandler());
      case 13: 
        return ValueString.get(s);
      case 14: 
        return ValueStringIgnoreCase.get(s);
      case 21: 
        return ValueStringFixed.get(s);
      case 7: 
        return ValueDouble.get(Double.parseDouble(s.trim()));
      case 8: 
        return ValueFloat.get(Float.parseFloat(s.trim()));
      case 16: 
        return ValueLobDb.createSmallLob(16, s.getBytes(Constants.UTF8));
      case 15: 
        return ValueLobDb.createSmallLob(15, StringUtils.convertHexToBytes(s.trim()));
      case 17: 
        return ValueArray.get(new Value[] { ValueString.get(s) });
      case 18: 
        SimpleResultSet rs = new SimpleResultSet();
        rs.setAutoClose(false);
        rs.addColumn("X", 12, s.length(), 0);
        rs.addRow(new Object[] { s });
        return ValueResultSet.get(rs);
      case 20: 
        return ValueUuid.get(s);
      }
      throw DbException.throwInternalError("type=" + targetType);
    }
    catch (NumberFormatException e)
    {
      throw DbException.get(22018, e, new String[] { getString() });
    }
  }
  
  public final int compareTypeSave(Value v, CompareMode mode)
  {
    if (this == v) {
      return 0;
    }
    if (this == ValueNull.INSTANCE) {
      return -1;
    }
    if (v == ValueNull.INSTANCE) {
      return 1;
    }
    return compareSecure(v, mode);
  }
  
  public final int compareTo(Value v, CompareMode mode)
  {
    if (this == v) {
      return 0;
    }
    if (this == ValueNull.INSTANCE) {
      return v == ValueNull.INSTANCE ? 0 : -1;
    }
    if (v == ValueNull.INSTANCE) {
      return 1;
    }
    if (getType() == v.getType()) {
      return compareSecure(v, mode);
    }
    int t2 = getHigherOrder(getType(), v.getType());
    return convertTo(t2).compareSecure(v.convertTo(t2), mode);
  }
  
  public int getScale()
  {
    return 0;
  }
  
  public Value convertScale(boolean onlyToSmallerScale, int targetScale)
  {
    return this;
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    return this;
  }
  
  private static byte convertToByte(long x)
  {
    if ((x > 127L) || (x < -128L)) {
      throw DbException.get(22003, Long.toString(x));
    }
    return (byte)(int)x;
  }
  
  private static short convertToShort(long x)
  {
    if ((x > 32767L) || (x < -32768L)) {
      throw DbException.get(22003, Long.toString(x));
    }
    return (short)(int)x;
  }
  
  private static int convertToInt(long x)
  {
    if ((x > 2147483647L) || (x < -2147483648L)) {
      throw DbException.get(22003, Long.toString(x));
    }
    return (int)x;
  }
  
  private static long convertToLong(double x)
  {
    if ((x > 9.223372036854776E18D) || (x < -9.223372036854776E18D)) {
      throw DbException.get(22003, Double.toString(x));
    }
    return Math.round(x);
  }
  
  private static long convertToLong(BigDecimal x)
  {
    if ((x.compareTo(MAX_LONG_DECIMAL) > 0) || (x.compareTo(MIN_LONG_DECIMAL) < 0)) {
      throw DbException.get(22003, x.toString());
    }
    return x.setScale(0, 4).longValue();
  }
  
  public Value link(DataHandler handler, int tableId)
  {
    return this;
  }
  
  public boolean isLinked()
  {
    return false;
  }
  
  public void unlink(DataHandler handler) {}
  
  public void close() {}
  
  public boolean checkPrecision(long precision)
  {
    return getPrecision() <= precision;
  }
  
  public String getTraceSQL()
  {
    return getSQL();
  }
  
  public String toString()
  {
    return getTraceSQL();
  }
  
  protected DbException throwUnsupportedExceptionForType(String op)
  {
    throw DbException.getUnsupportedException(DataType.getDataType(getType()).name + " " + op);
  }
  
  public int getTableId()
  {
    return 0;
  }
  
  public byte[] getSmall()
  {
    return null;
  }
  
  public Value copyToTemp()
  {
    return this;
  }
  
  public Value copyToResult()
  {
    return this;
  }
  
  public ResultSet getResultSet()
  {
    SimpleResultSet rs = new SimpleResultSet();
    rs.setAutoClose(false);
    rs.addColumn("X", DataType.convertTypeToSQLType(getType()), MathUtils.convertLongToInt(getPrecision()), getScale());
    
    rs.addRow(new Object[] { getObject() });
    return rs;
  }
  
  protected DataHandler getDataHandler()
  {
    return null;
  }
  
  public static abstract interface ValueBlob {}
  
  public static abstract interface ValueClob {}
}
