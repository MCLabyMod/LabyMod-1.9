package org.h2.engine;

import java.sql.SQLException;
import org.h2.api.DatabaseEventListener;
import org.h2.command.Prepared;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.SearchRow;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueString;

public class MetaRecord
  implements Comparable<MetaRecord>
{
  private final int id;
  private final int objectType;
  private final String sql;
  
  public MetaRecord(SearchRow r)
  {
    this.id = r.getValue(0).getInt();
    this.objectType = r.getValue(2).getInt();
    this.sql = r.getValue(3).getString();
  }
  
  MetaRecord(DbObject obj)
  {
    this.id = obj.getId();
    this.objectType = obj.getType();
    this.sql = obj.getCreateSQL();
  }
  
  void setRecord(SearchRow r)
  {
    r.setValue(0, ValueInt.get(this.id));
    r.setValue(1, ValueInt.get(0));
    r.setValue(2, ValueInt.get(this.objectType));
    r.setValue(3, ValueString.get(this.sql));
  }
  
  void execute(Database db, Session systemSession, DatabaseEventListener listener)
  {
    try
    {
      Prepared command = systemSession.prepare(this.sql);
      command.setObjectId(this.id);
      command.update();
    }
    catch (DbException e)
    {
      e = e.addSQL(this.sql);
      SQLException s = e.getSQLException();
      db.getTrace("database").error(s, this.sql);
      if (listener != null) {
        listener.exceptionThrown(s, this.sql);
      } else {
        throw e;
      }
    }
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getObjectType()
  {
    return this.objectType;
  }
  
  public String getSQL()
  {
    return this.sql;
  }
  
  public int compareTo(MetaRecord other)
  {
    int c1 = getCreateOrder();
    int c2 = other.getCreateOrder();
    if (c1 != c2) {
      return c1 - c2;
    }
    return getId() - other.getId();
  }
  
  private int getCreateOrder()
  {
    switch (this.objectType)
    {
    case 6: 
      return 0;
    case 2: 
      return 1;
    case 10: 
      return 2;
    case 9: 
      return 3;
    case 12: 
      return 4;
    case 3: 
      return 5;
    case 11: 
      return 6;
    case 0: 
      return 7;
    case 1: 
      return 8;
    case 5: 
      return 9;
    case 4: 
      return 10;
    case 7: 
      return 11;
    case 8: 
      return 12;
    case 14: 
      return 13;
    case 13: 
      return 14;
    }
    throw DbException.throwInternalError("type=" + this.objectType);
  }
  
  public String toString()
  {
    return "MetaRecord [id=" + this.id + ", objectType=" + this.objectType + ", sql=" + this.sql + "]";
  }
}
