import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class wc
  extends vw
{
  private static final ke<Boolean> bv = kh.a(wc.class, kg.h);
  private static final Set<ado> bw = Sets.newHashSet(new ado[] { ads.cb, ads.cc, ads.cV });
  private boolean bx;
  private int bz;
  private int bA;
  
  public wc(aht ☃)
  {
    super(☃);
    a(0.9F, 0.9F);
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(1, new uc(this, 1.25D));
    this.bp.a(3, new td(this, 1.0D));
    this.bp.a(4, new up(this, 1.2D, ads.ci, false));
    this.bp.a(4, new up(this, 1.2D, false, bw));
    this.bp.a(5, new tj(this, 1.1D));
    this.bp.a(6, new ug(this, 1.0D));
    this.bp.a(7, new tp(this, zj.class, 6.0F));
    this.bp.a(8, new uf(this));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(10.0D);
    a(yt.d).a(0.25D);
  }
  
  public rr bt()
  {
    if (bu().isEmpty()) {
      return null;
    }
    return (rr)bu().get(0);
  }
  
  public boolean cK()
  {
    rr ☃ = bt();
    if (!(☃ instanceof zj)) {
      return false;
    }
    zj ☃ = (zj)☃;
    
    adq ☃ = ☃.cb();
    if ((☃ != null) && (☃.b() == ads.ci)) {
      return true;
    }
    ☃ = ☃.cc();
    if ((☃ != null) && (☃.b() == ads.ci)) {
      return true;
    }
    return false;
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(bv, Boolean.valueOf(false));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Saddle", cZ());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    o(☃.p("Saddle"));
  }
  
  protected nf G()
  {
    return ng.dP;
  }
  
  protected nf bR()
  {
    return ng.dR;
  }
  
  protected nf bS()
  {
    return ng.dQ;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.dT, 0.15F, 1.0F);
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if (!super.a(☃, ☃, ☃))
    {
      if ((cZ()) && (!this.l.E) && (!aJ()))
      {
        ☃.m(this);
        return true;
      }
      return false;
    }
    return true;
  }
  
  protected void a(boolean ☃, int ☃)
  {
    super.a(☃, ☃);
    if (cZ()) {
      a(ads.aC, 1);
    }
  }
  
  protected kk J()
  {
    return azt.C;
  }
  
  public boolean cZ()
  {
    return ((Boolean)this.Z.a(bv)).booleanValue();
  }
  
  public void o(boolean ☃)
  {
    if (☃) {
      this.Z.b(bv, Boolean.valueOf(true));
    } else {
      this.Z.b(bv, Boolean.valueOf(false));
    }
  }
  
  public void a(ya ☃)
  {
    if ((this.l.E) || (this.F)) {
      return;
    }
    yr ☃ = new yr(this.l);
    ☃.a(rw.a, new adq(ads.D));
    ☃.b(this.p, this.q, this.r, this.v, this.w);
    ☃.m(cR());
    if (o_())
    {
      ☃.c(bf());
      ☃.i(bg());
    }
    this.l.a(☃);
    T();
  }
  
  public void e(float ☃, float ☃)
  {
    super.e(☃, ☃);
    if (☃ > 5.0F) {
      for (zj ☃ : b(zj.class)) {
        ☃.b(nk.u);
      }
    }
  }
  
  public void g(float ☃, float ☃)
  {
    rr ☃ = bu().isEmpty() ? null : (rr)bu().get(0);
    if ((!aJ()) || (!cK()))
    {
      this.P = 0.5F;
      this.aQ = 0.02F;
      super.g(☃, ☃);
      return;
    }
    this.x = (this.v = ☃.v);
    this.w = (☃.w * 0.5F);
    b(this.v, this.w);
    this.aO = (this.aM = this.v);
    
    this.P = 1.0F;
    this.aQ = (ck() * 0.1F);
    if (bx())
    {
      float ☃ = (float)a(yt.d).e() * 0.225F;
      if (this.bx)
      {
        if (this.bz++ > this.bA) {
          this.bx = false;
        }
        ☃ += ☃ * 1.15F * on.a(this.bz / this.bA * 3.1415927F);
      }
      l(☃);
      super.g(0.0F, 1.0F);
    }
    else
    {
      this.s = 0.0D;
      this.t = 0.0D;
      this.u = 0.0D;
    }
    this.aE = this.aF;
    double ☃ = this.p - this.m;
    double ☃ = this.r - this.o;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃) * 4.0F;
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    this.aF += (☃ - this.aF) * 0.4F;
    this.aG += this.aF;
  }
  
  public boolean da()
  {
    if (this.bx) {
      return false;
    }
    this.bx = true;
    this.bz = 0;
    this.bA = (bF().nextInt(841) + 140);
    return true;
  }
  
  public wc b(ro ☃)
  {
    return new wc(this.l);
  }
  
  public boolean e(adq ☃)
  {
    return (☃ != null) && (bw.contains(☃.b()));
  }
}
