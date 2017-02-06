package org.h2.schema;

import org.h2.engine.DbObject;

public abstract interface SchemaObject
  extends DbObject
{
  public abstract Schema getSchema();
  
  public abstract boolean isHidden();
}
