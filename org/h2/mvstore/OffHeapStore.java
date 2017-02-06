package org.h2.mvstore;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class OffHeapStore
  extends FileStore
{
  private final TreeMap<Long, ByteBuffer> memory = new TreeMap();
  
  public void open(String fileName, boolean readOnly, char[] encryptionKey)
  {
    this.memory.clear();
  }
  
  public String toString()
  {
    return this.memory.toString();
  }
  
  public ByteBuffer readFully(long pos, int len)
  {
    Map.Entry<Long, ByteBuffer> memEntry = this.memory.floorEntry(Long.valueOf(pos));
    if (memEntry == null) {
      throw DataUtils.newIllegalStateException(1, "Could not read from position {0}", new Object[] { Long.valueOf(pos) });
    }
    this.readCount += 1L;
    this.readBytes += len;
    ByteBuffer buff = (ByteBuffer)memEntry.getValue();
    ByteBuffer read = buff.duplicate();
    int offset = (int)(pos - ((Long)memEntry.getKey()).longValue());
    read.position(offset);
    read.limit(len + offset);
    return read.slice();
  }
  
  public void free(long pos, int length)
  {
    this.freeSpace.free(pos, length);
    ByteBuffer buff = (ByteBuffer)this.memory.remove(Long.valueOf(pos));
    if (buff != null) {
      if (buff.remaining() != length) {
        throw DataUtils.newIllegalStateException(1, "Partial remove is not supported at position {0}", new Object[] { Long.valueOf(pos) });
      }
    }
  }
  
  public void writeFully(long pos, ByteBuffer src)
  {
    this.fileSize = Math.max(this.fileSize, pos + src.remaining());
    Map.Entry<Long, ByteBuffer> mem = this.memory.floorEntry(Long.valueOf(pos));
    if (mem == null)
    {
      writeNewEntry(pos, src);
      return;
    }
    long prevPos = ((Long)mem.getKey()).longValue();
    ByteBuffer buff = (ByteBuffer)mem.getValue();
    int prevLength = buff.capacity();
    int length = src.remaining();
    if (prevPos == pos)
    {
      if (prevLength != length) {
        throw DataUtils.newIllegalStateException(1, "Could not write to position {0}; partial overwrite is not supported", new Object[] { Long.valueOf(pos) });
      }
      this.writeCount += 1L;
      this.writeBytes += length;
      buff.rewind();
      buff.put(src);
      return;
    }
    if (prevPos + prevLength > pos) {
      throw DataUtils.newIllegalStateException(1, "Could not write to position {0}; partial overwrite is not supported", new Object[] { Long.valueOf(pos) });
    }
    writeNewEntry(pos, src);
  }
  
  private void writeNewEntry(long pos, ByteBuffer src)
  {
    int length = src.remaining();
    this.writeCount += 1L;
    this.writeBytes += length;
    ByteBuffer buff = ByteBuffer.allocateDirect(length);
    buff.put(src);
    buff.rewind();
    this.memory.put(Long.valueOf(pos), buff);
  }
  
  public void truncate(long size)
  {
    this.writeCount += 1L;
    if (size == 0L)
    {
      this.fileSize = 0L;
      this.memory.clear();
      return;
    }
    this.fileSize = size;
    for (Iterator<Long> it = this.memory.keySet().iterator(); it.hasNext();)
    {
      long pos = ((Long)it.next()).longValue();
      if (pos < size) {
        break;
      }
      ByteBuffer buff = (ByteBuffer)this.memory.get(Long.valueOf(pos));
      if (buff.capacity() > size) {
        throw DataUtils.newIllegalStateException(1, "Could not truncate to {0}; partial truncate is not supported", new Object[] { Long.valueOf(pos) });
      }
      it.remove();
    }
  }
  
  public void close()
  {
    this.memory.clear();
  }
  
  public void sync() {}
  
  public int getDefaultRetentionTime()
  {
    return 0;
  }
}
