package org.h2.expression;

import org.h2.engine.Database;
import org.h2.util.IntIntHashMap;
import org.h2.value.Value;
import org.h2.value.ValueInt;

class AggregateDataSelectivity
  extends AggregateData
{
  private long count;
  private IntIntHashMap distinctHashes;
  private double m2;
  
  void add(Database database, int dataType, boolean distinct, Value v)
  {
    this.count += 1L;
    if (this.distinctHashes == null) {
      this.distinctHashes = new IntIntHashMap();
    }
    int size = this.distinctHashes.size();
    if (size > 10000)
    {
      this.distinctHashes = new IntIntHashMap();
      this.m2 += size;
    }
    int hash = v.hashCode();
    
    this.distinctHashes.put(hash, 1);
  }
  
  Value getValue(Database database, int dataType, boolean distinct)
  {
    if (distinct) {
      this.count = 0L;
    }
    Value v = null;
    int s = 0;
    if (this.count == 0L)
    {
      s = 0;
    }
    else
    {
      this.m2 += this.distinctHashes.size();
      this.m2 = (100.0D * this.m2 / this.count);
      s = (int)this.m2;
      s = s > 100 ? 100 : s <= 0 ? 1 : s;
    }
    v = ValueInt.get(s);
    return v.convertTo(dataType);
  }
}
