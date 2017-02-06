package org.h2.util;

public abstract class HashBase
{
  private static final long MAX_LOAD = 90L;
  protected int mask;
  protected int len;
  protected int size;
  protected int deletedCount;
  protected int level;
  protected boolean zeroKey;
  private int maxSize;
  private int minSize;
  private int maxDeleted;
  
  public HashBase()
  {
    reset(2);
  }
  
  protected abstract void rehash(int paramInt);
  
  public int size()
  {
    return this.size + (this.zeroKey ? 1 : 0);
  }
  
  void checkSizePut()
  {
    if (this.deletedCount > this.size) {
      rehash(this.level);
    }
    if (this.size + this.deletedCount >= this.maxSize) {
      rehash(this.level + 1);
    }
  }
  
  protected void checkSizeRemove()
  {
    if ((this.size < this.minSize) && (this.level > 0)) {
      rehash(this.level - 1);
    } else if (this.deletedCount > this.maxDeleted) {
      rehash(this.level);
    }
  }
  
  protected void reset(int newLevel)
  {
    if (newLevel > 30) {
      throw new IllegalStateException("exceeded max size of hash table");
    }
    this.size = 0;
    this.level = newLevel;
    this.len = (2 << this.level);
    this.mask = (this.len - 1);
    this.minSize = ((int)((1 << this.level) * 90L / 100L));
    this.maxSize = ((int)(this.len * 90L / 100L));
    this.deletedCount = 0;
    this.maxDeleted = (20 + this.len / 2);
  }
  
  protected int getIndex(int hash)
  {
    return hash & this.mask;
  }
}
