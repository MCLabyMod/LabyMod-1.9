package org.h2.table;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.RangeIndex;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.value.Value;

public class RangeTable
  extends Table
{
  public static final String NAME = "SYSTEM_RANGE";
  public static final String ALIAS = "GENERATE_SERIES";
  private Expression min;
  private Expression max;
  private Expression step;
  private boolean optimized;
  
  public RangeTable(Schema schema, Expression min, Expression max, boolean noColumns)
  {
    super(schema, 0, "SYSTEM_RANGE", true, true);
    Column[] cols = { noColumns ? new Column[0] : new Column("X", 5) };
    
    this.min = min;
    this.max = max;
    setColumns(cols);
  }
  
  public RangeTable(Schema schema, Expression min, Expression max, Expression step, boolean noColumns)
  {
    this(schema, min, max, noColumns);
    this.step = step;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public String getSQL()
  {
    String sql = "SYSTEM_RANGE(" + this.min.getSQL() + ", " + this.max.getSQL();
    if (this.step != null) {
      sql = sql + ", " + this.step.getSQL();
    }
    return sql + ")";
  }
  
  public boolean lock(Session session, boolean exclusive, boolean forceLockEvenInMvcc)
  {
    return false;
  }
  
  public void close(Session session) {}
  
  public void unlock(Session s) {}
  
  public boolean isLockedExclusively()
  {
    return false;
  }
  
  public Index addIndex(Session session, String indexName, int indexId, IndexColumn[] cols, IndexType indexType, boolean create, String indexComment)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public void removeRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public void addRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public void checkSupportAlter()
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public boolean canGetRowCount()
  {
    return true;
  }
  
  public boolean canDrop()
  {
    return false;
  }
  
  public long getRowCount(Session session)
  {
    return Math.max(0L, getMax(session) - getMin(session) + 1L);
  }
  
  public String getTableType()
  {
    throw DbException.throwInternalError();
  }
  
  public Index getScanIndex(Session session)
  {
    if (getStep(session) == 0L) {
      throw DbException.get(90142);
    }
    return new RangeIndex(this, IndexColumn.wrap(this.columns));
  }
  
  public long getMin(Session session)
  {
    optimize(session);
    return this.min.getValue(session).getLong();
  }
  
  public long getMax(Session session)
  {
    optimize(session);
    return this.max.getValue(session).getLong();
  }
  
  public long getStep(Session session)
  {
    optimize(session);
    if (this.step == null) {
      return 1L;
    }
    return this.step.getValue(session).getLong();
  }
  
  private void optimize(Session s)
  {
    if (!this.optimized)
    {
      this.min = this.min.optimize(s);
      this.max = this.max.optimize(s);
      if (this.step != null) {
        this.step = this.step.optimize(s);
      }
      this.optimized = true;
    }
  }
  
  public ArrayList<Index> getIndexes()
  {
    return null;
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("SYSTEM_RANGE");
  }
  
  public long getMaxDataModificationId()
  {
    return 0L;
  }
  
  public Index getUniqueIndex()
  {
    return null;
  }
  
  public long getRowCountApproximation()
  {
    return 100L;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public boolean isDeterministic()
  {
    return true;
  }
  
  public boolean canReference()
  {
    return false;
  }
}
