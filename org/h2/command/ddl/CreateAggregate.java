package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.engine.UserAggregate;
import org.h2.message.DbException;
import org.h2.schema.Schema;

public class CreateAggregate
  extends DefineCommand
{
  private Schema schema;
  private String name;
  private String javaClassMethod;
  private boolean ifNotExists;
  private boolean force;
  
  public CreateAggregate(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    this.session.commit(true);
    this.session.getUser().checkAdmin();
    Database db = this.session.getDatabase();
    if ((db.findAggregate(this.name) != null) || (this.schema.findFunction(this.name) != null))
    {
      if (!this.ifNotExists) {
        throw DbException.get(90076, this.name);
      }
    }
    else
    {
      int id = getObjectId();
      UserAggregate aggregate = new UserAggregate(db, id, this.name, this.javaClassMethod, this.force);
      
      db.addDatabaseObject(this.session, aggregate);
    }
    return 0;
  }
  
  public void setSchema(Schema schema)
  {
    this.schema = schema;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setJavaClassMethod(String string)
  {
    this.javaClassMethod = string;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setForce(boolean force)
  {
    this.force = force;
  }
  
  public int getType()
  {
    return 22;
  }
}
