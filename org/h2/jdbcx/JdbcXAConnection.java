package org.h2.jdbcx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.StatementEventListener;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.h2.Driver;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.util.JdbcUtils;
import org.h2.util.New;

public class JdbcXAConnection
  extends TraceObject
  implements XAConnection, XAResource
{
  private final JdbcDataSourceFactory factory;
  private JdbcConnection physicalConn;
  private volatile Connection handleConn;
  private final ArrayList<ConnectionEventListener> listeners = New.arrayList();
  private Xid currentTransaction;
  private boolean prepared;
  
  static
  {
    Driver.load();
  }
  
  JdbcXAConnection(JdbcDataSourceFactory factory, int id, JdbcConnection physicalConn)
  {
    this.factory = factory;
    setTrace(factory.getTrace(), 13, id);
    this.physicalConn = physicalConn;
  }
  
  public XAResource getXAResource()
  {
    debugCodeCall("getXAResource");
    return this;
  }
  
  public void close()
    throws SQLException
  {
    debugCodeCall("close");
    Connection lastHandle = this.handleConn;
    if (lastHandle != null)
    {
      this.listeners.clear();
      lastHandle.close();
    }
    if (this.physicalConn != null) {
      try
      {
        this.physicalConn.close();
      }
      finally
      {
        this.physicalConn = null;
      }
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    debugCodeCall("getConnection");
    Connection lastHandle = this.handleConn;
    if (lastHandle != null) {
      lastHandle.close();
    }
    this.physicalConn.rollback();
    this.handleConn = new PooledJdbcConnection(this.physicalConn);
    return this.handleConn;
  }
  
  public void addConnectionEventListener(ConnectionEventListener listener)
  {
    debugCode("addConnectionEventListener(listener);");
    this.listeners.add(listener);
  }
  
  public void removeConnectionEventListener(ConnectionEventListener listener)
  {
    debugCode("removeConnectionEventListener(listener);");
    this.listeners.remove(listener);
  }
  
  void closedHandle()
  {
    debugCode("closedHandle();");
    ConnectionEvent event = new ConnectionEvent(this);
    for (int i = this.listeners.size() - 1; i >= 0; i--)
    {
      ConnectionEventListener listener = (ConnectionEventListener)this.listeners.get(i);
      listener.connectionClosed(event);
    }
    this.handleConn = null;
  }
  
  public int getTransactionTimeout()
  {
    debugCodeCall("getTransactionTimeout");
    return 0;
  }
  
  public boolean setTransactionTimeout(int seconds)
  {
    debugCodeCall("setTransactionTimeout", seconds);
    return false;
  }
  
  public boolean isSameRM(XAResource xares)
  {
    debugCode("isSameRM(xares);");
    return xares == this;
  }
  
  public Xid[] recover(int flag)
    throws XAException
  {
    debugCodeCall("recover", quoteFlags(flag));
    checkOpen();
    Statement stat = null;
    try
    {
      stat = this.physicalConn.createStatement();
      ResultSet rs = stat.executeQuery("SELECT * FROM INFORMATION_SCHEMA.IN_DOUBT ORDER BY TRANSACTION");
      
      ArrayList<Xid> list = New.arrayList();
      int id;
      while (rs.next())
      {
        String tid = rs.getString("TRANSACTION");
        id = getNextId(15);
        Xid xid = new JdbcXid(this.factory, id, tid);
        list.add(xid);
      }
      rs.close();
      Xid[] result = new Xid[list.size()];
      list.toArray(result);
      if (list.size() > 0) {
        this.prepared = true;
      }
      return result;
    }
    catch (SQLException e)
    {
      XAException xa = new XAException(-3);
      xa.initCause(e);
      throw xa;
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
    }
  }
  
  public int prepare(Xid xid)
    throws XAException
  {
    if (isDebugEnabled()) {
      debugCode("prepare(" + JdbcXid.toString(xid) + ");");
    }
    checkOpen();
    if (!this.currentTransaction.equals(xid)) {
      throw new XAException(-5);
    }
    Statement stat = null;
    try
    {
      stat = this.physicalConn.createStatement();
      stat.execute("PREPARE COMMIT " + JdbcXid.toString(xid));
      this.prepared = true;
    }
    catch (SQLException e)
    {
      throw convertException(e);
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
    }
    return 0;
  }
  
  public void forget(Xid xid)
  {
    if (isDebugEnabled()) {
      debugCode("forget(" + JdbcXid.toString(xid) + ");");
    }
    this.prepared = false;
  }
  
  public void rollback(Xid xid)
    throws XAException
  {
    if (isDebugEnabled()) {
      debugCode("rollback(" + JdbcXid.toString(xid) + ");");
    }
    try
    {
      this.physicalConn.rollback();
      this.physicalConn.setAutoCommit(true);
      if (this.prepared)
      {
        Statement stat = null;
        try
        {
          stat = this.physicalConn.createStatement();
          stat.execute("ROLLBACK TRANSACTION " + JdbcXid.toString(xid));
        }
        finally
        {
          JdbcUtils.closeSilently(stat);
        }
        this.prepared = false;
      }
    }
    catch (SQLException e)
    {
      throw convertException(e);
    }
    this.currentTransaction = null;
  }
  
  public void end(Xid xid, int flags)
    throws XAException
  {
    if (isDebugEnabled()) {
      debugCode("end(" + JdbcXid.toString(xid) + ", " + quoteFlags(flags) + ");");
    }
    if (flags == 33554432) {
      return;
    }
    if (!this.currentTransaction.equals(xid)) {
      throw new XAException(-9);
    }
    this.prepared = false;
  }
  
  public void start(Xid xid, int flags)
    throws XAException
  {
    if (isDebugEnabled()) {
      debugCode("start(" + JdbcXid.toString(xid) + ", " + quoteFlags(flags) + ");");
    }
    if (flags == 134217728) {
      return;
    }
    if (flags == 2097152)
    {
      if ((this.currentTransaction != null) && (!this.currentTransaction.equals(xid))) {
        throw new XAException(-3);
      }
    }
    else if (this.currentTransaction != null) {
      throw new XAException(-4);
    }
    try
    {
      this.physicalConn.setAutoCommit(false);
    }
    catch (SQLException e)
    {
      throw convertException(e);
    }
    this.currentTransaction = xid;
    this.prepared = false;
  }
  
  public void commit(Xid xid, boolean onePhase)
    throws XAException
  {
    if (isDebugEnabled()) {
      debugCode("commit(" + JdbcXid.toString(xid) + ", " + onePhase + ");");
    }
    Statement stat = null;
    try
    {
      if (onePhase)
      {
        this.physicalConn.commit();
      }
      else
      {
        stat = this.physicalConn.createStatement();
        stat.execute("COMMIT TRANSACTION " + JdbcXid.toString(xid));
        this.prepared = false;
      }
      this.physicalConn.setAutoCommit(true);
    }
    catch (SQLException e)
    {
      throw convertException(e);
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
    }
    this.currentTransaction = null;
  }
  
  public void addStatementEventListener(StatementEventListener listener)
  {
    throw new UnsupportedOperationException();
  }
  
  public void removeStatementEventListener(StatementEventListener listener)
  {
    throw new UnsupportedOperationException();
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": " + this.physicalConn;
  }
  
  private static XAException convertException(SQLException e)
  {
    XAException xa = new XAException(e.getMessage());
    xa.initCause(e);
    return xa;
  }
  
  private static String quoteFlags(int flags)
  {
    StringBuilder buff = new StringBuilder();
    if ((flags & 0x800000) != 0) {
      buff.append("|XAResource.TMENDRSCAN");
    }
    if ((flags & 0x20000000) != 0) {
      buff.append("|XAResource.TMFAIL");
    }
    if ((flags & 0x200000) != 0) {
      buff.append("|XAResource.TMJOIN");
    }
    if ((flags & 0x40000000) != 0) {
      buff.append("|XAResource.TMONEPHASE");
    }
    if ((flags & 0x8000000) != 0) {
      buff.append("|XAResource.TMRESUME");
    }
    if ((flags & 0x1000000) != 0) {
      buff.append("|XAResource.TMSTARTRSCAN");
    }
    if ((flags & 0x4000000) != 0) {
      buff.append("|XAResource.TMSUCCESS");
    }
    if ((flags & 0x2000000) != 0) {
      buff.append("|XAResource.TMSUSPEND");
    }
    if ((flags & 0x3) != 0) {
      buff.append("|XAResource.XA_RDONLY");
    }
    if (buff.length() == 0) {
      buff.append("|XAResource.TMNOFLAGS");
    }
    return buff.toString().substring(1);
  }
  
  private void checkOpen()
    throws XAException
  {
    if (this.physicalConn == null) {
      throw new XAException(-3);
    }
  }
  
  class PooledJdbcConnection
    extends JdbcConnection
  {
    private boolean isClosed;
    
    public PooledJdbcConnection(JdbcConnection conn)
    {
      super();
    }
    
    public synchronized void close()
      throws SQLException
    {
      if (!this.isClosed)
      {
        try
        {
          rollback();
          setAutoCommit(true);
        }
        catch (SQLException e) {}
        JdbcXAConnection.this.closedHandle();
        this.isClosed = true;
      }
    }
    
    public synchronized boolean isClosed()
      throws SQLException
    {
      return (this.isClosed) || (super.isClosed());
    }
    
    protected synchronized void checkClosed(boolean write)
    {
      if (this.isClosed) {
        throw DbException.get(90007);
      }
      super.checkClosed(write);
    }
  }
}
