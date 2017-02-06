package org.h2.value;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Locale;
import org.h2.message.DbException;
import org.h2.util.JdbcUtils;
import org.h2.util.StringUtils;

public class CompareModeIcu4J
  extends CompareMode
{
  private final Comparator<String> collator;
  
  protected CompareModeIcu4J(String name, int strength, boolean binaryUnsigned)
  {
    super(name, strength, binaryUnsigned);
    this.collator = getIcu4jCollator(name, strength);
  }
  
  public int compareString(String a, String b, boolean ignoreCase)
  {
    if (ignoreCase)
    {
      a = a.toUpperCase();
      b = b.toUpperCase();
    }
    return this.collator.compare(a, b);
  }
  
  public boolean equalsChars(String a, int ai, String b, int bi, boolean ignoreCase)
  {
    return compareString(a.substring(ai, ai + 1), b.substring(bi, bi + 1), ignoreCase) == 0;
  }
  
  private static Comparator<String> getIcu4jCollator(String name, int strength)
  {
    try
    {
      Comparator<String> result = null;
      Class<?> collatorClass = JdbcUtils.loadUserClass("com.ibm.icu.text.Collator");
      
      Method getInstanceMethod = collatorClass.getMethod("getInstance", new Class[] { Locale.class });
      if (name.length() == 2)
      {
        Locale locale = new Locale(StringUtils.toLowerEnglish(name), "");
        if (compareLocaleNames(locale, name)) {
          result = (Comparator)getInstanceMethod.invoke(null, new Object[] { locale });
        }
      }
      else if (name.length() == 5)
      {
        int idx = name.indexOf('_');
        if (idx >= 0)
        {
          String language = StringUtils.toLowerEnglish(name.substring(0, idx));
          String country = name.substring(idx + 1);
          Locale locale = new Locale(language, country);
          if (compareLocaleNames(locale, name)) {
            result = (Comparator)getInstanceMethod.invoke(null, new Object[] { locale });
          }
        }
      }
      if (result == null) {
        for (Locale locale : (Locale[])collatorClass.getMethod("getAvailableLocales", new Class[0]).invoke(null, new Object[0])) {
          if (compareLocaleNames(locale, name))
          {
            result = (Comparator)getInstanceMethod.invoke(null, new Object[] { locale });
            break;
          }
        }
      }
      if (result == null) {
        throw DbException.getInvalidValueException("collator", name);
      }
      collatorClass.getMethod("setStrength", new Class[] { Integer.TYPE }).invoke(result, new Object[] { Integer.valueOf(strength) });
      return result;
    }
    catch (Exception e)
    {
      throw DbException.convert(e);
    }
  }
}
