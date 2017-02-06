package org.h2.store;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.h2.util.IOUtils;

public class DataReader
  extends Reader
{
  private final InputStream in;
  
  public DataReader(InputStream in)
  {
    this.in = in;
  }
  
  public byte readByte()
    throws IOException
  {
    int x = this.in.read();
    if (x < 0) {
      throw new FastEOFException();
    }
    return (byte)x;
  }
  
  public int readVarInt()
    throws IOException
  {
    int b = readByte();
    if (b >= 0) {
      return b;
    }
    int x = b & 0x7F;
    b = readByte();
    if (b >= 0) {
      return x | b << 7;
    }
    x |= (b & 0x7F) << 7;
    b = readByte();
    if (b >= 0) {
      return x | b << 14;
    }
    x |= (b & 0x7F) << 14;
    b = readByte();
    if (b >= 0) {
      return x | b << 21;
    }
    return x | (b & 0x7F) << 21 | readByte() << 28;
  }
  
  public long readVarLong()
    throws IOException
  {
    long x = readByte();
    if (x >= 0L) {
      return x;
    }
    x &= 0x7F;
    for (int s = 7;; s += 7)
    {
      long b = readByte();
      x |= (b & 0x7F) << s;
      if (b >= 0L) {
        return x;
      }
    }
  }
  
  public void readFully(byte[] buff, int len)
    throws IOException
  {
    int got = IOUtils.readFully(this.in, buff, len);
    if (got < len) {
      throw new FastEOFException();
    }
  }
  
  public String readString()
    throws IOException
  {
    int len = readVarInt();
    return readString(len);
  }
  
  private String readString(int len)
    throws IOException
  {
    char[] chars = new char[len];
    for (int i = 0; i < len; i++) {
      chars[i] = readChar();
    }
    return new String(chars);
  }
  
  private char readChar()
    throws IOException
  {
    int x = readByte() & 0xFF;
    if (x < 128) {
      return (char)x;
    }
    if (x >= 224) {
      return (char)(((x & 0xF) << 12) + ((readByte() & 0x3F) << 6) + (readByte() & 0x3F));
    }
    return (char)(((x & 0x1F) << 6) + (readByte() & 0x3F));
  }
  
  public void close()
    throws IOException
  {}
  
  public int read(char[] buff, int off, int len)
    throws IOException
  {
    int i = 0;
    try
    {
      for (; i < len; i++) {
        buff[i] = readChar();
      }
      return len;
    }
    catch (EOFException e) {}
    return i;
  }
  
  static class FastEOFException
    extends EOFException
  {
    private static final long serialVersionUID = 1L;
    
    public synchronized Throwable fillInStackTrace()
    {
      return null;
    }
  }
}
