package org.h2.engine;

import java.util.ArrayList;
import java.util.HashMap;
import org.h2.command.CommandInterface;
import org.h2.command.Parser;
import org.h2.message.DbException;
import org.h2.store.FileLock;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class Engine
  implements SessionFactory
{
  private static final Engine INSTANCE = new Engine();
  private static final HashMap<String, Database> DATABASES = New.hashMap();
  private volatile long wrongPasswordDelay = SysProperties.DELAY_WRONG_PASSWORD_MIN;
  private boolean jmx;
  
  public static Engine getInstance()
  {
    return INSTANCE;
  }
  
  private Session openSession(ConnectionInfo ci, boolean ifExists, String cipher)
  {
    String name = ci.getName();
    
    ci.removeProperty("NO_UPGRADE", false);
    boolean openNew = ci.getProperty("OPEN_NEW", false);
    Database database;
    Database database;
    if ((openNew) || (ci.isUnnamedInMemory())) {
      database = null;
    } else {
      database = (Database)DATABASES.get(name);
    }
    User user = null;
    boolean opened = false;
    if (database == null)
    {
      if ((ifExists) && (!Database.exists(name))) {
        throw DbException.get(90013, name);
      }
      database = new Database(ci, cipher);
      opened = true;
      if (database.getAllUsers().size() == 0)
      {
        user = new User(database, database.allocateObjectId(), ci.getUserName(), false);
        
        user.setAdmin(true);
        user.setUserPasswordHash(ci.getUserPasswordHash());
        database.setMasterUser(user);
      }
      if (!ci.isUnnamedInMemory()) {
        DATABASES.put(name, database);
      }
    }
    synchronized (database)
    {
      if (opened) {
        database.opened();
      }
      if (database.isClosing()) {
        return null;
      }
      if (user == null)
      {
        if (database.validateFilePasswordHash(cipher, ci.getFilePasswordHash()))
        {
          user = database.findUser(ci.getUserName());
          if ((user != null) && 
            (!user.validateUserPasswordHash(ci.getUserPasswordHash()))) {
            user = null;
          }
        }
        if ((opened) && ((user == null) || (!user.isAdmin()))) {
          database.setEventListener(null);
        }
      }
      if (user == null)
      {
        database.removeSession(null);
        throw DbException.get(28000);
      }
      checkClustering(ci, database);
      Session session = database.createSession(user);
      if (ci.getProperty("JMX", false))
      {
        try
        {
          Utils.callStaticMethod("org.h2.jmx.DatabaseInfo.registerMBean", new Object[] { ci, database });
        }
        catch (Exception e)
        {
          database.removeSession(session);
          throw DbException.get(50100, e, new String[] { "JMX" });
        }
        this.jmx = true;
      }
      return session;
    }
  }
  
  public Session createSession(ConnectionInfo ci)
  {
    return INSTANCE.createSessionAndValidate(ci);
  }
  
  private Session createSessionAndValidate(ConnectionInfo ci)
  {
    try
    {
      ConnectionInfo backup = null;
      String lockMethodName = ci.getProperty("FILE_LOCK", null);
      int fileLockMethod = FileLock.getFileLockMethod(lockMethodName);
      if (fileLockMethod == 3)
      {
        ci.setProperty("OPEN_NEW", "TRUE");
        try
        {
          backup = ci.clone();
        }
        catch (CloneNotSupportedException e)
        {
          throw DbException.convert(e);
        }
      }
      Session session = openSession(ci);
      validateUserAndPassword(true);
      if (backup != null) {
        session.setConnectionInfo(backup);
      }
      return session;
    }
    catch (DbException e)
    {
      if (e.getErrorCode() == 28000) {
        validateUserAndPassword(false);
      }
      throw e;
    }
  }
  
  private synchronized Session openSession(ConnectionInfo ci)
  {
    boolean ifExists = ci.removeProperty("IFEXISTS", false);
    boolean ignoreUnknownSetting = ci.removeProperty("IGNORE_UNKNOWN_SETTINGS", false);
    
    String cipher = ci.removeProperty("CIPHER", null);
    String init = ci.removeProperty("INIT", null);
    Session session;
    for (int i = 0;; i++)
    {
      session = openSession(ci, ifExists, cipher);
      if (session != null) {
        break;
      }
      if (i > 60000) {
        throw DbException.get(90020, "Waited for database closing longer than 1 minute");
      }
      try
      {
        Thread.sleep(1L);
      }
      catch (InterruptedException e) {}
    }
    session.setAllowLiterals(true);
    DbSettings defaultSettings = DbSettings.getDefaultSettings();
    for (String setting : ci.getKeys()) {
      if (!defaultSettings.containsKey(setting))
      {
        String value = ci.getProperty(setting);
        try
        {
          CommandInterface command = session.prepareCommand("SET " + Parser.quoteIdentifier(setting) + " " + value, Integer.MAX_VALUE);
          
          command.executeUpdate();
        }
        catch (DbException e)
        {
          if (!ignoreUnknownSetting)
          {
            session.close();
            throw e;
          }
        }
      }
    }
    if (init != null) {
      try
      {
        CommandInterface command = session.prepareCommand(init, Integer.MAX_VALUE);
        
        command.executeUpdate();
      }
      catch (DbException e)
      {
        if (!ignoreUnknownSetting)
        {
          session.close();
          throw e;
        }
      }
    }
    session.setAllowLiterals(false);
    session.commit(true);
    return session;
  }
  
  private static void checkClustering(ConnectionInfo ci, Database database)
  {
    String clusterSession = ci.getProperty(13, null);
    if ("''".equals(clusterSession)) {
      return;
    }
    String clusterDb = database.getCluster();
    if ((!"''".equals(clusterDb)) && 
      (!"TRUE".equals(clusterSession)) && 
      (!StringUtils.equals(clusterSession, clusterDb)))
    {
      if (clusterDb.equals("''")) {
        throw DbException.get(90093);
      }
      throw DbException.get(90094, clusterDb);
    }
  }
  
  void close(String name)
  {
    if (this.jmx) {
      try
      {
        Utils.callStaticMethod("org.h2.jmx.DatabaseInfo.unregisterMBean", new Object[] { name });
      }
      catch (Exception e)
      {
        throw DbException.get(50100, e, new String[] { "JMX" });
      }
    }
    DATABASES.remove(name);
  }
  
  private void validateUserAndPassword(boolean correct)
  {
    int min = SysProperties.DELAY_WRONG_PASSWORD_MIN;
    if (correct)
    {
      long delay = this.wrongPasswordDelay;
      if ((delay > min) && (delay > 0L)) {
        synchronized (INSTANCE)
        {
          delay = MathUtils.secureRandomInt((int)delay);
          try
          {
            Thread.sleep(delay);
          }
          catch (InterruptedException e) {}
          this.wrongPasswordDelay = min;
        }
      }
    }
    else
    {
      synchronized (INSTANCE)
      {
        long delay = this.wrongPasswordDelay;
        int max = SysProperties.DELAY_WRONG_PASSWORD_MAX;
        if (max <= 0) {
          max = Integer.MAX_VALUE;
        }
        this.wrongPasswordDelay += this.wrongPasswordDelay;
        if ((this.wrongPasswordDelay > max) || (this.wrongPasswordDelay < 0L)) {
          this.wrongPasswordDelay = max;
        }
        if (min > 0)
        {
          delay += Math.abs(MathUtils.secureRandomLong() % 100L);
          try
          {
            Thread.sleep(delay);
          }
          catch (InterruptedException e) {}
        }
        throw DbException.get(28000);
      }
    }
  }
}
