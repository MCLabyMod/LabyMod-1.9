package org.h2.expression;

import org.h2.engine.Database;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.util.MathUtils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;

public class Operation
  extends Expression
{
  public static final int CONCAT = 0;
  public static final int PLUS = 1;
  public static final int MINUS = 2;
  public static final int MULTIPLY = 3;
  public static final int DIVIDE = 4;
  public static final int NEGATE = 5;
  public static final int MODULUS = 6;
  private int opType;
  private Expression left;
  private Expression right;
  private int dataType;
  private boolean convertRight = true;
  
  public Operation(int opType, Expression left, Expression right)
  {
    this.opType = opType;
    this.left = left;
    this.right = right;
  }
  
  public String getSQL()
  {
    String sql;
    String sql;
    if (this.opType == 5) {
      sql = "- " + this.left.getSQL();
    } else {
      sql = this.left.getSQL() + " " + getOperationToken() + " " + this.right.getSQL();
    }
    return "(" + sql + ")";
  }
  
  private String getOperationToken()
  {
    switch (this.opType)
    {
    case 5: 
      return "-";
    case 0: 
      return "||";
    case 1: 
      return "+";
    case 2: 
      return "-";
    case 3: 
      return "*";
    case 4: 
      return "/";
    case 6: 
      return "%";
    }
    throw DbException.throwInternalError("opType=" + this.opType);
  }
  
  public Value getValue(Session session)
  {
    Value l = this.left.getValue(session).convertTo(this.dataType);
    Value r;
    Value r;
    if (this.right == null)
    {
      r = null;
    }
    else
    {
      r = this.right.getValue(session);
      if (this.convertRight) {
        r = r.convertTo(this.dataType);
      }
    }
    switch (this.opType)
    {
    case 5: 
      return l == ValueNull.INSTANCE ? l : l.negate();
    case 0: 
      Mode mode = session.getDatabase().getMode();
      if (l == ValueNull.INSTANCE)
      {
        if (mode.nullConcatIsNull) {
          return ValueNull.INSTANCE;
        }
        return r;
      }
      if (r == ValueNull.INSTANCE)
      {
        if (mode.nullConcatIsNull) {
          return ValueNull.INSTANCE;
        }
        return l;
      }
      String s1 = l.getString();String s2 = r.getString();
      StringBuilder buff = new StringBuilder(s1.length() + s2.length());
      buff.append(s1).append(s2);
      return ValueString.get(buff.toString());
    case 1: 
      if ((l == ValueNull.INSTANCE) || (r == ValueNull.INSTANCE)) {
        return ValueNull.INSTANCE;
      }
      return l.add(r);
    case 2: 
      if ((l == ValueNull.INSTANCE) || (r == ValueNull.INSTANCE)) {
        return ValueNull.INSTANCE;
      }
      return l.subtract(r);
    case 3: 
      if ((l == ValueNull.INSTANCE) || (r == ValueNull.INSTANCE)) {
        return ValueNull.INSTANCE;
      }
      return l.multiply(r);
    case 4: 
      if ((l == ValueNull.INSTANCE) || (r == ValueNull.INSTANCE)) {
        return ValueNull.INSTANCE;
      }
      return l.divide(r);
    case 6: 
      if ((l == ValueNull.INSTANCE) || (r == ValueNull.INSTANCE)) {
        return ValueNull.INSTANCE;
      }
      return l.modulus(r);
    }
    throw DbException.throwInternalError("type=" + this.opType);
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.left.mapColumns(resolver, level);
    if (this.right != null) {
      this.right.mapColumns(resolver, level);
    }
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    switch (this.opType)
    {
    case 5: 
      this.dataType = this.left.getType();
      if (this.dataType == -1) {
        this.dataType = 6;
      }
      break;
    case 0: 
      this.right = this.right.optimize(session);
      this.dataType = 13;
      if ((this.left.isConstant()) && (this.right.isConstant())) {
        return ValueExpression.get(getValue(session));
      }
      break;
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 6: 
      this.right = this.right.optimize(session);
      int l = this.left.getType();
      int r = this.right.getType();
      if (((l == 0) && (r == 0)) || ((l == -1) && (r == -1)))
      {
        if ((this.opType == 1) && (session.getDatabase().getMode().allowPlusForStringConcat))
        {
          this.dataType = 13;
          this.opType = 0;
        }
        else
        {
          this.dataType = 6;
        }
      }
      else
      {
        if ((l == 10) || (l == 11) || (l == 9) || (r == 10) || (r == 11) || (r == 9))
        {
          if (this.opType == 1)
          {
            if (r != Value.getHigherOrder(l, r))
            {
              swap();
              int t = l;
              l = r;
              r = t;
            }
            if (l == 4)
            {
              Function f = Function.getFunction(session.getDatabase(), "DATEADD");
              f.setParameter(0, ValueExpression.get(ValueString.get("DAY")));
              f.setParameter(1, this.left);
              f.setParameter(2, this.right);
              f.doneWithParameters();
              return f.optimize(session);
            }
            if ((l == 6) || (l == 8) || (l == 7))
            {
              Function f = Function.getFunction(session.getDatabase(), "DATEADD");
              f.setParameter(0, ValueExpression.get(ValueString.get("SECOND")));
              this.left = new Operation(3, ValueExpression.get(ValueInt.get(86400)), this.left);
              
              f.setParameter(1, this.left);
              f.setParameter(2, this.right);
              f.doneWithParameters();
              return f.optimize(session);
            }
            if ((l == 9) && (r == 9))
            {
              this.dataType = 9;
              return this;
            }
            if (l == 9)
            {
              this.dataType = 11;
              return this;
            }
          }
          else if (this.opType == 2)
          {
            if (((l == 10) || (l == 11)) && (r == 4))
            {
              Function f = Function.getFunction(session.getDatabase(), "DATEADD");
              f.setParameter(0, ValueExpression.get(ValueString.get("DAY")));
              this.right = new Operation(5, this.right, null);
              this.right = this.right.optimize(session);
              f.setParameter(1, this.right);
              f.setParameter(2, this.left);
              f.doneWithParameters();
              return f.optimize(session);
            }
            if (((l == 10) || (l == 11)) && ((r == 6) || (r == 8) || (r == 7)))
            {
              Function f = Function.getFunction(session.getDatabase(), "DATEADD");
              f.setParameter(0, ValueExpression.get(ValueString.get("SECOND")));
              this.right = new Operation(3, ValueExpression.get(ValueInt.get(86400)), this.right);
              
              this.right = new Operation(5, this.right, null);
              this.right = this.right.optimize(session);
              f.setParameter(1, this.right);
              f.setParameter(2, this.left);
              f.doneWithParameters();
              return f.optimize(session);
            }
            if ((l == 10) || (l == 11))
            {
              if (r == 9)
              {
                this.dataType = 11;
                return this;
              }
              if ((r == 10) || (r == 11))
              {
                Function f = Function.getFunction(session.getDatabase(), "DATEDIFF");
                f.setParameter(0, ValueExpression.get(ValueString.get("DAY")));
                f.setParameter(1, this.right);
                f.setParameter(2, this.left);
                f.doneWithParameters();
                return f.optimize(session);
              }
            }
            else if ((l == 9) && (r == 9))
            {
              this.dataType = 9;
              return this;
            }
          }
          else if (this.opType == 3)
          {
            if (l == 9)
            {
              this.dataType = 9;
              this.convertRight = false;
              return this;
            }
            if (r == 9)
            {
              swap();
              this.dataType = 9;
              this.convertRight = false;
              return this;
            }
          }
          else if ((this.opType == 4) && 
            (l == 9))
          {
            this.dataType = 9;
            this.convertRight = false;
            return this;
          }
          throw DbException.getUnsupportedException(DataType.getDataType(l).name + " " + getOperationToken() + " " + DataType.getDataType(r).name);
        }
        this.dataType = Value.getHigherOrder(l, r);
        if ((DataType.isStringType(this.dataType)) && (session.getDatabase().getMode().allowPlusForStringConcat)) {
          this.opType = 0;
        }
      }
      break;
    default: 
      DbException.throwInternalError("type=" + this.opType);
    }
    if ((this.left.isConstant()) && ((this.right == null) || (this.right.isConstant()))) {
      return ValueExpression.get(getValue(session));
    }
    return this;
  }
  
  private void swap()
  {
    Expression temp = this.left;
    this.left = this.right;
    this.right = temp;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
    if (this.right != null) {
      this.right.setEvaluatable(tableFilter, b);
    }
  }
  
  public int getType()
  {
    return this.dataType;
  }
  
  public long getPrecision()
  {
    if (this.right != null)
    {
      switch (this.opType)
      {
      case 0: 
        return this.left.getPrecision() + this.right.getPrecision();
      }
      return Math.max(this.left.getPrecision(), this.right.getPrecision());
    }
    return this.left.getPrecision();
  }
  
  public int getDisplaySize()
  {
    if (this.right != null)
    {
      switch (this.opType)
      {
      case 0: 
        return MathUtils.convertLongToInt(this.left.getDisplaySize() + this.right.getDisplaySize());
      }
      return Math.max(this.left.getDisplaySize(), this.right.getDisplaySize());
    }
    return this.left.getDisplaySize();
  }
  
  public int getScale()
  {
    if (this.right != null) {
      return Math.max(this.left.getScale(), this.right.getScale());
    }
    return this.left.getScale();
  }
  
  public void updateAggregate(Session session)
  {
    this.left.updateAggregate(session);
    if (this.right != null) {
      this.right.updateAggregate(session);
    }
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return (this.left.isEverything(visitor)) && ((this.right == null) || (this.right.isEverything(visitor)));
  }
  
  public int getCost()
  {
    return this.left.getCost() + 1 + (this.right == null ? 0 : this.right.getCost());
  }
}
