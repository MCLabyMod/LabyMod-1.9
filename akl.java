import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

public class akl
  extends akr
  implements alg
{
  public static final arn a = arn.a("powered");
  public static final arp<akl.a> b = arp.a("mode", akl.a.class);
  
  public akl(boolean ☃)
  {
    super(☃);
    w(this.A.b().a(D, cq.c).a(a, Boolean.valueOf(false)).a(b, akl.a.a));
    this.u = true;
  }
  
  public String c()
  {
    return di.a("item.comparator.name");
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.co;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.co);
  }
  
  protected int i(arc ☃)
  {
    return 2;
  }
  
  protected arc x(arc ☃)
  {
    Boolean ☃ = (Boolean)☃.c(a);
    akl.a ☃ = (akl.a)☃.c(b);
    cq ☃ = (cq)☃.c(D);
    
    return aju.ck.u().a(D, ☃).a(a, ☃).a(b, ☃);
  }
  
  protected arc y(arc ☃)
  {
    Boolean ☃ = (Boolean)☃.c(a);
    akl.a ☃ = (akl.a)☃.c(b);
    cq ☃ = (cq)☃.c(D);
    
    return aju.cj.u().a(D, ☃).a(a, ☃).a(b, ☃);
  }
  
  protected boolean z(arc ☃)
  {
    return (this.d) || (((Boolean)☃.c(a)).booleanValue());
  }
  
  protected int a(ahx ☃, cj ☃, arc ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apz)) {
      return ((apz)☃).b();
    }
    return 0;
  }
  
  private int j(aht ☃, cj ☃, arc ☃)
  {
    if (☃.c(b) == akl.a.b) {
      return Math.max(f(☃, ☃, ☃) - c(☃, ☃, ☃), 0);
    }
    return f(☃, ☃, ☃);
  }
  
  protected boolean e(aht ☃, cj ☃, arc ☃)
  {
    int ☃ = f(☃, ☃, ☃);
    if (☃ >= 15) {
      return true;
    }
    if (☃ == 0) {
      return false;
    }
    int ☃ = c(☃, ☃, ☃);
    if (☃ == 0) {
      return true;
    }
    return ☃ >= ☃;
  }
  
  protected int f(aht ☃, cj ☃, arc ☃)
  {
    int ☃ = super.f(☃, ☃, ☃);
    
    cq ☃ = (cq)☃.c(D);
    cj ☃ = ☃.a(☃);
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (☃.n())
    {
      ☃ = ☃.a(☃, ☃);
    }
    else if ((☃ < 15) && (☃.l()))
    {
      ☃ = ☃.a(☃);
      ☃ = ☃.o(☃);
      if (☃.n())
      {
        ☃ = ☃.a(☃, ☃);
      }
      else if (☃.a() == axe.a)
      {
        xs ☃ = a(☃, ☃, ☃);
        if (☃ != null) {
          ☃ = ☃.t();
        }
      }
    }
    return ☃;
  }
  
  private xs a(aht ☃, final cq ☃, cj ☃)
  {
    List<xs> ☃ = ☃.a(xs.class, new bbh(☃.p(), ☃.q(), ☃.r(), ☃.p() + 1, ☃.q() + 1, ☃.r() + 1), new Predicate()
    {
      public boolean a(rr ☃)
      {
        return (☃ != null) && (☃.bi() == ☃);
      }
    });
    if (☃.size() == 1) {
      return (xs)☃.get(0);
    }
    return null;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.bJ.e) {
      return false;
    }
    ☃ = ☃.a(b);
    float ☃ = ☃.c(b) == akl.a.b ? 0.55F : 0.5F;
    ☃.a(☃, ☃, ng.al, nh.e, 0.3F, ☃);
    
    ☃.a(☃, ☃, 2);
    k(☃, ☃, ☃);
    return true;
  }
  
  protected void g(aht ☃, cj ☃, arc ☃)
  {
    if (☃.a(☃, this)) {
      return;
    }
    int ☃ = j(☃, ☃, ☃);
    apv ☃ = ☃.r(☃);
    int ☃ = (☃ instanceof apz) ? ((apz)☃).b() : 0;
    if ((☃ != ☃) || (z(☃) != e(☃, ☃, ☃))) {
      if (i(☃, ☃, ☃)) {
        ☃.a(☃, this, 2, -1);
      } else {
        ☃.a(☃, this, 2, 0);
      }
    }
  }
  
  private void k(aht ☃, cj ☃, arc ☃)
  {
    int ☃ = j(☃, ☃, ☃);
    
    apv ☃ = ☃.r(☃);
    int ☃ = 0;
    if ((☃ instanceof apz))
    {
      apz ☃ = (apz)☃;
      
      ☃ = ☃.b();
      ☃.a(☃);
    }
    if ((☃ != ☃) || (☃.c(b) == akl.a.a))
    {
      boolean ☃ = e(☃, ☃, ☃);
      boolean ☃ = z(☃);
      if ((☃) && (!☃)) {
        ☃.a(☃, ☃.a(a, Boolean.valueOf(false)), 2);
      } else if ((!☃) && (☃)) {
        ☃.a(☃, ☃.a(a, Boolean.valueOf(true)), 2);
      }
      h(☃, ☃, ☃);
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (this.d) {
      ☃.a(☃, y(☃).a(a, Boolean.valueOf(true)), 4);
    }
    k(☃, ☃, ☃);
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    super.c(☃, ☃, ☃);
    ☃.a(☃, a(☃, 0));
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    super.b(☃, ☃, ☃);
    ☃.s(☃);
    
    h(☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    
    apv ☃ = ☃.r(☃);
    if (☃ == null) {
      return false;
    }
    return ☃.c(☃, ☃);
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new apz();
  }
  
  public arc a(int ☃)
  {
    return u().a(D, cq.b(☃)).a(a, Boolean.valueOf((☃ & 0x8) > 0)).a(b, (☃ & 0x4) > 0 ? akl.a.b : akl.a.a);
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(D)).b();
    if (((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 0x8;
    }
    if (☃.c(b) == akl.a.b) {
      ☃ |= 0x4;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(D, ☃.a((cq)☃.c(D)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(D)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { D, b, a });
  }
  
  public static enum a
    implements or
  {
    private final String c;
    
    private a(String ☃)
    {
      this.c = ☃;
    }
    
    public String toString()
    {
      return this.c;
    }
    
    public String m()
    {
      return this.c;
    }
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(D, ☃.bi().d()).a(a, Boolean.valueOf(false)).a(b, akl.a.a);
  }
}
