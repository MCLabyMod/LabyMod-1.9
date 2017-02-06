package org.h2.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;
import org.h2.command.CommandInterface;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceObject;
import org.h2.util.StringUtils;

public class JdbcSavepoint
  extends TraceObject
  implements Savepoint
{
  private static final String SYSTEM_SAVEPOINT_PREFIX = "SYSTEM_SAVEPOINT_";
  private final int savepointId;
  private final String name;
  private JdbcConnection conn;
  
  JdbcSavepoint(JdbcConnection conn, int savepointId, String name, Trace trace, int id)
  {
    setTrace(trace, 6, id);
    this.conn = conn;
    this.savepointId = savepointId;
    this.name = name;
  }
  
  void release()
  {
    this.conn = null;
  }
  
  static String getName(String name, int id)
  {
    if (name != null) {
      return StringUtils.quoteJavaString(name);
    }
    return "SYSTEM_SAVEPOINT_" + id;
  }
  
  void rollback()
  {
    checkValid();
    this.conn.prepareCommand("ROLLBACK TO SAVEPOINT " + getName(this.name, this.savepointId), Integer.MAX_VALUE).executeUpdate();
  }
  
  private void checkValid()
  {
    if (this.conn == null) {
      throw DbException.get(90063, getName(this.name, this.savepointId));
    }
  }
  
  public int getSavepointId()
    throws SQLException
  {
    try
    {
      debugCodeCall("getSavepointId");
      checkValid();
      if (this.name != null) {
        throw DbException.get(90065);
      }
      return this.savepointId;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getSavepointName()
    throws SQLException
  {
    try
    {
      debugCodeCall("getSavepointName");
      checkValid();
      if (this.name == null) {
        throw DbException.get(90064);
      }
      return this.name;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": id=" + this.savepointId + " name=" + this.name;
  }
}
