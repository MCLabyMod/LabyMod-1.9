package org.h2.index;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.store.LobStorageInterface;
import org.h2.store.Page;
import org.h2.store.PageStore;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class PageDataIndex
  extends PageIndex
{
  private final PageStore store;
  private final RegularTable tableData;
  private long lastKey;
  private long rowCount;
  private HashSet<Row> delta;
  private int rowCountDiff;
  private final HashMap<Integer, Integer> sessionRowCount;
  private int mainIndexColumn = -1;
  private DbException fastDuplicateKeyException;
  private int memoryPerPage;
  private int memoryCount;
  private final boolean multiVersion;
  
  public PageDataIndex(RegularTable table, int id, IndexColumn[] columns, IndexType indexType, boolean create, Session session)
  {
    initBaseIndex(table, id, table.getName() + "_DATA", columns, indexType);
    this.multiVersion = this.database.isMultiVersion();
    if (this.multiVersion)
    {
      this.sessionRowCount = New.hashMap();
      this.isMultiVersion = true;
    }
    else
    {
      this.sessionRowCount = null;
    }
    this.tableData = table;
    this.store = this.database.getPageStore();
    this.store.addIndex(this);
    if (!this.database.isPersistent()) {
      throw DbException.throwInternalError(table.getName());
    }
    if (create)
    {
      this.rootPageId = this.store.allocatePage();
      this.store.addMeta(this, session);
      PageDataLeaf root = PageDataLeaf.create(this, this.rootPageId, 0);
      this.store.update(root);
    }
    else
    {
      this.rootPageId = this.store.getRootPageId(id);
      PageData root = getPage(this.rootPageId, 0);
      this.lastKey = root.getLastKey();
      this.rowCount = root.getRowCount();
    }
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} opened rows: {1}", new Object[] { this, Long.valueOf(this.rowCount) });
    }
    table.setRowCount(this.rowCount);
    this.memoryPerPage = (240 + this.store.getPageSize() >> 2);
  }
  
  public DbException getDuplicateKeyException(String key)
  {
    if (this.fastDuplicateKeyException == null) {
      this.fastDuplicateKeyException = super.getDuplicateKeyException(null);
    }
    return this.fastDuplicateKeyException;
  }
  
  /* Error */
  public void add(Session session, Row row)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: getfield 34	org/h2/index/PageDataIndex:mainIndexColumn	I
    //   6: iconst_m1
    //   7: if_icmpeq +21 -> 28
    //   10: aload_2
    //   11: aload_2
    //   12: aload_0
    //   13: getfield 34	org/h2/index/PageDataIndex:mainIndexColumn	I
    //   16: invokevirtual 209	org/h2/result/Row:getValue	(I)Lorg/h2/value/Value;
    //   19: invokevirtual 214	org/h2/value/Value:getLong	()J
    //   22: invokevirtual 217	org/h2/result/Row:setKey	(J)V
    //   25: goto +31 -> 56
    //   28: aload_2
    //   29: invokevirtual 220	org/h2/result/Row:getKey	()J
    //   32: lconst_0
    //   33: lcmp
    //   34: ifne +22 -> 56
    //   37: aload_2
    //   38: aload_0
    //   39: dup
    //   40: getfield 144	org/h2/index/PageDataIndex:lastKey	J
    //   43: lconst_1
    //   44: ladd
    //   45: dup2_x1
    //   46: putfield 144	org/h2/index/PageDataIndex:lastKey	J
    //   49: l2i
    //   50: i2l
    //   51: invokevirtual 217	org/h2/result/Row:setKey	(J)V
    //   54: iconst_1
    //   55: istore_3
    //   56: aload_0
    //   57: getfield 86	org/h2/index/PageDataIndex:tableData	Lorg/h2/table/RegularTable;
    //   60: invokevirtual 223	org/h2/table/RegularTable:getContainsLargeObject	()Z
    //   63: ifeq +77 -> 140
    //   66: iconst_0
    //   67: istore 4
    //   69: aload_2
    //   70: invokevirtual 226	org/h2/result/Row:getColumnCount	()I
    //   73: istore 5
    //   75: iload 4
    //   77: iload 5
    //   79: if_icmpge +61 -> 140
    //   82: aload_2
    //   83: iload 4
    //   85: invokevirtual 209	org/h2/result/Row:getValue	(I)Lorg/h2/value/Value;
    //   88: astore 6
    //   90: aload 6
    //   92: aload_0
    //   93: getfield 60	org/h2/index/PageDataIndex:database	Lorg/h2/engine/Database;
    //   96: aload_0
    //   97: invokevirtual 229	org/h2/index/PageDataIndex:getId	()I
    //   100: invokevirtual 233	org/h2/value/Value:link	(Lorg/h2/store/DataHandler;I)Lorg/h2/value/Value;
    //   103: astore 7
    //   105: aload 7
    //   107: invokevirtual 236	org/h2/value/Value:isLinked	()Z
    //   110: ifeq +9 -> 119
    //   113: aload_1
    //   114: aload 7
    //   116: invokevirtual 240	org/h2/engine/Session:unlinkAtCommitStop	(Lorg/h2/value/Value;)V
    //   119: aload 6
    //   121: aload 7
    //   123: if_acmpeq +11 -> 134
    //   126: aload_2
    //   127: iload 4
    //   129: aload 7
    //   131: invokevirtual 244	org/h2/result/Row:setValue	(ILorg/h2/value/Value;)V
    //   134: iinc 4 1
    //   137: goto -62 -> 75
    //   140: aload_0
    //   141: getfield 153	org/h2/index/PageDataIndex:trace	Lorg/h2/message/Trace;
    //   144: invokevirtual 158	org/h2/message/Trace:isDebugEnabled	()Z
    //   147: ifeq +27 -> 174
    //   150: aload_0
    //   151: getfield 153	org/h2/index/PageDataIndex:trace	Lorg/h2/message/Trace;
    //   154: ldc -10
    //   156: iconst_2
    //   157: anewarray 162	java/lang/Object
    //   160: dup
    //   161: iconst_0
    //   162: aload_0
    //   163: invokevirtual 247	org/h2/index/PageDataIndex:getName	()Ljava/lang/String;
    //   166: aastore
    //   167: dup
    //   168: iconst_1
    //   169: aload_2
    //   170: aastore
    //   171: invokevirtual 172	org/h2/message/Trace:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   174: lconst_0
    //   175: lstore 4
    //   177: aload_0
    //   178: aload_1
    //   179: aload_2
    //   180: invokespecial 250	org/h2/index/PageDataIndex:addTry	(Lorg/h2/engine/Session;Lorg/h2/result/Row;)V
    //   183: aload_0
    //   184: getfield 92	org/h2/index/PageDataIndex:store	Lorg/h2/store/PageStore;
    //   187: invokevirtual 253	org/h2/store/PageStore:incrementChangeCount	()V
    //   190: goto +96 -> 286
    //   193: astore 6
    //   195: aload 6
    //   197: aload_0
    //   198: getfield 197	org/h2/index/PageDataIndex:fastDuplicateKeyException	Lorg/h2/message/DbException;
    //   201: if_acmpeq +6 -> 207
    //   204: aload 6
    //   206: athrow
    //   207: iload_3
    //   208: ifne +8 -> 216
    //   211: aload_0
    //   212: invokevirtual 257	org/h2/index/PageDataIndex:getNewDuplicateKeyException	()Lorg/h2/message/DbException;
    //   215: athrow
    //   216: lload 4
    //   218: lconst_0
    //   219: lcmp
    //   220: ifne +24 -> 244
    //   223: aload_2
    //   224: aload_2
    //   225: invokevirtual 220	org/h2/result/Row:getKey	()J
    //   228: l2d
    //   229: invokestatic 263	java/lang/Math:random	()D
    //   232: ldc2_w 264
    //   235: dmul
    //   236: dadd
    //   237: d2l
    //   238: invokevirtual 217	org/h2/result/Row:setKey	(J)V
    //   241: goto +14 -> 255
    //   244: aload_2
    //   245: aload_2
    //   246: invokevirtual 220	org/h2/result/Row:getKey	()J
    //   249: lload 4
    //   251: ladd
    //   252: invokevirtual 217	org/h2/result/Row:setKey	(J)V
    //   255: lload 4
    //   257: lconst_1
    //   258: ladd
    //   259: lstore 4
    //   261: aload_0
    //   262: getfield 92	org/h2/index/PageDataIndex:store	Lorg/h2/store/PageStore;
    //   265: invokevirtual 253	org/h2/store/PageStore:incrementChangeCount	()V
    //   268: goto +15 -> 283
    //   271: astore 8
    //   273: aload_0
    //   274: getfield 92	org/h2/index/PageDataIndex:store	Lorg/h2/store/PageStore;
    //   277: invokevirtual 253	org/h2/store/PageStore:incrementChangeCount	()V
    //   280: aload 8
    //   282: athrow
    //   283: goto -106 -> 177
    //   286: aload_0
    //   287: aload_0
    //   288: getfield 144	org/h2/index/PageDataIndex:lastKey	J
    //   291: aload_2
    //   292: invokevirtual 220	org/h2/result/Row:getKey	()J
    //   295: invokestatic 271	java/lang/Math:max	(JJ)J
    //   298: putfield 144	org/h2/index/PageDataIndex:lastKey	J
    //   301: return
    // Line number table:
    //   Java source line #107	-> byte code offset #0
    //   Java source line #108	-> byte code offset #2
    //   Java source line #109	-> byte code offset #10
    //   Java source line #111	-> byte code offset #28
    //   Java source line #112	-> byte code offset #37
    //   Java source line #113	-> byte code offset #54
    //   Java source line #116	-> byte code offset #56
    //   Java source line #117	-> byte code offset #66
    //   Java source line #118	-> byte code offset #82
    //   Java source line #119	-> byte code offset #90
    //   Java source line #120	-> byte code offset #105
    //   Java source line #121	-> byte code offset #113
    //   Java source line #123	-> byte code offset #119
    //   Java source line #124	-> byte code offset #126
    //   Java source line #117	-> byte code offset #134
    //   Java source line #130	-> byte code offset #140
    //   Java source line #131	-> byte code offset #150
    //   Java source line #133	-> byte code offset #174
    //   Java source line #136	-> byte code offset #177
    //   Java source line #154	-> byte code offset #183
    //   Java source line #138	-> byte code offset #193
    //   Java source line #139	-> byte code offset #195
    //   Java source line #140	-> byte code offset #204
    //   Java source line #142	-> byte code offset #207
    //   Java source line #143	-> byte code offset #211
    //   Java source line #145	-> byte code offset #216
    //   Java source line #148	-> byte code offset #223
    //   Java source line #150	-> byte code offset #244
    //   Java source line #152	-> byte code offset #255
    //   Java source line #154	-> byte code offset #261
    //   Java source line #155	-> byte code offset #268
    //   Java source line #154	-> byte code offset #271
    //   Java source line #157	-> byte code offset #286
    //   Java source line #158	-> byte code offset #301
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	302	0	this	PageDataIndex
    //   0	302	1	session	Session
    //   0	302	2	row	Row
    //   1	207	3	retry	boolean
    //   67	68	4	i	int
    //   175	85	4	add	long
    //   73	5	5	len	int
    //   88	32	6	v	Value
    //   193	12	6	e	DbException
    //   103	27	7	v2	Value
    //   271	10	8	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   177	183	193	org/h2/message/DbException
    //   177	183	271	finally
    //   193	261	271	finally
    //   271	273	271	finally
  }
  
  public DbException getNewDuplicateKeyException()
  {
    String sql = "PRIMARY KEY ON " + this.table.getSQL();
    if ((this.mainIndexColumn >= 0) && (this.mainIndexColumn < this.indexColumns.length)) {
      sql = sql + "(" + this.indexColumns[this.mainIndexColumn].getSQL() + ")";
    }
    DbException e = DbException.get(23505, sql);
    e.setSource(this);
    return e;
  }
  
  private void addTry(Session session, Row row)
  {
    for (;;)
    {
      PageData root = getPage(this.rootPageId, 0);
      int splitPoint = root.addRowTry(row);
      if (splitPoint == -1) {
        break;
      }
      if (this.trace.isDebugEnabled()) {
        this.trace.debug("{0} split", new Object[] { this });
      }
      long pivot = splitPoint == 0 ? row.getKey() : root.getKey(splitPoint - 1);
      PageData page1 = root;
      PageData page2 = root.split(splitPoint);
      int id = this.store.allocatePage();
      page1.setPageId(id);
      page1.setParentPageId(this.rootPageId);
      page2.setParentPageId(this.rootPageId);
      PageDataNode newRoot = PageDataNode.create(this, this.rootPageId, 0);
      newRoot.init(page1, pivot, page2);
      this.store.update(page1);
      this.store.update(page2);
      this.store.update(newRoot);
      root = newRoot;
    }
    row.setDeleted(false);
    if (this.multiVersion)
    {
      if (this.delta == null) {
        this.delta = New.hashSet();
      }
      boolean wasDeleted = this.delta.remove(row);
      if (!wasDeleted) {
        this.delta.add(row);
      }
      incrementRowCount(session.getId(), 1);
    }
    invalidateRowCount();
    this.rowCount += 1L;
    this.store.logAddOrRemoveRow(session, this.tableData.getId(), row, true);
  }
  
  PageDataOverflow getPageOverflow(int id)
  {
    Page p = this.store.getPage(id);
    if ((p instanceof PageDataOverflow)) {
      return (PageDataOverflow)p;
    }
    throw DbException.get(90030, p == null ? "null" : p.toString());
  }
  
  PageData getPage(int id, int parent)
  {
    Page pd = this.store.getPage(id);
    if (pd == null)
    {
      PageDataLeaf empty = PageDataLeaf.create(this, id, parent);
      
      this.store.logUndo(empty, null);
      this.store.update(empty);
      return empty;
    }
    if (!(pd instanceof PageData)) {
      throw DbException.get(90030, "" + pd);
    }
    PageData p = (PageData)pd;
    if ((parent != -1) && 
      (p.getParentPageId() != parent)) {
      throw DbException.throwInternalError(p + " parent " + p.getParentPageId() + " expected " + parent);
    }
    return p;
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  long getKey(SearchRow row, long ifEmpty, long ifNull)
  {
    if (row == null) {
      return ifEmpty;
    }
    Value v = row.getValue(this.mainIndexColumn);
    if (v == null) {
      throw DbException.throwInternalError(row.toString());
    }
    if (v == ValueNull.INSTANCE) {
      return ifNull;
    }
    return v.getLong();
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    long from = first == null ? Long.MIN_VALUE : first.getKey();
    long to = last == null ? Long.MAX_VALUE : last.getKey();
    PageData root = getPage(this.rootPageId, 0);
    return root.find(session, from, to, this.isMultiVersion);
  }
  
  Cursor find(Session session, long first, long last, boolean multiVersion)
  {
    PageData root = getPage(this.rootPageId, 0);
    return root.find(session, first, last, multiVersion);
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.throwInternalError();
  }
  
  long getLastKey()
  {
    PageData root = getPage(this.rootPageId, 0);
    return root.getLastKey();
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    long cost = 10L * (this.tableData.getRowCountApproximation() + 1000L);
    
    return cost;
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public void remove(Session session, Row row)
  {
    if (this.tableData.getContainsLargeObject())
    {
      int i = 0;
      for (int len = row.getColumnCount(); i < len; i++)
      {
        Value v = row.getValue(i);
        if (v.isLinked()) {
          session.unlinkAtCommit(v);
        }
      }
    }
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} remove {1}", new Object[] { getName(), row });
    }
    if (this.rowCount == 1L) {
      removeAllRows();
    } else {
      try
      {
        long key = row.getKey();
        PageData root = getPage(this.rootPageId, 0);
        root.remove(key);
        invalidateRowCount();
        this.rowCount -= 1L;
      }
      finally
      {
        this.store.incrementChangeCount();
      }
    }
    if (this.multiVersion)
    {
      row.setDeleted(true);
      if (this.delta == null) {
        this.delta = New.hashSet();
      }
      boolean wasAdded = this.delta.remove(row);
      if (!wasAdded) {
        this.delta.add(row);
      }
      incrementRowCount(session.getId(), -1);
    }
    this.store.logAddOrRemoveRow(session, this.tableData.getId(), row, false);
  }
  
  public void remove(Session session)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} remove", new Object[] { this });
    }
    removeAllRows();
    this.store.free(this.rootPageId);
    this.store.removeMeta(this, session);
  }
  
  public void truncate(Session session)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} truncate", new Object[] { this });
    }
    this.store.logTruncate(session, this.tableData.getId());
    removeAllRows();
    if ((this.tableData.getContainsLargeObject()) && (this.tableData.isPersistData()))
    {
      session.commit(false);
      this.database.getLobStorage().removeAllForTable(this.table.getId());
    }
    if (this.multiVersion) {
      this.sessionRowCount.clear();
    }
    this.tableData.setRowCount(0L);
  }
  
  private void removeAllRows()
  {
    try
    {
      PageData root = getPage(this.rootPageId, 0);
      root.freeRecursive();
      root = PageDataLeaf.create(this, this.rootPageId, 0);
      this.store.removeFromCache(this.rootPageId);
      this.store.update(root);
      this.rowCount = 0L;
      this.lastKey = 0L;
    }
    finally
    {
      this.store.incrementChangeCount();
    }
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("PAGE");
  }
  
  public Row getRow(Session session, long key)
  {
    return getRowWithKey(key);
  }
  
  public Row getRowWithKey(long key)
  {
    PageData root = getPage(this.rootPageId, 0);
    return root.getRowWithKey(key);
  }
  
  PageStore getPageStore()
  {
    return this.store;
  }
  
  public long getRowCountApproximation()
  {
    return this.rowCount;
  }
  
  public long getRowCount(Session session)
  {
    if (this.multiVersion)
    {
      Integer i = (Integer)this.sessionRowCount.get(Integer.valueOf(session.getId()));
      long count = i == null ? 0L : i.intValue();
      count += this.rowCount;
      count -= this.rowCountDiff;
      return count;
    }
    return this.rowCount;
  }
  
  public long getDiskSpaceUsed()
  {
    PageData root = getPage(this.rootPageId, 0);
    return root.getDiskSpaceUsed();
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public int getColumnIndex(Column col)
  {
    return -1;
  }
  
  public void close(Session session)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("{0} close", new Object[] { this });
    }
    if (this.delta != null) {
      this.delta.clear();
    }
    this.rowCountDiff = 0;
    if (this.sessionRowCount != null) {
      this.sessionRowCount.clear();
    }
    writeRowCount();
  }
  
  Iterator<Row> getDelta()
  {
    if (this.delta == null)
    {
      List<Row> e = Collections.emptyList();
      return e.iterator();
    }
    return this.delta.iterator();
  }
  
  private void incrementRowCount(int sessionId, int count)
  {
    if (this.multiVersion)
    {
      Integer id = Integer.valueOf(sessionId);
      Integer c = (Integer)this.sessionRowCount.get(id);
      int current = c == null ? 0 : c.intValue();
      this.sessionRowCount.put(id, Integer.valueOf(current + count));
      this.rowCountDiff += count;
    }
  }
  
  public void commit(int operation, Row row)
  {
    if (this.multiVersion)
    {
      if (this.delta != null) {
        this.delta.remove(row);
      }
      incrementRowCount(row.getSessionId(), operation == 1 ? 1 : -1);
    }
  }
  
  void setRootPageId(Session session, int newPos)
  {
    this.store.removeMeta(this, session);
    this.rootPageId = newPos;
    this.store.addMeta(this, session);
    this.store.addIndex(this);
  }
  
  public void setMainIndexColumn(int mainIndexColumn)
  {
    this.mainIndexColumn = mainIndexColumn;
  }
  
  public int getMainIndexColumn()
  {
    return this.mainIndexColumn;
  }
  
  public String toString()
  {
    return getName();
  }
  
  private void invalidateRowCount()
  {
    PageData root = getPage(this.rootPageId, 0);
    root.setRowCountStored(-1);
  }
  
  public void writeRowCount()
  {
    if ((SysProperties.MODIFY_ON_WRITE) && (this.rootPageId == 0)) {
      return;
    }
    try
    {
      PageData root = getPage(this.rootPageId, 0);
      root.setRowCountStored(MathUtils.convertLongToInt(this.rowCount));
    }
    finally
    {
      this.store.incrementChangeCount();
    }
  }
  
  public String getPlanSQL()
  {
    return this.table.getSQL() + ".tableScan";
  }
  
  int getMemoryPerPage()
  {
    return this.memoryPerPage;
  }
  
  void memoryChange(int x)
  {
    if (this.memoryCount < 64) {
      this.memoryPerPage += (x - this.memoryPerPage) / ++this.memoryCount;
    } else {
      this.memoryPerPage += (x > this.memoryPerPage ? 1 : -1) + (x - this.memoryPerPage) / 64;
    }
  }
  
  public boolean isRowIdIndex()
  {
    return true;
  }
}
