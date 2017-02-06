import java.util.List;

class bey$b
  extends bdq
{
  public int u = -1;
  
  public bey$b(bey parambey)
  {
    super(parambey.j, parambey.l, parambey.m, 80, parambey.m - 37, 24);
  }
  
  private void a(int ☃, int ☃, ado ☃, int ☃)
  {
    e(☃ + 1, ☃ + 1);
    
    bni.D();
    
    bcd.c();
    
    this.v.k.a(new adq(☃, 1, ☃), ☃ + 2, ☃ + 2);
    bcd.a();
    
    bni.E();
  }
  
  private void e(int ☃, int ☃)
  {
    d(☃, ☃, 0, 0);
  }
  
  private void d(int ☃, int ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.a.N().a(bcv.c);
    
    float ☃ = 0.0078125F;
    float ☃ = 0.0078125F;
    int ☃ = 18;
    int ☃ = 18;
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    ☃.a(7, bvp.g);
    ☃.b(☃ + 0, ☃ + 18, bey.a(this.v)).a((☃ + 0) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
    ☃.b(☃ + 18, ☃ + 18, bey.b(this.v)).a((☃ + 18) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
    ☃.b(☃ + 18, ☃ + 0, bey.c(this.v)).a((☃ + 18) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
    ☃.b(☃ + 0, ☃ + 0, bey.d(this.v)).a((☃ + 0) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
    ☃.b();
  }
  
  protected int b()
  {
    return bey.f().size();
  }
  
  protected void a(int ☃, boolean ☃, int ☃, int ☃)
  {
    this.u = ☃;
    this.v.a();
    bey.f(this.v).a(((bey.a)bey.f().get(bey.e(this.v).u)).d);
  }
  
  protected boolean a(int ☃)
  {
    return ☃ == this.u;
  }
  
  protected void a() {}
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    bey.a ☃ = (bey.a)bey.f().get(☃);
    a(☃, ☃, ☃.a, ☃.b);
    this.v.q.a(☃.c, ☃ + 18 + 5, ☃ + 6, 16777215);
  }
}
