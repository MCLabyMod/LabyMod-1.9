package org.h2.store;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.h2.value.Value;
import org.h2.value.ValueLobDb;

public class LobStorageFrontend
  implements LobStorageInterface
{
  public static final int TABLE_ID_SESSION_VARIABLE = -1;
  public static final int TABLE_TEMP = -2;
  public static final int TABLE_RESULT = -3;
  private final DataHandler handler;
  
  public LobStorageFrontend(DataHandler handler)
  {
    this.handler = handler;
  }
  
  public void removeLob(ValueLobDb lob) {}
  
  public InputStream getInputStream(ValueLobDb lob, byte[] hmac, long byteCount)
    throws IOException
  {
    if (byteCount < 0L) {
      byteCount = Long.MAX_VALUE;
    }
    return new BufferedInputStream(new LobStorageRemoteInputStream(this.handler, lob, hmac, byteCount));
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public ValueLobDb copyLob(ValueLobDb old, int tableId, long length)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setTable(ValueLobDb lob, int tableIdSessionVariable)
  {
    throw new UnsupportedOperationException();
  }
  
  public void removeAllForTable(int tableId)
  {
    throw new UnsupportedOperationException();
  }
  
  public Value createBlob(InputStream in, long maxLength)
  {
    return ValueLobDb.createTempBlob(in, maxLength, this.handler);
  }
  
  public Value createClob(Reader reader, long maxLength)
  {
    return ValueLobDb.createTempClob(reader, maxLength, this.handler);
  }
  
  public void init() {}
}
