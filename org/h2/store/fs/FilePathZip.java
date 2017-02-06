package org.h2.store.fs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.h2.message.DbException;
import org.h2.util.New;

public class FilePathZip
  extends FilePath
{
  public FilePathZip getPath(String path)
  {
    FilePathZip p = new FilePathZip();
    p.name = path;
    return p;
  }
  
  public void createDirectory() {}
  
  public boolean createFile()
  {
    throw DbException.getUnsupportedException("write");
  }
  
  public void delete()
  {
    throw DbException.getUnsupportedException("write");
  }
  
  public boolean exists()
  {
    try
    {
      String entryName = getEntryName();
      if (entryName.length() == 0) {
        return true;
      }
      ZipFile file = openZipFile();
      try
      {
        return file.getEntry(entryName) != null;
      }
      finally
      {
        file.close();
      }
      return false;
    }
    catch (IOException e) {}
  }
  
  public long lastModified()
  {
    return 0L;
  }
  
  public FilePath getParent()
  {
    int idx = this.name.lastIndexOf('/');
    return idx < 0 ? null : getPath(this.name.substring(0, idx));
  }
  
  public boolean isAbsolute()
  {
    String fileName = translateFileName(this.name);
    return FilePath.get(fileName).isAbsolute();
  }
  
  public FilePath unwrap()
  {
    return FilePath.get(this.name.substring(getScheme().length() + 1));
  }
  
  public boolean isDirectory()
  {
    try
    {
      String entryName = getEntryName();
      if (entryName.length() == 0) {
        return true;
      }
      ZipFile file = openZipFile();
      try
      {
        Enumeration<? extends ZipEntry> en = file.entries();
        while (en.hasMoreElements())
        {
          ZipEntry entry = (ZipEntry)en.nextElement();
          String n = entry.getName();
          boolean bool;
          if (n.equals(entryName)) {
            return entry.isDirectory();
          }
          if ((n.startsWith(entryName)) && 
            (n.length() == entryName.length() + 1) && 
            (n.equals(entryName + "/"))) {
            return true;
          }
        }
      }
      finally
      {
        file.close();
      }
      return false;
    }
    catch (IOException e) {}
    return false;
  }
  
  public boolean canWrite()
  {
    return false;
  }
  
  public boolean setReadOnly()
  {
    return true;
  }
  
  public long size()
  {
    try
    {
      ZipFile file = openZipFile();
      try
      {
        ZipEntry entry = file.getEntry(getEntryName());
        return entry == null ? 0L : entry.getSize();
      }
      finally
      {
        file.close();
      }
      return 0L;
    }
    catch (IOException e) {}
  }
  
  public ArrayList<FilePath> newDirectoryStream()
  {
    String path = this.name;
    ArrayList<FilePath> list = New.arrayList();
    try
    {
      if (path.indexOf('!') < 0) {
        path = path + "!";
      }
      if (!path.endsWith("/")) {
        path = path + "/";
      }
      ZipFile file = openZipFile();
      try
      {
        String dirName = getEntryName();
        String prefix = path.substring(0, path.length() - dirName.length());
        Enumeration<? extends ZipEntry> en = file.entries();
        while (en.hasMoreElements())
        {
          ZipEntry entry = (ZipEntry)en.nextElement();
          String name = entry.getName();
          if ((name.startsWith(dirName)) && 
          
            (name.length() > dirName.length()))
          {
            int idx = name.indexOf('/', dirName.length());
            if ((idx < 0) || (idx >= name.length() - 1)) {
              list.add(getPath(prefix + name));
            }
          }
        }
      }
      finally
      {
        file.close();
      }
      return list;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, "listFiles " + path);
    }
  }
  
  public InputStream newInputStream()
    throws IOException
  {
    return new FileChannelInputStream(open("r"), true);
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    ZipFile file = openZipFile();
    ZipEntry entry = file.getEntry(getEntryName());
    if (entry == null)
    {
      file.close();
      throw new FileNotFoundException(this.name);
    }
    return new FileZip(file, entry);
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    throw new IOException("write");
  }
  
  public void moveTo(FilePath newName, boolean atomicReplace)
  {
    throw DbException.getUnsupportedException("write");
  }
  
  private static String translateFileName(String fileName)
  {
    if (fileName.startsWith("zip:")) {
      fileName = fileName.substring("zip:".length());
    }
    int idx = fileName.indexOf('!');
    if (idx >= 0) {
      fileName = fileName.substring(0, idx);
    }
    return FilePathDisk.expandUserHomeDirectory(fileName);
  }
  
  public FilePath toRealPath()
  {
    return this;
  }
  
  private String getEntryName()
  {
    int idx = this.name.indexOf('!');
    String fileName;
    if (idx <= 0) {
      fileName = "";
    } else {
      fileName = this.name.substring(idx + 1);
    }
    String fileName = fileName.replace('\\', '/');
    if (fileName.startsWith("/")) {
      fileName = fileName.substring(1);
    }
    return fileName;
  }
  
  private ZipFile openZipFile()
    throws IOException
  {
    String fileName = translateFileName(this.name);
    return new ZipFile(fileName);
  }
  
  public FilePath createTempFile(String suffix, boolean deleteOnExit, boolean inTempDir)
    throws IOException
  {
    if (!inTempDir) {
      throw new IOException("File system is read-only");
    }
    return new FilePathDisk().getPath(this.name).createTempFile(suffix, deleteOnExit, true);
  }
  
  public String getScheme()
  {
    return "zip";
  }
}
