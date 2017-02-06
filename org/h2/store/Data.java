package org.h2.store;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.tools.SimpleResultSet;
import org.h2.util.DateTimeUtils;
import org.h2.util.MathUtils;
import org.h2.value.DataType;
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
import org.h2.value.ValueLob;
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

public class Data
{
  public static final int LENGTH_INT = 4;
  private static final int LENGTH_LONG = 8;
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
  private static final int LOCAL_TIME = 132;
  private static final int LOCAL_DATE = 133;
  private static final int LOCAL_TIMESTAMP = 134;
  private static final long MILLIS_PER_MINUTE = 60000L;
  private byte[] data;
  private int pos;
  private final DataHandler handler;
  
  private Data(DataHandler handler, byte[] data)
  {
    this.handler = handler;
    this.data = data;
  }
  
  public void setInt(int pos, int x)
  {
    byte[] buff = this.data;
    buff[pos] = ((byte)(x >> 24));
    buff[(pos + 1)] = ((byte)(x >> 16));
    buff[(pos + 2)] = ((byte)(x >> 8));
    buff[(pos + 3)] = ((byte)x);
  }
  
  public void writeInt(int x)
  {
    byte[] buff = this.data;
    buff[this.pos] = ((byte)(x >> 24));
    buff[(this.pos + 1)] = ((byte)(x >> 16));
    buff[(this.pos + 2)] = ((byte)(x >> 8));
    buff[(this.pos + 3)] = ((byte)x);
    this.pos += 4;
  }
  
  public int readInt()
  {
    byte[] buff = this.data;
    int x = (buff[this.pos] << 24) + ((buff[(this.pos + 1)] & 0xFF) << 16) + ((buff[(this.pos + 2)] & 0xFF) << 8) + (buff[(this.pos + 3)] & 0xFF);
    
    this.pos += 4;
    return x;
  }
  
  public static int getStringLen(String s)
  {
    int len = s.length();
    return getStringWithoutLengthLen(s, len) + getVarIntLen(len);
  }
  
  private static int getStringWithoutLengthLen(String s, int len)
  {
    int plus = 0;
    for (int i = 0; i < len; i++)
    {
      char c = s.charAt(i);
      if (c >= 'ࠀ') {
        plus += 2;
      } else if (c >= '') {
        plus++;
      }
    }
    return len + plus;
  }
  
  public String readString()
  {
    int len = readVarInt();
    return readString(len);
  }
  
  private String readString(int len)
  {
    byte[] buff = this.data;
    int p = this.pos;
    char[] chars = new char[len];
    for (int i = 0; i < len; i++)
    {
      int x = buff[(p++)] & 0xFF;
      if (x < 128) {
        chars[i] = ((char)x);
      } else if (x >= 224) {
        chars[i] = ((char)(((x & 0xF) << 12) + ((buff[(p++)] & 0x3F) << 6) + (buff[(p++)] & 0x3F)));
      } else {
        chars[i] = ((char)(((x & 0x1F) << 6) + (buff[(p++)] & 0x3F)));
      }
    }
    this.pos = p;
    return new String(chars);
  }
  
  public void writeString(String s)
  {
    int len = s.length();
    writeVarInt(len);
    writeStringWithoutLength(s, len);
  }
  
  private void writeStringWithoutLength(String s, int len)
  {
    int p = this.pos;
    byte[] buff = this.data;
    for (int i = 0; i < len; i++)
    {
      int c = s.charAt(i);
      if (c < 128)
      {
        buff[(p++)] = ((byte)c);
      }
      else if (c >= 2048)
      {
        buff[(p++)] = ((byte)(0xE0 | c >> 12));
        buff[(p++)] = ((byte)(c >> 6 & 0x3F));
        buff[(p++)] = ((byte)(c & 0x3F));
      }
      else
      {
        buff[(p++)] = ((byte)(0xC0 | c >> 6));
        buff[(p++)] = ((byte)(c & 0x3F));
      }
    }
    this.pos = p;
  }
  
