package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Role;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.table.Table;
import org.h2.util.New;

public class DropDatabase
  extends DefineCommand
{
  private boolean dropAllObjects;
  private boolean deleteFiles;
  
  public DropDatabase(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    if (this.dropAllObjects) {
      dropAllObjects();
    }
    if (this.deleteFiles) {
      this.session.getDatabase().setDeleteFilesOnDisconnect(true);
    }
    return 0;
  }
  
  private void dropAllObjects()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    db.lockMeta(this.session);
    boolean runLoopAgain;
    do
    {
      ArrayList<Table> tables = db.getAllTablesAndViews(false);
      ArrayList<Table> toRemove = New.arrayList();
      for (Table t : tables) {
        if ((t.getName() != null) && ("VIEW".equals(t.getTableType()))) {
          toRemove.add(t);
        }
      }
      for (Table t : tables) {
        if ((t.getName() != null) && ("TABLE LINK".equals(t.getTableType()))) {
          toRemove.add(t);
        }
      }
      for (Table t : tables) {
        if ((t.getName() != null) && ("TABLE".equals(t.getTableType())) && (!t.isHidden())) {
          toRemove.add(t);
        }
      }
      for (Table t : tables) {
        if ((t.getName() != null) && ("EXTERNAL".equals(t.getTableType())) && (!t.isHidden())) {
          toRemove.add(t);
        }
      }
      runLoopAgain = false;
      for (Table t : toRemove) {
        if (t.getName() != null) {
          if (db.getDependentTable(t, t) == null) {
            db.removeSchemaObject(this.session, t);
          } else {
            runLoopAgain = true;
          }
        }
      }
    } while (runLoopAgain);
    for (Schema schema : db.getAllSchemas()) {
      if (schema.canDrop()) {
        db.removeDatabaseObject(this.session, schema);
      }
    }
    this.session.findLocalTempTable(null);
    ArrayList<SchemaObject> list = New.arrayList();
    list.addAll(db.getAllSchemaObjects(3));
    
    list.addAll(db.getAllSchemaObjects(5));
    list.addAll(db.getAllSchemaObjects(4));
    list.addAll(db.getAllSchemaObjects(11));
    list.addAll(db.getAllSchemaObjects(9));
    for (SchemaObject obj : list) {
      if (!obj.isHidden()) {
        db.removeSchemaObject(this.session, obj);
      }
    }
    for (User user : db.getAllUsers()) {
      if (user != this.session.getUser()) {
        db.removeDatabaseObject(this.session, user);
      }
    }
    for (Role role : db.getAllRoles())
    {
      String sql = role.getCreateSQL();
      if (sql != null) {
        db.removeDatabaseObject(this.session, role);
      }
    }
    ArrayList<DbObject> dbObjects = New.arrayList();
    dbObjects.addAll(db.getAllRights());
    dbObjects.addAll(db.getAllAggregates());
    dbObjects.addAll(db.getAllUserDataTypes());
    for (DbObject obj : dbObjects)
    {
      String sql = obj.getCreateSQL();
      if (sql != null) {
        db.removeDatabaseObject(this.session, obj);
      }
    }
  }
  
  public void setDropAllObjects(boolean b)
  {
    this.dropAllObjects = b;
  }
  
  public void setDeleteFiles(boolean b)
  {
    this.deleteFiles = b;
  }
  
  public int getType()
  {
    return 38;
  }
}
