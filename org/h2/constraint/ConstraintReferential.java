package org.h2.constraint;

import java.util.ArrayList;
import java.util.HashSet;
import org.h2.command.Parser;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.Parameter;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class ConstraintReferential
  extends Constraint
{
  public static final int RESTRICT = 0;
  public static final int CASCADE = 1;
  public static final int SET_DEFAULT = 2;
  public static final int SET_NULL = 3;
  private IndexColumn[] columns;
  private IndexColumn[] refColumns;
  private int deleteAction;
  private int updateAction;
  private Table refTable;
  private Index index;
  private Index refIndex;
  private boolean indexOwner;
  private boolean refIndexOwner;
  private String deleteSQL;
  private String updateSQL;
  private boolean skipOwnTable;
  
  public ConstraintReferential(Schema schema, int id, String name, Table table)
  {
    super(schema, id, name, table);
  }
  
  public String getConstraintType()
  {
    return "REFERENTIAL";
  }
  
  private static void appendAction(StatementBuilder buff, int action)
  {
    switch (action)
    {
    case 1: 
      buff.append("CASCADE");
      break;
    case 2: 
      buff.append("SET DEFAULT");
      break;
    case 3: 
      buff.append("SET NULL");
      break;
    default: 
      DbException.throwInternalError("action=" + action);
    }
  }
  
  public String getCreateSQLForCopy(Table forTable, String quotedName)
  {
    return getCreateSQLForCopy(forTable, this.refTable, quotedName, true);
  }
  
  public String getCreateSQLForCopy(Table forTable, Table forRefTable, String quotedName, boolean internalIndex)
  {
    StatementBuilder buff = new StatementBuilder("ALTER TABLE ");
    String mainTable = forTable.getSQL();
    buff.append(mainTable).append(" ADD CONSTRAINT ");
    if (forTable.isHidden()) {
      buff.append("IF NOT EXISTS ");
    }
    buff.append(quotedName);
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    IndexColumn[] cols = this.columns;
    IndexColumn[] refCols = this.refColumns;
    buff.append(" FOREIGN KEY(");
    for (IndexColumn c : cols)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    buff.append(')');
    if ((internalIndex) && (this.indexOwner) && (forTable == this.table)) {
      buff.append(" INDEX ").append(this.index.getSQL());
    }
    buff.append(" REFERENCES ");
    String quotedRefTable;
    String quotedRefTable;
    if (this.table == this.refTable) {
      quotedRefTable = forTable.getSQL();
    } else {
      quotedRefTable = forRefTable.getSQL();
    }
    buff.append(quotedRefTable).append('(');
    buff.resetCount();
    for (IndexColumn r : refCols)
    {
      buff.appendExceptFirst(", ");
      buff.append(r.getSQL());
    }
    buff.append(')');
    if ((internalIndex) && (this.refIndexOwner) && (forTable == this.table)) {
      buff.append(" INDEX ").append(this.refIndex.getSQL());
    }
    if (this.deleteAction != 0)
    {
      buff.append(" ON DELETE ");
      appendAction(buff, this.deleteAction);
    }
    if (this.updateAction != 0)
    {
      buff.append(" ON UPDATE ");
      appendAction(buff, this.updateAction);
    }
    return buff.append(" NOCHECK").toString();
  }
  
  private String getShortDescription(Index searchIndex, SearchRow check)
  {
    StatementBuilder buff = new StatementBuilder(getName());
    buff.append(": ").append(this.table.getSQL()).append(" FOREIGN KEY(");
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    buff.append(") REFERENCES ").append(this.refTable.getSQL()).append('(');
    buff.resetCount();
    for (IndexColumn r : this.refColumns)
    {
      buff.appendExceptFirst(", ");
      buff.append(r.getSQL());
    }
    buff.append(')');
    if ((searchIndex != null) && (check != null))
    {
      buff.append(" (");
      buff.resetCount();
      Column[] cols = searchIndex.getColumns();
      int len = Math.min(this.columns.length, cols.length);
      for (int i = 0; i < len; i++)
      {
        int idx = cols[i].getColumnId();
        Value c = check.getValue(idx);
        buff.appendExceptFirst(", ");
        buff.append(c == null ? "" : c.toString());
      }
      buff.append(')');
    }
    return buff.toString();
  }
  
  public String getCreateSQLWithoutIndexes()
  {
    return getCreateSQLForCopy(this.table, this.refTable, getSQL(), false);
  }
  
  public String getCreateSQL()
  {
    return getCreateSQLForCopy(this.table, getSQL());
  }
  
  public void setColumns(IndexColumn[] cols)
  {
    this.columns = cols;
  }
  
  public IndexColumn[] getColumns()
  {
    return this.columns;
  }
  
  public HashSet<Column> getReferencedColumns(Table table)
  {
    HashSet<Column> result = New.hashSet();
    if (table == this.table) {
      for (IndexColumn c : this.columns) {
        result.add(c.column);
      }
    } else if (table == this.refTable) {
      for (IndexColumn c : this.refColumns) {
        result.add(c.column);
      }
    }
    return result;
  }
  
  public void setRefColumns(IndexColumn[] refCols)
  {
    this.refColumns = refCols;
  }
  
  public IndexColumn[] getRefColumns()
  {
    return this.refColumns;
  }
  
  public void setRefTable(Table refTable)
  {
    this.refTable = refTable;
    if (refTable.isTemporary()) {
      setTemporary(true);
    }
  }
  
  public void setIndex(Index index, boolean isOwner)
  {
    this.index = index;
    this.indexOwner = isOwner;
  }
  
  public void setRefIndex(Index refIndex, boolean isRefOwner)
  {
    this.refIndex = refIndex;
    this.refIndexOwner = isRefOwner;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.table.removeConstraint(this);
    this.refTable.removeConstraint(this);
    if (this.indexOwner) {
      this.table.removeIndexOrTransferOwnership(session, this.index);
    }
    if (this.refIndexOwner) {
      this.refTable.removeIndexOrTransferOwnership(session, this.refIndex);
    }
    this.database.removeMeta(session, getId());
    this.refTable = null;
    this.index = null;
    this.refIndex = null;
    this.columns = null;
    this.refColumns = null;
    this.deleteSQL = null;
    this.updateSQL = null;
    this.table = null;
    invalidate();
  }
  
  public void checkRow(Session session, Table t, Row oldRow, Row newRow)
  {
    if (!this.database.getReferentialIntegrity()) {
      return;
    }
    if ((!this.table.getCheckForeignKeyConstraints()) || (!this.refTable.getCheckForeignKeyConstraints())) {
      return;
    }
    if ((t == this.table) && 
      (!this.skipOwnTable)) {
      checkRowOwnTable(session, oldRow, newRow);
    }
    if (t == this.refTable) {
      checkRowRefTable(session, oldRow, newRow);
    }
  }
  
  private void checkRowOwnTable(Session session, Row oldRow, Row newRow)
  {
    if (newRow == null) {
      return;
    }
    boolean constraintColumnsEqual = oldRow != null;
    for (IndexColumn col : this.columns)
    {
      int idx = col.column.getColumnId();
      Value v = newRow.getValue(idx);
      if (v == ValueNull.INSTANCE) {
        return;
      }
      if ((constraintColumnsEqual) && 
        (!this.database.areEqual(v, oldRow.getValue(idx)))) {
        constraintColumnsEqual = false;
      }
    }
    if (constraintColumnsEqual) {
      return;
    }
    if (this.refTable == this.table)
    {
      boolean self = true;
      int i = 0;
      for (int len = this.columns.length; i < len; i++)
      {
        int idx = this.columns[i].column.getColumnId();
        Value v = newRow.getValue(idx);
        Column refCol = this.refColumns[i].column;
        int refIdx = refCol.getColumnId();
        Value r = newRow.getValue(refIdx);
        if (!this.database.areEqual(r, v))
        {
          self = false;
          break;
        }
      }
      if (self) {
        return;
      }
    }
    Row check = this.refTable.getTemplateRow();
    int i = 0;
    for (int len = this.columns.length; i < len; i++)
    {
      int idx = this.columns[i].column.getColumnId();
      Value v = newRow.getValue(idx);
      Column refCol = this.refColumns[i].column;
      int refIdx = refCol.getColumnId();
      check.setValue(refIdx, refCol.convert(v));
    }
    if (!existsRow(session, this.refIndex, check, null)) {
      throw DbException.get(23506, getShortDescription(this.refIndex, check));
    }
  }
  
  private boolean existsRow(Session session, Index searchIndex, SearchRow check, Row excluding)
  {
    Table searchTable = searchIndex.getTable();
    searchTable.lock(session, false, false);
    Cursor cursor = searchIndex.find(session, check, check);
    while (cursor.next())
    {
      SearchRow found = cursor.getSearchRow();
      if ((excluding == null) || (found.getKey() != excluding.getKey()))
      {
        Column[] cols = searchIndex.getColumns();
        boolean allEqual = true;
        int len = Math.min(this.columns.length, cols.length);
        for (int i = 0; i < len; i++)
        {
          int idx = cols[i].getColumnId();
          Value c = check.getValue(idx);
          Value f = found.getValue(idx);
          if (searchTable.compareTypeSave(c, f) != 0)
          {
            allEqual = false;
            break;
          }
        }
        if (allEqual) {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean isEqual(Row oldRow, Row newRow)
  {
    return this.refIndex.compareRows(oldRow, newRow) == 0;
  }
  
  private void checkRow(Session session, Row oldRow)
  {
    SearchRow check = this.table.getTemplateSimpleRow(false);
    int i = 0;
    for (int len = this.columns.length; i < len; i++)
    {
      Column refCol = this.refColumns[i].column;
      int refIdx = refCol.getColumnId();
      Column col = this.columns[i].column;
      Value v = col.convert(oldRow.getValue(refIdx));
      if (v == ValueNull.INSTANCE) {
        return;
      }
      check.setValue(col.getColumnId(), v);
    }
    Row excluding = this.refTable == this.table ? oldRow : null;
    if (existsRow(session, this.index, check, excluding)) {
      throw DbException.get(23503, getShortDescription(this.index, check));
    }
  }
  
  private void checkRowRefTable(Session session, Row oldRow, Row newRow)
  {
    if (oldRow == null) {
      return;
    }
    if ((newRow != null) && (isEqual(oldRow, newRow))) {
      return;
    }
    if (newRow == null)
    {
      if (this.deleteAction == 0)
      {
        checkRow(session, oldRow);
      }
      else
      {
        int i = this.deleteAction == 1 ? 0 : this.columns.length;
        Prepared deleteCommand = getDelete(session);
        setWhere(deleteCommand, i, oldRow);
        updateWithSkipCheck(deleteCommand);
      }
    }
    else if (this.updateAction == 0)
    {
      checkRow(session, oldRow);
    }
    else
    {
      Prepared updateCommand = getUpdate(session);
      if (this.updateAction == 1)
      {
        ArrayList<Parameter> params = updateCommand.getParameters();
        int i = 0;
        for (int len = this.columns.length; i < len; i++)
        {
          Parameter param = (Parameter)params.get(i);
          Column refCol = this.refColumns[i].column;
          param.setValue(newRow.getValue(refCol.getColumnId()));
        }
      }
      setWhere(updateCommand, this.columns.length, oldRow);
      updateWithSkipCheck(updateCommand);
    }
  }
  
  private void updateWithSkipCheck(Prepared prep)
  {
    try
    {
      this.skipOwnTable = true;
      prep.update();
    }
    finally
    {
      this.skipOwnTable = false;
    }
  }
  
  private void setWhere(Prepared command, int pos, Row row)
  {
    int i = 0;
    for (int len = this.refColumns.length; i < len; i++)
    {
      int idx = this.refColumns[i].column.getColumnId();
      Value v = row.getValue(idx);
      ArrayList<Parameter> params = command.getParameters();
      Parameter param = (Parameter)params.get(pos + i);
      param.setValue(v);
    }
  }
  
  public int getDeleteAction()
  {
    return this.deleteAction;
  }
  
  public void setDeleteAction(int action)
  {
    if ((action == this.deleteAction) && (this.deleteSQL == null)) {
      return;
    }
    if (this.deleteAction != 0) {
      throw DbException.get(90045, "ON DELETE");
    }
    this.deleteAction = action;
    buildDeleteSQL();
  }
  
  private void buildDeleteSQL()
  {
    if (this.deleteAction == 0) {
      return;
    }
    StatementBuilder buff = new StatementBuilder();
    if (this.deleteAction == 1) {
      buff.append("DELETE FROM ").append(this.table.getSQL());
    } else {
      appendUpdate(buff);
    }
    appendWhere(buff);
    this.deleteSQL = buff.toString();
  }
  
  private Prepared getUpdate(Session session)
  {
    return prepare(session, this.updateSQL, this.updateAction);
  }
  
  private Prepared getDelete(Session session)
  {
    return prepare(session, this.deleteSQL, this.deleteAction);
  }
  
  public int getUpdateAction()
  {
    return this.updateAction;
  }
  
  public void setUpdateAction(int action)
  {
    if ((action == this.updateAction) && (this.updateSQL == null)) {
      return;
    }
    if (this.updateAction != 0) {
      throw DbException.get(90045, "ON UPDATE");
    }
    this.updateAction = action;
    buildUpdateSQL();
  }
  
  private void buildUpdateSQL()
  {
    if (this.updateAction == 0) {
      return;
    }
    StatementBuilder buff = new StatementBuilder();
    appendUpdate(buff);
    appendWhere(buff);
    this.updateSQL = buff.toString();
  }
  
  public void rebuild()
  {
    buildUpdateSQL();
    buildDeleteSQL();
  }
  
  private Prepared prepare(Session session, String sql, int action)
  {
    Prepared command = session.prepare(sql);
    if (action != 1)
    {
      ArrayList<Parameter> params = command.getParameters();
      int i = 0;
      for (int len = this.columns.length; i < len; i++)
      {
        Column column = this.columns[i].column;
        Parameter param = (Parameter)params.get(i);
        Value value;
        Value value;
        if (action == 3)
        {
          value = ValueNull.INSTANCE;
        }
        else
        {
          Expression expr = column.getDefaultExpression();
          if (expr == null) {
            throw DbException.get(23507, column.getName());
          }
          value = expr.getValue(session);
        }
        param.setValue(value);
      }
    }
    return command;
  }
  
  private void appendUpdate(StatementBuilder buff)
  {
    buff.append("UPDATE ").append(this.table.getSQL()).append(" SET ");
    buff.resetCount();
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(" , ");
      buff.append(Parser.quoteIdentifier(c.column.getName())).append("=?");
    }
  }
  
  private void appendWhere(StatementBuilder buff)
  {
    buff.append(" WHERE ");
    buff.resetCount();
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(" AND ");
      buff.append(Parser.quoteIdentifier(c.column.getName())).append("=?");
    }
  }
  
  public Table getRefTable()
  {
    return this.refTable;
  }
  
  public boolean usesIndex(Index idx)
  {
    return (idx == this.index) || (idx == this.refIndex);
  }
  
  public void setIndexOwner(Index index)
  {
    if (this.index == index) {
      this.indexOwner = true;
    } else if (this.refIndex == index) {
      this.refIndexOwner = true;
    } else {
      DbException.throwInternalError();
    }
  }
  
  public boolean isBefore()
  {
    return false;
  }
  
  public void checkExistingData(Session session)
  {
    if (session.getDatabase().isStarting()) {
      return;
    }
    session.startStatementWithinTransaction();
    StatementBuilder buff = new StatementBuilder("SELECT 1 FROM (SELECT ");
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    buff.append(" FROM ").append(this.table.getSQL()).append(" WHERE ");
    buff.resetCount();
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(" AND ");
      buff.append(c.getSQL()).append(" IS NOT NULL ");
    }
    buff.append(" ORDER BY ");
    buff.resetCount();
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    buff.append(") C WHERE NOT EXISTS(SELECT 1 FROM ").append(this.refTable.getSQL()).append(" P WHERE ");
    
    buff.resetCount();
    int i = 0;
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(" AND ");
      buff.append("C.").append(c.getSQL()).append('=').append("P.").append(this.refColumns[(i++)].getSQL());
    }
    buff.append(')');
    String sql = buff.toString();
    ResultInterface r = session.prepare(sql).query(1);
    if (r.next()) {
      throw DbException.get(23506, getShortDescription(null, null));
    }
  }
  
  public Index getUniqueIndex()
  {
    return this.refIndex;
  }
}
