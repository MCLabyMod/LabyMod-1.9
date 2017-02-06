package org.h2.expression;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.index.IndexCondition;
import org.h2.message.DbException;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;

public class ConditionInConstantSet
  extends Condition
{
  private Expression left;
  private int queryLevel;
  private final ArrayList<Expression> valueList;
  private final TreeSet<Value> valueSet;
  
  public ConditionInConstantSet(final Session session, Expression left, ArrayList<Expression> valueList)
  {
    this.left = left;
    this.valueList = valueList;
    this.valueSet = new TreeSet(new Comparator()
    {
      public int compare(Value o1, Value o2)
      {
        return session.getDatabase().compare(o1, o2);
      }
    });
    int type = left.getType();
    for (Expression expression : valueList) {
      this.valueSet.add(expression.getValue(session).convertTo(type));
    }
  }
  
  public Value getValue(Session session)
  {
    Value x = this.left.getValue(session);
    if (x == ValueNull.INSTANCE) {
      return x;
    }
    boolean result = this.valueSet.contains(x);
    if (!result)
    {
      boolean setHasNull = this.valueSet.contains(ValueNull.INSTANCE);
      if (setHasNull) {
        return ValueNull.INSTANCE;
      }
    }
    return ValueBoolean.get(result);
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.left.mapColumns(resolver, level);
    this.queryLevel = Math.max(level, this.queryLevel);
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    return this;
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    if (!(this.left instanceof ExpressionColumn)) {
      return;
    }
    ExpressionColumn l = (ExpressionColumn)this.left;
    if (filter != l.getTableFilter()) {
      return;
    }
    if (session.getDatabase().getSettings().optimizeInList)
    {
      filter.addIndexCondition(IndexCondition.getInList(l, this.valueList));
      return;
    }
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
  }
  
  public String getSQL()
  {
    StatementBuilder buff = new StatementBuilder("(");
    buff.append(this.left.getSQL()).append(" IN(");
    for (Expression e : this.valueList)
    {
      buff.appendExceptFirst(", ");
      buff.append(e.getSQL());
    }
    return buff.append("))").toString();
  }
  
  public void updateAggregate(Session session)
  {
    this.left.updateAggregate(session);
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    if (!this.left.isEverything(visitor)) {
      return false;
    }
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
    int cost = this.left.getCost();
    return cost;
  }
  
  public boolean isDisjunctive()
  {
    return true;
  }
  
  Expression getAdditional(Session session, Comparison other)
  {
    Expression add = other.getIfEquals(this.left);
    if ((add != null) && 
      (add.isConstant()))
    {
      this.valueList.add(add);
      this.valueSet.add(add.getValue(session).convertTo(this.left.getType()));
      return this;
    }
    return null;
  }
}
