import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aqu
  extends aks
{
  public static final arn a = arn.a("extended");
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
  protected static final bbh c = new bbh(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
  protected static final bbh e = new bbh(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
  protected static final bbh g = new bbh(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
  private final boolean B;
  
  public aqu(boolean ☃)
  {
    super(axe.H);
    w(this.A.b().a(H, cq.c).a(a, Boolean.valueOf(false)));
    this.B = ☃;
    a(aop.d);
    c(0.5F);
    a(acq.d);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    if (((Boolean)☃.c(a)).booleanValue())
    {
      switch (aqu.1.a[((cq)☃.c(H)).ordinal()])
      {
      case 1: 
        return g;
      case 2: 
      default: 
        return f;
      case 3: 
        return e;
      case 4: 
        return d;
      case 5: 
        return c;
      }
      return b;
    }
    return j;
  }
  
  public boolean k(arc ☃)
  {
    return (!((Boolean)☃.c(a)).booleanValue()) || (☃.c(H) == cq.a);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, ☃.c(☃, ☃));
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    ☃.a(☃, ☃.a(H, a(☃, ☃)), 2);
    if (!☃.E) {
      e(☃, ☃, ☃);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!☃.E) {
      e(☃, ☃, ☃);
    }
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    if ((!☃.E) && (☃.r(☃) == null)) {
      e(☃, ☃, ☃);
    }
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(H, a(☃, ☃)).a(a, Boolean.valueOf(false));
  }
  
  private void e(aht ☃, cj ☃, arc ☃)
  {
    cq ☃ = (cq)☃.c(H);
    
    boolean ☃ = a(☃, ☃, ☃);
    if ((☃) && (!((Boolean)☃.c(a)).booleanValue()))
    {
      if (new aqy(☃, ☃, ☃, true).a()) {
        ☃.c(☃, this, 0, ☃.a());
      }
    }
    else if ((!☃) && (((Boolean)☃.c(a)).booleanValue())) {
      ☃.c(☃, this, 1, ☃.a());
    }
  }
  
  private boolean a(aht ☃, cj ☃, cq ☃)
  {
    for (cq ☃ : ) {
      if ((☃ != ☃) && (☃.b(☃.a(☃), ☃))) {
        return true;
      }
    }
    if (☃.b(☃, cq.a)) {
      return true;
    }
    cj ☃ = ☃.a();
    for (cq ☃ : cq.values()) {
      if ((☃ != cq.a) && (☃.b(☃.a(☃), ☃))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, int ☃, int ☃)
  {
    cq ☃ = (cq)☃.c(H);
    if (!☃.E)
    {
      boolean ☃ = a(☃, ☃, ☃);
      if ((☃) && (☃ == 1))
      {
        ☃.a(☃, ☃.a(a, Boolean.valueOf(true)), 2);
        return false;
      }
      if ((!☃) && (☃ == 0)) {
        return false;
      }
    }
    if (☃ == 0)
    {
      if (a(☃, ☃, ☃, true))
      {
        ☃.a(☃, ☃.a(a, Boolean.valueOf(true)), 2);
        ☃.a(null, ☃, ng.dV, nh.e, 0.5F, ☃.r.nextFloat() * 0.25F + 0.6F);
      }
      else
      {
        return false;
      }
    }
    else if (☃ == 1)
    {
      apv ☃ = ☃.r(☃.a(☃));
      if ((☃ instanceof aqx)) {
        ((aqx)☃).h();
      }
      ☃.a(☃, aju.M.u().a(aqw.a, ☃).a(aqw.b, this.B ? aqv.a.b : aqv.a.a), 3);
      ☃.a(☃, aqw.a(a(☃), ☃, false, true));
      if (this.B)
      {
        cj ☃ = ☃.a(☃.g() * 2, ☃.h() * 2, ☃.i() * 2);
        arc ☃ = ☃.o(☃);
        ajt ☃ = ☃.t();
        boolean ☃ = false;
        if (☃ == aju.M)
        {
          apv ☃ = ☃.r(☃);
          if ((☃ instanceof aqx))
          {
            aqx ☃ = (aqx)☃;
            if ((☃.e() == ☃) && (☃.d()))
            {
              ☃.h();
              ☃ = true;
            }
          }
        }
        if ((!☃) && (☃.a() != axe.a) && (a(☃, ☃, ☃, ☃.d(), false)) && ((☃.o() == axh.a) || (☃ == aju.J) || (☃ == aju.F))) {
          a(☃, ☃, ☃, false);
        }
      }
      else
      {
        ☃.g(☃.a(☃));
      }
      ☃.a(null, ☃, ng.dU, nh.e, 0.5F, ☃.r.nextFloat() * 0.15F + 0.6F);
    }
    return true;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public static cq e(int ☃)
  {
    int ☃ = ☃ & 0x7;
    if (☃ > 5) {
      return null;
    }
    return cq.a(☃);
  }
  
  public static cq a(cj ☃, sa ☃)
  {
    if ((on.e((float)☃.p - ☃.p()) < 2.0F) && (on.e((float)☃.r - ☃.r()) < 2.0F))
    {
      double ☃ = ☃.q + ☃.bn();
      if (☃ - ☃.q() > 2.0D) {
        return cq.b;
      }
      if (☃.q() - ☃ > 0.0D) {
        return cq.a;
      }
    }
    return ☃.bi().d();
  }
  
  public static boolean a(arc ☃, aht ☃, cj ☃, cq ☃, boolean ☃)
  {
    ajt ☃ = ☃.t();
    if (☃ == aju.Z) {
      return false;
    }
    if (!☃.aj().a(☃)) {
      return false;
    }
    if ((☃.q() < 0) || ((☃ == cq.a) && (☃.q() == 0))) {
      return false;
    }
    if ((☃.q() > ☃.Y() - 1) || ((☃ == cq.b) && (☃.q() == ☃.Y() - 1))) {
      return false;
    }
    if ((☃ == aju.J) || (☃ == aju.F))
    {
      if (((Boolean)☃.c(a)).booleanValue()) {
        return false;
      }
    }
    else
    {
      if (☃.b(☃, ☃) == -1.0F) {
        return false;
      }
      if (☃.o() == axh.c) {
        return false;
      }
      if (☃.o() == axh.b) {
        return ☃;
      }
    }
    if (☃.m()) {
      return false;
    }
    return true;
  }
  
  private boolean a(aht ☃, cj ☃, cq ☃, boolean ☃)
  {
    if (!☃) {
      ☃.g(☃.a(☃));
    }
    aqy ☃ = new aqy(☃, ☃, ☃, ☃);
    if (!☃.a()) {
      return false;
    }
    List<cj> ☃ = ☃.c();
    List<arc> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      cj ☃ = (cj)☃.get(☃);
      ☃.add(☃.o(☃).b(☃, ☃));
    }
    List<cj> ☃ = ☃.d();
    
    int ☃ = ☃.size() + ☃.size();
    arc[] ☃ = new arc[☃];
    cq ☃ = ☃ ? ☃ : ☃.d();
    for (int ☃ = ☃.size() - 1; ☃ >= 0; ☃--)
    {
      cj ☃ = (cj)☃.get(☃);
      
      arc ☃ = ☃.o(☃);
      
      ☃.t().b(☃, ☃, ☃, 0);
      ☃.g(☃);
      
      ☃[(--☃)] = ☃;
    }
    for (int ☃ = ☃.size() - 1; ☃ >= 0; ☃--)
    {
      cj ☃ = (cj)☃.get(☃);
      arc ☃ = ☃.o(☃);
      
      ☃.a(☃, aju.a.u(), 4);
      
      ☃ = ☃.a(☃);
      
      ☃.a(☃, aju.M.u().a(H, ☃), 4);
      ☃.a(☃, aqw.a((arc)☃.get(☃), ☃, ☃, false));
      
      ☃[(--☃)] = ☃;
    }
    cj ☃ = ☃.a(☃);
    if (☃)
    {
      aqv.a ☃ = this.B ? aqv.a.b : aqv.a.a;
      arc ☃ = aju.K.u().a(aqv.H, ☃).a(aqv.a, ☃);
      
      arc ☃ = aju.M.u().a(aqw.a, ☃).a(aqw.b, this.B ? aqv.a.b : aqv.a.a);
      
      ☃.a(☃, ☃, 4);
      ☃.a(☃, aqw.a(☃, ☃, true, false));
    }
    for (int ☃ = ☃.size() - 1; ☃ >= 0; ☃--) {
      ☃.d((cj)☃.get(☃), ☃[(☃++)].t());
    }
    for (int ☃ = ☃.size() - 1; ☃ >= 0; ☃--) {
      ☃.d((cj)☃.get(☃), ☃[(☃++)].t());
    }
    if (☃)
    {
      ☃.d(☃, aju.K);
      ☃.d(☃, this);
    }
    return true;
  }
  
  public arc a(int ☃)
  {
    return u().a(H, e(☃)).a(a, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(H)).a();
    if (((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(H, ☃.a((cq)☃.c(H)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(H)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { H, a });
  }
}
