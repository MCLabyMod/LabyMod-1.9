package org.h2.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.zip.CRC32;
import org.h2.api.JavaObjectSerializer;
import org.h2.compress.CompressLZF;
import org.h2.engine.Constants;
import org.h2.engine.MetaRecord;
import org.h2.engine.SessionInterface;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.MVStore.Builder;
import org.h2.mvstore.MVStoreTool;
import org.h2.mvstore.StreamStore;
import org.h2.mvstore.db.TransactionStore;
import org.h2.mvstore.db.TransactionStore.Transaction;
import org.h2.mvstore.db.TransactionStore.TransactionMap;
import org.h2.mvstore.db.ValueDataType;
import org.h2.result.Row;
import org.h2.result.SimpleRow;
import org.h2.security.SHA256;
import org.h2.store.Data;
import org.h2.store.DataHandler;
import org.h2.store.DataReader;
import org.h2.store.FileLister;
import org.h2.store.FileStore;
import org.h2.store.FileStoreInputStream;
import org.h2.store.LobStorageBackend;
import org.h2.store.LobStorageMap;
import org.h2.store.PageFreeList;
import org.h2.store.PageLog;
import org.h2.store.PageStore;
import org.h2.store.fs.FileUtils;
import org.h2.util.BitField;
import org.h2.util.IOUtils;
import org.h2.util.IntArray;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.SmallLRUCache;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.TempFileDeleter;
import org.h2.util.Tool;
import org.h2.util.Utils;
import org.h2.value.Value;
import org.h2.value.Value.ValueBlob;
import org.h2.value.Value.ValueClob;
import org.h2.value.ValueArray;
import org.h2.value.ValueLob;
import org.h2.value.ValueLobDb;
import org.h2.value.ValueLong;

