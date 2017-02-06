package org.h2.mvstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.h2.message.DbException;
import org.h2.mvstore.type.DataType;
import org.h2.mvstore.type.StringDataType;
import org.h2.store.fs.FilePath;
import org.h2.store.fs.FileUtils;

public class MVStoreTool
{
  public static void main(String... args)
  {
    for (int i = 0; i < args.length; i++) {
      if ("-dump".equals(args[i]))
      {
        String fileName = args[(++i)];
        dump(fileName, new PrintWriter(System.out), true);
      }
      else if ("-info".equals(args[i]))
      {
        String fileName = args[(++i)];
        info(fileName, new PrintWriter(System.out));
      }
      else if ("-compact".equals(args[i]))
      {
        String fileName = args[(++i)];
        compact(fileName, false);
      }
      else if ("-compress".equals(args[i]))
      {
        String fileName = args[(++i)];
        compact(fileName, true);
      }
    }
  }
  
  public static void dump(String fileName, boolean details)
  {
    dump(fileName, new PrintWriter(System.out), details);
  }
  
  public static void info(String fileName)
  {
    info(fileName, new PrintWriter(System.out));
  }
  
  public static void dump(String fileName, Writer writer, boolean details)
  {
    PrintWriter pw = new PrintWriter(writer, true);
    if (!FilePath.get(fileName).exists())
    {
      pw.println("File not found: " + fileName);
      return;
    }
    long size = FileUtils.size(fileName);
    pw.printf("File %s, %d bytes, %d MB\n", new Object[] { fileName, Long.valueOf(size), Long.valueOf(size / 1024L / 1024L) });
    FileChannel file = null;
    int blockSize = 4096;
    TreeMap<Integer, Long> mapSizesTotal = new TreeMap();
    
    long pageSizeTotal = 0L;
    try
    {
      file = FilePath.get(fileName).open("r");
      long fileSize = file.size();
      int len = Long.toHexString(fileSize).length();
      ByteBuffer block = ByteBuffer.allocate(4096);
      long pageCount = 0L;
      for (long pos = 0L; pos < fileSize;)
      {
        block.rewind();
        DataUtils.readFully(file, pos, block);
        block.rewind();
        int headerType = block.get();
        if (headerType == 72)
        {
          pw.printf("%0" + len + "x fileHeader %s%n", new Object[] { Long.valueOf(pos), new String(block.array(), DataUtils.LATIN).trim() });
          
          pos += blockSize;
        }
        else if (headerType != 99)
        {
          pos += blockSize;
        }
        else
        {
          block.position(0);
          Chunk c = null;
          try
          {
            c = Chunk.readChunkHeader(block, pos);
          }
          catch (IllegalStateException e)
          {
            pos += blockSize;
          }
          continue;
          if (c.len <= 0)
          {
            pos += blockSize;
          }
          else
          {
            int length = c.len * 4096;
            pw.printf("%n%0" + len + "x chunkHeader %s%n", new Object[] { Long.valueOf(pos), c.toString() });
            
            ByteBuffer chunk = ByteBuffer.allocate(length);
            DataUtils.readFully(file, pos, chunk);
            int p = block.position();
            pos += length;
            int remaining = c.pageCount;
            pageCount += c.pageCount;
            TreeMap<Integer, Integer> mapSizes = new TreeMap();
            
            int pageSizeSum = 0;
            while (remaining > 0)
            {
              try
              {
                chunk.position(p);
              }
              catch (IllegalArgumentException e)
              {
                pw.printf("ERROR illegal position %d%n", new Object[] { Integer.valueOf(p) });
                break;
              }
              int pageSize = chunk.getInt();
              
              chunk.getShort();
              int mapId = DataUtils.readVarInt(chunk);
              int entries = DataUtils.readVarInt(chunk);
              int type = chunk.get();
              boolean compressed = (type & 0x2) != 0;
              boolean node = (type & 0x1) != 0;
              if (details) {
                pw.printf("+%0" + len + "x %s, map %x, %d entries, %d bytes, maxLen %x%n", new Object[] { Integer.valueOf(p), (node ? "node" : "leaf") + (compressed ? " compressed" : ""), Integer.valueOf(mapId), Integer.valueOf(node ? entries + 1 : entries), Integer.valueOf(pageSize), Integer.valueOf(DataUtils.getPageMaxLength(DataUtils.getPagePos(0, 0, pageSize, 0))) });
              }
              p += pageSize;
              Integer mapSize = (Integer)mapSizes.get(Integer.valueOf(mapId));
              if (mapSize == null) {
                mapSize = Integer.valueOf(0);
              }
              mapSizes.put(Integer.valueOf(mapId), Integer.valueOf(mapSize.intValue() + pageSize));
              Long total = (Long)mapSizesTotal.get(Integer.valueOf(mapId));
              if (total == null) {
                total = Long.valueOf(0L);
              }
              mapSizesTotal.put(Integer.valueOf(mapId), Long.valueOf(total.longValue() + pageSize));
              pageSizeSum += pageSize;
              pageSizeTotal += pageSize;
              remaining--;
              long[] children = null;
              long[] counts = null;
              if (node)
              {
                children = new long[entries + 1];
                for (int i = 0; i <= entries; i++) {
                  children[i] = chunk.getLong();
                }
                counts = new long[entries + 1];
                for (int i = 0; i <= entries; i++)
                {
                  long s = DataUtils.readVarLong(chunk);
                  counts[i] = s;
                }
              }
              String[] keys = new String[entries];
              if ((mapId == 0) && (details))
              {
                if (!compressed) {
                  for (int i = 0; i < entries; i++)
                  {
                    String k = StringDataType.INSTANCE.read(chunk);
                    keys[i] = k;
                  }
                }
                if (node)
                {
                  for (int i = 0; i < entries; i++)
                  {
                    long cp = children[i];
                    pw.printf("    %d children < %s @ chunk %x +%0" + len + "x%n", new Object[] { Long.valueOf(counts[i]), keys[i], Integer.valueOf(DataUtils.getPageChunkId(cp)), Integer.valueOf(DataUtils.getPageOffset(cp)) });
                  }
                  long cp = children[entries];
                  pw.printf("    %d children >= %s @ chunk %x +%0" + len + "x%n", new Object[] { Long.valueOf(counts[entries]), keys.length >= entries ? null : keys[entries], Integer.valueOf(DataUtils.getPageChunkId(cp)), Integer.valueOf(DataUtils.getPageOffset(cp)) });
                }
                else if (!compressed)
                {
                  String[] values = new String[entries];
                  for (int i = 0; i < entries; i++)
                  {
                    String v = StringDataType.INSTANCE.read(chunk);
                    values[i] = v;
                  }
                  for (int i = 0; i < entries; i++) {
                    pw.println("    " + keys[i] + " = " + values[i]);
                  }
                }
              }
              else if ((node) && (details))
              {
                for (int i = 0; i <= entries; i++)
                {
                  long cp = children[i];
                  pw.printf("    %d children @ chunk %x +%0" + len + "x%n", new Object[] { Long.valueOf(counts[i]), Integer.valueOf(DataUtils.getPageChunkId(cp)), Integer.valueOf(DataUtils.getPageOffset(cp)) });
                }
              }
            }
            pageSizeSum = Math.max(1, pageSizeSum);
            for (Integer mapId : mapSizes.keySet())
            {
              int percent = 100 * ((Integer)mapSizes.get(mapId)).intValue() / pageSizeSum;
              pw.printf("map %x: %d bytes, %d%%%n", new Object[] { mapId, mapSizes.get(mapId), Integer.valueOf(percent) });
            }
            int footerPos = chunk.limit() - 128;
            try
            {
              chunk.position(footerPos);
              pw.printf("+%0" + len + "x chunkFooter %s%n", new Object[] { Integer.valueOf(footerPos), new String(chunk.array(), chunk.position(), 128, DataUtils.LATIN).trim() });
            }
            catch (IllegalArgumentException e)
            {
              pw.printf("ERROR illegal footer position %d%n", new Object[] { Integer.valueOf(footerPos) });
            }
          }
        }
      }
      pw.printf("%n%0" + len + "x eof%n", new Object[] { Long.valueOf(fileSize) });
      pw.printf("\n", new Object[0]);
      pageCount = Math.max(1L, pageCount);
      pw.printf("page size total: %d bytes, page count: %d, average page size: %d bytes\n", new Object[] { Long.valueOf(pageSizeTotal), Long.valueOf(pageCount), Long.valueOf(pageSizeTotal / pageCount) });
      
      pageSizeTotal = Math.max(1L, pageSizeTotal);
      for (Integer mapId : mapSizesTotal.keySet())
      {
        int percent = (int)(100L * ((Long)mapSizesTotal.get(mapId)).longValue() / pageSizeTotal);
        pw.printf("map %x: %d bytes, %d%%%n", new Object[] { mapId, mapSizesTotal.get(mapId), Integer.valueOf(percent) });
      }
      if (file != null) {
        try
        {
          file.close();
        }
        catch (IOException e) {}
      }
      pw.flush();
    }
    catch (IOException e)
    {
      pw.println("ERROR: " + e);
      e.printStackTrace(pw);
    }
    finally
    {
      if (file != null) {
        try
        {
          file.close();
        }
        catch (IOException e) {}
      }
    }
  }
  
