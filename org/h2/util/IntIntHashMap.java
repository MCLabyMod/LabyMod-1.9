package org.h2.util;

import org.h2.message.DbException;

public class IntIntHashMap
  extends HashBase
{
  public static final int NOT_FOUND = -1;
  private static final int DELETED = 1;
  private int[] keys;
  private int[] values;
  private int zeroValue;
  
  protected void reset(int newLevel)
  {
    super.reset(newLevel);
    this.keys = new int[this.len];
    this.values = new int[this.len];
  }
  
  public void put(int key, int value)
  {
    if (key == 0)
    {
      this.zeroKey = true;
      this.zeroValue = value;
      return;
    }
    checkSizePut();
    internalPut(key, value);
  }
  
  private void internalPut(int key, int value)
  {
    int index = getIndex(key);
    int plus = 1;
    int deleted = -1;
    do
    {
      int k = this.keys[index];
      if (k == 0)
      {
        if (this.values[index] != 1)
        {
          if (deleted >= 0)
          {
            index = deleted;
            this.deletedCount -= 1;
          }
          this.size += 1;
          this.keys[index] = key;
          this.values[index] = value;
          return;
        }
        if (deleted < 0) {
          deleted = index;
        }
      }
      else if (k == key)
      {
        this.values[index] = value;
        return;
      }
      index = index + plus++ & this.mask;
    } while (plus <= this.len);
    DbException.throwInternalError("hashmap is full");
  }
  
  public void remove(int key)
  {
    if (key == 0)
    {
      this.zeroKey = false;
      return;
    }
    checkSizeRemove();
    int index = getIndex(key);
    int plus = 1;
    do
    {
      int k = this.keys[index];
      if (k == key)
      {
        this.keys[index] = 0;
        this.values[index] = 1;
        this.deletedCount += 1;
        this.size -= 1;
        return;
      }
      if ((k == 0) && (this.values[index] == 0)) {
        return;
      }
      index = index + plus++ & this.mask;
    } while (plus <= this.len);
  }
  
  protected void rehash(int newLevel)
  {
    int[] oldKeys = this.keys;
    int[] oldValues = this.values;
    reset(newLevel);
    for (int i = 0; i < oldKeys.length; i++)
    {
      int k = oldKeys[i];
      if (k != 0) {
        internalPut(k, oldValues[i]);
      }
    }
  }
  
  public int get(int key)
  {
    if (key == 0) {
      return this.zeroKey ? this.zeroValue : -1;
    }
    int index = getIndex(key);
    int plus = 1;
    do
    {
      int k = this.keys[index];
      if ((k == 0) && (this.values[index] == 0)) {
        return -1;
      }
      if (k == key) {
        return this.values[index];
      }
      index = index + plus++ & this.mask;
    } while (plus <= this.len);
    return -1;
  }
}
