package org.h2.mvstore.db;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.h2.mvstore.Cursor;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.DataUtils.MapEntry;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVMap.Builder;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.WriteBuffer;
import org.h2.mvstore.type.DataType;
import org.h2.mvstore.type.ObjectDataType;
import org.h2.util.New;

public class TransactionStore
{
  final MVStore store;
  final MVMap<Integer, Object[]> preparedTransactions;
  final MVMap<Long, Object[]> undoLog;
  private HashMap<Integer, MVMap<Object, VersionedValue>> maps = New.hashMap();
  private final DataType dataType;
  private final BitSet openTransactions = new BitSet();
  private boolean init;
  private int maxTransactionId = 65535;
  private int nextTempMapId;
  
  public TransactionStore(MVStore store)
  {
    this(store, new ObjectDataType());
  }
  
  public TransactionStore(MVStore store, DataType dataType)
  {
    this.store = store;
    this.dataType = dataType;
    this.preparedTransactions = store.openMap("openTransactions", new MVMap.Builder());
    
    VersionedValueType oldValueType = new VersionedValueType(dataType);
    ArrayType undoLogValueType = new ArrayType(new DataType[] { new ObjectDataType(), dataType, oldValueType });
    
    MVMap.Builder<Long, Object[]> builder = new MVMap.Builder().valueType(undoLogValueType);
    
    this.undoLog = store.openMap("undoLog", builder);
    if (this.undoLog.getValueType() != undoLogValueType) {
      throw DataUtils.newIllegalStateException(100, "Undo map open with a different value type", new Object[0]);
    }
  }
  
  public synchronized void init()
  {
    this.init = true;
    for (String mapName : this.store.getMapNames()) {
      if (mapName.startsWith("temp."))
      {
        MVMap<Object, Integer> temp = openTempMap(mapName);
        this.store.removeMap(temp);
      }
    }
    synchronized (this.undoLog)
    {
      if (this.undoLog.size() > 0) {
        for (Long key : this.undoLog.keySet())
        {
          int transactionId = getTransactionId(key.longValue());
          this.openTransactions.set(transactionId);
        }
      }
    }
  }
  
  public void setMaxTransactionId(int max)
  {
    this.maxTransactionId = max;
  }
  
  static long getOperationId(int transactionId, long logId)
  {
    DataUtils.checkArgument((transactionId >= 0) && (transactionId < 16777216), "Transaction id out of range: {0}", new Object[] { Integer.valueOf(transactionId) });
    
    DataUtils.checkArgument((logId >= 0L) && (logId < 1099511627776L), "Transaction log id out of range: {0}", new Object[] { Long.valueOf(logId) });
    
    return transactionId << 40 | logId;
  }
  
  static int getTransactionId(long operationId)
  {
    return (int)(operationId >>> 40);
  }
  
  static long getLogId(long operationId)
  {
    return operationId & 0xFFFFFFFFFF;
  }
  
  public List<Transaction> getOpenTransactions()
  {
    synchronized (this.undoLog)
    {
      ArrayList<Transaction> list = New.arrayList();
      Long key = (Long)this.undoLog.firstKey();
      while (key != null)
      {
        int transactionId = getTransactionId(key.longValue());
        key = (Long)this.undoLog.lowerKey(Long.valueOf(getOperationId(transactionId + 1, 0L)));
        long logId = getLogId(key.longValue()) + 1L;
        Object[] data = (Object[])this.preparedTransactions.get(Integer.valueOf(transactionId));
        String name;
        int status;
        String name;
        if (data == null)
        {
          int status;
          int status;
          if (this.undoLog.containsKey(Long.valueOf(getOperationId(transactionId, 0L)))) {
            status = 1;
          } else {
            status = 3;
          }
          name = null;
        }
        else
        {
          status = ((Integer)data[0]).intValue();
          name = (String)data[1];
        }
        Transaction t = new Transaction(this, transactionId, status, name, logId);
        
        list.add(t);
        key = (Long)this.undoLog.ceilingKey(Long.valueOf(getOperationId(transactionId + 1, 0L)));
      }
      return list;
    }
  }
  
  public synchronized void close()
  {
    this.store.commit();
  }
  
