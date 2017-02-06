package org.h2.mvstore.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.index.BaseIndex;
import org.h2.index.Cursor;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVMap.Builder;
import org.h2.mvstore.MVStore;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;

public class MVSecondaryIndex
  extends BaseIndex
  implements MVIndex
{
  final MVTable mvTable;
  private final int keyColumns;
  private final String mapName;
  private TransactionStore.TransactionMap<Value, Value> dataMap;
  
  public MVSecondaryIndex(Database db, MVTable table, int id, String indexName, IndexColumn[] columns, IndexType indexType)
  {
    this.mvTable = table;
    initBaseIndex(table, id, indexName, columns, indexType);
    if (!this.database.isStarting()) {
      checkIndexColumnTypes(columns);
    }
    this.keyColumns = (columns.length + 1);
    this.mapName = ("index." + getId());
    int[] sortTypes = new int[this.keyColumns];
    for (int i = 0; i < columns.length; i++) {
      sortTypes[i] = columns[i].sortType;
    }
    sortTypes[(this.keyColumns - 1)] = 0;
    ValueDataType keyType = new ValueDataType(db.getCompareMode(), db, sortTypes);
    
    ValueDataType valueType = new ValueDataType(null, null, null);
    TransactionStore.Transaction t = this.mvTable.getTransaction(null);
    this.dataMap = t.openMap(this.mapName, keyType, valueType);
    t.commit();
    if (!keyType.equals(this.dataMap.getKeyType())) {
      throw DbException.throwInternalError("Incompatible key type");
    }
  }
  
  public void addRowsToBuffer(List<Row> rows, String bufferName)
  {
    MVMap<Value, Value> map = openMap(bufferName);
    for (Row row : rows)
    {
      ValueArray key = convertToKey(row);
      map.put(key, ValueNull.INSTANCE);
    }
  }
  
  public void addBufferedRows(List<String> bufferNames)
  {
    ArrayList<String> mapNames = New.arrayList(bufferNames);
    final CompareMode compareMode = this.database.getCompareMode();
    
    TreeSet<1Source> sources = new TreeSet();
    for (int i = 0; i < bufferNames.size(); i++)
    {
      MVMap<Value, Value> map = openMap((String)bufferNames.get(i));
      Iterator<Value> it = map.keyIterator(null);
      if (it.hasNext())
      {
        Comparable s = new Comparable()
        {
          Value value;
          Iterator<Value> next;
          int sourceId;
          
          public int compareTo(1Source o)
          {
            int comp = this.value.compareTo(o.value, compareMode);
            if (comp == 0) {
              comp = this.sourceId - o.sourceId;
            }
            return comp;
          }
        };
        s.value = ((Value)it.next());
        s.next = it;
        s.sourceId = i;
        sources.add(s);
      }
    }
    try
    {
      for (;;)
      {
        Comparable s = (1Source)sources.first();
        Value v = s.value;
        if (this.indexType.isUnique())
        {
          Value[] array = ((ValueArray)v).getList();
          
          array = (Value[])Arrays.copyOf(array, array.length);
          array[(this.keyColumns - 1)] = ValueLong.get(Long.MIN_VALUE);
          ValueArray unique = ValueArray.get(array);
          SearchRow row = convertToSearchRow((ValueArray)v);
          checkUnique(row, this.dataMap, unique);
        }
        this.dataMap.putCommitted(v, ValueNull.INSTANCE);
        
        Iterator<Value> it = s.next;
        if (!it.hasNext())
        {
          sources.remove(s);
          if (sources.size() == 0) {
            break;
          }
        }
        else
        {
          Value nextValue = (Value)it.next();
          sources.remove(s);
          s.value = nextValue;
          sources.add(s);
        }
      }
    }
    finally
    {
      Iterator i$;
      String tempMapName;
      MVMap<Value, Value> map;
      for (String tempMapName : mapNames)
      {
        MVMap<Value, Value> map = openMap(tempMapName);
        map.getStore().removeMap(map);
      }
    }
  }
  
  private MVMap<Value, Value> openMap(String mapName)
  {
    int[] sortTypes = new int[this.keyColumns];
    for (int i = 0; i < this.indexColumns.length; i++) {
      sortTypes[i] = this.indexColumns[i].sortType;
    }
    sortTypes[(this.keyColumns - 1)] = 0;
    ValueDataType keyType = new ValueDataType(this.database.getCompareMode(), this.database, sortTypes);
    
    ValueDataType valueType = new ValueDataType(null, null, null);
    MVMap.Builder<Value, Value> builder = new MVMap.Builder().keyType(keyType).valueType(valueType);
    
    MVMap<Value, Value> map = this.database.getMvStore().getStore().openMap(mapName, builder);
    if (!keyType.equals(map.getKeyType())) {
      throw DbException.throwInternalError("Incompatible key type");
    }
    return map;
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    ValueArray array = convertToKey(row);
    ValueArray unique = null;
    if (this.indexType.isUnique())
    {
      unique = convertToKey(row);
      unique.getList()[(this.keyColumns - 1)] = ValueLong.get(Long.MIN_VALUE);
      checkUnique(row, map, unique);
    }
    try
    {
      map.put(array, ValueNull.INSTANCE);
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90131, e, new String[] { this.table.getName() });
    }
    if (this.indexType.isUnique())
    {
      Iterator<Value> it = map.keyIterator(unique, true);
      while (it.hasNext())
      {
        ValueArray k = (ValueArray)it.next();
        SearchRow r2 = convertToSearchRow(k);
        if (compareRows(row, r2) == 0) {
          if ((!containsNullAndAllowMultipleNull(r2)) && 
          
            (!map.isSameTransaction(k)))
          {
            if (map.get(k) != null) {
              throw getDuplicateKeyException(k.toString());
            }
            throw DbException.get(90131, this.table.getName());
          }
        }
      }
    }
  }
  
  private void checkUnique(SearchRow row, TransactionStore.TransactionMap<Value, Value> map, ValueArray unique)
  {
    Iterator<Value> it = map.keyIterator(unique, true);
    while (it.hasNext())
    {
      ValueArray k = (ValueArray)it.next();
      SearchRow r2 = convertToSearchRow(k);
      if (compareRows(row, r2) != 0) {
        break;
      }
      if ((map.get(k) != null) && 
        (!containsNullAndAllowMultipleNull(r2))) {
        throw getDuplicateKeyException(k.toString());
      }
    }
  }
  
  public void remove(Session session, Row row)
  {
    ValueArray array = convertToKey(row);
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    try
    {
      Value old = (Value)map.remove(array);
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
    return find(session, first, false, last);
  }
  
  private Cursor find(Session session, SearchRow first, boolean bigger, SearchRow last)
  {
    ValueArray min = convertToKey(first);
    if (min != null) {
      min.getList()[(this.keyColumns - 1)] = ValueLong.get(Long.MIN_VALUE);
    }
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    if ((bigger) && (min != null))
    {
      int offset = 1;
      ValueArray v;
      do
      {
        for (;;)
        {
          v = (ValueArray)map.relativeKey(min, offset);
          if (v != null)
          {
            boolean foundHigher = false;
            for (int i = 0; i < this.keyColumns - 1; i++)
            {
              int idx = this.columnIds[i];
              Value b = first.getValue(idx);
              if (b == null) {
                break;
              }
              Value a = v.getList()[i];
              if (this.database.compare(a, b) > 0)
              {
                foundHigher = true;
                break;
              }
            }
            if (!foundHigher)
            {
              offset += offset;
              min = v;
              continue;
            }
          }
          if (offset <= 1) {
            break;
          }
          offset /= 2;
        }
        if (map.get(v) != null) {
          break;
        }
        min = (ValueArray)map.higherKey(min);
      } while (min != null);
      break label220;
      min = v;
      label220:
      if (min == null) {
        return new MVStoreCursor(session, Collections.emptyList().iterator(), null);
      }
    }
    return new MVStoreCursor(session, map.keyIterator(min), last);
  }
  
  private ValueArray convertToKey(SearchRow r)
  {
    if (r == null) {
      return null;
    }
    Value[] array = new Value[this.keyColumns];
    for (int i = 0; i < this.columns.length; i++)
    {
      Column c = this.columns[i];
      int idx = c.getColumnId();
      Value v = r.getValue(idx);
      if (v != null) {
        array[i] = v.convertTo(c.getType());
      }
    }
    array[(this.keyColumns - 1)] = ValueLong.get(r.getKey());
    return ValueArray.get(array);
  }
  
  SearchRow convertToSearchRow(ValueArray key)
  {
    Value[] array = key.getList();
    SearchRow searchRow = this.mvTable.getTemplateRow();
    searchRow.setKey(array[(array.length - 1)].getLong());
    Column[] cols = getColumns();
    for (int i = 0; i < array.length - 1; i++)
    {
      Column c = cols[i];
      int idx = c.getColumnId();
      Value v = array[i];
      searchRow.setValue(idx, v);
    }
    return searchRow;
  }
  
  public MVTable getTable()
  {
    return this.mvTable;
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    try
    {
      return 10L * getCostRangeIndex(masks, this.dataMap.sizeAsLongMax(), filter, sortOrder);
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90007, e, new String[0]);
    }
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
    map.clear();
  }
  
  public boolean canGetFirstOrLast()
  {
    return true;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
    Value key = first ? (Value)map.firstKey() : (Value)map.lastKey();
    for (;;)
    {
      if (key == null) {
        return new MVStoreCursor(session, Collections.emptyList().iterator(), null);
      }
      if (((ValueArray)key).getList()[0] != ValueNull.INSTANCE) {
        break;
      }
      key = first ? (Value)map.higherKey(key) : (Value)map.lowerKey(key);
    }
    ArrayList<Value> list = New.arrayList();
    list.add(key);
    MVStoreCursor cursor = new MVStoreCursor(session, list.iterator(), null);
    cursor.next();
    return cursor;
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
    TransactionStore.TransactionMap<Value, Value> map = getMap(session);
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
  
  public boolean canFindNext()
  {
    return true;
  }
  
  public Cursor findNext(Session session, SearchRow higherThan, SearchRow last)
  {
    return find(session, higherThan, true, last);
  }
  
  public void checkRename() {}
  
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
    private final Session session;
    private final Iterator<Value> it;
    private final SearchRow last;
    private Value current;
    private SearchRow searchRow;
    private Row row;
    
    public MVStoreCursor(Iterator<Value> session, SearchRow it)
    {
      this.session = session;
      this.it = it;
      this.last = last;
    }
    
    public Row get()
    {
      if (this.row == null)
      {
        SearchRow r = getSearchRow();
        if (r != null) {
          this.row = MVSecondaryIndex.this.mvTable.getRow(this.session, r.getKey());
        }
      }
      return this.row;
    }
    
    public SearchRow getSearchRow()
    {
      if ((this.searchRow == null) && 
        (this.current != null)) {
        this.searchRow = MVSecondaryIndex.this.convertToSearchRow((ValueArray)this.current);
      }
      return this.searchRow;
    }
    
    public boolean next()
    {
      this.current = (this.it.hasNext() ? (Value)this.it.next() : null);
      this.searchRow = null;
      if ((this.current != null) && 
        (this.last != null) && (MVSecondaryIndex.this.compareRows(getSearchRow(), this.last) > 0))
      {
        this.searchRow = null;
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
