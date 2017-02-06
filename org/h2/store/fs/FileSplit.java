package org.h2.store.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import org.h2.message.DbException;

class FileSplit
  extends FileBase
{
  private final FilePathSplit file;
  private final String mode;
  private final long maxLength;
  private FileChannel[] list;
  private long filePointer;
  private long length;
  
  FileSplit(FilePathSplit file, String mode, FileChannel[] list, long length, long maxLength)
  {
    this.file = file;
    this.mode = mode;
    this.list = list;
    this.length = length;
    this.maxLength = maxLength;
  }
  
  public void implCloseChannel()
    throws IOException
  {
    for (FileChannel c : this.list) {
      c.close();
    }
  }
  
  public long position()
  {
    return this.filePointer;
  }
  
  public long size()
  {
    return this.length;
  }
  
  public int read(ByteBuffer dst)
    throws IOException
  {
    int len = dst.remaining();
    if (len == 0) {
      return 0;
    }
    len = (int)Math.min(len, this.length - this.filePointer);
    if (len <= 0) {
      return -1;
    }
    long offset = this.filePointer % this.maxLength;
    len = (int)Math.min(len, this.maxLength - offset);
    FileChannel channel = getFileChannel();
    channel.position(offset);
    len = channel.read(dst);
    this.filePointer += len;
    return len;
  }
  
  public FileChannel position(long pos)
  {
    this.filePointer = pos;
    return this;
  }
  
  private FileChannel getFileChannel()
    throws IOException
  {
    int id = (int)(this.filePointer / this.maxLength);
    while (id >= this.list.length)
    {
      int i = this.list.length;
      FileChannel[] newList = new FileChannel[i + 1];
      System.arraycopy(this.list, 0, newList, 0, i);
      FilePath f = this.file.getBase(i);
      newList[i] = f.open(this.mode);
      this.list = newList;
    }
    return this.list[id];
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    if (newLength >= this.length) {
      return this;
    }
    this.filePointer = Math.min(this.filePointer, newLength);
    int newFileCount = 1 + (int)(newLength / this.maxLength);
    if (newFileCount < this.list.length)
    {
      FileChannel[] newList = new FileChannel[newFileCount];
      for (int i = this.list.length - 1; i >= newFileCount; i--)
      {
        this.list[i].truncate(0L);
        this.list[i].close();
        try
        {
          this.file.getBase(i).delete();
        }
        catch (DbException e)
        {
          throw DbException.convertToIOException(e);
        }
      }
      System.arraycopy(this.list, 0, newList, 0, newList.length);
      this.list = newList;
    }
    long size = newLength - this.maxLength * (newFileCount - 1);
    this.list[(this.list.length - 1)].truncate(size);
    this.length = newLength;
    return this;
  }
  
  public void force(boolean metaData)
    throws IOException
  {
    for (FileChannel c : this.list) {
      c.force(metaData);
    }
  }
  
  public int write(ByteBuffer src)
    throws IOException
  {
    if ((this.filePointer >= this.length) && (this.filePointer > this.maxLength))
    {
      long oldFilePointer = this.filePointer;
      for (long x = this.length - this.length % this.maxLength + this.maxLength; x < this.filePointer; x += this.maxLength)
      {
        if (x > this.length)
        {
          position(x - 1L);
          write(ByteBuffer.wrap(new byte[1]));
        }
        this.filePointer = oldFilePointer;
      }
    }
    long offset = this.filePointer % this.maxLength;
    int len = src.remaining();
    FileChannel channel = getFileChannel();
    channel.position(offset);
    int l = (int)Math.min(len, this.maxLength - offset);
    if (l == len)
    {
      l = channel.write(src);
    }
    else
    {
      int oldLimit = src.limit();
      src.limit(src.position() + l);
      l = channel.write(src);
      src.limit(oldLimit);
    }
    this.filePointer += l;
    this.length = Math.max(this.length, this.filePointer);
    return l;
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    return this.list[0].tryLock(position, size, shared);
  }
  
  public String toString()
  {
    return this.file.toString();
  }
}
