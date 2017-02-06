package org.h2.result;

import org.h2.util.StatementBuilder;
import org.h2.value.Value;

public class SimpleRow
  implements SearchRow
{
  private long key;
  private int version;
  private final Value[] data;
  private int memory;
  
  public SimpleRow(Value[] data)
  {
    this.data = data;
  }
  
  public int getColumnCount()
  {
    return this.data.length;
  }
  
  public long getKey()
  {
    return this.key;
  }
  
  public void setKey(long key)
  {
    this.key = key;
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
  
  public void setValue(int i, Value v)
  {
    this.data[i] = v;
  }
  
  public Value getValue(int i)
  {
    return this.data[i];
  }
  
  public String toString()
  {
    StatementBuilder buff = new StatementBuilder("( /* key:");
    buff.append(getKey());
    if (this.version != 0) {
      buff.append(" v:" + this.version);
    }
    buff.append(" */ ");
    for (Value v : this.data)
    {
      buff.appendExceptFirst(", ");
      buff.append(v == null ? "null" : v.getTraceSQL());
    }
    return buff.append(')').toString();
  }
  
  public int getMemory()
  {
    if (this.memory == 0)
    {
      int len = this.data.length;
      this.memory = (24 + len * 8);
      for (int i = 0; i < len; i++)
      {
        Value v = this.data[i];
        if (v != null) {
          this.memory += v.getMemory();
        }
      }
    }
    return this.memory;
  }
}
