package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.command.dml.Query;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Parameter;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Table;
import org.h2.table.TableView;

public class CreateView
  extends SchemaCommand
{
  private Query select;
  private String viewName;
  private boolean ifNotExists;
  private String selectSQL;
  private String[] columnNames;
  private String comment;
  private boolean orReplace;
  private boolean force;
  
  public CreateView(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setViewName(String name)
  {
    this.viewName = name;
  }
  
  public void setSelect(Query select)
  {
    this.select = select;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setSelectSQL(String selectSQL)
  {
    this.selectSQL = selectSQL;
  }
  
  public void setColumnNames(String[] cols)
  {
    this.columnNames = cols;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public void setOrReplace(boolean orReplace)
  {
    this.orReplace = orReplace;
  }
  
  public void setForce(boolean force)
  {
    this.force = force;
  }
  
  public int update()
  {
    this.session.commit(true);
    this.session.getUser().checkAdmin();
    Database db = this.session.getDatabase();
    TableView view = null;
    Table old = getSchema().findTableOrView(this.session, this.viewName);
    if (old != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      if ((!this.orReplace) || (!"VIEW".equals(old.getTableType()))) {
        throw DbException.get(90038, this.viewName);
      }
      view = (TableView)old;
    }
    int id = getObjectId();
    String querySQL;
    String querySQL;
    if (this.select == null)
    {
      querySQL = this.selectSQL;
    }
    else
    {
      ArrayList<Parameter> params = this.select.getParameters();
      if ((params != null) && (params.size() > 0)) {
        throw DbException.getUnsupportedException("parameters in views");
      }
      querySQL = this.select.getPlanSQL();
    }
    Session sysSession = db.getSystemSession();
    try
    {
      if (view == null)
      {
        Schema schema = this.session.getDatabase().getSchema(this.session.getCurrentSchemaName());
        sysSession.setCurrentSchema(schema);
        view = new TableView(getSchema(), id, this.viewName, querySQL, null, this.columnNames, sysSession, false);
      }
      else
      {
        view.replace(querySQL, this.columnNames, sysSession, false, this.force);
        view.setModified();
      }
    }
    finally
    {
      sysSession.setCurrentSchema(db.getSchema("PUBLIC"));
    }
    if (this.comment != null) {
      view.setComment(this.comment);
    }
    if (old == null) {
      db.addSchemaObject(this.session, view);
    } else {
      db.updateMeta(this.session, view);
    }
    return 0;
  }
  
  public int getType()
  {
    return 34;
  }
}
