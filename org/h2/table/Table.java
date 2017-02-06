package org.h2.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.h2.command.Prepared;
import org.h2.constraint.Constraint;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Right;
import org.h2.engine.Session;
import org.h2.engine.Session.Savepoint;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionVisitor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.RowList;
import org.h2.result.SearchRow;
import org.h2.result.SimpleRow;
import org.h2.result.SimpleRowValue;
import org.h2.result.SortOrder;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObjectBase;
import org.h2.schema.Sequence;
import org.h2.schema.TriggerObject;
import org.h2.util.New;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public abstract class Table
  extends SchemaObjectBase
{
  public static final int TYPE_CACHED = 0;
  public static final int TYPE_MEMORY = 1;
  public static final String TABLE_LINK = "TABLE LINK";
  public static final String SYSTEM_TABLE = "SYSTEM TABLE";
  public static final String TABLE = "TABLE";
  public static final String VIEW = "VIEW";
  public static final String EXTERNAL_TABLE_ENGINE = "EXTERNAL";
  protected Column[] columns;
  protected CompareMode compareMode;
  protected boolean isHidden;
  private final HashMap<String, Column> columnMap;
  private final boolean persistIndexes;
  private final boolean persistData;
  private ArrayList<TriggerObject> triggers;
  private ArrayList<Constraint> constraints;
  private ArrayList<Sequence> sequences;
  private ArrayList<TableView> views;
  private boolean checkForeignKeyConstraints = true;
  private boolean onCommitDrop;
  private boolean onCommitTruncate;
  private Row nullRow;
  
  public Table(Schema schema, int id, String name, boolean persistIndexes, boolean persistData)
  {
    this.columnMap = schema.getDatabase().newStringMap();
    initSchemaObjectBase(schema, id, name, "table");
    this.persistIndexes = persistIndexes;
    this.persistData = persistData;
    this.compareMode = schema.getDatabase().getCompareMode();
  }
  
  public void rename(String newName)
  {
    super.rename(newName);
    if (this.constraints != null)
    {
      int i = 0;
      for (int size = this.constraints.size(); i < size; i++)
      {
        Constraint constraint = (Constraint)this.constraints.get(i);
        constraint.rebuild();
      }
    }
  }
  
  public abstract boolean lock(Session paramSession, boolean paramBoolean1, boolean paramBoolean2);
  
  public abstract void close(Session paramSession);
  
  public abstract void unlock(Session paramSession);
  
  public abstract Index addIndex(Session paramSession, String paramString1, int paramInt, IndexColumn[] paramArrayOfIndexColumn, IndexType paramIndexType, boolean paramBoolean, String paramString2);
  
  public Row getRow(Session session, long key)
  {
    return null;
  }
  
  public abstract void removeRow(Session paramSession, Row paramRow);
  
  public abstract void truncate(Session paramSession);
  
  public abstract void addRow(Session paramSession, Row paramRow);
  
  public void commit(short operation, Row row) {}
  
  public abstract void checkSupportAlter();
  
  public abstract String getTableType();
  
  public abstract Index getScanIndex(Session paramSession);
  
  public abstract Index getUniqueIndex();
  
  public abstract ArrayList<Index> getIndexes();
  
  public abstract boolean isLockedExclusively();
  
  public abstract long getMaxDataModificationId();
  
  public abstract boolean isDeterministic();
  
  public abstract boolean canGetRowCount();
  
  public boolean canReference()
  {
    return true;
  }
  
  public abstract boolean canDrop();
  
  public abstract long getRowCount(Session paramSession);
  
  public abstract long getRowCountApproximation();
  
  public abstract long getDiskSpaceUsed();
  
  public Column getRowIdColumn()
  {
    return null;
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public boolean isQueryComparable()
  {
    return true;
  }
  
  public void addDependencies(HashSet<DbObject> dependencies)
  {
    if (dependencies.contains(this)) {
      return;
    }
    if (this.sequences != null) {
      for (Sequence s : this.sequences) {
        dependencies.add(s);
      }
    }
    ExpressionVisitor visitor = ExpressionVisitor.getDependenciesVisitor(dependencies);
    for (Column col : this.columns) {
      col.isEverything(visitor);
    }
    if (this.constraints != null) {
      for (Constraint c : this.constraints) {
        c.isEverything(visitor);
      }
    }
    dependencies.add(this);
  }
  
  public ArrayList<DbObject> getChildren()
  {
    ArrayList<DbObject> children = New.arrayList();
    ArrayList<Index> indexes = getIndexes();
    if (indexes != null) {
      children.addAll(indexes);
    }
    if (this.constraints != null) {
      children.addAll(this.constraints);
    }
    if (this.triggers != null) {
      children.addAll(this.triggers);
    }
    if (this.sequences != null) {
      children.addAll(this.sequences);
    }
    if (this.views != null) {
      children.addAll(this.views);
    }
    ArrayList<Right> rights = this.database.getAllRights();
    for (Right right : rights) {
      if (right.getGrantedTable() == this) {
        children.add(right);
      }
    }
    return children;
  }
  
  protected void setColumns(Column[] columns)
  {
    this.columns = columns;
    if (this.columnMap.size() > 0) {
      this.columnMap.clear();
    }
    for (int i = 0; i < columns.length; i++)
    {
      Column col = columns[i];
      int dataType = col.getType();
      if (dataType == -1) {
        throw DbException.get(50004, col.getSQL());
      }
      col.setTable(this, i);
      String columnName = col.getName();
      if (this.columnMap.get(columnName) != null) {
        throw DbException.get(42121, columnName);
      }
      this.columnMap.put(columnName, col);
    }
  }
  
  public void renameColumn(Column column, String newName)
  {
    for (Column c : this.columns) {
      if (c != column) {
        if (c.getName().equals(newName)) {
          throw DbException.get(42121, newName);
        }
      }
    }
    this.columnMap.remove(column.getName());
    column.rename(newName);
    this.columnMap.put(newName, column);
  }
  
  public boolean isLockedExclusivelyBy(Session session)
  {
    return false;
  }
  
  public void updateRows(Prepared prepared, Session session, RowList rows)
  {
    Session.Savepoint rollback = session.setSavepoint();
    
    int rowScanCount = 0;
    for (rows.reset(); rows.hasNext();)
    {
      rowScanCount++;
      if ((rowScanCount & 0x7F) == 0) {
        prepared.checkCanceled();
      }
      Row o = rows.next();
      rows.next();
      removeRow(session, o);
      session.log(this, (short)1, o);
    }
    for (rows.reset(); rows.hasNext();)
    {
      rowScanCount++;
      if ((rowScanCount & 0x7F) == 0) {
        prepared.checkCanceled();
      }
      rows.next();
      Row n = rows.next();
      try
      {
        addRow(session, n);
      }
      catch (DbException e)
      {
        if (e.getErrorCode() == 90131)
        {
          session.rollbackTo(rollback, false);
          session.startStatementWithinTransaction();
          rollback = session.setSavepoint();
        }
        throw e;
      }
      session.log(this, (short)0, n);
    }
  }
  
  public ArrayList<TableView> getViews()
  {
    return this.views;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    while ((this.views != null) && (this.views.size() > 0))
    {
      TableView view = (TableView)this.views.get(0);
      this.views.remove(0);
      this.database.removeSchemaObject(session, view);
    }
    while ((this.triggers != null) && (this.triggers.size() > 0))
    {
      TriggerObject trigger = (TriggerObject)this.triggers.get(0);
      this.triggers.remove(0);
      this.database.removeSchemaObject(session, trigger);
    }
    while ((this.constraints != null) && (this.constraints.size() > 0))
    {
      Constraint constraint = (Constraint)this.constraints.get(0);
      this.constraints.remove(0);
      this.database.removeSchemaObject(session, constraint);
    }
    for (Right right : this.database.getAllRights()) {
      if (right.getGrantedTable() == this) {
        this.database.removeDatabaseObject(session, right);
      }
    }
    this.database.removeMeta(session, getId());
    while ((this.sequences != null) && (this.sequences.size() > 0))
    {
      Sequence sequence = (Sequence)this.sequences.get(0);
      this.sequences.remove(0);
      if (!isTemporary()) {
        if (this.database.getDependentTable(sequence, this) == null) {
          this.database.removeSchemaObject(session, sequence);
        }
      }
    }
  }
  
  public void dropSingleColumnConstraintsAndIndexes(Session session, Column col)
  {
    ArrayList<Constraint> constraintsToDrop = New.arrayList();
    if (this.constraints != null)
    {
      int i = 0;
      for (int size = this.constraints.size(); i < size; i++)
      {
        Constraint constraint = (Constraint)this.constraints.get(i);
        HashSet<Column> columns = constraint.getReferencedColumns(this);
        if (columns.contains(col)) {
          if (columns.size() == 1) {
            constraintsToDrop.add(constraint);
          } else {
            throw DbException.get(90083, constraint.getSQL());
          }
        }
      }
    }
    ArrayList<Index> indexesToDrop = New.arrayList();
    ArrayList<Index> indexes = getIndexes();
    if (indexes != null)
    {
      int i = 0;
      for (int size = indexes.size(); i < size; i++)
      {
        Index index = (Index)indexes.get(i);
        if (index.getCreateSQL() != null) {
          if (index.getColumnIndex(col) >= 0) {
            if (index.getColumns().length == 1) {
              indexesToDrop.add(index);
            } else {
              throw DbException.get(90083, index.getSQL());
            }
          }
        }
      }
    }
    for (Constraint c : constraintsToDrop) {
      session.getDatabase().removeSchemaObject(session, c);
    }
    for (Index i : indexesToDrop) {
      if (getIndexes().contains(i)) {
        session.getDatabase().removeSchemaObject(session, i);
      }
    }
  }
  
  public Row getTemplateRow()
  {
    return new Row(new Value[this.columns.length], -1);
  }
  
  public SearchRow getTemplateSimpleRow(boolean singleColumn)
  {
    if (singleColumn) {
      return new SimpleRowValue(this.columns.length);
    }
    return new SimpleRow(new Value[this.columns.length]);
  }
  
  synchronized Row getNullRow()
  {
    if (this.nullRow == null)
    {
      this.nullRow = new Row(new Value[this.columns.length], 1);
      for (int i = 0; i < this.columns.length; i++) {
        this.nullRow.setValue(i, ValueNull.INSTANCE);
      }
    }
    return this.nullRow;
  }
  
  public Column[] getColumns()
  {
    return this.columns;
  }
  
  public int getType()
  {
    return 0;
  }
  
  public Column getColumn(int index)
  {
    return this.columns[index];
  }
  
  public Column getColumn(String columnName)
  {
    Column column = (Column)this.columnMap.get(columnName);
    if (column == null) {
      throw DbException.get(42122, columnName);
    }
    return column;
  }
  
  public boolean doesColumnExist(String columnName)
  {
    return this.columnMap.containsKey(columnName);
  }
  
  public PlanItem getBestPlanItem(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    PlanItem item = new PlanItem();
    item.setIndex(getScanIndex(session));
    item.cost = item.getIndex().getCost(session, null, null, null);
    ArrayList<Index> indexes = getIndexes();
    if ((indexes != null) && (masks != null))
    {
      int i = 1;
      for (int size = indexes.size(); i < size; i++)
      {
        Index index = (Index)indexes.get(i);
        double cost = index.getCost(session, masks, filter, sortOrder);
        if (cost < item.cost)
        {
          item.cost = cost;
          item.setIndex(index);
        }
      }
    }
    return item;
  }
  
  public Index findPrimaryKey()
  {
    ArrayList<Index> indexes = getIndexes();
    if (indexes != null)
    {
      int i = 0;
      for (int size = indexes.size(); i < size; i++)
      {
        Index idx = (Index)indexes.get(i);
        if (idx.getIndexType().isPrimaryKey()) {
          return idx;
        }
      }
    }
    return null;
  }
  
  public Index getPrimaryKey()
  {
    Index index = findPrimaryKey();
    if (index != null) {
      return index;
    }
    throw DbException.get(42112, "PRIMARY_KEY_");
  }
  
  public void validateConvertUpdateSequence(Session session, Row row)
  {
    for (int i = 0; i < this.columns.length; i++)
    {
      Value value = row.getValue(i);
      Column column = this.columns[i];
      if (column.getComputed())
      {
        value = null;
        Value localValue1 = column.computeValue(session, row);
      }
      Value v2 = column.validateConvertUpdateSequence(session, value);
      if (v2 != value) {
        row.setValue(i, v2);
      }
    }
  }
  
  private static void remove(ArrayList<? extends DbObject> list, DbObject obj)
  {
    if (list != null)
    {
      int i = list.indexOf(obj);
      if (i >= 0) {
        list.remove(i);
      }
    }
  }
  
  public void removeIndex(Index index)
  {
    ArrayList<Index> indexes = getIndexes();
    if (indexes != null)
    {
      remove(indexes, index);
      if (index.getIndexType().isPrimaryKey()) {
        for (Column col : index.getColumns()) {
          col.setPrimaryKey(false);
        }
      }
    }
  }
  
  public void removeView(TableView view)
  {
    remove(this.views, view);
  }
  
  public void removeConstraint(Constraint constraint)
  {
    remove(this.constraints, constraint);
  }
  
  public final void removeSequence(Sequence sequence)
  {
    remove(this.sequences, sequence);
  }
  
  public void removeTrigger(TriggerObject trigger)
  {
    remove(this.triggers, trigger);
  }
  
  public void addView(TableView view)
  {
    this.views = add(this.views, view);
  }
  
  public void addConstraint(Constraint constraint)
  {
    if ((this.constraints == null) || (this.constraints.indexOf(constraint) < 0)) {
      this.constraints = add(this.constraints, constraint);
    }
  }
  
  public ArrayList<Constraint> getConstraints()
  {
    return this.constraints;
  }
  
  public void addSequence(Sequence sequence)
  {
    this.sequences = add(this.sequences, sequence);
  }
  
  public void addTrigger(TriggerObject trigger)
  {
    this.triggers = add(this.triggers, trigger);
  }
  
  private static <T> ArrayList<T> add(ArrayList<T> list, T obj)
  {
    if (list == null) {
      list = New.arrayList();
    }
    list.add(obj);
    return list;
  }
  
  public void fire(Session session, int type, boolean beforeAction)
  {
    if (this.triggers != null) {
      for (TriggerObject trigger : this.triggers) {
        trigger.fire(session, type, beforeAction);
      }
    }
  }
  
  public boolean hasSelectTrigger()
  {
    if (this.triggers != null) {
      for (TriggerObject trigger : this.triggers) {
        if (trigger.isSelectTrigger()) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean fireRow()
  {
    return ((this.constraints != null) && (this.constraints.size() > 0)) || ((this.triggers != null) && (this.triggers.size() > 0));
  }
  
  public boolean fireBeforeRow(Session session, Row oldRow, Row newRow)
  {
    boolean done = fireRow(session, oldRow, newRow, true, false);
    fireConstraints(session, oldRow, newRow, true);
    return done;
  }
  
  private void fireConstraints(Session session, Row oldRow, Row newRow, boolean before)
  {
    if (this.constraints != null)
    {
      int i = 0;
      for (int size = this.constraints.size(); i < size; i++)
      {
        Constraint constraint = (Constraint)this.constraints.get(i);
        if (constraint.isBefore() == before) {
          constraint.checkRow(session, this, oldRow, newRow);
        }
      }
    }
  }
  
  public void fireAfterRow(Session session, Row oldRow, Row newRow, boolean rollback)
  {
    fireRow(session, oldRow, newRow, false, rollback);
    if (!rollback) {
      fireConstraints(session, oldRow, newRow, false);
    }
  }
  
  private boolean fireRow(Session session, Row oldRow, Row newRow, boolean beforeAction, boolean rollback)
  {
    if (this.triggers != null) {
      for (TriggerObject trigger : this.triggers)
      {
        boolean done = trigger.fireRow(session, oldRow, newRow, beforeAction, rollback);
        if (done) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isGlobalTemporary()
  {
    return false;
  }
  
  public boolean canTruncate()
  {
    return false;
  }
  
  public void setCheckForeignKeyConstraints(Session session, boolean enabled, boolean checkExisting)
  {
    if ((enabled) && (checkExisting) && 
      (this.constraints != null)) {
      for (Constraint c : this.constraints) {
        c.checkExistingData(session);
      }
    }
    this.checkForeignKeyConstraints = enabled;
  }
  
  public boolean getCheckForeignKeyConstraints()
  {
    return this.checkForeignKeyConstraints;
  }
  
  public Index getIndexForColumn(Column column)
  {
    ArrayList<Index> indexes = getIndexes();
    if (indexes != null)
    {
      int i = 1;
      for (int size = indexes.size(); i < size; i++)
      {
        Index index = (Index)indexes.get(i);
        if (index.canGetFirstOrLast())
        {
          int idx = index.getColumnIndex(column);
          if (idx == 0) {
            return index;
          }
        }
      }
    }
    return null;
  }
  
  public boolean getOnCommitDrop()
  {
    return this.onCommitDrop;
  }
  
  public void setOnCommitDrop(boolean onCommitDrop)
  {
    this.onCommitDrop = onCommitDrop;
  }
  
  public boolean getOnCommitTruncate()
  {
    return this.onCommitTruncate;
  }
  
  public void setOnCommitTruncate(boolean onCommitTruncate)
  {
    this.onCommitTruncate = onCommitTruncate;
  }
  
  public void removeIndexOrTransferOwnership(Session session, Index index)
  {
    boolean stillNeeded = false;
    if (this.constraints != null) {
      for (Constraint cons : this.constraints) {
        if (cons.usesIndex(index))
        {
          cons.setIndexOwner(index);
          this.database.updateMeta(session, cons);
          stillNeeded = true;
        }
      }
    }
    if (!stillNeeded) {
      this.database.removeSchemaObject(session, index);
    }
  }
  
  public ArrayList<Session> checkDeadlock(Session session, Session clash, Set<Session> visited)
  {
    return null;
  }
  
  public boolean isPersistIndexes()
  {
    return this.persistIndexes;
  }
  
  public boolean isPersistData()
  {
    return this.persistData;
  }
  
  public int compareTypeSave(Value a, Value b)
  {
    if (a == b) {
      return 0;
    }
    int dataType = Value.getHigherOrder(a.getType(), b.getType());
    a = a.convertTo(dataType);
    b = b.convertTo(dataType);
    return a.compareTypeSave(b, this.compareMode);
  }
  
  public CompareMode getCompareMode()
  {
    return this.compareMode;
  }
  
  public void checkWritingAllowed()
  {
    this.database.checkWritingAllowed();
  }
  
  public Value getDefaultValue(Session session, Column column)
  {
    Expression defaultExpr = column.getDefaultExpression();
    Value v;
    Value v;
    if (defaultExpr == null) {
      v = column.validateConvertUpdateSequence(session, null);
    } else {
      v = defaultExpr.getValue(session);
    }
    return column.convert(v);
  }
  
  public boolean isHidden()
  {
    return this.isHidden;
  }
  
  public void setHidden(boolean hidden)
  {
    this.isHidden = hidden;
  }
  
  public boolean isMVStore()
  {
    return false;
  }
}
