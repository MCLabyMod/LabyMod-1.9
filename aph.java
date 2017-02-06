import java.util.List;
import java.util.Random;

public class aph
  extends ajt
{
  public static final arn a = arn.a("powered");
  public static final arn b = arn.a("attached");
  public static final arn c = arn.a("disarmed");
  public static final arn d = arn.a("north");
  public static final arn e = arn.a("east");
  public static final arn f = arn.a("south");
  public static final arn g = arn.a("west");
  protected static final bbh B = new bbh(0.0D, 0.0625D, 0.0D, 1.0D, 0.15625D, 1.0D);
  protected static final bbh C = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
  
  public aph()
  {
    super(axe.q);
    w(this.A.b().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false)).a(f, Boolean.valueOf(false)).a(g, Boolean.valueOf(false)));
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    if (!((Boolean)☃.c(b)).booleanValue()) {
      return C;
    }
    return B;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.a(d, Boolean.valueOf(a(☃, ☃, ☃, cq.c))).a(e, Boolean.valueOf(a(☃, ☃, ☃, cq.f))).a(f, Boolean.valueOf(a(☃, ☃, ☃, cq.d))).a(g, Boolean.valueOf(a(☃, ☃, ☃, cq.e)));
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public ahm f()
  {
    return ahm.d;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.H;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.H);
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    ☃.a(☃, ☃, 3);
    e(☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    e(☃, ☃, ☃.a(a, Boolean.valueOf(true)));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    if (☃.E) {
      return;
    }
    if ((☃.cb() != null) && (☃.cb().b() == ads.bl)) {
      ☃.a(☃, ☃.a(c, Boolean.valueOf(true)), 4);
    }
  }
  
  private void e(aht ☃, cj ☃, arc ☃)
  {
    for (cq ☃ : new cq[] { cq.d, cq.e }) {
      for (int ☃ = 1; ☃ < 42; ☃++)
      {
        cj ☃ = ☃.a(☃, ☃);
        arc ☃ = ☃.o(☃);
        if (☃.t() == aju.bR)
        {
          if (☃.c(api.a) == ☃.d()) {
            aju.bR.a(☃, ☃, ☃, false, true, ☃, ☃);
          }
        }
        else {
          if (☃.t() != aju.bS) {
            break;
          }
        }
      }
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    if (☃.E) {
      return;
    }
    if (((Boolean)☃.c(a)).booleanValue()) {
      return;
    }
    b(☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    if (!((Boolean)☃.o(☃).c(a)).booleanValue()) {
      return;
    }
    b(☃, ☃);
  }
  
  private void b(aht ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    boolean ☃ = ((Boolean)☃.c(a)).booleanValue();
    boolean ☃ = false;
    
    List<? extends rr> ☃ = ☃.b(null, ☃.c(☃, ☃).a(☃));
    if (!☃.isEmpty()) {
      for (rr ☃ : ☃) {
        if (!☃.ba())
        {
          ☃ = true;
          break;
        }
      }
    }
    if (☃ != ☃)
    {
      ☃ = ☃.a(a, Boolean.valueOf(☃));
      ☃.a(☃, ☃, 3);
      e(☃, ☃, ☃);
    }
    if (☃) {
      ☃.a(new cj(☃), this, a(☃));
    }
  }
  
  public static boolean a(ahx ☃, cj ☃, arc ☃, cq ☃)
  {
    cj ☃ = ☃.a(☃);
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (☃ == aju.bR)
    {
      cq ☃ = ☃.d();
      return ☃.c(api.a) == ☃;
    }
    if (☃ == aju.bS) {
      return true;
    }
    return false;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Boolean.valueOf((☃ & 0x1) > 0)).a(b, Boolean.valueOf((☃ & 0x4) > 0)).a(c, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    if (((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 0x1;
    }
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x4;
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (aph.1.a[☃.ordinal()])
    {
    case 1: 
      return ☃.a(d, ☃.c(f)).a(e, ☃.c(g)).a(f, ☃.c(d)).a(g, ☃.c(e));
    case 2: 
      return ☃.a(d, ☃.c(e)).a(e, ☃.c(f)).a(f, ☃.c(g)).a(g, ☃.c(d));
    case 3: 
      return ☃.a(d, ☃.c(g)).a(e, ☃.c(d)).a(f, ☃.c(e)).a(g, ☃.c(f));
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    switch (aph.1.b[☃.ordinal()])
    {
    case 1: 
      return ☃.a(d, ☃.c(f)).a(f, ☃.c(d));
    case 2: 
      return ☃.a(e, ☃.c(g)).a(g, ☃.c(e));
    }
    return super.a(☃, ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, c, d, e, g, f });
  }
}
