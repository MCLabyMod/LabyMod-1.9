package org.h2.mvstore;

import java.nio.ByteBuffer;

public class WriteBuffer
{
  private static final int MAX_REUSE_CAPACITY = 4194304;
  private static final int MIN_GROW = 1048576;
  private ByteBuffer reuse = ByteBuffer.allocate(1048576);
  private ByteBuffer buff = this.reuse;
  
  public WriteBuffer putVarInt(int x)
  {
    DataUtils.writeVarInt(ensureCapacity(5), x);
    return this;
  }
  
  public WriteBuffer putVarLong(long x)
  {
    DataUtils.writeVarLong(ensureCapacity(10), x);
    return this;
  }
  
  public WriteBuffer putStringData(String s, int len)
  {
    ByteBuffer b = ensureCapacity(3 * len);
    for (int i = 0; i < len; i++)
    {
      int c = s.charAt(i);
      if (c < 128)
      {
        b.put((byte)c);
      }
      else if (c >= 2048)
      {
        b.put((byte)(0xE0 | c >> 12));
        b.put((byte)(c >> 6 & 0x3F));
        b.put((byte)(c & 0x3F));
      }
      else
      {
        b.put((byte)(0xC0 | c >> 6));
        b.put((byte)(c & 0x3F));
      }
    }
    return this;
  }
  
  public WriteBuffer put(byte x)
  {
    ensureCapacity(1).put(x);
    return this;
  }
  
  public WriteBuffer putChar(char x)
  {
    ensureCapacity(2).putChar(x);
    return this;
  }
  
  public WriteBuffer putShort(short x)
  {
    ensureCapacity(2).putShort(x);
    return this;
  }
  
  public WriteBuffer putInt(int x)
  {
    ensureCapacity(4).putInt(x);
    return this;
  }
  
  public WriteBuffer putLong(long x)
  {
    ensureCapacity(8).putLong(x);
    return this;
  }
  
  public WriteBuffer putFloat(float x)
  {
    ensureCapacity(4).putFloat(x);
    return this;
  }
  
  public WriteBuffer putDouble(double x)
  {
    ensureCapacity(8).putDouble(x);
    return this;
  }
  
  public WriteBuffer put(byte[] bytes)
  {
    ensureCapacity(bytes.length).put(bytes);
    return this;
  }
  
  public WriteBuffer put(byte[] bytes, int offset, int length)
  {
    ensureCapacity(length).put(bytes, offset, length);
    return this;
  }
  
  public WriteBuffer put(ByteBuffer src)
  {
    ensureCapacity(this.buff.remaining()).put(src);
    return this;
  }
  
  public WriteBuffer limit(int newLimit)
  {
    ensureCapacity(newLimit - this.buff.position()).limit(newLimit);
    return this;
  }
  
  public int capacity()
  {
    return this.buff.capacity();
  }
  
  public WriteBuffer position(int newPosition)
  {
    this.buff.position(newPosition);
    return this;
  }
  
  public int limit()
  {
    return this.buff.limit();
  }
  
  public int position()
  {
    return this.buff.position();
  }
  
  public WriteBuffer get(byte[] dst)
  {
    this.buff.get(dst);
    return this;
  }
  
  public WriteBuffer putInt(int index, int value)
  {
    this.buff.putInt(index, value);
    return this;
  }
  
  public WriteBuffer putShort(int index, short value)
  {
    this.buff.putShort(index, value);
    return this;
  }
  
  public WriteBuffer clear()
  {
    if (this.buff.limit() > 4194304) {
      this.buff = this.reuse;
    } else if (this.buff != this.reuse) {
      this.reuse = this.buff;
    }
    this.buff.clear();
    return this;
  }
  
  public ByteBuffer getBuffer()
  {
    return this.buff;
  }
  
  private ByteBuffer ensureCapacity(int len)
  {
    if (this.buff.remaining() < len) {
      grow(len);
    }
    return this.buff;
  }
  
  private void grow(int additional)
  {
    ByteBuffer temp = this.buff;
    int needed = additional - temp.remaining();
    
    long grow = Math.max(needed, 1048576);
    
    grow = Math.max(temp.capacity() / 2, grow);
    
    int newCapacity = (int)Math.min(2147483647L, temp.capacity() + grow);
    if (newCapacity < needed) {
      throw new OutOfMemoryError("Capacity: " + newCapacity + " needed: " + needed);
    }
    try
    {
      this.buff = ByteBuffer.allocate(newCapacity);
    }
    catch (OutOfMemoryError e)
    {
      throw new OutOfMemoryError("Capacity: " + newCapacity);
    }
    temp.flip();
    this.buff.put(temp);
    if (newCapacity <= 4194304) {
      this.reuse = this.buff;
    }
  }
}
