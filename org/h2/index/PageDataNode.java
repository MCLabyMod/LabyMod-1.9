package org.h2.index;

import java.util.Arrays;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;
import org.h2.util.Utils;

public class PageDataNode
  extends PageData
{
  private int[] childPageIds;
  private int rowCountStored = -1;
  private int rowCount = -1;
  private int length;
  
  private PageDataNode(PageDataIndex index, int pageId, Data data)
  {
    super(index, pageId, data);
  }
  
  static PageDataNode create(PageDataIndex index, int pageId, int parentPageId)
  {
    PageDataNode p = new PageDataNode(index, pageId, index.getPageStore().createData());
    
    index.getPageStore().logUndo(p, null);
    p.parentPageId = parentPageId;
    p.writeHead();
    
    p.length = (p.data.length() + 4);
    return p;
  }
  
  public static Page read(PageDataIndex index, Data data, int pageId)
  {
    PageDataNode p = new PageDataNode(index, pageId, data);
    p.read();
    return p;
  }
  
  private void read()
  {
    this.data.reset();
    this.data.readByte();
    this.data.readShortInt();
    this.parentPageId = this.data.readInt();
    int indexId = this.data.readVarInt();
    if (indexId != this.index.getId()) {
      throw DbException.get(90030, "page:" + getPos() + " expected index:" + this.index.getId() + "got:" + indexId);
    }
    this.rowCount = (this.rowCountStored = this.data.readInt());
    this.entryCount = this.data.readShortInt();
    this.childPageIds = new int[this.entryCount + 1];
    this.childPageIds[this.entryCount] = this.data.readInt();
    this.keys = Utils.newLongArray(this.entryCount);
    for (int i = 0; i < this.entryCount; i++)
    {
      this.childPageIds[i] = this.data.readInt();
      this.keys[i] = this.data.readVarLong();
    }
    this.length = this.data.length();
    check();
    this.written = true;
  }
  
  private void addChild(int x, int childPageId, long key)
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    this.childPageIds = insert(this.childPageIds, this.entryCount + 1, x + 1, childPageId);
    this.keys = insert(this.keys, this.entryCount, x, key);
    this.entryCount += 1;
    this.length += 4 + Data.getVarLongLen(key);
  }
  
  int addRowTry(Row row)
  {
    this.index.getPageStore().logUndo(this, this.data);
    int keyOffsetPairLen = 4 + Data.getVarLongLen(row.getKey());
    for (;;)
    {
      int x = find(row.getKey());
      PageData page = this.index.getPage(this.childPageIds[x], getPos());
      int splitPoint = page.addRowTry(row);
      if (splitPoint == -1) {
        break;
      }
      if (this.length + keyOffsetPairLen > this.index.getPageStore().getPageSize()) {
        return this.entryCount / 2;
      }
      long pivot = splitPoint == 0 ? row.getKey() : page.getKey(splitPoint - 1);
      PageData page2 = page.split(splitPoint);
      this.index.getPageStore().update(page);
      this.index.getPageStore().update(page2);
      addChild(x, page2.getPos(), pivot);
      this.index.getPageStore().update(this);
    }
    updateRowCount(1);
    return -1;
  }
  
  private void updateRowCount(int offset)
  {
    if (this.rowCount != -1) {
      this.rowCount += offset;
    }
    if (this.rowCountStored != -1)
    {
      this.rowCountStored = -1;
      this.index.getPageStore().logUndo(this, this.data);
      if (this.written) {
        writeHead();
      }
      this.index.getPageStore().update(this);
    }
  }
  
  Cursor find(Session session, long minKey, long maxKey, boolean multiVersion)
  {
    int x = find(minKey);
    int child = this.childPageIds[x];
    return this.index.getPage(child, getPos()).find(session, minKey, maxKey, multiVersion);
  }
  
  PageData split(int splitPoint)
  {
    int newPageId = this.index.getPageStore().allocatePage();
    PageDataNode p2 = create(this.index, newPageId, this.parentPageId);
    int firstChild = this.childPageIds[splitPoint];
    for (int i = splitPoint; i < this.entryCount;)
    {
      p2.addChild(p2.entryCount, this.childPageIds[(splitPoint + 1)], this.keys[splitPoint]);
      removeChild(splitPoint);
    }
    int lastChild = this.childPageIds[(splitPoint - 1)];
    removeChild(splitPoint - 1);
    this.childPageIds[(splitPoint - 1)] = lastChild;
    p2.childPageIds[0] = firstChild;
    p2.remapChildren(getPos());
    return p2;
  }
  
  protected void remapChildren(int old)
  {
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      PageData p = this.index.getPage(child, old);
      p.setParentPageId(getPos());
      this.index.getPageStore().update(p);
    }
  }
  
  void init(PageData page1, long pivot, PageData page2)
  {
    this.entryCount = 1;
    this.childPageIds = new int[] { page1.getPos(), page2.getPos() };
    this.keys = new long[] { pivot };
    this.length += 4 + Data.getVarLongLen(pivot);
    check();
  }
  
  long getLastKey()
  {
    return this.index.getPage(this.childPageIds[this.entryCount], getPos()).getLastKey();
  }
  
  PageDataLeaf getNextPage(long key)
  {
    int i = find(key) + 1;
    if (i > this.entryCount)
    {
      if (this.parentPageId == 0) {
        return null;
      }
      PageDataNode next = (PageDataNode)this.index.getPage(this.parentPageId, -1);
      return next.getNextPage(key);
    }
    PageData page = this.index.getPage(this.childPageIds[i], getPos());
    return page.getFirstLeaf();
  }
  
  PageDataLeaf getFirstLeaf()
  {
    int child = this.childPageIds[0];
    return this.index.getPage(child, getPos()).getFirstLeaf();
  }
  
  boolean remove(long key)
  {
    int at = find(key);
    
    PageData page = this.index.getPage(this.childPageIds[at], getPos());
    boolean empty = page.remove(key);
    this.index.getPageStore().logUndo(this, this.data);
    updateRowCount(-1);
    if (!empty) {
      return false;
    }
    this.index.getPageStore().free(page.getPos());
    if (this.entryCount < 1) {
      return true;
    }
    removeChild(at);
    this.index.getPageStore().update(this);
    return false;
  }
  
  void freeRecursive()
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.index.getPageStore().free(getPos());
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      this.index.getPage(child, getPos()).freeRecursive();
    }
  }
  
  Row getRowWithKey(long key)
  {
    int at = find(key);
    PageData page = this.index.getPage(this.childPageIds[at], getPos());
    return page.getRowWithKey(key);
  }
  
  int getRowCount()
  {
    if (this.rowCount == -1)
    {
      int count = 0;
      for (int i = 0; i < this.entryCount + 1; i++)
      {
        int child = this.childPageIds[i];
        PageData page = this.index.getPage(child, getPos());
        if (getPos() == page.getPos()) {
          throw DbException.throwInternalError("Page is its own child: " + getPos());
        }
        count += page.getRowCount();
        this.index.getDatabase().setProgress(0, this.index.getTable() + "." + this.index.getName(), count, Integer.MAX_VALUE);
      }
      this.rowCount = count;
    }
    return this.rowCount;
  }
  
  long getDiskSpaceUsed()
  {
    long count = 0L;
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      PageData page = this.index.getPage(child, getPos());
      if (getPos() == page.getPos()) {
        throw DbException.throwInternalError("Page is its own child: " + getPos());
      }
      count += page.getDiskSpaceUsed();
      this.index.getDatabase().setProgress(0, this.index.getTable() + "." + this.index.getName(), (int)(count >> 16), Integer.MAX_VALUE);
    }
    return count;
  }
  
  void setRowCountStored(int rowCount)
  {
    this.rowCount = rowCount;
    if (this.rowCountStored != rowCount)
    {
      this.rowCountStored = rowCount;
      this.index.getPageStore().logUndo(this, this.data);
      if (this.written)
      {
        this.changeCount = this.index.getPageStore().getChangeCount();
        writeHead();
      }
      this.index.getPageStore().update(this);
    }
  }
  
  private void check()
  {
    if (SysProperties.CHECK) {
      for (int i = 0; i < this.entryCount + 1; i++)
      {
        int child = this.childPageIds[i];
        if (child == 0) {
          DbException.throwInternalError();
        }
      }
    }
  }
  
  public void write()
  {
    writeData();
    this.index.getPageStore().writePage(getPos(), this.data);
  }
  
  private void writeHead()
  {
    this.data.reset();
    this.data.writeByte((byte)2);
    this.data.writeShortInt(0);
    if ((SysProperties.CHECK2) && 
      (this.data.length() != 3)) {
      DbException.throwInternalError();
    }
    this.data.writeInt(this.parentPageId);
    this.data.writeVarInt(this.index.getId());
    this.data.writeInt(this.rowCountStored);
    this.data.writeShortInt(this.entryCount);
  }
  
  private void writeData()
  {
    if (this.written) {
      return;
    }
    check();
    writeHead();
    this.data.writeInt(this.childPageIds[this.entryCount]);
    for (int i = 0; i < this.entryCount; i++)
    {
      this.data.writeInt(this.childPageIds[i]);
      this.data.writeVarLong(this.keys[i]);
    }
    if (this.length != this.data.length()) {
      DbException.throwInternalError("expected pos: " + this.length + " got: " + this.data.length());
    }
    this.written = true;
  }
  
  private void removeChild(int i)
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    int removedKeyIndex = i < this.entryCount ? i : i - 1;
    this.entryCount -= 1;
    this.length -= 4 + Data.getVarLongLen(this.keys[removedKeyIndex]);
    if (this.entryCount < 0) {
      DbException.throwInternalError();
    }
    this.keys = remove(this.keys, this.entryCount + 1, removedKeyIndex);
    this.childPageIds = remove(this.childPageIds, this.entryCount + 2, i);
  }
  
  public String toString()
  {
    return "page[" + getPos() + "] data node table:" + this.index.getId() + " entries:" + this.entryCount + " " + Arrays.toString(this.childPageIds);
  }
  
  public void moveTo(Session session, int newPos)
  {
    PageStore store = this.index.getPageStore();
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      store.getPage(child);
    }
    if (this.parentPageId != 0) {
      store.getPage(this.parentPageId);
    }
    store.logUndo(this, this.data);
    PageDataNode p2 = create(this.index, newPos, this.parentPageId);
    p2.rowCountStored = this.rowCountStored;
    p2.rowCount = this.rowCount;
    p2.childPageIds = this.childPageIds;
    p2.keys = this.keys;
    p2.entryCount = this.entryCount;
    p2.length = this.length;
    store.update(p2);
    if (this.parentPageId == 0)
    {
      this.index.setRootPageId(session, newPos);
    }
    else
    {
      PageDataNode p = (PageDataNode)store.getPage(this.parentPageId);
      p.moveChild(getPos(), newPos);
    }
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      PageData p = (PageData)store.getPage(child);
      p.setParentPageId(newPos);
      store.update(p);
    }
    store.free(getPos());
  }
  
  void moveChild(int oldPos, int newPos)
  {
    for (int i = 0; i < this.entryCount + 1; i++) {
      if (this.childPageIds[i] == oldPos)
      {
        this.index.getPageStore().logUndo(this, this.data);
        this.written = false;
        this.changeCount = this.index.getPageStore().getChangeCount();
        this.childPageIds[i] = newPos;
        this.index.getPageStore().update(this);
        return;
      }
    }
    throw DbException.throwInternalError();
  }
}
