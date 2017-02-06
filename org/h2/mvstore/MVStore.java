package org.h2.mvstore;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.h2.compress.CompressDeflate;
import org.h2.compress.CompressLZF;
import org.h2.compress.Compressor;
import org.h2.mvstore.cache.CacheLongKeyLIRS;
import org.h2.mvstore.type.StringDataType;
import org.h2.util.MathUtils;
import org.h2.util.New;

public class MVStore
{
  public static final boolean ASSERT = false;
  static final int BLOCK_SIZE = 4096;
  private static final int FORMAT_WRITE = 1;
  private static final int FORMAT_READ = 1;
  private static final int MARKED_FREE = 10000000;
  volatile BackgroundWriterThread backgroundWriterThread;
  private volatile boolean reuseSpace = true;
  private boolean closed;
  private FileStore fileStore;
  private boolean fileStoreIsProvided;
  private final int pageSplitSize;
  private CacheLongKeyLIRS<Page> cache;
  private CacheLongKeyLIRS<Page.PageChildren> cacheChunkRef;
  private Chunk lastChunk;
  private final ConcurrentHashMap<Integer, Chunk> chunks = new ConcurrentHashMap();
  private final ConcurrentHashMap<Long, HashMap<Integer, Chunk>> freedPageSpace = new ConcurrentHashMap();
  private MVMap<String, String> meta;
  private final ConcurrentHashMap<Integer, MVMap<?, ?>> maps = new ConcurrentHashMap();
  private HashMap<String, Object> storeHeader = New.hashMap();
  private WriteBuffer writeBuffer;
  private int lastMapId;
  private int versionsToKeep = 5;
  private final int compressionLevel;
  private Compressor compressorFast;
  private Compressor compressorHigh;
  private final Thread.UncaughtExceptionHandler backgroundExceptionHandler;
  private long currentVersion;
  private long lastStoredVersion;
  private int unsavedMemory;
  private int autoCommitMemory;
  private boolean saveNeeded;
  private long creationTime;
  private int retentionTime;
  private long lastCommitTime;
  private Chunk retainChunk;
  private volatile long currentStoreVersion = -1L;
  private Thread currentStoreThread;
  private volatile boolean metaChanged;
  private int autoCommitDelay;
  private int autoCompactFillRate;
  private long autoCompactLastFileOpCount;
  private Object compactSync = new Object();
  private IllegalStateException panicException;
  
  MVStore(HashMap<String, Object> config)
  {
    Object o = config.get("compress");
    this.compressionLevel = (o == null ? 0 : ((Integer)o).intValue());
    String fileName = (String)config.get("fileName");
    o = config.get("pageSplitSize");
    if (o == null) {
      this.pageSplitSize = (fileName == null ? 4096 : 16384);
    } else {
      this.pageSplitSize = ((Integer)o).intValue();
    }
    o = config.get("backgroundExceptionHandler");
    this.backgroundExceptionHandler = ((Thread.UncaughtExceptionHandler)o);
    this.meta = new MVMap(StringDataType.INSTANCE, StringDataType.INSTANCE);
    
    HashMap<String, Object> c = New.hashMap();
    c.put("id", Integer.valueOf(0));
    c.put("createVersion", Long.valueOf(this.currentVersion));
    this.meta.init(this, c);
    this.fileStore = ((FileStore)config.get("fileStore"));
    if ((fileName == null) && (this.fileStore == null))
    {
      this.cache = null;
      this.cacheChunkRef = null;
      return;
    }
    if (this.fileStore == null)
    {
      this.fileStoreIsProvided = false;
      this.fileStore = new FileStore();
    }
    else
    {
      this.fileStoreIsProvided = true;
    }
    this.retentionTime = this.fileStore.getDefaultRetentionTime();
    boolean readOnly = config.containsKey("readOnly");
    o = config.get("cacheSize");
    int mb = o == null ? 16 : ((Integer)o).intValue();
    if (mb > 0)
    {
      long maxMemoryBytes = mb * 1024L * 1024L;
      int segmentCount = 16;
      int stackMoveDistance = 8;
      this.cache = new CacheLongKeyLIRS(maxMemoryBytes, segmentCount, stackMoveDistance);
      
      this.cacheChunkRef = new CacheLongKeyLIRS(maxMemoryBytes / 4L, segmentCount, stackMoveDistance);
    }
    o = config.get("autoCommitBufferSize");
    int kb = o == null ? 1024 : ((Integer)o).intValue();
    
    this.autoCommitMemory = (kb * 1024 * 19);
    
    o = config.get("autoCompactFillRate");
    this.autoCompactFillRate = (o == null ? 50 : ((Integer)o).intValue());
    
    char[] encryptionKey = (char[])config.get("encryptionKey");
    try
    {
      if (!this.fileStoreIsProvided) {
        this.fileStore.open(fileName, readOnly, encryptionKey);
      }
      if (this.fileStore.size() == 0L)
      {
        this.creationTime = getTime();
        this.lastCommitTime = this.creationTime;
        this.storeHeader.put("H", Integer.valueOf(2));
        this.storeHeader.put("blockSize", Integer.valueOf(4096));
        this.storeHeader.put("format", Integer.valueOf(1));
        this.storeHeader.put("created", Long.valueOf(this.creationTime));
        writeStoreHeader();
      }
      else
      {
        readStoreHeader();
      }
    }
    catch (IllegalStateException e)
    {
      panic(e);
    }
    finally
    {
      if (encryptionKey != null) {
        Arrays.fill(encryptionKey, '\000');
      }
    }
    this.lastCommitTime = getTime();
    
    o = config.get("autoCommitDelay");
    int delay = o == null ? 1000 : ((Integer)o).intValue();
    setAutoCommitDelay(delay);
  }
  
  private void panic(IllegalStateException e)
  {
    if (this.backgroundExceptionHandler != null) {
      this.backgroundExceptionHandler.uncaughtException(null, e);
    }
    this.panicException = e;
    closeImmediately();
    throw e;
  }
  
  public static MVStore open(String fileName)
  {
    HashMap<String, Object> config = New.hashMap();
    config.put("fileName", fileName);
    return new MVStore(config);
  }
  
  <T extends MVMap<?, ?>> T openMapVersion(long version, int mapId, MVMap<?, ?> template)
  {
    MVMap<String, String> oldMeta = getMetaMap(version);
    long rootPos = getRootPos(oldMeta, mapId);
    MVMap<?, ?> m = template.openReadOnly();
    m.setRootPos(rootPos, version);
    return m;
  }
  
  public <K, V> MVMap<K, V> openMap(String name)
  {
    return openMap(name, new MVMap.Builder());
  }
  
  public synchronized <M extends MVMap<K, V>, K, V> M openMap(String name, MVMap.MapBuilder<M, K, V> builder)
  {
    checkOpen();
    String x = (String)this.meta.get("name." + name);
    long root;
    int id;
    M map;
    long root;
    if (x != null)
    {
      int id = DataUtils.parseHexInt(x);
      
      M old = (MVMap)this.maps.get(Integer.valueOf(id));
      if (old != null) {
        return old;
      }
      M map = builder.create();
      String config = (String)this.meta.get(MVMap.getMapKey(id));
      HashMap<String, Object> c = New.hashMap();
      c.putAll(DataUtils.parseMap(config));
      c.put("id", Integer.valueOf(id));
      map.init(this, c);
      root = getRootPos(this.meta, id);
    }
    else
    {
      HashMap<String, Object> c = New.hashMap();
      id = ++this.lastMapId;
      c.put("id", Integer.valueOf(id));
      c.put("createVersion", Long.valueOf(this.currentVersion));
      map = builder.create();
      map.init(this, c);
      markMetaChanged();
      x = Integer.toHexString(id);
      this.meta.put(MVMap.getMapKey(id), map.asString(name));
      this.meta.put("name." + name, x);
      root = 0L;
    }
    map.setRootPos(root, -1L);
    this.maps.put(Integer.valueOf(id), map);
    return map;
  }
  
