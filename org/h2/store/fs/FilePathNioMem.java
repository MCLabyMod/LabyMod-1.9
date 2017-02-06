package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import org.h2.message.DbException;
import org.h2.util.New;

public class FilePathNioMem
  extends FilePath
{
  private static final TreeMap<String, FileNioMemData> MEMORY_FILES = new TreeMap();
  
  public FilePathNioMem getPath(String path)
  {
    FilePathNioMem p = new FilePathNioMem();
    p.name = getCanonicalPath(path);
    return p;
  }
  
  public long size()
  {
    return getMemoryFile().length();
  }
  
  public void moveTo(FilePath newName, boolean atomicReplace)
  {
    synchronized (MEMORY_FILES)
    {
      if ((!atomicReplace) && (!this.name.equals(newName.name)) && (MEMORY_FILES.containsKey(newName.name))) {
        throw DbException.get(90024, new String[] { this.name, newName + " (exists)" });
      }
      FileNioMemData f = getMemoryFile();
      f.setName(newName.name);
      MEMORY_FILES.remove(this.name);
      MEMORY_FILES.put(newName.name, f);
    }
  }
  
  public boolean createFile()
  {
    synchronized (MEMORY_FILES)
    {
      if (exists()) {
        return false;
      }
      getMemoryFile();
    }
    return true;
  }
  
  public boolean exists()
  {
    if (isRoot()) {
      return true;
    }
    synchronized (MEMORY_FILES)
    {
      return MEMORY_FILES.get(this.name) != null;
    }
  }
  
  public void delete()
  {
    if (isRoot()) {
      return;
    }
    synchronized (MEMORY_FILES)
    {
      MEMORY_FILES.remove(this.name);
    }
  }
  
  public List<FilePath> newDirectoryStream()
  {
    ArrayList<FilePath> list = New.arrayList();
    synchronized (MEMORY_FILES)
    {
      for (String n : MEMORY_FILES.tailMap(this.name).keySet())
      {
        if (!n.startsWith(this.name)) {
          break;
        }
        list.add(getPath(n));
      }
      return list;
    }
  }
  
  public boolean setReadOnly()
  {
    return getMemoryFile().setReadOnly();
  }
  
  public boolean canWrite()
  {
    return getMemoryFile().canWrite();
  }
  
  public FilePathNioMem getParent()
  {
    int idx = this.name.lastIndexOf('/');
    return idx < 0 ? null : getPath(this.name.substring(0, idx));
  }
  
  public boolean isDirectory()
  {
    if (isRoot()) {
      return true;
    }
    synchronized (MEMORY_FILES)
    {
      return MEMORY_FILES.get(this.name) == null;
    }
  }
  
  public boolean isAbsolute()
  {
    return true;
  }
  
  public FilePathNioMem toRealPath()
  {
    return this;
  }
  
  public long lastModified()
  {
    return getMemoryFile().getLastModified();
  }
  
  public void createDirectory()
  {
    if ((exists()) && (isDirectory())) {
      throw DbException.get(90062, this.name + " (a file with this name already exists)");
    }
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    FileNioMemData obj = getMemoryFile();
    FileNioMem m = new FileNioMem(obj, false);
    return new FileChannelOutputStream(m, append);
  }
  
  public InputStream newInputStream()
  {
    FileNioMemData obj = getMemoryFile();
    FileNioMem m = new FileNioMem(obj, true);
    return new FileChannelInputStream(m, true);
  }
  
  public FileChannel open(String mode)
  {
    FileNioMemData obj = getMemoryFile();
    return new FileNioMem(obj, "r".equals(mode));
  }
  
  private FileNioMemData getMemoryFile()
  {
    synchronized (MEMORY_FILES)
    {
      FileNioMemData m = (FileNioMemData)MEMORY_FILES.get(this.name);
      if (m == null)
      {
        m = new FileNioMemData(this.name, compressed());
        MEMORY_FILES.put(this.name, m);
      }
      return m;
    }
  }
  
  private boolean isRoot()
  {
    return this.name.equals(getScheme() + ":");
  }
  
  protected static String getCanonicalPath(String fileName)
  {
    fileName = fileName.replace('\\', '/');
    int idx = fileName.indexOf(':') + 1;
    if ((fileName.length() > idx) && (fileName.charAt(idx) != '/')) {
      fileName = fileName.substring(0, idx) + "/" + fileName.substring(idx);
    }
    return fileName;
  }
  
  public String getScheme()
  {
    return "nioMemFS";
  }
  
  boolean compressed()
  {
    return false;
  }
}
