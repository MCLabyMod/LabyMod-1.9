package org.h2.store.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.util.IOUtils;
import org.h2.util.New;

public class FilePathDisk
  extends FilePath
{
  private static final String CLASSPATH_PREFIX = "classpath:";
  
  public FilePathDisk getPath(String path)
  {
    FilePathDisk p = new FilePathDisk();
    p.name = translateFileName(path);
    return p;
  }
  
  public long size()
  {
    return new File(this.name).length();
  }
  
  protected static String translateFileName(String fileName)
  {
    fileName = fileName.replace('\\', '/');
    if (fileName.startsWith("file:")) {
      fileName = fileName.substring("file:".length());
    }
    return expandUserHomeDirectory(fileName);
  }
  
  public static String expandUserHomeDirectory(String fileName)
  {
    if ((fileName.startsWith("~")) && ((fileName.length() == 1) || (fileName.startsWith("~/"))))
    {
      String userDir = SysProperties.USER_HOME;
      fileName = userDir + fileName.substring(1);
    }
    return fileName;
  }
  
  public void moveTo(FilePath newName, boolean atomicReplace)
  {
    File oldFile = new File(this.name);
    File newFile = new File(newName.name);
    if (oldFile.getAbsolutePath().equals(newFile.getAbsolutePath())) {
      return;
    }
    if (!oldFile.exists()) {
      throw DbException.get(90024, new String[] { this.name + " (not found)", newName.name });
    }
    if (atomicReplace)
    {
      boolean ok = oldFile.renameTo(newFile);
      if (ok) {
        return;
      }
      throw DbException.get(90024, new String[] { this.name, newName.name });
    }
    if (newFile.exists()) {
      throw DbException.get(90024, new String[] { this.name, newName + " (exists)" });
    }
    for (int i = 0; i < SysProperties.MAX_FILE_RETRY; i++)
    {
      IOUtils.trace("rename", this.name + " >" + newName, null);
      boolean ok = oldFile.renameTo(newFile);
      if (ok) {
        return;
      }
      wait(i);
    }
    throw DbException.get(90024, new String[] { this.name, newName.name });
  }
  
  private static void wait(int i)
  {
    if (i == 8) {
      System.gc();
    }
    try
    {
      long sleep = Math.min(256, i * i);
      Thread.sleep(sleep);
    }
    catch (InterruptedException e) {}
  }
  
  public boolean createFile()
  {
    File file = new File(this.name);
    for (int i = 0; i < SysProperties.MAX_FILE_RETRY; i++) {
      try
      {
        return file.createNewFile();
      }
      catch (IOException e)
      {
        wait(i);
      }
    }
    return false;
  }
  
  public boolean exists()
  {
    return new File(this.name).exists();
  }
  
  public void delete()
  {
    File file = new File(this.name);
    for (int i = 0; i < SysProperties.MAX_FILE_RETRY; i++)
    {
      IOUtils.trace("delete", this.name, null);
      boolean ok = file.delete();
      if ((ok) || (!file.exists())) {
        return;
      }
      wait(i);
    }
    throw DbException.get(90025, this.name);
  }
  
  public List<FilePath> newDirectoryStream()
  {
    ArrayList<FilePath> list = New.arrayList();
    File f = new File(this.name);
    try
    {
      String[] files = f.list();
      if (files != null)
      {
        String base = f.getCanonicalPath();
        if (!base.endsWith(SysProperties.FILE_SEPARATOR)) {
          base = base + SysProperties.FILE_SEPARATOR;
        }
        int i = 0;
        for (int len = files.length; i < len; i++) {
          list.add(getPath(base + files[i]));
        }
      }
      return list;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, this.name);
    }
  }
  
  public boolean canWrite()
  {
    return canWriteInternal(new File(this.name));
  }
  
  public boolean setReadOnly()
  {
    File f = new File(this.name);
    return f.setReadOnly();
  }
  
  public FilePathDisk toRealPath()
  {
    try
    {
      String fileName = new File(this.name).getCanonicalPath();
      return getPath(fileName);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, this.name);
    }
  }
  
  public FilePath getParent()
  {
    String p = new File(this.name).getParent();
    return p == null ? null : getPath(p);
  }
  
  public boolean isDirectory()
  {
    return new File(this.name).isDirectory();
  }
  
  public boolean isAbsolute()
  {
    return new File(this.name).isAbsolute();
  }
  
  public long lastModified()
  {
    return new File(this.name).lastModified();
  }
  
  private static boolean canWriteInternal(File file)
  {
    try
    {
      if (!file.canWrite()) {
        return false;
      }
    }
    catch (Exception e)
    {
      return false;
    }
    RandomAccessFile r = null;
    try
    {
      r = new RandomAccessFile(file, "rw");
      return true;
    }
    catch (FileNotFoundException e)
    {
      return 0;
    }
    finally
    {
      if (r != null) {
        try
        {
          r.close();
        }
        catch (IOException e) {}
      }
    }
  }
  
  public void createDirectory()
  {
    File dir = new File(this.name);
    for (int i = 0; i < SysProperties.MAX_FILE_RETRY; i++)
    {
      if (dir.exists())
      {
        if (dir.isDirectory()) {
          return;
        }
        throw DbException.get(90062, this.name + " (a file with this name already exists)");
      }
      if (dir.mkdir()) {
        return;
      }
      wait(i);
    }
    throw DbException.get(90062, this.name);
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    try
    {
      File file = new File(this.name);
      File parent = file.getParentFile();
      if (parent != null) {
        FileUtils.createDirectories(parent.getAbsolutePath());
      }
      FileOutputStream out = new FileOutputStream(this.name, append);
      IOUtils.trace("openFileOutputStream", this.name, out);
      return out;
    }
    catch (IOException e)
    {
      freeMemoryAndFinalize();
    }
    return new FileOutputStream(this.name);
  }
  
  public InputStream newInputStream()
    throws IOException
  {
    int index = this.name.indexOf(':');
    if ((index > 1) && (index < 20))
    {
      if (this.name.startsWith("classpath:"))
      {
        String fileName = this.name.substring("classpath:".length());
        if (!fileName.startsWith("/")) {
          fileName = "/" + fileName;
        }
        InputStream in = getClass().getResourceAsStream(fileName);
        if (in == null) {
          in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        }
        if (in == null) {
          throw new FileNotFoundException("resource " + fileName);
        }
        return in;
      }
      URL url = new URL(this.name);
      InputStream in = url.openStream();
      return in;
    }
    FileInputStream in = new FileInputStream(this.name);
    IOUtils.trace("openFileInputStream", this.name, in);
    return in;
  }
  
  static void freeMemoryAndFinalize()
  {
    IOUtils.trace("freeMemoryAndFinalize", null, null);
    Runtime rt = Runtime.getRuntime();
    long mem = rt.freeMemory();
    for (int i = 0; i < 16; i++)
    {
      rt.gc();
      long now = rt.freeMemory();
      rt.runFinalization();
      if (now == mem) {
        break;
      }
      mem = now;
    }
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    FileDisk f;
    try
    {
      f = new FileDisk(this.name, mode);
      IOUtils.trace("open", this.name, f);
    }
    catch (IOException e)
    {
      freeMemoryAndFinalize();
      try
      {
        f = new FileDisk(this.name, mode);
      }
      catch (IOException e2)
      {
        throw e;
      }
    }
    return f;
  }
  
  public String getScheme()
  {
    return "file";
  }
  
  public FilePath createTempFile(String suffix, boolean deleteOnExit, boolean inTempDir)
    throws IOException
  {
    String fileName = this.name + ".";
    String prefix = new File(fileName).getName();
    File dir;
    File dir;
    if (inTempDir) {
      dir = new File(System.getProperty("java.io.tmpdir", "."));
    } else {
      dir = new File(fileName).getAbsoluteFile().getParentFile();
    }
    FileUtils.createDirectories(dir.getAbsolutePath());
    File f;
    for (;;)
    {
      f = new File(dir, prefix + getNextTempFileNamePart(false) + suffix);
      if ((!f.exists()) && (f.createNewFile())) {
        break;
      }
      getNextTempFileNamePart(true);
    }
    if (deleteOnExit) {
      try
      {
        f.deleteOnExit();
      }
      catch (Throwable e) {}
    }
    return get(f.getCanonicalPath());
  }
}
