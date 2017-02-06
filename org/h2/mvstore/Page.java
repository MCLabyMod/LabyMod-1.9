package org.h2.mvstore;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.h2.compress.Compressor;
import org.h2.mvstore.type.DataType;
import org.h2.util.New;

public class Page
{
  public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
  private final MVMap<?, ?> map;
  private long version;
  private long pos;
  private long totalCount;
  private int cachedCompare;
  private int memory;
  private Object[] keys;
  private Object[] values;
  private PageReference[] children;
  private volatile boolean removedInMemory;
  
  Page(MVMap<?, ?> map, long version)
  {
    this.map = map;
    this.version = version;
  }
  
  static Page createEmpty(MVMap<?, ?> map, long version)
  {
    return create(map, version, EMPTY_OBJECT_ARRAY, EMPTY_OBJECT_ARRAY, null, 0L, 128);
  }
  
  public static Page create(MVMap<?, ?> map, long version, Object[] keys, Object[] values, PageReference[] children, long totalCount, int memory)
  {
    Page p = new Page(map, version);
    
    p.keys = keys;
    p.values = values;
    p.children = children;
    p.totalCount = totalCount;
    if (memory == 0) {
      p.recalculateMemory();
    } else {
      p.addMemory(memory);
    }
    MVStore store = map.store;
    if (store != null) {
      store.registerUnsavedPage(p.memory);
    }
    return p;
  }
  
  public static Page create(MVMap<?, ?> map, long version, Page source)
  {
    Page p = new Page(map, version);
    
    p.keys = source.keys;
    p.values = source.values;
    p.children = source.children;
    p.totalCount = source.totalCount;
    p.memory = source.memory;
    MVStore store = map.store;
    if (store != null) {
      store.registerUnsavedPage(p.memory);
    }
    return p;
  }
  
  static Page read(FileStore fileStore, long pos, MVMap<?, ?> map, long filePos, long maxPos)
  {
    int maxLength = DataUtils.getPageMaxLength(pos);
    if (maxLength == 2097152)
    {
      ByteBuffer buff = fileStore.readFully(filePos, 128);
      maxLength = buff.getInt();
    }
    maxLength = (int)Math.min(maxPos - filePos, maxLength);
    int length = maxLength;
    if (length < 0) {
      throw DataUtils.newIllegalStateException(6, "Illegal page length {0} reading at {1}; max pos {2} ", new Object[] { Integer.valueOf(length), Long.valueOf(filePos), Long.valueOf(maxPos) });
    }
    ByteBuffer buff = fileStore.readFully(filePos, length);
    Page p = new Page(map, 0L);
    p.pos = pos;
    int chunkId = DataUtils.getPageChunkId(pos);
    int offset = DataUtils.getPageOffset(pos);
    p.read(buff, chunkId, offset, maxLength);
    return p;
  }
  
  public Object getKey(int index)
  {
    return this.keys[index];
  }
  
  public Page getChildPage(int index)
  {
    PageReference ref = this.children[index];
    return ref.page != null ? ref.page : this.map.readPage(ref.pos);
  }
  
  public long getChildPagePos(int index)
  {
    return this.children[index].pos;
  }
  
  public Object getValue(int index)
  {
    return this.values[index];
  }
  
  public int getKeyCount()
  {
    return this.keys.length;
  }
  
  public boolean isLeaf()
  {
    return this.children == null;
  }
  
  public long getPos()
  {
    return this.pos;
  }
  
  public String toString()
  {
    StringBuilder buff = new StringBuilder();
    buff.append("id: ").append(System.identityHashCode(this)).append('\n');
    buff.append("version: ").append(Long.toHexString(this.version)).append("\n");
    buff.append("pos: ").append(Long.toHexString(this.pos)).append("\n");
    if (this.pos != 0L)
    {
      int chunkId = DataUtils.getPageChunkId(this.pos);
      buff.append("chunk: ").append(Long.toHexString(chunkId)).append("\n");
    }
    for (int i = 0; i <= this.keys.length; i++)
    {
      if (i > 0) {
        buff.append(" ");
      }
      if (this.children != null) {
        buff.append("[" + Long.toHexString(this.children[i].pos) + "] ");
      }
      if (i < this.keys.length)
      {
        buff.append(this.keys[i]);
        if (this.values != null)
        {
          buff.append(':');
          buff.append(this.values[i]);
        }
      }
    }
    return buff.toString();
  }
  
