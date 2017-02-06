package org.h2.command.dml;

import java.util.ArrayList;
import java.util.HashMap;
import org.h2.command.Command;
import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Comparison;
import org.h2.expression.ConditionAndOr;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.Parameter;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.ResultTarget;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class Insert
  extends Prepared
  implements ResultTarget
{
  private Table table;
  private Column[] columns;
  private final ArrayList<Expression[]> list = New.arrayList();
  private Query query;
  private boolean sortedInsertMode;
  private int rowNumber;
  private boolean insertFromSelect;
  private HashMap<Column, Expression> duplicateKeyAssignmentMap;
  
  public Insert(Session session)
  {
    super(session);
  }
  
  public void setCommand(Command command)
  {
    super.setCommand(command);
    if (this.query != null) {
      this.query.setCommand(command);
    }
  }
  
  public void setTable(Table table)
  {
    this.table = table;
  }
  
  public void setColumns(Column[] columns)
  {
    this.columns = columns;
  }
  
  public void setQuery(Query query)
  {
    this.query = query;
  }
  
  public void addAssignmentForDuplicate(Column column, Expression expression)
  {
    if (this.duplicateKeyAssignmentMap == null) {
      this.duplicateKeyAssignmentMap = New.hashMap();
    }
    if (this.duplicateKeyAssignmentMap.containsKey(column)) {
      throw DbException.get(42121, column.getName());
    }
    this.duplicateKeyAssignmentMap.put(column, expression);
  }
  
  public void addRow(Expression[] expr)
  {
    this.list.add(expr);
  }
  
  public int update()
  {
    Index index = null;
    if (this.sortedInsertMode)
    {
      index = this.table.getScanIndex(this.session);
      index.setSortedInsertMode(true);
    }
    try
    {
      return insertRows();
    }
    finally
    {
      if (index != null) {
        index.setSortedInsertMode(false);
      }
    }
  }
  
  private int insertRows()
  {
    this.session.getUser().checkRight(this.table, 4);
    setCurrentRowNumber(0);
    this.table.fire(this.session, 1, true);
    this.rowNumber = 0;
    int listSize = this.list.size();
    if (listSize > 0)
    {
      int columnLen = this.columns.length;
      for (int x = 0; x < listSize; x++)
      {
        this.session.startStatementWithinTransaction();
        Row newRow = this.table.getTemplateRow();
        Expression[] expr = (Expression[])this.list.get(x);
        setCurrentRowNumber(x + 1);
        for (int i = 0; i < columnLen; i++)
        {
          Column c = this.columns[i];
          int index = c.getColumnId();
          Expression e = expr[i];
          if (e != null)
          {
            e = e.optimize(this.session);
            try
            {
              Value v = c.convert(e.getValue(this.session));
              newRow.setValue(index, v);
            }
            catch (DbException ex)
            {
              throw setRow(ex, x, getSQL(expr));
            }
          }
        }
        this.rowNumber += 1;
        this.table.validateConvertUpdateSequence(this.session, newRow);
        boolean done = this.table.fireBeforeRow(this.session, null, newRow);
        if (!done)
        {
          this.table.lock(this.session, true, false);
          try
          {
            this.table.addRow(this.session, newRow);
          }
          catch (DbException de)
          {
            handleOnDuplicate(de);
          }
          this.session.log(this.table, (short)0, newRow);
          this.table.fireAfterRow(this.session, null, newRow, false);
        }
      }
    }
    else
    {
      this.table.lock(this.session, true, false);
      if (this.insertFromSelect)
      {
        this.query.query(0, this);
      }
      else
      {
        ResultInterface rows = this.query.query(0);
        while (rows.next())
        {
          Value[] r = rows.currentRow();
          addRow(r);
        }
        rows.close();
      }
    }
    this.table.fire(this.session, 1, false);
    return this.rowNumber;
  }
  
  public void addRow(Value[] values)
  {
    Row newRow = this.table.getTemplateRow();
    setCurrentRowNumber(++this.rowNumber);
    int j = 0;
    for (int len = this.columns.length; j < len; j++)
    {
      Column c = this.columns[j];
      int index = c.getColumnId();
      try
      {
        Value v = c.convert(values[j]);
        newRow.setValue(index, v);
      }
      catch (DbException ex)
      {
        throw setRow(ex, this.rowNumber, getSQL(values));
      }
    }
    this.table.validateConvertUpdateSequence(this.session, newRow);
    boolean done = this.table.fireBeforeRow(this.session, null, newRow);
    if (!done)
    {
      this.table.addRow(this.session, newRow);
      this.session.log(this.table, (short)0, newRow);
      this.table.fireAfterRow(this.session, null, newRow, false);
    }
  }
  
  public int getRowCount()
  {
    return this.rowNumber;
  }
  
  public String getPlanSQL()
  {
    StatementBuilder buff = new StatementBuilder("INSERT INTO ");
    buff.append(this.table.getSQL()).append('(');
    for (Column c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    buff.append(")\n");
    if (this.insertFromSelect) {
      buff.append("DIRECT ");
    }
    if (this.sortedInsertMode) {
      buff.append("SORTED ");
    }
    int row;
    if (this.list.size() > 0)
    {
      buff.append("VALUES ");
      row = 0;
      if (this.list.size() > 1) {
        buff.append('\n');
      }
      for (Expression[] expr : this.list)
      {
        if (row++ > 0) {
          buff.append(",\n");
        }
        buff.append('(');
        buff.resetCount();
        for (Expression e : expr)
        {
          buff.appendExceptFirst(", ");
          if (e == null) {
            buff.append("DEFAULT");
          } else {
            buff.append(e.getSQL());
          }
        }
        buff.append(')');
      }
    }
    else
    {
      buff.append(this.query.getPlanSQL());
    }
    return buff.toString();
  }
  
  public void prepare()
  {
    if (this.columns == null) {
      if ((this.list.size() > 0) && (((Expression[])this.list.get(0)).length == 0)) {
        this.columns = new Column[0];
      } else {
        this.columns = this.table.getColumns();
      }
    }
    if (this.list.size() > 0)
    {
      for (Expression[] expr : this.list)
      {
        if (expr.length != this.columns.length) {
          throw DbException.get(21002);
        }
        int i = 0;
        for (int len = expr.length; i < len; i++)
        {
          Expression e = expr[i];
          if (e != null)
          {
            e = e.optimize(this.session);
            if ((e instanceof Parameter))
            {
              Parameter p = (Parameter)e;
              p.setColumn(this.columns[i]);
            }
            expr[i] = e;
          }
        }
      }
    }
    else
    {
      this.query.prepare();
      if (this.query.getColumnCount() != this.columns.length) {
        throw DbException.get(21002);
      }
    }
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public void setSortedInsertMode(boolean sortedInsertMode)
  {
    this.sortedInsertMode = sortedInsertMode;
  }
  
  public int getType()
  {
    return 61;
  }
  
  public void setInsertFromSelect(boolean value)
  {
    this.insertFromSelect = value;
  }
  
  public boolean isCacheable()
  {
    return (this.duplicateKeyAssignmentMap == null) || (this.duplicateKeyAssignmentMap.isEmpty());
  }
  
  private void handleOnDuplicate(DbException de)
  {
    if (de.getErrorCode() != 23505) {
      throw de;
    }
    if ((this.duplicateKeyAssignmentMap == null) || (this.duplicateKeyAssignmentMap.isEmpty())) {
      throw de;
    }
    ArrayList<String> variableNames = new ArrayList(this.duplicateKeyAssignmentMap.size());
    for (int i = 0; i < this.columns.length; i++)
    {
      String key = this.table.getSchema().getName() + "." + this.table.getName() + "." + this.columns[i].getName();
      
      variableNames.add(key);
      this.session.setVariable(key, ((Expression[])this.list.get(getCurrentRowNumber() - 1))[i].getValue(this.session));
    }
    StatementBuilder buff = new StatementBuilder("UPDATE ");
    buff.append(this.table.getSQL()).append(" SET ");
    for (Column column : this.duplicateKeyAssignmentMap.keySet())
    {
      buff.appendExceptFirst(", ");
      Expression ex = (Expression)this.duplicateKeyAssignmentMap.get(column);
      buff.append(column.getSQL()).append("=").append(ex.getSQL());
    }
    buff.append(" WHERE ");
    Index foundIndex = searchForUpdateIndex();
    if (foundIndex == null) {
      throw DbException.getUnsupportedException("Unable to apply ON DUPLICATE KEY UPDATE, no index found!");
    }
    buff.append(prepareUpdateCondition(foundIndex).getSQL());
    String sql = buff.toString();
    Prepared command = this.session.prepare(sql);
    for (Parameter param : command.getParameters())
    {
      Parameter insertParam = (Parameter)this.parameters.get(param.getIndex());
      param.setValue(insertParam.getValue(this.session));
    }
    command.update();
    for (String variableName : variableNames) {
      this.session.setVariable(variableName, ValueNull.INSTANCE);
    }
  }
  
  private Index searchForUpdateIndex()
  {
    Index foundIndex = null;
    for (Index index : this.table.getIndexes()) {
      if ((index.getIndexType().isPrimaryKey()) || (index.getIndexType().isUnique()))
      {
        for (Column indexColumn : index.getColumns())
        {
          for (Column insertColumn : this.columns)
          {
            if (indexColumn.getName().equals(insertColumn.getName()))
            {
              foundIndex = index;
              break;
            }
            foundIndex = null;
          }
          if (foundIndex == null) {
            break;
          }
        }
        if (foundIndex != null) {
          break;
        }
      }
    }
    return foundIndex;
  }
  
  private Expression prepareUpdateCondition(Index foundIndex)
  {
    Expression condition = null;
    for (Column column : foundIndex.getColumns())
    {
      ExpressionColumn expr = new ExpressionColumn(this.session.getDatabase(), this.table.getSchema().getName(), this.table.getName(), column.getName());
      for (int i = 0; i < this.columns.length; i++) {
        if (expr.getColumnName().equals(this.columns[i].getName())) {
          if (condition == null) {
            condition = new Comparison(this.session, 0, expr, ((Expression[])this.list.get(getCurrentRowNumber() - 1))[(i++)]);
          } else {
            condition = new ConditionAndOr(0, condition, new Comparison(this.session, 0, expr, ((Expression[])this.list.get(0))[(i++)]));
          }
        }
      }
    }
    return condition;
  }
}
