package org.h2.index;

import java.util.Iterator;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;

class PageDataCursor
  implements Cursor
{
  private PageDataLeaf current;
  private int idx;
  private final long maxKey;
  private Row row;
  private final boolean multiVersion;
  private final Session session;
  private Iterator<Row> delta;
  
  PageDataCursor(Session session, PageDataLeaf current, int idx, long maxKey, boolean multiVersion)
  {
    this.current = current;
    this.idx = idx;
    this.maxKey = maxKey;
    this.multiVersion = multiVersion;
    this.session = session;
    if (multiVersion) {
      this.delta = current.index.getDelta();
    }
  }
  
  public Row get()
  {
    return this.row;
  }
  
  public SearchRow getSearchRow()
  {
    return get();
  }
  
  public boolean next()
  {
    if (!this.multiVersion)
    {
      nextRow();
      return checkMax();
    }
    do
    {
      while (this.delta != null) {
        if (!this.delta.hasNext())
        {
          this.delta = null;
          this.row = null;
        }
        else
        {
          this.row = ((Row)this.delta.next());
          if (this.row.isDeleted()) {
            if (this.row.getSessionId() != this.session.getId()) {
              break label135;
            }
          }
        }
      }
      nextRow();
    } while ((this.row != null) && (this.row.getSessionId() != 0) && (this.row.getSessionId() != this.session.getId()));
    label135:
    return checkMax();
  }
  
  private boolean checkMax()
  {
    if (this.row != null)
    {
      if (this.maxKey != Long.MAX_VALUE)
      {
        long x = this.current.index.getKey(this.row, Long.MAX_VALUE, Long.MAX_VALUE);
        if (x > this.maxKey)
        {
          this.row = null;
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  private void nextRow()
  {
    if (this.idx >= this.current.getEntryCount())
    {
      this.current = this.current.getNextPage();
      this.idx = 0;
      if (this.current == null)
      {
        this.row = null;
        return;
      }
    }
    this.row = this.current.getRowAt(this.idx);
    this.idx += 1;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
