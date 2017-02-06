package org.h2.expression;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.util.New;
import org.h2.util.ValueHashMap;
import org.h2.value.Value;
import org.h2.value.ValueNull;

class AggregateDataGroupConcat
  extends AggregateData
{
  private ArrayList<Value> list;
  private ValueHashMap<AggregateDataGroupConcat> distinctValues;
  
  void add(Database database, int dataType, boolean distinct, Value v)
  {
    if (v == ValueNull.INSTANCE) {
      return;
    }
    if (distinct)
    {
      if (this.distinctValues == null) {
        this.distinctValues = ValueHashMap.newInstance();
      }
      this.distinctValues.put(v, this);
      return;
    }
    if (this.list == null) {
      this.list = New.arrayList();
    }
    this.list.add(v);
  }
  
  Value getValue(Database database, int dataType, boolean distinct)
  {
    if (distinct) {
      groupDistinct(database, dataType);
    }
    return null;
  }
  
  ArrayList<Value> getList()
  {
    return this.list;
  }
  
  private void groupDistinct(Database database, int dataType)
  {
    if (this.distinctValues == null) {
      return;
    }
    for (Value v : this.distinctValues.keys()) {
      add(database, dataType, false, v);
    }
  }
}
