package org.h2.expression;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.StringUtils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;

public abstract class Expression
{
  private boolean addedToFilter;
  
  public abstract Value getValue(Session paramSession);
  
  public abstract int getType();
  
  public abstract void mapColumns(ColumnResolver paramColumnResolver, int paramInt);
  
  public abstract Expression optimize(Session paramSession);
  
  public abstract void setEvaluatable(TableFilter paramTableFilter, boolean paramBoolean);
  
  public abstract int getScale();
  
  public abstract long getPrecision();
  
  public abstract int getDisplaySize();
  
  public abstract String getSQL();
  
  public abstract void updateAggregate(Session paramSession);
  
  public abstract boolean isEverything(ExpressionVisitor paramExpressionVisitor);
  
  public abstract int getCost();
  
  public Expression getNotIfPossible(Session session)
  {
    return null;
  }
  
  public boolean isConstant()
  {
    return false;
  }
  
  public boolean isValueSet()
  {
    return false;
  }
  
  public boolean isAutoIncrement()
  {
    return false;
  }
  
  public Boolean getBooleanValue(Session session)
  {
    return getValue(session).getBoolean();
  }
  
  public void createIndexConditions(Session session, TableFilter filter) {}
  
  public String getColumnName()
  {
    return getAlias();
  }
  
  public String getSchemaName()
  {
    return null;
  }
  
  public String getTableName()
  {
    return null;
  }
  
  public int getNullable()
  {
    return 2;
  }
  
  public String getTableAlias()
  {
    return null;
  }
  
  public String getAlias()
  {
    return StringUtils.unEnclose(getSQL());
  }
  
  public boolean isWildcard()
  {
    return false;
  }
  
  public Expression getNonAliasExpression()
  {
    return this;
  }
  
  public boolean isDisjunctive()
  {
    return false;
  }
  
  public void addFilterConditions(TableFilter filter, boolean outerJoin)
  {
    if ((!this.addedToFilter) && (!outerJoin) && (isEverything(ExpressionVisitor.EVALUATABLE_VISITOR)))
    {
      filter.addFilterCondition(this, false);
      this.addedToFilter = true;
    }
  }
  
  public String toString()
  {
    return getSQL();
  }
  
  public Expression[] getExpressionColumns(Session session)
  {
    return null;
  }
  
  static Expression[] getExpressionColumns(Session session, ValueArray value)
  {
    Value[] list = value.getList();
    ExpressionColumn[] expr = new ExpressionColumn[list.length];
    int i = 0;
    for (int len = list.length; i < len; i++)
    {
      Value v = list[i];
      Column col = new Column("C" + (i + 1), v.getType(), v.getPrecision(), v.getScale(), v.getDisplaySize());
      
      expr[i] = new ExpressionColumn(session.getDatabase(), col);
    }
    return expr;
  }
  
  public static Expression[] getExpressionColumns(Session session, ResultSet rs)
  {
    try
    {
      ResultSetMetaData meta = rs.getMetaData();
      int columnCount = meta.getColumnCount();
      Expression[] expressions = new Expression[columnCount];
      Database db = session == null ? null : session.getDatabase();
      for (int i = 0; i < columnCount; i++)
      {
        String name = meta.getColumnLabel(i + 1);
        int type = DataType.getValueTypeFromResultSet(meta, i + 1);
        int precision = meta.getPrecision(i + 1);
        int scale = meta.getScale(i + 1);
        int displaySize = meta.getColumnDisplaySize(i + 1);
        Column col = new Column(name, type, precision, scale, displaySize);
        Expression expr = new ExpressionColumn(db, col);
        expressions[i] = expr;
      }
      return expressions;
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
}
