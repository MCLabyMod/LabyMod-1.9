package org.h2.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Profiler
  implements Runnable
{
  private static Instrumentation instrumentation;
  private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
  private static final int MAX_ELEMENTS = 1000;
  public int interval = 2;
  public int depth = 48;
  public boolean paused;
  public boolean sumClasses;
  private int pid;
  private final String[] ignoreLines = "java,sun,com.sun.,com.google.common.,com.mongodb.".split(",");
  private final String[] ignorePackages = "java,sun,com.sun.,com.google.common.,com.mongodb.".split(",");
  private final String[] ignoreThreads = "java.lang.Object.wait,java.lang.Thread.dumpThreads,java.lang.Thread.getThreads,java.lang.Thread.sleep,java.lang.UNIXProcess.waitForProcessExit,java.net.PlainDatagramSocketImpl.receive0,java.net.PlainSocketImpl.accept,java.net.PlainSocketImpl.socketAccept,java.net.SocketInputStream.socketRead,java.net.SocketOutputStream.socketWrite,sun.awt.windows.WToolkit.eventLoop,sun.misc.Unsafe.park,sun.nio.ch.EPollArrayWrapper.epollWait,sun.nio.ch.KQueueArrayWrapper.kevent0,sun.nio.ch.ServerSocketChannelImpl.accept,dalvik.system.VMStack.getThreadStackTrace,dalvik.system.NativeStart.run".split(",");
  private volatile boolean stop;
  private final HashMap<String, Integer> counts = new HashMap();
  private final HashMap<String, Integer> summary = new HashMap();
  private int minCount = 1;
  private int total;
  private Thread thread;
  private long start;
  private long time;
  private int threadDumps;
  
  public static void premain(String agentArgs, Instrumentation inst)
  {
    instrumentation = inst;
  }
  
  public static Instrumentation getInstrumentation()
  {
    return instrumentation;
  }
  
  public static void main(String... args)
  {
    new Profiler().run(args);
  }
  
  private void run(String... args)
  {
    if (args.length == 0)
    {
      System.out.println("Show profiling data");
      System.out.println("Usage: java " + getClass().getName() + " <pid> | <stackTraceFileNames>");
      
      System.out.println("Processes:");
      String processes = exec(new String[] { "jps", "-l" });
      System.out.println(processes);
      return;
    }
    this.start = System.currentTimeMillis();
    if (args[0].matches("\\d+"))
    {
      this.pid = Integer.parseInt(args[0]);
      long last = 0L;
      for (;;)
      {
        tick();
        long t = System.currentTimeMillis();
        if (t - last > 5000L)
        {
          this.time = (System.currentTimeMillis() - this.start);
          System.out.println(getTopTraces(3));
          last = t;
        }
      }
    }
    try
    {
      for (String file : args)
      {
        Reader reader = new InputStreamReader(new FileInputStream(file), "CP1252");
        
        LineNumberReader r = new LineNumberReader(reader);
        for (;;)
        {
          String line = r.readLine();
          if (line == null) {
            break;
          }
          if (line.startsWith("Full thread dump")) {
            this.threadDumps += 1;
          }
        }
        reader.close();
        reader = new InputStreamReader(new FileInputStream(file), "CP1252");
        
        r = new LineNumberReader(reader);
        processList(readStackTrace(r));
        reader.close();
      }
      System.out.println(getTopTraces(3));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private static List<Object[]> getRunnableStackTraces()
  {
    ArrayList<Object[]> list = new ArrayList();
    Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
    for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet())
    {
      Thread t = (Thread)entry.getKey();
      if (t.getState() == Thread.State.RUNNABLE)
      {
        StackTraceElement[] dump = (StackTraceElement[])entry.getValue();
        if ((dump != null) && (dump.length != 0)) {
          list.add(dump);
        }
      }
    }
    return list;
  }
  
  private static List<Object[]> readRunnableStackTraces(int pid)
  {
    try
    {
      String jstack = exec(new String[] { "jstack", "" + pid });
      LineNumberReader r = new LineNumberReader(new StringReader(jstack));
      
      return readStackTrace(r);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private static List<Object[]> readStackTrace(LineNumberReader r)
    throws IOException
  {
    ArrayList<Object[]> list = new ArrayList();
    for (;;)
    {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      if (line.startsWith("\""))
      {
        line = r.readLine();
        if (line == null) {
          break;
        }
        line = line.trim();
        if (line.startsWith("java.lang.Thread.State: RUNNABLE"))
        {
          ArrayList<String> stack = new ArrayList();
          for (;;)
          {
            line = r.readLine();
            if (line == null) {
              break;
            }
            line = line.trim();
            if (!line.startsWith("- "))
            {
              if (!line.startsWith("at ")) {
                break;
              }
              line = line.substring(3).trim();
              stack.add(line);
            }
          }
          if (stack.size() > 0)
          {
            String[] s = (String[])stack.toArray(new String[stack.size()]);
            list.add(s);
          }
        }
      }
    }
    return list;
  }
  
  private static String exec(String... args)
  {
    ByteArrayOutputStream err = new ByteArrayOutputStream();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try
    {
      Process p = Runtime.getRuntime().exec(args);
      copyInThread(p.getInputStream(), out);
      copyInThread(p.getErrorStream(), err);
      p.waitFor();
      String e = new String(err.toByteArray(), "UTF-8");
      if (e.length() > 0) {
        throw new RuntimeException(e);
      }
      return new String(out.toByteArray(), "UTF-8");
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private static void copyInThread(final InputStream in, final OutputStream out)
  {
    new Thread("Profiler stream copy")
    {
      public void run()
      {
        byte[] buffer = new byte['က'];
        try
        {
          for (;;)
          {
            int len = in.read(buffer, 0, buffer.length);
            if (len < 0) {
              break;
            }
            out.write(buffer, 0, len);
          }
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
      }
    }.start();
  }
  
  public Profiler startCollecting()
  {
    this.thread = new Thread(this, "Profiler");
    this.thread.setDaemon(true);
    this.thread.start();
    return this;
  }
  
  public Profiler stopCollecting()
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
    this.start = System.currentTimeMillis();
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
    this.time = (System.currentTimeMillis() - this.start);
  }
  
  private void tick()
  {
    if (this.interval > 0)
    {
      if (this.paused) {
        return;
      }
      try
      {
        Thread.sleep(this.interval);
      }
      catch (Exception e) {}
    }
    List<Object[]> list;
    List<Object[]> list;
    if (this.pid != 0) {
      list = readRunnableStackTraces(this.pid);
    } else {
      list = getRunnableStackTraces();
    }
    this.threadDumps += 1;
    processList(list);
  }
  
  private void processList(List<Object[]> list)
  {
    for (Object[] dump : list) {
      if (!startsWithAny(dump[0].toString(), this.ignoreThreads))
      {
        StringBuilder buff = new StringBuilder();
        
        String last = null;
        boolean packageCounts = false;
        int j = 0;
        for (int i = 0; (i < dump.length) && (j < this.depth); i++)
        {
          String el = dump[i].toString();
          if ((!el.equals(last)) && (!startsWithAny(el, this.ignoreLines)))
          {
            last = el;
            buff.append("at ").append(el).append(LINE_SEPARATOR);
            if ((!packageCounts) && (!startsWithAny(el, this.ignorePackages)))
            {
              packageCounts = true;
              for (int index = 0; index < el.length(); index++)
              {
                char c = el.charAt(index);
                if ((c == '(') || (Character.isUpperCase(c))) {
                  break;
                }
              }
              if ((index > 0) && (el.charAt(index - 1) == '.')) {
                index--;
              }
              if (this.sumClasses)
              {
                int m = el.indexOf('.', index + 1);
                index = m >= 0 ? m : index;
              }
              String groupName = el.substring(0, index);
              increment(this.summary, groupName, 0);
            }
            j++;
          }
        }
        if (buff.length() > 0)
        {
          this.minCount = increment(this.counts, buff.toString().trim(), this.minCount);
          this.total += 1;
        }
      }
    }
  }
  
  private static boolean startsWithAny(String s, String[] prefixes)
  {
    for (String p : prefixes) {
      if ((p.length() > 0) && (s.startsWith(p))) {
        return true;
      }
    }
    return false;
  }
  
  private static int increment(HashMap<String, Integer> map, String trace, int minCount)
  {
    Integer oldCount = (Integer)map.get(trace);
    if (oldCount == null) {
      map.put(trace, Integer.valueOf(1));
    } else {
      map.put(trace, Integer.valueOf(oldCount.intValue() + 1));
    }
    while (map.size() > 1000)
    {
      Iterator<Map.Entry<String, Integer>> ei = map.entrySet().iterator();
      while (ei.hasNext())
      {
        Map.Entry<String, Integer> e = (Map.Entry)ei.next();
        if (((Integer)e.getValue()).intValue() <= minCount) {
          ei.remove();
        }
      }
      if (map.size() > 1000) {
        minCount++;
      }
    }
    return minCount;
  }
  
  public String getTop(int count)
  {
    stopCollecting();
    return getTopTraces(count);
  }
  
  private String getTopTraces(int count)
  {
    StringBuilder buff = new StringBuilder();
    buff.append("Profiler: top ").append(count).append(" stack trace(s) of ");
    if (this.time > 0L) {
      buff.append(" of ").append(this.time).append(" ms");
    }
    if (this.threadDumps > 0) {
      buff.append(" of ").append(this.threadDumps).append(" thread dumps");
    }
    buff.append(":").append(LINE_SEPARATOR);
    if (this.counts.size() == 0) {
      buff.append("(none)").append(LINE_SEPARATOR);
    }
    HashMap<String, Integer> copy = new HashMap(this.counts);
    appendTop(buff, copy, count, this.total, false);
    buff.append("summary:").append(LINE_SEPARATOR);
    copy = new HashMap(this.summary);
    appendTop(buff, copy, count, this.total, true);
    buff.append('.');
    return buff.toString();
  }
  
  private static void appendTop(StringBuilder buff, HashMap<String, Integer> map, int count, int total, boolean table)
  {
    int x = 0;int min = 0;
    for (;;)
    {
      int highest = 0;
      Map.Entry<String, Integer> best = null;
      for (Map.Entry<String, Integer> el : map.entrySet()) {
        if (((Integer)el.getValue()).intValue() > highest)
        {
          best = el;
          highest = ((Integer)el.getValue()).intValue();
        }
      }
      if (best == null) {
        break;
      }
      map.remove(best.getKey());
      x++;
      if (x >= count)
      {
        if (((Integer)best.getValue()).intValue() < min) {
          break;
        }
        min = ((Integer)best.getValue()).intValue();
      }
      int c = ((Integer)best.getValue()).intValue();
      int percent = 100 * c / Math.max(total, 1);
      if (table)
      {
        if (percent > 1) {
          buff.append(percent).append("%: ").append((String)best.getKey()).append(LINE_SEPARATOR);
        }
      }
      else {
        buff.append(c).append('/').append(total).append(" (").append(percent).append("%):").append(LINE_SEPARATOR).append((String)best.getKey()).append(LINE_SEPARATOR);
      }
    }
  }
}
