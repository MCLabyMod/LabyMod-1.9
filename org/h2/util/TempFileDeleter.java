package org.h2.util;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;

public class TempFileDeleter
{
  private final ReferenceQueue<Object> queue = new ReferenceQueue();
  private final HashMap<PhantomReference<?>, String> refMap = New.hashMap();
  
  public static TempFileDeleter getInstance()
  {
    return new TempFileDeleter();
  }
  
  public synchronized Reference<?> addFile(String fileName, Object file)
  {
    IOUtils.trace("TempFileDeleter.addFile", fileName, file);
    PhantomReference<?> ref = new PhantomReference(file, this.queue);
    this.refMap.put(ref, fileName);
    deleteUnused();
    return ref;
  }
  
  public synchronized void deleteFile(Reference<?> ref, String fileName)
  {
    if (ref != null)
    {
      String f2 = (String)this.refMap.remove(ref);
      if (f2 != null)
      {
        if ((SysProperties.CHECK) && 
          (fileName != null) && (!f2.equals(fileName))) {
          DbException.throwInternalError("f2:" + f2 + " f:" + fileName);
        }
        fileName = f2;
      }
    }
    if ((fileName != null) && (FileUtils.exists(fileName))) {
      try
      {
        IOUtils.trace("TempFileDeleter.deleteFile", fileName, null);
        FileUtils.tryDelete(fileName);
      }
      catch (Exception e) {}
    }
  }
  
  public void deleteAll()
  {
    for (String tempFile : New.arrayList(this.refMap.values())) {
      deleteFile(null, tempFile);
    }
    deleteUnused();
  }
  
  public void deleteUnused()
  {
    while (this.queue != null)
    {
      Reference<? extends Object> ref = this.queue.poll();
      if (ref == null) {
        break;
      }
      deleteFile(ref, null);
    }
  }
  
  public void stopAutoDelete(Reference<?> ref, String fileName)
  {
    IOUtils.trace("TempFileDeleter.stopAutoDelete", fileName, ref);
    if (ref != null)
    {
      String f2 = (String)this.refMap.remove(ref);
      if ((SysProperties.CHECK) && (
        (f2 == null) || (!f2.equals(fileName)))) {
        DbException.throwInternalError("f2:" + f2 + " " + (f2 == null ? "" : f2) + " f:" + fileName);
      }
    }
    deleteUnused();
  }
}
