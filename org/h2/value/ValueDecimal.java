package org.h2.value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.util.MathUtils;

public class ValueDecimal
  extends Value
{
  public static final Object ZERO = new ValueDecimal(BigDecimal.ZERO);
  public static final Object ONE = new ValueDecimal(BigDecimal.ONE);
  static final int DEFAULT_PRECISION = 65535;
  static final int DEFAULT_SCALE = 32767;
  static final int DEFAULT_DISPLAY_SIZE = 65535;
  private static final int DIVIDE_SCALE_ADD = 25;
  private static final int BIG_DECIMAL_SCALE_MAX = 100000;
  private final BigDecimal value;
  private String valueString;
  private int precision;
  
  private ValueDecimal(BigDecimal value)
  {
    if (value == null) {
      throw new IllegalArgumentException("null");
    }
    if (!value.getClass().equals(BigDecimal.class)) {
      throw DbException.get(90125, new String[] { BigDecimal.class.getName(), value.getClass().getName() });
    }
    this.value = value;
  }
  
  public Value add(Value v)
  {
    ValueDecimal dec = (ValueDecimal)v;
    return get(this.value.add(dec.value));
  }
  
  public Value subtract(Value v)
  {
    ValueDecimal dec = (ValueDecimal)v;
    return get(this.value.subtract(dec.value));
  }
  
  public Value negate()
  {
    return get(this.value.negate());
  }
  
  public Value multiply(Value v)
  {
    ValueDecimal dec = (ValueDecimal)v;
    return get(this.value.multiply(dec.value));
  }
  
  public Value divide(Value v)
  {
    ValueDecimal dec = (ValueDecimal)v;
    if (dec.value.signum() == 0) {
      throw DbException.get(22012, getSQL());
    }
    BigDecimal bd = this.value.divide(dec.value, this.value.scale() + 25, 5);
    if (bd.signum() == 0) {
      bd = BigDecimal.ZERO;
    } else if ((bd.scale() > 0) && 
      (!bd.unscaledValue().testBit(0))) {
      bd = bd.stripTrailingZeros();
    }
    return get(bd);
  }
  
  public ValueDecimal modulus(Value v)
  {
    ValueDecimal dec = (ValueDecimal)v;
    if (dec.value.signum() == 0) {
      throw DbException.get(22012, getSQL());
    }
    BigDecimal bd = this.value.remainder(dec.value);
    return get(bd);
  }
  
  public String getSQL()
  {
    return getString();
  }
  
  public int getType()
  {
    return 6;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueDecimal v = (ValueDecimal)o;
    return this.value.compareTo(v.value);
  }
  
  public int getSignum()
  {
    return this.value.signum();
  }
  
  public BigDecimal getBigDecimal()
  {
    return this.value;
  }
  
  public String getString()
  {
    if (this.valueString == null)
    {
      String p = this.value.toPlainString();
      if (p.length() < 40) {
        this.valueString = p;
      } else {
        this.valueString = this.value.toString();
      }
    }
    return this.valueString;
  }
  
  public long getPrecision()
  {
    if (this.precision == 0) {
      this.precision = this.value.precision();
    }
    return this.precision;
  }
  
  public boolean checkPrecision(long prec)
  {
    if (prec == 65535L) {
      return true;
    }
    return getPrecision() <= prec;
  }
  
  public int getScale()
  {
    return this.value.scale();
  }
  
  public int hashCode()
  {
    return this.value.hashCode();
  }
  
  public Object getObject()
  {
    return this.value;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setBigDecimal(parameterIndex, this.value);
  }
  
  public Value convertScale(boolean onlyToSmallerScale, int targetScale)
  {
    if (this.value.scale() == targetScale) {
      return this;
    }
    if (((onlyToSmallerScale) || (targetScale >= 32767)) && 
      (this.value.scale() < targetScale)) {
      return this;
    }
    BigDecimal bd = setScale(this.value, targetScale);
    return get(bd);
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if (getPrecision() <= precision) {
      return this;
    }
    if (force) {
      return get(BigDecimal.valueOf(this.value.doubleValue()));
    }
    throw DbException.get(22003, Long.toString(precision));
  }
  
  public static ValueDecimal get(BigDecimal dec)
  {
    if (BigDecimal.ZERO.equals(dec)) {
      return (ValueDecimal)ZERO;
    }
    if (BigDecimal.ONE.equals(dec)) {
      return (ValueDecimal)ONE;
    }
    return (ValueDecimal)Value.cache(new ValueDecimal(dec));
  }
  
  public int getDisplaySize()
  {
    return MathUtils.convertLongToInt(getPrecision() + 2L);
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueDecimal)) && (this.value.equals(((ValueDecimal)other).value));
  }
  
  public int getMemory()
  {
    return this.value.precision() + 120;
  }
  
  public static BigDecimal setScale(BigDecimal bd, int scale)
  {
    if ((scale > 100000) || (scale < -100000)) {
      throw DbException.getInvalidValueException("scale", Integer.valueOf(scale));
    }
    return bd.setScale(scale, 4);
  }
}
