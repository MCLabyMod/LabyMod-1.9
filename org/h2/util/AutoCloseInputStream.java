package org.h2.util;

import java.io.IOException;
import java.io.InputStream;

public class AutoCloseInputStream
  extends InputStream
{
  private final InputStream in;
  private boolean closed;
  
  public AutoCloseInputStream(InputStream in)
  {
    this.in = in;
  }
  
  private int autoClose(int x)
    throws IOException
  {
    if (x < 0) {
      close();
    }
    return x;
  }
  
  public void close()
    throws IOException
  {
    if (!this.closed)
    {
      this.in.close();
      this.closed = true;
    }
  }
  
  public int read(byte[] b, int off, int len)
    throws IOException
  {
    return this.closed ? -1 : autoClose(this.in.read(b, off, len));
  }
  
  public int read(byte[] b)
    throws IOException
  {
    return this.closed ? -1 : autoClose(this.in.read(b));
  }
  
  public int read()
    throws IOException
  {
    return this.closed ? -1 : autoClose(this.in.read());
  }
}