  public Page copy(long version)
  {
    Page newPage = create(this.map, version, this.keys, this.values, this.children, this.totalCount, getMemory());
    
    removePage();
    newPage.cachedCompare = this.cachedCompare;
    return newPage;
  }
  
  public int binarySearch(Object key)
  {
    int low = 0;int high = this.keys.length - 1;
    
    int x = this.cachedCompare - 1;
    if ((x < 0) || (x > high)) {
      x = high >>> 1;
    }
    Object[] k = this.keys;
    while (low <= high)
    {
      int compare = this.map.compare(key, k[x]);
      if (compare > 0)
      {
        low = x + 1;
      }
      else if (compare < 0)
      {
        high = x - 1;
      }
      else
      {
        this.cachedCompare = (x + 1);
        return x;
      }
      x = low + high >>> 1;
    }
    this.cachedCompare = low;
    return -(low + 1);
  }
  
  Page split(int at)
  {
    return isLeaf() ? splitLeaf(at) : splitNode(at);
  }
  
  private Page splitLeaf(int at)
  {
    int a = at;int b = this.keys.length - a;
    Object[] aKeys = new Object[a];
    Object[] bKeys = new Object[b];
    System.arraycopy(this.keys, 0, aKeys, 0, a);
    System.arraycopy(this.keys, a, bKeys, 0, b);
    this.keys = aKeys;
    Object[] aValues = new Object[a];
    Object[] bValues = new Object[b];
    bValues = new Object[b];
    System.arraycopy(this.values, 0, aValues, 0, a);
    System.arraycopy(this.values, a, bValues, 0, b);
    this.values = aValues;
    this.totalCount = a;
    Page newPage = create(this.map, this.version, bKeys, bValues, null, bKeys.length, 0);
    
    recalculateMemory();
    newPage.recalculateMemory();
    return newPage;
  }
  
  private Page splitNode(int at)
  {
    int a = at;int b = this.keys.length - a;
    
    Object[] aKeys = new Object[a];
    Object[] bKeys = new Object[b - 1];
    System.arraycopy(this.keys, 0, aKeys, 0, a);
    System.arraycopy(this.keys, a + 1, bKeys, 0, b - 1);
    this.keys = aKeys;
    
    PageReference[] aChildren = new PageReference[a + 1];
    PageReference[] bChildren = new PageReference[b];
    System.arraycopy(this.children, 0, aChildren, 0, a + 1);
    System.arraycopy(this.children, a + 1, bChildren, 0, b);
    this.children = aChildren;
    
    long t = 0L;
    for (PageReference x : aChildren) {
      t += x.count;
    }
    this.totalCount = t;
    t = 0L;
    for (PageReference x : bChildren) {
      t += x.count;
    }
    Page newPage = create(this.map, this.version, bKeys, null, bChildren, t, 0);
    
    recalculateMemory();
    newPage.recalculateMemory();
    return newPage;
  }
  
  public long getTotalCount()
  {
    return this.totalCount;
  }
  
  long getCounts(int index)
  {
    return this.children[index].count;
  }
  
  public void setChild(int index, Page c)
  {
    if (c == null)
    {
      long oldCount = this.children[index].count;
      this.children = ((PageReference[])Arrays.copyOf(this.children, this.children.length));
      PageReference ref = new PageReference(null, 0L, 0L);
      this.children[index] = ref;
      this.totalCount -= oldCount;
    }
    else if ((c != this.children[index].page) || (c.getPos() != this.children[index].pos))
    {
      long oldCount = this.children[index].count;
      this.children = ((PageReference[])Arrays.copyOf(this.children, this.children.length));
      PageReference ref = new PageReference(c, c.pos, c.totalCount);
      this.children[index] = ref;
      this.totalCount += c.totalCount - oldCount;
    }
  }
  
