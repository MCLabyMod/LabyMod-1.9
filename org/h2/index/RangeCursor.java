package org.h2.index;

import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.value.Value;
import org.h2.value.ValueLong;

class RangeCursor
  implements Cursor
{
  private boolean beforeFirst;
  private long current;
  private Row currentRow;
  private final long start;
  private final long end;
  private final long step;
  
  RangeCursor(long start, long end)
  {
    this(start, end, 1L);
  }
  
  RangeCursor(long start, long end, long step)
  {
    this.start = start;
    this.end = end;
    this.step = step;
    this.beforeFirst = true;
  }
  
  public Row get()
  {
    return this.currentRow;
  }
  
  public SearchRow getSearchRow()
  {
    return this.currentRow;
  }
  
  public boolean next()
  {
    if (this.beforeFirst)
    {
      this.beforeFirst = false;
      this.current = this.start;
    }
    else
    {
      this.current += this.step;
    }
    this.currentRow = new Row(new Value[] { ValueLong.get(this.current) }, 1);
    return this.current <= this.end;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
