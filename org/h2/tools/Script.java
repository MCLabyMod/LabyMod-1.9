package org.h2.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.Driver;
import org.h2.util.JdbcUtils;
import org.h2.util.StringUtils;
import org.h2.util.Tool;

public class Script
  extends Tool
{
  public static void main(String... args)
    throws SQLException
  {
    new Script().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String url = null;
    String user = "";
    String password = "";
    String file = "backup.sql";
    String options1 = "";
    String options2 = "";
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
      else if (arg.equals("-script"))
      {
        file = args[(++i)];
      }
      else if (arg.equals("-options"))
      {
        StringBuilder buff1 = new StringBuilder();
        StringBuilder buff2 = new StringBuilder();
        i++;
        for (; i < args.length; i++)
        {
          String a = args[i];
          String upper = StringUtils.toUpperEnglish(a);
          if (("SIMPLE".equals(upper)) || (upper.startsWith("NO")) || ("DROP".equals(upper)))
          {
            buff1.append(' ');
            buff1.append(args[i]);
          }
          else if ("BLOCKSIZE".equals(upper))
          {
            buff1.append(' ');
            buff1.append(args[i]);
            i++;
            buff1.append(' ');
            buff1.append(args[i]);
          }
          else
          {
            buff2.append(' ');
            buff2.append(args[i]);
          }
        }
        options1 = buff1.toString();
        options2 = buff2.toString();
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
    process(url, user, password, file, options1, options2);
  }
  
  public static void process(String url, String user, String password, String fileName, String options1, String options2)
    throws SQLException
  {
    Connection conn = null;
    try
    {
      Driver.load();
      conn = DriverManager.getConnection(url, user, password);
      process(conn, fileName, options1, options2);
    }
    finally
    {
      JdbcUtils.closeSilently(conn);
    }
  }
  
  public static void process(Connection conn, String fileName, String options1, String options2)
    throws SQLException
  {
    Statement stat = null;
    try
    {
      stat = conn.createStatement();
      String sql = "SCRIPT " + options1 + " TO '" + fileName + "' " + options2;
      stat.execute(sql);
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
    }
  }
}
