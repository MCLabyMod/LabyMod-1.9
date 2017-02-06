package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.util.MathUtils;

public class ValueShort
  extends Value
{
  static final int PRECISION = 5;
  static final int DISPLAY_SIZE = 6;
  private final short value;
  
  private ValueShort(short value)
  {
    this.value = value;
  }
  
  public Value add(Value v)
  {
    ValueShort other = (ValueShort)v;
    return checkRange(this.value + other.value);
  }
  
  private static ValueShort checkRange(int x)
  {
    if ((x < 32768) || (x > 32767)) {
      throw DbException.get(22003, Integer.toString(x));
    }
    return get((short)x);
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
    ValueShort other = (ValueShort)v;
    return checkRange(this.value - other.value);
  }
  
  public Value multiply(Value v)
  {
    ValueShort other = (ValueShort)v;
    return checkRange(this.value * other.value);
  }
  
  public Value divide(Value v)
  {
    ValueShort other = (ValueShort)v;
    if (other.value == 0) {
      throw DbException.get(22012, getSQL());
    }
    return get((short)(this.value / other.value));
  }
  
  public Value modulus(Value v)
  {
    ValueShort other = (ValueShort)v;
    if (other.value == 0) {
      throw DbException.get(22012, getSQL());
    }
    return get((short)(this.value % other.value));
  }
  
  public String getSQL()
  {
    return getString();
  }
  
  public int getType()
  {
    return 3;
  }
  
  public short getShort()
  {
    return this.value;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueShort v = (ValueShort)o;
    return MathUtils.compareInt(this.value, v.value);
  }
  
  public String getString()
  {
    return String.valueOf(this.value);
  }
  
  public long getPrecision()
  {
    return 5L;
  }
  
  public int hashCode()
  {
    return this.value;
  }
  
  public Object getObject()
  {
    return Short.valueOf(this.value);
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setShort(parameterIndex, this.value);
  }
  
  public static ValueShort get(short i)
  {
    return (ValueShort)Value.cache(new ValueShort(i));
  }
  
  public int getDisplaySize()
  {
    return 6;
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueShort)) && (this.value == ((ValueShort)other).value);
  }
}
