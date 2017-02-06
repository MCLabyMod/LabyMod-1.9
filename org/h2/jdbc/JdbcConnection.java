package org.h2.jdbc;

import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.h2.command.CommandInterface;
import org.h2.engine.ConnectionInfo;
import org.h2.engine.SessionInterface;
import org.h2.engine.SessionRemote;
import org.h2.engine.SysProperties;
import org.h2.expression.ParameterInterface;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceObject;
import org.h2.result.ResultInterface;
import org.h2.store.DataHandler;
import org.h2.store.LobStorageInterface;
import org.h2.util.CloseWatcher;
import org.h2.util.JdbcUtils;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;

public class JdbcConnection
  extends TraceObject
  implements Connection
{
  private static boolean keepOpenStackTrace;
  private final String url;
  private final String user;
  private int holdability = 1;
  private SessionInterface session;
  private CommandInterface commit;
  private CommandInterface rollback;
  private CommandInterface getReadOnly;
  private CommandInterface getGeneratedKeys;
  private CommandInterface setLockMode;
  private CommandInterface getLockMode;
  private CommandInterface setQueryTimeout;
  private CommandInterface getQueryTimeout;
  private int savepointId;
  private String catalog;
  private Statement executingStatement;
  private final CompareMode compareMode = CompareMode.getInstance(null, 0);
  private final CloseWatcher watcher;
  private int queryTimeoutCache = -1;
  
  public JdbcConnection(String url, Properties info)
    throws SQLException
  {
    this(new ConnectionInfo(url, info), true);
  }
  
  public JdbcConnection(ConnectionInfo ci, boolean useBaseDir)
    throws SQLException
  {
    try
    {
      if (useBaseDir)
      {
        String baseDir = SysProperties.getBaseDir();
        if (baseDir != null) {
          ci.setBaseDir(baseDir);
        }
      }
      this.session = new SessionRemote(ci).connectEmbeddedOrServer(false);
      this.trace = this.session.getTrace();
      int id = getNextId(1);
      setTrace(this.trace, 1, id);
      this.user = ci.getUserName();
      if (isInfoEnabled()) {
        this.trace.infoCode("Connection " + getTraceObjectName() + " = DriverManager.getConnection(" + quote(ci.getOriginalURL()) + ", " + quote(this.user) + ", \"\");");
      }
      this.url = ci.getURL();
      closeOld();
      this.watcher = CloseWatcher.register(this, this.session, keepOpenStackTrace);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public JdbcConnection(JdbcConnection clone)
  {
    this.session = clone.session;
    this.trace = this.session.getTrace();
    int id = getNextId(1);
    setTrace(this.trace, 1, id);
    this.user = clone.user;
    this.url = clone.url;
    this.catalog = clone.catalog;
    this.commit = clone.commit;
    this.getGeneratedKeys = clone.getGeneratedKeys;
    this.getLockMode = clone.getLockMode;
    this.getQueryTimeout = clone.getQueryTimeout;
    this.getReadOnly = clone.getReadOnly;
    this.rollback = clone.rollback;
    this.watcher = null;
  }
  
  public JdbcConnection(SessionInterface session, String user, String url)
  {
    this.session = session;
    this.trace = session.getTrace();
    int id = getNextId(1);
    setTrace(this.trace, 1, id);
    this.user = user;
    this.url = url;
    this.watcher = null;
  }
  
  private void closeOld()
  {
    for (;;)
    {
      CloseWatcher w = CloseWatcher.pollUnclosed();
      if (w == null) {
        break;
      }
      try
      {
        w.getCloseable().close();
      }
      catch (Exception e)
      {
        this.trace.error(e, "closing session");
      }
      keepOpenStackTrace = true;
      String s = w.getOpenStackTrace();
      Exception ex = DbException.get(90018);
      this.trace.error(ex, s);
    }
  }
  
  public Statement createStatement()
    throws SQLException
  {
    try
    {
      int id = getNextId(8);
      if (isDebugEnabled()) {
        debugCodeAssign("Statement", 8, id, "createStatement()");
      }
      checkClosed();
      return new JdbcStatement(this, id, 1003, 1007, false);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Statement createStatement(int resultSetType, int resultSetConcurrency)
    throws SQLException
  {
    try
    {
      int id = getNextId(8);
      if (isDebugEnabled()) {
        debugCodeAssign("Statement", 8, id, "createStatement(" + resultSetType + ", " + resultSetConcurrency + ")");
      }
      checkTypeConcurrency(resultSetType, resultSetConcurrency);
      checkClosed();
      return new JdbcStatement(this, id, resultSetType, resultSetConcurrency, false);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
    throws SQLException
  {
    try
    {
      int id = getNextId(8);
      if (isDebugEnabled()) {
        debugCodeAssign("Statement", 8, id, "createStatement(" + resultSetType + ", " + resultSetConcurrency + ", " + resultSetHoldability + ")");
      }
      checkTypeConcurrency(resultSetType, resultSetConcurrency);
      checkHoldability(resultSetHoldability);
      checkClosed();
      return new JdbcStatement(this, id, resultSetType, resultSetConcurrency, false);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public PreparedStatement prepareStatement(String sql)
    throws SQLException
  {
    try
    {
      int id = getNextId(3);
      if (isDebugEnabled()) {
        debugCodeAssign("PreparedStatement", 3, id, "prepareStatement(" + quote(sql) + ")");
      }
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcPreparedStatement(this, sql, id, 1003, 1007, false);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  PreparedStatement prepareAutoCloseStatement(String sql)
    throws SQLException
  {
    try
    {
      int id = getNextId(3);
      if (isDebugEnabled()) {
        debugCodeAssign("PreparedStatement", 3, id, "prepareStatement(" + quote(sql) + ")");
      }
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcPreparedStatement(this, sql, id, 1003, 1007, true);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public DatabaseMetaData getMetaData()
    throws SQLException
  {
    try
    {
      int id = getNextId(2);
      if (isDebugEnabled()) {
        debugCodeAssign("DatabaseMetaData", 2, id, "getMetaData()");
      }
      checkClosed();
      return new JdbcDatabaseMetaData(this, this.trace, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public SessionInterface getSession()
  {
    return this.session;
  }
  
  public synchronized void close()
    throws SQLException
  {
    try
    {
      debugCodeCall("close");
      if (this.session == null) {
        return;
      }
      CloseWatcher.unregister(this.watcher);
      this.session.cancel();
      if (this.executingStatement != null) {
        try
        {
          this.executingStatement.cancel();
        }
        catch (NullPointerException e) {}
      }
      synchronized (this.session)
      {
        try
        {
          if (!this.session.isClosed()) {
            try
            {
              if (this.session.hasPendingTransaction())
              {
                if (!this.session.isReconnectNeeded(true)) {
                  try
                  {
                    rollbackInternal();
                  }
                  catch (DbException e)
                  {
                    if (e.getErrorCode() != 90067) {
                      throw e;
                    }
                  }
                }
                this.session.afterWriting();
              }
              closePreparedCommands();
            }
            finally
            {
              this.session.close();
            }
          }
        }
        finally
        {
          this.session = null;
        }
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private void closePreparedCommands()
  {
    this.commit = closeAndSetNull(this.commit);
    this.rollback = closeAndSetNull(this.rollback);
    this.getReadOnly = closeAndSetNull(this.getReadOnly);
    this.getGeneratedKeys = closeAndSetNull(this.getGeneratedKeys);
    this.getLockMode = closeAndSetNull(this.getLockMode);
    this.setLockMode = closeAndSetNull(this.setLockMode);
    this.getQueryTimeout = closeAndSetNull(this.getQueryTimeout);
    this.setQueryTimeout = closeAndSetNull(this.setQueryTimeout);
  }
  
  private static CommandInterface closeAndSetNull(CommandInterface command)
  {
    if (command != null) {
      command.close();
    }
    return null;
  }
  
  public synchronized void setAutoCommit(boolean autoCommit)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setAutoCommit(" + autoCommit + ");");
      }
      checkClosed();
      if ((autoCommit) && (!this.session.getAutoCommit())) {
        commit();
      }
      this.session.setAutoCommit(autoCommit);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public synchronized boolean getAutoCommit()
    throws SQLException
  {
    try
    {
      checkClosed();
      debugCodeCall("getAutoCommit");
      return this.session.getAutoCommit();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public synchronized void commit()
    throws SQLException
  {
    try
    {
      debugCodeCall("commit");
      checkClosedForWrite();
      try
      {
        this.commit = prepareCommand("COMMIT", this.commit);
        this.commit.executeUpdate();
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public synchronized void rollback()
    throws SQLException
  {
    try
    {
      debugCodeCall("rollback");
      checkClosedForWrite();
      try
      {
        rollbackInternal();
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      debugCodeCall("isClosed");
      return (this.session == null) || (this.session.isClosed());
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String nativeSQL(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("nativeSQL", sql);
      checkClosed();
      return translateSQL(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setReadOnly(boolean readOnly)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setReadOnly(" + readOnly + ");");
      }
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      debugCodeCall("isReadOnly");
      checkClosed();
      this.getReadOnly = prepareCommand("CALL READONLY()", this.getReadOnly);
      ResultInterface result = this.getReadOnly.executeQuery(0, false);
      result.next();
      return result.currentRow()[0].getBoolean().booleanValue();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setCatalog(String catalog)
    throws SQLException
  {
    try
    {
      debugCodeCall("setCatalog", catalog);
      checkClosed();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getCatalog()
    throws SQLException
  {
    try
    {
      debugCodeCall("getCatalog");
      checkClosed();
      if (this.catalog == null)
      {
        CommandInterface cat = prepareCommand("CALL DATABASE()", Integer.MAX_VALUE);
        ResultInterface result = cat.executeQuery(0, false);
        result.next();
        this.catalog = result.currentRow()[0].getString();
        cat.close();
      }
      return this.catalog;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
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
  
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
    throws SQLException
  {
    try
    {
      int id = getNextId(3);
      if (isDebugEnabled()) {
        debugCodeAssign("PreparedStatement", 3, id, "prepareStatement(" + quote(sql) + ", " + resultSetType + ", " + resultSetConcurrency + ")");
      }
      checkTypeConcurrency(resultSetType, resultSetConcurrency);
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcPreparedStatement(this, sql, id, resultSetType, resultSetConcurrency, false);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setTransactionIsolation(int level)
    throws SQLException
  {
    try
    {
      debugCodeCall("setTransactionIsolation", level);
      checkClosed();
      int lockMode;
      switch (level)
      {
      case 1: 
        lockMode = 0;
        break;
      case 2: 
        lockMode = 3;
        break;
      case 4: 
      case 8: 
        lockMode = 1;
        break;
      case 3: 
      case 5: 
      case 6: 
      case 7: 
      default: 
        throw DbException.getInvalidValueException("level", Integer.valueOf(level));
      }
      commit();
      this.setLockMode = prepareCommand("SET LOCK_MODE ?", this.setLockMode);
      ((ParameterInterface)this.setLockMode.getParameters().get(0)).setValue(ValueInt.get(lockMode), false);
      this.setLockMode.executeUpdate();
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
      this.setQueryTimeout = prepareCommand("SET QUERY_TIMEOUT ?", this.setQueryTimeout);
      ((ParameterInterface)this.setQueryTimeout.getParameters().get(0)).setValue(ValueInt.get(seconds * 1000), false);
      
      this.setQueryTimeout.executeUpdate();
      this.queryTimeoutCache = seconds;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  int getQueryTimeout()
    throws SQLException
  {
    try
    {
      if (this.queryTimeoutCache == -1)
      {
        checkClosed();
        this.getQueryTimeout = prepareCommand("SELECT VALUE FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME=?", this.getQueryTimeout);
        
        ((ParameterInterface)this.getQueryTimeout.getParameters().get(0)).setValue(ValueString.get("QUERY_TIMEOUT"), false);
        
        ResultInterface result = this.getQueryTimeout.executeQuery(0, false);
        result.next();
        int queryTimeout = result.currentRow()[0].getInt();
        result.close();
        if (queryTimeout != 0) {
          queryTimeout = (queryTimeout + 999) / 1000;
        }
        this.queryTimeoutCache = queryTimeout;
      }
      return this.queryTimeoutCache;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getTransactionIsolation()
    throws SQLException
  {
    try
    {
      debugCodeCall("getTransactionIsolation");
      checkClosed();
      this.getLockMode = prepareCommand("CALL LOCK_MODE()", this.getLockMode);
      ResultInterface result = this.getLockMode.executeQuery(0, false);
      result.next();
      int lockMode = result.currentRow()[0].getInt();
      result.close();
      int transactionIsolationLevel;
      switch (lockMode)
      {
      case 0: 
        transactionIsolationLevel = 1;
        break;
      case 3: 
        transactionIsolationLevel = 2;
        break;
      case 1: 
      case 2: 
        transactionIsolationLevel = 8;
        break;
      default: 
        throw DbException.throwInternalError("lockMode:" + lockMode);
      }
      return transactionIsolationLevel;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setHoldability(int holdability)
    throws SQLException
  {
    try
    {
      debugCodeCall("setHoldability", holdability);
      checkClosed();
      checkHoldability(holdability);
      this.holdability = holdability;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      debugCodeCall("getHoldability");
      checkClosed();
      return this.holdability;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Map<String, Class<?>> getTypeMap()
    throws SQLException
  {
    try
    {
      debugCodeCall("getTypeMap");
      checkClosed();
      return null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setTypeMap(Map<String, Class<?>> map)
    throws SQLException
  {
    try
    {
      debugCode("setTypeMap(" + quoteMap(map) + ");");
      checkMap(map);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public CallableStatement prepareCall(String sql)
    throws SQLException
  {
    try
    {
      int id = getNextId(0);
      if (isDebugEnabled()) {
        debugCodeAssign("CallableStatement", 0, id, "prepareCall(" + quote(sql) + ")");
      }
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcCallableStatement(this, sql, id, 1003, 1007);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
    throws SQLException
  {
    try
    {
      int id = getNextId(0);
      if (isDebugEnabled()) {
        debugCodeAssign("CallableStatement", 0, id, "prepareCall(" + quote(sql) + ", " + resultSetType + ", " + resultSetConcurrency + ")");
      }
      checkTypeConcurrency(resultSetType, resultSetConcurrency);
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcCallableStatement(this, sql, id, resultSetType, resultSetConcurrency);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
    throws SQLException
  {
    try
    {
      int id = getNextId(0);
      if (isDebugEnabled()) {
        debugCodeAssign("CallableStatement", 0, id, "prepareCall(" + quote(sql) + ", " + resultSetType + ", " + resultSetConcurrency + ", " + resultSetHoldability + ")");
      }
      checkTypeConcurrency(resultSetType, resultSetConcurrency);
      checkHoldability(resultSetHoldability);
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcCallableStatement(this, sql, id, resultSetType, resultSetConcurrency);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Savepoint setSavepoint()
    throws SQLException
  {
    try
    {
      int id = getNextId(6);
      if (isDebugEnabled()) {
        debugCodeAssign("Savepoint", 6, id, "setSavepoint()");
      }
      checkClosed();
      CommandInterface set = prepareCommand("SAVEPOINT " + JdbcSavepoint.getName(null, this.savepointId), Integer.MAX_VALUE);
      
      set.executeUpdate();
      JdbcSavepoint savepoint = new JdbcSavepoint(this, this.savepointId, null, this.trace, id);
      this.savepointId += 1;
      return savepoint;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Savepoint setSavepoint(String name)
    throws SQLException
  {
    try
    {
      int id = getNextId(6);
      if (isDebugEnabled()) {
        debugCodeAssign("Savepoint", 6, id, "setSavepoint(" + quote(name) + ")");
      }
      checkClosed();
      CommandInterface set = prepareCommand("SAVEPOINT " + JdbcSavepoint.getName(name, 0), Integer.MAX_VALUE);
      
      set.executeUpdate();
      return new JdbcSavepoint(this, 0, name, this.trace, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void rollback(Savepoint savepoint)
    throws SQLException
  {
    try
    {
      JdbcSavepoint sp = convertSavepoint(savepoint);
      debugCode("rollback(" + sp.getTraceObjectName() + ");");
      checkClosedForWrite();
      try
      {
        sp.rollback();
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void releaseSavepoint(Savepoint savepoint)
    throws SQLException
  {
    try
    {
      debugCode("releaseSavepoint(savepoint);");
      checkClosed();
      convertSavepoint(savepoint).release();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private static JdbcSavepoint convertSavepoint(Savepoint savepoint)
  {
    if (!(savepoint instanceof JdbcSavepoint)) {
      throw DbException.get(90063, "" + savepoint);
    }
    return (JdbcSavepoint)savepoint;
  }
  
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
    throws SQLException
  {
    try
    {
      int id = getNextId(3);
      if (isDebugEnabled()) {
        debugCodeAssign("PreparedStatement", 3, id, "prepareStatement(" + quote(sql) + ", " + resultSetType + ", " + resultSetConcurrency + ", " + resultSetHoldability + ")");
      }
      checkTypeConcurrency(resultSetType, resultSetConcurrency);
      checkHoldability(resultSetHoldability);
      checkClosed();
      sql = translateSQL(sql);
      return new JdbcPreparedStatement(this, sql, id, resultSetType, resultSetConcurrency, false);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("prepareStatement(" + quote(sql) + ", " + autoGeneratedKeys + ");");
      }
      return prepareStatement(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("prepareStatement(" + quote(sql) + ", " + quoteIntArray(columnIndexes) + ");");
      }
      return prepareStatement(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public PreparedStatement prepareStatement(String sql, String[] columnNames)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("prepareStatement(" + quote(sql) + ", " + quoteArray(columnNames) + ");");
      }
      return prepareStatement(sql);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  CommandInterface prepareCommand(String sql, int fetchSize)
  {
    return this.session.prepareCommand(sql, fetchSize);
  }
  
  private CommandInterface prepareCommand(String sql, CommandInterface old)
  {
    return old == null ? this.session.prepareCommand(sql, Integer.MAX_VALUE) : old;
  }
  
  private static int translateGetEnd(String sql, int i, char c)
  {
    int len = sql.length();
    switch (c)
    {
    case '$': 
      if ((i < len - 1) && (sql.charAt(i + 1) == '$') && ((i == 0) || (sql.charAt(i - 1) <= ' ')))
      {
        int j = sql.indexOf("$$", i + 2);
        if (j < 0) {
          throw DbException.getSyntaxError(sql, i);
        }
        return j + 1;
      }
      return i;
    case '\'': 
      int j = sql.indexOf('\'', i + 1);
      if (j < 0) {
        throw DbException.getSyntaxError(sql, i);
      }
      return j;
    case '"': 
      int j = sql.indexOf('"', i + 1);
      if (j < 0) {
        throw DbException.getSyntaxError(sql, i);
      }
      return j;
    case '/': 
      checkRunOver(i + 1, len, sql);
      if (sql.charAt(i + 1) == '*')
      {
        int j = sql.indexOf("*/", i + 2);
        if (j < 0) {
          throw DbException.getSyntaxError(sql, i);
        }
        i = j + 1;
      }
      else if (sql.charAt(i + 1) == '/')
      {
        i += 2;
        while ((i < len) && ((c = sql.charAt(i)) != '\r') && (c != '\n')) {
          i++;
        }
      }
      return i;
    case '-': 
      checkRunOver(i + 1, len, sql);
      if (sql.charAt(i + 1) == '-')
      {
        i += 2;
        while ((i < len) && ((c = sql.charAt(i)) != '\r') && (c != '\n')) {
          i++;
        }
      }
      return i;
    }
    throw DbException.throwInternalError("c=" + c);
  }
  
  private static String translateSQL(String sql)
  {
    return translateSQL(sql, true);
  }
  
  static String translateSQL(String sql, boolean escapeProcessing)
  {
    if (sql == null) {
      throw DbException.getInvalidValueException("SQL", null);
    }
    if (!escapeProcessing) {
      return sql;
    }
    if (sql.indexOf('{') < 0) {
      return sql;
    }
    int len = sql.length();
    char[] chars = null;
    int level = 0;
    char c;
    int remove;
    for (int i = 0; i < len; i++)
    {
      c = sql.charAt(i);
      switch (c)
      {
      case '"': 
      case '\'': 
      case '-': 
      case '/': 
        i = translateGetEnd(sql, i, c);
        break;
      case '{': 
        level++;
        if (chars == null) {
          chars = sql.toCharArray();
        }
        chars[i] = ' ';
        while (Character.isSpaceChar(chars[i]))
        {
          i++;
          checkRunOver(i, len, sql);
        }
        int start = i;
        if ((chars[i] >= '0') && (chars[i] <= '9'))
        {
          chars[(i - 1)] = '{';
          for (;;)
          {
            checkRunOver(i, len, sql);
            c = chars[i];
            if (c == '}') {
              break;
            }
            switch (c)
            {
            case '"': 
            case '\'': 
            case '-': 
            case '/': 
              i = translateGetEnd(sql, i, c);
              break;
            }
            i++;
          }
          level--;
        }
        else
        {
          if (chars[i] == '?')
          {
            i++;
            checkRunOver(i, len, sql);
            while (Character.isSpaceChar(chars[i]))
            {
              i++;
              checkRunOver(i, len, sql);
            }
            if (sql.charAt(i) != '=') {
              throw DbException.getSyntaxError(sql, i, "=");
            }
            i++;
            checkRunOver(i, len, sql);
            while (Character.isSpaceChar(chars[i]))
            {
              i++;
              checkRunOver(i, len, sql);
            }
          }
          while (!Character.isSpaceChar(chars[i]))
          {
            i++;
            checkRunOver(i, len, sql);
          }
          remove = 0;
          if (found(sql, start, "fn"))
          {
            remove = 2;
          }
          else
          {
            if (found(sql, start, "escape")) {
              continue;
            }
            if (found(sql, start, "call")) {
              continue;
            }
            if (found(sql, start, "oj"))
            {
              remove = 2;
            }
            else
            {
              if (found(sql, start, "ts")) {
                continue;
              }
              if (found(sql, start, "t")) {
                continue;
              }
              if (found(sql, start, "d")) {
                continue;
              }
              if (found(sql, start, "params")) {
                remove = "params".length();
              }
            }
          }
          for (i = start; remove > 0;)
          {
            chars[i] = ' ';i++;remove--; continue;
            
            level--;
            if (level < 0) {
              throw DbException.getSyntaxError(sql, i);
            }
            chars[i] = ' ';
            break;
            
            i = translateGetEnd(sql, i, c);
          }
        }
        break;
      }
    }
    if (level != 0) {
      throw DbException.getSyntaxError(sql, sql.length() - 1);
    }
    if (chars != null) {
      sql = new String(chars);
    }
    return sql;
  }
  
  private static void checkRunOver(int i, int len, String sql)
  {
    if (i >= len) {
      throw DbException.getSyntaxError(sql, i);
    }
  }
  
  private static boolean found(String sql, int start, String other)
  {
    return sql.regionMatches(true, start, other, 0, other.length());
  }
  
  private static void checkTypeConcurrency(int resultSetType, int resultSetConcurrency)
  {
    switch (resultSetType)
    {
    case 1003: 
    case 1004: 
    case 1005: 
      break;
    default: 
      throw DbException.getInvalidValueException("resultSetType", Integer.valueOf(resultSetType));
    }
    switch (resultSetConcurrency)
    {
    case 1007: 
    case 1008: 
      break;
    default: 
      throw DbException.getInvalidValueException("resultSetConcurrency", Integer.valueOf(resultSetConcurrency));
    }
  }
  
  private static void checkHoldability(int resultSetHoldability)
  {
    if ((resultSetHoldability != 1) && (resultSetHoldability != 2)) {
      throw DbException.getInvalidValueException("resultSetHoldability", Integer.valueOf(resultSetHoldability));
    }
  }
  
  protected void checkClosed()
  {
    checkClosed(false);
  }
  
  private void checkClosedForWrite()
  {
    checkClosed(true);
  }
  
  protected void checkClosed(boolean write)
  {
    if (this.session == null) {
      throw DbException.get(90007);
    }
    if (this.session.isClosed()) {
      throw DbException.get(90121);
    }
    if (this.session.isReconnectNeeded(write))
    {
      this.trace.debug("reconnect");
      closePreparedCommands();
      this.session = this.session.reconnect(write);
      this.trace = this.session.getTrace();
    }
  }
  
  protected void afterWriting()
  {
    if (this.session != null) {
      this.session.afterWriting();
    }
  }
  
  String getURL()
  {
    checkClosed();
    return this.url;
  }
  
  String getUser()
  {
    checkClosed();
    return this.user;
  }
  
  private void rollbackInternal()
  {
    this.rollback = prepareCommand("ROLLBACK", this.rollback);
    this.rollback.executeUpdate();
  }
  
  public int getPowerOffCount()
  {
    return (this.session == null) || (this.session.isClosed()) ? 0 : this.session.getPowerOffCount();
  }
  
  public void setPowerOffCount(int count)
  {
    if (this.session != null) {
      this.session.setPowerOffCount(count);
    }
  }
  
  public void setExecutingStatement(Statement stat)
  {
    this.executingStatement = stat;
  }
  
  ResultSet getGeneratedKeys(JdbcStatement stat, int id)
  {
    this.getGeneratedKeys = prepareCommand("SELECT SCOPE_IDENTITY() WHERE SCOPE_IDENTITY() IS NOT NULL", this.getGeneratedKeys);
    
    ResultInterface result = this.getGeneratedKeys.executeQuery(0, false);
    ResultSet rs = new JdbcResultSet(this, stat, result, id, false, true, false);
    return rs;
  }
  
  /* Error */
  public java.sql.Clob createClob()
    throws SQLException
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 101	org/h2/jdbc/JdbcConnection:getNextId	(I)I
    //   5: istore_1
    //   6: aload_0
    //   7: ldc_w 773
    //   10: bipush 10
    //   12: iload_1
    //   13: ldc_w 775
    //   16: invokevirtual 238	org/h2/jdbc/JdbcConnection:debugCodeAssign	(Ljava/lang/String;IILjava/lang/String;)V
    //   19: aload_0
    //   20: invokespecial 383	org/h2/jdbc/JdbcConnection:checkClosedForWrite	()V
    //   23: aload_0
    //   24: getfield 87	org/h2/jdbc/JdbcConnection:session	Lorg/h2/engine/SessionInterface;
    //   27: invokeinterface 779 1 0
    //   32: invokeinterface 785 1 0
    //   37: new 787	java/io/InputStreamReader
    //   40: dup
    //   41: new 789	java/io/ByteArrayInputStream
    //   44: dup
    //   45: getstatic 795	org/h2/util/Utils:EMPTY_BYTES	[B
    //   48: invokespecial 798	java/io/ByteArrayInputStream:<init>	([B)V
    //   51: invokespecial 801	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: lconst_0
    //   55: invokeinterface 806 4 0
    //   60: astore_2
    //   61: aload_0
    //   62: getfield 87	org/h2/jdbc/JdbcConnection:session	Lorg/h2/engine/SessionInterface;
    //   65: aload_2
    //   66: invokeinterface 810 2 0
    //   71: new 812	org/h2/jdbc/JdbcClob
    //   74: dup
    //   75: aload_0
    //   76: aload_2
    //   77: iload_1
    //   78: invokespecial 815	org/h2/jdbc/JdbcClob:<init>	(Lorg/h2/jdbc/JdbcConnection;Lorg/h2/value/Value;I)V
    //   81: astore_3
    //   82: aload_0
    //   83: invokevirtual 393	org/h2/jdbc/JdbcConnection:afterWriting	()V
    //   86: aload_3
    //   87: areturn
    //   88: astore 4
    //   90: aload_0
    //   91: invokevirtual 393	org/h2/jdbc/JdbcConnection:afterWriting	()V
    //   94: aload 4
    //   96: athrow
    //   97: astore_1
    //   98: aload_0
    //   99: aload_1
    //   100: invokevirtual 169	org/h2/jdbc/JdbcConnection:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   103: athrow
    // Line number table:
    //   Java source line #1550	-> byte code offset #0
    //   Java source line #1551	-> byte code offset #6
    //   Java source line #1552	-> byte code offset #19
    //   Java source line #1554	-> byte code offset #23
    //   Java source line #1557	-> byte code offset #61
    //   Java source line #1558	-> byte code offset #71
    //   Java source line #1560	-> byte code offset #82
    //   Java source line #1562	-> byte code offset #97
    //   Java source line #1563	-> byte code offset #98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	JdbcConnection
    //   5	73	1	id	int
    //   97	3	1	e	Exception
    //   60	17	2	v	Value
    //   88	7	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   23	82	88	finally
    //   88	90	88	finally
    //   0	86	97	java/lang/Exception
    //   88	97	97	java/lang/Exception
  }
  
  /* Error */
  public java.sql.Blob createBlob()
    throws SQLException
  {
    // Byte code:
    //   0: bipush 9
    //   2: invokestatic 101	org/h2/jdbc/JdbcConnection:getNextId	(I)I
    //   5: istore_1
    //   6: aload_0
    //   7: ldc_w 821
    //   10: bipush 9
    //   12: iload_1
    //   13: ldc_w 775
    //   16: invokevirtual 238	org/h2/jdbc/JdbcConnection:debugCodeAssign	(Ljava/lang/String;IILjava/lang/String;)V
    //   19: aload_0
    //   20: invokespecial 383	org/h2/jdbc/JdbcConnection:checkClosedForWrite	()V
    //   23: aload_0
    //   24: getfield 87	org/h2/jdbc/JdbcConnection:session	Lorg/h2/engine/SessionInterface;
    //   27: invokeinterface 779 1 0
    //   32: invokeinterface 785 1 0
    //   37: new 789	java/io/ByteArrayInputStream
    //   40: dup
    //   41: getstatic 795	org/h2/util/Utils:EMPTY_BYTES	[B
    //   44: invokespecial 798	java/io/ByteArrayInputStream:<init>	([B)V
    //   47: lconst_0
    //   48: invokeinterface 824 4 0
    //   53: astore_2
    //   54: aload_0
    //   55: getfield 87	org/h2/jdbc/JdbcConnection:session	Lorg/h2/engine/SessionInterface;
    //   58: aload_2
    //   59: invokeinterface 810 2 0
    //   64: new 826	org/h2/jdbc/JdbcBlob
    //   67: dup
    //   68: aload_0
    //   69: aload_2
    //   70: iload_1
    //   71: invokespecial 827	org/h2/jdbc/JdbcBlob:<init>	(Lorg/h2/jdbc/JdbcConnection;Lorg/h2/value/Value;I)V
    //   74: astore_3
    //   75: aload_0
    //   76: invokevirtual 393	org/h2/jdbc/JdbcConnection:afterWriting	()V
    //   79: aload_3
    //   80: areturn
    //   81: astore 4
    //   83: aload_0
    //   84: invokevirtual 393	org/h2/jdbc/JdbcConnection:afterWriting	()V
    //   87: aload 4
    //   89: athrow
    //   90: astore_1
    //   91: aload_0
    //   92: aload_1
    //   93: invokevirtual 169	org/h2/jdbc/JdbcConnection:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   96: athrow
    // Line number table:
    //   Java source line #1575	-> byte code offset #0
    //   Java source line #1576	-> byte code offset #6
    //   Java source line #1577	-> byte code offset #19
    //   Java source line #1579	-> byte code offset #23
    //   Java source line #1581	-> byte code offset #54
    //   Java source line #1582	-> byte code offset #64
    //   Java source line #1584	-> byte code offset #75
    //   Java source line #1586	-> byte code offset #90
    //   Java source line #1587	-> byte code offset #91
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	JdbcConnection
    //   5	66	1	id	int
    //   90	3	1	e	Exception
    //   53	17	2	v	Value
    //   81	7	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   23	75	81	finally
    //   81	83	81	finally
    //   0	79	90	java/lang/Exception
    //   81	90	90	java/lang/Exception
  }
  
  /* Error */
  public java.sql.NClob createNClob()
    throws SQLException
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 101	org/h2/jdbc/JdbcConnection:getNextId	(I)I
    //   5: istore_1
    //   6: aload_0
    //   7: ldc_w 831
    //   10: bipush 10
    //   12: iload_1
    //   13: ldc_w 833
    //   16: invokevirtual 238	org/h2/jdbc/JdbcConnection:debugCodeAssign	(Ljava/lang/String;IILjava/lang/String;)V
    //   19: aload_0
    //   20: invokespecial 383	org/h2/jdbc/JdbcConnection:checkClosedForWrite	()V
    //   23: aload_0
    //   24: getfield 87	org/h2/jdbc/JdbcConnection:session	Lorg/h2/engine/SessionInterface;
    //   27: invokeinterface 779 1 0
    //   32: invokeinterface 785 1 0
    //   37: new 787	java/io/InputStreamReader
    //   40: dup
    //   41: new 789	java/io/ByteArrayInputStream
    //   44: dup
    //   45: getstatic 795	org/h2/util/Utils:EMPTY_BYTES	[B
    //   48: invokespecial 798	java/io/ByteArrayInputStream:<init>	([B)V
    //   51: invokespecial 801	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: lconst_0
    //   55: invokeinterface 806 4 0
    //   60: astore_2
    //   61: aload_0
    //   62: getfield 87	org/h2/jdbc/JdbcConnection:session	Lorg/h2/engine/SessionInterface;
    //   65: aload_2
    //   66: invokeinterface 810 2 0
    //   71: new 812	org/h2/jdbc/JdbcClob
    //   74: dup
    //   75: aload_0
    //   76: aload_2
    //   77: iload_1
    //   78: invokespecial 815	org/h2/jdbc/JdbcClob:<init>	(Lorg/h2/jdbc/JdbcConnection;Lorg/h2/value/Value;I)V
    //   81: astore_3
    //   82: aload_0
    //   83: invokevirtual 393	org/h2/jdbc/JdbcConnection:afterWriting	()V
    //   86: aload_3
    //   87: areturn
    //   88: astore 4
    //   90: aload_0
    //   91: invokevirtual 393	org/h2/jdbc/JdbcConnection:afterWriting	()V
    //   94: aload 4
    //   96: athrow
    //   97: astore_1
    //   98: aload_0
    //   99: aload_1
    //   100: invokevirtual 169	org/h2/jdbc/JdbcConnection:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   103: athrow
    // Line number table:
    //   Java source line #1599	-> byte code offset #0
    //   Java source line #1600	-> byte code offset #6
    //   Java source line #1601	-> byte code offset #19
    //   Java source line #1603	-> byte code offset #23
    //   Java source line #1606	-> byte code offset #61
    //   Java source line #1607	-> byte code offset #71
    //   Java source line #1609	-> byte code offset #82
    //   Java source line #1611	-> byte code offset #97
    //   Java source line #1612	-> byte code offset #98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	JdbcConnection
    //   5	73	1	id	int
    //   97	3	1	e	Exception
    //   60	17	2	v	Value
    //   88	7	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   23	82	88	finally
    //   88	90	88	finally
    //   0	86	97	java/lang/Exception
    //   88	97	97	java/lang/Exception
  }
  
  public SQLXML createSQLXML()
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public Array createArrayOf(String typeName, Object[] elements)
    throws SQLException
  {
    throw unsupported("createArray");
  }
  
  public Struct createStruct(String typeName, Object[] attributes)
    throws SQLException
  {
    throw unsupported("Struct");
  }
  
  public synchronized boolean isValid(int timeout)
  {
    try
    {
      debugCodeCall("isValid", timeout);
      if ((this.session == null) || (this.session.isClosed())) {
        return false;
      }
      getTransactionIsolation();
      return true;
    }
    catch (Exception e)
    {
      logAndConvert(e);
    }
    return false;
  }
  
  public void setClientInfo(String name, String value)
    throws SQLClientInfoException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setClientInfo(" + quote(name) + ", " + quote(value) + ");");
      }
      checkClosed();
      
      throw new SQLClientInfoException();
    }
    catch (Exception e)
    {
      throw convertToClientInfoException(logAndConvert(e));
    }
  }
  
  private static SQLClientInfoException convertToClientInfoException(SQLException x)
  {
    if ((x instanceof SQLClientInfoException)) {
      return (SQLClientInfoException)x;
    }
    return new SQLClientInfoException(x.getMessage(), x.getSQLState(), x.getErrorCode(), null, null);
  }
  
  public void setClientInfo(Properties properties)
    throws SQLClientInfoException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setClientInfo(properties);");
      }
      checkClosed();
      
      throw new SQLClientInfoException();
    }
    catch (Exception e)
    {
      throw convertToClientInfoException(logAndConvert(e));
    }
  }
  
  public Properties getClientInfo()
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getClientInfo();");
      }
      checkClosed();
      ArrayList<String> serverList = this.session.getClusterServers();
      Properties p = new Properties();
      
      p.setProperty("numServers", String.valueOf(serverList.size()));
      for (int i = 0; i < serverList.size(); i++) {
        p.setProperty("server" + String.valueOf(i), (String)serverList.get(i));
      }
      return p;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getClientInfo(String name)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCodeCall("getClientInfo", name);
      }
      checkClosed();
      Properties p = getClientInfo();
      String s = p.getProperty(name);
      if (s == null) {
        throw new SQLClientInfoException();
      }
      return s;
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
  
  public Value createClob(Reader x, long length)
  {
    if (x == null) {
      return ValueNull.INSTANCE;
    }
    if (length <= 0L) {
      length = -1L;
    }
    Value v = this.session.getDataHandler().getLobStorage().createClob(x, length);
    this.session.addTemporaryLob(v);
    return v;
  }
  
  public Value createBlob(InputStream x, long length)
  {
    if (x == null) {
      return ValueNull.INSTANCE;
    }
    if (length <= 0L) {
      length = -1L;
    }
    Value v = this.session.getDataHandler().getLobStorage().createBlob(x, length);
    this.session.addTemporaryLob(v);
    return v;
  }
  
  static void checkMap(Map<String, Class<?>> map)
  {
    if ((map != null) && (map.size() > 0)) {
      throw DbException.getUnsupportedException("map.size > 0");
    }
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": url=" + this.url + " user=" + this.user;
  }
  
  Object convertToDefaultObject(Value v)
  {
    Object o;
    switch (v.getType())
    {
    case 16: 
      int id = getNextId(10);
      o = new JdbcClob(this, v, id);
      break;
    case 15: 
      int id = getNextId(9);
      o = new JdbcBlob(this, v, id);
      break;
    case 19: 
      if (SysProperties.serializeJavaObject) {
        o = JdbcUtils.deserialize(v.getBytesNoCopy(), this.session.getDataHandler());
      }
      break;
    }
    Object o = v.getObject();
    
    return o;
  }
  
  CompareMode getCompareMode()
  {
    return this.compareMode;
  }
  
  public void setTraceLevel(int level)
  {
    this.trace.setLevel(level);
  }
  
  public void setSchema(String schema)
    throws SQLException
  {}
  
  public String getSchema()
    throws SQLException
  {
    return null;
  }
  
  public void abort(Executor executor)
    throws SQLException
  {}
  
  public void setNetworkTimeout(Executor executor, int milliseconds)
    throws SQLException
  {}
  
  public int getNetworkTimeout()
    throws SQLException
  {
    return 0;
  }
}
