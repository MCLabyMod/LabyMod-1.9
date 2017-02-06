package org.h2.store;

import java.io.OutputStream;
import org.h2.tools.CompressTool;

public class FileStoreOutputStream
  extends OutputStream
{
  private FileStore store;
  private final Data page;
  private final String compressionAlgorithm;
  private final CompressTool compress;
  private final byte[] buffer = { 0 };
  
  public FileStoreOutputStream(FileStore store, DataHandler handler, String compressionAlgorithm)
  {
    this.store = store;
    if (compressionAlgorithm != null)
    {
      this.compress = CompressTool.getInstance();
      this.compressionAlgorithm = compressionAlgorithm;
    }
    else
    {
      this.compress = null;
      this.compressionAlgorithm = null;
    }
    this.page = Data.create(handler, 16);
  }
  
  public void write(int b)
  {
    this.buffer[0] = ((byte)b);
    write(this.buffer);
  }
  
  public void write(byte[] buff)
  {
    write(buff, 0, buff.length);
  }
  
  public void write(byte[] buff, int off, int len)
  {
    if (len > 0)
    {
      this.page.reset();
      if (this.compress != null)
      {
        if ((off != 0) || (len != buff.length))
        {
          byte[] b2 = new byte[len];
          System.arraycopy(buff, off, b2, 0, len);
          buff = b2;
          off = 0;
        }
        int uncompressed = len;
        buff = this.compress.compress(buff, this.compressionAlgorithm);
        len = buff.length;
        this.page.checkCapacity(8 + len);
        this.page.writeInt(len);
        this.page.writeInt(uncompressed);
        this.page.write(buff, off, len);
      }
      else
      {
        this.page.checkCapacity(4 + len);
        this.page.writeInt(len);
        this.page.write(buff, off, len);
      }
      this.page.fillAligned();
      this.store.write(this.page.getBytes(), 0, this.page.length());
    }
  }
  
  public void close()
  {
    if (this.store != null) {
      try
      {
        this.store.close();
      }
      finally
      {
        this.store = null;
      }
    }
  }
}