  public void setKey(int index, Object key)
  {
    this.keys = Arrays.copyOf(this.keys, this.keys.length);
    Object old = this.keys[index];
    DataType keyType = this.map.getKeyType();
    int mem = keyType.getMemory(key);
    if (old != null) {
      mem -= keyType.getMemory(old);
    }
    addMemory(mem);
    this.keys[index] = key;
  }
  
  public Object setValue(int index, Object value)
  {
    Object old = this.values[index];
    this.values = Arrays.copyOf(this.values, this.values.length);
    DataType valueType = this.map.getValueType();
    addMemory(valueType.getMemory(value) - valueType.getMemory(old));
    
    this.values[index] = value;
    return old;
  }
  
  void removeAllRecursive()
  {
    if (this.children != null)
    {
      int i = 0;
      for (int size = this.map.getChildPageCount(this); i < size; i++)
      {
        PageReference ref = this.children[i];
        if (ref.page != null)
        {
          ref.page.removeAllRecursive();
        }
        else
        {
          long c = this.children[i].pos;
          int type = DataUtils.getPageType(c);
          if (type == 0)
          {
            int mem = DataUtils.getPageMaxLength(c);
            this.map.removePage(c, mem);
          }
          else
          {
            this.map.readPage(c).removeAllRecursive();
          }
        }
      }
    }
    removePage();
  }
  
  public void insertLeaf(int index, Object key, Object value)
  {
    int len = this.keys.length + 1;
    Object[] newKeys = new Object[len];
    DataUtils.copyWithGap(this.keys, newKeys, len - 1, index);
    this.keys = newKeys;
    Object[] newValues = new Object[len];
    DataUtils.copyWithGap(this.values, newValues, len - 1, index);
    this.values = newValues;
    this.keys[index] = key;
    this.values[index] = value;
    this.totalCount += 1L;
    addMemory(this.map.getKeyType().getMemory(key) + this.map.getValueType().getMemory(value));
  }
  
  public void insertNode(int index, Object key, Page childPage)
  {
    Object[] newKeys = new Object[this.keys.length + 1];
    DataUtils.copyWithGap(this.keys, newKeys, this.keys.length, index);
    newKeys[index] = key;
    this.keys = newKeys;
    
    int childCount = this.children.length;
    PageReference[] newChildren = new PageReference[childCount + 1];
    DataUtils.copyWithGap(this.children, newChildren, childCount, index);
    newChildren[index] = new PageReference(childPage, childPage.getPos(), childPage.totalCount);
    
    this.children = newChildren;
    
    this.totalCount += childPage.totalCount;
    addMemory(this.map.getKeyType().getMemory(key) + 16);
  }
  
  public void remove(int index)
  {
    int keyLength = this.keys.length;
    int keyIndex = index >= keyLength ? index - 1 : index;
    Object old = this.keys[keyIndex];
    addMemory(-this.map.getKeyType().getMemory(old));
    Object[] newKeys = new Object[keyLength - 1];
    DataUtils.copyExcept(this.keys, newKeys, keyLength, keyIndex);
    this.keys = newKeys;
    if (this.values != null)
    {
      old = this.values[index];
      addMemory(-this.map.getValueType().getMemory(old));
      Object[] newValues = new Object[keyLength - 1];
      DataUtils.copyExcept(this.values, newValues, keyLength, index);
      this.values = newValues;
      this.totalCount -= 1L;
    }
    if (this.children != null)
    {
      addMemory(-16);
      long countOffset = this.children[index].count;
      
      int childCount = this.children.length;
      PageReference[] newChildren = new PageReference[childCount - 1];
      DataUtils.copyExcept(this.children, newChildren, childCount, index);
      this.children = newChildren;
      
      this.totalCount -= countOffset;
    }
  }
  
