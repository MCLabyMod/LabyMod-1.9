package org.h2.message;

import java.text.MessageFormat;
import java.util.ArrayList;
import org.h2.engine.SysProperties;
import org.h2.expression.ParameterInterface;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;

public class Trace
{
  public static final String COMMAND = "command";
  public static final String CONSTRAINT = "constraint";
  public static final String DATABASE = "database";
  public static final String FUNCTION = "function";
  public static final String FILE_LOCK = "fileLock";
  public static final String INDEX = "index";
  public static final String JDBC = "jdbc";
  public static final String LOCK = "lock";
  public static final String SCHEMA = "schema";
  public static final String SEQUENCE = "sequence";
  public static final String SETTING = "setting";
  public static final String TABLE = "table";
  public static final String TRIGGER = "trigger";
  public static final String USER = "user";
  public static final String PAGE_STORE = "pageStore";
  private final TraceWriter traceWriter;
  private final String module;
  private final String lineSeparator;
  private int traceLevel = -1;
  
  Trace(TraceWriter traceWriter, String module)
  {
    this.traceWriter = traceWriter;
    this.module = module;
    this.lineSeparator = SysProperties.LINE_SEPARATOR;
  }
  
  public void setLevel(int level)
  {
    this.traceLevel = level;
  }
  
  private boolean isEnabled(int level)
  {
    if (this.traceLevel == -1) {
      return this.traceWriter.isEnabled(level);
    }
    return level <= this.traceLevel;
  }
  
  public boolean isInfoEnabled()
  {
    return isEnabled(2);
  }
  
  public boolean isDebugEnabled()
  {
    return isEnabled(3);
  }
  
  public void error(Throwable t, String s)
  {
    if (isEnabled(1)) {
      this.traceWriter.write(1, this.module, s, t);
    }
  }
  
  public void error(Throwable t, String s, Object... params)
  {
    if (isEnabled(1))
    {
      s = MessageFormat.format(s, params);
      this.traceWriter.write(1, this.module, s, t);
    }
  }
  
  public void info(String s)
  {
    if (isEnabled(2)) {
      this.traceWriter.write(2, this.module, s, null);
    }
  }
  
  public void info(String s, Object... params)
  {
    if (isEnabled(2))
    {
      s = MessageFormat.format(s, params);
      this.traceWriter.write(2, this.module, s, null);
    }
  }
  
  void info(Throwable t, String s)
  {
    if (isEnabled(2)) {
      this.traceWriter.write(2, this.module, s, t);
    }
  }
  
  public static String formatParams(ArrayList<? extends ParameterInterface> parameters)
  {
    if (parameters.size() == 0) {
      return "";
    }
    StatementBuilder buff = new StatementBuilder();
    int i = 0;
    boolean params = false;
    for (ParameterInterface p : parameters) {
      if (p.isValueSet())
      {
        if (!params)
        {
          buff.append(" {");
          params = true;
        }
        buff.appendExceptFirst(", ");
        Value v = p.getParamValue();
        buff.append(++i).append(": ").append(v.getTraceSQL());
      }
    }
    if (params) {
      buff.append('}');
    }
    return buff.toString();
  }
  
  public void infoSQL(String sql, String params, int count, long time)
  {
    if (!isEnabled(2)) {
      return;
    }
    StringBuilder buff = new StringBuilder(sql.length() + params.length() + 20);
    buff.append(this.lineSeparator).append("/*SQL");
    boolean space = false;
    if (params.length() > 0)
    {
      space = true;
      buff.append(" l:").append(sql.length());
    }
    if (count > 0)
    {
      space = true;
      buff.append(" #:").append(count);
    }
    if (time > 0L)
    {
      space = true;
      buff.append(" t:").append(time);
    }
    if (!space) {
      buff.append(' ');
    }
    buff.append("*/").append(StringUtils.javaEncode(sql)).append(StringUtils.javaEncode(params)).append(';');
    
    sql = buff.toString();
    this.traceWriter.write(2, this.module, sql, null);
  }
  
  public void debug(String s, Object... params)
  {
    if (isEnabled(3))
    {
      s = MessageFormat.format(s, params);
      this.traceWriter.write(3, this.module, s, null);
    }
  }
  
  public void debug(String s)
  {
    if (isEnabled(3)) {
      this.traceWriter.write(3, this.module, s, null);
    }
  }
  
  public void debug(Throwable t, String s)
  {
    if (isEnabled(3)) {
      this.traceWriter.write(3, this.module, s, t);
    }
  }
  
  public void infoCode(String java)
  {
    if (isEnabled(2)) {
      this.traceWriter.write(2, this.module, this.lineSeparator + "/**/" + java, null);
    }
  }
  
  void debugCode(String java)
  {
    if (isEnabled(3)) {
      this.traceWriter.write(3, this.module, this.lineSeparator + "/**/" + java, null);
    }
  }
}
