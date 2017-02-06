package org.h2.value;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import org.h2.message.DbException;
import org.h2.util.DateTimeUtils;
import org.h2.util.MathUtils;

public class ValueTimestamp
  extends Value
{
  public static final int PRECISION = 23;
  static final int DISPLAY_SIZE = 23;
  static final int DEFAULT_SCALE = 10;
  private final long dateValue;
  private final long timeNanos;
  
  private ValueTimestamp(long dateValue, long timeNanos)
  {
    this.dateValue = dateValue;
    if ((timeNanos < 0L) || (timeNanos >= 86400000000000L)) {
      throw new IllegalArgumentException("timeNanos out of range " + timeNanos);
    }
    this.timeNanos = timeNanos;
  }
  
  public static ValueTimestamp fromDateValueAndNanos(long dateValue, long timeNanos)
  {
    return (ValueTimestamp)Value.cache(new ValueTimestamp(dateValue, timeNanos));
  }
  
  public static ValueTimestamp get(Timestamp timestamp)
  {
    long ms = timestamp.getTime();
    long nanos = timestamp.getNanos() % 1000000;
    long dateValue = DateTimeUtils.dateValueFromDate(ms);
    nanos += DateTimeUtils.nanosFromDate(ms);
    return fromDateValueAndNanos(dateValue, nanos);
  }
  
  public static ValueTimestamp fromMillisNanos(long ms, int nanos)
  {
    long dateValue = DateTimeUtils.dateValueFromDate(ms);
    long timeNanos = nanos + DateTimeUtils.nanosFromDate(ms);
    return fromDateValueAndNanos(dateValue, timeNanos);
  }
  
  public static ValueTimestamp fromMillis(long ms)
  {
    long dateValue = DateTimeUtils.dateValueFromDate(ms);
    long nanos = DateTimeUtils.nanosFromDate(ms);
    return fromDateValueAndNanos(dateValue, nanos);
  }
  
  public static ValueTimestamp parse(String s)
  {
    try
    {
      return parseTry(s);
    }
    catch (Exception e)
    {
      throw DbException.get(22007, e, new String[] { "TIMESTAMP", s });
    }
  }
  
  private static ValueTimestamp parseTry(String s)
  {
    int dateEnd = s.indexOf(' ');
    if (dateEnd < 0) {
      dateEnd = s.indexOf('T');
    }
    int timeStart;
    int timeStart;
    if (dateEnd < 0)
    {
      dateEnd = s.length();
      timeStart = -1;
    }
    else
    {
      timeStart = dateEnd + 1;
    }
    long dateValue = DateTimeUtils.parseDateValue(s, 0, dateEnd);
    long nanos;
    long nanos;
    if (timeStart < 0)
    {
      nanos = 0L;
    }
    else
    {
      int timeEnd = s.length();
      TimeZone tz = null;
      if (s.endsWith("Z"))
      {
        tz = TimeZone.getTimeZone("UTC");
        timeEnd--;
      }
      else
      {
        int timeZoneStart = s.indexOf('+', dateEnd);
        if (timeZoneStart < 0) {
          timeZoneStart = s.indexOf('-', dateEnd);
        }
        if (timeZoneStart >= 0)
        {
          String tzName = "GMT" + s.substring(timeZoneStart);
          tz = TimeZone.getTimeZone(tzName);
          if (!tz.getID().startsWith(tzName)) {
            throw new IllegalArgumentException(tzName + " (" + tz.getID() + "?)");
          }
          timeEnd = timeZoneStart;
        }
        else
        {
          timeZoneStart = s.indexOf(' ', dateEnd + 1);
          if (timeZoneStart > 0)
          {
            String tzName = s.substring(timeZoneStart + 1);
            tz = TimeZone.getTimeZone(tzName);
            if (!tz.getID().startsWith(tzName)) {
              throw new IllegalArgumentException(tzName);
            }
            timeEnd = timeZoneStart;
          }
        }
      }
      nanos = DateTimeUtils.parseTimeNanos(s, dateEnd + 1, timeEnd, true);
      if (tz != null)
      {
        int year = DateTimeUtils.yearFromDateValue(dateValue);
        int month = DateTimeUtils.monthFromDateValue(dateValue);
        int day = DateTimeUtils.dayFromDateValue(dateValue);
        long ms = nanos / 1000000L;
        nanos -= ms * 1000000L;
        long second = ms / 1000L;
        ms -= second * 1000L;
        int minute = (int)(second / 60L);
        second -= minute * 60;
        int hour = minute / 60;
        minute -= hour * 60;
        long millis = DateTimeUtils.getMillis(tz, year, month, day, hour, minute, (int)second, (int)ms);
        
        ms = DateTimeUtils.convertToLocal(new Date(millis), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
        
        long md = 86400000L;
        long absoluteDay = (ms >= 0L ? ms : ms - md + 1L) / md;
        dateValue = DateTimeUtils.dateValueFromAbsoluteDay(absoluteDay);
        ms -= absoluteDay * md;
        nanos += ms * 1000000L;
      }
    }
    return fromDateValueAndNanos(dateValue, nanos);
  }
  
  public long getDateValue()
  {
    return this.dateValue;
  }
  
  public long getTimeNanos()
  {
    return this.timeNanos;
  }
  
  public Timestamp getTimestamp()
  {
    return DateTimeUtils.convertDateValueToTimestamp(this.dateValue, this.timeNanos);
  }
  
  public int getType()
  {
    return 11;
  }
  
  public String getString()
  {
    StringBuilder buff = new StringBuilder(23);
    ValueDate.appendDate(buff, this.dateValue);
    buff.append(' ');
    ValueTime.appendTime(buff, this.timeNanos, true);
    return buff.toString();
  }
  
  public String getSQL()
  {
    return "TIMESTAMP '" + getString() + "'";
  }
  
  public long getPrecision()
  {
    return 23L;
  }
  
  public int getScale()
  {
    return 10;
  }
  
  public int getDisplaySize()
  {
    return 23;
  }
  
  public Value convertScale(boolean onlyToSmallerScale, int targetScale)
  {
    if (targetScale >= 10) {
      return this;
    }
    if (targetScale < 0) {
      throw DbException.getInvalidValueException("scale", Integer.valueOf(targetScale));
    }
    long n = this.timeNanos;
    BigDecimal bd = BigDecimal.valueOf(n);
    bd = bd.movePointLeft(9);
    bd = ValueDecimal.setScale(bd, targetScale);
    bd = bd.movePointRight(9);
    long n2 = bd.longValue();
    if (n2 == n) {
      return this;
    }
    return fromDateValueAndNanos(this.dateValue, n2);
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueTimestamp t = (ValueTimestamp)o;
    int c = MathUtils.compareLong(this.dateValue, t.dateValue);
    if (c != 0) {
      return c;
    }
    return MathUtils.compareLong(this.timeNanos, t.timeNanos);
  }
  
  public boolean equals(Object other)
  {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ValueTimestamp)) {
      return false;
    }
    ValueTimestamp x = (ValueTimestamp)other;
    return (this.dateValue == x.dateValue) && (this.timeNanos == x.timeNanos);
  }
  
  public int hashCode()
  {
    return (int)(this.dateValue ^ this.dateValue >>> 32 ^ this.timeNanos ^ this.timeNanos >>> 32);
  }
  
  public Object getObject()
  {
    return getTimestamp();
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setTimestamp(parameterIndex, getTimestamp());
  }
  
  public Value add(Value v)
  {
    ValueTimestamp t = (ValueTimestamp)v.convertTo(11);
    long d1 = DateTimeUtils.absoluteDayFromDateValue(this.dateValue);
    long d2 = DateTimeUtils.absoluteDayFromDateValue(t.dateValue);
    return DateTimeUtils.normalizeTimestamp(d1 + d2, this.timeNanos + t.timeNanos);
  }
  
  public Value subtract(Value v)
  {
    ValueTimestamp t = (ValueTimestamp)v.convertTo(11);
    long d1 = DateTimeUtils.absoluteDayFromDateValue(this.dateValue);
    long d2 = DateTimeUtils.absoluteDayFromDateValue(t.dateValue);
    return DateTimeUtils.normalizeTimestamp(d1 - d2, this.timeNanos - t.timeNanos);
  }
}
