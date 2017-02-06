package org.h2.index;

import org.h2.engine.Session;
import org.h2.result.Row;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;

abstract class PageData
  extends Page
{
  static final int START_PARENT = 3;
  static final int ROOT = 0;
  static final int UNKNOWN_ROWCOUNT = -1;
  protected final PageDataIndex index;
  protected int parentPageId;
  protected final Data data;
  protected int entryCount;
  protected long[] keys;
  protected boolean written;
  private final int memoryEstimated;
  
  PageData(PageDataIndex index, int pageId, Data data)
  {
    this.index = index;
    this.data = data;
    setPos(pageId);
    this.memoryEstimated = index.getMemoryPerPage();
  }
  
  abstract int getRowCount();
  
  abstract void setRowCountStored(int paramInt);
  
  abstract long getDiskSpaceUsed();
  
  int find(long key)
  {
    int l = 0;int r = this.entryCount;
    while (l < r)
    {
      int i = l + r >>> 1;
      long k = this.keys[i];
      if (k == key) {
        return i;
      }
      if (k > key) {
        r = i;
      } else {
        l = i + 1;
      }
    }
    return l;
  }
  
  abstract int addRowTry(Row paramRow);
  
  abstract Cursor find(Session paramSession, long paramLong1, long paramLong2, boolean paramBoolean);
  
  long getKey(int at)
  {
    return this.keys[at];
  }
  
  abstract PageData split(int paramInt);
  
  void setPageId(int id)
  {
    int old = getPos();
    this.index.getPageStore().removeFromCache(getPos());
    setPos(id);
    this.index.getPageStore().logUndo(this, null);
    remapChildren(old);
  }
  
  abstract long getLastKey();
  
  abstract PageDataLeaf getFirstLeaf();
  
  void setParentPageId(int id)
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.parentPageId = id;
    if (this.written)
    {
      this.changeCount = this.index.getPageStore().getChangeCount();
      this.data.setInt(3, this.parentPageId);
    }
  }
  
  abstract void remapChildren(int paramInt);
  
  abstract boolean remove(long paramLong);
  
  abstract void freeRecursive();
  
  abstract Row getRowWithKey(long paramLong);
  
  public int getMemory()
  {
    return this.memoryEstimated;
  }
  
  int getParentPageId()
  {
    return this.parentPageId;
  }
  
  public boolean canRemove()
  {
    if (this.changeCount >= this.index.getPageStore().getChangeCount()) {
      return false;
    }
    return true;
  }
}