  public synchronized Set<String> getMapNames()
  {
    HashSet<String> set = New.hashSet();
    checkOpen();
    for (Iterator<String> it = this.meta.keyIterator("name."); it.hasNext();)
    {
      String x = (String)it.next();
      if (!x.startsWith("name.")) {
        break;
      }
      set.add(x.substring("name.".length()));
    }
    return set;
  }
  
  public MVMap<String, String> getMetaMap()
  {
    checkOpen();
    return this.meta;
  }
  
  private MVMap<String, String> getMetaMap(long version)
  {
    Chunk c = getChunkForVersion(version);
    DataUtils.checkArgument(c != null, "Unknown version {0}", new Object[] { Long.valueOf(version) });
    c = readChunkHeader(c.block);
    MVMap<String, String> oldMeta = this.meta.openReadOnly();
    oldMeta.setRootPos(c.metaRootPos, version);
    return oldMeta;
  }
  
  private Chunk getChunkForVersion(long version)
  {
    Chunk c = this.lastChunk;
    for (;;)
    {
      if ((c == null) || (c.version <= version)) {
        return c;
      }
      c = (Chunk)this.chunks.get(Integer.valueOf(c.id - 1));
    }
  }
  
  public boolean hasMap(String name)
  {
    return this.meta.containsKey("name." + name);
  }
  
  private void markMetaChanged()
  {
    this.metaChanged = true;
  }
  
  private synchronized void readStoreHeader()
  {
    boolean validHeader = false;
    
    long newestVersion = -1L;
    long chunkBlock = -1L;
    
    ByteBuffer fileHeaderBlocks = this.fileStore.readFully(0L, 8192);
    byte[] buff = new byte['က'];
    for (int i = 0; i <= 4096; i += 4096)
    {
      fileHeaderBlocks.get(buff);
      try
      {
        String s = new String(buff, 0, 4096, DataUtils.LATIN).trim();
        
        HashMap<String, String> m = DataUtils.parseMap(s);
        int blockSize = DataUtils.readHexInt(m, "blockSize", 4096);
        if (blockSize != 4096) {
          throw DataUtils.newIllegalStateException(5, "Block size {0} is currently not supported", new Object[] { Integer.valueOf(blockSize) });
        }
        int check = DataUtils.readHexInt(m, "fletcher", 0);
        m.remove("fletcher");
        s = s.substring(0, s.lastIndexOf("fletcher") - 1);
        byte[] bytes = s.getBytes(DataUtils.LATIN);
        int checksum = DataUtils.getFletcher32(bytes, bytes.length);
        if (check == checksum)
        {
          long version = DataUtils.readHexLong(m, "version", 0L);
          if (version > newestVersion)
          {
            newestVersion = version;
            this.storeHeader.putAll(m);
            chunkBlock = DataUtils.readHexLong(m, "block", 0L);
            this.creationTime = DataUtils.readHexLong(m, "created", 0L);
            validHeader = true;
          }
        }
      }
      catch (Exception e) {}
    }
    if (!validHeader) {
      throw DataUtils.newIllegalStateException(6, "Store header is corrupt: {0}", new Object[] { this.fileStore });
    }
    long format = DataUtils.readHexLong(this.storeHeader, "format", 1L);
    if ((format > 1L) && (!this.fileStore.isReadOnly())) {
      throw DataUtils.newIllegalStateException(5, "The write format {0} is larger than the supported format {1}, and the file was not opened in read-only mode", new Object[] { Long.valueOf(format), Integer.valueOf(1) });
    }
    format = DataUtils.readHexLong(this.storeHeader, "formatRead", format);
    if (format > 1L) {
      throw DataUtils.newIllegalStateException(5, "The read format {0} is larger than the supported format {1}", new Object[] { Long.valueOf(format), Integer.valueOf(1) });
    }
    this.lastStoredVersion = -1L;
    this.chunks.clear();
    long now = System.currentTimeMillis();
    
    int year = 1970 + (int)(now / 31557600000L);
    if (year < 2014)
    {
      this.creationTime = (now - this.fileStore.getDefaultRetentionTime());
    }
    else if (now < this.creationTime)
    {
      this.creationTime = now;
      this.storeHeader.put("created", Long.valueOf(this.creationTime));
    }
    Chunk footer = readChunkFooter(this.fileStore.size());
    if ((footer != null) && 
      (footer.version > newestVersion))
    {
      newestVersion = footer.version;
      chunkBlock = footer.block;
    }
    if (chunkBlock <= 0L) {
      return;
    }
    this.lastChunk = null;
    for (;;)
    {
      Chunk header;
      try
      {
        header = readChunkHeader(chunkBlock);
      }
      catch (Exception e)
      {
        break;
      }
      if (header.version < newestVersion) {
        break;
      }
      footer = readChunkFooter((chunkBlock + header.len) * 4096L);
      if ((footer == null) || (footer.id != header.id)) {
        break;
      }
      this.lastChunk = header;
      newestVersion = header.version;
      if ((header.next == 0L) || (header.next >= this.fileStore.size() / 4096L)) {
        break;
      }
      chunkBlock = header.next;
    }
    if (this.lastChunk == null) {
      return;
    }
    this.lastMapId = this.lastChunk.mapId;
    this.currentVersion = this.lastChunk.version;
    setWriteVersion(this.currentVersion);
    this.chunks.put(Integer.valueOf(this.lastChunk.id), this.lastChunk);
    this.meta.setRootPos(this.lastChunk.metaRootPos, -1L);
    for (Iterator<String> it = this.meta.keyIterator("chunk."); it.hasNext();)
    {
      String s = (String)it.next();
      if (!s.startsWith("chunk.")) {
        break;
      }
      s = (String)this.meta.get(s);
      Chunk c = Chunk.fromString(s);
      if (!this.chunks.containsKey(Integer.valueOf(c.id)))
      {
        if (c.block == Long.MAX_VALUE) {
          throw DataUtils.newIllegalStateException(6, "Chunk {0} is invalid", new Object[] { Integer.valueOf(c.id) });
        }
        this.chunks.put(Integer.valueOf(c.id), c);
      }
    }
    for (Chunk c : this.chunks.values())
    {
      if (c.pageCountLive == 0) {
        registerFreePage(this.currentVersion, c.id, 0L, 0);
      }
      long start = c.block * 4096L;
      int length = c.len * 4096;
      this.fileStore.markUsed(start, length);
    }
  }
  
  private Chunk readChunkFooter(long end)
  {
    try
    {
      ByteBuffer lastBlock = this.fileStore.readFully(end - 128L, 128);
      
      byte[] buff = new byte[''];
      lastBlock.get(buff);
      String s = new String(buff, DataUtils.LATIN).trim();
      HashMap<String, String> m = DataUtils.parseMap(s);
      int check = DataUtils.readHexInt(m, "fletcher", 0);
      m.remove("fletcher");
      s = s.substring(0, s.lastIndexOf("fletcher") - 1);
      byte[] bytes = s.getBytes(DataUtils.LATIN);
      int checksum = DataUtils.getFletcher32(bytes, bytes.length);
      if (check == checksum)
      {
        int chunk = DataUtils.readHexInt(m, "chunk", 0);
        Chunk c = new Chunk(chunk);
        c.version = DataUtils.readHexLong(m, "version", 0L);
        c.block = DataUtils.readHexLong(m, "block", 0L);
        return c;
      }
    }
    catch (Exception e) {}
    return null;
  }
  
