package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.FunctionAlias;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;

public class DropFunctionAlias
  extends SchemaCommand
{
  private String aliasName;
  private boolean ifExists;
  
  public DropFunctionAlias(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    FunctionAlias functionAlias = getSchema().findFunction(this.aliasName);
    if (functionAlias == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90077, this.aliasName);
      }
    }
    else {
      db.removeSchemaObject(this.session, functionAlias);
    }
    return 0;
  }
  
  public void setAliasName(String name)
  {
    this.aliasName = name;
  }
  
  public void setIfExists(boolean ifExists)
  {
    this.ifExists = ifExists;
  }
  
  public int getType()
  {
    return 39;
  }
}
