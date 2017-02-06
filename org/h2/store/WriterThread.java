package org.h2.store;

import java.lang.ref.WeakReference;
import java.security.AccessControlException;
import org.h2.Driver;
import org.h2.engine.Database;
import org.h2.message.Trace;
import org.h2.message.TraceSystem;

public class WriterThread
  implements Runnable
{
  private volatile WeakReference<Database> databaseRef;
  private int writeDelay;
  private Thread thread;
  private volatile boolean stop;
  
  private WriterThread(Database database, int writeDelay)
  {
    this.databaseRef = new WeakReference(database);
    this.writeDelay = writeDelay;
  }
  
  public void setWriteDelay(int writeDelay)
  {
    this.writeDelay = writeDelay;
  }
  
  public static WriterThread create(Database database, int writeDelay)
  {
    try
    {
      WriterThread writer = new WriterThread(database, writeDelay);
      writer.thread = new Thread(writer, "H2 Log Writer " + database.getShortName());
      Driver.setThreadContextClassLoader(writer.thread);
      writer.thread.setDaemon(true);
      return writer;
    }
    catch (AccessControlException e) {}
    return null;
  }
  
  public void run()
  {
    while (!this.stop)
    {
      Database database = (Database)this.databaseRef.get();
      if (database == null) {
        break;
      }
      int wait = this.writeDelay;
      try
      {
        if (database.isFileLockSerialized())
        {
          wait = 5;
          database.checkpointIfRequired();
        }
        else
        {
          database.flush();
        }
      }
      catch (Exception e)
      {
        TraceSystem traceSystem = database.getTraceSystem();
        if (traceSystem != null) {
          traceSystem.getTrace("database").error(e, "flush");
        }
      }
      wait = Math.max(wait, 5);
      synchronized (this)
      {
        while ((!this.stop) && (wait > 0))
        {
          int w = Math.min(wait, 100);
          try
          {
            wait(w);
          }
          catch (InterruptedException e) {}
          wait -= w;
        }
      }
    }
    this.databaseRef = null;
  }
  
  public void stopThread()
  {
    this.stop = true;
    synchronized (this)
    {
      notify();
    }
  }
  
  public void startThread()
  {
    this.thread.start();
    this.thread = null;
  }
}
