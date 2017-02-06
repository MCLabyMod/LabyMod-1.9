package org.h2.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class AbbaDetector
{
  private static final boolean TRACE = false;
  private static final ThreadLocal<Deque<Object>> STACK = new ThreadLocal()
  {
    protected Deque<Object> initialValue()
    {
      return new ArrayDeque();
    }
  };
  private static final Map<Object, Map<Object, Exception>> LOCK_ORDERING = new WeakHashMap();
  private static final Set<String> KNOWN_DEADLOCKS = new HashSet();
  
  public static Object begin(Object o)
  {
    if (o == null) {
      o = new SecurityManager().
      {
        Class<?> clazz = getClassContext()[2];
      };
    }
    Deque<Object> stack = (Deque)STACK.get();
    if (!stack.isEmpty())
    {
      if (stack.contains(o)) {
        return o;
      }
      while (!stack.isEmpty())
      {
        Object last = stack.peek();
        if (Thread.holdsLock(last)) {
          break;
        }
        stack.pop();
      }
    }
    if (stack.size() > 0) {
      markHigher(o, stack);
    }
    stack.push(o);
    return o;
  }
  
  private static Object getTest(Object o)
  {
    return o;
  }
  
  private static String getObjectName(Object o)
  {
    return o.getClass().getSimpleName() + "@" + System.identityHashCode(o);
  }
  
  private static synchronized void markHigher(Object o, Deque<Object> older)
  {
    Object test = getTest(o);
    Map<Object, Exception> map = (Map)LOCK_ORDERING.get(test);
    if (map == null)
    {
      map = new WeakHashMap();
      LOCK_ORDERING.put(test, map);
    }
    Exception oldException = null;
    for (Object old : older)
    {
      Object oldTest = getTest(old);
      if (oldTest != test)
      {
        Map<Object, Exception> oldMap = (Map)LOCK_ORDERING.get(oldTest);
        if (oldMap != null)
        {
          Exception e = (Exception)oldMap.get(test);
          if (e != null)
          {
            String deadlockType = test.getClass() + " " + oldTest.getClass();
            if (!KNOWN_DEADLOCKS.contains(deadlockType))
            {
              String message = getObjectName(test) + " synchronized after \n " + getObjectName(oldTest) + ", but in the past before";
              
              RuntimeException ex = new RuntimeException(message);
              ex.initCause(e);
              ex.printStackTrace(System.out);
              
              KNOWN_DEADLOCKS.add(deadlockType);
            }
          }
        }
        if (!map.containsKey(oldTest))
        {
          if (oldException == null) {
            oldException = new Exception("Before");
          }
          map.put(oldTest, oldException);
        }
      }
    }
  }
}
