package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Table;

public class AlterTableRename
  extends SchemaCommand
{
  private Table oldTable;
  private String newTableName;
  private boolean hidden;
  
  public AlterTableRename(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setOldTable(Table table)
  {
    this.oldTable = table;
  }
  
  public void setNewTableName(String name)
  {
    this.newTableName = name;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    this.session.getUser().checkRight(this.oldTable, 15);
    Table t = getSchema().findTableOrView(this.session, this.newTableName);
    if ((t != null) && (this.hidden) && (this.newTableName.equals(this.oldTable.getName())))
    {
      if (!t.isHidden())
      {
        t.setHidden(this.hidden);
        this.oldTable.setHidden(true);
        db.updateMeta(this.session, this.oldTable);
      }
      return 0;
    }
    if ((t != null) || (this.newTableName.equals(this.oldTable.getName()))) {
      throw DbException.get(42101, this.newTableName);
    }
    if (this.oldTable.isTemporary()) {
      throw DbException.getUnsupportedException("temp table");
    }
    db.renameSchemaObject(this.session, this.oldTable, this.newTableName);
    return 0;
  }
  
  public int getType()
  {
    return 15;
  }
  
  public void setHidden(boolean hidden)
  {
    this.hidden = hidden;
  }
}
