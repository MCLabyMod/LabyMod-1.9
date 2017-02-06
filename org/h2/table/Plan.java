package org.h2.table;

import java.util.ArrayList;
import java.util.HashMap;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionVisitor;
import org.h2.util.New;

public class Plan
{
  private final TableFilter[] filters;
  private final HashMap<TableFilter, PlanItem> planItems = New.hashMap();
  private final Expression[] allConditions;
  private final TableFilter[] allFilters;
  
  public Plan(TableFilter[] filters, int count, Expression condition)
  {
    this.filters = new TableFilter[count];
    System.arraycopy(filters, 0, this.filters, 0, count);
    final ArrayList<Expression> allCond = New.arrayList();
    final ArrayList<TableFilter> all = New.arrayList();
    if (condition != null) {
      allCond.add(condition);
    }
    for (int i = 0; i < count; i++)
    {
      TableFilter f = filters[i];
      f.visit(new TableFilter.TableFilterVisitor()
      {
        public void accept(TableFilter f)
        {
          all.add(f);
          if (f.getJoinCondition() != null) {
            allCond.add(f.getJoinCondition());
          }
        }
      });
    }
    this.allConditions = new Expression[allCond.size()];
    allCond.toArray(this.allConditions);
    this.allFilters = new TableFilter[all.size()];
    all.toArray(this.allFilters);
  }
  
  public PlanItem getItem(TableFilter filter)
  {
    return (PlanItem)this.planItems.get(filter);
  }
  
  public TableFilter[] getFilters()
  {
    return this.filters;
  }
  
  public void removeUnusableIndexConditions()
  {
    for (int i = 0; i < this.allFilters.length; i++)
    {
      TableFilter f = this.allFilters[i];
      setEvaluatable(f, true);
      if ((i < this.allFilters.length - 1) || (f.getSession().getDatabase().getSettings().earlyFilter)) {
        f.optimizeFullCondition(false);
      }
      f.removeUnusableIndexConditions();
    }
    for (TableFilter f : this.allFilters) {
      setEvaluatable(f, false);
    }
  }
  
  public double calculateCost(Session session)
  {
    double cost = 1.0D;
    boolean invalidPlan = false;
    int level = 1;
    for (TableFilter tableFilter : this.allFilters)
    {
      PlanItem item = tableFilter.getBestPlanItem(session, level++);
      this.planItems.put(tableFilter, item);
      cost += cost * item.cost;
      setEvaluatable(tableFilter, true);
      Expression on = tableFilter.getJoinCondition();
      if ((on != null) && 
        (!on.isEverything(ExpressionVisitor.EVALUATABLE_VISITOR)))
      {
        invalidPlan = true;
        break;
      }
    }
    if (invalidPlan) {
      cost = Double.POSITIVE_INFINITY;
    }
    for (TableFilter f : this.allFilters) {
      setEvaluatable(f, false);
    }
    return cost;
  }
  
  private void setEvaluatable(TableFilter filter, boolean b)
  {
    filter.setEvaluatable(filter, b);
    for (Expression e : this.allConditions) {
      e.setEvaluatable(filter, b);
    }
  }
}
