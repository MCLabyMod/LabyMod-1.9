package org.h2.mvstore;

import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import org.h2.mvstore.type.DataType;
import org.h2.mvstore.type.ObjectDataType;
import org.h2.util.New;

public class MVMap<K, V>
  extends AbstractMap<K, V>
  implements ConcurrentMap<K, V>
{
  protected MVStore store;
  protected volatile Page root;
  protected volatile long writeVersion;
  private int id;
  private long createVersion;
  private final DataType keyType;
  private final DataType valueType;
  private ConcurrentArrayList<Page> oldRoots = new ConcurrentArrayList();
  private boolean closed;
  private boolean readOnly;
  private boolean isVolatile;
  
  protected MVMap(DataType keyType, DataType valueType)
  {
    this.keyType = keyType;
    this.valueType = valueType;
    this.root = Page.createEmpty(this, -1L);
  }
  
  static String getMapRootKey(int mapId)
  {
    return "root." + Integer.toHexString(mapId);
  }
  
  static String getMapKey(int mapId)
  {
    return "map." + Integer.toHexString(mapId);
  }
  
  protected void init(MVStore store, HashMap<String, Object> config)
  {
    this.store = store;
    this.id = DataUtils.readHexInt(config, "id", 0);
    this.createVersion = DataUtils.readHexLong(config, "createVersion", 0L);
    this.writeVersion = store.getCurrentVersion();
  }
  
  public synchronized V put(K key, V value)
  {
    DataUtils.checkArgument(value != null, "The value may not be null", new Object[0]);
    beforeWrite();
    long v = this.writeVersion;
    Page p = this.root.copy(v);
    p = splitRootIfNeeded(p, v);
    Object result = put(p, v, key, value);
    newRoot(p);
    return (V)result;
  }
  
  synchronized Page putBranch(Page root, K key, V value)
  {
    DataUtils.checkArgument(value != null, "The value may not be null", new Object[0]);
    long v = this.writeVersion;
    Page p = root.copy(v);
    p = splitRootIfNeeded(p, v);
    put(p, v, key, value);
    return p;
  }
  
  protected Page splitRootIfNeeded(Page p, long writeVersion)
  {
    if ((p.getMemory() <= this.store.getPageSplitSize()) || (p.getKeyCount() <= 1)) {
      return p;
    }
    int at = p.getKeyCount() / 2;
    long totalCount = p.getTotalCount();
    Object k = p.getKey(at);
    Page split = p.split(at);
    Object[] keys = { k };
    Page.PageReference[] children = { new Page.PageReference(p, p.getPos(), p.getTotalCount()), new Page.PageReference(split, split.getPos(), split.getTotalCount()) };
    
    p = Page.create(this, writeVersion, keys, null, children, totalCount, 0);
    
    return p;
  }
  
  protected Object put(Page p, long writeVersion, Object key, Object value)
  {
    int index = p.binarySearch(key);
    if (p.isLeaf())
    {
      if (index < 0)
      {
        index = -index - 1;
        p.insertLeaf(index, key, value);
        return null;
      }
      return p.setValue(index, value);
    }
    if (index < 0) {
      index = -index - 1;
    } else {
      index++;
    }
    Page c = p.getChildPage(index).copy(writeVersion);
    if ((c.getMemory() > this.store.getPageSplitSize()) && (c.getKeyCount() > 1))
    {
      int at = c.getKeyCount() / 2;
      Object k = c.getKey(at);
      Page split = c.split(at);
      p.setChild(index, split);
      p.insertNode(index, k, c);
      
      return put(p, writeVersion, key, value);
    }
    Object result = put(c, writeVersion, key, value);
    p.setChild(index, c);
    return result;
  }
  
  public K firstKey()
  {
    return (K)getFirstLast(true);
  }
  
  public K lastKey()
  {
    return (K)getFirstLast(false);
  }
  
  public K getKey(long index)
  {
    if ((index < 0L) || (index >= size())) {
      return null;
    }
    Page p = this.root;
    long offset = 0L;
    for (;;)
    {
      if (p.isLeaf())
      {
        if (index >= offset + p.getKeyCount()) {
          return null;
        }
        return (K)p.getKey((int)(index - offset));
      }
      int i = 0;int size = getChildPageCount(p);
      for (; i < size; i++)
      {
        long c = p.getCounts(i);
        if (index < c + offset) {
          break;
        }
        offset += c;
      }
      if (i == size) {
        return null;
      }
      p = p.getChildPage(i);
    }
  }
  
  public List<K> keyList()
  {
    new AbstractList()
    {
      public K get(int index)
      {
        return (K)MVMap.this.getKey(index);
      }
      
      public int size()
      {
        return MVMap.this.size();
      }
      
      public int indexOf(Object key)
      {
        return (int)MVMap.this.getKeyIndex(key);
      }
    };
  }
  
  public long getKeyIndex(K key)
  {
    if (size() == 0) {
      return -1L;
    }
    Page p = this.root;
    long offset = 0L;
    for (;;)
    {
      int x = p.binarySearch(key);
      if (p.isLeaf())
      {
        if (x < 0) {
          return -offset + x;
        }
        return offset + x;
      }
      if (x < 0) {
        x = -x - 1;
      } else {
        x++;
      }
      for (int i = 0; i < x; i++) {
        offset += p.getCounts(i);
      }
      p = p.getChildPage(x);
    }
  }
  
  protected K getFirstLast(boolean first)
  {
    if (size() == 0) {
      return null;
    }
    Page p = this.root;
    for (;;)
    {
      if (p.isLeaf()) {
        return (K)p.getKey(first ? 0 : p.getKeyCount() - 1);
      }
      p = p.getChildPage(first ? 0 : getChildPageCount(p) - 1);
    }
  }
  
  public K higherKey(K key)
  {
    return (K)getMinMax(key, false, true);
  }
  
  public K ceilingKey(K key)
  {
    return (K)getMinMax(key, false, false);
  }
  
  public K floorKey(K key)
  {
    return (K)getMinMax(key, true, false);
  }
  
  public K lowerKey(K key)
  {
    return (K)getMinMax(key, true, true);
  }
  
  protected K getMinMax(K key, boolean min, boolean excluding)
  {
    return (K)getMinMax(this.root, key, min, excluding);
  }
  
  private K getMinMax(Page p, K key, boolean min, boolean excluding)
  {
    if (p.isLeaf())
    {
      int x = p.binarySearch(key);
      if (x < 0) {
        x = -x - (min ? 2 : 1);
      } else if (excluding) {
        x += (min ? -1 : 1);
      }
      if ((x < 0) || (x >= p.getKeyCount())) {
        return null;
      }
      return (K)p.getKey(x);
    }
    int x = p.binarySearch(key);
    if (x < 0) {
      x = -x - 1;
    } else {
      x++;
    }
    for (;;)
    {
      if ((x < 0) || (x >= getChildPageCount(p))) {
        return null;
      }
      K k = getMinMax(p.getChildPage(x), key, min, excluding);
      if (k != null) {
        return k;
      }
      x += (min ? -1 : 1);
    }
  }
  
  public V get(Object key)
  {
    return (V)binarySearch(this.root, key);
  }
  
  protected Object binarySearch(Page p, Object key)
  {
    int x = p.binarySearch(key);
    if (!p.isLeaf())
    {
      if (x < 0) {
        x = -x - 1;
      } else {
        x++;
      }
      p = p.getChildPage(x);
      return binarySearch(p, key);
    }
    if (x >= 0) {
      return p.getValue(x);
    }
    return null;
  }
  
  public boolean containsKey(Object key)
  {
    return get(key) != null;
  }
  
  protected Page binarySearchPage(Page p, Object key)
  {
    int x = p.binarySearch(key);
    if (!p.isLeaf())
    {
      if (x < 0) {
        x = -x - 1;
      } else {
        x++;
      }
      p = p.getChildPage(x);
      return binarySearchPage(p, key);
    }
    if (x >= 0) {
      return p;
    }
    return null;
  }
  
  public synchronized void clear()
  {
    beforeWrite();
    this.root.removeAllRecursive();
    newRoot(Page.createEmpty(this, this.writeVersion));
  }
  
  void close()
  {
    this.closed = true;
  }
  
  public boolean isClosed()
  {
    return this.closed;
  }
  
  public V remove(Object key)
  {
    beforeWrite();
    V result = get(key);
    if (result == null) {
      return null;
    }
    long v = this.writeVersion;
    synchronized (this)
    {
      Page p = this.root.copy(v);
      result = remove(p, v, key);
      if ((!p.isLeaf()) && (p.getTotalCount() == 0L))
      {
        p.removePage();
        p = Page.createEmpty(this, p.getVersion());
      }
      newRoot(p);
    }
    return result;
  }
  
  public synchronized V putIfAbsent(K key, V value)
  {
    V old = get(key);
    if (old == null) {
      put(key, value);
    }
    return old;
  }
  
  public synchronized boolean remove(Object key, Object value)
  {
    V old = get(key);
    if (areValuesEqual(old, value))
    {
      remove(key);
      return true;
    }
    return false;
  }
  
  public boolean areValuesEqual(Object a, Object b)
  {
    if (a == b) {
      return true;
    }
    if ((a == null) || (b == null)) {
      return false;
    }
    return this.valueType.compare(a, b) == 0;
  }
  
  public synchronized boolean replace(K key, V oldValue, V newValue)
  {
    V old = get(key);
    if (areValuesEqual(old, oldValue))
    {
      put(key, newValue);
      return true;
    }
    return false;
  }
  
  public synchronized V replace(K key, V value)
  {
    V old = get(key);
    if (old != null)
    {
      put(key, value);
      return old;
    }
    return null;
  }
  
  protected Object remove(Page p, long writeVersion, Object key)
  {
    int index = p.binarySearch(key);
    Object result = null;
    if (p.isLeaf())
    {
      if (index >= 0)
      {
        result = p.getValue(index);
        p.remove(index);
      }
      return result;
    }
    if (index < 0) {
      index = -index - 1;
    } else {
      index++;
    }
    Page cOld = p.getChildPage(index);
    Page c = cOld.copy(writeVersion);
    result = remove(c, writeVersion, key);
    if ((result == null) || (c.getTotalCount() != 0L))
    {
      p.setChild(index, c);
    }
    else if (p.getKeyCount() == 0)
    {
      p.setChild(index, c);
      c.removePage();
    }
    else
    {
      p.remove(index);
    }
    return result;
  }
  
  protected void newRoot(Page newRoot)
  {
    if (this.root != newRoot)
    {
      removeUnusedOldVersions();
      if (this.root.getVersion() != newRoot.getVersion())
      {
        Page last = (Page)this.oldRoots.peekLast();
        if ((last == null) || (last.getVersion() != this.root.getVersion())) {
          this.oldRoots.add(this.root);
        }
      }
      this.root = newRoot;
    }
  }
  
  int compare(Object a, Object b)
  {
    return this.keyType.compare(a, b);
  }
  
  public DataType getKeyType()
  {
    return this.keyType;
  }
  
  public DataType getValueType()
  {
    return this.valueType;
  }
  
  Page readPage(long pos)
  {
    return this.store.readPage(this, pos);
  }
  
  void setRootPos(long rootPos, long version)
  {
    this.root = (rootPos == 0L ? Page.createEmpty(this, -1L) : readPage(rootPos));
    this.root.setVersion(version);
  }
  
  public Iterator<K> keyIterator(K from)
  {
    return new Cursor(this, this.root, from);
  }
  
  boolean rewrite(Set<Integer> set)
  {
    long previousVersion = this.store.getCurrentVersion() - 1L;
    if (previousVersion < this.createVersion) {
      return true;
    }
    MVMap<K, V> readMap;
    try
    {
      readMap = openVersion(previousVersion);
    }
    catch (IllegalArgumentException e)
    {
      return true;
    }
    try
    {
      rewrite(readMap.root, set);
      return true;
    }
    catch (IllegalStateException e)
    {
      if (DataUtils.getErrorCode(e.getMessage()) == 9) {
        return false;
      }
      throw e;
    }
  }
  
  private int rewrite(Page p, Set<Integer> set)
  {
    if (p.isLeaf())
    {
      long pos = p.getPos();
      int chunkId = DataUtils.getPageChunkId(pos);
      if (!set.contains(Integer.valueOf(chunkId))) {
        return 0;
      }
      if (p.getKeyCount() > 0)
      {
        K key = p.getKey(0);
        V value = get(key);
        if (value != null) {
          replace(key, value, value);
        }
      }
      return 1;
    }
    int writtenPageCount = 0;
    for (int i = 0; i < getChildPageCount(p); i++)
    {
      long childPos = p.getChildPagePos(i);
      if ((childPos != 0L) && (DataUtils.getPageType(childPos) == 0))
      {
        int chunkId = DataUtils.getPageChunkId(childPos);
        if (!set.contains(Integer.valueOf(chunkId))) {}
      }
      else
      {
        writtenPageCount += rewrite(p.getChildPage(i), set);
      }
    }
    if (writtenPageCount == 0)
    {
      long pos = p.getPos();
      int chunkId = DataUtils.getPageChunkId(pos);
      if (set.contains(Integer.valueOf(chunkId)))
      {
        Page p2 = p;
        while (!p2.isLeaf()) {
          p2 = p2.getChildPage(0);
        }
        K key = p2.getKey(0);
        V value = get(key);
        if (value != null) {
          replace(key, value, value);
        }
        writtenPageCount++;
      }
    }
    return writtenPageCount;
  }
  
  public Cursor<K, V> cursor(K from)
  {
    return new Cursor(this, this.root, from);
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    final MVMap<K, V> map = this;
    final Page root = this.root;
    new AbstractSet()
    {
      public Iterator<Map.Entry<K, V>> iterator()
      {
        final Cursor<K, V> cursor = new Cursor(map, root, null);
        new Iterator()
        {
          public boolean hasNext()
          {
            return cursor.hasNext();
          }
          
          public Map.Entry<K, V> next()
          {
            K k = cursor.next();
            return new DataUtils.MapEntry(k, cursor.getValue());
          }
          
          public void remove()
          {
            throw DataUtils.newUnsupportedOperationException("Removing is not supported");
          }
        };
      }
      
      public int size()
      {
        return MVMap.this.size();
      }
      
      public boolean contains(Object o)
      {
        return MVMap.this.containsKey(o);
      }
    };
  }
  
  public Set<K> keySet()
  {
    final MVMap<K, V> map = this;
    final Page root = this.root;
    new AbstractSet()
    {
      public Iterator<K> iterator()
      {
        return new Cursor(map, root, null);
      }
      
      public int size()
      {
        return MVMap.this.size();
      }
      
      public boolean contains(Object o)
      {
        return MVMap.this.containsKey(o);
      }
    };
  }
  
  public Page getRoot()
  {
    return this.root;
  }
  
  public String getName()
  {
    return this.store.getMapName(this.id);
  }
  
  public MVStore getStore()
  {
    return this.store;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  void rollbackTo(long version)
  {
    beforeWrite();
    if (version > this.createVersion) {
      if (this.root.getVersion() >= version) {
        for (;;)
        {
          Page last = (Page)this.oldRoots.peekLast();
          if (last == null) {
            break;
          }
          this.oldRoots.removeLast(last);
          this.root = last;
          if (this.root.getVersion() < version) {
            break;
          }
        }
      }
    }
  }
  
  void removeUnusedOldVersions()
  {
    long oldest = this.store.getOldestVersionToKeep();
    if (oldest == -1L) {
      return;
    }
    Page last = (Page)this.oldRoots.peekLast();
    for (;;)
    {
      Page p = (Page)this.oldRoots.peekFirst();
      if ((p == null) || (p.getVersion() >= oldest) || (p == last)) {
        break;
      }
      this.oldRoots.removeFirst(p);
    }
  }
  
  public boolean isReadOnly()
  {
    return this.readOnly;
  }
  
  public void setVolatile(boolean isVolatile)
  {
    this.isVolatile = isVolatile;
  }
  
  public boolean isVolatile()
  {
    return this.isVolatile;
  }
  
  protected void beforeWrite()
  {
    if (this.closed) {
      throw DataUtils.newIllegalStateException(4, "This map is closed", new Object[0]);
    }
    if (this.readOnly) {
      throw DataUtils.newUnsupportedOperationException("This map is read-only");
    }
    this.store.beforeWrite(this);
  }
  
  public int hashCode()
  {
    return this.id;
  }
  
  public boolean equals(Object o)
  {
    return this == o;
  }
  
  public int size()
  {
    long size = sizeAsLong();
    return size > 2147483647L ? Integer.MAX_VALUE : (int)size;
  }
  
  public long sizeAsLong()
  {
    return this.root.getTotalCount();
  }
  
  public boolean isEmpty()
  {
    return (this.root.isLeaf()) && (this.root.getKeyCount() == 0);
  }
  
  public long getCreateVersion()
  {
    return this.createVersion;
  }
  
  protected void removePage(long pos, int memory)
  {
    this.store.removePage(this, pos, memory);
  }
  
  public MVMap<K, V> openVersion(long version)
  {
    if (this.readOnly) {
      throw DataUtils.newUnsupportedOperationException("This map is read-only; need to call the method on the writable map");
    }
    DataUtils.checkArgument(version >= this.createVersion, "Unknown version {0}; this map was created in version is {1}", new Object[] { Long.valueOf(version), Long.valueOf(this.createVersion) });
    
    Page newest = null;
    
    Page r = this.root;
    if ((version >= r.getVersion()) && ((version == this.writeVersion) || (r.getVersion() >= 0L) || (version <= this.createVersion) || (this.store.getFileStore() == null)))
    {
      newest = r;
    }
    else
    {
      Page last = (Page)this.oldRoots.peekFirst();
      if ((last == null) || (version < last.getVersion())) {
        return this.store.openMapVersion(version, this.id, this);
      }
      Iterator<Page> it = this.oldRoots.iterator();
      while (it.hasNext())
      {
        Page p = (Page)it.next();
        if (p.getVersion() > version) {
          break;
        }
        last = p;
      }
      newest = last;
    }
    MVMap<K, V> m = openReadOnly();
    m.root = newest;
    return m;
  }
  
  MVMap<K, V> openReadOnly()
  {
    MVMap<K, V> m = new MVMap(this.keyType, this.valueType);
    m.readOnly = true;
    HashMap<String, Object> config = New.hashMap();
    config.put("id", Integer.valueOf(this.id));
    config.put("createVersion", Long.valueOf(this.createVersion));
    m.init(this.store, config);
    m.root = this.root;
    return m;
  }
  
  public long getVersion()
  {
    return this.root.getVersion();
  }
  
  protected int getChildPageCount(Page p)
  {
    return p.getRawChildPageCount();
  }
  
  public String getType()
  {
    return null;
  }
  
  String asString(String name)
  {
    StringBuilder buff = new StringBuilder();
    if (name != null) {
      DataUtils.appendMap(buff, "name", name);
    }
    if (this.createVersion != 0L) {
      DataUtils.appendMap(buff, "createVersion", Long.valueOf(this.createVersion));
    }
    String type = getType();
    if (type != null) {
      DataUtils.appendMap(buff, "type", type);
    }
    return buff.toString();
  }
  
  void setWriteVersion(long writeVersion)
  {
    this.writeVersion = writeVersion;
  }
  
  void copyFrom(MVMap<K, V> sourceMap)
  {
    beforeWrite();
    newRoot(copy(sourceMap.root, null));
  }
  
  private Page copy(Page source, CursorPos parent)
  {
    Page target = Page.create(this, this.writeVersion, source);
    if (source.isLeaf())
    {
      Page child = target;
      for (CursorPos p = parent; p != null; p = p.parent)
      {
        p.page.setChild(p.index, child);
        p.page = p.page.copy(this.writeVersion);
        child = p.page;
        if (p.parent == null)
        {
          newRoot(p.page);
          beforeWrite();
        }
      }
    }
    else
    {
      for (int i = 0; i < getChildPageCount(target); i++) {
        target.setChild(i, null);
      }
      CursorPos pos = new CursorPos(target, 0, parent);
      for (int i = 0; i < getChildPageCount(target); i++)
      {
        pos.index = i;
        long p = source.getChildPagePos(i);
        if (p != 0L) {
          copy(source.getChildPage(i), pos);
        }
      }
      target = pos.page;
    }
    return target;
  }
  
  public String toString()
  {
    return asString(null);
  }
  
  public static class Builder<K, V>
    implements MVMap.MapBuilder<MVMap<K, V>, K, V>
  {
    protected DataType keyType;
    protected DataType valueType;
    
    public Builder<K, V> keyType(DataType keyType)
    {
      this.keyType = keyType;
      return this;
    }
    
    public DataType getKeyType()
    {
      return this.keyType;
    }
    
    public DataType getValueType()
    {
      return this.valueType;
    }
    
    public Builder<K, V> valueType(DataType valueType)
    {
      this.valueType = valueType;
      return this;
    }
    
    public MVMap<K, V> create()
    {
      if (this.keyType == null) {
        this.keyType = new ObjectDataType();
      }
      if (this.valueType == null) {
        this.valueType = new ObjectDataType();
      }
      return new MVMap(this.keyType, this.valueType);
    }
  }
  
  public static abstract interface MapBuilder<M extends MVMap<K, V>, K, V>
  {
    public abstract M create();
  }
}
