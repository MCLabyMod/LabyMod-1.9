package org.h2.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.h2.message.DbException;
import org.h2.util.JdbcUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class TableLinkConnection
{
  private final HashMap<TableLinkConnection, TableLinkConnection> map;
  private final String driver;
  private final String url;
  private final String user;
  private final String password;
  private Connection conn;
  private int useCounter;
  
  private TableLinkConnection(HashMap<TableLinkConnection, TableLinkConnection> map, String driver, String url, String user, String password)
  {
    this.map = map;
    this.driver = driver;
    this.url = url;
    this.user = user;
    this.password = password;
  }
  
  public static TableLinkConnection open(HashMap<TableLinkConnection, TableLinkConnection> map, String driver, String url, String user, String password, boolean shareLinkedConnections)
  {
    TableLinkConnection t = new TableLinkConnection(map, driver, url, user, password);
    if (!shareLinkedConnections)
    {
      t.open();
      return t;
    }
    synchronized (map)
    {
      TableLinkConnection result = (TableLinkConnection)map.get(t);
      if (result == null)
      {
        t.open();
        
        map.put(t, t);
        result = t;
      }
      result.useCounter += 1;
      return result;
    }
  }
  
  private void open()
  {
    try
    {
      this.conn = JdbcUtils.getConnection(this.driver, this.url, this.user, this.password);
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  public int hashCode()
  {
    return Utils.hashCode(this.driver) ^ Utils.hashCode(this.url) ^ Utils.hashCode(this.user) ^ Utils.hashCode(this.password);
  }
  
  public boolean equals(Object o)
  {
    if ((o instanceof TableLinkConnection))
    {
      TableLinkConnection other = (TableLinkConnection)o;
      return (StringUtils.equals(this.driver, other.driver)) && (StringUtils.equals(this.url, other.url)) && (StringUtils.equals(this.user, other.user)) && (StringUtils.equals(this.password, other.password));
    }
    return false;
  }
  
  Connection getConnection()
  {
    return this.conn;
  }
  
  void close(boolean force)
  {
    boolean actuallyClose = false;
    synchronized (this.map)
    {
      if ((--this.useCounter <= 0) || (force))
      {
        actuallyClose = true;
        this.map.remove(this);
      }
    }
    if (actuallyClose) {
      JdbcUtils.closeSilently(this.conn);
    }
  }
}
