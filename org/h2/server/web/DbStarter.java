package org.h2.server.web;

import java.sql.Connection;
import org.h2.tools.Server;

public class DbStarter
{
  private Connection conn;
  private Server server;
  
  public Connection getConnection()
  {
    return this.conn;
  }
}
