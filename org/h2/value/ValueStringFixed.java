package org.h2.value;

import org.h2.engine.SysProperties;
import org.h2.util.StringUtils;

public class ValueStringFixed
  extends ValueString
{
  private static final ValueStringFixed EMPTY = new ValueStringFixed("");
  
  protected ValueStringFixed(String value)
  {
    super(value);
  }
  
  private static String trimRight(String s)
  {
    int endIndex = s.length() - 1;
    int i = endIndex;
    while ((i >= 0) && (s.charAt(i) == ' ')) {
      i--;
    }
    s = i == endIndex ? s : s.substring(0, i + 1);
    return s;
  }
  
  public int getType()
  {
    return 21;
  }
  
  public static ValueStringFixed get(String s)
  {
    s = trimRight(s);
    if (s.length() == 0) {
      return EMPTY;
    }
    ValueStringFixed obj = new ValueStringFixed(StringUtils.cache(s));
    if (s.length() > SysProperties.OBJECT_CACHE_MAX_PER_ELEMENT_SIZE) {
      return obj;
    }
    return (ValueStringFixed)Value.cache(obj);
  }
  
  protected ValueString getNew(String s)
  {
    return get(s);
  }
}
