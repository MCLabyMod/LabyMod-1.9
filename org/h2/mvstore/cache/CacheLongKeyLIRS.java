package org.h2.mvstore.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.h2.mvstore.DataUtils;

public class CacheLongKeyLIRS<V>
{
  private long maxMemory;
  private final Segment<V>[] segments;
  private final int segmentCount;
  private final int segmentShift;
  private final int segmentMask;
  private final int stackMoveDistance;
  
  public CacheLongKeyLIRS(long maxMemory)
  {
    this(maxMemory, 16, 8);
  }
  
  public CacheLongKeyLIRS(long maxMemory, int segmentCount, int stackMoveDistance)
  {
    setMaxMemory(maxMemory);
    DataUtils.checkArgument(Integer.bitCount(segmentCount) == 1, "The segment count must be a power of 2, is {0}", new Object[] { Integer.valueOf(segmentCount) });
    
    this.segmentCount = segmentCount;
    this.segmentMask = (segmentCount - 1);
    this.stackMoveDistance = stackMoveDistance;
    this.segments = new Segment[segmentCount];
    clear();
    
    this.segmentShift = (32 - Integer.bitCount(this.segmentMask));
  }
  
  public void clear()
  {
    long max = Math.max(1L, this.maxMemory / this.segmentCount);
    for (int i = 0; i < this.segmentCount; i++) {
      this.segments[i] = new Segment(max, this.stackMoveDistance, 8);
    }
  }
  
  private Entry<V> find(long key)
  {
    int hash = getHash(key);
    return getSegment(hash).find(key, hash);
  }
  
  public boolean containsKey(long key)
  {
    int hash = getHash(key);
    return getSegment(hash).containsKey(key, hash);
  }
  
  public V peek(long key)
  {
    Entry<V> e = find(key);
    return e == null ? null : e.value;
  }
  
  public V put(long key, V value)
  {
    return (V)put(key, value, sizeOf(value));
  }
  
  public V put(long key, V value, int memory)
  {
    int hash = getHash(key);
    int segmentIndex = getSegmentIndex(hash);
    Segment<V> s = this.segments[segmentIndex];
    synchronized (s)
    {
      s = resizeIfNeeded(s, segmentIndex);
      return (V)s.put(key, hash, value, memory);
    }
  }
  
  private Segment<V> resizeIfNeeded(Segment<V> s, int segmentIndex)
  {
    int newLen = s.getNewMapLen();
    if (newLen == 0) {
      return s;
    }
    Segment<V> s2 = this.segments[segmentIndex];
    if (s == s2)
    {
      s = new Segment(s, newLen);
      this.segments[segmentIndex] = s;
    }
    return s;
  }
  
  protected int sizeOf(V value)
  {
    return 1;
  }
  
  public V remove(long key)
  {
    int hash = getHash(key);
    int segmentIndex = getSegmentIndex(hash);
    Segment<V> s = this.segments[segmentIndex];
    synchronized (s)
    {
      s = resizeIfNeeded(s, segmentIndex);
      return (V)s.remove(key, hash);
    }
  }
  
  public int getMemory(long key)
  {
    int hash = getHash(key);
    return getSegment(hash).getMemory(key, hash);
  }
  
  public V get(long key)
  {
    int hash = getHash(key);
    return (V)getSegment(hash).get(key, hash);
  }
  
  private Segment<V> getSegment(int hash)
  {
    return this.segments[getSegmentIndex(hash)];
  }
  
  private int getSegmentIndex(int hash)
  {
    return hash >>> this.segmentShift & this.segmentMask;
  }
  
  static int getHash(long key)
  {
    int hash = (int)(key >>> 32 ^ key);
    
    hash = (hash >>> 16 ^ hash) * 73244475;
    hash = (hash >>> 16 ^ hash) * 73244475;
    hash = hash >>> 16 ^ hash;
    return hash;
  }
  
  public long getUsedMemory()
  {
    long x = 0L;
    for (Segment<V> s : this.segments) {
      x += s.usedMemory;
    }
    return x;
  }
  
