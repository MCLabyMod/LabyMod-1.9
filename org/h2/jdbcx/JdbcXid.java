package org.h2.jdbcx;

import java.util.StringTokenizer;
import javax.transaction.xa.Xid;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.util.StringUtils;

public class JdbcXid
  extends TraceObject
  implements Xid
{
  private static final String PREFIX = "XID";
  private final int formatId;
  private final byte[] branchQualifier;
  private final byte[] globalTransactionId;
  
  JdbcXid(JdbcDataSourceFactory factory, int id, String tid)
  {
    setTrace(factory.getTrace(), 15, id);
    try
    {
      StringTokenizer tokenizer = new StringTokenizer(tid, "_");
      String prefix = tokenizer.nextToken();
      if (!"XID".equals(prefix)) {
        throw DbException.get(90101, tid);
      }
      this.formatId = Integer.parseInt(tokenizer.nextToken());
      this.branchQualifier = StringUtils.convertHexToBytes(tokenizer.nextToken());
      this.globalTransactionId = StringUtils.convertHexToBytes(tokenizer.nextToken());
    }
    catch (RuntimeException e)
    {
      throw DbException.get(90101, tid);
    }
  }
  
  public static String toString(Xid xid)
  {
    StringBuilder buff = new StringBuilder("XID");
    buff.append('_').append(xid.getFormatId()).append('_').append(StringUtils.convertBytesToHex(xid.getBranchQualifier())).append('_').append(StringUtils.convertBytesToHex(xid.getGlobalTransactionId()));
    
    return buff.toString();
  }
  
  public int getFormatId()
  {
    debugCodeCall("getFormatId");
    return this.formatId;
  }
  
  public byte[] getBranchQualifier()
  {
    debugCodeCall("getBranchQualifier");
    return this.branchQualifier;
  }
  
  public byte[] getGlobalTransactionId()
  {
    debugCodeCall("getGlobalTransactionId");
    return this.globalTransactionId;
  }
}
