package org.h2.tools;

import java.sql.SQLException;

public abstract interface SimpleRowSource
{
  public abstract Object[] readRow()
    throws SQLException;
  
  public abstract void close();
  
  public abstract void reset()
    throws SQLException;
}
