package org.h2.store.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class FileRetryOnInterrupt
  extends FileBase
{
  private final String fileName;
  private final String mode;
  private FileChannel channel;
  private FileLockRetry lock;
  
  FileRetryOnInterrupt(String fileName, String mode)
    throws IOException
  {
    this.fileName = fileName;
    this.mode = mode;
    open();
  }
  
  private void open()
    throws IOException
  {
    this.channel = FileUtils.open(this.fileName, this.mode);
  }
  
  private void reopen(int i, IOException e)
    throws IOException
  {
    if (i > 20) {
      throw e;
    }
    if ((!(e instanceof ClosedByInterruptException)) && (!(e instanceof ClosedChannelException))) {
      throw e;
    }
    Thread.interrupted();
    FileChannel before = this.channel;
    synchronized (this)
    {
      if (before == this.channel)
      {
        open();
        reLock();
      }
    }
  }
  
  private void reLock()
    throws IOException
  {
    if (this.lock == null) {
      return;
    }
    try
    {
      this.lock.base.release();
    }
    catch (IOException e) {}
    FileLock l2 = this.channel.tryLock(this.lock.position(), this.lock.size(), this.lock.isShared());
    if (l2 == null) {
      throw new IOException("Re-locking failed");
    }
    this.lock.base = l2;
  }
  
  public void implCloseChannel()
    throws IOException
  {
    try
    {
      this.channel.close();
    }
    catch (IOException e) {}
  }
  
  public long position()
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        return this.channel.position();
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public long size()
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        return this.channel.size();
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public int read(ByteBuffer dst)
    throws IOException
  {
    long pos = position();
    for (int i = 0;; i++) {
      try
      {
        return this.channel.read(dst);
      }
      catch (IOException e)
      {
        reopen(i, e);
        position(pos);
      }
    }
  }
  
  public int read(ByteBuffer dst, long position)
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        return this.channel.read(dst, position);
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public FileChannel position(long pos)
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        this.channel.position(pos);
        return this;
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        this.channel.truncate(newLength);
        return this;
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public void force(boolean metaData)
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        this.channel.force(metaData);
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public int write(ByteBuffer src)
    throws IOException
  {
    long pos = position();
    for (int i = 0;; i++) {
      try
      {
        return this.channel.write(src);
      }
      catch (IOException e)
      {
        reopen(i, e);
        position(pos);
      }
    }
  }
  
  public int write(ByteBuffer src, long position)
    throws IOException
  {
    for (int i = 0;; i++) {
      try
      {
        return this.channel.write(src, position);
      }
      catch (IOException e)
      {
        reopen(i, e);
      }
    }
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    FileLock l = this.channel.tryLock(position, size, shared);
    if (l == null) {
      return null;
    }
    this.lock = new FileLockRetry(l, this);
    return this.lock;
  }
  
  static class FileLockRetry
    extends FileLock
  {
    FileLock base;
    
    protected FileLockRetry(FileLock base, FileChannel channel)
    {
      super(base.position(), base.size(), base.isShared());
      this.base = base;
    }
    
    public boolean isValid()
    {
      return this.base.isValid();
    }
    
    public void release()
      throws IOException
    {
      this.base.release();
    }
  }
  
  public String toString()
  {
    return "retry:" + this.fileName;
  }
}
