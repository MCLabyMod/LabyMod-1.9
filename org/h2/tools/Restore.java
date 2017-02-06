package org.h2.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.Tool;

public class Restore
  extends Tool
{
  public static void main(String... args)
    throws SQLException
  {
    new Restore().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String zipFileName = "backup.zip";
    String dir = ".";
    String db = null;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg.equals("-dir"))
      {
        dir = args[(++i)];
      }
      else if (arg.equals("-file"))
      {
        zipFileName = args[(++i)];
      }
      else if (arg.equals("-db"))
      {
        db = args[(++i)];
      }
      else if (!arg.equals("-quiet"))
      {
        if ((arg.equals("-help")) || (arg.equals("-?")))
        {
          showUsage();
          return;
        }
        showUsageAndThrowUnsupportedOption(arg);
      }
    }
    execute(zipFileName, dir, db);
  }
  
  private static String getOriginalDbName(String fileName, String db)
    throws IOException
  {
    InputStream in = null;
    try
    {
      in = FileUtils.newInputStream(fileName);
      ZipInputStream zipIn = new ZipInputStream(in);
      String originalDbName = null;
      boolean multiple = false;
      ZipEntry entry;
      for (;;)
      {
        entry = zipIn.getNextEntry();
        if (entry == null) {
          break;
        }
        String entryName = entry.getName();
        zipIn.closeEntry();
        String name = getDatabaseNameFromFileName(entryName);
        if (name != null)
        {
          if (db.equals(name))
          {
            originalDbName = name;
            
            break;
          }
          if (originalDbName == null) {
            originalDbName = name;
          } else {
            multiple = true;
          }
        }
      }
      zipIn.close();
      if ((multiple) && (!db.equals(originalDbName))) {
        throw new IOException("Multiple databases found, but not " + db);
      }
      return originalDbName;
    }
    finally
    {
      IOUtils.closeSilently(in);
    }
  }
  
  private static String getDatabaseNameFromFileName(String fileName)
  {
    if (fileName.endsWith(".h2.db")) {
      return fileName.substring(0, fileName.length() - ".h2.db".length());
    }
    if (fileName.endsWith(".mv.db")) {
      return fileName.substring(0, fileName.length() - ".mv.db".length());
    }
    return null;
  }
  
  public static void execute(String zipFileName, String directory, String db)
  {
    InputStream in = null;
    try
    {
      if (!FileUtils.exists(zipFileName)) {
        throw new IOException("File not found: " + zipFileName);
      }
      String originalDbName = null;
      int originalDbLen = 0;
      if (db != null)
      {
        originalDbName = getOriginalDbName(zipFileName, db);
        if (originalDbName == null) {
          throw new IOException("No database named " + db + " found");
        }
        if (originalDbName.startsWith(SysProperties.FILE_SEPARATOR)) {
          originalDbName = originalDbName.substring(1);
        }
        originalDbLen = originalDbName.length();
      }
      in = FileUtils.newInputStream(zipFileName);
      ZipInputStream zipIn = new ZipInputStream(in);
      for (;;)
      {
        ZipEntry entry = zipIn.getNextEntry();
        if (entry == null) {
          break;
        }
        String fileName = entry.getName();
        
        fileName = fileName.replace('\\', SysProperties.FILE_SEPARATOR.charAt(0));
        fileName = fileName.replace('/', SysProperties.FILE_SEPARATOR.charAt(0));
        if (fileName.startsWith(SysProperties.FILE_SEPARATOR)) {
          fileName = fileName.substring(1);
        }
        boolean copy = false;
        if (db == null)
        {
          copy = true;
        }
        else if (fileName.startsWith(originalDbName + "."))
        {
          fileName = db + fileName.substring(originalDbLen);
          copy = true;
        }
        if (copy)
        {
          OutputStream o = null;
          try
          {
            o = FileUtils.newOutputStream(directory + SysProperties.FILE_SEPARATOR + fileName, false);
            
            IOUtils.copy(zipIn, o);
            o.close();
          }
          finally {}
        }
        zipIn.closeEntry();
      }
      zipIn.closeEntry();
      zipIn.close();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, zipFileName);
    }
    finally
    {
      IOUtils.closeSilently(in);
    }
  }
}
