package org.h2.store.fs;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class FilePathRec
  extends FilePathWrapper
{
  private static final FilePathRec INSTANCE = new FilePathRec();
  private static Recorder recorder;
  private boolean trace;
  
  public static void register()
  {
    FilePath.register(INSTANCE);
  }
  
  public static void setRecorder(Recorder recorder)
  {
    recorder = recorder;
  }
  
  public boolean createFile()
  {
    log(2, this.name);
    return super.createFile();
  }
  
  public FilePath createTempFile(String suffix, boolean deleteOnExit, boolean inTempDir)
    throws IOException
  {
    log(3, unwrap(this.name) + ":" + suffix + ":" + deleteOnExit + ":" + inTempDir);
    
    return super.createTempFile(suffix, deleteOnExit, inTempDir);
  }
  
  public void delete()
  {
    log(4, this.name);
    super.delete();
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    return new FileRec(this, super.open(mode), this.name);
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    log(5, this.name);
    return super.newOutputStream(append);
  }
  
  public void moveTo(FilePath newPath, boolean atomicReplace)
  {
    log(6, unwrap(this.name) + ":" + unwrap(newPath.name));
    super.moveTo(newPath, atomicReplace);
  }
  
  public boolean isTrace()
  {
    return this.trace;
  }
  
  public void setTrace(boolean trace)
  {
    this.trace = trace;
  }
  
  void log(int op, String fileName)
  {
    log(op, fileName, null, 0L);
  }
  
  void log(int op, String fileName, byte[] data, long x)
  {
    if (recorder != null) {
      recorder.log(op, fileName, data, x);
    }
  }
  
  public String getScheme()
  {
    return "rec";
  }
}
