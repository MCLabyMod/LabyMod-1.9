package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;

public class DropSchema
  extends DefineCommand
{
  private String schemaName;
  private boolean ifExists;
  
  public DropSchema(Session session)
  {
    super(session);
  }
  
  public void setSchemaName(String name)
  {
    this.schemaName = name;
  }
  
  public int update()
  {
    this.session.getUser().checkSchemaAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    Schema schema = db.findSchema(this.schemaName);
    if (schema == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90079, this.schemaName);
      }
    }
    else
    {
      if (!schema.canDrop()) {
        throw DbException.get(90090, this.schemaName);
      }
      db.removeDatabaseObject(this.session, schema);
    }
    return 0;
  }
  
  public void setIfExists(boolean ifExists)
  {
    this.ifExists = ifExists;
  }
  
  public int getType()
  {
    return 42;
  }
}
