package org.h2.expression;

import org.h2.command.dml.Query;
import org.h2.engine.Session;
import org.h2.result.LocalResult;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;

public class ConditionExists
  extends Condition
{
  private final Query query;
  
  public ConditionExists(Query query)
  {
    this.query = query;
  }
  
  public Value getValue(Session session)
  {
    this.query.setSession(session);
    LocalResult result = this.query.query(1);
    session.addTemporaryResult(result);
    boolean r = result.getRowCount() > 0;
    return ValueBoolean.get(r);
  }
  
  public Expression optimize(Session session)
  {
    this.query.prepare();
    return this;
  }
  
  public String getSQL()
  {
    return "EXISTS(\n" + StringUtils.indent(this.query.getPlanSQL(), 4, false) + ")";
  }
  
  public void updateAggregate(Session session) {}
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.query.mapColumns(resolver, level + 1);
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.query.setEvaluatable(tableFilter, b);
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return this.query.isEverything(visitor);
  }
  
  public int getCost()
  {
    return this.query.getCostAsExpression();
  }
}
