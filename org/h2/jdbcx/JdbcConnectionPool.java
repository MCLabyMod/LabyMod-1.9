package org.h2.jdbcx;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import org.h2.message.DbException;
import org.h2.util.New;

public class JdbcConnectionPool
  implements DataSource, ConnectionEventListener
{
  private static final int DEFAULT_TIMEOUT = 30;
  private static final int DEFAULT_MAX_CONNECTIONS = 10;
  private final ConnectionPoolDataSource dataSource;
  private final ArrayList<PooledConnection> recycledConnections = New.arrayList();
  private PrintWriter logWriter;
  private int maxConnections = 10;
  private int timeout = 30;
  private int activeConnections;
  private boolean isDisposed;
  
  protected JdbcConnectionPool(ConnectionPoolDataSource dataSource)
  {
    this.dataSource = dataSource;
    if (dataSource != null) {
      try
      {
        this.logWriter = dataSource.getLogWriter();
      }
      catch (SQLException e) {}
    }
  }
  
  public static JdbcConnectionPool create(ConnectionPoolDataSource dataSource)
  {
    return new JdbcConnectionPool(dataSource);
  }
  
  public static JdbcConnectionPool create(String url, String user, String password)
  {
    JdbcDataSource ds = new JdbcDataSource();
    ds.setURL(url);
    ds.setUser(user);
    ds.setPassword(password);
    return new JdbcConnectionPool(ds);
  }
  
  public synchronized void setMaxConnections(int max)
  {
    if (max < 1) {
      throw new IllegalArgumentException("Invalid maxConnections value: " + max);
    }
    this.maxConnections = max;
    
    notifyAll();
  }
  
  public synchronized int getMaxConnections()
  {
    return this.maxConnections;
  }
  
  public synchronized int getLoginTimeout()
  {
    return this.timeout;
  }
  
  public synchronized void setLoginTimeout(int seconds)
  {
    if (seconds == 0) {
      seconds = 30;
    }
    this.timeout = seconds;
  }
  
  public synchronized void dispose()
  {
    if (this.isDisposed) {
      return;
    }
    this.isDisposed = true;
    ArrayList<PooledConnection> list = this.recycledConnections;
    int i = 0;
    for (int size = list.size(); i < size; i++) {
      closeConnection((PooledConnection)list.get(i));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    long max = System.currentTimeMillis() + this.timeout * 1000;
    do
    {
      synchronized (this)
      {
        if (this.activeConnections < this.maxConnections) {
          return getConnectionNow();
        }
        try
        {
          wait(1000L);
        }
        catch (InterruptedException e) {}
      }
    } while (System.currentTimeMillis() <= max);
    throw new SQLException("Login timeout", "08001", 8001);
  }
  
  public Connection getConnection(String user, String password)
  {
    throw new UnsupportedOperationException();
  }
  
  private Connection getConnectionNow()
    throws SQLException
  {
    if (this.isDisposed) {
      throw new IllegalStateException("Connection pool has been disposed.");
    }
    PooledConnection pc;
    PooledConnection pc;
    if (!this.recycledConnections.isEmpty()) {
      pc = (PooledConnection)this.recycledConnections.remove(this.recycledConnections.size() - 1);
    } else {
      pc = this.dataSource.getPooledConnection();
    }
    Connection conn = pc.getConnection();
    this.activeConnections += 1;
    pc.addConnectionEventListener(this);
    return conn;
  }
  
  synchronized void recycleConnection(PooledConnection pc)
  {
    if (this.activeConnections <= 0) {
      throw new AssertionError();
    }
    this.activeConnections -= 1;
    if ((!this.isDisposed) && (this.activeConnections < this.maxConnections)) {
      this.recycledConnections.add(pc);
    } else {
      closeConnection(pc);
    }
    if (this.activeConnections >= this.maxConnections - 1) {
      notifyAll();
    }
  }
  
  private void closeConnection(PooledConnection pc)
  {
    try
    {
      pc.close();
    }
    catch (SQLException e)
    {
      if (this.logWriter != null) {
        e.printStackTrace(this.logWriter);
      }
    }
  }
  
  public void connectionClosed(ConnectionEvent event)
  {
    PooledConnection pc = (PooledConnection)event.getSource();
    pc.removeConnectionEventListener(this);
    recycleConnection(pc);
  }
  
  public void connectionErrorOccurred(ConnectionEvent event) {}
  
  public synchronized int getActiveConnections()
  {
    return this.activeConnections;
  }
  
  public PrintWriter getLogWriter()
  {
    return this.logWriter;
  }
  
  public void setLogWriter(PrintWriter logWriter)
  {
    this.logWriter = logWriter;
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    throw DbException.getUnsupportedException("unwrap");
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    throw DbException.getUnsupportedException("isWrapperFor");
  }
  
  public Logger getParentLogger()
    throws SQLFeatureNotSupportedException
  {
    return null;
  }
}
