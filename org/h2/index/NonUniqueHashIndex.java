package org.h2.index;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.util.ValueHashMap;
import org.h2.value.Value;

public class NonUniqueHashIndex
  extends BaseIndex
{
  private final int indexColumn;
  private ValueHashMap<ArrayList<Long>> rows;
  private final RegularTable tableData;
  private long rowCount;
  
  public NonUniqueHashIndex(RegularTable table, int id, String indexName, IndexColumn[] columns, IndexType indexType)
  {
    initBaseIndex(table, id, indexName, columns, indexType);
    this.indexColumn = columns[0].column.getColumnId();
    this.tableData = table;
    reset();
  }
  
  private void reset()
  {
    this.rows = ValueHashMap.newInstance();
    this.rowCount = 0L;
  }
  
  public void truncate(Session session)
  {
    reset();
  }
  
  public void add(Session session, Row row)
  {
    Value key = row.getValue(this.indexColumn);
    ArrayList<Long> positions = (ArrayList)this.rows.get(key);
    if (positions == null)
    {
      positions = New.arrayList();
      this.rows.put(key, positions);
    }
    positions.add(Long.valueOf(row.getKey()));
    this.rowCount += 1L;
  }
  
  public void remove(Session session, Row row)
  {
    if (this.rowCount == 1L)
    {
      reset();
    }
    else
    {
      Value key = row.getValue(this.indexColumn);
      ArrayList<Long> positions = (ArrayList)this.rows.get(key);
      if (positions.size() == 1) {
        this.rows.remove(key);
      } else {
        positions.remove(Long.valueOf(row.getKey()));
      }
      this.rowCount -= 1L;
    }
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    if ((first == null) || (last == null)) {
      throw DbException.throwInternalError();
    }
    if ((first != last) && 
      (compareKeys(first, last) != 0)) {
      throw DbException.throwInternalError();
    }
    Value v = first.getValue(this.indexColumn);
    
    v = v.convertTo(this.tableData.getColumn(this.indexColumn).getType());
    ArrayList<Long> positions = (ArrayList)this.rows.get(v);
    return new NonUniqueHashCursor(session, this.tableData, positions);
  }
  
  public long getRowCount(Session session)
  {
    return this.rowCount;
  }
  
  public long getRowCountApproximation()
  {
    return this.rowCount;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public void close(Session session) {}
  
  public void remove(Session session) {}
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    for (Column column : this.columns)
    {
      int index = column.getColumnId();
      int mask = masks[index];
      if ((mask & 0x1) != 1) {
        return 9.223372036854776E18D;
      }
    }
    return 2.0D;
  }
  
  public void checkRename() {}
  
  public boolean needRebuild()
  {
    return true;
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.getUnsupportedException("HASH");
  }
  
  public boolean canScan()
  {
    return false;
  }
}
