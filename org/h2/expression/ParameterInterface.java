package org.h2.expression;

import org.h2.message.DbException;
import org.h2.value.Value;

public abstract interface ParameterInterface
{
  public abstract void setValue(Value paramValue, boolean paramBoolean);
  
  public abstract Value getParamValue();
  
  public abstract void checkSet()
    throws DbException;
  
  public abstract boolean isValueSet();
  
  public abstract int getType();
  
  public abstract long getPrecision();
  
  public abstract int getScale();
  
  public abstract int getNullable();
}
