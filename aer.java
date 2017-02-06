public class aer
  extends acc
{
  private final alz b;
  private final alz c;
  
  public aer(ajt ☃, alz ☃, alz ☃)
  {
    super(☃);
    this.b = ☃;
    this.c = ☃;
    
    e(0);
    a(true);
  }
  
  public int a(int ☃)
  {
    return ☃;
  }
  
  public String f_(adq ☃)
  {
    return this.b.e(☃.i());
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if ((☃.b == 0) || (!☃.a(☃.a(☃), ☃, ☃))) {
      return qo.c;
    }
    Comparable<?> ☃ = this.b.a(☃);
    arc ☃ = ☃.o(☃);
    if (☃.t() == this.b)
    {
      arr<?> ☃ = this.b.g();
      Comparable<?> ☃ = ☃.c(☃);
      alz.a ☃ = (alz.a)☃.c(alz.a);
      if (((☃ == cq.b) && (☃ == alz.a.b)) || ((☃ == cq.a) && (☃ == alz.a.a) && (☃ == ☃)))
      {
        arc ☃ = a(☃, ☃);
        
        bbh ☃ = ☃.d(☃, ☃);
        if ((☃ != ajt.k) && (☃.c(☃.a(☃))) && (☃.a(☃, ☃, 11)))
        {
          aop ☃ = this.c.w();
          ☃.a(☃, ☃, ☃.e(), nh.e, (☃.a() + 1.0F) / 2.0F, ☃.b() * 0.8F);
          ☃.b -= 1;
        }
        return qo.a;
      }
    }
    if (a(☃, ☃, ☃, ☃.a(☃), ☃)) {
      return qo.a;
    }
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃, cq ☃, zj ☃, adq ☃)
  {
    cj ☃ = ☃;
    
    arr<?> ☃ = this.b.g();
    Comparable<?> ☃ = this.b.a(☃);
    
    arc ☃ = ☃.o(☃);
    if (☃.t() == this.b)
    {
      boolean ☃ = ☃.c(alz.a) == alz.a.a;
      if (((☃ == cq.b) && (!☃)) || ((☃ == cq.a) && (☃) && (☃ == ☃.c(☃)))) {
        return true;
      }
    }
    ☃ = ☃.a(☃);
    arc ☃ = ☃.o(☃);
    if ((☃.t() == this.b) && (☃ == ☃.c(☃))) {
      return true;
    }
    return super.a(☃, ☃, ☃, ☃, ☃);
  }
  
  private boolean a(zj ☃, adq ☃, aht ☃, cj ☃, Object ☃)
  {
    arc ☃ = ☃.o(☃);
    if (☃.t() == this.b)
    {
      Comparable<?> ☃ = ☃.c(this.b.g());
      if (☃ == ☃)
      {
        arc ☃ = a(this.b.g(), ☃);
        
        bbh ☃ = ☃.d(☃, ☃);
        if ((☃ != ajt.k) && (☃.c(☃.a(☃))) && (☃.a(☃, ☃, 11)))
        {
          aop ☃ = this.c.w();
          ☃.a(☃, ☃, ☃.e(), nh.e, (☃.a() + 1.0F) / 2.0F, ☃.b() * 0.8F);
          ☃.b -= 1;
        }
        return true;
      }
    }
    return false;
  }
  
  protected <T extends Comparable<T>> arc a(arr<T> ☃, Comparable<?> ☃)
  {
    return this.c.u().a(☃, ☃);
  }
}