  void read(ByteBuffer buff, int chunkId, int offset, int maxLength)
  {
    int start = buff.position();
    int pageLength = buff.getInt();
    if (pageLength > maxLength) {
      throw DataUtils.newIllegalStateException(6, "File corrupted in chunk {0}, expected page length =< {1}, got {2}", new Object[] { Integer.valueOf(chunkId), Integer.valueOf(maxLength), Integer.valueOf(pageLength) });
    }
    buff.limit(start + pageLength);
    short check = buff.getShort();
    int mapId = DataUtils.readVarInt(buff);
    if (mapId != this.map.getId()) {
      throw DataUtils.newIllegalStateException(6, "File corrupted in chunk {0}, expected map id {1}, got {2}", new Object[] { Integer.valueOf(chunkId), Integer.valueOf(this.map.getId()), Integer.valueOf(mapId) });
    }
    int checkTest = DataUtils.getCheckValue(chunkId) ^ DataUtils.getCheckValue(offset) ^ DataUtils.getCheckValue(pageLength);
    if (check != (short)checkTest) {
      throw DataUtils.newIllegalStateException(6, "File corrupted in chunk {0}, expected check value {1}, got {2}", new Object[] { Integer.valueOf(chunkId), Integer.valueOf(checkTest), Short.valueOf(check) });
    }
    int len = DataUtils.readVarInt(buff);
    this.keys = new Object[len];
    int type = buff.get();
    boolean node = (type & 0x1) == 1;
    if (node)
    {
      this.children = new PageReference[len + 1];
      long[] p = new long[len + 1];
      for (int i = 0; i <= len; i++) {
        p[i] = buff.getLong();
      }
      long total = 0L;
      for (int i = 0; i <= len; i++)
      {
        long s = DataUtils.readVarLong(buff);
        total += s;
        this.children[i] = new PageReference(null, p[i], s);
      }
      this.totalCount = total;
    }
    boolean compressed = (type & 0x2) != 0;
    if (compressed)
    {
      Compressor compressor;
      Compressor compressor;
      if ((type & 0x6) == 6) {
        compressor = this.map.getStore().getCompressorHigh();
      } else {
        compressor = this.map.getStore().getCompressorFast();
      }
      int lenAdd = DataUtils.readVarInt(buff);
      int compLen = pageLength + start - buff.position();
      byte[] comp = DataUtils.newBytes(compLen);
      buff.get(comp);
      int l = compLen + lenAdd;
      buff = ByteBuffer.allocate(l);
      compressor.expand(comp, 0, compLen, buff.array(), buff.arrayOffset(), l);
    }
    this.map.getKeyType().read(buff, this.keys, len, true);
    if (!node)
    {
      this.values = new Object[len];
      this.map.getValueType().read(buff, this.values, len, false);
      this.totalCount = len;
    }
    recalculateMemory();
  }
  
  private int write(Chunk chunk, WriteBuffer buff)
  {
    int start = buff.position();
    int len = this.keys.length;
    int type = this.children != null ? 1 : 0;
    
    buff.putInt(0).putShort((short)0).putVarInt(this.map.getId()).putVarInt(len);
    
    int typePos = buff.position();
    buff.put((byte)type);
    if (type == 1)
    {
      writeChildren(buff);
      for (int i = 0; i <= len; i++) {
        buff.putVarLong(this.children[i].count);
      }
    }
    int compressStart = buff.position();
    this.map.getKeyType().write(buff, this.keys, len, true);
    if (type == 0) {
      this.map.getValueType().write(buff, this.values, len, false);
    }
    MVStore store = this.map.getStore();
    int expLen = buff.position() - compressStart;
    if (expLen > 16)
    {
      int compressionLevel = store.getCompressionLevel();
      if (compressionLevel > 0)
      {
        int compressType;
        Compressor compressor;
        int compressType;
        if (compressionLevel == 1)
        {
          Compressor compressor = this.map.getStore().getCompressorFast();
          compressType = 2;
        }
        else
        {
          compressor = this.map.getStore().getCompressorHigh();
          compressType = 6;
        }
        byte[] exp = new byte[expLen];
        buff.position(compressStart).get(exp);
        byte[] comp = new byte[expLen * 2];
        int compLen = compressor.compress(exp, expLen, comp, 0);
        int plus = DataUtils.getVarIntLen(compLen - expLen);
        if (compLen + plus < expLen)
        {
          buff.position(typePos).put((byte)(type + compressType));
          
          buff.position(compressStart).putVarInt(expLen - compLen).put(comp, 0, compLen);
        }
      }
    }
    int pageLength = buff.position() - start;
    int chunkId = chunk.id;
    int check = DataUtils.getCheckValue(chunkId) ^ DataUtils.getCheckValue(start) ^ DataUtils.getCheckValue(pageLength);
    
    buff.putInt(start, pageLength).putShort(start + 4, (short)check);
    if (this.pos != 0L) {
      throw DataUtils.newIllegalStateException(3, "Page already stored", new Object[0]);
    }
    this.pos = DataUtils.getPagePos(chunkId, start, pageLength, type);
    store.cachePage(this.pos, this, getMemory());
    if (type == 1) {
      store.cachePage(this.pos, this, getMemory());
    }
    long max = DataUtils.getPageMaxLength(this.pos);
    chunk.maxLen += max;
    chunk.maxLenLive += max;
    chunk.pageCount += 1;
    chunk.pageCountLive += 1;
    if (this.removedInMemory) {
      this.map.removePage(this.pos, this.memory);
    }
    return typePos + 1;
  }
  
