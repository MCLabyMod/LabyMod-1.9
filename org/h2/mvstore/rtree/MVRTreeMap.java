package org.h2.mvstore.rtree;

import java.util.ArrayList;
import java.util.Iterator;
import org.h2.mvstore.CursorPos;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVMap.MapBuilder;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.Page;
import org.h2.mvstore.Page.PageReference;
import org.h2.mvstore.type.DataType;
import org.h2.mvstore.type.ObjectDataType;
import org.h2.util.New;

public class MVRTreeMap<V>
  extends MVMap<SpatialKey, V>
{
  final SpatialDataType keyType;
  private boolean quadraticSplit;
  
  public MVRTreeMap(int dimensions, DataType valueType)
  {
    super(new SpatialDataType(dimensions), valueType);
    this.keyType = ((SpatialDataType)getKeyType());
  }
  
  public static <V> MVRTreeMap<V> create(int dimensions, DataType valueType)
  {
    return new MVRTreeMap(dimensions, valueType);
  }
  
  public V get(Object key)
  {
    return (V)get(this.root, key);
  }
  
  public RTreeCursor findIntersectingKeys(SpatialKey x)
  {
    new RTreeCursor(this.root, x)
    {
      protected boolean check(boolean leaf, SpatialKey key, SpatialKey test)
      {
        return MVRTreeMap.this.keyType.isOverlap(key, test);
      }
    };
  }
  
  public RTreeCursor findContainedKeys(SpatialKey x)
  {
    new RTreeCursor(this.root, x)
    {
      protected boolean check(boolean leaf, SpatialKey key, SpatialKey test)
      {
        if (leaf) {
          return MVRTreeMap.this.keyType.isInside(key, test);
        }
        return MVRTreeMap.this.keyType.isOverlap(key, test);
      }
    };
  }
  
  private boolean contains(Page p, int index, Object key)
  {
    return this.keyType.contains(p.getKey(index), key);
  }
  
  protected Object get(Page p, Object key)
  {
    if (!p.isLeaf()) {
      for (int i = 0; i < p.getKeyCount(); i++) {
        if (contains(p, i, key))
        {
          Object o = get(p.getChildPage(i), key);
          if (o != null) {
            return o;
          }
        }
      }
    } else {
      for (int i = 0; i < p.getKeyCount(); i++) {
        if (this.keyType.equals(p.getKey(i), key)) {
          return p.getValue(i);
        }
      }
    }
    return null;
  }
  
  protected synchronized Object remove(Page p, long writeVersion, Object key)
  {
    Object result = null;
    if (p.isLeaf())
    {
      for (int i = 0; i < p.getKeyCount(); i++) {
        if (this.keyType.equals(p.getKey(i), key))
        {
          result = p.getValue(i);
          p.remove(i);
          break;
        }
      }
      return result;
    }
    for (int i = 0; i < p.getKeyCount(); i++) {
      if (contains(p, i, key))
      {
        Page cOld = p.getChildPage(i);
        
        Page c = cOld.copy(writeVersion);
        long oldSize = c.getTotalCount();
        result = remove(c, writeVersion, key);
        p.setChild(i, c);
        if (oldSize != c.getTotalCount())
        {
          if (c.getTotalCount() == 0L)
          {
            p.remove(i);
            if (p.getKeyCount() != 0) {
              break;
            }
            c.removePage(); break;
          }
          Object oldBounds = p.getKey(i);
          if (this.keyType.isInside(key, oldBounds)) {
            break;
          }
          p.setKey(i, getBounds(c)); break;
        }
      }
    }
    return result;
  }
  
  private Object getBounds(Page x)
  {
    Object bounds = this.keyType.createBoundingBox(x.getKey(0));
    for (int i = 1; i < x.getKeyCount(); i++) {
      this.keyType.increaseBounds(bounds, x.getKey(i));
    }
    return bounds;
  }
  
  public V put(SpatialKey key, V value)
  {
    return (V)putOrAdd(key, value, false);
  }
  
  public void add(SpatialKey key, V value)
  {
    putOrAdd(key, value, true);
  }
  
  private synchronized Object putOrAdd(SpatialKey key, V value, boolean alwaysAdd)
  {
    beforeWrite();
    long v = this.writeVersion;
    Page p = this.root.copy(v);
    Object result;
    Object result;
    if ((alwaysAdd) || (get(key) == null))
    {
      if ((p.getMemory() > this.store.getPageSplitSize()) && (p.getKeyCount() > 3))
      {
        long totalCount = p.getTotalCount();
        Page split = split(p, v);
        Object k1 = getBounds(p);
        Object k2 = getBounds(split);
        Object[] keys = { k1, k2 };
        Page.PageReference[] children = { new Page.PageReference(p, p.getPos(), p.getTotalCount()), new Page.PageReference(split, split.getPos(), split.getTotalCount()), new Page.PageReference(null, 0L, 0L) };
        
        p = Page.create(this, v, keys, null, children, totalCount, 0);
      }
      add(p, v, key, value);
      result = null;
    }
    else
    {
      result = set(p, v, key, value);
    }
    newRoot(p);
    return result;
  }
  
  private Object set(Page p, long writeVersion, Object key, Object value)
  {
    if (p.isLeaf()) {
      for (int i = 0; i < p.getKeyCount(); i++) {
        if (this.keyType.equals(p.getKey(i), key)) {
          return p.setValue(i, value);
        }
      }
    } else {
      for (int i = 0; i < p.getKeyCount(); i++) {
        if (contains(p, i, key))
        {
          Page c = p.getChildPage(i);
          if (get(c, key) != null)
          {
            c = c.copy(writeVersion);
            Object result = set(c, writeVersion, key, value);
            p.setChild(i, c);
            return result;
          }
        }
      }
    }
    throw DataUtils.newIllegalStateException(3, "Not found: {0}", new Object[] { key });
  }
  
  private void add(Page p, long writeVersion, Object key, Object value)
  {
    if (p.isLeaf())
    {
      p.insertLeaf(p.getKeyCount(), key, value);
      return;
    }
    int index = -1;
    for (int i = 0; i < p.getKeyCount(); i++) {
      if (contains(p, i, key))
      {
        index = i;
        break;
      }
    }
    if (index < 0)
    {
      float min = Float.MAX_VALUE;
      for (int i = 0; i < p.getKeyCount(); i++)
      {
        Object k = p.getKey(i);
        float areaIncrease = this.keyType.getAreaIncrease(k, key);
        if (areaIncrease < min)
        {
          index = i;
          min = areaIncrease;
        }
      }
    }
    Page c = p.getChildPage(index).copy(writeVersion);
    if ((c.getMemory() > this.store.getPageSplitSize()) && (c.getKeyCount() > 4))
    {
      Page split = split(c, writeVersion);
      p.setKey(index, getBounds(c));
      p.setChild(index, c);
      p.insertNode(index, getBounds(split), split);
      
      add(p, writeVersion, key, value);
      return;
    }
    add(c, writeVersion, key, value);
    Object bounds = p.getKey(index);
    this.keyType.increaseBounds(bounds, key);
    p.setKey(index, bounds);
    p.setChild(index, c);
  }
  
  private Page split(Page p, long writeVersion)
  {
    return this.quadraticSplit ? splitQuadratic(p, writeVersion) : splitLinear(p, writeVersion);
  }
  
  private Page splitLinear(Page p, long writeVersion)
  {
    ArrayList<Object> keys = New.arrayList();
    for (int i = 0; i < p.getKeyCount(); i++) {
      keys.add(p.getKey(i));
    }
    int[] extremes = this.keyType.getExtremes(keys);
    if (extremes == null) {
      return splitQuadratic(p, writeVersion);
    }
    Page splitA = newPage(p.isLeaf(), writeVersion);
    Page splitB = newPage(p.isLeaf(), writeVersion);
    move(p, splitA, extremes[0]);
    if (extremes[1] > extremes[0]) {
      extremes[1] -= 1;
    }
    move(p, splitB, extremes[1]);
    Object boundsA = this.keyType.createBoundingBox(splitA.getKey(0));
    Object boundsB = this.keyType.createBoundingBox(splitB.getKey(0));
    while (p.getKeyCount() > 0)
    {
      Object o = p.getKey(0);
      float a = this.keyType.getAreaIncrease(boundsA, o);
      float b = this.keyType.getAreaIncrease(boundsB, o);
      if (a < b)
      {
        this.keyType.increaseBounds(boundsA, o);
        move(p, splitA, 0);
      }
      else
      {
        this.keyType.increaseBounds(boundsB, o);
        move(p, splitB, 0);
      }
    }
    while (splitB.getKeyCount() > 0) {
      move(splitB, p, 0);
    }
    return splitA;
  }
  
  private Page splitQuadratic(Page p, long writeVersion)
  {
    Page splitA = newPage(p.isLeaf(), writeVersion);
    Page splitB = newPage(p.isLeaf(), writeVersion);
    float largest = Float.MIN_VALUE;
    int ia = 0;int ib = 0;
    for (int a = 0; a < p.getKeyCount(); a++)
    {
      Object objA = p.getKey(a);
      for (int b = 0; b < p.getKeyCount(); b++) {
        if (a != b)
        {
          Object objB = p.getKey(b);
          float area = this.keyType.getCombinedArea(objA, objB);
          if (area > largest)
          {
            largest = area;
            ia = a;
            ib = b;
          }
        }
      }
    }
    move(p, splitA, ia);
    if (ia < ib) {
      ib--;
    }
    move(p, splitB, ib);
    Object boundsA = this.keyType.createBoundingBox(splitA.getKey(0));
    Object boundsB = this.keyType.createBoundingBox(splitB.getKey(0));
    while (p.getKeyCount() > 0)
    {
      float diff = 0.0F;float bestA = 0.0F;float bestB = 0.0F;
      int best = 0;
      for (int i = 0; i < p.getKeyCount(); i++)
      {
        Object o = p.getKey(i);
        float incA = this.keyType.getAreaIncrease(boundsA, o);
        float incB = this.keyType.getAreaIncrease(boundsB, o);
        float d = Math.abs(incA - incB);
        if (d > diff)
        {
          diff = d;
          bestA = incA;
          bestB = incB;
          best = i;
        }
      }
      if (bestA < bestB)
      {
        this.keyType.increaseBounds(boundsA, p.getKey(best));
        move(p, splitA, best);
      }
      else
      {
        this.keyType.increaseBounds(boundsB, p.getKey(best));
        move(p, splitB, best);
      }
    }
    while (splitB.getKeyCount() > 0) {
      move(splitB, p, 0);
    }
    return splitA;
  }
  
  private Page newPage(boolean leaf, long writeVersion)
  {
    Page.PageReference[] refs;
    Object[] values;
    Page.PageReference[] refs;
    if (leaf)
    {
      Object[] values = Page.EMPTY_OBJECT_ARRAY;
      refs = null;
    }
    else
    {
      values = null;
      refs = new Page.PageReference[] { new Page.PageReference(null, 0L, 0L) };
    }
    return Page.create(this, writeVersion, Page.EMPTY_OBJECT_ARRAY, values, refs, 0L, 0);
  }
  
  private static void move(Page source, Page target, int sourceIndex)
  {
    Object k = source.getKey(sourceIndex);
    if (source.isLeaf())
    {
      Object v = source.getValue(sourceIndex);
      target.insertLeaf(0, k, v);
    }
    else
    {
      Page c = source.getChildPage(sourceIndex);
      target.insertNode(0, k, c);
    }
    source.remove(sourceIndex);
  }
  
  public void addNodeKeys(ArrayList<SpatialKey> list, Page p)
  {
    if ((p != null) && (!p.isLeaf())) {
      for (int i = 0; i < p.getKeyCount(); i++)
      {
        list.add((SpatialKey)p.getKey(i));
        addNodeKeys(list, p.getChildPage(i));
      }
    }
  }
  
  public boolean isQuadraticSplit()
  {
    return this.quadraticSplit;
  }
  
  public void setQuadraticSplit(boolean quadraticSplit)
  {
    this.quadraticSplit = quadraticSplit;
  }
  
  protected int getChildPageCount(Page p)
  {
    return p.getRawChildPageCount() - 1;
  }
  
  public static class RTreeCursor
    implements Iterator<SpatialKey>
  {
    private final SpatialKey filter;
    private CursorPos pos;
    private SpatialKey current;
    private final Page root;
    private boolean initialized;
    
    protected RTreeCursor(Page root, SpatialKey filter)
    {
      this.root = root;
      this.filter = filter;
    }
    
    public boolean hasNext()
    {
      if (!this.initialized)
      {
        this.pos = new CursorPos(this.root, 0, null);
        fetchNext();
        this.initialized = true;
      }
      return this.current != null;
    }
    
    public void skip(long n)
    {
      while ((hasNext()) && (n-- > 0L)) {
        fetchNext();
      }
    }
    
    public SpatialKey next()
    {
      if (!hasNext()) {
        return null;
      }
      SpatialKey c = this.current;
      fetchNext();
      return c;
    }
    
    public void remove()
    {
      throw DataUtils.newUnsupportedOperationException("Removing is not supported");
    }
    
    protected void fetchNext()
    {
      while (this.pos != null)
      {
        Page p = this.pos.page;
        if (p.isLeaf()) {
          while (this.pos.index < p.getKeyCount())
          {
            SpatialKey c = (SpatialKey)p.getKey(this.pos.index++);
            if ((this.filter == null) || (check(true, c, this.filter)))
            {
              this.current = c;
              return;
            }
          }
        }
        boolean found = false;
        while (this.pos.index < p.getKeyCount())
        {
          int index = this.pos.index++;
          SpatialKey c = (SpatialKey)p.getKey(index);
          if ((this.filter == null) || (check(false, c, this.filter)))
          {
            Page child = this.pos.page.getChildPage(index);
            this.pos = new CursorPos(child, 0, this.pos);
            found = true;
            break;
          }
        }
        if (!found) {
          this.pos = this.pos.parent;
        }
      }
      this.current = null;
    }
    
    protected boolean check(boolean leaf, SpatialKey key, SpatialKey test)
    {
      return true;
    }
  }
  
  public String getType()
  {
    return "rtree";
  }
  
  public static class Builder<V>
    implements MVMap.MapBuilder<MVRTreeMap<V>, SpatialKey, V>
  {
    private int dimensions = 2;
    private DataType valueType;
    
    public Builder<V> dimensions(int dimensions)
    {
      this.dimensions = dimensions;
      return this;
    }
    
    public Builder<V> valueType(DataType valueType)
    {
      this.valueType = valueType;
      return this;
    }
    
    public MVRTreeMap<V> create()
    {
      if (this.valueType == null) {
        this.valueType = new ObjectDataType();
      }
      return new MVRTreeMap(this.dimensions, this.valueType);
    }
  }
}
