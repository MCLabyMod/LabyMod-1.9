package org.h2.index;

import org.h2.result.Row;
import org.h2.result.SearchRow;

public abstract interface Cursor
{
  public abstract Row get();
  
  public abstract SearchRow getSearchRow();
  
  public abstract boolean next();
  
  public abstract boolean previous();
}
