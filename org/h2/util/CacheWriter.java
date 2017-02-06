package org.h2.util;

import org.h2.message.Trace;

public abstract interface CacheWriter
{
  public abstract void writeBack(CacheObject paramCacheObject);
  
  public abstract void flushLog();
  
  public abstract Trace getTrace();
}
