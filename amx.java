import java.util.Random;

public class amx
  extends ajy
{
  public static final arq a = arq.a("age", 0, 3);
  private static final bbh[] c = { new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D) };
  
  protected amx()
  {
    super(axe.k, axf.D);
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(true);
    a(null);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return c[((Integer)☃.c(a)).intValue()];
  }
  
  protected boolean i(arc ☃)
  {
    return ☃.t() == aju.aW;
  }
  
  public boolean f(aht ☃, cj ☃, arc ☃)
  {
    return i(☃.o(☃.b()));
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    if ((☃ < 3) && (☃.nextInt(10) == 0))
    {
      ☃ = ☃.a(a, Integer.valueOf(☃ + 1));
      ☃.a(☃, ☃, 2);
    }
    super.b(☃, ☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    if (☃.E) {
      return;
    }
    int ☃ = 1;
    if (((Integer)☃.c(a)).intValue() >= 3)
    {
      ☃ = 2 + ☃.r.nextInt(3);
      if (☃ > 0) {
        ☃ += ☃.r.nextInt(☃ + 1);
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      a(☃, ☃, new adq(ads.bF));
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bF);
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
}
