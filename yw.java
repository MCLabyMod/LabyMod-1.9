import java.util.Calendar;
import java.util.Random;

public class yw
  extends yq
  implements ys
{
  private static final ke<Integer> a = kh.a(yw.class, kg.b);
  private static final ke<Boolean> b = kh.a(yw.class, kg.h);
  private final ui c = new ui(this, 1.0D, 20, 15.0F);
  private final ts bv = new ts(this, 1.2D, false)
  {
    public void d()
    {
      super.d();
      yw.this.a(false);
    }
    
    public void c()
    {
      super.c();
      yw.this.a(true);
    }
  };
  
  public yw(aht ☃)
  {
    super(☃);
    
    o();
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(2, new uk(this));
    this.bp.a(3, new tg(this, 1.0D));
    this.bp.a(3, new ta(this, wj.class, 6.0F, 1.0D, 1.2D));
    this.bp.a(5, new ug(this, 1.0D));
    this.bp.a(6, new tp(this, zj.class, 8.0F));
    this.bp.a(6, new uf(this));
    
    this.bq.a(1, new uv(this, false, new Class[0]));
    this.bq.a(2, new uy(this, zj.class, true));
    this.bq.a(3, new uy(this, wh.class, true));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.d).a(0.25D);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Integer.valueOf(0));
    this.Z.a(b, Boolean.valueOf(false));
  }
  
  protected nf G()
  {
    return ng.fh;
  }
  
  protected nf bR()
  {
    return ng.fm;
  }
  
  protected nf bS()
  {
    return ng.fi;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.fo, 0.15F, 1.0F);
  }
  
  public boolean B(rr ☃)
  {
    if (super.B(☃))
    {
      if ((da() == 1) && ((☃ instanceof sa))) {
        ((sa)☃).c(new rl(rm.t, 200));
      }
      return true;
    }
    return false;
  }
  
  public sf ca()
  {
    return sf.b;
  }
  
  public void n()
  {
    if ((this.l.B()) && (!this.l.E))
    {
      float ☃ = e(1.0F);
      cj ☃ = (by() instanceof aag) ? new cj(this.p, Math.round(this.q), this.r).a() : new cj(this.p, Math.round(this.q), this.r);
      if ((☃ > 0.5F) && (this.S.nextFloat() * 30.0F < (☃ - 0.4F) * 2.0F) && (this.l.h(☃)))
      {
        boolean ☃ = true;
        
        adq ☃ = a(rw.f);
        if (☃ != null)
        {
          if (☃.e())
          {
            ☃.b(☃.h() + this.S.nextInt(2));
            if (☃.h() >= ☃.j())
            {
              b(☃);
              a(rw.f, null);
            }
          }
          ☃ = false;
        }
        if (☃) {
          g(8);
        }
      }
    }
    if (this.l.E) {
      b(da());
    }
    super.n();
  }
  
  public void aw()
  {
    super.aw();
    if ((by() instanceof sh))
    {
      sh ☃ = (sh)by();
      this.aM = ☃.aM;
    }
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if (((☃.i() instanceof zm)) && ((☃.j() instanceof zj)))
    {
      zj ☃ = (zj)☃.j();
      double ☃ = ☃.p - this.p;
      double ☃ = ☃.r - this.r;
      if (☃ * ☃ + ☃ * ☃ >= 2500.0D) {
        ☃.b(nk.v);
      }
    }
    else if (((☃.j() instanceof yi)) && 
      (((yi)☃.j()).o()) && (((yi)☃.j()).dd()))
    {
      ((yi)☃.j()).de();
      a(new adq(ads.ch, 1, da() == 1 ? 1 : 0), 0.0F);
    }
  }
  
  protected kk J()
  {
    return da() == 1 ? azt.ak : azt.aj;
  }
  
  protected void a(ql ☃)
  {
    super.a(☃);
    
    a(rw.a, new adq(ads.f));
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    if (((this.l.s instanceof asx)) && (bF().nextInt(5) > 0))
    {
      this.bp.a(4, this.bv);
      
      a(1);
      a(rw.a, new adq(ads.s));
      a(yt.e).a(4.0D);
    }
    else
    {
      this.bp.a(4, this.c);
      
      a(☃);
      b(☃);
    }
    l(this.S.nextFloat() < 0.55F * ☃.c());
    if (a(rw.f) == null)
    {
      Calendar ☃ = this.l.ac();
      if ((☃.get(2) + 1 == 10) && (☃.get(5) == 31) && (this.S.nextFloat() < 0.25F))
      {
        a(rw.f, new adq(this.S.nextFloat() < 0.1F ? aju.aZ : aju.aU));
        this.bs[rw.f.b()] = 0.0F;
      }
    }
    return ☃;
  }
  
  public void o()
  {
    if ((this.l != null) && (!this.l.E))
    {
      this.bp.a(this.bv);
      this.bp.a(this.c);
      
      adq ☃ = cb();
      if ((☃ != null) && (☃.b() == ads.f)) {
        this.bp.a(4, this.c);
      } else {
        this.bp.a(4, this.bv);
      }
    }
  }
  
  public void a(sa ☃, float ☃)
  {
    zm ☃ = new aad(this.l, this);
    double ☃ = ☃.p - this.p;
    double ☃ = ☃.bl().b + ☃.H / 3.0F - ☃.q;
    double ☃ = ☃.r - this.r;
    double ☃ = on.a(☃ * ☃ + ☃ * ☃);
    ☃.c(☃, ☃ + ☃ * 0.20000000298023224D, ☃, 1.6F, 14 - this.l.ae().a() * 4);
    int ☃ = ago.a(agq.u, this);
    int ☃ = ago.a(agq.v, this);
    
    ☃.c(☃ * 2.0F + (this.S.nextGaussian() * 0.25D + this.l.ae().a() * 0.11F));
    if (☃ > 0) {
      ☃.c(☃.k() + ☃ * 0.5D + 0.5D);
    }
    if (☃ > 0) {
      ☃.a(☃);
    }
    if ((ago.a(agq.w, this) > 0) || (da() == 1)) {
      ☃.g(100);
    }
    a(ng.fn, 1.0F, 1.0F / (bF().nextFloat() * 0.4F + 0.8F));
    this.l.a(☃);
  }
  
  public int da()
  {
    return ((Integer)this.Z.a(a)).intValue();
  }
  
  public void a(int ☃)
  {
    this.Z.b(a, Integer.valueOf(☃));
    
    this.Y = (☃ == 1);
    b(☃);
  }
  
  private void b(int ☃)
  {
    if (☃ == 1) {
      a(0.7F, 2.4F);
    } else {
      a(0.6F, 1.99F);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("SkeletonType", 99))
    {
      int ☃ = ☃.f("SkeletonType");
      a(☃);
    }
    o();
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("SkeletonType", (byte)da());
  }
  
  public void a(rw ☃, adq ☃)
  {
    super.a(☃, ☃);
    if ((!this.l.E) && (☃ == rw.a)) {
      o();
    }
  }
  
  public float bn()
  {
    if (da() == 1) {
      return 2.1F;
    }
    return 1.74F;
  }
  
  public double ax()
  {
    return -0.35D;
  }
  
  public boolean db()
  {
    return ((Boolean)this.Z.a(b)).booleanValue();
  }
  
  public void a(boolean ☃)
  {
    this.Z.b(b, Boolean.valueOf(☃));
  }
}
