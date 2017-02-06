package org.h2.index;

import org.h2.engine.Database;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.schema.SchemaObjectBase;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.MathUtils;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public abstract class BaseIndex
  extends SchemaObjectBase
  implements Index
{
  protected IndexColumn[] indexColumns;
  protected Column[] columns;
  protected int[] columnIds;
  protected Table table;
  protected IndexType indexType;
  protected boolean isMultiVersion;
  
  protected void initBaseIndex(Table newTable, int id, String name, IndexColumn[] newIndexColumns, IndexType newIndexType)
  {
    initSchemaObjectBase(newTable.getSchema(), id, name, "index");
    this.indexType = newIndexType;
    this.table = newTable;
    if (newIndexColumns != null)
    {
      this.indexColumns = newIndexColumns;
      this.columns = new Column[newIndexColumns.length];
      int len = this.columns.length;
      this.columnIds = new int[len];
      for (int i = 0; i < len; i++)
      {
        Column col = newIndexColumns[i].column;
        this.columns[i] = col;
        this.columnIds[i] = col.getColumnId();
      }
    }
  }
  
  protected static void checkIndexColumnTypes(IndexColumn[] columns)
  {
    for (IndexColumn c : columns)
    {
      int type = c.column.getType();
      if ((type == 16) || (type == 15)) {
        throw DbException.getUnsupportedException("Index on BLOB or CLOB column: " + c.column.getCreateSQL());
      }
    }
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  protected DbException getDuplicateKeyException(String key)
  {
    String sql = getName() + " ON " + this.table.getSQL() + "(" + getColumnListSQL() + ")";
    if (key != null) {
      sql = sql + " VALUES " + key;
    }
    DbException e = DbException.get(23505, sql);
    e.setSource(this);
    return e;
  }
  
  public String getPlanSQL()
  {
    return getSQL();
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.table.removeIndex(this);
    remove(session);
    this.database.removeMeta(session, getId());
  }
  
  public boolean canFindNext()
  {
    return false;
  }
  
  public Cursor find(TableFilter filter, SearchRow first, SearchRow last)
  {
    return find(filter.getSession(), first, last);
  }
  
  public Cursor findNext(Session session, SearchRow higherThan, SearchRow last)
  {
    throw DbException.throwInternalError();
  }
  
  protected long getCostRangeIndex(int[] masks, long rowCount, TableFilter filter, SortOrder sortOrder)
  {
    rowCount += 1000L;
    long cost = rowCount;
    long rows = rowCount;
    int totalSelectivity = 0;
    if (masks == null) {
      return cost;
    }
    int i = 0;
    for (int len = this.columns.length; i < len; i++)
    {
      Column column = this.columns[i];
      int index = column.getColumnId();
      int mask = masks[index];
      if ((mask & 0x1) == 1)
      {
        if ((i == this.columns.length - 1) && (getIndexType().isUnique()))
        {
          cost = 3L;
          break;
        }
        totalSelectivity = 100 - (100 - totalSelectivity) * (100 - column.getSelectivity()) / 100;
        
        long distinctRows = rowCount * totalSelectivity / 100L;
        if (distinctRows <= 0L) {
          distinctRows = 1L;
        }
        rows = Math.max(rowCount / distinctRows, 1L);
        cost = 2L + rows;
      }
      else
      {
        if ((mask & 0x6) == 6)
        {
          cost = 2L + rows / 4L;
          break;
        }
        if ((mask & 0x2) == 2)
        {
          cost = 2L + rows / 3L;
          break;
        }
        if ((mask & 0x4) != 4) {
          break;
        }
        cost = rows / 3L;
        break;
      }
    }
    if (sortOrder != null)
    {
      boolean sortOrderMatches = true;
      int coveringCount = 0;
      int[] sortTypes = sortOrder.getSortTypes();
      int i = 0;
      for (int len = sortTypes.length; i < len; i++)
      {
        if (i >= this.indexColumns.length) {
          break;
        }
        Column col = sortOrder.getColumn(i, filter);
        if (col == null)
        {
          sortOrderMatches = false;
          break;
        }
        IndexColumn indexCol = this.indexColumns[i];
        if (col != indexCol.column)
        {
          sortOrderMatches = false;
          break;
        }
        int sortType = sortTypes[i];
        if (sortType != indexCol.sortType)
        {
          sortOrderMatches = false;
          break;
        }
        coveringCount++;
      }
      if (sortOrderMatches) {
        cost -= coveringCount;
      }
    }
    return cost;
  }
  
  public int compareRows(SearchRow rowData, SearchRow compare)
  {
    if (rowData == compare) {
      return 0;
    }
    int i = 0;
    for (int len = this.indexColumns.length; i < len; i++)
    {
      int index = this.columnIds[i];
      Value v = compare.getValue(index);
      if (v == null) {
        return 0;
      }
      int c = compareValues(rowData.getValue(index), v, this.indexColumns[i].sortType);
      if (c != 0) {
        return c;
      }
    }
    return 0;
  }
  
  protected boolean containsNullAndAllowMultipleNull(SearchRow newRow)
  {
    Mode mode = this.database.getMode();
    if (mode.uniqueIndexSingleNull) {
      return false;
    }
    if (mode.uniqueIndexSingleNullExceptAllColumnsAreNull)
    {
      for (int index : this.columnIds)
      {
        Value v = newRow.getValue(index);
        if (v != ValueNull.INSTANCE) {
          return false;
        }
      }
      return true;
    }
    for (int index : this.columnIds)
    {
      Value v = newRow.getValue(index);
      if (v == ValueNull.INSTANCE) {
        return true;
      }
    }
    return false;
  }
  
  int compareKeys(SearchRow rowData, SearchRow compare)
  {
    long k1 = rowData.getKey();
    long k2 = compare.getKey();
    if (k1 == k2)
    {
      if (this.isMultiVersion)
      {
        int v1 = rowData.getVersion();
        int v2 = compare.getVersion();
        return MathUtils.compareInt(v2, v1);
      }
      return 0;
    }
    return k1 > k2 ? 1 : -1;
  }
  
  private int compareValues(Value a, Value b, int sortType)
  {
    if (a == b) {
      return 0;
    }
    boolean aNull = a == null;boolean bNull = b == null;
    if ((aNull) || (bNull)) {
      return SortOrder.compareNull(aNull, sortType);
    }
    int comp = this.table.compareTypeSave(a, b);
    if ((sortType & 0x1) != 0) {
      comp = -comp;
    }
    return comp;
  }
  
  public int getColumnIndex(Column col)
  {
    int i = 0;
    for (int len = this.columns.length; i < len; i++) {
      if (this.columns[i].equals(col)) {
        return i;
      }
    }
    return -1;
  }
  
  private String getColumnListSQL()
  {
    StatementBuilder buff = new StatementBuilder();
    for (IndexColumn c : this.indexColumns)
    {
      buff.appendExceptFirst(", ");
      buff.append(c.getSQL());
    }
    return buff.toString();
  }
  
  public String getCreateSQLForCopy(Table targetTable, String quotedName)
  {
    StringBuilder buff = new StringBuilder("CREATE ");
    buff.append(this.indexType.getSQL());
    buff.append(' ');
    if (this.table.isHidden()) {
      buff.append("IF NOT EXISTS ");
    }
    buff.append(quotedName);
    buff.append(" ON ").append(targetTable.getSQL());
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    buff.append('(').append(getColumnListSQL()).append(')');
    return buff.toString();
  }
  
  public String getCreateSQL()
  {
    return getCreateSQLForCopy(this.table, getSQL());
  }
  
  public IndexColumn[] getIndexColumns()
  {
    return this.indexColumns;
  }
  
  public Column[] getColumns()
  {
    return this.columns;
  }
  
  public IndexType getIndexType()
  {
    return this.indexType;
  }
  
  public int getType()
  {
    return 1;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public void commit(int operation, Row row) {}
  
  void setMultiVersion(boolean multiVersion)
  {
    this.isMultiVersion = multiVersion;
  }
  
  public Row getRow(Session session, long key)
  {
    throw DbException.getUnsupportedException(toString());
  }
  
  public boolean isHidden()
  {
    return this.table.isHidden();
  }
  
  public boolean isRowIdIndex()
  {
    return false;
  }
  
  public boolean canScan()
  {
    return true;
  }
  
  public void setSortedInsertMode(boolean sortedInsertMode) {}
}
