package org.h2.expression;

import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;

public class Parameter
  extends Expression
  implements ParameterInterface
{
  private Value value;
  private Column column;
  private final int index;
  
  public Parameter(int index)
  {
    this.index = index;
  }
  
  public String getSQL()
  {
    return "?" + (this.index + 1);
  }
  
  public void setValue(Value v, boolean closeOld)
  {
    this.value = v;
  }
  
  public void setValue(Value v)
  {
    this.value = v;
  }
  
  public Value getParamValue()
  {
    if (this.value == null) {
      return ValueNull.INSTANCE;
    }
    return this.value;
  }
  
  public Value getValue(Session session)
  {
    return getParamValue();
  }
  
  public int getType()
  {
    if (this.value != null) {
      return this.value.getType();
    }
    if (this.column != null) {
      return this.column.getType();
    }
    return -1;
  }
  
  public void mapColumns(ColumnResolver resolver, int level) {}
  
  public void checkSet()
  {
    if (this.value == null) {
      throw DbException.get(90012, "#" + (this.index + 1));
    }
  }
  
  public Expression optimize(Session session)
  {
    return this;
  }
  
  public boolean isConstant()
  {
    return false;
  }
  
  public boolean isValueSet()
  {
    return this.value != null;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b) {}
  
  public int getScale()
  {
    if (this.value != null) {
      return this.value.getScale();
    }
    if (this.column != null) {
      return this.column.getScale();
    }
    return 0;
  }
  
  public long getPrecision()
  {
    if (this.value != null) {
      return this.value.getPrecision();
    }
    if (this.column != null) {
      return this.column.getPrecision();
    }
    return 0L;
  }
  
  public int getDisplaySize()
  {
    if (this.value != null) {
      return this.value.getDisplaySize();
    }
    if (this.column != null) {
      return this.column.getDisplaySize();
    }
    return 0;
  }
  
  public void updateAggregate(Session session) {}
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
      return true;
    case 0: 
      return this.value != null;
    }
    throw DbException.throwInternalError("type=" + visitor.getType());
  }
  
  public int getCost()
  {
    return 0;
  }
  
  public Expression getNotIfPossible(Session session)
  {
    return new Comparison(session, 0, this, ValueExpression.get(ValueBoolean.get(false)));
  }
  
  public void setColumn(Column column)
  {
    this.column = column;
  }
  
  public int getIndex()
  {
    return this.index;
  }
}
