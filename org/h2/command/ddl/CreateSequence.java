package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;
import org.h2.value.Value;

public class CreateSequence
  extends SchemaCommand
{
  private String sequenceName;
  private boolean ifNotExists;
  private boolean cycle;
  private Expression minValue;
  private Expression maxValue;
  private Expression start;
  private Expression increment;
  private Expression cacheSize;
  private boolean belongsToTable;
  
  public CreateSequence(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setSequenceName(String sequenceName)
  {
    this.sequenceName = sequenceName;
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setCycle(boolean cycle)
  {
    this.cycle = cycle;
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (getSchema().findSequence(this.sequenceName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90035, this.sequenceName);
    }
    int id = getObjectId();
    Long startValue = getLong(this.start);
    Long inc = getLong(this.increment);
    Long cache = getLong(this.cacheSize);
    Long min = getLong(this.minValue);
    Long max = getLong(this.maxValue);
    Sequence sequence = new Sequence(getSchema(), id, this.sequenceName, startValue, inc, cache, min, max, this.cycle, this.belongsToTable);
    
    db.addSchemaObject(this.session, sequence);
    return 0;
  }
  
  private Long getLong(Expression expr)
  {
    if (expr == null) {
      return null;
    }
    return Long.valueOf(expr.optimize(this.session).getValue(this.session).getLong());
  }
  
  public void setStartWith(Expression start)
  {
    this.start = start;
  }
  
  public void setIncrement(Expression increment)
  {
    this.increment = increment;
  }
  
  public void setMinValue(Expression minValue)
  {
    this.minValue = minValue;
  }
  
  public void setMaxValue(Expression maxValue)
  {
    this.maxValue = maxValue;
  }
  
  public void setBelongsToTable(boolean belongsToTable)
  {
    this.belongsToTable = belongsToTable;
  }
  
  public void setCacheSize(Expression cacheSize)
  {
    this.cacheSize = cacheSize;
  }
  
  public int getType()
  {
    return 29;
  }
}
