package org.h2.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.h2.message.DbException;
import org.h2.value.Value;
import org.h2.value.ValueDate;
import org.h2.value.ValueNull;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;

public class DateTimeUtils
{
  public static final long MILLIS_PER_DAY = 86400000L;
  private static final long NANOS_PER_DAY = 86400000000000L;
  private static final int SHIFT_YEAR = 9;
  private static final int SHIFT_MONTH = 5;
  private static final int[] NORMAL_DAYS_PER_MONTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  private static final int[] DAYS_OFFSET = { 0, 31, 61, 92, 122, 153, 184, 214, 245, 275, 306, 337, 366 };
  private static final ThreadLocal<Calendar> CACHED_CALENDAR = new ThreadLocal();
  
  public static void resetCalendar()
  {
    CACHED_CALENDAR.remove();
  }
  
  private static Calendar getCalendar()
  {
    Calendar c = (Calendar)CACHED_CALENDAR.get();
    if (c == null)
    {
      c = Calendar.getInstance();
      CACHED_CALENDAR.set(c);
    }
    return c;
  }
  
  public static java.sql.Date convertDate(Value value, Calendar calendar)
  {
    if (value == ValueNull.INSTANCE) {
      return null;
    }
    ValueDate d = (ValueDate)value.convertTo(10);
    Calendar cal = (Calendar)calendar.clone();
    cal.clear();
    cal.setLenient(true);
    long dateValue = d.getDateValue();
    setCalendarFields(cal, yearFromDateValue(dateValue), monthFromDateValue(dateValue), dayFromDateValue(dateValue), 0, 0, 0, 0);
    
    long ms = cal.getTimeInMillis();
    return new java.sql.Date(ms);
  }
  
  public static Time convertTime(Value value, Calendar calendar)
  {
    if (value == ValueNull.INSTANCE) {
      return null;
    }
    ValueTime t = (ValueTime)value.convertTo(9);
    Calendar cal = (Calendar)calendar.clone();
    cal.clear();
    cal.setLenient(true);
    long nanos = t.getNanos();
    long millis = nanos / 1000000L;
    nanos -= millis * 1000000L;
    long s = millis / 1000L;
    millis -= s * 1000L;
    long m = s / 60L;
    s -= m * 60L;
    long h = m / 60L;
    m -= h * 60L;
    setCalendarFields(cal, 1970, 1, 1, (int)h, (int)m, (int)s, (int)millis);
    
    long ms = cal.getTimeInMillis();
    return new Time(ms);
  }
  
  public static Timestamp convertTimestamp(Value value, Calendar calendar)
  {
    if (value == ValueNull.INSTANCE) {
      return null;
    }
    ValueTimestamp ts = (ValueTimestamp)value.convertTo(11);
    Calendar cal = (Calendar)calendar.clone();
    cal.clear();
    cal.setLenient(true);
    long dateValue = ts.getDateValue();
    long nanos = ts.getTimeNanos();
    long millis = nanos / 1000000L;
    nanos -= millis * 1000000L;
    long s = millis / 1000L;
    millis -= s * 1000L;
    long m = s / 60L;
    s -= m * 60L;
    long h = m / 60L;
    m -= h * 60L;
    setCalendarFields(cal, yearFromDateValue(dateValue), monthFromDateValue(dateValue), dayFromDateValue(dateValue), (int)h, (int)m, (int)s, (int)millis);
    
    long ms = cal.getTimeInMillis();
    Timestamp x = new Timestamp(ms);
    x.setNanos((int)(nanos + millis * 1000000L));
    return x;
  }
  
  public static ValueDate convertDate(java.sql.Date x, Calendar calendar)
  {
    if (calendar == null) {
      throw DbException.getInvalidValueException("calendar", null);
    }
    Calendar cal = (Calendar)calendar.clone();
    cal.setTimeInMillis(x.getTime());
    long dateValue = dateValueFromCalendar(cal);
    return ValueDate.fromDateValue(dateValue);
  }
  
  public static ValueTime convertTime(Time x, Calendar calendar)
  {
    if (calendar == null) {
      throw DbException.getInvalidValueException("calendar", null);
    }
    Calendar cal = (Calendar)calendar.clone();
    cal.setTimeInMillis(x.getTime());
    long nanos = nanosFromCalendar(cal);
    return ValueTime.fromNanos(nanos);
  }
  
