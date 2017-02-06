package org.h2.store;

import java.io.IOException;
import java.io.InputStream;
import org.h2.message.DbException;
import org.h2.value.ValueLobDb;

class LobStorageRemoteInputStream
  extends InputStream
{
  private final DataHandler handler;
  private final long lob;
  private final byte[] hmac;
  private long pos;
  private long remainingBytes;
  
  public LobStorageRemoteInputStream(DataHandler handler, ValueLobDb lob, byte[] hmac, long byteCount)
  {
    this.handler = handler;
    this.lob = lob.getLobId();
    this.hmac = hmac;
    this.remainingBytes = byteCount;
  }
  
  public int read()
    throws IOException
  {
    byte[] buff = new byte[1];
    int len = read(buff, 0, 1);
    return len < 0 ? len : buff[0] & 0xFF;
  }
  
  public int read(byte[] buff)
    throws IOException
  {
    return read(buff, 0, buff.length);
  }
  
  public int read(byte[] buff, int off, int length)
    throws IOException
  {
    if (length == 0) {
      return 0;
    }
    length = (int)Math.min(length, this.remainingBytes);
    if (length == 0) {
      return -1;
    }
    try
    {
      length = this.handler.readLob(this.lob, this.hmac, this.pos, buff, off, length);
    }
    catch (DbException e)
    {
      throw DbException.convertToIOException(e);
    }
    this.remainingBytes -= length;
    if (length == 0) {
      return -1;
    }
    this.pos += length;
    return length;
  }
  
  public long skip(long n)
  {
    this.remainingBytes -= n;
    this.pos += n;
    return n;
  }
}
