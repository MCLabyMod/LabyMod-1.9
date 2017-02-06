package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;

public class ValueDouble
  extends Value
{
  public static final int PRECISION = 17;
  public static final int DISPLAY_SIZE = 24;
  public static final long ZERO_BITS = Double.doubleToLongBits(0.0D);
  private static final ValueDouble ZERO = new ValueDouble(0.0D);
  private static final ValueDouble ONE = new ValueDouble(1.0D);
  private static final ValueDouble NAN = new ValueDouble(NaN.0D);
  private final double value;
  
  private ValueDouble(double value)
  {
    this.value = value;
  }
  
  public Value add(Value v)
  {
    ValueDouble v2 = (ValueDouble)v;
    return get(this.value + v2.value);
  }
  
  public Value subtract(Value v)
  {
    ValueDouble v2 = (ValueDouble)v;
    return get(this.value - v2.value);
  }
  
  public Value negate()
  {
    return get(-this.value);
  }
  
  public Value multiply(Value v)
  {
    ValueDouble v2 = (ValueDouble)v;
    return get(this.value * v2.value);
  }
  
  public Value divide(Value v)
  {
    ValueDouble v2 = (ValueDouble)v;
    if (v2.value == 0.0D) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value / v2.value);
  }
  
  public ValueDouble modulus(Value v)
  {
    ValueDouble other = (ValueDouble)v;
    if (other.value == 0.0D) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value % other.value);
  }
  
  public String getSQL()
  {
    if (this.value == Double.POSITIVE_INFINITY) {
      return "POWER(0, -1)";
    }
    if (this.value == Double.NEGATIVE_INFINITY) {
      return "(-POWER(0, -1))";
    }
    if (Double.isNaN(this.value)) {
      return "SQRT(-1)";
    }
    String s = getString();
    if (s.equals("-0.0")) {
      return "-CAST(0 AS DOUBLE)";
    }
    return s;
  }
  
  public int getType()
  {
    return 7;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueDouble v = (ValueDouble)o;
    return Double.compare(this.value, v.value);
  }
  
  public int getSignum()
  {
    return this.value < 0.0D ? -1 : this.value == 0.0D ? 0 : 1;
  }
  
  public double getDouble()
  {
    return this.value;
  }
  
  public String getString()
  {
    return String.valueOf(this.value);
  }
  
  public long getPrecision()
  {
    return 17L;
  }
  
  public int getScale()
  {
    return 0;
  }
  
  public int hashCode()
  {
    long hash = Double.doubleToLongBits(this.value);
    return (int)(hash ^ hash >> 32);
  }
  
  public Object getObject()
  {
    return Double.valueOf(this.value);
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setDouble(parameterIndex, this.value);
  }
  
  public static ValueDouble get(double d)
  {
    if (d == 1.0D) {
      return ONE;
    }
    if (d == 0.0D)
    {
      if (Double.doubleToLongBits(d) == ZERO_BITS) {
        return ZERO;
      }
    }
    else if (Double.isNaN(d)) {
      return NAN;
    }
    return (ValueDouble)Value.cache(new ValueDouble(d));
  }
  
  public int getDisplaySize()
  {
    return 24;
  }
  
  public boolean equals(Object other)
  {
    if (!(other instanceof ValueDouble)) {
      return false;
    }
    return compareSecure((ValueDouble)other, null) == 0;
  }
}
