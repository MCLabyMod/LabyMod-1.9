package org.h2.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.h2.compress.CompressDeflate;
import org.h2.compress.CompressLZF;
import org.h2.compress.CompressNo;
import org.h2.compress.Compressor;
import org.h2.compress.LZFInputStream;
import org.h2.compress.LZFOutputStream;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.util.StringUtils;

public class CompressTool
{
  private static final int MAX_BUFFER_SIZE = 393216;
  private byte[] cachedBuffer;
  
  private byte[] getBuffer(int min)
  {
    if (min > 393216) {
      return DataUtils.newBytes(min);
    }
    if ((this.cachedBuffer == null) || (this.cachedBuffer.length < min)) {
      this.cachedBuffer = DataUtils.newBytes(min);
    }
    return this.cachedBuffer;
  }
  
  public static CompressTool getInstance()
  {
    return new CompressTool();
  }
  
  public byte[] compress(byte[] in, String algorithm)
  {
    int len = in.length;
    if (in.length < 5) {
      algorithm = "NO";
    }
    Compressor compress = getCompressor(algorithm);
    byte[] buff = getBuffer((len < 100 ? len + 100 : len) * 2);
    int newLen = compress(in, in.length, compress, buff);
    byte[] out = DataUtils.newBytes(newLen);
    System.arraycopy(buff, 0, out, 0, newLen);
    return out;
  }
  
  private static int compress(byte[] in, int len, Compressor compress, byte[] out)
  {
    int newLen = 0;
    out[0] = ((byte)compress.getAlgorithm());
    int start = 1 + writeVariableInt(out, 1, len);
    newLen = compress.compress(in, len, out, start);
    if ((newLen > len + start) || (newLen <= 0))
    {
      out[0] = 0;
      System.arraycopy(in, 0, out, start, len);
      newLen = len + start;
    }
    return newLen;
  }
  
  public byte[] expand(byte[] in)
  {
    int algorithm = in[0];
    Compressor compress = getCompressor(algorithm);
    try
    {
      int len = readVariableInt(in, 1);
      int start = 1 + getVariableIntLength(len);
      byte[] buff = DataUtils.newBytes(len);
      compress.expand(in, start, in.length - start, buff, 0, len);
      return buff;
    }
    catch (Exception e)
    {
      throw DbException.get(90104, e, new String[0]);
    }
  }
  
  public static void expand(byte[] in, byte[] out, int outPos)
  {
    int algorithm = in[0];
    Compressor compress = getCompressor(algorithm);
    try
    {
      int len = readVariableInt(in, 1);
      int start = 1 + getVariableIntLength(len);
      compress.expand(in, start, in.length - start, out, outPos, len);
    }
    catch (Exception e)
    {
      throw DbException.get(90104, e, new String[0]);
    }
  }
  
  public static int readVariableInt(byte[] buff, int pos)
  {
    int x = buff[(pos++)] & 0xFF;
    if (x < 128) {
      return x;
    }
    if (x < 192) {
      return ((x & 0x3F) << 8) + (buff[pos] & 0xFF);
    }
    if (x < 224) {
      return ((x & 0x1F) << 16) + ((buff[(pos++)] & 0xFF) << 8) + (buff[pos] & 0xFF);
    }
    if (x < 240) {
      return ((x & 0xF) << 24) + ((buff[(pos++)] & 0xFF) << 16) + ((buff[(pos++)] & 0xFF) << 8) + (buff[pos] & 0xFF);
    }
    return ((buff[(pos++)] & 0xFF) << 24) + ((buff[(pos++)] & 0xFF) << 16) + ((buff[(pos++)] & 0xFF) << 8) + (buff[pos] & 0xFF);
  }
  