  private void writeStoreHeader()
  {
    StringBuilder buff = new StringBuilder();
    if (this.lastChunk != null)
    {
      this.storeHeader.put("block", Long.valueOf(this.lastChunk.block));
      this.storeHeader.put("chunk", Integer.valueOf(this.lastChunk.id));
      this.storeHeader.put("version", Long.valueOf(this.lastChunk.version));
    }
    DataUtils.appendMap(buff, this.storeHeader);
    byte[] bytes = buff.toString().getBytes(DataUtils.LATIN);
    int checksum = DataUtils.getFletcher32(bytes, bytes.length);
    DataUtils.appendMap(buff, "fletcher", Integer.valueOf(checksum));
    buff.append("\n");
    bytes = buff.toString().getBytes(DataUtils.LATIN);
    ByteBuffer header = ByteBuffer.allocate(8192);
    header.put(bytes);
    header.position(4096);
    header.put(bytes);
    header.rewind();
    write(0L, header);
  }
  
  private void write(long pos, ByteBuffer buffer)
  {
    try
    {
      this.fileStore.writeFully(pos, buffer);
    }
    catch (IllegalStateException e)
    {
      panic(e);
      throw e;
    }
  }
  
  public void close()
  {
    if (this.closed) {
      return;
    }
    if ((this.fileStore != null) && (!this.fileStore.isReadOnly()))
    {
      stopBackgroundThread();
      if (hasUnsavedChanges()) {
        commitAndSave();
      }
    }
    closeStore(true);
  }
  
  public void closeImmediately()
  {
    try
    {
      closeStore(false);
    }
    catch (Exception e)
    {
      if (this.backgroundExceptionHandler != null) {
        this.backgroundExceptionHandler.uncaughtException(null, e);
      }
    }
  }
  
  private void closeStore(boolean shrinkIfPossible)
  {
    if (this.closed) {
      return;
    }
    stopBackgroundThread();
    this.closed = true;
    if (this.fileStore == null) {
      return;
    }
    synchronized (this)
    {
      if (shrinkIfPossible) {
        shrinkFileIfPossible(0);
      }
      this.cache = null;
      this.cacheChunkRef = null;
      for (MVMap<?, ?> m : New.arrayList(this.maps.values())) {
        m.close();
      }
      this.meta = null;
      this.chunks.clear();
      this.maps.clear();
      try
      {
        if (!this.fileStoreIsProvided) {
          this.fileStore.close();
        }
      }
      finally
      {
        this.fileStore = null;
      }
    }
  }
  
  boolean isChunkLive(int chunkId)
  {
    String s = (String)this.meta.get(Chunk.getMetaKey(chunkId));
    return s != null;
  }
  
  private Chunk getChunk(long pos)
  {
    Chunk c = getChunkIfFound(pos);
    if (c == null)
    {
      int chunkId = DataUtils.getPageChunkId(pos);
      throw DataUtils.newIllegalStateException(6, "Chunk {0} not found", new Object[] { Integer.valueOf(chunkId) });
    }
    return c;
  }
  
  private Chunk getChunkIfFound(long pos)
  {
    int chunkId = DataUtils.getPageChunkId(pos);
    Chunk c = (Chunk)this.chunks.get(Integer.valueOf(chunkId));
    if (c == null)
    {
      checkOpen();
      if (!Thread.holdsLock(this)) {
        throw DataUtils.newIllegalStateException(9, "Chunk {0} no longer exists", new Object[] { Integer.valueOf(chunkId) });
      }
      String s = (String)this.meta.get(Chunk.getMetaKey(chunkId));
      if (s == null) {
        return null;
      }
      c = Chunk.fromString(s);
      if (c.block == Long.MAX_VALUE) {
        throw DataUtils.newIllegalStateException(6, "Chunk {0} is invalid", new Object[] { Integer.valueOf(chunkId) });
      }
      this.chunks.put(Integer.valueOf(c.id), c);
    }
    return c;
  }
  
  private void setWriteVersion(long version)
  {
    for (MVMap<?, ?> map : this.maps.values()) {
      map.setWriteVersion(version);
    }
    this.meta.setWriteVersion(version);
  }
  
  public long commit()
  {
    if (this.fileStore != null) {
      return commitAndSave();
    }
    long v = ++this.currentVersion;
    setWriteVersion(v);
    return v;
  }
  
  private synchronized long commitAndSave()
  {
    if (this.closed) {
      return this.currentVersion;
    }
    if (this.fileStore == null) {
      throw DataUtils.newIllegalStateException(2, "This is an in-memory store", new Object[0]);
    }
    if (this.currentStoreVersion >= 0L) {
      return this.currentVersion;
    }
    if (!hasUnsavedChanges()) {
      return this.currentVersion;
    }
    if (this.fileStore.isReadOnly()) {
      throw DataUtils.newIllegalStateException(2, "This store is read-only", new Object[0]);
    }
    try
    {
      this.currentStoreVersion = this.currentVersion;
      this.currentStoreThread = Thread.currentThread();
      return storeNow();
    }
    finally
    {
      this.currentStoreVersion = -1L;
      this.currentStoreThread = null;
    }
  }
  
  private long storeNow()
  {
    try
    {
      return storeNowTry();
    }
    catch (IllegalStateException e)
    {
      panic(e);
    }
    return -1L;
  }
  
