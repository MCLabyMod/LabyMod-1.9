package org.h2.command.dml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.expression.Comparison;
import org.h2.expression.ConditionAndOr;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ExpressionVisitor;
import org.h2.expression.Parameter;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.index.IndexCondition;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.result.ResultTarget;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.ValueHashMap;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueNull;

public class Select
  extends Query
{
  private TableFilter topTableFilter;
  private final ArrayList<TableFilter> filters = New.arrayList();
  private final ArrayList<TableFilter> topFilters = New.arrayList();
  private ArrayList<Expression> expressions;
  private Expression[] expressionArray;
  private Expression having;
  private Expression condition;
  private int visibleColumnCount;
  private int distinctColumnCount;
  private ArrayList<SelectOrderBy> orderList;
  private ArrayList<Expression> group;
  private int[] groupIndex;
  private boolean[] groupByExpression;
  private HashMap<Expression, Object> currentGroup;
  private int havingIndex;
  private boolean isGroupQuery;
  private boolean isGroupSortedQuery;
  private boolean isForUpdate;
  private boolean isForUpdateMvcc;
  private double cost;
  private boolean isQuickAggregateQuery;
  private boolean isDistinctQuery;
  private boolean isPrepared;
  private boolean checkInit;
  private boolean sortUsingIndex;
  private SortOrder sort;
  private int currentGroupRowId;
  
  public Select(Session session)
  {
    super(session);
  }
  
  public void addTableFilter(TableFilter filter, boolean isTop)
  {
    this.filters.add(filter);
    if (isTop) {
      this.topFilters.add(filter);
    }
  }
  
  public ArrayList<TableFilter> getTopFilters()
  {
    return this.topFilters;
  }
  
  public void setExpressions(ArrayList<Expression> expressions)
  {
    this.expressions = expressions;
  }
  
  public void setGroupQuery()
  {
    this.isGroupQuery = true;
  }
  
  public void setGroupBy(ArrayList<Expression> group)
  {
    this.group = group;
  }
  
  public ArrayList<Expression> getGroupBy()
  {
    return this.group;
  }
  
  public HashMap<Expression, Object> getCurrentGroup()
  {
    return this.currentGroup;
  }
  
  public int getCurrentGroupRowId()
  {
    return this.currentGroupRowId;
  }
  
  public void setOrder(ArrayList<SelectOrderBy> order)
  {
    this.orderList = order;
  }
  
  public void addCondition(Expression cond)
  {
    if (this.condition == null) {
      this.condition = cond;
    } else {
      this.condition = new ConditionAndOr(0, cond, this.condition);
    }
  }
  
  private void queryGroupSorted(int columnCount, ResultTarget result)
  {
    int rowNumber = 0;
    setCurrentRowNumber(0);
    this.currentGroup = null;
    Value[] previousKeyValues = null;
    while (this.topTableFilter.next())
    {
      setCurrentRowNumber(rowNumber + 1);
      if ((this.condition == null) || (Boolean.TRUE.equals(this.condition.getBooleanValue(this.session))))
      {
        rowNumber++;
        Value[] keyValues = new Value[this.groupIndex.length];
        for (int i = 0; i < this.groupIndex.length; i++)
        {
          int idx = this.groupIndex[i];
          Expression expr = (Expression)this.expressions.get(idx);
          keyValues[i] = expr.getValue(this.session);
        }
        if (previousKeyValues == null)
        {
          previousKeyValues = keyValues;
          this.currentGroup = New.hashMap();
        }
        else if (!Arrays.equals(previousKeyValues, keyValues))
        {
          addGroupSortedRow(previousKeyValues, columnCount, result);
          previousKeyValues = keyValues;
          this.currentGroup = New.hashMap();
        }
        this.currentGroupRowId += 1;
        for (int i = 0; i < columnCount; i++) {
          if ((this.groupByExpression == null) || (this.groupByExpression[i] == 0))
          {
            Expression expr = (Expression)this.expressions.get(i);
            expr.updateAggregate(this.session);
          }
        }
      }
    }
    if (previousKeyValues != null) {
      addGroupSortedRow(previousKeyValues, columnCount, result);
    }
  }
  
  private void addGroupSortedRow(Value[] keyValues, int columnCount, ResultTarget result)
  {
    Value[] row = new Value[columnCount];
    for (int j = 0; (this.groupIndex != null) && (j < this.groupIndex.length); j++) {
      row[this.groupIndex[j]] = keyValues[j];
    }
    for (int j = 0; j < columnCount; j++) {
      if ((this.groupByExpression == null) || (this.groupByExpression[j] == 0))
      {
        Expression expr = (Expression)this.expressions.get(j);
        row[j] = expr.getValue(this.session);
      }
    }
    if (isHavingNullOrFalse(row)) {
      return;
    }
    row = keepOnlyDistinct(row, columnCount);
    result.addRow(row);
  }
  
  private Value[] keepOnlyDistinct(Value[] row, int columnCount)
  {
    if (columnCount == this.distinctColumnCount) {
      return row;
    }
    Value[] r2 = new Value[this.distinctColumnCount];
    System.arraycopy(row, 0, r2, 0, this.distinctColumnCount);
    return r2;
  }
  
  private boolean isHavingNullOrFalse(Value[] row)
  {
    if (this.havingIndex >= 0)
    {
      Value v = row[this.havingIndex];
      if (v == ValueNull.INSTANCE) {
        return true;
      }
      if (!Boolean.TRUE.equals(v.getBoolean())) {
        return true;
      }
    }
    return false;
  }
  
  private Index getGroupSortedIndex()
  {
    if ((this.groupIndex == null) || (this.groupByExpression == null)) {
      return null;
    }
    ArrayList<Index> indexes = this.topTableFilter.getTable().getIndexes();
    if (indexes != null)
    {
      int i = 0;
      for (int size = indexes.size(); i < size; i++)
      {
        Index index = (Index)indexes.get(i);
        if (!index.getIndexType().isScan()) {
          if (!index.getIndexType().isHash()) {
            if (isGroupSortedIndex(this.topTableFilter, index)) {
              return index;
            }
          }
        }
      }
    }
    return null;
  }
  
  private boolean isGroupSortedIndex(TableFilter tableFilter, Index index)
  {
    Column[] indexColumns = index.getColumns();
    
    boolean[] grouped = new boolean[indexColumns.length];
    
    int i = 0;
    label130:
    for (int size = this.expressions.size(); i < size; i++) {
      if (this.groupByExpression[i] != 0)
      {
        Expression expr = ((Expression)this.expressions.get(i)).getNonAliasExpression();
        if (!(expr instanceof ExpressionColumn)) {
          return false;
        }
        ExpressionColumn exprCol = (ExpressionColumn)expr;
        for (int j = 0; j < indexColumns.length; j++) {
          if ((tableFilter == exprCol.getTableFilter()) && 
            (indexColumns[j].equals(exprCol.getColumn())))
          {
            grouped[j] = true;
            break label130;
          }
        }
        return false;
      }
    }
    for (int i = 1; i < grouped.length; i++) {
      if ((grouped[(i - 1)] == 0) && (grouped[i] != 0)) {
        return false;
      }
    }
    return true;
  }
  
  private int getGroupByExpressionCount()
  {
    if (this.groupByExpression == null) {
      return 0;
    }
    int count = 0;
    for (boolean b : this.groupByExpression) {
      if (b) {
        count++;
      }
    }
    return count;
  }
  
  private void queryGroup(int columnCount, LocalResult result)
  {
    ValueHashMap<HashMap<Expression, Object>> groups = ValueHashMap.newInstance();
    
    int rowNumber = 0;
    setCurrentRowNumber(0);
    this.currentGroup = null;
    ValueArray defaultGroup = ValueArray.get(new Value[0]);
    int sampleSize = getSampleSizeValue(this.session);
    while (this.topTableFilter.next())
    {
      setCurrentRowNumber(rowNumber + 1);
      if ((this.condition == null) || (Boolean.TRUE.equals(this.condition.getBooleanValue(this.session))))
      {
        rowNumber++;
        Value key;
        Value key;
        if (this.groupIndex == null)
        {
          key = defaultGroup;
        }
        else
        {
          Value[] keyValues = new Value[this.groupIndex.length];
          for (int i = 0; i < this.groupIndex.length; i++)
          {
            int idx = this.groupIndex[i];
            Expression expr = (Expression)this.expressions.get(idx);
            keyValues[i] = expr.getValue(this.session);
          }
          key = ValueArray.get(keyValues);
        }
        HashMap<Expression, Object> values = (HashMap)groups.get(key);
        if (values == null)
        {
          values = new HashMap();
          groups.put(key, values);
        }
        this.currentGroup = values;
        this.currentGroupRowId += 1;
        int len = columnCount;
        for (int i = 0; i < len; i++) {
          if ((this.groupByExpression == null) || (this.groupByExpression[i] == 0))
          {
            Expression expr = (Expression)this.expressions.get(i);
            expr.updateAggregate(this.session);
          }
        }
        if ((sampleSize > 0) && (rowNumber >= sampleSize)) {
          break;
        }
      }
    }
    if ((this.groupIndex == null) && (groups.size() == 0)) {
      groups.put(defaultGroup, new HashMap());
    }
    ArrayList<Value> keys = groups.keys();
    for (Value v : keys)
    {
      ValueArray key = (ValueArray)v;
      this.currentGroup = ((HashMap)groups.get(key));
      Value[] keyValues = key.getList();
      Value[] row = new Value[columnCount];
      for (int j = 0; (this.groupIndex != null) && (j < this.groupIndex.length); j++) {
        row[this.groupIndex[j]] = keyValues[j];
      }
      for (int j = 0; j < columnCount; j++) {
        if ((this.groupByExpression == null) || (this.groupByExpression[j] == 0))
        {
          Expression expr = (Expression)this.expressions.get(j);
          row[j] = expr.getValue(this.session);
        }
      }
      if (!isHavingNullOrFalse(row))
      {
        row = keepOnlyDistinct(row, columnCount);
        result.addRow(row);
      }
    }
  }
  
  private Index getSortIndex()
  {
    if (this.sort == null) {
      return null;
    }
    ArrayList<Column> sortColumns = New.arrayList();
    for (int idx : this.sort.getQueryColumnIndexes())
    {
      if ((idx < 0) || (idx >= this.expressions.size())) {
        throw DbException.getInvalidValueException("ORDER BY", Integer.valueOf(idx + 1));
      }
      Expression expr = (Expression)this.expressions.get(idx);
      expr = expr.getNonAliasExpression();
      if (!expr.isConstant())
      {
        if (!(expr instanceof ExpressionColumn)) {
          return null;
        }
        ExpressionColumn exprCol = (ExpressionColumn)expr;
        if (exprCol.getTableFilter() != this.topTableFilter) {
          return null;
        }
        sortColumns.add(exprCol.getColumn());
      }
    }
    Column[] sortCols = (Column[])sortColumns.toArray(new Column[sortColumns.size()]);
    int[] sortTypes = this.sort.getSortTypes();
    if (sortCols.length == 0) {
      return this.topTableFilter.getTable().getScanIndex(this.session);
    }
    ArrayList<Index> list = this.topTableFilter.getTable().getIndexes();
    if (list != null)
    {
      int i = 0;
      for (int size = list.size(); i < size; i++)
      {
        Index index = (Index)list.get(i);
        if (index.getCreateSQL() != null) {
          if (!index.getIndexType().isHash())
          {
            IndexColumn[] indexCols = index.getIndexColumns();
            if (indexCols.length >= sortCols.length)
            {
              boolean ok = true;
              for (int j = 0; j < sortCols.length; j++)
              {
                IndexColumn idxCol = indexCols[j];
                Column sortCol = sortCols[j];
                boolean implicitSortColumn = false;
                if (idxCol.column != sortCol)
                {
                  implicitSortColumn = isSortColumnImplicit(this.topTableFilter, idxCol.column);
                  if (!implicitSortColumn)
                  {
                    ok = false;
                    break;
                  }
                }
                if ((!implicitSortColumn) && (idxCol.sortType != sortTypes[j]))
                {
                  ok = false;
                  break;
                }
              }
              if (ok) {
                return index;
              }
            }
          }
        }
      }
    }
    if ((sortCols.length == 1) && (sortCols[0].getColumnId() == -1))
    {
      Index index = this.topTableFilter.getTable().getScanIndex(this.session);
      if (index.isRowIdIndex()) {
        return index;
      }
    }
    return null;
  }
  
  private boolean isSortColumnImplicit(TableFilter tableFilter, Column sortColumn)
  {
    if ((this.filters.size() == 1) && (this.condition != null) && (!this.condition.isDisjunctive()))
    {
      ArrayList<IndexCondition> conditions = tableFilter.getIndexConditionsForColumn(sortColumn);
      if (conditions.isEmpty()) {
        return false;
      }
      for (IndexCondition conditionExp : conditions) {
        if (!conditionExp.isEquality(true)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  private void queryDistinct(ResultTarget result, long limitRows)
  {
    if ((limitRows > 0L) && (this.offsetExpr != null))
    {
      int offset = this.offsetExpr.getValue(this.session).getInt();
      if (offset > 0) {
        limitRows += offset;
      }
    }
    int rowNumber = 0;
    setCurrentRowNumber(0);
    Index index = this.topTableFilter.getIndex();
    SearchRow first = null;
    int columnIndex = index.getColumns()[0].getColumnId();
    int sampleSize = getSampleSizeValue(this.session);
    for (;;)
    {
      setCurrentRowNumber(rowNumber + 1);
      Cursor cursor = index.findNext(this.session, first, null);
      if (!cursor.next()) {
        break;
      }
      SearchRow found = cursor.getSearchRow();
      Value value = found.getValue(columnIndex);
      if (first == null) {
        first = this.topTableFilter.getTable().getTemplateSimpleRow(true);
      }
      first.setValue(columnIndex, value);
      Value[] row = { value };
      result.addRow(row);
      rowNumber++;
      if (((this.sort == null) || (this.sortUsingIndex)) && (limitRows > 0L) && (rowNumber >= limitRows)) {
        break;
      }
      if ((sampleSize > 0) && (rowNumber >= sampleSize)) {
        break;
      }
    }
  }
  
  private void queryFlat(int columnCount, ResultTarget result, long limitRows)
  {
    if ((limitRows > 0L) && (this.offsetExpr != null))
    {
      int offset = this.offsetExpr.getValue(this.session).getInt();
      if (offset > 0) {
        limitRows += offset;
      }
    }
    int rowNumber = 0;
    setCurrentRowNumber(0);
    ArrayList<Row> forUpdateRows = null;
    if (this.isForUpdateMvcc) {
      forUpdateRows = New.arrayList();
    }
    int sampleSize = getSampleSizeValue(this.session);
    while (this.topTableFilter.next())
    {
      setCurrentRowNumber(rowNumber + 1);
      if ((this.condition == null) || (Boolean.TRUE.equals(this.condition.getBooleanValue(this.session))))
      {
        Value[] row = new Value[columnCount];
        for (int i = 0; i < columnCount; i++)
        {
          Expression expr = (Expression)this.expressions.get(i);
          row[i] = expr.getValue(this.session);
        }
        if (this.isForUpdateMvcc) {
          this.topTableFilter.lockRowAdd(forUpdateRows);
        }
        result.addRow(row);
        rowNumber++;
        if (((this.sort == null) || (this.sortUsingIndex)) && (limitRows > 0L) && (result.getRowCount() >= limitRows)) {
          break;
        }
        if ((sampleSize > 0) && (rowNumber >= sampleSize)) {
          break;
        }
      }
    }
    if (this.isForUpdateMvcc) {
      this.topTableFilter.lockRows(forUpdateRows);
    }
  }
  
  private void queryQuick(int columnCount, ResultTarget result)
  {
    Value[] row = new Value[columnCount];
    for (int i = 0; i < columnCount; i++)
    {
      Expression expr = (Expression)this.expressions.get(i);
      row[i] = expr.getValue(this.session);
    }
    result.addRow(row);
  }
  
  public ResultInterface queryMeta()
  {
    LocalResult result = new LocalResult(this.session, this.expressionArray, this.visibleColumnCount);
    
    result.done();
    return result;
  }
  
  protected LocalResult queryWithoutCache(int maxRows, ResultTarget target)
  {
    int limitRows = maxRows == 0 ? -1 : maxRows;
    if (this.limitExpr != null)
    {
      Value v = this.limitExpr.getValue(this.session);
      int l = v == ValueNull.INSTANCE ? -1 : v.getInt();
      if (limitRows < 0) {
        limitRows = l;
      } else if (l >= 0) {
        limitRows = Math.min(l, limitRows);
      }
    }
    int columnCount = this.expressions.size();
    LocalResult result = null;
    if ((target == null) || (!this.session.getDatabase().getSettings().optimizeInsertFromSelect)) {
      result = createLocalResult(result);
    }
    if ((this.sort != null) && ((!this.sortUsingIndex) || (this.distinct)))
    {
      result = createLocalResult(result);
      result.setSortOrder(this.sort);
    }
    if ((this.distinct) && (!this.isDistinctQuery))
    {
      result = createLocalResult(result);
      result.setDistinct();
    }
    if (this.randomAccessResult) {
      result = createLocalResult(result);
    }
    if ((this.isGroupQuery) && (!this.isGroupSortedQuery)) {
      result = createLocalResult(result);
    }
    if ((limitRows >= 0) || (this.offsetExpr != null)) {
      result = createLocalResult(result);
    }
    this.topTableFilter.startQuery(this.session);
    this.topTableFilter.reset();
    boolean exclusive = (this.isForUpdate) && (!this.isForUpdateMvcc);
    if (this.isForUpdateMvcc)
    {
      if (this.isGroupQuery) {
        throw DbException.getUnsupportedException("MVCC=TRUE && FOR UPDATE && GROUP");
      }
      if (this.distinct) {
        throw DbException.getUnsupportedException("MVCC=TRUE && FOR UPDATE && DISTINCT");
      }
      if (this.isQuickAggregateQuery) {
        throw DbException.getUnsupportedException("MVCC=TRUE && FOR UPDATE && AGGREGATE");
      }
      if (this.topTableFilter.getJoin() != null) {
        throw DbException.getUnsupportedException("MVCC=TRUE && FOR UPDATE && JOIN");
      }
    }
    this.topTableFilter.lock(this.session, exclusive, exclusive);
    ResultTarget to = result != null ? result : target;
    if (limitRows != 0) {
      if (this.isQuickAggregateQuery) {
        queryQuick(columnCount, to);
      } else if (this.isGroupQuery)
      {
        if (this.isGroupSortedQuery) {
          queryGroupSorted(columnCount, to);
        } else {
          queryGroup(columnCount, result);
        }
      }
      else if (this.isDistinctQuery) {
        queryDistinct(to, limitRows);
      } else {
        queryFlat(columnCount, to, limitRows);
      }
    }
    if (this.offsetExpr != null) {
      result.setOffset(this.offsetExpr.getValue(this.session).getInt());
    }
    if (limitRows >= 0) {
      result.setLimit(limitRows);
    }
    if (result != null)
    {
      result.done();
      if (target != null)
      {
        while (result.next()) {
          target.addRow(result.currentRow());
        }
        result.close();
        return null;
      }
      return result;
    }
    return null;
  }
  
  private LocalResult createLocalResult(LocalResult old)
  {
    return old != null ? old : new LocalResult(this.session, this.expressionArray, this.visibleColumnCount);
  }
  
  private void expandColumnList()
  {
    Database db = this.session.getDatabase();
    for (int i = 0; i < this.expressions.size(); i++)
    {
      Expression expr = (Expression)this.expressions.get(i);
      if (expr.isWildcard())
      {
        String schemaName = expr.getSchemaName();
        String tableAlias = expr.getTableAlias();
        if (tableAlias == null)
        {
          this.expressions.remove(i);
          for (TableFilter filter : this.filters) {
            i = expandColumnList(filter, i);
          }
          i--;
        }
        else
        {
          TableFilter filter = null;
          for (TableFilter f : this.filters) {
            if ((db.equalsIdentifiers(tableAlias, f.getTableAlias())) && (
              (schemaName == null) || (db.equalsIdentifiers(schemaName, f.getSchemaName()))))
            {
              filter = f;
              break;
            }
          }
          if (filter == null) {
            throw DbException.get(42102, tableAlias);
          }
          this.expressions.remove(i);
          i = expandColumnList(filter, i);
          i--;
        }
      }
    }
  }
  
  private int expandColumnList(TableFilter filter, int index)
  {
    Table t = filter.getTable();
    String alias = filter.getTableAlias();
    Column[] columns = t.getColumns();
    for (Column c : columns) {
      if (!filter.isNaturalJoinColumn(c))
      {
        ExpressionColumn ec = new ExpressionColumn(this.session.getDatabase(), null, alias, c.getName());
        
        this.expressions.add(index++, ec);
      }
    }
    return index;
  }
  
  public void init()
  {
    if ((SysProperties.CHECK) && (this.checkInit)) {
      DbException.throwInternalError();
    }
    expandColumnList();
    this.visibleColumnCount = this.expressions.size();
    ArrayList<String> expressionSQL;
    if ((this.orderList != null) || (this.group != null))
    {
      ArrayList<String> expressionSQL = New.arrayList();
      for (int i = 0; i < this.visibleColumnCount; i++)
      {
        Expression expr = (Expression)this.expressions.get(i);
        expr = expr.getNonAliasExpression();
        String sql = expr.getSQL();
        expressionSQL.add(sql);
      }
    }
    else
    {
      expressionSQL = null;
    }
    if (this.orderList != null) {
      initOrder(this.session, this.expressions, expressionSQL, this.orderList, this.visibleColumnCount, this.distinct, this.filters);
    }
    this.distinctColumnCount = this.expressions.size();
    if (this.having != null)
    {
      this.expressions.add(this.having);
      this.havingIndex = (this.expressions.size() - 1);
      this.having = null;
    }
    else
    {
      this.havingIndex = -1;
    }
    Database db = this.session.getDatabase();
    if (this.group != null)
    {
      int size = this.group.size();
      int expSize = expressionSQL.size();
      this.groupIndex = new int[size];
      for (int i = 0; i < size; i++)
      {
        Expression expr = (Expression)this.group.get(i);
        String sql = expr.getSQL();
        int found = -1;
        for (int j = 0; j < expSize; j++)
        {
          String s2 = (String)expressionSQL.get(j);
          if (db.equalsIdentifiers(s2, sql))
          {
            found = j;
            break;
          }
        }
        if (found < 0) {
          for (int j = 0; j < expSize; j++)
          {
            Expression e = (Expression)this.expressions.get(j);
            if (db.equalsIdentifiers(sql, e.getAlias()))
            {
              found = j;
              break;
            }
            sql = expr.getAlias();
            if (db.equalsIdentifiers(sql, e.getAlias()))
            {
              found = j;
              break;
            }
          }
        }
        if (found < 0)
        {
          int index = this.expressions.size();
          this.groupIndex[i] = index;
          this.expressions.add(expr);
        }
        else
        {
          this.groupIndex[i] = found;
        }
      }
      this.groupByExpression = new boolean[this.expressions.size()];
      for (int gi : this.groupIndex) {
        this.groupByExpression[gi] = true;
      }
      this.group = null;
    }
    for (TableFilter f : this.filters) {
      mapColumns(f, 0);
    }
    if (this.havingIndex >= 0)
    {
      Expression expr = (Expression)this.expressions.get(this.havingIndex);
      SelectListColumnResolver res = new SelectListColumnResolver(this);
      expr.mapColumns(res, 0);
    }
    this.checkInit = true;
  }
  
  public void prepare()
  {
    if (this.isPrepared) {
      return;
    }
    if ((SysProperties.CHECK) && (!this.checkInit)) {
      DbException.throwInternalError("not initialized");
    }
    if (this.orderList != null)
    {
      this.sort = prepareOrder(this.orderList, this.expressions.size());
      this.orderList = null;
    }
    for (int i = 0; i < this.expressions.size(); i++)
    {
      Expression e = (Expression)this.expressions.get(i);
      this.expressions.set(i, e.optimize(this.session));
    }
    if (this.condition != null)
    {
      this.condition = this.condition.optimize(this.session);
      for (TableFilter f : this.filters) {
        if ((!f.isJoinOuter()) && (!f.isJoinOuterIndirect())) {
          this.condition.createIndexConditions(this.session, f);
        }
      }
    }
    if ((this.isGroupQuery) && (this.groupIndex == null) && (this.havingIndex < 0) && (this.filters.size() == 1)) {
      if (this.condition == null)
      {
        Table t = ((TableFilter)this.filters.get(0)).getTable();
        ExpressionVisitor optimizable = ExpressionVisitor.getOptimizableVisitor(t);
        
        this.isQuickAggregateQuery = isEverything(optimizable);
      }
    }
    this.cost = preparePlan();
    if ((this.distinct) && (this.session.getDatabase().getSettings().optimizeDistinct) && (!this.isGroupQuery) && (this.filters.size() == 1) && (this.expressions.size() == 1) && (this.condition == null))
    {
      Expression expr = (Expression)this.expressions.get(0);
      expr = expr.getNonAliasExpression();
      if ((expr instanceof ExpressionColumn))
      {
        Column column = ((ExpressionColumn)expr).getColumn();
        int selectivity = column.getSelectivity();
        Index columnIndex = this.topTableFilter.getTable().getIndexForColumn(column);
        if ((columnIndex != null) && (selectivity != 50) && (selectivity < 20))
        {
          boolean ascending = columnIndex.getIndexColumns()[0].sortType == 0;
          
          Index current = this.topTableFilter.getIndex();
          if ((columnIndex.canFindNext()) && (ascending) && ((current == null) || (current.getIndexType().isScan()) || (columnIndex == current)))
          {
            IndexType type = columnIndex.getIndexType();
            if ((!type.isHash()) && ((!type.isUnique()) || (columnIndex.getColumns().length > 1)))
            {
              this.topTableFilter.setIndex(columnIndex);
              this.isDistinctQuery = true;
            }
          }
        }
      }
    }
    if ((this.sort != null) && (!this.isQuickAggregateQuery) && (!this.isGroupQuery))
    {
      Index index = getSortIndex();
      if (index != null)
      {
        Index current = this.topTableFilter.getIndex();
        if ((current.getIndexType().isScan()) || (current == index))
        {
          this.topTableFilter.setIndex(index);
          if (!this.topTableFilter.hasInComparisons()) {
            this.sortUsingIndex = true;
          }
        }
        else if (index.getIndexColumns().length >= current.getIndexColumns().length)
        {
          IndexColumn[] sortColumns = index.getIndexColumns();
          IndexColumn[] currentColumns = current.getIndexColumns();
          boolean swapIndex = false;
          for (int i = 0; i < currentColumns.length; i++)
          {
            if (sortColumns[i].column != currentColumns[i].column)
            {
              swapIndex = false;
              break;
            }
            if (sortColumns[i].sortType != currentColumns[i].sortType) {
              swapIndex = true;
            }
          }
          if (swapIndex)
          {
            this.topTableFilter.setIndex(index);
            this.sortUsingIndex = true;
          }
        }
      }
    }
    if ((!this.isQuickAggregateQuery) && (this.isGroupQuery) && (getGroupByExpressionCount() > 0))
    {
      Index index = getGroupSortedIndex();
      Index current = this.topTableFilter.getIndex();
      if ((index != null) && ((current.getIndexType().isScan()) || (current == index)))
      {
        this.topTableFilter.setIndex(index);
        this.isGroupSortedQuery = true;
      }
    }
    this.expressionArray = new Expression[this.expressions.size()];
    this.expressions.toArray(this.expressionArray);
    this.isPrepared = true;
  }
  
  public double getCost()
  {
    return this.cost;
  }
  
  public HashSet<Table> getTables()
  {
    HashSet<Table> set = New.hashSet();
    for (TableFilter filter : this.filters) {
      set.add(filter.getTable());
    }
    return set;
  }
  
  public void fireBeforeSelectTriggers()
  {
    int i = 0;
    for (int size = this.filters.size(); i < size; i++)
    {
      TableFilter filter = (TableFilter)this.filters.get(i);
      filter.getTable().fire(this.session, 8, true);
    }
  }
  
  private double preparePlan()
  {
    TableFilter[] topArray = (TableFilter[])this.topFilters.toArray(new TableFilter[this.topFilters.size()]);
    for (TableFilter t : topArray) {
      t.setFullCondition(this.condition);
    }
    Optimizer optimizer = new Optimizer(topArray, this.condition, this.session);
    optimizer.optimize();
    this.topTableFilter = optimizer.getTopFilter();
    double planCost = optimizer.getCost();
    
    setEvaluatableRecursive(this.topTableFilter);
    
    this.topTableFilter.prepare();
    return planCost;
  }
  
  private void setEvaluatableRecursive(TableFilter f)
  {
    for (; f != null; f = f.getJoin())
    {
      f.setEvaluatable(f, true);
      if (this.condition != null) {
        this.condition.setEvaluatable(f, true);
      }
      TableFilter n = f.getNestedJoin();
      if (n != null) {
        setEvaluatableRecursive(n);
      }
      Expression on = f.getJoinCondition();
      if ((on != null) && 
        (!on.isEverything(ExpressionVisitor.EVALUATABLE_VISITOR))) {
        if (this.session.getDatabase().getSettings().nestedJoins)
        {
          on = on.optimize(this.session);
          if ((!f.isJoinOuter()) && (!f.isJoinOuterIndirect()))
          {
            f.removeJoinCondition();
            addCondition(on);
          }
        }
        else
        {
          if (f.isJoinOuter())
          {
            on = on.optimize(this.session);
            
            throw DbException.get(90136, on.getSQL());
          }
          f.removeJoinCondition();
          
          on = on.optimize(this.session);
          addCondition(on);
        }
      }
      on = f.getFilterCondition();
      if ((on != null) && 
        (!on.isEverything(ExpressionVisitor.EVALUATABLE_VISITOR)))
      {
        f.removeFilterCondition();
        addCondition(on);
      }
      for (Expression e : this.expressions) {
        e.setEvaluatable(f, true);
      }
    }
  }
  
  public String getPlanSQL()
  {
    Expression[] exprList = (Expression[])this.expressions.toArray(new Expression[this.expressions.size()]);
    
    StatementBuilder buff = new StatementBuilder("SELECT");
    if (this.distinct) {
      buff.append(" DISTINCT");
    }
    for (int i = 0; i < this.visibleColumnCount; i++)
    {
      buff.appendExceptFirst(",");
      buff.append('\n');
      buff.append(StringUtils.indent(exprList[i].getSQL(), 4, false));
    }
    buff.append("\nFROM ");
    TableFilter filter = this.topTableFilter;
    int i;
    if (filter != null)
    {
      buff.resetCount();
      int i = 0;
      do
      {
        buff.appendExceptFirst("\n");
        buff.append(filter.getPlanSQL(i++ > 0));
        filter = filter.getJoin();
      } while (filter != null);
    }
    else
    {
      buff.resetCount();
      i = 0;
      for (TableFilter f : this.topFilters) {
        do
        {
          buff.appendExceptFirst("\n");
          buff.append(f.getPlanSQL(i++ > 0));
          f = f.getJoin();
        } while (f != null);
      }
    }
    if (this.condition != null) {
      buff.append("\nWHERE ").append(StringUtils.unEnclose(this.condition.getSQL()));
    }
    if (this.groupIndex != null)
    {
      buff.append("\nGROUP BY ");
      buff.resetCount();
      for (int gi : this.groupIndex)
      {
        Expression g = exprList[gi];
        g = g.getNonAliasExpression();
        buff.appendExceptFirst(", ");
        buff.append(StringUtils.unEnclose(g.getSQL()));
      }
    }
    if (this.group != null)
    {
      buff.append("\nGROUP BY ");
      buff.resetCount();
      for (Expression g : this.group)
      {
        buff.appendExceptFirst(", ");
        buff.append(StringUtils.unEnclose(g.getSQL()));
      }
    }
    if (this.having != null)
    {
      Expression h = this.having;
      buff.append("\nHAVING ").append(StringUtils.unEnclose(h.getSQL()));
    }
    else if (this.havingIndex >= 0)
    {
      Expression h = exprList[this.havingIndex];
      buff.append("\nHAVING ").append(StringUtils.unEnclose(h.getSQL()));
    }
    if (this.sort != null) {
      buff.append("\nORDER BY ").append(this.sort.getSQL(exprList, this.visibleColumnCount));
    }
    if (this.orderList != null)
    {
      buff.append("\nORDER BY ");
      buff.resetCount();
      for (SelectOrderBy o : this.orderList)
      {
        buff.appendExceptFirst(", ");
        buff.append(StringUtils.unEnclose(o.getSQL()));
      }
    }
    if (this.limitExpr != null)
    {
      buff.append("\nLIMIT ").append(StringUtils.unEnclose(this.limitExpr.getSQL()));
      if (this.offsetExpr != null) {
        buff.append(" OFFSET ").append(StringUtils.unEnclose(this.offsetExpr.getSQL()));
      }
    }
    if (this.sampleSizeExpr != null) {
      buff.append("\nSAMPLE_SIZE ").append(StringUtils.unEnclose(this.sampleSizeExpr.getSQL()));
    }
    if (this.isForUpdate) {
      buff.append("\nFOR UPDATE");
    }
    if (this.isQuickAggregateQuery) {
      buff.append("\n/* direct lookup */");
    }
    if (this.isDistinctQuery) {
      buff.append("\n/* distinct */");
    }
    if (this.sortUsingIndex) {
      buff.append("\n/* index sorted */");
    }
    if ((this.isGroupQuery) && 
      (this.isGroupSortedQuery)) {
      buff.append("\n/* group sorted */");
    }
    return buff.toString();
  }
  
  public void setHaving(Expression having)
  {
    this.having = having;
  }
  
  public Expression getHaving()
  {
    return this.having;
  }
  
  public int getColumnCount()
  {
    return this.visibleColumnCount;
  }
  
  public TableFilter getTopTableFilter()
  {
    return this.topTableFilter;
  }
  
  public ArrayList<Expression> getExpressions()
  {
    return this.expressions;
  }
  
  public void setForUpdate(boolean b)
  {
    this.isForUpdate = b;
    if ((this.session.getDatabase().getSettings().selectForUpdateMvcc) && (this.session.getDatabase().isMultiVersion())) {
      this.isForUpdateMvcc = b;
    }
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    for (Expression e : this.expressions) {
      e.mapColumns(resolver, level);
    }
    if (this.condition != null) {
      this.condition.mapColumns(resolver, level);
    }
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    for (Expression e : this.expressions) {
      e.setEvaluatable(tableFilter, b);
    }
    if (this.condition != null) {
      this.condition.setEvaluatable(tableFilter, b);
    }
  }
  
  public boolean isQuickAggregateQuery()
  {
    return this.isQuickAggregateQuery;
  }
  
  public void addGlobalCondition(Parameter param, int columnId, int comparisonType)
  {
    addParameter(param);
    
    Expression col = (Expression)this.expressions.get(columnId);
    col = col.getNonAliasExpression();
    Expression comp;
    if (col.isEverything(ExpressionVisitor.QUERY_COMPARABLE_VISITOR)) {
      comp = new Comparison(this.session, comparisonType, col, param);
    } else {
      comp = new Comparison(this.session, 16, param, param);
    }
    Expression comp = comp.optimize(this.session);
    boolean addToCondition = true;
    if (this.isGroupQuery)
    {
      addToCondition = false;
      for (int i = 0; (this.groupIndex != null) && (i < this.groupIndex.length); i++) {
        if (this.groupIndex[i] == columnId)
        {
          addToCondition = true;
          break;
        }
      }
      if (!addToCondition)
      {
        if (this.havingIndex >= 0) {
          this.having = ((Expression)this.expressions.get(this.havingIndex));
        }
        if (this.having == null) {
          this.having = comp;
        } else {
          this.having = new ConditionAndOr(0, this.having, comp);
        }
      }
    }
    if (addToCondition) {
      if (this.condition == null) {
        this.condition = comp;
      } else {
        this.condition = new ConditionAndOr(0, this.condition, comp);
      }
    }
  }
  
  public void updateAggregate(Session s)
  {
    for (Expression e : this.expressions) {
      e.updateAggregate(s);
    }
    if (this.condition != null) {
      this.condition.updateAggregate(s);
    }
    if (this.having != null) {
      this.having.updateAggregate(s);
    }
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 2: 
      if (this.isForUpdate) {
        return false;
      }
      int i = 0;
      for (int size = this.filters.size(); i < size; i++)
      {
        TableFilter f = (TableFilter)this.filters.get(i);
        if (!f.getTable().isDeterministic()) {
          return false;
        }
      }
      break;
    case 4: 
      int i = 0;
      for (int size = this.filters.size(); i < size; i++)
      {
        TableFilter f = (TableFilter)this.filters.get(i);
        long m = f.getTable().getMaxDataModificationId();
        visitor.addDataModificationId(m);
      }
      break;
    case 3: 
      if (!this.session.getDatabase().getSettings().optimizeEvaluatableSubqueries) {
        return false;
      }
      break;
    case 7: 
      int i = 0;
      for (int size = this.filters.size(); i < size; i++)
      {
        TableFilter f = (TableFilter)this.filters.get(i);
        Table table = f.getTable();
        visitor.addDependency(table);
        table.addDependencies(visitor.getDependencies());
      }
      break;
    }
    ExpressionVisitor v2 = visitor.incrementQueryLevel(1);
    boolean result = true;
    int i = 0;
    for (int size = this.expressions.size(); i < size; i++)
    {
      Expression e = (Expression)this.expressions.get(i);
      if (!e.isEverything(v2))
      {
        result = false;
        break;
      }
    }
    if ((result) && (this.condition != null) && (!this.condition.isEverything(v2))) {
      result = false;
    }
    if ((result) && (this.having != null) && (!this.having.isEverything(v2))) {
      result = false;
    }
    return result;
  }
  
  public boolean isReadOnly()
  {
    return isEverything(ExpressionVisitor.READONLY_VISITOR);
  }
  
  public boolean isCacheable()
  {
    return !this.isForUpdate;
  }
  
  public int getType()
  {
    return 66;
  }
  
  public boolean allowGlobalConditions()
  {
    if ((this.offsetExpr == null) && ((this.limitExpr == null) || (this.sort == null))) {
      return true;
    }
    return false;
  }
  
  public SortOrder getSortOrder()
  {
    return this.sort;
  }
}
