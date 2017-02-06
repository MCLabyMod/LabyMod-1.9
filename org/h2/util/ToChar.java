package org.h2.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.h2.message.DbException;

public class ToChar
{
  private static final long JULIAN_EPOCH;
  
  static
  {
    GregorianCalendar epoch = new GregorianCalendar(Locale.ENGLISH);
    epoch.setGregorianChange(new java.sql.Date(Long.MAX_VALUE));
    epoch.clear();
    epoch.set(4713, 0, 1, 0, 0, 0);
    epoch.set(0, 0);
    JULIAN_EPOCH = epoch.getTimeInMillis();
  }
  
  public static String toChar(BigDecimal number, String format, String nlsParam)
  {
    String formatUp = format != null ? format.toUpperCase() : null;
    if ((formatUp == null) || (formatUp.equals("TM")) || (formatUp.equals("TM9")))
    {
      String s = number.toPlainString();
      return s.startsWith("0.") ? s.substring(1) : s;
    }
    if (formatUp.equals("TME"))
    {
      int pow = number.precision() - number.scale() - 1;
      number = number.movePointLeft(pow);
      return number.toPlainString() + "E" + (pow < 0 ? '-' : '+') + (Math.abs(pow) < 10 ? "0" : "") + Math.abs(pow);
    }
    if (formatUp.equals("RN"))
    {
      boolean lowercase = format.startsWith("r");
      String rn = StringUtils.pad(toRomanNumeral(number.intValue()), 15, " ", false);
      return lowercase ? rn.toLowerCase() : rn;
    }
    if (formatUp.equals("FMRN"))
    {
      boolean lowercase = format.charAt(2) == 'r';
      String rn = toRomanNumeral(number.intValue());
      return lowercase ? rn.toLowerCase() : rn;
    }
    if (formatUp.endsWith("X")) {
      return toHex(number, format);
    }
    String originalFormat = format;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    char localGrouping = symbols.getGroupingSeparator();
    char localDecimal = symbols.getDecimalSeparator();
    
    boolean leadingSign = formatUp.startsWith("S");
    if (leadingSign) {
      format = format.substring(1);
    }
    boolean trailingSign = formatUp.endsWith("S");
    if (trailingSign) {
      format = format.substring(0, format.length() - 1);
    }
    boolean trailingMinus = formatUp.endsWith("MI");
    if (trailingMinus) {
      format = format.substring(0, format.length() - 2);
    }
    boolean angleBrackets = formatUp.endsWith("PR");
    if (angleBrackets) {
      format = format.substring(0, format.length() - 2);
    }
    int v = formatUp.indexOf("V");
    if (v >= 0)
    {
      int digits = 0;
      for (int i = v + 1; i < format.length(); i++)
      {
        char c = format.charAt(i);
        if ((c == '0') || (c == '9')) {
          digits++;
        }
      }
      number = number.movePointRight(digits);
      format = format.substring(0, v) + format.substring(v + 1);
    }
    Integer power;
    if (format.endsWith("EEEE"))
    {
      Integer power = Integer.valueOf(number.precision() - number.scale() - 1);
      number = number.movePointLeft(power.intValue());
      format = format.substring(0, format.length() - 4);
    }
    else
    {
      power = null;
    }
    int maxLength = 1;
    boolean fillMode = !formatUp.startsWith("FM");
    if (!fillMode) {
      format = format.substring(2);
    }
    format = format.replaceAll("[Bb]", "");
    
    int separator = findDecimalSeparator(format);
    int formatScale = calculateScale(format, separator);
    if (formatScale < number.scale()) {
      number = number.setScale(formatScale, 4);
    }
    for (int i = format.indexOf('0'); (i >= 0) && (i < separator); i++) {
      if (format.charAt(i) == '9') {
        format = format.substring(0, i) + "0" + format.substring(i + 1);
      }
    }
    StringBuilder output = new StringBuilder();
    String unscaled = number.unscaledValue().abs().toString();
    
    int i = separator - 1;
    int j = unscaled.length() - number.scale() - 1;
    for (; i >= 0; i--)
    {
      char c = format.charAt(i);
      maxLength++;
      if ((c == '9') || (c == '0'))
      {
        if (j >= 0)
        {
          char digit = unscaled.charAt(j);
          output.insert(0, digit);
          j--;
        }
        else if ((c == '0') && (power == null))
        {
          output.insert(0, '0');
        }
      }
      else if (c == ',')
      {
        if ((j >= 0) || ((i > 0) && (format.charAt(i - 1) == '0'))) {
          output.insert(0, c);
        }
      }
      else if ((c == 'G') || (c == 'g'))
      {
        if ((j >= 0) || ((i > 0) && (format.charAt(i - 1) == '0'))) {
          output.insert(0, localGrouping);
        }
      }
      else if ((c == 'C') || (c == 'c'))
      {
        Currency currency = Currency.getInstance(Locale.getDefault());
        output.insert(0, currency.getCurrencyCode());
        maxLength += 6;
      }
      else if ((c == 'L') || (c == 'l') || (c == 'U') || (c == 'u'))
      {
        Currency currency = Currency.getInstance(Locale.getDefault());
        output.insert(0, currency.getSymbol());
        maxLength += 9;
      }
      else if (c == '$')
      {
        Currency currency = Currency.getInstance(Locale.getDefault());
        String cs = currency.getSymbol();
        output.insert(0, cs);
      }
      else
      {
        throw DbException.get(90010, originalFormat);
      }
    }
    if (j >= 0) {
      return StringUtils.pad("", format.length() + 1, "#", true);
    }
    if (separator < format.length())
    {
      maxLength++;
      char pt = format.charAt(separator);
      if ((pt == 'd') || (pt == 'D')) {
        output.append(localDecimal);
      } else {
        output.append(pt);
      }
      i = separator + 1;
      j = unscaled.length() - number.scale();
      for (; i < format.length(); i++)
      {
        char c = format.charAt(i);
        maxLength++;
        if ((c == '9') || (c == '0'))
        {
          if (j < unscaled.length())
          {
            char digit = unscaled.charAt(j);
            output.append(digit);
            j++;
          }
          else if ((c == '0') || (fillMode))
          {
            output.append('0');
          }
        }
        else {
          throw DbException.get(90010, originalFormat);
        }
      }
    }
    addSign(output, number.signum(), leadingSign, trailingSign, trailingMinus, angleBrackets, fillMode);
    if (power != null)
    {
      output.append('E');
      output.append(power.intValue() < 0 ? '-' : '+');
      output.append(Math.abs(power.intValue()) < 10 ? "0" : "");
      output.append(Math.abs(power.intValue()));
    }
    if (fillMode) {
      if (power != null) {
        output.insert(0, ' ');
      } else {
        while (output.length() < maxLength) {
          output.insert(0, ' ');
        }
      }
    }
    return output.toString();
  }
  