  private void writeChildren(WriteBuffer buff)
  {
    int len = this.keys.length;
    for (int i = 0; i <= len; i++) {
      buff.putLong(this.children[i].pos);
    }
  }
  
  void writeUnsavedRecursive(Chunk chunk, WriteBuffer buff)
  {
    if (this.pos != 0L) {
      return;
    }
    int patch = write(chunk, buff);
    if (!isLeaf())
    {
      int len = this.children.length;
      for (int i = 0; i < len; i++)
      {
        Page p = this.children[i].page;
        if (p != null)
        {
          p.writeUnsavedRecursive(chunk, buff);
          this.children[i] = new PageReference(p, p.getPos(), p.totalCount);
        }
      }
      int old = buff.position();
      buff.position(patch);
      writeChildren(buff);
      buff.position(old);
    }
  }
  
  void writeEnd()
  {
    if (isLeaf()) {
      return;
    }
    int len = this.children.length;
    for (int i = 0; i < len; i++)
    {
      PageReference ref = this.children[i];
      if (ref.page != null)
      {
        if (ref.page.getPos() == 0L) {
          throw DataUtils.newIllegalStateException(3, "Page not written", new Object[0]);
        }
        ref.page.writeEnd();
        this.children[i] = new PageReference(null, ref.pos, ref.count);
      }
    }
  }
  
  long getVersion()
  {
    return this.version;
  }
  
  public int getRawChildPageCount()
  {
    return this.children.length;
  }
  
