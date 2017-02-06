package org.h2.command.dml;

import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;

public class TransactionCommand
  extends Prepared
{
  private final int type;
  private String savepointName;
  private String transactionName;
  
  public TransactionCommand(Session session, int type)
  {
    super(session);
    this.type = type;
  }
  
  public void setSavepointName(String name)
  {
    this.savepointName = name;
  }
  
  public int update()
  {
    switch (this.type)
    {
    case 69: 
      this.session.setAutoCommit(true);
      break;
    case 70: 
      this.session.setAutoCommit(false);
      break;
    case 83: 
      this.session.begin();
      break;
    case 71: 
      this.session.commit(false);
      break;
    case 72: 
      this.session.rollback();
      break;
    case 73: 
      this.session.getUser().checkAdmin();
      this.session.getDatabase().checkpoint();
      break;
    case 74: 
      this.session.addSavepoint(this.savepointName);
      break;
    case 75: 
      this.session.rollbackToSavepoint(this.savepointName);
      break;
    case 76: 
      this.session.getUser().checkAdmin();
      this.session.getDatabase().sync();
      break;
    case 77: 
      this.session.prepareCommit(this.transactionName);
      break;
    case 78: 
      this.session.getUser().checkAdmin();
      this.session.setPreparedTransaction(this.transactionName, true);
      break;
    case 79: 
      this.session.getUser().checkAdmin();
      this.session.setPreparedTransaction(this.transactionName, false);
      break;
    case 81: 
      this.session.getUser().checkAdmin();
      this.session.getDatabase().shutdownImmediately();
      break;
    case 80: 
    case 82: 
    case 84: 
      this.session.getUser().checkAdmin();
      this.session.commit(false);
      if ((this.type == 82) || (this.type == 84)) {
        this.session.getDatabase().setCompactMode(this.type);
      }
      this.session.getDatabase().setCloseDelay(0);
      Database db = this.session.getDatabase();
      
      this.session.throttle();
      for (Session s : db.getSessions(false))
      {
        if (db.isMultiThreaded()) {
          synchronized (s)
          {
            s.rollback();
          }
        } else {
          s.rollback();
        }
        if (s != this.session) {
          s.close();
        }
      }
      this.session.close();
      break;
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    return 0;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public boolean needRecompile()
  {
    return false;
  }
  
  public void setTransactionName(String string)
  {
    this.transactionName = string;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public boolean isCacheable()
  {
    return true;
  }
}
