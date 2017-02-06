import java.util.Random;

public class ajr
  extends amg
{
  public static final arp<ajr.a> a = arp.a("part", ajr.a.class);
  public static final arn b = arn.a("occupied");
  protected static final bbh c = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
  
  public ajr()
  {
    super(axe.n);
    w(this.A.b().a(a, ajr.a.b).a(b, Boolean.valueOf(false)));
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    if (☃.c(a) != ajr.a.a)
    {
      ☃ = ☃.a((cq)☃.c(D));
      ☃ = ☃.o(☃);
      if (☃.t() != this) {
        return true;
      }
    }
    if ((!☃.s.e()) || (☃.b(☃) == ail.j))
    {
      ☃.g(☃);
      
      cj ☃ = ☃.a(((cq)☃.c(D)).d());
      if (☃.o(☃).t() == this) {
        ☃.g(☃);
      }
      ☃.a(null, ☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D, 5.0F, true, true);
      return true;
    }
    if (((Boolean)☃.c(b)).booleanValue())
    {
      zj ☃ = c(☃, ☃);
      if (☃ == null)
      {
        ☃ = ☃.a(b, Boolean.valueOf(false));
        ☃.a(☃, ☃, 4);
      }
      else
      {
        ☃.b(new fb("tile.bed.occupied", new Object[0]));
        return true;
      }
    }
    zj.a ☃ = ☃.a(☃);
    if (☃ == zj.a.a)
    {
      ☃ = ☃.a(b, Boolean.valueOf(true));
      ☃.a(☃, ☃, 4);
      return true;
    }
    if (☃ == zj.a.c) {
      ☃.b(new fb("tile.bed.noSleep", new Object[0]));
    } else if (☃ == zj.a.f) {
      ☃.b(new fb("tile.bed.notSafe", new Object[0]));
    }
    return true;
  }
  
  private zj c(aht ☃, cj ☃)
  {
    for (zj ☃ : ☃.i) {
      if ((☃.cl()) && (☃.bG.equals(☃))) {
        return ☃;
      }
    }
    return null;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    cq ☃ = (cq)☃.c(D);
    if (☃.c(a) == ajr.a.a)
    {
      if (☃.o(☃.a(☃.d())).t() != this) {
        ☃.g(☃);
      }
    }
    else if (☃.o(☃.a(☃)).t() != this)
    {
      ☃.g(☃);
      if (!☃.E) {
        b(☃, ☃, ☃, 0);
      }
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (☃.c(a) == ajr.a.a) {
      return null;
    }
    return ads.bh;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return c;
  }
  
  public static cj a(aht ☃, cj ☃, int ☃)
  {
    cq ☃ = (cq)☃.o(☃).c(D);
    
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    for (int ☃ = 0; ☃ <= 1; ☃++)
    {
      int ☃ = ☃ - ☃.g() * ☃ - 1;
      int ☃ = ☃ - ☃.i() * ☃ - 1;
      int ☃ = ☃ + 2;
      int ☃ = ☃ + 2;
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          cj ☃ = new cj(☃, ☃, ☃);
          if (b(☃, ☃)) {
            if (☃ > 0) {
              ☃--;
            } else {
              return ☃;
            }
          }
        }
      }
    }
    return null;
  }
  
  protected static boolean b(aht ☃, cj ☃)
  {
    return (☃.o(☃.b()).q()) && (!☃.o(☃).a().a()) && (!☃.o(☃.a()).a().a());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    if (☃.c(a) == ajr.a.b) {
      super.a(☃, ☃, ☃, ☃, 0);
    }
  }
  
  public axh h(arc ☃)
  {
    return axh.b;
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bh);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    if ((☃.bJ.d) && 
      (☃.c(a) == ajr.a.a))
    {
      cj ☃ = ☃.a(((cq)☃.c(D)).d());
      if (☃.o(☃).t() == this) {
        ☃.g(☃);
      }
    }
  }
  
  public arc a(int ☃)
  {
    cq ☃ = cq.b(☃);
    if ((☃ & 0x8) > 0) {
      return u().a(a, ajr.a.a).a(D, ☃).a(b, Boolean.valueOf((☃ & 0x4) > 0));
    }
    return u().a(a, ajr.a.b).a(D, ☃);
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    if (☃.c(a) == ajr.a.b)
    {
      arc ☃ = ☃.o(☃.a((cq)☃.c(D)));
      if (☃.t() == this) {
        ☃ = ☃.a(b, ☃.c(b));
      }
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
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(D)).b();
    if (☃.c(a) == ajr.a.a)
    {
      ☃ |= 0x8;
      if (((Boolean)☃.c(b)).booleanValue()) {
        ☃ |= 0x4;
      }
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { D, a, b });
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
}
