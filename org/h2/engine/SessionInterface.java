package org.h2.engine;

import java.io.Closeable;
import java.util.ArrayList;
import org.h2.command.CommandInterface;
import org.h2.message.Trace;
import org.h2.store.DataHandler;
import org.h2.value.Value;

public abstract interface SessionInterface
  extends Closeable
{
  public abstract ArrayList<String> getClusterServers();
  
  public abstract CommandInterface prepareCommand(String paramString, int paramInt);
  
  public abstract void close();
  
  public abstract Trace getTrace();
  
  public abstract boolean isClosed();
  
  public abstract int getPowerOffCount();
  
  public abstract void setPowerOffCount(int paramInt);
  
  public abstract DataHandler getDataHandler();
  
  public abstract boolean hasPendingTransaction();
  
  public abstract void cancel();
  
  public abstract boolean isReconnectNeeded(boolean paramBoolean);
  
  public abstract SessionInterface reconnect(boolean paramBoolean);
  
  public abstract void afterWriting();
  
  public abstract boolean getAutoCommit();
  
  public abstract void setAutoCommit(boolean paramBoolean);
  
  public abstract void addTemporaryLob(Value paramValue);
}
