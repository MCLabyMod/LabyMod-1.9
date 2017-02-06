package org.h2.value;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import org.h2.message.DbException;

public class ValueNull
  extends Value
{
  public static final ValueNull INSTANCE = new ValueNull();
  public static final ValueNull DELETED = new ValueNull();
  private static final int PRECISION = 1;
  private static final int DISPLAY_SIZE = 4;
  
  public String getSQL()
  {
    return "NULL";
  }
  
  public int getType()
  {
    return 0;
  }
  
  public String getString()
  {
    return null;
  }
  
  public Boolean getBoolean()
  {
    return null;
  }
  
  public Date getDate()
  {
    return null;
  }
  
  public Time getTime()
  {
    return null;
  }
  
  public Timestamp getTimestamp()
  {
    return null;
  }
  
  public byte[] getBytes()
  {
    return null;
  }
  
  public byte getByte()
  {
    return 0;
  }
  
  public short getShort()
  {
    return 0;
  }
  
  public BigDecimal getBigDecimal()
  {
    return null;
  }
  
  public double getDouble()
  {
    return 0.0D;
  }
  
  public float getFloat()
  {
    return 0.0F;
  }
  
  public int getInt()
  {
    return 0;
  }
  
  public long getLong()
  {
    return 0L;
  }
  
  public InputStream getInputStream()
  {
    return null;
  }
  
  public Reader getReader()
  {
    return null;
  }
  
  public Value convertTo(int type)
  {
    return this;
  }
  
  protected int compareSecure(Value v, CompareMode mode)
  {
    throw DbException.throwInternalError("compare null");
  }
  
  public long getPrecision()
  {
    return 1L;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public Object getObject()
  {
    return null;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setNull(parameterIndex, DataType.convertTypeToSQLType(0));
  }
  
  public int getDisplaySize()
  {
    return 4;
  }
  
  public boolean equals(Object other)
  {
    return other == this;
  }
}
