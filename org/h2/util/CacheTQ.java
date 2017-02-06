package org.h2.util;

import java.util.ArrayList;

public class CacheTQ
  implements Cache
{
  static final String TYPE_NAME = "TQ";
  private final Cache lru;
  private final Cache fifo;
  private final SmallLRUCache<Integer, Object> recentlyUsed = SmallLRUCache.newInstance(1024);
  private int lastUsed = -1;
  private int maxMemory;
  
  CacheTQ(CacheWriter writer, int maxMemoryKb)
  {
    this.maxMemory = maxMemoryKb;
    this.lru = new CacheLRU(writer, (int)(maxMemoryKb * 0.8D), false);
    this.fifo = new CacheLRU(writer, (int)(maxMemoryKb * 0.2D), true);
    setMaxMemory(4 * maxMemoryKb);
  }
  
  public void clear()
  {
    this.lru.clear();
    this.fifo.clear();
    this.recentlyUsed.clear();
    this.lastUsed = -1;
  }
  
  public CacheObject find(int pos)
  {
    CacheObject r = this.lru.find(pos);
    if (r == null) {
      r = this.fifo.find(pos);
    }
    return r;
  }
  
  public CacheObject get(int pos)
  {
    CacheObject r = this.lru.find(pos);
    if (r != null) {
      return r;
    }
    r = this.fifo.find(pos);
    if ((r != null) && (!r.isStream()))
    {
      if (this.recentlyUsed.get(Integer.valueOf(pos)) != null)
      {
        if (this.lastUsed != pos)
        {
          this.fifo.remove(pos);
          this.lru.put(r);
        }
      }
      else {
        this.recentlyUsed.put(Integer.valueOf(pos), this);
      }
      this.lastUsed = pos;
    }
    return r;
  }
  
  public ArrayList<CacheObject> getAllChanged()
  {
    ArrayList<CacheObject> changed = New.arrayList();
    changed.addAll(this.lru.getAllChanged());
    changed.addAll(this.fifo.getAllChanged());
    return changed;
  }
  
  public int getMaxMemory()
  {
    return this.maxMemory;
  }
  
  public int getMemory()
  {
    return this.lru.getMemory() + this.fifo.getMemory();
  }
  
  public void put(CacheObject r)
  {
    if (r.isStream())
    {
      this.fifo.put(r);
    }
    else if (this.recentlyUsed.get(Integer.valueOf(r.getPos())) != null)
    {
      this.lru.put(r);
    }
    else
    {
      this.fifo.put(r);
      this.lastUsed = r.getPos();
    }
  }
  
  public boolean remove(int pos)
  {
    boolean result = this.lru.remove(pos);
    if (!result) {
      result = this.fifo.remove(pos);
    }
    this.recentlyUsed.remove(Integer.valueOf(pos));
    return result;
  }
  
  public void setMaxMemory(int maxMemoryKb)
  {
    this.maxMemory = maxMemoryKb;
    this.lru.setMaxMemory((int)(maxMemoryKb * 0.8D));
    this.fifo.setMaxMemory((int)(maxMemoryKb * 0.2D));
    this.recentlyUsed.setMaxSize(4 * maxMemoryKb);
  }
  
  public CacheObject update(int pos, CacheObject record)
  {
    if (this.lru.find(pos) != null) {
      return this.lru.update(pos, record);
    }
    return this.fifo.update(pos, record);
  }
}
