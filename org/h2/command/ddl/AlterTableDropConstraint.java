package org.h2.command.ddl;

import org.h2.constraint.Constraint;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;

public class AlterTableDropConstraint
  extends SchemaCommand
{
  private String constraintName;
  private final boolean ifExists;
  
  public AlterTableDropConstraint(Session session, Schema schema, boolean ifExists)
  {
    super(session, schema);
    this.ifExists = ifExists;
  }
  
  public void setConstraintName(String string)
  {
    this.constraintName = string;
  }
  
  public int update()
  {
    this.session.commit(true);
    Constraint constraint = getSchema().findConstraint(this.session, this.constraintName);
    if (constraint == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90057, this.constraintName);
      }
    }
    else
    {
      this.session.getUser().checkRight(constraint.getTable(), 15);
      this.session.getUser().checkRight(constraint.getRefTable(), 15);
      this.session.getDatabase().removeSchemaObject(this.session, constraint);
    }
    return 0;
  }
  
  public int getType()
  {
    return 14;
  }
}
