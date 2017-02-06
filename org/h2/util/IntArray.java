package org.h2.util;

import org.h2.engine.SysProperties;

public class IntArray
{
  private int[] data;
  private int size;
  private int hash;
  
  public IntArray()
  {
    this(10);
  }
  
  public IntArray(int capacity)
  {
    this.data = new int[capacity];
  }
  
  public IntArray(int[] data)
  {
    this.data = data;
    this.size = data.length;
  }
  
  public void add(int value)
  {
    if (this.size >= this.data.length) {
      ensureCapacity(this.size + this.size);
    }
    this.data[(this.size++)] = value;
  }
  
  public int get(int index)
  {
    if ((SysProperties.CHECK) && 
      (index >= this.size)) {
      throw new ArrayIndexOutOfBoundsException("i=" + index + " size=" + this.size);
    }
    return this.data[index];
  }
  
  public void remove(int index)
  {
    if ((SysProperties.CHECK) && 
      (index >= this.size)) {
      throw new ArrayIndexOutOfBoundsException("i=" + index + " size=" + this.size);
    }
    System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
    this.size -= 1;
  }
  
  public void ensureCapacity(int minCapacity)
  {
    minCapacity = Math.max(4, minCapacity);
    if (minCapacity >= this.data.length)
    {
      int[] d = new int[minCapacity];
      System.arraycopy(this.data, 0, d, 0, this.data.length);
      this.data = d;
    }
  }
  
  public boolean equals(Object obj)
  {
    if (!(obj instanceof IntArray)) {
      return false;
    }
    IntArray other = (IntArray)obj;
    if ((hashCode() != other.hashCode()) || (this.size != other.size)) {
      return false;
    }
    for (int i = 0; i < this.size; i++) {
      if (this.data[i] != other.data[i]) {
        return false;
      }
    }
    return true;
  }
  
  public int hashCode()
  {
    if (this.hash != 0) {
      return this.hash;
    }
    int h = this.size + 1;
    for (int i = 0; i < this.size; i++) {
      h = h * 31 + this.data[i];
    }
    this.hash = h;
    return h;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void toArray(int[] array)
  {
    System.arraycopy(this.data, 0, array, 0, this.size);
  }
  
  public String toString()
  {
    StatementBuilder buff = new StatementBuilder("{");
    for (int i = 0; i < this.size; i++)
    {
      buff.appendExceptFirst(", ");
      buff.append(this.data[i]);
    }
    return buff.append('}').toString();
  }
  
  public void removeRange(int fromIndex, int toIndex)
  {
    if ((SysProperties.CHECK) && (
      (fromIndex > toIndex) || (toIndex > this.size))) {
      throw new ArrayIndexOutOfBoundsException("from=" + fromIndex + " to=" + toIndex + " size=" + this.size);
    }
    System.arraycopy(this.data, toIndex, this.data, fromIndex, this.size - toIndex);
    this.size -= toIndex - fromIndex;
  }
}
