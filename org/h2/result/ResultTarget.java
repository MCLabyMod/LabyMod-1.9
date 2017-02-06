package org.h2.result;

import org.h2.value.Value;

public abstract interface ResultTarget
{
  public abstract void addRow(Value[] paramArrayOfValue);
  
  public abstract int getRowCount();
}
