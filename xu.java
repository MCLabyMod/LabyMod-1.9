import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class xu
  extends xr
{
  public xu.a c;
  
  public static enum a
  {
    public static final int A = "SkullAndRoses".length();
    public final String B;
    public final int C;
    public final int D;
    public final int E;
    public final int F;
    
    private a(String ☃, int ☃, int ☃, int ☃, int ☃)
    {
      this.B = ☃;
      this.C = ☃;
      this.D = ☃;
      this.E = ☃;
      this.F = ☃;
    }
  }
  
  public xu(aht ☃)
  {
    super(☃);
  }
  
  public xu(aht ☃, cj ☃, cq ☃)
  {
    super(☃, ☃);
    
    List<xu.a> ☃ = Lists.newArrayList();
    for (xu.a ☃ : xu.a.values())
    {
      this.c = ☃;
      a(☃);
      if (k()) {
        ☃.add(☃);
      }
    }
    if (!☃.isEmpty()) {
      this.c = ((xu.a)☃.get(this.S.nextInt(☃.size())));
    }
    a(☃);
  }
  
  public xu(aht ☃, cj ☃, cq ☃, String ☃)
  {
    this(☃, ☃, ☃);
    for (xu.a ☃ : xu.a.values()) {
      if (☃.B.equals(☃))
      {
        this.c = ☃;
        break;
      }
    }
    a(☃);
  }
  
  public void b(dn ☃)
  {
    ☃.a("Motive", this.c.B);
    super.b(☃);
  }
  
  public void a(dn ☃)
  {
    String ☃ = ☃.l("Motive");
    for (xu.a ☃ : xu.a.values()) {
      if (☃.B.equals(☃)) {
        this.c = ☃;
      }
    }
    if (this.c == null) {
      this.c = xu.a.a;
    }
    super.a(☃);
  }
  
  public int l()
  {
    return this.c.C;
  }
  
  public int n()
  {
    return this.c.D;
  }
  
  public void a(rr ☃)
  {
    if (!this.l.U().b("doEntityDrops")) {
      return;
    }
    a(ng.dN, 1.0F, 1.0F);
    if ((☃ instanceof zj))
    {
      zj ☃ = (zj)☃;
      if (☃.bJ.d) {
        return;
      }
    }
    a(new adq(ads.ap), 0.0F);
  }
  
  public void o()
  {
    a(ng.dO, 1.0F, 1.0F);
  }
  
  public void b(double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    b(☃, ☃, ☃);
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    cj ☃ = this.a.a(☃ - this.p, ☃ - this.q, ☃ - this.r);
    b(☃.p(), ☃.q(), ☃.r());
  }
}
