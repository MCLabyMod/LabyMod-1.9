package org.h2.mvstore;

import java.util.Iterator;

public class Cursor<K, V>
  implements Iterator<K>
{
  private final MVMap<K, ?> map;
  private final K from;
  private CursorPos pos;
  private K current;
  private K last;
  private V currentValue;
  private V lastValue;
  private Page lastPage;
  private final Page root;
  private boolean initialized;
  
  Cursor(MVMap<K, ?> map, Page root, K from)
  {
    this.map = map;
    this.root = root;
    this.from = from;
  }
  
  public boolean hasNext()
  {
    if (!this.initialized)
    {
      min(this.root, this.from);
      this.initialized = true;
      fetchNext();
    }
    return this.current != null;
  }
  
  public K next()
  {
    hasNext();
    K c = this.current;
    this.last = this.current;
    this.lastValue = this.currentValue;
    this.lastPage = (this.pos == null ? null : this.pos.page);
    fetchNext();
    return c;
  }
  
  public K getKey()
  {
    return (K)this.last;
  }
  
  public V getValue()
  {
    return (V)this.lastValue;
  }
  
  Page getPage()
  {
    return this.lastPage;
  }
  
  public void skip(long n)
  {
    if (!hasNext()) {
      return;
    }
    if (n < 10L)
    {
      while (n-- > 0L) {
        fetchNext();
      }
      return;
    }
    long index = this.map.getKeyIndex(this.current);
    K k = this.map.getKey(index + n);
    this.pos = null;
    min(this.root, k);
    fetchNext();
  }
  
  public void remove()
  {
    throw DataUtils.newUnsupportedOperationException("Removing is not supported");
  }
  
  private void min(Page p, K from)
  {
    for (;;)
    {
      if (p.isLeaf())
      {
        int x = from == null ? 0 : p.binarySearch(from);
        if (x < 0) {
          x = -x - 1;
        }
        this.pos = new CursorPos(p, x, this.pos);
        break;
      }
      int x = from == null ? -1 : p.binarySearch(from);
      if (x < 0) {
        x = -x - 1;
      } else {
        x++;
      }
      this.pos = new CursorPos(p, x + 1, this.pos);
      p = p.getChildPage(x);
    }
  }
  
  private void fetchNext()
  {
    while (this.pos != null)
    {
      if (this.pos.index < this.pos.page.getKeyCount())
      {
        int index = this.pos.index++;
        this.current = this.pos.page.getKey(index);
        this.currentValue = this.pos.page.getValue(index);
        return;
      }
      this.pos = this.pos.parent;
      if (this.pos == null) {
        break;
      }
      if (this.pos.index < this.map.getChildPageCount(this.pos.page)) {
        min(this.pos.page.getChildPage(this.pos.index++), null);
      }
    }
    this.current = null;
  }
}
