package org.h2.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.h2.message.DbException;

public class SmallMap
{
  private final HashMap<Integer, Object> map = New.hashMap();
  private Object cache;
  private int cacheId;
  private int lastId;
  private final int maxElements;
  
  public SmallMap(int maxElements)
  {
    this.maxElements = maxElements;
  }
  
  public int addObject(int id, Object o)
  {
    if (this.map.size() > this.maxElements * 2)
    {
      Iterator<Integer> it = this.map.keySet().iterator();
      while (it.hasNext())
      {
        Integer k = (Integer)it.next();
        if (k.intValue() + this.maxElements < this.lastId) {
          it.remove();
        }
      }
    }
    if (id > this.lastId) {
      this.lastId = id;
    }
    this.map.put(Integer.valueOf(id), o);
    this.cacheId = id;
    this.cache = o;
    return id;
  }
  
  public void freeObject(int id)
  {
    if (this.cacheId == id)
    {
      this.cacheId = -1;
      this.cache = null;
    }
    this.map.remove(Integer.valueOf(id));
  }
  
  public Object getObject(int id, boolean ifAvailable)
  {
    if (id == this.cacheId) {
      return this.cache;
    }
    Object obj = this.map.get(Integer.valueOf(id));
    if ((obj == null) && (!ifAvailable)) {
      throw DbException.get(90007);
    }
    return obj;
  }
}