  public synchronized Transaction begin()
  {
    if (!this.init) {
      throw DataUtils.newIllegalStateException(103, "Not initialized", new Object[0]);
    }
    int transactionId = this.openTransactions.nextClearBit(1);
    if (transactionId > this.maxTransactionId) {
      throw DataUtils.newIllegalStateException(102, "There are {0} open transactions", new Object[] { Integer.valueOf(transactionId - 1) });
    }
    this.openTransactions.set(transactionId);
    int status = 1;
    return new Transaction(this, transactionId, status, null, 0L);
  }
  
  synchronized void storeTransaction(Transaction t)
  {
    if ((t.getStatus() == 2) || (t.getName() != null))
    {
      Object[] v = { Integer.valueOf(t.getStatus()), t.getName() };
      this.preparedTransactions.put(Integer.valueOf(t.getId()), v);
    }
  }
  
  void log(Transaction t, long logId, int mapId, Object key, Object oldValue)
  {
    Long undoKey = Long.valueOf(getOperationId(t.getId(), logId));
    Object[] log = { Integer.valueOf(mapId), key, oldValue };
    synchronized (this.undoLog)
    {
      if ((logId == 0L) && 
        (this.undoLog.containsKey(undoKey))) {
        throw DataUtils.newIllegalStateException(102, "An old transaction with the same id is still open: {0}", new Object[] { Integer.valueOf(t.getId()) });
      }
      this.undoLog.put(undoKey, log);
    }
  }
  
  public void logUndo(Transaction t, long logId)
  {
    Long undoKey = Long.valueOf(getOperationId(t.getId(), logId));
    synchronized (this.undoLog)
    {
      Object[] old = (Object[])this.undoLog.remove(undoKey);
      if (old == null) {
        throw DataUtils.newIllegalStateException(103, "Transaction {0} was concurrently rolled back", new Object[] { Integer.valueOf(t.getId()) });
      }
    }
  }
  
  synchronized <K, V> void removeMap(TransactionMap<K, V> map)
  {
    this.maps.remove(Integer.valueOf(map.mapId));
    this.store.removeMap(map.map);
  }
  
  void commit(Transaction t, long maxLogId)
  {
    if (this.store.isClosed()) {
      return;
    }
    synchronized (this.undoLog)
    {
      t.setStatus(3);
      for (long logId = 0L; logId < maxLogId; logId += 1L)
      {
        Long undoKey = Long.valueOf(getOperationId(t.getId(), logId));
        Object[] op = (Object[])this.undoLog.get(undoKey);
        if (op == null)
        {
          undoKey = (Long)this.undoLog.ceilingKey(undoKey);
          if ((undoKey == null) || (getTransactionId(undoKey.longValue()) != t.getId())) {
            break;
          }
          logId = getLogId(undoKey.longValue()) - 1L;
        }
        else
        {
          int mapId = ((Integer)op[0]).intValue();
          MVMap<Object, VersionedValue> map = openMap(mapId);
          if (map != null)
          {
            Object key = op[1];
            VersionedValue value = (VersionedValue)map.get(key);
            if (value != null) {
              if (value.value == null)
              {
                map.remove(key);
              }
              else
              {
                VersionedValue v2 = new VersionedValue();
                v2.value = value.value;
                map.put(key, v2);
              }
            }
          }
          this.undoLog.remove(undoKey);
        }
      }
    }
    endTransaction(t);
  }
  
  synchronized <K> MVMap<K, VersionedValue> openMap(String name, DataType keyType, DataType valueType)
  {
    if (keyType == null) {
      keyType = new ObjectDataType();
    }
    if (valueType == null) {
      valueType = new ObjectDataType();
    }
    VersionedValueType vt = new VersionedValueType(valueType);
    
    MVMap.Builder<K, VersionedValue> builder = new MVMap.Builder().keyType(keyType).valueType(vt);
    
    MVMap<K, VersionedValue> map = this.store.openMap(name, builder);
    
    MVMap<Object, VersionedValue> m = map;
    this.maps.put(Integer.valueOf(map.getId()), m);
    return map;
  }
  
