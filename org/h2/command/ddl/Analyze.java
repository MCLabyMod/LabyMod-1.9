package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Parameter;
import org.h2.result.ResultInterface;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueNull;

public class Analyze
  extends DefineCommand
{
  private int sampleRows;
  
  public Analyze(Session session)
  {
    super(session);
    this.sampleRows = session.getDatabase().getSettings().analyzeSample;
  }
  
  public int update()
  {
    this.session.commit(true);
    this.session.getUser().checkAdmin();
    Database db = this.session.getDatabase();
    for (Table table : db.getAllTablesAndViews(false)) {
      analyzeTable(this.session, table, this.sampleRows, true);
    }
    return 0;
  }
  
  public static void analyzeTable(Session session, Table table, int sample, boolean manual)
  {
    if ((!table.getTableType().equals("TABLE")) || (table.isHidden()) || (session == null)) {
      return;
    }
    if (!manual)
    {
      if (session.getDatabase().isSysTableLocked()) {
        return;
      }
      if (table.hasSelectTrigger()) {
        return;
      }
    }
    if ((table.isTemporary()) && (!table.isGlobalTemporary()) && (session.findLocalTempTable(table.getName()) == null)) {
      return;
    }
    if ((table.isLockedExclusively()) && (!table.isLockedExclusivelyBy(session))) {
      return;
    }
    if (!session.getUser().hasRight(table, 1)) {
      return;
    }
    if (session.getCancel() != 0L) {
      return;
    }
    Column[] columns = table.getColumns();
    if (columns.length == 0) {
      return;
    }
    Database db = session.getDatabase();
    StatementBuilder buff = new StatementBuilder("SELECT ");
    for (Column col : columns)
    {
      buff.appendExceptFirst(", ");
      int type = col.getType();
      if ((type == 15) || (type == 16)) {
        buff.append("MAX(NULL)");
      } else {
        buff.append("SELECTIVITY(").append(col.getSQL()).append(')');
      }
    }
    buff.append(" FROM ").append(table.getSQL());
    if (sample > 0) {
      buff.append(" LIMIT ? SAMPLE_SIZE ? ");
    }
    String sql = buff.toString();
    Prepared command = session.prepare(sql);
    if (sample > 0)
    {
      ArrayList<Parameter> params = command.getParameters();
      ((Parameter)params.get(0)).setValue(ValueInt.get(1));
      ((Parameter)params.get(1)).setValue(ValueInt.get(sample));
    }
    ResultInterface result = command.query(0);
    result.next();
    for (int j = 0; j < columns.length; j++)
    {
      Value v = result.currentRow()[j];
      if (v != ValueNull.INSTANCE)
      {
        int selectivity = v.getInt();
        columns[j].setSelectivity(selectivity);
      }
    }
    if (manual)
    {
      db.updateMeta(session, table);
    }
    else
    {
      Session sysSession = db.getSystemSession();
      if (sysSession != session) {
        synchronized (sysSession)
        {
          synchronized (db)
          {
            db.updateMeta(sysSession, table);
            sysSession.commit(true);
          }
        }
      }
    }
  }
  
  public void setTop(int top)
  {
    this.sampleRows = top;
  }
  
  public int getType()
  {
    return 21;
  }
}
