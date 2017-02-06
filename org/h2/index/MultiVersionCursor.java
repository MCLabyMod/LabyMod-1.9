package org.h2.index;

import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.util.MathUtils;

public class MultiVersionCursor
  implements Cursor
{
  private final MultiVersionIndex index;
  private final Session session;
  private final Cursor baseCursor;
  private final Cursor deltaCursor;
  private final Object sync;
  private SearchRow baseRow;
  private Row deltaRow;
  private boolean onBase;
  private boolean end;
  private boolean needNewDelta;
  private boolean needNewBase;
  private boolean reverse;
  
  MultiVersionCursor(Session session, MultiVersionIndex index, Cursor base, Cursor delta, Object sync)
  {
    this.session = session;
    this.index = index;
    this.baseCursor = base;
    this.deltaCursor = delta;
    this.sync = sync;
    this.needNewDelta = true;
    this.needNewBase = true;
  }
  
  void loadCurrent()
  {
    synchronized (this.sync)
    {
      this.baseRow = this.baseCursor.getSearchRow();
      this.deltaRow = this.deltaCursor.get();
      this.needNewDelta = false;
      this.needNewBase = false;
    }
  }
  
  private void loadNext(boolean base)
  {
    synchronized (this.sync)
    {
      if (base)
      {
        if (step(this.baseCursor)) {
          this.baseRow = this.baseCursor.getSearchRow();
        } else {
          this.baseRow = null;
        }
      }
      else if (step(this.deltaCursor)) {
        this.deltaRow = this.deltaCursor.get();
      } else {
        this.deltaRow = null;
      }
    }
  }
  
  private boolean step(Cursor cursor)
  {
    return this.reverse ? cursor.previous() : cursor.next();
  }
  
  public Row get()
  {
    synchronized (this.sync)
    {
      if (this.end) {
        return null;
      }
      return this.onBase ? this.baseCursor.get() : this.deltaCursor.get();
    }
  }
  
  public SearchRow getSearchRow()
  {
    synchronized (this.sync)
    {
      if (this.end) {
        return null;
      }
      return this.onBase ? this.baseCursor.getSearchRow() : this.deltaCursor.getSearchRow();
    }
  }
  
  public boolean next()
  {
    synchronized (this.sync)
    {
      if ((SysProperties.CHECK) && (this.end)) {
        DbException.throwInternalError();
      }
      int compare;
      for (;;)
      {
        if (this.needNewDelta)
        {
          loadNext(false);
          this.needNewDelta = false;
        }
        if (this.needNewBase)
        {
          loadNext(true);
          this.needNewBase = false;
        }
        if (this.deltaRow == null)
        {
          if (this.baseRow == null)
          {
            this.end = true;
            return false;
          }
          this.onBase = true;
          this.needNewBase = true;
          return true;
        }
        int sessionId = this.deltaRow.getSessionId();
        boolean isThisSession = sessionId == this.session.getId();
        boolean isDeleted = this.deltaRow.isDeleted();
        if ((isThisSession) && (isDeleted))
        {
          this.needNewDelta = true;
        }
        else
        {
          if (this.baseRow == null)
          {
            if (isDeleted)
            {
              if (isThisSession)
              {
                this.end = true;
                return false;
              }
              this.onBase = false;
              this.needNewDelta = true;
              return true;
            }
            DbException.throwInternalError();
          }
          compare = this.index.compareRows(this.deltaRow, this.baseRow);
          if (compare == 0)
          {
            long k1 = this.deltaRow.getKey();
            long k2 = this.baseRow.getKey();
            compare = MathUtils.compareLong(k1, k2);
          }
          if (compare != 0) {
            break;
          }
          if (isDeleted)
          {
            if (!isThisSession) {
              break;
            }
            DbException.throwInternalError(); break;
          }
          if (isThisSession)
          {
            this.onBase = false;
            this.needNewBase = true;
            this.needNewDelta = true;
            return true;
          }
          this.needNewBase = true;
          this.needNewDelta = true;
        }
      }
      if (compare > 0)
      {
        this.onBase = true;
        this.needNewBase = true;
        return true;
      }
      this.onBase = false;
      this.needNewDelta = true;
      return true;
    }
  }
  
  public boolean previous()
  {
    this.reverse = true;
    try
    {
      return next();
    }
    finally
    {
      this.reverse = false;
    }
  }
}
