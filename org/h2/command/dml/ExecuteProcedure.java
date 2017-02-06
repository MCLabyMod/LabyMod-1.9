package org.h2.command.dml;

import java.util.ArrayList;
import org.h2.command.Prepared;
import org.h2.engine.Procedure;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.Parameter;
import org.h2.result.ResultInterface;
import org.h2.util.New;

public class ExecuteProcedure
  extends Prepared
{
  private final ArrayList<Expression> expressions = New.arrayList();
  private Procedure procedure;
  
  public ExecuteProcedure(Session session)
  {
    super(session);
  }
  
  public void setProcedure(Procedure procedure)
  {
    this.procedure = procedure;
  }
  
  public void setExpression(int index, Expression expr)
  {
    this.expressions.add(index, expr);
  }
  
  private void setParameters()
  {
    Prepared prepared = this.procedure.getPrepared();
    ArrayList<Parameter> params = prepared.getParameters();
    for (int i = 0; (params != null) && (i < params.size()) && (i < this.expressions.size()); i++)
    {
      Expression expr = (Expression)this.expressions.get(i);
      Parameter p = (Parameter)params.get(i);
      p.setValue(expr.getValue(this.session));
    }
  }
  
  public boolean isQuery()
  {
    Prepared prepared = this.procedure.getPrepared();
    return prepared.isQuery();
  }
  
  public int update()
  {
    setParameters();
    Prepared prepared = this.procedure.getPrepared();
    return prepared.update();
  }
  
  public ResultInterface query(int limit)
  {
    setParameters();
    Prepared prepared = this.procedure.getPrepared();
    return prepared.query(limit);
  }
  
  public boolean isTransactional()
  {
    return true;
  }
  
  public ResultInterface queryMeta()
  {
    Prepared prepared = this.procedure.getPrepared();
    return prepared.queryMeta();
  }
  
  public int getType()
  {
    return 59;
  }
}
