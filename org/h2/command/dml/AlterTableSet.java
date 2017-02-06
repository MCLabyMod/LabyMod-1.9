package org.h2.command.dml;

import org.h2.command.ddl.SchemaCommand;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Table;

public class AlterTableSet
  extends SchemaCommand
{
  private String tableName;
  private final int type;
  private final boolean value;
  private boolean checkExisting;
  
  public AlterTableSet(Session session, Schema schema, int type, boolean value)
  {
    super(session, schema);
    this.type = type;
    this.value = value;
  }
  
  public void setCheckExisting(boolean b)
  {
    this.checkExisting = b;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }
  
  public int update()
  {
    Table table = getSchema().getTableOrView(this.session, this.tableName);
    this.session.getUser().checkRight(table, 15);
    table.lock(this.session, true, true);
    switch (this.type)
    {
    case 55: 
      table.setCheckForeignKeyConstraints(this.session, this.value, this.value ? this.checkExisting : false);
      
      break;
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    return 0;
  }
  
  public int getType()
  {
    return this.type;
  }
}
