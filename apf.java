import com.google.common.base.Predicate;
import java.util.Random;

public class apf
  extends ajt
{
  public static final aro a = aro.a("facing", new Predicate()
  {
    public boolean a(cq ☃)
    {
      return ☃ != cq.a;
    }
  });
  protected static final bbh b = new bbh(0.4000000059604645D, 0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
  protected static final bbh c = new bbh(0.3499999940395355D, 0.20000000298023224D, 0.699999988079071D, 0.6499999761581421D, 0.800000011920929D, 1.0D);
  protected static final bbh d = new bbh(0.3499999940395355D, 0.20000000298023224D, 0.0D, 0.6499999761581421D, 0.800000011920929D, 0.30000001192092896D);
  protected static final bbh e = new bbh(0.699999988079071D, 0.20000000298023224D, 0.3499999940395355D, 1.0D, 0.800000011920929D, 0.6499999761581421D);
  protected static final bbh f = new bbh(0.0D, 0.20000000298023224D, 0.3499999940395355D, 0.30000001192092896D, 0.800000011920929D, 0.6499999761581421D);
  
  protected apf()
  {
    super(axe.q);
    w(this.A.b().a(a, cq.b));
    a(true);
    a(acq.c);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (apf.2.a[((cq)☃.c(a)).ordinal()])
    {
    case 1: 
      return f;
    case 2: 
      return e;
    case 3: 
      return d;
    case 4: 
      return c;
    }
    return b;
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
  
  private boolean b(aht ☃, cj ☃)
  {
    if (☃.o(☃).q()) {
      return true;
    }
    ajt ☃ = ☃.o(☃).t();
    return ((☃ instanceof alj)) || (☃ == aju.w) || (☃ == aju.bZ) || (☃ == aju.cG);
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    for (cq ☃ : a.c()) {
      if (a(☃, ☃, ☃)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean a(aht ☃, cj ☃, cq ☃)
  {
    cj ☃ = ☃.a(☃.d());
    
    boolean ☃ = ☃.k().c();
    return ((☃) && (☃.d(☃, true))) || ((☃.equals(cq.b)) && (b(☃, ☃)));
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    if (a(☃, ☃, ☃)) {
      return u().a(a, ☃);
    }
    for (cq ☃ : cq.c.a) {
      if (☃.d(☃.a(☃.d()), true)) {
        return u().a(a, ☃);
      }
    }
    return u();
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    f(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    e(☃, ☃, ☃);
  }
  
  protected boolean e(aht ☃, cj ☃, arc ☃)
  {
    if (!f(☃, ☃, ☃)) {
      return true;
    }
    cq ☃ = (cq)☃.c(a);
    cq.a ☃ = ☃.k();
    cq ☃ = ☃.d();
    
    boolean ☃ = false;
    if ((☃.c()) && (!☃.d(☃.a(☃), true))) {
      ☃ = true;
    } else if ((☃.b()) && (!b(☃, ☃.a(☃)))) {
      ☃ = true;
    }
    if (☃)
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
      return true;
    }
    return false;
  }
  
  protected boolean f(aht ☃, cj ☃, arc ☃)
  {
    if ((☃.t() == this) && 
      (a(☃, ☃, (cq)☃.c(a)))) {
      return true;
    }
    if (☃.o(☃).t() == this)
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
    return false;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    cq ☃ = (cq)☃.c(a);
    double ☃ = ☃.p() + 0.5D;
    double ☃ = ☃.q() + 0.7D;
    double ☃ = ☃.r() + 0.5D;
    double ☃ = 0.22D;
    double ☃ = 0.27D;
    if (☃.k().c())
    {
      cq ☃ = ☃.d();
      ☃.a(cy.l, ☃ + ☃ * ☃.g(), ☃ + ☃, ☃ + ☃ * ☃.i(), 0.0D, 0.0D, 0.0D, new int[0]);
      ☃.a(cy.A, ☃ + ☃ * ☃.g(), ☃ + ☃, ☃ + ☃ * ☃.i(), 0.0D, 0.0D, 0.0D, new int[0]);
    }
    else
    {
      ☃.a(cy.l, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      ☃.a(cy.A, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u();
    switch (☃)
    {
    case 1: 
      ☃ = ☃.a(a, cq.f);
      break;
    case 2: 
      ☃ = ☃.a(a, cq.e);
      break;
    case 3: 
      ☃ = ☃.a(a, cq.d);
      break;
    case 4: 
      ☃ = ☃.a(a, cq.c);
      break;
    case 5: 
    default: 
      ☃ = ☃.a(a, cq.b);
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    switch (apf.2.a[((cq)☃.c(a)).ordinal()])
    {
    case 1: 
      ☃ |= 0x1;
      break;
    case 2: 
      ☃ |= 0x2;
      break;
    case 3: 
      ☃ |= 0x3;
      break;
    case 4: 
      ☃ |= 0x4;
      break;
    case 5: 
    case 6: 
    default: 
      ☃ |= 0x5;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(a)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
