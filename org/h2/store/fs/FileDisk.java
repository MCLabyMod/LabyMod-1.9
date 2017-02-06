package org.h2.store.fs;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;
import org.h2.engine.SysProperties;

class FileDisk
  extends FileBase
{
  private final RandomAccessFile file;
  private final String name;
  private final boolean readOnly;
  
  FileDisk(String fileName, String mode)
    throws FileNotFoundException
  {
    this.file = new RandomAccessFile(fileName, mode);
    this.name = fileName;
    this.readOnly = mode.equals("r");
  }
  
  public void force(boolean metaData)
    throws IOException
  {
    String m = SysProperties.SYNC_METHOD;
    if (!"".equals(m)) {
      if ("sync".equals(m)) {
        this.file.getFD().sync();
      } else if ("force".equals(m)) {
        this.file.getChannel().force(true);
      } else if ("forceFalse".equals(m)) {
        this.file.getChannel().force(false);
      } else {
        this.file.getFD().sync();
      }
    }
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    if (this.readOnly) {
      throw new NonWritableChannelException();
    }
    if (newLength < this.file.length()) {
      this.file.setLength(newLength);
    }
    return this;
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    return this.file.getChannel().tryLock(position, size, shared);
  }
  
  public void implCloseChannel()
    throws IOException
  {
    this.file.close();
  }
  
  public long position()
    throws IOException
  {
    return this.file.getFilePointer();
  }
  
  public long size()
    throws IOException
  {
    return this.file.length();
  }
  
  public int read(ByteBuffer dst)
    throws IOException
  {
    int len = this.file.read(dst.array(), dst.arrayOffset() + dst.position(), dst.remaining());
    if (len > 0) {
      dst.position(dst.position() + len);
    }
    return len;
  }
  
  public FileChannel position(long pos)
    throws IOException
  {
    this.file.seek(pos);
    return this;
  }
  
  public int write(ByteBuffer src)
    throws IOException
  {
    int len = src.remaining();
    this.file.write(src.array(), src.arrayOffset() + src.position(), len);
    src.position(src.position() + len);
    return len;
  }
  
  public String toString()
  {
    return this.name;
  }
}
