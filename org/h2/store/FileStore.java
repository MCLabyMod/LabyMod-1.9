package org.h2.store;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.security.SecureFileStore;
import org.h2.store.fs.FileUtils;
import org.h2.util.TempFileDeleter;

public class FileStore
{
  public static final int HEADER_LENGTH = 48;
  private static final String HEADER = "-- H2 0.5/B --      ".substring(0, 15) + "\n";
  protected String name;
  private final DataHandler handler;
  private FileChannel file;
  private long filePos;
  private long fileLength;
  private Reference<?> autoDeleteReference;
  private boolean checkedWriting = true;
  private final String mode;
  private FileLock lock;
  
  protected FileStore(DataHandler handler, String name, String mode)
  {
    this.handler = handler;
    this.name = name;
    try
    {
      boolean exists = FileUtils.exists(name);
      if ((exists) && (!FileUtils.canWrite(name))) {
        mode = "r";
      } else {
        FileUtils.createDirectories(FileUtils.getParent(name));
      }
      this.file = FileUtils.open(name, mode);
      if (exists) {
        this.fileLength = this.file.size();
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, "name: " + name + " mode: " + mode);
    }
    this.mode = mode;
  }
  
  public static FileStore open(DataHandler handler, String name, String mode)
  {
    return open(handler, name, mode, null, null, 0);
  }
  
  public static FileStore open(DataHandler handler, String name, String mode, String cipher, byte[] key)
  {
    return open(handler, name, mode, cipher, key, 1024);
  }
  
  public static FileStore open(DataHandler handler, String name, String mode, String cipher, byte[] key, int keyIterations)
  {
    FileStore store;
    FileStore store;
    if (cipher == null) {
      store = new FileStore(handler, name, mode);
    } else {
      store = new SecureFileStore(handler, name, mode, cipher, key, keyIterations);
    }
    return store;
  }
  
  protected byte[] generateSalt()
  {
    return HEADER.getBytes(Constants.UTF8);
  }
  
  protected void initKey(byte[] salt) {}
  
  public void setCheckedWriting(boolean value)
  {
    this.checkedWriting = value;
  }
  
  private void checkWritingAllowed()
  {
    if ((this.handler != null) && (this.checkedWriting)) {
      this.handler.checkWritingAllowed();
    }
  }
  
  private void checkPowerOff()
  {
    if (this.handler != null) {
      this.handler.checkPowerOff();
    }
  }
  
  public void init()
  {
    int len = 16;
    
    byte[] magic = HEADER.getBytes(Constants.UTF8);
    if (length() < 48L)
    {
      this.checkedWriting = false;
      writeDirect(magic, 0, len);
      byte[] salt = generateSalt();
      writeDirect(salt, 0, len);
      initKey(salt);
      
      write(magic, 0, len);
      this.checkedWriting = true;
    }
    else
    {
      seek(0L);
      byte[] buff = new byte[len];
      readFullyDirect(buff, 0, len);
      if (!Arrays.equals(buff, magic)) {
        throw DbException.get(90048, this.name);
      }
      byte[] salt = new byte[len];
      readFullyDirect(salt, 0, len);
      initKey(salt);
      
      readFully(buff, 0, 16);
      if (!Arrays.equals(buff, magic)) {
        throw DbException.get(90049, this.name);
      }
    }
  }
  
