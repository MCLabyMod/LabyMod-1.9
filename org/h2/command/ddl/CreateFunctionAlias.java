package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.FunctionAlias;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.util.StringUtils;

public class CreateFunctionAlias
  extends SchemaCommand
{
  private String aliasName;
  private String javaClassMethod;
  private boolean deterministic;
  private boolean ifNotExists;
  private boolean force;
  private String source;
  private boolean bufferResultSetToLocalTemp = true;
  
  public CreateFunctionAlias(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public int update()
  {
    this.session.commit(true);
    this.session.getUser().checkAdmin();
    Database db = this.session.getDatabase();
    if (getSchema().findFunction(this.aliasName) != null)
    {
      if (!this.ifNotExists) {
        throw DbException.get(90076, this.aliasName);
      }
    }
    else
    {
      int id = getObjectId();
      FunctionAlias functionAlias;
      FunctionAlias functionAlias;
      if (this.javaClassMethod != null) {
        functionAlias = FunctionAlias.newInstance(getSchema(), id, this.aliasName, this.javaClassMethod, this.force, this.bufferResultSetToLocalTemp);
      } else {
        functionAlias = FunctionAlias.newInstanceFromSource(getSchema(), id, this.aliasName, this.source, this.force, this.bufferResultSetToLocalTemp);
      }
      functionAlias.setDeterministic(this.deterministic);
      db.addSchemaObject(this.session, functionAlias);
    }
    return 0;
  }
  
  public void setAliasName(String name)
  {
    this.aliasName = name;
  }
  
  public void setJavaClassMethod(String method)
  {
    this.javaClassMethod = StringUtils.replaceAll(method, " ", "");
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setForce(boolean force)
  {
    this.force = force;
  }
  
  public void setDeterministic(boolean deterministic)
  {
    this.deterministic = deterministic;
  }
  
  public void setBufferResultSetToLocalTemp(boolean b)
  {
    this.bufferResultSetToLocalTemp = b;
  }
  
  public void setSource(String source)
  {
    this.source = source;
  }
  
  public int getType()
  {
    return 24;
  }
}
