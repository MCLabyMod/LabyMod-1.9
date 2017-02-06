package org.h2.index;

import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.TableFilter;
import org.h2.util.ValueHashMap;
import org.h2.value.Value;

public class HashIndex
  extends BaseIndex
{
  private final int indexColumn;
  private final RegularTable tableData;
  private ValueHashMap<Long> rows;
  
  public HashIndex(RegularTable table, int id, String indexName, IndexColumn[] columns, IndexType indexType)
  {
    initBaseIndex(table, id, indexName, columns, indexType);
    this.indexColumn = columns[0].column.getColumnId();
    this.tableData = table;
    reset();
  }
  
  private void reset()
  {
    this.rows = ValueHashMap.newInstance();
  }
  
  public void truncate(Session session)
  {
    reset();
  }
  
  public void add(Session session, Row row)
  {
    Value key = row.getValue(this.indexColumn);
    Object old = this.rows.get(key);
    if (old != null) {
      throw getDuplicateKeyException(key.toString());
    }
    this.rows.put(key, Long.valueOf(row.getKey()));
  }
  
  public void remove(Session session, Row row)
  {
    this.rows.remove(row.getValue(this.indexColumn));
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    if ((first == null) || (last == null)) {
      throw DbException.throwInternalError();
    }
    Value v = first.getValue(this.indexColumn);
    
    v = v.convertTo(this.tableData.getColumn(this.indexColumn).getType());
    
    Long pos = (Long)this.rows.get(v);
    Row result;
    Row result;
    if (pos == null) {
      result = null;
    } else {
      result = this.tableData.getRow(session, pos.intValue());
    }
    return new SingleRowCursor(result);
  }
  
  public long getRowCount(Session session)
  {
    return getRowCountApproximation();
  }
  
  public long getRowCountApproximation()
  {
    return this.rows.size();
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
