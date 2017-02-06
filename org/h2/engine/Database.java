package org.h2.engine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.h2.api.DatabaseEventListener;
import org.h2.api.JavaObjectSerializer;
import org.h2.command.ddl.CreateTableData;
import org.h2.command.dml.SetTypes;
import org.h2.constraint.Constraint;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceSystem;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.db.MVTableEngine;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.schema.Sequence;
import org.h2.schema.TriggerObject;
import org.h2.store.DataHandler;
import org.h2.store.FileLock;
import org.h2.store.FileStore;
import org.h2.store.InDoubtTransaction;
import org.h2.store.LobStorageBackend;
import org.h2.store.LobStorageInterface;
import org.h2.store.LobStorageMap;
import org.h2.store.PageStore;
import org.h2.store.WriterThread;
import org.h2.store.fs.FileUtils;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.MetaTable;
import org.h2.table.Table;
import org.h2.table.TableLinkConnection;
import org.h2.table.TableView;
import org.h2.tools.DeleteDbFiles;
import org.h2.tools.Server;
import org.h2.util.BitField;
import org.h2.util.Cache;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.NetUtils;
import org.h2.util.New;
import org.h2.util.SmallLRUCache;
import org.h2.util.SourceCompiler;
import org.h2.util.StringUtils;
import org.h2.util.TempFileDeleter;
import org.h2.util.Utils;
import org.h2.value.CaseInsensitiveMap;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueInt;

