package org.h2.index;

import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.IndexColumn;
import org.h2.table.RangeTable;
import org.h2.table.TableFilter;
import org.h2.value.Value;

public class RangeIndex
  extends BaseIndex
{
  private final RangeTable rangeTable;
  
  public RangeIndex(RangeTable table, IndexColumn[] columns)
  {
    initBaseIndex(table, 0, "RANGE_INDEX", columns, IndexType.createNonUnique(true));
    
    this.rangeTable = table;
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public void remove(Session session, Row row)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    long min = this.rangeTable.getMin(session);long start = min;
    long max = this.rangeTable.getMax(session);long end = max;
    long step = this.rangeTable.getStep(session);
    try
    {
      start = Math.max(min, first == null ? min : first.getValue(0).getLong());
    }
    catch (Exception e) {}
    try
    {
      end = Math.min(max, last == null ? max : last.getValue(0).getLong());
    }
    catch (Exception e) {}
    return new RangeCursor(start, end, step);
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return 1.0D;
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public void remove(Session session)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    long pos = first ? this.rangeTable.getMin(session) : this.rangeTable.getMax(session);
    return new RangeCursor(pos, pos);
  }
  
  public long getRowCount(Session session)
  {
    return this.rangeTable.getRowCountApproximation();
  }
  
  public long getRowCountApproximation()
  {
    return this.rangeTable.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
}
