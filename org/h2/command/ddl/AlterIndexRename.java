package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.schema.Schema;

public class AlterIndexRename
  extends DefineCommand
{
  private Index oldIndex;
  private String newIndexName;
  
  public AlterIndexRename(Session session)
  {
    super(session);
  }
  
  public void setOldIndex(Index index)
  {
    this.oldIndex = index;
  }
  
  public void setNewName(String name)
  {
    this.newIndexName = name;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    Schema schema = this.oldIndex.getSchema();
    if ((schema.findIndex(this.session, this.newIndexName) != null) || (this.newIndexName.equals(this.oldIndex.getName()))) {
      throw DbException.get(42111, this.newIndexName);
    }
    this.session.getUser().checkRight(this.oldIndex.getTable(), 15);
    db.renameSchemaObject(this.session, this.oldIndex, this.newIndexName);
    return 0;
  }
  
  public int getType()
  {
    return 1;
  }
}
