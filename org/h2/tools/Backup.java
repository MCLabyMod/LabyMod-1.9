package org.h2.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.h2.command.dml.BackupCommand;
import org.h2.message.DbException;
import org.h2.store.FileLister;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.Tool;

public class Backup
  extends Tool
{
  public static void main(String... args)
    throws SQLException
  {
    new Backup().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String zipFileName = "backup.zip";
    String dir = ".";
    String db = null;
    boolean quiet = false;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg.equals("-dir"))
      {
        dir = args[(++i)];
      }
      else if (arg.equals("-db"))
      {
        db = args[(++i)];
      }
      else if (arg.equals("-quiet"))
      {
        quiet = true;
      }
      else if (arg.equals("-file"))
      {
        zipFileName = args[(++i)];
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
    try
    {
      process(zipFileName, dir, db, quiet);
    }
    catch (Exception e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  public static void execute(String zipFileName, String directory, String db, boolean quiet)
    throws SQLException
  {
    try
    {
      new Backup().process(zipFileName, directory, db, quiet);
    }
    catch (Exception e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  private void process(String zipFileName, String directory, String db, boolean quiet)
    throws SQLException
  {
    boolean allFiles = (db != null) && (db.length() == 0);
    List<String> list;
    List<String> list;
    if (allFiles) {
      list = FileUtils.newDirectoryStream(directory);
    } else {
      list = FileLister.getDatabaseFiles(directory, db, true);
    }
    if (list.size() == 0)
    {
      if (!quiet) {
        printNoDatabaseFilesFound(directory, db);
      }
      return;
    }
    if (!quiet) {
      FileLister.tryUnlockDatabase(list, "backup");
    }
    zipFileName = FileUtils.toRealPath(zipFileName);
    FileUtils.delete(zipFileName);
    OutputStream fileOut = null;
    try
    {
      fileOut = FileUtils.newOutputStream(zipFileName, false);
      ZipOutputStream zipOut = new ZipOutputStream(fileOut);
      String base = "";
      for (String fileName : list) {
        if ((allFiles) || (fileName.endsWith(".h2.db")) || (fileName.endsWith(".mv.db")))
        {
          base = FileUtils.getParent(fileName);
          break;
        }
      }
      for (String fileName : list)
      {
        String f = FileUtils.toRealPath(fileName);
        if (!f.startsWith(base)) {
          DbException.throwInternalError(f + " does not start with " + base);
        }
        if ((!f.endsWith(zipFileName)) && 
        
          (!FileUtils.isDirectory(fileName)))
        {
          f = f.substring(base.length());
          f = BackupCommand.correctFileName(f);
          ZipEntry entry = new ZipEntry(f);
          zipOut.putNextEntry(entry);
          InputStream in = null;
          try
          {
            in = FileUtils.newInputStream(fileName);
            IOUtils.copyAndCloseInput(in, zipOut);
          }
          catch (FileNotFoundException e) {}finally {}
          zipOut.closeEntry();
          if (!quiet) {
            this.out.println("Processed: " + fileName);
          }
        }
      }
      zipOut.close();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, zipFileName);
    }
    finally
    {
      IOUtils.closeSilently(fileOut);
    }
  }
}
