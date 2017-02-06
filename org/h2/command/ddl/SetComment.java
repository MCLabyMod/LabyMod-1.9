package org.h2.command.ddl;

import org.h2.engine.Comment;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.value.Value;

public class SetComment
  extends DefineCommand
{
  private String schemaName;
  private String objectName;
  private boolean column;
  private String columnName;
  private int objectType;
  private Expression expr;
  
  public SetComment(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    this.session.commit(true);
    Database db = this.session.getDatabase();
    this.session.getUser().checkAdmin();
    DbObject object = null;
    int errorCode = 50000;
    if (this.schemaName == null) {
      this.schemaName = this.session.getCurrentSchemaName();
    }
    switch (this.objectType)
    {
    case 11: 
      object = db.getSchema(this.schemaName).getConstant(this.objectName);
      break;
    case 5: 
      object = db.getSchema(this.schemaName).getConstraint(this.objectName);
      break;
    case 9: 
      object = db.getSchema(this.schemaName).findFunction(this.objectName);
      errorCode = 90077;
      break;
    case 1: 
      object = db.getSchema(this.schemaName).getIndex(this.objectName);
      break;
    case 7: 
      this.schemaName = null;
      object = db.findRole(this.objectName);
      errorCode = 90070;
      break;
    case 10: 
      this.schemaName = null;
      object = db.findSchema(this.objectName);
      errorCode = 90079;
      break;
    case 3: 
      object = db.getSchema(this.schemaName).getSequence(this.objectName);
      break;
    case 0: 
      object = db.getSchema(this.schemaName).getTableOrView(this.session, this.objectName);
      break;
    case 4: 
      object = db.getSchema(this.schemaName).findTrigger(this.objectName);
      errorCode = 90042;
      break;
    case 2: 
      this.schemaName = null;
      object = db.getUser(this.objectName);
      break;
    case 12: 
      this.schemaName = null;
      object = db.findUserDataType(this.objectName);
      errorCode = 90119;
      break;
    }
    if (object == null) {
      throw DbException.get(errorCode, this.objectName);
    }
    String text = this.expr.optimize(this.session).getValue(this.session).getString();
    if (this.column)
    {
      Table table = (Table)object;
      table.getColumn(this.columnName).setComment(text);
    }
    else
    {
      object.setComment(text);
    }
    if ((this.column) || (this.objectType == 0) || (this.objectType == 2) || (this.objectType == 1) || (this.objectType == 5))
    {
      db.updateMeta(this.session, object);
    }
    else
    {
      Comment comment = db.findComment(object);
      if (comment == null)
      {
        if (text != null)
        {
          int id = getObjectId();
          comment = new Comment(db, id, object);
          comment.setCommentText(text);
          db.addDatabaseObject(this.session, comment);
        }
      }
      else if (text == null)
      {
        db.removeDatabaseObject(this.session, comment);
      }
      else
      {
        comment.setCommentText(text);
        db.updateMeta(this.session, comment);
      }
    }
    return 0;
  }
  
  public void setCommentExpression(Expression expr)
  {
    this.expr = expr;
  }
  
  public void setObjectName(String objectName)
  {
    this.objectName = objectName;
  }
  
  public void setObjectType(int objectType)
  {
    this.objectType = objectType;
  }
  
  public void setColumnName(String columnName)
  {
    this.columnName = columnName;
  }
  
  public void setSchemaName(String schemaName)
  {
    this.schemaName = schemaName;
  }
  
  public void setColumn(boolean column)
  {
    this.column = column;
  }
  
  public int getType()
  {
    return 52;
  }
}
