package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.constraint.Constraint;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Table;

public class DropIndex
  extends SchemaCommand
{
  private String indexName;
  private boolean ifExists;
  
  public DropIndex(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
  }
  
  public void setIndexName(String indexName)
  {
    this.indexName = indexName;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    Index index = getSchema().findIndex(this.session, this.indexName);
    if (index == null)
    {
      if (!this.ifExists) {
        throw DbException.get(42112, this.indexName);
      }
    }
    else
    {
      Table table = index.getTable();
      this.session.getUser().checkRight(index.getTable(), 15);
      Constraint pkConstraint = null;
      ArrayList<Constraint> constraints = table.getConstraints();
      for (int i = 0; (constraints != null) && (i < constraints.size()); i++)
      {
        Constraint cons = (Constraint)constraints.get(i);
        if (cons.usesIndex(index)) {
          if ("PRIMARY KEY".equals(cons.getConstraintType())) {
            pkConstraint = cons;
          } else {
            throw DbException.get(90085, new String[] { this.indexName, cons.getName() });
          }
        }
      }
      index.getTable().setModified();
      if (pkConstraint != null) {
        db.removeSchemaObject(this.session, pkConstraint);
      } else {
        db.removeSchemaObject(this.session, index);
      }
    }
    return 0;
  }
  
  public int getType()
  {
    return 40;
  }
}
