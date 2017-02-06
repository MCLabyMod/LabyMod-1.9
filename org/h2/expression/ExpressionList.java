package org.h2.expression;

import org.h2.engine.Session;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueArray;

public class ExpressionList
  extends Expression
{
  private final Expression[] list;
  
  public ExpressionList(Expression[] list)
  {
    this.list = list;
  }
  
  public Value getValue(Session session)
  {
    Value[] v = new Value[this.list.length];
    for (int i = 0; i < this.list.length; i++) {
      v[i] = this.list[i].getValue(session);
    }
    return ValueArray.get(v);
  }
  
  public int getType()
  {
    return 17;
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    for (Expression e : this.list) {
      e.mapColumns(resolver, level);
    }
  }
  
  public Expression optimize(Session session)
  {
    boolean allConst = true;
    for (int i = 0; i < this.list.length; i++)
    {
      Expression e = this.list[i].optimize(session);
      if (!e.isConstant()) {
        allConst = false;
      }
      this.list[i] = e;
    }
    if (allConst) {
      return ValueExpression.get(getValue(session));
    }
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    for (Expression e : this.list) {
      e.setEvaluatable(tableFilter, b);
    }
  }
  
  public int getScale()
  {
    return 0;
  }
  
  public long getPrecision()
  {
    return 2147483647L;
  }
  
  public int getDisplaySize()
  {
    return Integer.MAX_VALUE;
  }
  
  public String getSQL()
  {
    StatementBuilder buff = new StatementBuilder("(");
    for (Expression e : this.list)
    {
      buff.appendExceptFirst(", ");
      buff.append(e.getSQL());
    }
    if (this.list.length == 1) {
      buff.append(',');
    }
    return buff.append(')').toString();
  }
  
  public void updateAggregate(Session session)
  {
    for (Expression e : this.list) {
      e.updateAggregate(session);
    }
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    for (Expression e : this.list) {
      if (!e.isEverything(visitor)) {
        return false;
      }
    }
    return true;
  }
  
  public int getCost()
  {
    int cost = 1;
    for (Expression e : this.list) {
      cost += e.getCost();
    }
    return cost;
  }
  
  public Expression[] getExpressionColumns(Session session)
  {
    ExpressionColumn[] expr = new ExpressionColumn[this.list.length];
    for (int i = 0; i < this.list.length; i++)
    {
      Expression e = this.list[i];
      Column col = new Column("C" + (i + 1), e.getType(), e.getPrecision(), e.getScale(), e.getDisplaySize());
      
      expr[i] = new ExpressionColumn(session.getDatabase(), col);
    }
    return expr;
  }
}
