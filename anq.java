import com.google.common.base.Predicate;
import java.util.Random;

public class anq
  extends amg
{
  private arg a;
  private arg b;
  private arg c;
  private arg d;
  
  protected anq()
  {
    super(axe.C, axf.q);
    w(this.A.b().a(D, cq.c));
    a(true);
    a(acq.b);
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    super.c(☃, ☃, ☃);
    c(☃, ☃);
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    return (e().a(☃, ☃) != null) || (h().a(☃, ☃) != null);
  }
  
  private void c(aht ☃, cj ☃)
  {
    arg.b ☃;
    if ((☃ = g().a(☃, ☃)) != null)
    {
      for (int ☃ = 0; ☃ < g().b(); ☃++)
      {
        arf ☃ = ☃.a(0, ☃, 0);
        ☃.a(☃.d(), aju.a.u(), 2);
      }
      wf ☃ = new wf(☃);
      cj ☃ = ☃.a(0, 2, 0).d();
      ☃.b(☃.p() + 0.5D, ☃.q() + 0.05D, ☃.r() + 0.5D, 0.0F, 0.0F);
      ☃.a(☃);
      for (int ☃ = 0; ☃ < 120; ☃++) {
        ☃.a(cy.G, ☃.p() + ☃.r.nextDouble(), ☃.q() + ☃.r.nextDouble() * 2.5D, ☃.r() + ☃.r.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
      }
      for (int ☃ = 0; ☃ < g().b(); ☃++)
      {
        arf ☃ = ☃.a(0, ☃, 0);
        ☃.c(☃.d(), aju.a);
      }
    }
    else if ((☃ = i().a(☃, ☃)) != null)
    {
      for (int ☃ = 0; ☃ < i().c(); ☃++) {
        for (int ☃ = 0; ☃ < i().b(); ☃++) {
          ☃.a(☃.a(☃, ☃, 0).d(), aju.a.u(), 2);
        }
      }
      cj ☃ = ☃.a(1, 2, 0).d();
      
      wh ☃ = new wh(☃);
      ☃.o(true);
      ☃.b(☃.p() + 0.5D, ☃.q() + 0.05D, ☃.r() + 0.5D, 0.0F, 0.0F);
      ☃.a(☃);
      for (int ☃ = 0; ☃ < 120; ☃++) {
        ☃.a(cy.F, ☃.p() + ☃.r.nextDouble(), ☃.q() + ☃.r.nextDouble() * 3.9D, ☃.r() + ☃.r.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
      }
      for (int ☃ = 0; ☃ < i().c(); ☃++) {
        for (int ☃ = 0; ☃ < i().b(); ☃++)
        {
          arf ☃ = ☃.a(☃, ☃, 0);
          ☃.c(☃.d(), aju.a);
        }
      }
    }
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (☃.o(☃).t().x.j()) && (☃.o(☃.b()).q());
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(D, ☃.a((cq)☃.c(D)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(D)));
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(D, ☃.bi().d());
  }
  
  public arc a(int ☃)
  {
    return u().a(D, cq.b(☃));
  }
  
  public int e(arc ☃)
  {
    return ((cq)☃.c(D)).b();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { D });
  }
  
  private static final Predicate<arc> e = new Predicate()
  {
    public boolean a(arc ☃)
    {
      return (☃ != null) && ((☃.t() == aju.aU) || (☃.t() == aju.aZ));
    }
  };
  
  protected arg e()
  {
    if (this.a == null) {
      this.a = arh.a().a(new String[] { " ", "#", "#" }).a('#', arf.a(ark.a(aju.aJ))).b();
    }
    return this.a;
  }
  
  protected arg g()
  {
    if (this.b == null) {
      this.b = arh.a().a(new String[] { "^", "#", "#" }).a('^', arf.a(e)).a('#', arf.a(ark.a(aju.aJ))).b();
    }
    return this.b;
  }
  
  protected arg h()
  {
    if (this.c == null) {
      this.c = arh.a().a(new String[] { "~ ~", "###", "~#~" }).a('#', arf.a(ark.a(aju.S))).a('~', arf.a(ark.a(aju.a))).b();
    }
    return this.c;
  }
  
  protected arg i()
  {
    if (this.d == null) {
      this.d = arh.a().a(new String[] { "~^~", "###", "~#~" }).a('^', arf.a(e)).a('#', arf.a(ark.a(aju.S))).a('~', arf.a(ark.a(aju.a))).b();
    }
    return this.d;
  }
}
