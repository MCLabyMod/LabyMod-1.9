package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;

public class ValueFloat
  extends Value
{
  public static final int ZERO_BITS = Float.floatToIntBits(0.0F);
  static final int PRECISION = 7;
  static final int DISPLAY_SIZE = 15;
  private static final ValueFloat ZERO = new ValueFloat(0.0F);
  private static final ValueFloat ONE = new ValueFloat(1.0F);
  private final float value;
  
  private ValueFloat(float value)
  {
    this.value = value;
  }
  
  public Value add(Value v)
  {
    ValueFloat v2 = (ValueFloat)v;
    return get(this.value + v2.value);
  }
  
  public Value subtract(Value v)
  {
    ValueFloat v2 = (ValueFloat)v;
    return get(this.value - v2.value);
  }
  
  public Value negate()
  {
    return get(-this.value);
  }
  
  public Value multiply(Value v)
  {
    ValueFloat v2 = (ValueFloat)v;
    return get(this.value * v2.value);
  }
  
  public Value divide(Value v)
  {
    ValueFloat v2 = (ValueFloat)v;
    if (v2.value == 0.0D) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value / v2.value);
  }
  
  public Value modulus(Value v)
  {
    ValueFloat other = (ValueFloat)v;
    if (other.value == 0.0F) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value % other.value);
  }
  
  public String getSQL()
  {
    if (this.value == Float.POSITIVE_INFINITY) {
      return "POWER(0, -1)";
    }
    if (this.value == Float.NEGATIVE_INFINITY) {
      return "(-POWER(0, -1))";
    }
    if (Double.isNaN(this.value)) {
      return "SQRT(-1)";
    }
    String s = getString();
    if (s.equals("-0.0")) {
      return "-CAST(0 AS REAL)";
    }
    return s;
  }
  
  public int getType()
  {
    return 8;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueFloat v = (ValueFloat)o;
    return Float.compare(this.value, v.value);
  }
  
  public int getSignum()
  {
    return this.value < 0.0F ? -1 : this.value == 0.0F ? 0 : 1;
  }
  
  public float getFloat()
  {
    return this.value;
  }
  
  public String getString()
  {
    return String.valueOf(this.value);
  }
  
  public long getPrecision()
  {
    return 7L;
  }
  
  public int getScale()
  {
    return 0;
  }
  
  public int hashCode()
  {
    long hash = Float.floatToIntBits(this.value);
    return (int)(hash ^ hash >> 32);
  }
  
  public Object getObject()
  {
    return Float.valueOf(this.value);
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setFloat(parameterIndex, this.value);
  }
  
  public static ValueFloat get(float d)
  {
    if (d == 1.0F) {
      return ONE;
    }
    if (d == 0.0F) {
      if (Float.floatToIntBits(d) == ZERO_BITS) {
        return ZERO;
      }
    }
    return (ValueFloat)Value.cache(new ValueFloat(d));
  }
  
  public int getDisplaySize()
  {
    return 15;
  }
  
  public boolean equals(Object other)
  {
    if (!(other instanceof ValueFloat)) {
      return false;
    }
    return compareSecure((ValueFloat)other, null) == 0;
  }
}
