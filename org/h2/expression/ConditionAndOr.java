package org.h2.expression;

import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;

public class ConditionAndOr
  extends Condition
{
  public static final int AND = 0;
  public static final int OR = 1;
  private final int andOrType;
  private Expression left;
  private Expression right;
  
  public ConditionAndOr(int andOrType, Expression left, Expression right)
  {
    this.andOrType = andOrType;
    this.left = left;
    this.right = right;
    if ((SysProperties.CHECK) && ((left == null) || (right == null))) {
      DbException.throwInternalError();
    }
  }
  
  public String getSQL()
  {
    String sql;
    switch (this.andOrType)
    {
    case 0: 
      sql = this.left.getSQL() + "\n    AND " + this.right.getSQL();
      break;
    case 1: 
      sql = this.left.getSQL() + "\n    OR " + this.right.getSQL();
      break;
    default: 
      throw DbException.throwInternalError("andOrType=" + this.andOrType);
    }
    return "(" + sql + ")";
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    if (this.andOrType == 0)
    {
      this.left.createIndexConditions(session, filter);
      this.right.createIndexConditions(session, filter);
    }
  }
  
  public Expression getNotIfPossible(Session session)
  {
    Expression l = this.left.getNotIfPossible(session);
    if (l == null) {
      l = new ConditionNot(this.left);
    }
    Expression r = this.right.getNotIfPossible(session);
    if (r == null) {
      r = new ConditionNot(this.right);
    }
    int reversed = this.andOrType == 0 ? 1 : 0;
    return new ConditionAndOr(reversed, l, r);
  }
  
  public Value getValue(Session session)
  {
    Value l = this.left.getValue(session);
    Value r;
    switch (this.andOrType)
    {
    case 0: 
      if (Boolean.FALSE.equals(l.getBoolean())) {
        return l;
      }
      r = this.right.getValue(session);
      if (Boolean.FALSE.equals(r.getBoolean())) {
        return r;
      }
      if (l == ValueNull.INSTANCE) {
        return l;
      }
      if (r == ValueNull.INSTANCE) {
        return r;
      }
      return ValueBoolean.get(true);
    case 1: 
      if (Boolean.TRUE.equals(l.getBoolean())) {
        return l;
      }
      r = this.right.getValue(session);
      if (Boolean.TRUE.equals(r.getBoolean())) {
        return r;
      }
      if (l == ValueNull.INSTANCE) {
        return l;
      }
      if (r == ValueNull.INSTANCE) {
        return r;
      }
      return ValueBoolean.get(false);
    }
    throw DbException.throwInternalError("type=" + this.andOrType);
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    this.right = this.right.optimize(session);
    int lc = this.left.getCost();int rc = this.right.getCost();
    if (rc < lc)
    {
      Expression t = this.left;
      this.left = this.right;
      this.right = t;
    }
    if ((session.getDatabase().getSettings().optimizeTwoEquals) && (this.andOrType == 0)) {
      if (((this.left instanceof Comparison)) && ((this.right instanceof Comparison)))
      {
        Comparison compLeft = (Comparison)this.left;
        Comparison compRight = (Comparison)this.right;
        Expression added = compLeft.getAdditional(session, compRight, true);
        if (added != null)
        {
          added = added.optimize(session);
          ConditionAndOr a = new ConditionAndOr(0, this, added);
          return a;
        }
      }
    }
    if ((this.andOrType == 1) && (session.getDatabase().getSettings().optimizeOr)) {
      if (((this.left instanceof Comparison)) && ((this.right instanceof Comparison)))
      {
        Comparison compLeft = (Comparison)this.left;
        Comparison compRight = (Comparison)this.right;
        Expression added = compLeft.getAdditional(session, compRight, false);
        if (added != null) {
          return added.optimize(session);
        }
      }
      else if (((this.left instanceof ConditionIn)) && ((this.right instanceof Comparison)))
      {
        Expression added = ((ConditionIn)this.left).getAdditional((Comparison)this.right);
        if (added != null) {
          return added.optimize(session);
        }
      }
      else if (((this.right instanceof ConditionIn)) && ((this.left instanceof Comparison)))
      {
        Expression added = ((ConditionIn)this.right).getAdditional((Comparison)this.left);
        if (added != null) {
          return added.optimize(session);
        }
      }
      else if (((this.left instanceof ConditionInConstantSet)) && ((this.right instanceof Comparison)))
      {
        Expression added = ((ConditionInConstantSet)this.left).getAdditional(session, (Comparison)this.right);
        if (added != null) {
          return added.optimize(session);
        }
      }
      else if (((this.right instanceof ConditionInConstantSet)) && ((this.left instanceof Comparison)))
      {
        Expression added = ((ConditionInConstantSet)this.right).getAdditional(session, (Comparison)this.left);
        if (added != null) {
          return added.optimize(session);
        }
      }
    }
    Value l = this.left.isConstant() ? this.left.getValue(session) : null;
    Value r = this.right.isConstant() ? this.right.getValue(session) : null;
    if ((l == null) && (r == null)) {
      return this;
    }
    if ((l != null) && (r != null)) {
      return ValueExpression.get(getValue(session));
    }
    switch (this.andOrType)
    {
    case 0: 
      if (l != null)
      {
        if (Boolean.FALSE.equals(l.getBoolean())) {
          return ValueExpression.get(l);
        }
        if (Boolean.TRUE.equals(l.getBoolean())) {
          return this.right;
        }
      }
      else if (r != null)
      {
        if (Boolean.FALSE.equals(r.getBoolean())) {
          return ValueExpression.get(r);
        }
        if (Boolean.TRUE.equals(r.getBoolean())) {
          return this.left;
        }
      }
      break;
    case 1: 
      if (l != null)
      {
        if (Boolean.TRUE.equals(l.getBoolean())) {
          return ValueExpression.get(l);
        }
        if (Boolean.FALSE.equals(l.getBoolean())) {
          return this.right;
        }
      }
      else if (r != null)
      {
        if (Boolean.TRUE.equals(r.getBoolean())) {
          return ValueExpression.get(r);
        }
        if (Boolean.FALSE.equals(r.getBoolean())) {
          return this.left;
        }
      }
      break;
    default: 
      DbException.throwInternalError("type=" + this.andOrType);
    }
    return this;
  }
  
  public void addFilterConditions(TableFilter filter, boolean outerJoin)
  {
    if (this.andOrType == 0)
    {
      this.left.addFilterConditions(filter, outerJoin);
      this.right.addFilterConditions(filter, outerJoin);
    }
    else
    {
      super.addFilterConditions(filter, outerJoin);
    }
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.left.mapColumns(resolver, level);
    this.right.mapColumns(resolver, level);
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
    this.right.setEvaluatable(tableFilter, b);
  }
  
  public void updateAggregate(Session session)
  {
    this.left.updateAggregate(session);
    this.right.updateAggregate(session);
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return (this.left.isEverything(visitor)) && (this.right.isEverything(visitor));
  }
  
  public int getCost()
  {
    return this.left.getCost() + this.right.getCost();
  }
  
  public boolean isDisjunctive()
  {
    return (this.andOrType == 1) || (this.left.isDisjunctive()) || (this.right.isDisjunctive());
  }
  
  public Expression getExpression(boolean getLeft)
  {
    return getLeft ? this.left : this.right;
  }
}
