package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Table;
import org.h2.table.TableView;
import org.h2.util.StatementBuilder;

public class DropTable
  extends SchemaCommand
{
  private boolean ifExists;
  private String tableName;
  private Table table;
  private DropTable next;
  private int dropAction;
  
  public DropTable(Session session, Schema schema)
  {
    super(session, schema);
    this.dropAction = (session.getDatabase().getSettings().dropRestrict ? 0 : 1);
  }
  
  public void addNextDropTable(DropTable drop)
  {
    if (this.next == null) {
      this.next = drop;
    } else {
      this.next.addNextDropTable(drop);
    }
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
    if (this.next != null) {
      this.next.setIfExists(b);
    }
  }
  
  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }
  
  private void prepareDrop()
  {
    this.table = getSchema().findTableOrView(this.session, this.tableName);
    if (this.table == null)
    {
      if (!this.ifExists) {
        throw DbException.get(42102, this.tableName);
      }
    }
    else
    {
      this.session.getUser().checkRight(this.table, 15);
      if (!this.table.canDrop()) {
        throw DbException.get(90118, this.tableName);
      }
      if (this.dropAction == 0)
      {
        ArrayList<TableView> views = this.table.getViews();
        if ((views != null) && (views.size() > 0))
        {
          StatementBuilder buff = new StatementBuilder();
          for (TableView v : views)
          {
            buff.appendExceptFirst(", ");
            buff.append(v.getName());
          }
          throw DbException.get(90107, new String[] { this.tableName, buff.toString() });
        }
      }
      this.table.lock(this.session, true, true);
    }
    if (this.next != null) {
      this.next.prepareDrop();
    }
  }
  
  private void executeDrop()
  {
    this.table = getSchema().findTableOrView(this.session, this.tableName);
    if (this.table != null)
    {
      this.table.setModified();
      Database db = this.session.getDatabase();
      db.lockMeta(this.session);
      db.removeSchemaObject(this.session, this.table);
    }
    if (this.next != null) {
      this.next.executeDrop();
    }
  }
  
  public int update()
  {
    this.session.commit(true);
    prepareDrop();
    executeDrop();
    return 0;
  }
  
  public void setDropAction(int dropAction)
  {
    this.dropAction = dropAction;
    if (this.next != null) {
      this.next.setDropAction(dropAction);
    }
  }
  
  public int getType()
  {
    return 44;
  }
}
