package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.TableLink;

public class CreateLinkedTable
  extends SchemaCommand
{
  private String tableName;
  private String driver;
  private String url;
  private String user;
  private String password;
  private String originalSchema;
  private String originalTable;
  private boolean ifNotExists;
  private String comment;
  private boolean emitUpdates;
  private boolean force;
  private boolean temporary;
  private boolean globalTemporary;
  private boolean readOnly;
  
  public CreateLinkedTable(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }
  
  public void setDriver(String driver)
  {
    this.driver = driver;
  }
  
  public void setOriginalTable(String originalTable)
  {
    this.originalTable = originalTable;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public void setUser(String user)
  {
    this.user = user;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    this.session.getUser().checkAdmin();
    if (getSchema().findTableOrView(this.session, this.tableName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(42101, this.tableName);
    }
    int id = getObjectId();
    TableLink table = getSchema().createTableLink(id, this.tableName, this.driver, this.url, this.user, this.password, this.originalSchema, this.originalTable, this.emitUpdates, this.force);
    
    table.setTemporary(this.temporary);
    table.setGlobalTemporary(this.globalTemporary);
    table.setComment(this.comment);
    table.setReadOnly(this.readOnly);
    if ((this.temporary) && (!this.globalTemporary)) {
      this.session.addLocalTempTable(table);
    } else {
      db.addSchemaObject(this.session, table);
    }
    return 0;
  }
  
  public void setEmitUpdates(boolean emitUpdates)
  {
    this.emitUpdates = emitUpdates;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public void setForce(boolean force)
  {
    this.force = force;
  }
  
  public void setTemporary(boolean temp)
  {
    this.temporary = temp;
  }
  
  public void setGlobalTemporary(boolean globalTemp)
  {
    this.globalTemporary = globalTemp;
  }
  
  public void setReadOnly(boolean readOnly)
  {
    this.readOnly = readOnly;
  }
  
  public void setOriginalSchema(String originalSchema)
  {
    this.originalSchema = originalSchema;
  }
  
  public int getType()
  {
    return 26;
  }
}
