package org.h2.util;

import java.io.Closeable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CloseWatcher
  extends PhantomReference<Object>
{
  private static ReferenceQueue<Object> queue = new ReferenceQueue();
  private static Set<CloseWatcher> refs = createSet();
  private String openStackTrace;
  private Closeable closeable;
  
  public CloseWatcher(Object referent, ReferenceQueue<Object> q, Closeable closeable)
  {
    super(referent, q);
    this.closeable = closeable;
  }
  
  private static Set<CloseWatcher> createSet()
  {
    return Collections.synchronizedSet(new HashSet());
  }
  
  public static CloseWatcher pollUnclosed()
  {
    ReferenceQueue<Object> q = queue;
    if (q == null) {
      return null;
    }
    for (;;)
    {
      CloseWatcher cw = (CloseWatcher)q.poll();
      if (cw == null) {
        return null;
      }
      if (refs != null) {
        refs.remove(cw);
      }
      if (cw.closeable != null) {
        return cw;
      }
    }
  }
  
  public static CloseWatcher register(Object o, Closeable closeable, boolean stackTrace)
  {
    ReferenceQueue<Object> q = queue;
    if (q == null)
    {
      q = new ReferenceQueue();
      queue = q;
    }
    CloseWatcher cw = new CloseWatcher(o, q, closeable);
    if (stackTrace)
    {
      Exception e = new Exception("Open Stack Trace");
      StringWriter s = new StringWriter();
      e.printStackTrace(new PrintWriter(s));
      cw.openStackTrace = s.toString();
    }
    if (refs == null) {
      refs = createSet();
    }
    refs.add(cw);
    return cw;
  }
  
  public static void unregister(CloseWatcher w)
  {
    w.closeable = null;
    refs.remove(w);
  }
  
  public String getOpenStackTrace()
  {
    return this.openStackTrace;
  }
  
  public Closeable getCloseable()
  {
    return this.closeable;
  }
}
