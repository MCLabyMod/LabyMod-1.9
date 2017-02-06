package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.engine.UserAggregate;
import org.h2.message.DbException;

public class DropAggregate
  extends DefineCommand
{
  private String name;
  private boolean ifExists;
  
  public DropAggregate(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    UserAggregate aggregate = db.findAggregate(this.name);
    if (aggregate == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90132, this.name);
      }
    }
    else {
      db.removeDatabaseObject(this.session, aggregate);
    }
    return 0;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setIfExists(boolean ifExists)
  {
    this.ifExists = ifExists;
  }
  
  public int getType()
  {
    return 36;
  }
}
