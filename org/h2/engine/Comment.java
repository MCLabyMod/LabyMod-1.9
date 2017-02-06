package org.h2.engine;

import org.h2.message.DbException;
import org.h2.table.Table;
import org.h2.util.StringUtils;

public class Comment
  extends DbObjectBase
{
  private final int objectType;
  private final String objectName;
  private String commentText;
  
  public Comment(Database database, int id, DbObject obj)
  {
    initDbObjectBase(database, id, getKey(obj), "database");
    this.objectType = obj.getType();
    this.objectName = obj.getSQL();
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  private static String getTypeName(int type)
  {
    switch (type)
    {
    case 11: 
      return "CONSTANT";
    case 5: 
      return "CONSTRAINT";
    case 9: 
      return "ALIAS";
    case 1: 
      return "INDEX";
    case 7: 
      return "ROLE";
    case 10: 
      return "SCHEMA";
    case 3: 
      return "SEQUENCE";
    case 0: 
      return "TABLE";
    case 4: 
      return "TRIGGER";
    case 2: 
      return "USER";
    case 12: 
      return "DOMAIN";
    }
    return "type" + type;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQL()
  {
    StringBuilder buff = new StringBuilder("COMMENT ON ");
    buff.append(getTypeName(this.objectType)).append(' ').append(this.objectName).append(" IS ");
    if (this.commentText == null) {
      buff.append("NULL");
    } else {
      buff.append(StringUtils.quoteStringSQL(this.commentText));
    }
    return buff.toString();
  }
  
  public int getType()
  {
    return 13;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
  }
  
  public void checkRename()
  {
    DbException.throwInternalError();
  }
  
  static String getKey(DbObject obj)
  {
    return getTypeName(obj.getType()) + " " + obj.getSQL();
  }
  
  public void setCommentText(String comment)
  {
    this.commentText = comment;
  }
}
