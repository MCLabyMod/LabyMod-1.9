package org.h2.value;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.store.DataHandler;
import org.h2.store.FileStore;
import org.h2.store.FileStoreInputStream;
import org.h2.store.FileStoreOutputStream;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.MathUtils;
import org.h2.util.SmallLRUCache;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class ValueLob
  extends Value
{
  private static int dirCounter;
  private final int type;
  private long precision;
  private DataHandler handler;
  private int tableId;
  private int objectId;
  private String fileName;
  private boolean linked;
  private byte[] small;
  private int hash;
  private boolean compressed;
  private FileStore tempFile;
  
  private ValueLob(int type, DataHandler handler, String fileName, int tableId, int objectId, boolean linked, long precision, boolean compressed)
  {
    this.type = type;
    this.handler = handler;
    this.fileName = fileName;
    this.tableId = tableId;
    this.objectId = objectId;
    this.linked = linked;
    this.precision = precision;
    this.compressed = compressed;
  }
  
  private ValueLob(int type, byte[] small)
  {
    this.type = type;
    this.small = small;
    if (small != null) {
      if (type == 15) {
        this.precision = small.length;
      } else {
        this.precision = getString().length();
      }
    }
  }
  
  private static ValueLob copy(ValueLob lob)
  {
    ValueLob copy = new ValueLob(lob.type, lob.handler, lob.fileName, lob.tableId, lob.objectId, lob.linked, lob.precision, lob.compressed);
    
    copy.small = lob.small;
    copy.hash = lob.hash;
    return copy;
  }
  
  private static ValueLob createSmallLob(int type, byte[] small)
  {
    return new ValueLob(type, small);
  }
  
  private static String getFileName(DataHandler handler, int tableId, int objectId)
  {
    if ((SysProperties.CHECK) && (tableId == 0) && (objectId == 0)) {
      DbException.throwInternalError("0 LOB");
    }
    String table = ".t" + tableId;
    return getFileNamePrefix(handler.getDatabasePath(), objectId) + table + ".lob.db";
  }
  
  public static ValueLob openLinked(int type, DataHandler handler, int tableId, int objectId, long precision, boolean compression)
  {
    String fileName = getFileName(handler, tableId, objectId);
    return new ValueLob(type, handler, fileName, tableId, objectId, true, precision, compression);
  }
  
  public static ValueLob openUnlinked(int type, DataHandler handler, int tableId, int objectId, long precision, boolean compression, String fileName)
  {
    return new ValueLob(type, handler, fileName, tableId, objectId, false, precision, compression);
  }
  
  private static ValueLob createClob(Reader in, long length, DataHandler handler)
  {
    try
    {
      if (handler == null)
      {
        String s = IOUtils.readStringAndClose(in, (int)length);
        return createSmallLob(16, s.getBytes(Constants.UTF8));
      }
      boolean compress = handler.getLobCompressionAlgorithm(16) != null;
      long remaining = Long.MAX_VALUE;
      if ((length >= 0L) && (length < remaining)) {
        remaining = length;
      }
      int len = getBufferSize(handler, compress, remaining);
      char[] buff;
      if (len >= Integer.MAX_VALUE)
      {
        String data = IOUtils.readStringAndClose(in, -1);
        char[] buff = data.toCharArray();
        len = buff.length;
      }
      else
      {
        buff = new char[len];
        len = IOUtils.readFully(in, buff, len);
      }
      if (len <= handler.getMaxLengthInplaceLob())
      {
        byte[] small = new String(buff, 0, len).getBytes(Constants.UTF8);
        return createSmallLob(16, small);
      }
      ValueLob lob = new ValueLob(16, null);
      lob.createFromReader(buff, len, in, remaining, handler);
      return lob;
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
  
  private void createFromReader(char[] buff, int len, Reader in, long remaining, DataHandler h)
    throws IOException
  {
    FileStoreOutputStream out = initLarge(h);
    boolean compress = h.getLobCompressionAlgorithm(16) != null;
    try
    {
      for (;;)
      {
        this.precision += len;
        byte[] b = new String(buff, 0, len).getBytes(Constants.UTF8);
        out.write(b, 0, b.length);
        remaining -= len;
        if (remaining <= 0L) {
          break;
        }
        len = getBufferSize(h, compress, remaining);
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
  }
  
  private static String getFileNamePrefix(String path, int objectId)
  {
    int f = objectId % SysProperties.LOB_FILES_PER_DIRECTORY;
    String name;
    if (f > 0) {
      name = SysProperties.FILE_SEPARATOR + objectId;
    } else {
      name = "";
    }
    objectId /= SysProperties.LOB_FILES_PER_DIRECTORY;
    while (objectId > 0)
    {
      f = objectId % SysProperties.LOB_FILES_PER_DIRECTORY;
      name = SysProperties.FILE_SEPARATOR + f + ".lobs.db" + name;
      
      objectId /= SysProperties.LOB_FILES_PER_DIRECTORY;
    }
    String name = FileUtils.toRealPath(path + ".lobs.db" + name);
    
    return name;
  }
  
  private static int getNewObjectId(DataHandler h)
  {
    String path = h.getDatabasePath();
    if ((path != null) && (path.length() == 0)) {
      path = new File(Utils.getProperty("java.io.tmpdir", "."), SysProperties.PREFIX_TEMP_FILE).getAbsolutePath();
    }
    int newId = 0;
    int lobsPerDir = SysProperties.LOB_FILES_PER_DIRECTORY;
    for (;;)
    {
      String dir = getFileNamePrefix(path, newId);
      String[] list = getFileList(h, dir);
      int fileCount = 0;
      boolean[] used = new boolean[lobsPerDir];
      for (String name : list) {
        if (name.endsWith(".db"))
        {
          name = FileUtils.getName(name);
          String n = name.substring(0, name.indexOf('.'));
          int id;
          try
          {
            id = Integer.parseInt(n);
          }
          catch (NumberFormatException e)
          {
            id = -1;
          }
          if (id > 0)
          {
            fileCount++;
            used[(id % lobsPerDir)] = true;
          }
        }
      }
      int fileId = -1;
      if (fileCount < lobsPerDir) {
        for (int i = 1; i < lobsPerDir; i++) {
          if (used[i] == 0)
          {
            fileId = i;
            break;
          }
        }
      }
      if (fileId > 0)
      {
        newId += fileId;
        invalidateFileList(h, dir);
        break;
      }
      if (newId > Integer.MAX_VALUE / lobsPerDir)
      {
        newId = 0;
        dirCounter = MathUtils.randomInt(lobsPerDir - 1) * lobsPerDir;
      }
      else
      {
        int dirId = dirCounter++ / (lobsPerDir - 1) + 1;
        newId *= lobsPerDir;
        newId += dirId * lobsPerDir;
      }
    }
    return newId;
  }
  
  private static void invalidateFileList(DataHandler h, String dir)
  {
    SmallLRUCache<String, String[]> cache = h.getLobFileListCache();
    if (cache != null) {
      synchronized (cache)
      {
        cache.remove(dir);
      }
    }
  }
  
  private static String[] getFileList(DataHandler h, String dir)
  {
    SmallLRUCache<String, String[]> cache = h.getLobFileListCache();
    String[] list;
    String[] list;
    if (cache == null) {
      list = (String[])FileUtils.newDirectoryStream(dir).toArray(new String[0]);
    } else {
      synchronized (cache)
      {
        list = (String[])cache.get(dir);
        if (list == null)
        {
          list = (String[])FileUtils.newDirectoryStream(dir).toArray(new String[0]);
          cache.put(dir, list);
        }
      }
    }
    return list;
  }
  
  private static ValueLob createBlob(InputStream in, long length, DataHandler handler)
  {
    try
    {
      if (handler == null)
      {
        byte[] data = IOUtils.readBytesAndClose(in, (int)length);
        return createSmallLob(15, data);
      }
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
        return createSmallLob(15, small);
      }
      ValueLob lob = new ValueLob(15, null);
      lob.createFromStream(buff, len, in, remaining, handler);
      return lob;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  private FileStoreOutputStream initLarge(DataHandler h)
  {
    this.handler = h;
    this.tableId = 0;
    this.linked = false;
    this.precision = 0L;
    this.small = null;
    this.hash = 0;
    String compressionAlgorithm = h.getLobCompressionAlgorithm(this.type);
    this.compressed = (compressionAlgorithm != null);
    synchronized (h)
    {
      String path = h.getDatabasePath();
      if ((path != null) && (path.length() == 0)) {
        path = new File(Utils.getProperty("java.io.tmpdir", "."), SysProperties.PREFIX_TEMP_FILE).getAbsolutePath();
      }
      this.objectId = getNewObjectId(h);
      this.fileName = (getFileNamePrefix(path, this.objectId) + ".temp.db");
      this.tempFile = h.openFile(this.fileName, "rw", false);
      this.tempFile.autoDelete();
    }
    FileStoreOutputStream out = new FileStoreOutputStream(this.tempFile, h, compressionAlgorithm);
    
    return out;
  }
  
  private void createFromStream(byte[] buff, int len, InputStream in, long remaining, DataHandler h)
    throws IOException
  {
    FileStoreOutputStream out = initLarge(h);
    boolean compress = h.getLobCompressionAlgorithm(15) != null;
    try
    {
      for (;;)
      {
        this.precision += len;
        out.write(buff, 0, len);
        remaining -= len;
        if (remaining > 0L)
        {
          len = getBufferSize(h, compress, remaining);
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
  }
  
  public Value convertTo(int t)
  {
    if (t == this.type) {
      return this;
    }
    if (t == 16)
    {
      ValueLob copy = createClob(getReader(), -1L, this.handler);
      return copy;
    }
    if (t == 15)
    {
      ValueLob copy = createBlob(getInputStream(), -1L, this.handler);
      return copy;
    }
    return super.convertTo(t);
  }
  
  public boolean isLinked()
  {
    return this.linked;
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
  
  public void close()
  {
    if (this.fileName != null)
    {
      if (this.tempFile != null)
      {
        this.tempFile.stopAutoDelete();
        this.tempFile = null;
      }
      deleteFile(this.handler, this.fileName);
    }
  }
  
  public void unlink(DataHandler handler)
  {
    if ((this.linked) && (this.fileName != null)) {
      synchronized (handler)
      {
        String temp = getFileName(handler, -1, this.objectId);
        deleteFile(handler, temp);
        renameFile(handler, this.fileName, temp);
        this.tempFile = FileStore.open(handler, temp, "rw");
        this.tempFile.autoDelete();
        this.tempFile.closeSilently();
        this.fileName = temp;
        this.linked = false;
      }
    }
  }
  
  public Value link(DataHandler h, int tabId)
  {
    if (this.fileName == null)
    {
      this.tableId = tabId;
      return this;
    }
    if (this.linked)
    {
      ValueLob copy = copy(this);
      copy.objectId = getNewObjectId(h);
      copy.tableId = tabId;
      String live = getFileName(h, copy.tableId, copy.objectId);
      copyFileTo(h, this.fileName, live);
      copy.fileName = live;
      copy.linked = true;
      return copy;
    }
    if (!this.linked)
    {
      this.tableId = tabId;
      String live = getFileName(h, this.tableId, this.objectId);
      if (this.tempFile != null)
      {
        this.tempFile.stopAutoDelete();
        this.tempFile = null;
      }
      renameFile(h, this.fileName, live);
      this.fileName = live;
      this.linked = true;
    }
    return this;
  }
  
  public int getTableId()
  {
    return this.tableId;
  }
  
  public int getObjectId()
  {
    return this.objectId;
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
      throw DbException.convertIOException(e, this.fileName);
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
      throw DbException.convertIOException(e, this.fileName);
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
    if (this.fileName == null) {
      return new ByteArrayInputStream(this.small);
    }
    FileStore store = this.handler.openFile(this.fileName, "r", true);
    boolean alwaysClose = SysProperties.lobCloseBetweenReads;
    return new BufferedInputStream(new FileStoreInputStream(store, this.handler, this.compressed, alwaysClose), 4096);
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
    buff.append(" /* ").append(this.fileName).append(" */)");
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
    return ((other instanceof ValueLob)) && (compareSecure((Value)other, null) == 0);
  }
  
  public void convertToFileIfRequired(DataHandler h)
  {
    try
    {
      if ((this.small != null) && (this.small.length > h.getMaxLengthInplaceLob()))
      {
        boolean compress = h.getLobCompressionAlgorithm(this.type) != null;
        int len = getBufferSize(h, compress, Long.MAX_VALUE);
        int tabId = this.tableId;
        if (this.type == 15) {
          createFromStream(DataUtils.newBytes(len), 0, getInputStream(), Long.MAX_VALUE, h);
        } else {
          createFromReader(new char[len], 0, getReader(), Long.MAX_VALUE, h);
        }
        Value v2 = link(h, tabId);
        if ((SysProperties.CHECK) && (v2 != this)) {
          DbException.throwInternalError();
        }
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  public boolean isCompressed()
  {
    return this.compressed;
  }
  
  private static synchronized void deleteFile(DataHandler handler, String fileName)
  {
    synchronized (handler.getLobSyncObject())
    {
      FileUtils.delete(fileName);
    }
  }
  
  private static synchronized void renameFile(DataHandler handler, String oldName, String newName)
  {
    synchronized (handler.getLobSyncObject())
    {
      FileUtils.move(oldName, newName);
    }
  }
  
  private static void copyFileTo(DataHandler h, String sourceFileName, String targetFileName)
  {
    synchronized (h.getLobSyncObject())
    {
      try
      {
        IOUtils.copyFiles(sourceFileName, targetFileName);
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, null);
      }
    }
  }
  
  public int getMemory()
  {
    if (this.small != null) {
      return this.small.length + 104;
    }
    return 140;
  }
  
  public ValueLob copyToTemp()
  {
    ValueLob lob;
    ValueLob lob;
    if (this.type == 16) {
      lob = createClob(getReader(), this.precision, this.handler);
    } else {
      lob = createBlob(getInputStream(), this.precision, this.handler);
    }
    return lob;
  }
  
  public Value convertPrecision(long precision, boolean force)
  {
    if (this.precision <= precision) {
      return this;
    }
    ValueLob lob;
    ValueLob lob;
    if (this.type == 16) {
      lob = createClob(getReader(), precision, this.handler);
    } else {
      lob = createBlob(getInputStream(), precision, this.handler);
    }
    return lob;
  }
}
