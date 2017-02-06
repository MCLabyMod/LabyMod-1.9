package org.h2.index;

import java.util.Arrays;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.result.SearchRow;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;

public class PageBtreeLeaf
  extends PageBtree
{
  private static final int OFFSET_LENGTH = 2;
  private final boolean optimizeUpdate;
  private boolean writtenData;
  
  private PageBtreeLeaf(PageBtreeIndex index, int pageId, Data data)
  {
    super(index, pageId, data);
    this.optimizeUpdate = index.getDatabase().getSettings().optimizeUpdate;
  }
  
  public static Page read(PageBtreeIndex index, Data data, int pageId)
  {
    PageBtreeLeaf p = new PageBtreeLeaf(index, pageId, data);
    p.read();
    return p;
  }
  
  static PageBtreeLeaf create(PageBtreeIndex index, int pageId, int parentPageId)
  {
    PageBtreeLeaf p = new PageBtreeLeaf(index, pageId, index.getPageStore().createData());
    
    index.getPageStore().logUndo(p, null);
    p.rows = SearchRow.EMPTY_ARRAY;
    p.parentPageId = parentPageId;
    p.writeHead();
    p.start = p.data.length();
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
    this.entryCount = this.data.readShortInt();
    this.offsets = new int[this.entryCount];
    this.rows = new SearchRow[this.entryCount];
    for (int i = 0; i < this.entryCount; i++) {
      this.offsets[i] = this.data.readShortInt();
    }
    this.start = this.data.length();
    this.written = true;
    this.writtenData = true;
  }
  
  int addRowTry(SearchRow row)
  {
    int x = addRow(row, true);
    memoryChange();
    return x;
  }
  
  private int addRow(SearchRow row, boolean tryOnly)
  {
    int rowLength = this.index.getRowSize(this.data, row, this.onlyPosition);
    int pageSize = this.index.getPageStore().getPageSize();
    int last = this.entryCount == 0 ? pageSize : this.offsets[(this.entryCount - 1)];
    if (last - rowLength < this.start + 2)
    {
      if ((tryOnly) && (this.entryCount > 1))
      {
        int x = find(row, false, true, true);
        if (this.entryCount < 5) {
          return this.entryCount / 2;
        }
        int third = this.entryCount / 3;
        return x >= 2 * third ? 2 * third : x < third ? third : x;
      }
      readAllRows();
      this.writtenData = false;
      this.onlyPosition = true;
      
      int o = pageSize;
      for (int i = 0; i < this.entryCount; i++)
      {
        o -= this.index.getRowSize(this.data, getRow(i), true);
        this.offsets[i] = o;
      }
      last = this.entryCount == 0 ? pageSize : this.offsets[(this.entryCount - 1)];
      rowLength = this.index.getRowSize(this.data, row, true);
      if ((SysProperties.CHECK) && (last - rowLength < this.start + 2)) {
        throw DbException.throwInternalError();
      }
    }
    this.index.getPageStore().logUndo(this, this.data);
    if (!this.optimizeUpdate) {
      readAllRows();
    }
    this.changeCount = this.index.getPageStore().getChangeCount();
    this.written = false;
    int x;
    int x;
    if (this.entryCount == 0) {
      x = 0;
    } else {
      x = find(row, false, true, true);
    }
    this.start += 2;
    int offset = (x == 0 ? pageSize : this.offsets[(x - 1)]) - rowLength;
    if ((this.optimizeUpdate) && (this.writtenData))
    {
      if (this.entryCount > 0)
      {
        byte[] d = this.data.getBytes();
        int dataStart = this.offsets[(this.entryCount - 1)];
        int dataEnd = offset;
        System.arraycopy(d, dataStart, d, dataStart - rowLength, dataEnd - dataStart + rowLength);
      }
      this.index.writeRow(this.data, offset, row, this.onlyPosition);
    }
    this.offsets = insert(this.offsets, this.entryCount, x, offset);
    add(this.offsets, x + 1, this.entryCount + 1, -rowLength);
    this.rows = ((SearchRow[])insert(this.rows, this.entryCount, x, row));
    this.entryCount += 1;
    this.index.getPageStore().update(this);
    return -1;
  }
  
  private void removeRow(int at)
  {
    if (!this.optimizeUpdate) {
      readAllRows();
    }
    this.index.getPageStore().logUndo(this, this.data);
    this.entryCount -= 1;
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    if (this.entryCount <= 0) {
      DbException.throwInternalError();
    }
    int startNext = at > 0 ? this.offsets[(at - 1)] : this.index.getPageStore().getPageSize();
    int rowLength = startNext - this.offsets[at];
    this.start -= 2;
    if ((this.optimizeUpdate) && 
      (this.writtenData))
    {
      byte[] d = this.data.getBytes();
      int dataStart = this.offsets[this.entryCount];
      System.arraycopy(d, dataStart, d, dataStart + rowLength, this.offsets[at] - dataStart);
      
      Arrays.fill(d, dataStart, dataStart + rowLength, (byte)0);
    }
    this.offsets = remove(this.offsets, this.entryCount + 1, at);
    add(this.offsets, at, this.entryCount, rowLength);
    this.rows = ((SearchRow[])remove(this.rows, this.entryCount + 1, at));
  }
  
  int getEntryCount()
  {
    return this.entryCount;
  }
  
  PageBtree split(int splitPoint)
  {
    int newPageId = this.index.getPageStore().allocatePage();
    PageBtreeLeaf p2 = create(this.index, newPageId, this.parentPageId);
    for (int i = splitPoint; i < this.entryCount;)
    {
      p2.addRow(getRow(splitPoint), false);
      removeRow(splitPoint);
    }
    memoryChange();
    p2.memoryChange();
    return p2;
  }
  
  PageBtreeLeaf getFirstLeaf()
  {
    return this;
  }
  
  PageBtreeLeaf getLastLeaf()
  {
    return this;
  }
  
  SearchRow remove(SearchRow row)
  {
    int at = find(row, false, false, true);
    SearchRow delete = getRow(at);
    if ((this.index.compareRows(row, delete) != 0) || (delete.getKey() != row.getKey())) {
      throw DbException.get(90112, this.index.getSQL() + ": " + row);
    }
    this.index.getPageStore().logUndo(this, this.data);
    if (this.entryCount == 1) {
      return row;
    }
    removeRow(at);
    memoryChange();
    this.index.getPageStore().update(this);
    if (at == this.entryCount) {
      return getRow(at - 1);
    }
    return null;
  }
  
  void freeRecursive()
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.index.getPageStore().free(getPos());
  }
  
  int getRowCount()
  {
    return this.entryCount;
  }
  
  void setRowCountStored(int rowCount) {}
  
  public void write()
  {
    writeData();
    this.index.getPageStore().writePage(getPos(), this.data);
  }
  
  private void writeHead()
  {
    this.data.reset();
    this.data.writeByte((byte)(0x4 | (this.onlyPosition ? 0 : 16)));
    
    this.data.writeShortInt(0);
    this.data.writeInt(this.parentPageId);
    this.data.writeVarInt(this.index.getId());
    this.data.writeShortInt(this.entryCount);
  }
  
  private void writeData()
  {
    if (this.written) {
      return;
    }
    if (!this.optimizeUpdate) {
      readAllRows();
    }
    writeHead();
    for (int i = 0; i < this.entryCount; i++) {
      this.data.writeShortInt(this.offsets[i]);
    }
    if ((!this.writtenData) || (!this.optimizeUpdate))
    {
      for (int i = 0; i < this.entryCount; i++) {
        this.index.writeRow(this.data, this.offsets[i], this.rows[i], this.onlyPosition);
      }
      this.writtenData = true;
    }
    this.written = true;
    memoryChange();
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
    cursor.setCurrent(this, i);
  }
  
  void last(PageBtreeCursor cursor)
  {
    cursor.setCurrent(this, this.entryCount - 1);
  }
  
  void remapChildren() {}
  
  void nextPage(PageBtreeCursor cursor)
  {
    if (this.parentPageId == 0)
    {
      cursor.setCurrent(null, 0);
      return;
    }
    PageBtreeNode next = (PageBtreeNode)this.index.getPage(this.parentPageId);
    next.nextPage(cursor, getPos());
  }
  
  void previousPage(PageBtreeCursor cursor)
  {
    if (this.parentPageId == 0)
    {
      cursor.setCurrent(null, 0);
      return;
    }
    PageBtreeNode next = (PageBtreeNode)this.index.getPage(this.parentPageId);
    next.previousPage(cursor, getPos());
  }
  
  public String toString()
  {
    return "page[" + getPos() + "] b-tree leaf table:" + this.index.getId() + " entries:" + this.entryCount;
  }
  
  public void moveTo(Session session, int newPos)
  {
    PageStore store = this.index.getPageStore();
    readAllRows();
    PageBtreeLeaf p2 = create(this.index, newPos, this.parentPageId);
    store.logUndo(this, this.data);
    store.logUndo(p2, null);
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
      PageBtreeNode p = (PageBtreeNode)store.getPage(this.parentPageId);
      p.moveChild(getPos(), newPos);
    }
    store.free(getPos());
  }
  
  protected void memoryChange()
  {
    if (!PageBtreeIndex.isMemoryChangeRequired()) {
      return;
    }
    int memory = 184 + this.index.getPageStore().getPageSize();
    if (this.rows != null)
    {
      memory += getEntryCount() * 12;
      for (int i = 0; i < this.entryCount; i++)
      {
        SearchRow r = this.rows[i];
        if (r != null) {
          memory += r.getMemory();
        }
      }
    }
    this.index.memoryChange(memory >> 2);
  }
}
