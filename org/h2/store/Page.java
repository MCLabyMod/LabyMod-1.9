package org.h2.store;

import java.lang.reflect.Array;
import org.h2.engine.Session;
import org.h2.util.CacheObject;

public abstract class Page
  extends CacheObject
{
  public static final int FLAG_LAST = 16;
  public static final int TYPE_EMPTY = 0;
  public static final int TYPE_DATA_LEAF = 1;
  public static final int TYPE_DATA_NODE = 2;
  public static final int TYPE_DATA_OVERFLOW = 3;
  public static final int TYPE_BTREE_LEAF = 4;
  public static final int TYPE_BTREE_NODE = 5;
  public static final int TYPE_FREE_LIST = 6;
  public static final int TYPE_STREAM_TRUNK = 7;
  public static final int TYPE_STREAM_DATA = 8;
  private static final int COPY_THRESHOLD = 4;
  protected long changeCount;
  
  public abstract void moveTo(Session paramSession, int paramInt);
  
  public abstract void write();
  
  public static <T> T[] insert(T[] old, int oldSize, int pos, T x)
  {
    T[] result;
    T[] result;
    if (old.length > oldSize)
    {
      result = old;
    }
    else
    {
      result = (Object[])Array.newInstance(old.getClass().getComponentType(), oldSize + 1 + 4);
      if (pos > 0) {
        System.arraycopy(old, 0, result, 0, pos);
      }
    }
    if (oldSize - pos > 0) {
      System.arraycopy(old, pos, result, pos + 1, oldSize - pos);
    }
    result[pos] = x;
    return result;
  }
  
  public static <T> T[] remove(T[] old, int oldSize, int pos)
  {
    T[] result;
    T[] result;
    if (old.length - oldSize < 4)
    {
      result = old;
    }
    else
    {
      result = (Object[])Array.newInstance(old.getClass().getComponentType(), oldSize - 1);
      
      System.arraycopy(old, 0, result, 0, Math.min(oldSize - 1, pos));
    }
    if (pos < oldSize) {
      System.arraycopy(old, pos + 1, result, pos, oldSize - pos - 1);
    }
    return result;
  }
  
  protected static long[] insert(long[] old, int oldSize, int pos, long x)
  {
    long[] result;
    long[] result;
    if ((old != null) && (old.length > oldSize))
    {
      result = old;
    }
    else
    {
      result = new long[oldSize + 1 + 4];
      if (pos > 0) {
        System.arraycopy(old, 0, result, 0, pos);
      }
    }
    if ((old != null) && (oldSize - pos > 0)) {
      System.arraycopy(old, pos, result, pos + 1, oldSize - pos);
    }
    result[pos] = x;
    return result;
  }
  
  protected static long[] remove(long[] old, int oldSize, int pos)
  {
    long[] result;
    long[] result;
    if (old.length - oldSize < 4)
    {
      result = old;
    }
    else
    {
      result = new long[oldSize - 1];
      System.arraycopy(old, 0, result, 0, pos);
    }
    System.arraycopy(old, pos + 1, result, pos, oldSize - pos - 1);
    return result;
  }
  
  protected static int[] insert(int[] old, int oldSize, int pos, int x)
  {
    int[] result;
    int[] result;
    if ((old != null) && (old.length > oldSize))
    {
      result = old;
    }
    else
    {
      result = new int[oldSize + 1 + 4];
      if ((pos > 0) && (old != null)) {
        System.arraycopy(old, 0, result, 0, pos);
      }
    }
    if ((old != null) && (oldSize - pos > 0)) {
      System.arraycopy(old, pos, result, pos + 1, oldSize - pos);
    }
    result[pos] = x;
    return result;
  }
  
  protected static int[] remove(int[] old, int oldSize, int pos)
  {
    int[] result;
    int[] result;
    if (old.length - oldSize < 4)
    {
      result = old;
    }
    else
    {
      result = new int[oldSize - 1];
      System.arraycopy(old, 0, result, 0, Math.min(oldSize - 1, pos));
    }
    if (pos < oldSize) {
      System.arraycopy(old, pos + 1, result, pos, oldSize - pos - 1);
    }
    return result;
  }
  
  protected static void add(int[] array, int from, int to, int x)
  {
    for (int i = from; i < to; i++) {
      array[i] += x;
    }
  }
  
  public boolean canMove()
  {
    return true;
  }
}
