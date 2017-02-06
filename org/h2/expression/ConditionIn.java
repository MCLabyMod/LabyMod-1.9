package org.h2.expression;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.index.IndexCondition;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;

public class ConditionIn
  extends Condition
{
  private final Database database;
  private Expression left;
  private final ArrayList<Expression> valueList;
  private int queryLevel;
  
  public ConditionIn(Database database, Expression left, ArrayList<Expression> values)
  {
    this.database = database;
    this.left = left;
    this.valueList = values;
  }
  
  public Value getValue(Session session)
  {
    Value l = this.left.getValue(session);
    if (l == ValueNull.INSTANCE) {
      return l;
    }
    boolean result = false;
    boolean hasNull = false;
    for (Expression e : this.valueList)
    {
      Value r = e.getValue(session);
      if (r == ValueNull.INSTANCE)
      {
        hasNull = true;
      }
      else
      {
        r = r.convertTo(l.getType());
        result = Comparison.compareNotNull(this.database, l, r, 0);
        if (result) {
          break;
        }
      }
    }
    if ((!result) && (hasNull)) {
      return ValueNull.INSTANCE;
    }
    return ValueBoolean.get(result);
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.left.mapColumns(resolver, level);
    for (Expression e : this.valueList) {
      e.mapColumns(resolver, level);
    }
    this.queryLevel = Math.max(level, this.queryLevel);
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    boolean constant = this.left.isConstant();
    if ((constant) && (this.left == ValueExpression.getNull())) {
      return this.left;
    }
    boolean allValuesConstant = true;
    boolean allValuesNull = true;
    int size = this.valueList.size();
    for (int i = 0; i < size; i++)
    {
      Expression e = (Expression)this.valueList.get(i);
      e = e.optimize(session);
      if ((e.isConstant()) && (e.getValue(session) != ValueNull.INSTANCE)) {
        allValuesNull = false;
      }
      if ((allValuesConstant) && (!e.isConstant())) {
        allValuesConstant = false;
      }
      if (((this.left instanceof ExpressionColumn)) && ((e instanceof Parameter))) {
        ((Parameter)e).setColumn(((ExpressionColumn)this.left).getColumn());
      }
      this.valueList.set(i, e);
    }
    if ((constant) && (allValuesConstant)) {
      return ValueExpression.get(getValue(session));
    }
    if (size == 1)
    {
      Expression right = (Expression)this.valueList.get(0);
      Expression expr = new Comparison(session, 0, this.left, right);
      expr = expr.optimize(session);
      return expr;
    }
    if ((allValuesConstant) && (!allValuesNull))
    {
      int leftType = this.left.getType();
      if (leftType == -1) {
        return this;
      }
      Expression expr = new ConditionInConstantSet(session, this.left, this.valueList);
      expr = expr.optimize(session);
      return expr;
    }
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
      ExpressionVisitor visitor = ExpressionVisitor.getNotFromResolverVisitor(filter);
      for (Expression e : this.valueList) {
        if (!e.isEverything(visitor)) {
          return;
        }
      }
      filter.addIndexCondition(IndexCondition.getInList(l, this.valueList));
      return;
    }
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
    for (Expression e : this.valueList) {
      e.setEvaluatable(tableFilter, b);
    }
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
    for (Expression e : this.valueList) {
      e.updateAggregate(session);
    }
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    if (!this.left.isEverything(visitor)) {
      return false;
    }
    return areAllValues(visitor);
  }
  
  private boolean areAllValues(ExpressionVisitor visitor)
  {
    for (Expression e : this.valueList) {
      if (!e.isEverything(visitor)) {
        return false;
      }
    }
    return true;
  }
  
  public int getCost()
  {
    int cost = this.left.getCost();
    for (Expression e : this.valueList) {
      cost += e.getCost();
    }
    return cost;
  }
  
  public boolean isDisjunctive()
  {
    return true;
  }
  
  Expression getAdditional(Comparison other)
  {
    Expression add = other.getIfEquals(this.left);
    if (add != null)
    {
      this.valueList.add(add);
      return this;
    }
    return null;
  }
}
