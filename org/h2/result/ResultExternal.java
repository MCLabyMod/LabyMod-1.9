package org.h2.result;

import java.util.ArrayList;
import org.h2.value.Value;

public abstract interface ResultExternal
{
  public abstract void reset();
  
  public abstract Value[] next();
  
  public abstract int addRow(Value[] paramArrayOfValue);
  
  public abstract int addRows(ArrayList<Value[]> paramArrayList);
  
  public abstract void done();
  
  public abstract void close();
  
  public abstract int removeRow(Value[] paramArrayOfValue);
  
  public abstract boolean contains(Value[] paramArrayOfValue);
  
  public abstract ResultExternal createShallowCopy();
}
