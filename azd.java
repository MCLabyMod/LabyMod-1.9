import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azd
  extends azg
{
  private static final Logger c = ;
  
  public azd(File ☃, pb ☃)
  {
    super(☃, ☃);
  }
  
  public String a()
  {
    return "Anvil";
  }
  
  public List<azl> b()
    throws azj
  {
    if ((this.a == null) || (!this.a.exists()) || (!this.a.isDirectory())) {
      throw new azj("Unable to read or access folder where game worlds are saved!");
    }
    List<azl> ☃ = Lists.newArrayList();
    
    File[] ☃ = this.a.listFiles();
    for (File ☃ : ☃) {
      if (☃.isDirectory())
      {
        String ☃ = ☃.getName();
        
        azh ☃ = c(☃);
        if ((☃ != null) && ((☃.k() == 19132) || (☃.k() == 19133)))
        {
          boolean ☃ = ☃.k() != c();
          String ☃ = ☃.j();
          if (StringUtils.isEmpty(☃)) {
            ☃ = ☃;
          }
          long ☃ = 0L;
          ☃.add(new azl(☃, ☃, ☃, ☃, ☃));
        }
      }
    }
    return ☃;
  }
  
  protected int c()
  {
    return 19133;
  }
  
  public void d() {}
  
  public azi a(String ☃, boolean ☃)
  {
    return new azc(this.a, ☃, ☃, this.b);
  }
  
  public boolean a(String ☃)
  {
    azh ☃ = c(☃);
    if ((☃ == null) || (☃.k() != 19132)) {
      return false;
    }
    return true;
  }
  
  public boolean b(String ☃)
  {
    azh ☃ = c(☃);
    if ((☃ == null) || (☃.k() == c())) {
      return false;
    }
    return true;
  }
  
  public boolean a(String ☃, op ☃)
  {
    ☃.a(0);
    
    List<File> ☃ = Lists.newArrayList();
    List<File> ☃ = Lists.newArrayList();
    List<File> ☃ = Lists.newArrayList();
    File ☃ = new File(this.a, ☃);
    File ☃ = new File(☃, "DIM-1");
    File ☃ = new File(☃, "DIM1");
    
    c.info("Scanning folders...");
    
    a(☃, ☃);
    if (☃.exists()) {
      a(☃, ☃);
    }
    if (☃.exists()) {
      a(☃, ☃);
    }
    int ☃ = ☃.size() + ☃.size() + ☃.size();
    c.info("Total conversion count is " + ☃);
    
    azh ☃ = c(☃);
    
    aik ☃ = null;
    if (☃.t() == ahy.c) {
      ☃ = new aio(ail.c);
    } else {
      ☃ = new aik(☃);
    }
    a(new File(☃, "region"), ☃, ☃, 0, ☃, ☃);
    
    a(new File(☃, "region"), ☃, new aio(ail.j), ☃.size(), ☃, ☃);
    
    a(new File(☃, "region"), ☃, new aio(ail.k), ☃.size() + ☃.size(), ☃, ☃);
    
    ☃.e(19133);
    if (☃.t() == ahy.h) {
      ☃.a(ahy.b);
    }
    g(☃);
    
    azi ☃ = a(☃, false);
    ☃.a(☃);
    
    return true;
  }
  
  private void g(String ☃)
  {
    File ☃ = new File(this.a, ☃);
    if (!☃.exists())
    {
      c.warn("Unable to create level.dat_mcr backup");
      return;
    }
    File ☃ = new File(☃, "level.dat");
    if (!☃.exists())
    {
      c.warn("Unable to create level.dat_mcr backup");
      return;
    }
    File ☃ = new File(☃, "level.dat_mcr");
    if (!☃.renameTo(☃)) {
      c.warn("Unable to create level.dat_mcr backup");
    }
  }
  
  private void a(File ☃, Iterable<File> ☃, aik ☃, int ☃, int ☃, op ☃)
  {
    for (File ☃ : ☃)
    {
      a(☃, ☃, ☃, ☃, ☃, ☃);
      
      ☃++;
      int ☃ = (int)Math.round(100.0D * ☃ / ☃);
      ☃.a(☃);
    }
  }
  
  private void a(File ☃, File ☃, aik ☃, int ☃, int ☃, op ☃)
  {
    try
    {
      String ☃ = ☃.getName();
      
      asq ☃ = new asq(☃);
      asq ☃ = new asq(new File(☃, ☃.substring(0, ☃.length() - ".mcr".length()) + ".mca"));
      for (int ☃ = 0; ☃ < 32; ☃++)
      {
        for (int ☃ = 0; ☃ < 32; ☃++) {
          if ((☃.c(☃, ☃)) && (!☃.c(☃, ☃)))
          {
            DataInputStream ☃ = ☃.a(☃, ☃);
            if (☃ == null)
            {
              c.warn("Failed to fetch input stream");
            }
            else
            {
              dn ☃ = dx.a(☃);
              ☃.close();
              
              dn ☃ = ☃.o("Level");
              asp.a ☃ = asp.a(☃);
              
              dn ☃ = new dn();
              dn ☃ = new dn();
              ☃.a("Level", ☃);
              asp.a(☃, ☃, ☃);
              
              DataOutputStream ☃ = ☃.b(☃, ☃);
              dx.a(☃, ☃);
              ☃.close();
            }
          }
        }
        int ☃ = (int)Math.round(100.0D * (☃ * 1024) / (☃ * 1024));
        int ☃ = (int)Math.round(100.0D * ((☃ + 1) * 32 + ☃ * 1024) / (☃ * 1024));
        if (☃ > ☃) {
          ☃.a(☃);
        }
      }
      ☃.c();
      ☃.c();
    }
    catch (IOException ☃)
    {
      ☃.printStackTrace();
    }
  }
  
  private void a(File ☃, Collection<File> ☃)
  {
    File ☃ = new File(☃, "region");
    File[] ☃ = ☃.listFiles(new FilenameFilter()
    {
      public boolean accept(File ☃, String ☃)
      {
        return ☃.endsWith(".mcr");
      }
    });
    if (☃ != null) {
      Collections.addAll(☃, ☃);
    }
  }
}
