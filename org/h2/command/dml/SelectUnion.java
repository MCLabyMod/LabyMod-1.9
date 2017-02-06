package org.h2.command.dml;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ExpressionVisitor;
import org.h2.expression.Parameter;
import org.h2.expression.ValueExpression;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.result.ResultTarget;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueNull;

public class SelectUnion
  extends Query
{
  public static final int UNION = 0;
  public static final int UNION_ALL = 1;
  public static final int EXCEPT = 2;
  public static final int INTERSECT = 3;
  private int unionType;
  private final Query left;
  private Query right;
  private ArrayList<Expression> expressions;
  private Expression[] expressionArray;
  private ArrayList<SelectOrderBy> orderList;
  private SortOrder sort;
  private boolean isPrepared;
  private boolean checkInit;
  private boolean isForUpdate;
  
  public SelectUnion(Session session, Query query)
  {
    super(session);
    this.left = query;
  }
  
  public void setUnionType(int type)
  {
    this.unionType = type;
  }
  
  public int getUnionType()
  {
    return this.unionType;
  }
  
  public void setRight(Query select)
  {
    this.right = select;
  }
  
  public Query getLeft()
  {
    return this.left;
  }
  
  public Query getRight()
  {
    return this.right;
  }
  
  public void setSQL(String sql)
  {
    this.sqlStatement = sql;
  }
  
  public void setOrder(ArrayList<SelectOrderBy> order)
  {
    this.orderList = order;
  }
  
  private Value[] convert(Value[] values, int columnCount)
  {
    Value[] newValues;
    Value[] newValues;
    if (columnCount == values.length) {
      newValues = values;
    } else {
      newValues = new Value[columnCount];
    }
    for (int i = 0; i < columnCount; i++)
    {
      Expression e = (Expression)this.expressions.get(i);
      newValues[i] = values[i].convertTo(e.getType());
    }
    return newValues;
  }
  
  public ResultInterface queryMeta()
  {
    int columnCount = this.left.getColumnCount();
    LocalResult result = new LocalResult(this.session, this.expressionArray, columnCount);
    result.done();
    return result;
  }
  
  public LocalResult getEmptyResult()
  {
    int columnCount = this.left.getColumnCount();
    return new LocalResult(this.session, this.expressionArray, columnCount);
  }
  
  protected LocalResult queryWithoutCache(int maxRows, ResultTarget target)
  {
    if (maxRows != 0)
    {
      int l;
      int l;
      if (this.limitExpr == null)
      {
        l = -1;
      }
      else
      {
        Value v = this.limitExpr.getValue(this.session);
        l = v == ValueNull.INSTANCE ? -1 : v.getInt();
      }
      if (l < 0) {
        l = maxRows;
      } else {
        l = Math.min(l, maxRows);
      }
      this.limitExpr = ValueExpression.get(ValueInt.get(l));
    }
    if ((this.session.getDatabase().getSettings().optimizeInsertFromSelect) && 
      (this.unionType == 1) && (target != null) && 
      (this.sort == null) && (!this.distinct) && (maxRows == 0) && (this.offsetExpr == null) && (this.limitExpr == null))
    {
      this.left.query(0, target);
      this.right.query(0, target);
      return null;
    }
    int columnCount = this.left.getColumnCount();
    LocalResult result = new LocalResult(this.session, this.expressionArray, columnCount);
    if (this.sort != null) {
      result.setSortOrder(this.sort);
    }
    if (this.distinct)
    {
      this.left.setDistinct(true);
      this.right.setDistinct(true);
      result.setDistinct();
    }
    if (this.randomAccessResult) {
      result.setRandomAccess();
    }
    switch (this.unionType)
    {
    case 0: 
    case 2: 
      this.left.setDistinct(true);
      this.right.setDistinct(true);
      result.setDistinct();
      break;
    case 1: 
      break;
    case 3: 
      this.left.setDistinct(true);
      this.right.setDistinct(true);
      break;
    default: 
      DbException.throwInternalError("type=" + this.unionType);
    }
    LocalResult l = this.left.query(0);
    LocalResult r = this.right.query(0);
    l.reset();
    r.reset();
    switch (this.unionType)
    {
    case 0: 
    case 1: 
      while (l.next()) {
        result.addRow(convert(l.currentRow(), columnCount));
      }
    }
    while (r.next())
    {
      result.addRow(convert(r.currentRow(), columnCount)); continue;
      while (l.next()) {
        result.addRow(convert(l.currentRow(), columnCount));
      }
      while (r.next())
      {
        result.removeDistinct(convert(r.currentRow(), columnCount)); continue;
        
        LocalResult temp = new LocalResult(this.session, this.expressionArray, columnCount);
        temp.setDistinct();
        temp.setRandomAccess();
        while (l.next()) {
          temp.addRow(convert(l.currentRow(), columnCount));
        }
        while (r.next())
        {
          Value[] values = convert(r.currentRow(), columnCount);
          if (temp.containsDistinct(values)) {
            result.addRow(values);
          }
          continue;
          
          DbException.throwInternalError("type=" + this.unionType);
        }
      }
    }
    if (this.offsetExpr != null) {
      result.setOffset(this.offsetExpr.getValue(this.session).getInt());
    }
    if (this.limitExpr != null)
    {
      Value v = this.limitExpr.getValue(this.session);
      if (v != ValueNull.INSTANCE) {
        result.setLimit(v.getInt());
      }
    }
    l.close();
    r.close();
    result.done();
    if (target != null)
    {
      while (result.next()) {
        target.addRow(result.currentRow());
      }
      result.close();
      return null;
    }
    return result;
  }
  
  public void init()
  {
    if ((SysProperties.CHECK) && (this.checkInit)) {
      DbException.throwInternalError();
    }
    this.checkInit = true;
    this.left.init();
    this.right.init();
    int len = this.left.getColumnCount();
    if (len != this.right.getColumnCount()) {
      throw DbException.get(21002);
    }
    ArrayList<Expression> le = this.left.getExpressions();
    
    this.expressions = New.arrayList();
    for (int i = 0; i < len; i++)
    {
      Expression l = (Expression)le.get(i);
      this.expressions.add(l);
    }
  }
  
  public void prepare()
  {
    if (this.isPrepared) {
      return;
    }
    if ((SysProperties.CHECK) && (!this.checkInit)) {
      DbException.throwInternalError("not initialized");
    }
    this.isPrepared = true;
    this.left.prepare();
    this.right.prepare();
    int len = this.left.getColumnCount();
    
    this.expressions = New.arrayList();
    ArrayList<Expression> le = this.left.getExpressions();
    ArrayList<Expression> re = this.right.getExpressions();
    for (int i = 0; i < len; i++)
    {
      Expression l = (Expression)le.get(i);
      Expression r = (Expression)re.get(i);
      int type = Value.getHigherOrder(l.getType(), r.getType());
      long prec = Math.max(l.getPrecision(), r.getPrecision());
      int scale = Math.max(l.getScale(), r.getScale());
      int displaySize = Math.max(l.getDisplaySize(), r.getDisplaySize());
      Column col = new Column(l.getAlias(), type, prec, scale, displaySize);
      Expression e = new ExpressionColumn(this.session.getDatabase(), col);
      this.expressions.add(e);
    }
    if (this.orderList != null)
    {
      initOrder(this.session, this.expressions, null, this.orderList, getColumnCount(), true, null);
      this.sort = prepareOrder(this.orderList, this.expressions.size());
      this.orderList = null;
    }
    this.expressionArray = new Expression[this.expressions.size()];
    this.expressions.toArray(this.expressionArray);
  }
  
  public double getCost()
  {
    return this.left.getCost() + this.right.getCost();
  }
  
  public HashSet<Table> getTables()
  {
    HashSet<Table> set = this.left.getTables();
    set.addAll(this.right.getTables());
    return set;
  }
  
  public ArrayList<Expression> getExpressions()
  {
    return this.expressions;
  }
  
  public void setForUpdate(boolean forUpdate)
  {
    this.left.setForUpdate(forUpdate);
    this.right.setForUpdate(forUpdate);
    this.isForUpdate = forUpdate;
  }
  
  public int getColumnCount()
  {
    return this.left.getColumnCount();
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
  
  public void addGlobalCondition(Parameter param, int columnId, int comparisonType)
  {
    addParameter(param);
    switch (this.unionType)
    {
    case 0: 
    case 1: 
    case 3: 
      this.left.addGlobalCondition(param, columnId, comparisonType);
      this.right.addGlobalCondition(param, columnId, comparisonType);
      break;
    case 2: 
      this.left.addGlobalCondition(param, columnId, comparisonType);
      break;
    default: 
      DbException.throwInternalError("type=" + this.unionType);
    }
  }
  
  public String getPlanSQL()
  {
    StringBuilder buff = new StringBuilder();
    buff.append('(').append(this.left.getPlanSQL()).append(')');
    switch (this.unionType)
    {
    case 1: 
      buff.append("\nUNION ALL\n");
      break;
    case 0: 
      buff.append("\nUNION\n");
      break;
    case 3: 
      buff.append("\nINTERSECT\n");
      break;
    case 2: 
      buff.append("\nEXCEPT\n");
      break;
    default: 
      DbException.throwInternalError("type=" + this.unionType);
    }
    buff.append('(').append(this.right.getPlanSQL()).append(')');
    Expression[] exprList = (Expression[])this.expressions.toArray(new Expression[this.expressions.size()]);
    if (this.sort != null) {
      buff.append("\nORDER BY ").append(this.sort.getSQL(exprList, exprList.length));
    }
    if (this.limitExpr != null)
    {
      buff.append("\nLIMIT ").append(StringUtils.unEnclose(this.limitExpr.getSQL()));
      if (this.offsetExpr != null) {
        buff.append("\nOFFSET ").append(StringUtils.unEnclose(this.offsetExpr.getSQL()));
      }
    }
    if (this.sampleSizeExpr != null) {
      buff.append("\nSAMPLE_SIZE ").append(StringUtils.unEnclose(this.sampleSizeExpr.getSQL()));
    }
    if (this.isForUpdate) {
      buff.append("\nFOR UPDATE");
    }
    return buff.toString();
  }
  
  public LocalResult query(int limit, ResultTarget target)
  {
    return queryWithoutCache(limit, target);
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return (this.left.isEverything(visitor)) && (this.right.isEverything(visitor));
  }
  
  public boolean isReadOnly()
  {
    return (this.left.isReadOnly()) && (this.right.isReadOnly());
  }
  
  public void updateAggregate(Session s)
  {
    this.left.updateAggregate(s);
    this.right.updateAggregate(s);
  }
  
  public void fireBeforeSelectTriggers()
  {
    this.left.fireBeforeSelectTriggers();
    this.right.fireBeforeSelectTriggers();
  }
  
  public int getType()
  {
    return 66;
  }
  
  public boolean allowGlobalConditions()
  {
    return (this.left.allowGlobalConditions()) && (this.right.allowGlobalConditions());
  }
}
