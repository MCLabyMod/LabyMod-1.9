package org.h2.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import org.h2.command.CommandInterface;
import org.h2.engine.SessionInterface;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.result.ResultInterface;
import org.h2.util.New;

public class JdbcStatement
  extends TraceObject
  implements Statement
{
  protected JdbcConnection conn;
  protected SessionInterface session;
  protected JdbcResultSet resultSet;
  protected int maxRows;
  protected int fetchSize = SysProperties.SERVER_RESULT_SET_FETCH_SIZE;
  protected int updateCount;
  protected final int resultSetType;
  protected final int resultSetConcurrency;
  protected final boolean closedByResultSet;
  private CommandInterface executingCommand;
  private int lastExecutedCommandType;
  private ArrayList<String> batchCommands;
  private boolean escapeProcessing = true;
  private boolean cancelled;
  
  JdbcStatement(JdbcConnection conn, int id, int resultSetType, int resultSetConcurrency, boolean closeWithResultSet)
  {
    this.conn = conn;
    this.session = conn.getSession();
    setTrace(this.session.getTrace(), 8, id);
    this.resultSetType = resultSetType;
    this.resultSetConcurrency = resultSetConcurrency;
    this.closedByResultSet = closeWithResultSet;
  }
  
  public ResultSet executeQuery(String sql)
    throws SQLException
  {
    try
    {
      int id = getNextId(4);
      if (isDebugEnabled()) {
        debugCodeAssign("ResultSet", 4, id, "executeQuery(" + quote(sql) + ")");
      }
      synchronized (this.session)
      {
        checkClosed();
        closeOldResultSet();
        sql = JdbcConnection.translateSQL(sql, this.escapeProcessing);
        CommandInterface command = this.conn.prepareCommand(sql, this.fetchSize);
        
        boolean scrollable = this.resultSetType != 1003;
        boolean updatable = this.resultSetConcurrency == 1008;
        setExecutingStatement(command);
        ResultInterface result;
        try
        {
          result = command.executeQuery(this.maxRows, scrollable);
        }
        finally
        {
          setExecutingStatement(null);
        }
        command.close();
        this.resultSet = new JdbcResultSet(this.conn, this, result, id, this.closedByResultSet, scrollable, updatable);
      }
      return this.resultSet;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("executeUpdate", sql);
      return executeUpdateInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private int executeUpdateInternal(String sql)
    throws SQLException
  {
    checkClosedForWrite();
    try
    {
      closeOldResultSet();
      sql = JdbcConnection.translateSQL(sql, this.escapeProcessing);
      CommandInterface command = this.conn.prepareCommand(sql, this.fetchSize);
      synchronized (this.session)
      {
        setExecutingStatement(command);
        try
        {
          this.updateCount = command.executeUpdate();
        }
        finally
        {
          setExecutingStatement(null);
        }
      }
      command.close();
      return this.updateCount;
    }
    finally
    {
      afterWriting();
    }
  }
  
  public boolean execute(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("execute", sql);
      return executeInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private boolean executeInternal(String sql)
    throws SQLException
  {
    int id = getNextId(4);
    checkClosedForWrite();
    try
    {
      closeOldResultSet();
      sql = JdbcConnection.translateSQL(sql, this.escapeProcessing);
      CommandInterface command = this.conn.prepareCommand(sql, this.fetchSize);
      boolean returnsResultSet;
      synchronized (this.session)
      {
        setExecutingStatement(command);
        try
        {
          if (command.isQuery())
          {
            boolean returnsResultSet = true;
            boolean scrollable = this.resultSetType != 1003;
            boolean updatable = this.resultSetConcurrency == 1008;
            ResultInterface result = command.executeQuery(this.maxRows, scrollable);
            this.resultSet = new JdbcResultSet(this.conn, this, result, id, this.closedByResultSet, scrollable, updatable);
          }
          else
          {
            returnsResultSet = false;
            this.updateCount = command.executeUpdate();
          }
        }
        finally
        {
          setExecutingStatement(null);
        }
      }
      command.close();
      return returnsResultSet;
    }
    finally
    {
      afterWriting();
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      checkClosed();
      if (this.resultSet != null)
      {
        int id = this.resultSet.getTraceId();
        debugCodeAssign("ResultSet", 4, id, "getResultSet()");
      }
      else
      {
        debugCodeCall("getResultSet");
      }
      return this.resultSet;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      debugCodeCall("getUpdateCount");
      checkClosed();
      return this.updateCount;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      debugCodeCall("close");
      synchronized (this.session)
      {
        closeOldResultSet();
        if (this.conn != null) {
          this.conn = null;
        }
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Connection getConnection()
  {
    debugCodeCall("getConnection");
    return this.conn;
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      debugCodeCall("getWarnings");
      checkClosed();
      return null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      debugCodeCall("clearWarnings");
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setCursorName(String name)
    throws SQLException
  {
    try
    {
      debugCodeCall("setCursorName", name);
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setFetchDirection(int direction)
    throws SQLException
  {
    try
    {
      debugCodeCall("setFetchDirection", direction);
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      debugCodeCall("getFetchDirection");
      checkClosed();
      return 1000;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      debugCodeCall("getMaxRows");
      checkClosed();
      return this.maxRows;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setMaxRows(int maxRows)
    throws SQLException
  {
    try
    {
      debugCodeCall("setMaxRows", maxRows);
      checkClosed();
      if (maxRows < 0) {
        throw DbException.getInvalidValueException("maxRows", Integer.valueOf(maxRows));
      }
      this.maxRows = maxRows;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setFetchSize(int rows)
    throws SQLException
  {
    try
    {
      debugCodeCall("setFetchSize", rows);
      checkClosed();
      if ((rows < 0) || ((rows > 0) && (this.maxRows > 0) && (rows > this.maxRows))) {
        throw DbException.getInvalidValueException("rows", Integer.valueOf(rows));
      }
      if (rows == 0) {
        rows = SysProperties.SERVER_RESULT_SET_FETCH_SIZE;
      }
      this.fetchSize = rows;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      debugCodeCall("getFetchSize");
      checkClosed();
      return this.fetchSize;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      debugCodeCall("getResultSetConcurrency");
      checkClosed();
      return this.resultSetConcurrency;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      debugCodeCall("getResultSetType");
      checkClosed();
      return this.resultSetType;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      debugCodeCall("getMaxFieldSize");
      checkClosed();
      return 0;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setMaxFieldSize(int max)
    throws SQLException
  {
    try
    {
      debugCodeCall("setMaxFieldSize", max);
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setEscapeProcessing(boolean enable)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setEscapeProcessing(" + enable + ");");
      }
      checkClosed();
      this.escapeProcessing = enable;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      debugCodeCall("cancel");
      checkClosed();
      
      CommandInterface c = this.executingCommand;
      try
      {
        if (c != null)
        {
          c.cancel();
          this.cancelled = true;
        }
      }
      finally
      {
        setExecutingStatement(null);
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean wasCancelled()
  {
    return this.cancelled;
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      debugCodeCall("getQueryTimeout");
      checkClosed();
      return this.conn.getQueryTimeout();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setQueryTimeout(int seconds)
    throws SQLException
  {
    try
    {
      debugCodeCall("setQueryTimeout", seconds);
      checkClosed();
      if (seconds < 0) {
        throw DbException.getInvalidValueException("seconds", Integer.valueOf(seconds));
      }
      this.conn.setQueryTimeout(seconds);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void addBatch(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("addBatch", sql);
      checkClosed();
      sql = JdbcConnection.translateSQL(sql, this.escapeProcessing);
      if (this.batchCommands == null) {
        this.batchCommands = New.arrayList();
      }
      this.batchCommands.add(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      debugCodeCall("clearBatch");
      checkClosed();
      this.batchCommands = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /* Error */
  public int[] executeBatch()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 319
    //   4: invokevirtual 210	org/h2/jdbc/JdbcStatement:debugCodeCall	(Ljava/lang/String;)V
    //   7: aload_0
    //   8: invokevirtual 181	org/h2/jdbc/JdbcStatement:checkClosedForWrite	()Z
    //   11: pop
    //   12: aload_0
    //   13: getfield 302	org/h2/jdbc/JdbcStatement:batchCommands	Ljava/util/ArrayList;
    //   16: ifnonnull +10 -> 26
    //   19: aload_0
    //   20: invokestatic 308	org/h2/util/New:arrayList	()Ljava/util/ArrayList;
    //   23: putfield 302	org/h2/jdbc/JdbcStatement:batchCommands	Ljava/util/ArrayList;
    //   26: aload_0
    //   27: getfield 302	org/h2/jdbc/JdbcStatement:batchCommands	Ljava/util/ArrayList;
    //   30: invokevirtual 322	java/util/ArrayList:size	()I
    //   33: istore_1
    //   34: iload_1
    //   35: newarray <illegal type>
    //   37: astore_2
    //   38: iconst_0
    //   39: istore_3
    //   40: aconst_null
    //   41: astore 4
    //   43: iconst_0
    //   44: istore 5
    //   46: iload 5
    //   48: iload_1
    //   49: if_icmpge +77 -> 126
    //   52: aload_0
    //   53: getfield 302	org/h2/jdbc/JdbcStatement:batchCommands	Ljava/util/ArrayList;
    //   56: iload 5
    //   58: invokevirtual 328	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   61: checkcast 141	java/lang/String
    //   64: astore 6
    //   66: aload_2
    //   67: iload 5
    //   69: aload_0
    //   70: aload 6
    //   72: invokespecial 178	org/h2/jdbc/JdbcStatement:executeUpdateInternal	(Ljava/lang/String;)I
    //   75: iastore
    //   76: goto +44 -> 120
    //   79: astore 7
    //   81: aload_0
    //   82: aload 7
    //   84: invokevirtual 159	org/h2/jdbc/JdbcStatement:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   87: astore 8
    //   89: aload 4
    //   91: ifnonnull +10 -> 101
    //   94: aload 8
    //   96: astore 4
    //   98: goto +14 -> 112
    //   101: aload 8
    //   103: aload 4
    //   105: invokevirtual 332	java/sql/SQLException:setNextException	(Ljava/sql/SQLException;)V
    //   108: aload 8
    //   110: astore 4
    //   112: aload_2
    //   113: iload 5
    //   115: bipush -3
    //   117: iastore
    //   118: iconst_1
    //   119: istore_3
    //   120: iinc 5 1
    //   123: goto -77 -> 46
    //   126: aload_0
    //   127: aconst_null
    //   128: putfield 302	org/h2/jdbc/JdbcStatement:batchCommands	Ljava/util/ArrayList;
    //   131: iload_3
    //   132: ifeq +14 -> 146
    //   135: new 334	org/h2/jdbc/JdbcBatchUpdateException
    //   138: dup
    //   139: aload 4
    //   141: aload_2
    //   142: invokespecial 337	org/h2/jdbc/JdbcBatchUpdateException:<init>	(Ljava/sql/SQLException;[I)V
    //   145: athrow
    //   146: aload_2
    //   147: astore 5
    //   149: aload_0
    //   150: invokevirtual 189	org/h2/jdbc/JdbcStatement:afterWriting	()V
    //   153: aload 5
    //   155: areturn
    //   156: astore 9
    //   158: aload_0
    //   159: invokevirtual 189	org/h2/jdbc/JdbcStatement:afterWriting	()V
    //   162: aload 9
    //   164: athrow
    //   165: astore_1
    //   166: aload_0
    //   167: aload_1
    //   168: invokevirtual 159	org/h2/jdbc/JdbcStatement:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   171: athrow
    // Line number table:
    //   Java source line #646	-> byte code offset #0
    //   Java source line #647	-> byte code offset #7
    //   Java source line #649	-> byte code offset #12
    //   Java source line #652	-> byte code offset #19
    //   Java source line #654	-> byte code offset #26
    //   Java source line #655	-> byte code offset #34
    //   Java source line #656	-> byte code offset #38
    //   Java source line #657	-> byte code offset #40
    //   Java source line #658	-> byte code offset #43
    //   Java source line #659	-> byte code offset #52
    //   Java source line #661	-> byte code offset #66
    //   Java source line #672	-> byte code offset #76
    //   Java source line #662	-> byte code offset #79
    //   Java source line #663	-> byte code offset #81
    //   Java source line #664	-> byte code offset #89
    //   Java source line #665	-> byte code offset #94
    //   Java source line #667	-> byte code offset #101
    //   Java source line #668	-> byte code offset #108
    //   Java source line #670	-> byte code offset #112
    //   Java source line #671	-> byte code offset #118
    //   Java source line #658	-> byte code offset #120
    //   Java source line #674	-> byte code offset #126
    //   Java source line #675	-> byte code offset #131
    //   Java source line #676	-> byte code offset #135
    //   Java source line #678	-> byte code offset #146
    //   Java source line #680	-> byte code offset #149
    //   Java source line #682	-> byte code offset #165
    //   Java source line #683	-> byte code offset #166
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	172	0	this	JdbcStatement
    //   33	16	1	size	int
    //   165	3	1	e	Exception
    //   37	110	2	result	int[]
    //   39	93	3	error	boolean
    //   41	99	4	next	SQLException
    //   44	110	5	i	int
    //   64	7	6	sql	String
    //   79	4	7	re	Exception
    //   87	22	8	e	SQLException
    //   156	7	9	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   66	76	79	java/lang/Exception
    //   12	149	156	finally
    //   156	158	156	finally
    //   0	153	165	java/lang/Exception
    //   156	165	165	java/lang/Exception
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      int id = getNextId(4);
      if (isDebugEnabled()) {
        debugCodeAssign("ResultSet", 4, id, "getGeneratedKeys()");
      }
      checkClosed();
      return this.conn.getGeneratedKeys(this, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      debugCodeCall("getMoreResults");
      checkClosed();
      closeOldResultSet();
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean getMoreResults(int current)
    throws SQLException
  {
    try
    {
      debugCodeCall("getMoreResults", current);
      switch (current)
      {
      case 1: 
      case 3: 
        checkClosed();
        closeOldResultSet();
        break;
      case 2: 
        break;
      default: 
        throw DbException.getInvalidValueException("current", Integer.valueOf(current));
      }
      return false;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql, int autoGeneratedKeys)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("executeUpdate(" + quote(sql) + ", " + autoGeneratedKeys + ");");
      }
      return executeUpdateInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql, int[] columnIndexes)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("executeUpdate(" + quote(sql) + ", " + quoteIntArray(columnIndexes) + ");");
      }
      return executeUpdateInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql, String[] columnNames)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("executeUpdate(" + quote(sql) + ", " + quoteArray(columnNames) + ");");
      }
      return executeUpdateInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql, int autoGeneratedKeys)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("execute(" + quote(sql) + ", " + autoGeneratedKeys + ");");
      }
      return executeInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql, int[] columnIndexes)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("execute(" + quote(sql) + ", " + quoteIntArray(columnIndexes) + ");");
      }
      return executeInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql, String[] columnNames)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("execute(" + quote(sql) + ", " + quoteArray(columnNames) + ");");
      }
      return executeInternal(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      debugCodeCall("getResultSetHoldability");
      checkClosed();
      return 1;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  boolean checkClosed()
  {
    return checkClosed(false);
  }
  
  boolean checkClosedForWrite()
  {
    return checkClosed(true);
  }
  
  protected boolean checkClosed(boolean write)
  {
    if (this.conn == null) {
      throw DbException.get(90007);
    }
    this.conn.checkClosed(write);
    SessionInterface s = this.conn.getSession();
    if (s != this.session)
    {
      this.session = s;
      this.trace = this.session.getTrace();
      return true;
    }
    return false;
  }
  
  void afterWriting()
  {
    if (this.conn != null) {
      this.conn.afterWriting();
    }
  }
  
  protected void closeOldResultSet()
    throws SQLException
  {
    try
    {
      if ((!this.closedByResultSet) && 
        (this.resultSet != null)) {
        this.resultSet.closeInternal();
      }
    }
    finally
    {
      this.cancelled = false;
      this.resultSet = null;
      this.updateCount = -1;
    }
  }
  
  protected void setExecutingStatement(CommandInterface c)
  {
    if (c == null)
    {
      this.conn.setExecutingStatement(null);
    }
    else
    {
      this.conn.setExecutingStatement(this);
      this.lastExecutedCommandType = c.getCommandType();
    }
    this.executingCommand = c;
  }
  
  public int getLastExecutedCommandType()
  {
    return this.lastExecutedCommandType;
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      debugCodeCall("isClosed");
      return this.conn == null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    if (isWrapperFor(iface)) {
      return this;
    }
    throw DbException.getInvalidValueException("iface", iface);
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    return (iface != null) && (iface.isAssignableFrom(getClass()));
  }
  
  public boolean isPoolable()
  {
    debugCodeCall("isPoolable");
    return false;
  }
  
  public void setPoolable(boolean poolable)
  {
    if (isDebugEnabled()) {
      debugCode("setPoolable(" + poolable + ");");
    }
  }
  
  public String toString()
  {
    return getTraceObjectName();
  }
  
  public void closeOnCompletion()
    throws SQLException
  {}
  
  public boolean isCloseOnCompletion()
    throws SQLException
  {
    return false;
  }
}
