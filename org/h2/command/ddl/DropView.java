package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Table;
import org.h2.table.TableView;

public class DropView
  extends SchemaCommand
{
  private String viewName;
  private boolean ifExists;
  private int dropAction;
  
  public DropView(Session session, Schema schema)
  {
    super(session, schema);
    this.dropAction = (session.getDatabase().getSettings().dropRestrict ? 0 : 1);
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
  }
  
  public void setDropAction(int dropAction)
  {
    this.dropAction = dropAction;
  }
  
  public void setViewName(String viewName)
  {
    this.viewName = viewName;
  }
  
  public int update()
  {
    this.session.commit(true);
    Table view = getSchema().findTableOrView(this.session, this.viewName);
    if (view == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90037, this.viewName);
      }
    }
    else
    {
      if (!"VIEW".equals(view.getTableType())) {
        throw DbException.get(90037, this.viewName);
      }
      this.session.getUser().checkRight(view, 15);
      if (this.dropAction == 0) {
        for (DbObject child : view.getChildren()) {
          if ((child instanceof TableView)) {
            throw DbException.get(90107, new String[] { this.viewName, child.getName() });
          }
        }
      }
      view.lock(this.session, true, true);
      this.session.getDatabase().removeSchemaObject(this.session, view);
    }
    return 0;
  }
  
  public int getType()
  {
    return 48;
  }
}