  private long storeNowTry()
  {
    freeUnusedChunks();
    int currentUnsavedPageCount = this.unsavedMemory;
    long storeVersion = this.currentStoreVersion;
    long version = ++this.currentVersion;
    setWriteVersion(version);
    long time = getTime();
    this.lastCommitTime = time;
    this.retainChunk = null;
    int lastChunkId;
    int lastChunkId;
    if (this.lastChunk == null)
    {
      lastChunkId = 0;
    }
    else
    {
      lastChunkId = this.lastChunk.id;
      this.meta.put(Chunk.getMetaKey(lastChunkId), this.lastChunk.asString());
      
      time = Math.max(this.lastChunk.time, time);
    }
    int newChunkId = lastChunkId;
    do
    {
      newChunkId = (newChunkId + 1) % 67108863;
    } while (this.chunks.containsKey(Integer.valueOf(newChunkId)));
    Chunk c = new Chunk(newChunkId);
    
    c.pageCount = Integer.MAX_VALUE;
    c.pageCountLive = Integer.MAX_VALUE;
    c.maxLen = Long.MAX_VALUE;
    c.maxLenLive = Long.MAX_VALUE;
    c.metaRootPos = Long.MAX_VALUE;
    c.block = Long.MAX_VALUE;
    c.len = Integer.MAX_VALUE;
    c.time = time;
    c.version = version;
    c.mapId = this.lastMapId;
    c.next = Long.MAX_VALUE;
    this.chunks.put(Integer.valueOf(c.id), c);
    
    this.meta.put(Chunk.getMetaKey(c.id), c.asString());
    this.meta.remove(Chunk.getMetaKey(c.id));
    ArrayList<MVMap<?, ?>> list = New.arrayList(this.maps.values());
    ArrayList<MVMap<?, ?>> changed = New.arrayList();
    for (MVMap<?, ?> m : list)
    {
      m.setWriteVersion(version);
      long v = m.getVersion();
      if ((m.getCreateVersion() <= storeVersion) && 
      
        (!m.isVolatile())) {
        if ((v >= 0L) && (v >= this.lastStoredVersion))
        {
          MVMap<?, ?> r = m.openVersion(storeVersion);
          if (r.getRoot().getPos() == 0L) {
            changed.add(r);
          }
        }
      }
    }
    applyFreedSpace(storeVersion);
    WriteBuffer buff = getWriteBuffer();
    
    c.writeChunkHeader(buff, 0);
    int headerLength = buff.position();
    c.pageCount = 0;
    c.pageCountLive = 0;
    c.maxLen = 0L;
    c.maxLenLive = 0L;
    for (MVMap<?, ?> m : changed)
    {
      Page p = m.getRoot();
      String key = MVMap.getMapRootKey(m.getId());
      if (p.getTotalCount() == 0L)
      {
        this.meta.put(key, "0");
      }
      else
      {
        p.writeUnsavedRecursive(c, buff);
        long root = p.getPos();
        this.meta.put(key, Long.toHexString(root));
      }
    }
    this.meta.setWriteVersion(version);
    
    Page metaRoot = this.meta.getRoot();
    metaRoot.writeUnsavedRecursive(c, buff);
    
    int chunkLength = buff.position();
    
    int length = MathUtils.roundUpInt(chunkLength + 128, 4096);
    
    buff.limit(length);
    
    long end = getFileLengthInUse();
    long filePos;
    long filePos;
    if (this.reuseSpace) {
      filePos = this.fileStore.allocate(length);
    } else {
      filePos = end;
    }
    boolean storeAtEndOfFile = filePos + length >= this.fileStore.size();
    if (!this.reuseSpace) {
      this.fileStore.markUsed(end, length);
    }
    c.block = (filePos / 4096L);
    c.len = (length / 4096);
    c.metaRootPos = metaRoot.getPos();
    if (this.reuseSpace)
    {
      int predictBlocks = c.len;
      long predictedNextStart = this.fileStore.allocate(predictBlocks * 4096);
      
      this.fileStore.free(predictedNextStart, predictBlocks * 4096);
      c.next = (predictedNextStart / 4096L);
    }
    else
    {
      c.next = 0L;
    }
    buff.position(0);
    c.writeChunkHeader(buff, headerLength);
    revertTemp(storeVersion);
    
    buff.position(buff.limit() - 128);
    buff.put(c.getFooterBytes());
    
    buff.position(0);
    write(filePos, buff.getBuffer());
    releaseWriteBuffer(buff);
    
    boolean writeStoreHeader = false;
    if (!storeAtEndOfFile) {
      if (this.lastChunk == null)
      {
        writeStoreHeader = true;
      }
      else if (this.lastChunk.next != c.block)
      {
        writeStoreHeader = true;
      }
      else
      {
        long headerVersion = DataUtils.readHexLong(this.storeHeader, "version", 0L);
        if (this.lastChunk.version - headerVersion > 20L)
        {
          writeStoreHeader = true;
        }
        else
        {
          int chunkId = DataUtils.readHexInt(this.storeHeader, "chunk", 0);
          for (;;)
          {
            Chunk old = (Chunk)this.chunks.get(Integer.valueOf(chunkId));
            if (old == null)
            {
              writeStoreHeader = true;
              break;
            }
            if (chunkId == this.lastChunk.id) {
              break;
            }
            chunkId++;
          }
        }
      }
    }
    this.lastChunk = c;
    if (writeStoreHeader) {
      writeStoreHeader();
    }
    if (!storeAtEndOfFile) {
      shrinkFileIfPossible(1);
    }
    for (MVMap<?, ?> m : changed)
    {
      Page p = m.getRoot();
      if (p.getTotalCount() > 0L) {
        p.writeEnd();
      }
    }
    metaRoot.writeEnd();
    
    this.unsavedMemory = Math.max(0, this.unsavedMemory - currentUnsavedPageCount);
    
    this.metaChanged = false;
    this.lastStoredVersion = storeVersion;
    
    return version;
  }
  
  private synchronized void freeUnusedChunks()
  {
    if ((this.lastChunk == null) || (!this.reuseSpace)) {
      return;
    }
    Set<Integer> referenced = collectReferencedChunks();
    ArrayList<Chunk> free = New.arrayList();
    long time = getTime();
    for (Chunk c : this.chunks.values()) {
      if (!referenced.contains(Integer.valueOf(c.id))) {
        free.add(c);
      }
    }
    for (Chunk c : free) {
      if (canOverwriteChunk(c, time))
      {
        this.chunks.remove(Integer.valueOf(c.id));
        markMetaChanged();
        this.meta.remove(Chunk.getMetaKey(c.id));
        long start = c.block * 4096L;
        int length = c.len * 4096;
        this.fileStore.free(start, length);
      }
      else if (c.unused == 0L)
      {
        c.unused = time;
        this.meta.put(Chunk.getMetaKey(c.id), c.asString());
        markMetaChanged();
      }
    }
  }
  
  private Set<Integer> collectReferencedChunks()
  {
    long testVersion = this.lastChunk.version;
    DataUtils.checkArgument(testVersion > 0L, "Collect references on version 0", new Object[0]);
    long readCount = getFileStore().readCount;
    Set<Integer> referenced = New.hashSet();
    for (Cursor<String, String> c = this.meta.cursor("root."); c.hasNext();)
    {
      String key = (String)c.next();
      if (!key.startsWith("root.")) {
        break;
      }
      long pos = DataUtils.parseHexLong((String)c.getValue());
      if (pos != 0L)
      {
        int mapId = DataUtils.parseHexInt(key.substring("root.".length()));
        collectReferencedChunks(referenced, mapId, pos, 0);
      }
    }
    long pos = this.lastChunk.metaRootPos;
    collectReferencedChunks(referenced, 0, pos, 0);
    readCount = this.fileStore.readCount - readCount;
    return referenced;
  }
  
  private void collectReferencedChunks(Set<Integer> targetChunkSet, int mapId, long pos, int level)
  {
    int c = DataUtils.getPageChunkId(pos);
    targetChunkSet.add(Integer.valueOf(c));
    if (DataUtils.getPageType(pos) == 0) {
      return;
    }
    Page.PageChildren refs = readPageChunkReferences(mapId, pos, -1);
    if (!refs.chunkList)
    {
      Set<Integer> target = New.hashSet();
      for (int i = 0; i < refs.children.length; i++)
      {
        long p = refs.children[i];
        collectReferencedChunks(target, mapId, p, level + 1);
      }
      target.remove(Integer.valueOf(c));
      long[] children = new long[target.size()];
      int i = 0;
      for (Integer p : target) {
        children[(i++)] = DataUtils.getPagePos(p.intValue(), 0, 0, 0);
      }
      refs.children = children;
      refs.chunkList = true;
      if (this.cacheChunkRef != null) {
        this.cacheChunkRef.put(refs.pos, refs, refs.getMemory());
      }
    }
    for (long p : refs.children) {
      targetChunkSet.add(Integer.valueOf(DataUtils.getPageChunkId(p)));
    }
  }
  
