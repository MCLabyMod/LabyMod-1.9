package org.h2.table;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import org.h2.command.ddl.Analyze;
import org.h2.command.ddl.CreateTableData;
import org.h2.constraint.Constraint;
import org.h2.constraint.ConstraintReferential;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.index.Cursor;
import org.h2.index.HashIndex;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.MultiVersionIndex;
import org.h2.index.NonUniqueHashIndex;
import org.h2.index.PageBtreeIndex;
import org.h2.index.PageDataIndex;
import org.h2.index.PageDelegateIndex;
import org.h2.index.ScanIndex;
import org.h2.index.SpatialTreeIndex;
import org.h2.index.TreeIndex;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.store.LobStorageInterface;
import org.h2.store.PageStore;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.value.CompareMode;
import org.h2.value.DataType;
import org.h2.value.Value;

public class RegularTable
  extends TableBase
{
  private Index scanIndex;
  private long rowCount;
  private volatile Session lockExclusiveSession;
  private HashSet<Session> lockSharedSessions = New.hashSet();
  private final ArrayDeque<Session> waitingSessions = new ArrayDeque();
  private final Trace traceLock;
  private final ArrayList<Index> indexes = New.arrayList();
  private long lastModificationId;
  private boolean containsLargeObject;
  private final PageDataIndex mainIndex;
  private int changesSinceAnalyze;
  private int nextAnalyze;
  private Column rowIdColumn;
  
  public RegularTable(CreateTableData data)
  {
    super(data);
    this.nextAnalyze = this.database.getSettings().analyzeAuto;
    this.isHidden = data.isHidden;
    for (Column col : getColumns()) {
      if (DataType.isLargeObject(col.getType())) {
        this.containsLargeObject = true;
      }
    }
    if ((data.persistData) && (this.database.isPersistent()))
    {
      this.mainIndex = new PageDataIndex(this, data.id, IndexColumn.wrap(getColumns()), IndexType.createScan(data.persistData), data.create, data.session);
      
      this.scanIndex = this.mainIndex;
    }
    else
    {
      this.mainIndex = null;
      this.scanIndex = new ScanIndex(this, data.id, IndexColumn.wrap(getColumns()), IndexType.createScan(data.persistData));
    }
    this.indexes.add(this.scanIndex);
    this.traceLock = this.database.getTrace("lock");
  }
  
  public void close(Session session)
  {
    for (Index index : this.indexes) {
      index.close(session);
    }
  }
  
  public Row getRow(Session session, long key)
  {
    return this.scanIndex.getRow(session, key);
  }
  
  public void addRow(Session session, Row row)
  {
    this.lastModificationId = this.database.getNextModificationDataId();
    if (this.database.isMultiVersion()) {
      row.setSessionId(session.getId());
    }
    int i = 0;
    try
    {
      for (int size = this.indexes.size(); i < size; i++)
      {
        Index index = (Index)this.indexes.get(i);
        index.add(session, row);
        checkRowCount(session, index, 1);
      }
      this.rowCount += 1L;
    }
    catch (Throwable e)
    {
      try
      {
        for (;;)
        {
          i--;
          if (i < 0) {
            break;
          }
          Index index = (Index)this.indexes.get(i);
          index.remove(session, row);
          checkRowCount(session, index, 0);
        }
      }
      catch (DbException e2)
      {
        this.trace.error(e2, "could not undo operation");
        throw e2;
      }
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
  
  public void commit(short operation, Row row)
  {
    this.lastModificationId = this.database.getNextModificationDataId();
    int i = 0;
    for (int size = this.indexes.size(); i < size; i++)
    {
      Index index = (Index)this.indexes.get(i);
      index.commit(operation, row);
    }
  }
  
  private void checkRowCount(Session session, Index index, int offset)
  {
    if ((SysProperties.CHECK) && (!this.database.isMultiVersion()) && 
      (!(index instanceof PageDelegateIndex)))
    {
      long rc = index.getRowCount(session);
      if (rc != this.rowCount + offset) {
        DbException.throwInternalError("rowCount expected " + (this.rowCount + offset) + " got " + rc + " " + getName() + "." + index.getName());
      }
    }
  }
  
  public Index getScanIndex(Session session)
  {
    return (Index)this.indexes.get(0);
  }
  
  public Index getUniqueIndex()
  {
    for (Index idx : this.indexes) {
      if (idx.getIndexType().isUnique()) {
        return idx;
      }
    }
    return null;
  }
  
  public ArrayList<Index> getIndexes()
  {
    return this.indexes;
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
    Index index;
    Index index;
    if ((isPersistIndexes()) && (indexType.isPersistent()))
    {
      int mainIndexColumn;
      int mainIndexColumn;
      if ((this.database.isStarting()) && (this.database.getPageStore().getRootPageId(indexId) != 0))
      {
        mainIndexColumn = -1;
      }
      else
      {
        int mainIndexColumn;
        if ((!this.database.isStarting()) && (this.mainIndex.getRowCount(session) != 0L)) {
          mainIndexColumn = -1;
        } else {
          mainIndexColumn = getMainIndexColumn(indexType, cols);
        }
      }
      Index index;
      if (mainIndexColumn != -1)
      {
        this.mainIndex.setMainIndexColumn(mainIndexColumn);
        index = new PageDelegateIndex(this, indexId, indexName, indexType, this.mainIndex, create, session);
      }
      else
      {
        Index index;
        if (indexType.isSpatial()) {
          index = new SpatialTreeIndex(this, indexId, indexName, cols, indexType, true, create, session);
        } else {
          index = new PageBtreeIndex(this, indexId, indexName, cols, indexType, create, session);
        }
      }
    }
    else
    {
      Index index;
      if (indexType.isHash())
      {
        if (cols.length != 1) {
          throw DbException.getUnsupportedException("hash indexes may index only one column");
        }
        Index index;
        if (indexType.isUnique()) {
          index = new HashIndex(this, indexId, indexName, cols, indexType);
        } else {
          index = new NonUniqueHashIndex(this, indexId, indexName, cols, indexType);
        }
      }
      else
      {
        Index index;
        if (indexType.isSpatial()) {
          index = new SpatialTreeIndex(this, indexId, indexName, cols, indexType, false, true, session);
        } else {
          index = new TreeIndex(this, indexId, indexName, cols, indexType);
        }
      }
    }
    if (this.database.isMultiVersion()) {
      index = new MultiVersionIndex(index, this);
    }
    if ((index.needRebuild()) && (this.rowCount > 0L)) {
      try
      {
        Index scan = getScanIndex(session);
        long remaining = scan.getRowCount(session);
        long total = remaining;
        Cursor cursor = scan.find(session, null, null);
        long i = 0L;
        int bufferSize = (int)Math.min(this.rowCount, this.database.getMaxMemoryRows());
        ArrayList<Row> buffer = New.arrayList(bufferSize);
        String n = getName() + ":" + index.getName();
        int t = MathUtils.convertLongToInt(total);
        while (cursor.next())
        {
          this.database.setProgress(1, n, MathUtils.convertLongToInt(i++), t);
          
          Row row = cursor.get();
          buffer.add(row);
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
  
  private int getMainIndexColumn(IndexType indexType, IndexColumn[] cols)
  {
    if (this.mainIndex.getMainIndexColumn() != -1) {
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
  
  public boolean canGetRowCount()
  {
    return true;
  }
  
  private static void addRowsToIndex(Session session, ArrayList<Row> list, Index index)
  {
    Index idx = index;
    Collections.sort(list, new Comparator()
    {
      public int compare(Row r1, Row r2)
      {
        return this.val$idx.compareRows(r1, r2);
      }
    });
    for (Row row : list) {
      index.add(session, row);
    }
    list.clear();
  }
  
  public boolean canDrop()
  {
    return true;
  }
  
  public long getRowCount(Session session)
  {
    if (this.database.isMultiVersion()) {
      return getScanIndex(session).getRowCount(session);
    }
    return this.rowCount;
  }
  
  public void removeRow(Session session, Row row)
  {
    if (this.database.isMultiVersion())
    {
      if (row.isDeleted()) {
        throw DbException.get(90131, getName());
      }
      int old = row.getSessionId();
      int newId = session.getId();
      if (old == 0) {
        row.setSessionId(newId);
      } else if (old != newId) {
        throw DbException.get(90131, getName());
      }
    }
    this.lastModificationId = this.database.getNextModificationDataId();
    int i = this.indexes.size() - 1;
    try
    {
      for (; i >= 0; i--)
      {
        Index index = (Index)this.indexes.get(i);
        index.remove(session, row);
        checkRowCount(session, index, -1);
      }
      this.rowCount -= 1L;
    }
    catch (Throwable e)
    {
      try
      {
        for (;;)
        {
          i++;
          if (i >= this.indexes.size()) {
            break;
          }
          Index index = (Index)this.indexes.get(i);
          index.add(session, row);
          checkRowCount(session, index, 0);
        }
      }
      catch (DbException e2)
      {
        this.trace.error(e2, "could not undo operation");
        throw e2;
      }
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
    this.rowCount = 0L;
    this.changesSinceAnalyze = 0;
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
  
  public boolean isLockedExclusivelyBy(Session session)
  {
    return this.lockExclusiveSession == session;
  }
  
  public boolean lock(Session session, boolean exclusive, boolean forceLockEvenInMvcc)
  {
    int lockMode = this.database.getLockMode();
    if (lockMode == 0) {
      return this.lockExclusiveSession != null;
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
    synchronized (this.database)
    {
      if (this.lockExclusiveSession == session) {
        return true;
      }
      if ((!exclusive) && (this.lockSharedSessions.contains(session))) {
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
        this.database.wait(sleep);
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
        if ((this.lockSharedSessions.size() == 1) && (this.lockSharedSessions.contains(session)))
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
      if (!this.lockSharedSessions.contains(session))
      {
        traceLock(session, exclusive, "ok");
        session.addLock(this);
        this.lockSharedSessions.add(session);
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
        if ((t instanceof RegularTable)) {
          if (((RegularTable)t).lockExclusiveSession == s) {
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
    synchronized (RegularTable.class)
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
      for (Session s : this.lockSharedSessions) {
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
      if ((error == null) && (this.lockExclusiveSession != null))
      {
        Table t = this.lockExclusiveSession.getWaitForLock();
        if (t != null)
        {
          error = t.checkDeadlock(this.lockExclusiveSession, clash, visited);
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
  
  public void unlock(Session s)
  {
    if (this.database != null)
    {
      traceLock(s, this.lockExclusiveSession == s, "unlock");
      if (this.lockExclusiveSession == s) {
        this.lockExclusiveSession = null;
      }
      if (this.lockSharedSessions.size() > 0) {
        this.lockSharedSessions.remove(s);
      }
      synchronized (this.database)
      {
        if (!this.waitingSessions.isEmpty()) {
          this.database.notifyAll();
        }
      }
    }
  }
  
  public static Row createRow(Value[] data)
  {
    return new Row(data, -1);
  }
  
  public void setRowCount(long count)
  {
    this.rowCount = count;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    if (this.containsLargeObject)
    {
      truncate(session);
      this.database.getLobStorage().removeAllForTable(getId());
      this.database.lockMeta(session);
    }
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
    this.scanIndex.remove(session);
    this.database.removeMeta(session, getId());
    this.scanIndex = null;
    this.lockExclusiveSession = null;
    this.lockSharedSessions = null;
    invalidate();
  }
  
  public String toString()
  {
    return getSQL();
  }
  
  public void checkRename() {}
  
  public void checkSupportAlter() {}
  
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
  
  public String getTableType()
  {
    return "TABLE";
  }
  
  public long getMaxDataModificationId()
  {
    return this.lastModificationId;
  }
  
  public boolean getContainsLargeObject()
  {
    return this.containsLargeObject;
  }
  
  public long getRowCountApproximation()
  {
    return this.scanIndex.getRowCountApproximation();
  }
  
  public long getDiskSpaceUsed()
  {
    return this.scanIndex.getDiskSpaceUsed();
  }
  
  public void setCompareMode(CompareMode compareMode)
  {
    this.compareMode = compareMode;
  }
  
  public boolean isDeterministic()
  {
    return true;
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
}
