package org.h2.api;

import java.sql.Connection;
import java.sql.SQLException;

public abstract interface Trigger
{
  public static final int INSERT = 1;
  public static final int UPDATE = 2;
  public static final int DELETE = 4;
  public static final int SELECT = 8;
  
  public abstract void init(Connection paramConnection, String paramString1, String paramString2, String paramString3, boolean paramBoolean, int paramInt)
    throws SQLException;
  
  public abstract void fire(Connection paramConnection, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
    throws SQLException;
  
  public abstract void close()
    throws SQLException;
  
  public abstract void remove()
    throws SQLException;
}
