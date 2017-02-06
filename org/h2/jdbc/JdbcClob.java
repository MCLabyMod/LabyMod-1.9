package org.h2.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import org.h2.engine.SessionInterface;
import org.h2.message.DbException;
import org.h2.message.TraceObject;
import org.h2.util.IOUtils;
import org.h2.util.Task;
import org.h2.value.Value;

public class JdbcClob
  extends TraceObject
  implements NClob
{
  Value value;
  private final JdbcConnection conn;
  
  public JdbcClob(JdbcConnection conn, Value value, int id)
  {
    setTrace(conn.getSession().getTrace(), 10, id);
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
      if (this.value.getType() == 16)
      {
        long precision = this.value.getPrecision();
        if (precision > 0L) {
          return precision;
        }
      }
      return IOUtils.copyAndCloseInput(this.value.getReader(), null, Long.MAX_VALUE);
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
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      debugCodeCall("getAsciiStream");
      checkClosed();
      String s = this.value.getString();
      return IOUtils.getInputStreamFromString(s);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public OutputStream setAsciiStream(long pos)
    throws SQLException
  {
    throw unsupported("LOB update");
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      debugCodeCall("getCharacterStream");
      checkClosed();
      return this.value.getReader();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public Writer setCharacterStream(long pos)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCodeCall("setCharacterStream(" + pos + ");");
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
          JdbcClob.this.value = c.createClob(IOUtils.getReader(in), -1L);
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
      return IOUtils.getBufferedWriter(out);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getSubString(long pos, int length)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getSubString(" + pos + ", " + length + ");");
      }
      checkClosed();
      if (pos < 1L) {
        throw DbException.getInvalidValueException("pos", Long.valueOf(pos));
      }
      if (length < 0) {
        throw DbException.getInvalidValueException("length", Integer.valueOf(length));
      }
      StringWriter writer = new StringWriter(Math.min(4096, length));
      
      Reader reader = this.value.getReader();
      try
      {
        IOUtils.skipFully(reader, pos - 1L);
        IOUtils.copyAndCloseInput(reader, writer, length);
      }
      finally
      {
        reader.close();
      }
      return writer.toString();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int setString(long pos, String str)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("setString(" + pos + ", " + quote(str) + ");");
      }
      checkClosed();
      if (pos != 1L) {
        throw DbException.getInvalidValueException("pos", Long.valueOf(pos));
      }
      if (str == null) {
        throw DbException.getInvalidValueException("str", str);
      }
      this.value = this.conn.createClob(new StringReader(str), -1L);
      return str.length();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int setString(long pos, String str, int offset, int len)
    throws SQLException
  {
    throw unsupported("LOB update");
  }
  
  public long position(String pattern, long start)
    throws SQLException
  {
    throw unsupported("LOB search");
  }
  
  public long position(Clob clobPattern, long start)
    throws SQLException
  {
    throw unsupported("LOB search");
  }
  
  public void free()
  {
    debugCodeCall("free");
    this.value = null;
  }
  
  public Reader getCharacterStream(long pos, long length)
    throws SQLException
  {
    throw unsupported("LOB subset");
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
