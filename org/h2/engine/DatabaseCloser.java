package org.h2.engine;

import java.lang.ref.WeakReference;
import org.h2.message.Trace;

class DatabaseCloser
  extends Thread
{
  private final boolean shutdownHook;
  private final Trace trace;
  private volatile WeakReference<Database> databaseRef;
  private int delayInMillis;
  
  DatabaseCloser(Database db, int delayInMillis, boolean shutdownHook)
  {
    this.databaseRef = new WeakReference(db);
    this.delayInMillis = delayInMillis;
    this.shutdownHook = shutdownHook;
    this.trace = db.getTrace("database");
  }
  
  void reset()
  {
    synchronized (this)
    {
      this.databaseRef = null;
    }
  }
  
  public void run()
  {
    while (this.delayInMillis > 0)
    {
      try
      {
        int step = 100;
        Thread.sleep(step);
        this.delayInMillis -= step;
      }
      catch (Exception e) {}
      if (this.databaseRef == null) {
        return;
      }
    }
    Database database = null;
    synchronized (this)
    {
      if (this.databaseRef != null) {
        database = (Database)this.databaseRef.get();
      }
    }
    if (database != null) {
      try
      {
        database.close(this.shutdownHook);
      }
      catch (RuntimeException e)
      {
        try
        {
          this.trace.error(e, "could not close the database");
        }
        catch (RuntimeException e2)
        {
          throw e;
        }
      }
    }
  }
}
