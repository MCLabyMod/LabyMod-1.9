package org.h2.expression;

import java.util.Arrays;
import java.util.Comparator;
import org.h2.engine.Database;
import org.h2.util.ValueHashMap;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueLong;

class AggregateDataHistogram
  extends AggregateData
{
  private long count;
  private ValueHashMap<AggregateDataHistogram> distinctValues;
  
  void add(Database database, int dataType, boolean distinct, Value v)
  {
    if (this.distinctValues == null) {
      this.distinctValues = ValueHashMap.newInstance();
    }
    AggregateDataHistogram a = (AggregateDataHistogram)this.distinctValues.get(v);
    if ((a == null) && 
      (this.distinctValues.size() < 10000))
    {
      a = new AggregateDataHistogram();
      this.distinctValues.put(v, a);
    }
    if (a != null) {
      a.count += 1L;
    }
  }
  
  Value getValue(Database database, int dataType, boolean distinct)
  {
    if (distinct)
    {
      this.count = 0L;
      groupDistinct(database, dataType);
    }
    ValueArray[] values = new ValueArray[this.distinctValues.size()];
    int i = 0;
    for (Value dv : this.distinctValues.keys())
    {
      AggregateDataHistogram d = (AggregateDataHistogram)this.distinctValues.get(dv);
      values[i] = ValueArray.get(new Value[] { dv, ValueLong.get(d.count) });
      i++;
    }
    final CompareMode compareMode = database.getCompareMode();
    Arrays.sort(values, new Comparator()
    {
      public int compare(ValueArray v1, ValueArray v2)
      {
        Value a1 = v1.getList()[0];
        Value a2 = v2.getList()[0];
        return a1.compareTo(a2, compareMode);
      }
    });
    Value v = ValueArray.get(values);
    return v.convertTo(dataType);
  }
  
  private void groupDistinct(Database database, int dataType)
  {
    if (this.distinctValues == null) {
      return;
    }
    this.count = 0L;
    for (Value v : this.distinctValues.keys()) {
      add(database, dataType, false, v);
    }
  }
}
