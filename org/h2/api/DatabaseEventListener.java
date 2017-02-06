package org.h2.api;

import java.sql.SQLException;
import java.util.EventListener;

public abstract interface DatabaseEventListener
  extends EventListener
{
  public static final int STATE_SCAN_FILE = 0;
  public static final int STATE_CREATE_INDEX = 1;
  public static final int STATE_RECOVER = 2;
  public static final int STATE_BACKUP_FILE = 3;
  public static final int STATE_RECONNECTED = 4;
  public static final int STATE_STATEMENT_START = 5;
  public static final int STATE_STATEMENT_END = 6;
  public static final int STATE_STATEMENT_PROGRESS = 7;
  
  public abstract void init(String paramString);
  
  public abstract void opened();
  
  public abstract void exceptionThrown(SQLException paramSQLException, String paramString);
  
  public abstract void setProgress(int paramInt1, String paramString, int paramInt2, int paramInt3);
  
  public abstract void closingDatabase();
}
