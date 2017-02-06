package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.TriggerObject;
import org.h2.table.Table;

public class CreateTrigger
  extends SchemaCommand
{
  private String triggerName;
  private boolean ifNotExists;
  private boolean insteadOf;
  private boolean before;
  private int typeMask;
  private boolean rowBased;
  private int queueSize = 1024;
  private boolean noWait;
  private String tableName;
  private String triggerClassName;
  private String triggerSource;
  private boolean force;
  private boolean onRollback;
  
  public CreateTrigger(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setInsteadOf(boolean insteadOf)
  {
    this.insteadOf = insteadOf;
  }
  
  public void setBefore(boolean before)
  {
    this.before = before;
  }
  
  public void setTriggerClassName(String triggerClassName)
  {
    this.triggerClassName = triggerClassName;
  }
  
  public void setTriggerSource(String triggerSource)
  {
    this.triggerSource = triggerSource;
  }
  
  public void setTypeMask(int typeMask)
  {
    this.typeMask = typeMask;
  }
  
  public void setRowBased(boolean rowBased)
  {
    this.rowBased = rowBased;
  }
  
  public void setQueueSize(int size)
  {
    this.queueSize = size;
  }
  
  public void setNoWait(boolean noWait)
  {
    this.noWait = noWait;
  }
  
  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }
  
  public void setTriggerName(String name)
  {
    this.triggerName = name;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (getSchema().findTrigger(this.triggerName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90041, this.triggerName);
    }
    if (((this.typeMask & 0x8) == 8) && (this.rowBased)) {
      throw DbException.get(90005, this.triggerName);
    }
    int id = getObjectId();
    Table table = getSchema().getTableOrView(this.session, this.tableName);
    TriggerObject trigger = new TriggerObject(getSchema(), id, this.triggerName, table);
    trigger.setInsteadOf(this.insteadOf);
    trigger.setBefore(this.before);
    trigger.setNoWait(this.noWait);
    trigger.setQueueSize(this.queueSize);
    trigger.setRowBased(this.rowBased);
    trigger.setTypeMask(this.typeMask);
    trigger.setOnRollback(this.onRollback);
    if (this.triggerClassName != null) {
      trigger.setTriggerClassName(this.triggerClassName, this.force);
    } else {
      trigger.setTriggerSource(this.triggerSource, this.force);
    }
    db.addSchemaObject(this.session, trigger);
    table.addTrigger(trigger);
    return 0;
  }
  
  public void setForce(boolean force)
  {
    this.force = force;
  }
  
  public void setOnRollback(boolean onRollback)
  {
    this.onRollback = onRollback;
  }
  
  public int getType()
  {
    return 31;
  }
}
