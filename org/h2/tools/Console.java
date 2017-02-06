package org.h2.tools;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import org.h2.server.ShutdownHandler;
import org.h2.util.JdbcUtils;
import org.h2.util.Tool;
import org.h2.util.Utils;

public class Console
  extends Tool
  implements ActionListener, MouseListener, WindowListener, ShutdownHandler
{
  private Frame frame;
  private boolean trayIconUsed;
  private Font font;
  private Button startBrowser;
  private TextField urlText;
  private Object tray;
  private Object trayIcon;
  private Server web;
  private Server tcp;
  private Server pg;
  private boolean isWindows;
  private long lastOpen;
  
  public static void main(String... args)
    throws SQLException
  {
    new Console().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    this.isWindows = Utils.getProperty("os.name", "").startsWith("Windows");
    boolean tcpStart = false;boolean pgStart = false;boolean webStart = false;boolean toolStart = false;
    boolean browserStart = false;
    boolean startDefaultServers = true;
    boolean printStatus = (args != null) && (args.length > 0);
    String driver = null;String url = null;String user = null;String password = null;
    boolean tcpShutdown = false;boolean tcpShutdownForce = false;
    String tcpPassword = "";
    String tcpShutdownServer = "";
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
        if ("-url".equals(arg))
        {
          startDefaultServers = false;
          url = args[(++i)];
        }
        else if ("-driver".equals(arg))
        {
          driver = args[(++i)];
        }
        else if ("-user".equals(arg))
        {
          user = args[(++i)];
        }
        else if ("-password".equals(arg))
        {
          password = args[(++i)];
        }
        else if (arg.startsWith("-web"))
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
        else if ("-tool".equals(arg))
        {
          startDefaultServers = false;
          webStart = true;
          toolStart = true;
        }
        else if ("-browser".equals(arg))
        {
          startDefaultServers = false;
          webStart = true;
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
            } else {
              showUsageAndThrowUnsupportedOption(arg);
            }
          }
        }
      }
    }
    if (startDefaultServers)
    {
      webStart = true;
      toolStart = true;
      browserStart = true;
      tcpStart = true;
      pgStart = true;
    }
    if (tcpShutdown)
    {
      this.out.println("Shutting down TCP Server at " + tcpShutdownServer);
      Server.shutdownTcpServer(tcpShutdownServer, tcpPassword, tcpShutdownForce, false);
    }
    SQLException startException = null;
    boolean webRunning = false;
    if (url != null)
    {
      Connection conn = JdbcUtils.getConnection(driver, url, user, password);
      Server.startWebServer(conn);
    }
    if (webStart) {
      try
      {
        this.web = Server.createWebServer(args);
        this.web.setShutdownHandler(this);
        this.web.start();
        if (printStatus) {
          this.out.println(this.web.getStatus());
        }
        webRunning = true;
      }
      catch (SQLException e)
      {
        printProblem(e, this.web);
        startException = e;
      }
    }
    if ((toolStart) && (webRunning) && (!GraphicsEnvironment.isHeadless()))
    {
      loadFont();
      try
      {
        if (!createTrayIcon()) {
          showWindow();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    if ((browserStart) && (this.web != null)) {
      openBrowser(this.web.getURL());
    }
    if (tcpStart) {
      try
      {
        this.tcp = Server.createTcpServer(args);
        this.tcp.start();
        if (printStatus) {
          this.out.println(this.tcp.getStatus());
        }
        this.tcp.setShutdownHandler(this);
      }
      catch (SQLException e)
      {
        printProblem(e, this.tcp);
        if (startException == null) {
          startException = e;
        }
      }
    }
    if (pgStart) {
      try
      {
        this.pg = Server.createPgServer(args);
        this.pg.start();
        if (printStatus) {
          this.out.println(this.pg.getStatus());
        }
      }
      catch (SQLException e)
      {
        printProblem(e, this.pg);
        if (startException == null) {
          startException = e;
        }
      }
    }
    if (startException != null)
    {
      shutdown();
      throw startException;
    }
  }
  
  private void printProblem(Exception e, Server server)
  {
    if (server == null)
    {
      e.printStackTrace();
    }
    else
    {
      this.out.println(server.getStatus());
      this.out.println("Root cause: " + e.getMessage());
    }
  }
  
  private static Image loadImage(String name)
  {
    try
    {
      byte[] imageData = Utils.getResource(name);
      if (imageData == null) {
        return null;
      }
      return Toolkit.getDefaultToolkit().createImage(imageData);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public void shutdown()
  {
    if ((this.web != null) && (this.web.isRunning(false)))
    {
      this.web.stop();
      this.web = null;
    }
    if ((this.tcp != null) && (this.tcp.isRunning(false)))
    {
      this.tcp.stop();
      this.tcp = null;
    }
    if ((this.pg != null) && (this.pg.isRunning(false)))
    {
      this.pg.stop();
      this.pg = null;
    }
    if (this.frame != null)
    {
      this.frame.dispose();
      this.frame = null;
    }
    if (this.trayIconUsed)
    {
      try
      {
        Utils.callMethod(this.tray, "remove", new Object[] { this.trayIcon });
      }
      catch (Exception e) {}finally
      {
        this.trayIcon = null;
        this.tray = null;
        this.trayIconUsed = false;
      }
      System.gc();
    }
  }
  
  private void loadFont()
  {
    if (this.isWindows) {
      this.font = new Font("Dialog", 0, 11);
    } else {
      this.font = new Font("Dialog", 0, 12);
    }
  }
  
  private boolean createTrayIcon()
  {
    try
    {
      boolean supported = ((Boolean)Utils.callStaticMethod("java.awt.SystemTray.isSupported", new Object[0])).booleanValue();
      if (!supported) {
        return false;
      }
      PopupMenu menuConsole = new PopupMenu();
      MenuItem itemConsole = new MenuItem("H2 Console");
      itemConsole.setActionCommand("console");
      itemConsole.addActionListener(this);
      itemConsole.setFont(this.font);
      menuConsole.add(itemConsole);
      MenuItem itemStatus = new MenuItem("Status");
      itemStatus.setActionCommand("status");
      itemStatus.addActionListener(this);
      itemStatus.setFont(this.font);
      menuConsole.add(itemStatus);
      MenuItem itemExit = new MenuItem("Exit");
      itemExit.setFont(this.font);
      itemExit.setActionCommand("exit");
      itemExit.addActionListener(this);
      menuConsole.add(itemExit);
      
      this.tray = Utils.callStaticMethod("java.awt.SystemTray.getSystemTray", new Object[0]);
      
      Dimension d = (Dimension)Utils.callMethod(this.tray, "getTrayIconSize", new Object[0]);
      String iconFile;
      String iconFile;
      if ((d.width >= 24) && (d.height >= 24))
      {
        iconFile = "/org/h2/res/h2-24.png";
      }
      else
      {
        String iconFile;
        if ((d.width >= 22) && (d.height >= 22)) {
          iconFile = "/org/h2/res/h2-64-t.png";
        } else {
          iconFile = "/org/h2/res/h2.png";
        }
      }
      Image icon = loadImage(iconFile);
      
      this.trayIcon = Utils.newInstance("java.awt.TrayIcon", new Object[] { icon, "H2 Database Engine", menuConsole });
      
      Utils.callMethod(this.trayIcon, "addMouseListener", new Object[] { this });
      
      Utils.callMethod(this.tray, "add", new Object[] { this.trayIcon });
      
      this.trayIconUsed = true;
      
      return true;
    }
    catch (Exception e) {}
    return false;
  }
  
  private void showWindow()
  {
    if (this.frame != null) {
      return;
    }
    this.frame = new Frame("H2 Console");
    this.frame.addWindowListener(this);
    Image image = loadImage("/org/h2/res/h2.png");
    if (image != null) {
      this.frame.setIconImage(image);
    }
    this.frame.setResizable(false);
    this.frame.setBackground(SystemColor.control);
    
    GridBagLayout layout = new GridBagLayout();
    this.frame.setLayout(layout);
    
    Panel mainPanel = new Panel(layout);
    
    GridBagConstraints constraintsPanel = new GridBagConstraints();
    constraintsPanel.gridx = 0;
    constraintsPanel.weightx = 1.0D;
    constraintsPanel.weighty = 1.0D;
    constraintsPanel.fill = 1;
    constraintsPanel.insets = new Insets(0, 10, 0, 10);
    constraintsPanel.gridy = 0;
    
    GridBagConstraints constraintsButton = new GridBagConstraints();
    constraintsButton.gridx = 0;
    constraintsButton.gridwidth = 2;
    constraintsButton.insets = new Insets(10, 0, 0, 0);
    constraintsButton.gridy = 1;
    constraintsButton.anchor = 13;
    
    GridBagConstraints constraintsTextField = new GridBagConstraints();
    constraintsTextField.fill = 2;
    constraintsTextField.gridy = 0;
    constraintsTextField.weightx = 1.0D;
    constraintsTextField.insets = new Insets(0, 5, 0, 0);
    constraintsTextField.gridx = 1;
    
    GridBagConstraints constraintsLabel = new GridBagConstraints();
    constraintsLabel.gridx = 0;
    constraintsLabel.gridy = 0;
    
    Label label = new Label("H2 Console URL:", 0);
    label.setFont(this.font);
    mainPanel.add(label, constraintsLabel);
    
    this.urlText = new TextField();
    this.urlText.setEditable(false);
    this.urlText.setFont(this.font);
    this.urlText.setText(this.web.getURL());
    if (this.isWindows) {
      this.urlText.setFocusable(false);
    }
    mainPanel.add(this.urlText, constraintsTextField);
    
    this.startBrowser = new Button("Start Browser");
    this.startBrowser.setFocusable(false);
    this.startBrowser.setActionCommand("console");
    this.startBrowser.addActionListener(this);
    this.startBrowser.setFont(this.font);
    mainPanel.add(this.startBrowser, constraintsButton);
    this.frame.add(mainPanel, constraintsPanel);
    
    int width = 300;int height = 120;
    this.frame.setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.frame.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
    try
    {
      this.frame.setVisible(true);
    }
    catch (Throwable t) {}
    try
    {
      this.frame.setAlwaysOnTop(true);
      this.frame.setAlwaysOnTop(false);
    }
    catch (Throwable t) {}
  }
  
  private void startBrowser()
  {
    if (this.web != null)
    {
      String url = this.web.getURL();
      if (this.urlText != null) {
        this.urlText.setText(url);
      }
      long now = System.currentTimeMillis();
      if ((this.lastOpen == 0L) || (this.lastOpen + 100L < now))
      {
        this.lastOpen = now;
        openBrowser(url);
      }
    }
  }
  
  private void openBrowser(String url)
  {
    try
    {
      Server.openBrowser(url);
    }
    catch (Exception e)
    {
      this.out.println(e.getMessage());
    }
  }
  
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if ("exit".equals(command)) {
      shutdown();
    } else if ("console".equals(command)) {
      startBrowser();
    } else if ("status".equals(command)) {
      showWindow();
    } else if (this.startBrowser == e.getSource()) {
      startBrowser();
    }
  }
  
  public void mouseClicked(MouseEvent e)
  {
    if (e.getButton() == 1) {
      startBrowser();
    }
  }
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public void mousePressed(MouseEvent e) {}
  
  public void mouseReleased(MouseEvent e) {}
  
  public void windowClosing(WindowEvent e)
  {
    if (this.trayIconUsed)
    {
      this.frame.dispose();
      this.frame = null;
    }
    else
    {
      shutdown();
    }
  }
  
  public void windowActivated(WindowEvent e) {}
  
  public void windowClosed(WindowEvent e) {}
  
  public void windowDeactivated(WindowEvent e) {}
  
  public void windowDeiconified(WindowEvent e) {}
  
  public void windowIconified(WindowEvent e) {}
  
  public void windowOpened(WindowEvent e) {}
}
