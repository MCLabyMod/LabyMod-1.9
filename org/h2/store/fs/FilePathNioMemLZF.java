package org.h2.store.fs;

class FilePathNioMemLZF
  extends FilePathNioMem
{
  boolean compressed()
  {
    return true;
  }
  
  public FilePathNioMem getPath(String path)
  {
    FilePathNioMemLZF p = new FilePathNioMemLZF();
    p.name = getCanonicalPath(path);
    return p;
  }
  
  public String getScheme()
  {
    return "nioMemLZF";
  }
}
