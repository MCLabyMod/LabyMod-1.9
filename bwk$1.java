import java.io.File;
import java.io.FileFilter;

final class bwk$1
  implements FileFilter
{
  public boolean accept(File ☃)
  {
    boolean ☃ = (☃.isFile()) && (☃.getName().endsWith(".zip"));
    boolean ☃ = (☃.isDirectory()) && (new File(☃, "pack.mcmeta").isFile());
    
    return (☃) || (☃);
  }
}