  public void setMaxMemory(long maxMemory)
  {
    DataUtils.checkArgument(maxMemory > 0L, "Max memory must be larger than 0, is {0}", new Object[] { Long.valueOf(maxMemory) });
    
    this.maxMemory = maxMemory;
    if (this.segments != null)
    {
      long max = 1L + maxMemory / this.segments.length;
      for (Segment<V> s : this.segments) {
        s.setMaxMemory(max);
      }
    }
  }
  
  public long getMaxMemory()
  {
    return this.maxMemory;
  }
  
  public synchronized Set<Map.Entry<Long, V>> entrySet()
  {
    HashMap<Long, V> map = new HashMap();
    for (Iterator i$ = keySet().iterator(); i$.hasNext();)
    {
      long k = ((Long)i$.next()).longValue();
      map.put(Long.valueOf(k), find(k).value);
    }
    return map.entrySet();
  }
  
  public Set<Long> keySet()
  {
    HashSet<Long> set = new HashSet();
    for (Segment<V> s : this.segments) {
      set.addAll(s.keySet());
    }
    return set;
  }
  
  public int sizeNonResident()
  {
    int x = 0;
    for (Segment<V> s : this.segments) {
      x += s.queue2Size;
    }
    return x;
  }
  
  public int sizeMapArray()
  {
    int x = 0;
    for (Segment<V> s : this.segments) {
      x += s.entries.length;
    }
    return x;
  }
  
  public int sizeHot()
  {
    int x = 0;
    for (Segment<V> s : this.segments) {
      x += s.mapSize - s.queueSize - s.queue2Size;
    }
    return x;
  }
  
  public long getHits()
  {
    long x = 0L;
    for (Segment<V> s : this.segments) {
      x += s.hits;
    }
    return x;
  }
  
  public long getMisses()
  {
    int x = 0;
    for (Segment<V> s : this.segments) {
      x = (int)(x + s.misses);
    }
    return x;
  }
  
  public int size()
  {
    int x = 0;
    for (Segment<V> s : this.segments) {
      x += s.mapSize - s.queue2Size;
    }
    return x;
  }
  
  public List<Long> keys(boolean cold, boolean nonResident)
  {
    ArrayList<Long> keys = new ArrayList();
    for (Segment<V> s : this.segments) {
      keys.addAll(s.keys(cold, nonResident));
    }
    return keys;
  }
  
