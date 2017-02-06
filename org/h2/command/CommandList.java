package org.h2.command;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.expression.ParameterInterface;
import org.h2.result.ResultInterface;

class CommandList
  extends Command
{
  private final Command command;
  private final String remaining;
  
  CommandList(Parser parser, String sql, Command c, String remaining)
  {
    super(parser, sql);
    this.command = c;
    this.remaining = remaining;
  }
  
  public ArrayList<? extends ParameterInterface> getParameters()
  {
    return this.command.getParameters();
  }
  
  private void executeRemaining()
  {
    Command remainingCommand = this.session.prepareLocal(this.remaining);
    if (remainingCommand.isQuery()) {
      remainingCommand.query(0);
    } else {
      remainingCommand.update();
    }
  }
  
  public int update()
  {
    int updateCount = this.command.executeUpdate();
    executeRemaining();
    return updateCount;
  }
  
  public ResultInterface query(int maxrows)
  {
    ResultInterface result = this.command.query(maxrows);
    executeRemaining();
    return result;
  }
  
  public boolean isQuery()
  {
    return this.command.isQuery();
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public ResultInterface queryMeta()
  {
    return this.command.queryMeta();
  }
  
  public int getCommandType()
  {
    return this.command.getCommandType();
  }
}
