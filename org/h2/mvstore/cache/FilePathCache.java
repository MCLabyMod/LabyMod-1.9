package org.h2.mvstore.cache;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import org.h2.store.fs.FileBase;
import org.h2.store.fs.FilePath;
import org.h2.store.fs.FilePathWrapper;

public class FilePathCache
  extends FilePathWrapper
{
  public static FileChannel wrap(FileChannel f)
  {
    return new FileCache(f);
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    return new FileCache(getBase().open(mode));
  }
  
  public String getScheme()
  {
    return "cache";
  }
  
  public static class FileCache
    extends FileBase
  {
    private static final int CACHE_BLOCK_SIZE = 4096;
    private final FileChannel base;
    private final CacheLongKeyLIRS<ByteBuffer> cache = new CacheLongKeyLIRS(1048576L);
    
    FileCache(FileChannel base)
    {
      this.base = base;
    }
    
    protected void implCloseChannel()
      throws IOException
    {
      this.base.close();
    }
    
    public FileChannel position(long newPosition)
      throws IOException
    {
      this.base.position(newPosition);
      return this;
    }
    
    public long position()
      throws IOException
    {
      return this.base.position();
    }
    
    public int read(ByteBuffer dst)
      throws IOException
    {
      return this.base.read(dst);
    }
    
    public int read(ByteBuffer dst, long position)
      throws IOException
    {
      long cachePos = getCachePos(position);
      int off = (int)(position - cachePos);
      int len = 4096 - off;
      len = Math.min(len, dst.remaining());
      ByteBuffer buff = (ByteBuffer)this.cache.get(cachePos);
      if (buff == null)
      {
        buff = ByteBuffer.allocate(4096);
        long pos = cachePos;
        for (;;)
        {
          int read = this.base.read(buff, pos);
          if (read <= 0) {
            break;
          }
          if (buff.remaining() == 0) {
            break;
          }
          pos += read;
        }
        int read = buff.position();
        if (read == 4096)
        {
          this.cache.put(cachePos, buff, 4096);
        }
        else
        {
          if (read <= 0) {
            return -1;
          }
          len = Math.min(len, read - off);
        }
      }
      dst.put(buff.array(), off, len);
      return len == 0 ? -1 : len;
    }
    
    private static long getCachePos(long pos)
    {
      return pos / 4096L * 4096L;
    }
    
    public long size()
      throws IOException
    {
      return this.base.size();
    }
    
    public FileChannel truncate(long newSize)
      throws IOException
    {
      this.cache.clear();
      this.base.truncate(newSize);
      return this;
    }
    
    public int write(ByteBuffer src, long position)
      throws IOException
    {
      clearCache(src, position);
      return this.base.write(src, position);
    }
    
    public int write(ByteBuffer src)
      throws IOException
    {
      clearCache(src, position());
      return this.base.write(src);
    }
    
    private void clearCache(ByteBuffer src, long position)
    {
      if (this.cache.size() > 0)
      {
        int len = src.remaining();
        long p = getCachePos(position);
        while (len > 0)
        {
          this.cache.remove(p);
          p += 4096L;
          len -= 4096;
        }
      }
    }
    
    public void force(boolean metaData)
      throws IOException
    {
      this.base.force(metaData);
    }
    
    public FileLock tryLock(long position, long size, boolean shared)
      throws IOException
    {
      return this.base.tryLock(position, size, shared);
    }
    
    public String toString()
    {
      return "cache:" + this.base.toString();
    }
  }
}