  synchronized MVMap<Object, VersionedValue> openMap(int mapId)
  {
    MVMap<Object, VersionedValue> map = (MVMap)this.maps.get(Integer.valueOf(mapId));
    if (map != null) {
      return map;
    }
    String mapName = this.store.getMapName(mapId);
    if (mapName == null) {
      return null;
    }
    VersionedValueType vt = new VersionedValueType(this.dataType);
    MVMap.Builder<Object, VersionedValue> mapBuilder = new MVMap.Builder().keyType(this.dataType).valueType(vt);
    
    map = this.store.openMap(mapName, mapBuilder);
    this.maps.put(Integer.valueOf(mapId), map);
    return map;
  }
  
  synchronized MVMap<Object, Integer> createTempMap()
  {
    String mapName = "temp." + this.nextTempMapId++;
    return openTempMap(mapName);
  }
  
  MVMap<Object, Integer> openTempMap(String mapName)
  {
    MVMap.Builder<Object, Integer> mapBuilder = new MVMap.Builder().keyType(this.dataType);
    
    return this.store.openMap(mapName, mapBuilder);
  }
  
  synchronized void endTransaction(Transaction t)
  {
    if (t.getStatus() == 2) {
      this.preparedTransactions.remove(Integer.valueOf(t.getId()));
    }
    t.setStatus(0);
    this.openTransactions.clear(t.transactionId);
    if (this.store.getAutoCommitDelay() == 0)
    {
      this.store.commit();
      return;
    }
    if (this.undoLog.isEmpty())
    {
      int unsaved = this.store.getUnsavedMemory();
      int max = this.store.getAutoCommitMemory();
      if (unsaved * 4 > max * 3) {
        this.store.commit();
      }
    }
  }
  
  void rollbackTo(Transaction t, long maxLogId, long toLogId)
  {
    synchronized (this.undoLog)
    {
      for (long logId = maxLogId - 1L; logId >= toLogId; logId -= 1L)
      {
        Long undoKey = Long.valueOf(getOperationId(t.getId(), logId));
        Object[] op = (Object[])this.undoLog.get(undoKey);
        if (op == null)
        {
          undoKey = (Long)this.undoLog.floorKey(undoKey);
          if ((undoKey == null) || (getTransactionId(undoKey.longValue()) != t.getId())) {
            break;
          }
          logId = getLogId(undoKey.longValue()) + 1L;
        }
        else
        {
          int mapId = ((Integer)op[0]).intValue();
          MVMap<Object, VersionedValue> map = openMap(mapId);
          if (map != null)
          {
            Object key = op[1];
            VersionedValue oldValue = (VersionedValue)op[2];
            if (oldValue == null) {
              map.remove(key);
            } else {
              map.put(key, oldValue);
            }
          }
          this.undoLog.remove(undoKey);
        }
      }
    }
  }
  
  Iterator<Change> getChanges(final Transaction t, final long maxLogId, long toLogId)
  {
    new Iterator()
    {
      private long logId;
      private TransactionStore.Change current;
      
      private void fetchNext()
      {
        synchronized (TransactionStore.this.undoLog)
        {
          while (this.logId >= t)
          {
            Long undoKey = Long.valueOf(TransactionStore.getOperationId(this.val$t.getId(), this.logId));
            Object[] op = (Object[])TransactionStore.this.undoLog.get(undoKey);
            this.logId -= 1L;
            if (op == null)
            {
              undoKey = (Long)TransactionStore.this.undoLog.floorKey(undoKey);
              if ((undoKey == null) || (TransactionStore.getTransactionId(undoKey.longValue()) != this.val$t.getId())) {
                break;
              }
              this.logId = TransactionStore.getLogId(undoKey.longValue());
            }
            else
            {
              int mapId = ((Integer)op[0]).intValue();
              MVMap<Object, TransactionStore.VersionedValue> m = TransactionStore.this.openMap(mapId);
              if (m != null)
              {
                this.current = new TransactionStore.Change();
                this.current.mapName = m.getName();
                this.current.key = op[1];
                TransactionStore.VersionedValue oldValue = (TransactionStore.VersionedValue)op[2];
                this.current.value = (oldValue == null ? null : oldValue.value);
                
                return;
              }
            }
          }
        }
        this.current = null;
      }
      
      public boolean hasNext()
      {
        return this.current != null;
      }
      
      public TransactionStore.Change next()
      {
        if (this.current == null) {
          throw DataUtils.newUnsupportedOperationException("no data");
        }
        TransactionStore.Change result = this.current;
        fetchNext();
        return result;
      }
      
      public void remove()
      {
        throw DataUtils.newUnsupportedOperationException("remove");
      }
    };
  }
  
