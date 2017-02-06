public class aod
  extends ajt
{
  public static final arp<cq.a> c = arp.a("axis", cq.a.class);
  
  protected aod(axe ☃)
  {
    super(☃, ☃.r());
  }
  
  protected aod(axe ☃, axf ☃)
  {
    super(☃, ☃);
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (aod.1.b[☃.ordinal()])
    {
    case 1: 
    case 2: 
      switch (aod.1.a[((cq.a)☃.c(c)).ordinal()])
      {
      case 1: 
        return ☃.a(c, cq.a.c);
      case 2: 
        return ☃.a(c, cq.a.a);
      }
      return ☃;
    }
    return ☃;
  }
  
  public arc a(int ☃)
  {
    cq.a ☃ = cq.a.b;
    int ☃ = ☃ & 0xC;
    if (☃ == 4) {
      ☃ = cq.a.a;
    } else if (☃ == 8) {
      ☃ = cq.a.c;
    }
    return u().a(c, ☃);
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    cq.a ☃ = (cq.a)☃.c(c);
    if (☃ == cq.a.a) {
      ☃ |= 0x4;
    } else if (☃ == cq.a.c) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { c });
  }
  
  protected adq u(arc ☃)
  {
    return new adq(ado.a(this));
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃).a(c, ☃.k());
  }
}
