public class aqf
  extends apv
{
  private ado a;
  private int f;
  
  public aqf() {}
  
  public aqf(ado ☃, int ☃)
  {
    this.a = ☃;
    this.f = ☃;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    kk ☃ = (kk)ado.f.b(this.a);
    ☃.a("Item", ☃ == null ? "" : ☃.toString());
    ☃.a("Data", this.f);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("Item", 8)) {
      this.a = ado.d(☃.l("Item"));
    } else {
      this.a = ado.c(☃.h("Item"));
    }
    this.f = ☃.h("Data");
  }
  
  public ff<?> D_()
  {
    dn ☃ = new dn();
    b(☃);
    
    ☃.q("Item");
    ☃.a("Item", ado.a(this.a));
    return new fs(this.c, 5, ☃);
  }
  
  public void a(ado ☃, int ☃)
  {
    this.a = ☃;
    this.f = ☃;
  }
  
  public adq b()
  {
    return this.a == null ? null : new adq(this.a, 1, this.f);
  }
  
  public ado c()
  {
    return this.a;
  }
  
  public int d()
  {
    return this.f;
  }
}