  public static class Change
  {
    public String mapName;
    public Object key;
    public Object value;
  }
  
  public static class Transaction
  {
    public static final int STATUS_CLOSED = 0;
    public static final int STATUS_OPEN = 1;
    public static final int STATUS_PREPARED = 2;
    public static final int STATUS_COMMITTING = 3;
    final TransactionStore store;
    final int transactionId;
    long logId;
    private int status;
    private String name;
    
    Transaction(TransactionStore store, int transactionId, int status, String name, long logId)
    {
      this.store = store;
      this.transactionId = transactionId;
      this.status = status;
      this.name = name;
      this.logId = logId;
    }
    
    public int getId()
    {
      return this.transactionId;
    }
    
    public int getStatus()
    {
      return this.status;
    }
    
    void setStatus(int status)
    {
      this.status = status;
    }
    
    public void setName(String name)
    {
      checkNotClosed();
      this.name = name;
      this.store.storeTransaction(this);
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public long setSavepoint()
    {
      return this.logId;
    }
    
    void log(int mapId, Object key, Object oldValue)
    {
      this.store.log(this, this.logId, mapId, key, oldValue);
      
      this.logId += 1L;
    }
    
    void logUndo()
    {
      this.store.logUndo(this, --this.logId);
    }
    
    public <K, V> TransactionStore.TransactionMap<K, V> openMap(String name)
    {
      return openMap(name, null, null);
    }
    
    public <K, V> TransactionStore.TransactionMap<K, V> openMap(String name, DataType keyType, DataType valueType)
    {
      checkNotClosed();
      MVMap<K, TransactionStore.VersionedValue> map = this.store.openMap(name, keyType, valueType);
      
      int mapId = map.getId();
      return new TransactionStore.TransactionMap(this, map, mapId);
    }
    
    public <K, V> TransactionStore.TransactionMap<K, V> openMap(MVMap<K, TransactionStore.VersionedValue> map)
    {
      checkNotClosed();
      int mapId = map.getId();
      return new TransactionStore.TransactionMap(this, map, mapId);
    }
    
    public void prepare()
    {
      checkNotClosed();
      this.status = 2;
      this.store.storeTransaction(this);
    }
    
    public void commit()
    {
      checkNotClosed();
      this.store.commit(this, this.logId);
    }
    
    public void rollbackToSavepoint(long savepointId)
    {
      checkNotClosed();
      this.store.rollbackTo(this, this.logId, savepointId);
      this.logId = savepointId;
    }
    
    public void rollback()
    {
      checkNotClosed();
      this.store.rollbackTo(this, this.logId, 0L);
      this.store.endTransaction(this);
    }
    
    public Iterator<TransactionStore.Change> getChanges(long savepointId)
    {
      return this.store.getChanges(this, this.logId, savepointId);
    }
    
    void checkNotClosed()
    {
      if (this.status == 0) {
        throw DataUtils.newIllegalStateException(4, "Transaction is closed", new Object[0]);
      }
    }
    
    public <K, V> void removeMap(TransactionStore.TransactionMap<K, V> map)
    {
      this.store.removeMap(map);
    }
    
    public String toString()
    {
      return "" + this.transactionId;
    }
  }
  
  public static class TransactionMap<K, V>
  {
    final int mapId;
    long readLogId = Long.MAX_VALUE;
    final MVMap<K, TransactionStore.VersionedValue> map;
    private TransactionStore.Transaction transaction;
    
    TransactionMap(TransactionStore.Transaction transaction, MVMap<K, TransactionStore.VersionedValue> map, int mapId)
    {
      this.transaction = transaction;
      this.map = map;
      this.mapId = mapId;
    }
    
    public void setSavepoint(long savepoint)
    {
      this.readLogId = savepoint;
    }
    
