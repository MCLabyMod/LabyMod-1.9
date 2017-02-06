package org.h2.index;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.MetaTable;
import org.h2.table.TableFilter;

public class MetaIndex
  extends BaseIndex
{
  private final MetaTable meta;
  private final boolean scan;
  
  public MetaIndex(MetaTable meta, IndexColumn[] columns, boolean scan)
  {
    initBaseIndex(meta, 0, null, columns, IndexType.createNonUnique(true));
    this.meta = meta;
    this.scan = scan;
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void remove(Session session, Row row)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    ArrayList<Row> rows = this.meta.generateRows(session, first, last);
    return new MetaCursor(rows);
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    if (this.scan) {
      return 10000.0D;
    }
    return getCostRangeIndex(masks, 1000L, filter, sortOrder);
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void remove(Session session)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public int getColumnIndex(Column col)
  {
    if (this.scan) {
      return -1;
    }
    return super.getColumnIndex(col);
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public long getRowCount(Session session)
  {
    return 1000L;
  }
  
  public long getRowCountApproximation()
  {
    return 1000L;
  }
  
  public long getDiskSpaceUsed()
  {
    return this.meta.getDiskSpaceUsed();
  }
  
  public String getPlanSQL()
  {
    return "meta";
  }
}
