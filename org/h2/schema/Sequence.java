package org.h2.schema;

import java.math.BigInteger;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.table.Table;

public class Sequence
  extends SchemaObjectBase
{
  public static final int DEFAULT_CACHE_SIZE = 32;
  private long value;
  private long valueWithMargin;
  private long increment;
  private long cacheSize;
  private long minValue;
  private long maxValue;
  private boolean cycle;
  private boolean belongsToTable;
  
  public Sequence(Schema schema, int id, String name, long startValue, long increment)
  {
    this(schema, id, name, Long.valueOf(startValue), Long.valueOf(increment), null, null, null, false, true);
  }
  
  public Sequence(Schema schema, int id, String name, Long startValue, Long increment, Long cacheSize, Long minValue, Long maxValue, boolean cycle, boolean belongsToTable)
  {
    initSchemaObjectBase(schema, id, name, "sequence");
    this.increment = (increment != null ? increment.longValue() : 1L);
    
    this.minValue = (minValue != null ? minValue.longValue() : getDefaultMinValue(startValue, this.increment));
    
    this.maxValue = (maxValue != null ? maxValue.longValue() : getDefaultMaxValue(startValue, this.increment));
    
    this.value = (startValue != null ? startValue.longValue() : getDefaultStartValue(this.increment));
    
    this.valueWithMargin = this.value;
    this.cacheSize = (cacheSize != null ? Math.max(1L, cacheSize.longValue()) : 32L);
    
    this.cycle = cycle;
    this.belongsToTable = belongsToTable;
    if (!isValid(this.value, this.minValue, this.maxValue, this.increment)) {
      throw DbException.get(90009, new String[] { name, String.valueOf(this.value), String.valueOf(this.minValue), String.valueOf(this.maxValue), String.valueOf(this.increment) });
    }
  }
  
  public synchronized void modify(Long startValue, Long minValue, Long maxValue, Long increment)
  {
    if (startValue == null) {
      startValue = Long.valueOf(this.value);
    }
    if (minValue == null) {
      minValue = Long.valueOf(this.minValue);
    }
    if (maxValue == null) {
      maxValue = Long.valueOf(this.maxValue);
    }
    if (increment == null) {
      increment = Long.valueOf(this.increment);
    }
    if (!isValid(startValue.longValue(), minValue.longValue(), maxValue.longValue(), increment.longValue())) {
      throw DbException.get(90009, new String[] { getName(), String.valueOf(startValue), String.valueOf(minValue), String.valueOf(maxValue), String.valueOf(increment) });
    }
    this.value = startValue.longValue();
    this.valueWithMargin = startValue.longValue();
    this.minValue = minValue.longValue();
    this.maxValue = maxValue.longValue();
    this.increment = increment.longValue();
  }
  
  private static boolean isValid(long value, long minValue, long maxValue, long increment)
  {
    return (minValue <= value) && (maxValue >= value) && (maxValue > minValue) && (increment != 0L) && (BigInteger.valueOf(increment).abs().compareTo(BigInteger.valueOf(maxValue).subtract(BigInteger.valueOf(minValue))) < 0);
  }
  
  private static long getDefaultMinValue(Long startValue, long increment)
  {
    long v = increment >= 0L ? 1L : Long.MIN_VALUE;
    if ((startValue != null) && (increment >= 0L) && (startValue.longValue() < v)) {
      v = startValue.longValue();
    }
    return v;
  }
  
  private static long getDefaultMaxValue(Long startValue, long increment)
  {
    long v = increment >= 0L ? Long.MAX_VALUE : -1L;
    if ((startValue != null) && (increment < 0L) && (startValue.longValue() > v)) {
      v = startValue.longValue();
    }
    return v;
  }
  
  private long getDefaultStartValue(long increment)
  {
    return increment >= 0L ? this.minValue : this.maxValue;
  }
  
  public boolean getBelongsToTable()
  {
    return this.belongsToTable;
  }
  
  public long getIncrement()
  {
    return this.increment;
  }
  
  public long getMinValue()
  {
    return this.minValue;
  }
  
  public long getMaxValue()
  {
    return this.maxValue;
  }
  
  public boolean getCycle()
  {
    return this.cycle;
  }
  
  public void setCycle(boolean cycle)
  {
    this.cycle = cycle;
  }
  
  public String getDropSQL()
  {
    if (getBelongsToTable()) {
      return null;
    }
    return "DROP SEQUENCE IF EXISTS " + getSQL();
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public synchronized String getCreateSQL()
  {
    StringBuilder buff = new StringBuilder("CREATE SEQUENCE ");
    buff.append(getSQL()).append(" START WITH ").append(this.value);
    if (this.increment != 1L) {
      buff.append(" INCREMENT BY ").append(this.increment);
    }
    if (this.minValue != getDefaultMinValue(Long.valueOf(this.value), this.increment)) {
      buff.append(" MINVALUE ").append(this.minValue);
    }
    if (this.maxValue != getDefaultMaxValue(Long.valueOf(this.value), this.increment)) {
      buff.append(" MAXVALUE ").append(this.maxValue);
    }
    if (this.cycle) {
      buff.append(" CYCLE");
    }
    if (this.cacheSize != 32L) {
      buff.append(" CACHE ").append(this.cacheSize);
    }
    if (this.belongsToTable) {
      buff.append(" BELONGS_TO_TABLE");
    }
    return buff.toString();
  }
  
  public synchronized long getNext(Session session)
  {
    boolean needsFlush = false;
    if (((this.increment > 0L) && (this.value >= this.valueWithMargin)) || ((this.increment < 0L) && (this.value <= this.valueWithMargin)))
    {
      this.valueWithMargin += this.increment * this.cacheSize;
      needsFlush = true;
    }
    if (((this.increment > 0L) && (this.value > this.maxValue)) || ((this.increment < 0L) && (this.value < this.minValue))) {
      if (this.cycle)
      {
        this.value = (this.increment > 0L ? this.minValue : this.maxValue);
        this.valueWithMargin = (this.value + this.increment * this.cacheSize);
        needsFlush = true;
      }
      else
      {
        throw DbException.get(90006, getName());
      }
    }
    if (needsFlush) {
      flush(session);
    }
    long v = this.value;
    this.value += this.increment;
    return v;
  }
  
  public void flushWithoutMargin()
  {
    if (this.valueWithMargin != this.value)
    {
      this.valueWithMargin = this.value;
      flush(null);
    }
  }
  
  public synchronized void flush(Session session)
  {
    if ((session == null) || (!this.database.isSysTableLocked()))
    {
      Session sysSession = this.database.getSystemSession();
      synchronized (sysSession)
      {
        flushInternal(sysSession);
        sysSession.commit(false);
      }
    }
    else
    {
      synchronized (session)
      {
        flushInternal(session);
      }
    }
  }
  
  private void flushInternal(Session session)
  {
    long realValue = this.value;
    try
    {
      this.value = this.valueWithMargin;
      if (!isTemporary()) {
        this.database.updateMeta(session, this);
      }
    }
    finally
    {
      this.value = realValue;
    }
  }
  
  public void close()
  {
    flushWithoutMargin();
  }
  
  public int getType()
  {
    return 3;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
    invalidate();
  }
  
  public void checkRename() {}
  
  public synchronized long getCurrentValue()
  {
    return this.value - this.increment;
  }
  
  public void setBelongsToTable(boolean b)
  {
    this.belongsToTable = b;
  }
  
  public void setCacheSize(long cacheSize)
  {
    this.cacheSize = Math.max(1L, cacheSize);
  }
  
  public long getCacheSize()
  {
    return this.cacheSize;
  }
}
