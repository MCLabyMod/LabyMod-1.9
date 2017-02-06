package org.h2.command.ddl;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.command.dml.Insert;
import org.h2.command.dml.Query;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.util.New;
import org.h2.value.DataType;

public class CreateTable
  extends SchemaCommand
{
  private final CreateTableData data = new CreateTableData();
  private final ArrayList<DefineCommand> constraintCommands = New.arrayList();
  private IndexColumn[] pkColumns;
  private boolean ifNotExists;
  private boolean onCommitDrop;
  private boolean onCommitTruncate;
  private Query asQuery;
  private String comment;
  private boolean sortedInsertMode;
  
  public CreateTable(Session session, Schema schema)
  {
    super(session, schema);
    this.data.persistIndexes = true;
    this.data.persistData = true;
  }
  
  public void setQuery(Query query)
  {
    this.asQuery = query;
  }
  
  public void setTemporary(boolean temporary)
  {
    this.data.temporary = temporary;
  }
  
  public void setTableName(String tableName)
  {
    this.data.tableName = tableName;
  }
  
  public void addColumn(Column column)
  {
    this.data.columns.add(column);
  }
  
  public void addConstraintCommand(DefineCommand command)
  {
    if ((command instanceof CreateIndex))
    {
      this.constraintCommands.add(command);
    }
    else
    {
      AlterTableAddConstraint con = (AlterTableAddConstraint)command;
      boolean alreadySet;
      boolean alreadySet;
      if (con.getType() == 6) {
        alreadySet = setPrimaryKeyColumns(con.getIndexColumns());
      } else {
        alreadySet = false;
      }
      if (!alreadySet) {
        this.constraintCommands.add(command);
      }
    }
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public int update()
  {
    if (!this.transactional) {
      this.session.commit(true);
    }
    Database db = this.session.getDatabase();
    if (!db.isPersistent()) {
      this.data.persistIndexes = false;
    }
    boolean isSessionTemporary = (this.data.temporary) && (!this.data.globalTemporary);
    if (!isSessionTemporary) {
      db.lockMeta(this.session);
    }
    if (getSchema().findTableOrView(this.session, this.data.tableName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(42101, this.data.tableName);
    }
    if (this.asQuery != null)
    {
      this.asQuery.prepare();
      if (this.data.columns.size() == 0) {
        generateColumnsFromQuery();
      } else if (this.data.columns.size() != this.asQuery.getColumnCount()) {
        throw DbException.get(21002);
      }
    }
    if (this.pkColumns != null) {
      for (Column c : this.data.columns) {
        for (IndexColumn idxCol : this.pkColumns) {
          if (c.getName().equals(idxCol.columnName)) {
            c.setNullable(false);
          }
        }
      }
    }
    this.data.id = getObjectId();
    this.data.create = this.create;
    this.data.session = this.session;
    Table table = getSchema().createTable(this.data);
    ArrayList<Sequence> sequences = New.arrayList();
    for (Column c : this.data.columns)
    {
      if (c.isAutoIncrement())
      {
        int objId = getObjectId();
        c.convertAutoIncrementToSequence(this.session, getSchema(), objId, this.data.temporary);
      }
      Sequence seq = c.getSequence();
      if (seq != null) {
        sequences.add(seq);
      }
    }
    table.setComment(this.comment);
    if (isSessionTemporary)
    {
      if (this.onCommitDrop) {
        table.setOnCommitDrop(true);
      }
      if (this.onCommitTruncate) {
        table.setOnCommitTruncate(true);
      }
      this.session.addLocalTempTable(table);
    }
    else
    {
      db.lockMeta(this.session);
      db.addSchemaObject(this.session, table);
    }
    try
    {
      for (Column c : this.data.columns) {
        c.prepareExpression(this.session);
      }
      for (Sequence sequence : sequences) {
        table.addSequence(sequence);
      }
      for (DefineCommand command : this.constraintCommands)
      {
        command.setTransactional(this.transactional);
        command.update();
      }
      if (this.asQuery != null)
      {
        boolean old = this.session.isUndoLogEnabled();
        try
        {
          this.session.setUndoLogEnabled(false);
          this.session.startStatementWithinTransaction();
          Insert insert = null;
          insert = new Insert(this.session);
          insert.setSortedInsertMode(this.sortedInsertMode);
          insert.setQuery(this.asQuery);
          insert.setTable(table);
          insert.setInsertFromSelect(true);
          insert.prepare();
          insert.update();
        }
        finally
        {
          this.session.setUndoLogEnabled(old);
        }
      }
      HashSet<DbObject> set = New.hashSet();
      set.clear();
      table.addDependencies(set);
      for (DbObject obj : set) {
        if (obj != table) {
          if ((obj.getType() == 0) && 
            ((obj instanceof Table)))
          {
            Table t = (Table)obj;
            if (t.getId() > table.getId()) {
              throw DbException.get(50100, "Table depends on another table with a higher ID: " + t + ", this is currently not supported, " + "as it would prevent the database from " + "being re-opened");
            }
          }
        }
      }
    }
    catch (DbException e)
    {
      db.checkPowerOff();
      db.removeSchemaObject(this.session, table);
      if (!this.transactional) {
        this.session.commit(true);
      }
      throw e;
    }
    return 0;
  }
  
  private void generateColumnsFromQuery()
  {
    int columnCount = this.asQuery.getColumnCount();
    ArrayList<Expression> expressions = this.asQuery.getExpressions();
    for (int i = 0; i < columnCount; i++)
    {
      Expression expr = (Expression)expressions.get(i);
      int type = expr.getType();
      String name = expr.getAlias();
      long precision = expr.getPrecision();
      int displaySize = expr.getDisplaySize();
      DataType dt = DataType.getDataType(type);
      if ((precision > 0L) && ((dt.defaultPrecision == 0L) || ((dt.defaultPrecision > precision) && (dt.defaultPrecision < 127L)))) {
        precision = dt.defaultPrecision;
      }
      int scale = expr.getScale();
      if ((scale > 0) && ((dt.defaultScale == 0) || ((dt.defaultScale > scale) && (dt.defaultScale < precision)))) {
        scale = dt.defaultScale;
      }
      if (scale > precision) {
        precision = scale;
      }
      Column col = new Column(name, type, precision, scale, displaySize);
      addColumn(col);
    }
  }
  
  private boolean setPrimaryKeyColumns(IndexColumn[] columns)
  {
    if (this.pkColumns != null)
    {
      int len = columns.length;
      if (len != this.pkColumns.length) {
        throw DbException.get(90017);
      }
      for (int i = 0; i < len; i++) {
        if (!columns[i].columnName.equals(this.pkColumns[i].columnName)) {
          throw DbException.get(90017);
        }
      }
      return true;
    }
    this.pkColumns = columns;
    return false;
  }
  
  public void setPersistIndexes(boolean persistIndexes)
  {
    this.data.persistIndexes = persistIndexes;
  }
  
  public void setGlobalTemporary(boolean globalTemporary)
  {
    this.data.globalTemporary = globalTemporary;
  }
  
  public void setOnCommitDrop()
  {
    this.onCommitDrop = true;
  }
  
  public void setOnCommitTruncate()
  {
    this.onCommitTruncate = true;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public void setPersistData(boolean persistData)
  {
    this.data.persistData = persistData;
    if (!persistData) {
      this.data.persistIndexes = false;
    }
  }
  
  public void setSortedInsertMode(boolean sortedInsertMode)
  {
    this.sortedInsertMode = sortedInsertMode;
  }
  
  public void setTableEngine(String tableEngine)
  {
    this.data.tableEngine = tableEngine;
  }
  
  public void setTableEngineParams(ArrayList<String> tableEngineParams)
  {
    this.data.tableEngineParams = tableEngineParams;
  }
  
  public void setHidden(boolean isHidden)
  {
    this.data.isHidden = isHidden;
  }
  
  public int getType()
  {
    return 30;
  }
}
