package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;

public class CreateSchema
  extends DefineCommand
{
  private String schemaName;
  private String authorization;
  private boolean ifNotExists;
  
  public CreateSchema(Session session)
  {
    super(session);
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public int update()
  {
    this.session.getUser().checkSchemaAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    User user = db.getUser(this.authorization);
    if (!db.isStarting()) {
      user.checkSchemaAdmin();
    }
    if (db.findSchema(this.schemaName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90078, this.schemaName);
    }
    int id = getObjectId();
    Schema schema = new Schema(db, id, this.schemaName, user, false);
    db.addDatabaseObject(this.session, schema);
    return 0;
  }
  
  public void setSchemaName(String name)
  {
    this.schemaName = name;
  }
  
  public void setAuthorization(String userName)
  {
    this.authorization = userName;
  }
  
  public int getType()
  {
    return 28;
  }
}
