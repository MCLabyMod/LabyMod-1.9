import java.util.List;

public class bej
  extends bfb
{
  private final bhk a;
  private avk f = avk.e();
  private String g;
  private String h;
  private String i;
  private bej.a r;
  private bcz s;
  private bcz t;
  private bcz u;
  
  public bej(bhk ☃, String ☃)
  {
    this.a = ☃;
    
    a(☃);
  }
  
  public String a()
  {
    return this.f.toString();
  }
  
  public void a(String ☃)
  {
    this.f = avk.a(☃);
  }
  
  public void b()
  {
    this.n.clear();
    
    this.g = bwo.a("createWorld.customize.flat.title", new Object[0]);
    this.h = bwo.a("createWorld.customize.flat.tile", new Object[0]);
    this.i = bwo.a("createWorld.customize.flat.height", new Object[0]);
    
    this.r = new bej.a();
    
    this.n.add(this.s = new bcz(2, this.l / 2 - 154, this.m - 52, 100, 20, bwo.a("createWorld.customize.flat.addLayer", new Object[0]) + " (NYI)"));
    this.n.add(this.t = new bcz(3, this.l / 2 - 50, this.m - 52, 100, 20, bwo.a("createWorld.customize.flat.editLayer", new Object[0]) + " (NYI)"));
    this.n.add(this.u = new bcz(4, this.l / 2 - 155, this.m - 52, 150, 20, bwo.a("createWorld.customize.flat.removeLayer", new Object[0])));
    
    this.n.add(new bcz(0, this.l / 2 - 155, this.m - 28, 150, 20, bwo.a("gui.done", new Object[0])));
    this.n.add(new bcz(5, this.l / 2 + 5, this.m - 52, 150, 20, bwo.a("createWorld.customize.presets", new Object[0])));
    this.n.add(new bcz(1, this.l / 2 + 5, this.m - 28, 150, 20, bwo.a("gui.cancel", new Object[0])));
    
    this.s.m = (this.t.m = 0);
    
    this.f.d();
    f();
  }
  
  public void k()
  {
    super.k();
    this.r.p();
  }
  
  protected void a(bcz ☃)
  {
    int ☃ = this.f.c().size() - this.r.u - 1;
    if (☃.k == 1)
    {
      this.j.a(this.a);
    }
    else if (☃.k == 0)
    {
      this.a.a = a();
      this.j.a(this.a);
    }
    else if (☃.k == 5)
    {
      this.j.a(new bey(this));
    }
    else if ((☃.k == 4) && (g()))
    {
      this.f.c().remove(☃);
      this.r.u = Math.min(this.r.u, this.f.c().size() - 1);
    }
    this.f.d();
    f();
  }
  
  public void f()
  {
    boolean ☃ = g();
    this.u.l = ☃;
    this.t.l = ☃;
    
    this.t.l = false;
    this.s.l = false;
  }
  
  private boolean g()
  {
    return (this.r.u > -1) && (this.r.u < this.f.c().size());
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    this.r.a(☃, ☃, ☃);
    a(this.q, this.g, this.l / 2, 8, 16777215);
    
    int ☃ = this.l / 2 - 92 - 16;
    c(this.q, this.h, ☃, 32, 16777215);
    c(this.q, this.i, ☃ + 2 + 213 - this.q.a(this.i), 32, 16777215);
    
    super.a(☃, ☃, ☃);
  }
  
  class a
    extends bdq
  {
    public int u = -1;
    
    public a()
    {
      super(bej.this.l, bej.this.m, 43, bej.this.m - 60, 24);
    }
    
    private void a(int ☃, int ☃, adq ☃)
    {
      e(☃ + 1, ☃ + 1);
      
      bni.D();
      if ((☃ != null) && (☃.b() != null))
      {
        bcd.c();
        bej.this.k.a(☃, ☃ + 2, ☃ + 2);
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
      ☃.b(☃ + 0, ☃ + 18, bej.a(bej.this)).a((☃ + 0) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
      ☃.b(☃ + 18, ☃ + 18, bej.b(bej.this)).a((☃ + 18) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
      ☃.b(☃ + 18, ☃ + 0, bej.c(bej.this)).a((☃ + 18) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
      ☃.b(☃ + 0, ☃ + 0, bej.d(bej.this)).a((☃ + 0) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
      ☃.b();
    }
    
    protected int b()
    {
      return bej.e(bej.this).c().size();
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃)
    {
      this.u = ☃;
      bej.this.f();
    }
    
    protected boolean a(int ☃)
    {
      return ☃ == this.u;
    }
    
    protected void a() {}
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      avl ☃ = (avl)bej.e(bej.this).c().get(bej.e(bej.this).c().size() - ☃ - 1);
      
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
      bej.this.q.a(☃, ☃ + 18 + 5, ☃ + 3, 16777215);
      String ☃;
      String ☃;
      if (☃ == 0)
      {
        ☃ = bwo.a("createWorld.customize.flat.layer.top", new Object[] { Integer.valueOf(☃.b()) });
      }
      else
      {
        String ☃;
        if (☃ == bej.e(bej.this).c().size() - 1) {
          ☃ = bwo.a("createWorld.customize.flat.layer.bottom", new Object[] { Integer.valueOf(☃.b()) });
        } else {
          ☃ = bwo.a("createWorld.customize.flat.layer", new Object[] { Integer.valueOf(☃.b()) });
        }
      }
      bej.this.q.a(☃, ☃ + 2 + 213 - bej.this.q.a(☃), ☃ + 3, 16777215);
    }
    
    protected int d()
    {
      return this.b - 70;
    }
  }
}
