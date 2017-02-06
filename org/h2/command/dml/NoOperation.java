package org.h2.command.dml;

import org.h2.command.Prepared;
import org.h2.engine.Session;
import org.h2.result.ResultInterface;

public class NoOperation
  extends Prepared
{
  public NoOperation(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    return 0;
  }
  
  public boolean isQuery()
  {
    return false;
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public boolean needRecompile()
  {
    return false;
  }
  
  public boolean isReadOnly()
  {
    return true;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public int getType()
  {
    return 63;
  }
}
