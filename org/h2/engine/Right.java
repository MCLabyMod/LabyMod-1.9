package org.h2.engine;

import org.h2.message.DbException;
import org.h2.table.Table;

public class Right
  extends DbObjectBase
{
  public static final int SELECT = 1;
  public static final int DELETE = 2;
  public static final int INSERT = 4;
  public static final int UPDATE = 8;
  public static final int ALTER_ANY_SCHEMA = 16;
  public static final int ALL = 15;
  private Role grantedRole;
  private int grantedRight;
  private Table grantedTable;
  private RightOwner grantee;
  
  public Right(Database db, int id, RightOwner grantee, Role grantedRole)
  {
    initDbObjectBase(db, id, "RIGHT_" + id, "user");
    this.grantee = grantee;
    this.grantedRole = grantedRole;
  }
  
  public Right(Database db, int id, RightOwner grantee, int grantedRight, Table grantedRightOnTable)
  {
    initDbObjectBase(db, id, "" + id, "user");
    this.grantee = grantee;
    this.grantedRight = grantedRight;
    this.grantedTable = grantedRightOnTable;
  }
  
  private static boolean appendRight(StringBuilder buff, int right, int mask, String name, boolean comma)
  {
    if ((right & mask) != 0)
    {
      if (comma) {
        buff.append(", ");
      }
      buff.append(name);
      return true;
    }
    return comma;
  }
  
  public String getRights()
  {
    StringBuilder buff = new StringBuilder();
    if (this.grantedRight == 15)
    {
      buff.append("ALL");
    }
    else
    {
      boolean comma = false;
      comma = appendRight(buff, this.grantedRight, 1, "SELECT", comma);
      comma = appendRight(buff, this.grantedRight, 2, "DELETE", comma);
      comma = appendRight(buff, this.grantedRight, 4, "INSERT", comma);
      comma = appendRight(buff, this.grantedRight, 16, "ALTER ANY SCHEMA", comma);
      
      appendRight(buff, this.grantedRight, 8, "UPDATE", comma);
    }
    return buff.toString();
  }
  
  public Role getGrantedRole()
  {
    return this.grantedRole;
  }
  
  public Table getGrantedTable()
  {
    return this.grantedTable;
  }
  
  public DbObject getGrantee()
  {
    return this.grantee;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    StringBuilder buff = new StringBuilder();
    buff.append("GRANT ");
    if (this.grantedRole != null)
    {
      buff.append(this.grantedRole.getSQL());
    }
    else
    {
      buff.append(getRights());
      if (table != null) {
        buff.append(" ON ").append(table.getSQL());
      }
    }
    buff.append(" TO ").append(this.grantee.getSQL());
    return buff.toString();
  }
  
  public String getCreateSQL()
  {
    return getCreateSQLForCopy(this.grantedTable, null);
  }
  
  public int getType()
  {
    return 8;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    if (this.grantedTable != null) {
      this.grantee.revokeRight(this.grantedTable);
    } else {
      this.grantee.revokeRole(this.grantedRole);
    }
    this.database.removeMeta(session, getId());
    this.grantedRole = null;
    this.grantedTable = null;
    this.grantee = null;
    invalidate();
  }
  
  public void checkRename()
  {
    DbException.throwInternalError();
  }
  
  public void setRightMask(int rightMask)
  {
    this.grantedRight = rightMask;
  }
  
  public int getRightMask()
  {
    return this.grantedRight;
  }
}
