package org.h2.engine;

import org.h2.message.DbException;
import org.h2.table.Table;

public class Setting
  extends DbObjectBase
{
  private int intValue;
  private String stringValue;
  
  public Setting(Database database, int id, String settingName)
  {
    initDbObjectBase(database, id, settingName, "setting");
  }
  
  public void setIntValue(int value)
  {
    this.intValue = value;
  }
  
  public int getIntValue()
  {
    return this.intValue;
  }
  
  public void setStringValue(String value)
  {
    this.stringValue = value;
  }
  
  public String getStringValue()
  {
    return this.stringValue;
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
    StringBuilder buff = new StringBuilder("SET ");
    buff.append(getSQL()).append(' ');
    if (this.stringValue != null) {
      buff.append(this.stringValue);
    } else {
      buff.append(this.intValue);
    }
    return buff.toString();
  }
  
  public int getType()
  {
    return 6;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
    invalidate();
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("RENAME");
  }
}
