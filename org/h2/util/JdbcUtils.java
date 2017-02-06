package org.h2.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import javax.naming.Context;
import javax.sql.DataSource;
import org.h2.api.JavaObjectSerializer;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.DataHandler;

public class JdbcUtils
{
  public static JavaObjectSerializer serializer;
  private static final String[] DRIVERS = { "h2:", "org.h2.Driver", "Cache:", "com.intersys.jdbc.CacheDriver", "daffodilDB://", "in.co.daffodil.db.rmi.RmiDaffodilDBDriver", "daffodil", "in.co.daffodil.db.jdbc.DaffodilDBDriver", "db2:", "COM.ibm.db2.jdbc.net.DB2Driver", "derby:net:", "org.apache.derby.jdbc.ClientDriver", "derby://", "org.apache.derby.jdbc.ClientDriver", "derby:", "org.apache.derby.jdbc.EmbeddedDriver", "FrontBase:", "com.frontbase.jdbc.FBJDriver", "firebirdsql:", "org.firebirdsql.jdbc.FBDriver", "hsqldb:", "org.hsqldb.jdbcDriver", "informix-sqli:", "com.informix.jdbc.IfxDriver", "jtds:", "net.sourceforge.jtds.jdbc.Driver", "microsoft:", "com.microsoft.jdbc.sqlserver.SQLServerDriver", "mimer:", "com.mimer.jdbc.Driver", "mysql:", "com.mysql.jdbc.Driver", "odbc:", "sun.jdbc.odbc.JdbcOdbcDriver", "oracle:", "oracle.jdbc.driver.OracleDriver", "pervasive:", "com.pervasive.jdbc.v2.Driver", "pointbase:micro:", "com.pointbase.me.jdbc.jdbcDriver", "pointbase:", "com.pointbase.jdbc.jdbcUniversalDriver", "postgresql:", "org.postgresql.Driver", "sybase:", "com.sybase.jdbc3.jdbc.SybDriver", "sqlserver:", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "teradata:", "com.ncr.teradata.TeraDriver" };
  private static boolean allowAllClasses;
  private static HashSet<String> allowedClassNames;
  private static ArrayList<Utils.ClassFactory> userClassFactories = new ArrayList();
  private static String[] allowedClassNamePrefixes;
  
  public static void addClassFactory(Utils.ClassFactory classFactory)
  {
    getUserClassFactories().add(classFactory);
  }
  
  public static void removeClassFactory(Utils.ClassFactory classFactory)
  {
    getUserClassFactories().remove(classFactory);
  }
  
  private static ArrayList<Utils.ClassFactory> getUserClassFactories()
  {
    if (userClassFactories == null) {
      userClassFactories = new ArrayList();
    }
    return userClassFactories;
  }
  
  static
  {
    String clazz = SysProperties.JAVA_OBJECT_SERIALIZER;
    if (clazz != null) {
      try
      {
        serializer = (JavaObjectSerializer)loadUserClass(clazz).newInstance();
      }
      catch (Exception e)
      {
        throw DbException.convert(e);
      }
    }
  }
  
  public static Class<?> loadUserClass(String className)
  {
    if (allowedClassNames == null)
    {
      String s = SysProperties.ALLOWED_CLASSES;
      ArrayList<String> prefixes = New.arrayList();
      boolean allowAll = false;
      HashSet<String> classNames = New.hashSet();
      for (String p : StringUtils.arraySplit(s, ',', true)) {
        if (p.equals("*")) {
          allowAll = true;
        } else if (p.endsWith("*")) {
          prefixes.add(p.substring(0, p.length() - 1));
        } else {
          classNames.add(p);
        }
      }
      allowedClassNamePrefixes = new String[prefixes.size()];
      prefixes.toArray(allowedClassNamePrefixes);
      allowAllClasses = allowAll;
      allowedClassNames = classNames;
    }
    if ((!allowAllClasses) && (!allowedClassNames.contains(className)))
    {
      boolean allowed = false;
      for (String s : allowedClassNamePrefixes) {
        if (className.startsWith(s)) {
          allowed = true;
        }
      }
      if (!allowed) {
        throw DbException.get(90134, className);
      }
    }
    for (Utils.ClassFactory classFactory : getUserClassFactories()) {
      if (classFactory.match(className)) {
        try
        {
          Class<?> userClass = classFactory.loadClass(className);
          if (userClass != null) {
            return userClass;
          }
        }
        catch (Exception e)
        {
          throw DbException.get(90086, e, new String[] { className });
        }
      }
    }
    try
    {
      return Class.forName(className);
    }
    catch (ClassNotFoundException e)
    {
      try
      {
        return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
      }
      catch (Exception e2)
      {
        throw DbException.get(90086, e, new String[] { className });
      }
    }
    catch (NoClassDefFoundError e)
    {
      throw DbException.get(90086, e, new String[] { className });
    }
    catch (Error e)
    {
      throw DbException.get(50000, e, new String[] { className });
    }
  }
  
