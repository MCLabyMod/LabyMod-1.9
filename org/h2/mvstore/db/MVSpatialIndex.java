package org.h2.mvstore.db;

import java.util.Iterator;
import java.util.List;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.index.BaseIndex;
import org.h2.index.Cursor;
import org.h2.index.IndexType;
import org.h2.index.SpatialIndex;
import org.h2.index.SpatialTreeIndex;
import org.h2.message.DbException;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.rtree.MVRTreeMap;
import org.h2.mvstore.rtree.MVRTreeMap.Builder;
import org.h2.mvstore.rtree.SpatialKey;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.value.Value;

public class MVSpatialIndex
  extends BaseIndex
  implements SpatialIndex, MVIndex
{
  final MVTable mvTable;
  private final String mapName;
  private TransactionStore.TransactionMap<SpatialKey, Value> dataMap;
  private MVRTreeMap<TransactionStore.VersionedValue> spatialMap;
  
  public MVSpatialIndex(Database db, MVTable table, int id, String indexName, IndexColumn[] columns, IndexType indexType)
  {
    if (columns.length != 1) {
      throw DbException.getUnsupportedException("Can only index one column");
    }
    IndexColumn col = columns[0];
    if ((col.sortType & 0x1) != 0) {
      throw DbException.getUnsupportedException("Cannot index in descending order");
    }
    if ((col.sortType & 0x2) != 0) {
      throw DbException.getUnsupportedException("Nulls first is not supported");
    }
    if ((col.sortType & 0x4) != 0) {
      throw DbException.getUnsupportedException("Nulls last is not supported");
    }
    if (col.column.getType() != 22) {
      throw DbException.getUnsupportedException("Spatial index on non-geometry column, " + col.column.getCreateSQL());
    }
    this.mvTable = table;
    initBaseIndex(table, id, indexName, columns, indexType);
    if (!this.database.isStarting()) {
      checkIndexColumnTypes(columns);
    }
    this.mapName = ("index." + getId());
    ValueDataType vt = new ValueDataType(null, null, null);
    TransactionStore.VersionedValueType valueType = new TransactionStore.VersionedValueType(vt);
    MVRTreeMap.Builder<TransactionStore.VersionedValue> mapBuilder = new MVRTreeMap.Builder().valueType(valueType);
    
    this.spatialMap = ((MVRTreeMap)db.getMvStore().getStore().openMap(this.mapName, mapBuilder));
    TransactionStore.Transaction t = this.mvTable.getTransaction(null);
    this.dataMap = t.openMap(this.spatialMap);
    t.commit();
  }
  
  public void addRowsToBuffer(List<Row> rows, String bufferName)
  {
    throw DbException.throwInternalError();
  }
  
  public void addBufferedRows(List<String> bufferNames)
  {
    throw DbException.throwInternalError();
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    TransactionStore.TransactionMap<SpatialKey, Value> map = getMap(session);
  }
  
  public void remove(Session session, Row row) {}
  
  public Cursor find(TableFilter filter, SearchRow first, SearchRow last)
  {
    return find(filter.getSession());
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    return find(session);
  }
  
  private Cursor find(Session session)
  {
    Iterator<SpatialKey> cursor = this.spatialMap.keyIterator(null);
    TransactionStore.TransactionMap<SpatialKey, Value> map = getMap(session);
    Iterator<SpatialKey> it = map.wrapIterator(cursor, false);
    return new MVStoreCursor(session, it);
  }
  
  public Cursor findByGeometry(TableFilter filter, SearchRow intersection)
  {
    Session session = filter.getSession();
    if (intersection == null) {
      return find(session);
    }
    return null;
  }
  
  SearchRow getRow(SpatialKey key)
  {
    SearchRow searchRow = this.mvTable.getTemplateRow();
    searchRow.setKey(key.getId());
    return searchRow;
  }
  
  public MVTable getTable()
  {
    return this.mvTable;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return getCostRangeIndex(masks, this.table.getRowCountApproximation(), filter, sortOrder);
  }
  
  protected long getCostRangeIndex(int[] masks, long rowCount, TableFilter filter, SortOrder sortOrder)
  {
    return SpatialTreeIndex.getCostRangeIndex(masks, rowCount, this.columns);
  }
  
  public void remove(Session session)
  {
    TransactionStore.TransactionMap<SpatialKey, Value> map = getMap(session);
    if (!map.isClosed())
    {
      TransactionStore.Transaction t = this.mvTable.getTransaction(session);
      t.removeMap(map);
    }
  }
  
  public void truncate(Session session)
  {
    TransactionStore.TransactionMap<SpatialKey, Value> map = getMap(session);
    map.clear();
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    if (!first) {
      throw DbException.throwInternalError("Spatial Index can only be fetch in ascending order");
    }
    return find(session);
  }
  
  public boolean needRebuild()
  {
    try
    {
      return this.dataMap.sizeAsLongMax() == 0L;
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90007, e, new String[0]);
    }
  }
  
  public long getRowCount(Session session)
  {
    TransactionStore.TransactionMap<SpatialKey, Value> map = getMap(session);
    return map.sizeAsLong();
  }
  
  public long getRowCountApproximation()
  {
    try
    {
      return this.dataMap.sizeAsLongMax();
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90007, e, new String[0]);
    }
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public void checkRename() {}
  
  TransactionStore.TransactionMap<SpatialKey, Value> getMap(Session session)
  {
    if (session == null) {
      return this.dataMap;
    }
    TransactionStore.Transaction t = this.mvTable.getTransaction(session);
    return this.dataMap.getInstance(t, Long.MAX_VALUE);
  }
  
  class MVStoreCursor
    implements Cursor
  {
    private final Session session;
    private final Iterator<SpatialKey> it;
    private SpatialKey current;
    private SearchRow searchRow;
    private Row row;
    
    public MVStoreCursor(Iterator<SpatialKey> session)
    {
      this.session = session;
      this.it = it;
    }
    
    public Row get()
    {
      if (this.row == null)
      {
        SearchRow r = getSearchRow();
        if (r != null) {
          this.row = MVSpatialIndex.this.mvTable.getRow(this.session, r.getKey());
        }
      }
      return this.row;
    }
    
    public SearchRow getSearchRow()
    {
      if ((this.searchRow == null) && 
        (this.current != null)) {
        this.searchRow = MVSpatialIndex.this.getRow(this.current);
      }
      return this.searchRow;
    }
    
    public boolean next()
    {
      this.current = ((SpatialKey)this.it.next());
      this.searchRow = null;
      this.row = null;
      return this.current != null;
    }
    
    public boolean previous()
    {
      throw DbException.getUnsupportedException("previous");
    }
  }
}
