package org.h2.message;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import org.h2.util.StringUtils;

public class TraceObject
{
  protected static final int CALLABLE_STATEMENT = 0;
  protected static final int CONNECTION = 1;
  protected static final int DATABASE_META_DATA = 2;
  protected static final int PREPARED_STATEMENT = 3;
  protected static final int RESULT_SET = 4;
  protected static final int RESULT_SET_META_DATA = 5;
  protected static final int SAVEPOINT = 6;
  protected static final int STATEMENT = 8;
  protected static final int BLOB = 9;
  protected static final int CLOB = 10;
  protected static final int PARAMETER_META_DATA = 11;
  protected static final int DATA_SOURCE = 12;
  protected static final int XA_DATA_SOURCE = 13;
  protected static final int XID = 15;
  protected static final int ARRAY = 16;
  private static final int LAST = 17;
  private static final int[] ID = new int[17];
  private static final String[] PREFIX = { "call", "conn", "dbMeta", "prep", "rs", "rsMeta", "sp", "ex", "stat", "blob", "clob", "pMeta", "ds", "xads", "xares", "xid", "ar" };
  protected Trace trace;
  private int traceType;
  private int id;
  
  protected void setTrace(Trace trace, int type, int id)
  {
    this.trace = trace;
    this.traceType = type;
    this.id = id;
  }
  
  public int getTraceId()
  {
    return this.id;
  }
  
  public String getTraceObjectName()
  {
    return PREFIX[this.traceType] + this.id;
  }
  
  protected static int getNextId(int type)
  {
    int tmp4_3 = type; int[] tmp4_0 = ID; int tmp6_5 = tmp4_0[tmp4_3];tmp4_0[tmp4_3] = (tmp6_5 + 1);return tmp6_5;
  }
  
  protected boolean isDebugEnabled()
  {
    return this.trace.isDebugEnabled();
  }
  
  protected boolean isInfoEnabled()
  {
    return this.trace.isInfoEnabled();
  }
  
  protected void debugCodeAssign(String className, int newType, int newId, String value)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debugCode(className + " " + PREFIX[newType] + newId + " = " + getTraceObjectName() + "." + value + ";");
    }
  }
  
  protected void debugCodeCall(String methodName)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debugCode(getTraceObjectName() + "." + methodName + "();");
    }
  }
  
  protected void debugCodeCall(String methodName, long param)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debugCode(getTraceObjectName() + "." + methodName + "(" + param + ");");
    }
  }
  
  protected void debugCodeCall(String methodName, String param)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debugCode(getTraceObjectName() + "." + methodName + "(" + quote(param) + ");");
    }
  }
  
  protected void debugCode(String text)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debugCode(getTraceObjectName() + "." + text);
    }
  }
  
  protected static String quote(String s)
  {
    return StringUtils.quoteJavaString(s);
  }
  
  protected static String quoteTime(Time x)
  {
    if (x == null) {
      return "null";
    }
    return "Time.valueOf(\"" + x.toString() + "\")";
  }
  
  protected static String quoteTimestamp(Timestamp x)
  {
    if (x == null) {
      return "null";
    }
    return "Timestamp.valueOf(\"" + x.toString() + "\")";
  }
  
  protected static String quoteDate(Date x)
  {
    if (x == null) {
      return "null";
    }
    return "Date.valueOf(\"" + x.toString() + "\")";
  }
  
  protected static String quoteBigDecimal(BigDecimal x)
  {
    if (x == null) {
      return "null";
    }
    return "new BigDecimal(\"" + x.toString() + "\")";
  }
  
  protected static String quoteBytes(byte[] x)
  {
    if (x == null) {
      return "null";
    }
    return "org.h2.util.StringUtils.convertHexToBytes(\"" + StringUtils.convertBytesToHex(x) + "\")";
  }
  
  protected static String quoteArray(String[] s)
  {
    return StringUtils.quoteJavaStringArray(s);
  }
  
  protected static String quoteIntArray(int[] s)
  {
    return StringUtils.quoteJavaIntArray(s);
  }
  
  protected static String quoteMap(Map<String, Class<?>> map)
  {
    if (map == null) {
      return "null";
    }
    if (map.size() == 0) {
      return "new Map()";
    }
    return "new Map() /* " + map.toString() + " */";
  }
  
  protected SQLException logAndConvert(Exception ex)
  {
    SQLException e = DbException.toSQLException(ex);
    if (this.trace == null)
    {
      DbException.traceThrowable(e);
    }
    else
    {
      int errorCode = e.getErrorCode();
      if ((errorCode >= 23000) && (errorCode < 24000)) {
        this.trace.info(e, "exception");
      } else {
        this.trace.error(e, "exception");
      }
    }
    return e;
  }
  
  protected SQLException unsupported(String message)
    throws SQLException
  {
    try
    {
      throw DbException.getUnsupportedException(message);
    }
    catch (Exception e)
    {
      return logAndConvert(e);
    }
  }
}
