package org.h2.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import org.h2.command.dml.Query;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ExpressionVisitor;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.util.StatementBuilder;
import org.h2.value.CompareMode;
import org.h2.value.Value;

public class IndexCondition
{
  public static final int EQUALITY = 1;
  public static final int START = 2;
  public static final int END = 4;
  public static final int RANGE = 6;
  public static final int ALWAYS_FALSE = 8;
  public static final int SPATIAL_INTERSECTS = 16;
  private final Column column;
  private final int compareType;
  private final Expression expression;
  private List<Expression> expressionList;
  private Query expressionQuery;
  
  private IndexCondition(int compareType, ExpressionColumn column, Expression expression)
  {
    this.compareType = compareType;
    this.column = (column == null ? null : column.getColumn());
    this.expression = expression;
  }
  
  public static IndexCondition get(int compareType, ExpressionColumn column, Expression expression)
  {
    return new IndexCondition(compareType, column, expression);
  }
  
  public static IndexCondition getInList(ExpressionColumn column, List<Expression> list)
  {
    IndexCondition cond = new IndexCondition(9, column, null);
    
    cond.expressionList = list;
    return cond;
  }
  
  public static IndexCondition getInQuery(ExpressionColumn column, Query query)
  {
    IndexCondition cond = new IndexCondition(10, column, null);
    
    cond.expressionQuery = query;
    return cond;
  }
  
  public Value getCurrentValue(Session session)
  {
    return this.expression.getValue(session);
  }
  
  public Value[] getCurrentValueList(Session session)
  {
    HashSet<Value> valueSet = new HashSet();
    for (Expression e : this.expressionList)
    {
      Value v = e.getValue(session);
      v = this.column.convert(v);
      valueSet.add(v);
    }
    Value[] array = new Value[valueSet.size()];
    valueSet.toArray(array);
    final CompareMode mode = session.getDatabase().getCompareMode();
    Arrays.sort(array, new Comparator()
    {
      public int compare(Value o1, Value o2)
      {
        return o1.compareTo(o2, mode);
      }
    });
    return array;
  }
  
  public ResultInterface getCurrentResult()
  {
    return this.expressionQuery.query(0);
  }
  
  public String getSQL()
  {
    if (this.compareType == 8) {
      return "FALSE";
    }
    StatementBuilder buff = new StatementBuilder();
    buff.append(this.column.getSQL());
    switch (this.compareType)
    {
    case 0: 
      buff.append(" = ");
      break;
    case 16: 
      buff.append(" IS ");
      break;
    case 1: 
      buff.append(" >= ");
      break;
    case 2: 
      buff.append(" > ");
      break;
    case 3: 
      buff.append(" <= ");
      break;
    case 4: 
      buff.append(" < ");
      break;
    case 9: 
      buff.append(" IN(");
      for (Expression e : this.expressionList)
      {
        buff.appendExceptFirst(", ");
        buff.append(e.getSQL());
      }
      buff.append(')');
      break;
    case 10: 
      buff.append(" IN(");
      buff.append(this.expressionQuery.getPlanSQL());
      buff.append(')');
      break;
    case 11: 
      buff.append(" && ");
      break;
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    default: 
      DbException.throwInternalError("type=" + this.compareType);
    }
    if (this.expression != null) {
      buff.append(this.expression.getSQL());
    }
    return buff.toString();
  }
  
  public int getMask(ArrayList<IndexCondition> indexConditions)
  {
    switch (this.compareType)
    {
    case 8: 
      return 8;
    case 0: 
    case 16: 
      return 1;
    case 9: 
    case 10: 
      if ((indexConditions.size() > 1) && 
        (!"TABLE".equals(this.column.getTable().getTableType()))) {
        return 0;
      }
      return 1;
    case 1: 
    case 2: 
      return 2;
    case 3: 
    case 4: 
      return 4;
    case 11: 
      return 16;
    }
    throw DbException.throwInternalError("type=" + this.compareType);
  }
  
  public boolean isAlwaysFalse()
  {
    return this.compareType == 8;
  }
  
  public boolean isStart()
  {
    switch (this.compareType)
    {
    case 0: 
    case 1: 
    case 2: 
    case 16: 
      return true;
    }
    return false;
  }
  
  public boolean isEnd()
  {
    switch (this.compareType)
    {
    case 0: 
    case 3: 
    case 4: 
    case 16: 
      return true;
    }
    return false;
  }
  
  public boolean isSpatialIntersects()
  {
    switch (this.compareType)
    {
    case 11: 
      return true;
    }
    return false;
  }
  
  public boolean isEquality(boolean constantExpression)
  {
    switch (this.compareType)
    {
    case 0: 
    case 16: 
      return (!constantExpression) || (this.expression.isConstant());
    }
    return false;
  }
  
  public int getCompareType()
  {
    return this.compareType;
  }
  
  public Column getColumn()
  {
    return this.column;
  }
  
  public boolean isEvaluatable()
  {
    if (this.expression != null) {
      return this.expression.isEverything(ExpressionVisitor.EVALUATABLE_VISITOR);
    }
    if (this.expressionList != null)
    {
      for (Expression e : this.expressionList) {
        if (!e.isEverything(ExpressionVisitor.EVALUATABLE_VISITOR)) {
          return false;
        }
      }
      return true;
    }
    return this.expressionQuery.isEverything(ExpressionVisitor.EVALUATABLE_VISITOR);
  }
  
  public String toString()
  {
    return "column=" + this.column + ", compareType=" + compareTypeToString(this.compareType) + ", expression=" + this.expression + ", expressionList=" + this.expressionList.toString() + ", expressionQuery=" + this.expressionQuery;
  }
  
  private static String compareTypeToString(int i)
  {
    StatementBuilder s = new StatementBuilder();
    if ((i & 0x1) == 1)
    {
      s.appendExceptFirst("&");
      s.append("EQUALITY");
    }
    if ((i & 0x2) == 2)
    {
      s.appendExceptFirst("&");
      s.append("START");
    }
    if ((i & 0x4) == 4)
    {
      s.appendExceptFirst("&");
      s.append("END");
    }
    if ((i & 0x8) == 8)
    {
      s.appendExceptFirst("&");
      s.append("ALWAYS_FALSE");
    }
    if ((i & 0x10) == 16)
    {
      s.appendExceptFirst("&");
      s.append("SPATIAL_INTERSECTS");
    }
    return s.toString();
  }
}
