package org.h2.util;

public final class BitField
{
  private static final int ADDRESS_BITS = 6;
  private static final int BITS = 64;
  private static final int ADDRESS_MASK = 63;
  private long[] data;
  private int maxLength;
  
  public BitField()
  {
    this(64);
  }
  
  public BitField(int capacity)
  {
    this.data = new long[capacity >>> 3];
  }
  
  public int nextClearBit(int fromIndex)
  {
    int i = fromIndex >> 6;
    int max = this.data.length;
    for (; i < max; i++) {
      if (this.data[i] != -1L)
      {
        int j = Math.max(fromIndex, i << 6);
        for (int end = j + 64; j < end; j++) {
          if (!get(j)) {
            return j;
          }
        }
      }
    }
    return max << 6;
  }
  
  public boolean get(int i)
  {
    int addr = i >> 6;
    if (addr >= this.data.length) {
      return false;
    }
    return (this.data[addr] & getBitMask(i)) != 0L;
  }
  
  public int getByte(int i)
  {
    int addr = i >> 6;
    if (addr >= this.data.length) {
      return 0;
    }
    return (int)(this.data[addr] >>> (i & 0x38) & 0xFF);
  }
  
  public void setByte(int i, int x)
  {
    int addr = i >> 6;
    checkCapacity(addr);
    this.data[addr] |= x << (i & 0x38);
    if ((this.maxLength < i) && (x != 0)) {
      this.maxLength = (i + 7);
    }
  }
  
  public void set(int i)
  {
    int addr = i >> 6;
    checkCapacity(addr);
    this.data[addr] |= getBitMask(i);
    if (this.maxLength < i) {
      this.maxLength = i;
    }
  }
  
  public void clear(int i)
  {
    int addr = i >> 6;
    if (addr >= this.data.length) {
      return;
    }
    this.data[addr] &= (getBitMask(i) ^ 0xFFFFFFFFFFFFFFFF);
  }
  
  private static long getBitMask(int i)
  {
    return 1L << (i & 0x3F);
  }
  
  private void checkCapacity(int size)
  {
    if (size >= this.data.length) {
      expandCapacity(size);
    }
  }
  
  private void expandCapacity(int size)
  {
    while (size >= this.data.length)
    {
      int newSize = this.data.length == 0 ? 1 : this.data.length * 2;
      long[] d = new long[newSize];
      System.arraycopy(this.data, 0, d, 0, this.data.length);
      this.data = d;
    }
  }
  
  public void set(int fromIndex, int toIndex, boolean value)
  {
    for (int i = toIndex - 1; i >= fromIndex; i--) {
      set(i, value);
    }
    if (value)
    {
      if (toIndex > this.maxLength) {
        this.maxLength = toIndex;
      }
    }
    else if (toIndex >= this.maxLength) {
      this.maxLength = fromIndex;
    }
  }
  
  private void set(int i, boolean value)
  {
    if (value) {
      set(i);
    } else {
      clear(i);
    }
  }
  
  public int length()
  {
    int m = this.maxLength >> 6;
    while ((m > 0) && (this.data[m] == 0L)) {
      m--;
    }
    this.maxLength = ((m << 6) + (64 - Long.numberOfLeadingZeros(this.data[m])));
    
    return this.maxLength;
  }
}
