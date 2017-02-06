package org.h2.engine;

import org.h2.message.DbException;
import org.h2.table.Column;
import org.h2.table.Table;

public class UserDataType
  extends DbObjectBase
{
  private Column column;
  
  public UserDataType(Database database, int id, String name)
  {
    initDbObjectBase(database, id, name, "database");
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getDropSQL()
  {
    return "DROP DOMAIN IF EXISTS " + getSQL();
  }
  
  public String getCreateSQL()
  {
    return "CREATE DOMAIN " + getSQL() + " AS " + this.column.getCreateSQL();
  }
  
  public Column getColumn()
  {
    return this.column;
  }
  
  public int getType()
  {
    return 12;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
  }
  
  public void checkRename() {}
  
  public void setColumn(Column column)
  {
    this.column = column;
  }
}
