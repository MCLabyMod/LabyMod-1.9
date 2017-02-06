package org.h2.store.fs;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class FilePathNioMapped
  extends FilePathNio
{
  public FileChannel open(String mode)
    throws IOException
  {
    return new FileNioMapped(this.name.substring(getScheme().length() + 1), mode);
  }
  
  public String getScheme()
  {
    return "nioMapped";
  }
}
