import java.util.Random;

public class aoc
  extends akr
{
  public static final arn a = arn.a("locked");
  public static final arq b = arq.a("delay", 1, 4);
  
  protected aoc(boolean ☃)
  {
    super(☃);
    w(this.A.b().a(D, cq.c).a(b, Integer.valueOf(1)).a(a, Boolean.valueOf(false)));
  }
  
  public String c()
  {
    return di.a("item.diode.name");
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.a(a, Boolean.valueOf(b(☃, ☃, ☃)));
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(D, ☃.a((cq)☃.c(D)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(D)));
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.bJ.e) {
      return false;
    }
    ☃.a(☃, ☃.a(b), 3);
    return true;
  }
  
  protected int i(arc ☃)
  {
    return ((Integer)☃.c(b)).intValue() * 2;
  }
  
  protected arc x(arc ☃)
  {
    Integer ☃ = (Integer)☃.c(b);
    Boolean ☃ = (Boolean)☃.c(a);
    cq ☃ = (cq)☃.c(D);
    return aju.bc.u().a(D, ☃).a(b, ☃).a(a, ☃);
  }
  
  protected arc y(arc ☃)
  {
    Integer ☃ = (Integer)☃.c(b);
    Boolean ☃ = (Boolean)☃.c(a);
    cq ☃ = (cq)☃.c(D);
    return aju.bb.u().a(D, ☃).a(b, ☃).a(a, ☃);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.bi;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bi);
  }
  
  public boolean b(ahx ☃, cj ☃, arc ☃)
  {
    return c(☃, ☃, ☃) > 0;
  }
  
  protected boolean A(arc ☃)
  {
    return B(☃);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (!this.d) {
      return;
    }
    cq ☃ = (cq)☃.c(D);
    
    double ☃ = ☃.p() + 0.5F + (☃.nextFloat() - 0.5F) * 0.2D;
    double ☃ = ☃.q() + 0.4F + (☃.nextFloat() - 0.5F) * 0.2D;
    double ☃ = ☃.r() + 0.5F + (☃.nextFloat() - 0.5F) * 0.2D;
    
    float ☃ = -5.0F;
    if (☃.nextBoolean()) {
      ☃ = ((Integer)☃.c(b)).intValue() * 2 - 1;
    }
    ☃ /= 16.0F;
    
    double ☃ = ☃ * ☃.g();
    double ☃ = ☃ * ☃.i();
    
    ☃.a(cy.E, ☃ + ☃, ☃, ☃ + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    super.b(☃, ☃, ☃);
    h(☃, ☃, ☃);
  }
  
  public arc a(int ☃)
  {
    return u().a(D, cq.b(☃)).a(a, Boolean.valueOf(false)).a(b, Integer.valueOf(1 + (☃ >> 2)));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(D)).b();
    ☃ |= ((Integer)☃.c(b)).intValue() - 1 << 2;
    
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { D, b, a });
  }
}
