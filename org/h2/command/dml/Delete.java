package org.h2.command.dml;

import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.result.RowList;
import org.h2.table.PlanItem;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class Delete
  extends Prepared
{
  private Expression condition;
  private TableFilter tableFilter;
  private Expression limitExpr;
  
  public Delete(Session session)
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
  
  public int update()
  {
    this.tableFilter.startQuery(this.session);
    this.tableFilter.reset();
    Table table = this.tableFilter.getTable();
    this.session.getUser().checkRight(table, 2);
    table.fire(this.session, 4, true);
    table.lock(this.session, true, false);
    RowList rows = new RowList(this.session);
    int limitRows = -1;
    if (this.limitExpr != null)
    {
      Value v = this.limitExpr.getValue(this.session);
      if (v != ValueNull.INSTANCE) {
        limitRows = v.getInt();
      }
    }
    try
    {
      setCurrentRowNumber(0);
      int count = 0;
      while ((limitRows != 0) && (this.tableFilter.next()))
      {
        setCurrentRowNumber(rows.size() + 1);
        if ((this.condition == null) || (Boolean.TRUE.equals(this.condition.getBooleanValue(this.session))))
        {
          Row row = this.tableFilter.get();
          boolean done = false;
          if (table.fireRow()) {
            done = table.fireBeforeRow(this.session, row, null);
          }
          if (!done) {
            rows.add(row);
          }
          count++;
          if ((limitRows >= 0) && (count >= limitRows)) {
            break;
          }
        }
      }
      int rowScanCount = 0;
      for (rows.reset(); rows.hasNext();)
      {
        rowScanCount++;
        if ((rowScanCount & 0x7F) == 0) {
          checkCanceled();
        }
        Row row = rows.next();
        table.removeRow(this.session, row);
        this.session.log(table, (short)1, row);
      }
      if (table.fireRow()) {
        for (rows.reset(); rows.hasNext();)
        {
          row = rows.next();
          table.fireAfterRow(this.session, row, null, false);
        }
      }
      Row row;
      table.fire(this.session, 4, false);
      return count;
    }
    finally
    {
      rows.close();
    }
  }
  
  public String getPlanSQL()
  {
    StringBuilder buff = new StringBuilder();
    buff.append("DELETE ");
    buff.append("FROM ").append(this.tableFilter.getPlanSQL(false));
    if (this.condition != null) {
      buff.append("\nWHERE ").append(StringUtils.unEnclose(this.condition.getSQL()));
    }
    if (this.limitExpr != null) {
      buff.append("\nLIMIT (").append(StringUtils.unEnclose(this.limitExpr.getSQL())).append(')');
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
    return 58;
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
