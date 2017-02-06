package org.h2.schema;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import org.h2.api.Trigger;
import org.h2.command.Parser;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.table.Table;
import org.h2.util.JdbcUtils;
import org.h2.util.SourceCompiler;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;

public class TriggerObject
  extends SchemaObjectBase
{
  public static final int DEFAULT_QUEUE_SIZE = 1024;
  private boolean insteadOf;
  private boolean before;
  private int typeMask;
  private boolean rowBased;
  private boolean onRollback;
  private int queueSize = 1024;
  private boolean noWait;
  private Table table;
  private String triggerClassName;
  private String triggerSource;
  private Trigger triggerCallback;
  
  public TriggerObject(Schema schema, int id, String name, Table table)
  {
    initSchemaObjectBase(schema, id, name, "trigger");
    this.table = table;
    setTemporary(table.isTemporary());
  }
  
  public void setBefore(boolean before)
  {
    this.before = before;
  }
  
  public void setInsteadOf(boolean insteadOf)
  {
    this.insteadOf = insteadOf;
  }
  
  private synchronized void load()
  {
    if (this.triggerCallback != null) {
      return;
    }
    try
    {
      Session sysSession = this.database.getSystemSession();
      Connection c2 = sysSession.createConnection(false);
      Object obj;
      Object obj;
      if (this.triggerClassName != null) {
        obj = JdbcUtils.loadUserClass(this.triggerClassName).newInstance();
      } else {
        obj = loadFromSource();
      }
      this.triggerCallback = ((Trigger)obj);
      this.triggerCallback.init(c2, getSchema().getName(), getName(), this.table.getName(), this.before, this.typeMask);
    }
    catch (Throwable e)
    {
      this.triggerCallback = null;
      throw DbException.get(90043, e, new String[] { getName(), this.triggerClassName != null ? this.triggerClassName : "..source..", e.toString() });
    }
  }
  
  private Trigger loadFromSource()
  {
    SourceCompiler compiler = this.database.getCompiler();
    synchronized (compiler)
    {
      String fullClassName = "org.h2.dynamic.trigger." + getName();
      compiler.setSource(fullClassName, this.triggerSource);
      try
      {
        Method m = compiler.getMethod(fullClassName);
        if (m.getParameterTypes().length > 0) {
          throw new IllegalStateException("No parameters are allowed for a trigger");
        }
        return (Trigger)m.invoke(null, new Object[0]);
      }
      catch (DbException e)
      {
        throw e;
      }
      catch (Exception e)
      {
        throw DbException.get(42000, e, new String[] { this.triggerSource });
      }
    }
  }
  
  public void setTriggerClassName(String triggerClassName, boolean force)
  {
    setTriggerAction(triggerClassName, null, force);
  }
  
  public void setTriggerSource(String source, boolean force)
  {
    setTriggerAction(null, source, force);
  }
  
  private void setTriggerAction(String triggerClassName, String source, boolean force)
  {
    this.triggerClassName = triggerClassName;
    this.triggerSource = source;
    try
    {
      load();
    }
    catch (DbException e)
    {
      if (!force) {
        throw e;
      }
    }
  }
  
  public void fire(Session session, int type, boolean beforeAction)
  {
    if ((this.rowBased) || (this.before != beforeAction) || ((this.typeMask & type) == 0)) {
      return;
    }
    load();
    Connection c2 = session.createConnection(false);
    boolean old = false;
    if (type != 8) {
      old = session.setCommitOrRollbackDisabled(true);
    }
    Value identity = session.getLastScopeIdentity();
    try
    {
      this.triggerCallback.fire(c2, null, null);
    }
    catch (Throwable e)
    {
      throw DbException.get(90044, e, new String[] { getName(), this.triggerClassName != null ? this.triggerClassName : "..source..", e.toString() });
    }
    finally
    {
      session.setLastScopeIdentity(identity);
      if (type != 8) {
        session.setCommitOrRollbackDisabled(old);
      }
    }
  }
  
  private static Object[] convertToObjectList(Row row)
  {
    if (row == null) {
      return null;
    }
    int len = row.getColumnCount();
    Object[] list = new Object[len];
    for (int i = 0; i < len; i++) {
      list[i] = row.getValue(i).getObject();
    }
    return list;
  }
  
  public boolean fireRow(Session session, Row oldRow, Row newRow, boolean beforeAction, boolean rollback)
  {
    if ((!this.rowBased) || (this.before != beforeAction)) {
      return false;
    }
    if ((rollback) && (!this.onRollback)) {
      return false;
    }
    load();
    
    boolean fire = false;
    if (((this.typeMask & 0x1) != 0) && 
      (oldRow == null) && (newRow != null)) {
      fire = true;
    }
    if (((this.typeMask & 0x2) != 0) && 
      (oldRow != null) && (newRow != null)) {
      fire = true;
    }
    if (((this.typeMask & 0x4) != 0) && 
      (oldRow != null) && (newRow == null)) {
      fire = true;
    }
    if (!fire) {
      return false;
    }
    Object[] oldList = convertToObjectList(oldRow);
    Object[] newList = convertToObjectList(newRow);
    Object[] newListBackup;
    if ((this.before) && (newList != null))
    {
      Object[] newListBackup = new Object[newList.length];
      System.arraycopy(newList, 0, newListBackup, 0, newList.length);
    }
    else
    {
      newListBackup = null;
    }
    Connection c2 = session.createConnection(false);
    boolean old = session.getAutoCommit();
    boolean oldDisabled = session.setCommitOrRollbackDisabled(true);
    Value identity = session.getLastScopeIdentity();
    try
    {
      session.setAutoCommit(false);
      this.triggerCallback.fire(c2, oldList, newList);
      if (newListBackup != null) {
        for (int i = 0; i < newList.length; i++)
        {
          Object o = newList[i];
          if (o == newListBackup[i]) {}
        }
      }
    }
    catch (Exception e)
    {
      if (!this.onRollback) {
        throw DbException.convert(e);
      }
    }
    finally
    {
      session.setLastScopeIdentity(identity);
      session.setCommitOrRollbackDisabled(oldDisabled);
      session.setAutoCommit(old);
    }
    return this.insteadOf;
  }
  
  public void setTypeMask(int typeMask)
  {
    this.typeMask = typeMask;
  }
  
  public void setRowBased(boolean rowBased)
  {
    this.rowBased = rowBased;
  }
  
  public void setQueueSize(int size)
  {
    this.queueSize = size;
  }
  
  public int getQueueSize()
  {
    return this.queueSize;
  }
  
  public void setNoWait(boolean noWait)
  {
    this.noWait = noWait;
  }
  
  public boolean isNoWait()
  {
    return this.noWait;
  }
  
  public void setOnRollback(boolean onRollback)
  {
    this.onRollback = onRollback;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQLForCopy(Table targetTable, String quotedName)
  {
    StringBuilder buff = new StringBuilder("CREATE FORCE TRIGGER ");
    buff.append(quotedName);
    if (this.insteadOf) {
      buff.append(" INSTEAD OF ");
    } else if (this.before) {
      buff.append(" BEFORE ");
    } else {
      buff.append(" AFTER ");
    }
    buff.append(getTypeNameList());
    buff.append(" ON ").append(targetTable.getSQL());
    if (this.rowBased) {
      buff.append(" FOR EACH ROW");
    }
    if (this.noWait) {
      buff.append(" NOWAIT");
    } else {
      buff.append(" QUEUE ").append(this.queueSize);
    }
    if (this.triggerClassName != null) {
      buff.append(" CALL ").append(Parser.quoteIdentifier(this.triggerClassName));
    } else {
      buff.append(" AS ").append(StringUtils.quoteStringSQL(this.triggerSource));
    }
    return buff.toString();
  }
  
  public String getTypeNameList()
  {
    StatementBuilder buff = new StatementBuilder();
    if ((this.typeMask & 0x1) != 0)
    {
      buff.appendExceptFirst(", ");
      buff.append("INSERT");
    }
    if ((this.typeMask & 0x2) != 0)
    {
      buff.appendExceptFirst(", ");
      buff.append("UPDATE");
    }
    if ((this.typeMask & 0x4) != 0)
    {
      buff.appendExceptFirst(", ");
      buff.append("DELETE");
    }
    if ((this.typeMask & 0x8) != 0)
    {
      buff.appendExceptFirst(", ");
      buff.append("SELECT");
    }
    if (this.onRollback)
    {
      buff.appendExceptFirst(", ");
      buff.append("ROLLBACK");
    }
    return buff.toString();
  }
  
  public String getCreateSQL()
  {
    return getCreateSQLForCopy(this.table, getSQL());
  }
  
  public int getType()
  {
    return 4;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.table.removeTrigger(this);
    this.database.removeMeta(session, getId());
    if (this.triggerCallback != null) {
      try
      {
        this.triggerCallback.remove();
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    }
    this.table = null;
    this.triggerClassName = null;
    this.triggerSource = null;
    this.triggerCallback = null;
    invalidate();
  }
  
  public void checkRename() {}
  
  public Table getTable()
  {
    return this.table;
  }
  
  public boolean isBefore()
  {
    return this.before;
  }
  
  public String getTriggerClassName()
  {
    return this.triggerClassName;
  }
  
  public String getTriggerSource()
  {
    return this.triggerSource;
  }
  
  public void close()
    throws SQLException
  {
    if (this.triggerCallback != null) {
      this.triggerCallback.close();
    }
  }
  
  public boolean isSelectTrigger()
  {
    return (this.typeMask & 0x8) != 0;
  }
}
