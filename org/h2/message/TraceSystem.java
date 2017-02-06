package org.h2.message;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.h2.api.ErrorCode;
import org.h2.jdbc.JdbcSQLException;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.New;

public class TraceSystem
  implements TraceWriter
{
  public static final int PARENT = -1;
  public static final int OFF = 0;
  public static final int ERROR = 1;
  public static final int INFO = 2;
  public static final int DEBUG = 3;
  public static final int ADAPTER = 4;
  public static final int DEFAULT_TRACE_LEVEL_SYSTEM_OUT = 0;
  public static final int DEFAULT_TRACE_LEVEL_FILE = 1;
  private static final int DEFAULT_MAX_FILE_SIZE = 67108864;
  private static final int CHECK_SIZE_EACH_WRITES = 4096;
  private int levelSystemOut = 0;
  private int levelFile = 1;
  private int levelMax;
  private int maxFileSize = 67108864;
  private String fileName;
  private HashMap<String, Trace> traces;
  private SimpleDateFormat dateFormat;
  private Writer fileWriter;
  private PrintWriter printWriter;
  private int checkSize;
  private boolean closed;
  private boolean writingErrorLogged;
  private TraceWriter writer = this;
  private PrintStream sysOut = System.out;
  
  public TraceSystem(String fileName)
  {
    this.fileName = fileName;
    updateLevel();
  }
  
  private void updateLevel()
  {
    this.levelMax = Math.max(this.levelSystemOut, this.levelFile);
  }
  
  public void setSysOut(PrintStream out)
  {
    this.sysOut = out;
  }
  
  public synchronized Trace getTrace(String module)
  {
    if (module.endsWith("]")) {
      return new Trace(this.writer, module);
    }
    if (this.traces == null) {
      this.traces = New.hashMap(16);
    }
    Trace t = (Trace)this.traces.get(module);
    if (t == null)
    {
      t = new Trace(this.writer, module);
      this.traces.put(module, t);
    }
    return t;
  }
  
  public boolean isEnabled(int level)
  {
    return level <= this.levelMax;
  }
  
  public void setFileName(String name)
  {
    this.fileName = name;
  }
  
  public void setMaxFileSize(int max)
  {
    this.maxFileSize = max;
  }
  
  public void setLevelSystemOut(int level)
  {
    this.levelSystemOut = level;
    updateLevel();
  }
  
  public void setLevelFile(int level)
  {
    if (level == 4)
    {
      String adapterClass = "org.h2.message.TraceWriterAdapter";
      try
      {
        this.writer = ((TraceWriter)Class.forName(adapterClass).newInstance());
      }
      catch (Throwable e)
      {
        e = DbException.get(90086, e, new String[] { adapterClass });
        write(1, "database", adapterClass, e);
        return;
      }
      String name = this.fileName;
      if (name != null)
      {
        if (name.endsWith(".trace.db")) {
          name = name.substring(0, name.length() - ".trace.db".length());
        }
        int idx = Math.max(name.lastIndexOf('/'), name.lastIndexOf('\\'));
        if (idx >= 0) {
          name = name.substring(idx + 1);
        }
        this.writer.setName(name);
      }
    }
    this.levelFile = level;
    updateLevel();
  }
  
  public int getLevelFile()
  {
    return this.levelFile;
  }
  
  private synchronized String format(String module, String s)
  {
    if (this.dateFormat == null) {
      this.dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss ");
    }
    return this.dateFormat.format(Long.valueOf(System.currentTimeMillis())) + module + ": " + s;
  }
  
  public void write(int level, String module, String s, Throwable t)
  {
    if ((level <= this.levelSystemOut) || (level > this.levelMax))
    {
      this.sysOut.println(format(module, s));
      if ((t != null) && (this.levelSystemOut == 3)) {
        t.printStackTrace(this.sysOut);
      }
    }
    if ((this.fileName != null) && 
      (level <= this.levelFile)) {
      writeFile(format(module, s), t);
    }
  }
  
  private synchronized void writeFile(String s, Throwable t)
  {
    try
    {
      if (this.checkSize++ >= 4096)
      {
        this.checkSize = 0;
        closeWriter();
        if ((this.maxFileSize > 0) && (FileUtils.size(this.fileName) > this.maxFileSize))
        {
          String old = this.fileName + ".old";
          FileUtils.delete(old);
          FileUtils.move(this.fileName, old);
        }
      }
      if (!openWriter()) {
        return;
      }
      this.printWriter.println(s);
      if (t != null) {
        if ((this.levelFile == 1) && ((t instanceof JdbcSQLException)))
        {
          JdbcSQLException se = (JdbcSQLException)t;
          int code = se.getErrorCode();
          if (ErrorCode.isCommon(code)) {
            this.printWriter.println(t.toString());
          } else {
            t.printStackTrace(this.printWriter);
          }
        }
        else
        {
          t.printStackTrace(this.printWriter);
        }
      }
      this.printWriter.flush();
      if (this.closed) {
        closeWriter();
      }
    }
    catch (Exception e)
    {
      logWritingError(e);
    }
  }
  
  private void logWritingError(Exception e)
  {
    if (this.writingErrorLogged) {
      return;
    }
    this.writingErrorLogged = true;
    Exception se = DbException.get(90034, e, new String[] { this.fileName, e.toString() });
    
    this.fileName = null;
    this.sysOut.println(se);
    se.printStackTrace();
  }
  
  private boolean openWriter()
  {
    if (this.printWriter == null) {
      try
      {
        FileUtils.createDirectories(FileUtils.getParent(this.fileName));
        if ((FileUtils.exists(this.fileName)) && (!FileUtils.canWrite(this.fileName))) {
          return false;
        }
        this.fileWriter = IOUtils.getBufferedWriter(FileUtils.newOutputStream(this.fileName, true));
        
        this.printWriter = new PrintWriter(this.fileWriter, true);
      }
      catch (Exception e)
      {
        logWritingError(e);
        return false;
      }
    }
    return true;
  }
  
  private synchronized void closeWriter()
  {
    if (this.printWriter != null)
    {
      this.printWriter.flush();
      this.printWriter.close();
      this.printWriter = null;
    }
    if (this.fileWriter != null)
    {
      try
      {
        this.fileWriter.close();
      }
      catch (IOException e) {}
      this.fileWriter = null;
    }
  }
  
  public void close()
  {
    closeWriter();
    this.closed = true;
  }
  
  public void setName(String name) {}
}
