package org.h2.engine;

import java.util.ArrayList;
import java.util.Arrays;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.security.SHA256;
import org.h2.table.MetaTable;
import org.h2.table.RangeTable;
import org.h2.table.Table;
import org.h2.table.TableView;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class User
  extends RightOwner
{
  private final boolean systemUser;
  private byte[] salt;
  private byte[] passwordHash;
  private boolean admin;
  
  public User(Database database, int id, String userName, boolean systemUser)
  {
    super(database, id, userName, "user");
    this.systemUser = systemUser;
  }
  
  public void setAdmin(boolean admin)
  {
    this.admin = admin;
  }
  
  public boolean isAdmin()
  {
    return this.admin;
  }
  
  public void setSaltAndHash(byte[] salt, byte[] hash)
  {
    this.salt = salt;
    this.passwordHash = hash;
  }
  
  public void setUserPasswordHash(byte[] userPasswordHash)
  {
    if (userPasswordHash != null) {
      if (userPasswordHash.length == 0)
      {
        this.salt = (this.passwordHash = userPasswordHash);
      }
      else
      {
        this.salt = new byte[8];
        MathUtils.randomBytes(this.salt);
        this.passwordHash = SHA256.getHashWithSalt(userPasswordHash, this.salt);
      }
    }
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getCreateSQL()
  {
    return getCreateSQL(true);
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public void checkRight(Table table, int rightMask)
  {
    if (!hasRight(table, rightMask)) {
      throw DbException.get(90096, table.getSQL());
    }
  }
  
  public boolean hasRight(Table table, int rightMask)
  {
    if ((rightMask != 1) && (!this.systemUser) && (table != null)) {
      table.checkWritingAllowed();
    }
    if (this.admin) {
      return true;
    }
    Role publicRole = this.database.getPublicRole();
    if (publicRole.isRightGrantedRecursive(table, rightMask)) {
      return true;
    }
    if (((table instanceof MetaTable)) || ((table instanceof RangeTable))) {
      return true;
    }
    if (table != null)
    {
      if (hasRight(null, 16)) {
        return true;
      }
      String tableType = table.getTableType();
      if ("VIEW".equals(tableType))
      {
        TableView v = (TableView)table;
        if (v.getOwner() == this) {
          return true;
        }
      }
      else if (tableType == null)
      {
        return true;
      }
      if ((table.isTemporary()) && (!table.isGlobalTemporary())) {
        return true;
      }
    }
    if (isRightGrantedRecursive(table, rightMask)) {
      return true;
    }
    return false;
  }
  
  public String getCreateSQL(boolean password)
  {
    StringBuilder buff = new StringBuilder("CREATE USER IF NOT EXISTS ");
    buff.append(getSQL());
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    if (password) {
      buff.append(" SALT '").append(StringUtils.convertBytesToHex(this.salt)).append("' HASH '").append(StringUtils.convertBytesToHex(this.passwordHash)).append('\'');
    } else {
      buff.append(" PASSWORD ''");
    }
    if (this.admin) {
      buff.append(" ADMIN");
    }
    return buff.toString();
  }
  
  boolean validateUserPasswordHash(byte[] userPasswordHash)
  {
    if ((userPasswordHash.length == 0) && (this.passwordHash.length == 0)) {
      return true;
    }
    if (userPasswordHash.length == 0) {
      userPasswordHash = SHA256.getKeyPasswordHash(getName(), new char[0]);
    }
    byte[] hash = SHA256.getHashWithSalt(userPasswordHash, this.salt);
    return Utils.compareSecure(hash, this.passwordHash);
  }
  
  public void checkAdmin()
  {
    if (!this.admin) {
      throw DbException.get(90040);
    }
  }
  
  public void checkSchemaAdmin()
  {
    if (!hasRight(null, 16)) {
      throw DbException.get(90040);
    }
  }
  
  public int getType()
  {
    return 2;
  }
  
  public ArrayList<DbObject> getChildren()
  {
    ArrayList<DbObject> children = New.arrayList();
    for (Right right : this.database.getAllRights()) {
      if (right.getGrantee() == this) {
        children.add(right);
      }
    }
    for (Schema schema : this.database.getAllSchemas()) {
      if (schema.getOwner() == this) {
        children.add(schema);
      }
    }
    return children;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    for (Right right : this.database.getAllRights()) {
      if (right.getGrantee() == this) {
        this.database.removeDatabaseObject(session, right);
      }
    }
    this.database.removeMeta(session, getId());
    this.salt = null;
    Arrays.fill(this.passwordHash, (byte)0);
    this.passwordHash = null;
    invalidate();
  }
  
  public void checkRename() {}
  
  public void checkOwnsNoSchemas()
  {
    for (Schema s : this.database.getAllSchemas()) {
      if (this == s.getOwner()) {
        throw DbException.get(90107, new String[] { getName(), s.getName() });
      }
    }
  }
}
