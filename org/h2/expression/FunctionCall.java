package org.h2.expression;

import org.h2.engine.Session;
import org.h2.value.ValueResultSet;

public abstract interface FunctionCall
{
  public abstract String getName();
  
  public abstract ValueResultSet getValueForColumnList(Session paramSession, Expression[] paramArrayOfExpression);
  
  public abstract int getType();
  
  public abstract Expression optimize(Session paramSession);
  
  public abstract Expression[] getArgs();
  
  public abstract String getSQL();
  
  public abstract boolean isDeterministic();
  
  public abstract boolean isBufferResultSetToLocalTemp();
}