  public boolean equals(Object other)
  {
    if (other == this) {
      return true;
    }
    if ((other instanceof Page))
    {
      if ((this.pos != 0L) && (((Page)other).pos == this.pos)) {
        return true;
      }
      return this == other;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.pos != 0L ? (int)(this.pos | this.pos >>> 32) : super.hashCode();
  }
  
  public int getMemory()
  {
    return this.memory;
  }
  
  private void addMemory(int mem)
  {
    this.memory += mem;
  }
  
  private void recalculateMemory()
  {
    int mem = 128;
    DataType keyType = this.map.getKeyType();
    for (int i = 0; i < this.keys.length; i++) {
      mem += keyType.getMemory(this.keys[i]);
    }
    if (isLeaf())
    {
      DataType valueType = this.map.getValueType();
      for (int i = 0; i < this.keys.length; i++) {
        mem += valueType.getMemory(this.values[i]);
      }
    }
    else
    {
      mem += getRawChildPageCount() * 16;
    }
    addMemory(mem - this.memory);
  }
  
  void setVersion(long version)
  {
    this.version = version;
  }
  
  public void removePage()
  {
    long p = this.pos;
    if (p == 0L) {
      this.removedInMemory = true;
    }
    this.map.removePage(p, this.memory);
  }
  
  public static class PageReference
  {
    final long pos;
    final Page page;
    final long count;
    
    public PageReference(Page page, long pos, long count)
    {
      this.page = page;
      this.pos = pos;
      this.count = count;
    }
  }
  
  public static class PageChildren
  {
    public static final long[] EMPTY_ARRAY = new long[0];
    final long pos;
    long[] children;
    boolean chunkList;
    
    private PageChildren(long pos, long[] children)
    {
      this.pos = pos;
      this.children = children;
    }
    
    PageChildren(Page p)
    {
      this.pos = p.getPos();
      int count = p.getRawChildPageCount();
      this.children = new long[count];
      for (int i = 0; i < count; i++) {
        this.children[i] = p.getChildPagePos(i);
      }
    }
    
    int getMemory()
    {
      return 64 + 8 * this.children.length;
    }
    
    static PageChildren read(FileStore fileStore, long pos, int mapId, long filePos, long maxPos)
    {
      int maxLength = DataUtils.getPageMaxLength(pos);
      if (maxLength == 2097152)
      {
        ByteBuffer buff = fileStore.readFully(filePos, 128);
        maxLength = buff.getInt();
      }
      maxLength = (int)Math.min(maxPos - filePos, maxLength);
      int length = maxLength;
      if (length < 0) {
        throw DataUtils.newIllegalStateException(6, "Illegal page length {0} reading at {1}; max pos {2} ", new Object[] { Integer.valueOf(length), Long.valueOf(filePos), Long.valueOf(maxPos) });
      }
      ByteBuffer buff = fileStore.readFully(filePos, length);
      int chunkId = DataUtils.getPageChunkId(pos);
      int offset = DataUtils.getPageOffset(pos);
      int start = buff.position();
      int pageLength = buff.getInt();
      if (pageLength > maxLength) {
        throw DataUtils.newIllegalStateException(6, "File corrupted in chunk {0}, expected page length =< {1}, got {2}", new Object[] { Integer.valueOf(chunkId), Integer.valueOf(maxLength), Integer.valueOf(pageLength) });
      }
      buff.limit(start + pageLength);
      short check = buff.getShort();
      int m = DataUtils.readVarInt(buff);
      if (m != mapId) {
        throw DataUtils.newIllegalStateException(6, "File corrupted in chunk {0}, expected map id {1}, got {2}", new Object[] { Integer.valueOf(chunkId), Integer.valueOf(mapId), Integer.valueOf(m) });
      }
      int checkTest = DataUtils.getCheckValue(chunkId) ^ DataUtils.getCheckValue(offset) ^ DataUtils.getCheckValue(pageLength);
      if (check != (short)checkTest) {
        throw DataUtils.newIllegalStateException(6, "File corrupted in chunk {0}, expected check value {1}, got {2}", new Object[] { Integer.valueOf(chunkId), Integer.valueOf(checkTest), Short.valueOf(check) });
      }
      int len = DataUtils.readVarInt(buff);
      int type = buff.get();
      boolean node = (type & 0x1) == 1;
      if (!node) {
        return null;
      }
      long[] children = new long[len + 1];
      for (int i = 0; i <= len; i++) {
        children[i] = buff.getLong();
      }
      return new PageChildren(pos, children);
    }
    
    void removeDuplicateChunkReferences()
    {
      HashSet<Integer> chunks = New.hashSet();
      
      chunks.add(Integer.valueOf(DataUtils.getPageChunkId(this.pos)));
      for (int i = 0; i < this.children.length; i++)
      {
        long p = this.children[i];
        int chunkId = DataUtils.getPageChunkId(p);
        boolean wasNew = chunks.add(Integer.valueOf(chunkId));
        if (DataUtils.getPageType(p) != 1) {
          if (!wasNew) {
            removeChild(i--);
          }
        }
      }
    }
    
    void collectReferencedChunks(Set<Integer> target)
    {
      target.add(Integer.valueOf(DataUtils.getPageChunkId(this.pos)));
      for (long p : this.children) {
        target.add(Integer.valueOf(DataUtils.getPageChunkId(p)));
      }
    }
    
    private void removeChild(int index)
    {
      if ((index == 0) && (this.children.length == 1))
      {
        this.children = EMPTY_ARRAY;
        return;
      }
      long[] c2 = new long[this.children.length - 1];
      DataUtils.copyExcept(this.children, c2, this.children.length, index);
      this.children = c2;
    }
  }
}
