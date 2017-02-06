public class apl
  extends aoj
{
  public static final aro b = amg.D;
  protected static final bbh c = new bbh(0.0D, 0.28125D, 0.0D, 0.125D, 0.78125D, 1.0D);
  protected static final bbh d = new bbh(0.875D, 0.28125D, 0.0D, 1.0D, 0.78125D, 1.0D);
  protected static final bbh e = new bbh(0.0D, 0.28125D, 0.0D, 1.0D, 0.78125D, 0.125D);
  protected static final bbh f = new bbh(0.0D, 0.28125D, 0.875D, 1.0D, 0.78125D, 1.0D);
  
  public apl()
  {
    w(this.A.b().a(b, cq.c));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (apl.1.a[((cq)☃.c(b)).ordinal()])
    {
    case 1: 
    default: 
      return f;
    case 2: 
      return e;
    case 3: 
      return d;
    }
    return c;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    cq ☃ = (cq)☃.c(b);
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
    return u().a(b, ☃);
  }
  
  public int e(arc ☃)
  {
    return ((cq)☃.c(b)).a();
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(b, ☃.a((cq)☃.c(b)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(b)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { b });
  }
}
