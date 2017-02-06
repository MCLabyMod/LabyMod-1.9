package org.h2.compress;

import java.io.IOException;
import java.io.OutputStream;

public class LZFOutputStream
  extends OutputStream
{
  static final int MAGIC = 1211255123;
  private final OutputStream out;
  private final CompressLZF compress = new CompressLZF();
  private final byte[] buffer;
  private int pos;
  private byte[] outBuffer;
  
  public LZFOutputStream(OutputStream out)
    throws IOException
  {
    this.out = out;
    int len = 131072;
    this.buffer = new byte[len];
    ensureOutput(len);
    writeInt(1211255123);
  }
  
  private void ensureOutput(int len)
  {
    int outputLen = (len < 100 ? len + 100 : len) * 2;
    if ((this.outBuffer == null) || (this.outBuffer.length < outputLen)) {
      this.outBuffer = new byte[outputLen];
    }
  }
  
  public void write(int b)
    throws IOException
  {
    if (this.pos >= this.buffer.length) {
      flush();
    }
    this.buffer[(this.pos++)] = ((byte)b);
  }
  
  private void compressAndWrite(byte[] buff, int len)
    throws IOException
  {
    if (len > 0)
    {
      ensureOutput(len);
      int compressed = this.compress.compress(buff, len, this.outBuffer, 0);
      if (compressed > len)
      {
        writeInt(-len);
        this.out.write(buff, 0, len);
      }
      else
      {
        writeInt(compressed);
        writeInt(len);
        this.out.write(this.outBuffer, 0, compressed);
      }
    }
  }
  
  private void writeInt(int x)
    throws IOException
  {
    this.out.write((byte)(x >> 24));
    this.out.write((byte)(x >> 16));
    this.out.write((byte)(x >> 8));
    this.out.write((byte)x);
  }
  
  public void write(byte[] buff, int off, int len)
    throws IOException
  {
    while (len > 0)
    {
      int copy = Math.min(this.buffer.length - this.pos, len);
      System.arraycopy(buff, off, this.buffer, this.pos, copy);
      this.pos += copy;
      if (this.pos >= this.buffer.length) {
        flush();
      }
      off += copy;
      len -= copy;
    }
  }
  
  public void flush()
    throws IOException
  {
    compressAndWrite(this.buffer, this.pos);
    this.pos = 0;
  }
  
  public void close()
    throws IOException
  {
    flush();
    this.out.close();
  }
}
