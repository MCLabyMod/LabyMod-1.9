package org.h2.store.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;

class FileNioMem
  extends FileBase
{
  final FileNioMemData data;
  private final boolean readOnly;
  private long pos;
  
  FileNioMem(FileNioMemData data, boolean readOnly)
  {
    this.data = data;
    this.readOnly = readOnly;
  }
  
  public long size()
  {
    return this.data.length();
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    if (this.readOnly) {
      throw new NonWritableChannelException();
    }
    if (newLength < size())
    {
      this.data.touch(this.readOnly);
      this.pos = Math.min(this.pos, newLength);
      this.data.truncate(newLength);
    }
    return this;
  }
  
  public FileChannel position(long newPos)
  {
    this.pos = ((int)newPos);
    return this;
  }
  
  public int write(ByteBuffer src)
    throws IOException
  {
    int len = src.remaining();
    if (len == 0) {
      return 0;
    }
    this.data.touch(this.readOnly);
    
    this.pos = this.data.readWrite(this.pos, src, 0, len, true);
    src.position(src.position() + len);
    return len;
  }
  
  public int read(ByteBuffer dst)
    throws IOException
  {
    int len = dst.remaining();
    if (len == 0) {
      return 0;
    }
    long newPos = this.data.readWrite(this.pos, dst, dst.position(), len, false);
    len = (int)(newPos - this.pos);
    if (len <= 0) {
      return -1;
    }
    dst.position(dst.position() + len);
    this.pos = newPos;
    return len;
  }
  
  public long position()
  {
    return this.pos;
  }
  
  public void implCloseChannel()
    throws IOException
  {
    this.pos = 0L;
  }
  
  public void force(boolean metaData)
    throws IOException
  {}
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    if (shared)
    {
      if (!this.data.lockShared()) {
        return null;
      }
    }
    else if (!this.data.lockExclusive()) {
      return null;
    }
    FileLock lock = new FileLock((FileChannel)null, position, size, shared)
    {
      public boolean isValid()
      {
        return true;
      }
      
      public void release()
        throws IOException
      {
        FileNioMem.this.data.unlock();
      }
    };
    return lock;
  }
  
  public String toString()
  {
    return this.data.getName();
  }
}