  public static void closeSilently(Statement stat)
  {
    if (stat != null) {
      try
      {
        stat.close();
      }
      catch (SQLException e) {}
    }
  }
  
  public static void closeSilently(Connection conn)
  {
    if (conn != null) {
      try
      {
        conn.close();
      }
      catch (SQLException e) {}
    }
  }
  
  public static void closeSilently(ResultSet rs)
  {
    if (rs != null) {
      try
      {
        rs.close();
      }
      catch (SQLException e) {}
    }
  }
  
  public static Connection getConnection(String driver, String url, String user, String password)
    throws SQLException
  {
    Properties prop = new Properties();
    if (user != null) {
      prop.setProperty("user", user);
    }
    if (password != null) {
      prop.setProperty("password", password);
    }
    return getConnection(driver, url, prop);
  }
  
  public static Connection getConnection(String driver, String url, Properties prop)
    throws SQLException
  {
    if (StringUtils.isNullOrEmpty(driver))
    {
      load(url);
    }
    else
    {
      Class<?> d = loadUserClass(driver);
      if (Driver.class.isAssignableFrom(d)) {
        return DriverManager.getConnection(url, prop);
      }
      if (Context.class.isAssignableFrom(d)) {
        try
        {
          Context context = (Context)d.newInstance();
          DataSource ds = (DataSource)context.lookup(url);
          String user = prop.getProperty("user");
          String password = prop.getProperty("password");
          if ((StringUtils.isNullOrEmpty(user)) && (StringUtils.isNullOrEmpty(password))) {
            return ds.getConnection();
          }
          return ds.getConnection(user, password);
        }
        catch (Exception e)
        {
          throw DbException.toSQLException(e);
        }
      }
      return DriverManager.getConnection(url, prop);
    }
    return DriverManager.getConnection(url, prop);
  }
  
  public static String getDriver(String url)
  {
    if (url.startsWith("jdbc:"))
    {
      url = url.substring("jdbc:".length());
      for (int i = 0; i < DRIVERS.length; i += 2)
      {
        String prefix = DRIVERS[i];
        if (url.startsWith(prefix)) {
          return DRIVERS[(i + 1)];
        }
      }
    }
    return null;
  }
  
  public static void load(String url)
  {
    String driver = getDriver(url);
    if (driver != null) {
      loadUserClass(driver);
    }
  }
  
  public static byte[] serialize(Object obj, DataHandler dataHandler)
  {
    try
    {
      JavaObjectSerializer handlerSerializer = null;
      if (dataHandler != null) {
        handlerSerializer = dataHandler.getJavaObjectSerializer();
      }
      if (handlerSerializer != null) {
        return handlerSerializer.serialize(obj);
      }
      if (serializer != null) {
        return serializer.serialize(obj);
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream os = new ObjectOutputStream(out);
      os.writeObject(obj);
      return out.toByteArray();
    }
    catch (Throwable e)
    {
      throw DbException.get(90026, e, new String[] { e.toString() });
    }
  }
  
  public static Object deserialize(byte[] data, DataHandler dataHandler)
  {
    try
    {
      JavaObjectSerializer dbJavaObjectSerializer = null;
      if (dataHandler != null) {
        dbJavaObjectSerializer = dataHandler.getJavaObjectSerializer();
      }
      if (dbJavaObjectSerializer != null) {
        return dbJavaObjectSerializer.deserialize(data);
      }
      if (serializer != null) {
        return serializer.deserialize(data);
      }
      ByteArrayInputStream in = new ByteArrayInputStream(data);
      ObjectInputStream is;
      ObjectInputStream is;
      if (SysProperties.USE_THREAD_CONTEXT_CLASS_LOADER)
      {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        is = new ObjectInputStream(in)
        {
          protected Class<?> resolveClass(ObjectStreamClass desc)
            throws IOException, ClassNotFoundException
          {
            try
            {
              return Class.forName(desc.getName(), true, loader);
            }
            catch (ClassNotFoundException e) {}
            return super.resolveClass(desc);
          }
        };
      }
      else
      {
        is = new ObjectInputStream(in);
      }
      return is.readObject();
    }
    catch (Throwable e)
    {
      throw DbException.get(90027, e, new String[] { e.toString() });
    }
  }
}
