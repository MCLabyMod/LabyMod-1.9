package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Role;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;

public class DropRole
  extends DefineCommand
{
  private String roleName;
  private boolean ifExists;
  
  public DropRole(Session session)
  {
    super(session);
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (this.roleName.equals("PUBLIC")) {
      throw DbException.get(90091, this.roleName);
    }
    Role role = db.findRole(this.roleName);
    if (role == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90070, this.roleName);
      }
    }
    else {
      db.removeDatabaseObject(this.session, role);
    }
    return 0;
  }
  
  public void setIfExists(boolean ifExists)
  {
    this.ifExists = ifExists;
  }
  
  public int getType()
  {
    return 41;
  }
}
