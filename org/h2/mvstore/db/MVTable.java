package org.h2.mvstore.db;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.h2.command.ddl.Analyze;
import org.h2.command.ddl.CreateTableData;
import org.h2.constraint.Constraint;
import org.h2.constraint.ConstraintReferential;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.MultiVersionIndex;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.mvstore.MVStore;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.store.LobStorageInterface;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableBase;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.value.DataType;

public class MVTable
  extends TableBase
{
  private MVPrimaryIndex primaryIndex;
  private final ArrayList<Index> indexes = New.arrayList();
  private long lastModificationId;
  private volatile Session lockExclusiveSession;
  private final ConcurrentHashMap<Session, Session> lockSharedSessions = new ConcurrentHashMap();
  private final ArrayDeque<Session> waitingSessions = new ArrayDeque();
  private final Trace traceLock;
  private int changesSinceAnalyze;
  private int nextAnalyze;
  private boolean containsLargeObject;
  private Column rowIdColumn;
  private final TransactionStore store;
  
  public MVTable(CreateTableData data, MVTableEngine.Store store)
  {
    super(data);
    this.nextAnalyze = this.database.getSettings().analyzeAuto;
    this.store = store.getTransactionStore();
    this.isHidden = data.isHidden;
    for (Column col : getColumns()) {
      if (DataType.isLargeObject(col.getType())) {
        this.containsLargeObject = true;
      }
    }
    this.traceLock = this.database.getTrace("lock");
  }
  
  void init(Session session)
  {
    this.primaryIndex = new MVPrimaryIndex(session.getDatabase(), this, getId(), IndexColumn.wrap(getColumns()), IndexType.createScan(true));
    
    this.indexes.add(this.primaryIndex);
  }
  
  public String getMapName()
  {
    return this.primaryIndex.getMapName();
  }
  
  public boolean lock(Session session, boolean exclusive, boolean forceLockEvenInMvcc)
  {
    int lockMode = this.database.getLockMode();
    if (lockMode == 0) {
      return false;
    }
    if ((!forceLockEvenInMvcc) && (this.database.isMultiVersion())) {
      if (exclusive) {
        exclusive = false;
      } else if (this.lockExclusiveSession == null) {
        return false;
      }
    }
    if (this.lockExclusiveSession == session) {
      return true;
    }
    if ((!exclusive) && (this.lockSharedSessions.containsKey(session))) {
      return true;
    }
    synchronized (getLockSyncObject())
    {
      if ((!exclusive) && (this.lockSharedSessions.containsKey(session))) {
        return true;
      }
      session.setWaitForLock(this, Thread.currentThread());
      this.waitingSessions.addLast(session);
      try
      {
        doLock1(session, lockMode, exclusive);
      }
      finally
      {
        session.setWaitForLock(null, null);
        this.waitingSessions.remove(session);
      }
    }
    return false;
  }
  
  private Object getLockSyncObject()
  {
    if (this.database.isMultiThreaded()) {
      return this;
    }
    return this.database;
  }
  
  private void doLock1(Session session, int lockMode, boolean exclusive)
  {
    traceLock(session, exclusive, "requesting for");
    
    long max = 0L;
    boolean checkDeadlock = false;
    for (;;)
    {
      if ((this.waitingSessions.getFirst() == session) && 
        (doLock2(session, lockMode, exclusive))) {
        return;
      }
      if (checkDeadlock)
      {
        ArrayList<Session> sessions = checkDeadlock(session, null, null);
        if (sessions != null) {
          throw DbException.get(40001, getDeadlockDetails(sessions, exclusive));
        }
      }
      else
      {
        checkDeadlock = true;
      }
      long now = System.currentTimeMillis();
      if (max == 0L)
      {
        max = now + session.getLockTimeout();
      }
      else if (now >= max)
      {
        traceLock(session, exclusive, "timeout after " + session.getLockTimeout());
        
        throw DbException.get(50200, getName());
      }
      try
      {
        traceLock(session, exclusive, "waiting for");
        if (this.database.getLockMode() == 2) {
          for (int i = 0; i < 20; i++)
          {
            long free = Runtime.getRuntime().freeMemory();
            System.gc();
            long free2 = Runtime.getRuntime().freeMemory();
            if (free == free2) {
              break;
            }
          }
        }
        long sleep = Math.min(100L, max - now);
        if (sleep == 0L) {
          sleep = 1L;
        }
        getLockSyncObject().wait(sleep);
      }
      catch (InterruptedException e) {}
    }
  }
  
  private boolean doLock2(Session session, int lockMode, boolean exclusive)
  {
    if (exclusive)
    {
      if (this.lockExclusiveSession == null)
      {
        if (this.lockSharedSessions.isEmpty())
        {
          traceLock(session, exclusive, "added for");
          session.addLock(this);
          this.lockExclusiveSession = session;
          return true;
        }
        if ((this.lockSharedSessions.size() == 1) && (this.lockSharedSessions.containsKey(session)))
        {
          traceLock(session, exclusive, "add (upgraded) for ");
          this.lockExclusiveSession = session;
          return true;
        }
      }
    }
    else if (this.lockExclusiveSession == null)
    {
      if ((lockMode == 3) && 
        (!this.database.isMultiThreaded()) && (!this.database.isMultiVersion())) {
        return true;
      }
      if (!this.lockSharedSessions.containsKey(session))
      {
        traceLock(session, exclusive, "ok");
        session.addLock(this);
        this.lockSharedSessions.put(session, session);
      }
      return true;
    }
    return false;
  }
  
  private static String getDeadlockDetails(ArrayList<Session> sessions, boolean exclusive)
  {
    StringBuilder buff = new StringBuilder();
    for (Session s : sessions)
    {
      Table lock = s.getWaitForLock();
      Thread thread = s.getWaitForLockThread();
      buff.append("\nSession ").append(s.toString()).append(" on thread ").append(thread.getName()).append(" is waiting to lock ").append(lock.toString()).append(exclusive ? " (exclusive)" : " (shared)").append(" while locking ");
      
      int i = 0;
      for (Table t : s.getLocks())
      {
        if (i++ > 0) {
          buff.append(", ");
        }
        buff.append(t.toString());
        if ((t instanceof MVTable)) {
          if (((MVTable)t).lockExclusiveSession == s) {
            buff.append(" (exclusive)");
          } else {
            buff.append(" (shared)");
          }
        }
      }
      buff.append('.');
    }
    return buff.toString();
  }
  
  public ArrayList<Session> checkDeadlock(Session session, Session clash, Set<Session> visited)
  {
    synchronized (MVTable.class)
    {
      if (clash == null)
      {
        clash = session;
        visited = New.hashSet();
      }
      else
      {
        if (clash == session) {
          return New.arrayList();
        }
        if (visited.contains(session)) {
          return null;
        }
      }
      visited.add(session);
      ArrayList<Session> error = null;
      for (Session s : this.lockSharedSessions.keySet()) {
        if (s != session)
        {
          Table t = s.getWaitForLock();
          if (t != null)
          {
            error = t.checkDeadlock(s, clash, visited);
            if (error != null)
            {
              error.add(session);
              break;
            }
          }
        }
      }
      Session copyOfLockExclusiveSession = this.lockExclusiveSession;
      if ((error == null) && (copyOfLockExclusiveSession != null))
      {
        Table t = copyOfLockExclusiveSession.getWaitForLock();
        if (t != null)
        {
          error = t.checkDeadlock(copyOfLockExclusiveSession, clash, visited);
          if (error != null) {
            error.add(session);
          }
        }
      }
      return error;
    }
  }
  
  private void traceLock(Session session, boolean exclusive, String s)
  {
    if (this.traceLock.isDebugEnabled()) {
      this.traceLock.debug("{0} {1} {2} {3}", new Object[] { Integer.valueOf(session.getId()), exclusive ? "exclusive write lock" : "shared read lock", s, getName() });
    }
  }
  
  public boolean isLockedExclusively()
  {
    return this.lockExclusiveSession != null;
  }
  
  public boolean isLockedExclusivelyBy(Session session)
  {
    return this.lockExclusiveSession == session;
  }
  
  public void unlock(Session s)
  {
    if (this.database != null)
    {
      traceLock(s, this.lockExclusiveSession == s, "unlock");
      if (this.lockExclusiveSession == s) {
        this.lockExclusiveSession = null;
      }
      synchronized (getLockSyncObject())
      {
        if (this.lockSharedSessions.size() > 0) {
          this.lockSharedSessions.remove(s);
        }
        if (!this.waitingSessions.isEmpty()) {
          getLockSyncObject().notifyAll();
        }
      }
    }
  }
  
  public boolean canTruncate()
  {
    if ((getCheckForeignKeyConstraints()) && (this.database.getReferentialIntegrity()))
    {
      ArrayList<Constraint> constraints = getConstraints();
      if (constraints != null)
      {
        int i = 0;
        for (int size = constraints.size(); i < size; i++)
        {
          Constraint c = (Constraint)constraints.get(i);
          if (c.getConstraintType().equals("REFERENTIAL"))
          {
            ConstraintReferential ref = (ConstraintReferential)c;
            if (ref.getRefTable() == this) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }
  
  public void close(Session session) {}
  
  public Row getRow(Session session, long key)
  {
    return this.primaryIndex.getRow(session, key);
  }
  
  public Index addIndex(Session session, String indexName, int indexId, IndexColumn[] cols, IndexType indexType, boolean create, String indexComment)
  {
    if (indexType.isPrimaryKey()) {
      for (IndexColumn c : cols)
      {
        Column column = c.column;
        if (column.isNullable()) {
          throw DbException.get(90023, column.getName());
        }
        column.setPrimaryKey(true);
      }
    }
    boolean isSessionTemporary = (isTemporary()) && (!isGlobalTemporary());
    if (!isSessionTemporary) {
      this.database.lockMeta(session);
    }
    int mainIndexColumn = getMainIndexColumn(indexType, cols);
    if (this.database.isStarting())
    {
      if (this.store.store.hasMap("index." + indexId)) {
        mainIndexColumn = -1;
      }
    }
    else if (this.primaryIndex.getRowCountMax() != 0L) {
      mainIndexColumn = -1;
    }
    MVIndex index;
    MVIndex index;
    if (mainIndexColumn != -1)
    {
      this.primaryIndex.setMainIndexColumn(mainIndexColumn);
      index = new MVDelegateIndex(this, indexId, indexName, this.primaryIndex, indexType);
    }
    else
    {
      MVIndex index;
      if (indexType.isSpatial()) {
        index = new MVSpatialIndex(session.getDatabase(), this, indexId, indexName, cols, indexType);
      } else {
        index = new MVSecondaryIndex(session.getDatabase(), this, indexId, indexName, cols, indexType);
      }
    }
    if (index.needRebuild()) {
      rebuildIndex(session, index, indexName);
    }
    index.setTemporary(isTemporary());
    if (index.getCreateSQL() != null)
    {
      index.setComment(indexComment);
      if (isSessionTemporary) {
        session.addLocalTempTableIndex(index);
      } else {
        this.database.addSchemaObject(session, index);
      }
    }
    this.indexes.add(index);
    setModified();
    return index;
  }
  
  private void rebuildIndex(Session session, MVIndex index, String indexName)
  {
    try
    {
      if ((session.getDatabase().getMvStore() == null) || ((index instanceof MVSpatialIndex))) {
        rebuildIndexBuffered(session, index);
      } else {
        rebuildIndexBlockMerge(session, index);
      }
    }
    catch (DbException e)
    {
      getSchema().freeUniqueName(indexName);
      try
      {
        index.remove(session);
      }
      catch (DbException e2)
      {
        this.trace.error(e2, "could not remove index");
        throw e2;
      }
      throw e;
    }
  }
  
  private void rebuildIndexBlockMerge(Session session, MVIndex index)
  {
    if ((index instanceof MVSpatialIndex)) {
      rebuildIndexBuffered(session, index);
    }
    Index scan = getScanIndex(session);
    long remaining = scan.getRowCount(session);
    long total = remaining;
    Cursor cursor = scan.find(session, null, null);
    long i = 0L;
    MVTableEngine.Store store = session.getDatabase().getMvStore();
    
    int bufferSize = this.database.getMaxMemoryRows() / 2;
    ArrayList<Row> buffer = New.arrayList(bufferSize);
    String n = getName() + ":" + index.getName();
    int t = MathUtils.convertLongToInt(total);
    ArrayList<String> bufferNames = New.arrayList();
    while (cursor.next())
    {
      Row row = cursor.get();
      buffer.add(row);
      this.database.setProgress(1, n, MathUtils.convertLongToInt(i++), t);
      if (buffer.size() >= bufferSize)
      {
        sortRows(buffer, index);
        String mapName = store.nextTemporaryMapName();
        index.addRowsToBuffer(buffer, mapName);
        bufferNames.add(mapName);
        buffer.clear();
      }
      remaining -= 1L;
    }
    sortRows(buffer, index);
    if (bufferNames.size() > 0)
    {
      String mapName = store.nextTemporaryMapName();
      index.addRowsToBuffer(buffer, mapName);
      bufferNames.add(mapName);
      buffer.clear();
      index.addBufferedRows(bufferNames);
    }
    else
    {
      addRowsToIndex(session, buffer, index);
    }
    if ((SysProperties.CHECK) && (remaining != 0L)) {
      DbException.throwInternalError("rowcount remaining=" + remaining + " " + getName());
    }
  }
  
  private void rebuildIndexBuffered(Session session, Index index)
  {
    Index scan = getScanIndex(session);
    long remaining = scan.getRowCount(session);
    long total = remaining;
    Cursor cursor = scan.find(session, null, null);
    long i = 0L;
    int bufferSize = (int)Math.min(total, this.database.getMaxMemoryRows());
    ArrayList<Row> buffer = New.arrayList(bufferSize);
    String n = getName() + ":" + index.getName();
    int t = MathUtils.convertLongToInt(total);
    while (cursor.next())
    {
      Row row = cursor.get();
      buffer.add(row);
      this.database.setProgress(1, n, MathUtils.convertLongToInt(i++), t);
      if (buffer.size() >= bufferSize) {
        addRowsToIndex(session, buffer, index);
      }
      remaining -= 1L;
    }
    addRowsToIndex(session, buffer, index);
    if ((SysProperties.CHECK) && (remaining != 0L)) {
      DbException.throwInternalError("rowcount remaining=" + remaining + " " + getName());
    }
  }
  
  private int getMainIndexColumn(IndexType indexType, IndexColumn[] cols)
  {
    if (this.primaryIndex.getMainIndexColumn() != -1) {
      return -1;
    }
    if ((!indexType.isPrimaryKey()) || (cols.length != 1)) {
      return -1;
    }
    IndexColumn first = cols[0];
    if (first.sortType != 0) {
      return -1;
    }
    switch (first.column.getType())
    {
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      break;
    default: 
      return -1;
    }
    return first.column.getColumnId();
  }
  
  private static void addRowsToIndex(Session session, ArrayList<Row> list, Index index)
  {
    sortRows(list, index);
    for (Row row : list) {
      index.add(session, row);
    }
    list.clear();
  }
  
  private static void sortRows(ArrayList<Row> list, Index index)
  {
    Collections.sort(list, new Comparator()
    {
      public int compare(Row r1, Row r2)
      {
        return this.val$index.compareRows(r1, r2);
      }
    });
  }
  
  public void removeRow(Session session, Row row)
  {
    this.lastModificationId = this.database.getNextModificationDataId();
    TransactionStore.Transaction t = getTransaction(session);
    long savepoint = t.setSavepoint();
    try
    {
      for (int i = this.indexes.size() - 1; i >= 0; i--)
      {
        Index index = (Index)this.indexes.get(i);
        index.remove(session, row);
      }
    }
    catch (Throwable e)
    {
      t.rollbackToSavepoint(savepoint);
      throw DbException.convert(e);
    }
    analyzeIfRequired(session);
  }
  
  public void truncate(Session session)
  {
    this.lastModificationId = this.database.getNextModificationDataId();
    for (int i = this.indexes.size() - 1; i >= 0; i--)
    {
      Index index = (Index)this.indexes.get(i);
      index.truncate(session);
    }
    this.changesSinceAnalyze = 0;
  }
  
  public void addRow(Session session, Row row)
  {
    this.lastModificationId = this.database.getNextModificationDataId();
    TransactionStore.Transaction t = getTransaction(session);
    long savepoint = t.setSavepoint();
    try
    {
      int i = 0;
      for (int size = this.indexes.size(); i < size; i++)
      {
        Index index = (Index)this.indexes.get(i);
        index.add(session, row);
      }
    }
    catch (Throwable e)
    {
      t.rollbackToSavepoint(savepoint);
      DbException de = DbException.convert(e);
      if (de.getErrorCode() == 23505) {
        for (int j = 0; j < this.indexes.size(); j++)
        {
          Index index = (Index)this.indexes.get(j);
          if ((index.getIndexType().isUnique()) && ((index instanceof MultiVersionIndex)))
          {
            MultiVersionIndex mv = (MultiVersionIndex)index;
            if (mv.isUncommittedFromOtherSession(session, row)) {
              throw DbException.get(90131, index.getName());
            }
          }
        }
      }
      throw de;
    }
    analyzeIfRequired(session);
  }
  
  private void analyzeIfRequired(Session session)
  {
    if ((this.nextAnalyze == 0) || (this.nextAnalyze > this.changesSinceAnalyze++)) {
      return;
    }
    this.changesSinceAnalyze = 0;
    int n = 2 * this.nextAnalyze;
    if (n > 0) {
      this.nextAnalyze = n;
    }
    int rows = session.getDatabase().getSettings().analyzeSample / 10;
    Analyze.analyzeTable(session, this, rows, false);
  }
  
  public void checkSupportAlter() {}
  
  public String getTableType()
  {
    return "TABLE";
  }
  
  public Index getScanIndex(Session session)
  {
    return this.primaryIndex;
  }
  
  public Index getUniqueIndex()
  {
    return this.primaryIndex;
  }
  
  public ArrayList<Index> getIndexes()
  {
    return this.indexes;
  }
  
  public long getMaxDataModificationId()
  {
    return this.lastModificationId;
  }
  
  public boolean getContainsLargeObject()
  {
    return this.containsLargeObject;
  }
  
  public boolean isDeterministic()
  {
    return true;
  }
  
  public boolean canGetRowCount()
  {
    return true;
  }
  
  public boolean canDrop()
  {
    return true;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    if (this.containsLargeObject)
    {
      truncate(session);
      this.database.getLobStorage().removeAllForTable(getId());
      this.database.lockMeta(session);
    }
    this.database.getMvStore().removeTable(this);
    super.removeChildrenAndResources(session);
    while (this.indexes.size() > 1)
    {
      Index index = (Index)this.indexes.get(1);
      if (index.getName() != null) {
        this.database.removeSchemaObject(session, index);
      }
      this.indexes.remove(index);
    }
    if (SysProperties.CHECK) {
      for (SchemaObject obj : this.database.getAllSchemaObjects(1))
      {
        Index index = (Index)obj;
        if (index.getTable() == this) {
          DbException.throwInternalError("index not dropped: " + index.getName());
        }
      }
    }
    this.primaryIndex.remove(session);
    this.database.removeMeta(session, getId());
    close(session);
    invalidate();
  }
  
  public long getRowCount(Session session)
  {
    return this.primaryIndex.getRowCount(session);
  }
  
  public long getRowCountApproximation()
  {
    return this.primaryIndex.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return this.primaryIndex.getDiskSpaceUsed();
  }
  
  public void checkRename() {}
  
  TransactionStore.Transaction getTransaction(Session session)
  {
    if (session == null) {
      return this.store.begin();
    }
    return session.getTransaction();
  }
  
  public Column getRowIdColumn()
  {
    if (this.rowIdColumn == null)
    {
      this.rowIdColumn = new Column("_ROWID_", 5);
      this.rowIdColumn.setTable(this, -1);
    }
    return this.rowIdColumn;
  }
  
  public String toString()
  {
    return getSQL();
  }
  
  public boolean isMVStore()
  {
    return true;
  }
  
  public void commit()
  {
    if (this.database != null) {
      this.lastModificationId = this.database.getNextModificationDataId();
    }
  }
}