  private void writeStringWithoutLength(char[] chars, int len)
  {
    int p = this.pos;
    byte[] buff = this.data;
    for (int i = 0; i < len; i++)
    {
      int c = chars[i];
      if (c < 128)
      {
        buff[(p++)] = ((byte)c);
      }
      else if (c >= 2048)
      {
        buff[(p++)] = ((byte)(0xE0 | c >> 12));
        buff[(p++)] = ((byte)(c >> 6 & 0x3F));
        buff[(p++)] = ((byte)(c & 0x3F));
      }
      else
      {
        buff[(p++)] = ((byte)(0xC0 | c >> 6));
        buff[(p++)] = ((byte)(c & 0x3F));
      }
    }
    this.pos = p;
  }
  
  public static Data create(DataHandler handler, int capacity)
  {
    return new Data(handler, new byte[capacity]);
  }
  
  public static Data create(DataHandler handler, byte[] buff)
  {
    return new Data(handler, buff);
  }
  
  public int length()
  {
    return this.pos;
  }
  
  public byte[] getBytes()
  {
    return this.data;
  }
  
  public void reset()
  {
    this.pos = 0;
  }
  
  public void write(byte[] buff, int off, int len)
  {
    System.arraycopy(buff, off, this.data, this.pos, len);
    this.pos += len;
  }
  
  public void read(byte[] buff, int off, int len)
  {
    System.arraycopy(this.data, this.pos, buff, off, len);
    this.pos += len;
  }
  
  public void writeByte(byte x)
  {
    this.data[(this.pos++)] = x;
  }
  
  public byte readByte()
  {
    return this.data[(this.pos++)];
  }
  
  public long readLong()
  {
    return (readInt() << 32) + (readInt() & 0xFFFFFFFF);
  }
  
  public void writeLong(long x)
  {
    writeInt((int)(x >>> 32));
    writeInt((int)x);
  }
  
