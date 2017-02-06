package org.h2.command;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.expression.Parameter;
import org.h2.expression.ParameterInterface;
import org.h2.result.ResultInterface;
import org.h2.value.Value;
import org.h2.value.ValueNull;

class CommandContainer
  extends Command
{
  private Prepared prepared;
  private boolean readOnlyKnown;
  private boolean readOnly;
  
  CommandContainer(Parser parser, String sql, Prepared prepared)
  {
    super(parser, sql);
    prepared.setCommand(this);
    this.prepared = prepared;
  }
  
  public ArrayList<? extends ParameterInterface> getParameters()
  {
    return this.prepared.getParameters();
  }
  
  public boolean isTransactional()
  {
    return this.prepared.isTransactional();
  }
  
  public boolean isQuery()
  {
    return this.prepared.isQuery();
  }
  
  private void recompileIfRequired()
  {
    if (this.prepared.needRecompile())
    {
      this.prepared.setModificationMetaId(0L);
      String sql = this.prepared.getSQL();
      ArrayList<Parameter> oldParams = this.prepared.getParameters();
      Parser parser = new Parser(this.session);
      this.prepared = parser.parse(sql);
      long mod = this.prepared.getModificationMetaId();
      this.prepared.setModificationMetaId(0L);
      ArrayList<Parameter> newParams = this.prepared.getParameters();
      int i = 0;
      for (int size = newParams.size(); i < size; i++)
      {
        Parameter old = (Parameter)oldParams.get(i);
        if (old.isValueSet())
        {
          Value v = old.getValue(this.session);
          Parameter p = (Parameter)newParams.get(i);
          p.setValue(v);
        }
      }
      this.prepared.prepare();
      this.prepared.setModificationMetaId(mod);
    }
  }
  
  public int update()
  {
    recompileIfRequired();
    setProgress(5);
    start();
    this.session.setLastScopeIdentity(ValueNull.INSTANCE);
    this.prepared.checkParameters();
    int updateCount = this.prepared.update();
    this.prepared.trace(this.startTime, updateCount);
    setProgress(6);
    return updateCount;
  }
  
  public ResultInterface query(int maxrows)
  {
    recompileIfRequired();
    setProgress(5);
    start();
    this.prepared.checkParameters();
    ResultInterface result = this.prepared.query(maxrows);
    this.prepared.trace(this.startTime, result.getRowCount());
    setProgress(6);
    return result;
  }
  
  public boolean isReadOnly()
  {
    if (!this.readOnlyKnown)
    {
      this.readOnly = this.prepared.isReadOnly();
      this.readOnlyKnown = true;
    }
    return this.readOnly;
  }
  
  public ResultInterface queryMeta()
  {
    return this.prepared.queryMeta();
  }
  
  public boolean isCacheable()
  {
    return this.prepared.isCacheable();
  }
  
  public int getCommandType()
  {
    return this.prepared.getType();
  }
}
