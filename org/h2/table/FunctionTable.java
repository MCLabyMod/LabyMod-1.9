package org.h2.table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.FunctionCall;
import org.h2.expression.TableFunction;
import org.h2.index.FunctionIndex;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueNull;
import org.h2.value.ValueResultSet;

public class FunctionTable
  extends Table
{
  private final FunctionCall function;
  private final long rowCount;
  private Expression functionExpr;
  private LocalResult cachedResult;
  private Value cachedValue;
  
  public FunctionTable(Schema schema, Session session, Expression functionExpr, FunctionCall function)
  {
    super(schema, 0, function.getName(), false, true);
    this.functionExpr = functionExpr;
    this.function = function;
    if ((function instanceof TableFunction)) {
      this.rowCount = ((TableFunction)function).getRowCount();
    } else {
      this.rowCount = Long.MAX_VALUE;
    }
    function.optimize(session);
    int type = function.getType();
    if (type != 18) {
      throw DbException.get(90000, function.getName());
    }
    Expression[] args = function.getArgs();
    int numParams = args.length;
    Expression[] columnListArgs = new Expression[numParams];
    for (int i = 0; i < numParams; i++)
    {
      args[i] = args[i].optimize(session);
      columnListArgs[i] = args[i];
    }
    ValueResultSet template = function.getValueForColumnList(session, columnListArgs);
    if (template == null) {
      throw DbException.get(90000, function.getName());
    }
    ResultSet rs = template.getResultSet();
    try
    {
      ResultSetMetaData meta = rs.getMetaData();
      int columnCount = meta.getColumnCount();
      Column[] cols = new Column[columnCount];
      for (int i = 0; i < columnCount; i++) {
        cols[i] = new Column(meta.getColumnName(i + 1), DataType.getValueTypeFromResultSet(meta, i + 1), meta.getPrecision(i + 1), meta.getScale(i + 1), meta.getColumnDisplaySize(i + 1));
      }
      setColumns(cols);
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
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
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public void removeRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public boolean canDrop()
  {
    throw DbException.throwInternalError();
  }
  
  public void addRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public void checkSupportAlter()
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public String getTableType()
  {
    return null;
  }
  
  public Index getScanIndex(Session session)
  {
    return new FunctionIndex(this, IndexColumn.wrap(this.columns));
  }
  
  public ArrayList<Index> getIndexes()
  {
    return null;
  }
  
  public boolean canGetRowCount()
  {
    return this.rowCount != Long.MAX_VALUE;
  }
  
  public long getRowCount(Session session)
  {
    return this.rowCount;
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("ALIAS");
  }
  
  public ResultInterface getResult(Session session)
  {
    ValueResultSet v = getValueResultSet(session);
    if (v == null) {
      return null;
    }
    if ((this.cachedResult != null) && (this.cachedValue == v))
    {
      this.cachedResult.reset();
      return this.cachedResult;
    }
    LocalResult result = LocalResult.read(session, v.getResultSet(), 0);
    if (this.function.isDeterministic())
    {
      this.cachedResult = result;
      this.cachedValue = v;
    }
    return result;
  }
  
  public ResultSet getResultSet(Session session)
  {
    ValueResultSet v = getValueResultSet(session);
    return v == null ? null : v.getResultSet();
  }
  
  private ValueResultSet getValueResultSet(Session session)
  {
    this.functionExpr = this.functionExpr.optimize(session);
    Value v = this.functionExpr.getValue(session);
    if (v == ValueNull.INSTANCE) {
      return null;
    }
    return (ValueResultSet)v;
  }
  
  public boolean isBufferResultSetToLocalTemp()
  {
    return this.function.isBufferResultSetToLocalTemp();
  }
  
  public long getMaxDataModificationId()
  {
    return Long.MAX_VALUE;
  }
  
  public Index getUniqueIndex()
  {
    return null;
  }
  
  public String getSQL()
  {
    return this.function.getSQL();
  }
  
  public long getRowCountApproximation()
  {
    return this.rowCount;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public boolean isDeterministic()
  {
    return this.function.isDeterministic();
  }
  
  public boolean canReference()
  {
    return false;
  }
}
