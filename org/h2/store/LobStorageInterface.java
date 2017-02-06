package org.h2.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.h2.value.Value;
import org.h2.value.ValueLobDb;

public abstract interface LobStorageInterface
{
  public abstract Value createClob(Reader paramReader, long paramLong);
  
  public abstract Value createBlob(InputStream paramInputStream, long paramLong);
  
  public abstract ValueLobDb copyLob(ValueLobDb paramValueLobDb, int paramInt, long paramLong);
  
  public abstract InputStream getInputStream(ValueLobDb paramValueLobDb, byte[] paramArrayOfByte, long paramLong)
    throws IOException;
  
  public abstract void setTable(ValueLobDb paramValueLobDb, int paramInt);
  
  public abstract void removeLob(ValueLobDb paramValueLobDb);
  
  public abstract void removeAllForTable(int paramInt);
  
  public abstract void init();
  
  public abstract boolean isReadOnly();
}
