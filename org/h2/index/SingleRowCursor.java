package org.h2.index;

import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;

public class SingleRowCursor
  implements Cursor
{
  private Row row;
  private boolean end;
  
  public SingleRowCursor(Row row)
  {
    this.row = row;
  }
  
  public Row get()
  {
    return this.row;
  }
  
  public SearchRow getSearchRow()
  {
    return this.row;
  }
  
  public boolean next()
  {
    if ((this.row == null) || (this.end))
    {
      this.row = null;
      return false;
    }
    this.end = true;
    return true;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
