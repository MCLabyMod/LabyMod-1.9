import java.util.Random;

public abstract class anr
  extends alz
{
  public static final arp<anr.c> d = arp.a("variant", anr.c.class);
  
  public anr()
  {
    super(axe.e);
    arc ☃ = this.A.b();
    if (!e()) {
      ☃ = ☃.a(a, alz.a.b);
    }
    w(☃.a(d, anr.c.a));
    a(acq.b);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.cX);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.cX);
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(d, anr.c.a);
    if (!e()) {
      ☃ = ☃.a(a, (☃ & 0x8) == 0 ? alz.a.b : alz.a.a);
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    if ((!e()) && 
      (☃.c(a) == alz.a.a)) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  protected ard b()
  {
    if (e()) {
      return new ard(this, new arr[] { d });
    }
    return new ard(this, new arr[] { a, d });
  }
  
  public String e(int ☃)
  {
    return super.a();
  }
  
  public arr<?> g()
  {
    return d;
  }
  
  public Comparable<?> a(adq ☃)
  {
    return anr.c.a;
  }
  
  public static class b
    extends anr
  {
    public boolean e()
    {
      return false;
    }
  }
  
  public static class a
    extends anr
  {
    public boolean e()
    {
      return true;
    }
  }
  
  public static enum c
    implements or
  {
    private c() {}
    
    public String m()
    {
      return "default";
    }
  }
}
