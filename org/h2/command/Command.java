package org.h2.command;

import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.Session.Savepoint;
import org.h2.expression.ParameterInterface;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.ResultInterface;
import org.h2.util.MathUtils;

public abstract class Command
  implements CommandInterface
{
  protected final Session session;
  protected long startTime;
  private final Trace trace;
  private volatile boolean cancel;
  private final String sql;
  private boolean canReuse;
  
  Command(Parser parser, String sql)
  {
    this.session = parser.getSession();
    this.sql = sql;
    this.trace = this.session.getDatabase().getTrace("command");
  }
  
  public abstract boolean isTransactional();
  
  public abstract boolean isQuery();
  
  public abstract ArrayList<? extends ParameterInterface> getParameters();
  
  public abstract boolean isReadOnly();
  
  public abstract ResultInterface queryMeta();
  
  public int update()
  {
    throw DbException.get(90001);
  }
  
  public ResultInterface query(int maxrows)
  {
    throw DbException.get(90002);
  }
  
  public final ResultInterface getMetaData()
  {
    return queryMeta();
  }
  
  void start()
  {
    if (this.trace.isInfoEnabled()) {
      this.startTime = System.currentTimeMillis();
    }
  }
  
  void setProgress(int state)
  {
    this.session.getDatabase().setProgress(state, this.sql, 0, 0);
  }
  
  protected void checkCanceled()
  {
    if (this.cancel)
    {
      this.cancel = false;
      throw DbException.get(57014);
    }
  }
  
  private void stop()
  {
    this.session.endStatement();
    this.session.setCurrentCommand(null);
    if (!isTransactional())
    {
      this.session.commit(true);
    }
    else if (this.session.getAutoCommit())
    {
      this.session.commit(false);
    }
    else if (this.session.getDatabase().isMultiThreaded())
    {
      Database db = this.session.getDatabase();
      if ((db != null) && 
        (db.getLockMode() == 3)) {
        this.session.unlockReadLocks();
      }
    }
    if ((this.trace.isInfoEnabled()) && (this.startTime > 0L))
    {
      long time = System.currentTimeMillis() - this.startTime;
      if (time > 100L) {
        this.trace.info("slow query: {0} ms", new Object[] { Long.valueOf(time) });
      }
    }
  }
  
  public ResultInterface executeQuery(int maxrows, boolean scrollable)
  {
    this.startTime = 0L;
    long start = 0L;
    Database database = this.session.getDatabase();
    Object sync = database.isMultiThreaded() ? this.session : database;
    this.session.waitIfExclusiveModeEnabled();
    boolean callStop = true;
    boolean writing = !isReadOnly();
    while ((writing) && 
      (!database.beforeWriting())) {}
    synchronized (sync)
    {
      this.session.setCurrentCommand(this);
      try
      {
        for (;;)
        {
          database.checkPowerOff();
          try
          {
            ResultInterface localResultInterface = query(maxrows);
            if (callStop) {
              stop();
            }
            if (writing) {
              database.afterWriting();
            }
            return localResultInterface;
          }
          catch (DbException e)
          {
            start = filterConcurrentUpdate(e, start);
          }
          catch (OutOfMemoryError e)
          {
            callStop = false;
            
            database.shutdownImmediately();
            throw DbException.convert(e);
          }
          catch (Throwable e)
          {
            throw DbException.convert(e);
          }
        }
        SQLException s;
        localObject2 = finally;
      }
      catch (DbException e)
      {
        e = e.addSQL(this.sql);
        s = e.getSQLException();
        database.exceptionThrown(s, this.sql);
        if (s.getErrorCode() == 90108)
        {
          callStop = false;
          database.shutdownImmediately();
          throw e;
        }
        database.checkPowerOff();
        throw e;
      }
      finally
      {
        if (callStop) {
          stop();
        }
        if (writing) {
          database.afterWriting();
        }
      }
    }
  }
  
  public int executeUpdate()
  {
    long start = 0L;
    Database database = this.session.getDatabase();
    Object sync = database.isMultiThreaded() ? this.session : database;
    this.session.waitIfExclusiveModeEnabled();
    boolean callStop = true;
    boolean writing = !isReadOnly();
    while ((writing) && 
      (!database.beforeWriting())) {}
    synchronized (sync)
    {
      Session.Savepoint rollback = this.session.setSavepoint();
      this.session.setCurrentCommand(this);
      try
      {
        for (;;)
        {
          database.checkPowerOff();
          try
          {
            int i = update();
            try
            {
              if (callStop) {
                stop();
              }
            }
            finally
            {
              if (writing) {
                database.afterWriting();
              }
            }
            return i;
          }
          catch (DbException e)
          {
            start = filterConcurrentUpdate(e, start);
          }
          catch (OutOfMemoryError e)
          {
            callStop = false;
            database.shutdownImmediately();
            throw DbException.convert(e);
          }
          catch (Throwable e)
          {
            throw DbException.convert(e);
          }
        }
        SQLException s;
        localObject4 = finally;
      }
      catch (DbException e)
      {
        e = e.addSQL(this.sql);
        s = e.getSQLException();
        database.exceptionThrown(s, this.sql);
        if (s.getErrorCode() == 90108)
        {
          callStop = false;
          database.shutdownImmediately();
          throw e;
        }
        database.checkPowerOff();
        if (s.getErrorCode() == 40001) {
          this.session.rollback();
        } else {
          this.session.rollbackTo(rollback, false);
        }
        throw e;
      }
      finally
      {
        try
        {
          if (callStop) {
            stop();
          }
        }
        finally
        {
          if (writing) {
            database.afterWriting();
          }
        }
      }
    }
  }
  
  private long filterConcurrentUpdate(DbException e, long start)
  {
    if (e.getErrorCode() != 90131) {
      throw e;
    }
    long now = System.nanoTime() / 1000000L;
    if ((start != 0L) && (now - start > this.session.getLockTimeout())) {
      throw DbException.get(50200, e.getCause(), new String[] { "" });
    }
    Database database = this.session.getDatabase();
    int sleep = 1 + MathUtils.randomInt(10);
    for (;;)
    {
      try
      {
        if (database.isMultiThreaded()) {
          Thread.sleep(sleep);
        } else {
          database.wait(sleep);
        }
      }
      catch (InterruptedException e1) {}
      long slept = System.nanoTime() / 1000000L - now;
      if (slept >= sleep) {
        break;
      }
    }
    return start == 0L ? now : start;
  }
  
  public void close()
  {
    this.canReuse = true;
  }
  
  public void cancel()
  {
    this.cancel = true;
  }
  
  public String toString()
  {
    return this.sql + Trace.formatParams(getParameters());
  }
  
  public boolean isCacheable()
  {
    return false;
  }
  
  public boolean canReuse()
  {
    return this.canReuse;
  }
  
  public void reuse()
  {
    this.canReuse = false;
    ArrayList<? extends ParameterInterface> parameters = getParameters();
    int i = 0;
    for (int size = parameters.size(); i < size; i++)
    {
      ParameterInterface param = (ParameterInterface)parameters.get(i);
      param.setValue(null, true);
    }
  }
}
