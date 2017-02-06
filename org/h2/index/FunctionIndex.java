package org.h2.index;

import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.FunctionTable;
import org.h2.table.IndexColumn;
import org.h2.table.TableFilter;

public class FunctionIndex
  extends BaseIndex
{
  private final FunctionTable functionTable;
  
  public FunctionIndex(FunctionTable functionTable, IndexColumn[] columns)
  {
    initBaseIndex(functionTable, 0, null, columns, IndexType.createNonUnique(true));
    this.functionTable = functionTable;
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public void remove(Session session, Row row)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    if (this.functionTable.isBufferResultSetToLocalTemp()) {
      return new FunctionCursor(this.functionTable.getResult(session));
    }
    return new FunctionCursorResultSet(session, this.functionTable.getResultSet(session));
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    if (masks != null) {
      throw DbException.getUnsupportedException("ALIAS");
    }
    long expectedRows;
    long expectedRows;
    if (this.functionTable.canGetRowCount()) {
      expectedRows = this.functionTable.getRowCountApproximation();
    } else {
      expectedRows = this.database.getSettings().estimatedFunctionTableRows;
    }
    return expectedRows * 10L;
  }
  
  public void remove(Session session)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public long getRowCount(Session session)
  {
    return this.functionTable.getRowCount(session);
  }
  
  public long getRowCountApproximation()
  {
    return this.functionTable.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public String getPlanSQL()
  {
    return "function";
  }
  
  public boolean canScan()
  {
    return false;
  }
}
