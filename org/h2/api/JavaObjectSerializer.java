package org.h2.api;

public abstract interface JavaObjectSerializer
{
  public abstract byte[] serialize(Object paramObject)
    throws Exception;
  
  public abstract Object deserialize(byte[] paramArrayOfByte)
    throws Exception;
}