public class Recover
  extends Tool
  implements DataHandler
{
  private String databaseName;
  private int storageId;
  private String storageName;
  private int recordLength;
  private int valueId;
  private boolean trace;
  private boolean transactionLog;
  private ArrayList<MetaRecord> schema;
  private HashSet<Integer> objectIdSet;
  private HashMap<Integer, String> tableMap;
  private HashMap<String, String> columnTypeMap;
  private boolean remove;
  private int pageSize;
  private FileStore store;
  private int[] parents;
  private Stats stat;
  private boolean lobMaps;
  
  static class Stats
  {
    long pageDataEmpty;
    long pageDataRows;
    long pageDataHead;
    final int[] pageTypeCount = new int[10];
    int free;
  }
  
  public static void main(String... args)
    throws SQLException
  {
    new Recover().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String dir = ".";
    String db = null;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if ("-dir".equals(arg))
      {
        dir = args[(++i)];
      }
      else if ("-db".equals(arg))
      {
        db = args[(++i)];
      }
      else if ("-removePassword".equals(arg))
      {
        this.remove = true;
      }
      else if ("-trace".equals(arg))
      {
        this.trace = true;
      }
      else if ("-transactionLog".equals(arg))
      {
        this.transactionLog = true;
      }
      else
      {
        if ((arg.equals("-help")) || (arg.equals("-?")))
        {
          showUsage();
          return;
        }
        showUsageAndThrowUnsupportedOption(arg);
      }
    }
    process(dir, db);
  }
  
  public static Reader readClob(String fileName)
    throws IOException
  {
    return new BufferedReader(new InputStreamReader(readBlob(fileName), Constants.UTF8));
  }
  
  public static InputStream readBlob(String fileName)
    throws IOException
  {
    return new BufferedInputStream(FileUtils.newInputStream(fileName));
  }
  
  public static Value.ValueBlob readBlobDb(Connection conn, long lobId, long precision)
  {
    DataHandler h = ((JdbcConnection)conn).getSession().getDataHandler();
    verifyPageStore(h);
    return ValueLobDb.create(15, h, -2, lobId, null, precision);
  }
  
  private static void verifyPageStore(DataHandler h)
  {
    if ((h.getLobStorage() instanceof LobStorageMap)) {
      throw DbException.get(50100, "Restore page store recovery SQL script can only be restored to a PageStore file");
    }
  }
  
  public static Value.ValueClob readClobDb(Connection conn, long lobId, long precision)
  {
    DataHandler h = ((JdbcConnection)conn).getSession().getDataHandler();
    verifyPageStore(h);
    return ValueLobDb.create(16, h, -2, lobId, null, precision);
  }
  
  public static InputStream readBlobMap(Connection conn, long lobId, long precision)
    throws SQLException
  {
    PreparedStatement prep = conn.prepareStatement("SELECT DATA FROM INFORMATION_SCHEMA.LOB_BLOCKS WHERE LOB_ID = ? AND SEQ = ? AND ? > 0");
    
    prep.setLong(1, lobId);
    
    prep.setLong(3, precision);
    new SequenceInputStream(new Enumeration()
    {
      private int seq;
      private byte[] data = fetch();
      
      private byte[] fetch()
      {
        try
        {
          this.val$prep.setInt(2, this.seq++);
          ResultSet rs = this.val$prep.executeQuery();
          if (rs.next()) {
            return rs.getBytes(1);
          }
          return null;
        }
        catch (SQLException e)
        {
          throw DbException.convert(e);
        }
      }
      
      public boolean hasMoreElements()
      {
        return this.data != null;
      }
      
      public InputStream nextElement()
      {
        ByteArrayInputStream in = new ByteArrayInputStream(this.data);
        this.data = fetch();
        return in;
      }
    });
  }
  
  public static Reader readClobMap(Connection conn, long lobId, long precision)
    throws Exception
  {
    InputStream in = readBlobMap(conn, lobId, precision);
    return new BufferedReader(new InputStreamReader(in, Constants.UTF8));
  }
  
  private void trace(String message)
  {
    if (this.trace) {
      this.out.println(message);
    }
  }
  
  private void traceError(String message, Throwable t)
  {
    this.out.println(message + ": " + t.toString());
    if (this.trace) {
      t.printStackTrace(this.out);
    }
  }
  
  public static void execute(String dir, String db)
    throws SQLException
  {
    try
    {
      new Recover().process(dir, db);
    }
    catch (DbException e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  private void process(String dir, String db)
  {
    ArrayList<String> list = FileLister.getDatabaseFiles(dir, db, true);
    if (list.size() == 0) {
      printNoDatabaseFilesFound(dir, db);
    }
    for (String fileName : list) {
      if (fileName.endsWith(".h2.db"))
      {
        dumpPageStore(fileName);
      }
      else if (fileName.endsWith(".lob.db"))
      {
        dumpLob(fileName, false);
      }
      else if (fileName.endsWith(".mv.db"))
      {
        String f = fileName.substring(0, fileName.length() - ".h2.db".length());
        
        PrintWriter writer = getWriter(fileName, ".txt");
        MVStoreTool.dump(fileName, writer, true);
        MVStoreTool.info(fileName, writer);
        writer.close();
        writer = getWriter(f + ".h2.db", ".sql");
        dumpMVStoreFile(writer, fileName);
        writer.close();
      }
    }
  }
  
  private PrintWriter getWriter(String fileName, String suffix)
  {
    fileName = fileName.substring(0, fileName.length() - 3);
    String outputFile = fileName + suffix;
    trace("Created file: " + outputFile);
    try
    {
      return new PrintWriter(IOUtils.getBufferedWriter(FileUtils.newOutputStream(outputFile, false)));
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  private void writeDataError(PrintWriter writer, String error, byte[] data)
  {
    writer.println("-- ERROR: " + error + " storageId: " + this.storageId + " recordLength: " + this.recordLength + " valueId: " + this.valueId);
    
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < data.length; i++)
    {
      int x = data[i] & 0xFF;
      if ((x >= 32) && (x < 128)) {
        sb.append((char)x);
      } else {
        sb.append('?');
      }
    }
    writer.println("-- dump: " + sb.toString());
    sb = new StringBuilder();
    for (int i = 0; i < data.length; i++)
    {
      int x = data[i] & 0xFF;
      sb.append(' ');
      if (x < 16) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(x));
    }
    writer.println("-- dump: " + sb.toString());
  }
  
  private void dumpLob(String fileName, boolean lobCompression)
  {
    OutputStream fileOut = null;
    FileStore fileStore = null;
    long size = 0L;
    String n = fileName + (lobCompression ? ".comp" : "") + ".txt";
    InputStream in = null;
    try
    {
      fileOut = FileUtils.newOutputStream(n, false);
      fileStore = FileStore.open(null, fileName, "r");
      fileStore.init();
      in = new FileStoreInputStream(fileStore, this, lobCompression, false);
      size = IOUtils.copy(in, fileOut);
    }
    catch (Throwable e) {}finally
    {
      IOUtils.closeSilently(fileOut);
      IOUtils.closeSilently(in);
      closeSilently(fileStore);
    }
    if (size == 0L) {
      try
      {
        FileUtils.delete(n);
      }
      catch (Exception e)
      {
        traceError(n, e);
      }
    }
  }
  
  private String getSQL(String column, Value v)
  {
    if ((v instanceof ValueLob))
    {
      ValueLob lob = (ValueLob)v;
      byte[] small = lob.getSmall();
      if (small == null)
      {
        String file = lob.getFileName();
        String type = lob.getType() == 15 ? "BLOB" : "CLOB";
        if (lob.isCompressed())
        {
          dumpLob(file, true);
          file = file + ".comp";
        }
        return "READ_" + type + "('" + file + ".txt')";
      }
    }
    else if ((v instanceof ValueLobDb))
    {
      ValueLobDb lob = (ValueLobDb)v;
      byte[] small = lob.getSmall();
      if (small == null)
      {
        int type = lob.getType();
        long id = lob.getLobId();
        long precision = lob.getPrecision();
        String m;
        String columnType;
        String m;
        if (type == 15)
        {
          String columnType = "BLOB";
          m = "READ_BLOB";
        }
        else
        {
          columnType = "CLOB";
          m = "READ_CLOB";
        }
        if (this.lobMaps) {
          m = m + "_MAP";
        } else {
          m = m + "_DB";
        }
        this.columnTypeMap.put(column, columnType);
        return m + "(" + id + ", " + precision + ")";
      }
    }
    return v.getSQL();
  }
  
  private void setDatabaseName(String name)
  {
    this.databaseName = name;
  }
  
  private void dumpPageStore(String fileName)
  {
    setDatabaseName(fileName.substring(0, fileName.length() - ".h2.db".length()));
    
    PrintWriter writer = null;
    this.stat = new Stats();
    try
    {
      writer = getWriter(fileName, ".sql");
      writer.println("CREATE ALIAS IF NOT EXISTS READ_BLOB FOR \"" + getClass().getName() + ".readBlob\";");
      
      writer.println("CREATE ALIAS IF NOT EXISTS READ_CLOB FOR \"" + getClass().getName() + ".readClob\";");
      
      writer.println("CREATE ALIAS IF NOT EXISTS READ_BLOB_DB FOR \"" + getClass().getName() + ".readBlobDb\";");
      
      writer.println("CREATE ALIAS IF NOT EXISTS READ_CLOB_DB FOR \"" + getClass().getName() + ".readClobDb\";");
      
      resetSchema();
      this.store = FileStore.open(null, fileName, this.remove ? "rw" : "r");
      long length = this.store.length();
      try
      {
        this.store.init();
      }
      catch (Exception e)
      {
        writeError(writer, e);
      }
      Data s = Data.create(this, 128);
      seek(0L);
      this.store.readFully(s.getBytes(), 0, 128);
      s.setPos(48);
      this.pageSize = s.readInt();
      int writeVersion = s.readByte();
      int readVersion = s.readByte();
      writer.println("-- pageSize: " + this.pageSize + " writeVersion: " + writeVersion + " readVersion: " + readVersion);
      if ((this.pageSize < 64) || (this.pageSize > 32768))
      {
        this.pageSize = 4096;
        writer.println("-- ERROR: page size; using " + this.pageSize);
      }
      long pageCount = length / this.pageSize;
      this.parents = new int[(int)pageCount];
      s = Data.create(this, this.pageSize);
      for (long i = 3L; i < pageCount; i += 1L)
      {
        s.reset();
        seek(i);
        this.store.readFully(s.getBytes(), 0, 32);
        s.readByte();
        s.readShortInt();
        this.parents[((int)i)] = s.readInt();
      }
      int logKey = 0;int logFirstTrunkPage = 0;int logFirstDataPage = 0;
      s = Data.create(this, this.pageSize);
      for (long i = 1L; i != 3L; i += 1L)
      {
        s.reset();
        seek(i);
        this.store.readFully(s.getBytes(), 0, this.pageSize);
        CRC32 crc = new CRC32();
        crc.update(s.getBytes(), 4, this.pageSize - 4);
        int expected = (int)crc.getValue();
        int got = s.readInt();
        long writeCounter = s.readLong();
        int key = s.readInt();
        int firstTrunkPage = s.readInt();
        int firstDataPage = s.readInt();
        if (expected == got)
        {
          logKey = key;
          logFirstTrunkPage = firstTrunkPage;
          logFirstDataPage = firstDataPage;
        }
        writer.println("-- head " + i + ": writeCounter: " + writeCounter + " log " + key + ":" + firstTrunkPage + "/" + firstDataPage + " crc " + got + " (" + (expected == got ? "ok" : new StringBuilder().append("expected: ").append(expected).toString()) + ")");
      }
      writer.println("-- log " + logKey + ":" + logFirstTrunkPage + "/" + logFirstDataPage);
      
      PrintWriter devNull = new PrintWriter(new OutputStream()
      {
        public void write(int b) {}
      });
      dumpPageStore(devNull, pageCount);
      this.stat = new Stats();
      this.schema.clear();
      this.objectIdSet = New.hashSet();
      dumpPageStore(writer, pageCount);
      writeSchema(writer);
      try
      {
        dumpPageLogStream(writer, logKey, logFirstTrunkPage, logFirstDataPage, pageCount);
      }
      catch (IOException e) {}
      writer.println("---- Statistics ----");
      writer.println("-- page count: " + pageCount + ", free: " + this.stat.free);
      long total = Math.max(1L, this.stat.pageDataRows + this.stat.pageDataEmpty + this.stat.pageDataHead);
      
      writer.println("-- page data bytes: head " + this.stat.pageDataHead + ", empty " + this.stat.pageDataEmpty + ", rows " + this.stat.pageDataRows + " (" + (100L - 100L * this.stat.pageDataEmpty / total) + "% full)");
      for (int i = 0; i < this.stat.pageTypeCount.length; i++)
      {
        int count = this.stat.pageTypeCount[i];
        if (count > 0) {
          writer.println("-- " + getPageType(i) + " " + 100 * count / pageCount + "%, " + count + " page(s)");
        }
      }
      writer.close();
    }
    catch (Throwable e)
    {
      writeError(writer, e);
    }
    finally
    {
      IOUtils.closeSilently(writer);
      closeSilently(this.store);
    }
  }
  
  private void dumpMVStoreFile(PrintWriter writer, String fileName)
  {
    writer.println("-- MVStore");
    writer.println("CREATE ALIAS IF NOT EXISTS READ_BLOB FOR \"" + getClass().getName() + ".readBlob\";");
    
    writer.println("CREATE ALIAS IF NOT EXISTS READ_CLOB FOR \"" + getClass().getName() + ".readClob\";");
    
    writer.println("CREATE ALIAS IF NOT EXISTS READ_BLOB_DB FOR \"" + getClass().getName() + ".readBlobDb\";");
    
    writer.println("CREATE ALIAS IF NOT EXISTS READ_CLOB_DB FOR \"" + getClass().getName() + ".readClobDb\";");
    
    writer.println("CREATE ALIAS IF NOT EXISTS READ_BLOB_MAP FOR \"" + getClass().getName() + ".readBlobMap\";");
    
    writer.println("CREATE ALIAS IF NOT EXISTS READ_CLOB_MAP FOR \"" + getClass().getName() + ".readClobMap\";");
    
    resetSchema();
    setDatabaseName(fileName.substring(0, fileName.length() - ".mv.db".length()));
    
    MVStore mv = new MVStore.Builder().fileName(fileName).readOnly().open();
    
    dumpLobMaps(writer, mv);
    writer.println("-- Meta");
    dumpMeta(writer, mv);
    writer.println("-- Tables");
    TransactionStore store = new TransactionStore(mv);
    try
    {
      store.init();
    }
    catch (Throwable e)
    {
      writeError(writer, e);
    }
    try
    {
      for (String mapName : mv.getMapNames()) {
        if (mapName.startsWith("table."))
        {
          String tableId = mapName.substring("table.".length());
          ValueDataType keyType = new ValueDataType(null, this, null);
          
          ValueDataType valueType = new ValueDataType(null, this, null);
          
          TransactionStore.TransactionMap<Value, Value> dataMap = store.begin().openMap(mapName, keyType, valueType);
          
          Iterator<Value> dataIt = dataMap.keyIterator(null);
          boolean init = false;
          while (dataIt.hasNext())
          {
            Value rowId = (Value)dataIt.next();
            Value[] values = ((ValueArray)dataMap.get(rowId)).getList();
            this.recordLength = values.length;
            if (!init)
            {
              setStorage(Integer.parseInt(tableId));
              for (this.valueId = 0; this.valueId < this.recordLength; this.valueId += 1)
              {
                String columnName = this.storageName + "." + this.valueId;
                getSQL(columnName, values[this.valueId]);
              }
              createTemporaryTable(writer);
              init = true;
            }
            StringBuilder buff = new StringBuilder();
            buff.append("INSERT INTO O_").append(tableId).append(" VALUES(");
            for (this.valueId = 0; this.valueId < this.recordLength; this.valueId += 1)
            {
              if (this.valueId > 0) {
                buff.append(", ");
              }
              String columnName = this.storageName + "." + this.valueId;
              buff.append(getSQL(columnName, values[this.valueId]));
            }
            buff.append(");");
            writer.println(buff.toString());
            if (this.storageId == 0) {
              try
              {
                SimpleRow r = new SimpleRow(values);
                MetaRecord meta = new MetaRecord(r);
                this.schema.add(meta);
                if (meta.getObjectType() == 0)
                {
                  String sql = values[3].getString();
                  String name = extractTableOrViewName(sql);
                  this.tableMap.put(Integer.valueOf(meta.getId()), name);
                }
              }
              catch (Throwable t)
              {
                writeError(writer, t);
              }
            }
          }
        }
      }
      writeSchema(writer);
      writer.println("DROP ALIAS READ_BLOB_MAP;");
      writer.println("DROP ALIAS READ_CLOB_MAP;");
      writer.println("DROP TABLE IF EXISTS INFORMATION_SCHEMA.LOB_BLOCKS;");
    }
    catch (Throwable e)
    {
      writeError(writer, e);
    }
    finally
    {
      mv.close();
    }
  }
  
  private static void dumpMeta(PrintWriter writer, MVStore mv)
  {
    MVMap<String, String> meta = mv.getMetaMap();
    for (Map.Entry<String, String> e : meta.entrySet()) {
      writer.println("-- " + (String)e.getKey() + " = " + (String)e.getValue());
    }
  }
  
  private void dumpLobMaps(PrintWriter writer, MVStore mv)
  {
    this.lobMaps = mv.hasMap("lobData");
    if (!this.lobMaps) {
      return;
    }
    MVMap<Long, byte[]> lobData = mv.openMap("lobData");
    StreamStore streamStore = new StreamStore(lobData);
    MVMap<Long, Object[]> lobMap = mv.openMap("lobMap");
    writer.println("-- LOB");
    writer.println("CREATE TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_BLOCKS(LOB_ID BIGINT, SEQ INT, DATA BINARY, PRIMARY KEY(LOB_ID, SEQ));");
    for (Map.Entry<Long, Object[]> e : lobMap.entrySet())
    {
      long lobId = ((Long)e.getKey()).longValue();
      Object[] value = (Object[])e.getValue();
      byte[] streamStoreId = (byte[])value[0];
      InputStream in = streamStore.get(streamStoreId);
      int len = 8192;
      byte[] block = new byte[len];
      try
      {
        for (int seq = 0;; seq++)
        {
          int l = IOUtils.readFully(in, block, block.length);
          String x = StringUtils.convertBytesToHex(block, l);
          if (l > 0) {
            writer.println("INSERT INTO INFORMATION_SCHEMA.LOB_BLOCKS VALUES(" + lobId + ", " + seq + ", '" + x + "');");
          }
          if (l != len) {
            break;
          }
        }
      }
      catch (IOException ex)
      {
        writeError(writer, ex);
      }
    }
  }
  
  private static String getPageType(int type)
  {
    switch (type)
    {
    case 0: 
      return "free";
    case 1: 
      return "data leaf";
    case 2: 
      return "data node";
    case 3: 
      return "data overflow";
    case 4: 
      return "btree leaf";
    case 5: 
      return "btree node";
    case 6: 
      return "free list";
    case 7: 
      return "stream trunk";
    case 8: 
      return "stream data";
    }
    return "[" + type + "]";
  }
  
  private void dumpPageStore(PrintWriter writer, long pageCount)
  {
    Data s = Data.create(this, this.pageSize);
    for (long page = 3L; page < pageCount; page += 1L)
    {
      s = Data.create(this, this.pageSize);
      seek(page);
      this.store.readFully(s.getBytes(), 0, this.pageSize);
      dumpPage(writer, s, page, pageCount);
    }
  }
  
  private void dumpPage(PrintWriter writer, Data s, long page, long pageCount)
  {
    try
    {
      int type = s.readByte();
      switch (type)
      {
      case 0: 
        this.stat.pageTypeCount[type] += 1;
        return;
      }
      boolean last = (type & 0x10) != 0;
      type &= 0xFFFFFFEF;
      if (!PageStore.checksumTest(s.getBytes(), (int)page, this.pageSize)) {
        writeDataError(writer, "checksum mismatch type: " + type, s.getBytes());
      }
      s.readShortInt();
      switch (type)
      {
      case 1: 
        this.stat.pageTypeCount[type] += 1;
        int parentPageId = s.readInt();
        setStorage(s.readVarInt());
        int columnCount = s.readVarInt();
        int entries = s.readShortInt();
        writer.println("-- page " + page + ": data leaf " + (last ? "(last) " : "") + "parent: " + parentPageId + " table: " + this.storageId + " entries: " + entries + " columns: " + columnCount);
        
        dumpPageDataLeaf(writer, s, last, page, columnCount, entries);
        break;
      case 2: 
        this.stat.pageTypeCount[type] += 1;
        int parentPageId = s.readInt();
        setStorage(s.readVarInt());
        int rowCount = s.readInt();
        int entries = s.readShortInt();
        writer.println("-- page " + page + ": data node " + (last ? "(last) " : "") + "parent: " + parentPageId + " table: " + this.storageId + " entries: " + entries + " rowCount: " + rowCount);
        
        dumpPageDataNode(writer, s, page, entries);
        break;
      case 3: 
        this.stat.pageTypeCount[type] += 1;
        writer.println("-- page " + page + ": data overflow " + (last ? "(last) " : ""));
        
        break;
      case 4: 
        this.stat.pageTypeCount[type] += 1;
        int parentPageId = s.readInt();
        setStorage(s.readVarInt());
        int entries = s.readShortInt();
        writer.println("-- page " + page + ": b-tree leaf " + (last ? "(last) " : "") + "parent: " + parentPageId + " index: " + this.storageId + " entries: " + entries);
        if (this.trace) {
          dumpPageBtreeLeaf(writer, s, entries, !last);
        }
        break;
      case 5: 
        this.stat.pageTypeCount[type] += 1;
        int parentPageId = s.readInt();
        setStorage(s.readVarInt());
        writer.println("-- page " + page + ": b-tree node " + (last ? "(last) " : "") + "parent: " + parentPageId + " index: " + this.storageId);
        
        dumpPageBtreeNode(writer, s, page, !last);
        break;
      case 6: 
        this.stat.pageTypeCount[type] += 1;
        writer.println("-- page " + page + ": free list " + (last ? "(last)" : ""));
        this.stat.free += dumpPageFreeList(writer, s, page, pageCount);
        break;
      case 7: 
        this.stat.pageTypeCount[type] += 1;
        writer.println("-- page " + page + ": log trunk");
        break;
      case 8: 
        this.stat.pageTypeCount[type] += 1;
        writer.println("-- page " + page + ": log data");
        break;
      default: 
        writer.println("-- ERROR page " + page + " unknown type " + type);
      }
    }
    catch (Exception e)
    {
      writeError(writer, e);
    }
  }
  
  private void dumpPageLogStream(PrintWriter writer, int logKey, int logFirstTrunkPage, int logFirstDataPage, long pageCount)
    throws IOException
  {
    Data s = Data.create(this, this.pageSize);
    DataReader in = new DataReader(new PageInputStream(writer, this, this.store, logKey, logFirstTrunkPage, logFirstDataPage, this.pageSize));
    
    writer.println("---- Transaction log ----");
    CompressLZF compress = new CompressLZF();
    for (;;)
    {
      int x = in.readByte();
      if (x < 0) {
        break;
      }
      if (x != 0) {
        if (x == 1)
        {
          int pageId = in.readVarInt();
          int size = in.readVarInt();
          byte[] data = new byte[this.pageSize];
          if (size == 0)
          {
            in.readFully(data, this.pageSize);
          }
          else if (size != 1)
          {
            byte[] compressBuffer = new byte[size];
            in.readFully(compressBuffer, size);
            try
            {
              compress.expand(compressBuffer, 0, size, data, 0, this.pageSize);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
              throw DbException.convertToIOException(e);
            }
          }
          String typeName = "";
          int type = data[0];
          boolean last = (type & 0x10) != 0;
          type &= 0xFFFFFFEF;
          switch (type)
          {
          case 0: 
            typeName = "empty";
            break;
          case 1: 
            typeName = "data leaf " + (last ? "(last)" : "");
            break;
          case 2: 
            typeName = "data node " + (last ? "(last)" : "");
            break;
          case 3: 
            typeName = "data overflow " + (last ? "(last)" : "");
            break;
          case 4: 
            typeName = "b-tree leaf " + (last ? "(last)" : "");
            break;
          case 5: 
            typeName = "b-tree node " + (last ? "(last)" : "");
            break;
          case 6: 
            typeName = "free list " + (last ? "(last)" : "");
            break;
          case 7: 
            typeName = "log trunk";
            break;
          case 8: 
            typeName = "log data";
            break;
          default: 
            typeName = "ERROR: unknown type " + type;
          }
          writer.println("-- undo page " + pageId + " " + typeName);
          if (this.trace)
          {
            Data d = Data.create(null, data);
            dumpPage(writer, d, pageId, pageCount);
          }
        }
        else if (x == 5)
        {
          int sessionId = in.readVarInt();
          setStorage(in.readVarInt());
          Row row = PageLog.readRow(in, s);
          writer.println("-- session " + sessionId + " table " + this.storageId + " + " + row.toString());
          if (this.transactionLog) {
            if ((this.storageId == 0) && (row.getColumnCount() >= 4))
            {
              int tableId = (int)row.getKey();
              String sql = row.getValue(3).getString();
              String name = extractTableOrViewName(sql);
              if (row.getValue(2).getInt() == 0) {
                this.tableMap.put(Integer.valueOf(tableId), name);
              }
              writer.println(sql + ";");
            }
            else
            {
              String tableName = (String)this.tableMap.get(Integer.valueOf(this.storageId));
              if (tableName != null)
              {
                StatementBuilder buff = new StatementBuilder();
                buff.append("INSERT INTO ").append(tableName).append(" VALUES(");
                for (int i = 0; i < row.getColumnCount(); i++)
                {
                  buff.appendExceptFirst(", ");
                  buff.append(row.getValue(i).getSQL());
                }
                buff.append(");");
                writer.println(buff.toString());
              }
            }
          }
        }
        else if (x == 6)
        {
          int sessionId = in.readVarInt();
          setStorage(in.readVarInt());
          long key = in.readVarLong();
          writer.println("-- session " + sessionId + " table " + this.storageId + " - " + key);
          if (this.transactionLog) {
            if (this.storageId == 0)
            {
              int tableId = (int)key;
              String tableName = (String)this.tableMap.get(Integer.valueOf(tableId));
              if (tableName != null) {
                writer.println("DROP TABLE IF EXISTS " + tableName + ";");
              }
            }
            else
            {
              String tableName = (String)this.tableMap.get(Integer.valueOf(this.storageId));
              if (tableName != null)
              {
                String sql = "DELETE FROM " + tableName + " WHERE _ROWID_ = " + key + ";";
                
                writer.println(sql);
              }
            }
          }
        }
        else if (x == 7)
        {
          int sessionId = in.readVarInt();
          setStorage(in.readVarInt());
          writer.println("-- session " + sessionId + " table " + this.storageId + " truncate");
          if (this.transactionLog) {
            writer.println("TRUNCATE TABLE " + this.storageId);
          }
        }
        else if (x == 2)
        {
          int sessionId = in.readVarInt();
          writer.println("-- commit " + sessionId);
        }
        else if (x == 4)
        {
          int sessionId = in.readVarInt();
          writer.println("-- rollback " + sessionId);
        }
        else if (x == 3)
        {
          int sessionId = in.readVarInt();
          String transaction = in.readString();
          writer.println("-- prepare commit " + sessionId + " " + transaction);
        }
        else if (x != 0)
        {
          if (x == 8)
          {
            writer.println("-- checkpoint");
          }
          else if (x == 9)
          {
            int size = in.readVarInt();
            StringBuilder buff = new StringBuilder("-- free");
            for (int i = 0; i < size; i++) {
              buff.append(' ').append(in.readVarInt());
            }
            writer.println(buff);
          }
          else
          {
            writer.println("-- ERROR: unknown operation " + x);
            break;
          }
        }
      }
    }
  }
  
  private String setStorage(int storageId)
  {
    this.storageId = storageId;
    this.storageName = ("O_" + String.valueOf(storageId).replace('-', 'M'));
    return this.storageName;
  }
  
  static class PageInputStream
    extends InputStream
  {
    private final PrintWriter writer;
    private final FileStore store;
    private final Data page;
    private final int pageSize;
    private long trunkPage;
    private long nextTrunkPage;
    private long dataPage;
    private final IntArray dataPages = new IntArray();
    private boolean endOfFile;
    private int remaining;
    private int logKey;
    
    public PageInputStream(PrintWriter writer, DataHandler handler, FileStore store, int logKey, long firstTrunkPage, long firstDataPage, int pageSize)
    {
      this.writer = writer;
      this.store = store;
      this.pageSize = pageSize;
      this.logKey = (logKey - 1);
      this.nextTrunkPage = firstTrunkPage;
      this.dataPage = firstDataPage;
      this.page = Data.create(handler, pageSize);
    }
    
    public int read()
    {
      byte[] b = { 0 };
      int len = read(b);
      return len < 0 ? -1 : b[0] & 0xFF;
    }
    
    public int read(byte[] b)
    {
      return read(b, 0, b.length);
    }
    
    public int read(byte[] b, int off, int len)
    {
      if (len == 0) {
        return 0;
      }
      int read = 0;
      while (len > 0)
      {
        int r = readBlock(b, off, len);
        if (r < 0) {
          break;
        }
        read += r;
        off += r;
        len -= r;
      }
      return read == 0 ? -1 : read;
    }
    
    private int readBlock(byte[] buff, int off, int len)
    {
      fillBuffer();
      if (this.endOfFile) {
        return -1;
      }
      int l = Math.min(this.remaining, len);
      this.page.read(buff, off, l);
      this.remaining -= l;
      return l;
    }
    
    private void fillBuffer()
    {
      if ((this.remaining > 0) || (this.endOfFile)) {
        return;
      }
      while (this.dataPages.size() == 0)
      {
        if (this.nextTrunkPage == 0L)
        {
          this.endOfFile = true;
          return;
        }
        this.trunkPage = this.nextTrunkPage;
        this.store.seek(this.trunkPage * this.pageSize);
        this.store.readFully(this.page.getBytes(), 0, this.pageSize);
        this.page.reset();
        if (!PageStore.checksumTest(this.page.getBytes(), (int)this.trunkPage, this.pageSize))
        {
          this.writer.println("-- ERROR: checksum mismatch page: " + this.trunkPage);
          this.endOfFile = true;
          return;
        }
        int t = this.page.readByte();
        this.page.readShortInt();
        if (t != 7)
        {
          this.writer.println("-- log eof " + this.trunkPage + " type: " + t + " expected type: " + 7);
          
          this.endOfFile = true;
          return;
        }
        this.page.readInt();
        int key = this.page.readInt();
        this.logKey += 1;
        if (key != this.logKey) {
          this.writer.println("-- log eof " + this.trunkPage + " type: " + t + " expected key: " + this.logKey + " got: " + key);
        }
        this.nextTrunkPage = this.page.readInt();
        this.writer.println("-- log " + key + ":" + this.trunkPage + " next: " + this.nextTrunkPage);
        
        int pageCount = this.page.readShortInt();
        for (int i = 0; i < pageCount; i++)
        {
          int d = this.page.readInt();
          if (this.dataPage != 0L)
          {
            if (d == this.dataPage) {
              this.dataPage = 0L;
            }
          }
          else {
            this.dataPages.add(d);
          }
        }
      }
      if (this.dataPages.size() > 0)
      {
        this.page.reset();
        long nextPage = this.dataPages.get(0);
        this.dataPages.remove(0);
        this.store.seek(nextPage * this.pageSize);
        this.store.readFully(this.page.getBytes(), 0, this.pageSize);
        this.page.reset();
        int t = this.page.readByte();
        if ((t != 0) && (!PageStore.checksumTest(this.page.getBytes(), (int)nextPage, this.pageSize)))
        {
          this.writer.println("-- ERROR: checksum mismatch page: " + nextPage);
          this.endOfFile = true;
          return;
        }
        this.page.readShortInt();
        int p = this.page.readInt();
        int k = this.page.readInt();
        this.writer.println("-- log " + k + ":" + this.trunkPage + "/" + nextPage);
        if (t != 8)
        {
          this.writer.println("-- log eof " + nextPage + " type: " + t + " parent: " + p + " expected type: " + 8);
          
          this.endOfFile = true;
          return;
        }
        if (k != this.logKey)
        {
          this.writer.println("-- log eof " + nextPage + " type: " + t + " parent: " + p + " expected key: " + this.logKey + " got: " + k);
          
          this.endOfFile = true;
          return;
        }
        this.remaining = (this.pageSize - this.page.length());
      }
    }
  }
  
  private void dumpPageBtreeNode(PrintWriter writer, Data s, long pageId, boolean positionOnly)
  {
    int rowCount = s.readInt();
    int entryCount = s.readShortInt();
    int[] children = new int[entryCount + 1];
    int[] offsets = new int[entryCount];
    children[entryCount] = s.readInt();
    checkParent(writer, pageId, children, entryCount);
    int empty = Integer.MAX_VALUE;
    for (int i = 0; i < entryCount; i++)
    {
      children[i] = s.readInt();
      checkParent(writer, pageId, children, i);
      int off = s.readShortInt();
      empty = Math.min(off, empty);
      offsets[i] = off;
    }
    empty -= s.length();
    if (!this.trace) {
      return;
    }
    writer.println("--   empty: " + empty);
    for (int i = 0; i < entryCount; i++)
    {
      int off = offsets[i];
      s.setPos(off);
      long key = s.readVarLong();
      Value data;
      Value data;
      if (positionOnly) {
        data = ValueLong.get(key);
      } else {
        try
        {
          data = s.readValue();
        }
        catch (Throwable e)
        {
          writeDataError(writer, "exception " + e, s.getBytes());
          continue;
        }
      }
      writer.println("-- [" + i + "] child: " + children[i] + " key: " + key + " data: " + data);
    }
    writer.println("-- [" + entryCount + "] child: " + children[entryCount] + " rowCount: " + rowCount);
  }
  
  private int dumpPageFreeList(PrintWriter writer, Data s, long pageId, long pageCount)
  {
    int pagesAddressed = PageFreeList.getPagesAddressed(this.pageSize);
    BitField used = new BitField();
    for (int i = 0; i < pagesAddressed; i += 8)
    {
      int x = s.readByte() & 0xFF;
      for (int j = 0; j < 8; j++) {
        if ((x & 1 << j) != 0) {
          used.set(i + j);
        }
      }
    }
    int free = 0;
    long i = 0L;
    for (long j = pageId; (i < pagesAddressed) && (j < pageCount); j += 1L)
    {
      if ((i == 0L) || (j % 100L == 0L))
      {
        if (i > 0L) {
          writer.println();
        }
        writer.print("-- " + j + " ");
      }
      else if (j % 20L == 0L)
      {
        writer.print(" - ");
      }
      else if (j % 10L == 0L)
      {
        writer.print(' ');
      }
      writer.print(used.get((int)i) ? '1' : '0');
      if (!used.get((int)i)) {
        free++;
      }
      i += 1L;
    }
    writer.println();
    return free;
  }
  
  private void dumpPageBtreeLeaf(PrintWriter writer, Data s, int entryCount, boolean positionOnly)
  {
    int[] offsets = new int[entryCount];
    int empty = Integer.MAX_VALUE;
    for (int i = 0; i < entryCount; i++)
    {
      int off = s.readShortInt();
      empty = Math.min(off, empty);
      offsets[i] = off;
    }
    empty -= s.length();
    writer.println("--   empty: " + empty);
    for (int i = 0; i < entryCount; i++)
    {
      int off = offsets[i];
      s.setPos(off);
      long key = s.readVarLong();
      Value data;
      Value data;
      if (positionOnly) {
        data = ValueLong.get(key);
      } else {
        try
        {
          data = s.readValue();
        }
        catch (Throwable e)
        {
          writeDataError(writer, "exception " + e, s.getBytes());
          continue;
        }
      }
      writer.println("-- [" + i + "] key: " + key + " data: " + data);
    }
  }
  
  private void checkParent(PrintWriter writer, long pageId, int[] children, int index)
  {
    int child = children[index];
    if ((child < 0) || (child >= this.parents.length)) {
      writer.println("-- ERROR [" + pageId + "] child[" + index + "]: " + child + " >= page count: " + this.parents.length);
    } else if (this.parents[child] != pageId) {
      writer.println("-- ERROR [" + pageId + "] child[" + index + "]: " + child + " parent: " + this.parents[child]);
    }
  }
  
  private void dumpPageDataNode(PrintWriter writer, Data s, long pageId, int entryCount)
  {
    int[] children = new int[entryCount + 1];
    long[] keys = new long[entryCount];
    children[entryCount] = s.readInt();
    checkParent(writer, pageId, children, entryCount);
    for (int i = 0; i < entryCount; i++)
    {
      children[i] = s.readInt();
      checkParent(writer, pageId, children, i);
      keys[i] = s.readVarLong();
    }
    if (!this.trace) {
      return;
    }
    for (int i = 0; i < entryCount; i++) {
      writer.println("-- [" + i + "] child: " + children[i] + " key: " + keys[i]);
    }
    writer.println("-- [" + entryCount + "] child: " + children[entryCount]);
  }
  
  private void dumpPageDataLeaf(PrintWriter writer, Data s, boolean last, long pageId, int columnCount, int entryCount)
  {
    long[] keys = new long[entryCount];
    int[] offsets = new int[entryCount];
    long next = 0L;
    if (!last)
    {
      next = s.readInt();
      writer.println("--   next: " + next);
    }
    int empty = this.pageSize;
    for (int i = 0; i < entryCount; i++)
    {
      keys[i] = s.readVarLong();
      int off = s.readShortInt();
      empty = Math.min(off, empty);
      offsets[i] = off;
    }
    this.stat.pageDataRows += this.pageSize - empty;
    empty -= s.length();
    this.stat.pageDataHead += s.length();
    this.stat.pageDataEmpty += empty;
    if (this.trace) {
      writer.println("--   empty: " + empty);
    }
    if (!last)
    {
      Data s2 = Data.create(this, this.pageSize);
      s.setPos(this.pageSize);
      long parent = pageId;
      for (;;)
      {
        checkParent(writer, parent, new int[] { (int)next }, 0);
        parent = next;
        seek(next);
        this.store.readFully(s2.getBytes(), 0, this.pageSize);
        s2.reset();
        int type = s2.readByte();
        s2.readShortInt();
        s2.readInt();
        if (type == 19)
        {
          int size = s2.readShortInt();
          writer.println("-- chain: " + next + " type: " + type + " size: " + size);
          
          s.checkCapacity(size);
          s.write(s2.getBytes(), s2.length(), size);
          break;
        }
        if (type == 3)
        {
          next = s2.readInt();
          if (next == 0L)
          {
            writeDataError(writer, "next:0", s2.getBytes());
            break;
          }
          int size = this.pageSize - s2.length();
          writer.println("-- chain: " + next + " type: " + type + " size: " + size + " next: " + next);
          
          s.checkCapacity(size);
          s.write(s2.getBytes(), s2.length(), size);
        }
        else
        {
          writeDataError(writer, "type: " + type, s2.getBytes());
          break;
        }
      }
    }
    for (int i = 0; i < entryCount; i++)
    {
      long key = keys[i];
      int off = offsets[i];
      if (this.trace) {
        writer.println("-- [" + i + "] storage: " + this.storageId + " key: " + key + " off: " + off);
      }
      s.setPos(off);
      Value[] data = createRecord(writer, s, columnCount);
      if (data != null)
      {
        createTemporaryTable(writer);
        writeRow(writer, s, data);
        if ((this.remove) && (this.storageId == 0))
        {
          String sql = data[3].getString();
          if (sql.startsWith("CREATE USER "))
          {
            int saltIndex = Utils.indexOf(s.getBytes(), "SALT ".getBytes(), off);
            if (saltIndex >= 0)
            {
              String userName = sql.substring("CREATE USER ".length(), sql.indexOf("SALT ") - 1);
              if (userName.startsWith("IF NOT EXISTS ")) {
                userName = userName.substring("IF NOT EXISTS ".length());
              }
              if (userName.startsWith("\"")) {
                userName = userName.substring(1, userName.length() - 1);
              }
              byte[] userPasswordHash = SHA256.getKeyPasswordHash(userName, "".toCharArray());
              
              byte[] salt = MathUtils.secureRandomBytes(8);
              byte[] passwordHash = SHA256.getHashWithSalt(userPasswordHash, salt);
              
              StringBuilder buff = new StringBuilder();
              buff.append("SALT '").append(StringUtils.convertBytesToHex(salt)).append("' HASH '").append(StringUtils.convertBytesToHex(passwordHash)).append('\'');
              
              byte[] replacement = buff.toString().getBytes();
              System.arraycopy(replacement, 0, s.getBytes(), saltIndex, replacement.length);
              
              seek(pageId);
              this.store.write(s.getBytes(), 0, this.pageSize);
              if (this.trace) {
                this.out.println("User: " + userName);
              }
              this.remove = false;
            }
          }
        }
      }
    }
  }
  
  private void seek(long page)
  {
    this.store.seek(page * this.pageSize);
  }
  
  private Value[] createRecord(PrintWriter writer, Data s, int columnCount)
  {
    this.recordLength = columnCount;
    if (columnCount <= 0)
    {
      writeDataError(writer, "columnCount<0", s.getBytes());
      return null;
    }
    Value[] data;
    try
    {
      data = new Value[columnCount];
    }
    catch (OutOfMemoryError e)
    {
      writeDataError(writer, "out of memory", s.getBytes());
      return null;
    }
    return data;
  }
  
  private void writeRow(PrintWriter writer, Data s, Value[] data)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO " + this.storageName + " VALUES(");
    for (this.valueId = 0; this.valueId < this.recordLength; this.valueId += 1) {
      try
      {
        Value v = s.readValue();
        data[this.valueId] = v;
        if (this.valueId > 0) {
          sb.append(", ");
        }
        String columnName = this.storageName + "." + this.valueId;
        sb.append(getSQL(columnName, v));
      }
      catch (Exception e)
      {
        writeDataError(writer, "exception " + e, s.getBytes());
      }
      catch (OutOfMemoryError e)
      {
        writeDataError(writer, "out of memory", s.getBytes());
      }
    }
    sb.append(");");
    writer.println(sb.toString());
    if (this.storageId == 0) {
      try
      {
        SimpleRow r = new SimpleRow(data);
        MetaRecord meta = new MetaRecord(r);
        this.schema.add(meta);
        if (meta.getObjectType() == 0)
        {
          String sql = data[3].getString();
          String name = extractTableOrViewName(sql);
          this.tableMap.put(Integer.valueOf(meta.getId()), name);
        }
      }
      catch (Throwable t)
      {
        writeError(writer, t);
      }
    }
  }
  
  private void resetSchema()
  {
    this.schema = New.arrayList();
    this.objectIdSet = New.hashSet();
    this.tableMap = New.hashMap();
    this.columnTypeMap = New.hashMap();
  }
  
  private void writeSchema(PrintWriter writer)
  {
    writer.println("---- Schema ----");
    Collections.sort(this.schema);
    for (MetaRecord m : this.schema) {
      if (!isSchemaObjectTypeDelayed(m))
      {
        String sql = m.getSQL();
        writer.println(sql + ";");
      }
    }
    boolean deleteLobs = false;
    for (Map.Entry<Integer, String> entry : this.tableMap.entrySet())
    {
      Integer objectId = (Integer)entry.getKey();
      String name = (String)entry.getValue();
      if ((this.objectIdSet.contains(objectId)) && 
        (name.startsWith("INFORMATION_SCHEMA.LOB")))
      {
        setStorage(objectId.intValue());
        writer.println("DELETE FROM " + name + ";");
        writer.println("INSERT INTO " + name + " SELECT * FROM " + this.storageName + ";");
        if (name.startsWith("INFORMATION_SCHEMA.LOBS"))
        {
          writer.println("UPDATE " + name + " SET TABLE = " + -2 + ";");
          
          deleteLobs = true;
        }
      }
    }
    for (Map.Entry<Integer, String> entry : this.tableMap.entrySet())
    {
      Integer objectId = (Integer)entry.getKey();
      String name = (String)entry.getValue();
      if (this.objectIdSet.contains(objectId))
      {
        setStorage(objectId.intValue());
        if (!name.startsWith("INFORMATION_SCHEMA.LOB")) {
          writer.println("INSERT INTO " + name + " SELECT * FROM " + this.storageName + ";");
        }
      }
    }
    for (Integer objectId : this.objectIdSet)
    {
      setStorage(objectId.intValue());
      writer.println("DROP TABLE " + this.storageName + ";");
    }
    writer.println("DROP ALIAS READ_BLOB;");
    writer.println("DROP ALIAS READ_CLOB;");
    writer.println("DROP ALIAS READ_BLOB_DB;");
    writer.println("DROP ALIAS READ_CLOB_DB;");
    if (deleteLobs) {
      writer.println("DELETE FROM INFORMATION_SCHEMA.LOBS WHERE TABLE = -2;");
    }
    for (MetaRecord m : this.schema) {
      if (isSchemaObjectTypeDelayed(m))
      {
        String sql = m.getSQL();
        writer.println(sql + ";");
      }
    }
  }
  
  private static boolean isSchemaObjectTypeDelayed(MetaRecord m)
  {
    switch (m.getObjectType())
    {
    case 1: 
    case 4: 
    case 5: 
      return true;
    }
    return false;
  }
  
  private void createTemporaryTable(PrintWriter writer)
  {
    if (!this.objectIdSet.contains(Integer.valueOf(this.storageId)))
    {
      this.objectIdSet.add(Integer.valueOf(this.storageId));
      StatementBuilder buff = new StatementBuilder("CREATE TABLE ");
      buff.append(this.storageName).append('(');
      for (int i = 0; i < this.recordLength; i++)
      {
        buff.appendExceptFirst(", ");
        buff.append('C').append(i).append(' ');
        String columnType = (String)this.columnTypeMap.get(this.storageName + "." + i);
        if (columnType == null) {
          buff.append("VARCHAR");
        } else {
          buff.append(columnType);
        }
      }
      writer.println(buff.append(");").toString());
      writer.flush();
    }
  }
  
  private static String extractTableOrViewName(String sql)
  {
    int indexTable = sql.indexOf(" TABLE ");
    int indexView = sql.indexOf(" VIEW ");
    if ((indexTable > 0) && (indexView > 0)) {
      if (indexTable < indexView) {
        indexView = -1;
      } else {
        indexTable = -1;
      }
    }
    if (indexView > 0) {
      sql = sql.substring(indexView + " VIEW ".length());
    } else if (indexTable > 0) {
      sql = sql.substring(indexTable + " TABLE ".length());
    } else {
      return "UNKNOWN";
    }
    if (sql.startsWith("IF NOT EXISTS ")) {
      sql = sql.substring("IF NOT EXISTS ".length());
    }
    boolean ignore = false;
    for (int i = 0; i < sql.length(); i++)
    {
      char ch = sql.charAt(i);
      if (ch == '"')
      {
        ignore = !ignore;
      }
      else if ((!ignore) && ((ch <= ' ') || (ch == '(')))
      {
        sql = sql.substring(0, i);
        return sql;
      }
    }
    return "UNKNOWN";
  }
  
  private static void closeSilently(FileStore fileStore)
  {
    if (fileStore != null) {
      fileStore.closeSilently();
    }
  }
  
  private void writeError(PrintWriter writer, Throwable e)
  {
    if (writer != null) {
      writer.println("// error: " + e);
    }
    traceError("Error", e);
  }
  
  public String getDatabasePath()
  {
    return this.databaseName;
  }
  
  public FileStore openFile(String name, String mode, boolean mustExist)
  {
    return FileStore.open(this, name, "rw");
  }
  
  public void checkPowerOff() {}
  
  public void checkWritingAllowed() {}
  
  public int getMaxLengthInplaceLob()
  {
    throw DbException.throwInternalError();
  }
  
  public String getLobCompressionAlgorithm(int type)
  {
    return null;
  }
  
  public Object getLobSyncObject()
  {
    return this;
  }
  
  public SmallLRUCache<String, String[]> getLobFileListCache()
  {
    return null;
  }
  
  public TempFileDeleter getTempFileDeleter()
  {
    return TempFileDeleter.getInstance();
  }
  
  public LobStorageBackend getLobStorage()
  {
    return null;
  }
  
  public int readLob(long lobId, byte[] hmac, long offset, byte[] buff, int off, int length)
  {
    throw DbException.throwInternalError();
  }
  
  public JavaObjectSerializer getJavaObjectSerializer()
  {
    return null;
  }
}
