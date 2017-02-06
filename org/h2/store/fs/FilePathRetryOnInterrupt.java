package org.h2.store.fs;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class FilePathRetryOnInterrupt
  extends FilePathWrapper
{
  static final String SCHEME = "retry";
  
  public FileChannel open(String mode)
    throws IOException
  {
    return new FileRetryOnInterrupt(this.name.substring(getScheme().length() + 1), mode);
  }
  
  public String getScheme()
  {
    return "retry";
  }
}
