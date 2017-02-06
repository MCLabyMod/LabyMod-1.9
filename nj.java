import java.util.List;

public class nj
  extends np
{
  public final int a;
  public final int b;
  public final nj c;
  private final String k;
  private nl l;
  public final adq d;
  private boolean m;
  
  public nj(String ☃, String ☃, int ☃, int ☃, ado ☃, nj ☃)
  {
    this(☃, ☃, ☃, ☃, new adq(☃), ☃);
  }
  
  public nj(String ☃, String ☃, int ☃, int ☃, ajt ☃, nj ☃)
  {
    this(☃, ☃, ☃, ☃, new adq(☃), ☃);
  }
  
  public nj(String ☃, String ☃, int ☃, int ☃, adq ☃, nj ☃)
  {
    super(☃, new fb("achievement." + ☃, new Object[0]));
    this.d = ☃;
    
    this.k = ("achievement." + ☃ + ".desc");
    this.a = ☃;
    this.b = ☃;
    if (☃ < nk.a) {
      nk.a = ☃;
    }
    if (☃ < nk.b) {
      nk.b = ☃;
    }
    if (☃ > nk.c) {
      nk.c = ☃;
    }
    if (☃ > nk.d) {
      nk.d = ☃;
    }
    this.c = ☃;
  }
  
  public nj a()
  {
    this.f = true;
    return this;
  }
  
  public nj b()
  {
    this.m = true;
    return this;
  }
  
  public nj c()
  {
    super.h();
    
    nk.e.add(this);
    
    return this;
  }
  
  public boolean d()
  {
    return true;
  }
  
  public eu e()
  {
    eu ☃ = super.e();
    ☃.b().a(g() ? a.f : a.k);
    return ☃;
  }
  
  public nj a(Class<? extends ns> ☃)
  {
    return (nj)super.b(☃);
  }
  
  public String f()
  {
    if (this.l != null) {
      return this.l.a(di.a(this.k));
    }
    return di.a(this.k);
  }
  
  public nj a(nl ☃)
  {
    this.l = ☃;
    return this;
  }
  
  public boolean g()
  {
    return this.m;
  }
}
