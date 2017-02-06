package org.h2.index;

import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.value.Value;

public class FunctionCursor
  implements Cursor
{
  private final ResultInterface result;
  private Value[] values;
  private Row row;
  
  FunctionCursor(ResultInterface result)
  {
    this.result = result;
  }
  
  public Row get()
  {
    if (this.values == null) {
      return null;
    }
    if (this.row == null) {
      this.row = new Row(this.values, 1);
    }
    return this.row;
  }
  
  public SearchRow getSearchRow()
  {
    return get();
  }
  
  public boolean next()
  {
    this.row = null;
    if ((this.result != null) && (this.result.next())) {
      this.values = this.result.currentRow();
    } else {
      this.values = null;
    }
    return this.values != null;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
