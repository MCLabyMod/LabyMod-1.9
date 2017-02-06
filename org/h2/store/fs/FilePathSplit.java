package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.util.New;

public class FilePathSplit
  extends FilePathWrapper
{
  private static final String PART_SUFFIX = ".part";
  
  protected String getPrefix()
  {
    return getScheme() + ":" + parse(this.name)[0] + ":";
  }
  
  public FilePath unwrap(String fileName)
  {
    return FilePath.get(parse(fileName)[1]);
  }
  
  public boolean setReadOnly()
  {
    boolean result = false;
    for (int i = 0;; i++)
    {
      FilePath f = getBase(i);
      if (!f.exists()) {
        break;
      }
      result = f.setReadOnly();
    }
    return result;
  }
  
  public void delete()
  {
    for (int i = 0;; i++)
    {
      FilePath f = getBase(i);
      if (!f.exists()) {
        break;
      }
      f.delete();
    }
  }
  
  public long lastModified()
  {
    long lastModified = 0L;
    for (int i = 0;; i++)
    {
      FilePath f = getBase(i);
      if (!f.exists()) {
        break;
      }
      long l = f.lastModified();
      lastModified = Math.max(lastModified, l);
    }
    return lastModified;
  }
  
  public long size()
  {
    long length = 0L;
    for (int i = 0;; i++)
    {
      FilePath f = getBase(i);
      if (!f.exists()) {
        break;
      }
      length += f.size();
    }
    return length;
  }
  
  public ArrayList<FilePath> newDirectoryStream()
  {
    List<FilePath> list = getBase().newDirectoryStream();
    ArrayList<FilePath> newList = New.arrayList();
    int i = 0;
    for (int size = list.size(); i < size; i++)
    {
      FilePath f = (FilePath)list.get(i);
      if (!f.getName().endsWith(".part")) {
        newList.add(wrap(f));
      }
    }
    return newList;
  }
  
  public InputStream newInputStream()
    throws IOException
  {
    InputStream input = getBase().newInputStream();
    for (int i = 1;; i++)
    {
      FilePath f = getBase(i);
      if (!f.exists()) {
        break;
      }
      InputStream i2 = f.newInputStream();
      input = new SequenceInputStream(input, i2);
    }
    return input;
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    ArrayList<FileChannel> list = New.arrayList();
    list.add(getBase().open(mode));
    for (int i = 1;; i++)
    {
      FilePath f = getBase(i);
      if (!f.exists()) {
        break;
      }
      list.add(f.open(mode));
    }
    FileChannel[] array = new FileChannel[list.size()];
    list.toArray(array);
    long maxLength = array[0].size();
    long length = maxLength;
    if (array.length == 1)
    {
      long defaultMaxLength = getDefaultMaxLength();
      if (maxLength < defaultMaxLength) {
        maxLength = defaultMaxLength;
      }
    }
    else
    {
      if (maxLength == 0L) {
        closeAndThrow(0, array, array[0], maxLength);
      }
      for (int i = 1; i < array.length - 1; i++)
      {
        FileChannel c = array[i];
        long l = c.size();
        length += l;
        if (l != maxLength) {
          closeAndThrow(i, array, c, maxLength);
        }
      }
      FileChannel c = array[(array.length - 1)];
      long l = c.size();
      length += l;
      if (l > maxLength) {
        closeAndThrow(array.length - 1, array, c, maxLength);
      }
    }
    return new FileSplit(this, mode, array, length, maxLength);
  }
  
  private long getDefaultMaxLength()
  {
    return 1L << Integer.decode(parse(this.name)[0]).intValue();
  }
  
  private void closeAndThrow(int id, FileChannel[] array, FileChannel o, long maxLength)
    throws IOException
  {
    String message = "Expected file length: " + maxLength + " got: " + o.size() + " for " + getName(id);
    for (FileChannel f : array) {
      f.close();
    }
    throw new IOException(message);
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    return new FileChannelOutputStream(open("rw"), append);
  }
  
  public void moveTo(FilePath path, boolean atomicReplace)
  {
    FilePathSplit newName = (FilePathSplit)path;
    for (int i = 0;; i++)
    {
      FilePath o = getBase(i);
      if (!o.exists()) {
        break;
      }
      o.moveTo(newName.getBase(i), atomicReplace);
    }
  }
  
  private String[] parse(String fileName)
  {
    if (!fileName.startsWith(getScheme())) {
      DbException.throwInternalError(fileName + " doesn't start with " + getScheme());
    }
    fileName = fileName.substring(getScheme().length() + 1);
    String size;
    if ((fileName.length() > 0) && (Character.isDigit(fileName.charAt(0))))
    {
      int idx = fileName.indexOf(':');
      String size = fileName.substring(0, idx);
      try
      {
        fileName = fileName.substring(idx + 1);
      }
      catch (NumberFormatException e) {}
    }
    else
    {
      size = Long.toString(SysProperties.SPLIT_FILE_SIZE_SHIFT);
    }
    return new String[] { size, fileName };
  }
  
  FilePath getBase(int id)
  {
    return FilePath.get(getName(id));
  }
  
  private String getName(int id)
  {
    return id > 0 ? getBase().name + "." + id + ".part" : getBase().name;
  }
  
  public String getScheme()
  {
    return "split";
  }
}
