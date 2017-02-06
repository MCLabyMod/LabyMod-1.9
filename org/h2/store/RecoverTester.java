package org.h2.store;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import org.h2.command.Prepared;
import org.h2.engine.ConnectionInfo;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.store.fs.FilePathRec;
import org.h2.store.fs.FileUtils;
import org.h2.store.fs.Recorder;
import org.h2.tools.Recover;
import org.h2.util.IOUtils;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class RecoverTester
  implements Recorder
{
  private static RecoverTester instance;
  private String testDatabase = "memFS:reopen";
  private int writeCount = Utils.getProperty("h2.recoverTestOffset", 0);
  private int testEvery = Utils.getProperty("h2.recoverTest", 64);
  private final long maxFileSize = Utils.getProperty("h2.recoverTestMaxFileSize", Integer.MAX_VALUE) * 1024L * 1024L;
  private int verifyCount;
  private final HashSet<String> knownErrors = New.hashSet();
  private volatile boolean testing;
  
  public static synchronized void init(String recoverTest)
  {
    RecoverTester tester = getInstance();
    if (StringUtils.isNumber(recoverTest)) {
      tester.setTestEvery(Integer.parseInt(recoverTest));
    }
    FilePathRec.setRecorder(tester);
  }
  
  public static synchronized RecoverTester getInstance()
  {
    if (instance == null) {
      instance = new RecoverTester();
    }
    return instance;
  }
  
  public void log(int op, String fileName, byte[] data, long x)
  {
    if ((op != 8) && (op != 7)) {
      return;
    }
    if ((!fileName.endsWith(".h2.db")) && (!fileName.endsWith(".mv.db"))) {
      return;
    }
    this.writeCount += 1;
    if (this.writeCount % this.testEvery != 0) {
      return;
    }
    if (FileUtils.size(fileName) > this.maxFileSize) {
      return;
    }
    if (this.testing) {
      return;
    }
    this.testing = true;
    PrintWriter out = null;
    try
    {
      out = new PrintWriter(new OutputStreamWriter(FileUtils.newOutputStream(fileName + ".log", true)));
      
      testDatabase(fileName, out);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
    finally
    {
      IOUtils.closeSilently(out);
      this.testing = false;
    }
  }
  
  private synchronized void testDatabase(String fileName, PrintWriter out)
  {
    out.println("+ write #" + this.writeCount + " verify #" + this.verifyCount);
    try
    {
      IOUtils.copyFiles(fileName, this.testDatabase + ".h2.db");
      String mvFileName = fileName.substring(0, fileName.length() - ".h2.db".length()) + ".mv.db";
      if (FileUtils.exists(mvFileName)) {
        IOUtils.copyFiles(mvFileName, this.testDatabase + ".mv.db");
      }
      this.verifyCount += 1;
      
      Properties p = new Properties();
      p.setProperty("user", "");
      p.setProperty("password", "");
      ConnectionInfo ci = new ConnectionInfo("jdbc:h2:" + this.testDatabase + ";FILE_LOCK=NO;TRACE_LEVEL_FILE=0", p);
      
      Database database = new Database(ci, null);
      
      Session sysSession = database.getSystemSession();
      sysSession.prepare("script to '" + this.testDatabase + ".sql'").query(0);
      sysSession.prepare("shutdown immediately").update();
      database.removeSession(null);
      
      return;
    }
    catch (DbException e)
    {
      SQLException e2 = DbException.toSQLException(e);
      int errorCode = e2.getErrorCode();
      if (errorCode == 28000) {
        return;
      }
      if (errorCode == 90049) {
        return;
      }
      e.printStackTrace(System.out);
    }
    catch (Exception e)
    {
      int errorCode = 0;
      if ((e instanceof SQLException)) {
        errorCode = ((SQLException)e).getErrorCode();
      }
      if (errorCode == 28000) {
        return;
      }
      if (errorCode == 90049) {
        return;
      }
      e.printStackTrace(System.out);
    }
    out.println("begin ------------------------------ " + this.writeCount);
    try
    {
      Recover.execute(fileName.substring(0, fileName.lastIndexOf('/')), null);
    }
    catch (SQLException e) {}
    this.testDatabase += "X";
    try
    {
      IOUtils.copyFiles(fileName, this.testDatabase + ".h2.db");
      
      Properties p = new Properties();
      ConnectionInfo ci = new ConnectionInfo("jdbc:h2:" + this.testDatabase + ";FILE_LOCK=NO", p);
      
      Database database = new Database(ci, null);
      
      database.removeSession(null);
    }
    catch (Exception e)
    {
      int errorCode = 0;
      if ((e instanceof DbException))
      {
        e = ((DbException)e).getSQLException();
        errorCode = ((SQLException)e).getErrorCode();
      }
      if (errorCode == 28000) {
        return;
      }
      if (errorCode == 90049) {
        return;
      }
      StringBuilder buff = new StringBuilder();
      StackTraceElement[] list = e.getStackTrace();
      for (int i = 0; (i < 10) && (i < list.length); i++) {
        buff.append(list[i].toString()).append('\n');
      }
      String s = buff.toString();
      if (!this.knownErrors.contains(s))
      {
        out.println(this.writeCount + " code: " + errorCode + " " + e.toString());
        e.printStackTrace(System.out);
        this.knownErrors.add(s);
      }
      else
      {
        out.println(this.writeCount + " code: " + errorCode);
      }
    }
  }
  
  public void setTestEvery(int testEvery)
  {
    this.testEvery = testEvery;
  }
}
