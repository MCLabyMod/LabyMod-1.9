package org.h2.expression;

import java.util.Arrays;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.index.IndexCondition;
import org.h2.message.DbException;
import org.h2.table.ColumnResolver;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueGeometry;
import org.h2.value.ValueNull;

public class Comparison
  extends Condition
{
  public static final int NULL_SAFE = 16;
  public static final int EQUAL = 0;
  public static final int EQUAL_NULL_SAFE = 16;
  public static final int BIGGER_EQUAL = 1;
  public static final int BIGGER = 2;
  public static final int SMALLER_EQUAL = 3;
  public static final int SMALLER = 4;
  public static final int NOT_EQUAL = 5;
  public static final int NOT_EQUAL_NULL_SAFE = 21;
  public static final int IS_NULL = 6;
  public static final int IS_NOT_NULL = 7;
  public static final int FALSE = 8;
  public static final int IN_LIST = 9;
  public static final int IN_QUERY = 10;
  public static final int SPATIAL_INTERSECTS = 11;
  private final Database database;
  private int compareType;
  private Expression left;
  private Expression right;
  
  public Comparison(Session session, int compareType, Expression left, Expression right)
  {
    this.database = session.getDatabase();
    this.left = left;
    this.right = right;
    this.compareType = compareType;
  }
  
  public String getSQL()
  {
    String sql;
    switch (this.compareType)
    {
    case 6: 
      sql = this.left.getSQL() + " IS NULL";
      break;
    case 7: 
      sql = this.left.getSQL() + " IS NOT NULL";
      break;
    case 11: 
      sql = "INTERSECTS(" + this.left.getSQL() + ", " + this.right.getSQL() + ")";
      break;
    default: 
      sql = this.left.getSQL() + " " + getCompareOperator(this.compareType) + " " + this.right.getSQL();
    }
    return "(" + sql + ")";
  }
  
  static String getCompareOperator(int compareType)
  {
    switch (compareType)
    {
    case 0: 
      return "=";
    case 16: 
      return "IS";
    case 1: 
      return ">=";
    case 2: 
      return ">";
    case 3: 
      return "<=";
    case 4: 
      return "<";
    case 5: 
      return "<>";
    case 21: 
      return "IS NOT";
    case 11: 
      return "&&";
    }
    throw DbException.throwInternalError("compareType=" + compareType);
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    if (this.right != null)
    {
      this.right = this.right.optimize(session);
      if (((this.right instanceof ExpressionColumn)) && (
        (this.left.isConstant()) || ((this.left instanceof Parameter))))
      {
        Expression temp = this.left;
        this.left = this.right;
        this.right = temp;
        this.compareType = getReversedCompareType(this.compareType);
      }
      if ((this.left instanceof ExpressionColumn)) {
        if (this.right.isConstant())
        {
          Value r = this.right.getValue(session);
          if ((r == ValueNull.INSTANCE) && 
            ((this.compareType & 0x10) == 0)) {
            return ValueExpression.getNull();
          }
        }
        else if ((this.right instanceof Parameter))
        {
          ((Parameter)this.right).setColumn(((ExpressionColumn)this.left).getColumn());
        }
      }
    }
    if ((this.compareType == 6) || (this.compareType == 7))
    {
      if (this.left.isConstant()) {
        return ValueExpression.get(getValue(session));
      }
    }
    else
    {
      if ((SysProperties.CHECK) && ((this.left == null) || (this.right == null))) {
        DbException.throwInternalError();
      }
      if ((this.left == ValueExpression.getNull()) || (this.right == ValueExpression.getNull())) {
        if ((this.compareType & 0x10) == 0) {
          return ValueExpression.getNull();
        }
      }
      if ((this.left.isConstant()) && (this.right.isConstant())) {
        return ValueExpression.get(getValue(session));
      }
    }
    return this;
  }
  
  public Value getValue(Session session)
  {
    Value l = this.left.getValue(session);
    if (this.right == null)
    {
      boolean result;
      switch (this.compareType)
      {
      case 6: 
        result = l == ValueNull.INSTANCE;
        break;
      case 7: 
        result = l != ValueNull.INSTANCE;
        break;
      default: 
        throw DbException.throwInternalError("type=" + this.compareType);
      }
      return ValueBoolean.get(result);
    }
    if ((l == ValueNull.INSTANCE) && 
      ((this.compareType & 0x10) == 0)) {
      return ValueNull.INSTANCE;
    }
    Value r = this.right.getValue(session);
    if ((r == ValueNull.INSTANCE) && 
      ((this.compareType & 0x10) == 0)) {
      return ValueNull.INSTANCE;
    }
    int dataType = Value.getHigherOrder(this.left.getType(), this.right.getType());
    l = l.convertTo(dataType);
    r = r.convertTo(dataType);
    boolean result = compareNotNull(this.database, l, r, this.compareType);
    return ValueBoolean.get(result);
  }
  
  static boolean compareNotNull(Database database, Value l, Value r, int compareType)
  {
    boolean result = false;
    ValueGeometry rg;
    switch (compareType)
    {
    case 0: 
    case 16: 
      result = database.areEqual(l, r);
      break;
    case 5: 
    case 21: 
      result = !database.areEqual(l, r);
      break;
    case 1: 
      result = database.compare(l, r) >= 0;
      break;
    case 2: 
      result = database.compare(l, r) > 0;
      break;
    case 3: 
      result = database.compare(l, r) <= 0;
      break;
    case 4: 
      result = database.compare(l, r) < 0;
      break;
    case 11: 
      ValueGeometry lg = (ValueGeometry)l.convertTo(22);
      rg = (ValueGeometry)r.convertTo(22);
      break;
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    default: 
      throw DbException.throwInternalError("type=" + compareType);
    }
    return result;
  }
  
  private int getReversedCompareType(int type)
  {
    switch (this.compareType)
    {
    case 0: 
    case 5: 
    case 11: 
    case 16: 
    case 21: 
      return type;
    case 1: 
      return 3;
    case 2: 
      return 4;
    case 3: 
      return 1;
    case 4: 
      return 2;
    }
    throw DbException.throwInternalError("type=" + this.compareType);
  }
  
  public Expression getNotIfPossible(Session session)
  {
    if (this.compareType == 11) {
      return null;
    }
    int type = getNotCompareType();
    return new Comparison(session, type, this.left, this.right);
  }
  
  private int getNotCompareType()
  {
    switch (this.compareType)
    {
    case 0: 
      return 5;
    case 16: 
      return 21;
    case 5: 
      return 0;
    case 21: 
      return 16;
    case 1: 
      return 4;
    case 2: 
      return 3;
    case 3: 
      return 2;
    case 4: 
      return 1;
    case 6: 
      return 7;
    case 7: 
      return 6;
    }
    throw DbException.throwInternalError("type=" + this.compareType);
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    if (!filter.getTable().isQueryComparable()) {
      return;
    }
    ExpressionColumn l = null;
    if ((this.left instanceof ExpressionColumn))
    {
      l = (ExpressionColumn)this.left;
      if (filter != l.getTableFilter()) {
        l = null;
      }
    }
    if (this.right == null)
    {
      if (l != null) {
        switch (this.compareType)
        {
        case 6: 
          if (session.getDatabase().getSettings().optimizeIsNull) {
            filter.addIndexCondition(IndexCondition.get(16, l, ValueExpression.getNull()));
          }
          break;
        }
      }
      return;
    }
    ExpressionColumn r = null;
    if ((this.right instanceof ExpressionColumn))
    {
      r = (ExpressionColumn)this.right;
      if (filter != r.getTableFilter()) {
        r = null;
      }
    }
    if ((l == null) && (r == null)) {
      return;
    }
    if ((l != null) && (r != null)) {
      return;
    }
    if (l == null)
    {
      ExpressionVisitor visitor = ExpressionVisitor.getNotFromResolverVisitor(filter);
      if (!this.left.isEverything(visitor)) {
        return;
      }
    }
    else if (r == null)
    {
      ExpressionVisitor visitor = ExpressionVisitor.getNotFromResolverVisitor(filter);
      if (!this.right.isEverything(visitor)) {
        return;
      }
    }
    else
    {
      return;
    }
    boolean addIndex;
    switch (this.compareType)
    {
    case 5: 
    case 21: 
      addIndex = false;
      break;
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 11: 
    case 16: 
      addIndex = true;
      break;
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    default: 
      throw DbException.throwInternalError("type=" + this.compareType);
    }
    if (addIndex) {
      if (l != null)
      {
        filter.addIndexCondition(IndexCondition.get(this.compareType, l, this.right));
      }
      else if (r != null)
      {
        int compareRev = getReversedCompareType(this.compareType);
        filter.addIndexCondition(IndexCondition.get(compareRev, r, this.left));
      }
    }
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
    if (this.right != null) {
      this.right.setEvaluatable(tableFilter, b);
    }
  }
  
  public void updateAggregate(Session session)
  {
    this.left.updateAggregate(session);
    if (this.right != null) {
      this.right.updateAggregate(session);
    }
  }
  
  public void addFilterConditions(TableFilter filter, boolean outerJoin)
  {
    if ((this.compareType == 6) && (outerJoin)) {
      return;
    }
    super.addFilterConditions(filter, outerJoin);
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.left.mapColumns(resolver, level);
    if (this.right != null) {
      this.right.mapColumns(resolver, level);
    }
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return (this.left.isEverything(visitor)) && ((this.right == null) || (this.right.isEverything(visitor)));
  }
  
  public int getCost()
  {
    return this.left.getCost() + (this.right == null ? 0 : this.right.getCost()) + 1;
  }
  
  Expression getIfEquals(Expression match)
  {
    if (this.compareType == 0)
    {
      String sql = match.getSQL();
      if (this.left.getSQL().equals(sql)) {
        return this.right;
      }
      if (this.right.getSQL().equals(sql)) {
        return this.left;
      }
    }
    return null;
  }
  
  Expression getAdditional(Session session, Comparison other, boolean and)
  {
    if ((this.compareType == other.compareType) && (this.compareType == 0))
    {
      boolean lc = this.left.isConstant();
      boolean rc = this.right.isConstant();
      boolean l2c = other.left.isConstant();
      boolean r2c = other.right.isConstant();
      String l = this.left.getSQL();
      String l2 = other.left.getSQL();
      String r = this.right.getSQL();
      String r2 = other.right.getSQL();
      if (and)
      {
        if (((!rc) || (!r2c)) && (l.equals(l2))) {
          return new Comparison(session, 0, this.right, other.right);
        }
        if (((!rc) || (!l2c)) && (l.equals(r2))) {
          return new Comparison(session, 0, this.right, other.left);
        }
        if (((!lc) || (!r2c)) && (r.equals(l2))) {
          return new Comparison(session, 0, this.left, other.right);
        }
        if (((!lc) || (!l2c)) && (r.equals(r2))) {
          return new Comparison(session, 0, this.left, other.left);
        }
      }
      else
      {
        Database db = session.getDatabase();
        if ((rc) && (r2c) && (l.equals(l2))) {
          return new ConditionIn(db, this.left, New.arrayList(Arrays.asList(new Expression[] { this.right, other.right })));
        }
        if ((rc) && (l2c) && (l.equals(r2))) {
          return new ConditionIn(db, this.left, New.arrayList(Arrays.asList(new Expression[] { this.right, other.left })));
        }
        if ((lc) && (r2c) && (r.equals(l2))) {
          return new ConditionIn(db, this.right, New.arrayList(Arrays.asList(new Expression[] { this.left, other.right })));
        }
        if ((lc) && (l2c) && (r.equals(r2))) {
          return new ConditionIn(db, this.right, New.arrayList(Arrays.asList(new Expression[] { this.left, other.left })));
        }
      }
    }
    return null;
  }
  
  public Expression getExpression(boolean getLeft)
  {
    return getLeft ? this.left : this.right;
  }
}
