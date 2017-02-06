package org.h2.store.fs;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class FilePathNio
  extends FilePathWrapper
{
  public FileChannel open(String mode)
    throws IOException
  {
    return new FileNio(this.name.substring(getScheme().length() + 1), mode);
  }
  
  public String getScheme()
  {
    return "nio";
  }
}
