package org.h2.server.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import org.h2.Driver;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.server.Service;
import org.h2.server.ShutdownHandler;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.NetUtils;
import org.h2.util.New;
import org.h2.util.SortedProperties;
import org.h2.util.StringUtils;
import org.h2.util.Tool;
import org.h2.util.Utils;

public class WebServer
  implements Service
{
  static final String TRANSFER = "transfer";
  static final String[][] LANGUAGES = { { "cs", "Čeština" }, { "de", "Deutsch" }, { "en", "English" }, { "es", "Español" }, { "fr", "Français" }, { "hu", "Magyar" }, { "ko", "한국어" }, { "in", "Indonesia" }, { "it", "Italiano" }, { "ja", "日本語" }, { "nl", "Nederlands" }, { "pl", "Polski" }, { "pt_BR", "Português (Brasil)" }, { "pt_PT", "Português (Europeu)" }, { "ru", "русский" }, { "sk", "Slovensky" }, { "tr", "Türkçe" }, { "uk", "Українська" }, { "zh_CN", "中文 (简体)" }, { "zh_TW", "中文 (繁體)" } };
  private static final String COMMAND_HISTORY = "commandHistory";
  private static final String DEFAULT_LANGUAGE = "en";
  private static final String[] GENERIC = { "Generic JNDI Data Source|javax.naming.InitialContext|java:comp/env/jdbc/Test|sa", "Generic Firebird Server|org.firebirdsql.jdbc.FBDriver|jdbc:firebirdsql:localhost:c:/temp/firebird/test|sysdba", "Generic SQLite|org.sqlite.JDBC|jdbc:sqlite:test|sa", "Generic DB2|COM.ibm.db2.jdbc.net.DB2Driver|jdbc:db2://localhost/test|", "Generic Oracle|oracle.jdbc.driver.OracleDriver|jdbc:oracle:thin:@localhost:1521:XE|sa", "Generic MS SQL Server 2000|com.microsoft.jdbc.sqlserver.SQLServerDriver|jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=sqlexpress|sa", "Generic MS SQL Server 2005|com.microsoft.sqlserver.jdbc.SQLServerDriver|jdbc:sqlserver://localhost;DatabaseName=test|sa", "Generic PostgreSQL|org.postgresql.Driver|jdbc:postgresql:test|", "Generic MySQL|com.mysql.jdbc.Driver|jdbc:mysql://localhost:3306/test|", "Generic HSQLDB|org.hsqldb.jdbcDriver|jdbc:hsqldb:test;hsqldb.default_table_type=cached|sa", "Generic Derby (Server)|org.apache.derby.jdbc.ClientDriver|jdbc:derby://localhost:1527/test;create=true|sa", "Generic Derby (Embedded)|org.apache.derby.jdbc.EmbeddedDriver|jdbc:derby:test;create=true|sa", "Generic H2 (Server)|org.h2.Driver|jdbc:h2:tcp://localhost/~/test|sa", "Generic H2 (Embedded)|org.h2.Driver|jdbc:h2:~/test|sa" };
  private static int ticker;
  private static final long SESSION_TIMEOUT = SysProperties.CONSOLE_TIMEOUT;
  private int port;
  private boolean allowOthers;
  private boolean isDaemon;
  private final Set<WebThread> running;
  private boolean ssl;
  private final HashMap<String, ConnectionInfo> connInfoMap;
  private long lastTimeoutCheck;
  private final HashMap<String, WebSession> sessions;
  private final HashSet<String> languages;
  private String startDateTime;
  private ServerSocket serverSocket;
  private String url;
  private ShutdownHandler shutdownHandler;
  private Thread listenerThread;
  private boolean ifExists;
  private boolean trace;
  private TranslateThread translateThread;
  private boolean allowChunked;
  private String serverPropertiesDir;
  private String commandHistoryString;
  
  public WebServer()
  {
    this.running = Collections.synchronizedSet(new HashSet());
    
    this.connInfoMap = New.hashMap();
    
    this.sessions = New.hashMap();
    this.languages = New.hashSet();
    
    this.allowChunked = true;
    this.serverPropertiesDir = "~";
  }
  
  byte[] getFile(String file)
    throws IOException
  {
    trace("getFile <" + file + ">");
    if ((file.startsWith("transfer/")) && (new File("transfer").exists()))
    {
      file = file.substring("transfer".length() + 1);
      if (!isSimpleName(file)) {
        return null;
      }
      File f = new File("transfer", file);
      if (!f.exists()) {
        return null;
      }
      return IOUtils.readBytesAndClose(new FileInputStream(f), -1);
    }
    byte[] data = Utils.getResource("/org/h2/server/web/res/" + file);
    if (data == null) {
      trace(" null");
    } else {
      trace(" size=" + data.length);
    }
    return data;
  }
  
  static boolean isSimpleName(String s)
  {
    for (char c : s.toCharArray()) {
      if ((c != '.') && (c != '_') && (c != '-') && (!Character.isLetterOrDigit(c))) {
        return false;
      }
    }
    return true;
  }
  
  synchronized void remove(WebThread t)
  {
    this.running.remove(t);
  }
  
  private static String generateSessionId()
  {
    byte[] buff = MathUtils.secureRandomBytes(16);
    return StringUtils.convertBytesToHex(buff);
  }
  
  WebSession getSession(String sessionId)
  {
    long now = System.currentTimeMillis();
    if (this.lastTimeoutCheck + SESSION_TIMEOUT < now)
    {
      for (String id : New.arrayList(this.sessions.keySet()))
      {
        WebSession session = (WebSession)this.sessions.get(id);
        if (session.lastAccess + SESSION_TIMEOUT < now)
        {
          trace("timeout for " + id);
          this.sessions.remove(id);
        }
      }
      this.lastTimeoutCheck = now;
    }
    WebSession session = (WebSession)this.sessions.get(sessionId);
    if (session != null) {
      session.lastAccess = System.currentTimeMillis();
    }
    return session;
  }
  
  WebSession createNewSession(String hostAddr)
  {
    String newId;
    do
    {
      newId = generateSessionId();
    } while (this.sessions.get(newId) != null);
    WebSession session = new WebSession(this);
    session.lastAccess = System.currentTimeMillis();
    session.put("sessionId", newId);
    session.put("ip", hostAddr);
    session.put("language", "en");
    session.put("frame-border", "0");
    session.put("frameset-border", "4");
    this.sessions.put(newId, session);
    
    readTranslations(session, "en");
    return getSession(newId);
  }
  
  String getStartDateTime()
  {
    if (this.startDateTime == null)
    {
      SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", new Locale("en", ""));
      
      format.setTimeZone(TimeZone.getTimeZone("GMT"));
      this.startDateTime = format.format(Long.valueOf(System.currentTimeMillis()));
    }
    return this.startDateTime;
  }
  
  public void init(String... args)
  {
    for (int i = 0; (args != null) && (i < args.length); i++) {
      if ("-properties".equals(args[i])) {
        this.serverPropertiesDir = args[(++i)];
      }
    }
    Properties prop = loadProperties();
    this.port = SortedProperties.getIntProperty(prop, "webPort", 8082);
    
    this.ssl = SortedProperties.getBooleanProperty(prop, "webSSL", false);
    
    this.allowOthers = SortedProperties.getBooleanProperty(prop, "webAllowOthers", false);
    
    this.commandHistoryString = prop.getProperty("commandHistory");
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String a = args[i];
      if (Tool.isOption(a, "-webPort"))
      {
        this.port = Integer.decode(args[(++i)]).intValue();
      }
      else if (Tool.isOption(a, "-webSSL"))
      {
        this.ssl = true;
      }
      else if (Tool.isOption(a, "-webAllowOthers"))
      {
        this.allowOthers = true;
      }
      else if (Tool.isOption(a, "-webDaemon"))
      {
        this.isDaemon = true;
      }
      else if (Tool.isOption(a, "-baseDir"))
      {
        String baseDir = args[(++i)];
        SysProperties.setBaseDir(baseDir);
      }
      else if (Tool.isOption(a, "-ifExists"))
      {
        this.ifExists = true;
      }
      else if (Tool.isOption(a, "-properties"))
      {
        i++;
      }
      else if (Tool.isOption(a, "-trace"))
      {
        this.trace = true;
      }
    }
    for (String[] lang : LANGUAGES) {
      this.languages.add(lang[0]);
    }
    updateURL();
  }
  
  public String getURL()
  {
    updateURL();
    return this.url;
  }
  
  private void updateURL()
  {
    try
    {
      this.url = ((this.ssl ? "https" : "http") + "://" + NetUtils.getLocalAddress() + ":" + this.port);
    }
    catch (NoClassDefFoundError e) {}
  }
  
  public void start()
  {
    this.serverSocket = NetUtils.createServerSocket(this.port, this.ssl);
    this.port = this.serverSocket.getLocalPort();
    updateURL();
  }
  
  public void listen()
  {
    this.listenerThread = Thread.currentThread();
    try
    {
      while (this.serverSocket != null)
      {
        Socket s = this.serverSocket.accept();
        WebThread c = new WebThread(s, this);
        this.running.add(c);
        c.start();
      }
    }
    catch (Exception e)
    {
      trace(e.toString());
    }
  }
  
  public boolean isRunning(boolean traceError)
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
  
  public boolean isStopped()
  {
    return this.serverSocket == null;
  }
  
  public void stop()
  {
    if (this.serverSocket != null)
    {
      try
      {
        this.serverSocket.close();
      }
      catch (IOException e)
      {
        traceError(e);
      }
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
    for (WebSession session : New.arrayList(this.sessions.values())) {
      session.close();
    }
    for (WebThread c : New.arrayList(this.running)) {
      try
      {
        c.stopNow();
        c.join(100);
      }
      catch (Exception e)
      {
        traceError(e);
      }
    }
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
  
  boolean supportsLanguage(String language)
  {
    return this.languages.contains(language);
  }
  
  void readTranslations(WebSession session, String language)
  {
    Properties text = new Properties();
    try
    {
      trace("translation: " + language);
      byte[] trans = getFile("_text_" + language + ".prop");
      trace("  " + new String(trans));
      text = SortedProperties.fromLines(new String(trans, Constants.UTF8));
      for (Map.Entry<Object, Object> entry : text.entrySet())
      {
        String value = (String)entry.getValue();
        if (value.startsWith("#")) {
          entry.setValue(value.substring(1));
        }
      }
    }
    catch (IOException e)
    {
      DbException.traceThrowable(e);
    }
    session.put("text", new HashMap(text));
  }
  
  ArrayList<HashMap<String, Object>> getSessions()
  {
    ArrayList<HashMap<String, Object>> list = New.arrayList();
    for (WebSession s : this.sessions.values()) {
      list.add(s.getInfo());
    }
    return list;
  }
  
  public String getType()
  {
    return "Web Console";
  }
  
  public String getName()
  {
    return "H2 Console Server";
  }
  
  void setAllowOthers(boolean b)
  {
    this.allowOthers = b;
  }
  
  public boolean getAllowOthers()
  {
    return this.allowOthers;
  }
  
  void setSSL(boolean b)
  {
    this.ssl = b;
  }
  
  void setPort(int port)
  {
    this.port = port;
  }
  
  boolean getSSL()
  {
    return this.ssl;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  public boolean isCommandHistoryAllowed()
  {
    return this.commandHistoryString != null;
  }
  
  public void setCommandHistoryAllowed(boolean allowed)
  {
    if (allowed)
    {
      if (this.commandHistoryString == null) {
        this.commandHistoryString = "";
      }
    }
    else {
      this.commandHistoryString = null;
    }
  }
  
  public ArrayList<String> getCommandHistoryList()
  {
    ArrayList<String> result = New.arrayList();
    if (this.commandHistoryString == null) {
      return result;
    }
    StringBuilder sb = new StringBuilder();
    for (int end = 0;; end++) {
      if ((end == this.commandHistoryString.length()) || (this.commandHistoryString.charAt(end) == ';'))
      {
        if (sb.length() > 0)
        {
          result.add(sb.toString());
          sb.delete(0, sb.length());
        }
        if (end == this.commandHistoryString.length()) {
          break;
        }
      }
      else if ((this.commandHistoryString.charAt(end) == '\\') && (end < this.commandHistoryString.length() - 1))
      {
        sb.append(this.commandHistoryString.charAt(++end));
      }
      else
      {
        sb.append(this.commandHistoryString.charAt(end));
      }
    }
    return result;
  }
  
  public void saveCommandHistoryList(ArrayList<String> commandHistory)
  {
    StringBuilder sb = new StringBuilder();
    for (String s : commandHistory)
    {
      if (sb.length() > 0) {
        sb.append(';');
      }
      sb.append(s.replace("\\", "\\\\").replace(";", "\\;"));
    }
    this.commandHistoryString = sb.toString();
    saveProperties(null);
  }
  
  ConnectionInfo getSetting(String name)
  {
    return (ConnectionInfo)this.connInfoMap.get(name);
  }
  
  void updateSetting(ConnectionInfo info)
  {
    this.connInfoMap.put(info.name, info);
    info.lastAccess = (ticker++);
  }
  
  void removeSetting(String name)
  {
    this.connInfoMap.remove(name);
  }
  
  private Properties loadProperties()
  {
    try
    {
      if ("null".equals(this.serverPropertiesDir)) {
        return new Properties();
      }
      return SortedProperties.loadProperties(this.serverPropertiesDir + "/" + ".h2.server.properties");
    }
    catch (Exception e)
    {
      DbException.traceThrowable(e);
    }
    return new Properties();
  }
  
  String[] getSettingNames()
  {
    ArrayList<ConnectionInfo> list = getSettings();
    String[] names = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      names[i] = ((ConnectionInfo)list.get(i)).name;
    }
    return names;
  }
  
  synchronized ArrayList<ConnectionInfo> getSettings()
  {
    ArrayList<ConnectionInfo> settings = New.arrayList();
    if (this.connInfoMap.size() == 0)
    {
      Properties prop = loadProperties();
      if (prop.size() == 0) {
        for (String gen : GENERIC)
        {
          ConnectionInfo info = new ConnectionInfo(gen);
          settings.add(info);
          updateSetting(info);
        }
      } else {
        for (int i = 0;; i++)
        {
          String data = prop.getProperty(String.valueOf(i));
          if (data == null) {
            break;
          }
          ConnectionInfo info = new ConnectionInfo(data);
          settings.add(info);
          updateSetting(info);
        }
      }
    }
    else
    {
      settings.addAll(this.connInfoMap.values());
    }
    Collections.sort(settings);
    return settings;
  }
  
  synchronized void saveProperties(Properties prop)
  {
    try
    {
      if (prop == null)
      {
        Properties old = loadProperties();
        prop = new SortedProperties();
        prop.setProperty("webPort", "" + SortedProperties.getIntProperty(old, "webPort", this.port));
        
        prop.setProperty("webAllowOthers", "" + SortedProperties.getBooleanProperty(old, "webAllowOthers", this.allowOthers));
        
        prop.setProperty("webSSL", "" + SortedProperties.getBooleanProperty(old, "webSSL", this.ssl));
        if (this.commandHistoryString != null) {
          prop.setProperty("commandHistory", this.commandHistoryString);
        }
      }
      ArrayList<ConnectionInfo> settings = getSettings();
      int len = settings.size();
      for (int i = 0; i < len; i++)
      {
        ConnectionInfo info = (ConnectionInfo)settings.get(i);
        if (info != null) {
          prop.setProperty(String.valueOf(len - i - 1), info.getString());
        }
      }
      if (!"null".equals(this.serverPropertiesDir))
      {
        OutputStream out = FileUtils.newOutputStream(this.serverPropertiesDir + "/" + ".h2.server.properties", false);
        
        prop.store(out, "H2 Server Properties");
        out.close();
      }
    }
    catch (Exception e)
    {
      DbException.traceThrowable(e);
    }
  }
  
  Connection getConnection(String driver, String databaseUrl, String user, String password)
    throws SQLException
  {
    driver = driver.trim();
    databaseUrl = databaseUrl.trim();
    Driver.load();
    Properties p = new Properties();
    p.setProperty("user", user.trim());
    
    p.setProperty("password", password);
    if (databaseUrl.startsWith("jdbc:h2:"))
    {
      if (this.ifExists) {
        databaseUrl = databaseUrl + ";IFEXISTS=TRUE";
      }
      return Driver.load().connect(databaseUrl, p);
    }
    return JdbcUtils.getConnection(driver, databaseUrl, p);
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
  
  public String addSession(Connection conn)
    throws SQLException
  {
    WebSession session = createNewSession("local");
    session.setShutdownServerOnDisconnect();
    session.setConnection(conn);
    session.put("url", conn.getMetaData().getURL());
    String s = (String)session.get("sessionId");
    return this.url + "/frame.jsp?jsessionid=" + s;
  }
  
  private class TranslateThread
    extends Thread
  {
    private final File file = new File("translation.properties");
    private final Map<Object, Object> translation;
    private volatile boolean stopNow;
    
    TranslateThread()
    {
      this.translation = translation;
    }
    
    public String getFileName()
    {
      return this.file.getAbsolutePath();
    }
    
    public void stopNow()
    {
      this.stopNow = true;
      try
      {
        join();
      }
      catch (InterruptedException e) {}
    }
    
    public void run()
    {
      while (!this.stopNow) {
        try
        {
          SortedProperties sp = new SortedProperties();
          if (this.file.exists())
          {
            InputStream in = FileUtils.newInputStream(this.file.getName());
            sp.load(in);
            this.translation.putAll(sp);
          }
          else
          {
            OutputStream out = FileUtils.newOutputStream(this.file.getName(), false);
            sp.putAll(this.translation);
            sp.store(out, "Translation");
          }
          Thread.sleep(1000L);
        }
        catch (Exception e)
        {
          WebServer.this.traceError(e);
        }
      }
    }
  }
  
  String startTranslate(Map<Object, Object> translation)
  {
    if (this.translateThread != null) {
      this.translateThread.stopNow();
    }
    this.translateThread = new TranslateThread(translation);
    this.translateThread.setDaemon(true);
    this.translateThread.start();
    return this.translateThread.getFileName();
  }
  
  public boolean isDaemon()
  {
    return this.isDaemon;
  }
  
  void setAllowChunked(boolean allowChunked)
  {
    this.allowChunked = allowChunked;
  }
  
  boolean getAllowChunked()
  {
    return this.allowChunked;
  }
}
