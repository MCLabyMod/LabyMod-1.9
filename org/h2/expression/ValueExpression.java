package org.h2.expression;

import org.h2.engine.Session;
import org.h2.index.IndexCondition;
import org.h2.message.DbException;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;

public class ValueExpression
  extends Expression
{
  private static final Object NULL = new ValueExpression(ValueNull.INSTANCE);
  private static final Object DEFAULT = new ValueExpression(ValueNull.INSTANCE);
  private final Value value;
  
  private ValueExpression(Value value)
  {
    this.value = value;
  }
  
  public static ValueExpression getNull()
  {
    return (ValueExpression)NULL;
  }
  
  public static ValueExpression getDefault()
  {
    return (ValueExpression)DEFAULT;
  }
  
  public static ValueExpression get(Value value)
  {
    if (value == ValueNull.INSTANCE) {
      return getNull();
    }
    return new ValueExpression(value);
  }
  
  public Value getValue(Session session)
  {
    return this.value;
  }
  
  public int getType()
  {
    return this.value.getType();
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    if (this.value.getType() == 1)
    {
      boolean v = ((ValueBoolean)this.value).getBoolean().booleanValue();
      if (!v) {
        filter.addIndexCondition(IndexCondition.get(8, null, this));
      }
    }
  }
  
  public Expression getNotIfPossible(Session session)
  {
    return new Comparison(session, 0, this, get(ValueBoolean.get(false)));
  }
  
  public void mapColumns(ColumnResolver resolver, int level) {}
  
  public Expression optimize(Session session)
  {
    return this;
  }
  
  public boolean isConstant()
  {
    return true;
  }
  
  public boolean isValueSet()
  {
    return true;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b) {}
  
  public int getScale()
  {
    return this.value.getScale();
  }
  
  public long getPrecision()
  {
    return this.value.getPrecision();
  }
  
  public int getDisplaySize()
  {
    return this.value.getDisplaySize();
  }
  
  public String getSQL()
  {
    if (this == DEFAULT) {
      return "DEFAULT";
    }
    return this.value.getSQL();
  }
  
  public void updateAggregate(Session session) {}
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
      return true;
    }
    throw DbException.throwInternalError("type=" + visitor.getType());
  }
  
  public int getCost()
  {
    return 0;
  }
  
  public Expression[] getExpressionColumns(Session session)
  {
    if (getType() == 17) {
      return getExpressionColumns(session, (ValueArray)getValue(session));
    }
    return super.getExpressionColumns(session);
  }
}
