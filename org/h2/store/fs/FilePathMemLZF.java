package org.h2.store.fs;

class FilePathMemLZF
  extends FilePathMem
{
  boolean compressed()
  {
    return true;
  }
  
  public String getScheme()
  {
    return "memLZF";
  }
}
