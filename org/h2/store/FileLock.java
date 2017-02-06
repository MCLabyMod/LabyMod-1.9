package org.h2.store;

import java.io.IOException;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import org.h2.Driver;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceSystem;
import org.h2.store.fs.FileUtils;
import org.h2.util.MathUtils;
import org.h2.util.NetUtils;
import org.h2.util.SortedProperties;
import org.h2.util.StringUtils;
import org.h2.value.Transfer;

public class FileLock
  implements Runnable
{
  public static final int LOCK_NO = 0;
  public static final int LOCK_FILE = 1;
  public static final int LOCK_SOCKET = 2;
  public static final int LOCK_SERIALIZED = 3;
  public static final int LOCK_FS = 4;
  private static final String MAGIC = "FileLock";
  private static final String FILE = "file";
  private static final String SOCKET = "socket";
  private static final String SERIALIZED = "serialized";
  private static final int RANDOM_BYTES = 16;
  private static final int SLEEP_GAP = 25;
  private static final int TIME_GRANULARITY = 2000;
  private volatile String fileName;
  private volatile ServerSocket serverSocket;
  private volatile boolean locked;
  private final int sleep;
  private final Trace trace;
  private long lastWrite;
  private String method;
  private String ipAddress;
  private Properties properties;
  private String uniqueId;
  private Thread watchdog;
  
  public FileLock(TraceSystem traceSystem, String fileName, int sleep)
  {
    this.trace = (traceSystem == null ? null : traceSystem.getTrace("fileLock"));
    
    this.fileName = fileName;
    this.sleep = sleep;
  }
  
  public synchronized void lock(int fileLockMethod)
  {
    checkServer();
    if (this.locked) {
      DbException.throwInternalError("already locked");
    }
    switch (fileLockMethod)
    {
    case 1: 
      lockFile();
      break;
    case 2: 
      lockSocket();
      break;
    case 3: 
      lockSerialized();
      break;
    }
    this.locked = true;
  }
  
  public synchronized void unlock()
  {
    if (!this.locked) {
      return;
    }
    this.locked = false;
    try
    {
      if (this.watchdog != null) {
        this.watchdog.interrupt();
      }
    }
    catch (Exception e)
    {
      this.trace.debug(e, "unlock");
    }
    try
    {
      if ((this.fileName != null) && 
        (load().equals(this.properties))) {
        FileUtils.delete(this.fileName);
      }
      if (this.serverSocket != null) {
        this.serverSocket.close();
      }
    }
    catch (Exception e)
    {
      this.trace.debug(e, "unlock");
    }
    finally
    {
      this.fileName = null;
      this.serverSocket = null;
    }
    try
    {
      if (this.watchdog != null) {
        this.watchdog.join();
      }
    }
    catch (Exception e)
    {
      this.trace.debug(e, "unlock");
    }
    finally
    {
      this.watchdog = null;
    }
  }
  
  public void setProperty(String key, String value)
  {
    if (value == null) {
      this.properties.remove(key);
    } else {
      this.properties.put(key, value);
    }
  }
  
  public Properties save()
  {
    try
    {
      OutputStream out = FileUtils.newOutputStream(this.fileName, false);
      try
      {
        this.properties.store(out, "FileLock");
      }
      finally
      {
        out.close();
      }
      this.lastWrite = FileUtils.lastModified(this.fileName);
      if (this.trace.isDebugEnabled()) {
        this.trace.debug("save " + this.properties);
      }
      return this.properties;
    }
    catch (IOException e)
    {
      throw getExceptionFatal("Could not save properties " + this.fileName, e);
    }
  }
  
  private void checkServer()
  {
    Properties prop = load();
    String server = prop.getProperty("server");
    if (server == null) {
      return;
    }
    boolean running = false;
    String id = prop.getProperty("id");
    try
    {
      Socket socket = NetUtils.createSocket(server, 9092, false);
      
      Transfer transfer = new Transfer(null);
      transfer.setSocket(socket);
      transfer.init();
      transfer.writeInt(6);
      transfer.writeInt(15);
      transfer.writeString(null);
      transfer.writeString(null);
      transfer.writeString(id);
      transfer.writeInt(14);
      transfer.flush();
      int state = transfer.readInt();
      if (state == 1) {
        running = true;
      }
      transfer.close();
      socket.close();
    }
    catch (IOException e)
    {
      return;
    }
    if (running)
    {
      DbException e = DbException.get(90020, "Server is running");
      
      throw e.addSQL(server + "/" + id);
    }
  }
  
  public Properties load()
  {
    IOException lastException = null;
    for (int i = 0; i < 5; i++) {
      try
      {
        Properties p2 = SortedProperties.loadProperties(this.fileName);
        if (this.trace.isDebugEnabled()) {
          this.trace.debug("load " + p2);
        }
        return p2;
      }
      catch (IOException e)
      {
        lastException = e;
      }
    }
    throw getExceptionFatal("Could not load properties " + this.fileName, lastException);
  }
  
  private void waitUntilOld()
  {
    for (int i = 0; i < 160; i++)
    {
      long last = FileUtils.lastModified(this.fileName);
      long dist = System.currentTimeMillis() - last;
      if (dist < -2000L)
      {
        try
        {
          Thread.sleep(2L * this.sleep);
        }
        catch (Exception e)
        {
          this.trace.debug(e, "sleep");
        }
        return;
      }
      if (dist > 2000L) {
        return;
      }
      try
      {
        Thread.sleep(25L);
      }
      catch (Exception e)
      {
        this.trace.debug(e, "sleep");
      }
    }
    throw getExceptionFatal("Lock file recently modified", null);
  }
  
  private void setUniqueId()
  {
    byte[] bytes = MathUtils.secureRandomBytes(16);
    String random = StringUtils.convertBytesToHex(bytes);
    this.uniqueId = (Long.toHexString(System.currentTimeMillis()) + random);
    this.properties.setProperty("id", this.uniqueId);
  }
  
  private void lockSerialized()
  {
    this.method = "serialized";
    FileUtils.createDirectories(FileUtils.getParent(this.fileName));
    if (FileUtils.createFile(this.fileName))
    {
      this.properties = new SortedProperties();
      this.properties.setProperty("method", String.valueOf(this.method));
      setUniqueId();
      save();
    }
    else
    {
      try
      {
        this.properties = load();
      }
      catch (DbException e) {}
      return;
    }
  }
  
  private void lockFile()
  {
    this.method = "file";
    this.properties = new SortedProperties();
    this.properties.setProperty("method", String.valueOf(this.method));
    setUniqueId();
    FileUtils.createDirectories(FileUtils.getParent(this.fileName));
    if (!FileUtils.createFile(this.fileName))
    {
      waitUntilOld();
      String m2 = load().getProperty("method", "file");
      if (!m2.equals("file")) {
        throw getExceptionFatal("Unsupported lock method " + m2, null);
      }
      save();
      sleep(2 * this.sleep);
      if (!load().equals(this.properties)) {
        throw getExceptionAlreadyInUse("Locked by another process");
      }
      FileUtils.delete(this.fileName);
      if (!FileUtils.createFile(this.fileName)) {
        throw getExceptionFatal("Another process was faster", null);
      }
    }
    save();
    sleep(25);
    if (!load().equals(this.properties))
    {
      this.fileName = null;
      throw getExceptionFatal("Concurrent update", null);
    }
    this.watchdog = new Thread(this, "H2 File Lock Watchdog " + this.fileName);
    Driver.setThreadContextClassLoader(this.watchdog);
    this.watchdog.setDaemon(true);
    this.watchdog.setPriority(9);
    this.watchdog.start();
  }
  
  private void lockSocket()
  {
    this.method = "socket";
    this.properties = new SortedProperties();
    this.properties.setProperty("method", String.valueOf(this.method));
    setUniqueId();
    
    this.ipAddress = NetUtils.getLocalAddress();
    FileUtils.createDirectories(FileUtils.getParent(this.fileName));
    if (!FileUtils.createFile(this.fileName))
    {
      waitUntilOld();
      long read = FileUtils.lastModified(this.fileName);
      Properties p2 = load();
      String m2 = p2.getProperty("method", "socket");
      if (m2.equals("file"))
      {
        lockFile();
        return;
      }
      if (!m2.equals("socket")) {
        throw getExceptionFatal("Unsupported lock method " + m2, null);
      }
      String ip = p2.getProperty("ipAddress", this.ipAddress);
      if (!this.ipAddress.equals(ip)) {
        throw getExceptionAlreadyInUse("Locked by another computer: " + ip);
      }
      String port = p2.getProperty("port", "0");
      int portId = Integer.parseInt(port);
      InetAddress address;
      try
      {
        address = InetAddress.getByName(ip);
      }
      catch (UnknownHostException e)
      {
        throw getExceptionFatal("Unknown host " + ip, e);
      }
      for (int i = 0; i < 3; i++) {
        try
        {
          Socket s = new Socket(address, portId);
          s.close();
          throw getExceptionAlreadyInUse("Locked by another process");
        }
        catch (BindException e)
        {
          throw getExceptionFatal("Bind Exception", null);
        }
        catch (ConnectException e)
        {
          this.trace.debug(e, "socket not connected to port " + port);
        }
        catch (IOException e)
        {
          throw getExceptionFatal("IOException", null);
        }
      }
      if (read != FileUtils.lastModified(this.fileName)) {
        throw getExceptionFatal("Concurrent update", null);
      }
      FileUtils.delete(this.fileName);
      if (!FileUtils.createFile(this.fileName)) {
        throw getExceptionFatal("Another process was faster", null);
      }
    }
    try
    {
      this.serverSocket = NetUtils.createServerSocket(0, false);
      int port = this.serverSocket.getLocalPort();
      this.properties.setProperty("ipAddress", this.ipAddress);
      this.properties.setProperty("port", String.valueOf(port));
    }
    catch (Exception e)
    {
      this.trace.debug(e, "lock");
      this.serverSocket = null;
      lockFile();
      return;
    }
    save();
    this.watchdog = new Thread(this, "H2 File Lock Watchdog (Socket) " + this.fileName);
    
    this.watchdog.setDaemon(true);
    this.watchdog.start();
  }
  
  private static void sleep(int time)
  {
    try
    {
      Thread.sleep(time);
    }
    catch (InterruptedException e)
    {
      throw getExceptionFatal("Sleep interrupted", e);
    }
  }
  
  private static DbException getExceptionFatal(String reason, Throwable t)
  {
    return DbException.get(8000, t, new String[] { reason });
  }
  
  private DbException getExceptionAlreadyInUse(String reason)
  {
    DbException e = DbException.get(90020, reason);
    if (this.fileName != null) {
      try
      {
        Properties prop = load();
        String server = prop.getProperty("server");
        if (server != null)
        {
          String serverId = server + "/" + prop.getProperty("id");
          e = e.addSQL(serverId);
        }
      }
      catch (DbException e2) {}
    }
    return e;
  }
  
  public static int getFileLockMethod(String method)
  {
    if ((method == null) || (method.equalsIgnoreCase("FILE"))) {
      return 1;
    }
    if (method.equalsIgnoreCase("NO")) {
      return 0;
    }
    if (method.equalsIgnoreCase("SOCKET")) {
      return 2;
    }
    if (method.equalsIgnoreCase("SERIALIZED")) {
      return 3;
    }
    if (method.equalsIgnoreCase("FS")) {
      return 4;
    }
    throw DbException.get(90060, method);
  }
  
  public String getUniqueId()
  {
    return this.uniqueId;
  }
  
  public void run()
  {
    try
    {
      while ((this.locked) && (this.fileName != null)) {
        try
        {
          if ((!FileUtils.exists(this.fileName)) || (FileUtils.lastModified(this.fileName) != this.lastWrite)) {
            save();
          }
          Thread.sleep(this.sleep);
        }
        catch (OutOfMemoryError e) {}catch (InterruptedException e) {}catch (NullPointerException e) {}catch (Exception e)
        {
          this.trace.debug(e, "watchdog");
        }
      }
      while (this.serverSocket != null) {
        try
        {
          this.trace.debug("watchdog accept");
          Socket s = this.serverSocket.accept();
          s.close();
        }
        catch (Exception e)
        {
          this.trace.debug(e, "watchdog");
        }
      }
    }
    catch (Exception e)
    {
      this.trace.debug(e, "watchdog");
    }
    this.trace.debug("watchdog end");
  }
}