    public TransactionMap<K, V> getInstance(TransactionStore.Transaction transaction, long savepoint)
    {
      TransactionMap<K, V> m = new TransactionMap(transaction, this.map, this.mapId);
      
      m.setSavepoint(savepoint);
      return m;
    }
    
    public long sizeAsLongMax()
    {
      return this.map.sizeAsLong();
    }
    
    public long sizeAsLong()
    {
      long sizeRaw = this.map.sizeAsLong();
      MVMap<Long, Object[]> undo = this.transaction.store.undoLog;
      long undoLogSize;
      synchronized (undo)
      {
        undoLogSize = undo.sizeAsLong();
      }
      if (undoLogSize == 0L) {
        return sizeRaw;
      }
      if (undoLogSize > sizeRaw)
      {
        long size = 0L;
        Cursor<K, TransactionStore.VersionedValue> cursor = this.map.cursor(null);
        while (cursor.hasNext())
        {
          K key = cursor.next();
          TransactionStore.VersionedValue data = (TransactionStore.VersionedValue)cursor.getValue();
          data = getValue(key, this.readLogId, data);
          if ((data != null) && (data.value != null)) {
            size += 1L;
          }
        }
        return size;
      }
      synchronized (undo)
      {
        long size = this.map.sizeAsLong();
        MVMap<Object, Integer> temp = this.transaction.store.createTempMap();
        try
        {
          for (Map.Entry<Long, Object[]> e : undo.entrySet())
          {
            Object[] op = (Object[])e.getValue();
            int m = ((Integer)op[0]).intValue();
            if (m == this.mapId)
            {
              K key = op[1];
              if (get(key) == null)
              {
                Integer old = (Integer)temp.put(key, Integer.valueOf(1));
                if (old == null) {
                  size -= 1L;
                }
              }
            }
          }
        }
        finally
        {
          this.transaction.store.store.removeMap(temp);
        }
        return size;
      }
    }
    
    public V remove(K key)
    {
      return (V)set(key, null);
    }
    
    public V put(K key, V value)
    {
      DataUtils.checkArgument(value != null, "The value may not be null", new Object[0]);
      return (V)set(key, value);
    }
    
    public V putCommitted(K key, V value)
    {
      DataUtils.checkArgument(value != null, "The value may not be null", new Object[0]);
      TransactionStore.VersionedValue newValue = new TransactionStore.VersionedValue();
      newValue.value = value;
      TransactionStore.VersionedValue oldValue = (TransactionStore.VersionedValue)this.map.put(key, newValue);
      return oldValue == null ? null : oldValue.value;
    }
    
    private V set(K key, V value)
    {
      this.transaction.checkNotClosed();
      V old = get(key);
      boolean ok = trySet(key, value, false);
      if (ok) {
        return old;
      }
      throw DataUtils.newIllegalStateException(101, "Entry is locked", new Object[0]);
    }
    
    public boolean tryRemove(K key)
    {
      return trySet(key, null, false);
    }
    
    public boolean tryPut(K key, V value)
    {
      DataUtils.checkArgument(value != null, "The value may not be null", new Object[0]);
      return trySet(key, value, false);
    }
    
    public boolean trySet(K key, V value, boolean onlyIfUnchanged)
    {
      TransactionStore.VersionedValue current = (TransactionStore.VersionedValue)this.map.get(key);
      if (onlyIfUnchanged)
      {
        TransactionStore.VersionedValue old = getValue(key, this.readLogId);
        if (!this.map.areValuesEqual(old, current))
        {
          long tx = TransactionStore.getTransactionId(current.operationId);
          if (tx == this.transaction.transactionId)
          {
            if (value == null) {
              return true;
            }
            if (current.value != null) {
              return false;
            }
          }
          else
          {
            return false;
          }
        }
      }
      TransactionStore.VersionedValue newValue = new TransactionStore.VersionedValue();
      newValue.operationId = TransactionStore.getOperationId(this.transaction.transactionId, this.transaction.logId);
      
      newValue.value = value;
      if (current == null)
      {
        this.transaction.log(this.mapId, key, current);
        TransactionStore.VersionedValue old = (TransactionStore.VersionedValue)this.map.putIfAbsent(key, newValue);
        if (old != null)
        {
          this.transaction.logUndo();
          return false;
        }
        return true;
      }
      long id = current.operationId;
      if (id == 0L)
      {
        this.transaction.log(this.mapId, key, current);
        if (!this.map.replace(key, current, newValue))
        {
          this.transaction.logUndo();
          return false;
        }
        return true;
      }
      int tx = TransactionStore.getTransactionId(current.operationId);
      if (tx == this.transaction.transactionId)
      {
        this.transaction.log(this.mapId, key, current);
        if (!this.map.replace(key, current, newValue))
        {
          this.transaction.logUndo();
          return false;
        }
        return true;
      }
      return false;
    }
    
