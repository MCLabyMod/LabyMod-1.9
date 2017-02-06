package org.h2.store;

import org.h2.engine.Session;
import org.h2.message.DbException;

public class PageStreamTrunk
  extends Page
{
  private static final int DATA_START = 17;
  int parent;
  int nextTrunk;
  private final PageStore store;
  private int logKey;
  private int[] pageIds;
  private int pageCount;
  private Data data;
  
  private PageStreamTrunk(PageStore store, int parent, int pageId, int next, int logKey, int[] pageIds)
  {
    setPos(pageId);
    this.parent = parent;
    this.store = store;
    this.nextTrunk = next;
    this.logKey = logKey;
    this.pageCount = pageIds.length;
    this.pageIds = pageIds;
  }
  
  private PageStreamTrunk(PageStore store, Data data, int pageId)
  {
    setPos(pageId);
    this.data = data;
    this.store = store;
  }
  
  static PageStreamTrunk read(PageStore store, Data data, int pageId)
  {
    PageStreamTrunk p = new PageStreamTrunk(store, data, pageId);
    p.read();
    return p;
  }
  
  static PageStreamTrunk create(PageStore store, int parent, int pageId, int next, int logKey, int[] pageIds)
  {
    return new PageStreamTrunk(store, parent, pageId, next, logKey, pageIds);
  }
  
  private void read()
  {
    this.data.reset();
    this.data.readByte();
    this.data.readShortInt();
    this.parent = this.data.readInt();
    this.logKey = this.data.readInt();
    this.nextTrunk = this.data.readInt();
    this.pageCount = this.data.readShortInt();
    this.pageIds = new int[this.pageCount];
    for (int i = 0; i < this.pageCount; i++) {
      this.pageIds[i] = this.data.readInt();
    }
  }
  
  int getPageData(int index)
  {
    if (index >= this.pageIds.length) {
      return -1;
    }
    return this.pageIds[index];
  }
  
  public void write()
  {
    this.data = this.store.createData();
    this.data.writeByte((byte)7);
    this.data.writeShortInt(0);
    this.data.writeInt(this.parent);
    this.data.writeInt(this.logKey);
    this.data.writeInt(this.nextTrunk);
    this.data.writeShortInt(this.pageCount);
    for (int i = 0; i < this.pageCount; i++) {
      this.data.writeInt(this.pageIds[i]);
    }
    this.store.writePage(getPos(), this.data);
  }
  
  static int getPagesAddressed(int pageSize)
  {
    return (pageSize - 17) / 4;
  }
  
  boolean contains(int dataPageId)
  {
    for (int i = 0; i < this.pageCount; i++) {
      if (this.pageIds[i] == dataPageId) {
        return true;
      }
    }
    return false;
  }
  
  int free(int lastUsedPage)
  {
    this.store.free(getPos(), false);
    int freed = 1;
    boolean notUsed = false;
    for (int i = 0; i < this.pageCount; i++)
    {
      int page = this.pageIds[i];
      if (notUsed) {
        this.store.freeUnused(page);
      } else {
        this.store.free(page, false);
      }
      freed++;
      if (page == lastUsedPage) {
        notUsed = true;
      }
    }
    return freed;
  }
  
  public int getMemory()
  {
    return this.store.getPageSize() >> 2;
  }
  
  public void moveTo(Session session, int newPos) {}
  
  int getLogKey()
  {
    return this.logKey;
  }
  
  public int getNextTrunk()
  {
    return this.nextTrunk;
  }
  
  static class Iterator
  {
    private final PageStore store;
    private int first;
    private int next;
    private int previous;
    private boolean canDelete;
    private int current;
    
    Iterator(PageStore store, int first)
    {
      this.store = store;
      this.next = first;
    }
    
    int getCurrentPageId()
    {
      return this.current;
    }
    
    PageStreamTrunk next()
    {
      this.canDelete = false;
      if (this.first == 0) {
        this.first = this.next;
      } else if (this.first == this.next) {
        return null;
      }
      if ((this.next == 0) || (this.next >= this.store.getPageCount())) {
        return null;
      }
      this.current = this.next;
      Page p;
      try
      {
        p = this.store.getPage(this.next);
      }
      catch (DbException e)
      {
        if (e.getErrorCode() == 90030) {
          return null;
        }
        throw e;
      }
      if ((p == null) || ((p instanceof PageStreamTrunk)) || ((p instanceof PageStreamData))) {
        this.canDelete = true;
      }
      if (!(p instanceof PageStreamTrunk)) {
        return null;
      }
      PageStreamTrunk t = (PageStreamTrunk)p;
      if ((this.previous > 0) && (t.parent != this.previous)) {
        return null;
      }
      this.previous = this.next;
      this.next = t.nextTrunk;
      return t;
    }
    
    boolean canDelete()
    {
      return this.canDelete;
    }
  }
  
  public boolean canRemove()
  {
    return true;
  }
  
  public String toString()
  {
    return "page[" + getPos() + "] stream trunk key:" + this.logKey + " next:" + this.nextTrunk;
  }
  
  public boolean canMove()
  {
    return false;
  }
}
