package org.h2.store;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.CRC32;
import org.h2.command.ddl.CreateTableData;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.MultiVersionIndex;
import org.h2.index.PageBtreeIndex;
import org.h2.index.PageBtreeLeaf;
import org.h2.index.PageBtreeNode;
import org.h2.index.PageDataIndex;
import org.h2.index.PageDataLeaf;
import org.h2.index.PageDataNode;
import org.h2.index.PageDataOverflow;
import org.h2.index.PageDelegateIndex;
import org.h2.index.PageIndex;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.store.fs.FileUtils;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.util.BitField;
import org.h2.util.Cache;
import org.h2.util.CacheLRU;
import org.h2.util.CacheObject;
import org.h2.util.CacheWriter;
import org.h2.util.IntArray;
import org.h2.util.IntIntHashMap;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueInt;
import org.h2.value.ValueString;

public class PageStore
  implements CacheWriter
{
  public static final int PAGE_SIZE_MIN = 64;
  public static final int PAGE_SIZE_MAX = 32768;
  public static final int LOG_MODE_OFF = 0;
  public static final int LOG_MODE_SYNC = 2;
  private static final int PAGE_ID_FREE_LIST_ROOT = 3;
  private static final int PAGE_ID_META_ROOT = 4;
  private static final int MIN_PAGE_COUNT = 5;
  private static final int INCREMENT_KB = 1024;
  private static final int INCREMENT_PERCENT_MIN = 35;
  private static final int READ_VERSION = 3;
  private static final int WRITE_VERSION = 3;
  private static final int META_TYPE_DATA_INDEX = 0;
  private static final int META_TYPE_BTREE_INDEX = 1;
  private static final int META_TABLE_ID = -1;
  private static final int COMPACT_BLOCK_SIZE = 1536;
  private final Database database;
  private final Trace trace;
  private final String fileName;
  private FileStore file;
  private String accessMode;
  private int pageSize = 4096;
  private int pageSizeShift;
  private long writeCountBase;
  private long writeCount;
  private long readCount;
  private int logKey;
  private int logFirstTrunkPage;
  private int logFirstDataPage;
  private final Cache cache;
  private int freeListPagesPerList;
  private boolean recoveryRunning;
  private boolean ignoreBigLog;
  private int firstFreeListIndex;
  private long fileLength;
  private int pageCount;
  private PageLog log;
  private Schema metaSchema;
  private RegularTable metaTable;
  private PageDataIndex metaIndex;
  private final IntIntHashMap metaRootPageId = new IntIntHashMap();
  private final HashMap<Integer, PageIndex> metaObjects = New.hashMap();
  private HashMap<Integer, PageIndex> tempObjects;
  private HashMap<Integer, Integer> reservedPages;
  private boolean isNew;
  private long maxLogSize = 16777216L;
  private final Session pageStoreSession;
  private final BitField freed = new BitField();
  private final ArrayList<PageFreeList> freeLists = New.arrayList();
  private boolean recordPageReads;
  private ArrayList<Integer> recordedPagesList;
  private IntIntHashMap recordedPagesIndex;
  private long changeCount = 1L;
  private Data emptyPage;
  private long logSizeBase;
  private HashMap<String, Integer> statistics;
  private int logMode = 2;
  private boolean lockFile;
  private boolean readMode;
  private int backupLevel;
  
  public PageStore(Database database, String fileName, String accessMode, int cacheSizeDefault)
  {
    this.fileName = fileName;
    this.accessMode = accessMode;
    this.database = database;
    this.trace = database.getTrace("pageStore");
    
    String cacheType = database.getCacheType();
    this.cache = CacheLRU.getCache(this, cacheType, cacheSizeDefault);
    this.pageStoreSession = new Session(database, null, 0);
  }
  
  public void statisticsStart()
  {
    this.statistics = New.hashMap();
  }
  
  public HashMap<String, Integer> statisticsEnd()
  {
    HashMap<String, Integer> result = this.statistics;
    this.statistics = null;
    return result;
  }
  
  private void statisticsIncrement(String key)
  {
    if (this.statistics != null)
    {
      Integer old = (Integer)this.statistics.get(key);
      this.statistics.put(key, Integer.valueOf(old == null ? 1 : old.intValue() + 1));
    }
  }
  
  public synchronized int copyDirect(int pageId, OutputStream out)
    throws IOException
  {
    byte[] buffer = new byte[this.pageSize];
    if (pageId >= this.pageCount) {
      return -1;
    }
    this.file.seek(pageId << this.pageSizeShift);
    this.file.readFullyDirect(buffer, 0, this.pageSize);
    this.readCount += 1L;
    out.write(buffer, 0, this.pageSize);
    return pageId + 1;
  }
  
  public synchronized void open()
  {
    try
    {
      this.metaRootPageId.put(-1, 4);
      if (FileUtils.exists(this.fileName))
      {
        long length = FileUtils.size(this.fileName);
        if (length < 320L)
        {
          if (this.database.isReadOnly()) {
            throw DbException.get(90030, this.fileName + " length: " + length);
          }
          openNew();
        }
        else
        {
          openExisting();
        }
      }
      else
      {
        openNew();
      }
    }
    catch (DbException e)
    {
      close();
      throw e;
    }
  }
  
  private void openNew()
  {
    setPageSize(this.pageSize);
    this.freeListPagesPerList = PageFreeList.getPagesAddressed(this.pageSize);
    this.file = this.database.openFile(this.fileName, this.accessMode, false);
    lockFile();
    this.recoveryRunning = true;
    writeStaticHeader();
    writeVariableHeader();
    this.log = new PageLog(this);
    increaseFileSize(5);
    openMetaIndex();
    this.logFirstTrunkPage = allocatePage();
    this.log.openForWriting(this.logFirstTrunkPage, false);
    this.isNew = true;
    this.recoveryRunning = false;
    increaseFileSize();
  }
  
  private void lockFile()
  {
    if ((this.lockFile) && 
      (!this.file.tryLock())) {
      throw DbException.get(90020, this.fileName);
    }
  }
  
  private void openExisting()
  {
    try
    {
      this.file = this.database.openFile(this.fileName, this.accessMode, true);
    }
    catch (DbException e)
    {
      if ((e.getErrorCode() == 90031) && 
        (e.getMessage().contains("locked"))) {
        throw DbException.get(90020, e, new String[] { this.fileName });
      }
      throw e;
    }
    lockFile();
    readStaticHeader();
    this.freeListPagesPerList = PageFreeList.getPagesAddressed(this.pageSize);
    this.fileLength = this.file.length();
    this.pageCount = ((int)(this.fileLength / this.pageSize));
    if (this.pageCount < 5)
    {
      if (this.database.isReadOnly()) {
        throw DbException.get(90030, this.fileName + " pageCount: " + this.pageCount);
      }
      this.file.releaseLock();
      this.file.close();
      FileUtils.delete(this.fileName);
      openNew();
      return;
    }
    readVariableHeader();
    this.log = new PageLog(this);
    this.log.openForReading(this.logKey, this.logFirstTrunkPage, this.logFirstDataPage);
    boolean old = this.database.isMultiVersion();
    
    this.database.setMultiVersion(false);
    boolean isEmpty = recover();
    this.database.setMultiVersion(old);
    if (!this.database.isReadOnly())
    {
      this.readMode = true;
      if ((!isEmpty) || (!SysProperties.MODIFY_ON_WRITE) || (this.tempObjects != null))
      {
        openForWriting();
        removeOldTempIndexes();
      }
    }
  }
  
  private void openForWriting()
  {
    if ((!this.readMode) || (this.database.isReadOnly())) {
      return;
    }
    this.readMode = false;
    this.recoveryRunning = true;
    this.log.free();
    this.logFirstTrunkPage = allocatePage();
    this.log.openForWriting(this.logFirstTrunkPage, false);
    this.recoveryRunning = false;
    this.freed.set(0, this.pageCount, true);
    checkpoint();
  }
  
  private void removeOldTempIndexes()
  {
    if (this.tempObjects != null)
    {
      this.metaObjects.putAll(this.tempObjects);
      for (PageIndex index : this.tempObjects.values()) {
        if (index.getTable().isTemporary())
        {
          index.truncate(this.pageStoreSession);
          index.remove(this.pageStoreSession);
        }
      }
      this.pageStoreSession.commit(true);
      this.tempObjects = null;
    }
    this.metaObjects.clear();
    this.metaObjects.put(Integer.valueOf(-1), this.metaIndex);
  }
  
  private void writeIndexRowCounts()
  {
    for (PageIndex index : this.metaObjects.values()) {
      index.writeRowCount();
    }
  }
  
  private void writeBack()
  {
    ArrayList<CacheObject> list = this.cache.getAllChanged();
    Collections.sort(list);
    int i = 0;
    for (int size = list.size(); i < size; i++) {
      writeBack((CacheObject)list.get(i));
    }
  }
  
  public synchronized void checkpoint()
  {
    this.trace.debug("checkpoint");
    if ((this.log == null) || (this.readMode) || (this.database.isReadOnly()) || (this.backupLevel > 0)) {
      return;
    }
    this.database.checkPowerOff();
    writeIndexRowCounts();
    
    this.log.checkpoint();
    writeBack();
    
    int firstUncommittedSection = getFirstUncommittedSection();
    
    this.log.removeUntil(firstUncommittedSection);
    
    writeBack();
    
    this.log.checkpoint();
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("writeFree");
    }
    byte[] test = new byte[16];
    byte[] empty = new byte[this.pageSize];
    for (int i = 3; i < this.pageCount; i++) {
      if (isUsed(i))
      {
        this.freed.clear(i);
      }
      else if (!this.freed.get(i))
      {
        if (this.trace.isDebugEnabled()) {
          this.trace.debug("free " + i);
        }
        this.file.seek(i << this.pageSizeShift);
        this.file.readFully(test, 0, 16);
        if (test[0] != 0)
        {
          this.file.seek(i << this.pageSizeShift);
          this.file.write(empty, 0, this.pageSize);
          this.writeCount += 1L;
        }
        this.freed.set(i);
      }
    }
  }
  
  public synchronized void compact(int compactMode)
  {
    if (!this.database.getSettings().pageStoreTrim) {
      return;
    }
    if ((SysProperties.MODIFY_ON_WRITE) && (this.readMode) && (compactMode == 0)) {
      return;
    }
    openForWriting();
    
    int lastUsed = -1;
    for (int i = getFreeListId(this.pageCount); i >= 0; i--)
    {
      lastUsed = getFreeList(i).getLastUsed();
      if (lastUsed != -1) {
        break;
      }
    }
    writeBack();
    this.log.free();
    this.recoveryRunning = true;
    try
    {
      this.logFirstTrunkPage = (lastUsed + 1);
      allocatePage(this.logFirstTrunkPage);
      this.log.openForWriting(this.logFirstTrunkPage, true);
      
      this.log.checkpoint();
    }
    finally
    {
      this.recoveryRunning = false;
    }
    long start = System.currentTimeMillis();
    boolean isCompactFully = compactMode == 82;
    
    boolean isDefrag = compactMode == 84;
    if (this.database.getSettings().defragAlways) {
      isCompactFully = isDefrag = 1;
    }
    int maxCompactTime = this.database.getSettings().maxCompactTime;
    int maxMove = this.database.getSettings().maxCompactCount;
    if ((isCompactFully) || (isDefrag))
    {
      maxCompactTime = Integer.MAX_VALUE;
      maxMove = Integer.MAX_VALUE;
    }
    int blockSize = isCompactFully ? 1536 : 1;
    int firstFree = 5;
    int x = lastUsed;
    for (int j = 0; (x > 5) && (j < maxMove); x -= blockSize) {
      for (int full = x - blockSize + 1; full <= x; full++) {
        if ((full > 5) && (isUsed(full))) {
          synchronized (this)
          {
            firstFree = getFirstFree(firstFree);
            if ((firstFree == -1) || (firstFree >= full))
            {
              j = maxMove;
              break;
            }
            if (compact(full, firstFree))
            {
              j++;
              long now = System.currentTimeMillis();
              if (now > start + maxCompactTime)
              {
                j = maxMove;
                break;
              }
            }
          }
        }
      }
    }
    if (isDefrag)
    {
      this.log.checkpoint();
      writeBack();
      this.cache.clear();
      ArrayList<Table> tables = this.database.getAllTablesAndViews(false);
      this.recordedPagesList = New.arrayList();
      this.recordedPagesIndex = new IntIntHashMap();
      this.recordPageReads = true;
      Session sysSession = this.database.getSystemSession();
      for (Table table : tables) {
        if ((!table.isTemporary()) && ("TABLE".equals(table.getTableType())))
        {
          scanIndex = table.getScanIndex(sysSession);
          cursor = scanIndex.find(sysSession, null, null);
          while (cursor.next()) {
            cursor.get();
          }
          for (Index index : table.getIndexes()) {
            if ((index != scanIndex) && (index.canScan()))
            {
              cursor = index.find(sysSession, null, null);
              while (cursor.next()) {}
            }
          }
        }
      }
      Index scanIndex;
      Cursor cursor;
      this.recordPageReads = false;
      int target = 4;
      int temp = 0;
      int i = 0;
      for (int size = this.recordedPagesList.size(); i < size; i++)
      {
        this.log.checkpoint();
        writeBack();
        int source = ((Integer)this.recordedPagesList.get(i)).intValue();
        Page pageSource = getPage(source);
        if (pageSource.canMove())
        {
          for (;;)
          {
            Page pageTarget = getPage(++target);
            if ((pageTarget == null) || (pageTarget.canMove())) {
              break;
            }
          }
          if (target != source)
          {
            temp = getFirstFree(temp);
            if (temp == -1) {
              DbException.throwInternalError("no free page for defrag");
            }
            this.cache.clear();
            swap(source, target, temp);
            int index = this.recordedPagesIndex.get(target);
            if (index != -1)
            {
              this.recordedPagesList.set(index, Integer.valueOf(source));
              this.recordedPagesIndex.put(source, index);
            }
            this.recordedPagesList.set(i, Integer.valueOf(target));
            this.recordedPagesIndex.put(target, i);
          }
        }
      }
      this.recordedPagesList = null;
      this.recordedPagesIndex = null;
    }
    checkpoint();
    this.log.checkpoint();
    writeIndexRowCounts();
    this.log.checkpoint();
    writeBack();
    commit(this.pageStoreSession);
    writeBack();
    this.log.checkpoint();
    
    this.log.free();
    
    this.recoveryRunning = true;
    try
    {
      setLogFirstPage(++this.logKey, 0, 0);
    }
    finally
    {
      this.recoveryRunning = false;
    }
    writeBack();
    for (int i = getFreeListId(this.pageCount); i >= 0; i--)
    {
      lastUsed = getFreeList(i).getLastUsed();
      if (lastUsed != -1) {
        break;
      }
    }
    int newPageCount = lastUsed + 1;
    if (newPageCount < this.pageCount) {
      this.freed.set(newPageCount, this.pageCount, false);
    }
    this.pageCount = newPageCount;
    
    this.freeLists.clear();
    this.trace.debug("pageCount: " + this.pageCount);
    long newLength = this.pageCount << this.pageSizeShift;
    if (this.file.length() != newLength)
    {
      this.file.setLength(newLength);
      this.writeCount += 1L;
    }
  }
  
  private int getFirstFree(int start)
  {
    int free = -1;
    for (int id = getFreeListId(start); start < this.pageCount; id++)
    {
      free = getFreeList(id).getFirstFree(start);
      if (free != -1) {
        break;
      }
    }
    return free;
  }
  
  private void swap(int a, int b, int free)
  {
    if ((a < 5) || (b < 5))
    {
      System.out.println(isUsed(a) + " " + isUsed(b));
      DbException.throwInternalError("can't swap " + a + " and " + b);
    }
    Page f = (Page)this.cache.get(free);
    if (f != null) {
      DbException.throwInternalError("not free: " + f);
    }
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("swap " + a + " and " + b + " via " + free);
    }
    Page pageA = null;
    if (isUsed(a))
    {
      pageA = getPage(a);
      if (pageA != null) {
        pageA.moveTo(this.pageStoreSession, free);
      }
      free(a);
    }
    if (free != b)
    {
      if (isUsed(b))
      {
        Page pageB = getPage(b);
        if (pageB != null) {
          pageB.moveTo(this.pageStoreSession, a);
        }
        free(b);
      }
      if (pageA != null)
      {
        f = getPage(free);
        if (f != null) {
          f.moveTo(this.pageStoreSession, b);
        }
        free(free);
      }
    }
  }
  
  private boolean compact(int full, int free)
  {
    if ((full < 5) || (free == -1) || (free >= full) || (!isUsed(full))) {
      return false;
    }
    Page f = (Page)this.cache.get(free);
    if (f != null) {
      DbException.throwInternalError("not free: " + f);
    }
    Page p = getPage(full);
    if (p == null)
    {
      freePage(full);
    }
    else if (((p instanceof PageStreamData)) || ((p instanceof PageStreamTrunk)))
    {
      if (p.getPos() < this.log.getMinPageId()) {
        freePage(full);
      }
    }
    else
    {
      if (this.trace.isDebugEnabled()) {
        this.trace.debug("move " + p.getPos() + " to " + free);
      }
      try
      {
        p.moveTo(this.pageStoreSession, free);
      }
      finally
      {
        this.changeCount += 1L;
        if ((SysProperties.CHECK) && (this.changeCount < 0L)) {
          throw DbException.throwInternalError("changeCount has wrapped");
        }
      }
    }
    return true;
  }
  
  public synchronized Page getPage(int pageId)
  {
    Page p = (Page)this.cache.get(pageId);
    if (p != null) {
      return p;
    }
    Data data = createData();
    readPage(pageId, data);
    int type = data.readByte();
    if (type == 0) {
      return null;
    }
    data.readShortInt();
    data.readInt();
    if (!checksumTest(data.getBytes(), pageId, this.pageSize)) {
      throw DbException.get(90030, "wrong checksum");
    }
    switch (type & 0xFFFFFFEF)
    {
    case 6: 
      p = PageFreeList.read(this, data, pageId);
      break;
    case 1: 
      int indexId = data.readVarInt();
      PageIndex idx = (PageIndex)this.metaObjects.get(Integer.valueOf(indexId));
      if (idx == null) {
        throw DbException.get(90030, "index not found " + indexId);
      }
      if (!(idx instanceof PageDataIndex)) {
        throw DbException.get(90030, "not a data index " + indexId + " " + idx);
      }
      PageDataIndex index = (PageDataIndex)idx;
      if (this.statistics != null) {
        statisticsIncrement(index.getTable().getName() + "." + index.getName() + " read");
      }
      p = PageDataLeaf.read(index, data, pageId);
      break;
    case 2: 
      int indexId = data.readVarInt();
      PageIndex idx = (PageIndex)this.metaObjects.get(Integer.valueOf(indexId));
      if (idx == null) {
        throw DbException.get(90030, "index not found " + indexId);
      }
      if (!(idx instanceof PageDataIndex)) {
        throw DbException.get(90030, "not a data index " + indexId + " " + idx);
      }
      PageDataIndex index = (PageDataIndex)idx;
      if (this.statistics != null) {
        statisticsIncrement(index.getTable().getName() + "." + index.getName() + " read");
      }
      p = PageDataNode.read(index, data, pageId);
      break;
    case 3: 
      p = PageDataOverflow.read(this, data, pageId);
      if (this.statistics != null) {
        statisticsIncrement("overflow read");
      }
      break;
    case 4: 
      int indexId = data.readVarInt();
      PageIndex idx = (PageIndex)this.metaObjects.get(Integer.valueOf(indexId));
      if (idx == null) {
        throw DbException.get(90030, "index not found " + indexId);
      }
      if (!(idx instanceof PageBtreeIndex)) {
        throw DbException.get(90030, "not a btree index " + indexId + " " + idx);
      }
      PageBtreeIndex index = (PageBtreeIndex)idx;
      if (this.statistics != null) {
        statisticsIncrement(index.getTable().getName() + "." + index.getName() + " read");
      }
      p = PageBtreeLeaf.read(index, data, pageId);
      break;
    case 5: 
      int indexId = data.readVarInt();
      PageIndex idx = (PageIndex)this.metaObjects.get(Integer.valueOf(indexId));
      if (idx == null) {
        throw DbException.get(90030, "index not found " + indexId);
      }
      if (!(idx instanceof PageBtreeIndex)) {
        throw DbException.get(90030, "not a btree index " + indexId + " " + idx);
      }
      PageBtreeIndex index = (PageBtreeIndex)idx;
      if (this.statistics != null) {
        statisticsIncrement(index.getTable().getName() + "." + index.getName() + " read");
      }
      p = PageBtreeNode.read(index, data, pageId);
      break;
    case 7: 
      p = PageStreamTrunk.read(this, data, pageId);
      break;
    case 8: 
      p = PageStreamData.read(this, data, pageId);
      break;
    default: 
      throw DbException.get(90030, "page=" + pageId + " type=" + type);
    }
    this.cache.put(p);
    return p;
  }
  
  private int getFirstUncommittedSection()
  {
    this.trace.debug("getFirstUncommittedSection");
    Session[] sessions = this.database.getSessions(true);
    int firstUncommittedSection = this.log.getLogSectionId();
    for (Session session : sessions)
    {
      int firstUncommitted = session.getFirstUncommittedLog();
      if ((firstUncommitted != -1) && 
        (firstUncommitted < firstUncommittedSection)) {
        firstUncommittedSection = firstUncommitted;
      }
    }
    return firstUncommittedSection;
  }
  
  private void readStaticHeader()
  {
    this.file.seek(48L);
    Data page = Data.create(this.database, new byte[16]);
    
    this.file.readFully(page.getBytes(), 0, 16);
    
    this.readCount += 1L;
    setPageSize(page.readInt());
    int writeVersion = page.readByte();
    int readVersion = page.readByte();
    if (readVersion > 3) {
      throw DbException.get(90048, this.fileName);
    }
    if (writeVersion > 3)
    {
      close();
      this.database.setReadOnly(true);
      this.accessMode = "r";
      this.file = this.database.openFile(this.fileName, this.accessMode, true);
    }
  }
  
  private void readVariableHeader()
  {
    Data page = createData();
    for (int i = 1;; i++)
    {
      if (i == 3) {
        throw DbException.get(90030, this.fileName);
      }
      page.reset();
      readPage(i, page);
      CRC32 crc = new CRC32();
      crc.update(page.getBytes(), 4, this.pageSize - 4);
      int expected = (int)crc.getValue();
      int got = page.readInt();
      if (expected == got)
      {
        this.writeCountBase = page.readLong();
        this.logKey = page.readInt();
        this.logFirstTrunkPage = page.readInt();
        this.logFirstDataPage = page.readInt();
        break;
      }
    }
  }
  
  public void setPageSize(int size)
  {
    if ((size < 64) || (size > 32768)) {
      throw DbException.get(90030, this.fileName + " pageSize: " + size);
    }
    boolean good = false;
    int shift = 0;
    for (int i = 1; i <= size;)
    {
      if (size == i)
      {
        good = true;
        break;
      }
      shift++;
      i += i;
    }
    if (!good) {
      throw DbException.get(90030, this.fileName);
    }
    this.pageSize = size;
    this.emptyPage = createData();
    this.pageSizeShift = shift;
  }
  
  private void writeStaticHeader()
  {
    Data page = Data.create(this.database, new byte[this.pageSize - 48]);
    page.writeInt(this.pageSize);
    page.writeByte((byte)3);
    page.writeByte((byte)3);
    this.file.seek(48L);
    this.file.write(page.getBytes(), 0, this.pageSize - 48);
    this.writeCount += 1L;
  }
  
  void setLogFirstPage(int logKey, int trunkPageId, int dataPageId)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("setLogFirstPage key: " + logKey + " trunk: " + trunkPageId + " data: " + dataPageId);
    }
    this.logKey = logKey;
    this.logFirstTrunkPage = trunkPageId;
    this.logFirstDataPage = dataPageId;
    writeVariableHeader();
  }
  
  private void writeVariableHeader()
  {
    this.trace.debug("writeVariableHeader");
    if (this.logMode == 2) {
      this.file.sync();
    }
    Data page = createData();
    page.writeInt(0);
    page.writeLong(getWriteCountTotal());
    page.writeInt(this.logKey);
    page.writeInt(this.logFirstTrunkPage);
    page.writeInt(this.logFirstDataPage);
    CRC32 crc = new CRC32();
    crc.update(page.getBytes(), 4, this.pageSize - 4);
    page.setInt(0, (int)crc.getValue());
    this.file.seek(this.pageSize);
    this.file.write(page.getBytes(), 0, this.pageSize);
    this.file.seek(this.pageSize + this.pageSize);
    this.file.write(page.getBytes(), 0, this.pageSize);
  }
  
  public synchronized void close()
  {
    this.trace.debug("close");
    if (this.log != null)
    {
      this.log.close();
      this.log = null;
    }
    if (this.file != null) {
      try
      {
        this.file.releaseLock();
        this.file.close();
      }
      finally
      {
        this.file = null;
      }
    }
  }
  
  public synchronized void flushLog()
  {
    if (this.file != null) {
      this.log.flush();
    }
  }
  
  public synchronized void sync()
  {
    if (this.file != null)
    {
      this.log.flush();
      this.file.sync();
    }
  }
  
  public Trace getTrace()
  {
    return this.trace;
  }
  
  public synchronized void writeBack(CacheObject obj)
  {
    Page record = (Page)obj;
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("writeBack " + record);
    }
    record.write();
    record.setChanged(false);
  }
  
  public synchronized void logUndo(Page page, Data old)
  {
    if (this.logMode == 0) {
      return;
    }
    checkOpen();
    this.database.checkWritingAllowed();
    if (!this.recoveryRunning)
    {
      int pos = page.getPos();
      if (!this.log.getUndo(pos))
      {
        if (old == null) {
          old = readPage(pos);
        }
        openForWriting();
        this.log.addUndo(pos, old);
      }
    }
  }
  
  public synchronized void update(Page page)
  {
    if ((this.trace.isDebugEnabled()) && 
      (!page.isChanged())) {
      this.trace.debug("updateRecord " + page.toString());
    }
    checkOpen();
    this.database.checkWritingAllowed();
    page.setChanged(true);
    int pos = page.getPos();
    if ((SysProperties.CHECK) && (!this.recoveryRunning)) {
      if (this.logMode != 0) {
        this.log.addUndo(pos, null);
      }
    }
    allocatePage(pos);
    this.cache.update(pos, page);
  }
  
  private int getFreeListId(int pageId)
  {
    return (pageId - 3) / this.freeListPagesPerList;
  }
  
  private PageFreeList getFreeListForPage(int pageId)
  {
    return getFreeList(getFreeListId(pageId));
  }
  
  private PageFreeList getFreeList(int i)
  {
    PageFreeList list = null;
    if (i < this.freeLists.size())
    {
      list = (PageFreeList)this.freeLists.get(i);
      if (list != null) {
        return list;
      }
    }
    int p = 3 + i * this.freeListPagesPerList;
    while (p >= this.pageCount) {
      increaseFileSize();
    }
    if (p < this.pageCount) {
      list = (PageFreeList)getPage(p);
    }
    if (list == null)
    {
      list = PageFreeList.create(this, p);
      this.cache.put(list);
    }
    while (this.freeLists.size() <= i) {
      this.freeLists.add(null);
    }
    this.freeLists.set(i, list);
    return list;
  }
  
  private void freePage(int pageId)
  {
    int index = getFreeListId(pageId);
    PageFreeList list = getFreeList(index);
    this.firstFreeListIndex = Math.min(index, this.firstFreeListIndex);
    list.free(pageId);
  }
  
  void allocatePage(int pageId)
  {
    PageFreeList list = getFreeListForPage(pageId);
    list.allocate(pageId);
  }
  
  private boolean isUsed(int pageId)
  {
    return getFreeListForPage(pageId).isUsed(pageId);
  }
  
  void allocatePages(IntArray list, int pagesToAllocate, BitField exclude, int after)
  {
    list.ensureCapacity(list.size() + pagesToAllocate);
    for (int i = 0; i < pagesToAllocate; i++)
    {
      int page = allocatePage(exclude, after);
      after = page;
      list.add(page);
    }
  }
  
  public synchronized int allocatePage()
  {
    openForWriting();
    int pos = allocatePage(null, 0);
    if ((!this.recoveryRunning) && 
      (this.logMode != 0)) {
      this.log.addUndo(pos, this.emptyPage);
    }
    return pos;
  }
  
  private int allocatePage(BitField exclude, int first)
  {
    int page;
    for (int i = this.firstFreeListIndex;; i++)
    {
      PageFreeList list = getFreeList(i);
      page = list.allocate(exclude, first);
      if (page >= 0)
      {
        this.firstFreeListIndex = i;
        break;
      }
    }
    while (page >= this.pageCount) {
      increaseFileSize();
    }
    if (this.trace.isDebugEnabled()) {}
    return page;
  }
  
  private void increaseFileSize()
  {
    int increment = 1048576 / this.pageSize;
    int percent = this.pageCount * 35 / 100;
    if (increment < percent) {
      increment = (1 + percent / increment) * increment;
    }
    int max = this.database.getSettings().pageStoreMaxGrowth;
    if (max < increment) {
      increment = max;
    }
    increaseFileSize(increment);
  }
  
  private void increaseFileSize(int increment)
  {
    for (int i = this.pageCount; i < this.pageCount + increment; i++) {
      this.freed.set(i);
    }
    this.pageCount += increment;
    long newLength = this.pageCount << this.pageSizeShift;
    this.file.setLength(newLength);
    this.writeCount += 1L;
    this.fileLength = newLength;
  }
  
  public synchronized void free(int pageId)
  {
    free(pageId, true);
  }
  
  void free(int pageId, boolean undo)
  {
    if (this.trace.isDebugEnabled()) {}
    this.cache.remove(pageId);
    if ((SysProperties.CHECK) && (!this.recoveryRunning) && (undo)) {
      if (this.logMode != 0) {
        this.log.addUndo(pageId, null);
      }
    }
    freePage(pageId);
    if (this.recoveryRunning)
    {
      writePage(pageId, createData());
      if ((this.reservedPages != null) && (this.reservedPages.containsKey(Integer.valueOf(pageId))))
      {
        int latestPos = ((Integer)this.reservedPages.get(Integer.valueOf(pageId))).intValue();
        if (latestPos > this.log.getLogPos()) {
          allocatePage(pageId);
        }
      }
    }
  }
  
  void freeUnused(int pageId)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("freeUnused " + pageId);
    }
    this.cache.remove(pageId);
    freePage(pageId);
    this.freed.set(pageId);
  }
  
  public Data createData()
  {
    return Data.create(this.database, new byte[this.pageSize]);
  }
  
  public synchronized Data readPage(int pos)
  {
    Data page = createData();
    readPage(pos, page);
    return page;
  }
  
  void readPage(int pos, Data page)
  {
    if ((this.recordPageReads) && 
      (pos >= 5) && (this.recordedPagesIndex.get(pos) == -1))
    {
      this.recordedPagesIndex.put(pos, this.recordedPagesList.size());
      this.recordedPagesList.add(Integer.valueOf(pos));
    }
    if ((pos < 0) || (pos >= this.pageCount)) {
      throw DbException.get(90030, pos + " of " + this.pageCount);
    }
    this.file.seek(pos << this.pageSizeShift);
    this.file.readFully(page.getBytes(), 0, this.pageSize);
    this.readCount += 1L;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public int getPageCount()
  {
    return this.pageCount;
  }
  
  public synchronized void writePage(int pageId, Data data)
  {
    if (pageId <= 0) {
      DbException.throwInternalError("write to page " + pageId);
    }
    byte[] bytes = data.getBytes();
    if (SysProperties.CHECK)
    {
      boolean shouldBeFreeList = (pageId - 3) % this.freeListPagesPerList == 0;
      
      boolean isFreeList = bytes[0] == 6;
      if ((bytes[0] != 0) && (shouldBeFreeList != isFreeList)) {
        throw DbException.throwInternalError();
      }
    }
    checksumSet(bytes, pageId);
    this.file.seek(pageId << this.pageSizeShift);
    this.file.write(bytes, 0, this.pageSize);
    this.writeCount += 1L;
  }
  
  public synchronized void removeFromCache(int pageId)
  {
    this.cache.remove(pageId);
  }
  
  Database getDatabase()
  {
    return this.database;
  }
  
  private boolean recover()
  {
    this.trace.debug("log recover");
    this.recoveryRunning = true;
    boolean isEmpty = true;
    isEmpty &= this.log.recover(0);
    Iterator i$;
    if (this.reservedPages != null) {
      for (i$ = this.reservedPages.keySet().iterator(); i$.hasNext();)
      {
        int r = ((Integer)i$.next()).intValue();
        if (this.trace.isDebugEnabled()) {
          this.trace.debug("reserve " + r);
        }
        allocatePage(r);
      }
    }
    isEmpty &= this.log.recover(1);
    openMetaIndex();
    readMetaData();
    isEmpty &= this.log.recover(2);
    boolean setReadOnly = false;
    if (!this.database.isReadOnly()) {
      if (this.log.getInDoubtTransactions().size() == 0)
      {
        this.log.recoverEnd();
        int firstUncommittedSection = getFirstUncommittedSection();
        this.log.removeUntil(firstUncommittedSection);
      }
      else
      {
        setReadOnly = true;
      }
    }
    PageDataIndex systemTable = (PageDataIndex)this.metaObjects.get(Integer.valueOf(0));
    this.isNew = (systemTable == null);
    for (PageIndex index : this.metaObjects.values()) {
      if (index.getTable().isTemporary())
      {
        if (this.tempObjects == null) {
          this.tempObjects = New.hashMap();
        }
        this.tempObjects.put(Integer.valueOf(index.getId()), index);
      }
      else
      {
        index.close(this.pageStoreSession);
      }
    }
    allocatePage(4);
    writeIndexRowCounts();
    this.recoveryRunning = false;
    this.reservedPages = null;
    
    writeBack();
    
    this.cache.clear();
    this.freeLists.clear();
    
    this.metaObjects.clear();
    this.metaObjects.put(Integer.valueOf(-1), this.metaIndex);
    if (setReadOnly) {
      this.database.setReadOnly(true);
    }
    this.trace.debug("log recover done");
    return isEmpty;
  }
  
  public synchronized void logAddOrRemoveRow(Session session, int tableId, Row row, boolean add)
  {
    if ((this.logMode != 0) && 
      (!this.recoveryRunning)) {
      this.log.logAddOrRemoveRow(session, tableId, row, add);
    }
  }
  
  public synchronized void commit(Session session)
  {
    checkOpen();
    openForWriting();
    this.log.commit(session.getId());
    long size = this.log.getSize();
    if (size - this.logSizeBase > this.maxLogSize / 2L)
    {
      int firstSection = this.log.getLogFirstSectionId();
      checkpoint();
      int newSection = this.log.getLogSectionId();
      if (newSection - firstSection <= 2) {
        return;
      }
      long newSize = this.log.getSize();
      if ((newSize < size) || (size < this.maxLogSize))
      {
        this.ignoreBigLog = false;
        return;
      }
      if (!this.ignoreBigLog)
      {
        this.ignoreBigLog = true;
        this.trace.error(null, "Transaction log could not be truncated; size: " + newSize / 1024L / 1024L + " MB");
      }
      this.logSizeBase = this.log.getSize();
    }
  }
  
  public synchronized void prepareCommit(Session session, String transaction)
  {
    this.log.prepareCommit(session, transaction);
  }
  
  public boolean isNew()
  {
    return this.isNew;
  }
  
  void allocateIfIndexRoot(int logPos, int tableId, Row row)
  {
    if (tableId == -1)
    {
      int rootPageId = row.getValue(3).getInt();
      if (this.reservedPages == null) {
        this.reservedPages = New.hashMap();
      }
      this.reservedPages.put(Integer.valueOf(rootPageId), Integer.valueOf(logPos));
    }
  }
  
  void redoDelete(int tableId, long key)
  {
    Index index = (Index)this.metaObjects.get(Integer.valueOf(tableId));
    PageDataIndex scan = (PageDataIndex)index;
    Row row = scan.getRowWithKey(key);
    if ((row == null) || (row.getKey() != key))
    {
      this.trace.error(null, "Entry not found: " + key + " found instead: " + row + " - ignoring");
      
      return;
    }
    redo(tableId, row, false);
  }
  
  void redo(int tableId, Row row, boolean add)
  {
    if (tableId == -1) {
      if (add) {
        addMeta(row, this.pageStoreSession, true);
      } else {
        removeMeta(row);
      }
    }
    Index index = (Index)this.metaObjects.get(Integer.valueOf(tableId));
    if (index == null) {
      throw DbException.throwInternalError("Table not found: " + tableId + " " + row + " " + add);
    }
    Table table = index.getTable();
    if (add) {
      table.addRow(this.pageStoreSession, row);
    } else {
      table.removeRow(this.pageStoreSession, row);
    }
  }
  
  void redoTruncate(int tableId)
  {
    Index index = (Index)this.metaObjects.get(Integer.valueOf(tableId));
    Table table = index.getTable();
    table.truncate(this.pageStoreSession);
  }
  
  private void openMetaIndex()
  {
    CreateTableData data = new CreateTableData();
    ArrayList<Column> cols = data.columns;
    cols.add(new Column("ID", 4));
    cols.add(new Column("TYPE", 4));
    cols.add(new Column("PARENT", 4));
    cols.add(new Column("HEAD", 4));
    cols.add(new Column("OPTIONS", 13));
    cols.add(new Column("COLUMNS", 13));
    this.metaSchema = new Schema(this.database, 0, "", null, true);
    data.schema = this.metaSchema;
    data.tableName = "PAGE_INDEX";
    data.id = -1;
    data.temporary = false;
    data.persistData = true;
    data.persistIndexes = true;
    data.create = false;
    data.session = this.pageStoreSession;
    this.metaTable = new RegularTable(data);
    this.metaIndex = ((PageDataIndex)this.metaTable.getScanIndex(this.pageStoreSession));
    
    this.metaObjects.clear();
    this.metaObjects.put(Integer.valueOf(-1), this.metaIndex);
  }
  
  private void readMetaData()
  {
    Cursor cursor = this.metaIndex.find(this.pageStoreSession, null, null);
    while (cursor.next())
    {
      Row row = cursor.get();
      int type = row.getValue(1).getInt();
      if (type == 0) {
        addMeta(row, this.pageStoreSession, false);
      }
    }
    cursor = this.metaIndex.find(this.pageStoreSession, null, null);
    while (cursor.next())
    {
      Row row = cursor.get();
      int type = row.getValue(1).getInt();
      if (type != 0) {
        addMeta(row, this.pageStoreSession, false);
      }
    }
  }
  
  private void removeMeta(Row row)
  {
    int id = row.getValue(0).getInt();
    PageIndex index = (PageIndex)this.metaObjects.get(Integer.valueOf(id));
    index.getTable().removeIndex(index);
    if (((index instanceof PageBtreeIndex)) || ((index instanceof PageDelegateIndex))) {
      if (index.isTemporary()) {
        this.pageStoreSession.removeLocalTempTableIndex(index);
      } else {
        index.getSchema().remove(index);
      }
    }
    index.remove(this.pageStoreSession);
    this.metaObjects.remove(Integer.valueOf(id));
  }
  
  private void addMeta(Row row, Session session, boolean redo)
  {
    int id = row.getValue(0).getInt();
    int type = row.getValue(1).getInt();
    int parent = row.getValue(2).getInt();
    int rootPageId = row.getValue(3).getInt();
    String[] options = StringUtils.arraySplit(row.getValue(4).getString(), ',', false);
    
    String columnList = row.getValue(5).getString();
    String[] columns = StringUtils.arraySplit(columnList, ',', false);
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("addMeta id=" + id + " type=" + type + " root=" + rootPageId + " parent=" + parent + " columns=" + columnList);
    }
    if ((redo) && (rootPageId != 0))
    {
      writePage(rootPageId, createData());
      allocatePage(rootPageId);
    }
    this.metaRootPageId.put(id, rootPageId);
    Index meta;
    Index meta;
    if (type == 0)
    {
      CreateTableData data = new CreateTableData();
      if ((SysProperties.CHECK) && 
        (columns == null)) {
        throw DbException.throwInternalError(row.toString());
      }
      int i = 0;
      for (int len = columns.length; i < len; i++)
      {
        Column col = new Column("C" + i, 4);
        data.columns.add(col);
      }
      data.schema = this.metaSchema;
      data.tableName = ("T" + id);
      data.id = id;
      data.temporary = options[2].equals("temp");
      data.persistData = true;
      data.persistIndexes = true;
      data.create = false;
      data.session = session;
      RegularTable table = new RegularTable(data);
      boolean binaryUnsigned = SysProperties.SORT_BINARY_UNSIGNED;
      if (options.length > 3) {
        binaryUnsigned = Boolean.parseBoolean(options[3]);
      }
      CompareMode mode = CompareMode.getInstance(options[0], Integer.parseInt(options[1]), binaryUnsigned);
      
      table.setCompareMode(mode);
      meta = table.getScanIndex(session);
    }
    else
    {
      Index p = (Index)this.metaObjects.get(Integer.valueOf(parent));
      if (p == null) {
        throw DbException.get(90030, "Table not found:" + parent + " for " + row + " meta:" + this.metaObjects);
      }
      RegularTable table = (RegularTable)p.getTable();
      Column[] tableCols = table.getColumns();
      int len = columns.length;
      IndexColumn[] cols = new IndexColumn[len];
      for (int i = 0; i < len; i++)
      {
        String c = columns[i];
        IndexColumn ic = new IndexColumn();
        int idx = c.indexOf('/');
        if (idx >= 0)
        {
          String s = c.substring(idx + 1);
          ic.sortType = Integer.parseInt(s);
          c = c.substring(0, idx);
        }
        Column column = tableCols[Integer.parseInt(c)];
        ic.column = column;
        cols[i] = ic;
      }
      IndexType indexType;
      if (options[3].equals("d"))
      {
        IndexType indexType = IndexType.createPrimaryKey(true, false);
        Column[] tableColumns = table.getColumns();
        for (IndexColumn indexColumn : cols) {
          tableColumns[indexColumn.column.getColumnId()].setNullable(false);
        }
      }
      else
      {
        indexType = IndexType.createNonUnique(true);
      }
      meta = table.addIndex(session, "I" + id, id, cols, indexType, false, null);
    }
    PageIndex index;
    PageIndex index;
    if ((meta instanceof MultiVersionIndex)) {
      index = (PageIndex)((MultiVersionIndex)meta).getBaseIndex();
    } else {
      index = (PageIndex)meta;
    }
    this.metaObjects.put(Integer.valueOf(id), index);
  }
  
  public synchronized void addIndex(PageIndex index)
  {
    this.metaObjects.put(Integer.valueOf(index.getId()), index);
  }
  
  public void addMeta(PageIndex index, Session session)
  {
    Table table = index.getTable();
    if ((SysProperties.CHECK) && 
      (!table.isTemporary())) {
      synchronized (this.database)
      {
        synchronized (this)
        {
          this.database.verifyMetaLocked(session);
        }
      }
    }
    synchronized (this)
    {
      int type = (index instanceof PageDataIndex) ? 0 : 1;
      
      IndexColumn[] columns = index.getIndexColumns();
      StatementBuilder buff = new StatementBuilder();
      for (IndexColumn col : columns)
      {
        buff.appendExceptFirst(",");
        int id = col.column.getColumnId();
        buff.append(id);
        int sortType = col.sortType;
        if (sortType != 0)
        {
          buff.append('/');
          buff.append(sortType);
        }
      }
      String columnList = buff.toString();
      CompareMode mode = table.getCompareMode();
      String options = mode.getName() + "," + mode.getStrength() + ",";
      if (table.isTemporary()) {
        options = options + "temp";
      }
      options = options + ",";
      if ((index instanceof PageDelegateIndex)) {
        options = options + "d";
      }
      options = options + "," + mode.isBinaryUnsigned();
      Row row = this.metaTable.getTemplateRow();
      row.setValue(0, ValueInt.get(index.getId()));
      row.setValue(1, ValueInt.get(type));
      row.setValue(2, ValueInt.get(table.getId()));
      row.setValue(3, ValueInt.get(index.getRootPageId()));
      row.setValue(4, ValueString.get(options));
      row.setValue(5, ValueString.get(columnList));
      row.setKey(index.getId() + 1);
      this.metaIndex.add(session, row);
    }
  }
  
  public void removeMeta(Index index, Session session)
  {
    if ((SysProperties.CHECK) && 
      (!index.getTable().isTemporary())) {
      synchronized (this.database)
      {
        synchronized (this)
        {
          this.database.verifyMetaLocked(session);
        }
      }
    }
    synchronized (this)
    {
      if (!this.recoveryRunning)
      {
        removeMetaIndex(index, session);
        this.metaObjects.remove(Integer.valueOf(index.getId()));
      }
    }
  }
  
  private void removeMetaIndex(Index index, Session session)
  {
    int key = index.getId() + 1;
    Row row = this.metaIndex.getRow(session, key);
    if (row.getKey() != key) {
      throw DbException.get(90030, "key: " + key + " index: " + index + " table: " + index.getTable() + " row: " + row);
    }
    this.metaIndex.remove(session, row);
  }
  
  public void setMaxLogSize(long maxSize)
  {
    this.maxLogSize = maxSize;
  }
  
  public synchronized void setInDoubtTransactionState(int sessionId, int pageId, boolean commit)
  {
    boolean old = this.database.isReadOnly();
    try
    {
      this.database.setReadOnly(false);
      this.log.setInDoubtTransactionState(sessionId, pageId, commit);
    }
    finally
    {
      this.database.setReadOnly(old);
    }
  }
  
  public ArrayList<InDoubtTransaction> getInDoubtTransactions()
  {
    return this.log.getInDoubtTransactions();
  }
  
  public boolean isRecoveryRunning()
  {
    return this.recoveryRunning;
  }
  
  private void checkOpen()
  {
    if (this.file == null) {
      throw DbException.get(90098);
    }
  }
  
  public long getWriteCountTotal()
  {
    return this.writeCount + this.writeCountBase;
  }
  
  public long getWriteCount()
  {
    return this.writeCount;
  }
  
  public long getReadCount()
  {
    return this.readCount;
  }
  
  public synchronized void logTruncate(Session session, int tableId)
  {
    if (!this.recoveryRunning)
    {
      openForWriting();
      this.log.logTruncate(session, tableId);
    }
  }
  
  public int getRootPageId(int indexId)
  {
    return this.metaRootPageId.get(indexId);
  }
  
  public Cache getCache()
  {
    return this.cache;
  }
  
  private void checksumSet(byte[] d, int pageId)
  {
    int ps = this.pageSize;
    int type = d[0];
    if (type == 0) {
      return;
    }
    int s1 = 255 + (type & 0xFF);int s2 = 255 + s1;
    s2 += s1 += (d[6] & 0xFF);
    s2 += s1 += (d[((ps >> 1) - 1)] & 0xFF);
    s2 += s1 += (d[(ps >> 1)] & 0xFF);
    s2 += s1 += (d[(ps - 2)] & 0xFF);
    s2 += s1 += (d[(ps - 1)] & 0xFF);
    d[1] = ((byte)((s1 & 0xFF) + (s1 >> 8) ^ pageId));
    d[2] = ((byte)((s2 & 0xFF) + (s2 >> 8) ^ pageId >> 8));
  }
  
  public static boolean checksumTest(byte[] d, int pageId, int pageSize)
  {
    int ps = pageSize;
    int s1 = 255 + (d[0] & 0xFF);int s2 = 255 + s1;
    s2 += s1 += (d[6] & 0xFF);
    s2 += s1 += (d[((ps >> 1) - 1)] & 0xFF);
    s2 += s1 += (d[(ps >> 1)] & 0xFF);
    s2 += s1 += (d[(ps - 2)] & 0xFF);
    s2 += s1 += (d[(ps - 1)] & 0xFF);
    if ((d[1] != (byte)((s1 & 0xFF) + (s1 >> 8) ^ pageId)) || (d[2] != (byte)((s2 & 0xFF) + (s2 >> 8) ^ pageId >> 8))) {
      return false;
    }
    return true;
  }
  
  public void incrementChangeCount()
  {
    this.changeCount += 1L;
    if ((SysProperties.CHECK) && (this.changeCount < 0L)) {
      throw DbException.throwInternalError("changeCount has wrapped");
    }
  }
  
  public long getChangeCount()
  {
    return this.changeCount;
  }
  
  public void setLogMode(int logMode)
  {
    this.logMode = logMode;
  }
  
  public int getLogMode()
  {
    return this.logMode;
  }
  
  public void setLockFile(boolean lockFile)
  {
    this.lockFile = lockFile;
  }
  
  public BitField getObjectIds()
  {
    BitField f = new BitField();
    Cursor cursor = this.metaIndex.find(this.pageStoreSession, null, null);
    while (cursor.next())
    {
      Row row = cursor.get();
      int id = row.getValue(0).getInt();
      if (id > 0) {
        f.set(id);
      }
    }
    return f;
  }
  
  public Session getPageStoreSession()
  {
    return this.pageStoreSession;
  }
  
  public synchronized void setBackup(boolean start)
  {
    this.backupLevel += (start ? 1 : -1);
  }
}
