package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.util.MathUtils;

public class ValueByte
  extends Value
{
  static final int PRECISION = 3;
  static final int DISPLAY_SIZE = 4;
  private final byte value;
  
  private ValueByte(byte value)
  {
    this.value = value;
  }
  
  public Value add(Value v)
  {
    ValueByte other = (ValueByte)v;
    return checkRange(this.value + other.value);
  }
  
  private static ValueByte checkRange(int x)
  {
    if ((x < -128) || (x > 127)) {
      throw DbException.get(22003, Integer.toString(x));
    }
    return get((byte)x);
  }
  
  public int getSignum()
  {
    return Integer.signum(this.value);
  }
  
  public Value negate()
  {
    return checkRange(-this.value);
  }
  
  public Value subtract(Value v)
  {
    ValueByte other = (ValueByte)v;
    return checkRange(this.value - other.value);
  }
  
  public Value multiply(Value v)
  {
    ValueByte other = (ValueByte)v;
    return checkRange(this.value * other.value);
  }
  
  public Value divide(Value v)
  {
    ValueByte other = (ValueByte)v;
    if (other.value == 0) {
      throw DbException.get(22012, getSQL());
    }
    return get((byte)(this.value / other.value));
  }
  
  public Value modulus(Value v)
  {
    ValueByte other = (ValueByte)v;
    if (other.value == 0) {
      throw DbException.get(22012, getSQL());
    }
    return get((byte)(this.value % other.value));
  }
  
  public String getSQL()
  {
    return getString();
  }
  
  public int getType()
  {
    return 2;
  }
  
  public byte getByte()
  {
    return this.value;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueByte v = (ValueByte)o;
    return MathUtils.compareInt(this.value, v.value);
  }
  
  public String getString()
  {
    return String.valueOf(this.value);
  }
  
  public long getPrecision()
  {
    return 3L;
  }
  
  public int hashCode()
  {
    return this.value;
  }
  
  public Object getObject()
  {
    return Byte.valueOf(this.value);
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setByte(parameterIndex, this.value);
  }
  
  public static ValueByte get(byte i)
  {
    return (ValueByte)Value.cache(new ValueByte(i));
  }
  
  public int getDisplaySize()
  {
    return 4;
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueByte)) && (this.value == ((ValueByte)other).value);
  }
}
