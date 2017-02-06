package org.h2.store.fs;

import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;
import org.h2.engine.SysProperties;

class FileNioMapped
  extends FileBase
{
  private static final long GC_TIMEOUT_MS = 10000L;
  private final String name;
  private final FileChannel.MapMode mode;
  private RandomAccessFile file;
  private MappedByteBuffer mapped;
  private long fileLength;
  private int pos;
  
  FileNioMapped(String fileName, String mode)
    throws IOException
  {
    if ("r".equals(mode)) {
      this.mode = FileChannel.MapMode.READ_ONLY;
    } else {
      this.mode = FileChannel.MapMode.READ_WRITE;
    }
    this.name = fileName;
    this.file = new RandomAccessFile(fileName, mode);
    reMap();
  }
  
  private void unMap()
    throws IOException
  {
    if (this.mapped == null) {
      return;
    }
    this.mapped.force();
    
    boolean useSystemGc = true;
    if (SysProperties.NIO_CLEANER_HACK) {
      try
      {
        Method cleanerMethod = this.mapped.getClass().getMethod("cleaner", new Class[0]);
        cleanerMethod.setAccessible(true);
        Object cleaner = cleanerMethod.invoke(this.mapped, new Object[0]);
        if (cleaner != null)
        {
          Method clearMethod = cleaner.getClass().getMethod("clean", new Class[0]);
          clearMethod.invoke(cleaner, new Object[0]);
        }
        useSystemGc = false;
      }
      catch (Throwable e) {}finally
      {
        this.mapped = null;
      }
    }
    if (useSystemGc)
    {
      WeakReference<MappedByteBuffer> bufferWeakRef = new WeakReference(this.mapped);
      
      this.mapped = null;
      long start = System.currentTimeMillis();
      while (bufferWeakRef.get() != null)
      {
        if (System.currentTimeMillis() - start > 10000L) {
          throw new IOException("Timeout (10000 ms) reached while trying to GC mapped buffer");
        }
        System.gc();
        Thread.yield();
      }
    }
  }
  
  private void reMap()
    throws IOException
  {
    int oldPos = 0;
    if (this.mapped != null)
    {
      oldPos = this.pos;
      unMap();
    }
    this.fileLength = this.file.length();
    checkFileSizeLimit(this.fileLength);
    
    this.mapped = this.file.getChannel().map(this.mode, 0L, this.fileLength);
    int limit = this.mapped.limit();
    int capacity = this.mapped.capacity();
    if ((limit < this.fileLength) || (capacity < this.fileLength)) {
      throw new IOException("Unable to map: length=" + limit + " capacity=" + capacity + " length=" + this.fileLength);
    }
    if (SysProperties.NIO_LOAD_MAPPED) {
      this.mapped.load();
    }
    this.pos = Math.min(oldPos, (int)this.fileLength);
  }
  
  private static void checkFileSizeLimit(long length)
    throws IOException
  {
    if (length > 2147483647L) {
      throw new IOException("File over 2GB is not supported yet when using this file system");
    }
  }
  
  public void implCloseChannel()
    throws IOException
  {
    if (this.file != null)
    {
      unMap();
      this.file.close();
      this.file = null;
    }
  }
  
  public long position()
  {
    return this.pos;
  }
  
  public String toString()
  {
    return "nioMapped:" + this.name;
  }
  
  public synchronized long size()
    throws IOException
  {
    return this.fileLength;
  }
  
  public synchronized int read(ByteBuffer dst)
    throws IOException
  {
    try
    {
      int len = dst.remaining();
      if (len == 0) {
        return 0;
      }
      len = (int)Math.min(len, this.fileLength - this.pos);
      if (len <= 0) {
        return -1;
      }
      this.mapped.position(this.pos);
      this.mapped.get(dst.array(), dst.arrayOffset() + dst.position(), len);
      dst.position(dst.position() + len);
      this.pos += len;
      return len;
    }
    catch (IllegalArgumentException e)
    {
      EOFException e2 = new EOFException("EOF");
      e2.initCause(e);
      throw e2;
    }
    catch (BufferUnderflowException e)
    {
      EOFException e2 = new EOFException("EOF");
      e2.initCause(e);
      throw e2;
    }
  }
  
  public FileChannel position(long pos)
    throws IOException
  {
    checkFileSizeLimit(pos);
    this.pos = ((int)pos);
    return this;
  }
  
  public synchronized FileChannel truncate(long newLength)
    throws IOException
  {
    if (this.mode == FileChannel.MapMode.READ_ONLY) {
      throw new NonWritableChannelException();
    }
    if (newLength < size()) {
      setFileLength(newLength);
    }
    return this;
  }
  
  public synchronized void setFileLength(long newLength)
    throws IOException
  {
    checkFileSizeLimit(newLength);
    int oldPos = this.pos;
    unMap();
    for (int i = 0;; i++) {
      try
      {
        this.file.setLength(newLength);
      }
      catch (IOException e)
      {
        if ((i > 16) || (e.toString().indexOf("user-mapped section open") < 0)) {
          throw e;
        }
        System.gc();
      }
    }
    reMap();
    this.pos = ((int)Math.min(newLength, oldPos));
  }
  
  public void force(boolean metaData)
    throws IOException
  {
    this.mapped.force();
    this.file.getFD().sync();
  }
  
  public synchronized int write(ByteBuffer src)
    throws IOException
  {
    int len = src.remaining();
    if (this.mapped.capacity() < this.pos + len) {
      setFileLength(this.pos + len);
    }
    this.mapped.position(this.pos);
    this.mapped.put(src);
    this.pos += len;
    return len;
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    return this.file.getChannel().tryLock(position, size, shared);
  }
}
