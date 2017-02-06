package org.h2.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SoftHashMap<K, V>
  extends AbstractMap<K, V>
{
  private final Map<K, SoftValue<V>> map;
  private final ReferenceQueue<V> queue = new ReferenceQueue();
  
  public SoftHashMap()
  {
    this.map = New.hashMap();
  }
  
  private void processQueue()
  {
    for (;;)
    {
      Reference<? extends V> o = this.queue.poll();
      if (o == null) {
        return;
      }
      SoftValue<V> k = (SoftValue)o;
      Object key = k.key;
      this.map.remove(key);
    }
  }
  
  public V get(Object key)
  {
    processQueue();
    SoftReference<V> o = (SoftReference)this.map.get(key);
    if (o == null) {
      return null;
    }
    return (V)o.get();
  }
  
  public V put(K key, V value)
  {
    processQueue();
    SoftValue<V> old = (SoftValue)this.map.put(key, new SoftValue(value, this.queue, key));
    return old == null ? null : old.get();
  }
  
  public V remove(Object key)
  {
    processQueue();
    SoftReference<V> ref = (SoftReference)this.map.remove(key);
    return ref == null ? null : ref.get();
  }
  
  public void clear()
  {
    processQueue();
    this.map.clear();
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    throw new UnsupportedOperationException();
  }
  
  private static class SoftValue<T>
    extends SoftReference<T>
  {
    final Object key;
    
    public SoftValue(T ref, ReferenceQueue<T> q, Object key)
    {
      super(q);
      this.key = key;
    }
  }
}
