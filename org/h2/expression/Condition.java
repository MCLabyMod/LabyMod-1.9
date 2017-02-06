package org.h2.expression;

abstract class Condition
  extends Expression
{
  public int getType()
  {
    return 1;
  }
  
  public int getScale()
  {
    return 0;
  }
  
  public long getPrecision()
  {
    return 1L;
  }
  
  public int getDisplaySize()
  {
    return 5;
  }
}
