package org.h2.engine;

import org.h2.message.DbException;
import org.h2.table.Table;

public class Role
  extends RightOwner
{
  private final boolean system;
  
  public Role(Database database, int id, String roleName, boolean system)
  {
    super(database, id, roleName, "user");
    this.system = system;
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQL(boolean ifNotExists)
  {
    if (this.system) {
      return null;
    }
    StringBuilder buff = new StringBuilder("CREATE ROLE ");
    if (ifNotExists) {
      buff.append("IF NOT EXISTS ");
    }
    buff.append(getSQL());
    return buff.toString();
  }
  
  public String getCreateSQL()
  {
    return getCreateSQL(false);
  }
  
  public int getType()
  {
    return 7;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    for (User user : this.database.getAllUsers())
    {
      Right right = user.getRightForRole(this);
      if (right != null) {
        this.database.removeDatabaseObject(session, right);
      }
    }
    for (Role r2 : this.database.getAllRoles())
    {
      Right right = r2.getRightForRole(this);
      if (right != null) {
        this.database.removeDatabaseObject(session, right);
      }
    }
    for (Right right : this.database.getAllRights()) {
      if (right.getGrantee() == this) {
        this.database.removeDatabaseObject(session, right);
      }
    }
    this.database.removeMeta(session, getId());
    invalidate();
  }
  
  public void checkRename() {}
}
