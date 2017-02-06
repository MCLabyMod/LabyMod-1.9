package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import org.h2.message.DbException;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class ValueUuid
  extends Value
{
  private static final int PRECISION = 16;
  private static final int DISPLAY_SIZE = 36;
  private final long high;
  private final long low;
  
  private ValueUuid(long high, long low)
  {
    this.high = high;
    this.low = low;
  }
  
  public int hashCode()
  {
    return (int)(this.high >>> 32 ^ this.high ^ this.low >>> 32 ^ this.low);
  }
  
  public static ValueUuid getNewRandom()
  {
    long high = MathUtils.secureRandomLong();
    long low = MathUtils.secureRandomLong();
    
    high = high & 0xFFFFFFFFFFFF0FFF | 0x4000;
    
    low = low & 0x3FFFFFFFFFFFFFFF | 0x8000000000000000;
    return new ValueUuid(high, low);
  }
  
  public static ValueUuid get(byte[] binary)
  {
    if (binary.length < 16) {
      return get(StringUtils.convertBytesToHex(binary));
    }
    long high = Utils.readLong(binary, 0);
    long low = Utils.readLong(binary, 8);
    return (ValueUuid)Value.cache(new ValueUuid(high, low));
  }
  
  public static ValueUuid get(long high, long low)
  {
    return (ValueUuid)Value.cache(new ValueUuid(high, low));
  }
  
  public static ValueUuid get(String s)
  {
    long low = 0L;long high = 0L;
    int i = 0;int j = 0;
    for (int length = s.length(); i < length; i++)
    {
      char c = s.charAt(i);
      if ((c >= '0') && (c <= '9'))
      {
        low = low << 4 | c - '0';
      }
      else if ((c >= 'a') && (c <= 'f'))
      {
        low = low << 4 | c - 'a' + 10;
      }
      else
      {
        if (c == '-') {
          continue;
        }
        if ((c >= 'A') && (c <= 'F'))
        {
          low = low << 4 | c - 'A' + 10;
        }
        else
        {
          if (c <= ' ') {
            continue;
          }
          throw DbException.get(22018, s);
        }
      }
      if (j++ == 15)
      {
        high = low;
        low = 0L;
      }
    }
    return (ValueUuid)Value.cache(new ValueUuid(high, low));
  }
  
  public String getSQL()
  {
    return StringUtils.quoteStringSQL(getString());
  }
  
  public int getType()
  {
    return 20;
  }
  
  public long getPrecision()
  {
    return 16L;
  }
  
  private static void appendHex(StringBuilder buff, long x, int bytes)
  {
    for (int i = bytes * 8 - 4; i >= 0; i -= 8) {
      buff.append(Integer.toHexString((int)(x >> i) & 0xF)).append(Integer.toHexString((int)(x >> i - 4) & 0xF));
    }
  }
  
  public String getString()
  {
    StringBuilder buff = new StringBuilder(36);
    appendHex(buff, this.high >> 32, 4);
    buff.append('-');
    appendHex(buff, this.high >> 16, 2);
    buff.append('-');
    appendHex(buff, this.high, 2);
    buff.append('-');
    appendHex(buff, this.low >> 48, 2);
    buff.append('-');
    appendHex(buff, this.low, 6);
    return buff.toString();
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    if (o == this) {
      return 0;
    }
    ValueUuid v = (ValueUuid)o;
    if (this.high == v.high) {
      return MathUtils.compareLong(this.low, v.low);
    }
    return this.high > v.high ? 1 : -1;
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueUuid)) && (compareSecure((Value)other, null) == 0);
  }
  
  public Object getObject()
  {
    return new UUID(this.high, this.low);
  }
  
  public byte[] getBytes()
  {
    byte[] buff = new byte[16];
    for (int i = 0; i < 8; i++)
    {
      buff[i] = ((byte)(int)(this.high >> 8 * (7 - i) & 0xFF));
      buff[(8 + i)] = ((byte)(int)(this.low >> 8 * (7 - i) & 0xFF));
    }
    return buff;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setBytes(parameterIndex, getBytes());
  }
  
  public long getHigh()
  {
    return this.high;
  }
  
  public long getLow()
  {
    return this.low;
  }
  
  public int getDisplaySize()
  {
    return 36;
  }
}
