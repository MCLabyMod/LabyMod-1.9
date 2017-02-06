package org.h2.index;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.store.Data;
import org.h2.store.Page;
import org.h2.store.PageStore;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.value.Value;

public class PageDataLeaf
  extends PageData
{
  private final boolean optimizeUpdate;
  private int[] offsets;
  private Row[] rows;
  private SoftReference<Row> rowRef;
  private int firstOverflowPageId;
  private int start;
  private int overflowRowSize;
  private int columnCount;
  private int memoryData;
  private boolean writtenData;
  
  private PageDataLeaf(PageDataIndex index, int pageId, Data data)
  {
    super(index, pageId, data);
    this.optimizeUpdate = index.getDatabase().getSettings().optimizeUpdate;
  }
  
  static PageDataLeaf create(PageDataIndex index, int pageId, int parentPageId)
  {
    PageDataLeaf p = new PageDataLeaf(index, pageId, index.getPageStore().createData());
    
    index.getPageStore().logUndo(p, null);
    p.rows = Row.EMPTY_ARRAY;
    p.parentPageId = parentPageId;
    p.columnCount = index.getTable().getColumns().length;
    p.writeHead();
    p.start = p.data.length();
    return p;
  }
  
  public static Page read(PageDataIndex index, Data data, int pageId)
  {
    PageDataLeaf p = new PageDataLeaf(index, pageId, data);
    p.read();
    return p;
  }
  
  private void read()
  {
    this.data.reset();
    int type = this.data.readByte();
    this.data.readShortInt();
    this.parentPageId = this.data.readInt();
    int tableId = this.data.readVarInt();
    if (tableId != this.index.getId()) {
      throw DbException.get(90030, "page:" + getPos() + " expected table:" + this.index.getId() + " got:" + tableId + " type:" + type);
    }
    this.columnCount = this.data.readVarInt();
    this.entryCount = this.data.readShortInt();
    this.offsets = new int[this.entryCount];
    this.keys = new long[this.entryCount];
    this.rows = new Row[this.entryCount];
    if (type == 1)
    {
      if (this.entryCount != 1) {
        DbException.throwInternalError("entries: " + this.entryCount);
      }
      this.firstOverflowPageId = this.data.readInt();
    }
    for (int i = 0; i < this.entryCount; i++)
    {
      this.keys[i] = this.data.readVarLong();
      this.offsets[i] = this.data.readShortInt();
    }
    this.start = this.data.length();
    this.written = true;
    this.writtenData = true;
  }
  
  private int getRowLength(Row row)
  {
    int size = 0;
    for (int i = 0; i < this.columnCount; i++) {
      size += this.data.getValueLen(row.getValue(i));
    }
    return size;
  }
  
  private int findInsertionPoint(long key)
  {
    int x = find(key);
    if ((x < this.entryCount) && (this.keys[x] == key)) {
      throw this.index.getDuplicateKeyException("" + key);
    }
    return x;
  }
  
  int addRowTry(Row row)
  {
    this.index.getPageStore().logUndo(this, this.data);
    int rowLength = getRowLength(row);
    int pageSize = this.index.getPageStore().getPageSize();
    int last = this.entryCount == 0 ? pageSize : this.offsets[(this.entryCount - 1)];
    int keyOffsetPairLen = 2 + Data.getVarLongLen(row.getKey());
    if ((this.entryCount > 0) && (last - rowLength < this.start + keyOffsetPairLen))
    {
      int x = findInsertionPoint(row.getKey());
      if (this.entryCount > 1)
      {
        if (this.entryCount < 5) {
          return this.entryCount / 2;
        }
        if (this.index.isSortedInsertMode()) {
          return x > this.entryCount - 1 ? this.entryCount - 1 : x < 2 ? 1 : x;
        }
        int third = this.entryCount / 3;
        return x >= 2 * third ? 2 * third : x < third ? third : x;
      }
      return x;
    }
    this.index.getPageStore().logUndo(this, this.data);
    int x;
    int x;
    if (this.entryCount == 0)
    {
      x = 0;
    }
    else
    {
      if (!this.optimizeUpdate) {
        readAllRows();
      }
      x = findInsertionPoint(row.getKey());
    }
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    last = x == 0 ? pageSize : this.offsets[(x - 1)];
    int offset = last - rowLength;
    this.start += keyOffsetPairLen;
    this.offsets = insert(this.offsets, this.entryCount, x, offset);
    add(this.offsets, x + 1, this.entryCount + 1, -rowLength);
    this.keys = insert(this.keys, this.entryCount, x, row.getKey());
    this.rows = ((Row[])insert(this.rows, this.entryCount, x, row));
    this.entryCount += 1;
    this.index.getPageStore().update(this);
    if ((this.optimizeUpdate) && 
      (this.writtenData) && (offset >= this.start))
    {
      byte[] d = this.data.getBytes();
      int dataStart = this.offsets[(this.entryCount - 1)] + rowLength;
      int dataEnd = this.offsets[x];
      System.arraycopy(d, dataStart, d, dataStart - rowLength, dataEnd - dataStart + rowLength);
      
      this.data.setPos(dataEnd);
      for (int j = 0; j < this.columnCount; j++) {
        this.data.writeValue(row.getValue(j));
      }
    }
    if (offset < this.start)
    {
      this.writtenData = false;
      if (this.entryCount > 1) {
        DbException.throwInternalError();
      }
      this.start += 4;
      int remaining = rowLength - (pageSize - this.start);
      
      offset = this.start;
      this.offsets[x] = offset;
      int previous = getPos();
      int dataOffset = pageSize;
      int page = this.index.getPageStore().allocatePage();
      this.firstOverflowPageId = page;
      this.overflowRowSize = (pageSize + rowLength);
      writeData();
      
      Row r = this.rows[0];
      this.rowRef = new SoftReference(r);
      this.rows[0] = null;
      Data all = this.index.getPageStore().createData();
      all.checkCapacity(this.data.length());
      all.write(this.data.getBytes(), 0, this.data.length());
      this.data.truncate(this.index.getPageStore().getPageSize());
      do
      {
        int next;
        int type;
        int size;
        int next;
        if (remaining <= pageSize - 9)
        {
          int type = 19;
          int size = remaining;
          next = 0;
        }
        else
        {
          type = 3;
          size = pageSize - 11;
          next = this.index.getPageStore().allocatePage();
        }
        PageDataOverflow overflow = PageDataOverflow.create(this.index.getPageStore(), page, type, previous, next, all, dataOffset, size);
        
        this.index.getPageStore().update(overflow);
        dataOffset += size;
        remaining -= size;
        previous = page;
        page = next;
      } while (remaining > 0);
    }
    if (this.rowRef == null) {
      memoryChange(true, row);
    } else {
      memoryChange(true, null);
    }
    return -1;
  }
  
  private void removeRow(int i)
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.written = false;
    this.changeCount = this.index.getPageStore().getChangeCount();
    if (!this.optimizeUpdate) {
      readAllRows();
    }
    Row r = getRowAt(i);
    if (r != null) {
      memoryChange(false, r);
    }
    this.entryCount -= 1;
    if (this.entryCount < 0) {
      DbException.throwInternalError();
    }
    if (this.firstOverflowPageId != 0)
    {
      this.start -= 4;
      freeOverflow();
      this.firstOverflowPageId = 0;
      this.overflowRowSize = 0;
      this.rowRef = null;
    }
    int keyOffsetPairLen = 2 + Data.getVarLongLen(this.keys[i]);
    int startNext = i > 0 ? this.offsets[(i - 1)] : this.index.getPageStore().getPageSize();
    int rowLength = startNext - this.offsets[i];
    if (this.optimizeUpdate)
    {
      if (this.writtenData)
      {
        byte[] d = this.data.getBytes();
        int dataStart = this.offsets[this.entryCount];
        System.arraycopy(d, dataStart, d, dataStart + rowLength, this.offsets[i] - dataStart);
        
        Arrays.fill(d, dataStart, dataStart + rowLength, (byte)0);
      }
    }
    else
    {
      int clearStart = this.offsets[this.entryCount];
      Arrays.fill(this.data.getBytes(), clearStart, clearStart + rowLength, (byte)0);
    }
    this.start -= keyOffsetPairLen;
    this.offsets = remove(this.offsets, this.entryCount + 1, i);
    add(this.offsets, i, this.entryCount, rowLength);
    this.keys = remove(this.keys, this.entryCount + 1, i);
    this.rows = ((Row[])remove(this.rows, this.entryCount + 1, i));
  }
  
  Cursor find(Session session, long minKey, long maxKey, boolean multiVersion)
  {
    int x = find(minKey);
    return new PageDataCursor(session, this, x, maxKey, multiVersion);
  }
  
  Row getRowAt(int at)
  {
    Row r = this.rows[at];
    if (r == null)
    {
      if (this.firstOverflowPageId == 0)
      {
        r = readRow(this.data, this.offsets[at], this.columnCount);
      }
      else
      {
        if (this.rowRef != null)
        {
          r = (Row)this.rowRef.get();
          if (r != null) {
            return r;
          }
        }
        PageStore store = this.index.getPageStore();
        Data buff = store.createData();
        int pageSize = store.getPageSize();
        int offset = this.offsets[at];
        buff.write(this.data.getBytes(), offset, pageSize - offset);
        int next = this.firstOverflowPageId;
        do
        {
          PageDataOverflow page = this.index.getPageOverflow(next);
          next = page.readInto(buff);
        } while (next != 0);
        this.overflowRowSize = (pageSize + buff.length());
        r = readRow(buff, 0, this.columnCount);
      }
      r.setKey(this.keys[at]);
      if (this.firstOverflowPageId != 0)
      {
        this.rowRef = new SoftReference(r);
      }
      else
      {
        this.rows[at] = r;
        memoryChange(true, r);
      }
    }
    return r;
  }
  
  int getEntryCount()
  {
    return this.entryCount;
  }
  
  PageData split(int splitPoint)
  {
    int newPageId = this.index.getPageStore().allocatePage();
    PageDataLeaf p2 = create(this.index, newPageId, this.parentPageId);
    for (int i = splitPoint; i < this.entryCount;)
    {
      int split = p2.addRowTry(getRowAt(splitPoint));
      if (split != -1) {
        DbException.throwInternalError("split " + split);
      }
      removeRow(splitPoint);
    }
    return p2;
  }
  
  long getLastKey()
  {
    if (this.entryCount == 0) {
      return 0L;
    }
    return getRowAt(this.entryCount - 1).getKey();
  }
  
  PageDataLeaf getNextPage()
  {
    if (this.parentPageId == 0) {
      return null;
    }
    PageDataNode next = (PageDataNode)this.index.getPage(this.parentPageId, -1);
    return next.getNextPage(this.keys[(this.entryCount - 1)]);
  }
  
  PageDataLeaf getFirstLeaf()
  {
    return this;
  }
  
  protected void remapChildren(int old)
  {
    if (this.firstOverflowPageId == 0) {
      return;
    }
    PageDataOverflow overflow = this.index.getPageOverflow(this.firstOverflowPageId);
    overflow.setParentPageId(getPos());
    this.index.getPageStore().update(overflow);
  }
  
  boolean remove(long key)
  {
    int i = find(key);
    if ((this.keys == null) || (this.keys[i] != key)) {
      throw DbException.get(90112, this.index.getSQL() + ": " + key + " " + (this.keys == null ? -1L : this.keys[i]));
    }
    this.index.getPageStore().logUndo(this, this.data);
    if (this.entryCount == 1)
    {
      freeRecursive();
      return true;
    }
    removeRow(i);
    this.index.getPageStore().update(this);
    return false;
  }
  
  void freeRecursive()
  {
    this.index.getPageStore().logUndo(this, this.data);
    this.index.getPageStore().free(getPos());
    freeOverflow();
  }
  
  private void freeOverflow()
  {
    if (this.firstOverflowPageId != 0)
    {
      int next = this.firstOverflowPageId;
      do
      {
        PageDataOverflow page = this.index.getPageOverflow(next);
        page.free();
        next = page.getNextOverflow();
      } while (next != 0);
    }
  }
  
  Row getRowWithKey(long key)
  {
    int at = find(key);
    return getRowAt(at);
  }
  
  int getRowCount()
  {
    return this.entryCount;
  }
  
  void setRowCountStored(int rowCount) {}
  
  long getDiskSpaceUsed()
  {
    return this.index.getPageStore().getPageSize();
  }
  
  public void write()
  {
    writeData();
    this.index.getPageStore().writePage(getPos(), this.data);
    this.data.truncate(this.index.getPageStore().getPageSize());
  }
  
  private void readAllRows()
  {
    for (int i = 0; i < this.entryCount; i++) {
      getRowAt(i);
    }
  }
  
  private void writeHead()
  {
    this.data.reset();
    int type;
    int type;
    if (this.firstOverflowPageId == 0) {
      type = 17;
    } else {
      type = 1;
    }
    this.data.writeByte((byte)type);
    this.data.writeShortInt(0);
    if ((SysProperties.CHECK2) && 
      (this.data.length() != 3)) {
      DbException.throwInternalError();
    }
    this.data.writeInt(this.parentPageId);
    this.data.writeVarInt(this.index.getId());
    this.data.writeVarInt(this.columnCount);
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
    if (this.firstOverflowPageId != 0)
    {
      this.data.writeInt(this.firstOverflowPageId);
      this.data.checkCapacity(this.overflowRowSize);
    }
    for (int i = 0; i < this.entryCount; i++)
    {
      this.data.writeVarLong(this.keys[i]);
      this.data.writeShortInt(this.offsets[i]);
    }
    if ((!this.writtenData) || (!this.optimizeUpdate))
    {
      for (int i = 0; i < this.entryCount; i++)
      {
        this.data.setPos(this.offsets[i]);
        Row r = getRowAt(i);
        for (int j = 0; j < this.columnCount; j++) {
          this.data.writeValue(r.getValue(j));
        }
      }
      this.writtenData = true;
    }
    this.written = true;
  }
  
  public String toString()
  {
    return "page[" + getPos() + "] data leaf table:" + this.index.getId() + " " + this.index.getTable().getName() + " entries:" + this.entryCount + " parent:" + this.parentPageId + (this.firstOverflowPageId == 0 ? "" : new StringBuilder().append(" overflow:").append(this.firstOverflowPageId).toString()) + " keys:" + Arrays.toString(this.keys) + " offsets:" + Arrays.toString(this.offsets);
  }
  
  public void moveTo(Session session, int newPos)
  {
    PageStore store = this.index.getPageStore();
    if (this.parentPageId != 0) {
      store.getPage(this.parentPageId);
    }
    store.logUndo(this, this.data);
    PageDataLeaf p2 = create(this.index, newPos, this.parentPageId);
    readAllRows();
    p2.keys = this.keys;
    p2.overflowRowSize = this.overflowRowSize;
    p2.firstOverflowPageId = this.firstOverflowPageId;
    p2.rowRef = this.rowRef;
    p2.rows = this.rows;
    if (this.firstOverflowPageId != 0) {
      p2.rows[0] = getRowAt(0);
    }
    p2.entryCount = this.entryCount;
    p2.offsets = this.offsets;
    p2.start = this.start;
    p2.remapChildren(getPos());
    p2.writeData();
    p2.data.truncate(this.index.getPageStore().getPageSize());
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
    store.free(getPos());
  }
  
  void setOverflow(int old, int overflow)
  {
    if ((SysProperties.CHECK) && (old != this.firstOverflowPageId)) {
      DbException.throwInternalError("move " + this + " " + this.firstOverflowPageId);
    }
    this.index.getPageStore().logUndo(this, this.data);
    this.firstOverflowPageId = overflow;
    if (this.written)
    {
      this.changeCount = this.index.getPageStore().getChangeCount();
      writeHead();
      this.data.writeInt(this.firstOverflowPageId);
    }
    this.index.getPageStore().update(this);
  }
  
  private void memoryChange(boolean add, Row r)
  {
    int diff = r == null ? 0 : 20 + r.getMemory();
    this.memoryData += (add ? diff : -diff);
    this.index.memoryChange(240 + this.memoryData + this.index.getPageStore().getPageSize() >> 2);
  }
  
  public boolean isStream()
  {
    return this.firstOverflowPageId > 0;
  }
  
  private static Row readRow(Data data, int pos, int columnCount)
  {
    Value[] values = new Value[columnCount];
    synchronized (data)
    {
      data.setPos(pos);
      for (int i = 0; i < columnCount; i++) {
        values[i] = data.readValue();
      }
    }
    return RegularTable.createRow(values);
  }
}
