package org.h2.result;

import org.h2.value.Value;

public class SimpleRowValue
  implements SearchRow
{
  private long key;
  private int version;
  private int index;
  private final int virtualColumnCount;
  private Value data;
  
  public SimpleRowValue(int columnCount)
  {
    this.virtualColumnCount = columnCount;
  }
  
  public void setKeyAndVersion(SearchRow row)
  {
    this.key = row.getKey();
    this.version = row.getVersion();
  }
  
  public int getVersion()
  {
    return this.version;
  }
  
  public int getColumnCount()
  {
    return this.virtualColumnCount;
  }
  
  public long getKey()
  {
    return this.key;
  }
  
  public void setKey(long key)
  {
    this.key = key;
  }
  
  public Value getValue(int idx)
  {
    return idx == this.index ? this.data : null;
  }
  
  public void setValue(int idx, Value v)
  {
    this.index = idx;
    this.data = v;
  }
  
  public String toString()
  {
    return "( /* " + this.key + " */ " + (this.data == null ? "null" : this.data.getTraceSQL()) + " )";
  }
  
  public int getMemory()
  {
    return 24 + (this.data == null ? 0 : this.data.getMemory());
  }
}
