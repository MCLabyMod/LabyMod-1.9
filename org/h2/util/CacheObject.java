package org.h2.util;

import org.h2.engine.SysProperties;
import org.h2.message.DbException;

public abstract class CacheObject
  implements Comparable<CacheObject>
{
  public CacheObject cachePrevious;
  public CacheObject cacheNext;
  public CacheObject cacheChained;
  private int pos;
  private boolean changed;
  
  public abstract boolean canRemove();
  
  public abstract int getMemory();
  
  public void setPos(int pos)
  {
    if ((SysProperties.CHECK) && (
      (this.cachePrevious != null) || (this.cacheNext != null) || (this.cacheChained != null))) {
      DbException.throwInternalError("setPos too late");
    }
    this.pos = pos;
  }
  
  public int getPos()
  {
    return this.pos;
  }
  
  public boolean isChanged()
  {
    return this.changed;
  }
  
  public void setChanged(boolean b)
  {
    this.changed = b;
  }
  
  public int compareTo(CacheObject other)
  {
    return MathUtils.compareInt(getPos(), other.getPos());
  }
  
  public boolean isStream()
  {
    return false;
  }
}
