package org.h2.result;

import org.h2.value.Value;

public abstract interface SearchRow
{
  public static final SearchRow[] EMPTY_ARRAY = new SearchRow[0];
  
  public abstract int getColumnCount();
  
  public abstract Value getValue(int paramInt);
  
  public abstract void setValue(int paramInt, Value paramValue);
  
  public abstract void setKeyAndVersion(SearchRow paramSearchRow);
  
  public abstract int getVersion();
  
  public abstract void setKey(long paramLong);
  
  public abstract long getKey();
  
  public abstract int getMemory();
}
