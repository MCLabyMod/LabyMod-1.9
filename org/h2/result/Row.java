package org.h2.result;

import org.h2.store.Data;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueLong;

public class Row
  implements SearchRow
{
  public static final int MEMORY_CALCULATE = -1;
  public static final Row[] EMPTY_ARRAY = new Row[0];
  private long key;
  private final Value[] data;
  private int memory;
  private int version;
  private boolean deleted;
  private int sessionId;
  
  public Row(Value[] data, int memory)
  {
    this.data = data;
    this.memory = memory;
  }
  
  public Row getCopy()
  {
    Value[] d2 = new Value[this.data.length];
    System.arraycopy(this.data, 0, d2, 0, this.data.length);
    Row r2 = new Row(d2, this.memory);
    r2.key = this.key;
    this.version += 1;
    r2.sessionId = this.sessionId;
    return r2;
  }
  
  public void setKeyAndVersion(SearchRow row)
  {
    setKey(row.getKey());
    setVersion(row.getVersion());
  }
  
  public int getVersion()
  {
    return this.version;
  }
  
  public void setVersion(int version)
  {
    this.version = version;
  }
  
  public long getKey()
  {
    return this.key;
  }
  
  public void setKey(long key)
  {
    this.key = key;
  }
  
  public Value getValue(int i)
  {
    return i == -1 ? ValueLong.get(this.key) : this.data[i];
  }
  
  public int getByteCount(Data dummy)
  {
    int size = 0;
    for (Value v : this.data) {
      size += dummy.getValueLen(v);
    }
    return size;
  }
  
  public void setValue(int i, Value v)
  {
    if (i == -1) {
      this.key = v.getLong();
    } else {
      this.data[i] = v;
    }
  }
  
  public boolean isEmpty()
  {
    return this.data == null;
  }
  
  public int getColumnCount()
  {
    return this.data.length;
  }
  
  public int getMemory()
  {
    if (this.memory != -1) {
      return this.memory;
    }
    int m = 40;
    if (this.data != null)
    {
      int len = this.data.length;
      m += 24 + len * 8;
      for (int i = 0; i < len; i++)
      {
        Value v = this.data[i];
        if (v != null) {
          m += v.getMemory();
        }
      }
    }
    this.memory = m;
    return m;
  }
  
  public String toString()
  {
    StatementBuilder buff = new StatementBuilder("( /* key:");
    buff.append(getKey());
    if (this.version != 0) {
      buff.append(" v:" + this.version);
    }
    if (isDeleted()) {
      buff.append(" deleted");
    }
    buff.append(" */ ");
    if (this.data != null) {
      for (Value v : this.data)
      {
        buff.appendExceptFirst(", ");
        buff.append(v == null ? "null" : v.getTraceSQL());
      }
    }
    return buff.append(')').toString();
  }
  
  public void setDeleted(boolean deleted)
  {
    this.deleted = deleted;
  }
  
  public void setSessionId(int sessionId)
  {
    this.sessionId = sessionId;
  }
  
  public int getSessionId()
  {
    return this.sessionId;
  }
  
  public void commit()
  {
    this.sessionId = 0;
  }
  
  public boolean isDeleted()
  {
    return this.deleted;
  }
  
  public Value[] getValueList()
  {
    return this.data;
  }
}
