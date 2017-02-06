package org.h2.expression;

import org.h2.engine.Database;
import org.h2.util.ValueHashMap;
import org.h2.value.Value;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;

class AggregateDataCount
  extends AggregateData
{
  private long count;
  private ValueHashMap<AggregateDataCount> distinctValues;
  
  void add(Database database, int dataType, boolean distinct, Value v)
  {
    if (v == ValueNull.INSTANCE) {
      return;
    }
    this.count += 1L;
    if (distinct)
    {
      if (this.distinctValues == null) {
        this.distinctValues = ValueHashMap.newInstance();
      }
      this.distinctValues.put(v, this);
      return;
    }
  }
  
  Value getValue(Database database, int dataType, boolean distinct)
  {
    if (distinct) {
      if (this.distinctValues != null) {
        this.count = this.distinctValues.size();
      } else {
        this.count = 0L;
      }
    }
    Value v = ValueLong.get(this.count);
    return v.convertTo(dataType);
  }
}
