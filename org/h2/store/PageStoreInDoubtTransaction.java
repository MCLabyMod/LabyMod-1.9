package org.h2.store;

import org.h2.message.DbException;

public class PageStoreInDoubtTransaction
  implements InDoubtTransaction
{
  private final PageStore store;
  private final int sessionId;
  private final int pos;
  private final String transactionName;
  private int state;
  
  public PageStoreInDoubtTransaction(PageStore store, int sessionId, int pos, String transaction)
  {
    this.store = store;
    this.sessionId = sessionId;
    this.pos = pos;
    this.transactionName = transaction;
    this.state = 0;
  }
  
  public void setState(int state)
  {
    switch (state)
    {
    case 1: 
      this.store.setInDoubtTransactionState(this.sessionId, this.pos, true);
      break;
    case 2: 
      this.store.setInDoubtTransactionState(this.sessionId, this.pos, false);
      break;
    default: 
      DbException.throwInternalError("state=" + state);
    }
    this.state = state;
  }
  
  public String getState()
  {
    switch (this.state)
    {
    case 0: 
      return "IN_DOUBT";
    case 1: 
      return "COMMIT";
    case 2: 
      return "ROLLBACK";
    }
    throw DbException.throwInternalError("state=" + this.state);
  }
  
  public String getTransactionName()
  {
    return this.transactionName;
  }
}
