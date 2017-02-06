package org.h2.command.ddl;

import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.table.TableView;

public class AlterView
  extends DefineCommand
{
  private TableView view;
  
  public AlterView(Session session)
  {
    super(session);
  }
  
  public void setView(TableView view)
  {
    this.view = view;
  }
  
  public int update()
  {
    this.session.commit(true);
    this.session.getUser().checkRight(this.view, 15);
    DbException e = this.view.recompile(this.session, false);
    if (e != null) {
      throw e;
    }
    return 0;
  }
  
  public int getType()
  {
    return 20;
  }
}
