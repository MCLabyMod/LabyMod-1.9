package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import org.h2.message.DbException;
import org.h2.util.DateTimeUtils;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;

public class ValueTime
  extends Value
{
  public static final int PRECISION = 6;
  static final int DISPLAY_SIZE = 8;
  private final long nanos;
  
  private ValueTime(long nanos)
  {
    this.nanos = nanos;
  }
  
  public static ValueTime fromNanos(long nanos)
  {
    return (ValueTime)Value.cache(new ValueTime(nanos));
  }
  
  public static ValueTime get(Time time)
  {
    return fromNanos(DateTimeUtils.nanosFromDate(time.getTime()));
  }
  
  public static ValueTime fromMillis(long ms)
  {
    return fromNanos(DateTimeUtils.nanosFromDate(ms));
  }
  
  public static ValueTime parse(String s)
  {
    try
    {
      return fromNanos(DateTimeUtils.parseTimeNanos(s, 0, s.length(), false));
    }
    catch (Exception e)
    {
      throw DbException.get(22007, e, new String[] { "TIME", s });
    }
  }
  
  public long getNanos()
  {
    return this.nanos;
  }
  
  public Time getTime()
  {
    return DateTimeUtils.convertNanoToTime(this.nanos);
  }
  
  public int getType()
  {
    return 9;
  }
  
  public String getString()
  {
    StringBuilder buff = new StringBuilder(8);
    appendTime(buff, this.nanos, false);
    return buff.toString();
  }
  
  public String getSQL()
  {
    return "TIME '" + getString() + "'";
  }
  
  public long getPrecision()
  {
    return 6L;
  }
  
  public int getDisplaySize()
  {
    return 8;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    return MathUtils.compareLong(this.nanos, ((ValueTime)o).nanos);
  }
  
  public boolean equals(Object other)
  {
    if (this == other) {
      return true;
    }
    return ((other instanceof ValueTime)) && (this.nanos == ((ValueTime)other).nanos);
  }
  
  public int hashCode()
  {
    return (int)(this.nanos ^ this.nanos >>> 32);
  }
  
  public Object getObject()
  {
    return getTime();
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setTime(parameterIndex, getTime());
  }
  
  public Value add(Value v)
  {
    ValueTime t = (ValueTime)v.convertTo(9);
    return fromNanos(this.nanos + t.getNanos());
  }
  
  public Value subtract(Value v)
  {
    ValueTime t = (ValueTime)v.convertTo(9);
    return fromNanos(this.nanos - t.getNanos());
  }
  
  public Value multiply(Value v)
  {
    return fromNanos((this.nanos * v.getDouble()));
  }
  
  public Value divide(Value v)
  {
    return fromNanos((this.nanos / v.getDouble()));
  }
  
  public int getSignum()
  {
    return Long.signum(this.nanos);
  }
  
  public Value negate()
  {
    return fromNanos(-this.nanos);
  }
  
  static void appendTime(StringBuilder buff, long nanos, boolean alwaysAddMillis)
  {
    if (nanos < 0L)
    {
      buff.append('-');
      nanos = -nanos;
    }
    long ms = nanos / 1000000L;
    nanos -= ms * 1000000L;
    long s = ms / 1000L;
    ms -= s * 1000L;
    long m = s / 60L;
    s -= m * 60L;
    long h = m / 60L;
    m -= h * 60L;
    StringUtils.appendZeroPadded(buff, 2, h);
    buff.append(':');
    StringUtils.appendZeroPadded(buff, 2, m);
    buff.append(':');
    StringUtils.appendZeroPadded(buff, 2, s);
    if ((alwaysAddMillis) || (ms > 0L) || (nanos > 0L))
    {
      buff.append('.');
      int start = buff.length();
      StringUtils.appendZeroPadded(buff, 3, ms);
      if (nanos > 0L) {
        StringUtils.appendZeroPadded(buff, 6, nanos);
      }
      for (int i = buff.length() - 1; i > start; i--)
      {
        if (buff.charAt(i) != '0') {
          break;
        }
        buff.deleteCharAt(i);
      }
    }
  }
}
