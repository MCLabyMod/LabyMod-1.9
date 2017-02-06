import java.util.Random;

public class aqw
  extends ajn
{
  public static final aro a = aqv.H;
  public static final arp<aqv.a> b = aqv.a;
  
  public aqw()
  {
    super(axe.H);
    w(this.A.b().a(a, cq.c).a(b, aqv.a.a));
    c(-1.0F);
  }
  
  public apv a(aht ☃, int ☃)
  {
    return null;
  }
  
  public static apv a(arc ☃, cq ☃, boolean ☃, boolean ☃)
  {
    return new aqx(☃, ☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqx)) {
      ((aqx)☃).h();
    } else {
      super.b(☃, ☃, ☃);
    }
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return false;
  }
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    return false;
  }
  
  public void d(aht ☃, cj ☃, arc ☃)
  {
    cj ☃ = ☃.a(((cq)☃.c(a)).d());
    arc ☃ = ☃.o(☃);
    if (((☃.t() instanceof aqu)) && (((Boolean)☃.c(aqu.a)).booleanValue())) {
      ☃.g(☃);
    }
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if ((!☃.E) && (☃.r(☃) == null))
    {
      ☃.g(☃);
      return true;
    }
    return false;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    if (☃.E) {
      return;
    }
    aqx ☃ = c(☃, ☃);
    if (☃ == null) {
      return;
    }
    arc ☃ = ☃.b();
    ☃.t().b(☃, ☃, ☃, 0);
  }
  
  public bbi a(arc ☃, aht ☃, cj ☃, bbj ☃, bbj ☃)
  {
    return null;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!☃.E) {
      ☃.r(☃);
    }
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    aqx ☃ = c(☃, ☃);
    if (☃ == null) {
      return null;
    }
    return ☃.a(☃, ☃);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    aqx ☃ = c(☃, ☃);
    if (☃ != null) {
      return ☃.a(☃, ☃);
    }
    return j;
  }
  
  private aqx c(ahx ☃, cj ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqx)) {
      return (aqx)☃;
    }
    return null;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return null;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, aqv.e(☃)).a(b, (☃ & 0x8) > 0 ? aqv.a.b : aqv.a.a);
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(a)));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(a)).a();
    if (☃.c(b) == aqv.a.b) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b });
  }
}
