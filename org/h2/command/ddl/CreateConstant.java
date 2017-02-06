package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.schema.Constant;
import org.h2.schema.Schema;
import org.h2.value.Value;

public class CreateConstant
  extends SchemaCommand
{
  private String constantName;
  private Expression expression;
  private boolean ifNotExists;
  
  public CreateConstant(Session session, Schema schema)
  {
    super(session, schema);
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public int update()
  {
    this.session.commit(true);
    this.session.getUser().checkAdmin();
    Database db = this.session.getDatabase();
    if (getSchema().findConstant(this.constantName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90114, this.constantName);
    }
    int id = getObjectId();
    Constant constant = new Constant(getSchema(), id, this.constantName);
    this.expression = this.expression.optimize(this.session);
    Value value = this.expression.getValue(this.session);
    constant.setValue(value);
    db.addSchemaObject(this.session, constant);
    return 0;
  }
  
  public void setConstantName(String constantName)
  {
    this.constantName = constantName;
  }
  
  public void setExpression(Expression expr)
  {
    this.expression = expr;
  }
  
  public int getType()
  {
    return 23;
  }
}
