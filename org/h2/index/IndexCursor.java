package org.h2.index;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueGeometry;
import org.h2.value.ValueNull;

public class IndexCursor
  implements Cursor
{
  private Session session;
  private final TableFilter tableFilter;
  private Index index;
  private Table table;
  private IndexColumn[] indexColumns;
  private boolean alwaysFalse;
  private SearchRow start;
  private SearchRow end;
  private SearchRow intersects;
  private Cursor cursor;
  private Column inColumn;
  private int inListIndex;
  private Value[] inList;
  private ResultInterface inResult;
  private HashSet<Value> inResultTested;
  
  public IndexCursor(TableFilter filter)
  {
    this.tableFilter = filter;
  }
  
  public void setIndex(Index index)
  {
    this.index = index;
    this.table = index.getTable();
    Column[] columns = this.table.getColumns();
    this.indexColumns = new IndexColumn[columns.length];
    IndexColumn[] idxCols = index.getIndexColumns();
    if (idxCols != null)
    {
      int i = 0;
      for (int len = columns.length; i < len; i++)
      {
        int idx = index.getColumnIndex(columns[i]);
        if (idx >= 0) {
          this.indexColumns[i] = idxCols[idx];
        }
      }
    }
  }
  
  public void find(Session s, ArrayList<IndexCondition> indexConditions)
  {
    this.session = s;
    this.alwaysFalse = false;
    this.start = (this.end = null);
    this.inList = null;
    this.inColumn = null;
    this.inResult = null;
    this.inResultTested = null;
    this.intersects = null;
    
    int i = 0;
    for (int size = indexConditions.size(); i < size; i++)
    {
      IndexCondition condition = (IndexCondition)indexConditions.get(i);
      if (condition.isAlwaysFalse())
      {
        this.alwaysFalse = true;
        break;
      }
      Column column = condition.getColumn();
      if (condition.getCompareType() == 9)
      {
        if ((this.start == null) && (this.end == null) && 
          (canUseIndexForIn(column)))
        {
          this.inColumn = column;
          this.inList = condition.getCurrentValueList(s);
          this.inListIndex = 0;
        }
      }
      else if (condition.getCompareType() == 10)
      {
        if ((this.start == null) && (this.end == null) && 
          (canUseIndexForIn(column)))
        {
          this.inColumn = column;
          this.inResult = condition.getCurrentResult();
        }
      }
      else
      {
        Value v = condition.getCurrentValue(s);
        boolean isStart = condition.isStart();
        boolean isEnd = condition.isEnd();
        boolean isIntersects = condition.isSpatialIntersects();
        int columnId = column.getColumnId();
        if (columnId >= 0)
        {
          IndexColumn idxCol = this.indexColumns[columnId];
          if ((idxCol != null) && ((idxCol.sortType & 0x1) != 0))
          {
            boolean temp = isStart;
            isStart = isEnd;
            isEnd = temp;
          }
        }
        if (isStart) {
          this.start = getSearchRow(this.start, columnId, v, true);
        }
        if (isEnd) {
          this.end = getSearchRow(this.end, columnId, v, false);
        }
        if (isIntersects) {
          this.intersects = getSpatialSearchRow(this.intersects, columnId, v);
        }
        if ((isStart) || (isEnd))
        {
          this.inColumn = null;
          this.inList = null;
          this.inResult = null;
        }
        if ((!this.session.getDatabase().getSettings().optimizeIsNull) && 
          (isStart) && (isEnd) && 
          (v == ValueNull.INSTANCE)) {
          this.alwaysFalse = true;
        }
      }
    }
    if (this.inColumn != null) {
      return;
    }
    if (!this.alwaysFalse) {
      if ((this.intersects != null) && ((this.index instanceof SpatialIndex))) {
        this.cursor = ((SpatialIndex)this.index).findByGeometry(this.tableFilter, this.intersects);
      } else {
        this.cursor = this.index.find(this.tableFilter, this.start, this.end);
      }
    }
  }
  
  private boolean canUseIndexForIn(Column column)
  {
    if (this.inColumn != null) {
      return false;
    }
    IndexColumn[] cols = this.index.getIndexColumns();
    if (cols == null) {
      return true;
    }
    IndexColumn idxCol = cols[0];
    return (idxCol == null) || (idxCol.column == column);
  }
  
  private SearchRow getSpatialSearchRow(SearchRow row, int columnId, Value v)
  {
    ValueGeometry vg;
    if (row == null) {
      row = this.table.getTemplateRow();
    } else if (row.getValue(columnId) != null) {
      vg = (ValueGeometry)row.getValue(columnId).convertTo(22);
    }
    if (columnId < 0) {
      row.setKey(v.getLong());
    } else {
      row.setValue(columnId, v);
    }
    return row;
  }
  
  private SearchRow getSearchRow(SearchRow row, int columnId, Value v, boolean max)
  {
    if (row == null) {
      row = this.table.getTemplateRow();
    } else {
      v = getMax(row.getValue(columnId), v, max);
    }
    if (columnId < 0) {
      row.setKey(v.getLong());
    } else {
      row.setValue(columnId, v);
    }
    return row;
  }
  
  private Value getMax(Value a, Value b, boolean bigger)
  {
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }
    if (this.session.getDatabase().getSettings().optimizeIsNull)
    {
      if (a == ValueNull.INSTANCE) {
        return b;
      }
      if (b == ValueNull.INSTANCE) {
        return a;
      }
    }
    int comp = a.compareTo(b, this.table.getDatabase().getCompareMode());
    if (comp == 0) {
      return a;
    }
    if (((a == ValueNull.INSTANCE) || (b == ValueNull.INSTANCE)) && 
      (this.session.getDatabase().getSettings().optimizeIsNull)) {
      return null;
    }
    if (!bigger) {
      comp = -comp;
    }
    return comp > 0 ? a : b;
  }
  
  public boolean isAlwaysFalse()
  {
    return this.alwaysFalse;
  }
  
  public Row get()
  {
    if (this.cursor == null) {
      return null;
    }
    return this.cursor.get();
  }
  
  public SearchRow getSearchRow()
  {
    return this.cursor.getSearchRow();
  }
  
  public boolean next()
  {
    for (;;)
    {
      if (this.cursor == null)
      {
        nextCursor();
        if (this.cursor == null) {
          return false;
        }
      }
      if (this.cursor.next()) {
        return true;
      }
      this.cursor = null;
    }
  }
  
  private void nextCursor()
  {
    if (this.inList != null) {
      while (this.inListIndex < this.inList.length)
      {
        Value v = this.inList[(this.inListIndex++)];
        if (v != ValueNull.INSTANCE)
        {
          find(v);
          break;
        }
      }
    }
    if (this.inResult != null) {
      while (this.inResult.next())
      {
        Value v = this.inResult.currentRow()[0];
        if (v != ValueNull.INSTANCE)
        {
          v = this.inColumn.convert(v);
          if (this.inResultTested == null) {
            this.inResultTested = new HashSet();
          }
          if (this.inResultTested.add(v))
          {
            find(v);
            break;
          }
        }
      }
    }
  }
  
  private void find(Value v)
  {
    v = this.inColumn.convert(v);
    int id = this.inColumn.getColumnId();
    if (this.start == null) {
      this.start = this.table.getTemplateRow();
    }
    this.start.setValue(id, v);
    this.cursor = this.index.find(this.tableFilter, this.start, this.start);
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
