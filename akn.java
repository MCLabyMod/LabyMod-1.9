import java.util.Random;

public class akn
  extends ajy
  implements ajv
{
  public static final arq c = arq.a("age", 0, 7);
  private static final bbh[] a = { new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
  
  protected akn()
  {
    w(this.A.b().a(e(), Integer.valueOf(0)));
    a(true);
    a(null);
    c(0.0F);
    a(aop.c);
    q();
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a[((Integer)☃.c(e())).intValue()];
  }
  
  protected boolean i(arc ☃)
  {
    return ☃.t() == aju.ak;
  }
  
  protected arq e()
  {
    return c;
  }
  
  public int g()
  {
    return 7;
  }
  
  protected int x(arc ☃)
  {
    return ((Integer)☃.c(e())).intValue();
  }
  
  public arc e(int ☃)
  {
    return u().a(e(), Integer.valueOf(☃));
  }
  
  public boolean y(arc ☃)
  {
    return ((Integer)☃.c(e())).intValue() >= g();
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    super.b(☃, ☃, ☃, ☃);
    if (☃.k(☃.a()) >= 9)
    {
      int ☃ = x(☃);
      if (☃ < g())
      {
        float ☃ = a(this, ☃, ☃);
        if (☃.nextInt((int)(25.0F / ☃) + 1) == 0) {
          ☃.a(☃, e(☃ + 1), 2);
        }
      }
    }
  }
  
  public void g(aht ☃, cj ☃, arc ☃)
  {
    int ☃ = x(☃) + b(☃);
    int ☃ = g();
    if (☃ > ☃) {
      ☃ = ☃;
    }
    ☃.a(☃, e(☃), 2);
  }
  
  protected int b(aht ☃)
  {
    return on.a(☃.r, 2, 5);
  }
  
  protected static float a(ajt ☃, aht ☃, cj ☃)
  {
    float ☃ = 1.0F;
    
    cj ☃ = ☃.b();
    for (int ☃ = -1; ☃ <= 1; ☃++) {
      for (int ☃ = -1; ☃ <= 1; ☃++)
      {
        float ☃ = 0.0F;
        
        arc ☃ = ☃.o(☃.a(☃, 0, ☃));
        if (☃.t() == aju.ak)
        {
          ☃ = 1.0F;
          if (((Integer)☃.c(ali.a)).intValue() > 0) {
            ☃ = 3.0F;
          }
        }
        if ((☃ != 0) || (☃ != 0)) {
          ☃ /= 4.0F;
        }
        ☃ += ☃;
      }
    }
    cj ☃ = ☃.c();
    cj ☃ = ☃.d();
    cj ☃ = ☃.e();
    cj ☃ = ☃.f();
    
    boolean ☃ = (☃ == ☃.o(☃).t()) || (☃ == ☃.o(☃).t());
    boolean ☃ = (☃ == ☃.o(☃).t()) || (☃ == ☃.o(☃).t());
    if ((☃) && (☃))
    {
      ☃ /= 2.0F;
    }
    else
    {
      boolean ☃ = (☃ == ☃.o(☃.c()).t()) || (☃ == ☃.o(☃.c()).t()) || (☃ == ☃.o(☃.d()).t()) || (☃ == ☃.o(☃.d()).t());
      if (☃) {
        ☃ /= 2.0F;
      }
    }
    return ☃;
  }
  
  public boolean f(aht ☃, cj ☃, arc ☃)
  {
    return ((☃.j(☃) >= 8) || (☃.h(☃))) && (i(☃.o(☃.b())));
  }
  
  protected ado h()
  {
    return ads.P;
  }
  
  protected ado i()
  {
    return ads.Q;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, 0);
    if (☃.E) {
      return;
    }
    int ☃ = x(☃);
    if (☃ >= g())
    {
      int ☃ = 3 + ☃;
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        if (☃.r.nextInt(2 * g()) <= ☃) {
          a(☃, ☃, new adq(h()));
        }
      }
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (y(☃)) {
      return i();
    }
    return h();
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(h());
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return !y(☃);
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return true;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    g(☃, ☃, ☃);
  }
  
  public arc a(int ☃)
  {
    return e(☃);
  }
  
  public int e(arc ☃)
  {
    return x(☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { c });
  }
}
