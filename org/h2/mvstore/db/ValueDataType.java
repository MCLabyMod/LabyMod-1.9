package org.h2.mvstore.db;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.WriteBuffer;
import org.h2.mvstore.rtree.SpatialDataType;
import org.h2.mvstore.rtree.SpatialKey;
import org.h2.result.SortOrder;
import org.h2.store.DataHandler;
import org.h2.tools.SimpleResultSet;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueByte;
import org.h2.value.ValueBytes;
import org.h2.value.ValueDate;
import org.h2.value.ValueDecimal;
import org.h2.value.ValueDouble;
import org.h2.value.ValueFloat;
import org.h2.value.ValueGeometry;
import org.h2.value.ValueInt;
import org.h2.value.ValueJavaObject;
import org.h2.value.ValueLobDb;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueResultSet;
import org.h2.value.ValueShort;
import org.h2.value.ValueString;
import org.h2.value.ValueStringFixed;
import org.h2.value.ValueStringIgnoreCase;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;
import org.h2.value.ValueUuid;

public class ValueDataType
  implements org.h2.mvstore.type.DataType
{
  private static final int INT_0_15 = 32;
  private static final int LONG_0_7 = 48;
  private static final int DECIMAL_0_1 = 56;
  private static final int DECIMAL_SMALL_0 = 58;
  private static final int DECIMAL_SMALL = 59;
  private static final int DOUBLE_0_1 = 60;
  private static final int FLOAT_0_1 = 62;
  private static final int BOOLEAN_FALSE = 64;
  private static final int BOOLEAN_TRUE = 65;
  private static final int INT_NEG = 66;
  private static final int LONG_NEG = 67;
  private static final int STRING_0_31 = 68;
  private static final int BYTES_0_31 = 100;
  private static final int SPATIAL_KEY_2D = 132;
  final DataHandler handler;
  final CompareMode compareMode;
  final int[] sortTypes;
  SpatialDataType spatialType;
  
  public ValueDataType(CompareMode compareMode, DataHandler handler, int[] sortTypes)
  {
    this.compareMode = compareMode;
    this.handler = handler;
    this.sortTypes = sortTypes;
  }
  
  private SpatialDataType getSpatialDataType()
  {
    if (this.spatialType == null) {
      this.spatialType = new SpatialDataType(2);
    }
    return this.spatialType;
  }
  
  public int compare(Object a, Object b)
  {
    if (a == b) {
      return 0;
    }
    if (((a instanceof ValueArray)) && ((b instanceof ValueArray)))
    {
      Value[] ax = ((ValueArray)a).getList();
      Value[] bx = ((ValueArray)b).getList();
      int al = ax.length;
      int bl = bx.length;
      int len = Math.min(al, bl);
      for (int i = 0; i < len; i++)
      {
        int sortType = this.sortTypes[i];
        int comp = compareValues(ax[i], bx[i], sortType);
        if (comp != 0) {
          return comp;
        }
      }
      if (len < al) {
        return -1;
      }
      if (len < bl) {
        return 1;
      }
      return 0;
    }
    return compareValues((Value)a, (Value)b, 0);
  }
  
  private int compareValues(Value a, Value b, int sortType)
  {
    if (a == b) {
      return 0;
    }
    if (a == null) {
      return -1;
    }
    if (b == null) {
      return 1;
    }
    boolean aNull = a == ValueNull.INSTANCE;
    boolean bNull = b == ValueNull.INSTANCE;
    if ((aNull) || (bNull)) {
      return SortOrder.compareNull(aNull, sortType);
    }
    int comp = compareTypeSave(a, b);
    if ((sortType & 0x1) != 0) {
      comp = -comp;
    }
    return comp;
  }
  
  private int compareTypeSave(Value a, Value b)
  {
    if (a == b) {
      return 0;
    }
    return a.compareTypeSave(b, this.compareMode);
  }
  
  public int getMemory(Object obj)
  {
    if ((obj instanceof SpatialKey)) {
      return getSpatialDataType().getMemory(obj);
    }
    return getMemory((Value)obj);
  }
  
  private static int getMemory(Value v)
  {
    return v == null ? 0 : v.getMemory();
  }
  
  public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      obj[i] = read(buff);
    }
  }
  
  public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      write(buff, obj[i]);
    }
  }
  
  public Object read(ByteBuffer buff)
  {
    return readValue(buff);
  }
  
  public void write(WriteBuffer buff, Object obj)
  {
    if ((obj instanceof SpatialKey))
    {
      buff.put((byte)-124);
      getSpatialDataType().write(buff, obj);
      return;
    }
    Value x = (Value)obj;
    writeValue(buff, x);
  }
  
  private void writeValue(WriteBuffer buff, Value v)
  {
    if (v == ValueNull.INSTANCE)
    {
      buff.put((byte)0);
      return;
    }
    int type = v.getType();
    switch (type)
    {
    case 1: 
      buff.put((byte)(v.getBoolean().booleanValue() ? 65 : 64));
      
      break;
    case 2: 
      buff.put((byte)type).put(v.getByte());
      break;
    case 3: 
      buff.put((byte)type).putShort(v.getShort());
      break;
    case 4: 
      int x = v.getInt();
      if (x < 0) {
        buff.put((byte)66).putVarInt(-x);
      } else if (x < 16) {
        buff.put((byte)(32 + x));
      } else {
        buff.put((byte)type).putVarInt(x);
      }
      break;
    case 5: 
      long x = v.getLong();
      if (x < 0L) {
        buff.put((byte)67).putVarLong(-x);
      } else if (x < 8L) {
        buff.put((byte)(int)(48L + x));
      } else {
        buff.put((byte)type).putVarLong(x);
      }
      break;
    case 6: 
      BigDecimal x = v.getBigDecimal();
      if (BigDecimal.ZERO.equals(x))
      {
        buff.put((byte)56);
      }
      else if (BigDecimal.ONE.equals(x))
      {
        buff.put((byte)57);
      }
      else
      {
        int scale = x.scale();
        BigInteger b = x.unscaledValue();
        int bits = b.bitLength();
        if (bits <= 63)
        {
          if (scale == 0) {
            buff.put((byte)58).putVarLong(b.longValue());
          } else {
            buff.put((byte)59).putVarInt(scale).putVarLong(b.longValue());
          }
        }
        else
        {
          byte[] bytes = b.toByteArray();
          buff.put((byte)type).putVarInt(scale).putVarInt(bytes.length).put(bytes);
        }
      }
      break;
    case 9: 
      ValueTime t = (ValueTime)v;
      long nanos = t.getNanos();
      long millis = nanos / 1000000L;
      nanos -= millis * 1000000L;
      buff.put((byte)type).putVarLong(millis).putVarLong(nanos);
      
      break;
    case 10: 
      long x = ((ValueDate)v).getDateValue();
      buff.put((byte)type).putVarLong(x);
      break;
    case 11: 
      ValueTimestamp ts = (ValueTimestamp)v;
      long dateValue = ts.getDateValue();
      long nanos = ts.getTimeNanos();
      long millis = nanos / 1000000L;
      nanos -= millis * 1000000L;
      buff.put((byte)type).putVarLong(dateValue).putVarLong(millis).putVarLong(nanos);
      
      break;
    case 19: 
      byte[] b = v.getBytesNoCopy();
      buff.put((byte)type).putVarInt(b.length).put(b);
      
      break;
    case 12: 
      byte[] b = v.getBytesNoCopy();
      int len = b.length;
      if (len < 32) {
        buff.put((byte)(100 + len)).put(b);
      } else {
        buff.put((byte)type).putVarInt(b.length).put(b);
      }
      break;
    case 20: 
      ValueUuid uuid = (ValueUuid)v;
      buff.put((byte)type).putLong(uuid.getHigh()).putLong(uuid.getLow());
      
      break;
    case 13: 
      String s = v.getString();
      int len = s.length();
      if (len < 32)
      {
        buff.put((byte)(68 + len)).putStringData(s, len);
      }
      else
      {
        buff.put((byte)type);
        writeString(buff, s);
      }
      break;
    case 14: 
    case 21: 
      buff.put((byte)type);
      writeString(buff, v.getString());
      break;
    case 7: 
      double x = v.getDouble();
      if (x == 1.0D)
      {
        buff.put((byte)61);
      }
      else
      {
        long d = Double.doubleToLongBits(x);
        if (d == ValueDouble.ZERO_BITS) {
          buff.put((byte)60);
        } else {
          buff.put((byte)type).putVarLong(Long.reverse(d));
        }
      }
      break;
    case 8: 
      float x = v.getFloat();
      if (x == 1.0F)
      {
        buff.put((byte)63);
      }
      else
      {
        int f = Float.floatToIntBits(x);
        if (f == ValueFloat.ZERO_BITS) {
          buff.put((byte)62);
        } else {
          buff.put((byte)type).putVarInt(Integer.reverse(f));
        }
      }
      break;
    case 15: 
    case 16: 
      buff.put((byte)type);
      ValueLobDb lob = (ValueLobDb)v;
      byte[] small = lob.getSmall();
      if (small == null) {
        buff.putVarInt(-3).putVarInt(lob.getTableId()).putVarLong(lob.getLobId()).putVarLong(lob.getPrecision());
      } else {
        buff.putVarInt(small.length).put(small);
      }
      break;
    case 17: 
      Value[] list = ((ValueArray)v).getList();
      buff.put((byte)type).putVarInt(list.length);
      for (Value x : list) {
        writeValue(buff, x);
      }
      break;
    case 18: 
      buff.put((byte)type);
      try
      {
        ResultSet rs = ((ValueResultSet)v).getResultSet();
        rs.beforeFirst();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        buff.putVarInt(columnCount);
        for (int i = 0; i < columnCount; i++)
        {
          writeString(buff, meta.getColumnName(i + 1));
          buff.putVarInt(meta.getColumnType(i + 1)).putVarInt(meta.getPrecision(i + 1)).putVarInt(meta.getScale(i + 1));
        }
        while (rs.next())
        {
          buff.put((byte)1);
          for (int i = 0; i < columnCount; i++)
          {
            int t = org.h2.value.DataType.getValueTypeFromResultSet(meta, i + 1);
            
            Value val = org.h2.value.DataType.readValue(null, rs, i + 1, t);
            
            writeValue(buff, val);
          }
        }
        buff.put((byte)0);
        rs.beforeFirst();
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    case 22: 
      byte[] b = v.getBytes();
      int len = b.length;
      buff.put((byte)type).putVarInt(len).put(b);
      
      break;
    default: 
      DbException.throwInternalError("type=" + v.getType());
    }
  }
  
  private static void writeString(WriteBuffer buff, String s)
  {
    int len = s.length();
    buff.putVarInt(len).putStringData(s, len);
  }
  
  private Object readValue(ByteBuffer buff)
  {
    int type = buff.get() & 0xFF;
    switch (type)
    {
    case 0: 
      return ValueNull.INSTANCE;
    case 65: 
      return ValueBoolean.get(true);
    case 64: 
      return ValueBoolean.get(false);
    case 66: 
      return ValueInt.get(-readVarInt(buff));
    case 4: 
      return ValueInt.get(readVarInt(buff));
    case 67: 
      return ValueLong.get(-readVarLong(buff));
    case 5: 
      return ValueLong.get(readVarLong(buff));
    case 2: 
      return ValueByte.get(buff.get());
    case 3: 
      return ValueShort.get(buff.getShort());
    case 56: 
      return ValueDecimal.ZERO;
    case 57: 
      return ValueDecimal.ONE;
    case 58: 
      return ValueDecimal.get(BigDecimal.valueOf(readVarLong(buff)));
    case 59: 
      int scale = readVarInt(buff);
      return ValueDecimal.get(BigDecimal.valueOf(readVarLong(buff), scale));
    case 6: 
      int scale = readVarInt(buff);
      int len = readVarInt(buff);
      byte[] buff2 = DataUtils.newBytes(len);
      buff.get(buff2, 0, len);
      BigInteger b = new BigInteger(buff2);
      return ValueDecimal.get(new BigDecimal(b, scale));
    case 10: 
      return ValueDate.fromDateValue(readVarLong(buff));
    case 9: 
      long nanos = readVarLong(buff) * 1000000L + readVarLong(buff);
      return ValueTime.fromNanos(nanos);
    case 11: 
      long dateValue = readVarLong(buff);
      long nanos = readVarLong(buff) * 1000000L + readVarLong(buff);
      return ValueTimestamp.fromDateValueAndNanos(dateValue, nanos);
    case 12: 
      int len = readVarInt(buff);
      byte[] b = DataUtils.newBytes(len);
      buff.get(b, 0, len);
      return ValueBytes.getNoCopy(b);
    case 19: 
      int len = readVarInt(buff);
      byte[] b = DataUtils.newBytes(len);
      buff.get(b, 0, len);
      return ValueJavaObject.getNoCopy(null, b, this.handler);
    case 20: 
      return ValueUuid.get(buff.getLong(), buff.getLong());
    case 13: 
      return ValueString.get(readString(buff));
    case 14: 
      return ValueStringIgnoreCase.get(readString(buff));
    case 21: 
      return ValueStringFixed.get(readString(buff));
    case 62: 
      return ValueFloat.get(0.0F);
    case 63: 
      return ValueFloat.get(1.0F);
    case 60: 
      return ValueDouble.get(0.0D);
    case 61: 
      return ValueDouble.get(1.0D);
    case 7: 
      return ValueDouble.get(Double.longBitsToDouble(Long.reverse(readVarLong(buff))));
    case 8: 
      return ValueFloat.get(Float.intBitsToFloat(Integer.reverse(readVarInt(buff))));
    case 15: 
    case 16: 
      int smallLen = readVarInt(buff);
      if (smallLen >= 0)
      {
        byte[] small = DataUtils.newBytes(smallLen);
        buff.get(small, 0, smallLen);
        return ValueLobDb.createSmallLob(type, small);
      }
      if (smallLen == -3)
      {
        int tableId = readVarInt(buff);
        long lobId = readVarLong(buff);
        long precision = readVarLong(buff);
        ValueLobDb lob = ValueLobDb.create(type, this.handler, tableId, lobId, null, precision);
        
        return lob;
      }
      throw DbException.get(90030, "lob type: " + smallLen);
    case 17: 
      int len = readVarInt(buff);
      Value[] list = new Value[len];
      for (int i = 0; i < len; i++) {
        list[i] = ((Value)readValue(buff));
      }
      return ValueArray.get(list);
    case 18: 
      SimpleResultSet rs = new SimpleResultSet();
      rs.setAutoClose(false);
      int columns = readVarInt(buff);
      for (int i = 0; i < columns; i++) {
        rs.addColumn(readString(buff), readVarInt(buff), readVarInt(buff), readVarInt(buff));
      }
      while (buff.get() != 0)
      {
        Object[] o = new Object[columns];
        for (int i = 0; i < columns; i++) {
          o[i] = ((Value)readValue(buff)).getObject();
        }
        rs.addRow(o);
      }
      return ValueResultSet.get(rs);
    case 22: 
      int len = readVarInt(buff);
      byte[] b = DataUtils.newBytes(len);
      buff.get(b, 0, len);
      return ValueGeometry.get(b);
    case 132: 
      return getSpatialDataType().read(buff);
    }
    if ((type >= 32) && (type < 48)) {
      return ValueInt.get(type - 32);
    }
    if ((type >= 48) && (type < 56)) {
      return ValueLong.get(type - 48);
    }
    if ((type >= 100) && (type < 132))
    {
      int len = type - 100;
      byte[] b = DataUtils.newBytes(len);
      buff.get(b, 0, len);
      return ValueBytes.getNoCopy(b);
    }
    if ((type >= 68) && (type < 100)) {
      return ValueString.get(readString(buff, type - 68));
    }
    throw DbException.get(90030, "type: " + type);
  }
  
  private static int readVarInt(ByteBuffer buff)
  {
    return DataUtils.readVarInt(buff);
  }
  
  private static long readVarLong(ByteBuffer buff)
  {
    return DataUtils.readVarLong(buff);
  }
  
  private static String readString(ByteBuffer buff, int len)
  {
    return DataUtils.readString(buff, len);
  }
  
  private static String readString(ByteBuffer buff)
  {
    int len = readVarInt(buff);
    return DataUtils.readString(buff, len);
  }
  
  public int hashCode()
  {
    return this.compareMode.hashCode() ^ Arrays.hashCode(this.sortTypes);
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ValueDataType)) {
      return false;
    }
    ValueDataType v = (ValueDataType)obj;
    if (!this.compareMode.equals(v.compareMode)) {
      return false;
    }
    return Arrays.equals(this.sortTypes, v.sortTypes);
  }
}
