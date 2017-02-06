package org.h2.mvstore;

import java.util.Arrays;
import java.util.Iterator;

public class ConcurrentArrayList<K>
{
  K[] array = (Object[])new Object[0];
  
  public K peekFirst()
  {
    K[] a = this.array;
    return a.length == 0 ? null : a[0];
  }
  
  public K peekLast()
  {
    K[] a = this.array;
    int len = a.length;
    return len == 0 ? null : a[(len - 1)];
  }
  
  public synchronized void add(K obj)
  {
    int len = this.array.length;
    this.array = Arrays.copyOf(this.array, len + 1);
    this.array[len] = obj;
  }
  
  public synchronized boolean removeFirst(K obj)
  {
    if (peekFirst() != obj) {
      return false;
    }
    int len = this.array.length;
    
    K[] a = (Object[])new Object[len - 1];
    System.arraycopy(this.array, 1, a, 0, len - 1);
    this.array = a;
    return true;
  }
  
  public synchronized boolean removeLast(K obj)
  {
    if (peekLast() != obj) {
      return false;
    }
    this.array = Arrays.copyOf(this.array, this.array.length - 1);
    return true;
  }
  
  public Iterator<K> iterator()
  {
    new Iterator()
    {
      K[] a = ConcurrentArrayList.this.array;
      int index;
      
      public boolean hasNext()
      {
        return this.index < this.a.length;
      }
      
      public K next()
      {
        return (K)this.a[(this.index++)];
      }
      
      public void remove()
      {
        throw DataUtils.newUnsupportedOperationException("remove");
      }
    };
  }
}
