package org.h2.mvstore;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import org.h2.mvstore.cache.FilePathCache;
import org.h2.store.fs.FilePath;
import org.h2.store.fs.FilePathDisk;
import org.h2.store.fs.FilePathEncrypt;
import org.h2.store.fs.FilePathEncrypt.FileEncrypt;
import org.h2.store.fs.FilePathNio;

public class FileStore
{
  protected long readCount;
  protected long readBytes;
  protected long writeCount;
  protected long writeBytes;
  protected final FreeSpaceBitSet freeSpace = new FreeSpaceBitSet(2, 4096);
  protected String fileName;
  protected boolean readOnly;
  protected long fileSize;
  protected FileChannel file;
  protected FileChannel encryptedFile;
  protected FileLock fileLock;
  
  public String toString()
  {
    return this.fileName;
  }
  
  public ByteBuffer readFully(long pos, int len)
  {
    ByteBuffer dst = ByteBuffer.allocate(len);
    DataUtils.readFully(this.file, pos, dst);
    this.readCount += 1L;
    this.readBytes += len;
    return dst;
  }
  
  public void writeFully(long pos, ByteBuffer src)
  {
    int len = src.remaining();
    this.fileSize = Math.max(this.fileSize, pos + len);
    DataUtils.writeFully(this.file, pos, src);
    this.writeCount += 1L;
    this.writeBytes += len;
  }
  
  public void open(String fileName, boolean readOnly, char[] encryptionKey)
  {
    if (this.file != null) {
      return;
    }
    if (fileName != null)
    {
      FilePath p = FilePath.get(fileName);
      if (((p instanceof FilePathDisk)) && (!fileName.startsWith(p.getScheme() + ":")))
      {
        FilePathNio.class.getName();
        fileName = "nio:" + fileName;
      }
    }
    this.fileName = fileName;
    FilePath f = FilePath.get(fileName);
    FilePath parent = f.getParent();
    if ((parent != null) && (!parent.exists())) {
      throw DataUtils.newIllegalArgumentException("Directory does not exist: {0}", new Object[] { parent });
    }
    if ((f.exists()) && (!f.canWrite())) {
      readOnly = true;
    }
    this.readOnly = readOnly;
    try
    {
      this.file = f.open(readOnly ? "r" : "rw");
      if (encryptionKey != null)
      {
        byte[] key = FilePathEncrypt.getPasswordBytes(encryptionKey);
        this.encryptedFile = this.file;
        this.file = new FilePathEncrypt.FileEncrypt(fileName, key, this.file);
      }
      this.file = FilePathCache.wrap(this.file);
      try
      {
        if (readOnly) {
          this.fileLock = this.file.tryLock(0L, Long.MAX_VALUE, true);
        } else {
          this.fileLock = this.file.tryLock();
        }
      }
      catch (OverlappingFileLockException e)
      {
        throw DataUtils.newIllegalStateException(7, "The file is locked: {0}", new Object[] { fileName, e });
      }
      if (this.fileLock == null) {
        throw DataUtils.newIllegalStateException(7, "The file is locked: {0}", new Object[] { fileName });
      }
      this.fileSize = this.file.size();
    }
    catch (IOException e)
    {
      throw DataUtils.newIllegalStateException(1, "Could not open file {0}", new Object[] { fileName, e });
    }
  }
  
  public void close()
  {
    try
    {
      if (this.fileLock != null)
      {
        this.fileLock.release();
        this.fileLock = null;
      }
      this.file.close();
      this.freeSpace.clear();
    }
    catch (Exception e)
    {
      throw DataUtils.newIllegalStateException(2, "Closing failed for file {0}", new Object[] { this.fileName, e });
    }
    finally
    {
      this.file = null;
    }
  }
  
  public void sync()
  {
    try
    {
      this.file.force(true);
    }
    catch (IOException e)
    {
      throw DataUtils.newIllegalStateException(2, "Could not sync file {0}", new Object[] { this.fileName, e });
    }
  }
  
  public long size()
  {
    return this.fileSize;
  }
  
  public void truncate(long size)
  {
    try
    {
      this.writeCount += 1L;
      this.file.truncate(size);
      this.fileSize = Math.min(this.fileSize, size);
    }
    catch (IOException e)
    {
      throw DataUtils.newIllegalStateException(2, "Could not truncate file {0} to size {1}", new Object[] { this.fileName, Long.valueOf(size), e });
    }
  }
  
  public FileChannel getFile()
  {
    return this.file;
  }
  
  public FileChannel getEncryptedFile()
  {
    return this.encryptedFile;
  }
  
  public long getWriteCount()
  {
    return this.writeCount;
  }
  
  public long getWriteBytes()
  {
    return this.writeBytes;
  }
  
  public long getReadCount()
  {
    return this.readCount;
  }
  
  public long getReadBytes()
  {
    return this.readBytes;
  }
  
  public boolean isReadOnly()
  {
    return this.readOnly;
  }
  
  public int getDefaultRetentionTime()
  {
    return 45000;
  }
  
  public void markUsed(long pos, int length)
  {
    this.freeSpace.markUsed(pos, length);
  }
  
  public long allocate(int length)
  {
    return this.freeSpace.allocate(length);
  }
  
  public void free(long pos, int length)
  {
    this.freeSpace.free(pos, length);
  }
  
  public int getFillRate()
  {
    return this.freeSpace.getFillRate();
  }
  
  long getFirstFree()
  {
    return this.freeSpace.getFirstFree();
  }
  
  public void clear()
  {
    this.freeSpace.clear();
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
}
