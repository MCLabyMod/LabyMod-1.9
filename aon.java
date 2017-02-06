import java.util.Random;

public class aon
  extends ajt
{
  public static final arq a = arq.a("layers", 1, 8);
  protected static final bbh[] b = { new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
  
  protected aon()
  {
    super(axe.y);
    w(this.A.b().a(a, Integer.valueOf(1)));
    a(true);
    a(acq.c);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b[((Integer)☃.c(a)).intValue()];
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return ((Integer)☃.o(☃).c(a)).intValue() < 5;
  }
  
  public boolean k(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue() == 7;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue() - 1;
    float ☃ = 0.125F;
    bbh ☃ = ☃.c(☃, ☃);
    return new bbh(☃.a, ☃.b, ☃.c, ☃.d, ☃ * ☃, ☃.f);
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
    arc ☃ = ☃.o(☃.b());
    ajt ☃ = ☃.t();
    if ((☃ == aju.aI) || (☃ == aju.cB)) {
      return false;
    }
    if (☃.a() == axe.j) {
      return true;
    }
    if ((☃ == this) && (((Integer)☃.c(a)).intValue() >= 7)) {
      return true;
    }
    return (☃.p()) && (☃.a().c());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    e(☃, ☃, ☃);
  }
  
  private boolean e(aht ☃, cj ☃, arc ☃)
  {
    if (!a(☃, ☃))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
      return false;
    }
    return true;
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    a(☃, ☃, new adq(ads.aF, ((Integer)☃.c(a)).intValue() + 1, 0));
    ☃.g(☃);
    
    ☃.b(nt.a(this));
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.aF;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.b(ahz.b, ☃) > 11)
    {
      b(☃, ☃, ☃.o(☃), 0);
      ☃.g(☃);
    }
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (☃ == cq.b) {
      return true;
    }
    arc ☃ = ☃.o(☃.a(☃));
    if ((☃.t() == this) && (((Integer)☃.c(a)).intValue() >= ((Integer)☃.c(a)).intValue())) {
      return true;
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Integer.valueOf((☃ & 0x7) + 1));
  }
  
  public boolean a(ahx ☃, cj ☃)
  {
    return ((Integer)☃.o(☃).c(a)).intValue() == 1;
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue() - 1;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
