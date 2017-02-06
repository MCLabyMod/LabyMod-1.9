import java.util.Random;

public class ale
  extends aks
{
  protected static final bbh a = new bbh(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
  protected static final bbh b = new bbh(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
  protected static final bbh c = new bbh(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);
  
  protected ale()
  {
    super(axe.q);
    w(this.A.b().a(H, cq.b));
    a(acq.c);
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(H, ☃.a((cq)☃.c(H)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(H, ☃.b((cq)☃.c(H)));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (ale.1.a[((cq)☃.c(H)).k().ordinal()])
    {
    case 1: 
    default: 
      return c;
    case 2: 
      return b;
    }
    return a;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return true;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    arc ☃ = ☃.o(☃.a(☃.d()));
    if (☃.t() == aju.cQ)
    {
      cq ☃ = (cq)☃.c(H);
      if (☃ == ☃) {
        return u().a(H, ☃.d());
      }
    }
    return u().a(H, ☃);
  }
  
  public void c(aht ☃, cj ☃, arc ☃) {}
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃) {}
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    cq ☃ = (cq)☃.c(H);
    double ☃ = ☃.p() + 0.55D - ☃.nextFloat() * 0.1F;
    double ☃ = ☃.q() + 0.55D - ☃.nextFloat() * 0.1F;
    double ☃ = ☃.r() + 0.55D - ☃.nextFloat() * 0.1F;
    double ☃ = 0.4F - (☃.nextFloat() + ☃.nextFloat()) * 0.4F;
    if (☃.nextInt(5) == 0) {
      ☃.a(cy.R, ☃ + ☃.g() * ☃, ☃ + ☃.h() * ☃, ☃ + ☃.i() * ☃, ☃.nextGaussian() * 0.005D, ☃.nextGaussian() * 0.005D, ☃.nextGaussian() * 0.005D, new int[0]);
    }
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u();
    ☃ = ☃.a(H, cq.a(☃));
    
    return ☃;
  }
  
  public int e(arc ☃)
  {
    return ((cq)☃.c(H)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { H });
  }
  
  public axh h(arc ☃)
  {
    return axh.a;
  }
}
