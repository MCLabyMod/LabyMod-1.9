package org.h2.server.pg;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.h2.Driver;
import org.h2.engine.Constants;
import org.h2.message.DbException;
import org.h2.server.Service;
import org.h2.util.NetUtils;
import org.h2.util.New;
import org.h2.util.Tool;

public class PgServer
  implements Service
{
  public static final int DEFAULT_PORT = 5435;
  public static final int PG_TYPE_VARCHAR = 1043;
  public static final int PG_TYPE_INT2VECTOR = 22;
  public static final int PG_TYPE_BOOL = 16;
  public static final int PG_TYPE_BYTEA = 17;
  public static final int PG_TYPE_BPCHAR = 1042;
  public static final int PG_TYPE_INT8 = 20;
  public static final int PG_TYPE_INT2 = 21;
  public static final int PG_TYPE_INT4 = 23;
  public static final int PG_TYPE_TEXT = 25;
  public static final int PG_TYPE_OID = 26;
  public static final int PG_TYPE_FLOAT4 = 700;
  public static final int PG_TYPE_FLOAT8 = 701;
  public static final int PG_TYPE_UNKNOWN = 705;
  public static final int PG_TYPE_TEXTARRAY = 1009;
  public static final int PG_TYPE_DATE = 1082;
  public static final int PG_TYPE_TIME = 1083;
  public static final int PG_TYPE_TIMESTAMP_NO_TMZONE = 1114;
  public static final int PG_TYPE_NUMERIC = 1700;
  private final HashSet<Integer> typeSet = New.hashSet();
  private int port = 5435;
  private boolean portIsSet;
  private boolean stop;
  private boolean trace;
  private ServerSocket serverSocket;
  private final Set<PgServerThread> running = Collections.synchronizedSet(new HashSet());
  private final AtomicInteger pid = new AtomicInteger();
  private String baseDir;
  private boolean allowOthers;
  private boolean isDaemon;
  private boolean ifExists;
  private String key;
  private String keyDatabase;
  
  public void init(String... args)
  {
    this.port = 5435;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String a = args[i];
      if (Tool.isOption(a, "-trace"))
      {
        this.trace = true;
      }
      else if (Tool.isOption(a, "-pgPort"))
      {
        this.port = Integer.decode(args[(++i)]).intValue();
        this.portIsSet = true;
      }
      else if (Tool.isOption(a, "-baseDir"))
      {
        this.baseDir = args[(++i)];
      }
      else if (Tool.isOption(a, "-pgAllowOthers"))
      {
        this.allowOthers = true;
      }
      else if (Tool.isOption(a, "-pgDaemon"))
      {
        this.isDaemon = true;
      }
      else if (Tool.isOption(a, "-ifExists"))
      {
        this.ifExists = true;
      }
      else if (Tool.isOption(a, "-key"))
      {
        this.key = args[(++i)];
        this.keyDatabase = args[(++i)];
      }
    }
    Driver.load();
  }
  
  boolean getTrace()
  {
    return this.trace;
  }
  
  void trace(String s)
  {
    if (this.trace) {
      System.out.println(s);
    }
  }
  
  synchronized void remove(PgServerThread t)
  {
    this.running.remove(t);
  }
  
  void traceError(Exception e)
  {
    if (this.trace) {
      e.printStackTrace();
    }
  }
  
  public String getURL()
  {
    return "pg://" + NetUtils.getLocalAddress() + ":" + this.port;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  private boolean allow(Socket socket)
  {
    if (this.allowOthers) {
      return true;
    }
    try
    {
      return NetUtils.isLocalAddress(socket);
    }
    catch (UnknownHostException e)
    {
      traceError(e);
    }
    return false;
  }
  
  public void start()
  {
    this.stop = false;
    try
    {
      this.serverSocket = NetUtils.createServerSocket(this.port, false);
    }
    catch (DbException e)
    {
      if (!this.portIsSet) {
        this.serverSocket = NetUtils.createServerSocket(0, false);
      } else {
        throw e;
      }
    }
    this.port = this.serverSocket.getLocalPort();
  }
  
  public void listen()
  {
    String threadName = Thread.currentThread().getName();
    try
    {
      while (!this.stop)
      {
        Socket s = this.serverSocket.accept();
        if (!allow(s))
        {
          trace("Connection not allowed");
          s.close();
        }
        else
        {
          PgServerThread c = new PgServerThread(s, this);
          this.running.add(c);
          c.setProcessId(this.pid.incrementAndGet());
          Thread thread = new Thread(c, threadName + " thread");
          thread.setDaemon(this.isDaemon);
          c.setThread(thread);
          thread.start();
        }
      }
    }
    catch (Exception e)
    {
      if (!this.stop) {
        e.printStackTrace();
      }
    }
  }
  
  public void stop()
  {
    if (!this.stop)
    {
      this.stop = true;
      if (this.serverSocket != null)
      {
        try
        {
          this.serverSocket.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        this.serverSocket = null;
      }
    }
    for (PgServerThread c : New.arrayList(this.running))
    {
      c.close();
      try
      {
        Thread t = c.getThread();
        if (t != null) {
          t.join(100L);
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public boolean isRunning(boolean traceError)
  {
    if (this.serverSocket == null) {
      return false;
    }
    try
    {
      Socket s = NetUtils.createLoopbackSocket(this.serverSocket.getLocalPort(), false);
      s.close();
      return true;
    }
    catch (Exception e)
    {
      if (traceError) {
        traceError(e);
      }
    }
    return false;
  }
  
  PgServerThread getThread(int processId)
  {
    for (PgServerThread c : New.arrayList(this.running)) {
      if (c.getProcessId() == processId) {
        return c;
      }
    }
    return null;
  }
  
  String getBaseDir()
  {
    return this.baseDir;
  }
  
  public boolean getAllowOthers()
  {
    return this.allowOthers;
  }
  
  public String getType()
  {
    return "PG";
  }
  
  public String getName()
  {
    return "H2 PG Server";
  }
  
  boolean getIfExists()
  {
    return this.ifExists;
  }
  
  public static String getIndexColumn(Connection conn, int indexId, Integer ordinalPosition, Boolean pretty)
    throws SQLException
  {
    if ((ordinalPosition == null) || (ordinalPosition.intValue() == 0))
    {
      PreparedStatement prep = conn.prepareStatement("select sql from information_schema.indexes where id=?");
      
      prep.setInt(1, indexId);
      ResultSet rs = prep.executeQuery();
      if (rs.next()) {
        return rs.getString(1);
      }
      return "";
    }
    PreparedStatement prep = conn.prepareStatement("select column_name from information_schema.indexes where id=? and ordinal_position=?");
    
    prep.setInt(1, indexId);
    prep.setInt(2, ordinalPosition.intValue());
    ResultSet rs = prep.executeQuery();
    if (rs.next()) {
      return rs.getString(1);
    }
    return "";
  }
  
  public static String getCurrentSchema(Connection conn)
    throws SQLException
  {
    ResultSet rs = conn.createStatement().executeQuery("call schema()");
    rs.next();
    return rs.getString(1);
  }
  
  public static int getOid(Connection conn, String tableName)
    throws SQLException
  {
    if ((tableName.startsWith("\"")) && (tableName.endsWith("\""))) {
      tableName = tableName.substring(1, tableName.length() - 1);
    }
    PreparedStatement prep = conn.prepareStatement("select oid from pg_class where relName = ?");
    
    prep.setString(1, tableName);
    ResultSet rs = prep.executeQuery();
    if (!rs.next()) {
      return 0;
    }
    return rs.getInt(1);
  }
  
  public static String getEncodingName(int code)
  {
    switch (code)
    {
    case 0: 
      return "SQL_ASCII";
    case 6: 
      return "UTF8";
    case 8: 
      return "LATIN1";
    }
    return code < 40 ? "UTF8" : "";
  }
  
  public static String getVersion()
  {
    return "PostgreSQL 8.1.4  server protocol using H2 " + Constants.getFullVersion();
  }
  
  public static Timestamp getStartTime()
  {
    return new Timestamp(System.currentTimeMillis());
  }
  
  public static String getUserById(Connection conn, int id)
    throws SQLException
  {
    PreparedStatement prep = conn.prepareStatement("SELECT NAME FROM INFORMATION_SCHEMA.USERS WHERE ID=?");
    
    prep.setInt(1, id);
    ResultSet rs = prep.executeQuery();
    if (rs.next()) {
      return rs.getString(1);
    }
    return null;
  }
  
  public static boolean hasDatabasePrivilege(int id, String privilege)
  {
    return true;
  }
  
  public static boolean hasTablePrivilege(String table, String privilege)
  {
    return true;
  }
  
  public static int getCurrentTid(String table, String id)
  {
    return 1;
  }
  
  public static String getPgExpr(String exprText, int relationOid)
  {
    return null;
  }
  
  public static String formatType(Connection conn, int pgType, int typeMod)
    throws SQLException
  {
    PreparedStatement prep = conn.prepareStatement("select typname from pg_catalog.pg_type where oid = ? and typtypmod = ?");
    
    prep.setInt(1, pgType);
    prep.setInt(2, typeMod);
    ResultSet rs = prep.executeQuery();
    if (rs.next()) {
      return rs.getString(1);
    }
    return null;
  }
  
  public static int convertType(int type)
  {
    switch (type)
    {
    case 16: 
      return 16;
    case 12: 
      return 1043;
    case 2005: 
      return 25;
    case 1: 
      return 1042;
    case 5: 
      return 21;
    case 4: 
      return 23;
    case -5: 
      return 20;
    case 3: 
      return 1700;
    case 7: 
      return 700;
    case 8: 
      return 701;
    case 92: 
      return 1083;
    case 91: 
      return 1082;
    case 93: 
      return 1114;
    case -3: 
      return 17;
    case 2004: 
      return 26;
    case 2003: 
      return 1009;
    }
    return 705;
  }
  
  HashSet<Integer> getTypeSet()
  {
    return this.typeSet;
  }
  
  void checkType(int type)
  {
    if (!this.typeSet.contains(Integer.valueOf(type))) {
      trace("Unsupported type: " + type);
    }
  }
  
  public String checkKeyAndGetDatabaseName(String db)
  {
    if (this.key == null) {
      return db;
    }
    if (this.key.equals(db)) {
      return this.keyDatabase;
    }
    throw DbException.get(28000);
  }
  
  public boolean isDaemon()
  {
    return this.isDaemon;
  }
}
