package org.h2.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Properties;
import org.h2.message.DbException;
import org.h2.store.FileLister;
import org.h2.store.fs.FileUtils;

public abstract class Tool
{
  protected PrintStream out = System.out;
  private Properties resources;
  
  public void setOut(PrintStream out)
  {
    this.out = out;
  }
  
  public abstract void runTool(String... paramVarArgs)
    throws SQLException;
  
  protected SQLException showUsageAndThrowUnsupportedOption(String option)
    throws SQLException
  {
    showUsage();
    throw throwUnsupportedOption(option);
  }
  
  protected SQLException throwUnsupportedOption(String option)
    throws SQLException
  {
    throw DbException.get(50100, option).getSQLException();
  }
  
  protected void printNoDatabaseFilesFound(String dir, String db)
  {
    dir = FileLister.getDir(dir);
    StringBuilder buff;
    if (!FileUtils.isDirectory(dir))
    {
      StringBuilder buff = new StringBuilder("Directory not found: ");
      buff.append(dir);
    }
    else
    {
      buff = new StringBuilder("No database files have been found");
      buff.append(" in directory ").append(dir);
      if (db != null) {
        buff.append(" for the database ").append(db);
      }
    }
    this.out.println(buff.toString());
  }
  
  protected void showUsage()
  {
    if (this.resources == null)
    {
      this.resources = new Properties();
      String resourceName = "/org/h2/res/javadoc.properties";
      try
      {
        byte[] buff = Utils.getResource(resourceName);
        if (buff != null) {
          this.resources.load(new ByteArrayInputStream(buff));
        }
      }
      catch (IOException e)
      {
        this.out.println("Cannot load " + resourceName);
      }
    }
    String className = getClass().getName();
    this.out.println(this.resources.get(className));
    this.out.println("Usage: java " + getClass().getName() + " <options>");
    this.out.println(this.resources.get(className + ".main"));
    this.out.println("See also http://h2database.com/javadoc/" + className.replace('.', '/') + ".html");
  }
  
  public static boolean isOption(String arg, String option)
  {
    if (arg.equals(option)) {
      return true;
    }
    if (arg.startsWith(option)) {
      throw DbException.getUnsupportedException("expected: " + option + " got: " + arg);
    }
    return false;
  }
}