  public static long convertToLocal(java.util.Date x, Calendar target)
  {
    if (target == null) {
      throw DbException.getInvalidValueException("calendar", null);
    }
    target = (Calendar)target.clone();
    Calendar local = Calendar.getInstance();
    synchronized (local)
    {
      local.setTime(x);
      convertTime(local, target);
    }
    return target.getTime().getTime();
  }
  
  private static void convertTime(Calendar from, Calendar to)
  {
    to.set(0, from.get(0));
    to.set(1, from.get(1));
    to.set(2, from.get(2));
    to.set(5, from.get(5));
    to.set(11, from.get(11));
    to.set(12, from.get(12));
    to.set(13, from.get(13));
    to.set(14, from.get(14));
  }
  
  public static ValueTimestamp convertTimestamp(Timestamp x, Calendar calendar)
  {
    if (calendar == null) {
      throw DbException.getInvalidValueException("calendar", null);
    }
    Calendar cal = (Calendar)calendar.clone();
    cal.setTimeInMillis(x.getTime());
    long dateValue = dateValueFromCalendar(cal);
    long nanos = nanosFromCalendar(cal);
    nanos += x.getNanos() % 1000000;
    return ValueTimestamp.fromDateValueAndNanos(dateValue, nanos);
  }
  
  public static long parseDateValue(String s, int start, int end)
  {
    if (s.charAt(start) == '+') {
      start++;
    }
    int s1 = s.indexOf('-', start + 1);
    int s2 = s.indexOf('-', s1 + 1);
    if ((s1 <= 0) || (s2 <= s1)) {
      throw new IllegalArgumentException(s);
    }
    int year = Integer.parseInt(s.substring(start, s1));
    int month = Integer.parseInt(s.substring(s1 + 1, s2));
    int day = Integer.parseInt(s.substring(s2 + 1, end));
    if (!isValidDate(year, month, day)) {
      throw new IllegalArgumentException(year + "-" + month + "-" + day);
    }
    return dateValue(year, month, day);
  }
  
  public static long parseTimeNanos(String s, int start, int end, boolean timeOfDay)
  {
    int hour = 0;int minute = 0;int second = 0;
    long nanos = 0L;
    int s1 = s.indexOf(':', start);
    int s2 = s.indexOf(':', s1 + 1);
    int s3 = s.indexOf('.', s2 + 1);
    if ((s1 <= 0) || (s2 <= s1)) {
      throw new IllegalArgumentException(s);
    }
    hour = Integer.parseInt(s.substring(start, s1));
    boolean negative;
    if (hour < 0)
    {
      if (timeOfDay) {
        throw new IllegalArgumentException(s);
      }
      boolean negative = true;
      hour = -hour;
    }
    else
    {
      negative = false;
    }
    minute = Integer.parseInt(s.substring(s1 + 1, s2));
    if (s3 < 0)
    {
      second = Integer.parseInt(s.substring(s2 + 1, end));
    }
    else
    {
      second = Integer.parseInt(s.substring(s2 + 1, s3));
      String n = (s.substring(s3 + 1, end) + "000000000").substring(0, 9);
      nanos = Integer.parseInt(n);
    }
    if ((hour >= 2000000) || (minute < 0) || (minute >= 60) || (second < 0) || (second >= 60)) {
      throw new IllegalArgumentException(s);
    }
    if ((timeOfDay) && (hour >= 24)) {
      throw new IllegalArgumentException(s);
    }
    nanos += ((hour * 60L + minute) * 60L + second) * 1000000000L;
    return negative ? -nanos : nanos;
  }
  
