package org.h2.index;

import org.h2.engine.Session;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.schema.SchemaObject;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;

public abstract interface Index
  extends SchemaObject
{
  public abstract String getPlanSQL();
  
  public abstract void close(Session paramSession);
  
  public abstract void add(Session paramSession, Row paramRow);
  
  public abstract void remove(Session paramSession, Row paramRow);
  
  public abstract Cursor find(Session paramSession, SearchRow paramSearchRow1, SearchRow paramSearchRow2);
  
  public abstract Cursor find(TableFilter paramTableFilter, SearchRow paramSearchRow1, SearchRow paramSearchRow2);
  
  public abstract double getCost(Session paramSession, int[] paramArrayOfInt, TableFilter paramTableFilter, SortOrder paramSortOrder);
  
  public abstract void remove(Session paramSession);
  
  public abstract void truncate(Session paramSession);
  
  public abstract boolean canGetFirstOrLast();
  
  public abstract boolean canFindNext();
  
  public abstract Cursor findNext(Session paramSession, SearchRow paramSearchRow1, SearchRow paramSearchRow2);
  
  public abstract Cursor findFirstOrLast(Session paramSession, boolean paramBoolean);
  
  public abstract boolean needRebuild();
  
  public abstract long getRowCount(Session paramSession);
  
  public abstract long getRowCountApproximation();
  
  public abstract long getDiskSpaceUsed();
  
  public abstract int compareRows(SearchRow paramSearchRow1, SearchRow paramSearchRow2);
  
  public abstract int getColumnIndex(Column paramColumn);
  
  public abstract IndexColumn[] getIndexColumns();
  
  public abstract Column[] getColumns();
  
  public abstract IndexType getIndexType();
  
  public abstract Table getTable();
  
  public abstract void commit(int paramInt, Row paramRow);
  
  public abstract Row getRow(Session paramSession, long paramLong);
  
  public abstract boolean isRowIdIndex();
  
  public abstract boolean canScan();
  
  public abstract void setSortedInsertMode(boolean paramBoolean);
}
