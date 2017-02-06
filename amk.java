public class amk
  extends ajt
{
  public static final aro a = amg.D;
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
  protected static final bbh c = new bbh(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
  protected static final bbh e = new bbh(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
  
  protected amk()
  {
    super(axe.q);
    w(this.A.b().a(a, cq.c));
    a(acq.c);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (amk.1.a[((cq)☃.c(a)).ordinal()])
    {
    case 1: 
      return e;
    case 2: 
      return d;
    case 3: 
      return c;
    }
    return b;
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
    if (☃.o(☃.e()).l()) {
      return true;
    }
    if (☃.o(☃.f()).l()) {
      return true;
    }
    if (☃.o(☃.c()).l()) {
      return true;
    }
    if (☃.o(☃.d()).l()) {
      return true;
    }
    return false;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    if ((☃.k().c()) && (a(☃, ☃, ☃))) {
      return u().a(a, ☃);
    }
    for (cq ☃ : cq.c.a) {
      if (a(☃, ☃, ☃)) {
        return u().a(a, ☃);
      }
    }
    return u();
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    cq ☃ = (cq)☃.c(a);
    if (!a(☃, ☃, ☃))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  protected boolean a(aht ☃, cj ☃, cq ☃)
  {
    return ☃.o(☃.a(☃.d())).l();
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    cq ☃ = cq.a(☃);
    if (☃.k() == cq.a.b) {
      ☃ = cq.c;
    }
    return u().a(a, ☃);
  }
  
  public int e(arc ☃)
  {
    return ((cq)☃.c(a)).a();
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
    return new ard(this, new arr[] { a });
  }
}
