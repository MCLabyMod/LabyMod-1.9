package org.h2.jmx;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.h2.command.Command;
import org.h2.engine.ConnectionInfo;
import org.h2.engine.Constants;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.TraceSystem;
import org.h2.mvstore.FileStore;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.schema.Schema;
import org.h2.store.PageStore;
import org.h2.table.Table;
import org.h2.util.Cache;
import org.h2.util.New;

public class DatabaseInfo
  implements DatabaseInfoMBean
{
  private static final Map<String, ObjectName> MBEANS = ;
  private final Database database;
  
  private DatabaseInfo(Database database)
  {
    if (database == null) {
      throw new IllegalArgumentException("Argument 'database' must not be null");
    }
    this.database = database;
  }
  
  private static ObjectName getObjectName(String name, String path)
    throws JMException
  {
    name = name.replace(':', '_');
    path = path.replace(':', '_');
    Hashtable<String, String> map = new Hashtable();
    map.put("name", name);
    map.put("path", path);
    return new ObjectName("org.h2", map);
  }
  
  public static void registerMBean(ConnectionInfo connectionInfo, Database database)
    throws JMException
  {
    String path = connectionInfo.getName();
    if (!MBEANS.containsKey(path))
    {
      MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
      String name = database.getShortName();
      ObjectName mbeanObjectName = getObjectName(name, path);
      MBEANS.put(path, mbeanObjectName);
      DatabaseInfo info = new DatabaseInfo(database);
      Object mbean = new DocumentedMBean(info, DatabaseInfoMBean.class);
      mbeanServer.registerMBean(mbean, mbeanObjectName);
    }
  }
  
  public static void unregisterMBean(String name)
    throws Exception
  {
    ObjectName mbeanObjectName = (ObjectName)MBEANS.remove(name);
    if (mbeanObjectName != null)
    {
      MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
      mbeanServer.unregisterMBean(mbeanObjectName);
    }
  }
  
  public boolean isExclusive()
  {
    return this.database.getExclusiveSession() != null;
  }
  
  public boolean isReadOnly()
  {
    return this.database.isReadOnly();
  }
  
  public String getMode()
  {
    return this.database.getMode().getName();
  }
  
  public boolean isMultiThreaded()
  {
    return this.database.isMultiThreaded();
  }
  
  public boolean isMvcc()
  {
    return this.database.isMultiVersion();
  }
  
  public int getLogMode()
  {
    return this.database.getLogMode();
  }
  
  public void setLogMode(int value)
  {
    this.database.setLogMode(value);
  }
  
  public int getTraceLevel()
  {
    return this.database.getTraceSystem().getLevelFile();
  }
  
  public void setTraceLevel(int level)
  {
    this.database.getTraceSystem().setLevelFile(level);
  }
  
  public long getFileWriteCountTotal()
  {
    if (!this.database.isPersistent()) {
      return 0L;
    }
    PageStore p = this.database.getPageStore();
    if (p != null) {
      return p.getWriteCountTotal();
    }
    return 0L;
  }
  
  public long getFileWriteCount()
  {
    if (!this.database.isPersistent()) {
      return 0L;
    }
    PageStore p = this.database.getPageStore();
    if (p != null) {
      return p.getWriteCount();
    }
    return this.database.getMvStore().getStore().getFileStore().getReadCount();
  }
  
  public long getFileReadCount()
  {
    if (!this.database.isPersistent()) {
      return 0L;
    }
    PageStore p = this.database.getPageStore();
    if (p != null) {
      return p.getReadCount();
    }
    return this.database.getMvStore().getStore().getFileStore().getReadCount();
  }
  
  public long getFileSize()
  {
    if (!this.database.isPersistent()) {
      return 0L;
    }
    PageStore p = this.database.getPageStore();
    if (p != null) {
      return p.getPageCount() * p.getPageSize() / 1024;
    }
    return this.database.getMvStore().getStore().getFileStore().size();
  }
  
  public int getCacheSizeMax()
  {
    if (!this.database.isPersistent()) {
      return 0;
    }
    PageStore p = this.database.getPageStore();
    if (p != null) {
      return p.getCache().getMaxMemory();
    }
    return this.database.getMvStore().getStore().getCacheSize() * 1024;
  }
  
  public void setCacheSizeMax(int kb)
  {
    if (this.database.isPersistent()) {
      this.database.setCacheSize(kb);
    }
  }
  
  public int getCacheSize()
  {
    if (!this.database.isPersistent()) {
      return 0;
    }
    PageStore p = this.database.getPageStore();
    if (p != null) {
      return p.getCache().getMemory();
    }
    return this.database.getMvStore().getStore().getCacheSizeUsed() * 1024;
  }
  
  public String getVersion()
  {
    return Constants.getFullVersion();
  }
  
  public String listSettings()
  {
    StringBuilder buff = new StringBuilder();
    for (Map.Entry<String, String> e : new TreeMap(this.database.getSettings().getSettings()).entrySet()) {
      buff.append((String)e.getKey()).append(" = ").append((String)e.getValue()).append('\n');
    }
    return buff.toString();
  }
  
  public String listSessions()
  {
    StringBuilder buff = new StringBuilder();
    for (Session session : this.database.getSessions(false))
    {
      buff.append("session id: ").append(session.getId());
      buff.append(" user: ").append(session.getUser().getName()).append('\n');
      
      buff.append("connected: ").append(new Timestamp(session.getSessionStart())).append('\n');
      
      Command command = session.getCurrentCommand();
      if (command != null)
      {
        buff.append("statement: ").append(session.getCurrentCommand()).append('\n');
        
        long commandStart = session.getCurrentCommandStart();
        if (commandStart != 0L) {
          buff.append("started: ").append(new Timestamp(commandStart)).append('\n');
        }
      }
      Table[] t = session.getLocks();
      if (t.length > 0) {
        for (Table table : session.getLocks())
        {
          if (table.isLockedExclusivelyBy(session)) {
            buff.append("write lock on ");
          } else {
            buff.append("read lock on ");
          }
          buff.append(table.getSchema().getName()).append('.').append(table.getName()).append('\n');
        }
      }
      buff.append('\n');
    }
    return buff.toString();
  }
}
