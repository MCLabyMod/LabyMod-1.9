package org.h2.index;

import org.h2.result.SearchRow;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;

public abstract class PageBtree
  extends Page
{
  static final int ROOT = 0;
  static final int UNKNOWN_ROWCOUNT = -1;
  protected final PageBtreeIndex index;
  protected int parentPageId;
  protected final Data data;
  protected int[] offsets;
  protected int entryCount;
  protected SearchRow[] rows;
  protected int start;
  protected boolean onlyPosition;
  protected boolean written;
  private final int memoryEstimated;
  
  PageBtree(PageBtreeIndex index, int pageId, Data data)
  {
    this.index = index;
    this.data = data;
    setPos(pageId);
    this.memoryEstimated = index.getMemoryPerPage();
  }
  
  abstract int getRowCount();
  
  abstract void setRowCountStored(int paramInt);
  
  int find(SearchRow compare, boolean bigger, boolean add, boolean compareKeys)
  {
    if (compare == null) {
      return 0;
    }
    int l = 0;int r = this.entryCount;
    int comp = 1;
    while (l < r)
    {
      int i = l + r >>> 1;
      SearchRow row = getRow(i);
      comp = this.index.compareRows(row, compare);
      if (comp == 0)
      {
        if ((add) && (this.index.indexType.isUnique()) && 
          (!this.index.containsNullAndAllowMultipleNull(compare))) {
          throw this.index.getDuplicateKeyException(compare.toString());
        }
        if (compareKeys)
        {
          comp = this.index.compareKeys(row, compare);
          if (comp == 0) {
            return i;
          }
        }
      }
      if ((comp > 0) || ((!bigger) && (comp == 0))) {
        r = i;
      } else {
        l = i + 1;
      }
    }
    return l;
  }
  
  abstract int addRowTry(SearchRow paramSearchRow);
  
  abstract void find(PageBtreeCursor paramPageBtreeCursor, SearchRow paramSearchRow, boolean paramBoolean);
  
  abstract void last(PageBtreeCursor paramPageBtreeCursor);
  
  SearchRow getRow(int at)
  {
    SearchRow row = this.rows[at];
    if (row == null)
    {
      row = this.index.readRow(this.data, this.offsets[at], this.onlyPosition, true);
      memoryChange();
      this.rows[at] = row;
    }
    else if (!this.index.hasData(row))
    {
      row = this.index.readRow(row.getKey());
      memoryChange();
      this.rows[at] = row;
    }
    return row;
  }
  
  protected void memoryChange() {}
  
  abstract PageBtree split(int paramInt);
  
  void setPageId(int id)
  {
    this.changeCount = this.index.getPageStore().getChangeCount();
    this.written = false;
    this.index.getPageStore().removeFromCache(getPos());
    setPos(id);
    this.index.getPageStore().logUndo(this, null);
    remapChildren();
  }
  
  abstract PageBtreeLeaf getFirstLeaf();
  
  abstract PageBtreeLeaf getLastLeaf();
  
  void setParentPageId(int id)
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.changeCount = this.index.getPageStore().getChangeCount();
    this.written = false;
    this.parentPageId = id;
  }
  
  abstract void remapChildren();
  
  abstract SearchRow remove(SearchRow paramSearchRow);
  
  abstract void freeRecursive();
  
  protected void readAllRows()
  {
    for (int i = 0; i < this.entryCount; i++)
    {
      SearchRow row = this.rows[i];
      if (row == null)
      {
        row = this.index.readRow(this.data, this.offsets[i], this.onlyPosition, false);
        this.rows[i] = row;
      }
    }
  }
  
  public int getMemory()
  {
    return this.memoryEstimated;
  }
  
  public boolean canRemove()
  {
    if (this.changeCount >= this.index.getPageStore().getChangeCount()) {
      return false;
    }
    return true;
  }
}
