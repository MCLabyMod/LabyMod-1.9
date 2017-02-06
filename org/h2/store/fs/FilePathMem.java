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

public class FilePathMem
  extends FilePath
{
  private static final TreeMap<String, FileMemData> MEMORY_FILES = new TreeMap();
  private static final FileMemData DIRECTORY = new FileMemData("", false);
  
  public FilePathMem getPath(String path)
  {
    FilePathMem p = new FilePathMem();
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
      if ((!atomicReplace) && (!newName.name.equals(this.name)) && (MEMORY_FILES.containsKey(newName.name))) {
        throw DbException.get(90024, new String[] { this.name, newName + " (exists)" });
      }
      FileMemData f = getMemoryFile();
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
        if ((!n.equals(this.name)) && (n.indexOf('/', this.name.length() + 1) < 0)) {
          list.add(getPath(n));
        }
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
  
  public FilePathMem getParent()
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
      FileMemData d = (FileMemData)MEMORY_FILES.get(this.name);
      return d == DIRECTORY;
    }
  }
  
  public boolean isAbsolute()
  {
    return true;
  }
  
  public FilePathMem toRealPath()
  {
    return this;
  }
  
  public long lastModified()
  {
    return getMemoryFile().getLastModified();
  }
  
  public void createDirectory()
  {
    if (exists()) {
      throw DbException.get(90062, this.name + " (a file with this name already exists)");
    }
    synchronized (MEMORY_FILES)
    {
      MEMORY_FILES.put(this.name, DIRECTORY);
    }
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    FileMemData obj = getMemoryFile();
    FileMem m = new FileMem(obj, false);
    return new FileChannelOutputStream(m, append);
  }
  
  public InputStream newInputStream()
  {
    FileMemData obj = getMemoryFile();
    FileMem m = new FileMem(obj, true);
    return new FileChannelInputStream(m, true);
  }
  
  public FileChannel open(String mode)
  {
    FileMemData obj = getMemoryFile();
    return new FileMem(obj, "r".equals(mode));
  }
  
  private FileMemData getMemoryFile()
  {
    synchronized (MEMORY_FILES)
    {
      FileMemData m = (FileMemData)MEMORY_FILES.get(this.name);
      if (m == DIRECTORY) {
        throw DbException.get(90062, this.name + " (a directory with this name already exists)");
      }
      if (m == null)
      {
        m = new FileMemData(this.name, compressed());
        MEMORY_FILES.put(this.name, m);
      }
      return m;
    }
  }
  
  private boolean isRoot()
  {
    return this.name.equals(getScheme() + ":");
  }
  
  private static String getCanonicalPath(String fileName)
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
    return "memFS";
  }
  
  boolean compressed()
  {
    return false;
  }
}
