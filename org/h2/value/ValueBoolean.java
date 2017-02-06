package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValueBoolean
  extends Value
{
  public static final int PRECISION = 1;
  public static final int DISPLAY_SIZE = 5;
  private static final Object TRUE = new ValueBoolean(true);
  private static final Object FALSE = new ValueBoolean(false);
  private final Boolean value;
  
  private ValueBoolean(boolean value)
  {
    this.value = Boolean.valueOf(value);
  }
  
  public int getType()
  {
    return 1;
  }
  
  public String getSQL()
  {
    return getString();
  }
  
  public String getString()
  {
    return this.value.booleanValue() ? "TRUE" : "FALSE";
  }
  
  public Value negate()
  {
    return (ValueBoolean)(this.value.booleanValue() ? FALSE : TRUE);
  }
  
  public Boolean getBoolean()
  {
    return this.value;
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    boolean v2 = ((ValueBoolean)o).value.booleanValue();
    boolean v = this.value.booleanValue();
    return v ? 1 : v == v2 ? 0 : -1;
  }
  
  public long getPrecision()
  {
    return 1L;
  }
  
  public int hashCode()
  {
    return this.value.booleanValue() ? 1 : 0;
  }
  
  public Object getObject()
  {
    return this.value;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setBoolean(parameterIndex, this.value.booleanValue());
  }
  
  public static ValueBoolean get(boolean b)
  {
    return (ValueBoolean)(b ? TRUE : FALSE);
  }
  
  public int getDisplaySize()
  {
    return 5;
  }
  
  public boolean equals(Object other)
  {
    return this == other;
  }
}
