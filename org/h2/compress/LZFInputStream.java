package org.h2.compress;

import java.io.IOException;
import java.io.InputStream;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;

public class LZFInputStream
  extends InputStream
{
  private final InputStream in;
  private CompressLZF decompress = new CompressLZF();
  private int pos;
  private int bufferLength;
  private byte[] inBuffer;
  private byte[] buffer;
  
  public LZFInputStream(InputStream in)
    throws IOException
  {
    this.in = in;
    if (readInt() != 1211255123) {
      throw new IOException("Not an LZFInputStream");
    }
  }
  
  private static byte[] ensureSize(byte[] buff, int len)
  {
    return (buff == null) || (buff.length < len) ? DataUtils.newBytes(len) : buff;
  }
  
  private void fillBuffer()
    throws IOException
  {
    if ((this.buffer != null) && (this.pos < this.bufferLength)) {
      return;
    }
    int len = readInt();
    if (this.decompress == null)
    {
      this.bufferLength = 0;
    }
    else if (len < 0)
    {
      len = -len;
      this.buffer = ensureSize(this.buffer, len);
      readFully(this.buffer, len);
      this.bufferLength = len;
    }
    else
    {
      this.inBuffer = ensureSize(this.inBuffer, len);
      int size = readInt();
      readFully(this.inBuffer, len);
      this.buffer = ensureSize(this.buffer, size);
      try
      {
        this.decompress.expand(this.inBuffer, 0, len, this.buffer, 0, size);
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
        DbException.convertToIOException(e);
      }
      this.bufferLength = size;
    }
    this.pos = 0;
  }
  
  private void readFully(byte[] buff, int len)
    throws IOException
  {
    int off = 0;
    while (len > 0)
    {
      int l = this.in.read(buff, off, len);
      len -= l;
      off += l;
    }
  }
  
  private int readInt()
    throws IOException
  {
    int x = this.in.read();
    if (x < 0)
    {
      this.decompress = null;
      return 0;
    }
    x = (x << 24) + (this.in.read() << 16) + (this.in.read() << 8) + this.in.read();
    return x;
  }
  
  public int read()
    throws IOException
  {
    fillBuffer();
    if (this.pos >= this.bufferLength) {
      return -1;
    }
    return this.buffer[(this.pos++)] & 0xFF;
  }
  
  public int read(byte[] b)
    throws IOException
  {
    return read(b, 0, b.length);
  }
  
  public int read(byte[] b, int off, int len)
    throws IOException
  {
    if (len == 0) {
      return 0;
    }
    int read = 0;
    while (len > 0)
    {
      int r = readBlock(b, off, len);
      if (r < 0) {
        break;
      }
      read += r;
      off += r;
      len -= r;
    }
    return read == 0 ? -1 : read;
  }
  
  private int readBlock(byte[] b, int off, int len)
    throws IOException
  {
    fillBuffer();
    if (this.pos >= this.bufferLength) {
      return -1;
    }
    int max = Math.min(len, this.bufferLength - this.pos);
    max = Math.min(max, b.length - off);
    System.arraycopy(this.buffer, this.pos, b, off, max);
    this.pos += max;
    return max;
  }
  
  public void close()
    throws IOException
  {
    this.in.close();
  }
}
