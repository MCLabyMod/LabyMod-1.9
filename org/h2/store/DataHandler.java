package org.h2.store;

import org.h2.api.JavaObjectSerializer;
import org.h2.message.DbException;
import org.h2.util.SmallLRUCache;
import org.h2.util.TempFileDeleter;

public abstract interface DataHandler
{
  public abstract String getDatabasePath();
  
  public abstract FileStore openFile(String paramString1, String paramString2, boolean paramBoolean);
  
  public abstract void checkPowerOff()
    throws DbException;
  
  public abstract void checkWritingAllowed()
    throws DbException;
  
  public abstract int getMaxLengthInplaceLob();
  
  public abstract String getLobCompressionAlgorithm(int paramInt);
  
  public abstract TempFileDeleter getTempFileDeleter();
  
  public abstract Object getLobSyncObject();
  
  public abstract SmallLRUCache<String, String[]> getLobFileListCache();
  
  public abstract LobStorageInterface getLobStorage();
  
  public abstract int readLob(long paramLong1, byte[] paramArrayOfByte1, long paramLong2, byte[] paramArrayOfByte2, int paramInt1, int paramInt2);
  
  public abstract JavaObjectSerializer getJavaObjectSerializer();
}
