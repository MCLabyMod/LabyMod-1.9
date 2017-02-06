package org.h2.expression;

import java.util.HashSet;
import org.h2.engine.DbObject;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.Table;

public class ExpressionVisitor
{
  public static final int INDEPENDENT = 0;
  public static final ExpressionVisitor INDEPENDENT_VISITOR = new ExpressionVisitor(0);
  public static final int OPTIMIZABLE_MIN_MAX_COUNT_ALL = 1;
  public static final int DETERMINISTIC = 2;
  public static final ExpressionVisitor DETERMINISTIC_VISITOR = new ExpressionVisitor(2);
  public static final int EVALUATABLE = 3;
  public static final ExpressionVisitor EVALUATABLE_VISITOR = new ExpressionVisitor(3);
  public static final int SET_MAX_DATA_MODIFICATION_ID = 4;
  public static final int READONLY = 5;
  public static final ExpressionVisitor READONLY_VISITOR = new ExpressionVisitor(5);
  public static final int NOT_FROM_RESOLVER = 6;
  public static final int GET_DEPENDENCIES = 7;
  public static final int QUERY_COMPARABLE = 8;
  public static final int GET_COLUMNS = 9;
  public static final ExpressionVisitor QUERY_COMPARABLE_VISITOR = new ExpressionVisitor(8);
  private final int type;
  private final int queryLevel;
  private final HashSet<DbObject> dependencies;
  private final HashSet<Column> columns;
  private final Table table;
  private final long[] maxDataModificationId;
  private final ColumnResolver resolver;
  
  private ExpressionVisitor(int type, int queryLevel, HashSet<DbObject> dependencies, HashSet<Column> columns, Table table, ColumnResolver resolver, long[] maxDataModificationId)
  {
    this.type = type;
    this.queryLevel = queryLevel;
    this.dependencies = dependencies;
    this.columns = columns;
    this.table = table;
    this.resolver = resolver;
    this.maxDataModificationId = maxDataModificationId;
  }
  
  private ExpressionVisitor(int type)
  {
    this.type = type;
    this.queryLevel = 0;
    this.dependencies = null;
    this.columns = null;
    this.table = null;
    this.resolver = null;
    this.maxDataModificationId = null;
  }
  
  public static ExpressionVisitor getDependenciesVisitor(HashSet<DbObject> dependencies)
  {
    return new ExpressionVisitor(7, 0, dependencies, null, null, null, null);
  }
  
  public static ExpressionVisitor getOptimizableVisitor(Table table)
  {
    return new ExpressionVisitor(1, 0, null, null, table, null, null);
  }
  
  static ExpressionVisitor getNotFromResolverVisitor(ColumnResolver resolver)
  {
    return new ExpressionVisitor(6, 0, null, null, null, resolver, null);
  }
  
  public static ExpressionVisitor getColumnsVisitor(HashSet<Column> columns)
  {
    return new ExpressionVisitor(9, 0, null, columns, null, null, null);
  }
  
  public static ExpressionVisitor getMaxModificationIdVisitor()
  {
    return new ExpressionVisitor(4, 0, null, null, null, null, new long[1]);
  }
  
  public void addDependency(DbObject obj)
  {
    this.dependencies.add(obj);
  }
  
  void addColumn(Column column)
  {
    this.columns.add(column);
  }
  
  public HashSet<DbObject> getDependencies()
  {
    return this.dependencies;
  }
  
  public ExpressionVisitor incrementQueryLevel(int offset)
  {
    return new ExpressionVisitor(this.type, this.queryLevel + offset, this.dependencies, this.columns, this.table, this.resolver, this.maxDataModificationId);
  }
  
  public ColumnResolver getResolver()
  {
    return this.resolver;
  }
  
  public void addDataModificationId(long value)
  {
    long m = this.maxDataModificationId[0];
    if (value > m) {
      this.maxDataModificationId[0] = value;
    }
  }
  
  public long getMaxDataModificationId()
  {
    return this.maxDataModificationId[0];
  }
  
  int getQueryLevel()
  {
    return this.queryLevel;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public int getType()
  {
    return this.type;
  }
}
