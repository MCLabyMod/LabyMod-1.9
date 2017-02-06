import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public abstract class awd
  extends atk
{
  private awf a;
  protected Map<Long, awh> c = Maps.newHashMap();
  
  public abstract String a();
  
  protected final synchronized void a(aht ☃, final int ☃, final int ☃, int ☃, int ☃, atf ☃)
  {
    a(☃);
    if (this.c.containsKey(Long.valueOf(ahn.a(☃, ☃)))) {
      return;
    }
    this.f.nextInt();
    try
    {
      if (a(☃, ☃))
      {
        awh ☃ = b(☃, ☃);
        this.c.put(Long.valueOf(ahn.a(☃, ☃)), ☃);
        if (☃.a()) {
          a(☃, ☃, ☃);
        }
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Exception preparing structure feature");
      c ☃ = ☃.a("Feature being prepared");
      
      ☃.a("Is feature chunk", new Callable()
      {
        public String a()
          throws Exception
        {
          return awd.this.a(☃, ☃) ? "True" : "False";
        }
      });
      ☃.a("Chunk location", String.format("%d,%d", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃) }));
      
      ☃.a("Chunk pos hash", new Callable()
      {
        public String a()
          throws Exception
        {
          return String.valueOf(ahn.a(☃, ☃));
        }
      });
      ☃.a("Structure type", new Callable()
      {
        public String a()
          throws Exception
        {
          return awd.this.getClass().getCanonicalName();
        }
      });
      throw new e(☃);
    }
  }
  
  public synchronized boolean a(aht ☃, Random ☃, ahn ☃)
  {
    a(☃);
    
    int ☃ = (☃.a << 4) + 8;
    int ☃ = (☃.b << 4) + 8;
    
    boolean ☃ = false;
    for (awh ☃ : this.c.values()) {
      if ((☃.a()) && (☃.a(☃)) && 
        (☃.b().a(☃, ☃, ☃ + 15, ☃ + 15)))
      {
        ☃.a(☃, ☃, new avp(☃, ☃, ☃ + 15, ☃ + 15));
        ☃.b(☃);
        ☃ = true;
        
        a(☃.e(), ☃.f(), ☃);
      }
    }
    return ☃;
  }
  
  public boolean b(cj ☃)
  {
    a(this.g);
    return c(☃) != null;
  }
  
  protected awh c(cj ☃)
  {
    for (awh ☃ : this.c.values()) {
      if ((☃.a()) && 
        (☃.b().b(☃)))
      {
        Iterator<awg> ☃ = ☃.c().iterator();
        while (☃.hasNext())
        {
          awg ☃ = (awg)☃.next();
          if (☃.c().b(☃)) {
            return ☃;
          }
        }
      }
    }
    return null;
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    a(☃);
    for (awh ☃ : this.c.values()) {
      if ((☃.a()) && 
        (☃.b().b(☃))) {
        return true;
      }
    }
    return false;
  }
  
  public cj a(aht ☃, cj ☃)
  {
    this.g = ☃;
    
    a(☃);
    
    this.f.setSeed(☃.O());
    long ☃ = this.f.nextLong();
    long ☃ = this.f.nextLong();
    long ☃ = (☃.p() >> 4) * ☃;
    long ☃ = (☃.r() >> 4) * ☃;
    this.f.setSeed(☃ ^ ☃ ^ ☃.O());
    
    a(☃, ☃.p() >> 4, ☃.r() >> 4, 0, 0, null);
    
    double ☃ = Double.MAX_VALUE;
    cj ☃ = null;
    for (awh ☃ : this.c.values()) {
      if (☃.a())
      {
        awg ☃ = (awg)☃.c().get(0);
        cj ☃ = ☃.a();
        
        double ☃ = ☃.k(☃);
        if (☃ < ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    if (☃ != null) {
      return ☃;
    }
    List<cj> ☃ = E_();
    if (☃ != null)
    {
      cj ☃ = null;
      for (cj ☃ : ☃)
      {
        double ☃ = ☃.k(☃);
        if (☃ < ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
      return ☃;
    }
    return null;
  }
  
  protected List<cj> E_()
  {
    return null;
  }
  
  protected void a(aht ☃)
  {
    dn ☃;
    if (this.a == null)
    {
      this.a = ((awf)☃.a(awf.class, a()));
      if (this.a == null)
      {
        this.a = new awf(a());
        ☃.a(a(), this.a);
      }
      else
      {
        ☃ = this.a.a();
        for (String ☃ : ☃.c())
        {
          eb ☃ = ☃.c(☃);
          if (☃.a() == 10)
          {
            dn ☃ = (dn)☃;
            if ((☃.e("ChunkX")) && (☃.e("ChunkZ")))
            {
              int ☃ = ☃.h("ChunkX");
              int ☃ = ☃.h("ChunkZ");
              
              awh ☃ = awe.a(☃, ☃);
              if (☃ != null) {
                this.c.put(Long.valueOf(ahn.a(☃, ☃)), ☃);
              }
            }
          }
        }
      }
    }
  }
  
  private void a(int ☃, int ☃, awh ☃)
  {
    this.a.a(☃.a(☃, ☃), ☃, ☃);
    this.a.c();
  }
  
  protected abstract boolean a(int paramInt1, int paramInt2);
  
  protected abstract awh b(int paramInt1, int paramInt2);
}
