package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.engine.UserDataType;
import org.h2.message.DbException;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.value.DataType;

public class CreateUserDataType
  extends DefineCommand
{
  private String typeName;
  private Column column;
  private boolean ifNotExists;
  
  public CreateUserDataType(Session session)
  {
    super(session);
  }
  
  public void setTypeName(String name)
  {
    this.typeName = name;
  }
  
  public void setColumn(Column column)
  {
    this.column = column;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    this.session.getUser().checkAdmin();
    if (db.findUserDataType(this.typeName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90119, this.typeName);
    }
    DataType builtIn = DataType.getTypeByName(this.typeName);
    if (builtIn != null)
    {
      if (!builtIn.hidden) {
        throw DbException.get(90119, this.typeName);
      }
      Table table = this.session.getDatabase().getFirstUserTable();
      if (table != null) {
        throw DbException.get(90119, this.typeName + " (" + table.getSQL() + ")");
      }
    }
    int id = getObjectId();
    UserDataType type = new UserDataType(db, id, this.typeName);
    type.setColumn(this.column);
    db.addDatabaseObject(this.session, type);
    return 0;
  }
  
  public int getType()
  {
    return 33;
  }
}