  private static void addSign(StringBuilder output, int signum, boolean leadingSign, boolean trailingSign, boolean trailingMinus, boolean angleBrackets, boolean fillMode)
  {
    if (angleBrackets)
    {
      if (signum < 0)
      {
        output.insert(0, '<');
        output.append('>');
      }
      else if (fillMode)
      {
        output.insert(0, ' ');
        output.append(' ');
      }
    }
    else
    {
      String sign;
      String sign;
      if (signum == 0)
      {
        sign = "";
      }
      else
      {
        String sign;
        if (signum < 0)
        {
          sign = "-";
        }
        else
        {
          String sign;
          if ((leadingSign) || (trailingSign))
          {
            sign = "+";
          }
          else
          {
            String sign;
            if (fillMode) {
              sign = " ";
            } else {
              sign = "";
            }
          }
        }
      }
      if ((trailingMinus) || (trailingSign)) {
        output.append(sign);
      } else {
        output.insert(0, sign);
      }
    }
  }
  
  private static int findDecimalSeparator(String format)
  {
    int index = format.indexOf('.');
    if (index == -1)
    {
      index = format.indexOf('D');
      if (index == -1)
      {
        index = format.indexOf('d');
        if (index == -1) {
          index = format.length();
        }
      }
    }
    return index;
  }
  
