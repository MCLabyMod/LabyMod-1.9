package org.h2.command.dml;

import java.sql.ResultSet;
import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionVisitor;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.value.Value;

public class Call
  extends Prepared
{
  private boolean isResultSet;
  private Expression expression;
  private Expression[] expressions;
  
  public Call(Session session)
  {
    super(session);
  }
  
  public ResultInterface queryMeta()
  {
    LocalResult result;
    LocalResult result;
    if (this.isResultSet)
    {
      Expression[] expr = this.expression.getExpressionColumns(this.session);
      result = new LocalResult(this.session, expr, expr.length);
    }
    else
    {
      result = new LocalResult(this.session, this.expressions, 1);
    }
    result.done();
    return result;
  }
  
  public int update()
  {
    Value v = this.expression.getValue(this.session);
    int type = v.getType();
    switch (type)
    {
    case 18: 
      return super.update();
    case -1: 
    case 0: 
      return 0;
    }
    return v.getInt();
  }
  
  public ResultInterface query(int maxrows)
  {
    setCurrentRowNumber(1);
    Value v = this.expression.getValue(this.session);
    if (this.isResultSet)
    {
      v = v.convertTo(18);
      ResultSet rs = v.getResultSet();
      return LocalResult.read(this.session, rs, maxrows);
    }
    LocalResult result = new LocalResult(this.session, this.expressions, 1);
    Value[] row = { v };
    result.addRow(row);
    result.done();
    return result;
  }
  
  public void prepare()
  {
    this.expression = this.expression.optimize(this.session);
    this.expressions = new Expression[] { this.expression };
    this.isResultSet = (this.expression.getType() == 18);
    if (this.isResultSet) {
      this.prepareAlways = true;
    }
  }
  
  public void setExpression(Expression expression)
  {
    this.expression = expression;
  }
  
  public boolean isQuery()
  {
    return true;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return this.expression.isEverything(ExpressionVisitor.READONLY_VISITOR);
  }
  
  public int getType()
  {
    return 57;
  }
  
  public boolean isCacheable()
  {
    return !this.isResultSet;
  }
}
