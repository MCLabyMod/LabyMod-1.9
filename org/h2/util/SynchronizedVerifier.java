package org.h2.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class SynchronizedVerifier
{
  private static volatile boolean enabled;
  private static final Map<Class<?>, AtomicBoolean> DETECT = Collections.synchronizedMap(new HashMap());
  private static final Map<Object, Object> CURRENT = Collections.synchronizedMap(new IdentityHashMap());
  
  public static void setDetect(Class<?> clazz, boolean value)
  {
    if (value)
    {
      DETECT.put(clazz, new AtomicBoolean());
    }
    else
    {
      AtomicBoolean b = (AtomicBoolean)DETECT.remove(clazz);
      if (b == null) {
        throw new AssertionError("Detection was not enabled");
      }
      if (!b.get()) {
        throw new AssertionError("No object of this class was tested");
      }
    }
    enabled = DETECT.size() > 0;
  }
  
  public static void check(Object o)
  {
    if (enabled) {
      detectConcurrentAccess(o);
    }
  }
  
  private static void detectConcurrentAccess(Object o)
  {
    AtomicBoolean value = (AtomicBoolean)DETECT.get(o.getClass());
    if (value != null)
    {
      value.set(true);
      if (CURRENT.remove(o) != null) {
        throw new AssertionError("Concurrent access");
      }
      CURRENT.put(o, o);
      try
      {
        Thread.sleep(1L);
      }
      catch (InterruptedException e) {}
      Object old = CURRENT.remove(o);
      if (old == null) {
        throw new AssertionError("Concurrent access");
      }
    }
  }
}
