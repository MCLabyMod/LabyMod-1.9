package org.h2.util;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class AbbaLockingDetector
  implements Runnable
{
  private int tickIntervalMs = 2;
  private volatile boolean stop;
  private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
  private Thread thread;
  private final Map<String, Map<String, String>> lockOrdering = new WeakHashMap();
  private final Set<String> knownDeadlocks = new HashSet();
  
  public AbbaLockingDetector startCollecting()
  {
    this.thread = new Thread(this, "AbbaLockingDetector");
    this.thread.setDaemon(true);
    this.thread.start();
    return this;
  }
  
  public synchronized void reset()
  {
    this.lockOrdering.clear();
    this.knownDeadlocks.clear();
  }
  
  public AbbaLockingDetector stopCollecting()
  {
    this.stop = true;
    if (this.thread != null)
    {
      try
      {
        this.thread.join();
      }
      catch (InterruptedException e) {}
      this.thread = null;
    }
    return this;
  }
  
  public void run()
  {
    for (;;)
    {
      if (!this.stop) {
        try
        {
          tick();
        }
        catch (Throwable t) {}
      }
    }
  }
  
  private void tick()
  {
    if (this.tickIntervalMs > 0) {
      try
      {
        Thread.sleep(this.tickIntervalMs);
      }
      catch (InterruptedException ex) {}
    }
    ThreadInfo[] list = this.threadMXBean.dumpAllThreads(true, false);
    
    processThreadList(list);
  }
  
  private void processThreadList(ThreadInfo[] threadInfoList)
  {
    List<String> lockOrder = new ArrayList();
    for (ThreadInfo threadInfo : threadInfoList)
    {
      lockOrder.clear();
      generateOrdering(lockOrder, threadInfo);
      if (lockOrder.size() > 1) {
        markHigher(lockOrder, threadInfo);
      }
    }
  }
  
  private static void generateOrdering(List<String> lockOrder, ThreadInfo info)
  {
    MonitorInfo[] lockedMonitors = info.getLockedMonitors();
    Arrays.sort(lockedMonitors, new Comparator()
    {
      public int compare(MonitorInfo a, MonitorInfo b)
      {
        return b.getLockedStackDepth() - a.getLockedStackDepth();
      }
    });
    for (MonitorInfo mi : lockedMonitors)
    {
      String lockName = getObjectName(mi);
      if (!lockName.equals("sun.misc.Launcher$AppClassLoader")) {
        if (!lockOrder.contains(lockName)) {
          lockOrder.add(lockName);
        }
      }
    }
  }
  
  private synchronized void markHigher(List<String> lockOrder, ThreadInfo threadInfo)
  {
    String topLock = (String)lockOrder.get(lockOrder.size() - 1);
    Map<String, String> map = (Map)this.lockOrdering.get(topLock);
    if (map == null)
    {
      map = new WeakHashMap();
      this.lockOrdering.put(topLock, map);
    }
    String oldException = null;
    for (int i = 0; i < lockOrder.size() - 1; i++)
    {
      String olderLock = (String)lockOrder.get(i);
      Map<String, String> oldMap = (Map)this.lockOrdering.get(olderLock);
      boolean foundDeadLock = false;
      if (oldMap != null)
      {
        String e = (String)oldMap.get(topLock);
        if (e != null)
        {
          foundDeadLock = true;
          String deadlockType = topLock + " " + olderLock;
          if (!this.knownDeadlocks.contains(deadlockType))
          {
            System.out.println(topLock + " synchronized after \n " + olderLock + ", but in the past before\n" + "AFTER\n" + getStackTraceForThread(threadInfo) + "BEFORE\n" + e);
            
            this.knownDeadlocks.add(deadlockType);
          }
        }
      }
      if ((!foundDeadLock) && (!map.containsKey(olderLock)))
      {
        if (oldException == null) {
          oldException = getStackTraceForThread(threadInfo);
        }
        map.put(olderLock, oldException);
      }
    }
  }
  
  private static String getStackTraceForThread(ThreadInfo info)
  {
    StringBuilder sb = new StringBuilder("\"" + info.getThreadName() + "\"" + " Id=" + info.getThreadId() + " " + info.getThreadState());
    if (info.getLockName() != null) {
      sb.append(" on " + info.getLockName());
    }
    if (info.getLockOwnerName() != null) {
      sb.append(" owned by \"" + info.getLockOwnerName() + "\" Id=" + info.getLockOwnerId());
    }
    if (info.isSuspended()) {
      sb.append(" (suspended)");
    }
    if (info.isInNative()) {
      sb.append(" (in native)");
    }
    sb.append('\n');
    StackTraceElement[] stackTrace = info.getStackTrace();
    MonitorInfo[] lockedMonitors = info.getLockedMonitors();
    boolean startDumping = false;
    for (int i = 0; i < stackTrace.length; i++)
    {
      StackTraceElement e = stackTrace[i];
      if (startDumping) {
        dumpStackTraceElement(info, sb, i, e);
      }
      for (MonitorInfo mi : lockedMonitors) {
        if (mi.getLockedStackDepth() == i)
        {
          if (!startDumping)
          {
            dumpStackTraceElement(info, sb, i, e);
            startDumping = true;
          }
          sb.append("\t-  locked " + mi);
          sb.append('\n');
        }
      }
    }
    return sb.toString();
  }
  
  private static void dumpStackTraceElement(ThreadInfo info, StringBuilder sb, int i, StackTraceElement e)
  {
    sb.append('\t').append("at ").append(e.toString());
    sb.append('\n');
    if ((i == 0) && (info.getLockInfo() != null))
    {
      Thread.State ts = info.getThreadState();
      switch (ts)
      {
      case BLOCKED: 
        sb.append("\t-  blocked on " + info.getLockInfo());
        sb.append('\n');
        break;
      case WAITING: 
        sb.append("\t-  waiting on " + info.getLockInfo());
        sb.append('\n');
        break;
      case TIMED_WAITING: 
        sb.append("\t-  waiting on " + info.getLockInfo());
        sb.append('\n');
        break;
      }
    }
  }
  
  private static String getObjectName(MonitorInfo info)
  {
    return info.getClassName() + "@" + Integer.toHexString(info.getIdentityHashCode());
  }
}
