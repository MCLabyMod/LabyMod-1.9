package org.h2.store.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class FileRec
  extends FileBase
{
  private final FilePathRec rec;
  private final FileChannel channel;
  private final String name;
  
  FileRec(FilePathRec rec, FileChannel file, String fileName)
  {
    this.rec = rec;
    this.channel = file;
    this.name = fileName;
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
  
  public int read(ByteBuffer dst, long position)
    throws IOException
  {
    return this.channel.read(dst, position);
  }
  
  public FileChannel position(long pos)
    throws IOException
  {
    this.channel.position(pos);
    return this;
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    this.rec.log(7, this.name, null, newLength);
    this.channel.truncate(newLength);
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
    byte[] buff = src.array();
    int len = src.remaining();
    if ((src.position() != 0) || (len != buff.length))
    {
      byte[] b = new byte[len];
      System.arraycopy(buff, src.arrayOffset() + src.position(), b, 0, len);
      buff = b;
    }
    int result = this.channel.write(src);
    this.rec.log(8, this.name, buff, this.channel.position());
    return result;
  }
  
  public int write(ByteBuffer src, long position)
    throws IOException
  {
    byte[] buff = src.array();
    int len = src.remaining();
    if ((src.position() != 0) || (len != buff.length))
    {
      byte[] b = new byte[len];
      System.arraycopy(buff, src.arrayOffset() + src.position(), b, 0, len);
      buff = b;
    }
    int result = this.channel.write(src, position);
    this.rec.log(8, this.name, buff, position);
    return result;
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    return this.channel.tryLock(position, size, shared);
  }
  
  public String toString()
  {
    return this.name;
  }
}
