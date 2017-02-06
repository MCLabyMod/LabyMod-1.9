package org.h2.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.h2.command.CommandInterface;
import org.h2.engine.SessionInterface;
import org.h2.expression.ParameterInterface;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.util.DateTimeUtils;
import org.h2.util.IOUtils;
import org.h2.util.New;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueByte;
import org.h2.value.ValueBytes;
import org.h2.value.ValueDate;
import org.h2.value.ValueDecimal;
import org.h2.value.ValueDouble;
import org.h2.value.ValueFloat;
import org.h2.value.ValueInt;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueShort;
import org.h2.value.ValueString;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;

public class JdbcPreparedStatement
  extends JdbcStatement
  implements PreparedStatement
{
  protected CommandInterface command;
  private final String sqlStatement;
  private ArrayList<Value[]> batchParameters;
  private HashMap<String, Integer> cachedColumnLabelMap;
  
  JdbcPreparedStatement(JdbcConnection conn, String sql, int id, int resultSetType, int resultSetConcurrency, boolean closeWithResultSet)
  {
    super(conn, id, resultSetType, resultSetConcurrency, closeWithResultSet);
    setTrace(this.session.getTrace(), 3, id);
    this.sqlStatement = sql;
    this.command = conn.prepareCommand(sql, this.fetchSize);
  }
  
  void setCachedColumnLabelMap(HashMap<String, Integer> cachedColumnLabelMap)
  {
    this.cachedColumnLabelMap = cachedColumnLabelMap;
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      int id = getNextId(4);
      if (isDebugEnabled()) {
        debugCodeAssign("ResultSet", 4, id, "executeQuery()");
      }
      synchronized (this.session)
      {
        checkClosed();
        closeOldResultSet();
        
        boolean scrollable = this.resultSetType != 1003;
        boolean updatable = this.resultSetConcurrency == 1008;
        ResultInterface result;
        try
        {
          setExecutingStatement(this.command);
          result = this.command.executeQuery(this.maxRows, scrollable);
        }
        finally
        {
          setExecutingStatement(null);
        }
        this.resultSet = new JdbcResultSet(this.conn, this, result, id, this.closedByResultSet, scrollable, updatable, this.cachedColumnLabelMap);
      }
      return this.resultSet;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /* Error */
  public int executeUpdate()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -114
    //   3: invokevirtual 146	org/h2/jdbc/JdbcPreparedStatement:debugCodeCall	(Ljava/lang/String;)V
    //   6: aload_0
    //   7: invokevirtual 149	org/h2/jdbc/JdbcPreparedStatement:checkClosedForWrite	()Z
    //   10: pop
    //   11: aload_0
    //   12: invokespecial 152	org/h2/jdbc/JdbcPreparedStatement:executeUpdateInternal	()I
    //   15: istore_1
    //   16: aload_0
    //   17: invokevirtual 155	org/h2/jdbc/JdbcPreparedStatement:afterWriting	()V
    //   20: iload_1
    //   21: ireturn
    //   22: astore_2
    //   23: aload_0
    //   24: invokevirtual 155	org/h2/jdbc/JdbcPreparedStatement:afterWriting	()V
    //   27: aload_2
    //   28: athrow
    //   29: astore_1
    //   30: aload_0
    //   31: aload_1
    //   32: invokevirtual 133	org/h2/jdbc/JdbcPreparedStatement:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   35: athrow
    // Line number table:
    //   Java source line #140	-> byte code offset #0
    //   Java source line #141	-> byte code offset #6
    //   Java source line #143	-> byte code offset #11
    //   Java source line #145	-> byte code offset #16
    //   Java source line #147	-> byte code offset #29
    //   Java source line #148	-> byte code offset #30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	this	JdbcPreparedStatement
    //   29	3	1	e	Exception
    //   22	6	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	16	22	finally
    //   22	23	22	finally
    //   0	20	29	java/lang/Exception
    //   22	29	29	java/lang/Exception
  }
  
  private int executeUpdateInternal()
    throws SQLException
  {
    closeOldResultSet();
    synchronized (this.session)
    {
      try
      {
        setExecutingStatement(this.command);
        this.updateCount = this.command.executeUpdate();
      }
      finally
      {
        setExecutingStatement(null);
      }
    }
    return this.updateCount;
  }
  
  /* Error */
  public boolean execute()
    throws SQLException
  {
    // Byte code:
    //   0: iconst_4
    //   1: invokestatic 74	org/h2/jdbc/JdbcPreparedStatement:getNextId	(I)I
    //   4: istore_1
    //   5: aload_0
    //   6: invokevirtual 78	org/h2/jdbc/JdbcPreparedStatement:isDebugEnabled	()Z
    //   9: ifeq +9 -> 18
    //   12: aload_0
    //   13: ldc -94
    //   15: invokevirtual 146	org/h2/jdbc/JdbcPreparedStatement:debugCodeCall	(Ljava/lang/String;)V
    //   18: aload_0
    //   19: invokevirtual 149	org/h2/jdbc/JdbcPreparedStatement:checkClosedForWrite	()Z
    //   22: pop
    //   23: aload_0
    //   24: getfield 119	org/h2/jdbc/JdbcPreparedStatement:conn	Lorg/h2/jdbc/JdbcConnection;
    //   27: invokevirtual 166	org/h2/jdbc/JdbcConnection:getSession	()Lorg/h2/engine/SessionInterface;
    //   30: dup
    //   31: astore_3
    //   32: monitorenter
    //   33: aload_0
    //   34: invokevirtual 93	org/h2/jdbc/JdbcPreparedStatement:closeOldResultSet	()V
    //   37: aload_0
    //   38: aload_0
    //   39: getfield 50	org/h2/jdbc/JdbcPreparedStatement:command	Lorg/h2/command/CommandInterface;
    //   42: invokevirtual 103	org/h2/jdbc/JdbcPreparedStatement:setExecutingStatement	(Lorg/h2/command/CommandInterface;)V
    //   45: aload_0
    //   46: getfield 50	org/h2/jdbc/JdbcPreparedStatement:command	Lorg/h2/command/CommandInterface;
    //   49: invokeinterface 169 1 0
    //   54: ifeq +86 -> 140
    //   57: iconst_1
    //   58: istore_2
    //   59: aload_0
    //   60: getfield 95	org/h2/jdbc/JdbcPreparedStatement:resultSetType	I
    //   63: sipush 1003
    //   66: if_icmpeq +7 -> 73
    //   69: iconst_1
    //   70: goto +4 -> 74
    //   73: iconst_0
    //   74: istore 4
    //   76: aload_0
    //   77: getfield 99	org/h2/jdbc/JdbcPreparedStatement:resultSetConcurrency	I
    //   80: sipush 1008
    //   83: if_icmpne +7 -> 90
    //   86: iconst_1
    //   87: goto +4 -> 91
    //   90: iconst_0
    //   91: istore 5
    //   93: aload_0
    //   94: getfield 50	org/h2/jdbc/JdbcPreparedStatement:command	Lorg/h2/command/CommandInterface;
    //   97: aload_0
    //   98: getfield 106	org/h2/jdbc/JdbcPreparedStatement:maxRows	I
    //   101: iload 4
    //   103: invokeinterface 111 3 0
    //   108: astore 6
    //   110: aload_0
    //   111: new 117	org/h2/jdbc/JdbcResultSet
    //   114: dup
    //   115: aload_0
    //   116: getfield 119	org/h2/jdbc/JdbcPreparedStatement:conn	Lorg/h2/jdbc/JdbcConnection;
    //   119: aload_0
    //   120: aload 6
    //   122: iload_1
    //   123: aload_0
    //   124: getfield 122	org/h2/jdbc/JdbcPreparedStatement:closedByResultSet	Z
    //   127: iload 4
    //   129: iload 5
    //   131: invokespecial 172	org/h2/jdbc/JdbcResultSet:<init>	(Lorg/h2/jdbc/JdbcConnection;Lorg/h2/jdbc/JdbcStatement;Lorg/h2/result/ResultInterface;IZZZ)V
    //   134: putfield 129	org/h2/jdbc/JdbcPreparedStatement:resultSet	Lorg/h2/jdbc/JdbcResultSet;
    //   137: goto +18 -> 155
    //   140: iconst_0
    //   141: istore_2
    //   142: aload_0
    //   143: aload_0
    //   144: getfield 50	org/h2/jdbc/JdbcPreparedStatement:command	Lorg/h2/command/CommandInterface;
    //   147: invokeinterface 157 1 0
    //   152: putfield 160	org/h2/jdbc/JdbcPreparedStatement:updateCount	I
    //   155: aload_0
    //   156: aconst_null
    //   157: invokevirtual 103	org/h2/jdbc/JdbcPreparedStatement:setExecutingStatement	(Lorg/h2/command/CommandInterface;)V
    //   160: goto +13 -> 173
    //   163: astore 7
    //   165: aload_0
    //   166: aconst_null
    //   167: invokevirtual 103	org/h2/jdbc/JdbcPreparedStatement:setExecutingStatement	(Lorg/h2/command/CommandInterface;)V
    //   170: aload 7
    //   172: athrow
    //   173: aload_3
    //   174: monitorexit
    //   175: goto +10 -> 185
    //   178: astore 8
    //   180: aload_3
    //   181: monitorexit
    //   182: aload 8
    //   184: athrow
    //   185: iload_2
    //   186: istore_3
    //   187: aload_0
    //   188: invokevirtual 155	org/h2/jdbc/JdbcPreparedStatement:afterWriting	()V
    //   191: iload_3
    //   192: ireturn
    //   193: astore 9
    //   195: aload_0
    //   196: invokevirtual 155	org/h2/jdbc/JdbcPreparedStatement:afterWriting	()V
    //   199: aload 9
    //   201: athrow
    //   202: astore_1
    //   203: aload_0
    //   204: aload_1
    //   205: invokevirtual 133	org/h2/jdbc/JdbcPreparedStatement:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   208: athrow
    // Line number table:
    //   Java source line #177	-> byte code offset #0
    //   Java source line #178	-> byte code offset #5
    //   Java source line #179	-> byte code offset #12
    //   Java source line #181	-> byte code offset #18
    //   Java source line #184	-> byte code offset #23
    //   Java source line #185	-> byte code offset #33
    //   Java source line #187	-> byte code offset #37
    //   Java source line #188	-> byte code offset #45
    //   Java source line #189	-> byte code offset #57
    //   Java source line #190	-> byte code offset #59
    //   Java source line #191	-> byte code offset #76
    //   Java source line #192	-> byte code offset #93
    //   Java source line #193	-> byte code offset #110
    //   Java source line #196	-> byte code offset #137
    //   Java source line #197	-> byte code offset #140
    //   Java source line #198	-> byte code offset #142
    //   Java source line #201	-> byte code offset #155
    //   Java source line #202	-> byte code offset #160
    //   Java source line #201	-> byte code offset #163
    //   Java source line #203	-> byte code offset #173
    //   Java source line #204	-> byte code offset #185
    //   Java source line #206	-> byte code offset #187
    //   Java source line #208	-> byte code offset #202
    //   Java source line #209	-> byte code offset #203
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	209	0	this	JdbcPreparedStatement
    //   4	119	1	id	int
    //   202	3	1	e	Exception
    //   58	2	2	returnsResultSet	boolean
    //   141	45	2	returnsResultSet	boolean
    //   74	54	4	scrollable	boolean
    //   91	39	5	updatable	boolean
    //   108	13	6	result	ResultInterface
    //   163	8	7	localObject1	Object
    //   178	5	8	localObject2	Object
    //   193	7	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   37	155	163	finally
    //   163	165	163	finally
    //   33	175	178	finally
    //   178	182	178	finally
    //   23	187	193	finally
    //   193	195	193	finally
    //   0	191	202	java/lang/Exception
    //   193	202	202	java/lang/Exception
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      debugCodeCall("clearParameters");
      checkClosed();
      ArrayList<? extends ParameterInterface> parameters = this.command.getParameters();
      int i = 0;
      for (int size = parameters.size(); i < size; i++)
      {
        ParameterInterface param = (ParameterInterface)parameters.get(i);
        
        param.setValue(null, this.batchParameters == null);
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet executeQuery(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("executeQuery", sql);
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void addBatch(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("addBatch", sql);
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("executeUpdate", sql);
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql)
    throws SQLException
  {
    try
    {
      debugCodeCall("execute", sql);
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setNull(int parameterIndex, int sqlType)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNull(" + parameterIndex + ", " + sqlType + ");");
      }
      setParameter(parameterIndex, ValueNull.INSTANCE);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setInt(int parameterIndex, int x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setInt(" + parameterIndex + ", " + x + ");");
      }
      setParameter(parameterIndex, ValueInt.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setString(int parameterIndex, String x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setString(" + parameterIndex + ", " + quote(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueString.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBigDecimal(int parameterIndex, BigDecimal x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBigDecimal(" + parameterIndex + ", " + quoteBigDecimal(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueDecimal.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setDate(int parameterIndex, Date x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setDate(" + parameterIndex + ", " + quoteDate(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueDate.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setTime(int parameterIndex, Time x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setTime(" + parameterIndex + ", " + quoteTime(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueTime.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setTimestamp(" + parameterIndex + ", " + quoteTimestamp(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueTimestamp.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setObject(int parameterIndex, Object x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setObject(" + parameterIndex + ", x);");
      }
      if (x == null) {
        setParameter(parameterIndex, ValueNull.INSTANCE);
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setObject(int parameterIndex, Object x, int targetSqlType)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setObject(" + parameterIndex + ", x, " + targetSqlType + ");");
      }
      int type = DataType.convertSQLTypeToValueType(targetSqlType);
      if (x == null) {
        setParameter(parameterIndex, ValueNull.INSTANCE);
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setObject(int parameterIndex, Object x, int targetSqlType, int scale)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setObject(" + parameterIndex + ", x, " + targetSqlType + ", " + scale + ");");
      }
      setObject(parameterIndex, x, targetSqlType);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBoolean(int parameterIndex, boolean x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBoolean(" + parameterIndex + ", " + x + ");");
      }
      setParameter(parameterIndex, ValueBoolean.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setByte(int parameterIndex, byte x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setByte(" + parameterIndex + ", " + x + ");");
      }
      setParameter(parameterIndex, ValueByte.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setShort(int parameterIndex, short x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setShort(" + parameterIndex + ", (short) " + x + ");");
      }
      setParameter(parameterIndex, ValueShort.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setLong(int parameterIndex, long x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setLong(" + parameterIndex + ", " + x + "L);");
      }
      setParameter(parameterIndex, ValueLong.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setFloat(int parameterIndex, float x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setFloat(" + parameterIndex + ", " + x + "f);");
      }
      setParameter(parameterIndex, ValueFloat.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setDouble(int parameterIndex, double x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setDouble(" + parameterIndex + ", " + x + "d);");
      }
      setParameter(parameterIndex, ValueDouble.get(x));
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setRef(int parameterIndex, Ref x)
    throws SQLException
  {
    throw unsupported("ref");
  }
  
  public void setDate(int parameterIndex, Date x, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setDate(" + parameterIndex + ", " + quoteDate(x) + ", calendar);");
      }
      if (x == null) {
        setParameter(parameterIndex, ValueNull.INSTANCE);
      } else {
        setParameter(parameterIndex, DateTimeUtils.convertDate(x, calendar));
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setTime(int parameterIndex, Time x, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setTime(" + parameterIndex + ", " + quoteTime(x) + ", calendar);");
      }
      if (x == null) {
        setParameter(parameterIndex, ValueNull.INSTANCE);
      } else {
        setParameter(parameterIndex, DateTimeUtils.convertTime(x, calendar));
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x, Calendar calendar)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setTimestamp(" + parameterIndex + ", " + quoteTimestamp(x) + ", calendar);");
      }
      if (x == null) {
        setParameter(parameterIndex, ValueNull.INSTANCE);
      } else {
        setParameter(parameterIndex, DateTimeUtils.convertTimestamp(x, calendar));
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /**
   * @deprecated
   */
  public void setUnicodeStream(int parameterIndex, InputStream x, int length)
    throws SQLException
  {
    throw unsupported("unicodeStream");
  }
  
  public void setNull(int parameterIndex, int sqlType, String typeName)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNull(" + parameterIndex + ", " + sqlType + ", " + quote(typeName) + ");");
      }
      setNull(parameterIndex, sqlType);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBlob(int parameterIndex, Blob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBlob(" + parameterIndex + ", x);");
      }
      checkClosedForWrite();
      try
      {
        Value v;
        Value v;
        if (x == null) {
          v = ValueNull.INSTANCE;
        } else {
          v = this.conn.createBlob(x.getBinaryStream(), -1L);
        }
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBlob(int parameterIndex, InputStream x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBlob(" + parameterIndex + ", x);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createBlob(x, -1L);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setClob(int parameterIndex, Clob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setClob(" + parameterIndex + ", x);");
      }
      checkClosedForWrite();
      try
      {
        Value v;
        Value v;
        if (x == null) {
          v = ValueNull.INSTANCE;
        } else {
          v = this.conn.createClob(x.getCharacterStream(), -1L);
        }
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setClob(int parameterIndex, Reader x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setClob(" + parameterIndex + ", x);");
      }
      checkClosedForWrite();
      try
      {
        Value v;
        Value v;
        if (x == null) {
          v = ValueNull.INSTANCE;
        } else {
          v = this.conn.createClob(x, -1L);
        }
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setArray(int parameterIndex, Array x)
    throws SQLException
  {
    throw unsupported("setArray");
  }
  
  public void setBytes(int parameterIndex, byte[] x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBytes(" + parameterIndex + ", " + quoteBytes(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueBytes.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBinaryStream(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createBlob(x, length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x, int length)
    throws SQLException
  {
    setBinaryStream(parameterIndex, x, length);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x)
    throws SQLException
  {
    setBinaryStream(parameterIndex, x, -1);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x, int length)
    throws SQLException
  {
    setAsciiStream(parameterIndex, x, length);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setAsciiStream(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createClob(IOUtils.getAsciiReader(x), length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x)
    throws SQLException
  {
    setAsciiStream(parameterIndex, x, -1);
  }
  
  public void setCharacterStream(int parameterIndex, Reader x, int length)
    throws SQLException
  {
    setCharacterStream(parameterIndex, x, length);
  }
  
  public void setCharacterStream(int parameterIndex, Reader x)
    throws SQLException
  {
    setCharacterStream(parameterIndex, x, -1);
  }
  
  public void setCharacterStream(int parameterIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setCharacterStream(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createClob(x, length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setURL(int parameterIndex, URL x)
    throws SQLException
  {
    throw unsupported("url");
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      debugCodeCall("getMetaData");
      checkClosed();
      ResultInterface result = this.command.getMetaData();
      if (result == null) {
        return null;
      }
      int id = getNextId(5);
      if (isDebugEnabled()) {
        debugCodeAssign("ResultSetMetaData", 5, id, "getMetaData()");
      }
      String catalog = this.conn.getCatalog();
      return new JdbcResultSetMetaData(null, this, result, catalog, this.session.getTrace(), id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      debugCodeCall("clearBatch");
      checkClosed();
      this.batchParameters = null;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.close();
      this.batchParameters = null;
      if (this.command != null)
      {
        this.command.close();
        this.command = null;
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  /* Error */
  public int[] executeBatch()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 596
    //   4: invokevirtual 146	org/h2/jdbc/JdbcPreparedStatement:debugCodeCall	(Ljava/lang/String;)V
    //   7: aload_0
    //   8: getfield 192	org/h2/jdbc/JdbcPreparedStatement:batchParameters	Ljava/util/ArrayList;
    //   11: ifnonnull +10 -> 21
    //   14: aload_0
    //   15: invokestatic 601	org/h2/util/New:arrayList	()Ljava/util/ArrayList;
    //   18: putfield 192	org/h2/jdbc/JdbcPreparedStatement:batchParameters	Ljava/util/ArrayList;
    //   21: aload_0
    //   22: getfield 192	org/h2/jdbc/JdbcPreparedStatement:batchParameters	Ljava/util/ArrayList;
    //   25: invokevirtual 184	java/util/ArrayList:size	()I
    //   28: istore_1
    //   29: iload_1
    //   30: newarray <illegal type>
    //   32: astore_2
    //   33: iconst_0
    //   34: istore_3
    //   35: aconst_null
    //   36: astore 4
    //   38: aload_0
    //   39: invokevirtual 149	org/h2/jdbc/JdbcPreparedStatement:checkClosedForWrite	()Z
    //   42: pop
    //   43: iconst_0
    //   44: istore 5
    //   46: iload 5
    //   48: iload_1
    //   49: if_icmpge +132 -> 181
    //   52: aload_0
    //   53: getfield 192	org/h2/jdbc/JdbcPreparedStatement:batchParameters	Ljava/util/ArrayList;
    //   56: iload 5
    //   58: invokevirtual 188	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   61: checkcast 605	[Lorg/h2/value/Value;
    //   64: astore 6
    //   66: aload_0
    //   67: getfield 50	org/h2/jdbc/JdbcPreparedStatement:command	Lorg/h2/command/CommandInterface;
    //   70: invokeinterface 179 1 0
    //   75: astore 7
    //   77: iconst_0
    //   78: istore 8
    //   80: iload 8
    //   82: aload 6
    //   84: arraylength
    //   85: if_icmpge +38 -> 123
    //   88: aload 6
    //   90: iload 8
    //   92: aaload
    //   93: astore 9
    //   95: aload 7
    //   97: iload 8
    //   99: invokevirtual 188	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   102: checkcast 190	org/h2/expression/ParameterInterface
    //   105: astore 10
    //   107: aload 10
    //   109: aload 9
    //   111: iconst_0
    //   112: invokeinterface 196 3 0
    //   117: iinc 8 1
    //   120: goto -40 -> 80
    //   123: aload_2
    //   124: iload 5
    //   126: aload_0
    //   127: invokespecial 152	org/h2/jdbc/JdbcPreparedStatement:executeUpdateInternal	()I
    //   130: iastore
    //   131: goto +44 -> 175
    //   134: astore 8
    //   136: aload_0
    //   137: aload 8
    //   139: invokevirtual 133	org/h2/jdbc/JdbcPreparedStatement:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   142: astore 9
    //   144: aload 4
    //   146: ifnonnull +10 -> 156
    //   149: aload 9
    //   151: astore 4
    //   153: goto +14 -> 167
    //   156: aload 9
    //   158: aload 4
    //   160: invokevirtual 609	java/sql/SQLException:setNextException	(Ljava/sql/SQLException;)V
    //   163: aload 9
    //   165: astore 4
    //   167: aload_2
    //   168: iload 5
    //   170: bipush -3
    //   172: iastore
    //   173: iconst_1
    //   174: istore_3
    //   175: iinc 5 1
    //   178: goto -132 -> 46
    //   181: aload_0
    //   182: aconst_null
    //   183: putfield 192	org/h2/jdbc/JdbcPreparedStatement:batchParameters	Ljava/util/ArrayList;
    //   186: iload_3
    //   187: ifeq +18 -> 205
    //   190: new 611	org/h2/jdbc/JdbcBatchUpdateException
    //   193: dup
    //   194: aload 4
    //   196: aload_2
    //   197: invokespecial 614	org/h2/jdbc/JdbcBatchUpdateException:<init>	(Ljava/sql/SQLException;[I)V
    //   200: astore 5
    //   202: aload 5
    //   204: athrow
    //   205: aload_2
    //   206: astore 5
    //   208: aload_0
    //   209: invokevirtual 155	org/h2/jdbc/JdbcPreparedStatement:afterWriting	()V
    //   212: aload 5
    //   214: areturn
    //   215: astore 11
    //   217: aload_0
    //   218: invokevirtual 155	org/h2/jdbc/JdbcPreparedStatement:afterWriting	()V
    //   221: aload 11
    //   223: athrow
    //   224: astore_1
    //   225: aload_0
    //   226: aload_1
    //   227: invokevirtual 133	org/h2/jdbc/JdbcPreparedStatement:logAndConvert	(Ljava/lang/Exception;)Ljava/sql/SQLException;
    //   230: athrow
    // Line number table:
    //   Java source line #1161	-> byte code offset #0
    //   Java source line #1162	-> byte code offset #7
    //   Java source line #1165	-> byte code offset #14
    //   Java source line #1167	-> byte code offset #21
    //   Java source line #1168	-> byte code offset #29
    //   Java source line #1169	-> byte code offset #33
    //   Java source line #1170	-> byte code offset #35
    //   Java source line #1171	-> byte code offset #38
    //   Java source line #1173	-> byte code offset #43
    //   Java source line #1174	-> byte code offset #52
    //   Java source line #1175	-> byte code offset #66
    //   Java source line #1177	-> byte code offset #77
    //   Java source line #1178	-> byte code offset #88
    //   Java source line #1179	-> byte code offset #95
    //   Java source line #1180	-> byte code offset #107
    //   Java source line #1177	-> byte code offset #117
    //   Java source line #1183	-> byte code offset #123
    //   Java source line #1194	-> byte code offset #131
    //   Java source line #1184	-> byte code offset #134
    //   Java source line #1185	-> byte code offset #136
    //   Java source line #1186	-> byte code offset #144
    //   Java source line #1187	-> byte code offset #149
    //   Java source line #1189	-> byte code offset #156
    //   Java source line #1190	-> byte code offset #163
    //   Java source line #1192	-> byte code offset #167
    //   Java source line #1193	-> byte code offset #173
    //   Java source line #1173	-> byte code offset #175
    //   Java source line #1196	-> byte code offset #181
    //   Java source line #1197	-> byte code offset #186
    //   Java source line #1198	-> byte code offset #190
    //   Java source line #1199	-> byte code offset #202
    //   Java source line #1201	-> byte code offset #205
    //   Java source line #1203	-> byte code offset #208
    //   Java source line #1205	-> byte code offset #224
    //   Java source line #1206	-> byte code offset #225
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	this	JdbcPreparedStatement
    //   28	21	1	size	int
    //   224	3	1	e	Exception
    //   32	174	2	result	int[]
    //   34	153	3	error	boolean
    //   36	159	4	next	SQLException
    //   44	132	5	i	int
    //   200	13	5	e	JdbcBatchUpdateException
    //   64	25	6	set	Value[]
    //   75	21	7	parameters	ArrayList<? extends ParameterInterface>
    //   78	40	8	j	int
    //   134	4	8	re	Exception
    //   93	17	9	value	Value
    //   142	22	9	e	SQLException
    //   105	3	10	param	ParameterInterface
    //   215	7	11	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   123	131	134	java/lang/Exception
    //   43	208	215	finally
    //   215	217	215	finally
    //   0	212	224	java/lang/Exception
    //   215	224	224	java/lang/Exception
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      debugCodeCall("addBatch");
      checkClosedForWrite();
      try
      {
        ArrayList<? extends ParameterInterface> parameters = this.command.getParameters();
        
        int size = parameters.size();
        Value[] set = new Value[size];
        for (int i = 0; i < size; i++)
        {
          ParameterInterface param = (ParameterInterface)parameters.get(i);
          Value value = param.getParamValue();
          set[i] = value;
        }
        if (this.batchParameters == null) {
          this.batchParameters = New.arrayList();
        }
        this.batchParameters.add(set);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql, int autoGeneratedKeys)
    throws SQLException
  {
    try
    {
      debugCode("executeUpdate(" + quote(sql) + ", " + autoGeneratedKeys + ");");
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql, int[] columnIndexes)
    throws SQLException
  {
    try
    {
      debugCode("executeUpdate(" + quote(sql) + ", " + quoteIntArray(columnIndexes) + ");");
      
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int executeUpdate(String sql, String[] columnNames)
    throws SQLException
  {
    try
    {
      debugCode("executeUpdate(" + quote(sql) + ", " + quoteArray(columnNames) + ");");
      
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql, int autoGeneratedKeys)
    throws SQLException
  {
    try
    {
      debugCode("execute(" + quote(sql) + ", " + autoGeneratedKeys + ");");
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql, int[] columnIndexes)
    throws SQLException
  {
    try
    {
      debugCode("execute(" + quote(sql) + ", " + quoteIntArray(columnIndexes) + ");");
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean execute(String sql, String[] columnNames)
    throws SQLException
  {
    try
    {
      debugCode("execute(" + quote(sql) + ", " + quoteArray(columnNames) + ");");
      throw DbException.get(90130);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      int id = getNextId(11);
      if (isDebugEnabled()) {
        debugCodeAssign("ParameterMetaData", 11, id, "getParameterMetaData()");
      }
      checkClosed();
      return new JdbcParameterMetaData(this.session.getTrace(), this, this.command, id);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private void setParameter(int parameterIndex, Value value)
  {
    checkClosed();
    parameterIndex--;
    ArrayList<? extends ParameterInterface> parameters = this.command.getParameters();
    if ((parameterIndex < 0) || (parameterIndex >= parameters.size())) {
      throw DbException.getInvalidValueException("parameterIndex", Integer.valueOf(parameterIndex + 1));
    }
    ParameterInterface param = (ParameterInterface)parameters.get(parameterIndex);
    
    param.setValue(value, this.batchParameters == null);
  }
  
  public void setRowId(int parameterIndex, RowId x)
    throws SQLException
  {
    throw unsupported("rowId");
  }
  
  public void setNString(int parameterIndex, String x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNString(" + parameterIndex + ", " + quote(x) + ");");
      }
      Value v = x == null ? ValueNull.INSTANCE : ValueString.get(x);
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setNCharacterStream(int parameterIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNCharacterStream(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createClob(x, length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setNCharacterStream(int parameterIndex, Reader x)
    throws SQLException
  {
    setNCharacterStream(parameterIndex, x, -1L);
  }
  
  public void setNClob(int parameterIndex, NClob x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNClob(" + parameterIndex + ", x);");
      }
      checkClosedForWrite();
      Value v;
      Value v;
      if (x == null) {
        v = ValueNull.INSTANCE;
      } else {
        v = this.conn.createClob(x.getCharacterStream(), -1L);
      }
      setParameter(parameterIndex, v);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setNClob(int parameterIndex, Reader x)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNClob(" + parameterIndex + ", x);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createClob(x, -1L);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setClob(int parameterIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setClob(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createClob(x, length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setBlob(int parameterIndex, InputStream x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBlob(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createBlob(x, length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setNClob(int parameterIndex, Reader x, long length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setNClob(" + parameterIndex + ", x, " + length + "L);");
      }
      checkClosedForWrite();
      try
      {
        Value v = this.conn.createClob(x, length);
        setParameter(parameterIndex, v);
      }
      finally
      {
        afterWriting();
      }
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void setSQLXML(int parameterIndex, SQLXML x)
    throws SQLException
  {
    throw unsupported("SQLXML");
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": " + this.command;
  }
  
  protected boolean checkClosed(boolean write)
  {
    if (super.checkClosed(write))
    {
      ArrayList<? extends ParameterInterface> oldParams = this.command.getParameters();
      this.command = this.conn.prepareCommand(this.sqlStatement, this.fetchSize);
      ArrayList<? extends ParameterInterface> newParams = this.command.getParameters();
      int i = 0;
      for (int size = oldParams.size(); i < size; i++)
      {
        ParameterInterface old = (ParameterInterface)oldParams.get(i);
        Value value = old.getParamValue();
        if (value != null)
        {
          ParameterInterface n = (ParameterInterface)newParams.get(i);
          n.setValue(value, false);
        }
      }
      return true;
    }
    return false;
  }
}
