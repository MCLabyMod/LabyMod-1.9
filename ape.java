import java.util.Random;

public class ape
  extends ajt
{
  public static final arn a = arn.a("explode");
  
  public ape()
  {
    super(axe.u);
    w(this.A.b().a(a, Boolean.valueOf(false)));
    a(acq.d);
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    super.c(☃, ☃, ☃);
    if (☃.y(☃))
    {
      d(☃, ☃, ☃.a(a, Boolean.valueOf(true)));
      ☃.g(☃);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃.y(☃))
    {
      d(☃, ☃, ☃.a(a, Boolean.valueOf(true)));
      ☃.g(☃);
    }
  }
  
  public void a(aht ☃, cj ☃, ahp ☃)
  {
    if (☃.E) {
      return;
    }
    ye ☃ = new ye(☃, ☃.p() + 0.5F, ☃.q(), ☃.r() + 0.5F, ☃.c());
    ☃.a((short)(☃.r.nextInt(☃.l() / 4) + ☃.l() / 8));
    ☃.a(☃);
  }
  
  public void d(aht ☃, cj ☃, arc ☃)
  {
    a(☃, ☃, ☃, null);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃)
  {
    if (☃.E) {
      return;
    }
    if (((Boolean)☃.c(a)).booleanValue())
    {
      ye ☃ = new ye(☃, ☃.p() + 0.5F, ☃.q(), ☃.r() + 0.5F, ☃);
      ☃.a(☃);
      ☃.a(null, ☃.p, ☃.q, ☃.r, ng.gj, nh.e, 1.0F, 1.0F);
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if ((☃ != null) && (
      (☃.b() == ads.d) || (☃.b() == ads.bV)))
    {
      a(☃, ☃, ☃.a(a, Boolean.valueOf(true)), ☃);
      ☃.a(☃, aju.a.u(), 11);
      if (☃.b() == ads.d) {
        ☃.a(1, ☃);
      } else if (!☃.bJ.d) {
        ☃.b -= 1;
      }
      return true;
    }
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    if ((!☃.E) && ((☃ instanceof zm)))
    {
      zm ☃ = (zm)☃;
      if (☃.aH())
      {
        a(☃, ☃, ☃.o(☃).a(a, Boolean.valueOf(true)), (☃.e instanceof sa) ? (sa)☃.e : null);
        ☃.g(☃);
      }
    }
  }
  
  public boolean a(ahp ☃)
  {
    return false;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Boolean.valueOf((☃ & 0x1) > 0));
  }
  
  public int e(arc ☃)
  {
    return ((Boolean)☃.c(a)).booleanValue() ? 1 : 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
