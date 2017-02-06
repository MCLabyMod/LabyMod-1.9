package org.h2.store.fs;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.h2.compress.CompressLZF;
import org.h2.util.MathUtils;

class FileMemData
{
  private static final int CACHE_SIZE = 8;
  private static final int BLOCK_SIZE_SHIFT = 10;
  private static final int BLOCK_SIZE = 1024;
  private static final int BLOCK_SIZE_MASK = 1023;
  private static final CompressLZF LZF = new CompressLZF();
  private static final byte[] BUFFER = new byte['ࠀ'];
  private static final byte[] COMPRESSED_EMPTY_BLOCK;
  private static final Cache<CompressItem, CompressItem> COMPRESS_LATER = new Cache(8);
  private String name;
  private final boolean compress;
  private long length;
  private byte[][] data;
  private long lastModified;
  private boolean isReadOnly;
  private boolean isLockedExclusive;
  private int sharedLockCount;
  
  static
  {
    byte[] n = new byte['Ѐ'];
    int len = LZF.compress(n, 1024, BUFFER, 0);
    COMPRESSED_EMPTY_BLOCK = new byte[len];
    System.arraycopy(BUFFER, 0, COMPRESSED_EMPTY_BLOCK, 0, len);
  }
  
  FileMemData(String name, boolean compress)
  {
    this.name = name;
    this.compress = compress;
    this.data = new byte[0][];
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
      FileMemData.CompressItem c = (FileMemData.CompressItem)eldest.getKey();
      FileMemData.compress(c.data, c.page);
      return true;
    }
  }
  
  static class CompressItem
  {
    byte[][] data;
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
  
  private static void compressLater(byte[][] data, int page)
  {
    CompressItem c = new CompressItem();
    c.data = data;
    c.page = page;
    synchronized (LZF)
    {
      COMPRESS_LATER.put(c, c);
    }
  }
  
  private static void expand(byte[][] data, int page)
  {
    byte[] d = data[page];
    if (d.length == 1024) {
      return;
    }
    byte[] out = new byte['Ѐ'];
    if (d != COMPRESSED_EMPTY_BLOCK) {
      synchronized (LZF)
      {
        LZF.expand(d, 0, d.length, out, 0, 1024);
      }
    }
    data[page] = out;
  }
  
  static void compress(byte[][] data, int page)
  {
    byte[] d = data[page];
    synchronized (LZF)
    {
      int len = LZF.compress(d, 1024, BUFFER, 0);
      if (len <= 1024)
      {
        d = new byte[len];
        System.arraycopy(BUFFER, 0, d, 0, len);
        data[page] = d;
      }
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
    long end = MathUtils.roundUpLong(newLength, 1024L);
    if (end != newLength)
    {
      int lastPage = (int)(newLength >>> 10);
      expand(this.data, lastPage);
      byte[] d = this.data[lastPage];
      for (int i = (int)(newLength & 0x3FF); i < 1024; i++) {
        d[i] = 0;
      }
      if (this.compress) {
        compressLater(this.data, lastPage);
      }
    }
  }
  
  private void changeLength(long len)
  {
    this.length = len;
    len = MathUtils.roundUpLong(len, 1024L);
    int blocks = (int)(len >>> 10);
    if (blocks != this.data.length)
    {
      byte[][] n = new byte[blocks][];
      System.arraycopy(this.data, 0, n, 0, Math.min(this.data.length, n.length));
      for (int i = this.data.length; i < blocks; i++) {
        n[i] = COMPRESSED_EMPTY_BLOCK;
      }
      this.data = n;
    }
  }
  
  long readWrite(long pos, byte[] b, int off, int len, boolean write)
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
      int l = (int)Math.min(len, 1024L - (pos & 0x3FF));
      int page = (int)(pos >>> 10);
      expand(this.data, page);
      byte[] block = this.data[page];
      int blockOffset = (int)(pos & 0x3FF);
      if (write) {
        System.arraycopy(b, off, block, blockOffset, l);
      } else {
        System.arraycopy(block, blockOffset, b, off, l);
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
