package org.h2.message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import org.h2.api.ErrorCode;
import org.h2.engine.Constants;
import org.h2.jdbc.JdbcSQLException;
import org.h2.util.SortedProperties;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class DbException
  extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  private static final Properties MESSAGES = new Properties();
  private Object source;
  
  static
  {
    try
    {
      byte[] messages = Utils.getResource("/org/h2/res/_messages_en.prop");
      if (messages != null) {
        MESSAGES.load(new ByteArrayInputStream(messages));
      }
      String language = Locale.getDefault().getLanguage();
      if (!"en".equals(language))
      {
        byte[] translations = Utils.getResource("/org/h2/res/_messages_" + language + ".prop");
        if (translations != null)
        {
          Properties p = SortedProperties.fromLines(new String(translations, Constants.UTF8));
          for (Map.Entry<Object, Object> e : p.entrySet())
          {
            String key = (String)e.getKey();
            String translation = (String)e.getValue();
            if ((translation != null) && (!translation.startsWith("#")))
            {
              String original = MESSAGES.getProperty(key);
              String message = translation + "\n" + original;
              MESSAGES.put(key, message);
            }
          }
        }
      }
    }
    catch (OutOfMemoryError e)
    {
      traceThrowable(e);
    }
    catch (IOException e)
    {
      traceThrowable(e);
    }
  }
  
  private DbException(SQLException e)
  {
    super(e.getMessage(), e);
  }
  
  private static String translate(String key, String... params)
  {
    String message = null;
    if (MESSAGES != null) {
      message = MESSAGES.getProperty(key);
    }
    if (message == null) {
      message = "(Message " + key + " not found)";
    }
    if (params != null)
    {
      for (int i = 0; i < params.length; i++)
      {
        String s = params[i];
        if ((s != null) && (s.length() > 0)) {
          params[i] = StringUtils.quoteIdentifier(s);
        }
      }
      message = MessageFormat.format(message, (Object[])params);
    }
    return message;
  }
  
  public SQLException getSQLException()
  {
    return (SQLException)getCause();
  }
  
  public int getErrorCode()
  {
    return getSQLException().getErrorCode();
  }
  
  public DbException addSQL(String sql)
  {
    SQLException e = getSQLException();
    if ((e instanceof JdbcSQLException))
    {
      JdbcSQLException j = (JdbcSQLException)e;
      if (j.getSQL() == null) {
        j.setSQL(sql);
      }
      return this;
    }
    e = new JdbcSQLException(e.getMessage(), sql, e.getSQLState(), e.getErrorCode(), e, null);
    
    return new DbException(e);
  }
  
  public static DbException get(int errorCode)
  {
    return get(errorCode, (String)null);
  }
  
  public static DbException get(int errorCode, String p1)
  {
    return get(errorCode, new String[] { p1 });
  }
  
  public static DbException get(int errorCode, Throwable cause, String... params)
  {
    return new DbException(getJdbcSQLException(errorCode, cause, params));
  }
  
  public static DbException get(int errorCode, String... params)
  {
    return new DbException(getJdbcSQLException(errorCode, null, params));
  }
  
  public static DbException getSyntaxError(String sql, int index)
  {
    sql = StringUtils.addAsterisk(sql, index);
    return get(42000, sql);
  }
  
  public static DbException getSyntaxError(String sql, int index, String message)
  {
    sql = StringUtils.addAsterisk(sql, index);
    return new DbException(getJdbcSQLException(42001, null, new String[] { sql, message }));
  }
  
  public static DbException getUnsupportedException(String message)
  {
    return get(50100, message);
  }
  
  public static DbException getInvalidValueException(String param, Object value)
  {
    return get(90008, new String[] { value == null ? "null" : value.toString(), param });
  }
  
  public static RuntimeException throwInternalError(String s)
  {
    RuntimeException e = new RuntimeException(s);
    traceThrowable(e);
    throw e;
  }
  
  public static RuntimeException throwInternalError()
  {
    return throwInternalError("Unexpected code path");
  }
  
  public static SQLException toSQLException(Exception e)
  {
    if ((e instanceof SQLException)) {
      return (SQLException)e;
    }
    return convert(e).getSQLException();
  }
  
  public static DbException convert(Throwable e)
  {
    if ((e instanceof DbException)) {
      return (DbException)e;
    }
    if ((e instanceof SQLException)) {
      return new DbException((SQLException)e);
    }
    if ((e instanceof InvocationTargetException)) {
      return convertInvocation((InvocationTargetException)e, null);
    }
    if ((e instanceof IOException)) {
      return get(90028, e, new String[] { e.toString() });
    }
    if ((e instanceof OutOfMemoryError)) {
      return get(90108, e, new String[0]);
    }
    if (((e instanceof StackOverflowError)) || ((e instanceof LinkageError))) {
      return get(50000, e, new String[] { e.toString() });
    }
    if ((e instanceof Error)) {
      throw ((Error)e);
    }
    return get(50000, e, new String[] { e.toString() });
  }
  
  public static DbException convertInvocation(InvocationTargetException te, String message)
  {
    Throwable t = te.getTargetException();
    if (((t instanceof SQLException)) || ((t instanceof DbException))) {
      return convert(t);
    }
    message = message + ": " + t.getMessage();
    return get(90105, t, new String[] { message });
  }
  
  public static DbException convertIOException(IOException e, String message)
  {
    if (message == null)
    {
      Throwable t = e.getCause();
      if ((t instanceof DbException)) {
        return (DbException)t;
      }
      return get(90028, e, new String[] { e.toString() });
    }
    return get(90031, e, new String[] { e.toString(), message });
  }
  
  private static JdbcSQLException getJdbcSQLException(int errorCode, Throwable cause, String... params)
  {
    String sqlstate = ErrorCode.getState(errorCode);
    String message = translate(sqlstate, params);
    return new JdbcSQLException(message, null, sqlstate, errorCode, cause, null);
  }
  
  public static IOException convertToIOException(Throwable e)
  {
    if ((e instanceof IOException)) {
      return (IOException)e;
    }
    if ((e instanceof JdbcSQLException))
    {
      JdbcSQLException e2 = (JdbcSQLException)e;
      if (e2.getOriginalCause() != null) {
        e = e2.getOriginalCause();
      }
    }
    return new IOException(e.toString(), e);
  }
  
  public Object getSource()
  {
    return this.source;
  }
  
  public void setSource(Object source)
  {
    this.source = source;
  }
  
  public static void traceThrowable(Throwable e)
  {
    PrintWriter writer = DriverManager.getLogWriter();
    if (writer != null) {
      e.printStackTrace(writer);
    }
  }
}
