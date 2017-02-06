package org.h2.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import org.h2.command.Command;
import org.h2.command.CommandInterface;
import org.h2.command.Parser;
import org.h2.command.Prepared;
import org.h2.command.dml.SetTypes;
import org.h2.constraint.Constraint;
import org.h2.index.Index;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceSystem;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.db.MVTable;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.mvstore.db.TransactionStore;
import org.h2.mvstore.db.TransactionStore.Change;
import org.h2.mvstore.db.TransactionStore.Transaction;
import org.h2.result.LocalResult;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.store.DataHandler;
import org.h2.store.InDoubtTransaction;
import org.h2.table.Table;
import org.h2.util.New;
import org.h2.util.SmallLRUCache;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;

public class Session
  extends SessionWithState
{
  public static final int LOG_WRITTEN = -1;
  private static final String SYSTEM_IDENTIFIER_PREFIX = "_";
  private static int nextSerialId;
  private final int serialId = nextSerialId++;
  private final Database database;
  private ConnectionInfo connectionInfo;
  private final User user;
  private final int id;
  private final ArrayList<Table> locks = New.arrayList();
  private final UndoLog undoLog;
  private boolean autoCommit = true;
  private Random random;
  private int lockTimeout;
  private Value lastIdentity = ValueLong.get(0L);
  private Value lastScopeIdentity = ValueLong.get(0L);
  private int firstUncommittedLog = -1;
  private int firstUncommittedPos = -1;
  private HashMap<String, Savepoint> savepoints;
  private HashMap<String, Table> localTempTables;
  private HashMap<String, Index> localTempTableIndexes;
  private HashMap<String, Constraint> localTempTableConstraints;
  private int throttle;
  private long lastThrottle;
  private Command currentCommand;
  private boolean allowLiterals;
  private String currentSchemaName;
  private String[] schemaSearchPath;
  private Trace trace;
  private HashMap<String, Value> unlinkLobMap;
  private int systemIdentifier;
  private HashMap<String, Procedure> procedures;
  private boolean undoLogEnabled = true;
  private boolean redoLogBinary = true;
  private boolean autoCommitAtTransactionEnd;
  private String currentTransactionName;
  private volatile long cancelAt;
  private boolean closed;
  private final long sessionStart = System.currentTimeMillis();
  private long transactionStart;
  private long currentCommandStart;
  private HashMap<String, Value> variables;
  private HashSet<LocalResult> temporaryResults;
  private int queryTimeout;
  private boolean commitOrRollbackDisabled;
  private Table waitForLock;
  private Thread waitForLockThread;
  private int modificationId;
  private int objectId;
  private final int queryCacheSize;
  private SmallLRUCache<String, Command> queryCache;
  private long modificationMetaID = -1L;
  private LinkedList<TimeoutValue> temporaryResultLobs;
  private ArrayList<Value> temporaryLobs;
  private TransactionStore.Transaction transaction;
  private long startStatement = -1L;
  
  public Session(Database database, User user, int id)
  {
    this.database = database;
    this.queryTimeout = database.getSettings().maxQueryTimeout;
    this.queryCacheSize = database.getSettings().queryCacheSize;
    this.undoLog = new UndoLog(this);
    this.user = user;
    this.id = id;
    Setting setting = database.findSetting(SetTypes.getTypeName(6));
    
    this.lockTimeout = (setting == null ? 2000 : setting.getIntValue());
    
    this.currentSchemaName = "PUBLIC";
  }
  
  public ArrayList<String> getClusterServers()
  {
    return new ArrayList();
  }
  
  public boolean setCommitOrRollbackDisabled(boolean x)
  {
    boolean old = this.commitOrRollbackDisabled;
    this.commitOrRollbackDisabled = x;
    return old;
  }
  
  private void initVariables()
  {
    if (this.variables == null) {
      this.variables = this.database.newStringMap();
    }
  }
  
  public void setVariable(String name, Value value)
  {
    initVariables();
    this.modificationId += 1;
    Value old;
    Value old;
    if (value == ValueNull.INSTANCE)
    {
      old = (Value)this.variables.remove(name);
    }
    else
    {
      value = value.link(this.database, -1);
      
      old = (Value)this.variables.put(name, value);
    }
    if (old != null)
    {
      old.unlink(this.database);
      old.close();
    }
  }
  
  public Value getVariable(String name)
  {
    initVariables();
    Value v = (Value)this.variables.get(name);
    return v == null ? ValueNull.INSTANCE : v;
  }
  
  public String[] getVariableNames()
  {
    if (this.variables == null) {
      return new String[0];
    }
    String[] list = new String[this.variables.size()];
    this.variables.keySet().toArray(list);
    return list;
  }
  
  public Table findLocalTempTable(String name)
  {
    if (this.localTempTables == null) {
      return null;
    }
    return (Table)this.localTempTables.get(name);
  }
  
  public ArrayList<Table> getLocalTempTables()
  {
    if (this.localTempTables == null) {
      return New.arrayList();
    }
    return New.arrayList(this.localTempTables.values());
  }
  
  public void addLocalTempTable(Table table)
  {
    if (this.localTempTables == null) {
      this.localTempTables = this.database.newStringMap();
    }
    if (this.localTempTables.get(table.getName()) != null) {
      throw DbException.get(42101, table.getSQL());
    }
    this.modificationId += 1;
    this.localTempTables.put(table.getName(), table);
  }
  
  public void removeLocalTempTable(Table table)
  {
    this.modificationId += 1;
    this.localTempTables.remove(table.getName());
    synchronized (this.database)
    {
      table.removeChildrenAndResources(this);
    }
  }
  
  public Index findLocalTempTableIndex(String name)
  {
    if (this.localTempTableIndexes == null) {
      return null;
    }
    return (Index)this.localTempTableIndexes.get(name);
  }
  
  public HashMap<String, Index> getLocalTempTableIndexes()
  {
    if (this.localTempTableIndexes == null) {
      return New.hashMap();
    }
    return this.localTempTableIndexes;
  }
  
  public void addLocalTempTableIndex(Index index)
  {
    if (this.localTempTableIndexes == null) {
      this.localTempTableIndexes = this.database.newStringMap();
    }
    if (this.localTempTableIndexes.get(index.getName()) != null) {
      throw DbException.get(42111, index.getSQL());
    }
    this.localTempTableIndexes.put(index.getName(), index);
  }
  
  public void removeLocalTempTableIndex(Index index)
  {
    if (this.localTempTableIndexes != null)
    {
      this.localTempTableIndexes.remove(index.getName());
      synchronized (this.database)
      {
        index.removeChildrenAndResources(this);
      }
    }
  }
  
  public Constraint findLocalTempTableConstraint(String name)
  {
    if (this.localTempTableConstraints == null) {
      return null;
    }
    return (Constraint)this.localTempTableConstraints.get(name);
  }
  
  public HashMap<String, Constraint> getLocalTempTableConstraints()
  {
    if (this.localTempTableConstraints == null) {
      return New.hashMap();
    }
    return this.localTempTableConstraints;
  }
  
  public void addLocalTempTableConstraint(Constraint constraint)
  {
    if (this.localTempTableConstraints == null) {
      this.localTempTableConstraints = this.database.newStringMap();
    }
    String name = constraint.getName();
    if (this.localTempTableConstraints.get(name) != null) {
      throw DbException.get(90045, constraint.getSQL());
    }
    this.localTempTableConstraints.put(name, constraint);
  }
  
  void removeLocalTempTableConstraint(Constraint constraint)
  {
    if (this.localTempTableConstraints != null)
    {
      this.localTempTableConstraints.remove(constraint.getName());
      synchronized (this.database)
      {
        constraint.removeChildrenAndResources(this);
      }
    }
  }
  
  public boolean getAutoCommit()
  {
    return this.autoCommit;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setAutoCommit(boolean b)
  {
    this.autoCommit = b;
  }
  
  public int getLockTimeout()
  {
    return this.lockTimeout;
  }
  
  public void setLockTimeout(int lockTimeout)
  {
    this.lockTimeout = lockTimeout;
  }
  
  public synchronized CommandInterface prepareCommand(String sql, int fetchSize)
  {
    return prepareLocal(sql);
  }
  
  public Prepared prepare(String sql)
  {
    return prepare(sql, false);
  }
  
  public Prepared prepare(String sql, boolean rightsChecked)
  {
    Parser parser = new Parser(this);
    parser.setRightsChecked(rightsChecked);
    return parser.prepare(sql);
  }
  
  public Command prepareLocal(String sql)
  {
    if (this.closed) {
      throw DbException.get(90067, "session closed");
    }
    if (this.queryCacheSize > 0) {
      if (this.queryCache == null)
      {
        this.queryCache = SmallLRUCache.newInstance(this.queryCacheSize);
        this.modificationMetaID = this.database.getModificationMetaId();
      }
      else
      {
        long newModificationMetaID = this.database.getModificationMetaId();
        if (newModificationMetaID != this.modificationMetaID)
        {
          this.queryCache.clear();
          this.modificationMetaID = newModificationMetaID;
        }
        Command command = (Command)this.queryCache.get(sql);
        if ((command != null) && (command.canReuse()))
        {
          command.reuse();
          return command;
        }
      }
    }
    Parser parser = new Parser(this);
    Command command = parser.prepareCommand(sql);
    if ((this.queryCache != null) && 
      (command.isCacheable())) {
      this.queryCache.put(sql, command);
    }
    return command;
  }
  
  public Database getDatabase()
  {
    return this.database;
  }
  
  public int getPowerOffCount()
  {
    return this.database.getPowerOffCount();
  }
  
  public void setPowerOffCount(int count)
  {
    this.database.setPowerOffCount(count);
  }
  
  public void commit(boolean ddl)
  {
    checkCommitRollback();
    this.currentTransactionName = null;
    this.transactionStart = 0L;
    if (this.transaction != null)
    {
      if (this.locks.size() > 0)
      {
        int i = 0;
        for (int size = this.locks.size(); i < size; i++)
        {
          Table t = (Table)this.locks.get(i);
          if ((t instanceof MVTable)) {
            ((MVTable)t).commit();
          }
        }
      }
      this.transaction.commit();
      this.transaction = null;
    }
    if (containsUncommitted()) {
      this.database.commit(this);
    }
    removeTemporaryLobs(true);
    if (this.undoLog.size() > 0)
    {
      if (this.database.isMultiVersion())
      {
        ArrayList<Row> rows = New.arrayList();
        synchronized (this.database)
        {
          while (this.undoLog.size() > 0)
          {
            UndoLogRecord entry = this.undoLog.getLast();
            entry.commit();
            rows.add(entry.getRow());
            this.undoLog.removeLast(false);
          }
          int i = 0;
          for (int size = rows.size(); i < size; i++)
          {
            Row r = (Row)rows.get(i);
            r.commit();
          }
        }
      }
      this.undoLog.clear();
    }
    if (!ddl)
    {
      cleanTempTables(false);
      if (this.autoCommitAtTransactionEnd)
      {
        this.autoCommit = true;
        this.autoCommitAtTransactionEnd = false;
      }
    }
    endTransaction();
  }
  
  private void removeTemporaryLobs(boolean onTimeout)
  {
    if (this.temporaryLobs != null)
    {
      for (Value v : this.temporaryLobs) {
        if (!v.isLinked()) {
          v.close();
        }
      }
      this.temporaryLobs.clear();
    }
    if ((this.temporaryResultLobs != null) && (this.temporaryResultLobs.size() > 0))
    {
      long keepYoungerThan = System.currentTimeMillis() - this.database.getSettings().lobTimeout;
      while (this.temporaryResultLobs.size() > 0)
      {
        TimeoutValue tv = (TimeoutValue)this.temporaryResultLobs.getFirst();
        if ((onTimeout) && (tv.created >= keepYoungerThan)) {
          break;
        }
        Value v = ((TimeoutValue)this.temporaryResultLobs.removeFirst()).value;
        if (!v.isLinked()) {
          v.close();
        }
      }
    }
  }
  
  private void checkCommitRollback()
  {
    if ((this.commitOrRollbackDisabled) && (this.locks.size() > 0)) {
      throw DbException.get(90058);
    }
  }
  
  private void endTransaction()
  {
    if ((this.unlinkLobMap != null) && (this.unlinkLobMap.size() > 0))
    {
      if (this.database.getMvStore() == null) {
        this.database.flush();
      }
      for (Value v : this.unlinkLobMap.values())
      {
        v.unlink(this.database);
        v.close();
      }
      this.unlinkLobMap = null;
    }
    unlockAll();
  }
  
  public void rollback()
  {
    checkCommitRollback();
    this.currentTransactionName = null;
    boolean needCommit = false;
    if (this.undoLog.size() > 0)
    {
      rollbackTo(null, false);
      needCommit = true;
    }
    if (this.transaction != null)
    {
      rollbackTo(null, false);
      needCommit = true;
      
      this.transaction.commit();
      this.transaction = null;
    }
    if ((this.locks.size() > 0) || (needCommit)) {
      this.database.commit(this);
    }
    cleanTempTables(false);
    if (this.autoCommitAtTransactionEnd)
    {
      this.autoCommit = true;
      this.autoCommitAtTransactionEnd = false;
    }
    endTransaction();
  }
  
  public void rollbackTo(Savepoint savepoint, boolean trimToSize)
  {
    int index = savepoint == null ? 0 : savepoint.logIndex;
    while (this.undoLog.size() > index)
    {
      UndoLogRecord entry = this.undoLog.getLast();
      entry.undo(this);
      this.undoLog.removeLast(trimToSize);
    }
    if (this.transaction != null)
    {
      long savepointId = savepoint == null ? 0L : savepoint.transactionSavepoint;
      HashMap<String, MVTable> tableMap = this.database.getMvStore().getTables();
      
      Iterator<TransactionStore.Change> it = this.transaction.getChanges(savepointId);
      while (it.hasNext())
      {
        TransactionStore.Change c = (TransactionStore.Change)it.next();
        MVTable t = (MVTable)tableMap.get(c.mapName);
        if (t != null)
        {
          long key = ((ValueLong)c.key).getLong();
          ValueArray value = (ValueArray)c.value;
          Row row;
          short op;
          Row row;
          if (value == null)
          {
            short op = 0;
            row = t.getRow(this, key);
          }
          else
          {
            op = 1;
            row = new Row(value.getList(), -1);
          }
          row.setKey(key);
          UndoLogRecord log = new UndoLogRecord(t, op, row);
          log.undo(this);
        }
      }
    }
    if (this.savepoints != null)
    {
      String[] names = new String[this.savepoints.size()];
      this.savepoints.keySet().toArray(names);
      for (String name : names)
      {
        Savepoint sp = (Savepoint)this.savepoints.get(name);
        int savepointIndex = sp.logIndex;
        if (savepointIndex > index) {
          this.savepoints.remove(name);
        }
      }
    }
  }
  
  public boolean hasPendingTransaction()
  {
    return this.undoLog.size() > 0;
  }
  
  public Savepoint setSavepoint()
  {
    Savepoint sp = new Savepoint();
    sp.logIndex = this.undoLog.size();
    if (this.database.getMvStore() != null) {
      sp.transactionSavepoint = getStatementSavepoint();
    }
    return sp;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public void cancel()
  {
    this.cancelAt = System.currentTimeMillis();
  }
  
  public void close()
  {
    if (!this.closed) {
      try
      {
        this.database.checkPowerOff();
        removeTemporaryLobs(false);
        cleanTempTables(true);
        this.undoLog.clear();
        this.database.removeSession(this);
      }
      finally
      {
        this.closed = true;
      }
    }
  }
  
  public void addLock(Table table)
  {
    if ((SysProperties.CHECK) && 
      (this.locks.contains(table))) {
      DbException.throwInternalError();
    }
    this.locks.add(table);
  }
  
  public void log(Table table, short operation, Row row)
  {
    if (table.isMVStore()) {
      return;
    }
    if (this.undoLogEnabled)
    {
      UndoLogRecord log = new UndoLogRecord(table, operation, row);
      if (SysProperties.CHECK)
      {
        int lockMode = this.database.getLockMode();
        if ((lockMode != 0) && (!this.database.isMultiVersion()))
        {
          String tableType = log.getTable().getTableType();
          if ((this.locks.indexOf(log.getTable()) < 0) && (!"TABLE LINK".equals(tableType)) && (!"EXTERNAL".equals(tableType))) {
            DbException.throwInternalError();
          }
        }
      }
      this.undoLog.add(log);
    }
    else if (this.database.isMultiVersion())
    {
      ArrayList<Index> indexes = table.getIndexes();
      int i = 0;
      for (int size = indexes.size(); i < size; i++)
      {
        Index index = (Index)indexes.get(i);
        index.commit(operation, row);
      }
      row.commit();
    }
  }
  
  public void unlockReadLocks()
  {
    if (this.database.isMultiVersion()) {
      return;
    }
    for (int i = 0; i < this.locks.size(); i++)
    {
      Table t = (Table)this.locks.get(i);
      if (!t.isLockedExclusively())
      {
        synchronized (this.database)
        {
          t.unlock(this);
          this.locks.remove(i);
        }
        i--;
      }
    }
  }
  
  void unlock(Table t)
  {
    this.locks.remove(t);
  }
  
  private void unlockAll()
  {
    if ((SysProperties.CHECK) && 
      (this.undoLog.size() > 0)) {
      DbException.throwInternalError();
    }
    if (this.locks.size() > 0)
    {
      int i = 0;
      for (int size = this.locks.size(); i < size; i++)
      {
        Table t = (Table)this.locks.get(i);
        t.unlock(this);
      }
      this.locks.clear();
    }
    this.savepoints = null;
    this.sessionStateChanged = true;
  }
  
  private void cleanTempTables(boolean closeSession)
  {
    if ((this.localTempTables != null) && (this.localTempTables.size() > 0)) {
      synchronized (this.database)
      {
        for (Table table : New.arrayList(this.localTempTables.values())) {
          if ((closeSession) || (table.getOnCommitDrop()))
          {
            this.modificationId += 1;
            table.setModified();
            this.localTempTables.remove(table.getName());
            table.removeChildrenAndResources(this);
            if (closeSession) {
              this.database.commit(this);
            }
          }
          else if (table.getOnCommitTruncate())
          {
            table.truncate(this);
          }
        }
      }
    }
  }
  
  public Random getRandom()
  {
    if (this.random == null) {
      this.random = new Random();
    }
    return this.random;
  }
  
  public Trace getTrace()
  {
    if ((this.trace != null) && (!this.closed)) {
      return this.trace;
    }
    String traceModuleName = "jdbc[" + this.id + "]";
    if (this.closed) {
      return new TraceSystem(null).getTrace(traceModuleName);
    }
    this.trace = this.database.getTrace(traceModuleName);
    return this.trace;
  }
  
  public void setLastIdentity(Value last)
  {
    this.lastIdentity = last;
    this.lastScopeIdentity = last;
  }
  
  public Value getLastIdentity()
  {
    return this.lastIdentity;
  }
  
  public void setLastScopeIdentity(Value last)
  {
    this.lastScopeIdentity = last;
  }
  
  public Value getLastScopeIdentity()
  {
    return this.lastScopeIdentity;
  }
  
  public void addLogPos(int logId, int pos)
  {
    if (this.firstUncommittedLog == -1)
    {
      this.firstUncommittedLog = logId;
      this.firstUncommittedPos = pos;
    }
  }
  
  public int getFirstUncommittedLog()
  {
    return this.firstUncommittedLog;
  }
  
  void setAllCommitted()
  {
    this.firstUncommittedLog = -1;
    this.firstUncommittedPos = -1;
  }
  
  public boolean containsUncommitted()
  {
    if (this.database.getMvStore() != null) {
      return this.transaction != null;
    }
    return this.firstUncommittedLog != -1;
  }
  
  public void addSavepoint(String name)
  {
    if (this.savepoints == null) {
      this.savepoints = this.database.newStringMap();
    }
    Savepoint sp = new Savepoint();
    sp.logIndex = this.undoLog.size();
    if (this.database.getMvStore() != null) {
      sp.transactionSavepoint = getStatementSavepoint();
    }
    this.savepoints.put(name, sp);
  }
  
  public void rollbackToSavepoint(String name)
  {
    checkCommitRollback();
    if (this.savepoints == null) {
      throw DbException.get(90063, name);
    }
    Savepoint savepoint = (Savepoint)this.savepoints.get(name);
    if (savepoint == null) {
      throw DbException.get(90063, name);
    }
    rollbackTo(savepoint, false);
  }
  
  public void prepareCommit(String transactionName)
  {
    if (this.transaction != null) {
      this.database.prepareCommit(this, transactionName);
    }
    if (containsUncommitted()) {
      this.database.prepareCommit(this, transactionName);
    }
    this.currentTransactionName = transactionName;
  }
  
  public void setPreparedTransaction(String transactionName, boolean commit)
  {
    if ((this.currentTransactionName != null) && (this.currentTransactionName.equals(transactionName)))
    {
      if (commit) {
        commit(false);
      } else {
        rollback();
      }
    }
    else
    {
      ArrayList<InDoubtTransaction> list = this.database.getInDoubtTransactions();
      
      int state = commit ? 1 : 2;
      
      boolean found = false;
      if (list != null) {
        for (InDoubtTransaction p : list) {
          if (p.getTransactionName().equals(transactionName))
          {
            p.setState(state);
            found = true;
            break;
          }
        }
      }
      if (!found) {
        throw DbException.get(90129, transactionName);
      }
    }
  }
  
  public boolean isClosed()
  {
    return this.closed;
  }
  
  public void setThrottle(int throttle)
  {
    this.throttle = throttle;
  }
  
  public void throttle()
  {
    if (this.currentCommandStart == 0L) {
      this.currentCommandStart = System.currentTimeMillis();
    }
    if (this.throttle == 0) {
      return;
    }
    long time = System.currentTimeMillis();
    if (this.lastThrottle + 50L > time) {
      return;
    }
    this.lastThrottle = (time + this.throttle);
    try
    {
      Thread.sleep(this.throttle);
    }
    catch (Exception e) {}
  }
  
  public void setCurrentCommand(Command command)
  {
    this.currentCommand = command;
    if ((this.queryTimeout > 0) && (command != null))
    {
      long now = System.currentTimeMillis();
      this.currentCommandStart = now;
      this.cancelAt = (now + this.queryTimeout);
    }
  }
  
  public void checkCanceled()
  {
    throttle();
    if (this.cancelAt == 0L) {
      return;
    }
    long time = System.currentTimeMillis();
    if (time >= this.cancelAt)
    {
      this.cancelAt = 0L;
      throw DbException.get(57014);
    }
  }
  
  public long getCancel()
  {
    return this.cancelAt;
  }
  
  public Command getCurrentCommand()
  {
    return this.currentCommand;
  }
  
  public long getCurrentCommandStart()
  {
    return this.currentCommandStart;
  }
  
  public boolean getAllowLiterals()
  {
    return this.allowLiterals;
  }
  
  public void setAllowLiterals(boolean b)
  {
    this.allowLiterals = b;
  }
  
  public void setCurrentSchema(Schema schema)
  {
    this.modificationId += 1;
    this.currentSchemaName = schema.getName();
  }
  
  public String getCurrentSchemaName()
  {
    return this.currentSchemaName;
  }
  
  public JdbcConnection createConnection(boolean columnList)
  {
    String url;
    String url;
    if (columnList) {
      url = "jdbc:columnlist:connection";
    } else {
      url = "jdbc:default:connection";
    }
    return new JdbcConnection(this, getUser().getName(), url);
  }
  
  public DataHandler getDataHandler()
  {
    return this.database;
  }
  
  public void unlinkAtCommit(Value v)
  {
    if ((SysProperties.CHECK) && (!v.isLinked())) {
      DbException.throwInternalError();
    }
    if (this.unlinkLobMap == null) {
      this.unlinkLobMap = New.hashMap();
    }
    this.unlinkLobMap.put(v.toString(), v);
  }
  
  public void unlinkAtCommitStop(Value v)
  {
    if (this.unlinkLobMap != null) {
      this.unlinkLobMap.remove(v.toString());
    }
  }
  
  public String getNextSystemIdentifier(String sql)
  {
    String identifier;
    do
    {
      identifier = "_" + this.systemIdentifier++;
    } while (sql.contains(identifier));
    return identifier;
  }
  
  public void addProcedure(Procedure procedure)
  {
    if (this.procedures == null) {
      this.procedures = this.database.newStringMap();
    }
    this.procedures.put(procedure.getName(), procedure);
  }
  
  public void removeProcedure(String name)
  {
    if (this.procedures != null) {
      this.procedures.remove(name);
    }
  }
  
  public Procedure getProcedure(String name)
  {
    if (this.procedures == null) {
      return null;
    }
    return (Procedure)this.procedures.get(name);
  }
  
  public void setSchemaSearchPath(String[] schemas)
  {
    this.modificationId += 1;
    this.schemaSearchPath = schemas;
  }
  
  public String[] getSchemaSearchPath()
  {
    return this.schemaSearchPath;
  }
  
  public int hashCode()
  {
    return this.serialId;
  }
  
  public String toString()
  {
    return "#" + this.serialId + " (user: " + this.user.getName() + ")";
  }
  
  public void setUndoLogEnabled(boolean b)
  {
    this.undoLogEnabled = b;
  }
  
  public void setRedoLogBinary(boolean b)
  {
    this.redoLogBinary = b;
  }
  
  public boolean isUndoLogEnabled()
  {
    return this.undoLogEnabled;
  }
  
  public void begin()
  {
    this.autoCommitAtTransactionEnd = true;
    this.autoCommit = false;
  }
  
  public long getSessionStart()
  {
    return this.sessionStart;
  }
  
  public long getTransactionStart()
  {
    if (this.transactionStart == 0L) {
      this.transactionStart = System.currentTimeMillis();
    }
    return this.transactionStart;
  }
  
  public Table[] getLocks()
  {
    ArrayList<Table> copy = New.arrayList();
    for (int i = 0; i < this.locks.size(); i++) {
      try
      {
        copy.add(this.locks.get(i));
      }
      catch (Exception e)
      {
        break;
      }
    }
    Table[] list = new Table[copy.size()];
    copy.toArray(list);
    return list;
  }
  
  public void waitIfExclusiveModeEnabled()
  {
    if (this.database.getLobSession() == this) {
      return;
    }
    for (;;)
    {
      Session exclusive = this.database.getExclusiveSession();
      if ((exclusive == null) || (exclusive == this)) {
        break;
      }
      if (Thread.holdsLock(exclusive)) {
        break;
      }
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException e) {}
    }
  }
  
  public void addTemporaryResult(LocalResult result)
  {
    if (!result.needToClose()) {
      return;
    }
    if (this.temporaryResults == null) {
      this.temporaryResults = New.hashSet();
    }
    if (this.temporaryResults.size() < 100) {
      this.temporaryResults.add(result);
    }
  }
  
  private void closeTemporaryResults()
  {
    if (this.temporaryResults != null)
    {
      for (LocalResult result : this.temporaryResults) {
        result.close();
      }
      this.temporaryResults = null;
    }
  }
  
  public void setQueryTimeout(int queryTimeout)
  {
    int max = this.database.getSettings().maxQueryTimeout;
    if ((max != 0) && ((max < queryTimeout) || (queryTimeout == 0))) {
      queryTimeout = max;
    }
    this.queryTimeout = queryTimeout;
    
    this.cancelAt = 0L;
  }
  
  public int getQueryTimeout()
  {
    return this.queryTimeout;
  }
  
  public void setWaitForLock(Table waitForLock, Thread waitForLockThread)
  {
    this.waitForLock = waitForLock;
    this.waitForLockThread = waitForLockThread;
  }
  
  public Table getWaitForLock()
  {
    return this.waitForLock;
  }
  
  public Thread getWaitForLockThread()
  {
    return this.waitForLockThread;
  }
  
  public int getModificationId()
  {
    return this.modificationId;
  }
  
  public boolean isReconnectNeeded(boolean write)
  {
    for (;;)
    {
      boolean reconnect = this.database.isReconnectNeeded();
      if (reconnect) {
        return true;
      }
      if (write)
      {
        if (this.database.beforeWriting()) {
          return false;
        }
      }
      else {
        return false;
      }
    }
  }
  
  public void afterWriting()
  {
    this.database.afterWriting();
  }
  
  public SessionInterface reconnect(boolean write)
  {
    readSessionState();
    close();
    Session newSession = Engine.getInstance().createSession(this.connectionInfo);
    newSession.sessionState = this.sessionState;
    newSession.recreateSessionState();
    while ((write) && 
      (!newSession.database.beforeWriting())) {}
    return newSession;
  }
  
  public void setConnectionInfo(ConnectionInfo ci)
  {
    this.connectionInfo = ci;
  }
  
  public Value getTransactionId()
  {
    if (this.database.getMvStore() != null)
    {
      if (this.transaction == null) {
        return ValueNull.INSTANCE;
      }
      return ValueString.get(Long.toString(getTransaction().getId()));
    }
    if (!this.database.isPersistent()) {
      return ValueNull.INSTANCE;
    }
    if (this.undoLog.size() == 0) {
      return ValueNull.INSTANCE;
    }
    return ValueString.get(this.firstUncommittedLog + "-" + this.firstUncommittedPos + "-" + this.id);
  }
  
  public int nextObjectId()
  {
    return this.objectId++;
  }
  
  public boolean isRedoLogBinaryEnabled()
  {
    return this.redoLogBinary;
  }
  
  public TransactionStore.Transaction getTransaction()
  {
    if (this.transaction == null)
    {
      if (this.database.getMvStore().getStore().isClosed())
      {
        this.database.shutdownImmediately();
        throw DbException.get(90098);
      }
      this.transaction = this.database.getMvStore().getTransactionStore().begin();
      this.startStatement = -1L;
    }
    return this.transaction;
  }
  
  public long getStatementSavepoint()
  {
    if (this.startStatement == -1L) {
      this.startStatement = getTransaction().setSavepoint();
    }
    return this.startStatement;
  }
  
  public void startStatementWithinTransaction()
  {
    this.startStatement = -1L;
  }
  
  public void endStatement()
  {
    this.startStatement = -1L;
    closeTemporaryResults();
  }
  
  public void addTemporaryLob(Value v)
  {
    if (v.getTableId() == -3)
    {
      if (this.temporaryResultLobs == null) {
        this.temporaryResultLobs = new LinkedList();
      }
      this.temporaryResultLobs.add(new TimeoutValue(v));
    }
    else
    {
      if (this.temporaryLobs == null) {
        this.temporaryLobs = new ArrayList();
      }
      this.temporaryLobs.add(v);
    }
  }
  
  public static class TimeoutValue
  {
    final long created = System.currentTimeMillis();
    final Value value;
    
    TimeoutValue(Value v)
    {
      this.value = v;
    }
  }
  
  public static class Savepoint
  {
    int logIndex;
    long transactionSavepoint;
  }
}
