import java.util.Random;

public abstract class alz
  extends ajt
{
  public static final arp<alz.a> a = arp.a("half", alz.a.class);
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
  protected static final bbh c = new bbh(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
  
  public alz(axe ☃)
  {
    super(☃);
    
    this.l = e();
    d(255);
  }
  
  protected boolean o()
  {
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    if (e()) {
      return j;
    }
    return ☃.c(a) == alz.a.a ? c : b;
  }
  
  public boolean k(arc ☃)
  {
    return (((alz)☃.t()).e()) || (☃.c(a) == alz.a.a);
  }
  
  public boolean b(arc ☃)
  {
    return e();
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    arc ☃ = super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃).a(a, alz.a.b);
    if (e()) {
      return ☃;
    }
    if ((☃ == cq.a) || ((☃ != cq.b) && (☃ > 0.5D))) {
      return ☃.a(a, alz.a.a);
    }
    return ☃;
  }
  
  public int a(Random ☃)
  {
    if (e()) {
      return 2;
    }
    return 1;
  }
  
  public boolean c(arc ☃)
  {
    return e();
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (e()) {
      return super.a(☃, ☃, ☃, ☃);
    }
    if ((☃ != cq.b) && (☃ != cq.a) && (!super.a(☃, ☃, ☃, ☃))) {
      return false;
    }
    arc ☃ = ☃.o(☃.a(☃));
    
    boolean ☃ = (i(☃)) && (☃.c(a) == alz.a.a);
    boolean ☃ = (i(☃)) && (☃.c(a) == alz.a.a);
    if (☃)
    {
      if (☃ == cq.a) {
        return true;
      }
      if ((☃ == cq.b) && (super.a(☃, ☃, ☃, ☃))) {
        return true;
      }
      return (!i(☃)) || (!☃);
    }
    if (☃ == cq.b) {
      return true;
    }
    if ((☃ == cq.a) && (super.a(☃, ☃, ☃, ☃))) {
      return true;
    }
    return (!i(☃)) || (☃);
  }
  
  protected static boolean i(arc ☃)
  {
    ajt ☃ = ☃.t();
    return (☃ == aju.U) || (☃ == aju.bM) || (☃ == aju.cP) || (☃ == aju.cX);
  }
  
  public abstract String e(int paramInt);
  
  public abstract boolean e();
  
  public abstract arr<?> g();
  
  public abstract Comparable<?> a(adq paramadq);
  
  public static enum a
    implements or
  {
    private final String c;
    
    private a(String ☃)
    {
      this.c = ☃;
    }
    
    public String toString()
    {
      return this.c;
    }
    
    public String m()
    {
      return this.c;
    }
  }
}
