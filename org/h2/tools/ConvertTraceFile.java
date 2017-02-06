package org.h2.tools;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.util.Tool;

public class ConvertTraceFile
  extends Tool
{
  private final HashMap<String, Stat> stats;
  private long timeTotal;
  
  public ConvertTraceFile()
  {
    this.stats = New.hashMap();
  }
  
  static class Stat
    implements Comparable<Stat>
  {
    String sql;
    int executeCount;
    long time;
    long resultCount;
    
    public int compareTo(Stat other)
    {
      if (other == this) {
        return 0;
      }
      int c = MathUtils.compareLong(other.time, this.time);
      if (c == 0)
      {
        c = MathUtils.compareInt(other.executeCount, this.executeCount);
        if (c == 0) {
          c = this.sql.compareTo(other.sql);
        }
      }
      return c;
    }
  }
  
  public static void main(String... args)
    throws SQLException
  {
    new ConvertTraceFile().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String traceFile = "test.trace.db";
    String javaClass = "Test";
    String script = "test.sql";
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg.equals("-traceFile"))
      {
        traceFile = args[(++i)];
      }
      else if (arg.equals("-javaClass"))
      {
        javaClass = args[(++i)];
      }
      else if (arg.equals("-script"))
      {
        script = args[(++i)];
      }
      else
      {
        if ((arg.equals("-help")) || (arg.equals("-?")))
        {
          showUsage();
          return;
        }
        showUsageAndThrowUnsupportedOption(arg);
      }
    }
    try
    {
      convertFile(traceFile, javaClass, script);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, traceFile);
    }
  }
  
  private void convertFile(String traceFileName, String javaClassName, String script)
    throws IOException
  {
    LineNumberReader reader = new LineNumberReader(IOUtils.getBufferedReader(FileUtils.newInputStream(traceFileName)));
    
    PrintWriter javaWriter = new PrintWriter(IOUtils.getBufferedWriter(FileUtils.newOutputStream(javaClassName + ".java", false)));
    
    PrintWriter scriptWriter = new PrintWriter(IOUtils.getBufferedWriter(FileUtils.newOutputStream(script, false)));
    
    javaWriter.println("import java.io.*;");
    javaWriter.println("import java.sql.*;");
    javaWriter.println("import java.math.*;");
    javaWriter.println("import java.util.Calendar;");
    String cn = javaClassName.replace('\\', '/');
    int idx = cn.lastIndexOf('/');
    if (idx > 0) {
      cn = cn.substring(idx + 1);
    }
    javaWriter.println("public class " + cn + " {");
    javaWriter.println("    public static void main(String... args) throws Exception {");
    
    javaWriter.println("        Class.forName(\"org.h2.Driver\");");
    for (;;)
    {
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      if (line.startsWith("/**/"))
      {
        line = "        " + line.substring(4);
        javaWriter.println(line);
      }
      else if (line.startsWith("/*SQL"))
      {
        int end = line.indexOf("*/");
        String sql = line.substring(end + "*/".length());
        sql = StringUtils.javaDecode(sql);
        line = line.substring("/*SQL".length(), end);
        if (line.length() > 0)
        {
          String statement = sql;
          int count = 0;
          long time = 0L;
          line = line.trim();
          if (line.length() > 0)
          {
            StringTokenizer tk = new StringTokenizer(line, " :");
            while (tk.hasMoreElements())
            {
              String token = tk.nextToken();
              if ("l".equals(token))
              {
                int len = Integer.parseInt(tk.nextToken());
                statement = sql.substring(0, len) + ";";
              }
              else if ("#".equals(token))
              {
                count = Integer.parseInt(tk.nextToken());
              }
              else if ("t".equals(token))
              {
                time = Long.parseLong(tk.nextToken());
              }
            }
          }
          addToStats(statement, count, time);
        }
        scriptWriter.println(sql);
      }
    }
    javaWriter.println("    }");
    javaWriter.println('}');
    reader.close();
    javaWriter.close();
    int accumTime;
    if (this.stats.size() > 0)
    {
      scriptWriter.println("-----------------------------------------");
      scriptWriter.println("-- SQL Statement Statistics");
      scriptWriter.println("-- time: total time in milliseconds (accumulated)");
      scriptWriter.println("-- count: how many times the statement ran");
      scriptWriter.println("-- result: total update count or row count");
      scriptWriter.println("-----------------------------------------");
      scriptWriter.println("-- self accu    time   count  result sql");
      accumTime = 0;
      ArrayList<Stat> list = New.arrayList(this.stats.values());
      Collections.sort(list);
      if (this.timeTotal == 0L) {
        this.timeTotal = 1L;
      }
      for (Stat stat : list)
      {
        accumTime = (int)(accumTime + stat.time);
        StringBuilder buff = new StringBuilder(100);
        buff.append("-- ").append(padNumberLeft(100L * stat.time / this.timeTotal, 3)).append("% ").append(padNumberLeft(100 * accumTime / this.timeTotal, 3)).append('%').append(padNumberLeft(stat.time, 8)).append(padNumberLeft(stat.executeCount, 8)).append(padNumberLeft(stat.resultCount, 8)).append(' ').append(removeNewlines(stat.sql));
        
        scriptWriter.println(buff.toString());
      }
    }
    scriptWriter.close();
  }
  
  private static String removeNewlines(String s)
  {
    return s == null ? s : s.replace('\r', ' ').replace('\n', ' ');
  }
  
  private static String padNumberLeft(long number, int digits)
  {
    return StringUtils.pad(String.valueOf(number), digits, " ", false);
  }
  
  private void addToStats(String sql, int resultCount, long time)
  {
    Stat stat = (Stat)this.stats.get(sql);
    if (stat == null)
    {
      stat = new Stat();
      stat.sql = sql;
      this.stats.put(sql, stat);
    }
    stat.executeCount += 1;
    stat.resultCount += resultCount;
    stat.time += time;
    this.timeTotal += time;
  }
}
