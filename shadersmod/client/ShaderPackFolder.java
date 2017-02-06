package shadersmod.client;

import StrUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ShaderPackFolder
  implements IShaderPack
{
  protected File packFile;
  
  public ShaderPackFolder(String name, File file)
  {
    this.packFile = file;
  }
  
  public void close() {}
  
  public InputStream getResourceAsStream(String resName)
  {
    try
    {
      String name = StrUtils.removePrefixSuffix(resName, "/", "/");
      File resFile = new File(this.packFile, name);
      if (!resFile.exists()) {
        return null;
      }
      return new BufferedInputStream(new FileInputStream(resFile));
    }
    catch (Exception excp) {}
    return null;
  }
  
  public boolean hasDirectory(String name)
  {
    File resFile = new File(this.packFile, name.substring(1));
    if (!resFile.exists()) {
      return false;
    }
    if (!resFile.isDirectory()) {
      return false;
    }
    return true;
  }
  
  public String getName()
  {
    return this.packFile.getName();
  }
}
