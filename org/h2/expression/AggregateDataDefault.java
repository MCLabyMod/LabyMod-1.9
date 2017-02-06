package org.h2.expression;

import org.h2.engine.Database;
import org.h2.message.DbException;
import org.h2.util.ValueHashMap;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueDouble;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;

class AggregateDataDefault
  extends AggregateData
{
  private final int aggregateType;
  private long count;
  private ValueHashMap<AggregateDataDefault> distinctValues;
  private Value value;
  private double m2;
  private double mean;
  
  AggregateDataDefault(int aggregateType)
  {
    this.aggregateType = aggregateType;
  }
  
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
    switch (this.aggregateType)
    {
    case 3: 
      if (this.value == null)
      {
        this.value = v.convertTo(dataType);
      }
      else
      {
        v = v.convertTo(this.value.getType());
        this.value = this.value.add(v);
      }
      break;
    case 6: 
      if (this.value == null)
      {
        this.value = v.convertTo(DataType.getAddProofType(dataType));
      }
      else
      {
        v = v.convertTo(this.value.getType());
        this.value = this.value.add(v);
      }
      break;
    case 4: 
      if ((this.value == null) || (database.compare(v, this.value) < 0)) {
        this.value = v;
      }
      break;
    case 5: 
      if ((this.value == null) || (database.compare(v, this.value) > 0)) {
        this.value = v;
      }
      break;
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      double x = v.getDouble();
      if (this.count == 1L)
      {
        this.mean = x;
        this.m2 = 0.0D;
      }
      else
      {
        double delta = x - this.mean;
        this.mean += delta / this.count;
        this.m2 += delta * (x - this.mean);
      }
      break;
    case 12: 
      v = v.convertTo(1);
      if (this.value == null) {
        this.value = v;
      } else {
        this.value = ValueBoolean.get((this.value.getBoolean().booleanValue()) && (v.getBoolean().booleanValue()));
      }
      break;
    case 11: 
      v = v.convertTo(1);
      if (this.value == null) {
        this.value = v;
      } else {
        this.value = ValueBoolean.get((this.value.getBoolean().booleanValue()) || (v.getBoolean().booleanValue()));
      }
      break;
    case 14: 
      if (this.value == null) {
        this.value = v.convertTo(dataType);
      } else {
        this.value = ValueLong.get(this.value.getLong() & v.getLong()).convertTo(dataType);
      }
      break;
    case 13: 
      if (this.value == null) {
        this.value = v.convertTo(dataType);
      } else {
        this.value = ValueLong.get(this.value.getLong() | v.getLong()).convertTo(dataType);
      }
      break;
    default: 
      DbException.throwInternalError("type=" + this.aggregateType);
    }
  }
  
  Value getValue(Database database, int dataType, boolean distinct)
  {
    if (distinct)
    {
      this.count = 0L;
      groupDistinct(database, dataType);
    }
    Value v = null;
    switch (this.aggregateType)
    {
    case 3: 
    case 4: 
    case 5: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
      v = this.value;
      break;
    case 6: 
      if (this.value != null) {
        v = divide(this.value, this.count);
      }
      break;
    case 7: 
      if (this.count < 1L) {
        return ValueNull.INSTANCE;
      }
      v = ValueDouble.get(Math.sqrt(this.m2 / this.count));
      break;
    case 8: 
      if (this.count < 2L) {
        return ValueNull.INSTANCE;
      }
      v = ValueDouble.get(Math.sqrt(this.m2 / (this.count - 1L)));
      break;
    case 9: 
      if (this.count < 1L) {
        return ValueNull.INSTANCE;
      }
      v = ValueDouble.get(this.m2 / this.count);
      break;
    case 10: 
      if (this.count < 2L) {
        return ValueNull.INSTANCE;
      }
      v = ValueDouble.get(this.m2 / (this.count - 1L));
      break;
    default: 
      DbException.throwInternalError("type=" + this.aggregateType);
    }
    return v == null ? ValueNull.INSTANCE : v.convertTo(dataType);
  }
  
  private static Value divide(Value a, long by)
  {
    if (by == 0L) {
      return ValueNull.INSTANCE;
    }
    int type = Value.getHigherOrder(a.getType(), 5);
    Value b = ValueLong.get(by).convertTo(type);
    a = a.convertTo(type).divide(b);
    return a;
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
