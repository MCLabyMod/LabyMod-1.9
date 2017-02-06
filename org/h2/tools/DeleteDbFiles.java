package org.h2.tools;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.store.FileLister;
import org.h2.store.fs.FileUtils;
import org.h2.util.Tool;

public class DeleteDbFiles
  extends Tool
{
  public static void main(String... args)
    throws SQLException
  {
    new DeleteDbFiles().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
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
    process(dir, db, quiet);
  }
  
  public static void execute(String dir, String db, boolean quiet)
  {
    new DeleteDbFiles().process(dir, db, quiet);
  }
  
  private void process(String dir, String db, boolean quiet)
  {
    ArrayList<String> files = FileLister.getDatabaseFiles(dir, db, true);
    if ((files.size() == 0) && (!quiet)) {
      printNoDatabaseFilesFound(dir, db);
    }
    for (String fileName : files)
    {
      process(fileName, quiet);
      if (!quiet) {
        this.out.println("Processed: " + fileName);
      }
    }
  }
  
  private static void process(String fileName, boolean quiet)
  {
    if (FileUtils.isDirectory(fileName)) {
      FileUtils.tryDelete(fileName);
    } else if ((quiet) || (fileName.endsWith(".temp.db")) || (fileName.endsWith(".trace.db"))) {
      FileUtils.tryDelete(fileName);
    } else {
      FileUtils.delete(fileName);
    }
  }
}
