package org.h2.engine;

import java.util.ArrayList;
import org.h2.command.Parser;
import org.h2.message.Trace;

public abstract class DbObjectBase
  implements DbObject
{
  protected Database database;
  protected Trace trace;
  protected String comment;
  private int id;
  private String objectName;
  private long modificationId;
  private boolean temporary;
  
  protected void initDbObjectBase(Database db, int objectId, String name, String traceModule)
  {
    this.database = db;
    this.trace = db.getTrace(traceModule);
    this.id = objectId;
    this.objectName = name;
    this.modificationId = db.getModificationMetaId();
  }
  
  public abstract String getCreateSQL();
  
  public abstract String getDropSQL();
  
  public abstract void removeChildrenAndResources(Session paramSession);
  
  public abstract void checkRename();
  
  public void setModified()
  {
    this.modificationId = (this.database == null ? -1L : this.database.getNextModificationMetaId());
  }
  
  public long getModificationId()
  {
    return this.modificationId;
  }
  
  protected void setObjectName(String name)
  {
    this.objectName = name;
  }
  
  public String getSQL()
  {
    return Parser.quoteIdentifier(this.objectName);
  }
  
  public ArrayList<DbObject> getChildren()
  {
    return null;
  }
  
  public Database getDatabase()
  {
    return this.database;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.objectName;
  }
  
  protected void invalidate()
  {
    setModified();
    this.id = -1;
    this.database = null;
    this.trace = null;
    this.objectName = null;
  }
  
  public void rename(String newName)
  {
    checkRename();
    this.objectName = newName;
    setModified();
  }
  
  public boolean isTemporary()
  {
    return this.temporary;
  }
  
  public void setTemporary(boolean temporary)
  {
    this.temporary = temporary;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public String toString()
  {
    return this.objectName + ":" + this.id + ":" + super.toString();
  }
}
