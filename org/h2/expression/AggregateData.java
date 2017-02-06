package org.h2.expression;

import org.h2.engine.Database;
import org.h2.value.Value;

abstract class AggregateData
{
  static AggregateData create(int aggregateType)
  {
    if (aggregateType == 15) {
      return new AggregateDataSelectivity();
    }
    if (aggregateType == 2) {
      return new AggregateDataGroupConcat();
    }
    if (aggregateType == 0) {
      return new AggregateDataCountAll();
    }
    if (aggregateType == 1) {
      return new AggregateDataCount();
    }
    if (aggregateType == 16) {
      return new AggregateDataHistogram();
    }
    return new AggregateDataDefault(aggregateType);
  }
  
  abstract void add(Database paramDatabase, int paramInt, boolean paramBoolean, Value paramValue);
  
  abstract Value getValue(Database paramDatabase, int paramInt, boolean paramBoolean);
}
