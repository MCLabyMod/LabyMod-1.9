package org.h2.value;

import java.text.Collator;
import java.util.Locale;
import org.h2.engine.SysProperties;
import org.h2.util.StringUtils;

public class CompareMode
{
  public static final String OFF = "OFF";
  public static final String DEFAULT = "DEFAULT_";
  public static final String ICU4J = "ICU4J_";
  public static final String SIGNED = "SIGNED";
  public static final String UNSIGNED = "UNSIGNED";
  private static CompareMode lastUsed;
  private static final boolean CAN_USE_ICU4J;
  private final String name;
  private final int strength;
  private final boolean binaryUnsigned;
  
  static
  {
    boolean b = false;
    try
    {
      Class.forName("com.ibm.icu.text.Collator");
      b = true;
    }
    catch (Exception e) {}
    CAN_USE_ICU4J = b;
  }
  
  protected CompareMode(String name, int strength, boolean binaryUnsigned)
  {
    this.name = name;
    this.strength = strength;
    this.binaryUnsigned = binaryUnsigned;
  }
  
  public static synchronized CompareMode getInstance(String name, int strength)
  {
    return getInstance(name, strength, SysProperties.SORT_BINARY_UNSIGNED);
  }
  
  public static synchronized CompareMode getInstance(String name, int strength, boolean binaryUnsigned)
  {
    if ((lastUsed != null) && 
      (StringUtils.equals(lastUsed.name, name)) && (lastUsed.strength == strength) && (lastUsed.binaryUnsigned == binaryUnsigned)) {
      return lastUsed;
    }
    if ((name == null) || (name.equals("OFF")))
    {
      lastUsed = new CompareMode(name, strength, binaryUnsigned);
    }
    else
    {
      boolean useICU4J;
      if (name.startsWith("ICU4J_"))
      {
        boolean useICU4J = true;
        name = name.substring("ICU4J_".length());
      }
      else if (name.startsWith("DEFAULT_"))
      {
        boolean useICU4J = false;
        name = name.substring("DEFAULT_".length());
      }
      else
      {
        useICU4J = CAN_USE_ICU4J;
      }
      if (useICU4J) {
        lastUsed = new CompareModeIcu4J(name, strength, binaryUnsigned);
      } else {
        lastUsed = new CompareModeDefault(name, strength, binaryUnsigned);
      }
    }
    return lastUsed;
  }
  
  public boolean equalsChars(String a, int ai, String b, int bi, boolean ignoreCase)
  {
    char ca = a.charAt(ai);
    char cb = b.charAt(bi);
    if (ignoreCase)
    {
      ca = Character.toUpperCase(ca);
      cb = Character.toUpperCase(cb);
    }
    return ca == cb;
  }
  
  public int compareString(String a, String b, boolean ignoreCase)
  {
    if (ignoreCase) {
      return a.compareToIgnoreCase(b);
    }
    return a.compareTo(b);
  }
  
  public static String getName(Locale l)
  {
    Locale english = Locale.ENGLISH;
    String name = l.getDisplayLanguage(english) + ' ' + l.getDisplayCountry(english) + ' ' + l.getVariant();
    
    name = StringUtils.toUpperEnglish(name.trim().replace(' ', '_'));
    return name;
  }
  
  static boolean compareLocaleNames(Locale locale, String name)
  {
    return (name.equalsIgnoreCase(locale.toString())) || (name.equalsIgnoreCase(getName(locale)));
  }
  
  public static Collator getCollator(String name)
  {
    Collator result = null;
    if (name.startsWith("ICU4J_")) {
      name = name.substring("ICU4J_".length());
    } else if (name.startsWith("DEFAULT_")) {
      name = name.substring("DEFAULT_".length());
    }
    if (name.length() == 2)
    {
      Locale locale = new Locale(StringUtils.toLowerEnglish(name), "");
      if (compareLocaleNames(locale, name)) {
        result = Collator.getInstance(locale);
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
          result = Collator.getInstance(locale);
        }
      }
    }
    if (result == null) {
      for (Locale locale : Collator.getAvailableLocales()) {
        if (compareLocaleNames(locale, name))
        {
          result = Collator.getInstance(locale);
          break;
        }
      }
    }
    return result;
  }
  
  public String getName()
  {
    return this.name == null ? "OFF" : this.name;
  }
  
  public int getStrength()
  {
    return this.strength;
  }
  
  public boolean isBinaryUnsigned()
  {
    return this.binaryUnsigned;
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof CompareMode)) {
      return false;
    }
    CompareMode o = (CompareMode)obj;
    if (!getName().equals(o.getName())) {
      return false;
    }
    if (this.strength != o.strength) {
      return false;
    }
    if (this.binaryUnsigned != o.binaryUnsigned) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    return getName().hashCode() ^ this.strength ^ (this.binaryUnsigned ? -1 : 0);
  }
}
