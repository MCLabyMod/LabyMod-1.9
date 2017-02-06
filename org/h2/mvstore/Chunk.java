package org.h2.mvstore;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class Chunk
{
  public static final int MAX_ID = 67108863;
  static final int MAX_HEADER_LENGTH = 1024;
  static final int FOOTER_LENGTH = 128;
  public final int id;
  public long block;
  public int len;
  public int pageCount;
  public int pageCountLive;
  public long maxLen;
  public long maxLenLive;
  public int collectPriority;
  public long metaRootPos;
  public long version;
  public long time;
  public long unused;
  public int mapId;
  public long next;
  
  Chunk(int id)
  {
    this.id = id;
  }
  
  static Chunk readChunkHeader(ByteBuffer buff, long start)
  {
    int pos = buff.position();
    byte[] data = new byte[Math.min(buff.remaining(), 1024)];
    buff.get(data);
    try
    {
      for (int i = 0; i < data.length; i++) {
        if (data[i] == 10)
        {
          buff.position(pos + i + 1);
          String s = new String(data, 0, i, DataUtils.LATIN).trim();
          return fromString(s);
        }
      }
    }
    catch (Exception e)
    {
      throw DataUtils.newIllegalStateException(6, "File corrupt reading chunk at position {0}", new Object[] { Long.valueOf(start), e });
    }
    throw DataUtils.newIllegalStateException(6, "File corrupt reading chunk at position {0}", new Object[] { Long.valueOf(start) });
  }
  
  void writeChunkHeader(WriteBuffer buff, int minLength)
  {
    long pos = buff.position();
    buff.put(asString().getBytes(DataUtils.LATIN));
    while (buff.position() - pos < minLength - 1) {
      buff.put((byte)32);
    }
    if ((minLength != 0) && (buff.position() > minLength)) {
      throw DataUtils.newIllegalStateException(3, "Chunk metadata too long", new Object[0]);
    }
    buff.put((byte)10);
  }
  
  static String getMetaKey(int chunkId)
  {
    return "chunk." + Integer.toHexString(chunkId);
  }
  
  public static Chunk fromString(String s)
  {
    HashMap<String, String> map = DataUtils.parseMap(s);
    int id = DataUtils.readHexInt(map, "chunk", 0);
    Chunk c = new Chunk(id);
    c.block = DataUtils.readHexLong(map, "block", 0L);
    c.len = DataUtils.readHexInt(map, "len", 0);
    c.pageCount = DataUtils.readHexInt(map, "pages", 0);
    c.pageCountLive = DataUtils.readHexInt(map, "livePages", c.pageCount);
    c.mapId = DataUtils.readHexInt(map, "map", 0);
    c.maxLen = DataUtils.readHexLong(map, "max", 0L);
    c.maxLenLive = DataUtils.readHexLong(map, "liveMax", c.maxLen);
    c.metaRootPos = DataUtils.readHexLong(map, "root", 0L);
    c.time = DataUtils.readHexLong(map, "time", 0L);
    c.unused = DataUtils.readHexLong(map, "unused", 0L);
    c.version = DataUtils.readHexLong(map, "version", id);
    c.next = DataUtils.readHexLong(map, "next", 0L);
    return c;
  }
  
  public int getFillRate()
  {
    if (this.maxLenLive <= 0L) {
      return 0;
    }
    if (this.maxLenLive == this.maxLen) {
      return 100;
    }
    return 1 + (int)(98L * this.maxLenLive / this.maxLen);
  }
  
  public int hashCode()
  {
    return this.id;
  }
  
  public boolean equals(Object o)
  {
    return ((o instanceof Chunk)) && (((Chunk)o).id == this.id);
  }
  
  public String asString()
  {
    StringBuilder buff = new StringBuilder();
    DataUtils.appendMap(buff, "chunk", Integer.valueOf(this.id));
    DataUtils.appendMap(buff, "block", Long.valueOf(this.block));
    DataUtils.appendMap(buff, "len", Integer.valueOf(this.len));
    if (this.maxLen != this.maxLenLive) {
      DataUtils.appendMap(buff, "liveMax", Long.valueOf(this.maxLenLive));
    }
    if (this.pageCount != this.pageCountLive) {
      DataUtils.appendMap(buff, "livePages", Integer.valueOf(this.pageCountLive));
    }
    DataUtils.appendMap(buff, "map", Integer.valueOf(this.mapId));
    DataUtils.appendMap(buff, "max", Long.valueOf(this.maxLen));
    if (this.next != 0L) {
      DataUtils.appendMap(buff, "next", Long.valueOf(this.next));
    }
    DataUtils.appendMap(buff, "pages", Integer.valueOf(this.pageCount));
    DataUtils.appendMap(buff, "root", Long.valueOf(this.metaRootPos));
    DataUtils.appendMap(buff, "time", Long.valueOf(this.time));
    if (this.unused != 0L) {
      DataUtils.appendMap(buff, "unused", Long.valueOf(this.unused));
    }
    DataUtils.appendMap(buff, "version", Long.valueOf(this.version));
    return buff.toString();
  }
  
  byte[] getFooterBytes()
  {
    StringBuilder buff = new StringBuilder();
    DataUtils.appendMap(buff, "chunk", Integer.valueOf(this.id));
    DataUtils.appendMap(buff, "block", Long.valueOf(this.block));
    DataUtils.appendMap(buff, "version", Long.valueOf(this.version));
    byte[] bytes = buff.toString().getBytes(DataUtils.LATIN);
    int checksum = DataUtils.getFletcher32(bytes, bytes.length);
    DataUtils.appendMap(buff, "fletcher", Integer.valueOf(checksum));
    while (buff.length() < 127) {
      buff.append(' ');
    }
    buff.append("\n");
    return buff.toString().getBytes(DataUtils.LATIN);
  }
  
  public String toString()
  {
    return asString();
  }
}
