package org.h2.value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.util.MathUtils;

public class ValueLong
  extends Value
{
  public static final BigInteger MAX = BigInteger.valueOf(Long.MAX_VALUE);
  public static final BigDecimal MIN_BD = BigDecimal.valueOf(Long.MIN_VALUE);
  public static final int PRECISION = 19;
  public static final int DISPLAY_SIZE = 20;
  private static final BigInteger MIN = BigInteger.valueOf(Long.MIN_VALUE);
  private static final int STATIC_SIZE = 100;
  private static final ValueLong[] STATIC_CACHE = new ValueLong[100];
  private final long value;
  
  static
  {
    for (int i = 0; i < 100; i++) {
      STATIC_CACHE[i] = new ValueLong(i);
    }
  }
  
  private ValueLong(long value)
  {
    this.value = value;
  }
  
  public Value add(Value v)
  {
    ValueLong other = (ValueLong)v;
    long result = this.value + other.value;
    int sv = Long.signum(this.value);
    int so = Long.signum(other.value);
    int sr = Long.signum(result);
    if ((sv != so) || (sr == so) || (sv == 0) || (so == 0)) {
      return get(result);
    }
    throw getOverflow();
  }
  
  public int getSignum()
  {
    return Long.signum(this.value);
  }
  
  public Value negate()
  {
    if (this.value == Long.MIN_VALUE) {
      throw getOverflow();
    }
    return get(-this.value);
  }
  
  private DbException getOverflow()
  {
    return DbException.get(22003, Long.toString(this.value));
  }
  
  public Value subtract(Value v)
  {
    ValueLong other = (ValueLong)v;
    int sv = Long.signum(this.value);
    int so = Long.signum(other.value);
    if ((sv == so) || (so == 0)) {
      return get(this.value - other.value);
    }
    return add(other.negate());
  }
  
  private static boolean isInteger(long a)
  {
    return (a >= -2147483648L) && (a <= 2147483647L);
  }
  
  public Value multiply(Value v)
  {
    ValueLong other = (ValueLong)v;
    long result = this.value * other.value;
    if ((this.value == 0L) || (this.value == 1L) || (other.value == 0L) || (other.value == 1L)) {
      return get(result);
    }
    if ((isInteger(this.value)) && (isInteger(other.value))) {
      return get(result);
    }
    BigInteger bv = BigInteger.valueOf(this.value);
    BigInteger bo = BigInteger.valueOf(other.value);
    BigInteger br = bv.multiply(bo);
    if ((br.compareTo(MIN) < 0) || (br.compareTo(MAX) > 0)) {
      throw getOverflow();
    }
    return get(br.longValue());
  }
  
  public Value divide(Value v)
  {
    ValueLong other = (ValueLong)v;
    if (other.value == 0L) {
      throw DbException.get(22012, getSQL());
    }
    return get(this.value / other.value);
  }
  
  public Value modulus(Value v)
  {
    ValueLong other = (ValueLong)v;
    if (other.value == 0L) {
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
    return 5;
  }
  
  public long getLong()
  {
    return this.value;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueLong v = (ValueLong)o;
    return MathUtils.compareLong(this.value, v.value);
  }
  
  public String getString()
  {
    return String.valueOf(this.value);
  }
  
  public long getPrecision()
  {
    return 19L;
  }
  
  public int hashCode()
  {
    return (int)(this.value ^ this.value >> 32);
  }
  
  public Object getObject()
  {
    return Long.valueOf(this.value);
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setLong(parameterIndex, this.value);
  }
  
  public static ValueLong get(long i)
  {
    if ((i >= 0L) && (i < 100L)) {
      return STATIC_CACHE[((int)i)];
    }
    return (ValueLong)Value.cache(new ValueLong(i));
  }
  
  public int getDisplaySize()
  {
    return 20;
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueLong)) && (this.value == ((ValueLong)other).value);
  }
}
