package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import org.h2.engine.SysProperties;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class ValueBytes
  extends Value
{
  private static final ValueBytes EMPTY = new ValueBytes(Utils.EMPTY_BYTES);
  protected byte[] value;
  protected int hash;
  
  protected ValueBytes(byte[] v)
  {
    this.value = v;
  }
  
  public static ValueBytes get(byte[] b)
  {
    if (b.length == 0) {
      return EMPTY;
    }
    b = Utils.cloneByteArray(b);
    return getNoCopy(b);
  }
  
  public static ValueBytes getNoCopy(byte[] b)
  {
    if (b.length == 0) {
      return EMPTY;
    }
    ValueBytes obj = new ValueBytes(b);
    if (b.length > SysProperties.OBJECT_CACHE_MAX_PER_ELEMENT_SIZE) {
      return obj;
    }
    return (ValueBytes)Value.cache(obj);
  }
  
  public int getType()
  {
    return 12;
  }
  
  public String getSQL()
  {
    return "X'" + StringUtils.convertBytesToHex(getBytesNoCopy()) + "'";
  }
  
  public byte[] getBytesNoCopy()
  {
    return this.value;
  }
  
  public byte[] getBytes()
  {
    return Utils.cloneByteArray(getBytesNoCopy());
  }
  
  protected int compareSecure(Value v, CompareMode mode)
  {
    byte[] v2 = ((ValueBytes)v).value;
    if (mode.isBinaryUnsigned()) {
      return Utils.compareNotNullUnsigned(this.value, v2);
    }
    return Utils.compareNotNullSigned(this.value, v2);
  }
  
  public String getString()
  {
    return StringUtils.convertBytesToHex(this.value);
  }
  
  public long getPrecision()
  {
    return this.value.length;
  }
  
  public int hashCode()
  {
    if (this.hash == 0) {
      this.hash = Utils.getByteArrayHash(this.value);
    }
    return this.hash;
  }
  
  public Object getObject()
  {
    return getBytes();
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    prep.setBytes(parameterIndex, this.value);
  }
  
  public int getDisplaySize()
  {
    return MathUtils.convertLongToInt(this.value.length * 2L);
  }
  
  public int getMemory()
  {
    return this.value.length + 24;
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueBytes)) && (Arrays.equals(this.value, ((ValueBytes)other).value));
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if (this.value.length <= precision) {
      return this;
    }
    int len = MathUtils.convertLongToInt(precision);
    byte[] buff = new byte[len];
    System.arraycopy(this.value, 0, buff, 0, len);
    return get(buff);
  }
}
