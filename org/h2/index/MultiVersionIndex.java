package org.h2.index;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class MultiVersionIndex
  implements Index
{
  private final Index base;
  private final TreeIndex delta;
  private final RegularTable table;
  private final Object sync;
  private final Column firstColumn;
  
  public MultiVersionIndex(Index base, RegularTable table)
  {
    this.base = base;
    this.table = table;
    IndexType deltaIndexType = IndexType.createNonUnique(false);
    if ((base instanceof SpatialIndex)) {
      throw DbException.get(50100, "MVCC & spatial index");
    }
    this.delta = new TreeIndex(table, -1, "DELTA", base.getIndexColumns(), deltaIndexType);
    
    this.delta.setMultiVersion(true);
    this.sync = base.getDatabase();
    this.firstColumn = base.getColumns()[0];
  }
  
  public void add(Session session, Row row)
  {
    synchronized (this.sync)
    {
      this.base.add(session, row);
      if (!removeIfExists(session, row)) {
        if (row.getSessionId() != 0) {
          this.delta.add(session, row);
        }
      }
    }
  }
  
  public void close(Session session)
  {
    synchronized (this.sync)
    {
      this.base.close(session);
    }
  }
  
  public Cursor find(TableFilter filter, SearchRow first, SearchRow last)
  {
    synchronized (this.sync)
    {
      Cursor baseCursor = this.base.find(filter, first, last);
      Cursor deltaCursor = this.delta.find(filter, first, last);
      return new MultiVersionCursor(filter.getSession(), this, baseCursor, deltaCursor, this.sync);
    }
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    synchronized (this.sync)
    {
      Cursor baseCursor = this.base.find(session, first, last);
      Cursor deltaCursor = this.delta.find(session, first, last);
      return new MultiVersionCursor(session, this, baseCursor, deltaCursor, this.sync);
    }
  }
  
  public Cursor findNext(Session session, SearchRow first, SearchRow last)
  {
    throw DbException.throwInternalError();
  }
  
  public boolean canFindNext()
  {
    return false;
  }
  
  public boolean canGetFirstOrLast()
  {
    return (this.base.canGetFirstOrLast()) && (this.delta.canGetFirstOrLast());
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    if (first)
    {
      Cursor cursor = find(session, null, null);
      while (cursor.next())
      {
        SearchRow row = cursor.getSearchRow();
        Value v = row.getValue(this.firstColumn.getColumnId());
        if (v != ValueNull.INSTANCE) {
          return cursor;
        }
      }
      return cursor;
    }
    Cursor baseCursor = this.base.findFirstOrLast(session, false);
    Cursor deltaCursor = this.delta.findFirstOrLast(session, false);
    MultiVersionCursor cursor = new MultiVersionCursor(session, this, baseCursor, deltaCursor, this.sync);
    
    cursor.loadCurrent();
    while (cursor.previous())
    {
      SearchRow row = cursor.getSearchRow();
      if (row == null) {
        break;
      }
      Value v = row.getValue(this.firstColumn.getColumnId());
      if (v != ValueNull.INSTANCE) {
        return cursor;
      }
    }
    return cursor;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return this.base.getCost(session, masks, filter, sortOrder);
  }
  
  public boolean needRebuild()
  {
    return this.base.needRebuild();
  }
  
  public boolean isUncommittedFromOtherSession(Session session, Row row)
  {
    Cursor c = this.delta.find(session, row, row);
    if (c.next())
    {
      Row r = c.get();
      return r.getSessionId() != session.getId();
    }
    return false;
  }
  
  private boolean removeIfExists(Session session, Row row)
  {
    Cursor c = this.delta.find(session, row, row);
    while (c.next())
    {
      Row r = c.get();
      if ((r.getKey() == row.getKey()) && (r.getVersion() == row.getVersion())) {
        if ((r != row) && (this.table.getScanIndex(session).compareRows(r, row) != 0))
        {
          row.setVersion(r.getVersion() + 1);
        }
        else
        {
          this.delta.remove(session, r);
          return true;
        }
      }
    }
    return false;
  }
  
  public void remove(Session session, Row row)
  {
    synchronized (this.sync)
    {
      this.base.remove(session, row);
      if (!removeIfExists(session, row)) {
        this.delta.add(session, row);
      }
    }
  }
  
  public void remove(Session session)
  {
    synchronized (this.sync)
    {
      this.base.remove(session);
    }
  }
  
  public void truncate(Session session)
  {
    synchronized (this.sync)
    {
      this.delta.truncate(session);
      this.base.truncate(session);
    }
  }
  
  public void commit(int operation, Row row)
  {
    synchronized (this.sync)
    {
      removeIfExists(null, row);
    }
  }
  
  public int compareRows(SearchRow rowData, SearchRow compare)
  {
    return this.base.compareRows(rowData, compare);
  }
  
  public int getColumnIndex(Column col)
  {
    return this.base.getColumnIndex(col);
  }
  
  public Column[] getColumns()
  {
    return this.base.getColumns();
  }
  
  public IndexColumn[] getIndexColumns()
  {
    return this.base.getIndexColumns();
  }
  
  public String getCreateSQL()
  {
    return this.base.getCreateSQL();
  }
  
  public String getCreateSQLForCopy(Table forTable, String quotedName)
  {
    return this.base.getCreateSQLForCopy(forTable, quotedName);
  }
  
  public String getDropSQL()
  {
    return this.base.getDropSQL();
  }
  
  public IndexType getIndexType()
  {
    return this.base.getIndexType();
  }
  
  public String getPlanSQL()
  {
    return this.base.getPlanSQL();
  }
  
  public long getRowCount(Session session)
  {
    return this.base.getRowCount(session);
  }
  
  public Table getTable()
  {
    return this.base.getTable();
  }
  
  public int getType()
  {
    return this.base.getType();
  }
  
  public void removeChildrenAndResources(Session session)
  {
    synchronized (this.sync)
    {
      this.table.removeIndex(this);
      remove(session);
    }
  }
  
  public String getSQL()
  {
    return this.base.getSQL();
  }
  
  public Schema getSchema()
  {
    return this.base.getSchema();
  }
  
  public void checkRename()
  {
    this.base.checkRename();
  }
  
  public ArrayList<DbObject> getChildren()
  {
    return this.base.getChildren();
  }
  
  public String getComment()
  {
    return this.base.getComment();
  }
  
  public Database getDatabase()
  {
    return this.base.getDatabase();
  }
  
  public int getId()
  {
    return this.base.getId();
  }
  
  public String getName()
  {
    return this.base.getName();
  }
  
  public boolean isTemporary()
  {
    return this.base.isTemporary();
  }
  
  public void rename(String newName)
  {
    this.base.rename(newName);
  }
  
  public void setComment(String comment)
  {
    this.base.setComment(comment);
  }
  
  public void setTemporary(boolean temporary)
  {
    this.base.setTemporary(temporary);
  }
  
  public long getRowCountApproximation()
  {
    return this.base.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return this.base.getDiskSpaceUsed();
  }
  
  public Index getBaseIndex()
  {
    return this.base;
  }
  
  public Row getRow(Session session, long key)
  {
    return this.base.getRow(session, key);
  }
  
  public boolean isHidden()
  {
    return this.base.isHidden();
  }
  
  public boolean isRowIdIndex()
  {
    return (this.base.isRowIdIndex()) && (this.delta.isRowIdIndex());
  }
  
  public boolean canScan()
  {
    return this.base.canScan();
  }
  
  public void setSortedInsertMode(boolean sortedInsertMode)
  {
    this.base.setSortedInsertMode(sortedInsertMode);
    this.delta.setSortedInsertMode(sortedInsertMode);
  }
}
