import java.util.Random;

public class aow
  extends ajy
  implements ajv
{
  public static final arq a = arq.a("age", 0, 7);
  public static final aro c = apf.a;
  private final ajt e;
  protected static final bbh[] d = { new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.625D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D) };
  
  protected aow(ajt ☃)
  {
    w(this.A.b().a(a, Integer.valueOf(0)).a(c, cq.b));
    this.e = ☃;
    a(true);
    a(null);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return d[((Integer)☃.c(a)).intValue()];
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    ☃ = ☃.a(c, cq.b);
    for (cq ☃ : cq.c.a) {
      if ((☃.o(☃.a(☃)).t() == this.e) && (☃ == 7))
      {
        ☃ = ☃.a(c, ☃);
        break;
      }
    }
    return ☃;
  }
  
  protected boolean i(arc ☃)
  {
    return ☃.t() == aju.ak;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    super.b(☃, ☃, ☃, ☃);
    if (☃.k(☃.a()) < 9) {
      return;
    }
    float ☃ = akn.a(this, ☃, ☃);
    if (☃.nextInt((int)(25.0F / ☃) + 1) == 0)
    {
      int ☃ = ((Integer)☃.c(a)).intValue();
      if (☃ < 7)
      {
        ☃ = ☃.a(a, Integer.valueOf(☃ + 1));
        ☃.a(☃, ☃, 2);
      }
      else
      {
        for (cq ☃ : cq.c.a) {
          if (☃.o(☃.a(☃)).t() == this.e) {
            return;
          }
        }
        ☃ = ☃.a(cq.c.a.a(☃));
        
        ajt ☃ = ☃.o(☃.b()).t();
        if ((☃.o(☃).t().x == axe.a) && ((☃ == aju.ak) || (☃ == aju.d) || (☃ == aju.c))) {
          ☃.a(☃, this.e.u());
        }
      }
    }
  }
  
  public void g(aht ☃, cj ☃, arc ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue() + on.a(☃.r, 2, 5);
    ☃.a(☃, ☃.a(a, Integer.valueOf(Math.min(7, ☃))), 2);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (☃.E) {
      return;
    }
    ado ☃ = e();
    if (☃ == null) {
      return;
    }
    int ☃ = ((Integer)☃.c(a)).intValue();
    for (int ☃ = 0; ☃ < 3; ☃++) {
      if (☃.r.nextInt(15) <= ☃) {
        a(☃, ☃, new adq(☃));
      }
    }
  }
  
  protected ado e()
  {
    if (this.e == aju.aU) {
      return ads.bn;
    }
    if (this.e == aju.bk) {
      return ads.bo;
    }
    return null;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    ado ☃ = e();
    
    return ☃ == null ? null : new adq(☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return ((Integer)☃.c(a)).intValue() != 7;
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
    return u().a(a, Integer.valueOf(☃));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, c });
  }
}
