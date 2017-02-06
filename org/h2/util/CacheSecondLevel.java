package org.h2.util;

import java.util.ArrayList;
import java.util.Map;

class CacheSecondLevel
  implements Cache
{
  private final Cache baseCache;
  private final Map<Integer, CacheObject> map;
  
  CacheSecondLevel(Cache cache, Map<Integer, CacheObject> map)
  {
    this.baseCache = cache;
    this.map = map;
  }
  
  public void clear()
  {
    this.map.clear();
    this.baseCache.clear();
  }
  
  public CacheObject find(int pos)
  {
    CacheObject ret = this.baseCache.find(pos);
    if (ret == null) {
      ret = (CacheObject)this.map.get(Integer.valueOf(pos));
    }
    return ret;
  }
  
  public CacheObject get(int pos)
  {
    CacheObject ret = this.baseCache.get(pos);
    if (ret == null) {
      ret = (CacheObject)this.map.get(Integer.valueOf(pos));
    }
    return ret;
  }
  
  public ArrayList<CacheObject> getAllChanged()
  {
    return this.baseCache.getAllChanged();
  }
  
  public int getMaxMemory()
  {
    return this.baseCache.getMaxMemory();
  }
  
  public int getMemory()
  {
    return this.baseCache.getMemory();
  }
  
  public void put(CacheObject r)
  {
    this.baseCache.put(r);
    this.map.put(Integer.valueOf(r.getPos()), r);
  }
  
  public boolean remove(int pos)
  {
    boolean result = this.baseCache.remove(pos);
    result |= this.map.remove(Integer.valueOf(pos)) != null;
    return result;
  }
  
  public void setMaxMemory(int size)
  {
    this.baseCache.setMaxMemory(size);
  }
  
  public CacheObject update(int pos, CacheObject record)
  {
    CacheObject oldRec = this.baseCache.update(pos, record);
    this.map.put(Integer.valueOf(pos), record);
    return oldRec;
  }
}