  public List<V> values()
  {
    ArrayList<V> list = new ArrayList();
    for (Iterator i$ = keySet().iterator(); i$.hasNext();)
    {
      long k = ((Long)i$.next()).longValue();
      V value = find(k).value;
      if (value != null) {
        list.add(value);
      }
    }
    return list;
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public boolean containsValue(Object value)
  {
    return getMap().containsValue(value);
  }
  
  public Map<Long, V> getMap()
  {
    HashMap<Long, V> map = new HashMap();
    for (Iterator i$ = keySet().iterator(); i$.hasNext();)
    {
      long k = ((Long)i$.next()).longValue();
      V x = find(k).value;
      if (x != null) {
        map.put(Long.valueOf(k), x);
      }
    }
    return map;
  }
  
  public void putAll(Map<Long, ? extends V> m)
  {
    for (Map.Entry<Long, ? extends V> e : m.entrySet()) {
      put(((Long)e.getKey()).longValue(), e.getValue());
    }
  }
  
  private static class Segment<V>
  {
    int mapSize;
    int queueSize;
    int queue2Size;
    long hits;
    long misses;
    final CacheLongKeyLIRS.Entry<V>[] entries;
    long usedMemory;
    private final int stackMoveDistance;
    private long maxMemory;
    private final int mask;
    private final CacheLongKeyLIRS.Entry<V> stack;
    private int stackSize;
    private final CacheLongKeyLIRS.Entry<V> queue;
    private final CacheLongKeyLIRS.Entry<V> queue2;
    private int stackMoveCounter;
    
    Segment(long maxMemory, int stackMoveDistance, int len)
    {
      setMaxMemory(maxMemory);
      this.stackMoveDistance = stackMoveDistance;
      
      this.mask = (len - 1);
      
      this.stack = new CacheLongKeyLIRS.Entry();
      this.stack.stackPrev = (this.stack.stackNext = this.stack);
      this.queue = new CacheLongKeyLIRS.Entry();
      this.queue.queuePrev = (this.queue.queueNext = this.queue);
      this.queue2 = new CacheLongKeyLIRS.Entry();
      this.queue2.queuePrev = (this.queue2.queueNext = this.queue2);
      
      CacheLongKeyLIRS.Entry<V>[] e = new CacheLongKeyLIRS.Entry[len];
      this.entries = e;
    }
    
    Segment(Segment<V> old, int len)
    {
      this(old.maxMemory, old.stackMoveDistance, len);
      this.hits = old.hits;
      this.misses = old.misses;
      CacheLongKeyLIRS.Entry<V> s = old.stack.stackPrev;
      while (s != old.stack)
      {
        CacheLongKeyLIRS.Entry<V> e = copy(s);
        addToMap(e);
        addToStack(e);
        s = s.stackPrev;
      }
      s = old.queue.queuePrev;
      while (s != old.queue)
      {
        CacheLongKeyLIRS.Entry<V> e = find(s.key, CacheLongKeyLIRS.getHash(s.key));
        if (e == null)
        {
          e = copy(s);
          addToMap(e);
        }
        addToQueue(this.queue, e);
        s = s.queuePrev;
      }
      s = old.queue2.queuePrev;
      while (s != old.queue2)
      {
        CacheLongKeyLIRS.Entry<V> e = find(s.key, CacheLongKeyLIRS.getHash(s.key));
        if (e == null)
        {
          e = copy(s);
          addToMap(e);
        }
        addToQueue(this.queue2, e);
        s = s.queuePrev;
      }
    }
    
    int getNewMapLen()
    {
      int len = this.mask + 1;
      if ((len * 3 < this.mapSize * 4) && (len < 268435456)) {
        return len * 2;
      }
      if ((len > 32) && (len / 8 > this.mapSize)) {
        return len / 2;
      }
      return 0;
    }
    
    private void addToMap(CacheLongKeyLIRS.Entry<V> e)
    {
      int index = CacheLongKeyLIRS.getHash(e.key) & this.mask;
      e.mapNext = this.entries[index];
      this.entries[index] = e;
      this.usedMemory += e.memory;
      this.mapSize += 1;
    }
    
    private static <V> CacheLongKeyLIRS.Entry<V> copy(CacheLongKeyLIRS.Entry<V> old)
    {
      CacheLongKeyLIRS.Entry<V> e = new CacheLongKeyLIRS.Entry();
      e.key = old.key;
      e.value = old.value;
      e.memory = old.memory;
      e.topMove = old.topMove;
      return e;
    }
    
    int getMemory(long key, int hash)
    {
      CacheLongKeyLIRS.Entry<V> e = find(key, hash);
      return e == null ? 0 : e.memory;
    }
    
    V get(long key, int hash)
    {
      CacheLongKeyLIRS.Entry<V> e = find(key, hash);
      if (e == null)
      {
        this.misses += 1L;
        return null;
      }
      V value = e.value;
      if (value == null)
      {
        this.misses += 1L;
        return null;
      }
      if (e.isHot())
      {
        if ((e != this.stack.stackNext) && (
          (this.stackMoveDistance == 0) || (this.stackMoveCounter - e.topMove > this.stackMoveDistance))) {
          access(key, hash);
        }
      }
      else {
        access(key, hash);
      }
      this.hits += 1L;
      return value;
    }
    
    private synchronized void access(long key, int hash)
    {
      CacheLongKeyLIRS.Entry<V> e = find(key, hash);
      if ((e == null) || (e.value == null)) {
        return;
      }
      if (e.isHot())
      {
        if ((e != this.stack.stackNext) && (
          (this.stackMoveDistance == 0) || (this.stackMoveCounter - e.topMove > this.stackMoveDistance)))
        {
          boolean wasEnd = e == this.stack.stackPrev;
          removeFromStack(e);
          if (wasEnd) {
            pruneStack();
          }
          addToStack(e);
        }
      }
      else
      {
        removeFromQueue(e);
        if (e.stackNext != null)
        {
          removeFromStack(e);
          
          convertOldestHotToCold();
        }
        else
        {
          addToQueue(this.queue, e);
        }
        addToStack(e);
      }
    }
    
    synchronized V put(long key, int hash, V value, int memory)
    {
      if (value == null) {
        throw DataUtils.newIllegalArgumentException("The value may not be null", new Object[0]);
      }
      CacheLongKeyLIRS.Entry<V> e = find(key, hash);
      V old;
      V old;
      if (e == null)
      {
        old = null;
      }
      else
      {
        old = e.value;
        remove(key, hash);
      }
      if (memory > this.maxMemory) {
        return old;
      }
      e = new CacheLongKeyLIRS.Entry();
      e.key = key;
      e.value = value;
      e.memory = memory;
      int index = hash & this.mask;
      e.mapNext = this.entries[index];
      this.entries[index] = e;
      this.usedMemory += memory;
      if (this.usedMemory > this.maxMemory)
      {
        evict();
        if (this.stackSize > 0) {
          addToQueue(this.queue, e);
        }
      }
      this.mapSize += 1;
      
      addToStack(e);
      return old;
    }
    
    synchronized V remove(long key, int hash)
    {
      int index = hash & this.mask;
      CacheLongKeyLIRS.Entry<V> e = this.entries[index];
      if (e == null) {
        return null;
      }
      V old;
      if (e.key == key)
      {
        V old = e.value;
        this.entries[index] = e.mapNext;
      }
      else
      {
        CacheLongKeyLIRS.Entry<V> last;
        do
        {
          last = e;
          e = e.mapNext;
          if (e == null) {
            return null;
          }
        } while (e.key != key);
        old = e.value;
        last.mapNext = e.mapNext;
      }
      this.mapSize -= 1;
      this.usedMemory -= e.memory;
      if (e.stackNext != null) {
        removeFromStack(e);
      }
      if (e.isHot())
      {
        e = this.queue.queueNext;
        if (e != this.queue)
        {
          removeFromQueue(e);
          if (e.stackNext == null) {
            addToStackBottom(e);
          }
        }
      }
      else
      {
        removeFromQueue(e);
      }
      pruneStack();
      return old;
    }
    
    private void evict()
    {
      do
      {
        evictBlock();
      } while (this.usedMemory > this.maxMemory);
    }
    
    private void evictBlock()
    {
      while ((this.queueSize <= this.mapSize >>> 5) && (this.stackSize > 0)) {
        convertOldestHotToCold();
      }
      while ((this.usedMemory > this.maxMemory) && (this.queueSize > 0))
      {
        CacheLongKeyLIRS.Entry<V> e = this.queue.queuePrev;
        this.usedMemory -= e.memory;
        removeFromQueue(e);
        e.value = null;
        e.memory = 0;
        addToQueue(this.queue2, e);
        while (this.queue2Size + this.queue2Size > this.stackSize)
        {
          e = this.queue2.queuePrev;
          int hash = CacheLongKeyLIRS.getHash(e.key);
          remove(e.key, hash);
        }
      }
    }
    
    private void convertOldestHotToCold()
    {
      CacheLongKeyLIRS.Entry<V> last = this.stack.stackPrev;
      if (last == this.stack) {
        throw new IllegalStateException();
      }
      removeFromStack(last);
      
      addToQueue(this.queue, last);
      pruneStack();
    }
    
    private void pruneStack()
    {
      for (;;)
      {
        CacheLongKeyLIRS.Entry<V> last = this.stack.stackPrev;
        if (last.isHot()) {
          break;
        }
        removeFromStack(last);
      }
    }
    
    CacheLongKeyLIRS.Entry<V> find(long key, int hash)
    {
      int index = hash & this.mask;
      CacheLongKeyLIRS.Entry<V> e = this.entries[index];
      while ((e != null) && (e.key != key)) {
        e = e.mapNext;
      }
      return e;
    }
    
    private void addToStack(CacheLongKeyLIRS.Entry<V> e)
    {
      e.stackPrev = this.stack;
      e.stackNext = this.stack.stackNext;
      e.stackNext.stackPrev = e;
      this.stack.stackNext = e;
      this.stackSize += 1;
      e.topMove = (this.stackMoveCounter++);
    }
    
    private void addToStackBottom(CacheLongKeyLIRS.Entry<V> e)
    {
      e.stackNext = this.stack;
      e.stackPrev = this.stack.stackPrev;
      e.stackPrev.stackNext = e;
      this.stack.stackPrev = e;
      this.stackSize += 1;
    }
    
    private void removeFromStack(CacheLongKeyLIRS.Entry<V> e)
    {
      e.stackPrev.stackNext = e.stackNext;
      e.stackNext.stackPrev = e.stackPrev;
      e.stackPrev = (e.stackNext = null);
      this.stackSize -= 1;
    }
    
    private void addToQueue(CacheLongKeyLIRS.Entry<V> q, CacheLongKeyLIRS.Entry<V> e)
    {
      e.queuePrev = q;
      e.queueNext = q.queueNext;
      e.queueNext.queuePrev = e;
      q.queueNext = e;
      if (e.value != null) {
        this.queueSize += 1;
      } else {
        this.queue2Size += 1;
      }
    }
    
    private void removeFromQueue(CacheLongKeyLIRS.Entry<V> e)
    {
      e.queuePrev.queueNext = e.queueNext;
      e.queueNext.queuePrev = e.queuePrev;
      e.queuePrev = (e.queueNext = null);
      if (e.value != null) {
        this.queueSize -= 1;
      } else {
        this.queue2Size -= 1;
      }
    }
    
    synchronized List<Long> keys(boolean cold, boolean nonResident)
    {
      ArrayList<Long> keys = new ArrayList();
      if (cold)
      {
        CacheLongKeyLIRS.Entry<V> start = nonResident ? this.queue2 : this.queue;
        for (CacheLongKeyLIRS.Entry<V> e = start.queueNext; e != start; e = e.queueNext) {
          keys.add(Long.valueOf(e.key));
        }
      }
      else
      {
        for (CacheLongKeyLIRS.Entry<V> e = this.stack.stackNext; e != this.stack; e = e.stackNext) {
          keys.add(Long.valueOf(e.key));
        }
      }
      return keys;
    }
    
    boolean containsKey(long key, int hash)
    {
      CacheLongKeyLIRS.Entry<V> e = find(key, hash);
      return (e != null) && (e.value != null);
    }
    
    synchronized Set<Long> keySet()
    {
      HashSet<Long> set = new HashSet();
      for (CacheLongKeyLIRS.Entry<V> e = this.stack.stackNext; e != this.stack; e = e.stackNext) {
        set.add(Long.valueOf(e.key));
      }
      for (CacheLongKeyLIRS.Entry<V> e = this.queue.queueNext; e != this.queue; e = e.queueNext) {
        set.add(Long.valueOf(e.key));
      }
      return set;
    }
    
    void setMaxMemory(long maxMemory)
    {
      this.maxMemory = maxMemory;
    }
  }
  
  static class Entry<V>
  {
    long key;
    V value;
    int memory;
    int topMove;
    Entry<V> stackNext;
    Entry<V> stackPrev;
    Entry<V> queueNext;
    Entry<V> queuePrev;
    Entry<V> mapNext;
    
    boolean isHot()
    {
      return this.queueNext == null;
    }
  }
}
