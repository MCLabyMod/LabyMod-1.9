package org.h2.index;

import org.h2.engine.Session;
import org.h2.result.Row;
import org.h2.result.SearchRow;

public class PageBtreeCursor
  implements Cursor
{
  private final Session session;
  private final PageBtreeIndex index;
  private final SearchRow last;
  private PageBtreeLeaf current;
  private int i;
  private SearchRow currentSearchRow;
  private Row currentRow;
  
  PageBtreeCursor(Session session, PageBtreeIndex index, SearchRow last)
  {
    this.session = session;
    this.index = index;
    this.last = last;
  }
  
  void setCurrent(PageBtreeLeaf current, int i)
  {
    this.current = current;
    this.i = i;
  }
  
  public Row get()
  {
    if ((this.currentRow == null) && (this.currentSearchRow != null)) {
      this.currentRow = this.index.getRow(this.session, this.currentSearchRow.getKey());
    }
    return this.currentRow;
  }
  
  public SearchRow getSearchRow()
  {
    return this.currentSearchRow;
  }
  
  public boolean next()
  {
    if (this.current == null) {
      return false;
    }
    if (this.i >= this.current.getEntryCount())
    {
      this.current.nextPage(this);
      if (this.current == null) {
        return false;
      }
    }
    this.currentSearchRow = this.current.getRow(this.i);
    this.currentRow = null;
    if ((this.last != null) && (this.index.compareRows(this.currentSearchRow, this.last) > 0))
    {
      this.currentSearchRow = null;
      return false;
    }
    this.i += 1;
    return true;
  }
  
  public boolean previous()
  {
    if (this.current == null) {
      return false;
    }
    if (this.i < 0)
    {
      this.current.previousPage(this);
      if (this.current == null) {
        return false;
      }
    }
    this.currentSearchRow = this.current.getRow(this.i);
    this.currentRow = null;
    this.i -= 1;
    return true;
  }
}
