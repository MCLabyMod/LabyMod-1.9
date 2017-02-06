package org.h2.command.dml;

import java.util.ArrayList;
import java.util.HashMap;
import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.expression.Parameter;
import org.h2.expression.ValueExpression;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.result.RowList;
import org.h2.table.Column;
import org.h2.table.PlanItem;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class Update
  extends Prepared
{
  private Expression condition;
  private TableFilter tableFilter;
  private Expression limitExpr;
  private final ArrayList<Column> columns = New.arrayList();
  private final HashMap<Column, Expression> expressionMap = New.hashMap();
  
  public Update(Session session)
  {
    super(session);
  }
  
  public void setTableFilter(TableFilter tableFilter)
  {
    this.tableFilter = tableFilter;
  }
  
  public void setCondition(Expression condition)
  {
    this.condition = condition;
  }
  
  public void setAssignment(Column column, Expression expression)
  {
    if (this.expressionMap.containsKey(column)) {
      throw DbException.get(42121, column.getName());
    }
    this.columns.add(column);
    this.expressionMap.put(column, expression);
    if ((expression instanceof Parameter))
    {
      Parameter p = (Parameter)expression;
      p.setColumn(column);
    }
  }
  
  public int update()
  {
    this.tableFilter.startQuery(this.session);
    this.tableFilter.reset();
    RowList rows = new RowList(this.session);
    try
    {
      Table table = this.tableFilter.getTable();
      this.session.getUser().checkRight(table, 8);
      table.fire(this.session, 2, true);
      table.lock(this.session, true, false);
      int columnCount = table.getColumns().length;
      
      setCurrentRowNumber(0);
      int count = 0;
      Column[] columns = table.getColumns();
      int limitRows = -1;
      if (this.limitExpr != null)
      {
        Value v = this.limitExpr.getValue(this.session);
        if (v != ValueNull.INSTANCE) {
          limitRows = v.getInt();
        }
      }
      while (this.tableFilter.next())
      {
        setCurrentRowNumber(count + 1);
        if ((limitRows >= 0) && (count >= limitRows)) {
          break;
        }
        if ((this.condition == null) || (Boolean.TRUE.equals(this.condition.getBooleanValue(this.session))))
        {
          Row oldRow = this.tableFilter.get();
          Row newRow = table.getTemplateRow();
          for (int i = 0; i < columnCount; i++)
          {
            Expression newExpr = (Expression)this.expressionMap.get(columns[i]);
            Value newValue;
            Value newValue;
            if (newExpr == null)
            {
              newValue = oldRow.getValue(i);
            }
            else
            {
              Value newValue;
              if (newExpr == ValueExpression.getDefault())
              {
                Column column = table.getColumn(i);
                newValue = table.getDefaultValue(this.session, column);
              }
              else
              {
                Column column = table.getColumn(i);
                newValue = column.convert(newExpr.getValue(this.session));
              }
            }
            newRow.setValue(i, newValue);
          }
          table.validateConvertUpdateSequence(this.session, newRow);
          boolean done = false;
          if (table.fireRow()) {
            done = table.fireBeforeRow(this.session, oldRow, newRow);
          }
          if (!done)
          {
            rows.add(oldRow);
            rows.add(newRow);
          }
          count++;
        }
      }
      table.updateRows(this, this.session, rows);
      if (table.fireRow())
      {
        rows.invalidateCache();
        for (rows.reset(); rows.hasNext();)
        {
          o = rows.next();
          Row n = rows.next();
          table.fireAfterRow(this.session, o, n, false);
        }
      }
      Row o;
      table.fire(this.session, 2, false);
      return count;
    }
    finally
    {
      rows.close();
    }
  }
  
  public String getPlanSQL()
  {
    StatementBuilder buff = new StatementBuilder("UPDATE ");
    buff.append(this.tableFilter.getPlanSQL(false)).append("\nSET\n    ");
    int i = 0;
    for (int size = this.columns.size(); i < size; i++)
    {
      Column c = (Column)this.columns.get(i);
      Expression e = (Expression)this.expressionMap.get(c);
      buff.appendExceptFirst(",\n    ");
      buff.append(c.getName()).append(" = ").append(e.getSQL());
    }
    if (this.condition != null) {
      buff.append("\nWHERE ").append(StringUtils.unEnclose(this.condition.getSQL()));
    }
    return buff.toString();
  }
  
  public void prepare()
  {
    if (this.condition != null)
    {
      this.condition.mapColumns(this.tableFilter, 0);
      this.condition = this.condition.optimize(this.session);
      this.condition.createIndexConditions(this.session, this.tableFilter);
    }
    int i = 0;
    for (int size = this.columns.size(); i < size; i++)
    {
      Column c = (Column)this.columns.get(i);
      Expression e = (Expression)this.expressionMap.get(c);
      e.mapColumns(this.tableFilter, 0);
      this.expressionMap.put(c, e.optimize(this.session));
    }
    PlanItem item = this.tableFilter.getBestPlanItem(this.session, 1);
    this.tableFilter.setPlanItem(item);
    this.tableFilter.prepare();
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
    return 68;
  }
  
  public void setLimit(Expression limit)
  {
    this.limitExpr = limit;
  }
  
  public boolean isCacheable()
  {
    return true;
  }
}
