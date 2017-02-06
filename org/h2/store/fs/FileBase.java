package org.h2.store.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public abstract class FileBase
  extends FileChannel
{
  public abstract long size()
    throws IOException;
  
  public abstract long position()
    throws IOException;
  
  public abstract FileChannel position(long paramLong)
    throws IOException;
  
  public abstract int read(ByteBuffer paramByteBuffer)
    throws IOException;
  
  public abstract int write(ByteBuffer paramByteBuffer)
    throws IOException;
  
  public synchronized int read(ByteBuffer dst, long position)
    throws IOException
  {
    long oldPos = position();
    position(position);
    int len = read(dst);
    position(oldPos);
    return len;
  }
  
  public synchronized int write(ByteBuffer src, long position)
    throws IOException
  {
    long oldPos = position();
    position(position);
    int len = write(src);
    position(oldPos);
    return len;
  }
  
  public abstract FileChannel truncate(long paramLong)
    throws IOException;
  
  public void force(boolean metaData)
    throws IOException
  {}
  
  protected void implCloseChannel()
    throws IOException
  {}
  
  public FileLock lock(long position, long size, boolean shared)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public MappedByteBuffer map(FileChannel.MapMode mode, long position, long size)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public long read(ByteBuffer[] dsts, int offset, int length)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public long transferFrom(ReadableByteChannel src, long position, long count)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public long transferTo(long position, long count, WritableByteChannel target)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public long write(ByteBuffer[] srcs, int offset, int length)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
}
