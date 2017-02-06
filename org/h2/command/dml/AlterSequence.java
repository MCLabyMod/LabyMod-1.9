package org.h2.command.dml;

import org.h2.command.ddl.SchemaCommand;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.value.Value;

public class AlterSequence
  extends SchemaCommand
{
  private Table table;
  private Sequence sequence;
  private Expression start;
  private Expression increment;
  private Boolean cycle;
  private Expression minValue;
  private Expression maxValue;
  private Expression cacheSize;
  
  public AlterSequence(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setSequence(Sequence sequence)
  {
    this.sequence = sequence;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public void setColumn(Column column)
  {
    this.table = column.getTable();
    this.sequence = column.getSequence();
    if (this.sequence == null) {
      throw DbException.get(90036, column.getSQL());
    }
  }
  
  public void setStartWith(Expression start)
  {
    this.start = start;
  }
  
  public void setIncrement(Expression increment)
  {
    this.increment = increment;
  }
  
  public void setCycle(Boolean cycle)
  {
    this.cycle = cycle;
  }
  
  public void setMinValue(Expression minValue)
  {
    this.minValue = minValue;
  }
  
  public void setMaxValue(Expression maxValue)
  {
    this.maxValue = maxValue;
  }
  
  public void setCacheSize(Expression cacheSize)
  {
    this.cacheSize = cacheSize;
  }
  
  public int update()
  {
    Database db = this.session.getDatabase();
    if (this.table != null) {
      this.session.getUser().checkRight(this.table, 15);
    }
    if (this.cycle != null) {
      this.sequence.setCycle(this.cycle.booleanValue());
    }
    if (this.cacheSize != null)
    {
      long size = this.cacheSize.optimize(this.session).getValue(this.session).getLong();
      this.sequence.setCacheSize(size);
    }
    if ((this.start != null) || (this.minValue != null) || (this.maxValue != null) || (this.increment != null))
    {
      Long startValue = getLong(this.start);
      Long min = getLong(this.minValue);
      Long max = getLong(this.maxValue);
      Long inc = getLong(this.increment);
      this.sequence.modify(startValue, min, max, inc);
    }
    Session sysSession = db.getSystemSession();
    synchronized (sysSession)
    {
      synchronized (db)
      {
        db.updateMeta(sysSession, this.sequence);
        sysSession.commit(true);
      }
    }
    return 0;
  }
  
  private Long getLong(Expression expr)
  {
    if (expr == null) {
      return null;
    }
    return Long.valueOf(expr.optimize(this.session).getValue(this.session).getLong());
  }
  
  public int getType()
  {
    return 54;
  }
}
