import java.util.Random;

public class apj
  extends ajt
{
  public static final arn a = arn.a("up");
  public static final arn b = arn.a("north");
  public static final arn c = arn.a("east");
  public static final arn d = arn.a("south");
  public static final arn e = arn.a("west");
  public static final arn[] f = { a, b, d, e, c };
  protected static final bbh g = new bbh(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh B = new bbh(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
  protected static final bbh C = new bbh(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh D = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
  protected static final bbh E = new bbh(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);
  
  public apj()
  {
    super(axe.l);
    w(this.A.b().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false)));
    a(true);
    a(acq.c);
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = ☃.b(☃, ☃);
    
    int ☃ = 0;
    bbh ☃ = j;
    if (((Boolean)☃.c(a)).booleanValue())
    {
      ☃ = g;
      ☃++;
    }
    if (((Boolean)☃.c(b)).booleanValue())
    {
      ☃ = D;
      ☃++;
    }
    if (((Boolean)☃.c(c)).booleanValue())
    {
      ☃ = C;
      ☃++;
    }
    if (((Boolean)☃.c(d)).booleanValue())
    {
      ☃ = E;
      ☃++;
    }
    if (((Boolean)☃.c(e)).booleanValue())
    {
      ☃ = B;
      ☃++;
    }
    return ☃ == 1 ? ☃ : j;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.a(a, Boolean.valueOf(☃.o(☃.a()).k()));
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(ahx ☃, cj ☃)
  {
    return true;
  }
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    switch (apj.1.a[☃.ordinal()])
    {
    case 1: 
      return x(☃.o(☃.a()));
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      return x(☃.o(☃.a(☃.d())));
    }
    return false;
  }
  
  private boolean x(arc ☃)
  {
    return (☃.h()) && (☃.a().c());
  }
  
  private boolean e(aht ☃, cj ☃, arc ☃)
  {
    arc ☃ = ☃;
    for (cq ☃ : cq.c.a)
    {
      arn ☃ = a(☃);
      if ((((Boolean)☃.c(☃)).booleanValue()) && 
        (!x(☃.o(☃.a(☃)))))
      {
        arc ☃ = ☃.o(☃.a());
        if ((☃.t() != this) || (!((Boolean)☃.c(☃)).booleanValue())) {
          ☃ = ☃.a(☃, Boolean.valueOf(false));
        }
      }
    }
    if (i(☃) == 0) {
      return false;
    }
    if (☃ != ☃) {
      ☃.a(☃, ☃, 2);
    }
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if ((!☃.E) && (!e(☃, ☃, ☃)))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    if (☃.r.nextInt(4) != 0) {
      return;
    }
    int ☃ = 4;
    int ☃ = 5;
    boolean ☃ = false;
    for (int ☃ = -☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃; ☃++) {
        for (int ☃ = -1; ☃ <= 1; ☃++) {
          if (☃.o(☃.a(☃, ☃, ☃)).t() == this)
          {
            ☃--;
            if (☃ <= 0)
            {
              ☃ = true;
              break label117;
            }
          }
        }
      }
    }
    label117:
    cq ☃ = cq.a(☃);
    cj ☃ = ☃.a();
    if ((☃ == cq.b) && (☃.q() < 255) && (☃.d(☃)))
    {
      if (☃) {
        return;
      }
      arc ☃ = ☃;
      for (cq ☃ : cq.c.a) {
        if ((☃.nextBoolean()) || (!x(☃.o(☃.a(☃))))) {
          ☃ = ☃.a(a(☃), Boolean.valueOf(false));
        }
      }
      if ((((Boolean)☃.c(b)).booleanValue()) || (((Boolean)☃.c(c)).booleanValue()) || (((Boolean)☃.c(d)).booleanValue()) || (((Boolean)☃.c(e)).booleanValue())) {
        ☃.a(☃, ☃, 2);
      }
      return;
    }
    if ((☃.k().c()) && (!((Boolean)☃.c(a(☃))).booleanValue()))
    {
      if (☃) {
        return;
      }
      cj ☃ = ☃.a(☃);
      
      arc ☃ = ☃.o(☃);
      ajt ☃ = ☃.t();
      if (☃.x == axe.a)
      {
        cq ☃ = ☃.e();
        cq ☃ = ☃.f();
        
        boolean ☃ = ((Boolean)☃.c(a(☃))).booleanValue();
        boolean ☃ = ((Boolean)☃.c(a(☃))).booleanValue();
        
        cj ☃ = ☃.a(☃);
        cj ☃ = ☃.a(☃);
        if ((☃) && (x(☃.o(☃)))) {
          ☃.a(☃, u().a(a(☃), Boolean.valueOf(true)), 2);
        } else if ((☃) && (x(☃.o(☃)))) {
          ☃.a(☃, u().a(a(☃), Boolean.valueOf(true)), 2);
        } else if ((☃) && (☃.d(☃)) && (x(☃.o(☃.a(☃))))) {
          ☃.a(☃, u().a(a(☃.d()), Boolean.valueOf(true)), 2);
        } else if ((☃) && (☃.d(☃)) && (x(☃.o(☃.a(☃))))) {
          ☃.a(☃, u().a(a(☃.d()), Boolean.valueOf(true)), 2);
        } else if (x(☃.o(☃.a()))) {
          ☃.a(☃, u(), 2);
        }
      }
      else if ((☃.x.k()) && (☃.h()))
      {
        ☃.a(☃, ☃.a(a(☃), Boolean.valueOf(true)), 2);
      }
      return;
    }
    if (☃.q() > 1)
    {
      cj ☃ = ☃.b();
      arc ☃ = ☃.o(☃);
      ajt ☃ = ☃.t();
      if (☃.x == axe.a)
      {
        arc ☃ = ☃;
        for (cq ☃ : cq.c.a) {
          if (☃.nextBoolean()) {
            ☃ = ☃.a(a(☃), Boolean.valueOf(false));
          }
        }
        if ((((Boolean)☃.c(b)).booleanValue()) || (((Boolean)☃.c(c)).booleanValue()) || (((Boolean)☃.c(d)).booleanValue()) || (((Boolean)☃.c(e)).booleanValue())) {
          ☃.a(☃, ☃, 2);
        }
      }
      else if (☃ == this)
      {
        arc ☃ = ☃;
        for (cq ☃ : cq.c.a)
        {
          arn ☃ = a(☃);
          if ((☃.nextBoolean()) && (((Boolean)☃.c(☃)).booleanValue())) {
            ☃ = ☃.a(☃, Boolean.valueOf(true));
          }
        }
        if ((((Boolean)☃.c(b)).booleanValue()) || (((Boolean)☃.c(c)).booleanValue()) || (((Boolean)☃.c(d)).booleanValue()) || (((Boolean)☃.c(e)).booleanValue())) {
          ☃.a(☃, ☃, 2);
        }
      }
    }
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    arc ☃ = u().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false));
    if (☃.k().c()) {
      return ☃.a(a(☃.d()), Boolean.valueOf(true));
    }
    return ☃;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    if ((!☃.E) && (☃ != null) && (☃.b() == ads.bl))
    {
      ☃.b(nt.a(this));
      
      a(☃, ☃, new adq(aju.bn, 1, 0));
    }
    else
    {
      super.a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    return u().a(d, Boolean.valueOf((☃ & 0x1) > 0)).a(e, Boolean.valueOf((☃ & 0x2) > 0)).a(b, Boolean.valueOf((☃ & 0x4) > 0)).a(c, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    if (((Boolean)☃.c(d)).booleanValue()) {
      ☃ |= 0x1;
    }
    if (((Boolean)☃.c(e)).booleanValue()) {
      ☃ |= 0x2;
    }
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x4;
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, c, d, e });
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (apj.1.b[☃.ordinal()])
    {
    case 1: 
      return ☃.a(b, ☃.c(d)).a(c, ☃.c(e)).a(d, ☃.c(b)).a(e, ☃.c(c));
    case 2: 
      return ☃.a(b, ☃.c(c)).a(c, ☃.c(d)).a(d, ☃.c(e)).a(e, ☃.c(b));
    case 3: 
      return ☃.a(b, ☃.c(e)).a(c, ☃.c(b)).a(d, ☃.c(c)).a(e, ☃.c(d));
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    switch (apj.1.c[☃.ordinal()])
    {
    case 1: 
      return ☃.a(b, ☃.c(d)).a(d, ☃.c(b));
    case 2: 
      return ☃.a(c, ☃.c(e)).a(e, ☃.c(c));
    }
    return super.a(☃, ☃);
  }
  
  public static arn a(cq ☃)
  {
    switch (apj.1.a[☃.ordinal()])
    {
    case 1: 
      return a;
    case 2: 
      return b;
    case 3: 
      return d;
    case 5: 
      return e;
    case 4: 
      return c;
    }
    throw new IllegalArgumentException(☃ + " is an invalid choice");
  }
  
  public static int i(arc ☃)
  {
    int ☃ = 0;
    for (arn ☃ : f) {
      if (((Boolean)☃.c(☃)).booleanValue()) {
        ☃++;
      }
    }
    return ☃;
  }
}
