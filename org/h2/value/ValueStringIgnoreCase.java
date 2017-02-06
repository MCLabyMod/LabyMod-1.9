package org.h2.value;

import org.h2.engine.SysProperties;
import org.h2.util.StringUtils;

public class ValueStringIgnoreCase
  extends ValueString
{
  private static final ValueStringIgnoreCase EMPTY = new ValueStringIgnoreCase("");
  private int hash;
  
  protected ValueStringIgnoreCase(String value)
  {
    super(value);
  }
  
  public int getType()
  {
    return 14;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueStringIgnoreCase v = (ValueStringIgnoreCase)o;
    return mode.compareString(this.value, v.value, true);
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueString)) && (this.value.equalsIgnoreCase(((ValueString)other).value));
  }
  
  public int hashCode()
  {
    if (this.hash == 0) {
      this.hash = this.value.toUpperCase().hashCode();
    }
    return this.hash;
  }
  
  public String getSQL()
  {
    return "CAST(" + StringUtils.quoteStringSQL(this.value) + " AS VARCHAR_IGNORECASE)";
  }
  
  public static ValueStringIgnoreCase get(String s)
  {
    if (s.length() == 0) {
      return EMPTY;
    }
    ValueStringIgnoreCase obj = new ValueStringIgnoreCase(StringUtils.cache(s));
    if (s.length() > SysProperties.OBJECT_CACHE_MAX_PER_ELEMENT_SIZE) {
      return obj;
    }
    ValueStringIgnoreCase cache = (ValueStringIgnoreCase)Value.cache(obj);
    if (cache.value.equals(s)) {
      return cache;
    }
    return obj;
  }
  
  protected ValueString getNew(String s)
  {
    return get(s);
  }
}