  public static void info(String fileName, Writer writer)
  {
    PrintWriter pw = new PrintWriter(writer, true);
    if (!FilePath.get(fileName).exists())
    {
      pw.println("File not found: " + fileName);
      return;
    }
    long fileLength = FileUtils.size(fileName);
    MVStore store = new MVStore.Builder().fileName(fileName).readOnly().open();
    try
    {
      MVMap<String, String> meta = store.getMetaMap();
      Map<String, Object> header = store.getStoreHeader();
      long fileCreated = DataUtils.readHexLong(header, "created", 0L);
      TreeMap<Integer, Chunk> chunks = new TreeMap();
      long chunkLength = 0L;
      long maxLength = 0L;
      long maxLengthLive = 0L;
      long maxLengthNotEmpty = 0L;
      for (Map.Entry<String, String> e : meta.entrySet())
      {
        String k = (String)e.getKey();
        if (k.startsWith("chunk."))
        {
          Chunk c = Chunk.fromString((String)e.getValue());
          chunks.put(Integer.valueOf(c.id), c);
          chunkLength += c.len * 4096;
          maxLength += c.maxLen;
          maxLengthLive += c.maxLenLive;
          if (c.maxLenLive > 0L) {
            maxLengthNotEmpty += c.maxLen;
          }
        }
      }
      pw.printf("Created: %s\n", new Object[] { formatTimestamp(fileCreated, fileCreated) });
      pw.printf("Last modified: %s\n", new Object[] { formatTimestamp(FileUtils.lastModified(fileName), fileCreated) });
      
      pw.printf("File length: %d\n", new Object[] { Long.valueOf(fileLength) });
      pw.printf("The last chunk is not listed\n", new Object[0]);
      pw.printf("Chunk length: %d\n", new Object[] { Long.valueOf(chunkLength) });
      pw.printf("Chunk count: %d\n", new Object[] { Integer.valueOf(chunks.size()) });
      pw.printf("Used space: %d%%\n", new Object[] { Integer.valueOf(getPercent(chunkLength, fileLength)) });
      pw.printf("Chunk fill rate: %d%%\n", new Object[] { Integer.valueOf(maxLength == 0L ? 100 : getPercent(maxLengthLive, maxLength)) });
      
      pw.printf("Chunk fill rate excluding empty chunks: %d%%\n", new Object[] { Integer.valueOf(maxLengthNotEmpty == 0L ? 100 : getPercent(maxLengthLive, maxLengthNotEmpty)) });
      for (Map.Entry<Integer, Chunk> e : chunks.entrySet())
      {
        Chunk c = (Chunk)e.getValue();
        long created = fileCreated + c.time;
        pw.printf("  Chunk %d: %s, %d%% used, %d blocks", new Object[] { Integer.valueOf(c.id), formatTimestamp(created, fileCreated), Integer.valueOf(getPercent(c.maxLenLive, c.maxLen)), Integer.valueOf(c.len) });
        if (c.maxLenLive == 0L) {
          pw.printf(", unused: %s", new Object[] { formatTimestamp(fileCreated + c.unused, fileCreated) });
        }
        pw.printf("\n", new Object[0]);
      }
      pw.printf("\n", new Object[0]);
    }
    catch (Exception e)
    {
      pw.println("ERROR: " + e);
      e.printStackTrace(pw);
    }
    finally
    {
      store.close();
    }
    pw.flush();
  }
  
