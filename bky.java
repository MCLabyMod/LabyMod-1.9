import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bky
{
  private static final Logger a = ;
  private final bcf b;
  private final List<bkx> c = Lists.newArrayList();
  
  public bky(bcf ☃)
  {
    this.b = ☃;
    a();
  }
  
  public void a()
  {
    try
    {
      this.c.clear();
      
      dn ☃ = dx.a(new File(this.b.w, "servers.dat"));
      if (☃ == null) {
        return;
      }
      du ☃ = ☃.c("servers", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
        this.c.add(bkx.a(☃.b(☃)));
      }
    }
    catch (Exception ☃)
    {
      a.error("Couldn't load server list", ☃);
    }
  }
  
  public void b()
  {
    try
    {
      du ☃ = new du();
      for (bkx ☃ : this.c) {
        ☃.a(☃.a());
      }
      dn ☃ = new dn();
      ☃.a("servers", ☃);
      dx.a(☃, new File(this.b.w, "servers.dat"));
    }
    catch (Exception ☃)
    {
      a.error("Couldn't save server list", ☃);
    }
  }
  
  public bkx a(int ☃)
  {
    return (bkx)this.c.get(☃);
  }
  
  public void b(int ☃)
  {
    this.c.remove(☃);
  }
  
  public void a(bkx ☃)
  {
    this.c.add(☃);
  }
  
  public int c()
  {
    return this.c.size();
  }
  
  public void a(int ☃, int ☃)
  {
    bkx ☃ = a(☃);
    this.c.set(☃, a(☃));
    this.c.set(☃, ☃);
    b();
  }
  
  public void a(int ☃, bkx ☃)
  {
    this.c.set(☃, ☃);
  }
  
  public static void b(bkx ☃)
  {
    bky ☃ = new bky(bcf.z());
    ☃.a();
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      bkx ☃ = ☃.a(☃);
      if ((☃.a.equals(☃.a)) && (☃.b.equals(☃.b)))
      {
        ☃.a(☃, ☃);
        break;
      }
    }
    ☃.b();
  }
}
