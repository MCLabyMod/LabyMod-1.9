package org.h2.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.h2.compress.CompressLZF;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.Row;
import org.h2.util.BitField;
import org.h2.util.IntArray;
import org.h2.util.IntIntHashMap;
import org.h2.util.New;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class PageLog
{
  public static final int NOOP = 0;
  public static final int UNDO = 1;
  public static final int COMMIT = 2;
  public static final int PREPARE_COMMIT = 3;
  public static final int ROLLBACK = 4;
  public static final int ADD = 5;
  public static final int REMOVE = 6;
  public static final int TRUNCATE = 7;
  public static final int CHECKPOINT = 8;
  public static final int FREE_LOG = 9;
  static final int RECOVERY_STAGE_UNDO = 0;
  static final int RECOVERY_STAGE_ALLOCATE = 1;
  static final int RECOVERY_STAGE_REDO = 2;
  private static final boolean COMPRESS_UNDO = true;
  private final PageStore store;
  private final Trace trace;
  private Data writeBuffer;
  private PageOutputStream pageOut;
  private int firstTrunkPage;
  private int firstDataPage;
  private final Data dataBuffer;
  private int logKey;
  private int logSectionId;
  private int logPos;
  private int firstSectionId;
  private final CompressLZF compress;
  private final byte[] compressBuffer;
  private BitField undo = new BitField();
  private final BitField undoAll = new BitField();
  private final IntIntHashMap logSectionPageMap = new IntIntHashMap();
  private HashMap<Integer, SessionState> sessionStates = New.hashMap();
  private BitField usedLogPages;
  private boolean freeing;
  
  PageLog(PageStore store)
  {
    this.store = store;
    this.dataBuffer = store.createData();
    this.trace = store.getTrace();
    this.compress = new CompressLZF();
    this.compressBuffer = new byte[store.getPageSize() * 2];
  }
  
  void openForWriting(int newFirstTrunkPage, boolean atEnd)
  {
    this.trace.debug("log openForWriting firstPage: " + newFirstTrunkPage);
    this.firstTrunkPage = newFirstTrunkPage;
    this.logKey += 1;
    this.pageOut = new PageOutputStream(this.store, newFirstTrunkPage, this.undoAll, this.logKey, atEnd);
    
    this.pageOut.reserve(1);
    
    this.store.setLogFirstPage(this.logKey, newFirstTrunkPage, this.pageOut.getCurrentDataPageId());
    
    this.writeBuffer = this.store.createData();
  }
  
  void free()
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log free");
    }
    int currentDataPage = 0;
    if (this.pageOut != null)
    {
      currentDataPage = this.pageOut.getCurrentDataPageId();
      this.pageOut.freeReserved();
    }
    try
    {
      this.freeing = true;
      int first = 0;
      int loopDetect = 1024;int loopCount = 0;
      PageStreamTrunk.Iterator it = new PageStreamTrunk.Iterator(this.store, this.firstTrunkPage);
      while ((this.firstTrunkPage != 0) && (this.firstTrunkPage < this.store.getPageCount()))
      {
        PageStreamTrunk t = it.next();
        if (t == null)
        {
          if (!it.canDelete()) {
            break;
          }
          this.store.free(this.firstTrunkPage, false); break;
        }
        if (loopCount++ >= loopDetect)
        {
          first = t.getPos();
          loopCount = 0;
          loopDetect *= 2;
        }
        else if ((first != 0) && (first == t.getPos()))
        {
          throw DbException.throwInternalError("endless loop at " + t);
        }
        t.free(currentDataPage);
        this.firstTrunkPage = t.getNextTrunk();
      }
    }
    finally
    {
      this.freeing = false;
    }
  }
  
  void openForReading(int newLogKey, int newFirstTrunkPage, int newFirstDataPage)
  {
    this.logKey = newLogKey;
    this.firstTrunkPage = newFirstTrunkPage;
    this.firstDataPage = newFirstDataPage;
  }
  
  boolean recover(int stage)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log recover stage: " + stage);
    }
    if (stage == 1)
    {
      PageInputStream in = new PageInputStream(this.store, this.logKey, this.firstTrunkPage, this.firstDataPage);
      
      this.usedLogPages = in.allocateAllPages();
      in.close();
      return true;
    }
    PageInputStream pageIn = new PageInputStream(this.store, this.logKey, this.firstTrunkPage, this.firstDataPage);
    
    DataReader in = new DataReader(pageIn);
    int logId = 0;
    Data data = this.store.createData();
    boolean isEmpty = true;
    try
    {
      int pos = 0;
      for (;;)
      {
        int x = in.readByte();
        if (x < 0) {
          break;
        }
        pos++;
        isEmpty = false;
        if (x == 1)
        {
          int pageId = in.readVarInt();
          int size = in.readVarInt();
          if (size == 0)
          {
            in.readFully(data.getBytes(), this.store.getPageSize());
          }
          else if (size == 1)
          {
            Arrays.fill(data.getBytes(), 0, this.store.getPageSize(), (byte)0);
          }
          else
          {
            in.readFully(this.compressBuffer, size);
            try
            {
              this.compress.expand(this.compressBuffer, 0, size, data.getBytes(), 0, this.store.getPageSize());
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
              DbException.convertToIOException(e);
            }
          }
          if (stage == 0) {
            if (!this.undo.get(pageId))
            {
              if (this.trace.isDebugEnabled()) {
                this.trace.debug("log undo {0}", new Object[] { Integer.valueOf(pageId) });
              }
              this.store.writePage(pageId, data);
              this.undo.set(pageId);
              this.undoAll.set(pageId);
            }
            else if (this.trace.isDebugEnabled())
            {
              this.trace.debug("log undo skip {0}", new Object[] { Integer.valueOf(pageId) });
            }
          }
        }
        else if (x == 5)
        {
          int sessionId = in.readVarInt();
          int tableId = in.readVarInt();
          Row row = readRow(in, data);
          if (stage == 0) {
            this.store.allocateIfIndexRoot(pos, tableId, row);
          } else if (stage == 2) {
            if (isSessionCommitted(sessionId, logId, pos))
            {
              if (this.trace.isDebugEnabled()) {
                this.trace.debug("log redo + table: " + tableId + " s: " + sessionId + " " + row);
              }
              this.store.redo(tableId, row, true);
            }
            else if (this.trace.isDebugEnabled())
            {
              this.trace.debug("log ignore s: " + sessionId + " + table: " + tableId + " " + row);
            }
          }
        }
        else if (x == 6)
        {
          int sessionId = in.readVarInt();
          int tableId = in.readVarInt();
          long key = in.readVarLong();
          if (stage == 2) {
            if (isSessionCommitted(sessionId, logId, pos))
            {
              if (this.trace.isDebugEnabled()) {
                this.trace.debug("log redo - table: " + tableId + " s:" + sessionId + " key: " + key);
              }
              this.store.redoDelete(tableId, key);
            }
            else if (this.trace.isDebugEnabled())
            {
              this.trace.debug("log ignore s: " + sessionId + " - table: " + tableId + " " + key);
            }
          }
        }
        else if (x == 7)
        {
          int sessionId = in.readVarInt();
          int tableId = in.readVarInt();
          if (stage == 2) {
            if (isSessionCommitted(sessionId, logId, pos))
            {
              if (this.trace.isDebugEnabled()) {
                this.trace.debug("log redo truncate table: " + tableId);
              }
              this.store.redoTruncate(tableId);
            }
            else if (this.trace.isDebugEnabled())
            {
              this.trace.debug("log ignore s: " + sessionId + " truncate table: " + tableId);
            }
          }
        }
        else if (x == 3)
        {
          int sessionId = in.readVarInt();
          String transaction = in.readString();
          if (this.trace.isDebugEnabled()) {
            this.trace.debug("log prepare commit " + sessionId + " " + transaction + " pos: " + pos);
          }
          if (stage == 0)
          {
            int page = pageIn.getDataPage();
            setPrepareCommit(sessionId, page, transaction);
          }
        }
        else if (x == 4)
        {
          int sessionId = in.readVarInt();
          if (this.trace.isDebugEnabled()) {
            this.trace.debug("log rollback " + sessionId + " pos: " + pos);
          }
        }
        else if (x == 2)
        {
          int sessionId = in.readVarInt();
          if (this.trace.isDebugEnabled()) {
            this.trace.debug("log commit " + sessionId + " pos: " + pos);
          }
          if (stage == 0) {
            setLastCommitForSession(sessionId, logId, pos);
          }
        }
        else if (x != 0)
        {
          if (x == 8)
          {
            logId++;
          }
          else if (x == 9)
          {
            int count = in.readVarInt();
            for (int i = 0; i < count; i++)
            {
              int pageId = in.readVarInt();
              if ((stage == 2) && 
                (!this.usedLogPages.get(pageId))) {
                this.store.free(pageId, false);
              }
            }
          }
          else if (this.trace.isDebugEnabled())
          {
            this.trace.debug("log end");
            break;
          }
        }
      }
    }
    catch (DbException e)
    {
      if (e.getErrorCode() == 90030) {
        this.trace.debug("log recovery stopped");
      } else {
        throw e;
      }
    }
    catch (IOException e)
    {
      this.trace.debug("log recovery completed");
    }
    this.undo = new BitField();
    if (stage == 2) {
      this.usedLogPages = null;
    }
    return isEmpty;
  }
  
  private void setPrepareCommit(int sessionId, int pageId, String transaction)
  {
    SessionState state = getOrAddSessionState(sessionId);
    PageStoreInDoubtTransaction doubt;
    PageStoreInDoubtTransaction doubt;
    if (transaction == null) {
      doubt = null;
    } else {
      doubt = new PageStoreInDoubtTransaction(this.store, sessionId, pageId, transaction);
    }
    state.inDoubtTransaction = doubt;
  }
  
  public static Row readRow(DataReader in, Data data)
    throws IOException
  {
    long key = in.readVarLong();
    int len = in.readVarInt();
    data.reset();
    data.checkCapacity(len);
    in.readFully(data.getBytes(), len);
    int columnCount = data.readVarInt();
    Value[] values = new Value[columnCount];
    for (int i = 0; i < columnCount; i++) {
      values[i] = data.readValue();
    }
    Row row = new Row(values, -1);
    row.setKey(key);
    return row;
  }
  
  boolean getUndo(int pageId)
  {
    return this.undo.get(pageId);
  }
  
  void addUndo(int pageId, Data page)
  {
    if ((this.undo.get(pageId)) || (this.freeing)) {
      return;
    }
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log undo " + pageId);
    }
    if ((SysProperties.CHECK) && 
      (page == null)) {
      DbException.throwInternalError("Undo entry not written");
    }
    this.undo.set(pageId);
    this.undoAll.set(pageId);
    Data buffer = getBuffer();
    buffer.writeByte((byte)1);
    buffer.writeVarInt(pageId);
    if (page.getBytes()[0] == 0)
    {
      buffer.writeVarInt(1);
    }
    else
    {
      int pageSize = this.store.getPageSize();
      
      int size = this.compress.compress(page.getBytes(), pageSize, this.compressBuffer, 0);
      if (size < pageSize)
      {
        buffer.writeVarInt(size);
        buffer.checkCapacity(size);
        buffer.write(this.compressBuffer, 0, size);
      }
      else
      {
        buffer.writeVarInt(0);
        buffer.checkCapacity(pageSize);
        buffer.write(page.getBytes(), 0, pageSize);
      }
    }
    write(buffer);
  }
  
  private void freeLogPages(IntArray pages)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log frees " + pages.get(0) + ".." + pages.get(pages.size() - 1));
    }
    Data buffer = getBuffer();
    buffer.writeByte((byte)9);
    int size = pages.size();
    buffer.writeVarInt(size);
    for (int i = 0; i < size; i++) {
      buffer.writeVarInt(pages.get(i));
    }
    write(buffer);
  }
  
  private void write(Data data)
  {
    this.pageOut.write(data.getBytes(), 0, data.length());
    data.reset();
  }
  
  void commit(int sessionId)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log commit s: " + sessionId);
    }
    if (this.store.getDatabase().getPageStore() == null) {
      return;
    }
    Data buffer = getBuffer();
    buffer.writeByte((byte)2);
    buffer.writeVarInt(sessionId);
    write(buffer);
    if (this.store.getDatabase().getFlushOnEachCommit()) {
      flush();
    }
  }
  
  void prepareCommit(Session session, String transaction)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log prepare commit s: " + session.getId() + ", " + transaction);
    }
    if (this.store.getDatabase().getPageStore() == null) {
      return;
    }
    int pageSize = this.store.getPageSize();
    this.pageOut.flush();
    this.pageOut.fillPage();
    Data buffer = getBuffer();
    buffer.writeByte((byte)3);
    buffer.writeVarInt(session.getId());
    buffer.writeString(transaction);
    if (buffer.length() >= PageStreamData.getCapacity(pageSize)) {
      throw DbException.getInvalidValueException("transaction name (too long)", transaction);
    }
    write(buffer);
    
    flushOut();
    this.pageOut.fillPage();
    if (this.store.getDatabase().getFlushOnEachCommit()) {
      flush();
    }
  }
  
  void logAddOrRemoveRow(Session session, int tableId, Row row, boolean add)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log " + (add ? "+" : "-") + " s: " + session.getId() + " table: " + tableId + " row: " + row);
    }
    session.addLogPos(this.logSectionId, this.logPos);
    this.logPos += 1;
    Data data = this.dataBuffer;
    data.reset();
    int columns = row.getColumnCount();
    data.writeVarInt(columns);
    data.checkCapacity(row.getByteCount(data));
    if (session.isRedoLogBinaryEnabled()) {
      for (int i = 0; i < columns; i++) {
        data.writeValue(row.getValue(i));
      }
    } else {
      for (int i = 0; i < columns; i++)
      {
        Value v = row.getValue(i);
        if (v.getType() == 12) {
          data.writeValue(ValueNull.INSTANCE);
        } else {
          data.writeValue(v);
        }
      }
    }
    Data buffer = getBuffer();
    buffer.writeByte((byte)(add ? 5 : 6));
    buffer.writeVarInt(session.getId());
    buffer.writeVarInt(tableId);
    buffer.writeVarLong(row.getKey());
    if (add)
    {
      buffer.writeVarInt(data.length());
      buffer.checkCapacity(data.length());
      buffer.write(data.getBytes(), 0, data.length());
    }
    write(buffer);
  }
  
  void logTruncate(Session session, int tableId)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("log truncate s: " + session.getId() + " table: " + tableId);
    }
    session.addLogPos(this.logSectionId, this.logPos);
    this.logPos += 1;
    Data buffer = getBuffer();
    buffer.writeByte((byte)7);
    buffer.writeVarInt(session.getId());
    buffer.writeVarInt(tableId);
    write(buffer);
  }
  
  void flush()
  {
    if (this.pageOut != null) {
      flushOut();
    }
  }
  
  void checkpoint()
  {
    Data buffer = getBuffer();
    buffer.writeByte((byte)8);
    write(buffer);
    this.undo = new BitField();
    this.logSectionId += 1;
    this.logPos = 0;
    this.pageOut.flush();
    this.pageOut.fillPage();
    int currentDataPage = this.pageOut.getCurrentDataPageId();
    this.logSectionPageMap.put(this.logSectionId, currentDataPage);
  }
  
  int getLogSectionId()
  {
    return this.logSectionId;
  }
  
  int getLogFirstSectionId()
  {
    return this.firstSectionId;
  }
  
  int getLogPos()
  {
    return this.logPos;
  }
  
  void removeUntil(int firstUncommittedSection)
  {
    if (firstUncommittedSection == 0) {
      return;
    }
    int firstDataPageToKeep = this.logSectionPageMap.get(firstUncommittedSection);
    this.firstTrunkPage = removeUntil(this.firstTrunkPage, firstDataPageToKeep);
    this.store.setLogFirstPage(this.logKey, this.firstTrunkPage, firstDataPageToKeep);
    while (this.firstSectionId < firstUncommittedSection)
    {
      if (this.firstSectionId > 0) {
        this.logSectionPageMap.remove(this.firstSectionId);
      }
      this.firstSectionId += 1;
    }
  }
  
  private int removeUntil(int trunkPage, int firstDataPageToKeep)
  {
    this.trace.debug("log.removeUntil " + trunkPage + " " + firstDataPageToKeep);
    int last = trunkPage;
    for (;;)
    {
      Page p = this.store.getPage(trunkPage);
      PageStreamTrunk t = (PageStreamTrunk)p;
      if (t == null) {
        throw DbException.throwInternalError("log.removeUntil not found: " + firstDataPageToKeep + " last " + last);
      }
      this.logKey = t.getLogKey();
      last = t.getPos();
      if (t.contains(firstDataPageToKeep)) {
        return last;
      }
      trunkPage = t.getNextTrunk();
      IntArray list = new IntArray();
      list.add(t.getPos());
      for (int i = 0;; i++)
      {
        int next = t.getPageData(i);
        if (next == -1) {
          break;
        }
        list.add(next);
      }
      freeLogPages(list);
      this.pageOut.free(t);
    }
  }
  
  void close()
  {
    this.trace.debug("log close");
    if (this.pageOut != null)
    {
      this.pageOut.close();
      this.pageOut = null;
    }
    this.writeBuffer = null;
  }
  
  private boolean isSessionCommitted(int sessionId, int logId, int pos)
  {
    SessionState state = (SessionState)this.sessionStates.get(Integer.valueOf(sessionId));
    if (state == null) {
      return false;
    }
    return state.isCommitted(logId, pos);
  }
  
  private void setLastCommitForSession(int sessionId, int logId, int pos)
  {
    SessionState state = getOrAddSessionState(sessionId);
    state.lastCommitLog = logId;
    state.lastCommitPos = pos;
    state.inDoubtTransaction = null;
  }
  
  private SessionState getOrAddSessionState(int sessionId)
  {
    Integer key = Integer.valueOf(sessionId);
    SessionState state = (SessionState)this.sessionStates.get(key);
    if (state == null)
    {
      state = new SessionState();
      this.sessionStates.put(key, state);
      state.sessionId = sessionId;
    }
    return state;
  }
  
  long getSize()
  {
    return this.pageOut == null ? 0L : this.pageOut.getSize();
  }
  
  ArrayList<InDoubtTransaction> getInDoubtTransactions()
  {
    ArrayList<InDoubtTransaction> list = New.arrayList();
    for (SessionState state : this.sessionStates.values())
    {
      PageStoreInDoubtTransaction in = state.inDoubtTransaction;
      if (in != null) {
        list.add(in);
      }
    }
    return list;
  }
  
  void setInDoubtTransactionState(int sessionId, int pageId, boolean commit)
  {
    PageStreamData d = (PageStreamData)this.store.getPage(pageId);
    d.initWrite();
    Data buff = this.store.createData();
    buff.writeByte((byte)(commit ? 2 : 4));
    buff.writeVarInt(sessionId);
    byte[] bytes = buff.getBytes();
    d.write(bytes, 0, bytes.length);
    bytes = new byte[d.getRemaining()];
    d.write(bytes, 0, bytes.length);
    d.write();
  }
  
  void recoverEnd()
  {
    this.sessionStates = New.hashMap();
  }
  
  private void flushOut()
  {
    this.pageOut.flush();
  }
  
  private Data getBuffer()
  {
    if (this.writeBuffer.length() == 0) {
      return this.writeBuffer;
    }
    return this.store.createData();
  }
  
  int getMinPageId()
  {
    return this.pageOut == null ? 0 : this.pageOut.getMinPageId();
  }
}
