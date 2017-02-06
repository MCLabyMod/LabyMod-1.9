package org.h2.command.ddl;

import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.table.Table;

public class TruncateTable
  extends DefineCommand
{
  private Table table;
  
  public TruncateTable(Session session)
  {
    super(session);
  }
  
  public void setTable(Table table)
  {
    this.table = table;
  }
  
  public int update()
  {
    this.session.commit(true);
    if (!this.table.canTruncate()) {
      throw DbException.get(90106, this.table.getSQL());
    }
    this.session.getUser().checkRight(this.table, 2);
    this.table.lock(this.session, true, true);
    this.table.truncate(this.session);
    return 0;
  }
  
  public int getType()
  {
    return 53;
  }
}
