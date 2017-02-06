package org.h2.mvstore;

import java.util.BitSet;
import org.h2.util.MathUtils;

public class FreeSpaceBitSet
{
  private static final boolean DETAILED_INFO = false;
  private final int firstFreeBlock;
  private final int blockSize;
  private final BitSet set = new BitSet();
  
  public FreeSpaceBitSet(int firstFreeBlock, int blockSize)
  {
    this.firstFreeBlock = firstFreeBlock;
    this.blockSize = blockSize;
    clear();
  }
  
  public void clear()
  {
    this.set.clear();
    this.set.set(0, this.firstFreeBlock);
  }
  
  public boolean isUsed(long pos, int length)
  {
    int start = getBlock(pos);
    int blocks = getBlockCount(length);
    for (int i = start; i < start + blocks; i++) {
      if (!this.set.get(i)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean isFree(long pos, int length)
  {
    int start = getBlock(pos);
    int blocks = getBlockCount(length);
    for (int i = start; i < start + blocks; i++) {
      if (this.set.get(i)) {
        return false;
      }
    }
    return true;
  }
  
  public long allocate(int length)
  {
    int blocks = getBlockCount(length);
    int i = 0;
    for (;;)
    {
      int start = this.set.nextClearBit(i);
      int end = this.set.nextSetBit(start + 1);
      if ((end < 0) || (end - start >= blocks))
      {
        this.set.set(start, start + blocks);
        return getPos(start);
      }
      i = end;
    }
  }
  
  public void markUsed(long pos, int length)
  {
    int start = getBlock(pos);
    int blocks = getBlockCount(length);
    this.set.set(start, start + blocks);
  }
  
  public void free(long pos, int length)
  {
    int start = getBlock(pos);
    int blocks = getBlockCount(length);
    this.set.clear(start, start + blocks);
  }
  
  private long getPos(int block)
  {
    return block * this.blockSize;
  }
  
  private int getBlock(long pos)
  {
    return (int)(pos / this.blockSize);
  }
  
  private int getBlockCount(int length)
  {
    return MathUtils.roundUpInt(length, this.blockSize) / this.blockSize;
  }
  
  public int getFillRate()
  {
    int total = this.set.length();int count = 0;
    for (int i = 0; i < total; i++) {
      if (this.set.get(i)) {
        count++;
      }
    }
    if (count == 0) {
      return 0;
    }
    return Math.max(1, (int)(100L * count / total));
  }
  
  public long getFirstFree()
  {
    return getPos(this.set.nextClearBit(0));
  }
  
  public String toString()
  {
    StringBuilder buff = new StringBuilder();
    
    buff.append('[');
    int i = 0;
    for (;;)
    {
      if (i > 0) {
        buff.append(", ");
      }
      int start = this.set.nextClearBit(i);
      buff.append(Integer.toHexString(start)).append('-');
      int end = this.set.nextSetBit(start + 1);
      if (end < 0) {
        break;
      }
      buff.append(Integer.toHexString(end - 1));
      i = end + 1;
    }
    buff.append(']');
    return buff.toString();
  }
}
