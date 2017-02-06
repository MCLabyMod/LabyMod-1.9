package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.IndexColumn;
import org.h2.table.Table;

public class CreateIndex
  extends SchemaCommand
{
  private String tableName;
  private String indexName;
  private IndexColumn[] indexColumns;
  private boolean primaryKey;
  private boolean unique;
  private boolean hash;
  private boolean spatial;
  private boolean ifNotExists;
  private String comment;
  
  public CreateIndex(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }
  
  public void setIndexName(String indexName)
  {
    this.indexName = indexName;
  }
  
  public void setIndexColumns(IndexColumn[] columns)
  {
    this.indexColumns = columns;
  }
  
  public int update()
  {
    if (!this.transactional) {
      this.session.commit(true);
    }
    Database db = this.session.getDatabase();
    boolean persistent = db.isPersistent();
    Table table = getSchema().getTableOrView(this.session, this.tableName);
    if (getSchema().findIndex(this.session, this.indexName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(42111, this.indexName);
    }
    this.session.getUser().checkRight(table, 15);
    table.lock(this.session, true, true);
    if (!table.isPersistIndexes()) {
      persistent = false;
    }
    int id = getObjectId();
    if (this.indexName == null) {
      if (this.primaryKey) {
        this.indexName = table.getSchema().getUniqueIndexName(this.session, table, "PRIMARY_KEY_");
      } else {
        this.indexName = table.getSchema().getUniqueIndexName(this.session, table, "INDEX_");
      }
    }
    IndexType indexType;
    IndexType indexType;
    if (this.primaryKey)
    {
      if (table.findPrimaryKey() != null) {
        throw DbException.get(90017);
      }
      indexType = IndexType.createPrimaryKey(persistent, this.hash);
    }
    else
    {
      IndexType indexType;
      if (this.unique) {
        indexType = IndexType.createUnique(persistent, this.hash);
      } else {
        indexType = IndexType.createNonUnique(persistent, this.hash, this.spatial);
      }
    }
    IndexColumn.mapColumns(this.indexColumns, table);
    table.addIndex(this.session, this.indexName, id, this.indexColumns, indexType, this.create, this.comment);
    
    return 0;
  }
  
  public void setPrimaryKey(boolean b)
  {
    this.primaryKey = b;
  }
  
  public void setUnique(boolean b)
  {
    this.unique = b;
  }
  
  public void setHash(boolean b)
  {
    this.hash = b;
  }
  
  public void setSpatial(boolean b)
  {
    this.spatial = b;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public int getType()
  {
    return 25;
  }
}
