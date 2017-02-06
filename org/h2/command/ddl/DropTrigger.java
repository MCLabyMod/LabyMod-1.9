package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.TriggerObject;
import org.h2.table.Table;

public class DropTrigger
  extends SchemaCommand
{
  private String triggerName;
  private boolean ifExists;
  
  public DropTrigger(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
  }
  
  public void setTriggerName(String triggerName)
  {
    this.triggerName = triggerName;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    TriggerObject trigger = getSchema().findTrigger(this.triggerName);
    if (trigger == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90042, this.triggerName);
      }
    }
    else
    {
      Table table = trigger.getTable();
      this.session.getUser().checkRight(table, 15);
      db.removeSchemaObject(this.session, trigger);
    }
    return 0;
  }
  
  public int getType()
  {
    return 45;
  }
}
