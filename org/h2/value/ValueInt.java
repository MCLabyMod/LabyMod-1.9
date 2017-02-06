package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.util.MathUtils;

public class ValueInt
  extends Value
{
  public static final int PRECISION = 10;
  public static final int DISPLAY_SIZE = 11;
  private static final int STATIC_SIZE = 128;
  private static final int DYNAMIC_SIZE = 256;
  private static final ValueInt[] STATIC_CACHE = new ValueInt[''];
  private static final ValueInt[] DYNAMIC_CACHE = new ValueInt['Ā'];
  private final int value;
  
  static
  {
    for (int i = 0; i < 128; i++) {
      STATIC_CACHE[i] = new ValueInt(i);
    }
  }
  
  private ValueInt(int value)
  {
    this.value = value;
  }
  
  public static ValueInt get(int i)
  {
    if ((i >= 0) && (i < 128)) {
      return STATIC_CACHE[i];
    }
    ValueInt v = DYNAMIC_CACHE[(i & 0xFF)];
    if ((v == null) || (v.value != i))
    {
      v = new ValueInt(i);
      DYNAMIC_CACHE[(i & 0xFF)] = v;
    }
    return v;
  }
  
  public Value add(Value v)
  {
    ValueInt other = (ValueInt)v;
    return checkRange(this.value + other.value);
  }
  
  private static ValueInt checkRange(long x)
  {
    if ((x < -2147483648L) || (x > 2147483647L)) {
      throw DbException.get(22003, Long.toString(x));
    }
    return get((int)x);
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
    ValueInt other = (ValueInt)v;
    return checkRange(this.value - other.value);
  }
  
  public Value multiply(Value v)
  {
    ValueInt other = (ValueInt)v;
    return checkRange(this.value * other.value);
  }
  
  public Value divide(Value v)
  {
    ValueInt other = (ValueInt)v;
    if (other.value == 0) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value / other.value);
  }
  
  public Value modulus(Value v)
  {
    ValueInt other = (ValueInt)v;
    if (other.value == 0) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value % other.value);
  }
  
  public String getSQL()
  {
    return getString();
  }
  
  public int getType()
  {
    return 4;
  }
  
  public int getInt()
  {
    return this.value;
  }
  
  public long getLong()
  {
    return this.value;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueInt v = (ValueInt)o;
    return MathUtils.compareInt(this.value, v.value);
  }
  
  public String getString()
  {
    return String.valueOf(this.value);
  }
  
  public long getPrecision()
  {
    return 10L;
  }
  
  public int hashCode()
  {
    return this.value;
  }
  
  public Object getObject()
  {
    return Integer.valueOf(this.value);
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setInt(parameterIndex, this.value);
  }
  
  public int getDisplaySize()
  {
    return 11;
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueInt)) && (this.value == ((ValueInt)other).value);
  }
}
