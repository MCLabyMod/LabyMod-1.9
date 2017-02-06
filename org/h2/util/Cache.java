package org.h2.util;

import java.util.ArrayList;

public abstract interface Cache
{
  public abstract ArrayList<CacheObject> getAllChanged();
  
  public abstract void clear();
  
  public abstract CacheObject get(int paramInt);
  
  public abstract void put(CacheObject paramCacheObject);
  
  public abstract CacheObject update(int paramInt, CacheObject paramCacheObject);
  
  public abstract boolean remove(int paramInt);
  
  public abstract CacheObject find(int paramInt);
  
  public abstract void setMaxMemory(int paramInt);
  
  public abstract int getMaxMemory();
  
  public abstract int getMemory();
}
