package org.h2.command.ddl;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.command.Parser;
import org.h2.command.Prepared;
import org.h2.constraint.Constraint;
import org.h2.constraint.ConstraintReferential;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionVisitor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.schema.Sequence;
import org.h2.schema.TriggerObject;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.table.TableView;
import org.h2.util.New;
import org.h2.value.Value;

public class AlterTableAlterColumn
  extends SchemaCommand
{
  private Table table;
  private Column oldColumn;
  private Column newColumn;
  private int type;
  private Expression defaultExpression;
  private Expression newSelectivity;
  private String addBefore;
  private String addAfter;
  private boolean ifNotExists;
  private ArrayList<Column> columnsToAdd;
  
  public AlterTableAlterColumn(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setTable(Table table)
  {
    this.table = table;
  }
  
  public void setOldColumn(Column oldColumn)
  {
    this.oldColumn = oldColumn;
  }
  
  public void setAddBefore(String before)
  {
    this.addBefore = before;
  }
  
  public void setAddAfter(String after)
  {
    this.addAfter = after;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    this.session.getUser().checkRight(this.table, 15);
    this.table.checkSupportAlter();
    this.table.lock(this.session, true, true);
    Sequence sequence = this.oldColumn == null ? null : this.oldColumn.getSequence();
    if (this.newColumn != null) {
      checkDefaultReferencesTable(this.newColumn.getDefaultExpression());
    }
    if (this.columnsToAdd != null) {
      for (Column column : this.columnsToAdd) {
        checkDefaultReferencesTable(column.getDefaultExpression());
      }
    }
    switch (this.type)
    {
    case 8: 
      if (this.oldColumn.isNullable())
      {
        checkNoNullValues();
        this.oldColumn.setNullable(false);
        db.updateMeta(this.session, this.table);
      }
      break;
    case 9: 
      if (!this.oldColumn.isNullable())
      {
        checkNullable();
        this.oldColumn.setNullable(true);
        db.updateMeta(this.session, this.table);
      }
      break;
    case 10: 
      checkDefaultReferencesTable(this.defaultExpression);
      this.oldColumn.setSequence(null);
      this.oldColumn.setDefaultExpression(this.session, this.defaultExpression);
      removeSequence(sequence);
      db.updateMeta(this.session, this.table);
      break;
    case 11: 
      if (this.oldColumn.isWideningConversion(this.newColumn))
      {
        convertAutoIncrementColumn(this.newColumn);
        this.oldColumn.copy(this.newColumn);
        db.updateMeta(this.session, this.table);
      }
      else
      {
        this.oldColumn.setSequence(null);
        this.oldColumn.setDefaultExpression(this.session, null);
        this.oldColumn.setConvertNullToDefault(false);
        if ((this.oldColumn.isNullable()) && (!this.newColumn.isNullable())) {
          checkNoNullValues();
        } else if ((!this.oldColumn.isNullable()) && (this.newColumn.isNullable())) {
          checkNullable();
        }
        convertAutoIncrementColumn(this.newColumn);
        copyData();
      }
      break;
    case 7: 
      if ((!this.ifNotExists) || (this.columnsToAdd.size() != 1) || (!this.table.doesColumnExist(((Column)this.columnsToAdd.get(0)).getName())))
      {
        for (Column column : this.columnsToAdd) {
          if (column.isAutoIncrement())
          {
            int objId = getObjectId();
            column.convertAutoIncrementToSequence(this.session, getSchema(), objId, this.table.isTemporary());
          }
        }
        copyData();
      }
      break;
    case 12: 
      if (this.table.getColumns().length == 1) {
        throw DbException.get(90084, this.oldColumn.getSQL());
      }
      this.table.dropSingleColumnConstraintsAndIndexes(this.session, this.oldColumn);
      copyData();
      break;
    case 13: 
      int value = this.newSelectivity.optimize(this.session).getValue(this.session).getInt();
      this.oldColumn.setSelectivity(value);
      db.updateMeta(this.session, this.table);
      break;
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    return 0;
  }
  
  private void checkDefaultReferencesTable(Expression defaultExpression)
  {
    if (defaultExpression == null) {
      return;
    }
    HashSet<DbObject> dependencies = New.hashSet();
    ExpressionVisitor visitor = ExpressionVisitor.getDependenciesVisitor(dependencies);
    
    defaultExpression.isEverything(visitor);
    if (dependencies.contains(this.table)) {
      throw DbException.get(90083, defaultExpression.getSQL());
    }
  }
  
  private void convertAutoIncrementColumn(Column c)
  {
    if (c.isAutoIncrement()) {
      if (c.isPrimaryKey())
      {
        c.setOriginalSQL("IDENTITY");
      }
      else
      {
        int objId = getObjectId();
        c.convertAutoIncrementToSequence(this.session, getSchema(), objId, this.table.isTemporary());
      }
    }
  }
  
  private void removeSequence(Sequence sequence)
  {
    if (sequence != null)
    {
      this.table.removeSequence(sequence);
      sequence.setBelongsToTable(false);
      Database db = this.session.getDatabase();
      db.removeSchemaObject(this.session, sequence);
    }
  }
  
  private void copyData()
  {
    if (this.table.isTemporary()) {
      throw DbException.getUnsupportedException("TEMP TABLE");
    }
    Database db = this.session.getDatabase();
    String baseName = this.table.getName();
    String tempName = db.getTempTableName(baseName, this.session);
    Column[] columns = this.table.getColumns();
    ArrayList<Column> newColumns = New.arrayList();
    Table newTable = cloneTableStructure(columns, db, tempName, newColumns);
    try
    {
      checkViews(this.table, newTable);
    }
    catch (DbException e)
    {
      execute("DROP TABLE " + newTable.getName(), true);
      throw DbException.get(90109, e, new String[] { getSQL(), e.getMessage() });
    }
    String tableName = this.table.getName();
    ArrayList<TableView> views = this.table.getViews();
    if (views != null)
    {
      views = New.arrayList(views);
      for (TableView view : views) {
        this.table.removeView(view);
      }
    }
    execute("DROP TABLE " + this.table.getSQL() + " IGNORE", true);
    db.renameSchemaObject(this.session, newTable, tableName);
    for (DbObject child : newTable.getChildren()) {
      if (!(child instanceof Sequence))
      {
        String name = child.getName();
        if ((name != null) && (child.getCreateSQL() != null)) {
          if (name.startsWith(tempName + "_"))
          {
            name = name.substring(tempName.length() + 1);
            SchemaObject so = (SchemaObject)child;
            if ((so instanceof Constraint))
            {
              if (so.getSchema().findConstraint(this.session, name) != null) {
                name = so.getSchema().getUniqueConstraintName(this.session, newTable);
              }
            }
            else if (((so instanceof Index)) && 
              (so.getSchema().findIndex(this.session, name) != null)) {
              name = so.getSchema().getUniqueIndexName(this.session, newTable, name);
            }
            db.renameSchemaObject(this.session, so, name);
          }
        }
      }
    }
    if (views != null) {
      for (TableView view : views)
      {
        String sql = view.getCreateSQL(true, true);
        execute(sql, true);
      }
    }
  }
  
  private Table cloneTableStructure(Column[] columns, Database db, String tempName, ArrayList<Column> newColumns)
  {
    for (Column col : columns) {
      newColumns.add(col.getClone());
    }
    if (this.type == 12)
    {
      int position = this.oldColumn.getColumnId();
      newColumns.remove(position);
    }
    else
    {
      int position;
      if (this.type == 7)
      {
        int position;
        if (this.addBefore != null)
        {
          position = this.table.getColumn(this.addBefore).getColumnId();
        }
        else
        {
          int position;
          if (this.addAfter != null) {
            position = this.table.getColumn(this.addAfter).getColumnId() + 1;
          } else {
            position = columns.length;
          }
        }
        for (Column column : this.columnsToAdd) {
          newColumns.add(position++, column);
        }
      }
      else if (this.type == 11)
      {
        int position = this.oldColumn.getColumnId();
        newColumns.remove(position);
        newColumns.add(position, this.newColumn);
      }
    }
    int id = db.allocateObjectId();
    CreateTableData data = new CreateTableData();
    data.tableName = tempName;
    data.id = id;
    data.columns = newColumns;
    data.temporary = this.table.isTemporary();
    data.persistData = this.table.isPersistData();
    data.persistIndexes = this.table.isPersistIndexes();
    data.isHidden = this.table.isHidden();
    data.create = true;
    data.session = this.session;
    Table newTable = getSchema().createTable(data);
    newTable.setComment(this.table.getComment());
    StringBuilder buff = new StringBuilder();
    buff.append(newTable.getCreateSQL());
    StringBuilder columnList = new StringBuilder();
    for (Column nc : newColumns)
    {
      if (columnList.length() > 0) {
        columnList.append(", ");
      }
      if ((this.type == 7) && (this.columnsToAdd.contains(nc)))
      {
        Expression def = nc.getDefaultExpression();
        columnList.append(def == null ? "NULL" : def.getSQL());
      }
      else
      {
        columnList.append(nc.getSQL());
      }
    }
    buff.append(" AS SELECT ");
    if (columnList.length() == 0) {
      buff.append('*');
    } else {
      buff.append(columnList);
    }
    buff.append(" FROM ").append(this.table.getSQL());
    String newTableSQL = buff.toString();
    String newTableName = newTable.getName();
    Schema newTableSchema = newTable.getSchema();
    newTable.removeChildrenAndResources(this.session);
    
    execute(newTableSQL, true);
    newTable = newTableSchema.getTableOrView(this.session, newTableName);
    ArrayList<String> triggers = New.arrayList();
    for (DbObject child : this.table.getChildren()) {
      if (!(child instanceof Sequence)) {
        if ((child instanceof Index))
        {
          Index idx = (Index)child;
          if (idx.getIndexType().getBelongsToConstraint()) {}
        }
        else
        {
          String createSQL = child.getCreateSQL();
          if ((createSQL != null) && 
          
            (!(child instanceof TableView)))
          {
            if (child.getType() == 0) {
              DbException.throwInternalError();
            }
            String quotedName = Parser.quoteIdentifier(tempName + "_" + child.getName());
            String sql = null;
            if ((child instanceof ConstraintReferential))
            {
              ConstraintReferential r = (ConstraintReferential)child;
              if (r.getTable() != this.table) {
                sql = r.getCreateSQLForCopy(r.getTable(), newTable, quotedName, false);
              }
            }
            if (sql == null) {
              sql = child.getCreateSQLForCopy(newTable, quotedName);
            }
            if (sql != null) {
              if ((child instanceof TriggerObject)) {
                triggers.add(sql);
              } else {
                execute(sql, true);
              }
            }
          }
        }
      }
    }
    this.table.setModified();
    for (Column col : newColumns)
    {
      Sequence seq = col.getSequence();
      if (seq != null)
      {
        this.table.removeSequence(seq);
        col.setSequence(null);
      }
    }
    for (String sql : triggers) {
      execute(sql, true);
    }
    return newTable;
  }
  
  private void checkViews(SchemaObject sourceTable, SchemaObject newTable)
  {
    String sourceTableName = sourceTable.getName();
    String newTableName = newTable.getName();
    Database db = sourceTable.getDatabase();
    
    String temp = db.getTempTableName(sourceTableName, this.session);
    db.renameSchemaObject(this.session, sourceTable, temp);
    try
    {
      db.renameSchemaObject(this.session, newTable, sourceTableName);
      checkViewsAreValid(sourceTable);
    }
    finally
    {
      try
      {
        db.renameSchemaObject(this.session, newTable, newTableName);
      }
      finally
      {
        db.renameSchemaObject(this.session, sourceTable, sourceTableName);
      }
    }
  }
  
  private void checkViewsAreValid(DbObject tableOrView)
  {
    for (DbObject view : tableOrView.getChildren()) {
      if ((view instanceof TableView))
      {
        String sql = ((TableView)view).getQuery();
        
        this.session.prepare(sql);
        checkViewsAreValid(view);
      }
    }
  }
  
  private void execute(String sql, boolean ddl)
  {
    Prepared command = this.session.prepare(sql);
    command.update();
    if (ddl) {
      this.session.commit(true);
    }
  }
  
  private void checkNullable()
  {
    for (Index index : this.table.getIndexes()) {
      if (index.getColumnIndex(this.oldColumn) >= 0)
      {
        IndexType indexType = index.getIndexType();
        if ((indexType.isPrimaryKey()) || (indexType.isHash())) {
          throw DbException.get(90075, index.getSQL());
        }
      }
    }
  }
  
  private void checkNoNullValues()
  {
    String sql = "SELECT COUNT(*) FROM " + this.table.getSQL() + " WHERE " + this.oldColumn.getSQL() + " IS NULL";
    
    Prepared command = this.session.prepare(sql);
    ResultInterface result = command.query(0);
    result.next();
    if (result.currentRow()[0].getInt() > 0) {
      throw DbException.get(90081, this.oldColumn.getSQL());
    }
  }
  
  public void setType(int type)
  {
    this.type = type;
  }
  
  public void setSelectivity(Expression selectivity)
  {
    this.newSelectivity = selectivity;
  }
  
  public void setDefaultExpression(Expression defaultExpression)
  {
    this.defaultExpression = defaultExpression;
  }
  
  public void setNewColumn(Column newColumn)
  {
    this.newColumn = newColumn;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setNewColumns(ArrayList<Column> columnsToAdd)
  {
    this.columnsToAdd = columnsToAdd;
  }
}
