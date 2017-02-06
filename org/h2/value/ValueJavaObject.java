package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.engine.SysProperties;
import org.h2.store.DataHandler;
import org.h2.util.JdbcUtils;
import org.h2.util.Utils;

public class ValueJavaObject
  extends ValueBytes
{
  private static final ValueJavaObject EMPTY = new ValueJavaObject(Utils.EMPTY_BYTES, null);
  private final DataHandler dataHandler;
  
  protected ValueJavaObject(byte[] v, DataHandler dataHandler)
  {
    super(v);
    this.dataHandler = dataHandler;
  }
  
  public static ValueJavaObject getNoCopy(Object javaObject, byte[] b, DataHandler dataHandler)
  {
    if ((b != null) && (b.length == 0)) {
      return EMPTY;
    }
    ValueJavaObject obj;
    ValueJavaObject obj;
    if (SysProperties.serializeJavaObject)
    {
      if (b == null) {
        b = JdbcUtils.serialize(javaObject, dataHandler);
      }
      obj = new ValueJavaObject(b, dataHandler);
    }
    else
    {
      obj = new NotSerialized(javaObject, b, dataHandler);
    }
    if ((b == null) || (b.length > SysProperties.OBJECT_CACHE_MAX_PER_ELEMENT_SIZE)) {
      return obj;
    }
    return (ValueJavaObject)Value.cache(obj);
  }
  
  public int getType()
  {
    return 19;
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    Object obj = JdbcUtils.deserialize(getBytesNoCopy(), getDataHandler());
    prep.setObject(parameterIndex, obj, 2000);
  }
  
  private static class NotSerialized
    extends ValueJavaObject
  {
    private Object javaObject;
    private int displaySize = -1;
    
    NotSerialized(Object javaObject, byte[] v, DataHandler dataHandler)
    {
      super(dataHandler);
      this.javaObject = javaObject;
    }
    
    public void set(PreparedStatement prep, int parameterIndex)
      throws SQLException
    {
      prep.setObject(parameterIndex, getObject(), 2000);
    }
    
    public byte[] getBytesNoCopy()
    {
      if (this.value == null) {
        this.value = JdbcUtils.serialize(this.javaObject, null);
      }
      return this.value;
    }
    
    protected int compareSecure(Value v, CompareMode mode)
    {
      Object o1 = getObject();
      Object o2 = v.getObject();
      
      boolean o1Comparable = o1 instanceof Comparable;
      boolean o2Comparable = o2 instanceof Comparable;
      if ((o1Comparable) && (o2Comparable) && (Utils.haveCommonComparableSuperclass(o1.getClass(), o2.getClass())))
      {
        Comparable<Object> c1 = (Comparable)o1;
        return c1.compareTo(o2);
      }
      if (o1.getClass() != o2.getClass())
      {
        if (o1Comparable != o2Comparable) {
          return o1Comparable ? -1 : 1;
        }
        return o1.getClass().getName().compareTo(o2.getClass().getName());
      }
      int h1 = hashCode();
      int h2 = v.hashCode();
      if (h1 == h2)
      {
        if (o1.equals(o2)) {
          return 0;
        }
        return Utils.compareNotNullSigned(getBytesNoCopy(), v.getBytesNoCopy());
      }
      return h1 > h2 ? 1 : -1;
    }
    
    public String getString()
    {
      String str = getObject().toString();
      if (this.displaySize == -1) {
        this.displaySize = str.length();
      }
      return str;
    }
    
    public long getPrecision()
    {
      return 0L;
    }
    
    public int hashCode()
    {
      if (this.hash == 0) {
        this.hash = getObject().hashCode();
      }
      return this.hash;
    }
    
    public Object getObject()
    {
      if (this.javaObject == null) {
        this.javaObject = JdbcUtils.deserialize(this.value, getDataHandler());
      }
      return this.javaObject;
    }
    
    public int getDisplaySize()
    {
      if (this.displaySize == -1) {
        this.displaySize = getString().length();
      }
      return this.displaySize;
    }
    
    public int getMemory()
    {
      if (this.value == null) {
        return DataType.getDataType(getType()).memory;
      }
      int mem = super.getMemory();
      if (this.javaObject != null) {
        mem *= 2;
      }
      return mem;
    }
    
    public boolean equals(Object other)
    {
      if (!(other instanceof NotSerialized)) {
        return false;
      }
      return getObject().equals(((NotSerialized)other).getObject());
    }
    
    public Value convertPrecision(long precision, boolean force)
    {
      return this;
    }
  }
  
  protected DataHandler getDataHandler()
  {
    return this.dataHandler;
  }
}
