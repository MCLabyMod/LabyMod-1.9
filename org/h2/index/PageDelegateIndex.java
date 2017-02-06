package org.h2.index;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.store.PageStore;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.TableFilter;

public class PageDelegateIndex
  extends PageIndex
{
  private final PageDataIndex mainIndex;
  
  public PageDelegateIndex(RegularTable table, int id, String name, IndexType indexType, PageDataIndex mainIndex, boolean create, Session session)
  {
    IndexColumn[] cols = IndexColumn.wrap(new Column[] { table.getColumn(mainIndex.getMainIndexColumn()) });
    
    initBaseIndex(table, id, name, cols, indexType);
    this.mainIndex = mainIndex;
    if ((!this.database.isPersistent()) || (id < 0)) {
      throw DbException.throwInternalError("" + name);
    }
    PageStore store = this.database.getPageStore();
    store.addIndex(this);
    if (create) {
      store.addMeta(this, session);
    }
  }
  
  public void add(Session session, Row row) {}
  
  public boolean canFindNext()
  {
    return false;
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public void close(Session session) {}
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    long min = this.mainIndex.getKey(first, Long.MIN_VALUE, Long.MIN_VALUE);
    
    long max = this.mainIndex.getKey(last, Long.MAX_VALUE, Long.MIN_VALUE);
    return this.mainIndex.find(session, min, max, false);
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    Cursor cursor;
    Cursor cursor;
    if (first)
    {
      cursor = this.mainIndex.find(session, Long.MIN_VALUE, Long.MAX_VALUE, false);
    }
    else
    {
      long x = this.mainIndex.getLastKey();
      cursor = this.mainIndex.find(session, x, x, false);
    }
    cursor.next();
    return cursor;
  }
  
  public Cursor findNext(Session session, SearchRow higherThan, SearchRow last)
  {
    throw DbException.throwInternalError();
  }
  
  public int getColumnIndex(Column col)
  {
    if (col.getColumnId() == this.mainIndex.getMainIndexColumn()) {
      return 0;
    }
    return -1;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return 10L * getCostRangeIndex(masks, this.mainIndex.getRowCount(session), filter, sortOrder);
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public void remove(Session session, Row row) {}
  
  public void remove(Session session)
  {
    this.mainIndex.setMainIndexColumn(-1);
    session.getDatabase().getPageStore().removeMeta(this, session);
  }
  
  public void truncate(Session session) {}
  
  public void checkRename() {}
  
  public long getRowCount(Session session)
  {
    return this.mainIndex.getRowCount(session);
  }
  
  public long getRowCountApproximation()
  {
    return this.mainIndex.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return this.mainIndex.getDiskSpaceUsed();
  }
  
  public void writeRowCount() {}
}
