import com.google.common.base.Predicate;
import java.util.Random;

public class aok
  extends ajn
{
  public static final aro a = aks.H;
  public static final arn b = arn.a("nodrop");
  private static final Predicate<arf> B = new Predicate()
  {
    public boolean a(arf ☃)
    {
      return (☃.a() != null) && (☃.a().t() == aju.ce) && ((☃.b() instanceof aqo)) && (((aqo)☃.b()).d() == 1);
    }
  };
  protected static final bbh c = new bbh(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
  protected static final bbh d = new bbh(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
  protected static final bbh e = new bbh(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
  protected static final bbh f = new bbh(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
  protected static final bbh g = new bbh(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
  private arg C;
  private arg D;
  
  protected aok()
  {
    super(axe.q);
    w(this.A.b().a(a, cq.c).a(b, Boolean.valueOf(false)));
  }
  
  public String c()
  {
    return di.a("tile.skull.skeleton.name");
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (aok.2.a[((cq)☃.c(a)).ordinal()])
    {
    case 1: 
    default: 
      return c;
    case 2: 
      return d;
    case 3: 
      return e;
    case 4: 
      return f;
    }
    return g;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(a, ☃.bi()).a(b, Boolean.valueOf(false));
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aqo();
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    int ☃ = 0;
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqo)) {
      ☃ = ((aqo)☃).d();
    }
    return new adq(ads.ch, 1, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃) {}
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    if (☃.bJ.d)
    {
      ☃ = ☃.a(b, Boolean.valueOf(true));
      ☃.a(☃, ☃, 4);
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    if (☃.E) {
      return;
    }
    if (!((Boolean)☃.c(b)).booleanValue())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqo))
      {
        aqo ☃ = (aqo)☃;
        adq ☃ = a(☃, ☃, ☃);
        if ((☃.d() == 3) && (☃.b() != null))
        {
          ☃.d(new dn());
          
          dn ☃ = new dn();
          
          dy.a(☃, ☃.b());
          ☃.o().a("SkullOwner", ☃);
        }
        a(☃, ☃, ☃);
      }
    }
    super.b(☃, ☃, ☃);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.ch;
  }
  
  public boolean b(aht ☃, cj ☃, adq ☃)
  {
    if ((☃.i() == 1) && (☃.q() >= 2) && (☃.ae() != qk.a) && (!☃.E)) {
      return e().a(☃, ☃) != null;
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, aqo ☃)
  {
    if ((☃.d() != 1) || (☃.q() < 2) || (☃.ae() == qk.a) || (☃.E)) {
      return;
    }
    arg ☃ = g();
    arg.b ☃ = ☃.a(☃, ☃);
    if (☃ == null) {
      return;
    }
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      arf ☃ = ☃.a(☃, 0, 0);
      ☃.a(☃.d(), ☃.a().a(b, Boolean.valueOf(true)), 2);
    }
    for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
      for (int ☃ = 0; ☃ < ☃.b(); ☃++)
      {
        arf ☃ = ☃.a(☃, ☃, 0);
        ☃.a(☃.d(), aju.a.u(), 2);
      }
    }
    cj ☃ = ☃.a(1, 0, 0).d();
    
    xo ☃ = new xo(☃);
    cj ☃ = ☃.a(1, 2, 0).d();
    ☃.b(☃.p() + 0.5D, ☃.q() + 0.55D, ☃.r() + 0.5D, ☃.b().k() == cq.a.a ? 0.0F : 90.0F, 0.0F);
    ☃.aM = (☃.b().k() == cq.a.a ? 0.0F : 90.0F);
    ☃.o();
    for (zj ☃ : ☃.a(zj.class, ☃.bl().g(50.0D))) {
      ☃.b(nk.I);
    }
    ☃.a(☃);
    for (int ☃ = 0; ☃ < 120; ☃++) {
      ☃.a(cy.F, ☃.p() + ☃.r.nextDouble(), ☃.q() - 2 + ☃.r.nextDouble() * 3.9D, ☃.r() + ☃.r.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
    }
    for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
      for (int ☃ = 0; ☃ < ☃.b(); ☃++)
      {
        arf ☃ = ☃.a(☃, ☃, 0);
        ☃.c(☃.d(), aju.a);
      }
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, cq.a(☃ & 0x7)).a(b, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(a)).a();
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
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
    return new ard(this, new arr[] { a, b });
  }
  
  protected arg e()
  {
    if (this.C == null) {
      this.C = arh.a().a(new String[] { "   ", "###", "~#~" }).a('#', arf.a(ark.a(aju.aW))).a('~', arf.a(ark.a(aju.a))).b();
    }
    return this.C;
  }
  
  protected arg g()
  {
    if (this.D == null) {
      this.D = arh.a().a(new String[] { "^^^", "###", "~#~" }).a('#', arf.a(ark.a(aju.aW))).a('^', B).a('~', arf.a(ark.a(aju.a))).b();
    }
    return this.D;
  }
}
