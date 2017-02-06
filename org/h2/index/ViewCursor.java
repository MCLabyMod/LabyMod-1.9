package org.h2.index;

import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.table.Table;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class ViewCursor
  implements Cursor
{
  private final Table table;
  private final ViewIndex index;
  private final LocalResult result;
  private final SearchRow first;
  private final SearchRow last;
  private Row current;
  
  ViewCursor(ViewIndex index, LocalResult result, SearchRow first, SearchRow last)
  {
    this.table = index.getTable();
    this.index = index;
    this.result = result;
    this.first = first;
    this.last = last;
  }
  
  public Row get()
  {
    return this.current;
  }
  
  public SearchRow getSearchRow()
  {
    return this.current;
  }
  
  public boolean next()
  {
    int comp;
    do
    {
      int comp;
      do
      {
        boolean res = this.result.next();
        if (!res)
        {
          if (this.index.isRecursive()) {
            this.result.reset();
          } else {
            this.result.close();
          }
          this.current = null;
          return false;
        }
        this.current = this.table.getTemplateRow();
        Value[] values = this.result.currentRow();
        int i = 0;
        for (int len = this.current.getColumnCount(); i < len; i++)
        {
          Value v = i < values.length ? values[i] : ValueNull.INSTANCE;
          this.current.setValue(i, v);
        }
        if (this.first == null) {
          break;
        }
        comp = this.index.compareRows(this.current, this.first);
      } while (comp < 0);
      if (this.last == null) {
        break;
      }
      comp = this.index.compareRows(this.current, this.last);
    } while (comp > 0);
    return true;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
