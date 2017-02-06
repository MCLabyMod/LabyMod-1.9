package org.h2.store;

import org.h2.engine.Session;
import org.h2.util.BitField;

public class PageFreeList
  extends Page
{
  private static final int DATA_START = 3;
  private final PageStore store;
  private final BitField used;
  private final int pageCount;
  private boolean full;
  private Data data;
  
  private PageFreeList(PageStore store, int pageId)
  {
    setPos(pageId);
    this.store = store;
    this.pageCount = ((store.getPageSize() - 3) * 8);
    this.used = new BitField(this.pageCount);
    this.used.set(0);
  }
  
  static PageFreeList read(PageStore store, Data data, int pageId)
  {
    PageFreeList p = new PageFreeList(store, pageId);
    p.data = data;
    p.read();
    return p;
  }
  
  static PageFreeList create(PageStore store, int pageId)
  {
    return new PageFreeList(store, pageId);
  }
  
  int allocate(BitField exclude, int first)
  {
    if (this.full) {
      return -1;
    }
    int start = Math.max(0, first - getPos());
    for (;;)
    {
      int free = this.used.nextClearBit(start);
      if (free >= this.pageCount)
      {
        if (start == 0) {
          this.full = true;
        }
        return -1;
      }
      if ((exclude != null) && (exclude.get(free + getPos())))
      {
        start = exclude.nextClearBit(free + getPos()) - getPos();
        if (start >= this.pageCount) {
          return -1;
        }
      }
      else
      {
        this.used.set(free);
        this.store.logUndo(this, this.data);
        this.store.update(this);
        return free + getPos();
      }
    }
  }
  
  int getFirstFree(int first)
  {
    if (this.full) {
      return -1;
    }
    int start = Math.max(0, first - getPos());
    int free = this.used.nextClearBit(start);
    if (free >= this.pageCount) {
      return -1;
    }
    return free + getPos();
  }
  
  int getLastUsed()
  {
    int last = this.used.length() - 1;
    return last <= 0 ? -1 : last + getPos();
  }
  
  void allocate(int pageId)
  {
    int idx = pageId - getPos();
    if ((idx >= 0) && (!this.used.get(idx)))
    {
      this.used.set(idx);
      this.store.logUndo(this, this.data);
      this.store.update(this);
    }
  }
  
  void free(int pageId)
  {
    this.full = false;
    this.store.logUndo(this, this.data);
    this.used.clear(pageId - getPos());
    this.store.update(this);
  }
  
  private void read()
  {
    this.data.reset();
    this.data.readByte();
    this.data.readShortInt();
    for (int i = 0; i < this.pageCount; i += 8)
    {
      int x = this.data.readByte() & 0xFF;
      this.used.setByte(i, x);
    }
    this.full = false;
  }
  
  public void write()
  {
    this.data = this.store.createData();
    this.data.writeByte((byte)6);
    this.data.writeShortInt(0);
    for (int i = 0; i < this.pageCount; i += 8) {
      this.data.writeByte((byte)this.used.getByte(i));
    }
    this.store.writePage(getPos(), this.data);
  }
  
  public static int getPagesAddressed(int pageSize)
  {
    return (pageSize - 3) * 8;
  }
  
  public int getMemory()
  {
    return this.store.getPageSize() >> 2;
  }
  
  boolean isUsed(int pageId)
  {
    return this.used.get(pageId - getPos());
  }
  
  public void moveTo(Session session, int newPos)
  {
    this.store.free(getPos(), false);
  }
  
  public String toString()
  {
    return "page [" + getPos() + "] freeList" + (this.full ? "full" : "");
  }
  
  public boolean canRemove()
  {
    return true;
  }
  
  public boolean canMove()
  {
    return false;
  }
}
