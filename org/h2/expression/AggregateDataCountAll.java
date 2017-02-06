package org.h2.expression;

import org.h2.engine.Database;
import org.h2.message.DbException;
import org.h2.value.Value;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;

class AggregateDataCountAll
  extends AggregateData
{
  private long count;
  
  void add(Database database, int dataType, boolean distinct, Value v)
  {
    if (distinct) {
      throw DbException.throwInternalError();
    }
    this.count += 1L;
  }
  
  Value getValue(Database database, int dataType, boolean distinct)
  {
    if (distinct) {
      throw DbException.throwInternalError();
    }
    Value v = ValueLong.get(this.count);
    return v == null ? ValueNull.INSTANCE : v.convertTo(dataType);
  }
}
