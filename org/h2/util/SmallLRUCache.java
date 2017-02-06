package org.h2.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class SmallLRUCache<K, V>
  extends LinkedHashMap<K, V>
{
  private static final long serialVersionUID = 1L;
  private int size;
  
  private SmallLRUCache(int size)
  {
    super(size, 0.75F, true);
    this.size = size;
  }
  
  public static <K, V> SmallLRUCache<K, V> newInstance(int size)
  {
    return new SmallLRUCache(size);
  }
  
  public void setMaxSize(int size)
  {
    this.size = size;
  }
  
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
  {
    return size() > this.size;
  }
}