  public static long getMillis(TimeZone tz, int year, int month, int day, int hour, int minute, int second, int millis)
  {
    try
    {
      return getTimeTry(false, tz, year, month, day, hour, minute, second, millis);
    }
    catch (IllegalArgumentException e)
    {
      String message = e.toString();
      if (message.indexOf("HOUR_OF_DAY") > 0)
      {
        if ((hour < 0) || (hour > 23)) {
          throw e;
        }
        return getTimeTry(true, tz, year, month, day, hour, minute, second, millis);
      }
      if (message.indexOf("DAY_OF_MONTH") > 0)
      {
        int maxDay;
        int maxDay;
        if (month == 2) {
          maxDay = new GregorianCalendar().isLeapYear(year) ? 29 : 28;
        } else {
          maxDay = 30 + (month + (month > 7 ? 1 : 0) & 0x1);
        }
        if ((day < 1) || (day > maxDay)) {
          throw e;
        }
        hour += 6;
        return getTimeTry(true, tz, year, month, day, hour, minute, second, millis);
      }
    }
    return getTimeTry(true, tz, year, month, day, hour, minute, second, millis);
  }
  
  private static long getTimeTry(boolean lenient, TimeZone tz, int year, int month, int day, int hour, int minute, int second, int millis)
  {
    Calendar c;
    Calendar c;
    if (tz == null) {
      c = getCalendar();
    } else {
      c = Calendar.getInstance(tz);
    }
    c.clear();
    c.setLenient(lenient);
    setCalendarFields(c, year, month, day, hour, minute, second, millis);
    return c.getTime().getTime();
  }
  
  private static void setCalendarFields(Calendar cal, int year, int month, int day, int hour, int minute, int second, int millis)
  {
    if (year <= 0)
    {
      cal.set(0, 0);
      cal.set(1, 1 - year);
    }
    else
    {
      cal.set(0, 1);
      cal.set(1, year);
    }
    cal.set(2, month - 1);
    cal.set(5, day);
    cal.set(11, hour);
    cal.set(12, minute);
    cal.set(13, second);
    cal.set(14, millis);
  }
  
  public static int getDatePart(java.util.Date d, int field)
  {
    Calendar c = getCalendar();
    c.setTime(d);
    if (field == 1) {
      return getYear(c);
    }
    int value = c.get(field);
    if (field == 2) {
      return value + 1;
    }
    return value;
  }
  
  private static int getYear(Calendar calendar)
  {
    int year = calendar.get(1);
    if (calendar.get(0) == 0) {
      year = 1 - year;
    }
    return year;
  }
  
  public static long getTimeLocalWithoutDst(java.util.Date d)
  {
    return d.getTime() + getCalendar().get(15);
  }
  
  public static long getTimeUTCWithoutDst(long millis)
  {
    return millis - getCalendar().get(15);
  }
  
