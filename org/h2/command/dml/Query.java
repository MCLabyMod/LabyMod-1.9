package org.h2.command.dml;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Alias;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ExpressionVisitor;
import org.h2.expression.Parameter;
import org.h2.expression.ValueExpression;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.ResultTarget;
import org.h2.result.SortOrder;
import org.h2.table.ColumnResolver;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueNull;

public abstract class Query
  extends Prepared
{
  protected Expression limitExpr;
  protected Expression offsetExpr;
  protected Expression sampleSizeExpr;
  protected boolean distinct;
  protected boolean randomAccessResult;
  private boolean noCache;
  private int lastLimit;
  private long lastEvaluated;
  private LocalResult lastResult;
  private Value[] lastParameters;
  private boolean cacheableChecked;
  
  Query(Session session)
  {
    super(session);
  }
  
  protected abstract LocalResult queryWithoutCache(int paramInt, ResultTarget paramResultTarget);
  
  public abstract void init();
  
  public abstract ArrayList<Expression> getExpressions();
  
  public abstract double getCost();
  
  public int getCostAsExpression()
  {
    return (int)Math.min(1000000.0D, 10.0D + 10.0D * getCost());
  }
  
  public abstract HashSet<Table> getTables();
  
  public abstract void setOrder(ArrayList<SelectOrderBy> paramArrayList);
  
  public abstract void setForUpdate(boolean paramBoolean);
  
  public abstract int getColumnCount();
  
  public abstract void mapColumns(ColumnResolver paramColumnResolver, int paramInt);
  
  public abstract void setEvaluatable(TableFilter paramTableFilter, boolean paramBoolean);
  
  public abstract void addGlobalCondition(Parameter paramParameter, int paramInt1, int paramInt2);
  
  public abstract boolean allowGlobalConditions();
  
  public abstract boolean isEverything(ExpressionVisitor paramExpressionVisitor);
  
  public abstract void updateAggregate(Session paramSession);
  
  public abstract void fireBeforeSelectTriggers();
  
  public void setDistinct(boolean b)
  {
    this.distinct = b;
  }
  
  public boolean isDistinct()
  {
    return this.distinct;
  }
  
  public void setRandomAccessResult(boolean b)
  {
    this.randomAccessResult = b;
  }
  
  public boolean isQuery()
  {
    return true;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public void disableCache()
  {
    this.noCache = true;
  }
  
  private boolean sameResultAsLast(Session s, Value[] params, Value[] lastParams, long lastEval)
  {
    if (!this.cacheableChecked)
    {
      long max = getMaxDataModificationId();
      this.noCache = (max == Long.MAX_VALUE);
      this.cacheableChecked = true;
    }
    if (this.noCache) {
      return false;
    }
    Database db = s.getDatabase();
    for (int i = 0; i < params.length; i++)
    {
      Value a = lastParams[i];Value b = params[i];
      if ((a.getType() != b.getType()) || (!db.areEqual(a, b))) {
        return false;
      }
    }
    if ((!isEverything(ExpressionVisitor.DETERMINISTIC_VISITOR)) || (!isEverything(ExpressionVisitor.INDEPENDENT_VISITOR))) {
      return false;
    }
    if ((db.getModificationDataId() > lastEval) && (getMaxDataModificationId() > lastEval)) {
      return false;
    }
    return true;
  }
  
  public final Value[] getParameterValues()
  {
    ArrayList<Parameter> list = getParameters();
    if (list == null) {
      list = New.arrayList();
    }
    int size = list.size();
    Value[] params = new Value[size];
    for (int i = 0; i < size; i++)
    {
      Value v = ((Parameter)list.get(i)).getParamValue();
      params[i] = v;
    }
    return params;
  }
  
  public LocalResult query(int maxrows)
  {
    return query(maxrows, null);
  }
  
  LocalResult query(int limit, ResultTarget target)
  {
    fireBeforeSelectTriggers();
    if ((this.noCache) || (!this.session.getDatabase().getOptimizeReuseResults())) {
      return queryWithoutCache(limit, target);
    }
    Value[] params = getParameterValues();
    long now = this.session.getDatabase().getModificationDataId();
    if ((isEverything(ExpressionVisitor.DETERMINISTIC_VISITOR)) && 
      (this.lastResult != null) && (!this.lastResult.isClosed()) && (limit == this.lastLimit)) {
      if (sameResultAsLast(this.session, params, this.lastParameters, this.lastEvaluated))
      {
        this.lastResult = this.lastResult.createShallowCopy(this.session);
        if (this.lastResult != null)
        {
          this.lastResult.reset();
          return this.lastResult;
        }
      }
    }
    this.lastParameters = params;
    closeLastResult();
    LocalResult r = queryWithoutCache(limit, target);
    this.lastResult = r;
    this.lastEvaluated = now;
    this.lastLimit = limit;
    return r;
  }
  
  private void closeLastResult()
  {
    if (this.lastResult != null) {
      this.lastResult.close();
    }
  }
  
  static void initOrder(Session session, ArrayList<Expression> expressions, ArrayList<String> expressionSQL, ArrayList<SelectOrderBy> orderList, int visible, boolean mustBeInResult, ArrayList<TableFilter> filters)
  {
    Database db = session.getDatabase();
    for (SelectOrderBy o : orderList)
    {
      Expression e = o.expression;
      if (e != null)
      {
        boolean isAlias = false;
        int idx = expressions.size();
        if ((e instanceof ExpressionColumn))
        {
          ExpressionColumn exprCol = (ExpressionColumn)e;
          String tableAlias = exprCol.getOriginalTableAliasName();
          String col = exprCol.getOriginalColumnName();
          for (int j = 0; j < visible; j++)
          {
            boolean found = false;
            Expression ec = (Expression)expressions.get(j);
            if ((ec instanceof ExpressionColumn))
            {
              ExpressionColumn c = (ExpressionColumn)ec;
              found = db.equalsIdentifiers(col, c.getColumnName());
              if ((found) && (tableAlias != null))
              {
                String ca = c.getOriginalTableAliasName();
                if (ca == null)
                {
                  found = false;
                  if (filters != null)
                  {
                    int i = 0;
                    for (int size = filters.size(); i < size; i++)
                    {
                      TableFilter f = (TableFilter)filters.get(i);
                      if (db.equalsIdentifiers(f.getTableAlias(), tableAlias))
                      {
                        found = true;
                        break;
                      }
                    }
                  }
                }
                else
                {
                  found = db.equalsIdentifiers(ca, tableAlias);
                }
              }
            }
            else
            {
              if (!(ec instanceof Alias)) {
                continue;
              }
              if ((tableAlias == null) && (db.equalsIdentifiers(col, ec.getAlias())))
              {
                found = true;
              }
              else
              {
                Expression ec2 = ec.getNonAliasExpression();
                if ((ec2 instanceof ExpressionColumn))
                {
                  ExpressionColumn c2 = (ExpressionColumn)ec2;
                  String ta = exprCol.getSQL();
                  String tb = c2.getSQL();
                  String s2 = c2.getColumnName();
                  found = db.equalsIdentifiers(col, s2);
                  if (!db.equalsIdentifiers(ta, tb)) {
                    found = false;
                  }
                }
              }
            }
            if (found)
            {
              idx = j;
              isAlias = true;
              break;
            }
          }
        }
        else
        {
          String s = e.getSQL();
          if (expressionSQL != null)
          {
            int j = 0;
            for (int size = expressionSQL.size(); j < size; j++)
            {
              String s2 = (String)expressionSQL.get(j);
              if (db.equalsIdentifiers(s2, s))
              {
                idx = j;
                isAlias = true;
                break;
              }
            }
          }
        }
        if (!isAlias)
        {
          if (mustBeInResult) {
            throw DbException.get(90068, e.getSQL());
          }
          expressions.add(e);
          String sql = e.getSQL();
          expressionSQL.add(sql);
        }
        o.columnIndexExpr = ValueExpression.get(ValueInt.get(idx + 1));
        Expression expr = ((Expression)expressions.get(idx)).getNonAliasExpression();
        o.expression = expr;
      }
    }
  }
  
  public SortOrder prepareOrder(ArrayList<SelectOrderBy> orderList, int expressionCount)
  {
    int size = orderList.size();
    int[] index = new int[size];
    int[] sortType = new int[size];
    for (int i = 0; i < size; i++)
    {
      SelectOrderBy o = (SelectOrderBy)orderList.get(i);
      
      boolean reverse = false;
      Expression expr = o.columnIndexExpr;
      Value v = expr.getValue(null);
      int idx;
      int idx;
      if (v == ValueNull.INSTANCE)
      {
        idx = 0;
      }
      else
      {
        idx = v.getInt();
        if (idx < 0)
        {
          reverse = true;
          idx = -idx;
        }
        idx--;
        if ((idx < 0) || (idx >= expressionCount)) {
          throw DbException.get(90068, "" + (idx + 1));
        }
      }
      index[i] = idx;
      boolean desc = o.descending;
      if (reverse) {
        desc = !desc;
      }
      int type = desc ? 1 : 0;
      if (o.nullsFirst) {
        type += 2;
      } else if (o.nullsLast) {
        type += 4;
      }
      sortType[i] = type;
    }
    return new SortOrder(this.session.getDatabase(), index, sortType, orderList);
  }
  
  public void setOffset(Expression offset)
  {
    this.offsetExpr = offset;
  }
  
  public Expression getOffset()
  {
    return this.offsetExpr;
  }
  
  public void setLimit(Expression limit)
  {
    this.limitExpr = limit;
  }
  
  public Expression getLimit()
  {
    return this.limitExpr;
  }
  
  void addParameter(Parameter param)
  {
    if (this.parameters == null) {
      this.parameters = New.arrayList();
    }
    this.parameters.add(param);
  }
  
  public void setSampleSize(Expression sampleSize)
  {
    this.sampleSizeExpr = sampleSize;
  }
  
  int getSampleSizeValue(Session session)
  {
    if (this.sampleSizeExpr == null) {
      return 0;
    }
    Value v = this.sampleSizeExpr.optimize(session).getValue(session);
    if (v == ValueNull.INSTANCE) {
      return 0;
    }
    return v.getInt();
  }
  
  public final long getMaxDataModificationId()
  {
    ExpressionVisitor visitor = ExpressionVisitor.getMaxModificationIdVisitor();
    isEverything(visitor);
    return visitor.getMaxDataModificationId();
  }
}
