package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

public abstract class FilePathWrapper
  extends FilePath
{
  private FilePath base;
  
  public FilePathWrapper getPath(String path)
  {
    return create(path, unwrap(path));
  }
  
  public FilePathWrapper wrap(FilePath base)
  {
    return base == null ? null : create(getPrefix() + base.name, base);
  }
  
  public FilePath unwrap()
  {
    return unwrap(this.name);
  }
  
  private FilePathWrapper create(String path, FilePath base)
  {
    try
    {
      FilePathWrapper p = (FilePathWrapper)getClass().newInstance();
      p.name = path;
      p.base = base;
      return p;
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("Path: " + path, e);
    }
  }
  
  protected String getPrefix()
  {
    return getScheme() + ":";
  }
  
  protected FilePath unwrap(String path)
  {
    return FilePath.get(path.substring(getScheme().length() + 1));
  }
  
  protected FilePath getBase()
  {
    return this.base;
  }
  
  public boolean canWrite()
  {
    return this.base.canWrite();
  }
  
  public void createDirectory()
  {
    this.base.createDirectory();
  }
  
  public boolean createFile()
  {
    return this.base.createFile();
  }
  
  public void delete()
  {
    this.base.delete();
  }
  
  public boolean exists()
  {
    return this.base.exists();
  }
  
  public FilePath getParent()
  {
    return wrap(this.base.getParent());
  }
  
  public boolean isAbsolute()
  {
    return this.base.isAbsolute();
  }
  
  public boolean isDirectory()
  {
    return this.base.isDirectory();
  }
  
  public long lastModified()
  {
    return this.base.lastModified();
  }
  
  public FilePath toRealPath()
  {
    return wrap(this.base.toRealPath());
  }
  
  public List<FilePath> newDirectoryStream()
  {
    List<FilePath> list = this.base.newDirectoryStream();
    int i = 0;
    for (int len = list.size(); i < len; i++) {
      list.set(i, wrap((FilePath)list.get(i)));
    }
    return list;
  }
  
  public void moveTo(FilePath newName, boolean atomicReplace)
  {
    this.base.moveTo(((FilePathWrapper)newName).base, atomicReplace);
  }
  
  public InputStream newInputStream()
    throws IOException
  {
    return this.base.newInputStream();
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    return this.base.newOutputStream(append);
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    return this.base.open(mode);
  }
  
  public boolean setReadOnly()
  {
    return this.base.setReadOnly();
  }
  
  public long size()
  {
    return this.base.size();
  }
  
  public FilePath createTempFile(String suffix, boolean deleteOnExit, boolean inTempDir)
    throws IOException
  {
    return wrap(this.base.createTempFile(suffix, deleteOnExit, inTempDir));
  }
}
