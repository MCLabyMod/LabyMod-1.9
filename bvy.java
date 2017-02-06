import java.io.File;

public class bvy
  extends bvv
{
  private final File a;
  
  public bvy(File ☃)
  {
    this.a = ☃;
  }
  
  public File a(kk ☃)
  {
    return new File(this.a, ☃.toString().replace(':', '/'));
  }
  
  public File a()
  {
    return new File(this.a, "pack.mcmeta");
  }
}
