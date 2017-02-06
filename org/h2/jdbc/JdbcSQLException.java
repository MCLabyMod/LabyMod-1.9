package org.h2.jdbc;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;

public class JdbcSQLException
  extends SQLException
{
  public static final String HIDE_SQL = "--hide--";
  private static final long serialVersionUID = 1L;
  private final String originalMessage;
  private final Throwable cause;
  private final String stackTrace;
  private String message;
  private String sql;
  
  public JdbcSQLException(String message, String sql, String state, int errorCode, Throwable cause, String stackTrace)
  {
    super(message, state, errorCode);
    this.originalMessage = message;
    setSQL(sql);
    this.cause = cause;
    this.stackTrace = stackTrace;
    buildMessage();
    initCause(cause);
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getOriginalMessage()
  {
    return this.originalMessage;
  }
  
  public void printStackTrace()
  {
    printStackTrace(System.err);
  }
  
  public void printStackTrace(PrintWriter s)
  {
    if (s != null)
    {
      super.printStackTrace(s);
      
      SQLException next = getNextException();
      for (int i = 0; (i < 100) && (next != null); i++)
      {
        s.println(next.toString());
        next = next.getNextException();
      }
      if (next != null) {
        s.println("(truncated)");
      }
    }
  }
  
  public void printStackTrace(PrintStream s)
  {
    if (s != null)
    {
      super.printStackTrace(s);
      
      SQLException next = getNextException();
      for (int i = 0; (i < 100) && (next != null); i++)
      {
        s.println(next.toString());
        next = next.getNextException();
      }
      if (next != null) {
        s.println("(truncated)");
      }
    }
  }
  
  public Throwable getOriginalCause()
  {
    return this.cause;
  }
  
  public String getSQL()
  {
    return this.sql;
  }
  
  public void setSQL(String sql)
  {
    if ((sql != null) && (sql.contains("--hide--"))) {
      sql = "-";
    }
    this.sql = sql;
    buildMessage();
  }
  
  private void buildMessage()
  {
    StringBuilder buff = new StringBuilder(this.originalMessage == null ? "- " : this.originalMessage);
    if (this.sql != null) {
      buff.append("; SQL statement:\n").append(this.sql);
    }
    buff.append(" [").append(getErrorCode()).append('-').append(187).append(']');
    
    this.message = buff.toString();
  }
  
  public String toString()
  {
    if (this.stackTrace == null) {
      return super.toString();
    }
    return this.stackTrace;
  }
}
