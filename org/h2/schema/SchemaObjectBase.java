package org.h2.schema;

import org.h2.engine.DbObjectBase;

public abstract class SchemaObjectBase
  extends DbObjectBase
  implements SchemaObject
{
  private Schema schema;
  
  protected void initSchemaObjectBase(Schema newSchema, int id, String name, String traceModule)
  {
    initDbObjectBase(newSchema.getDatabase(), id, name, traceModule);
    this.schema = newSchema;
  }
  
  public Schema getSchema()
  {
    return this.schema;
  }
  
  public String getSQL()
  {
    return this.schema.getSQL() + "." + super.getSQL();
  }
  
  public boolean isHidden()
  {
    return false;
  }
}
