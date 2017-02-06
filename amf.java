import com.google.common.base.Predicate;
import java.util.List;

public class amf
  extends ajn
{
  public static final aro a = aro.a("facing", new Predicate()
  {
    public boolean a(cq ☃)
    {
      return ☃ != cq.b;
    }
  });
  public static final arn b = arn.a("enabled");
  protected static final bbh c = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
  protected static final bbh e = new bbh(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh g = new bbh(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
  
  public amf()
  {
    super(axe.f, axf.m);
    w(this.A.b().a(a, cq.a).a(b, Boolean.valueOf(true)));
    a(acq.d);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return j;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, c);
    a(☃, ☃, ☃, g);
    a(☃, ☃, ☃, f);
    a(☃, ☃, ☃, d);
    a(☃, ☃, ☃, e);
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    cq ☃ = ☃.d();
    if (☃ == cq.b) {
      ☃ = cq.a;
    }
    return u().a(a, ☃).a(b, Boolean.valueOf(true));
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aqi();
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (☃.s())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqi)) {
        ((aqi)☃).a(☃.q());
      }
    }
  }
  
  public boolean k(arc ☃)
  {
    return true;
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    e(☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqi))
    {
      ☃.a((aqi)☃);
      ☃.b(nt.R);
    }
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    e(☃, ☃, ☃);
  }
  
  private void e(aht ☃, cj ☃, arc ☃)
  {
    boolean ☃ = !☃.y(☃);
    if (☃ != ((Boolean)☃.c(b)).booleanValue()) {
      ☃.a(☃, ☃.a(b, Boolean.valueOf(☃)), 4);
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqi))
    {
      qj.a(☃, ☃, (aqi)☃);
      
      ☃.f(☃, this);
    }
    super.b(☃, ☃, ☃);
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return true;
  }
  
  public static cq e(int ☃)
  {
    return cq.a(☃ & 0x7);
  }
  
  public static boolean f(int ☃)
  {
    return (☃ & 0x8) != 8;
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    return aau.a(☃.r(☃));
  }
  
  public ahm f()
  {
    return ahm.b;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, e(☃)).a(b, Boolean.valueOf(f(☃)));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(a)).a();
    if (!((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x8;
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
    return new ard(this, new arr[] { a, b });
  }
}
