package org.h2.index;

import java.util.Iterator;
import java.util.Set;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.db.MVTableEngine;
import org.h2.mvstore.db.MVTableEngine.Store;
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

public class SpatialTreeIndex
  extends BaseIndex
  implements SpatialIndex
{
  private static final String MAP_PREFIX = "RTREE_";
  private final MVRTreeMap<Long> treeMap;
  private final MVStore store;
  private boolean closed;
  private boolean needRebuild;
  
  public SpatialTreeIndex(Table table, int id, String indexName, IndexColumn[] columns, IndexType indexType, boolean persistent, boolean create, Session session)
  {
    if (indexType.isUnique()) {
      throw DbException.getUnsupportedException("not unique");
    }
    if ((!persistent) && (!create)) {
      throw DbException.getUnsupportedException("Non persistent index called with create==false");
    }
    if (columns.length > 1) {
      throw DbException.getUnsupportedException("can only do one column");
    }
    if ((columns[0].sortType & 0x1) != 0) {
      throw DbException.getUnsupportedException("cannot do descending");
    }
    if ((columns[0].sortType & 0x2) != 0) {
      throw DbException.getUnsupportedException("cannot do nulls first");
    }
    if ((columns[0].sortType & 0x4) != 0) {
      throw DbException.getUnsupportedException("cannot do nulls last");
    }
    initBaseIndex(table, id, indexName, columns, indexType);
    this.needRebuild = create;
    this.table = table;
    if ((!this.database.isStarting()) && 
      (columns[0].column.getType() != 22)) {
      throw DbException.getUnsupportedException("spatial index on non-geometry column, " + columns[0].column.getCreateSQL());
    }
    if (!persistent)
    {
      this.store = MVStore.open(null);
      this.treeMap = ((MVRTreeMap)this.store.openMap("spatialIndex", new MVRTreeMap.Builder()));
    }
    else
    {
      if (id < 0) {
        throw DbException.getUnsupportedException("Persistent index with id<0");
      }
      MVTableEngine.init(session.getDatabase());
      this.store = session.getDatabase().getMvStore().getStore();
      
      this.treeMap = ((MVRTreeMap)this.store.openMap("RTREE_" + getId(), new MVRTreeMap.Builder()));
      if (this.treeMap.isEmpty()) {
        this.needRebuild = true;
      }
    }
  }
  
  public void close(Session session)
  {
    this.store.close();
    this.closed = true;
  }
  
  public void add(Session session, Row row)
  {
    if (this.closed) {
      throw DbException.throwInternalError();
    }
  }
  
  public void remove(Session session, Row row)
  {
    if (this.closed) {
      throw DbException.throwInternalError();
    }
  }
  
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
    return new SpatialCursor(this.treeMap.keySet().iterator(), this.table, session);
  }
  
  public Cursor findByGeometry(TableFilter filter, SearchRow intersection)
  {
    if (intersection == null) {
      return find(filter.getSession());
    }
    return null;
  }
  
  protected long getCostRangeIndex(int[] masks, long rowCount, TableFilter filter, SortOrder sortOrder)
  {
    return getCostRangeIndex(masks, rowCount, this.columns);
  }
  
  public static long getCostRangeIndex(int[] masks, long rowCount, Column[] columns)
  {
    rowCount += 1000L;
    long cost = rowCount;
    if (masks == null) {
      return cost;
    }
    for (Column column : columns)
    {
      int index = column.getColumnId();
      int mask = masks[index];
      if ((mask & 0x10) != 0) {
        cost = 3L + rowCount / 4L;
      }
    }
    return 10L * cost;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return getCostRangeIndex(masks, this.table.getRowCountApproximation(), filter, sortOrder);
  }
  
  public void remove(Session session)
  {
    if (!this.treeMap.isClosed()) {
      this.store.removeMap(this.treeMap);
    }
  }
  
  public void truncate(Session session)
  {
    this.treeMap.clear();
  }
  
  public void checkRename() {}
  
  public boolean needRebuild()
  {
    return this.needRebuild;
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    if (this.closed) {
      throw DbException.throwInternalError();
    }
    if (!first) {
      throw DbException.throwInternalError("Spatial Index can only be fetch by ascending order");
    }
    return find(session);
  }
  
  public long getRowCount(Session session)
  {
    return this.treeMap.sizeAsLong();
  }
  
  public long getRowCountApproximation()
  {
    return this.treeMap.sizeAsLong();
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  private static final class SpatialCursor
    implements Cursor
  {
    private final Iterator<SpatialKey> it;
    private SpatialKey current;
    private final Table table;
    private Session session;
    
    public SpatialCursor(Iterator<SpatialKey> it, Table table, Session session)
    {
      this.it = it;
      this.table = table;
      this.session = session;
    }
    
    public Row get()
    {
      return this.table.getRow(this.session, this.current.getId());
    }
    
    public SearchRow getSearchRow()
    {
      return get();
    }
    
    public boolean next()
    {
      if (!this.it.hasNext()) {
        return false;
      }
      this.current = ((SpatialKey)this.it.next());
      return true;
    }
    
    public boolean previous()
    {
      return false;
    }
  }
}
