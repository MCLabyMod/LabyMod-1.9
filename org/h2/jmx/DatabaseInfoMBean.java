package org.h2.jmx;

public abstract interface DatabaseInfoMBean
{
  public abstract boolean isExclusive();
  
  public abstract boolean isReadOnly();
  
  public abstract String getMode();
  
  public abstract boolean isMultiThreaded();
  
  public abstract boolean isMvcc();
  
  public abstract int getLogMode();
  
  public abstract void setLogMode(int paramInt);
  
  public abstract long getFileWriteCountTotal();
  
  public abstract long getFileWriteCount();
  
  public abstract long getFileReadCount();
  
  public abstract long getFileSize();
  
  public abstract int getCacheSizeMax();
  
  public abstract void setCacheSizeMax(int paramInt);
  
  public abstract int getCacheSize();
  
  public abstract String getVersion();
  
  public abstract int getTraceLevel();
  
  public abstract void setTraceLevel(int paramInt);
  
  public abstract String listSettings();
  
  public abstract String listSessions();
}
