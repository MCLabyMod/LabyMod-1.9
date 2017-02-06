package org.h2.command.dml;

import java.util.ArrayList;
import org.h2.command.Command;
import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.expression.Parameter;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;

public class Replace
  extends Prepared
{
  private Table table;
  private Column[] columns;
  private Column[] keys;
  private final ArrayList<Expression[]> list = New.arrayList();
  private Query query;
  private Prepared update;
  
  public Replace(Session session)
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
  
  public void setKeys(Column[] keys)
  {
    this.keys = keys;
  }
  
  public void setQuery(Query query)
  {
    this.query = query;
  }
  
  public void addRow(Expression[] expr)
  {
    this.list.add(expr);
  }
  
  public int update()
  {
    this.session.getUser().checkRight(this.table, 4);
    this.session.getUser().checkRight(this.table, 8);
    setCurrentRowNumber(0);
    int count;
    if (this.list.size() > 0)
    {
      int count = 0;
      int x = 0;
      for (int size = this.list.size(); x < size; x++)
      {
        setCurrentRowNumber(x + 1);
        Expression[] expr = (Expression[])this.list.get(x);
        Row newRow = this.table.getTemplateRow();
        int i = 0;
        for (int len = this.columns.length; i < len; i++)
        {
          Column c = this.columns[i];
          int index = c.getColumnId();
          Expression e = expr[i];
          if (e != null) {
            try
            {
              Value v = c.convert(e.getValue(this.session));
              newRow.setValue(index, v);
            }
            catch (DbException ex)
            {
              throw setRow(ex, count, getSQL(expr));
            }
          }
        }
        replace(newRow);
        count++;
      }
    }
    else
    {
      ResultInterface rows = this.query.query(0);
      count = 0;
      this.table.fire(this.session, 3, true);
      this.table.lock(this.session, true, false);
      while (rows.next())
      {
        count++;
        Value[] r = rows.currentRow();
        Row newRow = this.table.getTemplateRow();
        setCurrentRowNumber(count);
        for (int j = 0; j < this.columns.length; j++)
        {
          Column c = this.columns[j];
          int index = c.getColumnId();
          try
          {
            Value v = c.convert(r[j]);
            newRow.setValue(index, v);
          }
          catch (DbException ex)
          {
            throw setRow(ex, count, getSQL(r));
          }
        }
        replace(newRow);
      }
      rows.close();
      this.table.fire(this.session, 3, false);
    }
    return count;
  }
  
  private void replace(Row row)
  {
    int count = update(row);
    if (count == 0) {
      try
      {
        this.table.validateConvertUpdateSequence(this.session, row);
        boolean done = this.table.fireBeforeRow(this.session, null, row);
        if (!done)
        {
          this.table.lock(this.session, true, false);
          this.table.addRow(this.session, row);
          this.session.log(this.table, (short)0, row);
          this.table.fireAfterRow(this.session, null, row, false);
        }
      }
      catch (DbException e)
      {
        if (e.getErrorCode() == 23505)
        {
          Index index = (Index)e.getSource();
          if (index != null)
          {
            Column[] indexColumns = index.getColumns();
            boolean indexMatchesKeys = false;
            if (indexColumns.length <= this.keys.length) {
              for (int i = 0; i < indexColumns.length; i++) {
                if (indexColumns[i] != this.keys[i])
                {
                  indexMatchesKeys = false;
                  break;
                }
              }
            }
            if (indexMatchesKeys) {
              throw DbException.get(90131, this.table.getName());
            }
          }
        }
        throw e;
      }
    } else if (count != 1) {
      throw DbException.get(23505, this.table.getSQL());
    }
  }
  
  private int update(Row row)
  {
    if (this.update == null) {
      return 0;
    }
    ArrayList<Parameter> k = this.update.getParameters();
    for (int i = 0; i < this.columns.length; i++)
    {
      Column col = this.columns[i];
      Value v = row.getValue(col.getColumnId());
      Parameter p = (Parameter)k.get(i);
      p.setValue(v);
    }
    for (int i = 0; i < this.keys.length; i++)
    {
      Column col = this.keys[i];
      Value v = row.getValue(col.getColumnId());
      if (v == null) {
        throw DbException.get(90081, col.getSQL());
      }
      Parameter p = (Parameter)k.get(this.columns.length + i);
      p.setValue(v);
    }
    return this.update.update();
  }
  
  public String getPlanSQL()
  {
    StatementBuilder buff = new StatementBuilder("REPLACE INTO ");
    buff.append(this.table.getSQL()).append('(');
    for (Column c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    buff.append(')');
    buff.append('\n');
    int row;
    if (this.list.size() > 0)
    {
      buff.append("VALUES ");
      row = 0;
      for (Expression[] expr : this.list)
      {
        if (row++ > 0) {
          buff.append(", ");
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
        for (int i = 0; i < expr.length; i++)
        {
          Expression e = expr[i];
          if (e != null) {
            expr[i] = e.optimize(this.session);
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
    if (this.keys == null)
    {
      Index idx = this.table.getPrimaryKey();
      if (idx == null) {
        throw DbException.get(90057, "PRIMARY KEY");
      }
      this.keys = idx.getColumns();
    }
    for (Column key : this.keys)
    {
      boolean found = false;
      for (Column column : this.columns) {
        if (column.getColumnId() == key.getColumnId())
        {
          found = true;
          break;
        }
      }
      if (!found) {
        return;
      }
    }
    StatementBuilder buff = new StatementBuilder("UPDATE ");
    buff.append(this.table.getSQL()).append(" SET ");
    for (Column c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL()).append("=?");
    }
    buff.append(" WHERE ");
    buff.resetCount();
    for (Column c : this.keys)
    {
      buff.appendExceptFirst(" AND ");
      buff.append(c.getSQL()).append("=?");
    }
    String sql = buff.toString();
    this.update = this.session.prepare(sql);
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public int getType()
  {
    return 63;
  }
  
  public boolean isCacheable()
  {
    return true;
  }
}
