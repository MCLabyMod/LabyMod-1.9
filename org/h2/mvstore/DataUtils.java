package org.h2.mvstore;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.h2.util.New;

public class DataUtils
{
  public static final int ERROR_READING_FAILED = 1;
  public static final int ERROR_WRITING_FAILED = 2;
  public static final int ERROR_INTERNAL = 3;
  public static final int ERROR_CLOSED = 4;
  public static final int ERROR_UNSUPPORTED_FORMAT = 5;
  public static final int ERROR_FILE_CORRUPT = 6;
  public static final int ERROR_FILE_LOCKED = 7;
  public static final int ERROR_SERIALIZATION = 8;
  public static final int ERROR_CHUNK_NOT_FOUND = 9;
  public static final int ERROR_BLOCK_NOT_FOUND = 50;
  public static final int ERROR_TRANSACTION_CORRUPT = 100;
  public static final int ERROR_TRANSACTION_LOCKED = 101;
  public static final int ERROR_TOO_MANY_OPEN_TRANSACTIONS = 102;
  public static final int ERROR_TRANSACTION_ILLEGAL_STATE = 103;
  public static final int PAGE_TYPE_LEAF = 0;
  public static final int PAGE_TYPE_NODE = 1;
  public static final int PAGE_COMPRESSED = 2;
  public static final int PAGE_COMPRESSED_HIGH = 6;
  public static final int MAX_VAR_INT_LEN = 5;
  public static final int MAX_VAR_LONG_LEN = 10;
  public static final int COMPRESSED_VAR_INT_MAX = 2097151;
  public static final long COMPRESSED_VAR_LONG_MAX = 562949953421311L;
  public static final int PAGE_MEMORY = 128;
  public static final int PAGE_MEMORY_CHILD = 16;
  public static final int PAGE_LARGE = 2097152;
  public static final Charset UTF8 = Charset.forName("UTF-8");
  public static final Charset LATIN = Charset.forName("ISO-8859-1");
  private static final byte[] EMPTY_BYTES = new byte[0];
  private static final int MAX_GROW = 16777216;
  