  private Page.PageChildren readPageChunkReferences(int mapId, long pos, int parentChunk)
  {
    if (DataUtils.getPageType(pos) == 0) {
      return null;
    }
    Page.PageChildren r;
    Page.PageChildren r;
    if (this.cacheChunkRef != null) {
      r = (Page.PageChildren)this.cacheChunkRef.get(pos);
    } else {
      r = null;
    }
    if (r == null)
    {
      if (this.cache != null)
      {
        Page p = (Page)this.cache.get(pos);
        if (p != null) {
          r = new Page.PageChildren(p);
        }
      }
      if (r == null)
      {
        Chunk c = getChunk(pos);
        long filePos = c.block * 4096L;
        filePos += DataUtils.getPageOffset(pos);
        if (filePos < 0L) {
          throw DataUtils.newIllegalStateException(6, "Negative position {0}; p={1}, c={2}", new Object[] { Long.valueOf(filePos), Long.valueOf(pos), c.toString() });
        }
        long maxPos = (c.block + c.len) * 4096L;
        r = Page.PageChildren.read(this.fileStore, pos, mapId, filePos, maxPos);
      }
      r.removeDuplicateChunkReferences();
      if (this.cacheChunkRef != null) {
        this.cacheChunkRef.put(pos, r, r.getMemory());
      }
    }
    if (r.children.length == 0)
    {
      int chunk = DataUtils.getPageChunkId(pos);
      if (chunk == parentChunk) {
        return null;
      }
    }
    return r;
  }
  
  private WriteBuffer getWriteBuffer()
  {
    WriteBuffer buff;
    if (this.writeBuffer != null)
    {
      WriteBuffer buff = this.writeBuffer;
      buff.clear();
    }
    else
    {
      buff = new WriteBuffer();
    }
    return buff;
  }
  
  private void releaseWriteBuffer(WriteBuffer buff)
  {
    if (buff.capacity() <= 4194304) {
      this.writeBuffer = buff;
    }
  }
  
  private boolean canOverwriteChunk(Chunk c, long time)
  {
    if (c.time + this.retentionTime > time) {
      return false;
    }
    if ((c.unused == 0L) || (c.unused + this.retentionTime / 2 > time)) {
      return false;
    }
    Chunk r = this.retainChunk;
    if ((r != null) && (c.version > r.version)) {
      return false;
    }
    return true;
  }
  
  private long getTime()
  {
    return System.currentTimeMillis() - this.creationTime;
  }
  
  private void applyFreedSpace(long storeVersion)
  {
    for (;;)
    {
      ArrayList<Chunk> modified = New.arrayList();
      
      Iterator<Map.Entry<Long, HashMap<Integer, Chunk>>> it = this.freedPageSpace.entrySet().iterator();
      while (it.hasNext())
      {
        Map.Entry<Long, HashMap<Integer, Chunk>> e = (Map.Entry)it.next();
        long v = ((Long)e.getKey()).longValue();
        if (v <= storeVersion)
        {
          HashMap<Integer, Chunk> freed = (HashMap)e.getValue();
          for (Chunk f : freed.values())
          {
            Chunk c = (Chunk)this.chunks.get(Integer.valueOf(f.id));
            if (c != null)
            {
              c.maxLenLive += f.maxLenLive;
              c.pageCountLive += f.pageCountLive;
              if ((c.pageCountLive < 0) && (c.pageCountLive > -10000000)) {
                throw DataUtils.newIllegalStateException(3, "Corrupt page count {0}", new Object[] { Integer.valueOf(c.pageCountLive) });
              }
              if ((c.maxLenLive < 0L) && (c.maxLenLive > -10000000L)) {
                throw DataUtils.newIllegalStateException(3, "Corrupt max length {0}", new Object[] { Long.valueOf(c.maxLenLive) });
              }
              if (((c.pageCountLive <= 0) && (c.maxLenLive > 0L)) || ((c.maxLenLive <= 0L) && (c.pageCountLive > 0))) {
                throw DataUtils.newIllegalStateException(3, "Corrupt max length {0}", new Object[] { Long.valueOf(c.maxLenLive) });
              }
              modified.add(c);
            }
          }
          it.remove();
        }
      }
      for (Chunk c : modified) {
        this.meta.put(Chunk.getMetaKey(c.id), c.asString());
      }
      if (modified.size() == 0) {
        break;
      }
    }
  }
  
  private void shrinkFileIfPossible(int minPercent)
  {
    long end = getFileLengthInUse();
    long fileSize = this.fileStore.size();
    if (end >= fileSize) {
      return;
    }
    if ((minPercent > 0) && (fileSize - end < 4096L)) {
      return;
    }
    int savedPercent = (int)(100L - end * 100L / fileSize);
    if (savedPercent < minPercent) {
      return;
    }
    this.fileStore.truncate(end);
  }
  
  private long getFileLengthInUse()
  {
    long size = 8192L;
    for (Chunk c : this.chunks.values()) {
      if (c.len != Integer.MAX_VALUE)
      {
        long x = (c.block + c.len) * 4096L;
        size = Math.max(size, x);
      }
    }
    return size;
  }
  
