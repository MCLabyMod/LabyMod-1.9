package org.h2.expression;

import org.h2.command.Parser;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.FunctionAlias;
import org.h2.engine.FunctionAlias.JavaMethod;
import org.h2.engine.Session;
import org.h2.schema.Schema;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StatementBuilder;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueNull;
import org.h2.value.ValueResultSet;

public class JavaFunction
  extends Expression
  implements FunctionCall
{
  private final FunctionAlias functionAlias;
  private final FunctionAlias.JavaMethod javaMethod;
  private final Expression[] args;
  
  public JavaFunction(FunctionAlias functionAlias, Expression[] args)
  {
    this.functionAlias = functionAlias;
    this.javaMethod = functionAlias.findJavaMethod(args);
    this.args = args;
  }
  
  public Value getValue(Session session)
  {
    return this.javaMethod.getValue(session, this.args, false);
  }
  
  public int getType()
  {
    return this.javaMethod.getDataType();
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    for (Expression e : this.args) {
      e.mapColumns(resolver, level);
    }
  }
  
  public Expression optimize(Session session)
  {
    boolean allConst = isDeterministic();
    int i = 0;
    for (int len = this.args.length; i < len; i++)
    {
      Expression e = this.args[i].optimize(session);
      this.args[i] = e;
      allConst &= e.isConstant();
    }
    if (allConst) {
      return ValueExpression.get(getValue(session));
    }
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    for (Expression e : this.args) {
      if (e != null) {
        e.setEvaluatable(tableFilter, b);
      }
    }
  }
  
  public int getScale()
  {
    return DataType.getDataType(getType()).defaultScale;
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
    StatementBuilder buff = new StatementBuilder();
    if ((this.functionAlias.getDatabase().getSettings().functionsInSchema) || (!this.functionAlias.getSchema().getName().equals("PUBLIC"))) {
      buff.append(Parser.quoteIdentifier(this.functionAlias.getSchema().getName())).append('.');
    }
    buff.append(Parser.quoteIdentifier(this.functionAlias.getName())).append('(');
    for (Expression e : this.args)
    {
      buff.appendExceptFirst(", ");
      buff.append(e.getSQL());
    }
    return buff.append(')').toString();
  }
  
  public void updateAggregate(Session session)
  {
    for (Expression e : this.args) {
      if (e != null) {
        e.updateAggregate(session);
      }
    }
  }
  
  public String getName()
  {
    return this.functionAlias.getName();
  }
  
  public ValueResultSet getValueForColumnList(Session session, Expression[] argList)
  {
    Value v = this.javaMethod.getValue(session, argList, true);
    return v == ValueNull.INSTANCE ? null : (ValueResultSet)v;
  }
  
  public Expression[] getArgs()
  {
    return this.args;
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 2: 
      if (!isDeterministic()) {
        return false;
      }
      break;
    case 7: 
      visitor.addDependency(this.functionAlias);
      break;
    }
    for (Expression e : this.args) {
      if ((e != null) && (!e.isEverything(visitor))) {
        return false;
      }
    }
    return true;
  }
  
  public int getCost()
  {
    int cost = this.javaMethod.hasConnectionParam() ? 25 : 5;
    for (Expression e : this.args) {
      cost += e.getCost();
    }
    return cost;
  }
  
  public boolean isDeterministic()
  {
    return this.functionAlias.isDeterministic();
  }
  
  public Expression[] getExpressionColumns(Session session)
  {
    switch (getType())
    {
    case 18: 
      ValueResultSet rs = getValueForColumnList(session, getArgs());
      return getExpressionColumns(session, rs.getResultSet());
    case 17: 
      return getExpressionColumns(session, (ValueArray)getValue(session));
    }
    return super.getExpressionColumns(session);
  }
  
  public boolean isBufferResultSetToLocalTemp()
  {
    return this.functionAlias.isBufferResultSetToLocalTemp();
  }
}
