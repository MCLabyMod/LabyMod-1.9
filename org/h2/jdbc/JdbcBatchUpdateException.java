package org.h2.jdbc;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.BatchUpdateException;
import java.sql.SQLException;

public class JdbcBatchUpdateException
  extends BatchUpdateException
{
  private static final long serialVersionUID = 1L;
  
  JdbcBatchUpdateException(SQLException next, int[] updateCounts)
  {
    super(next.getMessage(), next.getSQLState(), next.getErrorCode(), updateCounts);
    setNextException(next);
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
      if (getNextException() != null) {
        getNextException().printStackTrace(s);
      }
    }
  }
  
  public void printStackTrace(PrintStream s)
  {
    if (s != null)
    {
      super.printStackTrace(s);
      if (getNextException() != null) {
        getNextException().printStackTrace(s);
      }
    }
  }
}
