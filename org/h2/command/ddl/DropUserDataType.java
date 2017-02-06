package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.engine.UserDataType;
import org.h2.message.DbException;

public class DropUserDataType
  extends DefineCommand
{
  private String typeName;
  private boolean ifExists;
  
  public DropUserDataType(Session session)
  {
    super(session);
  }
  
  public void setIfExists(boolean ifExists)
  {
    this.ifExists = ifExists;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    UserDataType type = db.findUserDataType(this.typeName);
    if (type == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90120, this.typeName);
      }
    }
    else {
      db.removeDatabaseObject(this.session, type);
    }
    return 0;
  }
  
  public void setTypeName(String name)
  {
    this.typeName = name;
  }
  
  public int getType()
  {
    return 47;
  }
}
