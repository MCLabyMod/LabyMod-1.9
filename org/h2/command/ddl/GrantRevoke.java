package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.Right;
import org.h2.engine.RightOwner;
import org.h2.engine.Role;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.table.Table;
import org.h2.util.New;

public class GrantRevoke
  extends DefineCommand
{
  private ArrayList<String> roleNames;
  private int operationType;
  private int rightMask;
  private final ArrayList<Table> tables = New.arrayList();
  private RightOwner grantee;
  
  public GrantRevoke(Session session)
  {
    super(session);
  }
  
  public void setOperationType(int operationType)
  {
    this.operationType = operationType;
  }
  
  public void addRight(int right)
  {
    this.rightMask |= right;
  }
  
  public void addRoleName(String roleName)
  {
    if (this.roleNames == null) {
      this.roleNames = New.arrayList();
    }
    this.roleNames.add(roleName);
  }
  
  public void setGranteeName(String granteeName)
  {
    Database db = this.session.getDatabase();
    this.grantee = db.findUser(granteeName);
    if (this.grantee == null)
    {
      this.grantee = db.findRole(granteeName);
      if (this.grantee == null) {
        throw DbException.get(90071, granteeName);
      }
    }
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (this.roleNames != null) {
      for (String name : this.roleNames)
      {
        Role grantedRole = db.findRole(name);
        if (grantedRole == null) {
          throw DbException.get(90070, name);
        }
        if (this.operationType == 49) {
          grantRole(grantedRole);
        } else if (this.operationType == 50) {
          revokeRole(grantedRole);
        } else {
          DbException.throwInternalError("type=" + this.operationType);
        }
      }
    } else if (this.operationType == 49) {
      grantRight();
    } else if (this.operationType == 50) {
      revokeRight();
    } else {
      DbException.throwInternalError("type=" + this.operationType);
    }
    return 0;
  }
  
  private void grantRight()
  {
    Database db = this.session.getDatabase();
    for (Table table : this.tables)
    {
      Right right = this.grantee.getRightForTable(table);
      if (right == null)
      {
        int id = getObjectId();
        right = new Right(db, id, this.grantee, this.rightMask, table);
        this.grantee.grantRight(table, right);
        db.addDatabaseObject(this.session, right);
      }
      else
      {
        right.setRightMask(right.getRightMask() | this.rightMask);
        db.updateMeta(this.session, right);
      }
    }
  }
  
  private void grantRole(Role grantedRole)
  {
    if ((grantedRole != this.grantee) && (this.grantee.isRoleGranted(grantedRole))) {
      return;
    }
    if ((this.grantee instanceof Role))
    {
      Role granteeRole = (Role)this.grantee;
      if (grantedRole.isRoleGranted(granteeRole)) {
        throw DbException.get(90074, grantedRole.getSQL());
      }
    }
    Database db = this.session.getDatabase();
    int id = getObjectId();
    Right right = new Right(db, id, this.grantee, grantedRole);
    db.addDatabaseObject(this.session, right);
    this.grantee.grantRole(grantedRole, right);
  }
  
  private void revokeRight()
  {
    for (Table table : this.tables)
    {
      Right right = this.grantee.getRightForTable(table);
      if (right != null)
      {
        int mask = right.getRightMask();
        int newRight = mask & (this.rightMask ^ 0xFFFFFFFF);
        Database db = this.session.getDatabase();
        if (newRight == 0)
        {
          db.removeDatabaseObject(this.session, right);
        }
        else
        {
          right.setRightMask(newRight);
          db.updateMeta(this.session, right);
        }
      }
    }
  }
  
  private void revokeRole(Role grantedRole)
  {
    Right right = this.grantee.getRightForRole(grantedRole);
    if (right == null) {
      return;
    }
    Database db = this.session.getDatabase();
    db.removeDatabaseObject(this.session, right);
  }
  
  public boolean isTransactional()
  {
    return false;
  }
  
  public void addTable(Table table)
  {
    this.tables.add(table);
  }
  
  public int getType()
  {
    return this.operationType;
  }
  
  public boolean isRoleMode()
  {
    return this.roleNames != null;
  }
  
  public boolean isRightMode()
  {
    return this.rightMask != 0;
  }
}