public class Database
  implements DataHandler
{
  private static int initialPowerOffCount;
  private static final String SYSTEM_USER_NAME = "DBA";
  private final boolean persistent;
  private final String databaseName;
  private final String databaseShortName;
  private final String databaseURL;
  private final String cipher;
  private final byte[] filePasswordHash;
  private final byte[] fileEncryptionKey;
  private final HashMap<String, Role> roles = New.hashMap();
  private final HashMap<String, User> users = New.hashMap();
  private final HashMap<String, Setting> settings = New.hashMap();
  private final HashMap<String, Schema> schemas = New.hashMap();
  private final HashMap<String, Right> rights = New.hashMap();
  private final HashMap<String, UserDataType> userDataTypes = New.hashMap();
  private final HashMap<String, UserAggregate> aggregates = New.hashMap();
  private final HashMap<String, Comment> comments = New.hashMap();
  private final Set<Session> userSessions = Collections.synchronizedSet(new HashSet());
  private Session exclusiveSession;
  private final BitField objectIds = new BitField();
  private final Object lobSyncObject = new Object();
  private Schema mainSchema;
  private Schema infoSchema;
  private int nextSessionId;
  private int nextTempTableId;
  private User systemUser;
  private Session systemSession;
  private Session lobSession;
  private Table meta;
  private Index metaIdIndex;
  private FileLock lock;
  private WriterThread writer;
  private boolean starting;
  private TraceSystem traceSystem;
  private Trace trace;
  private final int fileLockMethod;
  private Role publicRole;
  private long modificationDataId;
  private long modificationMetaId;
  private CompareMode compareMode;
  private String cluster = "''";
  private boolean readOnly;
  private int writeDelay = 500;
  private DatabaseEventListener eventListener;
  private int maxMemoryRows = SysProperties.MAX_MEMORY_ROWS;
  private int maxMemoryUndo = 50000;
  private int lockMode = 3;
  private int maxLengthInplaceLob;
  private int allowLiterals = 2;
  private int powerOffCount = initialPowerOffCount;
  private int closeDelay;
  private DatabaseCloser delayedCloser;
  private volatile boolean closing;
  private boolean ignoreCase;
  private boolean deleteFilesOnDisconnect;
  private String lobCompressionAlgorithm;
  private boolean optimizeReuseResults = true;
  private final String cacheType;
  private final String accessModeData;
  private boolean referentialIntegrity = true;
  private boolean multiVersion;
  private DatabaseCloser closeOnExit;
  private Mode mode = Mode.getInstance("REGULAR");
  private boolean multiThreaded;
  private int maxOperationMemory = 100000;
  private SmallLRUCache<String, String[]> lobFileListCache;
  private final boolean autoServerMode;
  private final int autoServerPort;
  private Server server;
  private HashMap<TableLinkConnection, TableLinkConnection> linkConnections;
  private final TempFileDeleter tempFileDeleter = TempFileDeleter.getInstance();
  private PageStore pageStore;
  private Properties reconnectLastLock;
  private volatile long reconnectCheckNext;
  private volatile boolean reconnectChangePending;
  private volatile int checkpointAllowed;
  private volatile boolean checkpointRunning;
  private final Object reconnectSync = new Object();
  private int cacheSize;
  private int compactMode;
  private SourceCompiler compiler;
  private volatile boolean metaTablesInitialized;
  private boolean flushOnEachCommit;
  private LobStorageInterface lobStorage;
  private final int pageSize;
  private int defaultTableType = 0;
  private final DbSettings dbSettings;
  private final int reconnectCheckDelay;
  private int logMode;
  private MVTableEngine.Store mvStore;
  private int retentionTime;
  private DbException backgroundException;
  private JavaObjectSerializer javaObjectSerializer;
  private String javaObjectSerializerName;
  private volatile boolean javaObjectSerializerInitialized;
  private boolean queryStatistics;
  private QueryStatisticsData queryStatisticsData;
  
  public Database(ConnectionInfo ci, String cipher)
  {
    String name = ci.getName();
    this.dbSettings = ci.getDbSettings();
    this.reconnectCheckDelay = this.dbSettings.reconnectCheckDelay;
    this.compareMode = CompareMode.getInstance(null, 0);
    this.persistent = ci.isPersistent();
    this.filePasswordHash = ci.getFilePasswordHash();
    this.fileEncryptionKey = ci.getFileEncryptionKey();
    this.databaseName = name;
    this.databaseShortName = parseDatabaseShortName();
    this.maxLengthInplaceLob = 256;
    this.cipher = cipher;
    String lockMethodName = ci.getProperty("FILE_LOCK", null);
    this.accessModeData = StringUtils.toLowerEnglish(ci.getProperty("ACCESS_MODE_DATA", "rw"));
    
    this.autoServerMode = ci.getProperty("AUTO_SERVER", false);
    this.autoServerPort = ci.getProperty("AUTO_SERVER_PORT", 0);
    int defaultCacheSize = Utils.scaleForAvailableMemory(65536);
    
    this.cacheSize = ci.getProperty("CACHE_SIZE", defaultCacheSize);
    
    this.pageSize = ci.getProperty("PAGE_SIZE", 4096);
    if ("r".equals(this.accessModeData)) {
      this.readOnly = true;
    }
    if ((this.dbSettings.mvStore) && (lockMethodName == null))
    {
      if (this.autoServerMode) {
        this.fileLockMethod = 1;
      } else {
        this.fileLockMethod = 4;
      }
    }
    else {
      this.fileLockMethod = FileLock.getFileLockMethod(lockMethodName);
    }
    if ((this.dbSettings.mvStore) && (this.fileLockMethod == 3)) {
      throw DbException.getUnsupportedException("MV_STORE combined with FILE_LOCK=SERIALIZED");
    }
    this.databaseURL = ci.getURL();
    String listener = ci.removeProperty("DATABASE_EVENT_LISTENER", null);
    if (listener != null)
    {
      listener = StringUtils.trim(listener, true, true, "'");
      setEventListenerClass(listener);
    }
    String modeName = ci.removeProperty("MODE", null);
    if (modeName != null) {
      this.mode = Mode.getInstance(modeName);
    }
    this.multiVersion = ci.getProperty("MVCC", this.dbSettings.mvStore);
    
    this.logMode = ci.getProperty("LOG", 2);
    
    this.javaObjectSerializerName = ci.getProperty("JAVA_OBJECT_SERIALIZER", null);
    
    this.multiThreaded = ci.getProperty("MULTI_THREADED", false);
    
    boolean closeAtVmShutdown = this.dbSettings.dbCloseOnExit;
    
    int traceLevelFile = ci.getIntProperty(10, 1);
    
    int traceLevelSystemOut = ci.getIntProperty(9, 0);
    
    this.cacheType = StringUtils.toUpperEnglish(ci.removeProperty("CACHE_TYPE", "LRU"));
    
    openDatabase(traceLevelFile, traceLevelSystemOut, closeAtVmShutdown);
  }
  
  private void openDatabase(int traceLevelFile, int traceLevelSystemOut, boolean closeAtVmShutdown)
  {
    try
    {
      open(traceLevelFile, traceLevelSystemOut);
      if (closeAtVmShutdown) {
        try
        {
          this.closeOnExit = new DatabaseCloser(this, 0, true);
          Runtime.getRuntime().addShutdownHook(this.closeOnExit);
        }
        catch (IllegalStateException e) {}catch (SecurityException e) {}
      }
    }
    catch (Throwable e)
    {
      if ((e instanceof OutOfMemoryError)) {
        e.fillInStackTrace();
      }
      if (this.traceSystem != null)
      {
        if ((e instanceof SQLException))
        {
          SQLException e2 = (SQLException)e;
          if (e2.getErrorCode() != 90020) {
            this.trace.error(e, "opening {0}", new Object[] { this.databaseName });
          }
        }
        this.traceSystem.close();
      }
      closeOpenFilesAndUnlock(false);
      throw DbException.convert(e);
    }
  }
  
  public static void setInitialPowerOffCount(int count)
  {
    initialPowerOffCount = count;
  }
  
  public void setPowerOffCount(int count)
  {
    if (this.powerOffCount == -1) {
      return;
    }
    this.powerOffCount = count;
  }
  
  public MVTableEngine.Store getMvStore()
  {
    return this.mvStore;
  }
  
  public void setMvStore(MVTableEngine.Store mvStore)
  {
    this.mvStore = mvStore;
    this.retentionTime = mvStore.getStore().getRetentionTime();
  }
  
  public boolean areEqual(Value a, Value b)
  {
    return a.compareTo(b, this.compareMode) == 0;
  }
  
  public int compare(Value a, Value b)
  {
    return a.compareTo(b, this.compareMode);
  }
  
  public int compareTypeSave(Value a, Value b)
  {
    return a.compareTypeSave(b, this.compareMode);
  }
  
  public long getModificationDataId()
  {
    return this.modificationDataId;
  }
  
  private synchronized boolean reconnectModified(boolean pending)
  {
    if ((this.readOnly) || (this.lock == null) || (this.fileLockMethod != 3)) {
      return true;
    }
    try
    {
      if (pending == this.reconnectChangePending)
      {
        long now = System.currentTimeMillis();
        if (now > this.reconnectCheckNext)
        {
          if (pending)
          {
            String pos = "" + this.pageStore.getWriteCountTotal();
            
            this.lock.setProperty("logPos", pos);
            this.lock.save();
          }
          this.reconnectCheckNext = (now + this.reconnectCheckDelay);
        }
        return true;
      }
      Properties old = this.lock.load();
      if (pending)
      {
        if (old.getProperty("changePending") != null) {
          return false;
        }
        this.trace.debug("wait before writing");
        Thread.sleep((int)(this.reconnectCheckDelay * 1.1D));
        Properties now = this.lock.load();
        if (!now.equals(old)) {
          return false;
        }
      }
      String pos = "" + this.pageStore.getWriteCountTotal();
      
      this.lock.setProperty("logPos", pos);
      if (pending) {
        this.lock.setProperty("changePending", "true-" + Math.random());
      } else {
        this.lock.setProperty("changePending", null);
      }
      this.reconnectCheckNext = (System.currentTimeMillis() + 2 * this.reconnectCheckDelay);
      
      old = this.lock.save();
      if (pending)
      {
        this.trace.debug("wait before writing again");
        Thread.sleep((int)(this.reconnectCheckDelay * 1.1D));
        Properties now = this.lock.load();
        if (!now.equals(old)) {
          return false;
        }
      }
      else
      {
        Thread.sleep(1L);
      }
      this.reconnectLastLock = old;
      this.reconnectChangePending = pending;
      this.reconnectCheckNext = (System.currentTimeMillis() + this.reconnectCheckDelay);
      
      return true;
    }
    catch (Exception e)
    {
      this.trace.error(e, "pending {0}", new Object[] { Boolean.valueOf(pending) });
    }
    return false;
  }
  
  public long getNextModificationDataId()
  {
    return ++this.modificationDataId;
  }
  
  public long getModificationMetaId()
  {
    return this.modificationMetaId;
  }
  
  public long getNextModificationMetaId()
  {
    this.modificationDataId += 1L;
    return this.modificationMetaId++;
  }
  
  public int getPowerOffCount()
  {
    return this.powerOffCount;
  }
  
  public void checkPowerOff()
  {
    if (this.powerOffCount == 0) {
      return;
    }
    if (this.powerOffCount > 1)
    {
      this.powerOffCount -= 1;
      return;
    }
    if (this.powerOffCount != -1) {
      try
      {
        this.powerOffCount = -1;
        stopWriter();
        if (this.mvStore != null) {
          this.mvStore.closeImmediately();
        }
        if (this.pageStore != null)
        {
          try
          {
            this.pageStore.close();
          }
          catch (DbException e) {}
          this.pageStore = null;
        }
        if (this.lock != null)
        {
          stopServer();
          if (this.fileLockMethod != 3) {
            this.lock.unlock();
          }
          this.lock = null;
        }
        if (this.traceSystem != null) {
          this.traceSystem.close();
        }
      }
      catch (DbException e)
      {
        DbException.traceThrowable(e);
      }
    }
    Engine.getInstance().close(this.databaseName);
    throw DbException.get(90098);
  }
  
  static boolean exists(String name)
  {
    if (FileUtils.exists(name + ".h2.db")) {
      return true;
    }
    return FileUtils.exists(name + ".mv.db");
  }
  
  public Trace getTrace(String module)
  {
    return this.traceSystem.getTrace(module);
  }
  
  public FileStore openFile(String name, String openMode, boolean mustExist)
  {
    if ((mustExist) && (!FileUtils.exists(name))) {
      throw DbException.get(90124, name);
    }
    FileStore store = FileStore.open(this, name, openMode, this.cipher, this.filePasswordHash);
    try
    {
      store.init();
    }
    catch (DbException e)
    {
      store.closeSilently();
      throw e;
    }
    return store;
  }
  
  boolean validateFilePasswordHash(String testCipher, byte[] testHash)
  {
    if (!StringUtils.equals(testCipher, this.cipher)) {
      return false;
    }
    return Utils.compareSecure(testHash, this.filePasswordHash);
  }
  
  private String parseDatabaseShortName()
  {
    String n = this.databaseName;
    if (n.endsWith(":")) {
      n = null;
    }
    if (n != null)
    {
      StringTokenizer tokenizer = new StringTokenizer(n, "/\\:,;");
      while (tokenizer.hasMoreTokens()) {
        n = tokenizer.nextToken();
      }
    }
    if ((n == null) || (n.length() == 0)) {
      n = "unnamed";
    }
    return this.dbSettings.databaseToUpper ? StringUtils.toUpperEnglish(n) : n;
  }
  
  private synchronized void open(int traceLevelFile, int traceLevelSystemOut)
  {
    if (this.persistent)
    {
      String dataFileName = this.databaseName + ".data.db";
      boolean existsData = FileUtils.exists(dataFileName);
      String pageFileName = this.databaseName + ".h2.db";
      String mvFileName = this.databaseName + ".mv.db";
      boolean existsPage = FileUtils.exists(pageFileName);
      boolean existsMv = FileUtils.exists(mvFileName);
      if ((existsData) && (!existsPage) && (!existsMv)) {
        throw DbException.get(90048, "Old database: " + dataFileName + " - please convert the database " + "to a SQL script and re-create it.");
      }
      if ((existsPage) && (!FileUtils.canWrite(pageFileName))) {
        this.readOnly = true;
      }
      if ((existsMv) && (!FileUtils.canWrite(mvFileName))) {
        this.readOnly = true;
      }
      if ((existsPage) && (!existsMv)) {
        this.dbSettings.mvStore = false;
      }
      if (this.readOnly)
      {
        if (traceLevelFile >= 3)
        {
          String traceFile = Utils.getProperty("java.io.tmpdir", ".") + "/" + "h2_" + System.currentTimeMillis();
          
          this.traceSystem = new TraceSystem(traceFile + ".trace.db");
        }
        else
        {
          this.traceSystem = new TraceSystem(null);
        }
      }
      else {
        this.traceSystem = new TraceSystem(this.databaseName + ".trace.db");
      }
      this.traceSystem.setLevelFile(traceLevelFile);
      this.traceSystem.setLevelSystemOut(traceLevelSystemOut);
      this.trace = this.traceSystem.getTrace("database");
      this.trace.info("opening {0} (build {1})", new Object[] { this.databaseName, Integer.valueOf(187) });
      if ((this.autoServerMode) && (
        (this.readOnly) || (this.fileLockMethod == 0) || (this.fileLockMethod == 3) || (this.fileLockMethod == 4) || (!this.persistent))) {
        throw DbException.getUnsupportedException("autoServerMode && (readOnly || fileLockMethod == NO || fileLockMethod == SERIALIZED || fileLockMethod == FS || inMemory)");
      }
      String lockFileName = this.databaseName + ".lock.db";
      if ((this.readOnly) && 
        (FileUtils.exists(lockFileName))) {
        throw DbException.get(90020, "Lock file exists: " + lockFileName);
      }
      if ((!this.readOnly) && (this.fileLockMethod != 0) && 
        (this.fileLockMethod != 4))
      {
        this.lock = new FileLock(this.traceSystem, lockFileName, 1000);
        this.lock.lock(this.fileLockMethod);
        if (this.autoServerMode) {
          startServer(this.lock.getUniqueId());
        }
      }
      if (SysProperties.MODIFY_ON_WRITE) {
        while (isReconnectNeeded()) {}
      }
      while ((isReconnectNeeded()) && (!beforeWriting())) {}
      deleteOldTempFiles();
      this.starting = true;
      if (SysProperties.MODIFY_ON_WRITE) {
        try
        {
          getPageStore();
        }
        catch (DbException e)
        {
          if (e.getErrorCode() != 90097) {
            throw e;
          }
          this.pageStore = null;
          while (!beforeWriting()) {}
          getPageStore();
        }
      } else {
        getPageStore();
      }
      this.starting = false;
      if (this.mvStore == null) {
        this.writer = WriterThread.create(this, this.writeDelay);
      } else {
        setWriteDelay(this.writeDelay);
      }
    }
    else
    {
      if (this.autoServerMode) {
        throw DbException.getUnsupportedException("autoServerMode && inMemory");
      }
      this.traceSystem = new TraceSystem(null);
      this.trace = this.traceSystem.getTrace("database");
      if (this.dbSettings.mvStore) {
        getPageStore();
      }
    }
    this.systemUser = new User(this, 0, "DBA", true);
    this.mainSchema = new Schema(this, 0, "PUBLIC", this.systemUser, true);
    this.infoSchema = new Schema(this, -1, "INFORMATION_SCHEMA", this.systemUser, true);
    this.schemas.put(this.mainSchema.getName(), this.mainSchema);
    this.schemas.put(this.infoSchema.getName(), this.infoSchema);
    this.publicRole = new Role(this, 0, "PUBLIC", true);
    this.roles.put("PUBLIC", this.publicRole);
    this.systemUser.setAdmin(true);
    this.systemSession = new Session(this, this.systemUser, ++this.nextSessionId);
    this.lobSession = new Session(this, this.systemUser, ++this.nextSessionId);
    CreateTableData data = new CreateTableData();
    ArrayList<Column> cols = data.columns;
    Column columnId = new Column("ID", 4);
    columnId.setNullable(false);
    cols.add(columnId);
    cols.add(new Column("HEAD", 4));
    cols.add(new Column("TYPE", 4));
    cols.add(new Column("SQL", 13));
    boolean create = true;
    if (this.pageStore != null) {
      create = this.pageStore.isNew();
    }
    data.tableName = "SYS";
    data.id = 0;
    data.temporary = false;
    data.persistData = this.persistent;
    data.persistIndexes = this.persistent;
    data.create = create;
    data.isHidden = true;
    data.session = this.systemSession;
    this.meta = this.mainSchema.createTable(data);
    IndexColumn[] pkCols = IndexColumn.wrap(new Column[] { columnId });
    this.metaIdIndex = this.meta.addIndex(this.systemSession, "SYS_ID", 0, pkCols, IndexType.createPrimaryKey(false, false), true, null);
    
    this.objectIds.set(0);
    this.starting = true;
    Cursor cursor = this.metaIdIndex.find(this.systemSession, null, null);
    ArrayList<MetaRecord> records = New.arrayList();
    while (cursor.next())
    {
      MetaRecord rec = new MetaRecord(cursor.get());
      this.objectIds.set(rec.getId());
      records.add(rec);
    }
    Collections.sort(records);
    synchronized (this.systemSession)
    {
      for (MetaRecord rec : records) {
        rec.execute(this, this.systemSession, this.eventListener);
      }
    }
    if (this.mvStore != null)
    {
      this.mvStore.initTransactions();
      this.mvStore.removeTemporaryMaps(this.objectIds);
    }
    recompileInvalidViews(this.systemSession);
    this.starting = false;
    if (!this.readOnly)
    {
      String name = SetTypes.getTypeName(34);
      if (this.settings.get(name) == null)
      {
        Setting setting = new Setting(this, allocateObjectId(), name);
        setting.setIntValue(187);
        lockMeta(this.systemSession);
        addDatabaseObject(this.systemSession, setting);
      }
      if (this.pageStore != null)
      {
        BitField f = this.pageStore.getObjectIds();
        int i = 0;
        for (int len = f.length(); i < len; i++) {
          if ((f.get(i)) && (!this.objectIds.get(i)))
          {
            this.trace.info("unused object id: " + i);
            this.objectIds.set(i);
          }
        }
      }
    }
    getLobStorage().init();
    this.systemSession.commit(true);
    
    this.trace.info("opened {0}", new Object[] { this.databaseName });
    if (this.checkpointAllowed > 0) {
      afterWriting();
    }
  }
  
  private void startServer(String key)
  {
    try
    {
      this.server = Server.createTcpServer(new String[] { "-tcpPort", Integer.toString(this.autoServerPort), "-tcpAllowOthers", "-tcpDaemon", "-key", key, this.databaseName });
      
      this.server.start();
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    String localAddress = NetUtils.getLocalAddress();
    String address = localAddress + ":" + this.server.getPort();
    this.lock.setProperty("server", address);
    String hostName = NetUtils.getHostName(localAddress);
    this.lock.setProperty("hostName", hostName);
    this.lock.save();
  }
  
  private void stopServer()
  {
    if (this.server != null)
    {
      Server s = this.server;
      
      this.server = null;
      s.stop();
    }
  }
  
  private void recompileInvalidViews(Session session)
  {
    boolean recompileSuccessful;
    do
    {
      recompileSuccessful = false;
      for (Table obj : getAllTablesAndViews(false)) {
        if ((obj instanceof TableView))
        {
          TableView view = (TableView)obj;
          if (view.isInvalid())
          {
            view.recompile(session, true);
            if (!view.isInvalid()) {
              recompileSuccessful = true;
            }
          }
        }
      }
    } while (recompileSuccessful);
    for (Table obj : getAllTablesAndViews(false)) {
      if ((obj instanceof TableView))
      {
        TableView view = (TableView)obj;
        if (!view.isInvalid()) {
          view.recompile(this.systemSession, true);
        }
      }
    }
  }
  
  private void initMetaTables()
  {
    if (this.metaTablesInitialized) {
      return;
    }
    synchronized (this.infoSchema)
    {
      if (!this.metaTablesInitialized)
      {
        int type = 0;int count = MetaTable.getMetaTableTypeCount();
        for (; type < count; type++)
        {
          MetaTable m = new MetaTable(this.infoSchema, -1 - type, type);
          this.infoSchema.add(m);
        }
        this.metaTablesInitialized = true;
      }
    }
  }
  
  private synchronized void addMeta(Session session, DbObject obj)
  {
    int id = obj.getId();
    if ((id > 0) && (!this.starting) && (!obj.isTemporary()))
    {
      Row r = this.meta.getTemplateRow();
      MetaRecord rec = new MetaRecord(obj);
      rec.setRecord(r);
      this.objectIds.set(id);
      if (SysProperties.CHECK) {
        verifyMetaLocked(session);
      }
      this.meta.addRow(session, r);
      if (isMultiVersion()) {
        session.log(this.meta, (short)0, r);
      }
    }
  }
  
  public void verifyMetaLocked(Session session)
  {
    if ((this.meta != null) && (!this.meta.isLockedExclusivelyBy(session)) && (this.lockMode != 0)) {
      throw DbException.throwInternalError();
    }
  }
  
  public boolean lockMeta(Session session)
  {
    if (this.meta == null) {
      return true;
    }
    boolean wasLocked = this.meta.lock(session, true, true);
    return wasLocked;
  }
  
  public synchronized void removeMeta(Session session, int id)
  {
    if ((id > 0) && (!this.starting))
    {
      SearchRow r = this.meta.getTemplateSimpleRow(false);
      r.setValue(0, ValueInt.get(id));
      boolean wasLocked = lockMeta(session);
      Cursor cursor = this.metaIdIndex.find(session, r, r);
      if (cursor.next())
      {
        if ((SysProperties.CHECK) && 
          (this.lockMode != 0) && (!wasLocked)) {
          throw DbException.throwInternalError();
        }
        Row found = cursor.get();
        this.meta.removeRow(session, found);
        if (isMultiVersion()) {
          session.log(this.meta, (short)1, found);
        }
        this.objectIds.clear(id);
        if (SysProperties.CHECK) {
          checkMetaFree(session, id);
        }
      }
      else if (!wasLocked)
      {
        this.meta.unlock(session);
        session.unlock(this.meta);
      }
    }
  }
  
  private HashMap<String, DbObject> getMap(int type)
  {
    HashMap<String, ? extends DbObject> result;
    switch (type)
    {
    case 2: 
      result = this.users;
      break;
    case 6: 
      result = this.settings;
      break;
    case 7: 
      result = this.roles;
      break;
    case 8: 
      result = this.rights;
      break;
    case 10: 
      result = this.schemas;
      break;
    case 12: 
      result = this.userDataTypes;
      break;
    case 13: 
      result = this.comments;
      break;
    case 14: 
      result = this.aggregates;
      break;
    case 3: 
    case 4: 
    case 5: 
    case 9: 
    case 11: 
    default: 
      throw DbException.throwInternalError("type=" + type);
    }
    return result;
  }
  
  public synchronized void addSchemaObject(Session session, SchemaObject obj)
  {
    int id = obj.getId();
    if ((id > 0) && (!this.starting)) {
      checkWritingAllowed();
    }
    lockMeta(session);
    obj.getSchema().add(obj);
    addMeta(session, obj);
  }
  
  public synchronized void addDatabaseObject(Session session, DbObject obj)
  {
    int id = obj.getId();
    if ((id > 0) && (!this.starting)) {
      checkWritingAllowed();
    }
    HashMap<String, DbObject> map = getMap(obj.getType());
    if (obj.getType() == 2)
    {
      User user = (User)obj;
      if ((user.isAdmin()) && (this.systemUser.getName().equals("DBA"))) {
        this.systemUser.rename(user.getName());
      }
    }
    String name = obj.getName();
    if ((SysProperties.CHECK) && (map.get(name) != null)) {
      DbException.throwInternalError("object already exists");
    }
    lockMeta(session);
    addMeta(session, obj);
    map.put(name, obj);
  }
  
  public UserAggregate findAggregate(String name)
  {
    return (UserAggregate)this.aggregates.get(name);
  }
  
  public Comment findComment(DbObject object)
  {
    if (object.getType() == 13) {
      return null;
    }
    String key = Comment.getKey(object);
    return (Comment)this.comments.get(key);
  }
  
  public Role findRole(String roleName)
  {
    return (Role)this.roles.get(roleName);
  }
  
  public Schema findSchema(String schemaName)
  {
    Schema schema = (Schema)this.schemas.get(schemaName);
    if (schema == this.infoSchema) {
      initMetaTables();
    }
    return schema;
  }
  
  public Setting findSetting(String name)
  {
    return (Setting)this.settings.get(name);
  }
  
  public User findUser(String name)
  {
    return (User)this.users.get(name);
  }
  
  public UserDataType findUserDataType(String name)
  {
    return (UserDataType)this.userDataTypes.get(name);
  }
  
  public User getUser(String name)
  {
    User user = findUser(name);
    if (user == null) {
      throw DbException.get(90032, name);
    }
    return user;
  }
  
  synchronized Session createSession(User user)
  {
    if (this.exclusiveSession != null) {
      throw DbException.get(90135);
    }
    Session session = new Session(this, user, ++this.nextSessionId);
    this.userSessions.add(session);
    this.trace.info("connecting session #{0} to {1}", new Object[] { Integer.valueOf(session.getId()), this.databaseName });
    if (this.delayedCloser != null)
    {
      this.delayedCloser.reset();
      this.delayedCloser = null;
    }
    return session;
  }
  
  public synchronized void removeSession(Session session)
  {
    if (session != null)
    {
      if (this.exclusiveSession == session) {
        this.exclusiveSession = null;
      }
      this.userSessions.remove(session);
      if ((session != this.systemSession) && (session != this.lobSession)) {
        this.trace.info("disconnecting session #{0}", new Object[] { Integer.valueOf(session.getId()) });
      }
    }
    if ((this.userSessions.size() == 0) && (session != this.systemSession) && (session != this.lobSession)) {
      if (this.closeDelay == 0)
      {
        close(false);
      }
      else
      {
        if (this.closeDelay < 0) {
          return;
        }
        this.delayedCloser = new DatabaseCloser(this, this.closeDelay * 1000, false);
        this.delayedCloser.setName("H2 Close Delay " + getShortName());
        this.delayedCloser.setDaemon(true);
        this.delayedCloser.start();
      }
    }
    if ((session != this.systemSession) && (session != this.lobSession) && (session != null)) {
      this.trace.info("disconnected session #{0}", new Object[] { Integer.valueOf(session.getId()) });
    }
  }
  
  private synchronized void closeAllSessionsException(Session except)
  {
    Session[] all = new Session[this.userSessions.size()];
    this.userSessions.toArray(all);
    for (Session s : all) {
      if (s != except) {
        try
        {
          s.rollback();
          s.close();
        }
        catch (DbException e)
        {
          this.trace.error(e, "disconnecting session #{0}", new Object[] { Integer.valueOf(s.getId()) });
        }
      }
    }
  }
  
  synchronized void close(boolean fromShutdownHook)
  {
    if (this.closing) {
      return;
    }
    throwLastBackgroundException();
    if ((this.fileLockMethod == 3) && (!this.reconnectChangePending))
    {
      try
      {
        closeOpenFilesAndUnlock(false);
      }
      catch (DbException e) {}
      this.traceSystem.close();
      Engine.getInstance().close(this.databaseName);
      return;
    }
    this.closing = true;
    stopServer();
    if (this.userSessions.size() > 0)
    {
      if (!fromShutdownHook) {
        return;
      }
      this.trace.info("closing {0} from shutdown hook", new Object[] { this.databaseName });
      closeAllSessionsException(null);
    }
    this.trace.info("closing {0}", new Object[] { this.databaseName });
    if (this.eventListener != null)
    {
      this.closing = false;
      DatabaseEventListener e = this.eventListener;
      
      this.eventListener = null;
      e.closingDatabase();
      if (this.userSessions.size() > 0) {
        return;
      }
      this.closing = true;
    }
    removeOrphanedLobs();
    try
    {
      if (this.systemSession != null)
      {
        if (this.powerOffCount != -1)
        {
          for (Table table : getAllTablesAndViews(false)) {
            if (table.isGlobalTemporary()) {
              table.removeChildrenAndResources(this.systemSession);
            } else {
              table.close(this.systemSession);
            }
          }
          for (SchemaObject obj : getAllSchemaObjects(3))
          {
            Sequence sequence = (Sequence)obj;
            sequence.close();
          }
        }
        for (SchemaObject obj : getAllSchemaObjects(4))
        {
          TriggerObject trigger = (TriggerObject)obj;
          try
          {
            trigger.close();
          }
          catch (SQLException e)
          {
            this.trace.error(e, "close");
          }
        }
        if (this.powerOffCount != -1)
        {
          this.meta.close(this.systemSession);
          this.systemSession.commit(true);
        }
      }
    }
    catch (DbException e)
    {
      this.trace.error(e, "close");
    }
    this.tempFileDeleter.deleteAll();
    try
    {
      closeOpenFilesAndUnlock(true);
    }
    catch (DbException e)
    {
      this.trace.error(e, "close");
    }
    this.trace.info("closed");
    this.traceSystem.close();
    if (this.closeOnExit != null)
    {
      this.closeOnExit.reset();
      try
      {
        Runtime.getRuntime().removeShutdownHook(this.closeOnExit);
      }
      catch (IllegalStateException e) {}catch (SecurityException e) {}
      this.closeOnExit = null;
    }
    Engine.getInstance().close(this.databaseName);
    if ((this.deleteFilesOnDisconnect) && (this.persistent))
    {
      this.deleteFilesOnDisconnect = false;
      try
      {
        String directory = FileUtils.getParent(this.databaseName);
        String name = FileUtils.getName(this.databaseName);
        DeleteDbFiles.execute(directory, name, true);
      }
      catch (Exception e) {}
    }
  }
  
  private void removeOrphanedLobs()
  {
    if (!this.persistent) {
      return;
    }
    boolean lobStorageIsUsed = this.infoSchema.findTableOrView(this.systemSession, "LOB_DATA") != null;
    
    lobStorageIsUsed |= this.mvStore != null;
    if (!lobStorageIsUsed) {
      return;
    }
    try
    {
      getLobStorage();
      this.lobStorage.removeAllForTable(-1);
    }
    catch (DbException e)
    {
      this.trace.error(e, "close");
    }
  }
  
  private void stopWriter()
  {
    if (this.writer != null)
    {
      this.writer.stopThread();
      this.writer = null;
    }
  }
  
  private synchronized void closeOpenFilesAndUnlock(boolean flush)
  {
    stopWriter();
    if ((this.pageStore != null) && 
      (flush)) {
      try
      {
        this.pageStore.checkpoint();
        if (!this.readOnly)
        {
          lockMeta(this.pageStore.getPageStoreSession());
          this.pageStore.compact(this.compactMode);
        }
      }
      catch (DbException e)
      {
        if (SysProperties.CHECK2)
        {
          int code = e.getErrorCode();
          if ((code != 90098) && (code != 50200) && (code != 90031)) {
            e.printStackTrace();
          }
        }
        this.trace.error(e, "close");
      }
      catch (Throwable t)
      {
        if (SysProperties.CHECK2) {
          t.printStackTrace();
        }
        this.trace.error(t, "close");
      }
    }
    reconnectModified(false);
    if (this.mvStore != null)
    {
      long maxCompactTime = this.dbSettings.maxCompactTime;
      if (this.compactMode == 82) {
        this.mvStore.compactFile(this.dbSettings.maxCompactTime);
      } else if (this.compactMode == 84) {
        maxCompactTime = Long.MAX_VALUE;
      } else if (getSettings().defragAlways) {
        maxCompactTime = Long.MAX_VALUE;
      }
      this.mvStore.close(maxCompactTime);
    }
    closeFiles();
    if ((this.persistent) && (this.lock == null) && (this.fileLockMethod != 0) && (this.fileLockMethod != 4)) {
      return;
    }
    if (this.persistent) {
      deleteOldTempFiles();
    }
    if (this.systemSession != null)
    {
      this.systemSession.close();
      this.systemSession = null;
    }
    if (this.lobSession != null)
    {
      this.lobSession.close();
      this.lobSession = null;
    }
    if (this.lock != null)
    {
      if (this.fileLockMethod == 3) {
        if (this.lock.load().containsKey("changePending")) {
          try
          {
            Thread.sleep((int)(this.reconnectCheckDelay * 1.1D));
          }
          catch (InterruptedException e)
          {
            this.trace.error(e, "close");
          }
        }
      }
      this.lock.unlock();
      this.lock = null;
    }
  }
  
  private synchronized void closeFiles()
  {
    try
    {
      if (this.mvStore != null) {
        this.mvStore.closeImmediately();
      }
      if (this.pageStore != null)
      {
        this.pageStore.close();
        this.pageStore = null;
      }
    }
    catch (DbException e)
    {
      this.trace.error(e, "close");
    }
  }
  
  private void checkMetaFree(Session session, int id)
  {
    SearchRow r = this.meta.getTemplateSimpleRow(false);
    r.setValue(0, ValueInt.get(id));
    Cursor cursor = this.metaIdIndex.find(session, r, r);
    if (cursor.next()) {
      DbException.throwInternalError();
    }
  }
  
  public synchronized int allocateObjectId()
  {
    int i = this.objectIds.nextClearBit(0);
    this.objectIds.set(i);
    return i;
  }
  
  public ArrayList<UserAggregate> getAllAggregates()
  {
    return New.arrayList(this.aggregates.values());
  }
  
  public ArrayList<Comment> getAllComments()
  {
    return New.arrayList(this.comments.values());
  }
  
  public int getAllowLiterals()
  {
    if (this.starting) {
      return 2;
    }
    return this.allowLiterals;
  }
  
  public ArrayList<Right> getAllRights()
  {
    return New.arrayList(this.rights.values());
  }
  
  public ArrayList<Role> getAllRoles()
  {
    return New.arrayList(this.roles.values());
  }
  
  public ArrayList<SchemaObject> getAllSchemaObjects()
  {
    initMetaTables();
    ArrayList<SchemaObject> list = New.arrayList();
    for (Schema schema : this.schemas.values()) {
      list.addAll(schema.getAll());
    }
    return list;
  }
  
  public ArrayList<SchemaObject> getAllSchemaObjects(int type)
  {
    if (type == 0) {
      initMetaTables();
    }
    ArrayList<SchemaObject> list = New.arrayList();
    for (Schema schema : this.schemas.values()) {
      list.addAll(schema.getAll(type));
    }
    return list;
  }
  
  public ArrayList<Table> getAllTablesAndViews(boolean includeMeta)
  {
    if (includeMeta) {
      initMetaTables();
    }
    ArrayList<Table> list = New.arrayList();
    for (Schema schema : this.schemas.values()) {
      list.addAll(schema.getAllTablesAndViews());
    }
    return list;
  }
  
  public ArrayList<Schema> getAllSchemas()
  {
    initMetaTables();
    return New.arrayList(this.schemas.values());
  }
  
  public ArrayList<Setting> getAllSettings()
  {
    return New.arrayList(this.settings.values());
  }
  
  public ArrayList<UserDataType> getAllUserDataTypes()
  {
    return New.arrayList(this.userDataTypes.values());
  }
  
  public ArrayList<User> getAllUsers()
  {
    return New.arrayList(this.users.values());
  }
  
  public String getCacheType()
  {
    return this.cacheType;
  }
  
  public String getCluster()
  {
    return this.cluster;
  }
  
  public CompareMode getCompareMode()
  {
    return this.compareMode;
  }
  
  public String getDatabasePath()
  {
    if (this.persistent) {
      return FileUtils.toRealPath(this.databaseName);
    }
    return null;
  }
  
  public String getShortName()
  {
    return this.databaseShortName;
  }
  
  public String getName()
  {
    return this.databaseName;
  }
  
  public Session[] getSessions(boolean includingSystemSession)
  {
    ArrayList<Session> list;
    synchronized (this.userSessions)
    {
      list = New.arrayList(this.userSessions);
    }
    Session sys = this.systemSession;
    Session lob = this.lobSession;
    if ((includingSystemSession) && (sys != null)) {
      list.add(sys);
    }
    if ((includingSystemSession) && (lob != null)) {
      list.add(lob);
    }
    Session[] array = new Session[list.size()];
    list.toArray(array);
    return array;
  }
  
  public synchronized void updateMeta(Session session, DbObject obj)
  {
    lockMeta(session);
    int id = obj.getId();
    removeMeta(session, id);
    addMeta(session, obj);
  }
  
  public synchronized void renameSchemaObject(Session session, SchemaObject obj, String newName)
  {
    checkWritingAllowed();
    obj.getSchema().rename(obj, newName);
    updateMetaAndFirstLevelChildren(session, obj);
  }
  
  private synchronized void updateMetaAndFirstLevelChildren(Session session, DbObject obj)
  {
    ArrayList<DbObject> list = obj.getChildren();
    Comment comment = findComment(obj);
    if (comment != null) {
      DbException.throwInternalError();
    }
    updateMeta(session, obj);
    if (list != null) {
      for (DbObject o : list) {
        if (o.getCreateSQL() != null) {
          updateMeta(session, o);
        }
      }
    }
  }
  
  public synchronized void renameDatabaseObject(Session session, DbObject obj, String newName)
  {
    checkWritingAllowed();
    int type = obj.getType();
    HashMap<String, DbObject> map = getMap(type);
    if (SysProperties.CHECK)
    {
      if (!map.containsKey(obj.getName())) {
        DbException.throwInternalError("not found: " + obj.getName());
      }
      if ((obj.getName().equals(newName)) || (map.containsKey(newName))) {
        DbException.throwInternalError("object already exists: " + newName);
      }
    }
    obj.checkRename();
    int id = obj.getId();
    lockMeta(session);
    removeMeta(session, id);
    map.remove(obj.getName());
    obj.rename(newName);
    map.put(newName, obj);
    updateMetaAndFirstLevelChildren(session, obj);
  }
  
  public String createTempFile()
  {
    try
    {
      boolean inTempDir = this.readOnly;
      String name = this.databaseName;
      if (!this.persistent) {
        name = "memFS:" + name;
      }
      return FileUtils.createTempFile(name, ".temp.db", true, inTempDir);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, this.databaseName);
    }
  }
  
  private void deleteOldTempFiles()
  {
    String path = FileUtils.getParent(this.databaseName);
    for (String name : FileUtils.newDirectoryStream(path)) {
      if ((name.endsWith(".temp.db")) && (name.startsWith(this.databaseName))) {
        FileUtils.tryDelete(name);
      }
    }
  }
  
  public Schema getSchema(String schemaName)
  {
    Schema schema = findSchema(schemaName);
    if (schema == null) {
      throw DbException.get(90079, schemaName);
    }
    return schema;
  }
  
  public synchronized void removeDatabaseObject(Session session, DbObject obj)
  {
    checkWritingAllowed();
    String objName = obj.getName();
    int type = obj.getType();
    HashMap<String, DbObject> map = getMap(type);
    if ((SysProperties.CHECK) && (!map.containsKey(objName))) {
      DbException.throwInternalError("not found: " + objName);
    }
    Comment comment = findComment(obj);
    lockMeta(session);
    if (comment != null) {
      removeDatabaseObject(session, comment);
    }
    int id = obj.getId();
    obj.removeChildrenAndResources(session);
    map.remove(objName);
    removeMeta(session, id);
  }
  
  public Table getDependentTable(SchemaObject obj, Table except)
  {
    switch (obj.getType())
    {
    case 1: 
    case 2: 
    case 4: 
    case 5: 
    case 8: 
    case 13: 
      return null;
    }
    HashSet<DbObject> set = New.hashSet();
    for (Table t : getAllTablesAndViews(false)) {
      if ((except != t) && 
      
        (!"VIEW".equals(t.getTableType())))
      {
        set.clear();
        t.addDependencies(set);
        if (set.contains(obj)) {
          return t;
        }
      }
    }
    return null;
  }
  
  public synchronized void removeSchemaObject(Session session, SchemaObject obj)
  {
    int type = obj.getType();
    if (type == 0)
    {
      Table table = (Table)obj;
      if ((table.isTemporary()) && (!table.isGlobalTemporary()))
      {
        session.removeLocalTempTable(table);
        return;
      }
    }
    else if (type == 1)
    {
      Index index = (Index)obj;
      Table table = index.getTable();
      if ((table.isTemporary()) && (!table.isGlobalTemporary()))
      {
        session.removeLocalTempTableIndex(index);
        return;
      }
    }
    else if (type == 5)
    {
      Constraint constraint = (Constraint)obj;
      Table table = constraint.getTable();
      if ((table.isTemporary()) && (!table.isGlobalTemporary()))
      {
        session.removeLocalTempTableConstraint(constraint);
        return;
      }
    }
    checkWritingAllowed();
    lockMeta(session);
    Comment comment = findComment(obj);
    if (comment != null) {
      removeDatabaseObject(session, comment);
    }
    obj.getSchema().remove(obj);
    int id = obj.getId();
    if (!this.starting)
    {
      Table t = getDependentTable(obj, null);
      if (t != null)
      {
        obj.getSchema().add(obj);
        throw DbException.get(90107, new String[] { obj.getSQL(), t.getSQL() });
      }
      obj.removeChildrenAndResources(session);
    }
    removeMeta(session, id);
  }
  
  public boolean isPersistent()
  {
    return this.persistent;
  }
  
  public TraceSystem getTraceSystem()
  {
    return this.traceSystem;
  }
  
  public synchronized void setCacheSize(int kb)
  {
    if (this.starting)
    {
      int max = MathUtils.convertLongToInt(Utils.getMemoryMax()) / 2;
      kb = Math.min(kb, max);
    }
    this.cacheSize = kb;
    if (this.pageStore != null) {
      this.pageStore.getCache().setMaxMemory(kb);
    }
    if (this.mvStore != null) {
      this.mvStore.setCacheSize(Math.max(1, kb));
    }
  }
  
  public synchronized void setMasterUser(User user)
  {
    lockMeta(this.systemSession);
    addDatabaseObject(this.systemSession, user);
    this.systemSession.commit(true);
  }
  
  public Role getPublicRole()
  {
    return this.publicRole;
  }
  
  public synchronized String getTempTableName(String baseName, Session session)
  {
    String tempName;
    do
    {
      tempName = baseName + "_COPY_" + session.getId() + "_" + this.nextTempTableId++;
    } while (this.mainSchema.findTableOrView(session, tempName) != null);
    return tempName;
  }
  
  public void setCompareMode(CompareMode compareMode)
  {
    this.compareMode = compareMode;
  }
  
  public void setCluster(String cluster)
  {
    this.cluster = cluster;
  }
  
  public void checkWritingAllowed()
  {
    if (this.readOnly) {
      throw DbException.get(90097);
    }
    if ((this.fileLockMethod == 3) && 
      (!this.reconnectChangePending)) {
      throw DbException.get(90097);
    }
  }
  
  public boolean isReadOnly()
  {
    return this.readOnly;
  }
  
  public void setWriteDelay(int value)
  {
    this.writeDelay = value;
    if (this.writer != null)
    {
      this.writer.setWriteDelay(value);
      
      this.flushOnEachCommit = (this.writeDelay < 5);
    }
    if (this.mvStore != null)
    {
      int millis = value < 0 ? 0 : value;
      this.mvStore.getStore().setAutoCommitDelay(millis);
    }
  }
  
  public int getRetentionTime()
  {
    return this.retentionTime;
  }
  
  public void setRetentionTime(int value)
  {
    this.retentionTime = value;
    if (this.mvStore != null) {
      this.mvStore.getStore().setRetentionTime(value);
    }
  }
  
  public boolean getFlushOnEachCommit()
  {
    return this.flushOnEachCommit;
  }
  
  public ArrayList<InDoubtTransaction> getInDoubtTransactions()
  {
    if (this.mvStore != null) {
      return this.mvStore.getInDoubtTransactions();
    }
    return this.pageStore == null ? null : this.pageStore.getInDoubtTransactions();
  }
  
  synchronized void prepareCommit(Session session, String transaction)
  {
    if (this.readOnly) {
      return;
    }
    if (this.mvStore != null)
    {
      this.mvStore.prepareCommit(session, transaction);
      return;
    }
    if (this.pageStore != null)
    {
      this.pageStore.flushLog();
      this.pageStore.prepareCommit(session, transaction);
    }
  }
  
  synchronized void commit(Session session)
  {
    throwLastBackgroundException();
    if (this.readOnly) {
      return;
    }
    if (this.pageStore != null) {
      this.pageStore.commit(session);
    }
    session.setAllCommitted();
  }
  
  private void throwLastBackgroundException()
  {
    if (this.backgroundException != null)
    {
      DbException b = this.backgroundException;
      this.backgroundException = null;
      if (b != null) {
        throw b;
      }
    }
  }
  
  public void setBackgroundException(DbException e)
  {
    if (this.backgroundException == null)
    {
      this.backgroundException = e;
      TraceSystem t = getTraceSystem();
      if (t != null) {
        t.getTrace("database").error(e, "flush");
      }
    }
  }
  
  public synchronized void flush()
  {
    if (this.readOnly) {
      return;
    }
    if (this.pageStore != null) {
      this.pageStore.flushLog();
    }
    if (this.mvStore != null) {
      try
      {
        this.mvStore.flush();
      }
      catch (RuntimeException e)
      {
        this.backgroundException = DbException.convert(e);
        throw e;
      }
    }
  }
  
  public void setEventListener(DatabaseEventListener eventListener)
  {
    this.eventListener = eventListener;
  }
  
  public void setEventListenerClass(String className)
  {
    if ((className == null) || (className.length() == 0)) {
      this.eventListener = null;
    } else {
      try
      {
        this.eventListener = ((DatabaseEventListener)JdbcUtils.loadUserClass(className).newInstance());
        
        String url = this.databaseURL;
        if (this.cipher != null) {
          url = url + ";CIPHER=" + this.cipher;
        }
        this.eventListener.init(url);
      }
      catch (Throwable e)
      {
        throw DbException.get(90099, e, new String[] { className, e.toString() });
      }
    }
  }
  
  public void setProgress(int state, String name, int x, int max)
  {
    if (this.eventListener != null) {
      try
      {
        this.eventListener.setProgress(state, name, x, max);
      }
      catch (Exception e2) {}
    }
  }
  
  public void exceptionThrown(SQLException e, String sql)
  {
    if (this.eventListener != null) {
      try
      {
        this.eventListener.exceptionThrown(e, sql);
      }
      catch (Exception e2) {}
    }
  }
  
  public synchronized void sync()
  {
    if (this.readOnly) {
      return;
    }
    if (this.mvStore != null) {
      this.mvStore.sync();
    }
    if (this.pageStore != null) {
      this.pageStore.sync();
    }
  }
  
  public int getMaxMemoryRows()
  {
    return this.maxMemoryRows;
  }
  
  public void setMaxMemoryRows(int value)
  {
    this.maxMemoryRows = value;
  }
  
  public void setMaxMemoryUndo(int value)
  {
    this.maxMemoryUndo = value;
  }
  
  public int getMaxMemoryUndo()
  {
    return this.maxMemoryUndo;
  }
  
  public void setLockMode(int lockMode)
  {
    switch (lockMode)
    {
    case 0: 
      if (this.multiThreaded) {
        throw DbException.get(90021, "LOCK_MODE=0 & MULTI_THREADED");
      }
      break;
    case 1: 
    case 2: 
    case 3: 
      break;
    default: 
      throw DbException.getInvalidValueException("lock mode", Integer.valueOf(lockMode));
    }
    this.lockMode = lockMode;
  }
  
  public int getLockMode()
  {
    return this.lockMode;
  }
  
  public synchronized void setCloseDelay(int value)
  {
    this.closeDelay = value;
  }
  
  public Session getSystemSession()
  {
    return this.systemSession;
  }
  
  public boolean isClosing()
  {
    return this.closing;
  }
  
  public void setMaxLengthInplaceLob(int value)
  {
    this.maxLengthInplaceLob = value;
  }
  
  public int getMaxLengthInplaceLob()
  {
    return this.maxLengthInplaceLob;
  }
  
  public void setIgnoreCase(boolean b)
  {
    this.ignoreCase = b;
  }
  
  public boolean getIgnoreCase()
  {
    if (this.starting) {
      return false;
    }
    return this.ignoreCase;
  }
  
  public synchronized void setDeleteFilesOnDisconnect(boolean b)
  {
    this.deleteFilesOnDisconnect = b;
  }
  
  public String getLobCompressionAlgorithm(int type)
  {
    return this.lobCompressionAlgorithm;
  }
  
  public void setLobCompressionAlgorithm(String stringValue)
  {
    this.lobCompressionAlgorithm = stringValue;
  }
  
  public synchronized void setMaxLogSize(long value)
  {
    if (this.pageStore != null) {
      this.pageStore.setMaxLogSize(value);
    }
  }
  
  public void setAllowLiterals(int value)
  {
    this.allowLiterals = value;
  }
  
  public boolean getOptimizeReuseResults()
  {
    return this.optimizeReuseResults;
  }
  
  public void setOptimizeReuseResults(boolean b)
  {
    this.optimizeReuseResults = b;
  }
  
  public Object getLobSyncObject()
  {
    return this.lobSyncObject;
  }
  
  public int getSessionCount()
  {
    return this.userSessions.size();
  }
  
  public void setReferentialIntegrity(boolean b)
  {
    this.referentialIntegrity = b;
  }
  
  public boolean getReferentialIntegrity()
  {
    return this.referentialIntegrity;
  }
  
  public void setQueryStatistics(boolean b)
  {
    this.queryStatistics = b;
    synchronized (this)
    {
      this.queryStatisticsData = null;
    }
  }
  
  public boolean getQueryStatistics()
  {
    return this.queryStatistics;
  }
  
  public QueryStatisticsData getQueryStatisticsData()
  {
    if (!this.queryStatistics) {
      return null;
    }
    if (this.queryStatisticsData == null) {
      synchronized (this)
      {
        if (this.queryStatisticsData == null) {
          this.queryStatisticsData = new QueryStatisticsData();
        }
      }
    }
    return this.queryStatisticsData;
  }
  
  public boolean isStarting()
  {
    return this.starting;
  }
  
  public boolean isMultiVersion()
  {
    return this.multiVersion;
  }
  
  void opened()
  {
    if (this.eventListener != null) {
      this.eventListener.opened();
    }
    if (this.writer != null) {
      this.writer.startThread();
    }
  }
  
  public void setMode(Mode mode)
  {
    this.mode = mode;
  }
  
  public Mode getMode()
  {
    return this.mode;
  }
  
  public boolean isMultiThreaded()
  {
    return this.multiThreaded;
  }
  
  public void setMultiThreaded(boolean multiThreaded)
  {
    if ((multiThreaded) && (this.multiThreaded != multiThreaded))
    {
      if ((this.multiVersion) && (this.mvStore == null)) {
        throw DbException.get(90021, "MVCC & MULTI_THREADED");
      }
      if (this.lockMode == 0) {
        throw DbException.get(90021, "LOCK_MODE=0 & MULTI_THREADED");
      }
    }
    this.multiThreaded = multiThreaded;
  }
  
  public void setMaxOperationMemory(int maxOperationMemory)
  {
    this.maxOperationMemory = maxOperationMemory;
  }
  
  public int getMaxOperationMemory()
  {
    return this.maxOperationMemory;
  }
  
  public Session getExclusiveSession()
  {
    return this.exclusiveSession;
  }
  
  public void setExclusiveSession(Session session, boolean closeOthers)
  {
    this.exclusiveSession = session;
    if (closeOthers) {
      closeAllSessionsException(session);
    }
  }
  
  public SmallLRUCache<String, String[]> getLobFileListCache()
  {
    if (this.lobFileListCache == null) {
      this.lobFileListCache = SmallLRUCache.newInstance(128);
    }
    return this.lobFileListCache;
  }
  
  public boolean isSysTableLocked()
  {
    return (this.meta == null) || (this.meta.isLockedExclusively());
  }
  
  public TableLinkConnection getLinkConnection(String driver, String url, String user, String password)
  {
    if (this.linkConnections == null) {
      this.linkConnections = New.hashMap();
    }
    return TableLinkConnection.open(this.linkConnections, driver, url, user, password, this.dbSettings.shareLinkedConnections);
  }
  
  public String toString()
  {
    return this.databaseShortName + ":" + super.toString();
  }
  
  public void shutdownImmediately()
  {
    setPowerOffCount(1);
    try
    {
      checkPowerOff();
    }
    catch (DbException e) {}
    closeFiles();
  }
  
  public TempFileDeleter getTempFileDeleter()
  {
    return this.tempFileDeleter;
  }
  
  public PageStore getPageStore()
  {
    if (this.dbSettings.mvStore)
    {
      if (this.mvStore == null) {
        this.mvStore = MVTableEngine.init(this);
      }
      return null;
    }
    if (this.pageStore == null)
    {
      this.pageStore = new PageStore(this, this.databaseName + ".h2.db", this.accessModeData, this.cacheSize);
      if (this.pageSize != 4096) {
        this.pageStore.setPageSize(this.pageSize);
      }
      if ((!this.readOnly) && (this.fileLockMethod == 4)) {
        this.pageStore.setLockFile(true);
      }
      this.pageStore.setLogMode(this.logMode);
      this.pageStore.open();
    }
    return this.pageStore;
  }
  
  public Table getFirstUserTable()
  {
    for (Table table : getAllTablesAndViews(false)) {
      if (table.getCreateSQL() != null) {
        if (!table.isHidden()) {
          return table;
        }
      }
    }
    return null;
  }
  
  public boolean isReconnectNeeded()
  {
    if (this.fileLockMethod != 3) {
      return false;
    }
    if (this.reconnectChangePending) {
      return false;
    }
    long now = System.currentTimeMillis();
    if (now < this.reconnectCheckNext) {
      return false;
    }
    this.reconnectCheckNext = (now + this.reconnectCheckDelay);
    if (this.lock == null) {
      this.lock = new FileLock(this.traceSystem, this.databaseName + ".lock.db", 1000);
    }
    try
    {
      Properties prop = this.lock.load();Properties first = prop;
      for (;;)
      {
        if (prop.equals(this.reconnectLastLock)) {
          return false;
        }
        if (prop.getProperty("changePending", null) == null) {
          break;
        }
        if (System.currentTimeMillis() > now + this.reconnectCheckDelay * 10) {
          if (first.equals(prop))
          {
            this.lock.setProperty("changePending", null);
            this.lock.save();
            break;
          }
        }
        this.trace.debug("delay (change pending)");
        Thread.sleep(this.reconnectCheckDelay);
        prop = this.lock.load();
      }
      this.reconnectLastLock = prop;
    }
    catch (Exception e)
    {
      this.trace.error(e, "readOnly {0}", new Object[] { Boolean.valueOf(this.readOnly) });
    }
    return true;
  }
  
  public void checkpointIfRequired()
  {
    if ((this.fileLockMethod != 3) || (this.readOnly) || (!this.reconnectChangePending) || (this.closing)) {
      return;
    }
    long now = System.currentTimeMillis();
    if (now > this.reconnectCheckNext + this.reconnectCheckDelay)
    {
      if ((SysProperties.CHECK) && (this.checkpointAllowed < 0)) {
        DbException.throwInternalError();
      }
      synchronized (this.reconnectSync)
      {
        if (this.checkpointAllowed > 0) {
          return;
        }
        this.checkpointRunning = true;
      }
      synchronized (this)
      {
        this.trace.debug("checkpoint start");
        flushSequences();
        checkpoint();
        reconnectModified(false);
        this.trace.debug("checkpoint end");
      }
      synchronized (this.reconnectSync)
      {
        this.checkpointRunning = false;
      }
    }
  }
  
  public boolean isFileLockSerialized()
  {
    return this.fileLockMethod == 3;
  }
  
  private void flushSequences()
  {
    for (SchemaObject obj : getAllSchemaObjects(3))
    {
      Sequence sequence = (Sequence)obj;
      sequence.flushWithoutMargin();
    }
  }
  
  public void checkpoint()
  {
    if (this.persistent)
    {
      synchronized (this)
      {
        if (this.pageStore != null) {
          this.pageStore.checkpoint();
        }
      }
      if (this.mvStore != null) {
        this.mvStore.flush();
      }
    }
    getTempFileDeleter().deleteUnused();
  }
  
  public boolean beforeWriting()
  {
    if (this.fileLockMethod != 3) {
      return true;
    }
    while (this.checkpointRunning) {
      try
      {
        Thread.sleep(10 + (int)(Math.random() * 10.0D));
      }
      catch (Exception e) {}
    }
    synchronized (this.reconnectSync)
    {
      if (reconnectModified(true))
      {
        this.checkpointAllowed += 1;
        if ((SysProperties.CHECK) && (this.checkpointAllowed > 20)) {
          throw DbException.throwInternalError();
        }
        return true;
      }
    }
    this.reconnectCheckNext = (System.currentTimeMillis() - 1L);
    this.reconnectLastLock = null;
    return false;
  }
  
  public void afterWriting()
  {
    if (this.fileLockMethod != 3) {
      return;
    }
    synchronized (this.reconnectSync)
    {
      this.checkpointAllowed -= 1;
    }
    if ((SysProperties.CHECK) && (this.checkpointAllowed < 0)) {
      throw DbException.throwInternalError();
    }
  }
  
  public void setReadOnly(boolean readOnly)
  {
    this.readOnly = readOnly;
  }
  
  public void setCompactMode(int compactMode)
  {
    this.compactMode = compactMode;
  }
  
  public SourceCompiler getCompiler()
  {
    if (this.compiler == null) {
      this.compiler = new SourceCompiler();
    }
    return this.compiler;
  }
  
  public LobStorageInterface getLobStorage()
  {
    if (this.lobStorage == null) {
      if (this.dbSettings.mvStore) {
        this.lobStorage = new LobStorageMap(this);
      } else {
        this.lobStorage = new LobStorageBackend(this);
      }
    }
    return this.lobStorage;
  }
  
  public JdbcConnection getLobConnectionForInit()
  {
    String url = "jdbc:default:connection";
    JdbcConnection conn = new JdbcConnection(this.systemSession, this.systemUser.getName(), url);
    
    conn.setTraceLevel(0);
    return conn;
  }
  
  public JdbcConnection getLobConnectionForRegularUse()
  {
    String url = "jdbc:default:connection";
    JdbcConnection conn = new JdbcConnection(this.lobSession, this.systemUser.getName(), url);
    
    conn.setTraceLevel(0);
    return conn;
  }
  
  public Session getLobSession()
  {
    return this.lobSession;
  }
  
  public void setLogMode(int log)
  {
    if ((log < 0) || (log > 2)) {
      throw DbException.getInvalidValueException("LOG", Integer.valueOf(log));
    }
    if (this.pageStore != null)
    {
      if ((log != 2) || (this.pageStore.getLogMode() != 2)) {
        this.trace.error(null, "log {0}", new Object[] { Integer.valueOf(log) });
      }
      this.logMode = log;
      this.pageStore.setLogMode(log);
    }
    if (this.mvStore != null) {
      this.logMode = log;
    }
  }
  
  public int getLogMode()
  {
    if (this.pageStore != null) {
      return this.pageStore.getLogMode();
    }
    if (this.mvStore != null) {
      return this.logMode;
    }
    return 0;
  }
  
  public int getDefaultTableType()
  {
    return this.defaultTableType;
  }
  
  public void setDefaultTableType(int defaultTableType)
  {
    this.defaultTableType = defaultTableType;
  }
  
  public void setMultiVersion(boolean multiVersion)
  {
    this.multiVersion = multiVersion;
  }
  
  public DbSettings getSettings()
  {
    return this.dbSettings;
  }
  
  public <V> HashMap<String, V> newStringMap()
  {
    return this.dbSettings.databaseToUpper ? new HashMap() : new CaseInsensitiveMap();
  }
  
  public boolean equalsIdentifiers(String a, String b)
  {
    if ((a == b) || (a.equals(b))) {
      return true;
    }
    if ((!this.dbSettings.databaseToUpper) && (a.equalsIgnoreCase(b))) {
      return true;
    }
    return false;
  }
  
  public int readLob(long lobId, byte[] hmac, long offset, byte[] buff, int off, int length)
  {
    throw DbException.throwInternalError();
  }
  
  public byte[] getFileEncryptionKey()
  {
    return this.fileEncryptionKey;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public JavaObjectSerializer getJavaObjectSerializer()
  {
    initJavaObjectSerializer();
    return this.javaObjectSerializer;
  }
  
  private void initJavaObjectSerializer()
  {
    if (this.javaObjectSerializerInitialized) {
      return;
    }
    synchronized (this)
    {
      if (this.javaObjectSerializerInitialized) {
        return;
      }
      String serializerName = this.javaObjectSerializerName;
      if (serializerName != null)
      {
        serializerName = serializerName.trim();
        if ((!serializerName.isEmpty()) && (!serializerName.equals("null"))) {
          try
          {
            this.javaObjectSerializer = ((JavaObjectSerializer)JdbcUtils.loadUserClass(serializerName).newInstance());
          }
          catch (Exception e)
          {
            throw DbException.convert(e);
          }
        }
      }
      this.javaObjectSerializerInitialized = true;
    }
  }
  
  public void setJavaObjectSerializerName(String serializerName)
  {
    synchronized (this)
    {
      this.javaObjectSerializerInitialized = false;
      this.javaObjectSerializerName = serializerName;
    }
  }
}
