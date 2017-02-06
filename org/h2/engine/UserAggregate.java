package org.h2.engine;

import java.sql.Connection;
import java.sql.SQLException;
import org.h2.api.Aggregate;
import org.h2.api.AggregateFunction;
import org.h2.command.Parser;
import org.h2.message.DbException;
import org.h2.table.Table;
import org.h2.util.JdbcUtils;
import org.h2.value.DataType;

public class UserAggregate
  extends DbObjectBase
{
  private String className;
  private Class<?> javaClass;
  
  public UserAggregate(Database db, int id, String name, String className, boolean force)
  {
    initDbObjectBase(db, id, name, "function");
    this.className = className;
    if (!force) {
      getInstance();
    }
  }
  
  public Aggregate getInstance()
  {
    if (this.javaClass == null) {
      this.javaClass = JdbcUtils.loadUserClass(this.className);
    }
    try
    {
      Object obj = this.javaClass.newInstance();
      Aggregate agg;
      if ((obj instanceof Aggregate)) {
        agg = (Aggregate)obj;
      }
      return new AggregateWrapper((AggregateFunction)obj);
    }
    catch (Exception e)
    {
      throw DbException.convert(e);
    }
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getDropSQL()
  {
    return "DROP AGGREGATE IF EXISTS " + getSQL();
  }
  
  public String getCreateSQL()
  {
    return "CREATE FORCE AGGREGATE " + getSQL() + " FOR " + Parser.quoteIdentifier(this.className);
  }
  
  public int getType()
  {
    return 14;
  }
  
  public synchronized void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
    this.className = null;
    this.javaClass = null;
    invalidate();
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("AGGREGATE");
  }
  
  public String getJavaClassName()
  {
    return this.className;
  }
  
  private static class AggregateWrapper
    implements Aggregate
  {
    private final AggregateFunction aggregateFunction;
    
    AggregateWrapper(AggregateFunction aggregateFunction)
    {
      this.aggregateFunction = aggregateFunction;
    }
    
    public void init(Connection conn)
      throws SQLException
    {
      this.aggregateFunction.init(conn);
    }
    
    public int getInternalType(int[] inputTypes)
      throws SQLException
    {
      int[] sqlTypes = new int[inputTypes.length];
      for (int i = 0; i < inputTypes.length; i++) {
        sqlTypes[i] = DataType.convertTypeToSQLType(inputTypes[i]);
      }
      return DataType.convertSQLTypeToValueType(this.aggregateFunction.getType(sqlTypes));
    }
    
    public void add(Object value)
      throws SQLException
    {
      this.aggregateFunction.add(value);
    }
    
    public Object getResult()
      throws SQLException
    {
      return this.aggregateFunction.getResult();
    }
  }
}
