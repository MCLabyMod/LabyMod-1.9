import java.util.Random;

public class yi
  extends yq
{
  private static final ke<Integer> a = kh.a(yi.class, kg.b);
  private static final ke<Boolean> b = kh.a(yi.class, kg.h);
  private static final ke<Boolean> c = kh.a(yi.class, kg.h);
  private int bv;
  private int bw;
  private int bx = 30;
  private int by = 3;
  private int bz = 0;
  
  public yi(aht ☃)
  {
    super(☃);
    
    a(0.6F, 1.7F);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(2, new un(this));
    this.bp.a(3, new ta(this, wb.class, 6.0F, 1.0D, 1.2D));
    this.bp.a(4, new ts(this, 1.0D, false));
    this.bp.a(5, new ug(this, 0.8D));
    this.bp.a(6, new tp(this, zj.class, 8.0F));
    this.bp.a(6, new uf(this));
    
    this.bq.a(1, new uy(this, zj.class, true));
    this.bq.a(2, new uv(this, false, new Class[0]));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.d).a(0.25D);
  }
  
  public int aW()
  {
    if (A() == null) {
      return 3;
    }
    return 3 + (int)(bQ() - 1.0F);
  }
  
  public void e(float ☃, float ☃)
  {
    super.e(☃, ☃);
    
    this.bw = ((int)(this.bw + ☃ * 1.5F));
    if (this.bw > this.bx - 5) {
      this.bw = (this.bx - 5);
    }
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Integer.valueOf(-1));
    this.Z.a(b, Boolean.valueOf(false));
    this.Z.a(c, Boolean.valueOf(false));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (((Boolean)this.Z.a(b)).booleanValue()) {
      ☃.a("powered", true);
    }
    ☃.a("Fuse", (short)this.bx);
    ☃.a("ExplosionRadius", (byte)this.by);
    ☃.a("ignited", db());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.Z.b(b, Boolean.valueOf(☃.p("powered")));
    if (☃.b("Fuse", 99)) {
      this.bx = ☃.g("Fuse");
    }
    if (☃.b("ExplosionRadius", 99)) {
      this.by = ☃.f("ExplosionRadius");
    }
    if (☃.p("ignited")) {
      dc();
    }
  }
  
  public void m()
  {
    if (au())
    {
      this.bv = this.bw;
      if (db()) {
        a(1);
      }
      int ☃ = da();
      if ((☃ > 0) && (this.bw == 0)) {
        a(ng.at, 1.0F, 0.5F);
      }
      this.bw += ☃;
      if (this.bw < 0) {
        this.bw = 0;
      }
      if (this.bw >= this.bx)
      {
        this.bw = this.bx;
        df();
      }
    }
    super.m();
  }
  
  protected nf bR()
  {
    return ng.as;
  }
  
  protected nf bS()
  {
    return ng.ar;
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if (this.l.U().b("doMobLoot")) {
      if ((☃.j() instanceof yw))
      {
        int ☃ = ado.a(ads.cA);
        int ☃ = ado.a(ads.cL);
        int ☃ = ☃ + this.S.nextInt(☃ - ☃ + 1);
        a(ado.c(☃), 1);
      }
      else if (((☃.j() instanceof yi)) && 
        (☃.j() != this) && (((yi)☃.j()).o()) && (((yi)☃.j()).dd()))
      {
        ((yi)☃.j()).de();
        a(new adq(ads.ch, 1, 4), 0.0F);
      }
    }
  }
  
  public boolean B(rr ☃)
  {
    return true;
  }
  
  public boolean o()
  {
    return ((Boolean)this.Z.a(b)).booleanValue();
  }
  
  public float a(float ☃)
  {
    return (this.bv + (this.bw - this.bv) * ☃) / (this.bx - 2);
  }
  
  protected kk J()
  {
    return azt.p;
  }
  
  public int da()
  {
    return ((Integer)this.Z.a(a)).intValue();
  }
  
  public void a(int ☃)
  {
    this.Z.b(a, Integer.valueOf(☃));
  }
  
  public void a(ya ☃)
  {
    super.a(☃);
    this.Z.b(b, Boolean.valueOf(true));
  }
  
  protected boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.d))
    {
      this.l.a(☃, this.p, this.q, this.r, ng.bw, bz(), 1.0F, this.S.nextFloat() * 0.4F + 0.8F);
      ☃.a(☃);
      if (!this.l.E)
      {
        dc();
        ☃.a(1, ☃);
        return true;
      }
    }
    return super.a(☃, ☃, ☃);
  }
  
  private void df()
  {
    if (!this.l.E)
    {
      boolean ☃ = this.l.U().b("mobGriefing");
      float ☃ = o() ? 2.0F : 1.0F;
      this.aT = true;
      this.l.a(this, this.p, this.q, this.r, this.by * ☃, ☃);
      T();
    }
  }
  
  public boolean db()
  {
    return ((Boolean)this.Z.a(c)).booleanValue();
  }
  
  public void dc()
  {
    this.Z.b(c, Boolean.valueOf(true));
  }
  
  public boolean dd()
  {
    return (this.bz < 1) && (this.l.U().b("doMobLoot"));
  }
  
  public void de()
  {
    this.bz += 1;
  }
}
