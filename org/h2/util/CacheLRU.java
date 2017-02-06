package org.h2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.Trace;

public class CacheLRU
  implements Cache
{
  static final String TYPE_NAME = "LRU";
  private final CacheWriter writer;
  private final boolean fifo;
  private final CacheObject head = new CacheHead();
  private final int mask;
  private CacheObject[] values;
  private int recordCount;
  private final int len;
  private int maxMemory;
  private int memory;
  
  CacheLRU(CacheWriter writer, int maxMemoryKb, boolean fifo)
  {
    this.writer = writer;
    this.fifo = fifo;
    setMaxMemory(maxMemoryKb);
    this.len = MathUtils.nextPowerOf2(this.maxMemory / 64);
    this.mask = (this.len - 1);
    clear();
  }
  
  public static Cache getCache(CacheWriter writer, String cacheType, int cacheSize)
  {
    Map<Integer, CacheObject> secondLevel = null;
    if (cacheType.startsWith("SOFT_"))
    {
      secondLevel = new SoftHashMap();
      cacheType = cacheType.substring("SOFT_".length());
    }
    Cache cache;
    if ("LRU".equals(cacheType))
    {
      cache = new CacheLRU(writer, cacheSize, false);
    }
    else
    {
      Cache cache;
      if ("TQ".equals(cacheType)) {
        cache = new CacheTQ(writer, cacheSize);
      } else {
        throw DbException.getInvalidValueException("CACHE_TYPE", cacheType);
      }
    }
    Cache cache;
    if (secondLevel != null) {
      cache = new CacheSecondLevel(cache, secondLevel);
    }
    return cache;
  }
  
  public void clear()
  {
    this.head.cacheNext = (this.head.cachePrevious = this.head);
    
    this.values = null;
    this.values = new CacheObject[this.len];
    this.recordCount = 0;
    this.memory = (this.len * 8);
  }
  
  public void put(CacheObject rec)
  {
    if (SysProperties.CHECK)
    {
      int pos = rec.getPos();
      CacheObject old = find(pos);
      if (old != null) {
        DbException.throwInternalError("try to add a record twice at pos " + pos);
      }
    }
    int index = rec.getPos() & this.mask;
    rec.cacheChained = this.values[index];
    this.values[index] = rec;
    this.recordCount += 1;
    this.memory += rec.getMemory();
    addToFront(rec);
    removeOldIfRequired();
  }
  
  public CacheObject update(int pos, CacheObject rec)
  {
    CacheObject old = find(pos);
    if (old == null)
    {
      put(rec);
    }
    else
    {
      if ((SysProperties.CHECK) && 
        (old != rec)) {
        DbException.throwInternalError("old!=record pos:" + pos + " old:" + old + " new:" + rec);
      }
      if (!this.fifo)
      {
        removeFromLinkedList(rec);
        addToFront(rec);
      }
    }
    return old;
  }
  
  private void removeOldIfRequired()
  {
    if (this.memory >= this.maxMemory) {
      removeOld();
    }
  }
  
  private void removeOld()
  {
    int i = 0;
    ArrayList<CacheObject> changed = New.arrayList();
    int mem = this.memory;
    int rc = this.recordCount;
    boolean flushed = false;
    CacheObject next = this.head.cacheNext;
    while (rc > 16)
    {
      if (changed.size() == 0 ? 
        mem <= this.maxMemory : 
        
        mem * 4 <= this.maxMemory * 3) {
        break;
      }
      CacheObject check = next;
      next = check.cacheNext;
      i++;
      if (i >= this.recordCount) {
        if (!flushed)
        {
          this.writer.flushLog();
          flushed = true;
          i = 0;
        }
        else
        {
          this.writer.getTrace().info("cannot remove records, cache size too small? records:" + this.recordCount + " memory:" + this.memory);
          
          break;
        }
      }
      if ((SysProperties.CHECK) && (check == this.head)) {
        DbException.throwInternalError("try to remove head");
      }
      if (!check.canRemove())
      {
        removeFromLinkedList(check);
        addToFront(check);
      }
      else
      {
        rc--;
        mem -= check.getMemory();
        if (check.isChanged()) {
          changed.add(check);
        } else {
          remove(check.getPos());
        }
      }
    }
    if (changed.size() > 0)
    {
      if (!flushed) {
        this.writer.flushLog();
      }
      Collections.sort(changed);
      int max = this.maxMemory;
      int size = changed.size();
      try
      {
        this.maxMemory = Integer.MAX_VALUE;
        for (i = 0; i < size; i++)
        {
          CacheObject rec = (CacheObject)changed.get(i);
          this.writer.writeBack(rec);
        }
      }
      finally
      {
        this.maxMemory = max;
      }
      for (i = 0; i < size; i++)
      {
        CacheObject rec = (CacheObject)changed.get(i);
        remove(rec.getPos());
        if ((SysProperties.CHECK) && 
          (rec.cacheNext != null)) {
          throw DbException.throwInternalError();
        }
      }
    }
  }
  
  private void addToFront(CacheObject rec)
  {
    if ((SysProperties.CHECK) && (rec == this.head)) {
      DbException.throwInternalError("try to move head");
    }
    rec.cacheNext = this.head;
    rec.cachePrevious = this.head.cachePrevious;
    rec.cachePrevious.cacheNext = rec;
    this.head.cachePrevious = rec;
  }
  
  private void removeFromLinkedList(CacheObject rec)
  {
    if ((SysProperties.CHECK) && (rec == this.head)) {
      DbException.throwInternalError("try to remove head");
    }
    rec.cachePrevious.cacheNext = rec.cacheNext;
    rec.cacheNext.cachePrevious = rec.cachePrevious;
    
    rec.cacheNext = null;
    rec.cachePrevious = null;
  }
  
  public boolean remove(int pos)
  {
    int index = pos & this.mask;
    CacheObject rec = this.values[index];
    if (rec == null) {
      return false;
    }
    if (rec.getPos() == pos)
    {
      this.values[index] = rec.cacheChained;
    }
    else
    {
      CacheObject last;
      do
      {
        last = rec;
        rec = rec.cacheChained;
        if (rec == null) {
          return false;
        }
      } while (rec.getPos() != pos);
      last.cacheChained = rec.cacheChained;
    }
    this.recordCount -= 1;
    this.memory -= rec.getMemory();
    removeFromLinkedList(rec);
    if (SysProperties.CHECK)
    {
      rec.cacheChained = null;
      CacheObject o = find(pos);
      if (o != null) {
        DbException.throwInternalError("not removed: " + o);
      }
    }
    return true;
  }
  
  public CacheObject find(int pos)
  {
    CacheObject rec = this.values[(pos & this.mask)];
    while ((rec != null) && (rec.getPos() != pos)) {
      rec = rec.cacheChained;
    }
    return rec;
  }
  
  public CacheObject get(int pos)
  {
    CacheObject rec = find(pos);
    if ((rec != null) && 
      (!this.fifo))
    {
      removeFromLinkedList(rec);
      addToFront(rec);
    }
    return rec;
  }
  
  public ArrayList<CacheObject> getAllChanged()
  {
    ArrayList<CacheObject> list = New.arrayList();
    CacheObject rec = this.head.cacheNext;
    while (rec != this.head)
    {
      if (rec.isChanged()) {
        list.add(rec);
      }
      rec = rec.cacheNext;
    }
    return list;
  }
  
  public void setMaxMemory(int maxKb)
  {
    int newSize = MathUtils.convertLongToInt(maxKb * 1024L / 4L);
    this.maxMemory = (newSize < 0 ? 0 : newSize);
    
    removeOldIfRequired();
  }
  
  public int getMaxMemory()
  {
    return (int)(this.maxMemory * 4L / 1024L);
  }
  
  public int getMemory()
  {
    return (int)(this.memory * 4L / 1024L);
  }
}
