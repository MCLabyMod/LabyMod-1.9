package org.h2.tools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.Driver;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.ScriptReader;
import org.h2.util.StringUtils;
import org.h2.util.Tool;

public class RunScript
  extends Tool
{
  private boolean showResults;
  private boolean checkResults;
  
  public static void main(String... args)
    throws SQLException
  {
    new RunScript().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String url = null;
    String user = "";
    String password = "";
    String script = "backup.sql";
    String options = null;
    boolean continueOnError = false;
    boolean showTime = false;
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
      else if (arg.equals("-continueOnError"))
      {
        continueOnError = true;
      }
      else if (arg.equals("-checkResults"))
      {
        this.checkResults = true;
      }
      else if (arg.equals("-showResults"))
      {
        this.showResults = true;
      }
      else if (arg.equals("-script"))
      {
        script = args[(++i)];
      }
      else if (arg.equals("-time"))
      {
        showTime = true;
      }
      else if (arg.equals("-driver"))
      {
        String driver = args[(++i)];
        JdbcUtils.loadUserClass(driver);
      }
      else if (arg.equals("-options"))
      {
        StringBuilder buff = new StringBuilder();
        i++;
        for (; i < args.length; i++) {
          buff.append(' ').append(args[i]);
        }
        options = buff.toString();
      }
      else
      {
        if ((arg.equals("-help")) || (arg.equals("-?")))
        {
          showUsage();
          return;
        }
        showUsageAndThrowUnsupportedOption(arg);
      }
    }
    if (url == null)
    {
      showUsage();
      throw new SQLException("URL not set");
    }
    long time = System.currentTimeMillis();
    if (options != null) {
      processRunscript(url, user, password, script, options);
    } else {
      process(url, user, password, script, null, continueOnError);
    }
    if (showTime)
    {
      time = System.currentTimeMillis() - time;
      this.out.println("Done in " + time + " ms");
    }
  }
  
  public static ResultSet execute(Connection conn, Reader reader)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    ResultSet rs = null;
    ScriptReader r = new ScriptReader(reader);
    for (;;)
    {
      String sql = r.readStatement();
      if (sql == null) {
        break;
      }
      if (sql.trim().length() != 0)
      {
        boolean resultSet = stat.execute(sql);
        if (resultSet)
        {
          if (rs != null)
          {
            rs.close();
            rs = null;
          }
          rs = stat.getResultSet();
        }
      }
    }
    return rs;
  }
  
  private void process(Connection conn, String fileName, boolean continueOnError, Charset charset)
    throws SQLException, IOException
  {
    InputStream in = FileUtils.newInputStream(fileName);
    String path = FileUtils.getParent(fileName);
    try
    {
      in = new BufferedInputStream(in, 4096);
      Reader reader = new InputStreamReader(in, charset);
      process(conn, continueOnError, path, reader, charset);
    }
    finally
    {
      IOUtils.closeSilently(in);
    }
  }
  
  private void process(Connection conn, boolean continueOnError, String path, Reader reader, Charset charset)
    throws SQLException, IOException
  {
    Statement stat = conn.createStatement();
    ScriptReader r = new ScriptReader(reader);
    for (;;)
    {
      String sql = r.readStatement();
      if (sql == null) {
        break;
      }
      String trim = sql.trim();
      if (trim.length() != 0) {
        if ((trim.startsWith("@")) && (StringUtils.toUpperEnglish(trim).startsWith("@INCLUDE")))
        {
          sql = trim;
          sql = sql.substring("@INCLUDE".length()).trim();
          if (!FileUtils.isAbsolute(sql)) {
            sql = path + SysProperties.FILE_SEPARATOR + sql;
          }
          process(conn, sql, continueOnError, charset);
        }
        else
        {
          try
          {
            if ((this.showResults) && (!trim.startsWith("-->"))) {
              this.out.print(sql + ";");
            }
            if ((this.showResults) || (this.checkResults))
            {
              boolean query = stat.execute(sql);
              if (query)
              {
                ResultSet rs = stat.getResultSet();
                int columns = rs.getMetaData().getColumnCount();
                StringBuilder buff = new StringBuilder();
                while (rs.next())
                {
                  buff.append("\n-->");
                  for (int i = 0; i < columns; i++)
                  {
                    String s = rs.getString(i + 1);
                    if (s != null)
                    {
                      s = StringUtils.replaceAll(s, "\r\n", "\n");
                      s = StringUtils.replaceAll(s, "\n", "\n-->    ");
                      s = StringUtils.replaceAll(s, "\r", "\r-->    ");
                    }
                    buff.append(' ').append(s);
                  }
                }
                buff.append("\n;");
                String result = buff.toString();
                if (this.showResults) {
                  this.out.print(result);
                }
                if (this.checkResults)
                {
                  String expected = r.readStatement() + ";";
                  expected = StringUtils.replaceAll(expected, "\r\n", "\n");
                  expected = StringUtils.replaceAll(expected, "\r", "\n");
                  if (!expected.equals(result))
                  {
                    expected = StringUtils.replaceAll(expected, " ", "+");
                    result = StringUtils.replaceAll(result, " ", "+");
                    throw new SQLException("Unexpected output for:\n" + sql.trim() + "\nGot:\n" + result + "\nExpected:\n" + expected);
                  }
                }
              }
            }
            else
            {
              stat.execute(sql);
            }
          }
          catch (Exception e)
          {
            if (continueOnError) {
              e.printStackTrace(this.out);
            } else {
              throw DbException.toSQLException(e);
            }
          }
        }
      }
    }
  }
  
  private static void processRunscript(String url, String user, String password, String fileName, String options)
    throws SQLException
  {
    Connection conn = null;
    Statement stat = null;
    try
    {
      Driver.load();
      conn = DriverManager.getConnection(url, user, password);
      stat = conn.createStatement();
      String sql = "RUNSCRIPT FROM '" + fileName + "' " + options;
      stat.execute(sql);
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
      JdbcUtils.closeSilently(conn);
    }
  }
  
  public static void execute(String url, String user, String password, String fileName, Charset charset, boolean continueOnError)
    throws SQLException
  {
    new RunScript().process(url, user, password, fileName, charset, continueOnError);
  }
  
  void process(String url, String user, String password, String fileName, Charset charset, boolean continueOnError)
    throws SQLException
  {
    try
    {
      Driver.load();
      Connection conn = DriverManager.getConnection(url, user, password);
      if (charset == null) {
        charset = Constants.UTF8;
      }
      try
      {
        process(conn, fileName, continueOnError, charset);
      }
      finally
      {
        conn.close();
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, fileName);
    }
  }
}
