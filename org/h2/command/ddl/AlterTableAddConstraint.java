package org.h2.command.ddl;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.constraint.Constraint;
import org.h2.constraint.ConstraintCheck;
import org.h2.constraint.ConstraintReferential;
import org.h2.constraint.ConstraintUnique;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;

public class AlterTableAddConstraint
  extends SchemaCommand
{
  private int type;
  private String constraintName;
  private String tableName;
  private IndexColumn[] indexColumns;
  private int deleteAction;
  private int updateAction;
  private Schema refSchema;
  private String refTableName;
  private IndexColumn[] refIndexColumns;
  private Expression checkExpression;
  private Index index;
  private Index refIndex;
  private String comment;
  private boolean checkExisting;
  private boolean primaryKeyHash;
  private final boolean ifNotExists;
  private ArrayList<Index> createdIndexes = New.arrayList();
  
  public AlterTableAddConstraint(Session session, Schema schema, boolean ifNotExists)
  {
    super(session, schema);
    this.ifNotExists = ifNotExists;
  }
  
  private String generateConstraintName(Table table)
  {
    if (this.constraintName == null) {
      this.constraintName = getSchema().getUniqueConstraintName(this.session, table);
    }
    return this.constraintName;
  }
  
  public int update()
  {
    try
    {
      return tryUpdate();
    }
    catch (DbException e)
    {
      for (Index index : this.createdIndexes) {
        this.session.getDatabase().removeSchemaObject(this.session, index);
      }
      throw e;
    }
    finally
    {
      getSchema().freeUniqueName(this.constraintName);
    }
  }
  
  private int tryUpdate()
  {
    if (!this.transactional) {
      this.session.commit(true);
    }
    Database db = this.session.getDatabase();
    Table table = getSchema().getTableOrView(this.session, this.tableName);
    if (getSchema().findConstraint(this.session, this.constraintName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90045, this.constraintName);
    }
    this.session.getUser().checkRight(table, 15);
    db.lockMeta(this.session);
    table.lock(this.session, true, true);
    Constraint constraint;
    switch (this.type)
    {
    case 6: 
      IndexColumn.mapColumns(this.indexColumns, table);
      this.index = table.findPrimaryKey();
      ArrayList<Constraint> constraints = table.getConstraints();
      for (int i = 0; (constraints != null) && (i < constraints.size()); i++)
      {
        Constraint c = (Constraint)constraints.get(i);
        if ("PRIMARY KEY".equals(c.getConstraintType())) {
          throw DbException.get(90017);
        }
      }
      if (this.index != null)
      {
        IndexColumn[] pkCols = this.index.getIndexColumns();
        if (pkCols.length != this.indexColumns.length) {
          throw DbException.get(90017);
        }
        for (int i = 0; i < pkCols.length; i++) {
          if (pkCols[i].column != this.indexColumns[i].column) {
            throw DbException.get(90017);
          }
        }
      }
      if (this.index == null)
      {
        IndexType indexType = IndexType.createPrimaryKey(table.isPersistIndexes(), this.primaryKeyHash);
        
        String indexName = table.getSchema().getUniqueIndexName(this.session, table, "PRIMARY_KEY_");
        
        int id = getObjectId();
        try
        {
          this.index = table.addIndex(this.session, indexName, id, this.indexColumns, indexType, true, null);
        }
        finally
        {
          getSchema().freeUniqueName(indexName);
        }
      }
      this.index.getIndexType().setBelongsToConstraint(true);
      int constraintId = getObjectId();
      String name = generateConstraintName(table);
      ConstraintUnique pk = new ConstraintUnique(getSchema(), constraintId, name, table, true);
      
      pk.setColumns(this.indexColumns);
      pk.setIndex(this.index, true);
      constraint = pk;
      break;
    case 4: 
      IndexColumn.mapColumns(this.indexColumns, table);
      boolean isOwner = false;
      if ((this.index != null) && (canUseUniqueIndex(this.index, table, this.indexColumns)))
      {
        isOwner = true;
        this.index.getIndexType().setBelongsToConstraint(true);
      }
      else
      {
        this.index = getUniqueIndex(table, this.indexColumns);
        if (this.index == null)
        {
          this.index = createIndex(table, this.indexColumns, true);
          isOwner = true;
        }
      }
      int id = getObjectId();
      String name = generateConstraintName(table);
      ConstraintUnique unique = new ConstraintUnique(getSchema(), id, name, table, false);
      
      unique.setColumns(this.indexColumns);
      unique.setIndex(this.index, isOwner);
      constraint = unique;
      break;
    case 3: 
      int id = getObjectId();
      String name = generateConstraintName(table);
      ConstraintCheck check = new ConstraintCheck(getSchema(), id, name, table);
      TableFilter filter = new TableFilter(this.session, table, null, false, null);
      this.checkExpression.mapColumns(filter, 0);
      this.checkExpression = this.checkExpression.optimize(this.session);
      check.setExpression(this.checkExpression);
      check.setTableFilter(filter);
      constraint = check;
      if (this.checkExisting) {
        check.checkExistingData(this.session);
      }
      break;
    case 5: 
      Table refTable = this.refSchema.getTableOrView(this.session, this.refTableName);
      this.session.getUser().checkRight(refTable, 15);
      if (!refTable.canReference()) {
        throw DbException.getUnsupportedException("Reference " + refTable.getSQL());
      }
      boolean isOwner = false;
      IndexColumn.mapColumns(this.indexColumns, table);
      if ((this.index != null) && (canUseIndex(this.index, table, this.indexColumns, false)))
      {
        isOwner = true;
        this.index.getIndexType().setBelongsToConstraint(true);
      }
      else
      {
        if (db.isStarting()) {
          this.index = getIndex(table, this.indexColumns, true);
        } else {
          this.index = getIndex(table, this.indexColumns, false);
        }
        if (this.index == null)
        {
          this.index = createIndex(table, this.indexColumns, false);
          isOwner = true;
        }
      }
      if (this.refIndexColumns == null)
      {
        Index refIdx = refTable.getPrimaryKey();
        this.refIndexColumns = refIdx.getIndexColumns();
      }
      else
      {
        IndexColumn.mapColumns(this.refIndexColumns, refTable);
      }
      if (this.refIndexColumns.length != this.indexColumns.length) {
        throw DbException.get(21002);
      }
      boolean isRefOwner = false;
      if ((this.refIndex != null) && (this.refIndex.getTable() == refTable) && (canUseIndex(this.refIndex, refTable, this.refIndexColumns, false)))
      {
        isRefOwner = true;
        this.refIndex.getIndexType().setBelongsToConstraint(true);
      }
      else
      {
        this.refIndex = null;
      }
      if (this.refIndex == null)
      {
        this.refIndex = getIndex(refTable, this.refIndexColumns, false);
        if (this.refIndex == null)
        {
          this.refIndex = createIndex(refTable, this.refIndexColumns, true);
          isRefOwner = true;
        }
      }
      int id = getObjectId();
      String name = generateConstraintName(table);
      ConstraintReferential ref = new ConstraintReferential(getSchema(), id, name, table);
      
      ref.setColumns(this.indexColumns);
      ref.setIndex(this.index, isOwner);
      ref.setRefTable(refTable);
      ref.setRefColumns(this.refIndexColumns);
      ref.setRefIndex(this.refIndex, isRefOwner);
      if (this.checkExisting) {
        ref.checkExistingData(this.session);
      }
      constraint = ref;
      refTable.addConstraint(constraint);
      ref.setDeleteAction(this.deleteAction);
      ref.setUpdateAction(this.updateAction);
      break;
    default: 
      throw DbException.throwInternalError("type=" + this.type);
    }
    constraint.setComment(this.comment);
    if ((table.isTemporary()) && (!table.isGlobalTemporary())) {
      this.session.addLocalTempTableConstraint(constraint);
    } else {
      db.addSchemaObject(this.session, constraint);
    }
    table.addConstraint(constraint);
    return 0;
  }
  
  private Index createIndex(Table t, IndexColumn[] cols, boolean unique)
  {
    int indexId = getObjectId();
    IndexType indexType;
    IndexType indexType;
    if (unique) {
      indexType = IndexType.createUnique(t.isPersistIndexes(), false);
    } else {
      indexType = IndexType.createNonUnique(t.isPersistIndexes());
    }
    indexType.setBelongsToConstraint(true);
    String prefix = this.constraintName == null ? "CONSTRAINT" : this.constraintName;
    String indexName = t.getSchema().getUniqueIndexName(this.session, t, prefix + "_INDEX_");
    try
    {
      Index index = t.addIndex(this.session, indexName, indexId, cols, indexType, true, null);
      
      this.createdIndexes.add(index);
      return index;
    }
    finally
    {
      getSchema().freeUniqueName(indexName);
    }
  }
  
  public void setDeleteAction(int action)
  {
    this.deleteAction = action;
  }
  
  public void setUpdateAction(int action)
  {
    this.updateAction = action;
  }
  
  private static Index getUniqueIndex(Table t, IndexColumn[] cols)
  {
    for (Index idx : t.getIndexes()) {
      if (canUseUniqueIndex(idx, t, cols)) {
        return idx;
      }
    }
    return null;
  }
  
  private static Index getIndex(Table t, IndexColumn[] cols, boolean moreColumnOk)
  {
    for (Index idx : t.getIndexes()) {
      if (canUseIndex(idx, t, cols, moreColumnOk)) {
        return idx;
      }
    }
    return null;
  }
  
  private static boolean canUseUniqueIndex(Index idx, Table table, IndexColumn[] cols)
  {
    if ((idx.getTable() != table) || (!idx.getIndexType().isUnique())) {
      return false;
    }
    Column[] indexCols = idx.getColumns();
    if (indexCols.length > cols.length) {
      return false;
    }
    HashSet<Column> set = New.hashSet();
    for (IndexColumn c : cols) {
      set.add(c.column);
    }
    for (Column c : indexCols) {
      if (!set.contains(c)) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean canUseIndex(Index existingIndex, Table table, IndexColumn[] cols, boolean moreColumnsOk)
  {
    if ((existingIndex.getTable() != table) || (existingIndex.getCreateSQL() == null)) {
      return false;
    }
    Column[] indexCols = existingIndex.getColumns();
    if (moreColumnsOk)
    {
      if (indexCols.length < cols.length) {
        return false;
      }
      for (IndexColumn col : cols)
      {
        int idx = existingIndex.getColumnIndex(col.column);
        if ((idx < 0) || (idx >= cols.length)) {
          return false;
        }
      }
    }
    else
    {
      if (indexCols.length != cols.length) {
        return false;
      }
      for (IndexColumn col : cols)
      {
        int idx = existingIndex.getColumnIndex(col.column);
        if (idx < 0) {
          return false;
        }
      }
    }
    return true;
  }
  
  public void setConstraintName(String constraintName)
  {
    this.constraintName = constraintName;
  }
  
  public void setType(int type)
  {
    this.type = type;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public void setCheckExpression(Expression expression)
  {
    this.checkExpression = expression;
  }
  
  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }
  
  public void setIndexColumns(IndexColumn[] indexColumns)
  {
    this.indexColumns = indexColumns;
  }
  
  public IndexColumn[] getIndexColumns()
  {
    return this.indexColumns;
  }
  
  public void setRefTableName(Schema refSchema, String ref)
  {
    this.refSchema = refSchema;
    this.refTableName = ref;
  }
  
  public void setRefIndexColumns(IndexColumn[] indexColumns)
  {
    this.refIndexColumns = indexColumns;
  }
  
  public void setIndex(Index index)
  {
    this.index = index;
  }
  
  public void setRefIndex(Index refIndex)
  {
    this.refIndex = refIndex;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public void setCheckExisting(boolean b)
  {
    this.checkExisting = b;
  }
  
  public void setPrimaryKeyHash(boolean b)
  {
    this.primaryKeyHash = b;
  }
}
