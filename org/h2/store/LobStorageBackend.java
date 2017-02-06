package org.h2.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.h2.engine.Database;
import org.h2.engine.SysProperties;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.tools.CompressTool;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.value.Value;
import org.h2.value.ValueLobDb;

public class LobStorageBackend
  implements LobStorageInterface
{
  public static final String LOB_DATA_TABLE = "LOB_DATA";
  private static final String LOB_SCHEMA = "INFORMATION_SCHEMA";
  private static final String LOBS = "INFORMATION_SCHEMA.LOBS";
  private static final String LOB_MAP = "INFORMATION_SCHEMA.LOB_MAP";
  private static final String LOB_DATA = "INFORMATION_SCHEMA.LOB_DATA";
  private static final int BLOCK_LENGTH = 20000;
  private static final int HASH_CACHE_SIZE = 4096;
  JdbcConnection conn;
  final Database database;
  private final HashMap<String, PreparedStatement> prepared = New.hashMap();
  private long nextBlock;
  private final CompressTool compress = CompressTool.getInstance();
  private long[] hashBlocks;
  private boolean init;
  
  public LobStorageBackend(Database database)
  {
    this.database = database;
  }
  
  public void init()
  {
    if (this.init) {
      return;
    }
    synchronized (this.database)
    {
      if (this.init) {
        return;
      }
      this.init = true;
      this.conn = this.database.getLobConnectionForRegularUse();
      JdbcConnection initConn = this.database.getLobConnectionForInit();
      try
      {
        Statement stat = initConn.createStatement();
        
        boolean create = true;
        PreparedStatement prep = initConn.prepareStatement("SELECT ZERO() FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=? AND TABLE_NAME=? AND COLUMN_NAME=?");
        
        prep.setString(1, "INFORMATION_SCHEMA");
        prep.setString(2, "LOB_MAP");
        prep.setString(3, "POS");
        
        ResultSet rs = prep.executeQuery();
        if (rs.next())
        {
          prep = initConn.prepareStatement("SELECT ZERO() FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=? AND TABLE_NAME=?");
          
          prep.setString(1, "INFORMATION_SCHEMA");
          prep.setString(2, "LOB_DATA");
          rs = prep.executeQuery();
          if (rs.next()) {
            create = false;
          }
        }
        if (create)
        {
          stat.execute("CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOBS(ID BIGINT PRIMARY KEY, BYTE_COUNT BIGINT, TABLE INT) HIDDEN");
          
          stat.execute("CREATE INDEX IF NOT EXISTS INFORMATION_SCHEMA.INDEX_LOB_TABLE ON INFORMATION_SCHEMA.LOBS(TABLE)");
          
          stat.execute("CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_MAP(LOB BIGINT, SEQ INT, POS BIGINT, HASH INT, BLOCK BIGINT, PRIMARY KEY(LOB, SEQ)) HIDDEN");
          
          stat.execute("ALTER TABLE INFORMATION_SCHEMA.LOB_MAP RENAME TO INFORMATION_SCHEMA.LOB_MAP HIDDEN");
          
          stat.execute("ALTER TABLE INFORMATION_SCHEMA.LOB_MAP ADD IF NOT EXISTS POS BIGINT BEFORE HASH");
          
          stat.execute("ALTER TABLE INFORMATION_SCHEMA.LOB_MAP DROP COLUMN IF EXISTS \"OFFSET\"");
          
          stat.execute("CREATE INDEX IF NOT EXISTS INFORMATION_SCHEMA.INDEX_LOB_MAP_DATA_LOB ON INFORMATION_SCHEMA.LOB_MAP(BLOCK, LOB)");
          
          stat.execute("CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_DATA(BLOCK BIGINT PRIMARY KEY, COMPRESSED INT, DATA BINARY) HIDDEN");
        }
        rs = stat.executeQuery("SELECT MAX(BLOCK) FROM INFORMATION_SCHEMA.LOB_DATA");
        rs.next();
        this.nextBlock = (rs.getLong(1) + 1L);
        stat.close();
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    }
  }
  
  private long getNextLobId()
    throws SQLException
  {
    String sql = "SELECT MAX(LOB) FROM INFORMATION_SCHEMA.LOB_MAP";
    PreparedStatement prep = prepare(sql);
    ResultSet rs = prep.executeQuery();
    rs.next();
    long x = rs.getLong(1) + 1L;
    reuse(sql, prep);
    sql = "SELECT MAX(ID) FROM INFORMATION_SCHEMA.LOBS";
    prep = prepare(sql);
    rs = prep.executeQuery();
    rs.next();
    x = Math.max(x, rs.getLong(1) + 1L);
    reuse(sql, prep);
    return x;
  }
  
  public void removeAllForTable(int tableId)
  {
    init();
    try
    {
      String sql = "SELECT ID FROM INFORMATION_SCHEMA.LOBS WHERE TABLE = ?";
      PreparedStatement prep = prepare(sql);
      prep.setInt(1, tableId);
      ResultSet rs = prep.executeQuery();
      while (rs.next()) {
        removeLob(rs.getLong(1));
      }
      reuse(sql, prep);
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    if (tableId == -1)
    {
      removeAllForTable(-2);
      removeAllForTable(-3);
    }
  }
  
  byte[] readBlock(long block)
    throws SQLException
  {
    assertNotHolds(this.conn.getSession());
    synchronized (this.database)
    {
      synchronized (this.conn.getSession())
      {
        String sql = "SELECT COMPRESSED, DATA FROM INFORMATION_SCHEMA.LOB_DATA WHERE BLOCK = ?";
        
        PreparedStatement prep = prepare(sql);
        prep.setLong(1, block);
        ResultSet rs = prep.executeQuery();
        if (!rs.next()) {
          throw DbException.get(90028, "Missing lob entry, block: " + block).getSQLException();
        }
        int compressed = rs.getInt(1);
        byte[] buffer = rs.getBytes(2);
        if (compressed != 0) {
          buffer = this.compress.expand(buffer);
        }
        reuse(sql, prep);
        return buffer;
      }
    }
  }
  
  PreparedStatement prepare(String sql)
    throws SQLException
  {
    if ((SysProperties.CHECK2) && 
      (!Thread.holdsLock(this.database))) {
      throw DbException.throwInternalError();
    }
    PreparedStatement prep = (PreparedStatement)this.prepared.remove(sql);
    if (prep == null) {
      prep = this.conn.prepareStatement(sql);
    }
    return prep;
  }
  
  void reuse(String sql, PreparedStatement prep)
  {
    if ((SysProperties.CHECK2) && 
      (!Thread.holdsLock(this.database))) {
      throw DbException.throwInternalError();
    }
    this.prepared.put(sql, prep);
  }
  
  public void removeLob(ValueLobDb lob)
  {
    removeLob(lob.getLobId());
  }
  
  private void removeLob(long lobId)
  {
    try
    {
      assertNotHolds(this.conn.getSession());
      synchronized (this.database)
      {
        synchronized (this.conn.getSession())
        {
          String sql = "SELECT BLOCK, HASH FROM INFORMATION_SCHEMA.LOB_MAP D WHERE D.LOB = ? AND NOT EXISTS(SELECT 1 FROM INFORMATION_SCHEMA.LOB_MAP O WHERE O.BLOCK = D.BLOCK AND O.LOB <> ?)";
          
          PreparedStatement prep = prepare(sql);
          prep.setLong(1, lobId);
          prep.setLong(2, lobId);
          ResultSet rs = prep.executeQuery();
          ArrayList<Long> blocks = New.arrayList();
          while (rs.next())
          {
            blocks.add(Long.valueOf(rs.getLong(1)));
            int hash = rs.getInt(2);
            setHashCacheBlock(hash, -1L);
          }
          reuse(sql, prep);
          
          sql = "DELETE FROM INFORMATION_SCHEMA.LOB_MAP WHERE LOB = ?";
          prep = prepare(sql);
          prep.setLong(1, lobId);
          prep.execute();
          reuse(sql, prep);
          
          sql = "DELETE FROM INFORMATION_SCHEMA.LOB_DATA WHERE BLOCK = ?";
          prep = prepare(sql);
          for (Iterator i$ = blocks.iterator(); i$.hasNext();)
          {
            long block = ((Long)i$.next()).longValue();
            prep.setLong(1, block);
            prep.execute();
          }
          reuse(sql, prep);
          
          sql = "DELETE FROM INFORMATION_SCHEMA.LOBS WHERE ID = ?";
          prep = prepare(sql);
          prep.setLong(1, lobId);
          prep.execute();
          reuse(sql, prep);
        }
      }
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
  }
  
  /* Error */
  public InputStream getInputStream(ValueLobDb lob, byte[] hmac, long byteCount)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 197	org/h2/store/LobStorageBackend:init	()V
    //   4: aload_0
    //   5: getfield 82	org/h2/store/LobStorageBackend:conn	Lorg/h2/jdbc/JdbcConnection;
    //   8: invokevirtual 218	org/h2/jdbc/JdbcConnection:getSession	()Lorg/h2/engine/SessionInterface;
    //   11: invokestatic 222	org/h2/store/LobStorageBackend:assertNotHolds	(Ljava/lang/Object;)V
    //   14: aload_0
    //   15: getfield 68	org/h2/store/LobStorageBackend:database	Lorg/h2/engine/Database;
    //   18: dup
    //   19: astore 5
    //   21: monitorenter
    //   22: aload_0
    //   23: getfield 82	org/h2/store/LobStorageBackend:conn	Lorg/h2/jdbc/JdbcConnection;
    //   26: invokevirtual 218	org/h2/jdbc/JdbcConnection:getSession	()Lorg/h2/engine/SessionInterface;
    //   29: dup
    //   30: astore 6
    //   32: monitorenter
    //   33: aload_1
    //   34: invokevirtual 301	org/h2/value/ValueLobDb:getLobId	()J
    //   37: lstore 7
    //   39: new 9	org/h2/store/LobStorageBackend$LobInputStream
    //   42: dup
    //   43: aload_0
    //   44: lload 7
    //   46: lload_3
    //   47: invokespecial 362	org/h2/store/LobStorageBackend$LobInputStream:<init>	(Lorg/h2/store/LobStorageBackend;JJ)V
    //   50: aload 6
    //   52: monitorexit
    //   53: aload 5
    //   55: monitorexit
    //   56: areturn
    //   57: astore 9
    //   59: aload 6
    //   61: monitorexit
    //   62: aload 9
    //   64: athrow
    //   65: astore 10
    //   67: aload 5
    //   69: monitorexit
    //   70: aload 10
    //   72: athrow
    //   73: astore 5
    //   75: aload 5
    //   77: invokestatic 366	org/h2/message/DbException:convertToIOException	(Ljava/lang/Throwable;)Ljava/io/IOException;
    //   80: athrow
    // Line number table:
    //   Java source line #335	-> byte code offset #0
    //   Java source line #336	-> byte code offset #4
    //   Java source line #338	-> byte code offset #14
    //   Java source line #339	-> byte code offset #22
    //   Java source line #340	-> byte code offset #33
    //   Java source line #341	-> byte code offset #39
    //   Java source line #342	-> byte code offset #57
    //   Java source line #343	-> byte code offset #65
    //   Java source line #344	-> byte code offset #73
    //   Java source line #345	-> byte code offset #75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	this	LobStorageBackend
    //   0	81	1	lob	ValueLobDb
    //   0	81	2	hmac	byte[]
    //   0	81	3	byteCount	long
    //   73	3	5	e	SQLException
    //   37	8	7	lobId	long
    //   57	6	9	localObject1	Object
    //   65	6	10	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   33	53	57	finally
    //   57	62	57	finally
    //   22	56	65	finally
    //   57	70	65	finally
    //   0	56	73	java/sql/SQLException
    //   57	73	73	java/sql/SQLException
  }
  
  /* Error */
  private ValueLobDb addLob(InputStream in, long maxLength, int type, CountingReaderInputStream countingReaderForClob)
  {
    // Byte code:
    //   0: sipush 20000
    //   3: newarray <illegal type>
    //   5: astore 6
    //   7: lload_2
    //   8: lconst_0
    //   9: lcmp
    //   10: ifge +7 -> 17
    //   13: ldc2_w 371
    //   16: lstore_2
    //   17: lconst_0
    //   18: lstore 7
    //   20: ldc2_w 321
    //   23: lstore 9
    //   25: aload_0
    //   26: getfield 68	org/h2/store/LobStorageBackend:database	Lorg/h2/engine/Database;
    //   29: invokevirtual 376	org/h2/engine/Database:getMaxLengthInplaceLob	()I
    //   32: istore 11
    //   34: aload_0
    //   35: getfield 68	org/h2/store/LobStorageBackend:database	Lorg/h2/engine/Database;
    //   38: iload 4
    //   40: invokevirtual 380	org/h2/engine/Database:getLobCompressionAlgorithm	(I)Ljava/lang/String;
    //   43: astore 12
    //   45: aconst_null
    //   46: astore 13
    //   48: iconst_0
    //   49: istore 14
    //   51: lload_2
    //   52: lconst_0
    //   53: lcmp
    //   54: ifle +194 -> 248
    //   57: ldc2_w 385
    //   60: lload_2
    //   61: invokestatic 389	java/lang/Math:min	(JJ)J
    //   64: l2i
    //   65: istore 15
    //   67: aload_1
    //   68: aload 6
    //   70: iload 15
    //   72: invokestatic 395	org/h2/util/IOUtils:readFully	(Ljava/io/InputStream;[BI)I
    //   75: istore 15
    //   77: iload 15
    //   79: ifgt +6 -> 85
    //   82: goto +166 -> 248
    //   85: lload_2
    //   86: iload 15
    //   88: i2l
    //   89: lsub
    //   90: lstore_2
    //   91: iload 15
    //   93: aload 6
    //   95: arraylength
    //   96: if_icmpeq +23 -> 119
    //   99: iload 15
    //   101: newarray <illegal type>
    //   103: astore 16
    //   105: aload 6
    //   107: iconst_0
    //   108: aload 16
    //   110: iconst_0
    //   111: iload 15
    //   113: invokestatic 401	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   116: goto +7 -> 123
    //   119: aload 6
    //   121: astore 16
    //   123: iload 14
    //   125: ifne +27 -> 152
    //   128: aload 16
    //   130: arraylength
    //   131: sipush 20000
    //   134: if_icmpge +18 -> 152
    //   137: aload 16
    //   139: arraylength
    //   140: iload 11
    //   142: if_icmpgt +10 -> 152
    //   145: aload 16
    //   147: astore 13
    //   149: goto +99 -> 248
    //   152: aload_0
    //   153: getfield 82	org/h2/store/LobStorageBackend:conn	Lorg/h2/jdbc/JdbcConnection;
    //   156: invokevirtual 218	org/h2/jdbc/JdbcConnection:getSession	()Lorg/h2/engine/SessionInterface;
    //   159: invokestatic 222	org/h2/store/LobStorageBackend:assertNotHolds	(Ljava/lang/Object;)V
    //   162: aload_0
    //   163: getfield 68	org/h2/store/LobStorageBackend:database	Lorg/h2/engine/Database;
    //   166: dup
    //   167: astore 17
    //   169: monitorenter
    //   170: aload_0
    //   171: getfield 82	org/h2/store/LobStorageBackend:conn	Lorg/h2/jdbc/JdbcConnection;
    //   174: invokevirtual 218	org/h2/jdbc/JdbcConnection:getSession	()Lorg/h2/engine/SessionInterface;
    //   177: dup
    //   178: astore 18
    //   180: monitorenter
    //   181: iload 14
    //   183: ifne +9 -> 192
    //   186: aload_0
    //   187: invokespecial 403	org/h2/store/LobStorageBackend:getNextLobId	()J
    //   190: lstore 9
    //   192: aload_0
    //   193: lload 9
    //   195: iload 14
    //   197: lload 7
    //   199: aload 16
    //   201: aload 12
    //   203: invokevirtual 407	org/h2/store/LobStorageBackend:storeBlock	(JIJ[BLjava/lang/String;)V
    //   206: aload 18
    //   208: monitorexit
    //   209: goto +11 -> 220
    //   212: astore 19
    //   214: aload 18
    //   216: monitorexit
    //   217: aload 19
    //   219: athrow
    //   220: aload 17
    //   222: monitorexit
    //   223: goto +11 -> 234
    //   226: astore 20
    //   228: aload 17
    //   230: monitorexit
    //   231: aload 20
    //   233: athrow
    //   234: lload 7
    //   236: iload 15
    //   238: i2l
    //   239: ladd
    //   240: lstore 7
    //   242: iinc 14 1
    //   245: goto -194 -> 51
    //   248: lload 9
    //   250: ldc2_w 321
    //   253: lcmp
    //   254: ifne +13 -> 267
    //   257: aload 13
    //   259: ifnonnull +8 -> 267
    //   262: iconst_0
    //   263: newarray <illegal type>
    //   265: astore 13
    //   267: aload 13
    //   269: ifnull +36 -> 305
    //   272: aload 5
    //   274: ifnonnull +10 -> 284
    //   277: aload 13
    //   279: arraylength
    //   280: i2l
    //   281: goto +8 -> 289
    //   284: aload 5
    //   286: invokevirtual 410	org/h2/store/CountingReaderInputStream:getLength	()J
    //   289: lstore 14
    //   291: iload 4
    //   293: aload 13
    //   295: lload 14
    //   297: invokestatic 414	org/h2/value/ValueLobDb:createSmallLob	(I[BJ)Lorg/h2/value/ValueLobDb;
    //   300: astore 16
    //   302: aload 16
    //   304: areturn
    //   305: aload 5
    //   307: ifnonnull +8 -> 315
    //   310: lload 7
    //   312: goto +8 -> 320
    //   315: aload 5
    //   317: invokevirtual 410	org/h2/store/CountingReaderInputStream:getLength	()J
    //   320: lstore 14
    //   322: aload_0
    //   323: iload 4
    //   325: lload 9
    //   327: bipush -2
    //   329: lload 7
    //   331: lload 14
    //   333: invokespecial 418	org/h2/store/LobStorageBackend:registerLob	(IJIJJ)Lorg/h2/value/ValueLobDb;
    //   336: areturn
    //   337: astore 13
    //   339: lload 9
    //   341: ldc2_w 321
    //   344: lcmp
    //   345: ifeq +9 -> 354
    //   348: aload_0
    //   349: lload 9
    //   351: invokespecial 209	org/h2/store/LobStorageBackend:removeLob	(J)V
    //   354: aload 13
    //   356: aconst_null
    //   357: invokestatic 422	org/h2/message/DbException:convertIOException	(Ljava/io/IOException;Ljava/lang/String;)Lorg/h2/message/DbException;
    //   360: athrow
    //   361: astore 6
    //   363: aload 6
    //   365: invokestatic 160	org/h2/message/DbException:convert	(Ljava/lang/Throwable;)Lorg/h2/message/DbException;
    //   368: athrow
    // Line number table:
    //   Java source line #352	-> byte code offset #0
    //   Java source line #353	-> byte code offset #7
    //   Java source line #354	-> byte code offset #13
    //   Java source line #356	-> byte code offset #17
    //   Java source line #357	-> byte code offset #20
    //   Java source line #358	-> byte code offset #25
    //   Java source line #359	-> byte code offset #34
    //   Java source line #361	-> byte code offset #45
    //   Java source line #362	-> byte code offset #48
    //   Java source line #363	-> byte code offset #57
    //   Java source line #364	-> byte code offset #67
    //   Java source line #365	-> byte code offset #77
    //   Java source line #366	-> byte code offset #82
    //   Java source line #368	-> byte code offset #85
    //   Java source line #371	-> byte code offset #91
    //   Java source line #372	-> byte code offset #99
    //   Java source line #373	-> byte code offset #105
    //   Java source line #375	-> byte code offset #119
    //   Java source line #377	-> byte code offset #123
    //   Java source line #379	-> byte code offset #145
    //   Java source line #380	-> byte code offset #149
    //   Java source line #382	-> byte code offset #152
    //   Java source line #384	-> byte code offset #162
    //   Java source line #385	-> byte code offset #170
    //   Java source line #386	-> byte code offset #181
    //   Java source line #387	-> byte code offset #186
    //   Java source line #389	-> byte code offset #192
    //   Java source line #390	-> byte code offset #206
    //   Java source line #391	-> byte code offset #220
    //   Java source line #392	-> byte code offset #234
    //   Java source line #362	-> byte code offset #242
    //   Java source line #394	-> byte code offset #248
    //   Java source line #396	-> byte code offset #262
    //   Java source line #398	-> byte code offset #267
    //   Java source line #401	-> byte code offset #272
    //   Java source line #403	-> byte code offset #291
    //   Java source line #404	-> byte code offset #302
    //   Java source line #408	-> byte code offset #305
    //   Java source line #410	-> byte code offset #322
    //   Java source line #412	-> byte code offset #337
    //   Java source line #413	-> byte code offset #339
    //   Java source line #414	-> byte code offset #348
    //   Java source line #416	-> byte code offset #354
    //   Java source line #418	-> byte code offset #361
    //   Java source line #419	-> byte code offset #363
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	369	0	this	LobStorageBackend
    //   0	369	1	in	InputStream
    //   0	369	2	maxLength	long
    //   0	369	4	type	int
    //   0	369	5	countingReaderForClob	CountingReaderInputStream
    //   5	115	6	buff	byte[]
    //   361	3	6	e	SQLException
    //   18	312	7	length	long
    //   23	327	9	lobId	long
    //   32	109	11	maxLengthInPlaceLob	int
    //   43	159	12	compressAlgorithm	String
    //   46	248	13	small	byte[]
    //   337	18	13	e	IOException
    //   49	194	14	seq	int
    //   289	7	14	precision	long
    //   320	12	14	precision	long
    //   65	172	15	len	int
    //   103	6	16	b	byte[]
    //   121	79	16	b	byte[]
    //   212	6	19	localObject1	Object
    //   226	6	20	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   181	209	212	finally
    //   212	217	212	finally
    //   170	223	226	finally
    //   226	231	226	finally
    //   45	304	337	java/io/IOException
    //   305	336	337	java/io/IOException
    //   0	304	361	java/sql/SQLException
    //   305	336	361	java/sql/SQLException
    //   337	361	361	java/sql/SQLException
  }
  
  private ValueLobDb registerLob(int type, long lobId, int tableId, long byteCount, long precision)
    throws SQLException
  {
    assertNotHolds(this.conn.getSession());
    synchronized (this.database)
    {
      synchronized (this.conn.getSession())
      {
        String sql = "INSERT INTO INFORMATION_SCHEMA.LOBS(ID, BYTE_COUNT, TABLE) VALUES(?, ?, ?)";
        
        PreparedStatement prep = prepare(sql);
        prep.setLong(1, lobId);
        prep.setLong(2, byteCount);
        prep.setInt(3, tableId);
        prep.execute();
        reuse(sql, prep);
        ValueLobDb v = ValueLobDb.create(type, this.database, tableId, lobId, null, precision);
        
        return v;
      }
    }
  }
  
  public boolean isReadOnly()
  {
    return this.database.isReadOnly();
  }
  
  public ValueLobDb copyLob(ValueLobDb old, int tableId, long length)
  {
    int type = old.getType();
    long oldLobId = old.getLobId();
    assertNotHolds(this.conn.getSession());
    synchronized (this.database)
    {
      synchronized (this.conn.getSession())
      {
        try
        {
          init();
          long lobId = getNextLobId();
          String sql = "INSERT INTO INFORMATION_SCHEMA.LOB_MAP(LOB, SEQ, POS, HASH, BLOCK) SELECT ?, SEQ, POS, HASH, BLOCK FROM INFORMATION_SCHEMA.LOB_MAP WHERE LOB = ?";
          
          PreparedStatement prep = prepare(sql);
          prep.setLong(1, lobId);
          prep.setLong(2, oldLobId);
          prep.executeUpdate();
          reuse(sql, prep);
          
          sql = "INSERT INTO INFORMATION_SCHEMA.LOBS(ID, BYTE_COUNT, TABLE) SELECT ?, BYTE_COUNT, ? FROM INFORMATION_SCHEMA.LOBS WHERE ID = ?";
          
          prep = prepare(sql);
          prep.setLong(1, lobId);
          prep.setLong(2, tableId);
          prep.setLong(3, oldLobId);
          prep.executeUpdate();
          reuse(sql, prep);
          
          ValueLobDb v = ValueLobDb.create(type, this.database, tableId, lobId, null, length);
          return v;
        }
        catch (SQLException e)
        {
          throw DbException.convert(e);
        }
      }
    }
  }
  
  private long getHashCacheBlock(int hash)
  {
    initHashCache();
    int index = hash & 0xFFF;
    long oldHash = this.hashBlocks[index];
    if (oldHash == hash) {
      return this.hashBlocks[(index + 4096)];
    }
    return -1L;
  }
  
  private void setHashCacheBlock(int hash, long block)
  {
    initHashCache();
    int index = hash & 0xFFF;
    this.hashBlocks[index] = hash;
    this.hashBlocks[(index + 4096)] = block;
  }
  
  private void initHashCache()
  {
    if (this.hashBlocks == null) {
      this.hashBlocks = new long[' '];
    }
  }
  
  void storeBlock(long lobId, int seq, long pos, byte[] b, String compressAlgorithm)
    throws SQLException
  {
    boolean blockExists = false;
    if (compressAlgorithm != null) {
      b = this.compress.compress(b, compressAlgorithm);
    }
    int hash = Arrays.hashCode(b);
    assertHoldsLock(this.conn.getSession());
    assertHoldsLock(this.database);
    long block = getHashCacheBlock(hash);
    if (block != -1L)
    {
      String sql = "SELECT COMPRESSED, DATA FROM INFORMATION_SCHEMA.LOB_DATA WHERE BLOCK = ?";
      
      PreparedStatement prep = prepare(sql);
      prep.setLong(1, block);
      ResultSet rs = prep.executeQuery();
      if (rs.next())
      {
        boolean compressed = rs.getInt(1) != 0;
        byte[] compare = rs.getBytes(2);
        if (compressed == (compressAlgorithm != null)) {
          if (Arrays.equals(b, compare)) {
            blockExists = true;
          }
        }
      }
      reuse(sql, prep);
    }
    if (!blockExists)
    {
      block = this.nextBlock++;
      setHashCacheBlock(hash, block);
      String sql = "INSERT INTO INFORMATION_SCHEMA.LOB_DATA(BLOCK, COMPRESSED, DATA) VALUES(?, ?, ?)";
      
      PreparedStatement prep = prepare(sql);
      prep.setLong(1, block);
      prep.setInt(2, compressAlgorithm == null ? 0 : 1);
      prep.setBytes(3, b);
      prep.execute();
      reuse(sql, prep);
    }
    String sql = "INSERT INTO INFORMATION_SCHEMA.LOB_MAP(LOB, SEQ, POS, HASH, BLOCK) VALUES(?, ?, ?, ?, ?)";
    
    PreparedStatement prep = prepare(sql);
    prep.setLong(1, lobId);
    prep.setInt(2, seq);
    prep.setLong(3, pos);
    prep.setLong(4, hash);
    prep.setLong(5, block);
    prep.execute();
    reuse(sql, prep);
  }
  
  public Value createBlob(InputStream in, long maxLength)
  {
    init();
    return addLob(in, maxLength, 15, null);
  }
  
  public Value createClob(Reader reader, long maxLength)
  {
    init();
    long max = maxLength == -1L ? Long.MAX_VALUE : maxLength;
    CountingReaderInputStream in = new CountingReaderInputStream(reader, max);
    ValueLobDb lob = addLob(in, Long.MAX_VALUE, 16, in);
    return lob;
  }
  
  public void setTable(ValueLobDb lob, int table)
  {
    long lobId = lob.getLobId();
    assertNotHolds(this.conn.getSession());
    synchronized (this.database)
    {
      synchronized (this.conn.getSession())
      {
        try
        {
          init();
          String sql = "UPDATE INFORMATION_SCHEMA.LOBS SET TABLE = ? WHERE ID = ?";
          PreparedStatement prep = prepare(sql);
          prep.setInt(1, table);
          prep.setLong(2, lobId);
          prep.executeUpdate();
          reuse(sql, prep);
        }
        catch (SQLException e)
        {
          throw DbException.convert(e);
        }
      }
    }
  }
  
  private static void assertNotHolds(Object lock)
  {
    if (Thread.holdsLock(lock)) {
      throw DbException.throwInternalError();
    }
  }
  
  static void assertHoldsLock(Object lock)
  {
    if (!Thread.holdsLock(lock)) {
      throw DbException.throwInternalError();
    }
  }
  
  public class LobInputStream
    extends InputStream
  {
    private final long[] lobMapBlocks;
    private int lobMapIndex;
    private long remainingBytes;
    private byte[] buffer;
    private int bufferPos;
    
    public LobInputStream(long lobId, long byteCount)
      throws SQLException
    {
      LobStorageBackend.assertHoldsLock(LobStorageBackend.this.conn.getSession());
      LobStorageBackend.assertHoldsLock(LobStorageBackend.this.database);
      String sql;
      if (byteCount == -1L)
      {
        sql = "SELECT BYTE_COUNT FROM INFORMATION_SCHEMA.LOBS WHERE ID = ?";
        PreparedStatement prep = LobStorageBackend.this.prepare(sql);
        prep.setLong(1, lobId);
        ResultSet rs = prep.executeQuery();
        if (!rs.next()) {
          throw DbException.get(90028, "Missing lob entry: " + lobId).getSQLException();
        }
        byteCount = rs.getLong(1);
        LobStorageBackend.this.reuse(sql, prep);
      }
      this.remainingBytes = byteCount;
      
      String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.LOB_MAP WHERE LOB = ?";
      PreparedStatement prep = LobStorageBackend.this.prepare(sql);
      prep.setLong(1, lobId);
      ResultSet rs = prep.executeQuery();
      rs.next();
      int lobMapCount = rs.getInt(1);
      if (lobMapCount == 0) {
        throw DbException.get(90028, "Missing lob entry: " + lobId).getSQLException();
      }
      LobStorageBackend.this.reuse(sql, prep);
      
      this.lobMapBlocks = new long[lobMapCount];
      
      sql = "SELECT BLOCK FROM INFORMATION_SCHEMA.LOB_MAP WHERE LOB = ? ORDER BY SEQ";
      prep = LobStorageBackend.this.prepare(sql);
      prep.setLong(1, lobId);
      rs = prep.executeQuery();
      int i = 0;
      while (rs.next())
      {
        this.lobMapBlocks[i] = rs.getLong(1);
        i++;
      }
      LobStorageBackend.this.reuse(sql, prep);
    }
    
    public int read()
      throws IOException
    {
      fillBuffer();
      if (this.remainingBytes <= 0L) {
        return -1;
      }
      this.remainingBytes -= 1L;
      return this.buffer[(this.bufferPos++)] & 0xFF;
    }
    
    public long skip(long n)
      throws IOException
    {
      if (n <= 0L) {
        return 0L;
      }
      long remaining = n;
      remaining -= skipSmall(remaining);
      if (remaining > 20000L)
      {
        while (remaining > 20000L)
        {
          remaining -= 20000L;
          this.remainingBytes -= 20000L;
          this.lobMapIndex += 1;
        }
        this.bufferPos = 0;
        this.buffer = null;
      }
      fillBuffer();
      remaining -= skipSmall(remaining);
      remaining -= super.skip(remaining);
      return n - remaining;
    }
    
    private int skipSmall(long n)
    {
      if ((this.buffer != null) && (this.bufferPos < this.buffer.length))
      {
        int x = MathUtils.convertLongToInt(Math.min(n, this.buffer.length - this.bufferPos));
        this.bufferPos += x;
        this.remainingBytes -= x;
        return x;
      }
      return 0;
    }
    
    public int available()
      throws IOException
    {
      return MathUtils.convertLongToInt(this.remainingBytes);
    }
    
    public int read(byte[] buff)
      throws IOException
    {
      return readFully(buff, 0, buff.length);
    }
    
    public int read(byte[] buff, int off, int length)
      throws IOException
    {
      return readFully(buff, off, length);
    }
    
    private int readFully(byte[] buff, int off, int length)
      throws IOException
    {
      if (length == 0) {
        return 0;
      }
      int read = 0;
      while (length > 0)
      {
        fillBuffer();
        if (this.remainingBytes <= 0L) {
          break;
        }
        int len = (int)Math.min(length, this.remainingBytes);
        len = Math.min(len, this.buffer.length - this.bufferPos);
        System.arraycopy(this.buffer, this.bufferPos, buff, off, len);
        this.bufferPos += len;
        read += len;
        this.remainingBytes -= len;
        off += len;
        length -= len;
      }
      return read == 0 ? -1 : read;
    }
    
    private void fillBuffer()
      throws IOException
    {
      if ((this.buffer != null) && (this.bufferPos < this.buffer.length)) {
        return;
      }
      if (this.remainingBytes <= 0L) {
        return;
      }
      if (this.lobMapIndex >= this.lobMapBlocks.length) {
        System.out.println("halt!");
      }
      try
      {
        this.buffer = LobStorageBackend.this.readBlock(this.lobMapBlocks[this.lobMapIndex]);
        this.lobMapIndex += 1;
        this.bufferPos = 0;
      }
      catch (SQLException e)
      {
        throw DbException.convertToIOException(e);
      }
    }
  }
}
