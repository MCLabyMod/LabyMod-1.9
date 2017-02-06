package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;

public class AlterSchemaRename
  extends DefineCommand
{
  private Schema oldSchema;
  private String newSchemaName;
  
  public AlterSchemaRename(Session session)
  {
    super(session);
  }
  
  public void setOldSchema(Schema schema)
  {
    this.oldSchema = schema;
  }
  
  public void setNewName(String name)
  {
    this.newSchemaName = name;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (!this.oldSchema.canDrop()) {
      throw DbException.get(90090, this.oldSchema.getName());
    }
    if ((db.findSchema(this.newSchemaName) != null) || (this.newSchemaName.equals(this.oldSchema.getName()))) {
      throw DbException.get(90078, this.newSchemaName);
    }
    this.session.getUser().checkSchemaAdmin();
    db.renameDatabaseObject(this.session, this.oldSchema, this.newSchemaName);
    ArrayList<SchemaObject> all = db.getAllSchemaObjects();
    for (SchemaObject schemaObject : all) {
      db.updateMeta(this.session, schemaObject);
    }
    return 0;
  }
  
  public int getType()
  {
    return 2;
  }
}
