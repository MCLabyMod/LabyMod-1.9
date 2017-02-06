import java.util.Random;

public class alf
  extends ajn
{
  public static final aro a = amg.D;
  protected static final bbh b = new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
  
  protected alf()
  {
    super(axe.e);
    w(this.A.b().a(a, cq.c));
    a(acq.c);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public aob a(arc ☃)
  {
    return aob.c;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.Z);
  }
  
  public int a(Random ☃)
  {
    return 8;
  }
  
  protected boolean o()
  {
    return true;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(a, ☃.bi().d());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    ☃.a(☃, ☃.a(a, ☃.bi().d()), 2);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    abq ☃ = ☃.cV();
    apv ☃ = ☃.r(☃);
    if ((☃ == null) || (!(☃ instanceof aqe))) {
      return true;
    }
    if (☃.o(☃.a()).l()) {
      return true;
    }
    if (☃.E) {
      return true;
    }
    ☃.a((aqe)☃);
    ☃.a(☃);
    ☃.b(nt.X);
    
    return true;
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aqe();
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      int ☃ = ☃.nextInt(2) * 2 - 1;
      int ☃ = ☃.nextInt(2) * 2 - 1;
      
      double ☃ = ☃.p() + 0.5D + 0.25D * ☃;
      double ☃ = ☃.q() + ☃.nextFloat();
      double ☃ = ☃.r() + 0.5D + 0.25D * ☃;
      double ☃ = ☃.nextFloat() * ☃;
      double ☃ = (☃.nextFloat() - 0.5D) * 0.125D;
      double ☃ = ☃.nextFloat() * ☃;
      
      ☃.a(cy.y, ☃, ☃, ☃, ☃, ☃, ☃, new int[0]);
    }
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
