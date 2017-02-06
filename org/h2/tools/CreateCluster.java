package org.h2.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.Driver;
import org.h2.store.fs.FileUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.Tool;

public class CreateCluster
  extends Tool
{
  public static void main(String... args)
    throws SQLException
  {
    new CreateCluster().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String urlSource = null;
    String urlTarget = null;
    String user = "";
    String password = "";
    String serverList = null;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg.equals("-urlSource"))
      {
        urlSource = args[(++i)];
      }
      else if (arg.equals("-urlTarget"))
      {
        urlTarget = args[(++i)];
      }
      else if (arg.equals("-user"))
      {
        user = args[(++i)];
      }
      else if (arg.equals("-password"))
      {
        password = args[(++i)];
      }
      else if (arg.equals("-serverList"))
      {
        serverList = args[(++i)];
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
    if ((urlSource == null) || (urlTarget == null) || (serverList == null))
    {
      showUsage();
      throw new SQLException("Source URL, target URL, or server list not set");
    }
    process(urlSource, urlTarget, user, password, serverList);
  }
  
  public void execute(String urlSource, String urlTarget, String user, String password, String serverList)
    throws SQLException
  {
    process(urlSource, urlTarget, user, password, serverList);
  }
  
  private void process(String urlSource, String urlTarget, String user, String password, String serverList)
    throws SQLException
  {
    Connection connSource = null;Connection connTarget = null;
    Statement statSource = null;Statement statTarget = null;
    String scriptFile = "backup.sql";
    try
    {
      Driver.load();
      
      boolean exists = true;
      try
      {
        connTarget = DriverManager.getConnection(urlTarget + ";IFEXISTS=TRUE;CLUSTER=" + "TRUE", user, password);
        
        Statement stat = connTarget.createStatement();
        stat.execute("DROP ALL OBJECTS DELETE FILES");
        stat.close();
        exists = false;
        connTarget.close();
      }
      catch (SQLException e)
      {
        if (e.getErrorCode() == 90013) {
          exists = false;
        } else {
          throw e;
        }
      }
      if (exists) {
        throw new SQLException("Target database must not yet exist. Please delete it first: " + urlTarget);
      }
      connSource = DriverManager.getConnection(urlSource + ";CLUSTER=''", user, password);
      
      statSource = connSource.createStatement();
      
      statSource.execute("SET EXCLUSIVE 2");
      try
      {
        Script script = new Script();
        script.setOut(this.out);
        Script.process(connSource, scriptFile, "", "");
        
        connTarget = DriverManager.getConnection(urlTarget + ";CLUSTER=''", user, password);
        
        statTarget = connTarget.createStatement();
        statTarget.execute("DROP ALL OBJECTS DELETE FILES");
        connTarget.close();
        
        RunScript runScript = new RunScript();
        runScript.setOut(this.out);
        runScript.process(urlTarget, user, password, scriptFile, null, false);
        
        connTarget = DriverManager.getConnection(urlTarget, user, password);
        statTarget = connTarget.createStatement();
        
        statSource.executeUpdate("SET CLUSTER '" + serverList + "'");
        statTarget.executeUpdate("SET CLUSTER '" + serverList + "'");
      }
      finally
      {
        statSource.execute("SET EXCLUSIVE FALSE");
      }
    }
    finally
    {
      FileUtils.delete(scriptFile);
      JdbcUtils.closeSilently(statSource);
      JdbcUtils.closeSilently(statTarget);
      JdbcUtils.closeSilently(connSource);
      JdbcUtils.closeSilently(connTarget);
    }
  }
}
