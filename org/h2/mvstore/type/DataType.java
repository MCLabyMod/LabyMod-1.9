package org.h2.mvstore.type;

import java.nio.ByteBuffer;
import org.h2.mvstore.WriteBuffer;

public abstract interface DataType
{
  public abstract int compare(Object paramObject1, Object paramObject2);
  
  public abstract int getMemory(Object paramObject);
  
  public abstract void write(WriteBuffer paramWriteBuffer, Object paramObject);
  
  public abstract void write(WriteBuffer paramWriteBuffer, Object[] paramArrayOfObject, int paramInt, boolean paramBoolean);
  
  public abstract Object read(ByteBuffer paramByteBuffer);
  
  public abstract void read(ByteBuffer paramByteBuffer, Object[] paramArrayOfObject, int paramInt, boolean paramBoolean);
}
