package org.h2.engine;

import java.util.HashMap;
import org.h2.table.Table;
import org.h2.util.New;

public abstract class RightOwner
  extends DbObjectBase
{
  private HashMap<Role, Right> grantedRoles;
  private HashMap<Table, Right> grantedRights;
  
  protected RightOwner(Database database, int id, String name, String traceModule)
  {
    initDbObjectBase(database, id, name, traceModule);
  }
  
  public boolean isRoleGranted(Role grantedRole)
  {
    if (grantedRole == this) {
      return true;
    }
    if (this.grantedRoles != null) {
      for (Role role : this.grantedRoles.keySet())
      {
        if (role == grantedRole) {
          return true;
        }
        if (role.isRoleGranted(grantedRole)) {
          return true;
        }
      }
    }
    return false;
  }
  
  boolean isRightGrantedRecursive(Table table, int rightMask)
  {
    if (this.grantedRights != null)
    {
      Right right = (Right)this.grantedRights.get(table);
      if ((right != null) && 
        ((right.getRightMask() & rightMask) == rightMask)) {
        return true;
      }
    }
    if (this.grantedRoles != null) {
      for (RightOwner role : this.grantedRoles.keySet()) {
        if (role.isRightGrantedRecursive(table, rightMask)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void grantRight(Table table, Right right)
  {
    if (this.grantedRights == null) {
      this.grantedRights = New.hashMap();
    }
    this.grantedRights.put(table, right);
  }
  
  void revokeRight(Table table)
  {
    if (this.grantedRights == null) {
      return;
    }
    this.grantedRights.remove(table);
    if (this.grantedRights.size() == 0) {
      this.grantedRights = null;
    }
  }
  
  public void grantRole(Role role, Right right)
  {
    if (this.grantedRoles == null) {
      this.grantedRoles = New.hashMap();
    }
    this.grantedRoles.put(role, right);
  }
  
  void revokeRole(Role role)
  {
    if (this.grantedRoles == null) {
      return;
    }
    Right right = (Right)this.grantedRoles.get(role);
    if (right == null) {
      return;
    }
    this.grantedRoles.remove(role);
    if (this.grantedRoles.size() == 0) {
      this.grantedRoles = null;
    }
  }
  
  public Right getRightForTable(Table table)
  {
    if (this.grantedRights == null) {
      return null;
    }
    return (Right)this.grantedRights.get(table);
  }
  
  public Right getRightForRole(Role role)
  {
    if (this.grantedRoles == null) {
      return null;
    }
    return (Right)this.grantedRoles.get(role);
  }
}
