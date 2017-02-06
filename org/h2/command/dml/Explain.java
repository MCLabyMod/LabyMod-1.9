package org.h2.command.dml;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.store.PageStore;
import org.h2.table.Column;
import org.h2.value.Value;
import org.h2.value.ValueString;

public class Explain
  extends Prepared
{
  private Prepared command;
  private LocalResult result;
  private boolean executeCommand;
  
  public Explain(Session session)
  {
    super(session);
  }
  
  public void setCommand(Prepared command)
  {
    this.command = command;
  }
  
  public void prepare()
  {
    this.command.prepare();
  }
  
  public void setExecuteCommand(boolean executeCommand)
  {
    this.executeCommand = executeCommand;
  }
  
  public ResultInterface queryMeta()
  {
    return query(-1);
  }
  
  public ResultInterface query(int maxrows)
  {
    Column column = new Column("PLAN", 13);
    Database db = this.session.getDatabase();
    ExpressionColumn expr = new ExpressionColumn(db, column);
    Expression[] expressions = { expr };
    this.result = new LocalResult(this.session, expressions, 1);
    if (maxrows >= 0)
    {
      String plan;
      if (this.executeCommand)
      {
        PageStore store = null;
        MVTableEngine.Store mvStore = null;
        if (db.isPersistent())
        {
          store = db.getPageStore();
          if (store != null) {
            store.statisticsStart();
          }
          mvStore = db.getMvStore();
          if (mvStore != null) {
            mvStore.statisticsStart();
          }
        }
        if (this.command.isQuery()) {
          this.command.query(maxrows);
        } else {
          this.command.update();
        }
        String plan = this.command.getPlanSQL();
        Map<String, Integer> statistics = null;
        if (store != null) {
          statistics = store.statisticsEnd();
        } else if (mvStore != null) {
          statistics = mvStore.statisticsEnd();
        }
        if (statistics != null)
        {
          int total = 0;
          for (Map.Entry<String, Integer> e : statistics.entrySet()) {
            total += ((Integer)e.getValue()).intValue();
          }
          if (total > 0)
          {
            statistics = new TreeMap(statistics);
            StringBuilder buff = new StringBuilder();
            if (statistics.size() > 1) {
              buff.append("total: ").append(total).append('\n');
            }
            for (Map.Entry<String, Integer> e : statistics.entrySet())
            {
              int value = ((Integer)e.getValue()).intValue();
              int percent = (int)(100L * value / total);
              buff.append((String)e.getKey()).append(": ").append(value);
              if (statistics.size() > 1) {
                buff.append(" (").append(percent).append("%)");
              }
              buff.append('\n');
            }
            plan = plan + "\n/*\n" + buff.toString() + "*/";
          }
        }
      }
      else
      {
        plan = this.command.getPlanSQL();
      }
      add(plan);
    }
    this.result.done();
    return this.result;
  }
  
  private void add(String text)
  {
    Value[] row = { ValueString.get(text) };
    this.result.addRow(row);
  }
  
  public boolean isQuery()
  {
    return true;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return this.command.isReadOnly();
  }
  
  public int getType()
  {
    return 60;
  }
}
