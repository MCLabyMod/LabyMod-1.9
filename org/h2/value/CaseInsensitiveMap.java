package org.h2.value;

import java.util.HashMap;
import org.h2.util.StringUtils;

public class CaseInsensitiveMap<V>
  extends HashMap<String, V>
{
  private static final long serialVersionUID = 1L;
  
  public V get(Object key)
  {
    return (V)super.get(toUpper(key));
  }
  
  public V put(String key, V value)
  {
    return (V)super.put(toUpper(key), value);
  }
  
  public boolean containsKey(Object key)
  {
    return super.containsKey(toUpper(key));
  }
  
  public V remove(Object key)
  {
    return (V)super.remove(toUpper(key));
  }
  
  private static String toUpper(Object key)
  {
    return key == null ? null : StringUtils.toUpperEnglish(key.toString());
  }
}
