package org.h2.value;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.store.DataHandler;
import org.h2.store.FileStore;
import org.h2.store.FileStoreInputStream;
import org.h2.store.FileStoreOutputStream;
import org.h2.store.LobStorageInterface;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class ValueLobDb
  extends Value
  implements Value.ValueClob, Value.ValueBlob
{
  private final int type;
  private final long lobId;
  private final byte[] hmac;
  private final byte[] small;
  private final DataHandler handler;
  private final long precision;
  private final String fileName;
  private final FileStore tempFile;
  private int tableId;
  private int hash;
  
  private ValueLobDb(int type, DataHandler handler, int tableId, long lobId, byte[] hmac, long precision)
  {
    this.type = type;
    this.handler = handler;
    this.tableId = tableId;
    this.lobId = lobId;
    this.hmac = hmac;
    this.precision = precision;
    this.small = null;
    this.fileName = null;
    this.tempFile = null;
  }
  
  private ValueLobDb(int type, byte[] small, long precision)
  {
    this.type = type;
    this.small = small;
    this.precision = precision;
    this.lobId = 0L;
    this.hmac = null;
    this.handler = null;
    this.fileName = null;
    this.tempFile = null;
  }
  
  private ValueLobDb(DataHandler handler, Reader in, long remaining)
    throws IOException
  {
    this.type = 16;
    this.handler = handler;
    this.small = null;
    this.lobId = 0L;
    this.hmac = null;
    this.fileName = createTempLobFileName(handler);
    this.tempFile = this.handler.openFile(this.fileName, "rw", false);
    this.tempFile.autoDelete();
    FileStoreOutputStream out = new FileStoreOutputStream(this.tempFile, null, null);
    long tmpPrecision = 0L;
    try
    {
      char[] buff = new char['က'];
      for (;;)
      {
        int len = getBufferSize(this.handler, false, remaining);
        len = IOUtils.readFully(in, buff, len);
        if (len == 0) {
          break;
        }
      }
    }
    finally
    {
      out.close();
    }
    this.precision = tmpPrecision;
  }
  
  private ValueLobDb(DataHandler handler, byte[] buff, int len, InputStream in, long remaining)
    throws IOException
  {
    this.type = 15;
    this.handler = handler;
    this.small = null;
    this.lobId = 0L;
    this.hmac = null;
    this.fileName = createTempLobFileName(handler);
    this.tempFile = this.handler.openFile(this.fileName, "rw", false);
    this.tempFile.autoDelete();
    FileStoreOutputStream out = new FileStoreOutputStream(this.tempFile, null, null);
    long tmpPrecision = 0L;
    boolean compress = this.handler.getLobCompressionAlgorithm(15) != null;
    try
    {
      for (;;)
      {
        tmpPrecision += len;
        out.write(buff, 0, len);
        remaining -= len;
        if (remaining > 0L)
        {
          len = getBufferSize(this.handler, compress, remaining);
          len = IOUtils.readFully(in, buff, len);
          if (len <= 0) {
            break;
          }
        }
      }
    }
    finally
    {
      out.close();
    }
    this.precision = tmpPrecision;
  }
  
  private static String createTempLobFileName(DataHandler handler)
    throws IOException
  {
    String path = handler.getDatabasePath();
    if (path.length() == 0) {
      path = SysProperties.PREFIX_TEMP_FILE;
    }
    return FileUtils.createTempFile(path, ".temp.db", true, true);
  }
  
  public static ValueLobDb create(int type, DataHandler handler, int tableId, long id, byte[] hmac, long precision)
  {
    return new ValueLobDb(type, handler, tableId, id, hmac, precision);
  }
  
  public Value convertTo(int t)
  {
    if (t == this.type) {
      return this;
    }
    if (t == 16)
    {
      if (this.handler != null)
      {
        Value copy = this.handler.getLobStorage().createClob(getReader(), -1L);
        
        return copy;
      }
      if (this.small != null) {
        return createSmallLob(t, this.small);
      }
    }
    else if (t == 15)
    {
      if (this.handler != null)
      {
        Value copy = this.handler.getLobStorage().createBlob(getInputStream(), -1L);
        
        return copy;
      }
      if (this.small != null) {
        return createSmallLob(t, this.small);
      }
    }
    return super.convertTo(t);
  }
  
  public boolean isLinked()
  {
    return (this.tableId != -1) && (this.tableId != -3) && (this.small == null);
  }
  
  public boolean isStored()
  {
    return (this.small == null) && (this.fileName == null);
  }
  
  public void close()
  {
    if (this.fileName != null)
    {
      if (this.tempFile != null) {
        this.tempFile.stopAutoDelete();
      }
      synchronized (this.handler.getLobSyncObject())
      {
        FileUtils.delete(this.fileName);
      }
    }
    if (this.handler != null) {
      this.handler.getLobStorage().removeLob(this);
    }
  }
  
  public void unlink(DataHandler database)
  {
    if ((this.small == null) && (this.tableId != -1))
    {
      database.getLobStorage().setTable(this, -1);
      
      this.tableId = -1;
    }
  }
  
  public Value link(DataHandler database, int tabId)
  {
    if (this.small == null)
    {
      if (this.tableId == -2)
      {
        database.getLobStorage().setTable(this, tabId);
        this.tableId = tabId;
      }
      else
      {
        return this.handler.getLobStorage().copyLob(this, tabId, getPrecision());
      }
    }
    else if (this.small.length > database.getMaxLengthInplaceLob())
    {
      LobStorageInterface s = database.getLobStorage();
      Value v;
      Value v;
      if (this.type == 15) {
        v = s.createBlob(getInputStream(), getPrecision());
      } else {
        v = s.createClob(getReader(), getPrecision());
      }
      return v.link(database, tabId);
    }
    return this;
  }
  
  public int getTableId()
  {
    return this.tableId;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public long getPrecision()
  {
    return this.precision;
  }
  
  public String getString()
  {
    int len = (this.precision > 2147483647L) || (this.precision == 0L) ? Integer.MAX_VALUE : (int)this.precision;
    try
    {
      if (this.type == 16)
      {
        if (this.small != null) {
          return new String(this.small, Constants.UTF8);
        }
        return IOUtils.readStringAndClose(getReader(), len);
      }
      byte[] buff;
      byte[] buff;
      if (this.small != null) {
        buff = this.small;
      } else {
        buff = IOUtils.readBytesAndClose(getInputStream(), len);
      }
      return StringUtils.convertBytesToHex(buff);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, toString());
    }
  }
  
  public byte[] getBytes()
  {
    if (this.type == 16) {
      return super.getBytes();
    }
    byte[] data = getBytesNoCopy();
    return Utils.cloneByteArray(data);
  }
  
  public byte[] getBytesNoCopy()
  {
    if (this.type == 16) {
      return super.getBytesNoCopy();
    }
    if (this.small != null) {
      return this.small;
    }
    try
    {
      return IOUtils.readBytesAndClose(getInputStream(), Integer.MAX_VALUE);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, toString());
    }
  }
  
  public int hashCode()
  {
    if (this.hash == 0)
    {
      if (this.precision > 4096L) {
        return (int)(this.precision ^ this.precision >>> 32);
      }
      if (this.type == 16) {
        this.hash = getString().hashCode();
      } else {
        this.hash = Utils.getByteArrayHash(getBytes());
      }
    }
    return this.hash;
  }
  
  protected int compareSecure(Value v, CompareMode mode)
  {
    if ((v instanceof ValueLobDb))
    {
      ValueLobDb v2 = (ValueLobDb)v;
      if (v == this) {
        return 0;
      }
      if ((this.lobId == v2.lobId) && (this.small == null) && (v2.small == null)) {
        return 0;
      }
    }
    if (this.type == 16) {
      return Integer.signum(getString().compareTo(v.getString()));
    }
    byte[] v2 = v.getBytesNoCopy();
    return Utils.compareNotNullSigned(getBytes(), v2);
  }
  
  public Object getObject()
  {
    if (this.type == 16) {
      return getReader();
    }
    return getInputStream();
  }
  
  public Reader getReader()
  {
    return IOUtils.getBufferedReader(getInputStream());
  }
  
  public InputStream getInputStream()
  {
    if (this.small != null) {
      return new ByteArrayInputStream(this.small);
    }
    if (this.fileName != null)
    {
      FileStore store = this.handler.openFile(this.fileName, "r", true);
      boolean alwaysClose = SysProperties.lobCloseBetweenReads;
      return new BufferedInputStream(new FileStoreInputStream(store, this.handler, false, alwaysClose), 4096);
    }
    long byteCount = this.type == 15 ? this.precision : -1L;
    try
    {
      return this.handler.getLobStorage().getInputStream(this, this.hmac, byteCount);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, toString());
    }
  }
  
  public void set(PreparedStatement prep, int parameterIndex)
    throws SQLException
  {
    long p = getPrecision();
    if ((p > 2147483647L) || (p <= 0L)) {
      p = -1L;
    }
    if (this.type == 15) {
      prep.setBinaryStream(parameterIndex, getInputStream(), (int)p);
    } else {
      prep.setCharacterStream(parameterIndex, getReader(), (int)p);
    }
  }
  
  public String getSQL()
  {
    if (this.type == 16)
    {
      String s = getString();
      return StringUtils.quoteStringSQL(s);
    }
    byte[] buff = getBytes();
    String s = StringUtils.convertBytesToHex(buff);
    return "X'" + s + "'";
  }
  
  public String getTraceSQL()
  {
    if ((this.small != null) && (getPrecision() <= SysProperties.MAX_TRACE_DATA_LENGTH)) {
      return getSQL();
    }
    StringBuilder buff = new StringBuilder();
    if (this.type == 16) {
      buff.append("SPACE(").append(getPrecision());
    } else {
      buff.append("CAST(REPEAT('00', ").append(getPrecision()).append(") AS BINARY");
    }
    buff.append(" /* table: ").append(this.tableId).append(" id: ").append(this.lobId).append(" */)");
    
    return buff.toString();
  }
  
  public byte[] getSmall()
  {
    return this.small;
  }
  
  public int getDisplaySize()
  {
    return MathUtils.convertLongToInt(getPrecision());
  }
  
  public boolean equals(Object other)
  {
    return ((other instanceof ValueLobDb)) && (compareSecure((Value)other, null) == 0);
  }
  
  public int getMemory()
  {
    if (this.small != null) {
      return this.small.length + 104;
    }
    return 140;
  }
  
  public ValueLobDb copyToTemp()
  {
    return this;
  }
  
  public ValueLobDb copyToResult()
  {
    if (this.handler == null) {
      return this;
    }
    LobStorageInterface s = this.handler.getLobStorage();
    if (s.isReadOnly()) {
      return this;
    }
    return s.copyLob(this, -3, getPrecision());
  }
  
  public long getLobId()
  {
    return this.lobId;
  }
  
  public String toString()
  {
    return "lob: " + this.fileName + " table: " + this.tableId + " id: " + this.lobId;
  }
  
  public static ValueLobDb createTempClob(Reader in, long length, DataHandler handler)
  {
    BufferedReader reader;
    BufferedReader reader;
    if ((in instanceof BufferedReader)) {
      reader = (BufferedReader)in;
    } else {
      reader = new BufferedReader(in, 4096);
    }
    try
    {
      boolean compress = handler.getLobCompressionAlgorithm(16) != null;
      long remaining = Long.MAX_VALUE;
      if ((length >= 0L) && (length < remaining)) {
        remaining = length;
      }
      int len = getBufferSize(handler, compress, remaining);
      char[] buff;
      if (len >= Integer.MAX_VALUE)
      {
        String data = IOUtils.readStringAndClose(reader, -1);
        char[] buff = data.toCharArray();
        len = buff.length;
      }
      else
      {
        buff = new char[len];
        reader.mark(len);
        len = IOUtils.readFully(reader, buff, len);
      }
      if (len <= handler.getMaxLengthInplaceLob())
      {
        byte[] small = new String(buff, 0, len).getBytes(Constants.UTF8);
        return createSmallLob(16, small, len);
      }
      reader.reset();
      return new ValueLobDb(handler, reader, remaining);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  public static ValueLobDb createTempBlob(InputStream in, long length, DataHandler handler)
  {
    try
    {
      long remaining = Long.MAX_VALUE;
      boolean compress = handler.getLobCompressionAlgorithm(15) != null;
      if ((length >= 0L) && (length < remaining)) {
        remaining = length;
      }
      int len = getBufferSize(handler, compress, remaining);
      byte[] buff;
      if (len >= Integer.MAX_VALUE)
      {
        byte[] buff = IOUtils.readBytesAndClose(in, -1);
        len = buff.length;
      }
      else
      {
        buff = DataUtils.newBytes(len);
        len = IOUtils.readFully(in, buff, len);
      }
      if (len <= handler.getMaxLengthInplaceLob())
      {
        byte[] small = DataUtils.newBytes(len);
        System.arraycopy(buff, 0, small, 0, len);
        return createSmallLob(15, small, small.length);
      }
      return new ValueLobDb(handler, buff, len, in, remaining);
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  private static int getBufferSize(DataHandler handler, boolean compress, long remaining)
  {
    if ((remaining < 0L) || (remaining > 2147483647L)) {
      remaining = 2147483647L;
    }
    int inplace = handler.getMaxLengthInplaceLob();
    long m = compress ? 131072L : 4096L;
    if ((m < remaining) && (m <= inplace))
    {
      m = Math.min(remaining, inplace + 1L);
      
      m = MathUtils.roundUpLong(m, 4096L);
    }
    m = Math.min(remaining, m);
    m = MathUtils.convertLongToInt(m);
    if (m < 0L) {
      m = 2147483647L;
    }
    return (int)m;
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if (this.precision <= precision) {
      return this;
    }
    ValueLobDb lob;
    if (this.type == 16)
    {
      ValueLobDb lob;
      if (this.handler == null) {
        try
        {
          int p = MathUtils.convertLongToInt(precision);
          String s = IOUtils.readStringAndClose(getReader(), p);
          byte[] data = s.getBytes(Constants.UTF8);
          lob = createSmallLob(this.type, data, s.length());
        }
        catch (IOException e)
        {
          throw DbException.convertIOException(e, null);
        }
      } else {
        lob = createTempClob(getReader(), precision, this.handler);
      }
    }
    else if (this.handler == null)
    {
      try
      {
        int p = MathUtils.convertLongToInt(precision);
        byte[] data = IOUtils.readBytesAndClose(getInputStream(), p);
        lob = createSmallLob(this.type, data, data.length);
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, null);
      }
    }
    else
    {
      lob = createTempBlob(getInputStream(), precision, this.handler);
    }
    return lob;
  }
  
  public static Value createSmallLob(int type, byte[] small)
  {
    int precision;
    int precision;
    if (type == 16) {
      precision = new String(small, Constants.UTF8).length();
    } else {
      precision = small.length;
    }
    return createSmallLob(type, small, precision);
  }
  
  public static ValueLobDb createSmallLob(int type, byte[] small, long precision)
  {
    return new ValueLobDb(type, small, precision);
  }
}
