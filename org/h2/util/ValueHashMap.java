package org.h2.util;

import java.util.ArrayList;
import org.h2.message.DbException;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class ValueHashMap<V>
  extends HashBase
{
  private Value[] keys;
  private V[] values;
  
  public static <T> ValueHashMap<T> newInstance()
  {
    return new ValueHashMap();
  }
  
  protected void reset(int newLevel)
  {
    super.reset(newLevel);
    this.keys = new Value[this.len];
    this.values = ((Object[])new Object[this.len]);
  }
  
  protected void rehash(int newLevel)
  {
    Value[] oldKeys = this.keys;
    V[] oldValues = this.values;
    reset(newLevel);
    int len = oldKeys.length;
    for (int i = 0; i < len; i++)
    {
      Value k = oldKeys[i];
      if ((k != null) && (k != ValueNull.DELETED)) {
        internalPut(k, oldValues[i]);
      }
    }
  }
  
  private int getIndex(Value key)
  {
    return key.hashCode() & this.mask;
  }
  
  public void put(Value key, V value)
  {
    checkSizePut();
    internalPut(key, value);
  }
  
  private void internalPut(Value key, V value)
  {
    int index = getIndex(key);
    int plus = 1;
    int deleted = -1;
    do
    {
      Value k = this.keys[index];
      if (k == null)
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
      if (k == ValueNull.DELETED)
      {
        if (deleted < 0) {
          deleted = index;
        }
      }
      else if (k.equals(key))
      {
        this.values[index] = value;
        return;
      }
      index = index + plus++ & this.mask;
    } while (plus <= this.len);
    DbException.throwInternalError("hashmap is full");
  }
  
  public void remove(Value key)
  {
    checkSizeRemove();
    int index = getIndex(key);
    int plus = 1;
    do
    {
      Value k = this.keys[index];
      if (k == null) {
        return;
      }
      if (k != ValueNull.DELETED) {
        if (k.equals(key))
        {
          this.keys[index] = ValueNull.DELETED;
          this.values[index] = null;
          this.deletedCount += 1;
          this.size -= 1;
          return;
        }
      }
      index = index + plus++ & this.mask;
    } while (plus <= this.len);
  }
  
  public V get(Value key)
  {
    int index = getIndex(key);
    int plus = 1;
    do
    {
      Value k = this.keys[index];
      if (k == null) {
        return null;
      }
      if (k != ValueNull.DELETED) {
        if (k.equals(key)) {
          return (V)this.values[index];
        }
      }
      index = index + plus++ & this.mask;
    } while (plus <= this.len);
    return null;
  }
  
  public ArrayList<Value> keys()
  {
    ArrayList<Value> list = New.arrayList(this.size);
    for (Value k : this.keys) {
      if ((k != null) && (k != ValueNull.DELETED)) {
        list.add(k);
      }
    }
    return list;
  }
  
  public ArrayList<V> values()
  {
    ArrayList<V> list = New.arrayList(this.size);
    int len = this.keys.length;
    for (int i = 0; i < len; i++)
    {
      Value k = this.keys[i];
      if ((k != null) && (k != ValueNull.DELETED)) {
        list.add(this.values[i]);
      }
    }
    return list;
  }
}
