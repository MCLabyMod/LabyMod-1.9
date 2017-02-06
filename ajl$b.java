public class ajl$b
  extends ajl
{
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.875D, 1.0D, 0.78125D, 1.0D);
  protected static final bbh e = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.78125D, 0.125D);
  protected static final bbh f = new bbh(0.875D, 0.0D, 0.0D, 1.0D, 0.78125D, 1.0D);
  protected static final bbh g = new bbh(0.0D, 0.0D, 0.0D, 0.125D, 0.78125D, 1.0D);
  
  public ajl$b()
  {
    w(this.A.b().a(a, cq.c));
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(a)));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (ajl.1.a[((cq)☃.c(a)).ordinal()])
    {
    case 1: 
    default: 
      return d;
    case 2: 
      return e;
    case 3: 
      return f;
    }
    return g;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    cq ☃ = (cq)☃.c(a);
    if (!☃.o(☃.a(☃.d())).a().a())
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
    super.a(☃, ☃, ☃, ☃);
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
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
