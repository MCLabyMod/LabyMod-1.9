package org.h2.mvstore.db;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.index.BaseIndex;
import org.h2.index.Cursor;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils.MapEntry;
import org.h2.mvstore.MVMap;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.store.LobStorageInterface;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;

public class MVPrimaryIndex
  extends BaseIndex
{
  static final ValueLong MIN = ValueLong.get(Long.MIN_VALUE);
  static final ValueLong MAX = ValueLong.get(Long.MAX_VALUE);
  static final ValueLong ZERO = ValueLong.get(0L);
  private final MVTable mvTable;
  private final String mapName;
  private TransactionStore.TransactionMap<Value, Value> dataMap;
  private long lastKey;
  private int mainIndexColumn = -1;
  
  public MVPrimaryIndex(Database db, MVTable table, int id, IndexColumn[] columns, IndexType indexType)
  {
    this.mvTable = table;
    initBaseIndex(table, id, table.getName() + "_DATA", columns, indexType);
    int[] sortTypes = new int[columns.length];
    for (int i = 0; i < columns.length; i++) {
      sortTypes[i] = 0;
    }
    ValueDataType keyType = new ValueDataType(null, null, null);
    ValueDataType valueType = new ValueDataType(db.getCompareMode(), db, sortTypes);
    
    this.mapName = ("table." + getId());
    TransactionStore.Transaction t = this.mvTable.getTransaction(null);
    this.dataMap = t.openMap(this.mapName, keyType, valueType);
    t.commit();
    if (!table.isPersistData()) {
      this.dataMap.map.setVolatile(true);
    }
    Value k = (Value)this.dataMap.lastKey();
    this.lastKey = (k == null ? 0L : k.getLong());
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public String getPlanSQL()
  {
    return this.table.getSQL() + ".tableScan";
  }
  
  public void setMainIndexColumn(int mainIndexColumn)
  {
    this.mainIndexColumn = mainIndexColumn;
  }
  
  public int getMainIndexColumn()
  {
    return this.mainIndexColumn;
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    if (this.mainIndexColumn == -1)
    {
      if (row.getKey() == 0L) {
        row.setKey(++this.lastKey);
      }
    }
    else
    {
      long c = row.getValue(this.mainIndexColumn).getLong();
      row.setKey(c);
    }
    if (this.mvTable.getContainsLargeObject())
    {
      int i = 0;
      for (int len = row.getColumnCount(); i < len; i++)
      {
        Value v = row.getValue(i);
        Value v2 = v.link(this.database, getId());
        if (v2.isLinked()) {
          session.unlinkAtCommitStop(v2);
        }
        if (v != v2) {
          row.setValue(i, v2);
        }
      }
    }
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    Value key = ValueLong.get(row.getKey());
    Value old = (Value)map.getLatest(key);
    if (old != null)
    {
      String sql = "PRIMARY KEY ON " + this.table.getSQL();
      if ((this.mainIndexColumn >= 0) && (this.mainIndexColumn < this.indexColumns.length)) {
        sql = sql + "(" + this.indexColumns[this.mainIndexColumn].getSQL() + ")";
      }
      DbException e = DbException.get(23505, sql);
      e.setSource(this);
      throw e;
    }
    try
    {
      map.put(key, ValueArray.get(row.getValueList()));
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90131, e, new String[] { this.table.getName() });
    }
    this.lastKey = Math.max(this.lastKey, row.getKey());
  }
  
  public void remove(Session session, Row row)
  {
    if (this.mvTable.getContainsLargeObject())
    {
      int i = 0;
      for (int len = row.getColumnCount(); i < len; i++)
      {
        Value v = row.getValue(i);
        if (v.isLinked()) {
          session.unlinkAtCommit(v);
        }
      }
    }
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    try
    {
      Value old = (Value)map.remove(ValueLong.get(row.getKey()));
      if (old == null) {
        throw DbException.get(90112, getSQL() + ": " + row.getKey());
      }
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90131, e, new String[] { this.table.getName() });
    }
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    ValueLong min;
    ValueLong min;
    if (first == null)
    {
      min = MIN;
    }
    else
    {
      ValueLong min;
      if (this.mainIndexColumn < 0)
      {
        min = ValueLong.get(first.getKey());
      }
      else
      {
        ValueLong v = (ValueLong)first.getValue(this.mainIndexColumn);
        ValueLong min;
        if (v == null) {
          min = ValueLong.get(first.getKey());
        } else {
          min = v;
        }
      }
    }
    ValueLong max;
    ValueLong max;
    if (last == null)
    {
      max = MAX;
    }
    else
    {
      ValueLong max;
      if (this.mainIndexColumn < 0)
      {
        max = ValueLong.get(last.getKey());
      }
      else
      {
        ValueLong v = (ValueLong)last.getValue(this.mainIndexColumn);
        ValueLong max;
        if (v == null) {
          max = ValueLong.get(last.getKey());
        } else {
          max = v;
        }
      }
    }
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    return new MVStoreCursor(map.entryIterator(min), max);
  }
  
  public MVTable getTable()
  {
    return this.mvTable;
  }
  
  public Row getRow(Session session, long key)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    Value v = (Value)map.get(ValueLong.get(key));
    ValueArray array = (ValueArray)v;
    Row row = new Row(array.getList(), 0);
    row.setKey(key);
    return row;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    try
    {
      long cost = 10L * (this.dataMap.sizeAsLongMax() + 1000L);
      return cost;
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90007, e, new String[0]);
    }
  }
  
  public int getColumnIndex(Column col)
  {
    return -1;
  }
  
  public void remove(Session session)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    if (!map.isClosed())
    {
      TransactionStore.Transaction t = this.mvTable.getTransaction(session);
      t.removeMap(map);
    }
  }
  
  public void truncate(Session session)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    if (this.mvTable.getContainsLargeObject()) {
      this.database.getLobStorage().removeAllForTable(this.table.getId());
    }
    map.clear();
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    ValueLong v = (ValueLong)(first ? (Value)map.firstKey() : (Value)map.lastKey());
    if (v == null) {
      return new MVStoreCursor(Collections.emptyList().iterator(), null);
    }
    Value value = (Value)map.get(v);
    Map.Entry<Value, Value> e = new DataUtils.MapEntry(v, value);
    
    List<Map.Entry<Value, Value>> list = Arrays.asList(new Map.Entry[] { e });
    MVStoreCursor c = new MVStoreCursor(list.iterator(), v);
    c.next();
    return c;
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public long getRowCount(Session session)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    return map.sizeAsLong();
  }
  
  public long getRowCountMax()
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
  
  public long getRowCountApproximation()
  {
    return getRowCountMax();
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public String getMapName()
  {
    return this.mapName;
  }
  
  public void checkRename() {}
  
  ValueLong getKey(SearchRow row, ValueLong ifEmpty, ValueLong ifNull)
  {
    if (row == null) {
      return ifEmpty;
    }
    Value v = row.getValue(this.mainIndexColumn);
    if (v == null) {
      throw DbException.throwInternalError(row.toString());
    }
    if (v == ValueNull.INSTANCE) {
      return ifNull;
    }
    return (ValueLong)v.convertTo(5);
  }
  
  Cursor find(Session session, ValueLong first, ValueLong last)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    return new MVStoreCursor(map.entryIterator(first), last);
  }
  
  public boolean isRowIdIndex()
  {
    return true;
  }
  
  TransactionStore.TransactionMap<Value, Value> getMap(Session session)
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
    private final Iterator<Map.Entry<Value, Value>> it;
    private final ValueLong last;
    private Map.Entry<Value, Value> current;
    private Row row;
    
    public MVStoreCursor(ValueLong it)
    {
      this.it = it;
      this.last = last;
    }
    
    public Row get()
    {
      if ((this.row == null) && 
        (this.current != null))
      {
        ValueArray array = (ValueArray)this.current.getValue();
        this.row = new Row(array.getList(), 0);
        this.row.setKey(((Value)this.current.getKey()).getLong());
      }
      return this.row;
    }
    
    public SearchRow getSearchRow()
    {
      return get();
    }
    
    public boolean next()
    {
      this.current = (this.it.hasNext() ? (Map.Entry)this.it.next() : null);
      if ((this.current != null) && (((Value)this.current.getKey()).getLong() > this.last.getLong())) {
        this.current = null;
      }
      this.row = null;
      return this.current != null;
    }
    
    public boolean previous()
    {
      throw DbException.getUnsupportedException("previous");
    }
  }
}