    public V get(K key)
    {
      return (V)get(key, this.readLogId);
    }
    
    public V getLatest(K key)
    {
      return (V)get(key, Long.MAX_VALUE);
    }
    
    public boolean containsKey(K key)
    {
      return get(key) != null;
    }
    
    public V get(K key, long maxLogId)
    {
      TransactionStore.VersionedValue data = getValue(key, maxLogId);
      return data == null ? null : data.value;
    }
    
    public boolean isSameTransaction(K key)
    {
      TransactionStore.VersionedValue data = (TransactionStore.VersionedValue)this.map.get(key);
      if (data == null) {
        return false;
      }
      int tx = TransactionStore.getTransactionId(data.operationId);
      return tx == this.transaction.transactionId;
    }
    
    private TransactionStore.VersionedValue getValue(K key, long maxLog)
    {
      TransactionStore.VersionedValue data = (TransactionStore.VersionedValue)this.map.get(key);
      return getValue(key, maxLog, data);
    }
    
    TransactionStore.VersionedValue getValue(K key, long maxLog, TransactionStore.VersionedValue data)
    {
      for (;;)
      {
        if (data == null) {
          return null;
        }
        long id = data.operationId;
        if (id == 0L) {
          return data;
        }
        int tx = TransactionStore.getTransactionId(id);
        if (tx == this.transaction.transactionId) {
          if (TransactionStore.getLogId(id) < maxLog) {
            return data;
          }
        }
        Object[] d;
        synchronized (this.transaction.store.undoLog)
        {
          d = (Object[])this.transaction.store.undoLog.get(Long.valueOf(id));
        }
        if (d == null) {
          data = (TransactionStore.VersionedValue)this.map.get(key);
        } else {
          data = (TransactionStore.VersionedValue)d[2];
        }
      }
    }
    
    public boolean isClosed()
    {
      return this.map.isClosed();
    }
    
    public void clear()
    {
      this.map.clear();
    }
    
    public K firstKey()
    {
      Iterator<K> it = keyIterator(null);
      return (K)(it.hasNext() ? it.next() : null);
    }
    
    public K lastKey()
    {
      K k = this.map.lastKey();
      for (;;)
      {
        if (k == null) {
          return null;
        }
        if (get(k) != null) {
          return k;
        }
        k = this.map.lowerKey(k);
      }
    }
    
    public K higherKey(K key)
    {
      for (;;)
      {
        K k = this.map.higherKey(key);
        if ((k == null) || (get(k) != null)) {
          return k;
        }
        key = k;
      }
    }
    
    public K relativeKey(K key, long offset)
    {
      K k = offset > 0L ? this.map.ceilingKey(key) : this.map.floorKey(key);
      if (k == null) {
        return k;
      }
      long index = this.map.getKeyIndex(k);
      return (K)this.map.getKey(index + offset);
    }
    
    public K lowerKey(K key)
    {
      for (;;)
      {
        K k = this.map.lowerKey(key);
        if ((k == null) || (get(k) != null)) {
          return k;
        }
        key = k;
      }
    }
    
    public Iterator<K> keyIterator(K from)
    {
      return keyIterator(from, false);
    }
    
