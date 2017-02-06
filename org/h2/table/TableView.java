package org.h2.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.h2.command.Prepared;
import org.h2.command.dml.Query;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Alias;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ExpressionVisitor;
import org.h2.expression.Parameter;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.ViewIndex;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.Row;
import org.h2.result.SortOrder;
import org.h2.schema.Schema;
import org.h2.util.New;
import org.h2.util.SmallLRUCache;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.SynchronizedVerifier;

public class TableView
  extends Table
{
  private static final long ROW_COUNT_APPROXIMATION = 100L;
  private String querySQL;
  private ArrayList<Table> tables;
  private String[] columnNames;
  private Query viewQuery;
  private ViewIndex index;
  private boolean recursive;
  private DbException createException;
  private final SmallLRUCache<CacheKey, ViewIndex> indexCache = SmallLRUCache.newInstance(64);
  private long lastModificationCheck;
  private long maxDataModificationId;
  private User owner;
  private Query topQuery;
  private LocalResult recursiveResult;
  private boolean tableExpression;
  
  public TableView(Schema schema, int id, String name, String querySQL, ArrayList<Parameter> params, String[] columnNames, Session session, boolean recursive)
  {
    super(schema, id, name, false, true);
    init(querySQL, params, columnNames, session, recursive);
  }
  
  public void replace(String querySQL, String[] columnNames, Session session, boolean recursive, boolean force)
  {
    String oldQuerySQL = this.querySQL;
    String[] oldColumnNames = this.columnNames;
    boolean oldRecursive = this.recursive;
    init(querySQL, null, columnNames, session, recursive);
    DbException e = recompile(session, force);
    if (e != null)
    {
      init(oldQuerySQL, null, oldColumnNames, session, oldRecursive);
      recompile(session, true);
      throw e;
    }
  }
  
  private synchronized void init(String querySQL, ArrayList<Parameter> params, String[] columnNames, Session session, boolean recursive)
  {
    this.querySQL = querySQL;
    this.columnNames = columnNames;
    this.recursive = recursive;
    this.index = new ViewIndex(this, querySQL, params, recursive);
    SynchronizedVerifier.check(this.indexCache);
    this.indexCache.clear();
    initColumnsAndTables(session);
  }
  
  private static Query compileViewQuery(Session session, String sql)
  {
    Prepared p = session.prepare(sql);
    if (!(p instanceof Query)) {
      throw DbException.getSyntaxError(sql, 0);
    }
    return (Query)p;
  }
  
  public synchronized DbException recompile(Session session, boolean force)
  {
    try
    {
      compileViewQuery(session, this.querySQL);
    }
    catch (DbException e)
    {
      if (!force) {
        return e;
      }
    }
    ArrayList<TableView> views = getViews();
    if (views != null) {
      views = New.arrayList(views);
    }
    SynchronizedVerifier.check(this.indexCache);
    this.indexCache.clear();
    initColumnsAndTables(session);
    if (views != null) {
      for (TableView v : views)
      {
        DbException e = v.recompile(session, force);
        if ((e != null) && (!force)) {
          return e;
        }
      }
    }
    return force ? null : this.createException;
  }
  
  private void initColumnsAndTables(Session session)
  {
    removeViewFromTables();
    Column[] cols;
    try
    {
      Query query = compileViewQuery(session, this.querySQL);
      this.querySQL = query.getPlanSQL();
      this.tables = New.arrayList(query.getTables());
      ArrayList<Expression> expressions = query.getExpressions();
      ArrayList<Column> list = New.arrayList();
      int i = 0;
      for (int count = query.getColumnCount(); i < count; i++)
      {
        Expression expr = (Expression)expressions.get(i);
        String name = null;
        if ((this.columnNames != null) && (this.columnNames.length > i)) {
          name = this.columnNames[i];
        }
        if (name == null) {
          name = expr.getAlias();
        }
        int type = expr.getType();
        long precision = expr.getPrecision();
        int scale = expr.getScale();
        int displaySize = expr.getDisplaySize();
        Column col = new Column(name, type, precision, scale, displaySize);
        col.setTable(this, i);
        
        ExpressionColumn fromColumn = null;
        if ((expr instanceof ExpressionColumn))
        {
          fromColumn = (ExpressionColumn)expr;
        }
        else if ((expr instanceof Alias))
        {
          Expression aliasExpr = expr.getNonAliasExpression();
          if ((aliasExpr instanceof ExpressionColumn)) {
            fromColumn = (ExpressionColumn)aliasExpr;
          }
        }
        if (fromColumn != null)
        {
          Expression checkExpression = fromColumn.getColumn().getCheckConstraint(session, name);
          if (checkExpression != null) {
            col.addCheckConstraint(session, checkExpression);
          }
        }
        list.add(col);
      }
      cols = new Column[list.size()];
      list.toArray(cols);
      this.createException = null;
      this.viewQuery = query;
    }
    catch (DbException e)
    {
      e.addSQL(getCreateSQL());
      this.createException = e;
      
      this.tables = New.arrayList();
      cols = new Column[0];
      if ((this.recursive) && (this.columnNames != null))
      {
        cols = new Column[this.columnNames.length];
        for (int i = 0; i < this.columnNames.length; i++) {
          cols[i] = new Column(this.columnNames[i], 13);
        }
        this.index.setRecursive(true);
        this.createException = null;
      }
    }
    setColumns(cols);
    if (getId() != 0) {
      addViewToTables();
    }
  }
  
  public boolean isInvalid()
  {
    return this.createException != null;
  }
  
  public PlanItem getBestPlanItem(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    PlanItem item = new PlanItem();
    item.cost = this.index.getCost(session, masks, filter, sortOrder);
    CacheKey cacheKey = new CacheKey(masks, session);
    synchronized (this)
    {
      SynchronizedVerifier.check(this.indexCache);
      ViewIndex i2 = (ViewIndex)this.indexCache.get(cacheKey);
      if (i2 != null)
      {
        item.setIndex(i2);
        return item;
      }
    }
    ViewIndex i2 = new ViewIndex(this, this.index, session, masks);
    synchronized (this)
    {
      ViewIndex i3 = (ViewIndex)this.indexCache.get(cacheKey);
      if (i3 != null)
      {
        item.setIndex(i3);
        return item;
      }
      this.indexCache.put(cacheKey, i2);
      item.setIndex(i2);
    }
    return item;
  }
  
  public boolean isQueryComparable()
  {
    if (!super.isQueryComparable()) {
      return false;
    }
    for (Table t : this.tables) {
      if (!t.isQueryComparable()) {
        return false;
      }
    }
    if ((this.topQuery != null) && (!this.topQuery.isEverything(ExpressionVisitor.QUERY_COMPARABLE_VISITOR))) {
      return false;
    }
    return true;
  }
  
  public String getDropSQL()
  {
    return "DROP VIEW IF EXISTS " + getSQL() + " CASCADE";
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    return getCreateSQL(false, true, quotedName);
  }
  
  public String getCreateSQL()
  {
    return getCreateSQL(false, true);
  }
  
  public String getCreateSQL(boolean orReplace, boolean force)
  {
    return getCreateSQL(orReplace, force, getSQL());
  }
  
  private String getCreateSQL(boolean orReplace, boolean force, String quotedName)
  {
    StatementBuilder buff = new StatementBuilder("CREATE ");
    if (orReplace) {
      buff.append("OR REPLACE ");
    }
    if (force) {
      buff.append("FORCE ");
    }
    buff.append("VIEW ");
    buff.append(quotedName);
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    if ((this.columns != null) && (this.columns.length > 0))
    {
      buff.append('(');
      for (Column c : this.columns)
      {
        buff.appendExceptFirst(", ");
        buff.append(c.getSQL());
      }
      buff.append(')');
    }
    else if (this.columnNames != null)
    {
      buff.append('(');
      for (String n : this.columnNames)
      {
        buff.appendExceptFirst(", ");
        buff.append(n);
      }
      buff.append(')');
    }
    return buff.append(" AS\n").append(this.querySQL).toString();
  }
  
  public void checkRename() {}
  
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
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void removeRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void addRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void checkSupportAlter()
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public long getRowCount(Session session)
  {
    throw DbException.throwInternalError();
  }
  
  public boolean canGetRowCount()
  {
    return false;
  }
  
  public boolean canDrop()
  {
    return true;
  }
  
  public String getTableType()
  {
    return "VIEW";
  }
  
  public void removeChildrenAndResources(Session session)
  {
    removeViewFromTables();
    super.removeChildrenAndResources(session);
    this.database.removeMeta(session, getId());
    this.querySQL = null;
    this.index = null;
    invalidate();
  }
  
  public String getSQL()
  {
    if (isTemporary()) {
      return "(\n" + StringUtils.indent(this.querySQL) + ")";
    }
    return super.getSQL();
  }
  
  public String getQuery()
  {
    return this.querySQL;
  }
  
  public Index getScanIndex(Session session)
  {
    if (this.createException != null)
    {
      String msg = this.createException.getMessage();
      throw DbException.get(90109, this.createException, new String[] { getSQL(), msg });
    }
    PlanItem item = getBestPlanItem(session, null, null, null);
    return item.getIndex();
  }
  
  public boolean canReference()
  {
    return false;
  }
  
  public ArrayList<Index> getIndexes()
  {
    return null;
  }
  
  public long getMaxDataModificationId()
  {
    if (this.createException != null) {
      return Long.MAX_VALUE;
    }
    if (this.viewQuery == null) {
      return Long.MAX_VALUE;
    }
    long dbMod = this.database.getModificationDataId();
    if ((dbMod > this.lastModificationCheck) && (this.maxDataModificationId <= dbMod))
    {
      this.maxDataModificationId = this.viewQuery.getMaxDataModificationId();
      this.lastModificationCheck = dbMod;
    }
    return this.maxDataModificationId;
  }
  
  public Index getUniqueIndex()
  {
    return null;
  }
  
  private void removeViewFromTables()
  {
    if (this.tables != null)
    {
      for (Table t : this.tables) {
        t.removeView(this);
      }
      this.tables.clear();
    }
  }
  
  private void addViewToTables()
  {
    for (Table t : this.tables) {
      t.addView(this);
    }
  }
  
  private void setOwner(User owner)
  {
    this.owner = owner;
  }
  
  public User getOwner()
  {
    return this.owner;
  }
  
  public static TableView createTempView(Session session, User owner, String name, Query query, Query topQuery)
  {
    Schema mainSchema = session.getDatabase().getSchema("PUBLIC");
    String querySQL = query.getPlanSQL();
    TableView v = new TableView(mainSchema, 0, name, querySQL, query.getParameters(), null, session, false);
    if (v.createException != null) {
      throw v.createException;
    }
    v.setTopQuery(topQuery);
    v.setOwner(owner);
    v.setTemporary(true);
    return v;
  }
  
  private void setTopQuery(Query topQuery)
  {
    this.topQuery = topQuery;
  }
  
  public long getRowCountApproximation()
  {
    return 100L;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public int getParameterOffset()
  {
    return this.topQuery == null ? 0 : this.topQuery.getParameters().size();
  }
  
  public boolean isDeterministic()
  {
    if ((this.recursive) || (this.viewQuery == null)) {
      return false;
    }
    return this.viewQuery.isEverything(ExpressionVisitor.DETERMINISTIC_VISITOR);
  }
  
  public void setRecursiveResult(LocalResult value)
  {
    if (this.recursiveResult != null) {
      this.recursiveResult.close();
    }
    this.recursiveResult = value;
  }
  
  public LocalResult getRecursiveResult()
  {
    return this.recursiveResult;
  }
  
  public void setTableExpression(boolean tableExpression)
  {
    this.tableExpression = tableExpression;
  }
  
  public boolean isTableExpression()
  {
    return this.tableExpression;
  }
  
  public void addDependencies(HashSet<DbObject> dependencies)
  {
    super.addDependencies(dependencies);
    if (this.tables != null) {
      for (Table t : this.tables) {
        if (!"VIEW".equals(t.getTableType())) {
          t.addDependencies(dependencies);
        }
      }
    }
  }
  
  private static final class CacheKey
  {
    private final int[] masks;
    private final Session session;
    
    public CacheKey(int[] masks, Session session)
    {
      this.masks = masks;
      this.session = session;
    }
    
    public int hashCode()
    {
      int prime = 31;
      int result = 1;
      result = 31 * result + Arrays.hashCode(this.masks);
      result = 31 * result + this.session.hashCode();
      return result;
    }
    
    public boolean equals(Object obj)
    {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      CacheKey other = (CacheKey)obj;
      if (this.session != other.session) {
        return false;
      }
      if (!Arrays.equals(this.masks, other.masks)) {
        return false;
      }
      return true;
    }
  }
}
