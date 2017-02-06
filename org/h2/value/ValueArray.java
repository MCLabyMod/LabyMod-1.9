package org.h2.value;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StatementBuilder;

public class ValueArray
  extends Value
{
  private final Class<?> componentType;
  private final Value[] values;
  private int hash;
  
  private ValueArray(Class<?> componentType, Value[] list)
  {
    this.componentType = componentType;
    this.values = list;
  }
  
  private ValueArray(Value[] list)
  {
    this(Object.class, list);
  }
  
  public static ValueArray get(Value[] list)
  {
    return new ValueArray(list);
  }
  
  public static ValueArray get(Class<?> componentType, Value[] list)
  {
    return new ValueArray(componentType, list);
  }
  
  public int hashCode()
  {
    if (this.hash != 0) {
      return this.hash;
    }
    int h = 1;
    for (Value v : this.values) {
      h = h * 31 + v.hashCode();
    }
    this.hash = h;
    return h;
  }
  
  public Value[] getList()
  {
    return this.values;
  }
  
  public int getType()
  {
    return 17;
  }
  
  public Class<?> getComponentType()
  {
    return this.componentType;
  }
  
  public long getPrecision()
  {
    long p = 0L;
    for (Value v : this.values) {
      p += v.getPrecision();
    }
    return p;
  }
  
  public String getString()
  {
    StatementBuilder buff = new StatementBuilder("(");
    for (Value v : this.values)
    {
      buff.appendExceptFirst(", ");
      buff.append(v.getString());
    }
    return buff.append(')').toString();
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueArray v = (ValueArray)o;
    if (this.values == v.values) {
      return 0;
    }
    int l = this.values.length;
    int ol = v.values.length;
    int len = Math.min(l, ol);
    for (int i = 0; i < len; i++)
    {
      Value v1 = this.values[i];
      Value v2 = v.values[i];
      int comp = v1.compareTo(v2, mode);
      if (comp != 0) {
        return comp;
      }
    }
    return l == ol ? 0 : l > ol ? 1 : -1;
  }
  
  public Object getObject()
  {
    int len = this.values.length;
    Object[] list = (Object[])Array.newInstance(this.componentType, len);
    for (int i = 0; i < len; i++) {
      list[i] = this.values[i].getObject();
    }
    return list;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
  {
    throw throwUnsupportedExceptionForType("PreparedStatement.set");
  }
  
  public String getSQL()
  {
    StatementBuilder buff = new StatementBuilder("(");
    for (Value v : this.values)
    {
      buff.appendExceptFirst(", ");
      buff.append(v.getSQL());
    }
    if (this.values.length == 1) {
      buff.append(',');
    }
    return buff.append(')').toString();
  }
  
  public String getTraceSQL()
  {
    StatementBuilder buff = new StatementBuilder("(");
    for (Value v : this.values)
    {
      buff.appendExceptFirst(", ");
      buff.append(v == null ? "null" : v.getTraceSQL());
    }
    return buff.append(')').toString();
  }
  
  public int getDisplaySize()
  {
    long size = 0L;
    for (Value v : this.values) {
      size += v.getDisplaySize();
    }
    return MathUtils.convertLongToInt(size);
  }
  
  public boolean equals(Object other)
  {
    if (!(other instanceof ValueArray)) {
      return false;
    }
    ValueArray v = (ValueArray)other;
    if (this.values == v.values) {
      return true;
    }
    int len = this.values.length;
    if (len != v.values.length) {
      return false;
    }
    for (int i = 0; i < len; i++) {
      if (!this.values[i].equals(v.values[i])) {
        return false;
      }
    }
    return true;
  }
  
  public int getMemory()
  {
    int memory = 32;
    for (Value v : this.values) {
      memory += v.getMemory() + 8;
    }
    return memory;
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if (!force) {
      return this;
    }
    ArrayList<Value> list = New.arrayList();
    for (Value v : this.values)
    {
      v = v.convertPrecision(precision, true);
      
      precision -= Math.max(1L, v.getPrecision());
      if (precision < 0L) {
        break;
      }
      list.add(v);
    }
    Value[] array = new Value[list.size()];
    list.toArray(array);
    return get(array);
  }
}
