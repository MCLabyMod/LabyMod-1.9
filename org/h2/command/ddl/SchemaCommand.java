package org.h2.command.ddl;

import org.h2.engine.Session;
import org.h2.schema.Schema;

public abstract class SchemaCommand
  extends DefineCommand
{
  private final Schema schema;
  
  public SchemaCommand(Session session, Schema schema)
  {
    super(session);
    this.schema = schema;
  }
  
  protected Schema getSchema()
  {
    return this.schema;
  }
}
