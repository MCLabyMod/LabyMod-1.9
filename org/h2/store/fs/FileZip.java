package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.h2.util.IOUtils;

class FileZip
  extends FileBase
{
  private static final byte[] SKIP_BUFFER = new byte['Ѐ'];
  private final ZipFile file;
  private final ZipEntry entry;
  private long pos;
  private InputStream in;
  private long inPos;
  private final long length;
  private boolean skipUsingRead;
  
  FileZip(ZipFile file, ZipEntry entry)
  {
    this.file = file;
    this.entry = entry;
    this.length = entry.getSize();
  }
  
  public long position()
  {
    return this.pos;
  }
  
  public long size()
  {
    return this.length;
  }
  
  public int read(ByteBuffer dst)
    throws IOException
  {
    seek();
    int len = this.in.read(dst.array(), dst.arrayOffset() + dst.position(), dst.remaining());
    if (len > 0)
    {
      dst.position(dst.position() + len);
      this.pos += len;
      this.inPos += len;
    }
    return len;
  }
  
  private void seek()
    throws IOException
  {
    if (this.inPos > this.pos)
    {
      if (this.in != null) {
        this.in.close();
      }
      this.in = null;
    }
    if (this.in == null)
    {
      this.in = this.file.getInputStream(this.entry);
      this.inPos = 0L;
    }
    if (this.inPos < this.pos)
    {
      long skip = this.pos - this.inPos;
      if (!this.skipUsingRead) {
        try
        {
          IOUtils.skipFully(this.in, skip);
        }
        catch (NullPointerException e)
        {
          this.skipUsingRead = true;
        }
      }
      if (this.skipUsingRead) {
        while (skip > 0L)
        {
          int s = (int)Math.min(SKIP_BUFFER.length, skip);
          s = this.in.read(SKIP_BUFFER, 0, s);
          skip -= s;
        }
      }
      this.inPos = this.pos;
    }
  }
  
  public FileChannel position(long newPos)
  {
    this.pos = newPos;
    return this;
  }
  
  public FileChannel truncate(long newLength)
    throws IOException
  {
    throw new IOException("File is read-only");
  }
  
  public void force(boolean metaData)
    throws IOException
  {}
  
  public int write(ByteBuffer src)
    throws IOException
  {
    throw new IOException("File is read-only");
  }
  
  public synchronized FileLock tryLock(long position, long size, boolean shared)
    throws IOException
  {
    if (shared) {
      new FileLock((FileChannel)null, position, size, shared)
      {
        public boolean isValid()
        {
          return true;
        }
        
        public void release()
          throws IOException
        {}
      };
    }
    return null;
  }
  
  protected void implCloseChannel()
    throws IOException
  {
    if (this.in != null)
    {
      this.in.close();
      this.in = null;
    }
    this.file.close();
  }
}
