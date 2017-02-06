package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Constant;
import org.h2.schema.Schema;

public class DropConstant
  extends SchemaCommand
{
  private String constantName;
  private boolean ifExists;
  
  public DropConstant(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
  }
  
  public void setConstantName(String constantName)
  {
    this.constantName = constantName;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    Constant constant = getSchema().findConstant(this.constantName);
    if (constant == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90115, this.constantName);
      }
    }
    else {
      db.removeSchemaObject(this.session, constant);
    }
    return 0;
  }
  
  public int getType()
  {
    return 37;
  }
}
