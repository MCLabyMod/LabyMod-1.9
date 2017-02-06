package org.h2.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.store.LobStorageInterface;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;

public class ScanIndex
  extends BaseIndex
{
  private long firstFree = -1L;
  private ArrayList<Row> rows = New.arrayList();
  private final RegularTable tableData;
  private int rowCountDiff;
  private final HashMap<Integer, Integer> sessionRowCount;
  private HashSet<Row> delta;
  private long rowCount;
  
  public ScanIndex(RegularTable table, int id, IndexColumn[] columns, IndexType indexType)
  {
    initBaseIndex(table, id, table.getName() + "_DATA", columns, indexType);
    if (this.database.isMultiVersion()) {
      this.sessionRowCount = New.hashMap();
    } else {
      this.sessionRowCount = null;
    }
    this.tableData = table;
  }
  
  public void remove(Session session)
  {
    truncate(session);
  }
  
  public void truncate(Session session)
  {
    this.rows = New.arrayList();
    this.firstFree = -1L;
    if ((this.tableData.getContainsLargeObject()) && (this.tableData.isPersistData())) {
      this.database.getLobStorage().removeAllForTable(this.table.getId());
    }
    this.tableData.setRowCount(0L);
    this.rowCount = 0L;
    this.rowCountDiff = 0;
    if (this.database.isMultiVersion()) {
      this.sessionRowCount.clear();
    }
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public void close(Session session) {}
  
  public Row getRow(Session session, long key)
  {
    return (Row)this.rows.get((int)key);
  }
  
  public void add(Session session, Row row)
  {
    if (this.firstFree == -1L)
    {
      int key = this.rows.size();
      row.setKey(key);
      this.rows.add(row);
    }
    else
    {
      long key = this.firstFree;
      Row free = (Row)this.rows.get((int)key);
      this.firstFree = free.getKey();
      row.setKey(key);
      this.rows.set((int)key, row);
    }
    row.setDeleted(false);
    if (this.database.isMultiVersion())
    {
      if (this.delta == null) {
        this.delta = New.hashSet();
      }
      boolean wasDeleted = this.delta.remove(row);
      if (!wasDeleted) {
        this.delta.add(row);
      }
      incrementRowCount(session.getId(), 1);
    }
    this.rowCount += 1L;
  }
  
  public void commit(int operation, Row row)
  {
    if (this.database.isMultiVersion())
    {
      if (this.delta != null) {
        this.delta.remove(row);
      }
      incrementRowCount(row.getSessionId(), operation == 1 ? 1 : -1);
    }
  }
  
  private void incrementRowCount(int sessionId, int count)
  {
    if (this.database.isMultiVersion())
    {
      Integer id = Integer.valueOf(sessionId);
      Integer c = (Integer)this.sessionRowCount.get(id);
      int current = c == null ? 0 : c.intValue();
      this.sessionRowCount.put(id, Integer.valueOf(current + count));
      this.rowCountDiff += count;
    }
  }
  
  public void remove(Session session, Row row)
  {
    if ((!this.database.isMultiVersion()) && (this.rowCount == 1L))
    {
      this.rows = New.arrayList();
      this.firstFree = -1L;
    }
    else
    {
      Row free = new Row(null, 1);
      free.setKey(this.firstFree);
      long key = row.getKey();
      if (this.rows.size() <= key) {
        throw DbException.get(90112, this.rows.size() + ": " + key);
      }
      this.rows.set((int)key, free);
      this.firstFree = key;
    }
    if (this.database.isMultiVersion())
    {
      row.setDeleted(true);
      if (this.delta == null) {
        this.delta = New.hashSet();
      }
      boolean wasAdded = this.delta.remove(row);
      if (!wasAdded) {
        this.delta.add(row);
      }
      incrementRowCount(session.getId(), -1);
    }
    this.rowCount -= 1L;
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    return new ScanCursor(session, this, this.database.isMultiVersion());
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return this.tableData.getRowCountApproximation() + 1000L;
  }
  
  public long getRowCount(Session session)
  {
    if (this.database.isMultiVersion())
    {
      Integer i = (Integer)this.sessionRowCount.get(Integer.valueOf(session.getId()));
      long count = i == null ? 0L : i.intValue();
      count += this.rowCount;
      count -= this.rowCountDiff;
      return count;
    }
    return this.rowCount;
  }
  
  Row getNextRow(Row row)
  {
    long key;
    long key;
    if (row == null) {
      key = -1L;
    } else {
      key = row.getKey();
    }
    do
    {
      key += 1L;
      if (key >= this.rows.size()) {
        return null;
      }
      row = (Row)this.rows.get((int)key);
    } while (row.isEmpty());
    return row;
  }
  
  public int getColumnIndex(Column col)
  {
    return -1;
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("SCAN");
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.getUnsupportedException("SCAN");
  }
  
  Iterator<Row> getDelta()
  {
    if (this.delta == null)
    {
      List<Row> e = Collections.emptyList();
      return e.iterator();
    }
    return this.delta.iterator();
  }
  
  public long getRowCountApproximation()
  {
    return this.rowCount;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public String getPlanSQL()
  {
    return this.table.getSQL() + ".tableScan";
  }
}
