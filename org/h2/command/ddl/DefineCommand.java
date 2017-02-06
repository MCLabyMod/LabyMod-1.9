package org.h2.command.ddl;

import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.result.ResultInterface;

public abstract class DefineCommand
  extends Prepared
{
  protected boolean transactional;
  
  DefineCommand(Session session)
  {
    super(session);
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public void setTransactional(boolean transactional)
  {
    this.transactional = transactional;
  }
  
  public boolean isTransactional()
  {
    return this.transactional;
  }
}