  public void writeValue(Value v)
  {
    int start = this.pos;
    if (v == ValueNull.INSTANCE)
    {
      this.data[(this.pos++)] = 0;
      return;
    }
    int type = v.getType();
    switch (type)
    {
    case 1: 
      writeByte((byte)(v.getBoolean().booleanValue() ? 65 : 64));
      
      break;
    case 2: 
      writeByte((byte)type);
      writeByte(v.getByte());
      break;
    case 3: 
      writeByte((byte)type);
      writeShortInt(v.getShort());
      break;
    case 4: 
      int x = v.getInt();
      if (x < 0)
      {
        writeByte((byte)66);
        writeVarInt(-x);
      }
      else if (x < 16)
      {
        writeByte((byte)(32 + x));
      }
      else
      {
        writeByte((byte)type);
        writeVarInt(x);
      }
      break;
    case 5: 
      long x = v.getLong();
      if (x < 0L)
      {
        writeByte((byte)67);
        writeVarLong(-x);
      }
      else if (x < 8L)
      {
        writeByte((byte)(int)(48L + x));
      }
      else
      {
        writeByte((byte)type);
        writeVarLong(x);
      }
      break;
    case 6: 
      BigDecimal x = v.getBigDecimal();
      if (BigDecimal.ZERO.equals(x))
      {
        writeByte((byte)56);
      }
      else if (BigDecimal.ONE.equals(x))
      {
        writeByte((byte)57);
      }
      else
      {
        int scale = x.scale();
        BigInteger b = x.unscaledValue();
        int bits = b.bitLength();
        if (bits <= 63)
        {
          if (scale == 0)
          {
            writeByte((byte)58);
            writeVarLong(b.longValue());
          }
          else
          {
            writeByte((byte)59);
            writeVarInt(scale);
            writeVarLong(b.longValue());
          }
        }
        else
        {
          writeByte((byte)type);
          writeVarInt(scale);
          byte[] bytes = b.toByteArray();
          writeVarInt(bytes.length);
          write(bytes, 0, bytes.length);
        }
      }
      break;
    case 9: 
      if (SysProperties.STORE_LOCAL_TIME)
      {
        writeByte((byte)-124);
        ValueTime t = (ValueTime)v;
        long nanos = t.getNanos();
        long millis = nanos / 1000000L;
        nanos -= millis * 1000000L;
        writeVarLong(millis);
        writeVarLong(nanos);
      }
      else
      {
        writeByte((byte)type);
        writeVarLong(DateTimeUtils.getTimeLocalWithoutDst(v.getTime()));
      }
      break;
    case 10: 
      if (SysProperties.STORE_LOCAL_TIME)
      {
        writeByte((byte)-123);
        long x = ((ValueDate)v).getDateValue();
        writeVarLong(x);
      }
      else
      {
        writeByte((byte)type);
        long x = DateTimeUtils.getTimeLocalWithoutDst(v.getDate());
        writeVarLong(x / 60000L);
      }
      break;
    case 11: 
      if (SysProperties.STORE_LOCAL_TIME)
      {
        writeByte((byte)-122);
        ValueTimestamp ts = (ValueTimestamp)v;
        long dateValue = ts.getDateValue();
        writeVarLong(dateValue);
        long nanos = ts.getTimeNanos();
        long millis = nanos / 1000000L;
        nanos -= millis * 1000000L;
        writeVarLong(millis);
        writeVarLong(nanos);
      }
      else
      {
        Timestamp ts = v.getTimestamp();
        writeByte((byte)type);
        writeVarLong(DateTimeUtils.getTimeLocalWithoutDst(ts));
        writeVarInt(ts.getNanos() % 1000000);
      }
      break;
    case 19: 
    case 22: 
      writeByte((byte)type);
      byte[] b = v.getBytesNoCopy();
      int len = b.length;
      writeVarInt(len);
      write(b, 0, len);
      break;
    case 12: 
      byte[] b = v.getBytesNoCopy();
      int len = b.length;
      if (len < 32)
      {
        writeByte((byte)(100 + len));
        write(b, 0, len);
      }
      else
      {
        writeByte((byte)type);
        writeVarInt(len);
        write(b, 0, len);
      }
      break;
    case 20: 
      writeByte((byte)type);
      ValueUuid uuid = (ValueUuid)v;
      writeLong(uuid.getHigh());
      writeLong(uuid.getLow());
      break;
    case 13: 
      String s = v.getString();
      int len = s.length();
      if (len < 32)
      {
        writeByte((byte)(68 + len));
        writeStringWithoutLength(s, len);
      }
      else
      {
        writeByte((byte)type);
        writeString(s);
      }
      break;
    case 14: 
    case 21: 
      writeByte((byte)type);
      writeString(v.getString());
      break;
    case 7: 
      double x = v.getDouble();
      if (x == 1.0D)
      {
        writeByte((byte)61);
      }
      else
      {
        long d = Double.doubleToLongBits(x);
        if (d == ValueDouble.ZERO_BITS)
        {
          writeByte((byte)60);
        }
        else
        {
          writeByte((byte)type);
          writeVarLong(Long.reverse(d));
        }
      }
      break;
    case 8: 
      float x = v.getFloat();
      if (x == 1.0F)
      {
        writeByte((byte)63);
      }
      else
      {
        int f = Float.floatToIntBits(x);
        if (f == ValueFloat.ZERO_BITS)
        {
          writeByte((byte)62);
        }
        else
        {
          writeByte((byte)type);
          writeVarInt(Integer.reverse(f));
        }
      }
      break;
    case 15: 
    case 16: 
      writeByte((byte)type);
      if ((v instanceof ValueLob))
      {
        ValueLob lob = (ValueLob)v;
        lob.convertToFileIfRequired(this.handler);
        byte[] small = lob.getSmall();
        if (small == null)
        {
          int t = -1;
          if (!lob.isLinked()) {
            t = -2;
          }
          writeVarInt(t);
          writeVarInt(lob.getTableId());
          writeVarInt(lob.getObjectId());
          writeVarLong(lob.getPrecision());
          writeByte((byte)(lob.isCompressed() ? 1 : 0));
          if (t == -2) {
            writeString(lob.getFileName());
          }
        }
        else
        {
          writeVarInt(small.length);
          write(small, 0, small.length);
        }
      }
      else
      {
        ValueLobDb lob = (ValueLobDb)v;
        byte[] small = lob.getSmall();
        if (small == null)
        {
          writeVarInt(-3);
          writeVarInt(lob.getTableId());
          writeVarLong(lob.getLobId());
          writeVarLong(lob.getPrecision());
        }
        else
        {
          writeVarInt(small.length);
          write(small, 0, small.length);
        }
      }
      break;
    case 17: 
      writeByte((byte)type);
      Value[] list = ((ValueArray)v).getList();
      writeVarInt(list.length);
      for (Value x : list) {
        writeValue(x);
      }
      break;
    case 18: 
      writeByte((byte)type);
      try
      {
        ResultSet rs = ((ValueResultSet)v).getResultSet();
        rs.beforeFirst();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        writeVarInt(columnCount);
        for (int i = 0; i < columnCount; i++)
        {
          writeString(meta.getColumnName(i + 1));
          writeVarInt(meta.getColumnType(i + 1));
          writeVarInt(meta.getPrecision(i + 1));
          writeVarInt(meta.getScale(i + 1));
        }
        while (rs.next())
        {
          writeByte((byte)1);
          for (int i = 0; i < columnCount; i++)
          {
            int t = DataType.getValueTypeFromResultSet(meta, i + 1);
            Value val = DataType.readValue(null, rs, i + 1, t);
            writeValue(val);
          }
        }
        writeByte((byte)0);
        rs.beforeFirst();
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    default: 
      DbException.throwInternalError("type=" + v.getType());
    }
    if ((SysProperties.CHECK2) && 
      (this.pos - start != getValueLen(v, this.handler))) {
      throw DbException.throwInternalError("value size error: got " + (this.pos - start) + " expected " + getValueLen(v, this.handler));
    }
  }
  
  public Value readValue()
  {
    int type = this.data[(this.pos++)] & 0xFF;
    switch (type)
    {
    case 0: 
      return ValueNull.INSTANCE;
    case 65: 
      return ValueBoolean.get(true);
    case 64: 
      return ValueBoolean.get(false);
    case 66: 
      return ValueInt.get(-readVarInt());
    case 4: 
      return ValueInt.get(readVarInt());
    case 67: 
      return ValueLong.get(-readVarLong());
    case 5: 
      return ValueLong.get(readVarLong());
    case 2: 
      return ValueByte.get(readByte());
    case 3: 
      return ValueShort.get(readShortInt());
    case 56: 
      return (ValueDecimal)ValueDecimal.ZERO;
    case 57: 
      return (ValueDecimal)ValueDecimal.ONE;
    case 58: 
      return ValueDecimal.get(BigDecimal.valueOf(readVarLong()));
    case 59: 
      int scale = readVarInt();
      return ValueDecimal.get(BigDecimal.valueOf(readVarLong(), scale));
    case 6: 
      int scale = readVarInt();
      int len = readVarInt();
      byte[] buff = DataUtils.newBytes(len);
      read(buff, 0, len);
      BigInteger b = new BigInteger(buff);
      return ValueDecimal.get(new BigDecimal(b, scale));
    case 133: 
      return ValueDate.fromDateValue(readVarLong());
    case 10: 
      long x = readVarLong() * 60000L;
      return ValueDate.fromMillis(DateTimeUtils.getTimeUTCWithoutDst(x));
    case 132: 
      long nanos = readVarLong() * 1000000L + readVarLong();
      return ValueTime.fromNanos(nanos);
    case 9: 
      return ValueTime.fromMillis(DateTimeUtils.getTimeUTCWithoutDst(readVarLong()));
    case 134: 
      long dateValue = readVarLong();
      long nanos = readVarLong() * 1000000L + readVarLong();
      return ValueTimestamp.fromDateValueAndNanos(dateValue, nanos);
    case 11: 
      return ValueTimestamp.fromMillisNanos(DateTimeUtils.getTimeUTCWithoutDst(readVarLong()), readVarInt());
    case 12: 
      int len = readVarInt();
      byte[] b = DataUtils.newBytes(len);
      read(b, 0, len);
      return ValueBytes.getNoCopy(b);
    case 22: 
      int len = readVarInt();
      byte[] b = DataUtils.newBytes(len);
      read(b, 0, len);
      return ValueGeometry.get(b);
    case 19: 
      int len = readVarInt();
      byte[] b = DataUtils.newBytes(len);
      read(b, 0, len);
      return ValueJavaObject.getNoCopy(null, b, this.handler);
    case 20: 
      return ValueUuid.get(readLong(), readLong());
    case 13: 
      return ValueString.get(readString());
    case 14: 
      return ValueStringIgnoreCase.get(readString());
    case 21: 
      return ValueStringFixed.get(readString());
    case 62: 
      return ValueFloat.get(0.0F);
    case 63: 
      return ValueFloat.get(1.0F);
    case 60: 
      return ValueDouble.get(0.0D);
    case 61: 
      return ValueDouble.get(1.0D);
    case 7: 
      return ValueDouble.get(Double.longBitsToDouble(Long.reverse(readVarLong())));
    case 8: 
      return ValueFloat.get(Float.intBitsToFloat(Integer.reverse(readVarInt())));
    case 15: 
    case 16: 
      int smallLen = readVarInt();
      if (smallLen >= 0)
      {
        byte[] small = DataUtils.newBytes(smallLen);
        read(small, 0, smallLen);
        return ValueLobDb.createSmallLob(type, small);
      }
      if (smallLen == -3)
      {
        int tableId = readVarInt();
        long lobId = readVarLong();
        long precision = readVarLong();
        ValueLobDb lob = ValueLobDb.create(type, this.handler, tableId, lobId, null, precision);
        
        return lob;
      }
      int tableId = readVarInt();
      int objectId = readVarInt();
      long precision = 0L;
      boolean compression = false;
      if ((smallLen == -1) || (smallLen == -2))
      {
        precision = readVarLong();
        compression = readByte() == 1;
      }
      if (smallLen == -2)
      {
        String filename = readString();
        return ValueLob.openUnlinked(type, this.handler, tableId, objectId, precision, compression, filename);
      }
      return ValueLob.openLinked(type, this.handler, tableId, objectId, precision, compression);
    case 17: 
      int len = readVarInt();
      Value[] list = new Value[len];
      for (int i = 0; i < len; i++) {
        list[i] = readValue();
      }
      return ValueArray.get(list);
    case 18: 
      SimpleResultSet rs = new SimpleResultSet();
      rs.setAutoClose(false);
      int columns = readVarInt();
      for (int i = 0; i < columns; i++) {
        rs.addColumn(readString(), readVarInt(), readVarInt(), readVarInt());
      }
      while (readByte() != 0)
      {
        Object[] o = new Object[columns];
        for (int i = 0; i < columns; i++) {
          o[i] = readValue().getObject();
        }
        rs.addRow(o);
      }
      return ValueResultSet.get(rs);
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
      read(b, 0, len);
      return ValueBytes.getNoCopy(b);
    }
    if ((type >= 68) && (type < 100)) {
      return ValueString.get(readString(type - 68));
    }
    throw DbException.get(90030, "type: " + type);
  }
  
  public int getValueLen(Value v)
  {
    return getValueLen(v, this.handler);
  }
  
  public static int getValueLen(Value v, DataHandler handler)
  {
    if (v == ValueNull.INSTANCE) {
      return 1;
    }
    switch (v.getType())
    {
    case 1: 
      return 1;
    case 2: 
      return 2;
    case 3: 
      return 3;
    case 4: 
      int x = v.getInt();
      if (x < 0) {
        return 1 + getVarIntLen(-x);
      }
      if (x < 16) {
        return 1;
      }
      return 1 + getVarIntLen(x);
    case 5: 
      long x = v.getLong();
      if (x < 0L) {
        return 1 + getVarLongLen(-x);
      }
      if (x < 8L) {
        return 1;
      }
      return 1 + getVarLongLen(x);
    case 7: 
      double x = v.getDouble();
      if (x == 1.0D) {
        return 1;
      }
      long d = Double.doubleToLongBits(x);
      if (d == ValueDouble.ZERO_BITS) {
        return 1;
      }
      return 1 + getVarLongLen(Long.reverse(d));
    case 8: 
      float x = v.getFloat();
      if (x == 1.0F) {
        return 1;
      }
      int f = Float.floatToIntBits(x);
      if (f == ValueFloat.ZERO_BITS) {
        return 1;
      }
      return 1 + getVarIntLen(Integer.reverse(f));
    case 13: 
      String s = v.getString();
      int len = s.length();
      if (len < 32) {
        return 1 + getStringWithoutLengthLen(s, len);
      }
      return 1 + getStringLen(s);
    case 14: 
    case 21: 
      return 1 + getStringLen(v.getString());
    case 6: 
      BigDecimal x = v.getBigDecimal();
      if (BigDecimal.ZERO.equals(x)) {
        return 1;
      }
      if (BigDecimal.ONE.equals(x)) {
        return 1;
      }
      int scale = x.scale();
      BigInteger b = x.unscaledValue();
      int bits = b.bitLength();
      if (bits <= 63)
      {
        if (scale == 0) {
          return 1 + getVarLongLen(b.longValue());
        }
        return 1 + getVarIntLen(scale) + getVarLongLen(b.longValue());
      }
      byte[] bytes = b.toByteArray();
      return 1 + getVarIntLen(scale) + getVarIntLen(bytes.length) + bytes.length;
    case 9: 
      if (SysProperties.STORE_LOCAL_TIME)
      {
        long nanos = ((ValueTime)v).getNanos();
        long millis = nanos / 1000000L;
        nanos -= millis * 1000000L;
        return 1 + getVarLongLen(millis) + getVarLongLen(nanos);
      }
      return 1 + getVarLongLen(DateTimeUtils.getTimeLocalWithoutDst(v.getTime()));
    case 10: 
      if (SysProperties.STORE_LOCAL_TIME)
      {
        long dateValue = ((ValueDate)v).getDateValue();
        return 1 + getVarLongLen(dateValue);
      }
      long x = DateTimeUtils.getTimeLocalWithoutDst(v.getDate());
      return 1 + getVarLongLen(x / 60000L);
    case 11: 
      if (SysProperties.STORE_LOCAL_TIME)
      {
        ValueTimestamp ts = (ValueTimestamp)v;
        long dateValue = ts.getDateValue();
        long nanos = ts.getTimeNanos();
        long millis = nanos / 1000000L;
        nanos -= millis * 1000000L;
        return 1 + getVarLongLen(dateValue) + getVarLongLen(millis) + getVarLongLen(nanos);
      }
      Timestamp ts = v.getTimestamp();
      return 1 + getVarLongLen(DateTimeUtils.getTimeLocalWithoutDst(ts)) + getVarIntLen(ts.getNanos() % 1000000);
    case 19: 
    case 22: 
      byte[] b = v.getBytesNoCopy();
      return 1 + getVarIntLen(b.length) + b.length;
    case 12: 
      byte[] b = v.getBytesNoCopy();
      int len = b.length;
      if (len < 32) {
        return 1 + b.length;
      }
      return 1 + getVarIntLen(b.length) + b.length;
    case 20: 
      return 17;
    case 15: 
    case 16: 
      int len = 1;
      if ((v instanceof ValueLob))
      {
        ValueLob lob = (ValueLob)v;
        lob.convertToFileIfRequired(handler);
        byte[] small = lob.getSmall();
        if (small == null)
        {
          int t = -1;
          if (!lob.isLinked()) {
            t = -2;
          }
          len += getVarIntLen(t);
          len += getVarIntLen(lob.getTableId());
          len += getVarIntLen(lob.getObjectId());
          len += getVarLongLen(lob.getPrecision());
          len++;
          if (t == -2) {
            len += getStringLen(lob.getFileName());
          }
        }
        else
        {
          len += getVarIntLen(small.length);
          len += small.length;
        }
      }
      else
      {
        ValueLobDb lob = (ValueLobDb)v;
        byte[] small = lob.getSmall();
        if (small == null)
        {
          len += getVarIntLen(-3);
          len += getVarIntLen(lob.getTableId());
          len += getVarLongLen(lob.getLobId());
          len += getVarLongLen(lob.getPrecision());
        }
        else
        {
          len += getVarIntLen(small.length);
          len += small.length;
        }
      }
      return len;
    case 17: 
      Value[] list = ((ValueArray)v).getList();
      int len = 1 + getVarIntLen(list.length);
      for (Value x : list) {
        len += getValueLen(x, handler);
      }
      return len;
    case 18: 
      int len = 1;
      try
      {
        ResultSet rs = ((ValueResultSet)v).getResultSet();
        rs.beforeFirst();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        len += getVarIntLen(columnCount);
        for (int i = 0; i < columnCount; i++)
        {
          len += getStringLen(meta.getColumnName(i + 1));
          len += getVarIntLen(meta.getColumnType(i + 1));
          len += getVarIntLen(meta.getPrecision(i + 1));
          len += getVarIntLen(meta.getScale(i + 1));
        }
        while (rs.next())
        {
          len++;
          for (int i = 0; i < columnCount; i++)
          {
            int t = DataType.getValueTypeFromResultSet(meta, i + 1);
            Value val = DataType.readValue(null, rs, i + 1, t);
            len += getValueLen(val, handler);
          }
        }
        len++;
        rs.beforeFirst();
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
      return len;
    }
    throw DbException.throwInternalError("type=" + v.getType());
  }
  
  public void setPos(int pos)
  {
    this.pos = pos;
  }
  
  public void writeShortInt(int x)
  {
    byte[] buff = this.data;
    buff[(this.pos++)] = ((byte)(x >> 8));
    buff[(this.pos++)] = ((byte)x);
  }
  
  public short readShortInt()
  {
    byte[] buff = this.data;
    return (short)(((buff[(this.pos++)] & 0xFF) << 8) + (buff[(this.pos++)] & 0xFF));
  }
  
  public void truncate(int size)
  {
    if (this.pos > size)
    {
      byte[] buff = new byte[size];
      System.arraycopy(this.data, 0, buff, 0, size);
      this.pos = size;
      this.data = buff;
    }
  }
  
  private static int getVarIntLen(int x)
  {
    if ((x & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((x & 0xC000) == 0) {
      return 2;
    }
    if ((x & 0xFFE00000) == 0) {
      return 3;
    }
    if ((x & 0xF0000000) == 0) {
      return 4;
    }
    return 5;
  }
  
  public void writeVarInt(int x)
  {
    while ((x & 0xFFFFFF80) != 0)
    {
      this.data[(this.pos++)] = ((byte)(0x80 | x & 0x7F));
      x >>>= 7;
    }
    this.data[(this.pos++)] = ((byte)x);
  }
  
  public int readVarInt()
  {
    int b = this.data[this.pos];
    if (b >= 0)
    {
      this.pos += 1;
      return b;
    }
    return readVarIntRest(b);
  }
  
  private int readVarIntRest(int b)
  {
    int x = b & 0x7F;
    b = this.data[(this.pos + 1)];
    if (b >= 0)
    {
      this.pos += 2;
      return x | b << 7;
    }
    x |= (b & 0x7F) << 7;
    b = this.data[(this.pos + 2)];
    if (b >= 0)
    {
      this.pos += 3;
      return x | b << 14;
    }
    x |= (b & 0x7F) << 14;
    b = this.data[(this.pos + 3)];
    if (b >= 0)
    {
      this.pos += 4;
      return x | b << 21;
    }
    x |= (b & 0x7F) << 21 | this.data[(this.pos + 4)] << 28;
    this.pos += 5;
    return x;
  }
  
  public static int getVarLongLen(long x)
  {
    int i = 1;
    for (;;)
    {
      x >>>= 7;
      if (x == 0L) {
        return i;
      }
      i++;
    }
  }
  
  public void writeVarLong(long x)
  {
    while ((x & 0xFFFFFFFFFFFFFF80) != 0L)
    {
      this.data[(this.pos++)] = ((byte)(int)(x & 0x7F | 0x80));
      x >>>= 7;
    }
    this.data[(this.pos++)] = ((byte)(int)x);
  }
  
  public long readVarLong()
  {
    long x = this.data[(this.pos++)];
    if (x >= 0L) {
      return x;
    }
    x &= 0x7F;
    for (int s = 7;; s += 7)
    {
      long b = this.data[(this.pos++)];
      x |= (b & 0x7F) << s;
      if (b >= 0L) {
        return x;
      }
    }
  }
  
  public void checkCapacity(int plus)
  {
    if (this.pos + plus >= this.data.length) {
      expand(plus);
    }
  }
  
  private void expand(int plus)
  {
    byte[] d = DataUtils.newBytes((this.data.length + plus) * 2);
    
    System.arraycopy(this.data, 0, d, 0, this.data.length);
    this.data = d;
  }
  
  public void fillAligned()
  {
    int len = MathUtils.roundUpInt(this.pos + 2, 16);
    this.pos = len;
    if (this.data.length < len) {
      checkCapacity(len - this.data.length);
    }
  }
  
  public static void copyString(Reader source, OutputStream target)
    throws IOException
  {
    char[] buff = new char['က'];
    Data d = new Data(null, new byte['　']);
    for (;;)
    {
      int l = source.read(buff);
      if (l < 0) {
        break;
      }
      d.writeStringWithoutLength(buff, l);
      target.write(d.data, 0, d.pos);
      d.reset();
    }
  }
  
  public DataHandler getHandler()
  {
    return this.handler;
  }
}
