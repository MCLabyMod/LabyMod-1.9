import java.util.Random;

public class akb
  extends ajt
{
  public static final arq a = arq.a("bites", 0, 6);
  protected static final bbh[] b = { new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new bbh(0.1875D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new bbh(0.3125D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new bbh(0.4375D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new bbh(0.5625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new bbh(0.6875D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new bbh(0.8125D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D) };
  
  protected akb()
  {
    super(axe.F);
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b[((Integer)☃.c(a)).intValue()];
  }
  
  public bbh c(arc ☃, aht ☃, cj ☃)
  {
    return ☃.d(☃, ☃);
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    b(☃, ☃, ☃, ☃);
    return true;
  }
  
  private void b(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    if (!☃.l(false)) {
      return;
    }
    ☃.b(nt.J);
    
    ☃.cS().a(2, 0.1F);
    int ☃ = ((Integer)☃.c(a)).intValue();
    if (☃ < 6) {
      ☃.a(☃, ☃.a(a, Integer.valueOf(☃ + 1)), 3);
    } else {
      ☃.g(☃);
    }
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (super.a(☃, ☃)) {
      return b(☃, ☃);
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!b(☃, ☃)) {
      ☃.g(☃);
    }
  }
  
  private boolean b(aht ☃, cj ☃)
  {
    return ☃.o(☃.b()).a().a();
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bg);
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Integer.valueOf(☃));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    return (7 - ((Integer)☃.c(a)).intValue()) * 2;
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
}
