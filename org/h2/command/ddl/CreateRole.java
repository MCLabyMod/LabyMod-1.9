package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Role;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;

public class CreateRole
  extends DefineCommand
{
  private String roleName;
  private boolean ifNotExists;
  
  public CreateRole(Session session)
  {
    super(session);
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setRoleName(String name)
  {
    this.roleName = name;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (db.findUser(this.roleName) != null) {
      throw DbException.get(90033, this.roleName);
    }
    if (db.findRole(this.roleName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90069, this.roleName);
    }
    int id = getObjectId();
    Role role = new Role(db, id, this.roleName, false);
    db.addDatabaseObject(this.session, role);
    return 0;
  }
  
  public int getType()
  {
    return 27;
  }
}
