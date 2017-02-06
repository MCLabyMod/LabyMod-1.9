package org.h2.value;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.util.DateTimeUtils;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;

public class ValueDate
  extends Value
{
  public static final int PRECISION = 8;
  public static final int DISPLAY_SIZE = 10;
  private final long dateValue;
  
  private ValueDate(long dateValue)
  {
    this.dateValue = dateValue;
  }
  
  public static ValueDate fromDateValue(long dateValue)
  {
    return (ValueDate)Value.cache(new ValueDate(dateValue));
  }
  
  public static ValueDate get(Date date)
  {
    return fromDateValue(DateTimeUtils.dateValueFromDate(date.getTime()));
  }
  
  public static ValueDate fromMillis(long ms)
  {
    return fromDateValue(DateTimeUtils.dateValueFromDate(ms));
  }
  
  public static ValueDate parse(String s)
  {
    try
    {
      return fromDateValue(DateTimeUtils.parseDateValue(s, 0, s.length()));
    }
    catch (Exception e)
    {
      throw DbException.get(22007, e, new String[] { "DATE", s });
    }
  }
  
  public long getDateValue()
  {
    return this.dateValue;
  }
  
  public Date getDate()
  {
    return DateTimeUtils.convertDateValueToDate(this.dateValue);
  }
  
  public int getType()
  {
    return 10;
  }
  
  public String getString()
  {
    StringBuilder buff = new StringBuilder(10);
    appendDate(buff, this.dateValue);
    return buff.toString();
  }
  
  public String getSQL()
  {
    return "DATE '" + getString() + "'";
  }
  
  public long getPrecision()
  {
    return 8L;
  }
  
  public int getDisplaySize()
  {
    return 10;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    return MathUtils.compareLong(this.dateValue, ((ValueDate)o).dateValue);
  }
  
  public boolean equals(Object other)
  {
    if (this == other) {
      return true;
    }
    return ((other instanceof ValueDate)) && (this.dateValue == ((ValueDate)other).dateValue);
  }
  
  public int hashCode()
  {
    return (int)(this.dateValue ^ this.dateValue >>> 32);
  }
  
  public Object getObject()
  {
    return getDate();
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setDate(parameterIndex, getDate());
  }
  
  static void appendDate(StringBuilder buff, long dateValue)
  {
    int y = DateTimeUtils.yearFromDateValue(dateValue);
    int m = DateTimeUtils.monthFromDateValue(dateValue);
    int d = DateTimeUtils.dayFromDateValue(dateValue);
    if ((y > 0) && (y < 10000)) {
      StringUtils.appendZeroPadded(buff, 4, y);
    } else {
      buff.append(y);
    }
    buff.append('-');
    StringUtils.appendZeroPadded(buff, 2, m);
    buff.append('-');
    StringUtils.appendZeroPadded(buff, 2, d);
  }
}