  public static int getVarIntLen(int x)
  {
    if ((x & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((x & 0xC000) == 0) {
      return 2;
    }
    if ((x & 0xFFE00000) == 0) {
      return 3;
    }
    if ((x & 0xF0000000) == 0) {
      return 4;
    }
    return 5;
  }
  
  public static int getVarLongLen(long x)
  {
    int i = 1;
    for (;;)
    {
      x >>>= 7;
      if (x == 0L) {
        return i;
      }
      i++;
    }
  }
  
  public static int readVarInt(ByteBuffer buff)
  {
    int b = buff.get();
    if (b >= 0) {
      return b;
    }
    return readVarIntRest(buff, b);
  }
  
  private static int readVarIntRest(ByteBuffer buff, int b)
  {
    int x = b & 0x7F;
    b = buff.get();
    if (b >= 0) {
      return x | b << 7;
    }
    x |= (b & 0x7F) << 7;
    b = buff.get();
    if (b >= 0) {
      return x | b << 14;
    }
    x |= (b & 0x7F) << 14;
    b = buff.get();
    if (b >= 0) {
      return x | b << 21;
    }
    x |= (b & 0x7F) << 21 | buff.get() << 28;
    return x;
  }
  
  public static long readVarLong(ByteBuffer buff)
  {
    long x = buff.get();
    if (x >= 0L) {
      return x;
    }
    x &= 0x7F;
    for (int s = 7; s < 64; s += 7)
    {
      long b = buff.get();
      x |= (b & 0x7F) << s;
      if (b >= 0L) {
        break;
      }
    }
    return x;
  }
  
  public static void writeVarInt(OutputStream out, int x)
    throws IOException
  {
    while ((x & 0xFFFFFF80) != 0)
    {
      out.write((byte)(0x80 | x & 0x7F));
      x >>>= 7;
    }
    out.write((byte)x);
  }
  
  public static void writeVarInt(ByteBuffer buff, int x)
  {
    while ((x & 0xFFFFFF80) != 0)
    {
      buff.put((byte)(0x80 | x & 0x7F));
      x >>>= 7;
    }
    buff.put((byte)x);
  }
  
  public static ByteBuffer writeStringData(ByteBuffer buff, String s, int len)
  {
    buff = ensureCapacity(buff, 3 * len);
    for (int i = 0; i < len; i++)
    {
      int c = s.charAt(i);
      if (c < 128)
      {
        buff.put((byte)c);
      }
      else if (c >= 2048)
      {
        buff.put((byte)(0xE0 | c >> 12));
        buff.put((byte)(c >> 6 & 0x3F));
        buff.put((byte)(c & 0x3F));
      }
      else
      {
        buff.put((byte)(0xC0 | c >> 6));
        buff.put((byte)(c & 0x3F));
      }
    }
    return buff;
  }
  
  public static String readString(ByteBuffer buff, int len)
  {
    char[] chars = new char[len];
    for (int i = 0; i < len; i++)
    {
      int x = buff.get() & 0xFF;
      if (x < 128) {
        chars[i] = ((char)x);
      } else if (x >= 224) {
        chars[i] = ((char)(((x & 0xF) << 12) + ((buff.get() & 0x3F) << 6) + (buff.get() & 0x3F)));
      } else {
        chars[i] = ((char)(((x & 0x1F) << 6) + (buff.get() & 0x3F)));
      }
    }
    return new String(chars);
  }
  
  public static void writeVarLong(ByteBuffer buff, long x)
  {
    while ((x & 0xFFFFFFFFFFFFFF80) != 0L)
    {
      buff.put((byte)(int)(0x80 | x & 0x7F));
      x >>>= 7;
    }
    buff.put((byte)(int)x);
  }
  
  public static void writeVarLong(OutputStream out, long x)
    throws IOException
  {
    while ((x & 0xFFFFFFFFFFFFFF80) != 0L)
    {
      out.write((byte)(int)(0x80 | x & 0x7F));
      x >>>= 7;
    }
    out.write((byte)(int)x);
  }
  
  public static void copyWithGap(Object src, Object dst, int oldSize, int gapIndex)
  {
    if (gapIndex > 0) {
      System.arraycopy(src, 0, dst, 0, gapIndex);
    }
    if (gapIndex < oldSize) {
      System.arraycopy(src, gapIndex, dst, gapIndex + 1, oldSize - gapIndex);
    }
  }
  
  public static void copyExcept(Object src, Object dst, int oldSize, int removeIndex)
  {
    if ((removeIndex > 0) && (oldSize > 0)) {
      System.arraycopy(src, 0, dst, 0, removeIndex);
    }
    if (removeIndex < oldSize) {
      System.arraycopy(src, removeIndex + 1, dst, removeIndex, oldSize - removeIndex - 1);
    }
  }
  
  public static void readFully(FileChannel file, long pos, ByteBuffer dst)
  {
    try
    {
      do
      {
        int len = file.read(dst, pos);
        if (len < 0) {
          throw new EOFException();
        }
        pos += len;
      } while (dst.remaining() > 0);
      dst.rewind();
    }
    catch (IOException e)
    {
      long size;
      try
      {
        size = file.size();
      }
      catch (IOException e2)
      {
        size = -1L;
      }
      throw newIllegalStateException(1, "Reading from {0} failed; file length {1} read length {2} at {3}", new Object[] { file, Long.valueOf(size), Integer.valueOf(dst.remaining()), Long.valueOf(pos), e });
    }
  }
  
  public static void writeFully(FileChannel file, long pos, ByteBuffer src)
  {
    try
    {
      int off = 0;
      do
      {
        int len = file.write(src, pos + off);
        off += len;
      } while (src.remaining() > 0);
    }
    catch (IOException e)
    {
      throw newIllegalStateException(2, "Writing to {0} failed; length {1} at {2}", new Object[] { file, Integer.valueOf(src.remaining()), Long.valueOf(pos), e });
    }
  }
  
  public static int encodeLength(int len)
  {
    if (len <= 32) {
      return 0;
    }
    int code = Integer.numberOfLeadingZeros(len);
    int remaining = len << code + 1;
    code += code;
    if ((remaining & 0x80000000) != 0) {
      code--;
    }
    if (remaining << 1 != 0) {
      code--;
    }
    code = Math.min(31, 52 - code);
    
    return code;
  }
  
  public static int getPageChunkId(long pos)
  {
    return (int)(pos >>> 38);
  }
  
  public static int getPageMaxLength(long pos)
  {
    int code = (int)(pos >> 1 & 0x1F);
    if (code == 31) {
      return 2097152;
    }
    return 2 + (code & 0x1) << (code >> 1) + 4;
  }
  
  public static int getPageOffset(long pos)
  {
    return (int)(pos >> 6);
  }
  
  public static int getPageType(long pos)
  {
    return (int)pos & 0x1;
  }
  
  public static long getPagePos(int chunkId, int offset, int length, int type)
  {
    long pos = chunkId << 38;
    pos |= offset << 6;
    pos |= encodeLength(length) << 1;
    pos |= type;
    return pos;
  }
  
  public static short getCheckValue(int x)
  {
    return (short)(x >> 16 ^ x);
  }
  
  public static StringBuilder appendMap(StringBuilder buff, HashMap<String, ?> map)
  {
    ArrayList<String> list = New.arrayList(map.keySet());
    Collections.sort(list);
    for (String k : list) {
      appendMap(buff, k, map.get(k));
    }
    return buff;
  }
  
  public static void appendMap(StringBuilder buff, String key, Object value)
  {
    if (buff.length() > 0) {
      buff.append(',');
    }
    buff.append(key).append(':');
    String v;
    String v;
    if ((value instanceof Long))
    {
      v = Long.toHexString(((Long)value).longValue());
    }
    else
    {
      String v;
      if ((value instanceof Integer)) {
        v = Integer.toHexString(((Integer)value).intValue());
      } else {
        v = value.toString();
      }
    }
    if ((v.indexOf(',') < 0) && (v.indexOf('"') < 0))
    {
      buff.append(v);
    }
    else
    {
      buff.append('"');
      int i = 0;
      for (int size = v.length(); i < size; i++)
      {
        char c = v.charAt(i);
        if (c == '"') {
          buff.append('\\');
        }
        buff.append(c);
      }
      buff.append('"');
    }
  }
  
  public static HashMap<String, String> parseMap(String s)
  {
    HashMap<String, String> map = New.hashMap();
    int i = 0;
    for (int size = s.length(); i < size;)
    {
      int startKey = i;
      i = s.indexOf(':', i);
      if (i < 0) {
        throw newIllegalStateException(6, "Not a map: {0}", new Object[] { s });
      }
      String key = s.substring(startKey, i++);
      StringBuilder buff = new StringBuilder();
      while (i < size)
      {
        char c = s.charAt(i++);
        if (c == ',') {
          break;
        }
        if (c == '"') {
          while (i < size)
          {
            c = s.charAt(i++);
            if (c == '\\')
            {
              if (i == size) {
                throw newIllegalStateException(6, "Not a map: {0}", new Object[] { s });
              }
              c = s.charAt(i++);
            }
            else
            {
              if (c == '"') {
                break;
              }
            }
            buff.append(c);
          }
        }
        buff.append(c);
      }
      map.put(key, buff.toString());
    }
    return map;
  }
  
  public static int getFletcher32(byte[] bytes, int length)
  {
    int s1 = 65535;int s2 = 65535;
    int i = 0;int evenLength = length / 2 * 2;
    while (i < evenLength)
    {
      for (int end = Math.min(i + 720, evenLength); i < end;)
      {
        int x = (bytes[(i++)] & 0xFF) << 8 | bytes[(i++)] & 0xFF;
        s2 += s1 += x;
      }
      s1 = (s1 & 0xFFFF) + (s1 >>> 16);
      s2 = (s2 & 0xFFFF) + (s2 >>> 16);
    }
    if (i < length)
    {
      int x = (bytes[i] & 0xFF) << 8;
      s2 += s1 += x;
    }
    s1 = (s1 & 0xFFFF) + (s1 >>> 16);
    s2 = (s2 & 0xFFFF) + (s2 >>> 16);
    return s2 << 16 | s1;
  }
  
  public static void checkArgument(boolean test, String message, Object... arguments)
  {
    if (!test) {
      throw newIllegalArgumentException(message, arguments);
    }
  }
  
  public static IllegalArgumentException newIllegalArgumentException(String message, Object... arguments)
  {
    return (IllegalArgumentException)initCause(new IllegalArgumentException(formatMessage(0, message, arguments)), arguments);
  }
  
  public static UnsupportedOperationException newUnsupportedOperationException(String message)
  {
    return new UnsupportedOperationException(formatMessage(0, message, new Object[0]));
  }
  
  public static ConcurrentModificationException newConcurrentModificationException(String message)
  {
    return new ConcurrentModificationException(formatMessage(0, message, new Object[0]));
  }
  
  public static IllegalStateException newIllegalStateException(int errorCode, String message, Object... arguments)
  {
    return (IllegalStateException)initCause(new IllegalStateException(formatMessage(errorCode, message, arguments)), arguments);
  }
  
  private static <T extends Exception> T initCause(T e, Object... arguments)
  {
    int size = arguments.length;
    if (size > 0)
    {
      Object o = arguments[(size - 1)];
      if ((o instanceof Exception)) {
        e.initCause((Exception)o);
      }
    }
    return e;
  }
  
  public static String formatMessage(int errorCode, String message, Object... arguments)
  {
    for (int i = 0; i < arguments.length; i++)
    {
      Object a = arguments[i];
      if (!(a instanceof Exception))
      {
        String s = a == null ? "null" : a.toString();
        if (s.length() > 1000) {
          s = s.substring(0, 1000) + "...";
        }
        arguments[i] = s;
      }
    }
    return MessageFormat.format(message, arguments) + " [" + 1 + "." + 4 + "." + 187 + "/" + errorCode + "]";
  }
  
  public static int getErrorCode(String m)
  {
    if ((m != null) && (m.endsWith("]")))
    {
      int dash = m.lastIndexOf('/');
      if (dash >= 0)
      {
        String s = m.substring(dash + 1, m.length() - 1);
        try
        {
          return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {}
      }
    }
    return 0;
  }
  
  public static byte[] newBytes(int len)
  {
    if (len == 0) {
      return EMPTY_BYTES;
    }
    try
    {
      return new byte[len];
    }
    catch (OutOfMemoryError e)
    {
      Error e2 = new OutOfMemoryError("Requested memory: " + len);
      e2.initCause(e);
      throw e2;
    }
  }
  
  public static ByteBuffer ensureCapacity(ByteBuffer buff, int len)
  {
    
    if (buff.remaining() > len) {
      return buff;
    }
    return grow(buff, len);
  }
  
  private static ByteBuffer grow(ByteBuffer buff, int len)
  {
    len = buff.remaining() + len;
    int capacity = buff.capacity();
    len = Math.max(len, Math.min(capacity + 16777216, capacity * 2));
    ByteBuffer temp = ByteBuffer.allocate(len);
    buff.flip();
    temp.put(buff);
    return temp;
  }
  
  public static long readHexLong(Map<String, ? extends Object> map, String key, long defaultValue)
  {
    Object v = map.get(key);
    if (v == null) {
      return defaultValue;
    }
    if ((v instanceof Long)) {
      return ((Long)v).longValue();
    }
    try
    {
      return parseHexLong((String)v);
    }
    catch (NumberFormatException e)
    {
      throw newIllegalStateException(6, "Error parsing the value {0}", new Object[] { v, e });
    }
  }
  
  public static long parseHexLong(String x)
  {
    try
    {
      if (x.length() == 16) {
        return Long.parseLong(x.substring(0, 8), 16) << 32 | Long.parseLong(x.substring(8, 16), 16);
      }
      return Long.parseLong(x, 16);
    }
    catch (NumberFormatException e)
    {
      throw newIllegalStateException(6, "Error parsing the value {0}", new Object[] { x, e });
    }
  }
  
  public static int parseHexInt(String x)
  {
    try
    {
      return (int)Long.parseLong(x, 16);
    }
    catch (NumberFormatException e)
    {
      throw newIllegalStateException(6, "Error parsing the value {0}", new Object[] { x, e });
    }
  }
  
  public static int readHexInt(HashMap<String, ? extends Object> map, String key, int defaultValue)
  {
    Object v = map.get(key);
    if (v == null) {
      return defaultValue;
    }
    if ((v instanceof Integer)) {
      return ((Integer)v).intValue();
    }
    try
    {
      return (int)Long.parseLong((String)v, 16);
    }
    catch (NumberFormatException e)
    {
      throw newIllegalStateException(6, "Error parsing the value {0}", new Object[] { v, e });
    }
  }
  
  public static class MapEntry<K, V>
    implements Map.Entry<K, V>
  {
    private final K key;
    private V value;
    
    public MapEntry(K key, V value)
    {
      this.key = key;
      this.value = value;
    }
    
    public K getKey()
    {
      return (K)this.key;
    }
    
    public V getValue()
    {
      return (V)this.value;
    }
    
    public V setValue(V value)
    {
      throw DataUtils.newUnsupportedOperationException("Updating the value is not supported");
    }
  }
}
