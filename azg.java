import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azg
  implements azk
{
  private static final Logger c = ;
  protected final File a;
  protected final pb b;
  
  public azg(File ☃, pb ☃)
  {
    this.b = ☃;
    if (!☃.exists()) {
      ☃.mkdirs();
    }
    this.a = ☃;
  }
  
  public String a()
  {
    return "Old Format";
  }
  
  public List<azl> b()
    throws azj
  {
    List<azl> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < 5; ☃++)
    {
      String ☃ = "World" + (☃ + 1);
      
      azh ☃ = c(☃);
      if (☃ != null) {
        ☃.add(new azl(☃, ☃, "", ☃.g(), false));
      }
    }
    return ☃;
  }
  
  public void d() {}
  
  public azh c(String ☃)
  {
    File ☃ = new File(this.a, ☃);
    if (!☃.exists()) {
      return null;
    }
    File ☃ = new File(☃, "level.dat");
    if (☃.exists())
    {
      azh ☃ = a(☃, this.b);
      if (☃ != null) {
        return ☃;
      }
    }
    ☃ = new File(☃, "level.dat_old");
    if (☃.exists()) {
      return a(☃, this.b);
    }
    return null;
  }
  
  public static azh a(File ☃, pb ☃)
  {
    try
    {
      dn ☃ = dx.a(new FileInputStream(☃));
      dn ☃ = ☃.o("Data");
      return new azh(☃.a(oz.a, ☃));
    }
    catch (Exception ☃)
    {
      c.error("Exception reading " + ☃, ☃);
    }
    return null;
  }
  
  public void a(String ☃, String ☃)
  {
    File ☃ = new File(this.a, ☃);
    if (!☃.exists()) {
      return;
    }
    File ☃ = new File(☃, "level.dat");
    if (☃.exists()) {
      try
      {
        dn ☃ = dx.a(new FileInputStream(☃));
        dn ☃ = ☃.o("Data");
        ☃.a("LevelName", ☃);
        
        dx.a(☃, new FileOutputStream(☃));
      }
      catch (Exception ☃)
      {
        ☃.printStackTrace();
      }
    }
  }
  
  public boolean d(String ☃)
  {
    File ☃ = new File(this.a, ☃);
    if (☃.exists()) {
      return false;
    }
    try
    {
      ☃.mkdir();
      ☃.delete();
    }
    catch (Throwable ☃)
    {
      c.warn("Couldn't make new level", ☃);
      return false;
    }
    return true;
  }
  
  public boolean e(String ☃)
  {
    File ☃ = new File(this.a, ☃);
    if (!☃.exists()) {
      return true;
    }
    c.info("Deleting level " + ☃);
    for (int ☃ = 1; ☃ <= 5; ☃++)
    {
      c.info("Attempt " + ☃ + "...");
      if (a(☃.listFiles())) {
        break;
      }
      c.warn("Unsuccessful in deleting contents.");
      if (☃ < 5) {
        try
        {
          Thread.sleep(500L);
        }
        catch (InterruptedException localInterruptedException) {}
      }
    }
    return ☃.delete();
  }
  
  protected static boolean a(File[] ☃)
  {
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      File ☃ = ☃[☃];
      c.debug("Deleting " + ☃);
      if ((☃.isDirectory()) && 
        (!a(☃.listFiles())))
      {
        c.warn("Couldn't delete directory " + ☃);
        return false;
      }
      if (!☃.delete())
      {
        c.warn("Couldn't delete file " + ☃);
        return false;
      }
    }
    return true;
  }
  
  public azi a(String ☃, boolean ☃)
  {
    return new azf(this.a, ☃, ☃, this.b);
  }
  
  public boolean a(String ☃)
  {
    return false;
  }
  
  public boolean b(String ☃)
  {
    return false;
  }
  
  public boolean a(String ☃, op ☃)
  {
    return false;
  }
  
  public boolean f(String ☃)
  {
    File ☃ = new File(this.a, ☃);
    return ☃.isDirectory();
  }
  
  public File b(String ☃, String ☃)
  {
    return new File(new File(this.a, ☃), ☃);
  }
}
