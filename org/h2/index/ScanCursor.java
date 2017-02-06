package org.h2.index;

import java.util.Iterator;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;

public class ScanCursor
  implements Cursor
{
  private final ScanIndex scan;
  private Row row;
  private final Session session;
  private final boolean multiVersion;
  private Iterator<Row> delta;
  
  ScanCursor(Session session, ScanIndex scan, boolean multiVersion)
  {
    this.session = session;
    this.scan = scan;
    this.multiVersion = multiVersion;
    if (multiVersion) {
      this.delta = scan.getDelta();
    }
    this.row = null;
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
    if (this.multiVersion)
    {
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
                break label137;
              }
            }
          }
        }
        this.row = this.scan.getNextRow(this.row);
      } while ((this.row != null) && (this.row.getSessionId() != 0) && (this.row.getSessionId() != this.session.getId()));
      label137:
      return this.row != null;
    }
    this.row = this.scan.getNextRow(this.row);
    return this.row != null;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