  public static int writeVariableInt(byte[] buff, int pos, int x)
  {
    if (x < 0)
    {
      buff[(pos++)] = -16;
      buff[(pos++)] = ((byte)(x >> 24));
      buff[(pos++)] = ((byte)(x >> 16));
      buff[(pos++)] = ((byte)(x >> 8));
      buff[pos] = ((byte)x);
      return 5;
    }
    if (x < 128)
    {
      buff[pos] = ((byte)x);
      return 1;
    }
    if (x < 16384)
    {
      buff[(pos++)] = ((byte)(0x80 | x >> 8));
      buff[pos] = ((byte)x);
      return 2;
    }
    if (x < 2097152)
    {
      buff[(pos++)] = ((byte)(0xC0 | x >> 16));
      buff[(pos++)] = ((byte)(x >> 8));
      buff[pos] = ((byte)x);
      return 3;
    }
    if (x < 268435456)
    {
      buff[(pos++)] = ((byte)(0xE0 | x >> 24));
      buff[(pos++)] = ((byte)(x >> 16));
      buff[(pos++)] = ((byte)(x >> 8));
      buff[pos] = ((byte)x);
      return 4;
    }
    buff[(pos++)] = -16;
    buff[(pos++)] = ((byte)(x >> 24));
    buff[(pos++)] = ((byte)(x >> 16));
    buff[(pos++)] = ((byte)(x >> 8));
    buff[pos] = ((byte)x);
    return 5;
  }
  
  public static int getVariableIntLength(int x)
  {
    if (x < 0) {
      return 5;
    }
    if (x < 128) {
      return 1;
    }
    if (x < 16384) {
      return 2;
    }
    if (x < 2097152) {
      return 3;
    }
    if (x < 268435456) {
      return 4;
    }
    return 5;
  }
  
  private static Compressor getCompressor(String algorithm)
  {
    if (algorithm == null) {
      algorithm = "LZF";
    }
    int idx = algorithm.indexOf(' ');
    String options = null;
    if (idx > 0)
    {
      options = algorithm.substring(idx + 1);
      algorithm = algorithm.substring(0, idx);
    }
    int a = getCompressAlgorithm(algorithm);
    Compressor compress = getCompressor(a);
    compress.setOptions(options);
    return compress;
  }
  
  public static int getCompressAlgorithm(String algorithm)
  {
    algorithm = StringUtils.toUpperEnglish(algorithm);
    if ("NO".equals(algorithm)) {
      return 0;
    }
    if ("LZF".equals(algorithm)) {
      return 1;
    }
    if ("DEFLATE".equals(algorithm)) {
      return 2;
    }
    throw DbException.get(90103, algorithm);
  }
  
  private static Compressor getCompressor(int algorithm)
  {
    switch (algorithm)
    {
    case 0: 
      return new CompressNo();
    case 1: 
      return new CompressLZF();
    case 2: 
      return new CompressDeflate();
    }
    throw DbException.get(90103, "" + algorithm);
  }
  
  public static OutputStream wrapOutputStream(OutputStream out, String compressionAlgorithm, String entryName)
  {
    try
    {
      if ("GZIP".equals(compressionAlgorithm))
      {
        out = new GZIPOutputStream(out);
      }
      else if ("ZIP".equals(compressionAlgorithm))
      {
        ZipOutputStream z = new ZipOutputStream(out);
        z.putNextEntry(new ZipEntry(entryName));
        out = z;
      }
      else if ("DEFLATE".equals(compressionAlgorithm))
      {
        out = new DeflaterOutputStream(out);
      }
      else if ("LZF".equals(compressionAlgorithm))
      {
        out = new LZFOutputStream(out);
      }
      else if (compressionAlgorithm != null)
      {
        throw DbException.get(90103, compressionAlgorithm);
      }
      return out;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  public static InputStream wrapInputStream(InputStream in, String compressionAlgorithm, String entryName)
  {
    try
    {
      if ("GZIP".equals(compressionAlgorithm))
      {
        in = new GZIPInputStream(in);
      }
      else if ("ZIP".equals(compressionAlgorithm))
      {
        ZipInputStream z = new ZipInputStream(in);
        for (;;)
        {
          ZipEntry entry = z.getNextEntry();
          if (entry == null) {
            return null;
          }
          if (entryName.equals(entry.getName())) {
            break;
          }
        }
        in = z;
      }
      else if ("DEFLATE".equals(compressionAlgorithm))
      {
        in = new InflaterInputStream(in);
      }
      else if ("LZF".equals(compressionAlgorithm))
      {
        in = new LZFInputStream(in);
      }
      else if (compressionAlgorithm != null)
      {
        throw DbException.get(90103, compressionAlgorithm);
      }
      return in;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
}
