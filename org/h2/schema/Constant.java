package org.h2.schema;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.ValueExpression;
import org.h2.message.DbException;
import org.h2.table.Table;
import org.h2.value.Value;

public class Constant
  extends SchemaObjectBase
{
  private Value value;
  private ValueExpression expression;
  
  public Constant(Schema schema, int id, String name)
  {
    initSchemaObjectBase(schema, id, name, "schema");
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQL()
  {
    return "CREATE CONSTANT " + getSQL() + " VALUE " + this.value.getSQL();
  }
  
  public int getType()
  {
    return 11;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
    invalidate();
  }
  
  public void checkRename() {}
  
  public void setValue(Value value)
  {
    this.value = value;
    this.expression = ValueExpression.get(value);
  }
  
  public ValueExpression getValue()
  {
    return this.expression;
  }
}
