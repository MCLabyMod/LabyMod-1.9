import java.util.List;

class bej$a
  extends bdq
{
  public int u = -1;
  
  public bej$a(bej parambej)
  {
    super(parambej.j, parambej.l, parambej.m, 43, parambej.m - 60, 24);
  }
  
  private void a(int ☃, int ☃, adq ☃)
  {
    e(☃ + 1, ☃ + 1);
    
    bni.D();
    if ((☃ != null) && (☃.b() != null))
    {
      bcd.c();
      this.v.k.a(☃, ☃ + 2, ☃ + 2);
      bcd.a();
    }
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
    ☃.b(☃ + 0, ☃ + 18, bej.a(this.v)).a((☃ + 0) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
    ☃.b(☃ + 18, ☃ + 18, bej.b(this.v)).a((☃ + 18) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
    ☃.b(☃ + 18, ☃ + 0, bej.c(this.v)).a((☃ + 18) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
    ☃.b(☃ + 0, ☃ + 0, bej.d(this.v)).a((☃ + 0) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
    ☃.b();
  }
  
  protected int b()
  {
    return bej.e(this.v).c().size();
  }
  
  protected void a(int ☃, boolean ☃, int ☃, int ☃)
  {
    this.u = ☃;
    this.v.f();
  }
  
  protected boolean a(int ☃)
  {
    return ☃ == this.u;
  }
  
  protected void a() {}
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    avl ☃ = (avl)bej.e(this.v).c().get(bej.e(this.v).c().size() - ☃ - 1);
    
    arc ☃ = ☃.c();
    ajt ☃ = ☃.t();
    ado ☃ = ado.a(☃);
    adq ☃ = (☃ == aju.a) || (☃ == null) ? null : new adq(☃, 1, ☃.e(☃));
    String ☃ = ☃ == null ? bwo.a("createWorld.customize.flat.air", new Object[0]) : ☃.a(☃);
    if (☃ == null)
    {
      if ((☃ == aju.j) || (☃ == aju.i)) {
        ☃ = ads.az;
      } else if ((☃ == aju.l) || (☃ == aju.k)) {
        ☃ = ads.aA;
      }
      if (☃ != null)
      {
        ☃ = new adq(☃, 1, ☃.e(☃));
        ☃ = ☃.c();
      }
    }
    a(☃, ☃, ☃);
    this.v.q.a(☃, ☃ + 18 + 5, ☃ + 3, 16777215);
    String ☃;
    String ☃;
    if (☃ == 0)
    {
      ☃ = bwo.a("createWorld.customize.flat.layer.top", new Object[] { Integer.valueOf(☃.b()) });
    }
    else
    {
      String ☃;
      if (☃ == bej.e(this.v).c().size() - 1) {
        ☃ = bwo.a("createWorld.customize.flat.layer.bottom", new Object[] { Integer.valueOf(☃.b()) });
      } else {
        ☃ = bwo.a("createWorld.customize.flat.layer", new Object[] { Integer.valueOf(☃.b()) });
      }
    }
    this.v.q.a(☃, ☃ + 2 + 213 - this.v.q.a(☃), ☃ + 3, 16777215);
  }
  
  protected int d()
  {
    return this.b - 70;
  }
}