    public Iterator<K> keyIterator(final K from, final boolean includeUncommitted)
    {
      new Iterator()
      {
        private K currentKey;
        private Cursor<K, TransactionStore.VersionedValue> cursor;
        
        private void fetchNext()
        {
          while (this.cursor.hasNext())
          {
            try
            {
              k = this.cursor.next();
            }
            catch (IllegalStateException e)
            {
              K k;
              if (DataUtils.getErrorCode(e.getMessage()) == 9)
              {
                this.cursor = TransactionStore.TransactionMap.this.map.cursor(this.currentKey);
                if (!this.cursor.hasNext()) {
                  break;
                }
                this.cursor.next();
                if (!this.cursor.hasNext()) {
                  break;
                }
                k = this.cursor.next();
              }
              else
              {
                throw e;
              }
            }
            K k;
            this.currentKey = k;
            if (includeUncommitted) {
              return;
            }
            if (TransactionStore.TransactionMap.this.containsKey(k)) {
              return;
            }
          }
          this.currentKey = null;
        }
        
        public boolean hasNext()
        {
          return this.currentKey != null;
        }
        
        public K next()
        {
          K result = this.currentKey;
          fetchNext();
          return result;
        }
        
        public void remove()
        {
          throw DataUtils.newUnsupportedOperationException("Removing is not supported");
        }
      };
    }
    
    public Iterator<Map.Entry<K, V>> entryIterator(final K from)
    {
      new Iterator()
      {
        private Map.Entry<K, V> current;
        private K currentKey;
        private Cursor<K, TransactionStore.VersionedValue> cursor;
        
        private void fetchNext()
        {
          while (this.cursor.hasNext())
          {
            try
            {
              k = this.cursor.next();
            }
            catch (IllegalStateException e)
            {
              K k;
              if (DataUtils.getErrorCode(e.getMessage()) == 9)
              {
                this.cursor = TransactionStore.TransactionMap.this.map.cursor(this.currentKey);
                if (!this.cursor.hasNext()) {
                  break;
                }
                this.cursor.next();
                if (!this.cursor.hasNext()) {
                  break;
                }
                k = this.cursor.next();
              }
              else
              {
                throw e;
              }
            }
            K k;
            K key = k;
            TransactionStore.VersionedValue data = (TransactionStore.VersionedValue)this.cursor.getValue();
            data = TransactionStore.TransactionMap.this.getValue(key, TransactionStore.TransactionMap.this.readLogId, data);
            if ((data != null) && (data.value != null))
            {
              V value = data.value;
              this.current = new DataUtils.MapEntry(key, value);
              this.currentKey = key;
              return;
            }
          }
          this.current = null;
          this.currentKey = null;
        }
        
        public boolean hasNext()
        {
          return this.current != null;
        }
        
        public Map.Entry<K, V> next()
        {
          Map.Entry<K, V> result = this.current;
          fetchNext();
          return result;
        }
        
        public void remove()
        {
          throw DataUtils.newUnsupportedOperationException("Removing is not supported");
        }
      };
    }
    
    public Iterator<K> wrapIterator(final Iterator<K> iterator, final boolean includeUncommitted)
    {
      new Iterator()
      {
        private K current;
        
        private void fetchNext()
        {
          while (iterator.hasNext())
          {
            this.current = iterator.next();
            if (includeUncommitted) {
              return;
            }
            if (TransactionStore.TransactionMap.this.containsKey(this.current)) {
              return;
            }
          }
          this.current = null;
        }
        
        public boolean hasNext()
        {
          return this.current != null;
        }
        
        public K next()
        {
          K result = this.current;
          fetchNext();
          return result;
        }
        
        public void remove()
        {
          throw DataUtils.newUnsupportedOperationException("Removing is not supported");
        }
      };
    }
    
    public TransactionStore.Transaction getTransaction()
    {
      return this.transaction;
    }
    
    public DataType getKeyType()
    {
      return this.map.getKeyType();
    }
  }
  
  static class VersionedValue
  {
    public long operationId;
    public Object value;
    
    public String toString()
    {
      return this.value + (this.operationId == 0L ? "" : new StringBuilder().append(" ").append(TransactionStore.getTransactionId(this.operationId)).append("/").append(TransactionStore.getLogId(this.operationId)).toString());
    }
  }
  
