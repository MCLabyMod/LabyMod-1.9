package org.h2.expression;

import org.h2.command.dml.Query;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.index.IndexCondition;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;

public class ConditionInSelect
  extends Condition
{
  private final Database database;
  private Expression left;
  private final Query query;
  private final boolean all;
  private final int compareType;
  private int queryLevel;
  
  public ConditionInSelect(Database database, Expression left, Query query, boolean all, int compareType)
  {
    this.database = database;
    this.left = left;
    this.query = query;
    this.all = all;
    this.compareType = compareType;
  }
  
  public Value getValue(Session session)
  {
    this.query.setSession(session);
    this.query.setDistinct(true);
    LocalResult rows = this.query.query(0);
    try
    {
      Value l = this.left.getValue(session);
      Object localObject1;
      if (rows.getRowCount() == 0) {
        return ValueBoolean.get(this.all);
      }
      if (l == ValueNull.INSTANCE) {
        return l;
      }
      if (!session.getDatabase().getSettings().optimizeInSelect) {
        return getValueSlow(rows, l);
      }
      if ((this.all) || ((this.compareType != 0) && (this.compareType != 16))) {
        return getValueSlow(rows, l);
      }
      int dataType = rows.getColumnType(0);
      Object localObject2;
      if (dataType == 0) {
        return ValueBoolean.get(false);
      }
      l = l.convertTo(dataType);
      if (rows.containsDistinct(new Value[] { l })) {
        return ValueBoolean.get(true);
      }
      if (rows.containsDistinct(new Value[] { ValueNull.INSTANCE })) {
        return ValueNull.INSTANCE;
      }
      return ValueBoolean.get(false);
    }
    finally
    {
      rows.close();
    }
  }
  
  private Value getValueSlow(LocalResult rows, Value l)
  {
    boolean hasNull = false;
    boolean result = this.all;
    while (rows.next())
    {
      Value r = rows.currentRow()[0];
      boolean value;
      if (r == ValueNull.INSTANCE)
      {
        boolean value = false;
        hasNull = true;
      }
      else
      {
        value = Comparison.compareNotNull(this.database, l, r, this.compareType);
      }
      if ((!value) && (this.all))
      {
        result = false;
        break;
      }
      if ((value) && (!this.all))
      {
        result = true;
        break;
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
    this.query.mapColumns(resolver, level + 1);
    this.queryLevel = Math.max(level, this.queryLevel);
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    this.query.setRandomAccessResult(true);
    this.query.prepare();
    if (this.query.getColumnCount() != 1) {
      throw DbException.get(90052);
    }
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
    this.query.setEvaluatable(tableFilter, b);
  }
  
  public String getSQL()
  {
    StringBuilder buff = new StringBuilder();
    buff.append('(').append(this.left.getSQL()).append(' ');
    if (this.all) {
      buff.append(Comparison.getCompareOperator(this.compareType)).append(" ALL");
    } else if (this.compareType == 0) {
      buff.append("IN");
    } else {
      buff.append(Comparison.getCompareOperator(this.compareType)).append(" ANY");
    }
    buff.append("(\n").append(StringUtils.indent(this.query.getPlanSQL(), 4, false)).append("))");
    
    return buff.toString();
  }
  
  public void updateAggregate(Session session)
  {
    this.left.updateAggregate(session);
    this.query.updateAggregate(session);
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return (this.left.isEverything(visitor)) && (this.query.isEverything(visitor));
  }
  
  public int getCost()
  {
    return this.left.getCost() + this.query.getCostAsExpression();
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    if (!session.getDatabase().getSettings().optimizeInList) {
      return;
    }
    if (!(this.left instanceof ExpressionColumn)) {
      return;
    }
    ExpressionColumn l = (ExpressionColumn)this.left;
    if (filter != l.getTableFilter()) {
      return;
    }
    ExpressionVisitor visitor = ExpressionVisitor.getNotFromResolverVisitor(filter);
    if (!this.query.isEverything(visitor)) {
      return;
    }
    filter.addIndexCondition(IndexCondition.getInQuery(l, this.query));
  }
  
  public boolean isDisjunctive()
  {
    return true;
  }
}
