package org.h2.expression;

import java.util.ArrayList;
import org.h2.command.dml.Query;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueNull;

public class Subquery
  extends Expression
{
  private final Query query;
  private Expression expression;
  
  public Subquery(Query query)
  {
    this.query = query;
  }
  
  public Value getValue(Session session)
  {
    this.query.setSession(session);
    ResultInterface result = this.query.query(2);
    try
    {
      int rowcount = result.getRowCount();
      if (rowcount > 1) {
        throw DbException.get(90053);
      }
      Value v;
      Value[] values;
      Value v;
      if (rowcount <= 0)
      {
        v = ValueNull.INSTANCE;
      }
      else
      {
        result.next();
        values = result.currentRow();
        Value v;
        if (result.getVisibleColumnCount() == 1) {
          v = values[0];
        } else {
          v = ValueArray.get(values);
        }
      }
      return v;
    }
    finally
    {
      result.close();
    }
  }
  
  public int getType()
  {
    return getExpression().getType();
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.query.mapColumns(resolver, level + 1);
  }
  
  public Expression optimize(Session session)
  {
    this.query.prepare();
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.query.setEvaluatable(tableFilter, b);
  }
  
  public int getScale()
  {
    return getExpression().getScale();
  }
  
  public long getPrecision()
  {
    return getExpression().getPrecision();
  }
  
  public int getDisplaySize()
  {
    return getExpression().getDisplaySize();
  }
  
  public String getSQL()
  {
    return "(" + this.query.getPlanSQL() + ")";
  }
  
  public void updateAggregate(Session session)
  {
    this.query.updateAggregate(session);
  }
  
  private Expression getExpression()
  {
    if (this.expression == null)
    {
      ArrayList<Expression> expressions = this.query.getExpressions();
      int columnCount = this.query.getColumnCount();
      if (columnCount == 1)
      {
        this.expression = ((Expression)expressions.get(0));
      }
      else
      {
        Expression[] list = new Expression[columnCount];
        for (int i = 0; i < columnCount; i++) {
          list[i] = ((Expression)expressions.get(i));
        }
        this.expression = new ExpressionList(list);
      }
    }
    return this.expression;
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return this.query.isEverything(visitor);
  }
  
  public Query getQuery()
  {
    return this.query;
  }
  
  public int getCost()
  {
    return this.query.getCostAsExpression();
  }
  
  public Expression[] getExpressionColumns(Session session)
  {
    return getExpression().getExpressionColumns(session);
  }
}
