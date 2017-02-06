package org.h2.result;

import org.h2.value.Value;

public abstract interface ResultInterface
{
  public abstract void reset();
  
  public abstract Value[] currentRow();
  
  public abstract boolean next();
  
  public abstract int getRowId();
  
  public abstract int getVisibleColumnCount();
  
  public abstract int getRowCount();
  
  public abstract boolean needToClose();
  
  public abstract void close();
  
  public abstract String getAlias(int paramInt);
  
  public abstract String getSchemaName(int paramInt);
  
  public abstract String getTableName(int paramInt);
  
  public abstract String getColumnName(int paramInt);
  
  public abstract int getColumnType(int paramInt);
  
  public abstract long getColumnPrecision(int paramInt);
  
  public abstract int getColumnScale(int paramInt);
  
  public abstract int getDisplaySize(int paramInt);
  
  public abstract boolean isAutoIncrement(int paramInt);
  
  public abstract int getNullable(int paramInt);
  
  public abstract void setFetchSize(int paramInt);
  
  public abstract int getFetchSize();
}
