package org.h2.command.dml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.result.ResultInterface;
import org.h2.store.FileLister;
import org.h2.store.PageStore;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.value.Value;

public class BackupCommand
  extends Prepared
{
  private Expression fileNameExpr;
  
  public BackupCommand(Session session)
  {
    super(session);
  }
  
  public void setFileName(Expression fileName)
  {
    this.fileNameExpr = fileName;
  }
  
  public int update()
  {
    String name = this.fileNameExpr.getValue(this.session).getString();
    this.session.getUser().checkAdmin();
    backupTo(name);
    return 0;
  }
  
  private void backupTo(String fileName)
  {
    Database db = this.session.getDatabase();
    if (!db.isPersistent()) {
      throw DbException.get(90126);
    }
    try
    {
      MVTableEngine.Store mvStore = db.getMvStore();
      if (mvStore != null) {
        mvStore.flush();
      }
      String name = db.getName();
      name = FileUtils.getName(name);
      OutputStream zip = FileUtils.newOutputStream(fileName, false);
      ZipOutputStream out = new ZipOutputStream(zip);
      db.flush();
      if (db.getPageStore() != null)
      {
        String fn = db.getName() + ".h2.db";
        backupPageStore(out, fn, db.getPageStore());
      }
      String base = FileUtils.getParent(db.getName());
      synchronized (db.getLobSyncObject())
      {
        String prefix = db.getDatabasePath();
        String dir = FileUtils.getParent(prefix);
        dir = FileLister.getDir(dir);
        ArrayList<String> fileList = FileLister.getDatabaseFiles(dir, name, true);
        for (String n : fileList)
        {
          if (n.endsWith(".lob.db")) {
            backupFile(out, base, n);
          }
          if ((n.endsWith(".mv.db")) && (mvStore != null))
          {
            MVStore s = mvStore.getStore();
            boolean before = s.getReuseSpace();
            s.setReuseSpace(false);
            try
            {
              InputStream in = mvStore.getInputStream();
              backupFile(out, base, n, in);
            }
            finally
            {
              s.setReuseSpace(before);
            }
          }
        }
      }
      out.close();
      zip.close();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, fileName);
    }
  }
  
  private void backupPageStore(ZipOutputStream out, String fileName, PageStore store)
    throws IOException
  {
    Database db = this.session.getDatabase();
    fileName = FileUtils.getName(fileName);
    out.putNextEntry(new ZipEntry(fileName));
    int pos = 0;
    try
    {
      store.setBackup(true);
      for (;;)
      {
        pos = store.copyDirect(pos, out);
        if (pos < 0) {
          break;
        }
        int max = store.getPageCount();
        db.setProgress(3, fileName, pos, max);
      }
    }
    finally
    {
      store.setBackup(false);
    }
    out.closeEntry();
  }
  
  private static void backupFile(ZipOutputStream out, String base, String fn)
    throws IOException
  {
    InputStream in = FileUtils.newInputStream(fn);
    backupFile(out, base, fn, in);
  }
  
  private static void backupFile(ZipOutputStream out, String base, String fn, InputStream in)
    throws IOException
  {
    String f = FileUtils.toRealPath(fn);
    base = FileUtils.toRealPath(base);
    if (!f.startsWith(base)) {
      DbException.throwInternalError(f + " does not start with " + base);
    }
    f = f.substring(base.length());
    f = correctFileName(f);
    out.putNextEntry(new ZipEntry(f));
    IOUtils.copyAndCloseInput(in, out);
    out.closeEntry();
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public static String correctFileName(String f)
  {
    f = f.replace('\\', '/');
    if (f.startsWith("/")) {
      f = f.substring(1);
    }
    return f;
  }
  
  public boolean needRecompile()
  {
    return false;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public int getType()
  {
    return 56;
  }
}
