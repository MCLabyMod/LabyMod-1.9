package org.h2.index;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.table.RegularTable;

public class NonUniqueHashCursor
  implements Cursor
{
  private final Session session;
  private final ArrayList<Long> positions;
  private final RegularTable tableData;
  private int index = -1;
  
  public NonUniqueHashCursor(Session session, RegularTable tableData, ArrayList<Long> positions)
  {
    this.session = session;
    this.tableData = tableData;
    this.positions = positions;
  }
  
  public Row get()
  {
    if ((this.index < 0) || (this.index >= this.positions.size())) {
      return null;
    }
    return this.tableData.getRow(this.session, ((Long)this.positions.get(this.index)).longValue());
  }
  
  public SearchRow getSearchRow()
  {
    return get();
  }
  
  public boolean next()
  {
    return (this.positions != null) && (++this.index < this.positions.size());
  }
  
  public boolean previous()
  {
    return (this.positions != null) && (--this.index >= 0);
  }
}
