package org.h2.mvstore.db;

import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.h2.api.TableEngine;
import org.h2.command.ddl.CreateTableData;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.FileStore;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.MVStore.Builder;
import org.h2.mvstore.MVStoreTool;
import org.h2.store.InDoubtTransaction;
import org.h2.store.fs.FileChannelInputStream;
import org.h2.store.fs.FileUtils;
import org.h2.table.TableBase;
import org.h2.util.BitField;
import org.h2.util.New;

public class MVTableEngine
  implements TableEngine
{
  public static Store init(Database db)
  {
    Store store = db.getMvStore();
    if (store != null) {
      return store;
    }
    byte[] key = db.getFileEncryptionKey();
    String dbPath = db.getDatabasePath();
    MVStore.Builder builder = new MVStore.Builder();
    if (dbPath == null)
    {
      store = new Store(db, builder);
    }
    else
    {
      String fileName = dbPath + ".mv.db";
      MVStoreTool.compactCleanUp(fileName);
      builder.fileName(fileName);
      builder.pageSplitSize(db.getPageSize());
      if (db.isReadOnly())
      {
        builder.readOnly();
      }
      else
      {
        boolean exists = FileUtils.exists(fileName);
        if ((!exists) || (FileUtils.canWrite(fileName)))
        {
          String dir = FileUtils.getParent(fileName);
          FileUtils.createDirectories(dir);
        }
      }
      if (key != null)
      {
        char[] password = new char[key.length / 2];
        for (int i = 0; i < password.length; i++) {
          password[i] = ((char)((key[(i + i)] & 0xFF) << 16 | key[(i + i + 1)] & 0xFF));
        }
        builder.encryptionKey(password);
      }
      if (db.getSettings().compressData)
      {
        builder.compress();
        
        builder.pageSplitSize(65536);
      }
      builder.backgroundExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread t, Throwable e)
        {
          this.val$db.setBackgroundException(DbException.convert(e));
        }
      });
      try
      {
        store = new Store(db, builder);
      }
      catch (IllegalStateException e)
      {
        int errorCode = DataUtils.getErrorCode(e.getMessage());
        if (errorCode == 6)
        {
          if (key != null) {
            throw DbException.get(90049, e, new String[] { fileName });
          }
        }
        else
        {
          if (errorCode == 7) {
            throw DbException.get(90020, e, new String[] { fileName });
          }
          if (errorCode == 1) {
            throw DbException.get(90028, e, new String[] { fileName });
          }
        }
        throw DbException.get(90030, e, new String[] { fileName });
      }
    }
    db.setMvStore(store);
    return store;
  }
  
  public TableBase createTable(CreateTableData data)
  {
    Database db = data.session.getDatabase();
    Store store = init(db);
    MVTable table = new MVTable(data, store);
    table.init(data.session);
    store.tableMap.put(table.getMapName(), table);
    return table;
  }
  
  public static class Store
  {
    final ConcurrentHashMap<String, MVTable> tableMap = new ConcurrentHashMap();
    private final MVStore store;
    private final TransactionStore transactionStore;
    private long statisticsStart;
    private int temporaryMapId;
    
    public Store(Database db, MVStore.Builder builder)
    {
      this.store = builder.open();
      if (!db.getSettings().reuseSpace) {
        this.store.setReuseSpace(false);
      }
      this.transactionStore = new TransactionStore(this.store, new ValueDataType(null, db, null));
      
      this.transactionStore.init();
    }
    
    public MVStore getStore()
    {
      return this.store;
    }
    
    public TransactionStore getTransactionStore()
    {
      return this.transactionStore;
    }
    
    public HashMap<String, MVTable> getTables()
    {
      return new HashMap(this.tableMap);
    }
    
    public void removeTable(MVTable table)
    {
      this.tableMap.remove(table.getMapName());
    }
    
    public void flush()
    {
      FileStore s = this.store.getFileStore();
      if ((s == null) || (s.isReadOnly())) {
        return;
      }
      if (!this.store.compact(50, 4194304)) {
        this.store.commit();
      }
    }
    
    public void closeImmediately()
    {
      if (this.store.isClosed()) {
        return;
      }
      this.store.closeImmediately();
    }
    
    public void initTransactions()
    {
      List<TransactionStore.Transaction> list = this.transactionStore.getOpenTransactions();
      for (TransactionStore.Transaction t : list) {
        if (t.getStatus() == 3) {
          t.commit();
        } else if (t.getStatus() != 2) {
          t.rollback();
        }
      }
    }
    
    public void removeTemporaryMaps(BitField objectIds)
    {
      for (String mapName : this.store.getMapNames()) {
        if (mapName.startsWith("temp."))
        {
          MVMap<?, ?> map = this.store.openMap(mapName);
          this.store.removeMap(map);
        }
        else if ((mapName.startsWith("table.")) || (mapName.startsWith("index.")))
        {
          int id = Integer.parseInt(mapName.substring(1 + mapName.indexOf(".")));
          if (!objectIds.get(id))
          {
            ValueDataType keyType = new ValueDataType(null, null, null);
            ValueDataType valueType = new ValueDataType(null, null, null);
            TransactionStore.Transaction t = this.transactionStore.begin();
            TransactionStore.TransactionMap<?, ?> m = t.openMap(mapName, keyType, valueType);
            this.transactionStore.removeMap(m);
            t.commit();
          }
        }
      }
    }
    
    public synchronized String nextTemporaryMapName()
    {
      return "temp." + this.temporaryMapId++;
    }
    
    public void prepareCommit(Session session, String transactionName)
    {
      TransactionStore.Transaction t = session.getTransaction();
      t.setName(transactionName);
      t.prepare();
      this.store.commit();
    }
    
    public ArrayList<InDoubtTransaction> getInDoubtTransactions()
    {
      List<TransactionStore.Transaction> list = this.transactionStore.getOpenTransactions();
      ArrayList<InDoubtTransaction> result = New.arrayList();
      for (TransactionStore.Transaction t : list) {
        if (t.getStatus() == 2) {
          result.add(new MVTableEngine.MVInDoubtTransaction(this.store, t));
        }
      }
      return result;
    }
    
    public void setCacheSize(int kb)
    {
      this.store.setCacheSize(Math.max(1, kb / 1024));
    }
    
    public InputStream getInputStream()
    {
      FileChannel fc = this.store.getFileStore().getEncryptedFile();
      if (fc == null) {
        fc = this.store.getFileStore().getFile();
      }
      return new FileChannelInputStream(fc, false);
    }
    
    public void sync()
    {
      flush();
      this.store.sync();
    }
    
    public void compactFile(long maxCompactTime)
    {
      this.store.setRetentionTime(0);
      long start = System.currentTimeMillis();
      while (this.store.compact(95, 16777216))
      {
        this.store.sync();
        this.store.compactMoveChunks(95, 16777216L);
        long time = System.currentTimeMillis() - start;
        if (time > maxCompactTime) {
          break;
        }
      }
    }
    
    public void close(long maxCompactTime)
    {
      try
      {
        if ((!this.store.isClosed()) && (this.store.getFileStore() != null))
        {
          boolean compactFully = false;
          if (!this.store.getFileStore().isReadOnly())
          {
            this.transactionStore.close();
            if (maxCompactTime == Long.MAX_VALUE) {
              compactFully = true;
            }
          }
          String fileName = this.store.getFileStore().getFileName();
          this.store.close();
          if ((compactFully) && (FileUtils.exists(fileName))) {
            MVStoreTool.compact(fileName, true);
          }
        }
      }
      catch (IllegalStateException e)
      {
        int errorCode = DataUtils.getErrorCode(e.getMessage());
        if (errorCode != 2) {
          if (errorCode != 6) {}
        }
        this.store.closeImmediately();
        throw DbException.get(90028, e, new String[] { "Closing" });
      }
    }
    
    public void statisticsStart()
    {
      FileStore fs = this.store.getFileStore();
      this.statisticsStart = (fs == null ? 0L : fs.getReadCount());
    }
    
    public Map<String, Integer> statisticsEnd()
    {
      HashMap<String, Integer> map = New.hashMap();
      FileStore fs = this.store.getFileStore();
      int reads = fs == null ? 0 : (int)(fs.getReadCount() - this.statisticsStart);
      map.put("reads", Integer.valueOf(reads));
      return map;
    }
  }
  
  private static class MVInDoubtTransaction
    implements InDoubtTransaction
  {
    private final MVStore store;
    private final TransactionStore.Transaction transaction;
    private int state = 0;
    
    MVInDoubtTransaction(MVStore store, TransactionStore.Transaction transaction)
    {
      this.store = store;
      this.transaction = transaction;
    }
    
    public void setState(int state)
    {
      if (state == 1) {
        this.transaction.commit();
      } else {
        this.transaction.rollback();
      }
      this.store.commit();
      this.state = state;
    }
    
    public String getState()
    {
      switch (this.state)
      {
      case 0: 
        return "IN_DOUBT";
      case 1: 
        return "COMMIT";
      case 2: 
        return "ROLLBACK";
      }
      throw DbException.throwInternalError("state=" + this.state);
    }
    
    public String getTransactionName()
    {
      return this.transaction.getName();
    }
  }
}
