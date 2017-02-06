package org.h2.expression;

import org.h2.engine.Session;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class ConditionNot
  extends Condition
{
  private Expression condition;
  
  public ConditionNot(Expression condition)
  {
    this.condition = condition;
  }
  
  public Expression getNotIfPossible(Session session)
  {
    return this.condition;
  }
  
  public Value getValue(Session session)
  {
    Value v = this.condition.getValue(session);
    if (v == ValueNull.INSTANCE) {
      return v;
    }
    return v.convertTo(1).negate();
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.condition.mapColumns(resolver, level);
  }
  
  public Expression optimize(Session session)
  {
    Expression e2 = this.condition.getNotIfPossible(session);
    if (e2 != null) {
      return e2.optimize(session);
    }
    Expression expr = this.condition.optimize(session);
    if (expr.isConstant())
    {
      Value v = expr.getValue(session);
      if (v == ValueNull.INSTANCE) {
        return ValueExpression.getNull();
      }
      return ValueExpression.get(v.convertTo(1).negate());
    }
    this.condition = expr;
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.condition.setEvaluatable(tableFilter, b);
  }
  
  public String getSQL()
  {
    return "(NOT " + this.condition.getSQL() + ")";
  }
  
  public void updateAggregate(Session session)
  {
    this.condition.updateAggregate(session);
  }
  
  public void addFilterConditions(TableFilter filter, boolean outerJoin)
  {
    if (outerJoin) {
      return;
    }
    super.addFilterConditions(filter, outerJoin);
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return this.condition.isEverything(visitor);
  }
  
  public int getCost()
  {
    return this.condition.getCost();
  }
}
