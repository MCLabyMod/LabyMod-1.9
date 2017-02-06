package shadersmod.client;

import StrUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ShaderPackZip
  implements IShaderPack
{
  protected File packFile;
  protected ZipFile packZipFile;
  
  public ShaderPackZip(String name, File file)
  {
    this.packFile = file;
    this.packZipFile = null;
  }
  
  public void close()
  {
    if (this.packZipFile == null) {
      return;
    }
    try
    {
      this.packZipFile.close();
    }
    catch (Exception excp) {}
    this.packZipFile = null;
  }
  
  public InputStream getResourceAsStream(String resName)
  {
    try
    {
      if (this.packZipFile == null) {
        this.packZipFile = new ZipFile(this.packFile);
      }
      String name = StrUtils.removePrefix(resName, "/");
      ZipEntry entry = this.packZipFile.getEntry(name);
      if (entry == null) {
        return null;
      }
      return this.packZipFile.getInputStream(entry);
    }
    catch (Exception excp) {}
    return null;
  }
  
  public boolean hasDirectory(String resName)
  {
    try
    {
      if (this.packZipFile == null) {
        this.packZipFile = new ZipFile(this.packFile);
      }
      String name = StrUtils.removePrefix(resName, "/");
      ZipEntry entry = this.packZipFile.getEntry(name);
      if (entry == null) {
        return false;
      }
      return true;
    }
    catch (IOException e) {}
    return false;
  }
  
  public String getName()
  {
    return this.packFile.getName();
  }
}
