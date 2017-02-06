import com.google.common.collect.Maps;
import java.util.Map;

public class bpm
{
  private Map<Class<? extends apv>, bpn<? extends apv>> m = Maps.newHashMap();
  public static bpm a = new bpm();
  private bct n;
  public static double b;
  public static double c;
  public static double d;
  public bvi e;
  public aht f;
  public rr g;
  public float h;
  public float i;
  public double j;
  public double k;
  public double l;
  
  private bpm()
  {
    this.m.put(aqn.class, new bpt());
    this.m.put(aqk.class, new bpr());
    this.m.put(aqx.class, new bps());
    this.m.put(apx.class, new bpo());
    this.m.put(aqe.class, new bpq());
    this.m.put(aqd.class, new bpp());
    this.m.put(aqr.class, new bpx());
    this.m.put(aqq.class, new bpw());
    this.m.put(apu.class, new bpl());
    this.m.put(aqo.class, new bpu());
    this.m.put(apt.class, new bpk());
    this.m.put(aqp.class, new bpv());
    for (bpn<?> ☃ : this.m.values()) {
      ☃.a(this);
    }
  }
  
  public <T extends apv> bpn<T> a(Class<? extends apv> ☃)
  {
    bpn<? extends apv> ☃ = (bpn)this.m.get(☃);
    if ((☃ == null) && (☃ != apv.class))
    {
      ☃ = a(☃.getSuperclass());
      this.m.put(☃, ☃);
    }
    return ☃;
  }
  
  public <T extends apv> bpn<T> a(apv ☃)
  {
    if (☃ == null) {
      return null;
    }
    return a(☃.getClass());
  }
  
  public void a(aht ☃, bvi ☃, bct ☃, rr ☃, float ☃)
  {
    if (this.f != ☃) {
      a(☃);
    }
    this.e = ☃;
    this.g = ☃;
    this.n = ☃;
    
    this.h = (☃.x + (☃.v - ☃.x) * ☃);
    this.i = (☃.y + (☃.w - ☃.y) * ☃);
    
    this.j = (☃.M + (☃.p - ☃.M) * ☃);
    this.k = (☃.N + (☃.q - ☃.N) * ☃);
    this.l = (☃.O + (☃.r - ☃.O) * ☃);
  }
  
  public void a(apv ☃, float ☃, int ☃)
  {
    if (☃.a(this.j, this.k, this.l) < ☃.s())
    {
      int ☃ = this.f.b(☃.v(), 0);
      int ☃ = ☃ % 65536;
      int ☃ = ☃ / 65536;
      bzg.a(bzg.r, ☃, ☃);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      cj ☃ = ☃.v();
      a(☃, ☃.p() - b, ☃.q() - c, ☃.r() - d, ☃, ☃);
    }
  }
  
  public void a(apv ☃, double ☃, double ☃, double ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, -1);
  }
  
  public void a(apv ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    bpn<apv> ☃ = a(☃);
    if (☃ != null) {
      try
      {
        ☃.a(☃, ☃, ☃, ☃, ☃, ☃);
      }
      catch (Throwable ☃)
      {
        b ☃ = b.a(☃, "Rendering Block Entity");
        c ☃ = ☃.a("Block Entity Details");
        
        ☃.a(☃);
        
        throw new e(☃);
      }
    }
  }
  
  public void a(aht ☃)
  {
    this.f = ☃;
    if (☃ == null) {
      this.g = null;
    }
  }
  
  public bct a()
  {
    return this.n;
  }
}
