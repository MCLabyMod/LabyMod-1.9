package org.h2.index;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.store.Data;
import org.h2.store.LobStorageInterface;
import org.h2.store.Page;
import org.h2.store.PageStore;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.MathUtils;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class PageBtreeIndex
  extends PageIndex
{
  private static int memoryChangeRequired;
  private final PageStore store;
  private final RegularTable tableData;
  private final boolean needRebuild;
  private long rowCount;
  private int memoryPerPage;
  private int memoryCount;
  
  public PageBtreeIndex(RegularTable table, int id, String indexName, IndexColumn[] columns, IndexType indexType, boolean create, Session session)
  {
    initBaseIndex(table, id, indexName, columns, indexType);
    if ((!this.database.isStarting()) && (create)) {
      checkIndexColumnTypes(columns);
    }
    this.tableData = table;
    if ((!this.database.isPersistent()) || (id < 0)) {
      throw DbException.throwInternalError("" + indexName);
    }
    this.store = this.database.getPageStore();
    this.store.addIndex(this);
    if (create)
    {
      this.rootPageId = this.store.allocatePage();
      
      this.store.addMeta(this, session);
      PageBtreeLeaf root = PageBtreeLeaf.create(this, this.rootPageId, 0);
      this.store.logUndo(root, null);
      this.store.update(root);
    }
    else
    {
      this.rootPageId = this.store.getRootPageId(id);
      PageBtree root = getPage(this.rootPageId);
      this.rowCount = root.getRowCount();
    }
    this.needRebuild = ((create) || ((this.rowCount == 0L) && (this.store.isRecoveryRunning())));
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("opened {0} rows: {1}", new Object[] { getName(), Long.valueOf(this.rowCount) });
    }
    this.memoryPerPage = (184 + this.store.getPageSize() >> 2);
  }
  
  public void add(Session session, Row row)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} add {1}", new Object[] { getName(), row });
    }
    SearchRow newRow = getSearchRow(row);
    try
    {
      addRow(newRow);
    }
    finally
    {
      this.store.incrementChangeCount();
    }
  }
  
  private void addRow(SearchRow newRow)
  {
    for (;;)
    {
      PageBtree root = getPage(this.rootPageId);
      int splitPoint = root.addRowTry(newRow);
      if (splitPoint == -1) {
        break;
      }
      if (this.trace.isDebugEnabled()) {
        this.trace.debug("split {0}", new Object[] { Integer.valueOf(splitPoint) });
      }
      SearchRow pivot = root.getRow(splitPoint - 1);
      this.store.logUndo(root, root.data);
      PageBtree page1 = root;
      PageBtree page2 = root.split(splitPoint);
      this.store.logUndo(page2, null);
      int id = this.store.allocatePage();
      page1.setPageId(id);
      page1.setParentPageId(this.rootPageId);
      page2.setParentPageId(this.rootPageId);
      PageBtreeNode newRoot = PageBtreeNode.create(this, this.rootPageId, 0);
      
      this.store.logUndo(newRoot, null);
      newRoot.init(page1, pivot, page2);
      this.store.update(page1);
      this.store.update(page2);
      this.store.update(newRoot);
      root = newRoot;
    }
    invalidateRowCount();
    this.rowCount += 1L;
  }
  
  private SearchRow getSearchRow(Row row)
  {
    SearchRow r = this.table.getTemplateSimpleRow(this.columns.length == 1);
    r.setKeyAndVersion(row);
    for (Column c : this.columns)
    {
      int idx = c.getColumnId();
      r.setValue(idx, row.getValue(idx));
    }
    return r;
  }
  
  PageBtree getPage(int id)
  {
    Page p = this.store.getPage(id);
    if (p == null)
    {
      PageBtreeLeaf empty = PageBtreeLeaf.create(this, id, 0);
      
      this.store.logUndo(empty, null);
      this.store.update(empty);
      return empty;
    }
    if (!(p instanceof PageBtree)) {
      throw DbException.get(90030, "" + p);
    }
    return (PageBtree)p;
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public Cursor findNext(Session session, SearchRow first, SearchRow last)
  {
    return find(session, first, true, last);
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    return find(session, first, false, last);
  }
  
  private Cursor find(Session session, SearchRow first, boolean bigger, SearchRow last)
  {
    if ((SysProperties.CHECK) && (this.store == null)) {
      throw DbException.get(90007);
    }
    PageBtree root = getPage(this.rootPageId);
    PageBtreeCursor cursor = new PageBtreeCursor(session, this, last);
    root.find(cursor, first, bigger);
    return cursor;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    if (first)
    {
      Cursor cursor = find(session, null, false, null);
      while (cursor.next())
      {
        SearchRow row = cursor.getSearchRow();
        Value v = row.getValue(this.columnIds[0]);
        if (v != ValueNull.INSTANCE) {
          return cursor;
        }
      }
      return cursor;
    }
    PageBtree root = getPage(this.rootPageId);
    PageBtreeCursor cursor = new PageBtreeCursor(session, this, null);
    root.last(cursor);
    cursor.previous();
    do
    {
      SearchRow row = cursor.getSearchRow();
      if (row == null) {
        break;
      }
      Value v = row.getValue(this.columnIds[0]);
      if (v != ValueNull.INSTANCE) {
        return cursor;
      }
    } while (cursor.previous());
    return cursor;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return 10L * getCostRangeIndex(masks, this.tableData.getRowCount(session), filter, sortOrder);
  }
  
  public boolean needRebuild()
  {
    return this.needRebuild;
  }
  
  public void remove(Session session, Row row)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} remove {1}", new Object[] { getName(), row });
    }
    if (this.rowCount == 1L) {
      removeAllRows();
    } else {
      try
      {
        PageBtree root = getPage(this.rootPageId);
        root.remove(row);
        invalidateRowCount();
        this.rowCount -= 1L;
      }
      finally
      {
        this.store.incrementChangeCount();
      }
    }
  }
  
  public void remove(Session session)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("remove");
    }
    removeAllRows();
    this.store.free(this.rootPageId);
    this.store.removeMeta(this, session);
  }
  
  public void truncate(Session session)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("truncate");
    }
    removeAllRows();
    if (this.tableData.getContainsLargeObject()) {
      this.database.getLobStorage().removeAllForTable(this.table.getId());
    }
    this.tableData.setRowCount(0L);
  }
  
  private void removeAllRows()
  {
    try
    {
      PageBtree root = getPage(this.rootPageId);
      root.freeRecursive();
      root = PageBtreeLeaf.create(this, this.rootPageId, 0);
      this.store.removeFromCache(this.rootPageId);
      this.store.update(root);
      this.rowCount = 0L;
    }
    finally
    {
      this.store.incrementChangeCount();
    }
  }
  
  public void checkRename() {}
  
  public Row getRow(Session session, long key)
  {
    return this.tableData.getRow(session, key);
  }
  
  PageStore getPageStore()
  {
    return this.store;
  }
  
  public long getRowCountApproximation()
  {
    return this.tableData.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return this.tableData.getDiskSpaceUsed();
  }
  
  public long getRowCount(Session session)
  {
    return this.rowCount;
  }
  
  public void close(Session session)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("close");
    }
    try
    {
      writeRowCount();
    }
    finally
    {
      this.store.incrementChangeCount();
    }
  }
  
  SearchRow readRow(Data data, int offset, boolean onlyPosition, boolean needData)
  {
    synchronized (data)
    {
      data.setPos(offset);
      long key = data.readVarLong();
      if (onlyPosition)
      {
        if (needData) {
          return this.tableData.getRow(null, key);
        }
        SearchRow row = this.table.getTemplateSimpleRow(true);
        row.setKey(key);
        return row;
      }
      SearchRow row = this.table.getTemplateSimpleRow(this.columns.length == 1);
      row.setKey(key);
      for (Column col : this.columns)
      {
        int idx = col.getColumnId();
        row.setValue(idx, data.readValue());
      }
      return row;
    }
  }
  
  SearchRow readRow(long key)
  {
    return this.tableData.getRow(null, key);
  }
  
  void writeRow(Data data, int offset, SearchRow row, boolean onlyPosition)
  {
    data.setPos(offset);
    data.writeVarLong(row.getKey());
    if (!onlyPosition) {
      for (Column col : this.columns)
      {
        int idx = col.getColumnId();
        data.writeValue(row.getValue(idx));
      }
    }
  }
  
  int getRowSize(Data dummy, SearchRow row, boolean onlyPosition)
  {
    int rowsize = Data.getVarLongLen(row.getKey());
    if (!onlyPosition) {
      for (Column col : this.columns)
      {
        Value v = row.getValue(col.getColumnId());
        rowsize += dummy.getValueLen(v);
      }
    }
    return rowsize;
  }
  
  public boolean canFindNext()
  {
    return true;
  }
  
  void setRootPageId(Session session, int newPos)
  {
    this.store.removeMeta(this, session);
    this.rootPageId = newPos;
    this.store.addMeta(this, session);
    this.store.addIndex(this);
  }
  
  private void invalidateRowCount()
  {
    PageBtree root = getPage(this.rootPageId);
    root.setRowCountStored(-1);
  }
  
  public void writeRowCount()
  {
    if ((SysProperties.MODIFY_ON_WRITE) && (this.rootPageId == 0)) {
      return;
    }
    PageBtree root = getPage(this.rootPageId);
    root.setRowCountStored(MathUtils.convertLongToInt(this.rowCount));
  }
  
  boolean hasData(SearchRow row)
  {
    return row.getValue(this.columns[0].getColumnId()) != null;
  }
  
  int getMemoryPerPage()
  {
    return this.memoryPerPage;
  }
  
  void memoryChange(int x)
  {
    if (this.memoryCount < 64) {
      this.memoryPerPage += (x - this.memoryPerPage) / ++this.memoryCount;
    } else {
      this.memoryPerPage += (x > this.memoryPerPage ? 1 : -1) + (x - this.memoryPerPage) / 64;
    }
  }
  
  static boolean isMemoryChangeRequired()
  {
    if (memoryChangeRequired-- <= 0)
    {
      memoryChangeRequired = 10;
      return true;
    }
    return false;
  }
}
