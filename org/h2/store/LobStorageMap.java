package org.h2.store;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import org.h2.engine.Constants;
import org.h2.engine.Database;
import org.h2.message.DbException;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.StreamStore;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.util.IOUtils;
import org.h2.util.New;
import org.h2.value.Value;
import org.h2.value.ValueLobDb;

public class LobStorageMap
  implements LobStorageInterface
{
  private static final boolean TRACE = false;
  private final Database database;
  private boolean init;
  private Object nextLobIdSync = new Object();
  private long nextLobId;
  private MVMap<Long, Object[]> lobMap;
  private MVMap<Object[], Boolean> refMap;
  private MVMap<Long, byte[]> dataMap;
  private StreamStore streamStore;
  
  public LobStorageMap(Database database)
  {
    this.database = database;
  }
  
  public void init()
  {
    if (this.init) {
      return;
    }
    this.init = true;
    MVTableEngine.Store s = this.database.getMvStore();
    MVStore mvStore;
    MVStore mvStore;
    if (s == null) {
      mvStore = MVStore.open(null);
    } else {
      mvStore = s.getStore();
    }
    this.lobMap = mvStore.openMap("lobMap");
    this.refMap = mvStore.openMap("lobRef");
    this.dataMap = mvStore.openMap("lobData");
    this.streamStore = new StreamStore(this.dataMap);
    if (this.database.isReadOnly()) {
      return;
    }
    if (this.dataMap.isEmpty()) {
      return;
    }
    long lastUsedKey = -1L;
    Long lobId = (Long)this.lobMap.lastKey();
    while (lobId != null)
    {
      Object[] v = (Object[])this.lobMap.get(lobId);
      byte[] id = (byte[])v[0];
      lastUsedKey = this.streamStore.getMaxBlockKey(id);
      if (lastUsedKey >= 0L) {
        break;
      }
      lobId = (Long)this.lobMap.lowerKey(lobId);
    }
    for (;;)
    {
      Long last = (Long)this.dataMap.lastKey();
      if ((last == null) || (last.longValue() <= lastUsedKey)) {
        break;
      }
      this.dataMap.remove(last);
    }
    Long last = (Long)this.dataMap.lastKey();
    if (last != null) {
      this.streamStore.setNextKey(last.longValue() + 1L);
    }
  }
  
  public Value createBlob(InputStream in, long maxLength)
  {
    init();
    int type = 15;
    if (maxLength < 0L) {
      maxLength = Long.MAX_VALUE;
    }
    int max = (int)Math.min(maxLength, this.database.getMaxLengthInplaceLob());
    try
    {
      if ((max != 0) && (max < Integer.MAX_VALUE))
      {
        BufferedInputStream b = new BufferedInputStream(in, max);
        b.mark(max);
        byte[] small = new byte[max];
        int len = IOUtils.readFully(b, small, max);
        if (len < max)
        {
          if (len < small.length) {
            small = Arrays.copyOf(small, len);
          }
          return ValueLobDb.createSmallLob(type, small);
        }
        b.reset();
        in = b;
      }
      return createLob(in, type);
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90007, e, new String[0]);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  public Value createClob(Reader reader, long maxLength)
  {
    init();
    int type = 16;
    if (maxLength < 0L) {
      maxLength = Long.MAX_VALUE;
    }
    int max = (int)Math.min(maxLength, this.database.getMaxLengthInplaceLob());
    try
    {
      if ((max != 0) && (max < Integer.MAX_VALUE))
      {
        BufferedReader b = new BufferedReader(reader, max);
        b.mark(max);
        char[] small = new char[max];
        int len = IOUtils.readFully(b, small, max);
        if (len < max)
        {
          if (len < small.length) {
            small = Arrays.copyOf(small, len);
          }
          byte[] utf8 = new String(small, 0, len).getBytes(Constants.UTF8);
          return ValueLobDb.createSmallLob(type, utf8);
        }
        b.reset();
        reader = b;
      }
      CountingReaderInputStream in = new CountingReaderInputStream(reader, maxLength);
      
      ValueLobDb lob = createLob(in, type);
      
      return ValueLobDb.create(type, this.database, lob.getTableId(), lob.getLobId(), null, in.getLength());
    }
    catch (IllegalStateException e)
    {
      throw DbException.get(90007, e, new String[0]);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  private ValueLobDb createLob(InputStream in, int type)
    throws IOException
  {
    byte[] streamStoreId;
    try
    {
      streamStoreId = this.streamStore.put(in);
    }
    catch (Exception e)
    {
      throw DbException.convertToIOException(e);
    }
    long lobId = generateLobId();
    long length = this.streamStore.length(streamStoreId);
    int tableId = -2;
    Object[] value = { streamStoreId, Integer.valueOf(tableId), Long.valueOf(length), Integer.valueOf(0) };
    this.lobMap.put(Long.valueOf(lobId), value);
    Object[] key = { streamStoreId, Long.valueOf(lobId) };
    this.refMap.put(key, Boolean.TRUE);
    ValueLobDb lob = ValueLobDb.create(type, this.database, tableId, lobId, null, length);
    
    return lob;
  }
  
  private long generateLobId()
  {
    synchronized (this.nextLobIdSync)
    {
      if (this.nextLobId == 0L)
      {
        Long id = (Long)this.lobMap.lastKey();
        this.nextLobId = (id == null ? 1L : id.longValue() + 1L);
      }
      return this.nextLobId++;
    }
  }
  
  public boolean isReadOnly()
  {
    return this.database.isReadOnly();
  }
  
  public ValueLobDb copyLob(ValueLobDb old, int tableId, long length)
  {
    init();
    int type = old.getType();
    long oldLobId = old.getLobId();
    long oldLength = old.getPrecision();
    if (oldLength != length) {
      throw DbException.throwInternalError("Length is different");
    }
    Object[] value = (Object[])this.lobMap.get(Long.valueOf(oldLobId));
    value = Arrays.copyOf(value, value.length);
    byte[] streamStoreId = (byte[])value[0];
    long lobId = generateLobId();
    value[1] = Integer.valueOf(tableId);
    this.lobMap.put(Long.valueOf(lobId), value);
    Object[] key = { streamStoreId, Long.valueOf(lobId) };
    this.refMap.put(key, Boolean.TRUE);
    ValueLobDb lob = ValueLobDb.create(type, this.database, tableId, lobId, null, length);
    
    return lob;
  }
  
  public InputStream getInputStream(ValueLobDb lob, byte[] hmac, long byteCount)
    throws IOException
  {
    init();
    Object[] value = (Object[])this.lobMap.get(Long.valueOf(lob.getLobId()));
    if (value == null)
    {
      if ((lob.getTableId() == -3) || (lob.getTableId() == -1)) {
        throw DbException.get(90039, "" + lob.getLobId() + "/" + lob.getTableId());
      }
      throw DbException.throwInternalError("Lob not found: " + lob.getLobId() + "/" + lob.getTableId());
    }
    byte[] streamStoreId = (byte[])value[0];
    return this.streamStore.get(streamStoreId);
  }
  
  public void setTable(ValueLobDb lob, int tableId)
  {
    init();
    long lobId = lob.getLobId();
    Object[] value = (Object[])this.lobMap.remove(Long.valueOf(lobId));
    
    value[1] = Integer.valueOf(tableId);
    this.lobMap.put(Long.valueOf(lobId), value);
  }
  
  public void removeAllForTable(int tableId)
  {
    init();
    if (this.database.getMvStore().getStore().isClosed()) {
      return;
    }
    ArrayList<Long> list = New.arrayList();
    for (Map.Entry<Long, Object[]> e : this.lobMap.entrySet())
    {
      Object[] value = (Object[])e.getValue();
      int t = ((Integer)value[1]).intValue();
      if (t == tableId) {
        list.add(e.getKey());
      }
    }
    for (Iterator i$ = list.iterator(); i$.hasNext();)
    {
      long lobId = ((Long)i$.next()).longValue();
      removeLob(tableId, lobId);
    }
    if (tableId == -1)
    {
      removeAllForTable(-2);
      removeAllForTable(-3);
    }
  }
  
  public void removeLob(ValueLobDb lob)
  {
    init();
    int tableId = lob.getTableId();
    long lobId = lob.getLobId();
    removeLob(tableId, lobId);
  }
  
  private void removeLob(int tableId, long lobId)
  {
    Object[] value = (Object[])this.lobMap.remove(Long.valueOf(lobId));
    if (value == null) {
      return;
    }
    byte[] streamStoreId = (byte[])value[0];
    Object[] key = { streamStoreId, Long.valueOf(lobId) };
    this.refMap.remove(key);
    
    key = new Object[] { streamStoreId, Long.valueOf(0L) };
    value = (Object[])this.refMap.ceilingKey(key);
    boolean hasMoreEntries = false;
    if (value != null)
    {
      byte[] s2 = (byte[])value[0];
      if (Arrays.equals(streamStoreId, s2)) {
        hasMoreEntries = true;
      }
    }
    if (!hasMoreEntries) {
      this.streamStore.remove(streamStoreId);
    }
  }
  
  private static void trace(String op)
  {
    System.out.println("[" + Thread.currentThread().getName() + "] LOB " + op);
  }
}
