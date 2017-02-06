package org.h2.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.h2.Driver;
import org.h2.message.DbException;
import org.h2.util.JdbcUtils;
import org.h2.util.NetUtils;
import org.h2.util.New;
import org.h2.util.Tool;

public class TcpServer
  implements Service
{
  private static final int SHUTDOWN_NORMAL = 0;
  private static final int SHUTDOWN_FORCE = 1;
  private static final String MANAGEMENT_DB_PREFIX = "management_db_";
  private static final Map<Integer, TcpServer> SERVERS = Collections.synchronizedMap(new HashMap());
  private int port;
  private boolean portIsSet;
  private boolean trace;
  private boolean ssl;
  private boolean stop;
  private ShutdownHandler shutdownHandler;
  private ServerSocket serverSocket;
  private final Set<TcpServerThread> running = Collections.synchronizedSet(new HashSet());
  private String baseDir;
  private boolean allowOthers;
  private boolean isDaemon;
  private boolean ifExists;
  private Connection managementDb;
  private PreparedStatement managementDbAdd;
  private PreparedStatement managementDbRemove;
  private String managementPassword = "";
  private Thread listenerThread;
  private int nextThreadId;
  private String key;
  private String keyDatabase;
  
  public static String getManagementDbName(int port)
  {
    return "mem:management_db_" + port;
  }
  
  private void initManagementDb()
    throws SQLException
  {
    Properties prop = new Properties();
    prop.setProperty("user", "");
    prop.setProperty("password", this.managementPassword);
    
    Connection conn = Driver.load().connect("jdbc:h2:" + getManagementDbName(this.port), prop);
    
    this.managementDb = conn;
    Statement stat = null;
    try
    {
      stat = conn.createStatement();
      stat.execute("CREATE ALIAS IF NOT EXISTS STOP_SERVER FOR \"" + TcpServer.class.getName() + ".stopServer\"");
      
      stat.execute("CREATE TABLE IF NOT EXISTS SESSIONS(ID INT PRIMARY KEY, URL VARCHAR, USER VARCHAR, CONNECTED TIMESTAMP)");
      
      this.managementDbAdd = conn.prepareStatement("INSERT INTO SESSIONS VALUES(?, ?, ?, NOW())");
      
      this.managementDbRemove = conn.prepareStatement("DELETE FROM SESSIONS WHERE ID=?");
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
    }
    SERVERS.put(Integer.valueOf(this.port), this);
  }
  
  void shutdown()
  {
    if (this.shutdownHandler != null) {
      this.shutdownHandler.shutdown();
    }
  }
  
  public void setShutdownHandler(ShutdownHandler shutdownHandler)
  {
    this.shutdownHandler = shutdownHandler;
  }
  
  synchronized void addConnection(int id, String url, String user)
  {
    try
    {
      this.managementDbAdd.setInt(1, id);
      this.managementDbAdd.setString(2, url);
      this.managementDbAdd.setString(3, user);
      this.managementDbAdd.execute();
    }
    catch (SQLException e)
    {
      DbException.traceThrowable(e);
    }
  }
  
  synchronized void removeConnection(int id)
  {
    try
    {
      this.managementDbRemove.setInt(1, id);
      this.managementDbRemove.execute();
    }
    catch (SQLException e)
    {
      DbException.traceThrowable(e);
    }
  }
  
  private synchronized void stopManagementDb()
  {
    if (this.managementDb != null)
    {
      try
      {
        this.managementDb.close();
      }
      catch (SQLException e)
      {
        DbException.traceThrowable(e);
      }
      this.managementDb = null;
    }
  }
  
  public void init(String... args)
  {
    this.port = 9092;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String a = args[i];
      if (Tool.isOption(a, "-trace"))
      {
        this.trace = true;
      }
      else if (Tool.isOption(a, "-tcpSSL"))
      {
        this.ssl = true;
      }
      else if (Tool.isOption(a, "-tcpPort"))
      {
        this.port = Integer.decode(args[(++i)]).intValue();
        this.portIsSet = true;
      }
      else if (Tool.isOption(a, "-tcpPassword"))
      {
        this.managementPassword = args[(++i)];
      }
      else if (Tool.isOption(a, "-baseDir"))
      {
        this.baseDir = args[(++i)];
      }
      else if (Tool.isOption(a, "-key"))
      {
        this.key = args[(++i)];
        this.keyDatabase = args[(++i)];
      }
      else if (Tool.isOption(a, "-tcpAllowOthers"))
      {
        this.allowOthers = true;
      }
      else if (Tool.isOption(a, "-tcpDaemon"))
      {
        this.isDaemon = true;
      }
      else if (Tool.isOption(a, "-ifExists"))
      {
        this.ifExists = true;
      }
    }
    Driver.load();
  }
  
  public String getURL()
  {
    return (this.ssl ? "ssl" : "tcp") + "://" + NetUtils.getLocalAddress() + ":" + this.port;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  boolean allow(Socket socket)
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
  
  public synchronized void start()
    throws SQLException
  {
    this.stop = false;
    try
    {
      this.serverSocket = NetUtils.createServerSocket(this.port, this.ssl);
    }
    catch (DbException e)
    {
      if (!this.portIsSet) {
        this.serverSocket = NetUtils.createServerSocket(0, this.ssl);
      } else {
        throw e;
      }
    }
    this.port = this.serverSocket.getLocalPort();
    initManagementDb();
  }
  
  public void listen()
  {
    this.listenerThread = Thread.currentThread();
    String threadName = this.listenerThread.getName();
    try
    {
      while (!this.stop)
      {
        Socket s = this.serverSocket.accept();
        TcpServerThread c = new TcpServerThread(s, this, this.nextThreadId++);
        this.running.add(c);
        Thread thread = new Thread(c, threadName + " thread");
        thread.setDaemon(this.isDaemon);
        c.setThread(thread);
        thread.start();
      }
      this.serverSocket = NetUtils.closeSilently(this.serverSocket);
    }
    catch (Exception e)
    {
      if (!this.stop) {
        DbException.traceThrowable(e);
      }
    }
    stopManagementDb();
  }
  
  public synchronized boolean isRunning(boolean traceError)
  {
    if (this.serverSocket == null) {
      return false;
    }
    try
    {
      Socket s = NetUtils.createLoopbackSocket(this.port, this.ssl);
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
  
  public void stop()
  {
    SERVERS.remove(Integer.valueOf(this.port));
    if (!this.stop)
    {
      stopManagementDb();
      this.stop = true;
      if (this.serverSocket != null)
      {
        try
        {
          this.serverSocket.close();
        }
        catch (IOException e)
        {
          DbException.traceThrowable(e);
        }
        catch (NullPointerException e) {}
        this.serverSocket = null;
      }
      if (this.listenerThread != null) {
        try
        {
          this.listenerThread.join(1000L);
        }
        catch (InterruptedException e)
        {
          DbException.traceThrowable(e);
        }
      }
    }
    for (TcpServerThread c : New.arrayList(this.running)) {
      if (c != null)
      {
        c.close();
        try
        {
          c.getThread().join(100L);
        }
        catch (Exception e)
        {
          DbException.traceThrowable(e);
        }
      }
    }
  }
  
  public static void stopServer(int port, String password, int shutdownMode)
  {
    if (port == 0)
    {
      Integer[] arr$ = (Integer[])SERVERS.keySet().toArray(new Integer[0]);int len$ = arr$.length;
      for (int i$ = 0; i$ < len$; i$++)
      {
        int p = arr$[i$].intValue();
        if (p != 0) {
          stopServer(p, password, shutdownMode);
        }
      }
      return;
    }
    TcpServer server = (TcpServer)SERVERS.get(Integer.valueOf(port));
    if (server == null) {
      return;
    }
    if (!server.managementPassword.equals(password)) {
      return;
    }
    if (shutdownMode == 0)
    {
      server.stopManagementDb();
      server.stop = true;
      try
      {
        Socket s = NetUtils.createLoopbackSocket(port, false);
        s.close();
      }
      catch (Exception e) {}
    }
    else if (shutdownMode == 1)
    {
      server.stop();
    }
    server.shutdown();
  }
  
  void remove(TcpServerThread t)
  {
    this.running.remove(t);
  }
  
  String getBaseDir()
  {
    return this.baseDir;
  }
  
  void trace(String s)
  {
    if (this.trace) {
      System.out.println(s);
    }
  }
  
  void traceError(Throwable e)
  {
    if (this.trace) {
      e.printStackTrace();
    }
  }
  
  public boolean getAllowOthers()
  {
    return this.allowOthers;
  }
  
  public String getType()
  {
    return "TCP";
  }
  
  public String getName()
  {
    return "H2 TCP Server";
  }
  
  boolean getIfExists()
  {
    return this.ifExists;
  }
  
  /* Error */
  public static synchronized void shutdown(String url, String password, boolean force, boolean all)
    throws SQLException
  {
    // Byte code:
    //   0: sipush 9092
    //   3: istore 4
    //   5: aload_0
    //   6: bipush 58
    //   8: invokevirtual 496	java/lang/String:lastIndexOf	(I)I
    //   11: istore 5
    //   13: iload 5
    //   15: iflt +31 -> 46
    //   18: aload_0
    //   19: iload 5
    //   21: iconst_1
    //   22: iadd
    //   23: invokevirtual 499	java/lang/String:substring	(I)Ljava/lang/String;
    //   26: astore 6
    //   28: aload 6
    //   30: invokestatic 504	org/h2/util/StringUtils:isNumber	(Ljava/lang/String;)Z
    //   33: ifeq +13 -> 46
    //   36: aload 6
    //   38: invokestatic 244	java/lang/Integer:decode	(Ljava/lang/String;)Ljava/lang/Integer;
    //   41: invokevirtual 248	java/lang/Integer:intValue	()I
    //   44: istore 4
    //   46: iload 4
    //   48: invokestatic 112	org/h2/server/TcpServer:getManagementDbName	(I)Ljava/lang/String;
    //   51: astore 6
    //   53: invokestatic 106	org/h2/Driver:load	()Lorg/h2/Driver;
    //   56: pop
    //   57: goto +11 -> 68
    //   60: astore 7
    //   62: aload 7
    //   64: invokestatic 508	org/h2/message/DbException:convert	(Ljava/lang/Throwable;)Lorg/h2/message/DbException;
    //   67: athrow
    //   68: iconst_0
    //   69: istore 7
    //   71: iload 7
    //   73: iconst_2
    //   74: if_icmpge +195 -> 269
    //   77: aconst_null
    //   78: astore 8
    //   80: aconst_null
    //   81: astore 9
    //   83: new 72	java/lang/StringBuilder
    //   86: dup
    //   87: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   90: ldc 108
    //   92: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload_0
    //   96: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: ldc_w 510
    //   102: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload 6
    //   107: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: ldc 64
    //   115: aload_1
    //   116: invokestatic 516	java/sql/DriverManager:getConnection	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
    //   119: astore 8
    //   121: aload 8
    //   123: ldc_w 518
    //   126: invokeinterface 147 2 0
    //   131: astore 9
    //   133: aload 9
    //   135: iconst_1
    //   136: iload_3
    //   137: ifeq +7 -> 144
    //   140: iconst_0
    //   141: goto +5 -> 146
    //   144: iload 4
    //   146: invokeinterface 197 3 0
    //   151: aload 9
    //   153: iconst_2
    //   154: aload_1
    //   155: invokeinterface 201 3 0
    //   160: aload 9
    //   162: iconst_3
    //   163: iload_2
    //   164: ifeq +7 -> 171
    //   167: iconst_1
    //   168: goto +4 -> 172
    //   171: iconst_0
    //   172: invokeinterface 197 3 0
    //   177: aload 9
    //   179: invokeinterface 204 1 0
    //   184: pop
    //   185: goto +26 -> 211
    //   188: astore 10
    //   190: iload_2
    //   191: ifeq +6 -> 197
    //   194: goto +17 -> 211
    //   197: aload 10
    //   199: invokevirtual 521	java/sql/SQLException:getErrorCode	()I
    //   202: ldc_w 522
    //   205: if_icmpeq +6 -> 211
    //   208: aload 10
    //   210: athrow
    //   211: aload 9
    //   213: invokestatic 159	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   216: aload 8
    //   218: invokestatic 525	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Connection;)V
    //   221: goto +48 -> 269
    //   224: astore 10
    //   226: iload 7
    //   228: iconst_1
    //   229: if_icmpne +6 -> 235
    //   232: aload 10
    //   234: athrow
    //   235: aload 9
    //   237: invokestatic 159	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   240: aload 8
    //   242: invokestatic 525	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Connection;)V
    //   245: goto +18 -> 263
    //   248: astore 11
    //   250: aload 9
    //   252: invokestatic 159	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   255: aload 8
    //   257: invokestatic 525	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Connection;)V
    //   260: aload 11
    //   262: athrow
    //   263: iinc 7 1
    //   266: goto -195 -> 71
    //   269: goto +11 -> 280
    //   272: astore 4
    //   274: aload 4
    //   276: invokestatic 529	org/h2/message/DbException:toSQLException	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   279: athrow
    //   280: return
    // Line number table:
    //   Java source line #433	-> byte code offset #0
    //   Java source line #434	-> byte code offset #5
    //   Java source line #435	-> byte code offset #13
    //   Java source line #436	-> byte code offset #18
    //   Java source line #437	-> byte code offset #28
    //   Java source line #438	-> byte code offset #36
    //   Java source line #441	-> byte code offset #46
    //   Java source line #443	-> byte code offset #53
    //   Java source line #446	-> byte code offset #57
    //   Java source line #444	-> byte code offset #60
    //   Java source line #445	-> byte code offset #62
    //   Java source line #447	-> byte code offset #68
    //   Java source line #448	-> byte code offset #77
    //   Java source line #449	-> byte code offset #80
    //   Java source line #451	-> byte code offset #83
    //   Java source line #452	-> byte code offset #121
    //   Java source line #453	-> byte code offset #133
    //   Java source line #454	-> byte code offset #151
    //   Java source line #455	-> byte code offset #160
    //   Java source line #457	-> byte code offset #177
    //   Java source line #466	-> byte code offset #185
    //   Java source line #458	-> byte code offset #188
    //   Java source line #459	-> byte code offset #190
    //   Java source line #462	-> byte code offset #197
    //   Java source line #463	-> byte code offset #208
    //   Java source line #473	-> byte code offset #211
    //   Java source line #474	-> byte code offset #216
    //   Java source line #468	-> byte code offset #224
    //   Java source line #469	-> byte code offset #226
    //   Java source line #470	-> byte code offset #232
    //   Java source line #473	-> byte code offset #235
    //   Java source line #474	-> byte code offset #240
    //   Java source line #475	-> byte code offset #245
    //   Java source line #473	-> byte code offset #248
    //   Java source line #474	-> byte code offset #255
    //   Java source line #447	-> byte code offset #263
    //   Java source line #479	-> byte code offset #269
    //   Java source line #477	-> byte code offset #272
    //   Java source line #478	-> byte code offset #274
    //   Java source line #480	-> byte code offset #280
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	281	0	url	String
    //   0	281	1	password	String
    //   0	281	2	force	boolean
    //   0	281	3	all	boolean
    //   3	142	4	port	int
    //   272	3	4	e	Exception
    //   11	9	5	idx	int
    //   26	11	6	p	String
    //   51	55	6	db	String
    //   60	3	7	e	Throwable
    //   69	195	7	i	int
    //   78	178	8	conn	Connection
    //   81	170	9	prep	PreparedStatement
    //   188	21	10	e	SQLException
    //   224	9	10	e	SQLException
    //   248	13	11	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   53	57	60	java/lang/Throwable
    //   177	185	188	java/sql/SQLException
    //   83	211	224	java/sql/SQLException
    //   83	211	248	finally
    //   224	235	248	finally
    //   248	250	248	finally
    //   0	269	272	java/lang/Exception
  }
  
  void cancelStatement(String sessionId, int statementId)
  {
    for (TcpServerThread c : New.arrayList(this.running)) {
      if (c != null) {
        c.cancelStatement(sessionId, statementId);
      }
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
