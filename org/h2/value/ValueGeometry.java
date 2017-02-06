package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.util.StringUtils;

public class ValueGeometry
  extends Value
{
  public static ValueGeometry get(String s, int srid)
  {
    return null;
  }
  
  public static ValueGeometry get(byte[] bytes)
  {
    return null;
  }
  
  public int getType()
  {
    return 22;
  }
  
  public String getSQL()
  {
    return "X'" + StringUtils.convertBytesToHex(getBytesNoCopy()) + "'::Geometry";
  }
  
  public Value convertTo(int targetType)
  {
    if (targetType == 19) {
      return this;
    }
    return super.convertTo(targetType);
  }
  
  public long getPrecision()
  {
    return 0L;
  }
  
  public int getDisplaySize()
  {
    return 0;
  }
  
  public String getString()
  {
    return null;
  }
  
  public Object getObject()
  {
    return null;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {}
  
  protected int compareSecure(Value v, CompareMode mode)
  {
    return 0;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean equals(Object other)
  {
    return false;
  }
}
