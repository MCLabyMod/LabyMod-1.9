package org.h2.tools;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.server.Service;
import org.h2.server.ShutdownHandler;
import org.h2.server.TcpServer;
import org.h2.server.pg.PgServer;
import org.h2.server.web.WebServer;
import org.h2.util.StringUtils;
import org.h2.util.Tool;
import org.h2.util.Utils;

public class Server
  extends Tool
  implements Runnable, ShutdownHandler
{
  private final Service service;
  private Server web;
  private Server tcp;
  private Server pg;
  private ShutdownHandler shutdownHandler;
  private boolean started;
  
  public Server()
  {
    this.service = null;
  }
  
  public Server(Service service, String... args)
    throws SQLException
  {
    verifyArgs(args);
    this.service = service;
    try
    {
      service.init(args);
    }
    catch (Exception e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  public static void main(String... args)
    throws SQLException
  {
    new Server().runTool(args);
  }
  
  private void verifyArgs(String... args)
    throws SQLException
  {
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg != null) {
        if ((!"-?".equals(arg)) && (!"-help".equals(arg))) {
          if (arg.startsWith("-web"))
          {
            if (!"-web".equals(arg)) {
              if (!"-webAllowOthers".equals(arg)) {
                if (!"-webDaemon".equals(arg)) {
                  if (!"-webSSL".equals(arg)) {
                    if ("-webPort".equals(arg)) {
                      i++;
                    } else {
                      throwUnsupportedOption(arg);
                    }
                  }
                }
              }
            }
          }
          else if (!"-browser".equals(arg)) {
            if (arg.startsWith("-tcp"))
            {
              if (!"-tcp".equals(arg)) {
                if (!"-tcpAllowOthers".equals(arg)) {
                  if (!"-tcpDaemon".equals(arg)) {
                    if (!"-tcpSSL".equals(arg)) {
                      if ("-tcpPort".equals(arg)) {
                        i++;
                      } else if ("-tcpPassword".equals(arg)) {
                        i++;
                      } else if ("-tcpShutdown".equals(arg)) {
                        i++;
                      } else if (!"-tcpShutdownForce".equals(arg)) {
                        throwUnsupportedOption(arg);
                      }
                    }
                  }
                }
              }
            }
            else if (arg.startsWith("-pg"))
            {
              if (!"-pg".equals(arg)) {
                if (!"-pgAllowOthers".equals(arg)) {
                  if (!"-pgDaemon".equals(arg)) {
                    if ("-pgPort".equals(arg)) {
                      i++;
                    } else {
                      throwUnsupportedOption(arg);
                    }
                  }
                }
              }
            }
            else if (arg.startsWith("-ftp"))
            {
              if ("-ftpPort".equals(arg)) {
                i++;
              } else if ("-ftpDir".equals(arg)) {
                i++;
              } else if ("-ftpRead".equals(arg)) {
                i++;
              } else if ("-ftpWrite".equals(arg)) {
                i++;
              } else if ("-ftpWritePassword".equals(arg)) {
                i++;
              } else if (!"-ftpTask".equals(arg)) {
                throwUnsupportedOption(arg);
              }
            }
            else if ("-properties".equals(arg)) {
              i++;
            } else if (!"-trace".equals(arg)) {
              if (!"-ifExists".equals(arg)) {
                if ("-baseDir".equals(arg)) {
                  i++;
                } else if ("-key".equals(arg)) {
                  i += 2;
                } else if (!"-tool".equals(arg)) {
                  throwUnsupportedOption(arg);
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    boolean tcpStart = false;boolean pgStart = false;boolean webStart = false;
    boolean browserStart = false;
    boolean tcpShutdown = false;boolean tcpShutdownForce = false;
    String tcpPassword = "";
    String tcpShutdownServer = "";
    boolean startDefaultServers = true;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg != null)
      {
        if (("-?".equals(arg)) || ("-help".equals(arg)))
        {
          showUsage();
          return;
        }
        if (arg.startsWith("-web"))
        {
          if ("-web".equals(arg))
          {
            startDefaultServers = false;
            webStart = true;
          }
          else if (!"-webAllowOthers".equals(arg))
          {
            if (!"-webDaemon".equals(arg)) {
              if (!"-webSSL".equals(arg)) {
                if ("-webPort".equals(arg)) {
                  i++;
                } else {
                  showUsageAndThrowUnsupportedOption(arg);
                }
              }
            }
          }
        }
        else if ("-browser".equals(arg))
        {
          startDefaultServers = false;
          browserStart = true;
        }
        else if (arg.startsWith("-tcp"))
        {
          if ("-tcp".equals(arg))
          {
            startDefaultServers = false;
            tcpStart = true;
          }
          else if (!"-tcpAllowOthers".equals(arg))
          {
            if (!"-tcpDaemon".equals(arg)) {
              if (!"-tcpSSL".equals(arg)) {
                if ("-tcpPort".equals(arg))
                {
                  i++;
                }
                else if ("-tcpPassword".equals(arg))
                {
                  tcpPassword = args[(++i)];
                }
                else if ("-tcpShutdown".equals(arg))
                {
                  startDefaultServers = false;
                  tcpShutdown = true;
                  tcpShutdownServer = args[(++i)];
                }
                else if ("-tcpShutdownForce".equals(arg))
                {
                  tcpShutdownForce = true;
                }
                else
                {
                  showUsageAndThrowUnsupportedOption(arg);
                }
              }
            }
          }
        }
        else if (arg.startsWith("-pg"))
        {
          if ("-pg".equals(arg))
          {
            startDefaultServers = false;
            pgStart = true;
          }
          else if (!"-pgAllowOthers".equals(arg))
          {
            if (!"-pgDaemon".equals(arg)) {
              if ("-pgPort".equals(arg)) {
                i++;
              } else {
                showUsageAndThrowUnsupportedOption(arg);
              }
            }
          }
        }
        else if ("-properties".equals(arg))
        {
          i++;
        }
        else if (!"-trace".equals(arg))
        {
          if (!"-ifExists".equals(arg)) {
            if ("-baseDir".equals(arg)) {
              i++;
            } else if ("-key".equals(arg)) {
              i += 2;
            } else {
              showUsageAndThrowUnsupportedOption(arg);
            }
          }
        }
      }
    }
    verifyArgs(args);
    if (startDefaultServers)
    {
      tcpStart = true;
      pgStart = true;
      webStart = true;
      browserStart = true;
    }
    if (tcpShutdown)
    {
      this.out.println("Shutting down TCP Server at " + tcpShutdownServer);
      shutdownTcpServer(tcpShutdownServer, tcpPassword, tcpShutdownForce, false);
    }
    try
    {
      if (tcpStart)
      {
        this.tcp = createTcpServer(args);
        this.tcp.start();
        this.out.println(this.tcp.getStatus());
        this.tcp.setShutdownHandler(this);
      }
      if (pgStart)
      {
        this.pg = createPgServer(args);
        this.pg.start();
        this.out.println(this.pg.getStatus());
      }
      if (webStart)
      {
        this.web = createWebServer(args);
        this.web.setShutdownHandler(this);
        SQLException result = null;
        try
        {
          this.web.start();
        }
        catch (Exception e)
        {
          result = DbException.toSQLException(e);
        }
        this.out.println(this.web.getStatus());
        if (browserStart) {
          try
          {
            openBrowser(this.web.getURL());
          }
          catch (Exception e)
          {
            this.out.println(e.getMessage());
          }
        }
        if (result != null) {
          throw result;
        }
      }
      else if (browserStart)
      {
        this.out.println("The browser can only start if a web server is started (-web)");
      }
    }
    catch (SQLException e)
    {
      stopAll();
      throw e;
    }
  }
  
  public static void shutdownTcpServer(String url, String password, boolean force, boolean all)
    throws SQLException
  {
    TcpServer.shutdown(url, password, force, all);
  }
  
  public String getStatus()
  {
    StringBuilder buff = new StringBuilder();
    if (!this.started)
    {
      buff.append("Not started");
    }
    else if (isRunning(false))
    {
      buff.append(this.service.getType()).append(" server running at ").append(this.service.getURL()).append(" (");
      if (this.service.getAllowOthers()) {
        buff.append("others can connect");
      } else {
        buff.append("only local connections");
      }
      buff.append(')');
    }
    else
    {
      buff.append("The ").append(this.service.getType()).append(" server could not be started. Possible cause: another server is already running at ").append(this.service.getURL());
    }
    return buff.toString();
  }
  
  public static Server createWebServer(String... args)
    throws SQLException
  {
    WebServer service = new WebServer();
    Server server = new Server(service, args);
    service.setShutdownHandler(server);
    return server;
  }
  
  public static Server createTcpServer(String... args)
    throws SQLException
  {
    TcpServer service = new TcpServer();
    Server server = new Server(service, args);
    service.setShutdownHandler(server);
    return server;
  }
  
  public static Server createPgServer(String... args)
    throws SQLException
  {
    return new Server(new PgServer(), args);
  }
  
  public Server start()
    throws SQLException
  {
    try
    {
      this.started = true;
      this.service.start();
      String name = this.service.getName() + " (" + this.service.getURL() + ")";
      Thread t = new Thread(this, name);
      t.setDaemon(this.service.isDaemon());
      t.start();
      for (int i = 1; i < 64; i += i)
      {
        wait(i);
        if (isRunning(false)) {
          return this;
        }
      }
      if (isRunning(true)) {
        return this;
      }
      throw DbException.get(90061, new String[] { name, "timeout; please check your network configuration, specially the file /etc/hosts" });
    }
    catch (DbException e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  private static void wait(int i)
  {
    try
    {
      long sleep = i * i;
      Thread.sleep(sleep);
    }
    catch (InterruptedException e) {}
  }
  
  private void stopAll()
  {
    Server s = this.web;
    if ((s != null) && (s.isRunning(false)))
    {
      s.stop();
      this.web = null;
    }
    s = this.tcp;
    if ((s != null) && (s.isRunning(false)))
    {
      s.stop();
      this.tcp = null;
    }
    s = this.pg;
    if ((s != null) && (s.isRunning(false)))
    {
      s.stop();
      this.pg = null;
    }
  }
  
  public boolean isRunning(boolean traceError)
  {
    return this.service.isRunning(traceError);
  }
  
  public void stop()
  {
    this.started = false;
    if (this.service != null) {
      this.service.stop();
    }
  }
  
  public String getURL()
  {
    return this.service.getURL();
  }
  
  public int getPort()
  {
    return this.service.getPort();
  }
  
  public void run()
  {
    try
    {
      this.service.listen();
    }
    catch (Exception e)
    {
      DbException.traceThrowable(e);
    }
  }
  
  public void setShutdownHandler(ShutdownHandler shutdownHandler)
  {
    this.shutdownHandler = shutdownHandler;
  }
  
  public void shutdown()
  {
    if (this.shutdownHandler != null) {
      this.shutdownHandler.shutdown();
    } else {
      stopAll();
    }
  }
  
  public Service getService()
  {
    return this.service;
  }
  
  public static void openBrowser(String url)
    throws Exception
  {
    try
    {
      String osName = StringUtils.toLowerEnglish(Utils.getProperty("os.name", "linux"));
      
      Runtime rt = Runtime.getRuntime();
      String browser = Utils.getProperty("h2.browser", null);
      if (browser == null) {
        try
        {
          browser = System.getenv("BROWSER");
        }
        catch (SecurityException se) {}
      }
      if (browser != null)
      {
        if (browser.startsWith("call:"))
        {
          browser = browser.substring("call:".length());
          Utils.callStaticMethod(browser, new Object[] { url });
        }
        else if (browser.contains("%url"))
        {
          String[] args = StringUtils.arraySplit(browser, ',', false);
          for (int i = 0; i < args.length; i++) {
            args[i] = StringUtils.replaceAll(args[i], "%url", url);
          }
          rt.exec(args);
        }
        else if (osName.contains("windows"))
        {
          rt.exec(new String[] { "cmd.exe", "/C", browser, url });
        }
        else
        {
          rt.exec(new String[] { browser, url });
        }
        return;
      }
      try
      {
        Class<?> desktopClass = Class.forName("java.awt.Desktop");
        
        Boolean supported = (Boolean)desktopClass.getMethod("isDesktopSupported", new Class[0]).invoke(null, new Object[0]);
        
        URI uri = new URI(url);
        if (supported.booleanValue())
        {
          Object desktop = desktopClass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
          
          desktopClass.getMethod("browse", new Class[] { URI.class }).invoke(desktop, new Object[] { uri });
          
          return;
        }
      }
      catch (Exception e) {}
      if (osName.contains("windows"))
      {
        rt.exec(new String[] { "rundll32", "url.dll,FileProtocolHandler", url });
      }
      else if ((osName.contains("mac")) || (osName.contains("darwin")))
      {
        Runtime.getRuntime().exec(new String[] { "open", url });
      }
      else
      {
        String[] browsers = { "chromium", "google-chrome", "firefox", "mozilla-firefox", "mozilla", "konqueror", "netscape", "opera", "midori" };
        
        boolean ok = false;
        for (String b : browsers) {
          try
          {
            rt.exec(new String[] { b, url });
            ok = true;
          }
          catch (Exception e) {}
        }
        if (!ok) {
          throw new Exception("Browser detection failed and system property h2.browser not set");
        }
      }
    }
    catch (Exception e)
    {
      throw new Exception("Failed to start a browser to open the URL " + url + ": " + e.getMessage());
    }
  }
  
  public static void startWebServer(Connection conn)
    throws SQLException
  {
    WebServer webServer = new WebServer();
    Server web = new Server(webServer, new String[] { "-webPort", "0" });
    web.start();
    Server server = new Server();
    server.web = web;
    webServer.setShutdownHandler(server);
    String url = webServer.addSession(conn);
    try
    {
      openBrowser(url);
      while (!webServer.isStopped()) {
        Thread.sleep(1000L);
      }
    }
    catch (Exception e) {}
  }
}
