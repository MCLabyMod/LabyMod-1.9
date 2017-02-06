package org.h2.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import org.h2.Driver;
import org.h2.engine.Constants;
import org.h2.server.web.ConnectionInfo;
import org.h2.util.JdbcUtils;
import org.h2.util.New;
import org.h2.util.ScriptReader;
import org.h2.util.SortedProperties;
import org.h2.util.StringUtils;
import org.h2.util.Tool;
import org.h2.util.Utils;

public class Shell
  extends Tool
  implements Runnable
{
  private static final int MAX_ROW_BUFFER = 5000;
  private static final int HISTORY_COUNT = 20;
  private static final char BOX_VERTICAL = '|';
  private PrintStream err = System.err;
  private InputStream in = System.in;
  private BufferedReader reader;
  private Connection conn;
  private Statement stat;
  private boolean listMode;
  private int maxColumnSize = 100;
  private final ArrayList<String> history = New.arrayList();
  private boolean stopHide;
  private String serverPropertiesDir = "~";
  
  public static void main(String... args)
    throws SQLException
  {
    new Shell().runTool(args);
  }
  
  public void setErr(PrintStream err)
  {
    this.err = err;
  }
  
  public void setIn(InputStream in)
  {
    this.in = in;
  }
  
  public void setInReader(BufferedReader reader)
  {
    this.reader = reader;
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String url = null;
    String user = "";
    String password = "";
    String sql = null;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg.equals("-url"))
      {
        url = args[(++i)];
      }
      else if (arg.equals("-user"))
      {
        user = args[(++i)];
      }
      else if (arg.equals("-password"))
      {
        password = args[(++i)];
      }
      else if (arg.equals("-driver"))
      {
        String driver = args[(++i)];
        JdbcUtils.loadUserClass(driver);
      }
      else if (arg.equals("-sql"))
      {
        sql = args[(++i)];
      }
      else if (arg.equals("-properties"))
      {
        this.serverPropertiesDir = args[(++i)];
      }
      else
      {
        if ((arg.equals("-help")) || (arg.equals("-?")))
        {
          showUsage();
          return;
        }
        if (arg.equals("-list")) {
          this.listMode = true;
        } else {
          showUsageAndThrowUnsupportedOption(arg);
        }
      }
    }
    if (url != null)
    {
      Driver.load();
      this.conn = DriverManager.getConnection(url, user, password);
      this.stat = this.conn.createStatement();
    }
    if (sql == null)
    {
      promptLoop();
    }
    else
    {
      ScriptReader r = new ScriptReader(new StringReader(sql));
      for (;;)
      {
        String s = r.readStatement();
        if (s == null) {
          break;
        }
        execute(s);
      }
      if (this.conn != null) {
        this.conn.close();
      }
    }
  }
  
  public void runTool(Connection conn, String... args)
    throws SQLException
  {
    this.conn = conn;
    this.stat = conn.createStatement();
    runTool(args);
  }
  
  private void showHelp()
  {
    println("Commands are case insensitive; SQL statements end with ';'");
    println("help or ?      Display this help");
    println("list           Toggle result list / stack trace mode");
    println("maxwidth       Set maximum column width (default is 100)");
    println("autocommit     Enable or disable autocommit");
    println("history        Show the last 20 statements");
    println("quit or exit   Close the connection and exit");
    println("");
  }
  
  private void promptLoop()
  {
    println("");
    println("Welcome to H2 Shell " + Constants.getFullVersion());
    println("Exit with Ctrl+C");
    if (this.conn != null) {
      showHelp();
    }
    String statement = null;
    if (this.reader == null) {
      this.reader = new BufferedReader(new InputStreamReader(this.in));
    }
    try
    {
      for (;;)
      {
        if (this.conn == null)
        {
          connect();
          showHelp();
        }
        if (statement == null) {
          print("sql> ");
        } else {
          print("...> ");
        }
        String line = readLine();
        if (line == null) {
          break;
        }
        String trimmed = line.trim();
        if (trimmed.length() != 0)
        {
          boolean end = trimmed.endsWith(";");
          if (end)
          {
            line = line.substring(0, line.lastIndexOf(';'));
            trimmed = trimmed.substring(0, trimmed.length() - 1);
          }
          String lower = StringUtils.toLowerEnglish(trimmed);
          if ((!"exit".equals(lower)) && ("quit".equals(lower))) {
            break;
          }
          if (("help".equals(lower)) || ("?".equals(lower)))
          {
            showHelp();
          }
          else if ("list".equals(lower))
          {
            this.listMode = (!this.listMode);
            println("Result list mode is now " + (this.listMode ? "on" : "off"));
          }
          else if ("history".equals(lower))
          {
            int i = 0;
            for (int size = this.history.size(); i < size; i++)
            {
              String s = (String)this.history.get(i);
              s = s.replace('\n', ' ').replace('\r', ' ');
              println("#" + (1 + i) + ": " + s);
            }
            if (this.history.size() > 0) {
              println("To re-run a statement, type the number and press and enter");
            } else {
              println("No history");
            }
          }
          else if (lower.startsWith("autocommit"))
          {
            lower = lower.substring("autocommit".length()).trim();
            if ("true".equals(lower)) {
              this.conn.setAutoCommit(true);
            } else if ("false".equals(lower)) {
              this.conn.setAutoCommit(false);
            } else {
              println("Usage: autocommit [true|false]");
            }
            println("Autocommit is now " + this.conn.getAutoCommit());
          }
          else if (lower.startsWith("maxwidth"))
          {
            lower = lower.substring("maxwidth".length()).trim();
            try
            {
              this.maxColumnSize = Integer.parseInt(lower);
            }
            catch (NumberFormatException e)
            {
              println("Usage: maxwidth <integer value>");
            }
            println("Maximum column width is now " + this.maxColumnSize);
          }
          else
          {
            boolean addToHistory = true;
            if (statement == null)
            {
              if (StringUtils.isNumber(line))
              {
                int pos = Integer.parseInt(line);
                if ((pos == 0) || (pos > this.history.size()))
                {
                  println("Not found");
                }
                else
                {
                  statement = (String)this.history.get(pos - 1);
                  addToHistory = false;
                  println(statement);
                  end = true;
                }
              }
              else
              {
                statement = line;
              }
            }
            else {
              statement = statement + "\n" + line;
            }
            if (end)
            {
              if (addToHistory)
              {
                this.history.add(0, statement);
                if (this.history.size() > 20) {
                  this.history.remove(20);
                }
              }
              execute(statement);
              statement = null;
            }
          }
        }
      }
    }
    catch (SQLException e)
    {
      for (;;)
      {
        println("SQL Exception: " + e.getMessage());
        statement = null;
      }
    }
    catch (IOException e)
    {
      println(e.getMessage());
    }
    catch (Exception e)
    {
      println("Exception: " + e.toString());
      e.printStackTrace(this.err);
    }
    if (this.conn != null) {
      try
      {
        this.conn.close();
        println("Connection closed");
      }
      catch (SQLException e)
      {
        println("SQL Exception: " + e.getMessage());
        e.printStackTrace(this.err);
      }
    }
  }
  
  private void connect()
    throws IOException, SQLException
  {
    String url = "jdbc:h2:~/test";
    String user = "";
    String driver = null;
    try
    {
      Properties prop;
      Properties prop;
      if ("null".equals(this.serverPropertiesDir)) {
        prop = new Properties();
      } else {
        prop = SortedProperties.loadProperties(this.serverPropertiesDir + "/" + ".h2.server.properties");
      }
      String data = null;
      boolean found = false;
      for (int i = 0;; i++)
      {
        String d = prop.getProperty(String.valueOf(i));
        if (d == null) {
          break;
        }
        found = true;
        data = d;
      }
      if (found)
      {
        ConnectionInfo info = new ConnectionInfo(data);
        url = info.url;
        user = info.user;
        driver = info.driver;
      }
    }
    catch (IOException e) {}
    println("[Enter]   " + url);
    print("URL       ");
    url = readLine(url).trim();
    if (driver == null) {
      driver = JdbcUtils.getDriver(url);
    }
    if (driver != null) {
      println("[Enter]   " + driver);
    }
    print("Driver    ");
    driver = readLine(driver).trim();
    println("[Enter]   " + user);
    print("User      ");
    user = readLine(user);
    println("[Enter]   Hide");
    print("Password  ");
    String password = readLine();
    if (password.length() == 0) {
      password = readPassword();
    }
    this.conn = JdbcUtils.getConnection(driver, url, user, password);
    this.stat = this.conn.createStatement();
    println("Connected");
  }
  
  protected void print(String s)
  {
    this.out.print(s);
    this.out.flush();
  }
  
  private void println(String s)
  {
    this.out.println(s);
    this.out.flush();
  }
  
  private String readPassword()
    throws IOException
  {
    try
    {
      Object console = Utils.callStaticMethod("java.lang.System.console", new Object[0]);
      print("Password  ");
      char[] password = (char[])Utils.callMethod(console, "readPassword", new Object[0]);
      return password == null ? null : new String(password);
    }
    catch (Exception e)
    {
      Thread passwordHider = new Thread(this, "Password hider");
      this.stopHide = false;
      passwordHider.start();
      print("Password  > ");
      String p = readLine();
      this.stopHide = true;
      try
      {
        passwordHider.join();
      }
      catch (InterruptedException e) {}
      print("\b\b");
      return p;
    }
  }
  
  public void run()
  {
    while (!this.stopHide)
    {
      print("\b\b><");
      try
      {
        Thread.sleep(10L);
      }
      catch (InterruptedException e) {}
    }
  }
  
  private String readLine(String defaultValue)
    throws IOException
  {
    String s = readLine();
    return s.length() == 0 ? defaultValue : s;
  }
  
  private String readLine()
    throws IOException
  {
    String line = this.reader.readLine();
    if (line == null) {
      throw new IOException("Aborted");
    }
    return line;
  }
  
  private void execute(String sql)
  {
    if (sql.trim().length() == 0) {
      return;
    }
    long time = System.currentTimeMillis();
    try
    {
      ResultSet rs = null;
      try
      {
        if (this.stat.execute(sql))
        {
          rs = this.stat.getResultSet();
          int rowCount = printResult(rs, this.listMode);
          time = System.currentTimeMillis() - time;
          println("(" + rowCount + (rowCount == 1 ? " row, " : " rows, ") + time + " ms)");
        }
        else
        {
          int updateCount = this.stat.getUpdateCount();
          time = System.currentTimeMillis() - time;
          println("(Update count: " + updateCount + ", " + time + " ms)");
        }
      }
      finally
      {
        JdbcUtils.closeSilently(rs);
      }
    }
    catch (SQLException e)
    {
      println("Error: " + e.toString());
      if (this.listMode) {
        e.printStackTrace(this.err);
      }
      return;
    }
  }
  
  private int printResult(ResultSet rs, boolean asList)
    throws SQLException
  {
    if (asList) {
      return printResultAsList(rs);
    }
    return printResultAsTable(rs);
  }
  
  private int printResultAsTable(ResultSet rs)
    throws SQLException
  {
    ResultSetMetaData meta = rs.getMetaData();
    int len = meta.getColumnCount();
    boolean truncated = false;
    ArrayList<String[]> rows = New.arrayList();
    
    String[] columns = new String[len];
    for (int i = 0; i < len; i++)
    {
      String s = meta.getColumnLabel(i + 1);
      columns[i] = (s == null ? "" : s);
    }
    rows.add(columns);
    int rowCount = 0;
    while (rs.next())
    {
      rowCount++;
      truncated |= loadRow(rs, len, rows);
      if (rowCount > 5000)
      {
        printRows(rows, len);
        rows.clear();
      }
    }
    printRows(rows, len);
    rows.clear();
    if (truncated) {
      println("(data is partially truncated)");
    }
    return rowCount;
  }
  
  private boolean loadRow(ResultSet rs, int len, ArrayList<String[]> rows)
    throws SQLException
  {
    boolean truncated = false;
    String[] row = new String[len];
    for (int i = 0; i < len; i++)
    {
      String s = rs.getString(i + 1);
      if (s == null) {
        s = "null";
      }
      if ((len > 1) && (s.length() > this.maxColumnSize))
      {
        s = s.substring(0, this.maxColumnSize);
        truncated = true;
      }
      row[i] = s;
    }
    rows.add(row);
    return truncated;
  }
  
  private int[] printRows(ArrayList<String[]> rows, int len)
  {
    int[] columnSizes = new int[len];
    for (int i = 0; i < len; i++)
    {
      int max = 0;
      for (String[] row : rows) {
        max = Math.max(max, row[i].length());
      }
      if (len > 1) {
        Math.min(this.maxColumnSize, max);
      }
      columnSizes[i] = max;
    }
    for (String[] row : rows)
    {
      StringBuilder buff = new StringBuilder();
      for (int i = 0; i < len; i++)
      {
        if (i > 0) {
          buff.append(' ').append('|').append(' ');
        }
        String s = row[i];
        buff.append(s);
        if (i < len - 1) {
          for (int j = s.length(); j < columnSizes[i]; j++) {
            buff.append(' ');
          }
        }
      }
      println(buff.toString());
    }
    return columnSizes;
  }
  
  private int printResultAsList(ResultSet rs)
    throws SQLException
  {
    ResultSetMetaData meta = rs.getMetaData();
    int longestLabel = 0;
    int len = meta.getColumnCount();
    String[] columns = new String[len];
    for (int i = 0; i < len; i++)
    {
      String s = meta.getColumnLabel(i + 1);
      columns[i] = s;
      longestLabel = Math.max(longestLabel, s.length());
    }
    StringBuilder buff = new StringBuilder();
    int rowCount = 0;
    while (rs.next())
    {
      rowCount++;
      buff.setLength(0);
      if (rowCount > 1) {
        println("");
      }
      for (int i = 0; i < len; i++)
      {
        if (i > 0) {
          buff.append('\n');
        }
        String label = columns[i];
        buff.append(label);
        for (int j = label.length(); j < longestLabel; j++) {
          buff.append(' ');
        }
        buff.append(": ").append(rs.getString(i + 1));
      }
      println(buff.toString());
    }
    if (rowCount == 0)
    {
      for (int i = 0; i < len; i++)
      {
        if (i > 0) {
          buff.append('\n');
        }
        String label = columns[i];
        buff.append(label);
      }
      println(buff.toString());
    }
    return rowCount;
  }
}