  public static int getIsoDayOfWeek(java.util.Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime());
    int val = cal.get(7) - 1;
    return val == 0 ? 7 : val;
  }
  
  public static int getIsoWeek(java.util.Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(date.getTime());
    c.setFirstDayOfWeek(2);
    c.setMinimalDaysInFirstWeek(4);
    return c.get(3);
  }
  
  public static int getIsoYear(java.util.Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime());
    cal.setFirstDayOfWeek(2);
    cal.setMinimalDaysInFirstWeek(4);
    int year = getYear(cal);
    int month = cal.get(2);
    int week = cal.get(3);
    if ((month == 0) && (week > 51)) {
      year--;
    } else if ((month == 11) && (week == 1)) {
      year++;
    }
    return year;
  }
  
  /* Error */
  public static String formatDateTime(java.util.Date date, String format, String locale, String timeZone)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: aload_3
    //   3: invokestatic 364	org/h2/util/DateTimeUtils:getDateFormat	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/text/SimpleDateFormat;
    //   6: astore 4
    //   8: aload 4
    //   10: dup
    //   11: astore 5
    //   13: monitorenter
    //   14: aload 4
    //   16: aload_0
    //   17: invokevirtual 370	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   20: aload 5
    //   22: monitorexit
    //   23: areturn
    //   24: astore 6
    //   26: aload 5
    //   28: monitorexit
    //   29: aload 6
    //   31: athrow
    // Line number table:
    //   Java source line #552	-> byte code offset #0
    //   Java source line #553	-> byte code offset #8
    //   Java source line #554	-> byte code offset #14
    //   Java source line #555	-> byte code offset #24
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	date	java.util.Date
    //   0	32	1	format	String
    //   0	32	2	locale	String
    //   0	32	3	timeZone	String
    //   6	9	4	dateFormat	SimpleDateFormat
    //   11	16	5	Ljava/lang/Object;	Object
    //   24	6	6	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   14	23	24	finally
    //   24	29	24	finally
  }
  
  /* Error */
  public static java.util.Date parseDateTime(String date, String format, String locale, String timeZone)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: aload_3
    //   3: invokestatic 364	org/h2/util/DateTimeUtils:getDateFormat	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/text/SimpleDateFormat;
    //   6: astore 4
    //   8: aload 4
    //   10: dup
    //   11: astore 5
    //   13: monitorenter
    //   14: aload 4
    //   16: aload_0
    //   17: invokevirtual 382	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   20: aload 5
    //   22: monitorexit
    //   23: areturn
    //   24: astore 6
    //   26: aload 5
    //   28: monitorexit
    //   29: aload 6
    //   31: athrow
    //   32: astore 5
    //   34: ldc_w 383
    //   37: aload 5
    //   39: iconst_1
    //   40: anewarray 231	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: aastore
    //   47: invokestatic 386	org/h2/message/DbException:get	(ILjava/lang/Throwable;[Ljava/lang/String;)Lorg/h2/message/DbException;
    //   50: athrow
    // Line number table:
    //   Java source line #569	-> byte code offset #0
    //   Java source line #571	-> byte code offset #8
    //   Java source line #572	-> byte code offset #14
    //   Java source line #573	-> byte code offset #24
    //   Java source line #574	-> byte code offset #32
    //   Java source line #576	-> byte code offset #34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	date	String
    //   0	51	1	format	String
    //   0	51	2	locale	String
    //   0	51	3	timeZone	String
    //   6	9	4	dateFormat	SimpleDateFormat
    //   11	16	5	Ljava/lang/Object;	Object
    //   32	6	5	e	Exception
    //   24	6	6	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   14	23	24	finally
    //   24	29	24	finally
    //   8	23	32	java/lang/Exception
    //   24	32	32	java/lang/Exception
  }
  
  private static SimpleDateFormat getDateFormat(String format, String locale, String timeZone)
  {
    try
    {
      SimpleDateFormat df;
      SimpleDateFormat df;
      if (locale == null)
      {
        df = new SimpleDateFormat(format);
      }
      else
      {
        Locale l = new Locale(locale);
        df = new SimpleDateFormat(format, l);
      }
      if (timeZone != null) {
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
      }
      return df;
    }
    catch (Exception e)
    {
      throw DbException.get(90014, e, new String[] { format + "/" + locale + "/" + timeZone });
    }
  }
  
  public static boolean isValidDate(int year, int month, int day)
  {
    if ((month < 1) || (month > 12) || (day < 1)) {
      return false;
    }
    if (year > 1582)
    {
      if (month != 2) {
        return day <= NORMAL_DAYS_PER_MONTH[month];
      }
      if ((year & 0x3) != 0) {
        return day <= 28;
      }
      return day <= ((year % 100 != 0) || (year % 400 == 0) ? 29 : 28);
    }
    if ((year == 1582) && (month == 10)) {
      return (day <= 31) && ((day < 5) || (day > 14));
    }
    if ((month != 2) && (day <= NORMAL_DAYS_PER_MONTH[month])) {
      return true;
    }
    return day <= ((year & 0x3) != 0 ? 28 : 29);
  }
  
  public static java.sql.Date convertDateValueToDate(long dateValue)
  {
    long millis = getMillis(null, yearFromDateValue(dateValue), monthFromDateValue(dateValue), dayFromDateValue(dateValue), 0, 0, 0, 0);
    
    return new java.sql.Date(millis);
  }
  
  public static Timestamp convertDateValueToTimestamp(long dateValue, long timeNanos)
  {
    long millis = timeNanos / 1000000L;
    timeNanos -= millis * 1000000L;
    long s = millis / 1000L;
    millis -= s * 1000L;
    long m = s / 60L;
    s -= m * 60L;
    long h = m / 60L;
    m -= h * 60L;
    long ms = getMillis(null, yearFromDateValue(dateValue), monthFromDateValue(dateValue), dayFromDateValue(dateValue), (int)h, (int)m, (int)s, 0);
    
    Timestamp ts = new Timestamp(ms);
    ts.setNanos((int)(timeNanos + millis * 1000000L));
    return ts;
  }
  
  public static Time convertNanoToTime(long nanos)
  {
    long millis = nanos / 1000000L;
    long s = millis / 1000L;
    millis -= s * 1000L;
    long m = s / 60L;
    s -= m * 60L;
    long h = m / 60L;
    m -= h * 60L;
    long ms = getMillis(null, 1970, 1, 1, (int)(h % 24L), (int)m, (int)s, (int)millis);
    
    return new Time(ms);
  }
  
  public static int yearFromDateValue(long x)
  {
    return (int)(x >>> 9);
  }
  
  public static int monthFromDateValue(long x)
  {
    return (int)(x >>> 5) & 0xF;
  }
  
  public static int dayFromDateValue(long x)
  {
    return (int)(x & 0x1F);
  }
  
  public static long dateValue(long year, int month, int day)
  {
    return year << 9 | month << 5 | day;
  }
  
  public static long dateValueFromDate(long ms)
  {
    Calendar cal = getCalendar();
    cal.clear();
    cal.setTimeInMillis(ms);
    return dateValueFromCalendar(cal);
  }
  
  private static long dateValueFromCalendar(Calendar cal)
  {
    int year = getYear(cal);
    int month = cal.get(2) + 1;
    int day = cal.get(5);
    return year << 9 | month << 5 | day;
  }
  
  public static long nanosFromDate(long ms)
  {
    Calendar cal = getCalendar();
    cal.clear();
    cal.setTimeInMillis(ms);
    return nanosFromCalendar(cal);
  }
  
  private static long nanosFromCalendar(Calendar cal)
  {
    int h = cal.get(11);
    int m = cal.get(12);
    int s = cal.get(13);
    int millis = cal.get(14);
    return (((h * 60L + m) * 60L + s) * 1000L + millis) * 1000000L;
  }
  
  public static ValueTimestamp normalizeTimestamp(long absoluteDay, long nanos)
  {
    if ((nanos > 86400000000000L) || (nanos < 0L))
    {
      long d;
      long d;
      if (nanos > 86400000000000L) {
        d = nanos / 86400000000000L;
      } else {
        d = (nanos - 86400000000000L + 1L) / 86400000000000L;
      }
      nanos -= d * 86400000000000L;
      absoluteDay += d;
    }
    return ValueTimestamp.fromDateValueAndNanos(dateValueFromAbsoluteDay(absoluteDay), nanos);
  }
  
  public static long absoluteDayFromDateValue(long dateValue)
  {
    long y = yearFromDateValue(dateValue);
    int m = monthFromDateValue(dateValue);
    int d = dayFromDateValue(dateValue);
    if (m <= 2)
    {
      y -= 1L;
      m += 12;
    }
    long a = (y * 2922L >> 3) + DAYS_OFFSET[(m - 3)] + d - 719484L;
    if ((y <= 1582L) && ((y < 1582L) || (m * 100 + d < 1005))) {
      a += 13L;
    } else if ((y < 1901L) || (y > 2099L)) {
      a += y / 400L - y / 100L + 15L;
    }
    return a;
  }
  
  public static long dateValueFromAbsoluteDay(long absoluteDay)
  {
    long d = absoluteDay + 719468L;
    long y100 = 0L;
    long offset;
    long offset;
    if (d > 578040L)
    {
      long y400 = d / 146097L;
      d -= y400 * 146097L;
      y100 = d / 36524L;
      d -= y100 * 36524L;
      offset = y400 * 400L + y100 * 100L;
    }
    else
    {
      d += 292200000002L;
      offset = -800000000L;
    }
    long y4 = d / 1461L;
    d -= y4 * 1461L;
    long y = d / 365L;
    d -= y * 365L;
    if ((d == 0L) && ((y == 4L) || (y100 == 4L)))
    {
      y -= 1L;
      d += 365L;
    }
    y += offset + y4 * 4L;
    
    int m = ((int)d * 2 + 1) * 5 / 306;
    d -= DAYS_OFFSET[m] - 1;
    if (m >= 10)
    {
      y += 1L;
      m -= 12;
    }
    return dateValue(y, m + 3, (int)d);
  }
}
