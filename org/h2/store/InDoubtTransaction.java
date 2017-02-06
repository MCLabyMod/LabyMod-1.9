package org.h2.store;

public abstract interface InDoubtTransaction
{
  public static final int IN_DOUBT = 0;
  public static final int COMMIT = 1;
  public static final int ROLLBACK = 2;
  
  public abstract void setState(int paramInt);
  
  public abstract String getState();
  
  public abstract String getTransactionName();
}
