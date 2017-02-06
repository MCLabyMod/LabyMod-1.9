package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.table.Column;
import org.h2.table.Table;

public class AlterTableRenameColumn
  extends DefineCommand
{
  private Table table;
  private Column column;
  private String newName;
  
  public AlterTableRenameColumn(Session session)
  {
    super(session);
  }
  
  public void setTable(Table table)
  {
    this.table = table;
  }
  
  public void setColumn(Column column)
  {
    this.column = column;
  }
  
  public void setNewColumnName(String newName)
  {
    this.newName = newName;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    this.session.getUser().checkRight(this.table, 15);
    this.table.checkSupportAlter();
    
    Expression newCheckExpr = this.column.getCheckConstraint(this.session, this.newName);
    this.table.renameColumn(this.column, this.newName);
    this.column.removeCheckConstraint();
    this.column.addCheckConstraint(this.session, newCheckExpr);
    this.table.setModified();
    db.updateMeta(this.session, this.table);
    for (DbObject child : this.table.getChildren()) {
      if (child.getCreateSQL() != null) {
        db.updateMeta(this.session, child);
      }
    }
    return 0;
  }
  
  public int getType()
  {
    return 16;
  }
}
