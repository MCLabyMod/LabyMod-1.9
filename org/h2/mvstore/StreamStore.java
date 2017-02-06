package org.h2.mvstore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class StreamStore
{
  private final Map<Long, byte[]> map;
  private int minBlockSize = 256;
  private int maxBlockSize = 262144;
  private final AtomicLong nextKey = new AtomicLong();
  private final AtomicReference<byte[]> nextBuffer = new AtomicReference();
  
  public StreamStore(Map<Long, byte[]> map)
  {
    this.map = map;
  }
  
  public Map<Long, byte[]> getMap()
  {
    return this.map;
  }
  
  public void setNextKey(long nextKey)
  {
    this.nextKey.set(nextKey);
  }
  
  public long getNextKey()
  {
    return this.nextKey.get();
  }
  
  public void setMinBlockSize(int minBlockSize)
  {
    this.minBlockSize = minBlockSize;
  }
  
  public int getMinBlockSize()
  {
    return this.minBlockSize;
  }
  
  public void setMaxBlockSize(int maxBlockSize)
  {
    this.maxBlockSize = maxBlockSize;
  }
  
  public long getMaxBlockSize()
  {
    return this.maxBlockSize;
  }
  
  public byte[] put(InputStream in)
    throws IOException
  {
    ByteArrayOutputStream id = new ByteArrayOutputStream();
    int level = 0;
    try
    {
      while (!put(id, in, level)) {
        if (id.size() > this.maxBlockSize / 2)
        {
          id = putIndirectId(id);
          level++;
        }
      }
    }
    catch (IOException e)
    {
      remove(id.toByteArray());
      throw e;
    }
    if (id.size() > this.minBlockSize * 2) {
      id = putIndirectId(id);
    }
    return id.toByteArray();
  }
  
  private boolean put(ByteArrayOutputStream id, InputStream in, int level)
    throws IOException
  {
    if (level > 0)
    {
      ByteArrayOutputStream id2 = new ByteArrayOutputStream();
      for (;;)
      {
        boolean eof = put(id2, in, level - 1);
        if (id2.size() > this.maxBlockSize / 2)
        {
          id2 = putIndirectId(id2);
          id2.writeTo(id);
          return eof;
        }
        if (eof)
        {
          id2.writeTo(id);
          return true;
        }
      }
    }
    byte[] readBuffer = (byte[])this.nextBuffer.getAndSet(null);
    if (readBuffer == null) {
      readBuffer = new byte[this.maxBlockSize];
    }
    byte[] buff = read(in, readBuffer);
    if (buff != readBuffer) {
      this.nextBuffer.set(readBuffer);
    }
    int len = buff.length;
    if (len == 0) {
      return true;
    }
    boolean eof = len < this.maxBlockSize;
    if (len < this.minBlockSize)
    {
      id.write(0);
      DataUtils.writeVarInt(id, len);
      id.write(buff);
    }
    else
    {
      id.write(1);
      DataUtils.writeVarInt(id, len);
      DataUtils.writeVarLong(id, writeBlock(buff));
    }
    return eof;
  }
  
  private static byte[] read(InputStream in, byte[] target)
    throws IOException
  {
    int copied = 0;
    int remaining = target.length;
    while (remaining > 0) {
      try
      {
        int len = in.read(target, copied, remaining);
        if (len < 0) {
          return Arrays.copyOf(target, copied);
        }
        copied += len;
        remaining -= len;
      }
      catch (RuntimeException e)
      {
        throw new IOException(e);
      }
    }
    return target;
  }
  
  private ByteArrayOutputStream putIndirectId(ByteArrayOutputStream id)
    throws IOException
  {
    byte[] data = id.toByteArray();
    id = new ByteArrayOutputStream();
    
    id.write(2);
    DataUtils.writeVarLong(id, length(data));
    DataUtils.writeVarLong(id, writeBlock(data));
    return id;
  }
  
  private long writeBlock(byte[] data)
  {
    long key = getAndIncrementNextKey();
    this.map.put(Long.valueOf(key), data);
    onStore(data.length);
    return key;
  }
  
  protected void onStore(int len) {}
  
  private long getAndIncrementNextKey()
  {
    long key = this.nextKey.getAndIncrement();
    if (!this.map.containsKey(Long.valueOf(key))) {
      return key;
    }
    synchronized (this)
    {
      long low = key;long high = Long.MAX_VALUE;
      while (low < high)
      {
        long x = low + high >>> 1;
        if (this.map.containsKey(Long.valueOf(x))) {
          low = x + 1L;
        } else {
          high = x;
        }
      }
      key = low;
      this.nextKey.set(key + 1L);
      return key;
    }
  }
  
  public long getMaxBlockKey(byte[] id)
  {
    long maxKey = -1L;
    ByteBuffer idBuffer = ByteBuffer.wrap(id);
    while (idBuffer.hasRemaining()) {
      switch (idBuffer.get())
      {
      case 0: 
        int len = DataUtils.readVarInt(idBuffer);
        idBuffer.position(idBuffer.position() + len);
        break;
      case 1: 
        DataUtils.readVarInt(idBuffer);
        long k = DataUtils.readVarLong(idBuffer);
        maxKey = Math.max(maxKey, k);
        break;
      case 2: 
        DataUtils.readVarLong(idBuffer);
        long k2 = DataUtils.readVarLong(idBuffer);
        
        byte[] r = (byte[])this.map.get(Long.valueOf(k2));
        maxKey = Math.max(maxKey, getMaxBlockKey(r));
        break;
      default: 
        throw DataUtils.newIllegalArgumentException("Unsupported id {0}", new Object[] { Arrays.toString(id) });
      }
    }
    return maxKey;
  }
  
  public void remove(byte[] id)
  {
    ByteBuffer idBuffer = ByteBuffer.wrap(id);
    while (idBuffer.hasRemaining()) {
      switch (idBuffer.get())
      {
      case 0: 
        int len = DataUtils.readVarInt(idBuffer);
        idBuffer.position(idBuffer.position() + len);
        break;
      case 1: 
        DataUtils.readVarInt(idBuffer);
        long k = DataUtils.readVarLong(idBuffer);
        this.map.remove(Long.valueOf(k));
        break;
      case 2: 
        DataUtils.readVarLong(idBuffer);
        long k2 = DataUtils.readVarLong(idBuffer);
        
        remove((byte[])this.map.get(Long.valueOf(k2)));
        this.map.remove(Long.valueOf(k2));
        break;
      default: 
        throw DataUtils.newIllegalArgumentException("Unsupported id {0}", new Object[] { Arrays.toString(id) });
      }
    }
  }
  
  public long length(byte[] id)
  {
    ByteBuffer idBuffer = ByteBuffer.wrap(id);
    long length = 0L;
    while (idBuffer.hasRemaining()) {
      switch (idBuffer.get())
      {
      case 0: 
        int len = DataUtils.readVarInt(idBuffer);
        idBuffer.position(idBuffer.position() + len);
        length += len;
        break;
      case 1: 
        length += DataUtils.readVarInt(idBuffer);
        DataUtils.readVarLong(idBuffer);
        break;
      case 2: 
        length += DataUtils.readVarLong(idBuffer);
        DataUtils.readVarLong(idBuffer);
        break;
      default: 
        throw DataUtils.newIllegalArgumentException("Unsupported id {0}", new Object[] { Arrays.toString(id) });
      }
    }
    return length;
  }
  
  public boolean isInPlace(byte[] id)
  {
    ByteBuffer idBuffer = ByteBuffer.wrap(id);
    while (idBuffer.hasRemaining())
    {
      if (idBuffer.get() != 0) {
        return false;
      }
      int len = DataUtils.readVarInt(idBuffer);
      idBuffer.position(idBuffer.position() + len);
    }
    return true;
  }
  
  public InputStream get(byte[] id)
  {
    return new Stream(this, id);
  }
  
  byte[] getBlock(long key)
  {
    byte[] data = (byte[])this.map.get(Long.valueOf(key));
    if (data == null) {
      throw DataUtils.newIllegalStateException(50, "Block {0} not found", new Object[] { Long.valueOf(key) });
    }
    return data;
  }
  
  static class Stream
    extends InputStream
  {
    private final StreamStore store;
    private byte[] oneByteBuffer;
    private ByteBuffer idBuffer;
    private ByteArrayInputStream buffer;
    private long skip;
    private final long length;
    private long pos;
    
    Stream(StreamStore store, byte[] id)
    {
      this.store = store;
      this.length = store.length(id);
      this.idBuffer = ByteBuffer.wrap(id);
    }
    
    public int read()
      throws IOException
    {
      byte[] buffer = this.oneByteBuffer;
      if (buffer == null) {
        buffer = this.oneByteBuffer = new byte[1];
      }
      int len = read(buffer, 0, 1);
      return len == -1 ? -1 : buffer[0] & 0xFF;
    }
    
    public long skip(long n)
    {
      n = Math.min(this.length - this.pos, n);
      if (n == 0L) {
        return 0L;
      }
      if (this.buffer != null)
      {
        long s = this.buffer.skip(n);
        if (s > 0L)
        {
          n = s;
        }
        else
        {
          this.buffer = null;
          this.skip += n;
        }
      }
      else
      {
        this.skip += n;
      }
      this.pos += n;
      return n;
    }
    
    public void close()
    {
      this.buffer = null;
      this.idBuffer.position(this.idBuffer.limit());
      this.pos = this.length;
    }
    
    public int read(byte[] b, int off, int len)
      throws IOException
    {
      if (len <= 0) {
        return 0;
      }
      for (;;)
      {
        if (this.buffer == null)
        {
          try
          {
            this.buffer = nextBuffer();
          }
          catch (IllegalStateException e)
          {
            String msg = DataUtils.formatMessage(50, "Block not found in id {0}", new Object[] { Arrays.toString(this.idBuffer.array()) });
            
            throw new IOException(msg, e);
          }
          if (this.buffer == null) {
            return -1;
          }
        }
        int result = this.buffer.read(b, off, len);
        if (result > 0)
        {
          this.pos += result;
          return result;
        }
        this.buffer = null;
      }
    }
    
    private ByteArrayInputStream nextBuffer()
    {
      while (this.idBuffer.hasRemaining()) {
        switch (this.idBuffer.get())
        {
        case 0: 
          int len = DataUtils.readVarInt(this.idBuffer);
          if (this.skip >= len)
          {
            this.skip -= len;
            this.idBuffer.position(this.idBuffer.position() + len);
          }
          else
          {
            int p = (int)(this.idBuffer.position() + this.skip);
            int l = (int)(len - this.skip);
            this.idBuffer.position(p + l);
            return new ByteArrayInputStream(this.idBuffer.array(), p, l);
          }
          break;
        case 1: 
          int len = DataUtils.readVarInt(this.idBuffer);
          long key = DataUtils.readVarLong(this.idBuffer);
          if (this.skip >= len)
          {
            this.skip -= len;
          }
          else
          {
            byte[] data = this.store.getBlock(key);
            int s = (int)this.skip;
            this.skip = 0L;
            return new ByteArrayInputStream(data, s, data.length - s);
          }
          break;
        case 2: 
          long len = DataUtils.readVarLong(this.idBuffer);
          long key = DataUtils.readVarLong(this.idBuffer);
          if (this.skip >= len)
          {
            this.skip -= len;
          }
          else
          {
            byte[] k = this.store.getBlock(key);
            ByteBuffer newBuffer = ByteBuffer.allocate(k.length + this.idBuffer.limit() - this.idBuffer.position());
            
            newBuffer.put(k);
            newBuffer.put(this.idBuffer);
            newBuffer.flip();
            this.idBuffer = newBuffer;
            return nextBuffer();
          }
          break;
        default: 
          throw DataUtils.newIllegalArgumentException("Unsupported id {0}", new Object[] { Arrays.toString(this.idBuffer.array()) });
        }
      }
      return null;
    }
  }
}
