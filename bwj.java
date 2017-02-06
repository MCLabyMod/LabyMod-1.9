import java.io.File;
import java.io.FileNotFoundException;

public class bwj
  extends FileNotFoundException
{
  public bwj(File ☃, String ☃)
  {
    super(String.format("'%s' in ResourcePack '%s'", new Object[] { ☃, ☃ }));
  }
}
