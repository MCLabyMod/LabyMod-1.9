package org.h2.jdbcx;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import org.h2.Driver;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.TraceObject;
import org.h2.util.StringUtils;

public class JdbcDataSource
  extends TraceObject
  implements XADataSource, DataSource, ConnectionPoolDataSource, Serializable, Referenceable
{
  private static final long serialVersionUID = 1288136338451857771L;
  private transient JdbcDataSourceFactory factory;
  private transient PrintWriter logWriter;
  private int loginTimeout;
  private String userName = "";
  private char[] passwordChars = new char[0];
  private String url = "";
  private String description;
  
  static
  {
    Driver.load();
  }
  
  public JdbcDataSource()
  {
    initFactory();
    int id = getNextId(12);
    setTrace(this.factory.getTrace(), 12, id);
  }
  
  private void readObject(ObjectInputStream in)
    throws IOException, ClassNotFoundException
  {
    initFactory();
    in.defaultReadObject();
  }
  
  private void initFactory()
  {
    this.factory = new JdbcDataSourceFactory();
  }
  
  public int getLoginTimeout()
  {
    debugCodeCall("getLoginTimeout");
    return this.loginTimeout;
  }
  
  public void setLoginTimeout(int timeout)
  {
    debugCodeCall("setLoginTimeout", timeout);
    this.loginTimeout = timeout;
  }
  
  public PrintWriter getLogWriter()
  {
    debugCodeCall("getLogWriter");
    return this.logWriter;
  }
  
  public void setLogWriter(PrintWriter out)
  {
    debugCodeCall("setLogWriter(out)");
    this.logWriter = out;
  }
  
  public Connection getConnection()
    throws SQLException
  {
    debugCodeCall("getConnection");
    return getJdbcConnection(this.userName, StringUtils.cloneCharArray(this.passwordChars));
  }
  
  public Connection getConnection(String user, String password)
    throws SQLException
  {
    if (isDebugEnabled()) {
      debugCode("getConnection(" + quote(user) + ", \"\");");
    }
    return getJdbcConnection(user, convertToCharArray(password));
  }
  
  private JdbcConnection getJdbcConnection(String user, char[] password)
    throws SQLException
  {
    if (isDebugEnabled()) {
      debugCode("getJdbcConnection(" + quote(user) + ", new char[0]);");
    }
    Properties info = new Properties();
    info.setProperty("user", user);
    info.put("password", password);
    Connection conn = Driver.load().connect(this.url, info);
    if (conn == null) {
      throw new SQLException("No suitable driver found for " + this.url, "08001", 8001);
    }
    if (!(conn instanceof JdbcConnection)) {
      throw new SQLException("Connecting with old version is not supported: " + this.url, "08001", 8001);
    }
    return (JdbcConnection)conn;
  }
  
  public String getURL()
  {
    debugCodeCall("getURL");
    return this.url;
  }
  
  public void setURL(String url)
  {
    debugCodeCall("setURL", url);
    this.url = url;
  }
  
  public String getUrl()
  {
    debugCodeCall("getUrl");
    return this.url;
  }
  
  public void setUrl(String url)
  {
    debugCodeCall("setUrl", url);
    this.url = url;
  }
  
  public void setPassword(String password)
  {
    debugCodeCall("setPassword", "");
    this.passwordChars = convertToCharArray(password);
  }
  
  public void setPasswordChars(char[] password)
  {
    if (isDebugEnabled()) {
      debugCode("setPasswordChars(new char[0]);");
    }
    this.passwordChars = password;
  }
  
  private static char[] convertToCharArray(String s)
  {
    return s == null ? null : s.toCharArray();
  }
  
  private static String convertToString(char[] a)
  {
    return a == null ? null : new String(a);
  }
  
  public String getPassword()
  {
    debugCodeCall("getPassword");
    return convertToString(this.passwordChars);
  }
  
  public String getUser()
  {
    debugCodeCall("getUser");
    return this.userName;
  }
  
  public void setUser(String user)
  {
    debugCodeCall("setUser", user);
    this.userName = user;
  }
  
  public String getDescription()
  {
    debugCodeCall("getDescription");
    return this.description;
  }
  
  public void setDescription(String description)
  {
    debugCodeCall("getDescription", description);
    this.description = description;
  }
  
  public Reference getReference()
  {
    debugCodeCall("getReference");
    String factoryClassName = JdbcDataSourceFactory.class.getName();
    Reference ref = new Reference(getClass().getName(), factoryClassName, null);
    ref.add(new StringRefAddr("url", this.url));
    ref.add(new StringRefAddr("user", this.userName));
    ref.add(new StringRefAddr("password", convertToString(this.passwordChars)));
    ref.add(new StringRefAddr("loginTimeout", String.valueOf(this.loginTimeout)));
    ref.add(new StringRefAddr("description", this.description));
    return ref;
  }
  
  public XAConnection getXAConnection()
    throws SQLException
  {
    debugCodeCall("getXAConnection");
    int id = getNextId(13);
    return new JdbcXAConnection(this.factory, id, getJdbcConnection(this.userName, StringUtils.cloneCharArray(this.passwordChars)));
  }
  
  public XAConnection getXAConnection(String user, String password)
    throws SQLException
  {
    if (isDebugEnabled()) {
      debugCode("getXAConnection(" + quote(user) + ", \"\");");
    }
    int id = getNextId(13);
    return new JdbcXAConnection(this.factory, id, getJdbcConnection(user, convertToCharArray(password)));
  }
  
  public PooledConnection getPooledConnection()
    throws SQLException
  {
    debugCodeCall("getPooledConnection");
    return getXAConnection();
  }
  
  public PooledConnection getPooledConnection(String user, String password)
    throws SQLException
  {
    if (isDebugEnabled()) {
      debugCode("getPooledConnection(" + quote(user) + ", \"\");");
    }
    return getXAConnection(user, password);
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    throw unsupported("unwrap");
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    throw unsupported("isWrapperFor");
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": url=" + this.url + " user=" + this.userName;
  }
  
  public Logger getParentLogger()
    throws SQLFeatureNotSupportedException
  {
    return null;
  }
}
