package org.h2.server;

import java.sql.SQLException;

public abstract interface Service
{
  public abstract void init(String... paramVarArgs)
    throws Exception;
  
  public abstract String getURL();
  
  public abstract void start()
    throws SQLException;
  
  public abstract void listen();
  
  public abstract void stop();
  
  public abstract boolean isRunning(boolean paramBoolean);
  
  public abstract boolean getAllowOthers();
  
  public abstract String getName();
  
  public abstract String getType();
  
  public abstract int getPort();
  
  public abstract boolean isDaemon();
}
