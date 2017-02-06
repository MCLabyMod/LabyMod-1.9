package org.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.upgrade.DbUpgrade;

public class Driver
  implements java.sql.Driver
{
  private static final Driver INSTANCE = new Driver();
  private static final String DEFAULT_URL = "jdbc:default:connection";
  private static final ThreadLocal<Connection> DEFAULT_CONNECTION = new ThreadLocal();
  private static volatile boolean registered;
  
  static
  {
    load();
  }
  
  public Connection connect(String url, Properties info)
    throws SQLException
  {
    try
    {
      if (info == null) {
        info = new Properties();
      }
      if (!acceptsURL(url)) {
        return null;
      }
      if (url.equals("jdbc:default:connection")) {
        return (Connection)DEFAULT_CONNECTION.get();
      }
      Connection c = DbUpgrade.connectOrUpgrade(url, info);
      if (c != null) {
        return c;
      }
      return new JdbcConnection(url, info);
    }
    catch (Exception e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  public boolean acceptsURL(String url)
  {
    if (url != null)
    {
      if (url.startsWith("jdbc:h2:")) {
        return true;
      }
      if (url.equals("jdbc:default:connection")) {
        return DEFAULT_CONNECTION.get() != null;
      }
    }
    return false;
  }
  
  public int getMajorVersion()
  {
    return 1;
  }
  
  public int getMinorVersion()
  {
    return 4;
  }
  
  public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
  {
    return new DriverPropertyInfo[0];
  }
  
  public boolean jdbcCompliant()
  {
    return true;
  }
  
  public static synchronized Driver load()
  {
    try
    {
      if (!registered)
      {
        registered = true;
        DriverManager.registerDriver(INSTANCE);
      }
    }
    catch (SQLException e)
    {
      DbException.traceThrowable(e);
    }
    return INSTANCE;
  }
  
  public static synchronized void unload()
  {
    try
    {
      if (registered)
      {
        registered = false;
        DriverManager.deregisterDriver(INSTANCE);
      }
    }
    catch (SQLException e)
    {
      DbException.traceThrowable(e);
    }
  }
  
  public static void setDefaultConnection(Connection c)
  {
    if (c == null) {
      DEFAULT_CONNECTION.remove();
    } else {
      DEFAULT_CONNECTION.set(c);
    }
  }
  
  public static void setThreadContextClassLoader(Thread thread)
  {
    try
    {
      thread.setContextClassLoader(Driver.class.getClassLoader());
    }
    catch (Throwable t) {}
  }
  
  public Logger getParentLogger()
    throws SQLFeatureNotSupportedException
  {
    return null;
  }
}