  public boolean hasUnsavedChanges()
  {
    checkOpen();
    if (this.metaChanged) {
      return true;
    }
    for (MVMap<?, ?> m : this.maps.values()) {
      if (!m.isClosed())
      {
        long v = m.getVersion();
        if ((v >= 0L) && (v > this.lastStoredVersion)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private Chunk readChunkHeader(long block)
  {
    long p = block * 4096L;
    ByteBuffer buff = this.fileStore.readFully(p, 1024);
    return Chunk.readChunkHeader(buff, p);
  }
  
  public synchronized boolean compactRewriteFully()
  {
    checkOpen();
    if (this.lastChunk == null) {
      return false;
    }
    for (MVMap<?, ?> m : this.maps.values())
    {
      MVMap<Object, Object> map = m;
      Cursor<Object, Object> cursor = map.cursor(null);
      Page lastPage = null;
      while (cursor.hasNext())
      {
        cursor.next();
        Page p = cursor.getPage();
        if (p != lastPage)
        {
          Object k = p.getKey(0);
          Object v = p.getValue(0);
          map.put(k, v);
          lastPage = p;
        }
      }
    }
    commitAndSave();
    return true;
  }
  
  public synchronized boolean compactMoveChunks()
  {
    return compactMoveChunks(100, Long.MAX_VALUE);
  }
  
  public synchronized boolean compactMoveChunks(int targetFillRate, long moveSize)
  {
    checkOpen();
    if ((this.lastChunk == null) || (!this.reuseSpace)) {
      return false;
    }
    int oldRetentionTime = this.retentionTime;
    boolean oldReuse = this.reuseSpace;
    try
    {
      this.retentionTime = 0;
      freeUnusedChunks();
      if (this.fileStore.getFillRate() > targetFillRate) {
        return false;
      }
      long start = this.fileStore.getFirstFree() / 4096L;
      ArrayList<Chunk> move = compactGetMoveBlocks(start, moveSize);
      compactMoveChunks(move);
      freeUnusedChunks();
      storeNow();
    }
    finally
    {
      this.reuseSpace = oldReuse;
      this.retentionTime = oldRetentionTime;
    }
    return true;
  }
  
  private ArrayList<Chunk> compactGetMoveBlocks(long startBlock, long moveSize)
  {
    ArrayList<Chunk> move = New.arrayList();
    for (Chunk c : this.chunks.values()) {
      if (c.block > startBlock) {
        move.add(c);
      }
    }
    Collections.sort(move, new Comparator()
    {
      public int compare(Chunk o1, Chunk o2)
      {
        return Long.signum(o1.block - o2.block);
      }
    });
    int count = 0;
    long size = 0L;
    for (Chunk c : move)
    {
      long chunkSize = c.len * 4096L;
      if (size + chunkSize > moveSize) {
        break;
      }
      size += chunkSize;
      count++;
    }
    while ((move.size() > count) && (move.size() > 1)) {
      move.remove(1);
    }
    return move;
  }
  
  private void compactMoveChunks(ArrayList<Chunk> move)
  {
    for (Chunk c : move)
    {
      WriteBuffer buff = getWriteBuffer();
      long start = c.block * 4096L;
      int length = c.len * 4096;
      buff.limit(length);
      ByteBuffer readBuff = this.fileStore.readFully(start, length);
      Chunk.readChunkHeader(readBuff, start);
      int chunkHeaderLen = readBuff.position();
      buff.position(chunkHeaderLen);
      buff.put(readBuff);
      long end = getFileLengthInUse();
      this.fileStore.markUsed(end, length);
      this.fileStore.free(start, length);
      c.block = (end / 4096L);
      c.next = 0L;
      buff.position(0);
      c.writeChunkHeader(buff, chunkHeaderLen);
      buff.position(length - 128);
      buff.put(this.lastChunk.getFooterBytes());
      buff.position(0);
      write(end, buff.getBuffer());
      releaseWriteBuffer(buff);
      markMetaChanged();
      this.meta.put(Chunk.getMetaKey(c.id), c.asString());
    }
    this.reuseSpace = false;
    commitAndSave();
    
    sync();
    
    this.reuseSpace = true;
    for (Chunk c : move) {
      if (this.chunks.containsKey(Integer.valueOf(c.id)))
      {
        WriteBuffer buff = getWriteBuffer();
        long start = c.block * 4096L;
        int length = c.len * 4096;
        buff.limit(length);
        ByteBuffer readBuff = this.fileStore.readFully(start, length);
        Chunk.readChunkHeader(readBuff, 0L);
        int chunkHeaderLen = readBuff.position();
        buff.position(chunkHeaderLen);
        buff.put(readBuff);
        long pos = this.fileStore.allocate(length);
        this.fileStore.free(start, length);
        buff.position(0);
        c.block = (pos / 4096L);
        c.writeChunkHeader(buff, chunkHeaderLen);
        buff.position(length - 128);
        buff.put(this.lastChunk.getFooterBytes());
        buff.position(0);
        write(pos, buff.getBuffer());
        releaseWriteBuffer(buff);
        markMetaChanged();
        this.meta.put(Chunk.getMetaKey(c.id), c.asString());
      }
    }
    commitAndSave();
    sync();
    shrinkFileIfPossible(0);
  }
  
  public void sync()
  {
    this.fileStore.sync();
  }
  
  public boolean compact(int targetFillRate, int write)
  {
    if (!this.reuseSpace) {
      return false;
    }
    synchronized (this.compactSync)
    {
      checkOpen();
      ArrayList<Chunk> old;
      synchronized (this)
      {
        old = compactGetOldChunks(targetFillRate, write);
      }
      if ((old == null) || (old.size() == 0)) {
        return false;
      }
      compactRewrite(old);
      return true;
    }
  }
  
  private ArrayList<Chunk> compactGetOldChunks(int targetFillRate, int write)
  {
    if (this.lastChunk == null) {
      return null;
    }
    long maxLengthSum = 0L;
    long maxLengthLiveSum = 0L;
    
    long time = getTime();
    for (Chunk c : this.chunks.values()) {
      if (c.time + this.retentionTime <= time)
      {
        maxLengthSum += c.maxLen;
        maxLengthLiveSum += c.maxLenLive;
      }
    }
    if (maxLengthLiveSum < 0L) {
      return null;
    }
    if (maxLengthSum <= 0L) {
      maxLengthSum = 1L;
    }
    int fillRate = (int)(100L * maxLengthLiveSum / maxLengthSum);
    if (fillRate >= targetFillRate) {
      return null;
    }
    ArrayList<Chunk> old = New.arrayList();
    Chunk last = (Chunk)this.chunks.get(Integer.valueOf(this.lastChunk.id));
    for (Chunk c : this.chunks.values()) {
      if (c.time + this.retentionTime <= time)
      {
        long age = last.version - c.version + 1L;
        c.collectPriority = ((int)(c.getFillRate() * 1000 / age));
        old.add(c);
      }
    }
    if (old.size() == 0) {
      return null;
    }
    Collections.sort(old, new Comparator()
    {
      public int compare(Chunk o1, Chunk o2)
      {
        int comp = new Integer(o1.collectPriority).compareTo(Integer.valueOf(o2.collectPriority));
        if (comp == 0) {
          comp = new Long(o1.maxLenLive).compareTo(Long.valueOf(o2.maxLenLive));
        }
        return comp;
      }
    });
    long written = 0L;
    int chunkCount = 0;
    Chunk move = null;
    for (Chunk c : old)
    {
      if ((move != null) && 
        (c.collectPriority > 0) && (written > write)) {
        break;
      }
      written += c.maxLenLive;
      chunkCount++;
      move = c;
    }
    if (chunkCount < 1) {
      return null;
    }
    boolean remove = false;
    for (Iterator<Chunk> it = old.iterator(); it.hasNext();)
    {
      Chunk c = (Chunk)it.next();
      if (move == c) {
        remove = true;
      } else if (remove) {
        it.remove();
      }
    }
    return old;
  }
  
  private void compactRewrite(ArrayList<Chunk> old)
  {
    HashSet<Integer> set = New.hashSet();
    for (Chunk c : old) {
      set.add(Integer.valueOf(c.id));
    }
    for (MVMap<?, ?> m : this.maps.values())
    {
      MVMap<Object, Object> map = m;
      if (!map.rewrite(set)) {
        return;
      }
    }
    if (!this.meta.rewrite(set)) {
      return;
    }
    freeUnusedChunks();
    commitAndSave();
  }
  
  Page readPage(MVMap<?, ?> map, long pos)
  {
    if (pos == 0L) {
      throw DataUtils.newIllegalStateException(6, "Position 0", new Object[0]);
    }
    Page p = this.cache == null ? null : (Page)this.cache.get(pos);
    if (p == null)
    {
      Chunk c = getChunk(pos);
      long filePos = c.block * 4096L;
      filePos += DataUtils.getPageOffset(pos);
      if (filePos < 0L) {
        throw DataUtils.newIllegalStateException(6, "Negative position {0}", new Object[] { Long.valueOf(filePos) });
      }
      long maxPos = (c.block + c.len) * 4096L;
      p = Page.read(this.fileStore, pos, map, filePos, maxPos);
      cachePage(pos, p, p.getMemory());
    }
    return p;
  }
  
  void removePage(MVMap<?, ?> map, long pos, int memory)
  {
    if (pos == 0L)
    {
      this.unsavedMemory = Math.max(0, this.unsavedMemory - memory);
      return;
    }
    if ((this.cache != null) && 
      (DataUtils.getPageType(pos) == 0)) {
      this.cache.remove(pos);
    }
    Chunk c = getChunk(pos);
    long version = this.currentVersion;
    if ((map == this.meta) && (this.currentStoreVersion >= 0L) && 
      (Thread.currentThread() == this.currentStoreThread)) {
      version = this.currentStoreVersion;
    }
    registerFreePage(version, c.id, DataUtils.getPageMaxLength(pos), 1);
  }
  
  private void registerFreePage(long version, int chunkId, long maxLengthLive, int pageCount)
  {
    HashMap<Integer, Chunk> freed = (HashMap)this.freedPageSpace.get(Long.valueOf(version));
    if (freed == null)
    {
      freed = New.hashMap();
      HashMap<Integer, Chunk> f2 = (HashMap)this.freedPageSpace.putIfAbsent(Long.valueOf(version), freed);
      if (f2 != null) {
        freed = f2;
      }
    }
    synchronized (freed)
    {
      Chunk f = (Chunk)freed.get(Integer.valueOf(chunkId));
      if (f == null)
      {
        f = new Chunk(chunkId);
        freed.put(Integer.valueOf(chunkId), f);
      }
      f.maxLenLive -= maxLengthLive;
      f.pageCountLive -= pageCount;
    }
  }
  
  Compressor getCompressorFast()
  {
    if (this.compressorFast == null) {
      this.compressorFast = new CompressLZF();
    }
    return this.compressorFast;
  }
  
  Compressor getCompressorHigh()
  {
    if (this.compressorHigh == null) {
      this.compressorHigh = new CompressDeflate();
    }
    return this.compressorHigh;
  }
  
  int getCompressionLevel()
  {
    return this.compressionLevel;
  }
  
  public int getPageSplitSize()
  {
    return this.pageSplitSize;
  }
  
  public boolean getReuseSpace()
  {
    return this.reuseSpace;
  }
  
  public void setReuseSpace(boolean reuseSpace)
  {
    this.reuseSpace = reuseSpace;
  }
  
  public int getRetentionTime()
  {
    return this.retentionTime;
  }
  
  public void setRetentionTime(int ms)
  {
    this.retentionTime = ms;
  }
  
  public void setVersionsToKeep(int count)
  {
    this.versionsToKeep = count;
  }
  
  public long getVersionsToKeep()
  {
    return this.versionsToKeep;
  }
  
  long getOldestVersionToKeep()
  {
    long v = this.currentVersion;
    if (this.fileStore == null) {
      return v - this.versionsToKeep;
    }
    long storeVersion = this.currentStoreVersion;
    if (storeVersion > -1L) {
      v = Math.min(v, storeVersion);
    }
    return v;
  }
  
  private boolean isKnownVersion(long version)
  {
    if ((version > this.currentVersion) || (version < 0L)) {
      return false;
    }
    if ((version == this.currentVersion) || (this.chunks.size() == 0)) {
      return true;
    }
    Chunk c = getChunkForVersion(version);
    if (c == null) {
      return false;
    }
    MVMap<String, String> oldMeta = getMetaMap(version);
    if (oldMeta == null) {
      return false;
    }
    Iterator<String> it = oldMeta.keyIterator("chunk.");
    while (it.hasNext())
    {
      String chunkKey = (String)it.next();
      if (!chunkKey.startsWith("chunk.")) {
        break;
      }
      if (!this.meta.containsKey(chunkKey)) {
        return false;
      }
    }
    return true;
  }
  
  void registerUnsavedPage(int memory)
  {
    this.unsavedMemory += memory;
    int newValue = this.unsavedMemory;
    if ((newValue > this.autoCommitMemory) && (this.autoCommitMemory > 0)) {
      this.saveNeeded = true;
    }
  }
  
  void beforeWrite(MVMap<?, ?> map)
  {
    if (this.saveNeeded)
    {
      if (map == this.meta) {
        return;
      }
      this.saveNeeded = false;
      if ((this.unsavedMemory > this.autoCommitMemory) && (this.autoCommitMemory > 0)) {
        commitAndSave();
      }
    }
  }
  
  public int getStoreVersion()
  {
    checkOpen();
    String x = (String)this.meta.get("setting.storeVersion");
    return x == null ? 0 : DataUtils.parseHexInt(x);
  }
  
  public synchronized void setStoreVersion(int version)
  {
    checkOpen();
    markMetaChanged();
    this.meta.put("setting.storeVersion", Integer.toHexString(version));
  }
  
  public void rollback()
  {
    rollbackTo(this.currentVersion);
  }
  
  public synchronized void rollbackTo(long version)
  {
    checkOpen();
    if (version == 0L)
    {
      for (MVMap<?, ?> m : this.maps.values()) {
        m.close();
      }
      this.meta.clear();
      this.chunks.clear();
      if (this.fileStore != null) {
        this.fileStore.clear();
      }
      this.maps.clear();
      this.freedPageSpace.clear();
      this.currentVersion = version;
      setWriteVersion(version);
      this.metaChanged = false;
      return;
    }
    DataUtils.checkArgument(isKnownVersion(version), "Unknown version {0}", new Object[] { Long.valueOf(version) });
    for (MVMap<?, ?> m : this.maps.values()) {
      m.rollbackTo(version);
    }
    for (long v = this.currentVersion; (v >= version) && 
          (this.freedPageSpace.size() != 0); v -= 1L) {
      this.freedPageSpace.remove(Long.valueOf(v));
    }
    this.meta.rollbackTo(version);
    this.metaChanged = false;
    boolean loadFromFile = false;
    
    Chunk removeChunksNewerThan = null;
    Chunk c = this.lastChunk;
    while ((c != null) && (c.version >= version))
    {
      removeChunksNewerThan = c;
      c = (Chunk)this.chunks.get(Integer.valueOf(c.id - 1));
    }
    Chunk last = this.lastChunk;
    if ((removeChunksNewerThan != null) && (last.version > removeChunksNewerThan.version))
    {
      revertTemp(version);
      loadFromFile = true;
      for (;;)
      {
        last = this.lastChunk;
        if (last == null) {
          break;
        }
        if (last.version <= removeChunksNewerThan.version) {
          break;
        }
        this.chunks.remove(Integer.valueOf(this.lastChunk.id));
        long start = last.block * 4096L;
        int length = last.len * 4096;
        this.fileStore.free(start, length);
        
        WriteBuffer buff = getWriteBuffer();
        buff.limit(length);
        
        Arrays.fill(buff.getBuffer().array(), (byte)0);
        write(start, buff.getBuffer());
        releaseWriteBuffer(buff);
        this.lastChunk = ((Chunk)this.chunks.get(Integer.valueOf(this.lastChunk.id - 1)));
      }
      writeStoreHeader();
      readStoreHeader();
    }
    for (MVMap<?, ?> m : New.arrayList(this.maps.values()))
    {
      int id = m.getId();
      if (m.getCreateVersion() >= version)
      {
        m.close();
        this.maps.remove(Integer.valueOf(id));
      }
      else if (loadFromFile)
      {
        m.setRootPos(getRootPos(this.meta, id), -1L);
      }
    }
    if (this.lastChunk != null)
    {
      c = (Chunk)this.chunks.get(Integer.valueOf(this.lastChunk.id - 1));
      if (c != null) {
        this.meta.put(Chunk.getMetaKey(c.id), c.asString());
      }
    }
    this.currentVersion = version;
    setWriteVersion(version);
  }
  
  private static long getRootPos(MVMap<String, String> map, int mapId)
  {
    String root = (String)map.get(MVMap.getMapRootKey(mapId));
    return root == null ? 0L : DataUtils.parseHexLong(root);
  }
  
  private void revertTemp(long storeVersion)
  {
    Iterator<Long> it = this.freedPageSpace.keySet().iterator();
    while (it.hasNext())
    {
      long v = ((Long)it.next()).longValue();
      if (v <= storeVersion) {
        it.remove();
      }
    }
    for (MVMap<?, ?> m : this.maps.values()) {
      m.removeUnusedOldVersions();
    }
  }
  
  public long getCurrentVersion()
  {
    return this.currentVersion;
  }
  
  public FileStore getFileStore()
  {
    return this.fileStore;
  }
  
  public Map<String, Object> getStoreHeader()
  {
    return this.storeHeader;
  }
  
  private void checkOpen()
  {
    if (this.closed) {
      throw DataUtils.newIllegalStateException(4, "This store is closed", new Object[] { this.panicException });
    }
  }
  
  public synchronized void renameMap(MVMap<?, ?> map, String newName)
  {
    checkOpen();
    DataUtils.checkArgument(map != this.meta, "Renaming the meta map is not allowed", new Object[0]);
    
    int id = map.getId();
    String oldName = getMapName(id);
    if (oldName.equals(newName)) {
      return;
    }
    DataUtils.checkArgument(!this.meta.containsKey("name." + newName), "A map named {0} already exists", new Object[] { newName });
    
    markMetaChanged();
    String x = Integer.toHexString(id);
    this.meta.remove("name." + oldName);
    this.meta.put(MVMap.getMapKey(id), map.asString(newName));
    this.meta.put("name." + newName, x);
  }
  
  public synchronized void removeMap(MVMap<?, ?> map)
  {
    checkOpen();
    DataUtils.checkArgument(map != this.meta, "Removing the meta map is not allowed", new Object[0]);
    
    map.clear();
    int id = map.getId();
    String name = getMapName(id);
    markMetaChanged();
    this.meta.remove(MVMap.getMapKey(id));
    this.meta.remove("name." + name);
    this.meta.remove(MVMap.getMapRootKey(id));
    this.maps.remove(Integer.valueOf(id));
  }
  
  public synchronized String getMapName(int id)
  {
    checkOpen();
    String m = (String)this.meta.get(MVMap.getMapKey(id));
    return m == null ? null : (String)DataUtils.parseMap(m).get("name");
  }
  
  void writeInBackground()
  {
    if (this.closed) {
      return;
    }
    long time = getTime();
    if (time <= this.lastCommitTime + this.autoCommitDelay) {
      return;
    }
    if (hasUnsavedChanges()) {
      try
      {
        commitAndSave();
      }
      catch (Exception e)
      {
        if (this.backgroundExceptionHandler != null)
        {
          this.backgroundExceptionHandler.uncaughtException(null, e);
          return;
        }
      }
    }
    if (this.autoCompactFillRate > 0) {
      try
      {
        long fileOpCount = this.fileStore.getWriteCount() + this.fileStore.getReadCount();
        boolean fileOps;
        boolean fileOps;
        if (this.autoCompactLastFileOpCount != fileOpCount) {
          fileOps = true;
        } else {
          fileOps = false;
        }
        int fillRate = fileOps ? this.autoCompactFillRate / 3 : this.autoCompactFillRate;
        
        compact(fillRate, this.autoCommitMemory);
        this.autoCompactLastFileOpCount = (this.fileStore.getWriteCount() + this.fileStore.getReadCount());
      }
      catch (Exception e)
      {
        if (this.backgroundExceptionHandler != null) {
          this.backgroundExceptionHandler.uncaughtException(null, e);
        }
      }
    }
  }
  
  public void setCacheSize(int mb)
  {
    if (this.cache != null)
    {
      this.cache.setMaxMemory(mb * 1024L * 1024L);
      this.cache.clear();
    }
  }
  
  public boolean isClosed()
  {
    return this.closed;
  }
  
  private void stopBackgroundThread()
  {
    BackgroundWriterThread t = this.backgroundWriterThread;
    if (t == null) {
      return;
    }
    this.backgroundWriterThread = null;
    if (Thread.currentThread() == t) {
      return;
    }
    synchronized (t.sync)
    {
      t.sync.notifyAll();
    }
    if (Thread.holdsLock(this)) {
      return;
    }
    try
    {
      t.join();
    }
    catch (Exception e) {}
  }
  
  public void setAutoCommitDelay(int millis)
  {
    if (this.autoCommitDelay == millis) {
      return;
    }
    this.autoCommitDelay = millis;
    if ((this.fileStore == null) || (this.fileStore.isReadOnly())) {
      return;
    }
    stopBackgroundThread();
    if (millis > 0)
    {
      int sleep = Math.max(1, millis / 10);
      BackgroundWriterThread t = new BackgroundWriterThread(this, sleep, this.fileStore.toString());
      
      t.start();
      this.backgroundWriterThread = t;
    }
  }
  
  public int getAutoCommitDelay()
  {
    return this.autoCommitDelay;
  }
  
  public int getAutoCommitMemory()
  {
    return this.autoCommitMemory;
  }
  
  public int getUnsavedMemory()
  {
    return this.unsavedMemory;
  }
  
  void cachePage(long pos, Page page, int memory)
  {
    if (this.cache != null) {
      this.cache.put(pos, page, memory);
    }
  }
  
  public int getCacheSizeUsed()
  {
    if (this.cache == null) {
      return 0;
    }
    return (int)(this.cache.getUsedMemory() / 1024L / 1024L);
  }
  
  public int getCacheSize()
  {
    if (this.cache == null) {
      return 0;
    }
    return (int)(this.cache.getMaxMemory() / 1024L / 1024L);
  }
  
  public CacheLongKeyLIRS<Page> getCache()
  {
    return this.cache;
  }
  
  private static class BackgroundWriterThread
    extends Thread
  {
    public final Object sync = new Object();
    private final MVStore store;
    private final int sleep;
    
    BackgroundWriterThread(MVStore store, int sleep, String fileStoreName)
    {
      super();
      this.store = store;
      this.sleep = sleep;
      setDaemon(true);
    }
    
    public void run()
    {
      for (;;)
      {
        Thread t = this.store.backgroundWriterThread;
        if (t == null) {
          break;
        }
        synchronized (this.sync)
        {
          try
          {
            this.sync.wait(this.sleep);
          }
          catch (InterruptedException e) {}
          continue;
        }
        this.store.writeInBackground();
      }
    }
  }
  
  public static class Builder
  {
    private final HashMap<String, Object> config = New.hashMap();
    
    private Builder set(String key, Object value)
    {
      this.config.put(key, value);
      return this;
    }
    
    public Builder autoCommitDisabled()
    {
      set("autoCommitBufferSize", Integer.valueOf(0));
      return set("autoCommitDelay", Integer.valueOf(0));
    }
    
    public Builder autoCommitBufferSize(int kb)
    {
      return set("autoCommitBufferSize", Integer.valueOf(kb));
    }
    
    public Builder autoCompactFillRate(int percent)
    {
      return set("autoCompactFillRate", Integer.valueOf(percent));
    }
    
    public Builder fileName(String fileName)
    {
      return set("fileName", fileName);
    }
    
    public Builder encryptionKey(char[] password)
    {
      return set("encryptionKey", password);
    }
    
    public Builder readOnly()
    {
      return set("readOnly", Integer.valueOf(1));
    }
    
    public Builder cacheSize(int mb)
    {
      return set("cacheSize", Integer.valueOf(mb));
    }
    
    public Builder compress()
    {
      return set("compress", Integer.valueOf(1));
    }
    
    public Builder compressHigh()
    {
      return set("compress", Integer.valueOf(2));
    }
    
    public Builder pageSplitSize(int pageSplitSize)
    {
      return set("pageSplitSize", Integer.valueOf(pageSplitSize));
    }
    
    public Builder backgroundExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler)
    {
      return set("backgroundExceptionHandler", exceptionHandler);
    }
    
    public Builder fileStore(FileStore store)
    {
      return set("fileStore", store);
    }
    
    public MVStore open()
    {
      return new MVStore(this.config);
    }
    
    public String toString()
    {
      return DataUtils.appendMap(new StringBuilder(), this.config).toString();
    }
    
    public static Builder fromString(String s)
    {
      HashMap<String, String> config = DataUtils.parseMap(s);
      Builder builder = new Builder();
      builder.config.putAll(config);
      return builder;
    }
  }
}
