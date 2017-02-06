package org.h2.expression;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.h2.api.Aggregate;
import org.h2.command.Parser;
import org.h2.command.dml.Select;
import org.h2.engine.Session;
import org.h2.engine.UserAggregate;
import org.h2.message.DbException;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StatementBuilder;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class JavaAggregate
  extends Expression
{
  private final UserAggregate userAggregate;
  private final Select select;
  private final Expression[] args;
  private int[] argTypes;
  private int dataType;
  private Connection userConnection;
  private int lastGroupRowId;
  
  public JavaAggregate(UserAggregate userAggregate, Expression[] args, Select select)
  {
    this.userAggregate = userAggregate;
    this.args = args;
    this.select = select;
  }
  
  public int getCost()
  {
    int cost = 5;
    for (Expression e : this.args) {
      cost += e.getCost();
    }
    return cost;
  }
  
  public long getPrecision()
  {
    return 2147483647L;
  }
  
  public int getDisplaySize()
  {
    return Integer.MAX_VALUE;
  }
  
  public int getScale()
  {
    return DataType.getDataType(this.dataType).defaultScale;
  }
  
  public String getSQL()
  {
    StatementBuilder buff = new StatementBuilder();
    buff.append(Parser.quoteIdentifier(this.userAggregate.getName())).append('(');
    for (Expression e : this.args)
    {
      buff.appendExceptFirst(", ");
      buff.append(e.getSQL());
    }
    return buff.append(')').toString();
  }
  
  public int getType()
  {
    return this.dataType;
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 1: 
    case 2: 
      return false;
    case 7: 
      visitor.addDependency(this.userAggregate);
      break;
    }
    for (Expression e : this.args) {
      if ((e != null) && (!e.isEverything(visitor))) {
        return false;
      }
    }
    return true;
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    for (Expression arg : this.args) {
      arg.mapColumns(resolver, level);
    }
  }
  
  public Expression optimize(Session session)
  {
    this.userConnection = session.createConnection(false);
    int len = this.args.length;
    this.argTypes = new int[len];
    for (int i = 0; i < len; i++)
    {
      Expression expr = this.args[i];
      this.args[i] = expr.optimize(session);
      int type = expr.getType();
      this.argTypes[i] = type;
    }
    try
    {
      Aggregate aggregate = getInstance();
      this.dataType = aggregate.getInternalType(this.argTypes);
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    for (Expression e : this.args) {
      e.setEvaluatable(tableFilter, b);
    }
  }
  
  private Aggregate getInstance()
    throws SQLException
  {
    Aggregate agg = this.userAggregate.getInstance();
    agg.init(this.userConnection);
    return agg;
  }
  
  public Value getValue(Session session)
  {
    HashMap<Expression, Object> group = this.select.getCurrentGroup();
    if (group == null) {
      throw DbException.get(90054, getSQL());
    }
    try
    {
      Aggregate agg = (Aggregate)group.get(this);
      if (agg == null) {
        agg = getInstance();
      }
      Object obj = agg.getResult();
      if (obj == null) {
        return ValueNull.INSTANCE;
      }
      return null;
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  public void updateAggregate(Session session)
  {
    HashMap<Expression, Object> group = this.select.getCurrentGroup();
    if (group == null) {
      return;
    }
    int groupRowId = this.select.getCurrentGroupRowId();
    if (this.lastGroupRowId == groupRowId) {
      return;
    }
    this.lastGroupRowId = groupRowId;
    
    Aggregate agg = (Aggregate)group.get(this);
    try
    {
      if (agg == null)
      {
        agg = getInstance();
        group.put(this, agg);
      }
      Object[] argValues = new Object[this.args.length];
      Object arg = null;
      int i = 0;
      for (int len = this.args.length; i < len; i++)
      {
        Value v = this.args[i].getValue(session);
        v = v.convertTo(this.argTypes[i]);
        arg = v.getObject();
        argValues[i] = arg;
      }
      if (this.args.length == 1) {
        agg.add(arg);
      } else {
        agg.add(argValues);
      }
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
}