  private static int calculateScale(String format, int separator)
  {
    int scale = 0;
    for (int i = separator; i < format.length(); i++)
    {
      char c = format.charAt(i);
      if ((c == '0') || (c == '9')) {
        scale++;
      }
    }
    return scale;
  }
  
  private static String toRomanNumeral(int number)
  {
    int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    
    String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < values.length; i++)
    {
      int value = values[i];
      String numeral = numerals[i];
      while (number >= value)
      {
        result.append(numeral);
        number -= value;
      }
    }
    return result.toString();
  }
  
  private static String toHex(BigDecimal number, String format)
  {
    boolean fillMode = !format.toUpperCase().startsWith("FM");
    boolean uppercase = !format.contains("x");
    boolean zeroPadded = format.startsWith("0");
    int digits = 0;
    for (int i = 0; i < format.length(); i++)
    {
      char c = format.charAt(i);
      if ((c == '0') || (c == 'X') || (c == 'x')) {
        digits++;
      }
    }
    int i = number.setScale(0, 4).intValue();
    String hex = Integer.toHexString(i);
    if (digits < hex.length())
    {
      hex = StringUtils.pad("", digits + 1, "#", true);
    }
    else
    {
      if (uppercase) {
        hex = hex.toUpperCase();
      }
      if (zeroPadded) {
        hex = StringUtils.pad(hex, digits, "0", false);
      }
      if (fillMode) {
        hex = StringUtils.pad(hex, format.length() + 1, " ", false);
      }
    }
    return hex;
  }
  
  public static String toChar(Timestamp ts, String format, String nlsParam)
  {
    if (format == null) {
      format = "DD-MON-YY HH.MI.SS.FF PM";
    }
    GregorianCalendar cal = new GregorianCalendar(Locale.ENGLISH);
    cal.setTimeInMillis(ts.getTime());
    StringBuilder output = new StringBuilder();
    boolean fillMode = true;
    for (int i = 0; i < format.length();)
    {
      Capitalization cap;
      if ((cap = containsAt(format, i, new String[] { "A.D.", "B.C." })) != null)
      {
        String era = cal.get(0) == 1 ? "A.D." : "B.C.";
        output.append(cap.apply(era));
        i += 4;
      }
      else if ((cap = containsAt(format, i, new String[] { "AD", "BC" })) != null)
      {
        String era = cal.get(0) == 1 ? "AD" : "BC";
        output.append(cap.apply(era));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "A.M.", "P.M." })) != null)
      {
        String am = cal.get(9) == 0 ? "A.M." : "P.M.";
        output.append(cap.apply(am));
        i += 4;
      }
      else if ((cap = containsAt(format, i, new String[] { "AM", "PM" })) != null)
      {
        String am = cal.get(9) == 0 ? "AM" : "PM";
        output.append(cap.apply(am));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "DL" })) != null)
      {
        output.append(new SimpleDateFormat("EEEE, MMMM d, yyyy").format(ts));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "DS" })) != null)
      {
        output.append(new SimpleDateFormat("MM/dd/yyyy").format(ts));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "TS" })) != null)
      {
        output.append(new SimpleDateFormat("h:mm:ss aa").format(ts));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "DDD" })) != null)
      {
        output.append(cal.get(6));
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "DD" })) != null)
      {
        output.append(String.format("%02d", new Object[] { Integer.valueOf(cal.get(5)) }));
        
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "DY" })) != null)
      {
        String day = new SimpleDateFormat("EEE").format(ts).toUpperCase();
        output.append(cap.apply(day));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "DAY" })) != null)
      {
        String day = new SimpleDateFormat("EEEE").format(ts);
        if (fillMode) {
          day = StringUtils.pad(day, "Wednesday".length(), " ", true);
        }
        output.append(cap.apply(day));
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "D" })) != null)
      {
        output.append(cal.get(7));
        i++;
      }
      else if ((cap = containsAt(format, i, new String[] { "J" })) != null)
      {
        long millis = ts.getTime() - JULIAN_EPOCH;
        long days = Math.floor(millis / 86400000L);
        output.append(days);
        i++;
      }
      else if ((cap = containsAt(format, i, new String[] { "HH24" })) != null)
      {
        output.append(new DecimalFormat("00").format(cal.get(11)));
        i += 4;
      }
      else if ((cap = containsAt(format, i, new String[] { "HH12" })) != null)
      {
        output.append(new DecimalFormat("00").format(cal.get(10)));
        i += 4;
      }
      else if ((cap = containsAt(format, i, new String[] { "HH" })) != null)
      {
        output.append(new DecimalFormat("00").format(cal.get(10)));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "MI" })) != null)
      {
        output.append(new DecimalFormat("00").format(cal.get(12)));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "SSSSS" })) != null)
      {
        int seconds = cal.get(11) * 60 * 60;
        seconds += cal.get(12) * 60;
        seconds += cal.get(13);
        output.append(seconds);
        i += 5;
      }
      else if ((cap = containsAt(format, i, new String[] { "SS" })) != null)
      {
        output.append(new DecimalFormat("00").format(cal.get(13)));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "FF1", "FF2", "FF3", "FF4", "FF5", "FF6", "FF7", "FF8", "FF9" })) != null)
      {
        int x = Integer.parseInt(format.substring(i + 2, i + 3));
        int ff = (int)(cal.get(14) * Math.pow(10.0D, x - 3));
        output.append(ff);
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "FF" })) != null)
      {
        output.append(cal.get(14) * 1000);
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "TZR" })) != null)
      {
        TimeZone tz = TimeZone.getDefault();
        output.append(tz.getID());
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "TZD" })) != null)
      {
        TimeZone tz = TimeZone.getDefault();
        boolean daylight = tz.inDaylightTime(new java.util.Date());
        output.append(tz.getDisplayName(daylight, 0));
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "IW", "WW" })) != null)
      {
        output.append(cal.get(3));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "W" })) != null)
      {
        int w = (int)(1.0D + Math.floor(cal.get(5) / 7));
        output.append(w);
        i++;
      }
      else if ((cap = containsAt(format, i, new String[] { "Y,YYY" })) != null)
      {
        output.append(new DecimalFormat("#,###").format(getYear(cal)));
        i += 5;
      }
      else if ((cap = containsAt(format, i, new String[] { "SYYYY" })) != null)
      {
        if (cal.get(0) == 0) {
          output.append('-');
        }
        output.append(new DecimalFormat("0000").format(getYear(cal)));
        i += 5;
      }
      else if ((cap = containsAt(format, i, new String[] { "YYYY", "IYYY", "RRRR" })) != null)
      {
        output.append(new DecimalFormat("0000").format(getYear(cal)));
        i += 4;
      }
      else if ((cap = containsAt(format, i, new String[] { "YYY", "IYY" })) != null)
      {
        output.append(new DecimalFormat("000").format(getYear(cal) % 1000));
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "YY", "IY", "RR" })) != null)
      {
        output.append(new DecimalFormat("00").format(getYear(cal) % 100));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "I", "Y" })) != null)
      {
        output.append(getYear(cal) % 10);
        i++;
      }
      else if ((cap = containsAt(format, i, new String[] { "MONTH" })) != null)
      {
        String month = new SimpleDateFormat("MMMM").format(ts);
        if (fillMode) {
          month = StringUtils.pad(month, "September".length(), " ", true);
        }
        output.append(cap.apply(month));
        i += 5;
      }
      else if ((cap = containsAt(format, i, new String[] { "MON" })) != null)
      {
        String month = new SimpleDateFormat("MMM").format(ts);
        output.append(cap.apply(month));
        i += 3;
      }
      else if ((cap = containsAt(format, i, new String[] { "MM" })) != null)
      {
        output.append(String.format("%02d", new Object[] { Integer.valueOf(cal.get(2) + 1) }));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "RM" })) != null)
      {
        int month = cal.get(2) + 1;
        output.append(cap.apply(toRomanNumeral(month)));
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "Q" })) != null)
      {
        int q = (int)(1.0D + Math.floor(cal.get(2) / 3));
        output.append(q);
        i++;
      }
      else if ((cap = containsAt(format, i, new String[] { "X" })) != null)
      {
        char c = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        output.append(c);
        i++;
      }
      else if ((cap = containsAt(format, i, new String[] { "FM" })) != null)
      {
        fillMode = !fillMode;
        i += 2;
      }
      else if ((cap = containsAt(format, i, new String[] { "FX" })) != null)
      {
        i += 2;
      }
      else
      {
        if ((cap = containsAt(format, i, new String[] { "\"" })) != null) {
          for (i += 1; i < format.length(); i++)
          {
            char c = format.charAt(i);
            if (c != '"')
            {
              output.append(c);
            }
            else
            {
              i++;
              break;
            }
          }
        }
        if ((format.charAt(i) == '-') || (format.charAt(i) == '/') || (format.charAt(i) == ',') || (format.charAt(i) == '.') || (format.charAt(i) == ';') || (format.charAt(i) == ':') || (format.charAt(i) == ' '))
        {
          output.append(format.charAt(i));
          i++;
        }
        else
        {
          throw DbException.get(90010, format);
        }
      }
    }
    return output.toString();
  }
  
  private static int getYear(Calendar cal)
  {
    int year = cal.get(1);
    if (cal.get(0) == 0) {
      year--;
    }
    return year;
  }
  
  private static Capitalization containsAt(String s, int index, String... substrings)
  {
    for (String substring : substrings) {
      if (index + substring.length() <= s.length())
      {
        boolean found = true;
        Boolean up1 = null;
        Boolean up2 = null;
        for (int i = 0; i < substring.length(); i++)
        {
          char c1 = s.charAt(index + i);
          char c2 = substring.charAt(i);
          if ((c1 != c2) && (Character.toUpperCase(c1) != Character.toUpperCase(c2)))
          {
            found = false;
            break;
          }
          if (Character.isLetter(c1)) {
            if (up1 == null) {
              up1 = Boolean.valueOf(Character.isUpperCase(c1));
            } else if (up2 == null) {
              up2 = Boolean.valueOf(Character.isUpperCase(c1));
            }
          }
        }
        if (found) {
          return Capitalization.toCapitalization(up1, up2);
        }
      }
    }
    return null;
  }
  
  private static enum Capitalization
  {
    UPPERCASE,  LOWERCASE,  CAPITALIZE;
    
    private Capitalization() {}
    
    public static Capitalization toCapitalization(Boolean up1, Boolean up2)
    {
      if (up1 == null) {
        return CAPITALIZE;
      }
      if (up2 == null) {
        return up1.booleanValue() ? UPPERCASE : LOWERCASE;
      }
      if (up1.booleanValue()) {
        return up2.booleanValue() ? UPPERCASE : CAPITALIZE;
      }
      return LOWERCASE;
    }
    
    public String apply(String s)
    {
      if ((s == null) || (s.isEmpty())) {
        return s;
      }
      switch (ToChar.1.$SwitchMap$org$h2$util$ToChar$Capitalization[ordinal()])
      {
      case 1: 
        return s.toUpperCase();
      case 2: 
        return s.toLowerCase();
      case 3: 
        return Character.toUpperCase(s.charAt(0)) + (s.length() > 1 ? s.toLowerCase().substring(1) : "");
      }
      throw new IllegalArgumentException("Unknown capitalization strategy: " + this);
    }
  }
}
