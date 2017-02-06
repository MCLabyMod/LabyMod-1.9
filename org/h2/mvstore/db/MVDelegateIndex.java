package org.h2.mvstore.db;

import java.util.List;
import org.h2.engine.Session;
import org.h2.index.BaseIndex;
import org.h2.index.Cursor;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.TableFilter;
import org.h2.value.ValueLong;

public class MVDelegateIndex
  extends BaseIndex
  implements MVIndex
{
  private final MVPrimaryIndex mainIndex;
  
  public MVDelegateIndex(MVTable table, int id, String name, MVPrimaryIndex mainIndex, IndexType indexType)
  {
    IndexColumn[] cols = IndexColumn.wrap(new Column[] { table.getColumn(mainIndex.getMainIndexColumn()) });
    
    initBaseIndex(table, id, name, cols, indexType);
    this.mainIndex = mainIndex;
    if (id < 0) {
      throw DbException.throwInternalError("" + name);
    }
  }
  
  public void addRowsToBuffer(List<Row> rows, String bufferName)
  {
    throw DbException.throwInternalError();
  }
  
  public void addBufferedRows(List<String> bufferNames)
  {
    throw DbException.throwInternalError();
  }
  
  public void add(Session session, Row row) {}
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public void close(Session session) {}
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    ValueLong min = this.mainIndex.getKey(first, MVPrimaryIndex.MIN, MVPrimaryIndex.MIN);
    
    ValueLong max = this.mainIndex.getKey(last, MVPrimaryIndex.MAX, MVPrimaryIndex.MIN);
    
    return this.mainIndex.find(session, min, max);
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    return this.mainIndex.findFirstOrLast(session, first);
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
    return 10L * getCostRangeIndex(masks, this.mainIndex.getRowCountApproximation(), filter, sortOrder);
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public void remove(Session session, Row row) {}
  
  public void remove(Session session)
  {
    this.mainIndex.setMainIndexColumn(-1);
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
    return 0L;
  }
}
