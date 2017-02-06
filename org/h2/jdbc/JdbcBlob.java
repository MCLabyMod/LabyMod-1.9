package org.h2.jdbc;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import org.h2.engine.SessionInterface;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.util.IOUtils;
import org.h2.util.Task;
import org.h2.value.Value;

public class JdbcBlob
  extends TraceObject
  implements Blob
{
  Value value;
  private final JdbcConnection conn;
  
  public JdbcBlob(JdbcConnection conn, Value value, int id)
  {
    setTrace(conn.getSession().getTrace(), 9, id);
    this.conn = conn;
    this.value = value;
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      debugCodeCall("length");
      checkClosed();
      if (this.value.getType() == 15)
      {
        long precision = this.value.getPrecision();
        if (precision > 0L) {
          return precision;
        }
      }
      return IOUtils.copyAndCloseInput(this.value.getInputStream(), null);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public void truncate(long len)
    throws SQLException
  {
    throw unsupported("LOB update");
  }
  
  public byte[] getBytes(long pos, int length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getBytes(" + pos + ", " + length + ");");
      }
      checkClosed();
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      InputStream in = this.value.getInputStream();
      try
      {
        IOUtils.skipFully(in, pos - 1L);
        IOUtils.copy(in, out, length);
      }
      finally
      {
        in.close();
      }
      return out.toByteArray();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int setBytes(long pos, byte[] bytes)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBytes(" + pos + ", " + quoteBytes(bytes) + ");");
      }
      checkClosed();
      if (pos != 1L) {
        throw DbException.getInvalidValueException("pos", Long.valueOf(pos));
      }
      this.value = this.conn.createBlob(new ByteArrayInputStream(bytes), -1L);
      return bytes.length;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int setBytes(long pos, byte[] bytes, int offset, int len)
    throws SQLException
  {
    throw unsupported("LOB update");
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      debugCodeCall("getBinaryStream");
      checkClosed();
      return this.value.getInputStream();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public OutputStream setBinaryStream(long pos)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setBinaryStream(" + pos + ");");
      }
      checkClosed();
      if (pos != 1L) {
        throw DbException.getInvalidValueException("pos", Long.valueOf(pos));
      }
      if (this.value.getPrecision() != 0L) {
        throw DbException.getInvalidValueException("length", Long.valueOf(this.value.getPrecision()));
      }
      final JdbcConnection c = this.conn;
      final PipedInputStream in = new PipedInputStream();
      final Task task = new Task()
      {
        public void call()
        {
          JdbcBlob.this.value = c.createBlob(in, -1L);
        }
      };
      PipedOutputStream out = new PipedOutputStream(in)
      {
        public void close()
          throws IOException
        {
          super.close();
          try
          {
            task.get();
          }
          catch (Exception e)
          {
            throw DbException.convertToIOException(e);
          }
        }
      };
      task.execute();
      return new BufferedOutputStream(out);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public long position(byte[] pattern, long start)
    throws SQLException
  {
    if (isDebugEnabled()) {
      debugCode("position(" + quoteBytes(pattern) + ", " + start + ");");
    }
    throw unsupported("LOB search");
  }
  
  public long position(Blob blobPattern, long start)
    throws SQLException
  {
    if (isDebugEnabled()) {
      debugCode("position(blobPattern, " + start + ");");
    }
    throw unsupported("LOB subset");
  }
  
  public void free()
  {
    debugCodeCall("free");
    this.value = null;
  }
  
  public InputStream getBinaryStream(long pos, long length)
    throws SQLException
  {
    throw unsupported("LOB update");
  }
  
  private void checkClosed()
  {
    this.conn.checkClosed();
    if (this.value == null) {
      throw DbException.get(90007);
    }
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": " + (this.value == null ? "null" : this.value.getTraceSQL());
  }
}
