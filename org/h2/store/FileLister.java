package org.h2.store;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.h2.message.DbException;
import org.h2.message.TraceSystem;
import org.h2.store.fs.FilePath;
import org.h2.store.fs.FileUtils;
import org.h2.util.New;

public class FileLister
{
  public static void tryUnlockDatabase(List<String> files, String message)
    throws SQLException
  {
    Iterator i$ = files.iterator();
    for (;;)
    {
      if (i$.hasNext())
      {
        String fileName = (String)i$.next();
        if (fileName.endsWith(".lock.db"))
        {
          FileLock lock = new FileLock(new TraceSystem(null), fileName, 1000);
          try
          {
            lock.lock(1);
            lock.unlock();
          }
          catch (DbException e)
          {
            throw DbException.get(90133, message).getSQLException();
          }
        }
        else if (fileName.endsWith(".mv.db"))
        {
          FileChannel f = null;
          try
          {
            f = FilePath.get(fileName).open("r");
            java.nio.channels.FileLock lock = f.tryLock(0L, Long.MAX_VALUE, true);
            lock.release();
            if (f != null) {
              try
              {
                f.close();
              }
              catch (IOException e) {}
            }
          }
          catch (Exception e)
          {
            throw DbException.get(90133, e, new String[] { message }).getSQLException();
          }
          finally
          {
            if (f != null) {
              try
              {
                f.close();
              }
              catch (IOException e) {}
            }
          }
        }
      }
    }
  }
  
  public static String getDir(String dir)
  {
    if ((dir == null) || (dir.equals(""))) {
      return ".";
    }
    return FileUtils.toRealPath(dir);
  }
  
  public static ArrayList<String> getDatabaseFiles(String dir, String db, boolean all)
  {
    ArrayList<String> files = New.arrayList();
    
    String start = FileUtils.toRealPath(new StringBuilder().append(dir).append("/").append(db).toString()) + ".";
    for (String f : FileUtils.newDirectoryStream(dir))
    {
      boolean ok = false;
      if (f.endsWith(".lobs.db"))
      {
        if ((start == null) || (f.startsWith(start)))
        {
          files.addAll(getDatabaseFiles(f, null, all));
          ok = true;
        }
      }
      else if (f.endsWith(".lob.db")) {
        ok = true;
      } else if (f.endsWith(".h2.db")) {
        ok = true;
      } else if (f.endsWith(".mv.db")) {
        ok = true;
      } else if (all) {
        if (f.endsWith(".lock.db")) {
          ok = true;
        } else if (f.endsWith(".temp.db")) {
          ok = true;
        } else if (f.endsWith(".trace.db")) {
          ok = true;
        }
      }
      if ((ok) && (
        (db == null) || (f.startsWith(start))))
      {
        String fileName = f;
        files.add(fileName);
      }
    }
    return files;
  }
}
