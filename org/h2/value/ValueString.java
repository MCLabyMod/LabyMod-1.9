package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.engine.SysProperties;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;

public class ValueString
  extends Value
{
  private static final ValueString EMPTY = new ValueString("");
  protected final String value;
  
  protected ValueString(String value)
  {
    this.value = value;
  }
  
  public String getSQL()
  {
    return StringUtils.quoteStringSQL(this.value);
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueString)) && (this.value.equals(((ValueString)other).value));
  }
  
  protected int compareSecure(Value o, CompareMode mode)
  {
    ValueString v = (ValueString)o;
    return mode.compareString(this.value, v.value, false);
  }
  
  public String getString()
  {
    return this.value;
  }
  
  public long getPrecision()
  {
    return this.value.length();
  }
  
  public Object getObject()
  {
    return this.value;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setString(parameterIndex, this.value);
  }
  
  public int getDisplaySize()
  {
    return this.value.length();
  }
  
  public int getMemory()
  {
    return this.value.length() * 2 + 48;
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if ((precision == 0L) || (this.value.length() <= precision)) {
      return this;
    }
    int p = MathUtils.convertLongToInt(precision);
    return getNew(this.value.substring(0, p));
  }
  
  public int hashCode()
  {
    return this.value.hashCode();
  }
  
  public int getType()
  {
    return 13;
  }
  
  public static Value get(String s)
  {
    return get(s, false);
  }
  
  public static Value get(String s, boolean treatEmptyStringsAsNull)
  {
    if (s.isEmpty()) {
      return treatEmptyStringsAsNull ? ValueNull.INSTANCE : EMPTY;
    }
    ValueString obj = new ValueString(StringUtils.cache(s));
    if (s.length() > SysProperties.OBJECT_CACHE_MAX_PER_ELEMENT_SIZE) {
      return obj;
    }
    return Value.cache(obj);
  }
  
  protected Value getNew(String s)
  {
    return get(s);
  }
}
