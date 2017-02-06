package org.h2.expression;

import org.h2.command.Parser;
import org.h2.engine.Session;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;

public class Alias
  extends Expression
{
  private final String alias;
  private Expression expr;
  private final boolean aliasColumnName;
  
  public Alias(Expression expression, String alias, boolean aliasColumnName)
  {
    this.expr = expression;
    this.alias = alias;
    this.aliasColumnName = aliasColumnName;
  }
  
  public Expression getNonAliasExpression()
  {
    return this.expr;
  }
  
  public Value getValue(Session session)
  {
    return this.expr.getValue(session);
  }
  
  public int getType()
  {
    return this.expr.getType();
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.expr.mapColumns(resolver, level);
  }
  
  public Expression optimize(Session session)
  {
    this.expr = this.expr.optimize(session);
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.expr.setEvaluatable(tableFilter, b);
  }
  
  public int getScale()
  {
    return this.expr.getScale();
  }
  
  public long getPrecision()
  {
    return this.expr.getPrecision();
  }
  
  public int getDisplaySize()
  {
    return this.expr.getDisplaySize();
  }
  
  public boolean isAutoIncrement()
  {
    return this.expr.isAutoIncrement();
  }
  
  public String getSQL()
  {
    return this.expr.getSQL() + " AS " + Parser.quoteIdentifier(this.alias);
  }
  
  public void updateAggregate(Session session)
  {
    this.expr.updateAggregate(session);
  }
  
  public String getAlias()
  {
    return this.alias;
  }
  
  public int getNullable()
  {
    return this.expr.getNullable();
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return this.expr.isEverything(visitor);
  }
  
  public int getCost()
  {
    return this.expr.getCost();
  }
  
  public String getTableName()
  {
    if (this.aliasColumnName) {
      return super.getTableName();
    }
    return this.expr.getTableName();
  }
  
  public String getColumnName()
  {
    if ((!(this.expr instanceof ExpressionColumn)) || (this.aliasColumnName)) {
      return super.getColumnName();
    }
    return this.expr.getColumnName();
  }
}
