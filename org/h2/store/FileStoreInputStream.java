package org.h2.store;

import java.io.IOException;
import java.io.InputStream;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.tools.CompressTool;

public class FileStoreInputStream
  extends InputStream
{
  private FileStore store;
  private final Data page;
  private int remainingInBuffer;
  private final CompressTool compress;
  private boolean endOfFile;
  private final boolean alwaysClose;
  
  public FileStoreInputStream(FileStore store, DataHandler handler, boolean compression, boolean alwaysClose)
  {
    this.store = store;
    this.alwaysClose = alwaysClose;
    if (compression) {
      this.compress = CompressTool.getInstance();
    } else {
      this.compress = null;
    }
    this.page = Data.create(handler, 16);
    try
    {
      if (store.length() <= 48L) {
        close();
      } else {
        fillBuffer();
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, store.name);
    }
  }
  
  public int available()
  {
    return this.remainingInBuffer <= 0 ? 0 : this.remainingInBuffer;
  }
  
  public int read(byte[] buff)
    throws IOException
  {
    return read(buff, 0, buff.length);
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
  
  private int readBlock(byte[] buff, int off, int len)
    throws IOException
  {
    fillBuffer();
    if (this.endOfFile) {
      return -1;
    }
    int l = Math.min(this.remainingInBuffer, len);
    this.page.read(buff, off, l);
    this.remainingInBuffer -= l;
    return l;
  }
  
  private void fillBuffer()
    throws IOException
  {
    if ((this.remainingInBuffer > 0) || (this.endOfFile)) {
      return;
    }
    this.page.reset();
    this.store.openFile();
    if (this.store.length() == this.store.getFilePointer())
    {
      close();
      return;
    }
    this.store.readFully(this.page.getBytes(), 0, 16);
    this.page.reset();
    this.remainingInBuffer = this.page.readInt();
    if (this.remainingInBuffer < 0)
    {
      close();
      return;
    }
    this.page.checkCapacity(this.remainingInBuffer);
    if (this.compress != null)
    {
      this.page.checkCapacity(4);
      this.page.readInt();
    }
    this.page.setPos(this.page.length() + this.remainingInBuffer);
    this.page.fillAligned();
    int len = this.page.length() - 16;
    this.page.reset();
    this.page.readInt();
    this.store.readFully(this.page.getBytes(), 16, len);
    this.page.reset();
    this.page.readInt();
    if (this.compress != null)
    {
      int uncompressed = this.page.readInt();
      byte[] buff = DataUtils.newBytes(this.remainingInBuffer);
      this.page.read(buff, 0, this.remainingInBuffer);
      this.page.reset();
      this.page.checkCapacity(uncompressed);
      CompressTool.expand(buff, this.page.getBytes(), 0);
      this.remainingInBuffer = uncompressed;
    }
    if (this.alwaysClose) {
      this.store.closeFile();
    }
  }
  
  public void close()
  {
    if (this.store != null) {
      try
      {
        this.store.close();
        this.endOfFile = true;
      }
      finally
      {
        this.store = null;
      }
    }
  }
  
  protected void finalize()
  {
    close();
  }
  
  public int read()
    throws IOException
  {
    fillBuffer();
    if (this.endOfFile) {
      return -1;
    }
    int i = this.page.readByte() & 0xFF;
    this.remainingInBuffer -= 1;
    return i;
  }
}