  private static String formatTimestamp(long t, long start)
  {
    String x = new Timestamp(t).toString();
    String s = x.substring(0, 19);
    s = s + " (+" + (t - start) / 1000L + " s)";
    return s;
  }
  
  private static int getPercent(long value, long max)
  {
    if (value == 0L) {
      return 0;
    }
    if (value == max) {
      return 100;
    }
    return (int)(1L + 98L * value / Math.max(1L, max));
  }
  
  public static void compact(String fileName, boolean compress)
  {
    String tempName = fileName + ".tempFile";
    FileUtils.delete(tempName);
    compact(fileName, tempName, compress);
    try
    {
      FileUtils.moveAtomicReplace(tempName, fileName);
    }
    catch (DbException e)
    {
      String newName = fileName + ".newFile";
      FileUtils.delete(newName);
      FileUtils.move(tempName, newName);
      FileUtils.delete(fileName);
      FileUtils.move(newName, fileName);
    }
  }
  
  public static void compactCleanUp(String fileName)
  {
    String tempName = fileName + ".tempFile";
    if (FileUtils.exists(tempName)) {
      FileUtils.delete(tempName);
    }
    String newName = fileName + ".newFile";
    if (FileUtils.exists(newName)) {
      if (FileUtils.exists(fileName)) {
        FileUtils.delete(newName);
      } else {
        FileUtils.move(newName, fileName);
      }
    }
  }
  
