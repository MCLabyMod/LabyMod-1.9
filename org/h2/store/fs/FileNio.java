package org.h2.store.fs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;

class FileNio
  extends FileBase
{
  private final String name;
  private final FileChannel channel;
  
  FileNio(String fileName, String mode)
    throws IOException
  {
    this.name = fileName;
    this.channel = new RandomAccessFile(fileName, mode).getChannel();
  }
  
  public void implCloseChannel()
    throws IOException
  {
    this.channel.close();
  }
  
  public long position()
    throws IOException
  {
    return this.channel.position();
  }
  
  public long size()
    throws IOException
  {
    return this.channel.size();
  }
  
  public int read(ByteBuffer dst)
    throws IOException
  {
    return this.channel.read(dst);
  }
  
  public FileChannel position(long pos)
    throws IOException
  {
    this.channel.position(pos);
    return this;
  }
  
  public int read(ByteBuffer dst, long position)
    throws IOException
  {
    return this.channel.read(dst, position);
  }
  
  public int write(ByteBuffer src, long position)
    throws IOException
  {
    return this.channel.write(src, position);
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    long size = this.channel.size();
    if (newLength < size)
    {
      long pos = this.channel.position();
      this.channel.truncate(newLength);
      long newPos = this.channel.position();
      if (pos < newLength)
      {
        if (newPos != pos) {
          this.channel.position(pos);
        }
      }
      else if (newPos > newLength) {
        this.channel.position(newLength);
      }
    }
    return this;
  }
  
  public void force(boolean metaData)
    throws IOException
  {
    this.channel.force(metaData);
  }
  
  public int write(ByteBuffer src)
    throws IOException
  {
    try
    {
      return this.channel.write(src);
    }
    catch (NonWritableChannelException e)
    {
      throw new IOException("read only");
    }
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    return this.channel.tryLock(position, size, shared);
  }
  
  public String toString()
  {
    return "nio:" + this.name;
  }
}