  public static class VersionedValueType
    implements DataType
  {
    private final DataType valueType;
    
    VersionedValueType(DataType valueType)
    {
      this.valueType = valueType;
    }
    
    public int getMemory(Object obj)
    {
      TransactionStore.VersionedValue v = (TransactionStore.VersionedValue)obj;
      return this.valueType.getMemory(v.value) + 8;
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (aObj == bObj) {
        return 0;
      }
      TransactionStore.VersionedValue a = (TransactionStore.VersionedValue)aObj;
      TransactionStore.VersionedValue b = (TransactionStore.VersionedValue)bObj;
      long comp = a.operationId - b.operationId;
      if (comp == 0L) {
        return this.valueType.compare(a.value, b.value);
      }
      return Long.signum(comp);
    }
    
    public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
    {
      if (buff.get() == 0) {
        for (int i = 0; i < len; i++)
        {
          TransactionStore.VersionedValue v = new TransactionStore.VersionedValue();
          v.value = this.valueType.read(buff);
          obj[i] = v;
        }
      } else {
        for (int i = 0; i < len; i++) {
          obj[i] = read(buff);
        }
      }
    }
    
    public Object read(ByteBuffer buff)
    {
      TransactionStore.VersionedValue v = new TransactionStore.VersionedValue();
      v.operationId = DataUtils.readVarLong(buff);
      if (buff.get() == 1) {
        v.value = this.valueType.read(buff);
      }
      return v;
    }
    
    public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
    {
      boolean fastPath = true;
      for (int i = 0; i < len; i++)
      {
        TransactionStore.VersionedValue v = (TransactionStore.VersionedValue)obj[i];
        if ((v.operationId != 0L) || (v.value == null)) {
          fastPath = false;
        }
      }
      if (fastPath)
      {
        buff.put((byte)0);
        for (int i = 0; i < len; i++)
        {
          TransactionStore.VersionedValue v = (TransactionStore.VersionedValue)obj[i];
          this.valueType.write(buff, v.value);
        }
      }
      else
      {
        buff.put((byte)1);
        for (int i = 0; i < len; i++) {
          write(buff, obj[i]);
        }
      }
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      TransactionStore.VersionedValue v = (TransactionStore.VersionedValue)obj;
      buff.putVarLong(v.operationId);
      if (v.value == null)
      {
        buff.put((byte)0);
      }
      else
      {
        buff.put((byte)1);
        this.valueType.write(buff, v.value);
      }
    }
  }
  
  public static class ArrayType
    implements DataType
  {
    private final int arrayLength;
    private final DataType[] elementTypes;
    
    ArrayType(DataType[] elementTypes)
    {
      this.arrayLength = elementTypes.length;
      this.elementTypes = elementTypes;
    }
    
    public int getMemory(Object obj)
    {
      Object[] array = (Object[])obj;
      int size = 0;
      for (int i = 0; i < this.arrayLength; i++)
      {
        DataType t = this.elementTypes[i];
        Object o = array[i];
        if (o != null) {
          size += t.getMemory(o);
        }
      }
      return size;
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (aObj == bObj) {
        return 0;
      }
      Object[] a = (Object[])aObj;
      Object[] b = (Object[])bObj;
      for (int i = 0; i < this.arrayLength; i++)
      {
        DataType t = this.elementTypes[i];
        int comp = t.compare(a[i], b[i]);
        if (comp != 0) {
          return comp;
        }
      }
      return 0;
    }
    
    public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
    {
      for (int i = 0; i < len; i++) {
        obj[i] = read(buff);
      }
    }
    
    public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
    {
      for (int i = 0; i < len; i++) {
        write(buff, obj[i]);
      }
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      Object[] array = (Object[])obj;
      for (int i = 0; i < this.arrayLength; i++)
      {
        DataType t = this.elementTypes[i];
        Object o = array[i];
        if (o == null)
        {
          buff.put((byte)0);
        }
        else
        {
          buff.put((byte)1);
          t.write(buff, o);
        }
      }
    }
    
    public Object read(ByteBuffer buff)
    {
      Object[] array = new Object[this.arrayLength];
      for (int i = 0; i < this.arrayLength; i++)
      {
        DataType t = this.elementTypes[i];
        if (buff.get() == 1) {
          array[i] = t.read(buff);
        }
      }
      return array;
    }
  }
}
