package org.h2.index;

import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;

public class PageDataOverflow
  extends Page
{
  static final int START_LAST = 9;
  static final int START_MORE = 11;
  private static final int START_NEXT_OVERFLOW = 7;
  private final PageStore store;
  private int type;
  private int parentPageId;
  private int nextPage;
  private final Data data;
  private int start;
  private int size;
  
  private PageDataOverflow(PageStore store, int pageId, Data data)
  {
    this.store = store;
    setPos(pageId);
    this.data = data;
  }
  
  public static Page read(PageStore store, Data data, int pageId)
  {
    PageDataOverflow p = new PageDataOverflow(store, pageId, data);
    p.read();
    return p;
  }
  
  static PageDataOverflow create(PageStore store, int page, int type, int parentPageId, int next, Data all, int offset, int size)
  {
    Data data = store.createData();
    PageDataOverflow p = new PageDataOverflow(store, page, data);
    store.logUndo(p, null);
    data.writeByte((byte)type);
    data.writeShortInt(0);
    data.writeInt(parentPageId);
    if (type == 3) {
      data.writeInt(next);
    } else {
      data.writeShortInt(size);
    }
    p.start = data.length();
    data.write(all.getBytes(), offset, size);
    p.type = type;
    p.parentPageId = parentPageId;
    p.nextPage = next;
    p.size = size;
    return p;
  }
  
  private void read()
  {
    this.data.reset();
    this.type = this.data.readByte();
    this.data.readShortInt();
    this.parentPageId = this.data.readInt();
    if (this.type == 19)
    {
      this.size = this.data.readShortInt();
      this.nextPage = 0;
    }
    else if (this.type == 3)
    {
      this.nextPage = this.data.readInt();
      this.size = (this.store.getPageSize() - this.data.length());
    }
    else
    {
      throw DbException.get(90030, "page:" + getPos() + " type:" + this.type);
    }
    this.start = this.data.length();
  }
  
  int readInto(Data target)
  {
    target.checkCapacity(this.size);
    if (this.type == 19)
    {
      target.write(this.data.getBytes(), 9, this.size);
      return 0;
    }
    target.write(this.data.getBytes(), 11, this.size);
    return this.nextPage;
  }
  
  int getNextOverflow()
  {
    return this.nextPage;
  }
  
  private void writeHead()
  {
    this.data.writeByte((byte)this.type);
    this.data.writeShortInt(0);
    this.data.writeInt(this.parentPageId);
  }
  
  public void write()
  {
    writeData();
    this.store.writePage(getPos(), this.data);
  }
  
  private void writeData()
  {
    this.data.reset();
    writeHead();
    if (this.type == 3) {
      this.data.writeInt(this.nextPage);
    } else {
      this.data.writeShortInt(this.size);
    }
  }
  
  public String toString()
  {
    return "page[" + getPos() + "] data leaf overflow parent:" + this.parentPageId + " next:" + this.nextPage;
  }
  
  public int getMemory()
  {
    return 120 + this.store.getPageSize() >> 2;
  }
  
  void setParentPageId(int parent)
  {
    this.store.logUndo(this, this.data);
    this.parentPageId = parent;
  }
  
  public void moveTo(Session session, int newPos)
  {
    Page parent = this.store.getPage(this.parentPageId);
    if (parent == null) {
      throw DbException.throwInternalError();
    }
    PageDataOverflow next = null;
    if (this.nextPage != 0) {
      next = (PageDataOverflow)this.store.getPage(this.nextPage);
    }
    this.store.logUndo(this, this.data);
    PageDataOverflow p2 = create(this.store, newPos, this.type, this.parentPageId, this.nextPage, this.data, this.start, this.size);
    
    this.store.update(p2);
    if (next != null)
    {
      next.setParentPageId(newPos);
      this.store.update(next);
    }
    if ((parent instanceof PageDataOverflow))
    {
      PageDataOverflow p1 = (PageDataOverflow)parent;
      p1.setNext(getPos(), newPos);
    }
    else
    {
      PageDataLeaf p1 = (PageDataLeaf)parent;
      p1.setOverflow(getPos(), newPos);
    }
    this.store.update(parent);
    this.store.free(getPos());
  }
  
  private void setNext(int old, int nextPage)
  {
    if ((SysProperties.CHECK) && (old != this.nextPage)) {
      DbException.throwInternalError("move " + this + " " + nextPage);
    }
    this.store.logUndo(this, this.data);
    this.nextPage = nextPage;
    this.data.setInt(7, nextPage);
  }
  
  void free()
  {
    this.store.logUndo(this, this.data);
    this.store.free(getPos());
  }
  
  public boolean canRemove()
  {
    return true;
  }
  
  public boolean isStream()
  {
    return true;
  }
}
