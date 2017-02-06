import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azf
  implements azi, azq
{
  private static final Logger b = ;
  private final File c;
  private final File d;
  private final File e;
  private final long f = MinecraftServer.av();
  private final String g;
  private final awm h;
  protected final pb a;
  
  public azf(File ☃, String ☃, boolean ☃, pb ☃)
  {
    this.a = ☃;
    this.c = new File(☃, ☃);
    this.c.mkdirs();
    this.d = new File(this.c, "playerdata");
    this.e = new File(this.c, "data");
    this.e.mkdirs();
    this.g = ☃;
    if (☃)
    {
      this.d.mkdirs();
      this.h = new awm(new File(this.c, "structures").toString());
    }
    else
    {
      this.h = null;
    }
    i();
  }
  
  private void i()
  {
    try
    {
      File ☃ = new File(this.c, "session.lock");
      DataOutputStream ☃ = new DataOutputStream(new FileOutputStream(☃));
      try
      {
        ☃.writeLong(this.f);
      }
      finally
      {
        ☃.close();
      }
    }
    catch (IOException ☃)
    {
      ☃.printStackTrace();
      throw new RuntimeException("Failed to check session lock, aborting");
    }
  }
  
  public File b()
  {
    return this.c;
  }
  
  public void c()
    throws ahu
  {
    try
    {
      File ☃ = new File(this.c, "session.lock");
      DataInputStream ☃ = new DataInputStream(new FileInputStream(☃));
      try
      {
        if (☃.readLong() != this.f) {
          throw new ahu("The save is being accessed from another location, aborting");
        }
      }
      finally
      {
        ☃.close();
      }
    }
    catch (IOException ☃)
    {
      throw new ahu("Failed to check session lock, aborting");
    }
  }
  
  public asm a(asv ☃)
  {
    throw new RuntimeException("Old Chunk Storage is no longer supported.");
  }
  
  public azh d()
  {
    File ☃ = new File(this.c, "level.dat");
    if (☃.exists())
    {
      azh ☃ = azg.a(☃, this.a);
      if (☃ != null) {
        return ☃;
      }
    }
    ☃ = new File(this.c, "level.dat_old");
    if (☃.exists()) {
      return azg.a(☃, this.a);
    }
    return null;
  }
  
  public void a(azh ☃, dn ☃)
  {
    dn ☃ = ☃.a(☃);
    
    dn ☃ = new dn();
    ☃.a("Data", ☃);
    try
    {
      File ☃ = new File(this.c, "level.dat_new");
      File ☃ = new File(this.c, "level.dat_old");
      File ☃ = new File(this.c, "level.dat");
      
      dx.a(☃, new FileOutputStream(☃));
      if (☃.exists()) {
        ☃.delete();
      }
      ☃.renameTo(☃);
      if (☃.exists()) {
        ☃.delete();
      }
      ☃.renameTo(☃);
      if (☃.exists()) {
        ☃.delete();
      }
    }
    catch (Exception ☃)
    {
      ☃.printStackTrace();
    }
  }
  
  public void a(azh ☃)
  {
    a(☃, null);
  }
  
  public void a(zj ☃)
  {
    try
    {
      dn ☃ = new dn();
      ☃.e(☃);
      File ☃ = new File(this.d, ☃.bc().toString() + ".dat.tmp");
      File ☃ = new File(this.d, ☃.bc().toString() + ".dat");
      dx.a(☃, new FileOutputStream(☃));
      if (☃.exists()) {
        ☃.delete();
      }
      ☃.renameTo(☃);
    }
    catch (Exception ☃)
    {
      b.warn("Failed to save player data for " + ☃.h_());
    }
  }
  
  public dn b(zj ☃)
  {
    dn ☃ = null;
    try
    {
      File ☃ = new File(this.d, ☃.bc().toString() + ".dat");
      if ((☃.exists()) && (☃.isFile())) {
        ☃ = dx.a(new FileInputStream(☃));
      }
    }
    catch (Exception ☃)
    {
      b.warn("Failed to load player data for " + ☃.h_());
    }
    if (☃ != null) {
      ☃.f(this.a.a(oz.b, ☃));
    }
    return ☃;
  }
  
  public azq e()
  {
    return this;
  }
  
  public String[] f()
  {
    String[] ☃ = this.d.list();
    if (☃ == null) {
      ☃ = new String[0];
    }
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      if (☃[☃].endsWith(".dat")) {
        ☃[☃] = ☃[☃].substring(0, ☃[☃].length() - 4);
      }
    }
    return ☃;
  }
  
  public void a() {}
  
  public File a(String ☃)
  {
    return new File(this.e, ☃ + ".dat");
  }
  
  public awm h()
  {
    return this.h;
  }
}
