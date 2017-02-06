package org.h2.index;

import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.result.SearchRow;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;
import org.h2.util.Utils;

public class PageBtreeNode
  extends PageBtree
{
  private static final int CHILD_OFFSET_PAIR_LENGTH = 6;
  private static final int MAX_KEY_LENGTH = 10;
  private final boolean pageStoreInternalCount;
  private int[] childPageIds;
  private int rowCountStored = -1;
  private int rowCount = -1;
  
  private PageBtreeNode(PageBtreeIndex index, int pageId, Data data)
  {
    super(index, pageId, data);
    this.pageStoreInternalCount = index.getDatabase().getSettings().pageStoreInternalCount;
  }
  
  public static Page read(PageBtreeIndex index, Data data, int pageId)
  {
    PageBtreeNode p = new PageBtreeNode(index, pageId, data);
    p.read();
    return p;
  }
  
  static PageBtreeNode create(PageBtreeIndex index, int pageId, int parentPageId)
  {
    PageBtreeNode p = new PageBtreeNode(index, pageId, index.getPageStore().createData());
    
    index.getPageStore().logUndo(p, null);
    p.parentPageId = parentPageId;
    p.writeHead();
    
    p.start = (p.data.length() + 4);
    p.rows = SearchRow.EMPTY_ARRAY;
    if (p.pageStoreInternalCount) {
      p.rowCount = 0;
    }
    return p;
  }
  
  private void read()
  {
    this.data.reset();
    int type = this.data.readByte();
    this.data.readShortInt();
    this.parentPageId = this.data.readInt();
    this.onlyPosition = ((type & 0x10) == 0);
    int indexId = this.data.readVarInt();
    if (indexId != this.index.getId()) {
      throw DbException.get(90030, "page:" + getPos() + " expected index:" + this.index.getId() + "got:" + indexId);
    }
    this.rowCount = (this.rowCountStored = this.data.readInt());
    this.entryCount = this.data.readShortInt();
    this.childPageIds = new int[this.entryCount + 1];
    this.childPageIds[this.entryCount] = this.data.readInt();
    this.rows = (this.entryCount == 0 ? SearchRow.EMPTY_ARRAY : new SearchRow[this.entryCount]);
    this.offsets = Utils.newIntArray(this.entryCount);
    for (int i = 0; i < this.entryCount; i++)
    {
      this.childPageIds[i] = this.data.readInt();
      this.offsets[i] = this.data.readShortInt();
    }
    check();
    this.start = this.data.length();
    this.written = true;
  }
  
  private int addChildTry(SearchRow row)
  {
    if (this.entryCount < 4) {
      return -1;
    }
    int startData;
    int startData;
    if (this.onlyPosition)
    {
      startData = this.entryCount + 10;
    }
    else
    {
      int rowLength = this.index.getRowSize(this.data, row, this.onlyPosition);
      int pageSize = this.index.getPageStore().getPageSize();
      int last = this.entryCount == 0 ? pageSize : this.offsets[(this.entryCount - 1)];
      startData = last - rowLength;
    }
    if (startData < this.start + 6) {
      return this.entryCount / 2;
    }
    return -1;
  }
  
  private void addChild(int x, int childPageId, SearchRow row)
  {
    int rowLength = this.index.getRowSize(this.data, row, this.onlyPosition);
    int pageSize = this.index.getPageStore().getPageSize();
    int last = this.entryCount == 0 ? pageSize : this.offsets[(this.entryCount - 1)];
    if (last - rowLength < this.start + 6)
    {
      readAllRows();
      this.onlyPosition = true;
      
      int o = pageSize;
      for (int i = 0; i < this.entryCount; i++)
      {
        o -= this.index.getRowSize(this.data, getRow(i), true);
        this.offsets[i] = o;
      }
      last = this.entryCount == 0 ? pageSize : this.offsets[(this.entryCount - 1)];
      rowLength = this.index.getRowSize(this.data, row, true);
      if ((SysProperties.CHECK) && (last - rowLength < this.start + 6)) {
        throw DbException.throwInternalError();
      }
    }
    int offset = last - rowLength;
    if ((this.entryCount > 0) && 
      (x < this.entryCount)) {
      offset = (x == 0 ? pageSize : this.offsets[(x - 1)]) - rowLength;
    }
    this.rows = ((SearchRow[])insert(this.rows, this.entryCount, x, row));
    this.offsets = insert(this.offsets, this.entryCount, x, offset);
    add(this.offsets, x + 1, this.entryCount + 1, -rowLength);
    this.childPageIds = insert(this.childPageIds, this.entryCount + 1, x + 1, childPageId);
    this.start += 6;
    if ((this.pageStoreInternalCount) && 
      (this.rowCount != -1)) {
      this.rowCount += offset;
    }
    this.entryCount += 1;
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
  }
  
  int addRowTry(SearchRow row)
  {
    for (;;)
    {
      int x = find(row, false, true, true);
      PageBtree page = this.index.getPage(this.childPageIds[x]);
      int splitPoint = page.addRowTry(row);
      if (splitPoint == -1) {
        break;
      }
      SearchRow pivot = page.getRow(splitPoint - 1);
      this.index.getPageStore().logUndo(this, this.data);
      int splitPoint2 = addChildTry(pivot);
      if (splitPoint2 != -1) {
        return splitPoint2;
      }
      PageBtree page2 = page.split(splitPoint);
      readAllRows();
      addChild(x, page2.getPos(), pivot);
      this.index.getPageStore().update(page);
      this.index.getPageStore().update(page2);
      this.index.getPageStore().update(this);
    }
    updateRowCount(1);
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
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
  
  PageBtree split(int splitPoint)
  {
    int newPageId = this.index.getPageStore().allocatePage();
    PageBtreeNode p2 = create(this.index, newPageId, this.parentPageId);
    this.index.getPageStore().logUndo(this, this.data);
    if (this.onlyPosition) {
      p2.onlyPosition = true;
    }
    int firstChild = this.childPageIds[splitPoint];
    readAllRows();
    for (int i = splitPoint; i < this.entryCount;)
    {
      p2.addChild(p2.entryCount, this.childPageIds[(splitPoint + 1)], getRow(splitPoint));
      removeChild(splitPoint);
    }
    int lastChild = this.childPageIds[(splitPoint - 1)];
    removeChild(splitPoint - 1);
    this.childPageIds[(splitPoint - 1)] = lastChild;
    if (p2.childPageIds == null) {
      p2.childPageIds = new int[1];
    }
    p2.childPageIds[0] = firstChild;
    p2.remapChildren();
    return p2;
  }
  
  protected void remapChildren()
  {
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      PageBtree p = this.index.getPage(child);
      p.setParentPageId(getPos());
      this.index.getPageStore().update(p);
    }
  }
  
  void init(PageBtree page1, SearchRow pivot, PageBtree page2)
  {
    this.entryCount = 0;
    this.childPageIds = new int[] { page1.getPos() };
    this.rows = SearchRow.EMPTY_ARRAY;
    this.offsets = Utils.EMPTY_INT_ARRAY;
    addChild(0, page2.getPos(), pivot);
    if (this.pageStoreInternalCount) {
      this.rowCount = (page1.getRowCount() + page2.getRowCount());
    }
    check();
  }
  
  void find(PageBtreeCursor cursor, SearchRow first, boolean bigger)
  {
    int i = find(first, bigger, false, false);
    if (i > this.entryCount)
    {
      if (this.parentPageId == 0) {
        return;
      }
      PageBtreeNode next = (PageBtreeNode)this.index.getPage(this.parentPageId);
      next.find(cursor, first, bigger);
      return;
    }
    PageBtree page = this.index.getPage(this.childPageIds[i]);
    page.find(cursor, first, bigger);
  }
  
  void last(PageBtreeCursor cursor)
  {
    int child = this.childPageIds[this.entryCount];
    this.index.getPage(child).last(cursor);
  }
  
  PageBtreeLeaf getFirstLeaf()
  {
    int child = this.childPageIds[0];
    return this.index.getPage(child).getFirstLeaf();
  }
  
  PageBtreeLeaf getLastLeaf()
  {
    int child = this.childPageIds[this.entryCount];
    return this.index.getPage(child).getLastLeaf();
  }
  
  SearchRow remove(SearchRow row)
  {
    int at = find(row, false, false, true);
    
    PageBtree page = this.index.getPage(this.childPageIds[at]);
    SearchRow last = page.remove(row);
    this.index.getPageStore().logUndo(this, this.data);
    updateRowCount(-1);
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    if (last == null) {
      return null;
    }
    if (last == row)
    {
      this.index.getPageStore().free(page.getPos());
      if (this.entryCount < 1) {
        return row;
      }
      if (at == this.entryCount) {
        last = getRow(at - 1);
      } else {
        last = null;
      }
      removeChild(at);
      this.index.getPageStore().update(this);
      return last;
    }
    if (at == this.entryCount) {
      return last;
    }
    int child = this.childPageIds[at];
    removeChild(at);
    
    addChild(at, child, last);
    
    int temp = this.childPageIds[at];
    this.childPageIds[at] = this.childPageIds[(at + 1)];
    this.childPageIds[(at + 1)] = temp;
    this.index.getPageStore().update(this);
    return null;
  }
  
  int getRowCount()
  {
    if (this.rowCount == -1)
    {
      int count = 0;
      for (int i = 0; i < this.entryCount + 1; i++)
      {
        int child = this.childPageIds[i];
        PageBtree page = this.index.getPage(child);
        count += page.getRowCount();
        this.index.getDatabase().setProgress(0, this.index.getName(), count, Integer.MAX_VALUE);
      }
      this.rowCount = count;
    }
    return this.rowCount;
  }
  
  void setRowCountStored(int rowCount)
  {
    if ((rowCount < 0) && (this.pageStoreInternalCount)) {
      return;
    }
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
    check();
    writeData();
    this.index.getPageStore().writePage(getPos(), this.data);
  }
  
  private void writeHead()
  {
    this.data.reset();
    this.data.writeByte((byte)(0x5 | (this.onlyPosition ? 0 : 16)));
    
    this.data.writeShortInt(0);
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
    readAllRows();
    writeHead();
    this.data.writeInt(this.childPageIds[this.entryCount]);
    for (int i = 0; i < this.entryCount; i++)
    {
      this.data.writeInt(this.childPageIds[i]);
      this.data.writeShortInt(this.offsets[i]);
    }
    for (int i = 0; i < this.entryCount; i++) {
      this.index.writeRow(this.data, this.offsets[i], this.rows[i], this.onlyPosition);
    }
    this.written = true;
  }
  
  void freeRecursive()
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.index.getPageStore().free(getPos());
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      this.index.getPage(child).freeRecursive();
    }
  }
  
  private void removeChild(int i)
  {
    readAllRows();
    this.entryCount -= 1;
    if (this.pageStoreInternalCount) {
      updateRowCount(-this.index.getPage(this.childPageIds[i]).getRowCount());
    }
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    if (this.entryCount < 0) {
      DbException.throwInternalError();
    }
    if (this.entryCount > i)
    {
      int startNext = i > 0 ? this.offsets[(i - 1)] : this.index.getPageStore().getPageSize();
      int rowLength = startNext - this.offsets[i];
      add(this.offsets, i, this.entryCount + 1, rowLength);
    }
    this.rows = ((SearchRow[])remove(this.rows, this.entryCount + 1, i));
    this.offsets = remove(this.offsets, this.entryCount + 1, i);
    this.childPageIds = remove(this.childPageIds, this.entryCount + 2, i);
    this.start -= 6;
  }
  
  void nextPage(PageBtreeCursor cursor, int pageId)
  {
    for (int i = 0; i < this.entryCount + 1; i++) {
      if (this.childPageIds[i] == pageId)
      {
        i++;
        break;
      }
    }
    if (i > this.entryCount)
    {
      if (this.parentPageId == 0)
      {
        cursor.setCurrent(null, 0);
        return;
      }
      PageBtreeNode next = (PageBtreeNode)this.index.getPage(this.parentPageId);
      next.nextPage(cursor, getPos());
      return;
    }
    PageBtree page = this.index.getPage(this.childPageIds[i]);
    PageBtreeLeaf leaf = page.getFirstLeaf();
    cursor.setCurrent(leaf, 0);
  }
  
  void previousPage(PageBtreeCursor cursor, int pageId)
  {
    for (int i = this.entryCount; i >= 0; i--) {
      if (this.childPageIds[i] == pageId)
      {
        i--;
        break;
      }
    }
    if (i < 0)
    {
      if (this.parentPageId == 0)
      {
        cursor.setCurrent(null, 0);
        return;
      }
      PageBtreeNode previous = (PageBtreeNode)this.index.getPage(this.parentPageId);
      previous.previousPage(cursor, getPos());
      return;
    }
    PageBtree page = this.index.getPage(this.childPageIds[i]);
    PageBtreeLeaf leaf = page.getLastLeaf();
    cursor.setCurrent(leaf, leaf.entryCount - 1);
  }
  
  public String toString()
  {
    return "page[" + getPos() + "] b-tree node table:" + this.index.getId() + " entries:" + this.entryCount;
  }
  
  public void moveTo(Session session, int newPos)
  {
    PageStore store = this.index.getPageStore();
    store.logUndo(this, this.data);
    PageBtreeNode p2 = create(this.index, newPos, this.parentPageId);
    readAllRows();
    p2.rowCountStored = this.rowCountStored;
    p2.rowCount = this.rowCount;
    p2.childPageIds = this.childPageIds;
    p2.rows = this.rows;
    p2.entryCount = this.entryCount;
    p2.offsets = this.offsets;
    p2.onlyPosition = this.onlyPosition;
    p2.parentPageId = this.parentPageId;
    p2.start = this.start;
    store.update(p2);
    if (this.parentPageId == 0)
    {
      this.index.setRootPageId(session, newPos);
    }
    else
    {
      Page p = store.getPage(this.parentPageId);
      if (!(p instanceof PageBtreeNode)) {
        throw DbException.throwInternalError();
      }
      PageBtreeNode n = (PageBtreeNode)p;
      n.moveChild(getPos(), newPos);
    }
    for (int i = 0; i < this.entryCount + 1; i++)
    {
      int child = this.childPageIds[i];
      PageBtree p = this.index.getPage(child);
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