  public static void compact(String sourceFileName, String targetFileName, boolean compress)
  {
    MVStore source = new MVStore.Builder().fileName(sourceFileName).readOnly().open();
    
    FileUtils.delete(targetFileName);
    MVStore.Builder b = new MVStore.Builder().fileName(targetFileName);
    if (compress) {
      b.compress();
    }
    MVStore target = b.open();
    compact(source, target);
    target.close();
    source.close();
  }
  
  public static void compact(MVStore source, MVStore target)
  {
    MVMap<String, String> sourceMeta = source.getMetaMap();
    MVMap<String, String> targetMeta = target.getMetaMap();
    for (Map.Entry<String, String> m : sourceMeta.entrySet())
    {
      String key = (String)m.getKey();
      if (!key.startsWith("chunk.")) {
        if (!key.startsWith("map.")) {
          if (!key.startsWith("name.")) {
            if (!key.startsWith("root.")) {
              targetMeta.put(key, m.getValue());
            }
          }
        }
      }
    }
    for (String mapName : source.getMapNames())
    {
      MVMap.Builder<Object, Object> mp = new MVMap.Builder().keyType(new GenericDataType()).valueType(new GenericDataType());
      
      MVMap<Object, Object> sourceMap = source.openMap(mapName, mp);
      MVMap<Object, Object> targetMap = target.openMap(mapName, mp);
      targetMap.copyFrom(sourceMap);
    }
  }
  
  static class GenericDataType
    implements DataType
  {
    public int compare(Object a, Object b)
    {
      throw DataUtils.newUnsupportedOperationException("Can not compare");
    }
    
    public int getMemory(Object obj)
    {
      return obj == null ? 0 : ((byte[])obj).length * 8;
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (obj != null) {
        buff.put((byte[])obj);
      }
    }
    
    public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
    {
      for (Object o : obj) {
        write(buff, o);
      }
    }
    
    public Object read(ByteBuffer buff)
    {
      int len = buff.remaining();
      if (len == 0) {
        return null;
      }
      byte[] data = new byte[len];
      buff.get(data);
      return data;
    }
    
    public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
    {
      for (int i = 0; i < obj.length; i++) {
        obj[i] = read(buff);
      }
    }
  }
}
