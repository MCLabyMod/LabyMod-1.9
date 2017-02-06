package org.h2.command.ddl;

import org.h2.engine.Session;

public class DeallocateProcedure
  extends DefineCommand
{
  private String procedureName;
  
  public DeallocateProcedure(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    this.session.removeProcedure(this.procedureName);
    return 0;
  }
  
  public void setProcedureName(String name)
  {
    this.procedureName = name;
  }
  
  public int getType()
  {
    return 35;
  }
}
