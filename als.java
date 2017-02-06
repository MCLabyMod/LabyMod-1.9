import java.util.Random;

public class als
  extends ajn
{
  public static final aro a = amg.D;
  private final boolean b;
  private static boolean c;
  
  protected als(boolean ☃)
  {
    super(axe.e);
    w(this.A.b().a(a, cq.c));
    this.b = ☃;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.al);
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    e(☃, ☃, ☃);
  }
  
  private void e(aht ☃, cj ☃, arc ☃)
  {
    if (☃.E) {
      return;
    }
    arc ☃ = ☃.o(☃.c());
    arc ☃ = ☃.o(☃.d());
    arc ☃ = ☃.o(☃.e());
    arc ☃ = ☃.o(☃.f());
    
    cq ☃ = (cq)☃.c(a);
    if ((☃ == cq.c) && (☃.b()) && (!☃.b())) {
      ☃ = cq.d;
    } else if ((☃ == cq.d) && (☃.b()) && (!☃.b())) {
      ☃ = cq.c;
    } else if ((☃ == cq.e) && (☃.b()) && (!☃.b())) {
      ☃ = cq.f;
    } else if ((☃ == cq.f) && (☃.b()) && (!☃.b())) {
      ☃ = cq.e;
    }
    ☃.a(☃, ☃.a(a, ☃), 2);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (!this.b) {
      return;
    }
    cq ☃ = (cq)☃.c(a);
    
    double ☃ = ☃.p() + 0.5D;
    double ☃ = ☃.q() + ☃.nextDouble() * 6.0D / 16.0D;
    double ☃ = ☃.r() + 0.5D;
    double ☃ = 0.52D;
    double ☃ = ☃.nextDouble() * 0.6D - 0.3D;
    if (☃.nextDouble() < 0.1D) {
      ☃.a(☃.p() + 0.5D, ☃.q(), ☃.r() + 0.5D, ng.bx, nh.e, 1.0F, 1.0F, false);
    }
    switch (als.1.a[☃.ordinal()])
    {
    case 1: 
      ☃.a(cy.l, ☃ - ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      ☃.a(cy.A, ☃ - ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      break;
    case 2: 
      ☃.a(cy.l, ☃ + ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      ☃.a(cy.A, ☃ + ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      break;
    case 3: 
      ☃.a(cy.l, ☃ + ☃, ☃, ☃ - ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      ☃.a(cy.A, ☃ + ☃, ☃, ☃ - ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      break;
    case 4: 
      ☃.a(cy.l, ☃ + ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      ☃.a(cy.A, ☃ + ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqg))
    {
      ☃.a((aqg)☃);
      ☃.b(nt.aa);
    }
    return true;
  }
  
  public static void a(boolean ☃, aht ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    apv ☃ = ☃.r(☃);
    
    c = true;
    if (☃)
    {
      ☃.a(☃, aju.am.u().a(a, ☃.c(a)), 3);
      ☃.a(☃, aju.am.u().a(a, ☃.c(a)), 3);
    }
    else
    {
      ☃.a(☃, aju.al.u().a(a, ☃.c(a)), 3);
      ☃.a(☃, aju.al.u().a(a, ☃.c(a)), 3);
    }
    c = false;
    if (☃ != null)
    {
      ☃.z();
      ☃.a(☃, ☃);
    }
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aqg();
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(a, ☃.bi().d());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    ☃.a(☃, ☃.a(a, ☃.bi().d()), 2);
    if (☃.s())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqg)) {
        ((aqg)☃).a(☃.q());
      }
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    if (!c)
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqg))
      {
        qj.a(☃, ☃, (aqg)☃);
        
        ☃.f(☃, this);
      }
    }
    super.b(☃, ☃, ☃);
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    return aau.a(☃.r(☃));
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.al);
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public arc a(int ☃)
  {
    cq ☃ = cq.a(☃);
    if (☃.k() == cq.a.b) {
      ☃ = cq.c;
    }
    return u().a(a, ☃);
  }
  
  public int e(arc ☃)
  {
    return ((cq)☃.c(a)).a();
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
