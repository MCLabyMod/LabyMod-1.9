package org.h2.expression;

import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.schema.Sequence;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueLong;

public class SequenceValue
  extends Expression
{
  private final Sequence sequence;
  
  public SequenceValue(Sequence sequence)
  {
    this.sequence = sequence;
  }
  
  public Value getValue(Session session)
  {
    long value = this.sequence.getNext(session);
    session.setLastIdentity(ValueLong.get(value));
    return ValueLong.get(value);
  }
  
  public int getType()
  {
    return 5;
  }
  
  public void mapColumns(ColumnResolver resolver, int level) {}
  
  public Expression optimize(Session session)
  {
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b) {}
  
  public int getScale()
  {
    return 0;
  }
  
  public long getPrecision()
  {
    return 10L;
  }
  
  public int getDisplaySize()
  {
    return 11;
  }
  
  public String getSQL()
  {
    return "(NEXT VALUE FOR " + this.sequence.getSQL() + ")";
  }
  
  public void updateAggregate(Session session) {}
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 1: 
    case 3: 
    case 6: 
    case 9: 
      return true;
    case 0: 
    case 2: 
    case 5: 
    case 8: 
      return false;
    case 4: 
      visitor.addDataModificationId(this.sequence.getModificationId());
      return true;
    case 7: 
      visitor.addDependency(this.sequence);
      return true;
    }
    throw DbException.throwInternalError("type=" + visitor.getType());
  }
  
  public int getCost()
  {
    return 1;
  }
}