  public void close()
  {
    if (this.file != null) {
      try
      {
        trace("close", this.name, this.file);
        this.file.close();
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, this.name);
      }
      finally
      {
        this.file = null;
      }
    }
  }
  
  public void closeSilently()
  {
    try
    {
      close();
    }
    catch (Exception e) {}
  }
  
  public void closeAndDeleteSilently()
  {
    if (this.file != null)
    {
      closeSilently();
      this.handler.getTempFileDeleter().deleteFile(this.autoDeleteReference, this.name);
      this.name = null;
    }
  }
  
  protected void readFullyDirect(byte[] b, int off, int len)
  {
    readFully(b, off, len);
  }
  
  public void readFully(byte[] b, int off, int len)
  {
    if ((SysProperties.CHECK) && ((len < 0) || (len % 16 != 0))) {
      DbException.throwInternalError("unaligned read " + this.name + " len " + len);
    }
    checkPowerOff();
    try
    {
      FileUtils.readFully(this.file, ByteBuffer.wrap(b, off, len));
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, this.name);
    }
    this.filePos += len;
  }
  
  public void seek(long pos)
  {
    if ((SysProperties.CHECK) && (pos % 16L != 0L)) {
      DbException.throwInternalError("unaligned seek " + this.name + " pos " + pos);
    }
    try
    {
      if (pos != this.filePos)
      {
        this.file.position(pos);
        this.filePos = pos;
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, this.name);
    }
  }
  
  protected void writeDirect(byte[] b, int off, int len)
  {
    write(b, off, len);
  }
  
  public void write(byte[] b, int off, int len)
  {
    if ((SysProperties.CHECK) && ((len < 0) || (len % 16 != 0))) {
      DbException.throwInternalError("unaligned write " + this.name + " len " + len);
    }
    checkWritingAllowed();
    checkPowerOff();
    try
    {
      FileUtils.writeFully(this.file, ByteBuffer.wrap(b, off, len));
    }
    catch (IOException e)
    {
      closeFileSilently();
      throw DbException.convertIOException(e, this.name);
    }
    this.filePos += len;
    this.fileLength = Math.max(this.filePos, this.fileLength);
  }
  
  public void setLength(long newLength)
  {
    if ((SysProperties.CHECK) && (newLength % 16L != 0L)) {
      DbException.throwInternalError("unaligned setLength " + this.name + " pos " + newLength);
    }
    checkPowerOff();
    checkWritingAllowed();
    try
    {
      if (newLength > this.fileLength)
      {
        long pos = this.filePos;
        this.file.position(newLength - 1L);
        FileUtils.writeFully(this.file, ByteBuffer.wrap(new byte[1]));
        this.file.position(pos);
      }
      else
      {
        this.file.truncate(newLength);
      }
      this.fileLength = newLength;
    }
    catch (IOException e)
    {
      closeFileSilently();
      throw DbException.convertIOException(e, this.name);
    }
  }
  
  public long length()
  {
    try
    {
      long len = this.fileLength;
      if (SysProperties.CHECK2)
      {
        len = this.file.size();
        if (len != this.fileLength) {
          DbException.throwInternalError("file " + this.name + " length " + len + " expected " + this.fileLength);
        }
      }
      if ((SysProperties.CHECK2) && (len % 16L != 0L))
      {
        long newLength = len + 16L - len % 16L;
        
        this.file.truncate(newLength);
        this.fileLength = newLength;
        DbException.throwInternalError("unaligned file length " + this.name + " len " + len);
      }
      return len;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, this.name);
    }
  }
  
  public long getFilePointer()
  {
    if (SysProperties.CHECK2) {
      try
      {
        if (this.file.position() != this.filePos) {
          DbException.throwInternalError();
        }
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, this.name);
      }
    }
    return this.filePos;
  }
  
  public void sync()
  {
    try
    {
      this.file.force(true);
    }
    catch (IOException e)
    {
      closeFileSilently();
      throw DbException.convertIOException(e, this.name);
    }
  }
  
  public void autoDelete()
  {
    if (this.autoDeleteReference == null) {
      this.autoDeleteReference = this.handler.getTempFileDeleter().addFile(this.name, this);
    }
  }
  
  public void stopAutoDelete()
  {
    this.handler.getTempFileDeleter().stopAutoDelete(this.autoDeleteReference, this.name);
    this.autoDeleteReference = null;
  }
  
  public void closeFile()
    throws IOException
  {
    this.file.close();
    this.file = null;
  }
  
  private void closeFileSilently()
  {
    try
    {
      this.file.close();
    }
    catch (IOException e) {}
  }
  
  public void openFile()
    throws IOException
  {
    if (this.file == null)
    {
      this.file = FileUtils.open(this.name, this.mode);
      this.file.position(this.filePos);
    }
  }
  
  private static void trace(String method, String fileName, Object o)
  {
    if (SysProperties.TRACE_IO) {
      System.out.println("FileStore." + method + " " + fileName + " " + o);
    }
  }
  
  public synchronized boolean tryLock()
  {
    try
    {
      this.lock = this.file.tryLock();
      return this.lock != null;
    }
    catch (Exception e) {}
    return false;
  }
  
  public synchronized void releaseLock()
  {
    if ((this.file != null) && (this.lock != null))
    {
      try
      {
        this.lock.release();
      }
      catch (Exception e) {}
      this.lock = null;
    }
  }
}
