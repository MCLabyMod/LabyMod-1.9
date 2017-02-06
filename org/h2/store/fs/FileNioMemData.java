package org.h2.store.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.h2.compress.CompressLZF;
import org.h2.util.MathUtils;

class FileNioMemData
{
  private static final int CACHE_SIZE = 8;
  private static final int BLOCK_SIZE_SHIFT = 16;
  private static final int BLOCK_SIZE = 65536;
  private static final int BLOCK_SIZE_MASK = 65535;
  private static final CompressLZF LZF = new CompressLZF();
  private static final byte[] BUFFER = new byte[131072];
  private static final ByteBuffer COMPRESSED_EMPTY_BLOCK;
  private static final Cache<CompressItem, CompressItem> COMPRESS_LATER = new Cache(8);
  private String name;
  private final boolean compress;
  private long length;
  private ByteBuffer[] data;
  private long lastModified;
  private boolean isReadOnly;
  private boolean isLockedExclusive;
  private int sharedLockCount;
  
  static
  {
    byte[] n = new byte[65536];
    int len = LZF.compress(n, 65536, BUFFER, 0);
    COMPRESSED_EMPTY_BLOCK = ByteBuffer.allocateDirect(len);
    COMPRESSED_EMPTY_BLOCK.put(BUFFER, 0, len);
  }
  
  FileNioMemData(String name, boolean compress)
  {
    this.name = name;
    this.compress = compress;
    this.data = new ByteBuffer[0];
    this.lastModified = System.currentTimeMillis();
  }
  
  synchronized boolean lockExclusive()
  {
    if ((this.sharedLockCount > 0) || (this.isLockedExclusive)) {
      return false;
    }
    this.isLockedExclusive = true;
    return true;
  }
  
  synchronized boolean lockShared()
  {
    if (this.isLockedExclusive) {
      return false;
    }
    this.sharedLockCount += 1;
    return true;
  }
  
  synchronized void unlock()
  {
    if (this.isLockedExclusive) {
      this.isLockedExclusive = false;
    } else {
      this.sharedLockCount = Math.max(0, this.sharedLockCount - 1);
    }
  }
  
  static class Cache<K, V>
    extends LinkedHashMap<K, V>
  {
    private static final long serialVersionUID = 1L;
    private final int size;
    
    Cache(int size)
    {
      super(0.75F, true);
      this.size = size;
    }
    
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
      if (size() < this.size) {
        return false;
      }
      FileNioMemData.CompressItem c = (FileNioMemData.CompressItem)eldest.getKey();
      FileNioMemData.compress(c.data, c.page);
      return true;
    }
  }
  
  static class CompressItem
  {
    ByteBuffer[] data;
    int page;
    
    public int hashCode()
    {
      return this.page;
    }
    
    public boolean equals(Object o)
    {
      if ((o instanceof CompressItem))
      {
        CompressItem c = (CompressItem)o;
        return (c.data == this.data) && (c.page == this.page);
      }
      return false;
    }
  }
  
  private static void compressLater(ByteBuffer[] data, int page)
  {
    CompressItem c = new CompressItem();
    c.data = data;
    c.page = page;
    synchronized (LZF)
    {
      COMPRESS_LATER.put(c, c);
    }
  }
  
  private static void expand(ByteBuffer[] data, int page)
  {
    ByteBuffer d = data[page];
    if (d.capacity() == 65536) {
      return;
    }
    ByteBuffer out = ByteBuffer.allocateDirect(65536);
    if (d != COMPRESSED_EMPTY_BLOCK) {
      synchronized (LZF)
      {
        d.position(0);
        CompressLZF.expand(d, out);
      }
    }
    data[page] = out;
  }
  
  static void compress(ByteBuffer[] data, int page)
  {
    ByteBuffer d = data[page];
    synchronized (LZF)
    {
      int len = LZF.compress(d, 0, BUFFER, 0);
      d = ByteBuffer.allocateDirect(len);
      d.put(BUFFER, 0, len);
      data[page] = d;
    }
  }
  
  void touch(boolean openReadOnly)
    throws IOException
  {
    if ((this.isReadOnly) || (openReadOnly)) {
      throw new IOException("Read only");
    }
    this.lastModified = System.currentTimeMillis();
  }
  
  long length()
  {
    return this.length;
  }
  
  void truncate(long newLength)
  {
    changeLength(newLength);
    long end = MathUtils.roundUpLong(newLength, 65536L);
    if (end != newLength)
    {
      int lastPage = (int)(newLength >>> 16);
      expand(this.data, lastPage);
      ByteBuffer d = this.data[lastPage];
      for (int i = (int)(newLength & 0xFFFF); i < 65536; i++) {
        d.put(i, (byte)0);
      }
      if (this.compress) {
        compressLater(this.data, lastPage);
      }
    }
  }
  
  private void changeLength(long len)
  {
    this.length = len;
    len = MathUtils.roundUpLong(len, 65536L);
    int blocks = (int)(len >>> 16);
    if (blocks != this.data.length)
    {
      ByteBuffer[] n = new ByteBuffer[blocks];
      System.arraycopy(this.data, 0, n, 0, Math.min(this.data.length, n.length));
      for (int i = this.data.length; i < blocks; i++) {
        n[i] = COMPRESSED_EMPTY_BLOCK;
      }
      this.data = n;
    }
  }
  
  long readWrite(long pos, ByteBuffer b, int off, int len, boolean write)
  {
    long end = pos + len;
    if (end > this.length) {
      if (write) {
        changeLength(end);
      } else {
        len = (int)(this.length - pos);
      }
    }
    while (len > 0)
    {
      int l = (int)Math.min(len, 65536L - (pos & 0xFFFF));
      int page = (int)(pos >>> 16);
      expand(this.data, page);
      ByteBuffer block = this.data[page];
      int blockOffset = (int)(pos & 0xFFFF);
      if (write)
      {
        ByteBuffer tmp = b.slice();
        tmp.position(off);
        tmp.limit(off + l);
        block.position(blockOffset);
        block.put(tmp);
      }
      else
      {
        block.position(blockOffset);
        ByteBuffer tmp = block.slice();
        tmp.limit(l);
        int oldPosition = b.position();
        b.position(off);
        b.put(tmp);
        
        b.position(oldPosition);
      }
      if (this.compress) {
        compressLater(this.data, page);
      }
      off += l;
      pos += l;
      len -= l;
    }
    return pos;
  }
  
  void setName(String name)
  {
    this.name = name;
  }
  
  String getName()
  {
    return this.name;
  }
  
  long getLastModified()
  {
    return this.lastModified;
  }
  
  boolean canWrite()
  {
    return !this.isReadOnly;
  }
  
  boolean setReadOnly()
  {
    this.isReadOnly = true;
    return true;
  }
}
