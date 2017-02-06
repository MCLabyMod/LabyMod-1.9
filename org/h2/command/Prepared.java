package org.h2.command;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.QueryStatisticsData;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.Parameter;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.ResultInterface;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;

public abstract class Prepared
{
  protected Session session;
  protected String sqlStatement;
  protected boolean create = true;
  protected ArrayList<Parameter> parameters;
  protected boolean prepareAlways;
  private long modificationMetaId;
  private Command command;
  private int objectId;
  private int currentRowNumber;
  private int rowScanCount;
  
  public Prepared(Session session)
  {
    this.session = session;
    this.modificationMetaId = session.getDatabase().getModificationMetaId();
  }
  
  public abstract boolean isTransactional();
  
  public abstract ResultInterface queryMeta();
  
  public abstract int getType();
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public boolean needRecompile()
  {
    Database db = this.session.getDatabase();
    if (db == null) {
      throw DbException.get(90067, "database closed");
    }
    return (this.prepareAlways) || (this.modificationMetaId < db.getModificationMetaId()) || (db.getSettings().recompileAlways);
  }
  
  long getModificationMetaId()
  {
    return this.modificationMetaId;
  }
  
  void setModificationMetaId(long id)
  {
    this.modificationMetaId = id;
  }
  
  public void setParameterList(ArrayList<Parameter> parameters)
  {
    this.parameters = parameters;
  }
  
  public ArrayList<Parameter> getParameters()
  {
    return this.parameters;
  }
  
  protected void checkParameters()
  {
    if (this.parameters != null)
    {
      int i = 0;
      for (int size = this.parameters.size(); i < size; i++)
      {
        Parameter param = (Parameter)this.parameters.get(i);
        param.checkSet();
      }
    }
  }
  
  public void setCommand(Command command)
  {
    this.command = command;
  }
  
  public boolean isQuery()
  {
    return false;
  }
  
  public void prepare() {}
  
  public int update()
  {
    throw DbException.get(90001);
  }
  
  public ResultInterface query(int maxrows)
  {
    throw DbException.get(90002);
  }
  
  public void setSQL(String sql)
  {
    this.sqlStatement = sql;
  }
  
  public String getSQL()
  {
    return this.sqlStatement;
  }
  
  protected int getCurrentObjectId()
  {
    return this.objectId;
  }
  
  protected int getObjectId()
  {
    int id = this.objectId;
    if (id == 0) {
      id = this.session.getDatabase().allocateObjectId();
    } else {
      this.objectId = 0;
    }
    return id;
  }
  
  public String getPlanSQL()
  {
    return null;
  }
  
  public void checkCanceled()
  {
    this.session.checkCanceled();
    Command c = this.command != null ? this.command : this.session.getCurrentCommand();
    if (c != null) {
      c.checkCanceled();
    }
  }
  
  public void setObjectId(int i)
  {
    this.objectId = i;
    this.create = false;
  }
  
  public void setSession(Session currentSession)
  {
    this.session = currentSession;
  }
  
  void trace(long startTime, int rowCount)
  {
    if ((this.session.getTrace().isInfoEnabled()) && (startTime > 0L))
    {
      long deltaTime = System.currentTimeMillis() - startTime;
      String params = Trace.formatParams(this.parameters);
      this.session.getTrace().infoSQL(this.sqlStatement, params, rowCount, deltaTime);
    }
    if (this.session.getDatabase().getQueryStatistics())
    {
      long deltaTime = System.currentTimeMillis() - startTime;
      this.session.getDatabase().getQueryStatisticsData().update(toString(), deltaTime, rowCount);
    }
  }
  
  public void setPrepareAlways(boolean prepareAlways)
  {
    this.prepareAlways = prepareAlways;
  }
  
  protected void setCurrentRowNumber(int rowNumber)
  {
    if ((++this.rowScanCount & 0x7F) == 0) {
      checkCanceled();
    }
    this.currentRowNumber = rowNumber;
    setProgress();
  }
  
  public int getCurrentRowNumber()
  {
    return this.currentRowNumber;
  }
  
  private void setProgress()
  {
    if ((this.currentRowNumber & 0x7F) == 0) {
      this.session.getDatabase().setProgress(7, this.sqlStatement, this.currentRowNumber, 0);
    }
  }
  
  public String toString()
  {
    return this.sqlStatement;
  }
  
  protected static String getSQL(Value[] values)
  {
    StatementBuilder buff = new StatementBuilder();
    for (Value v : values)
    {
      buff.appendExceptFirst(", ");
      if (v != null) {
        buff.append(v.getSQL());
      }
    }
    return buff.toString();
  }
  
  protected static String getSQL(Expression[] list)
  {
    StatementBuilder buff = new StatementBuilder();
    for (Expression e : list)
    {
      buff.appendExceptFirst(", ");
      if (e != null) {
        buff.append(e.getSQL());
      }
    }
    return buff.toString();
  }
  
  protected DbException setRow(DbException e, int rowId, String values)
  {
    StringBuilder buff = new StringBuilder();
    if (this.sqlStatement != null) {
      buff.append(this.sqlStatement);
    }
    buff.append(" -- ");
    if (rowId > 0) {
      buff.append("row #").append(rowId + 1).append(' ');
    }
    buff.append('(').append(values).append(')');
    return e.addSQL(buff.toString());
  }
  
  public boolean isCacheable()
  {
    return false;
  }
}
