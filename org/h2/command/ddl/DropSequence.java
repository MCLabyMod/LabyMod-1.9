package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;

public class DropSequence
  extends SchemaCommand
{
  private String sequenceName;
  private boolean ifExists;
  
  public DropSequence(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
  }
  
  public void setSequenceName(String sequenceName)
  {
    this.sequenceName = sequenceName;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    Sequence sequence = getSchema().findSequence(this.sequenceName);
    if (sequence == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90036, this.sequenceName);
      }
    }
    else
    {
      if (sequence.getBelongsToTable()) {
        throw DbException.get(90082, this.sequenceName);
      }
      db.removeSchemaObject(this.session, sequence);
    }
    return 0;
  }
  
  public int getType()
  {
    return 43;
  }
}
