import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lo
  implements arz
{
  private static final Logger a = ;
  private final Set<Long> b = Collections.newSetFromMap(new ConcurrentHashMap());
  private final ary c;
  private final asm d;
  private final ol<ase> e = new ol();
  private final List<ase> f = Lists.newArrayList();
  private final lp g;
  
  public lo(lp ☃, asm ☃, ary ☃)
  {
    this.g = ☃;
    this.d = ☃;
    this.c = ☃;
  }
  
  public List<ase> a()
  {
    return this.f;
  }
  
  public void a(int ☃, int ☃)
  {
    if (this.g.s.c(☃, ☃)) {
      this.b.add(Long.valueOf(ahn.a(☃, ☃)));
    }
  }
  
  public void b()
  {
    for (ase ☃ : this.f) {
      a(☃.b, ☃.c);
    }
  }
  
  public ase b(int ☃, int ☃)
  {
    long ☃ = ahn.a(☃, ☃);
    ase ☃ = (ase)this.e.a(☃);
    this.b.remove(Long.valueOf(☃));
    
    return ☃;
  }
  
  public ase c(int ☃, int ☃)
  {
    ase ☃ = b(☃, ☃);
    if (☃ == null)
    {
      ☃ = f(☃, ☃);
      if (☃ != null)
      {
        this.e.a(ahn.a(☃, ☃), ☃);
        this.f.add(☃);
        ☃.c();
        ☃.a(this, this.c);
      }
    }
    return ☃;
  }
  
  public ase d(int ☃, int ☃)
  {
    ase ☃ = c(☃, ☃);
    if (☃ == null)
    {
      long ☃ = ahn.a(☃, ☃);
      ☃ = f(☃, ☃);
      if (☃ == null) {
        try
        {
          ☃ = this.c.a(☃, ☃);
        }
        catch (Throwable ☃)
        {
          b ☃ = b.a(☃, "Exception generating new chunk");
          c ☃ = ☃.a("Chunk to be generated");
          
          ☃.a("Location", String.format("%d,%d", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃) }));
          ☃.a("Position hash", Long.valueOf(☃));
          ☃.a("Generator", this.c);
          
          throw new e(☃);
        }
      }
      this.e.a(☃, ☃);
      this.f.add(☃);
      ☃.c();
      ☃.a(this, this.c);
    }
    return ☃;
  }
  
  private ase f(int ☃, int ☃)
  {
    try
    {
      ase ☃ = this.d.a(this.g, ☃, ☃);
      if (☃ != null)
      {
        ☃.b(this.g.P());
        this.c.b(☃, ☃, ☃);
      }
      return ☃;
    }
    catch (Exception ☃)
    {
      a.error("Couldn't load chunk", ☃);
    }
    return null;
  }
  
  private void a(ase ☃)
  {
    try
    {
      this.d.b(this.g, ☃);
    }
    catch (Exception ☃)
    {
      a.error("Couldn't save entities", ☃);
    }
  }
  
  private void b(ase ☃)
  {
    try
    {
      ☃.b(this.g.P());
      this.d.a(this.g, ☃);
    }
    catch (IOException ☃)
    {
      a.error("Couldn't save chunk", ☃);
    }
    catch (ahu ☃)
    {
      a.error("Couldn't save chunk; already in use by another instance of Minecraft?", ☃);
    }
  }
  
  public boolean a(boolean ☃)
  {
    int ☃ = 0;
    List<ase> ☃ = Lists.newArrayList(this.f);
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      ase ☃ = (ase)☃.get(☃);
      if (☃) {
        a(☃);
      }
      if (☃.a(☃))
      {
        b(☃);
        ☃.f(false);
        ☃++;
        if ((☃ == 24) && (!☃)) {
          return false;
        }
      }
    }
    return true;
  }
  
  public void c()
  {
    this.d.b();
  }
  
  public boolean d()
  {
    if (!this.g.b)
    {
      for (int ☃ = 0; ☃ < 100; ☃++) {
        if (!this.b.isEmpty())
        {
          Long ☃ = (Long)this.b.iterator().next();
          
          ase ☃ = (ase)this.e.a(☃.longValue());
          if (☃ != null)
          {
            ☃.d();
            b(☃);
            a(☃);
            this.e.d(☃.longValue());
            this.f.remove(☃);
          }
          this.b.remove(☃);
        }
      }
      this.d.a();
    }
    return false;
  }
  
  public boolean e()
  {
    return !this.g.b;
  }
  
  public String f()
  {
    return "ServerChunkCache: " + this.e.a() + " Drop: " + this.b.size();
  }
  
  public List<aig.c> a(sc ☃, cj ☃)
  {
    return this.c.a(☃, ☃);
  }
  
  public cj a(aht ☃, String ☃, cj ☃)
  {
    return this.c.a(☃, ☃, ☃);
  }
  
  public int g()
  {
    return this.e.a();
  }
  
  public boolean e(int ☃, int ☃)
  {
    return this.e.b(ahn.a(☃, ☃));
  }
}
