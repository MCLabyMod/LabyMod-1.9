import com.google.common.base.Optional;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class yd
  extends rr
{
  private static final Logger b = ;
  private static final ke<Optional<adq>> c = kh.a(yd.class, kg.f);
  private int d;
  private int e;
  private int f = 5;
  private String g;
  private String h;
  public float a = (float)(Math.random() * 3.141592653589793D * 2.0D);
  
  public yd(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃);
    a(0.25F, 0.25F);
    b(☃, ☃, ☃);
    
    this.v = ((float)(Math.random() * 360.0D));
    
    this.s = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    this.t = 0.20000000298023224D;
    this.u = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
  }
  
  public yd(aht ☃, double ☃, double ☃, double ☃, adq ☃)
  {
    this(☃, ☃, ☃, ☃);
    a(☃);
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  public yd(aht ☃)
  {
    super(☃);
    a(0.25F, 0.25F);
    a(new adq(aju.a, 0));
  }
  
  protected void i()
  {
    R().a(c, Optional.absent());
  }
  
  public void m()
  {
    if (k() == null)
    {
      T();
      return;
    }
    super.m();
    if ((this.e > 0) && (this.e != 32767)) {
      this.e -= 1;
    }
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    
    this.t -= 0.03999999910593033D;
    this.Q = j(this.p, (bl().b + bl().e) / 2.0D, this.r);
    d(this.s, this.t, this.u);
    
    boolean ☃ = ((int)this.m != (int)this.p) || ((int)this.n != (int)this.q) || ((int)this.o != (int)this.r);
    if ((☃) || (this.T % 25 == 0))
    {
      if (this.l.o(new cj(this)).a() == axe.i)
      {
        this.t = 0.20000000298023224D;
        this.s = ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
        this.u = ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
        a(ng.bz, 0.4F, 2.0F + this.S.nextFloat() * 0.4F);
      }
      if (!this.l.E) {
        x();
      }
    }
    float ☃ = 0.98F;
    if (this.z) {
      ☃ = this.l.o(new cj(on.c(this.p), on.c(bl().b) - 1, on.c(this.r))).t().z * 0.98F;
    }
    this.s *= ☃;
    this.t *= 0.9800000190734863D;
    this.u *= ☃;
    if (this.z) {
      this.t *= -0.5D;
    }
    if (this.d != 32768) {
      this.d += 1;
    }
    aj();
    if ((!this.l.E) && (this.d >= 6000)) {
      T();
    }
  }
  
  private void x()
  {
    for (yd ☃ : this.l.a(yd.class, bl().b(0.5D, 0.0D, 0.5D))) {
      a(☃);
    }
  }
  
  private boolean a(yd ☃)
  {
    if (☃ == this) {
      return false;
    }
    if ((!☃.au()) || (!au())) {
      return false;
    }
    adq ☃ = k();
    adq ☃ = ☃.k();
    if ((this.e == 32767) || (☃.e == 32767)) {
      return false;
    }
    if ((this.d == 32768) || (☃.d == 32768)) {
      return false;
    }
    if (☃.b() != ☃.b()) {
      return false;
    }
    if ((☃.n() ^ ☃.n())) {
      return false;
    }
    if ((☃.n()) && (!☃.o().equals(☃.o()))) {
      return false;
    }
    if (☃.b() == null) {
      return false;
    }
    if ((☃.b().k()) && (☃.i() != ☃.i())) {
      return false;
    }
    if (☃.b < ☃.b) {
      return ☃.a(this);
    }
    if (☃.b + ☃.b > ☃.c()) {
      return false;
    }
    ☃.b += ☃.b;
    ☃.e = Math.max(☃.e, this.e);
    ☃.d = Math.min(☃.d, this.d);
    ☃.a(☃);
    T();
    
    return true;
  }
  
  public void j()
  {
    this.d = 4800;
  }
  
  public boolean aj()
  {
    if (this.l.a(bl(), axe.h, this))
    {
      if ((!this.V) && (!this.X)) {
        ak();
      }
      this.V = true;
    }
    else
    {
      this.V = false;
    }
    return this.V;
  }
  
  protected void h(int ☃)
  {
    a(rc.a, ☃);
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((k() != null) && (k().b() == ads.cj) && (☃.c())) {
      return false;
    }
    ao();
    this.f = ((int)(this.f - ☃));
    if (this.f <= 0) {
      T();
    }
    return false;
  }
  
  public void b(dn ☃)
  {
    ☃.a("Health", (short)this.f);
    ☃.a("Age", (short)this.d);
    ☃.a("PickupDelay", (short)this.e);
    if (n() != null) {
      ☃.a("Thrower", this.g);
    }
    if (l() != null) {
      ☃.a("Owner", this.h);
    }
    if (k() != null) {
      ☃.a("Item", k().b(new dn()));
    }
  }
  
  public void a(dn ☃)
  {
    this.f = ☃.g("Health");
    this.d = ☃.g("Age");
    if (☃.e("PickupDelay")) {
      this.e = ☃.g("PickupDelay");
    }
    if (☃.e("Owner")) {
      this.h = ☃.l("Owner");
    }
    if (☃.e("Thrower")) {
      this.g = ☃.l("Thrower");
    }
    dn ☃ = ☃.o("Item");
    a(adq.a(☃));
    if (k() == null) {
      T();
    }
  }
  
  public void d(zj ☃)
  {
    if (this.l.E) {
      return;
    }
    adq ☃ = k();
    int ☃ = ☃.b;
    if ((this.e == 0) && ((this.h == null) || (6000 - this.d <= 200) || (this.h.equals(☃.h_()))) && (☃.br.c(☃)))
    {
      if (☃.b() == ado.a(aju.r)) {
        ☃.b(nk.g);
      }
      if (☃.b() == ado.a(aju.s)) {
        ☃.b(nk.g);
      }
      if (☃.b() == ads.aM) {
        ☃.b(nk.t);
      }
      if (☃.b() == ads.k) {
        ☃.b(nk.w);
      }
      if (☃.b() == ads.bC) {
        ☃.b(nk.A);
      }
      if ((☃.b() == ads.k) && (n() != null))
      {
        zj ☃ = this.l.a(n());
        if ((☃ != null) && (☃ != ☃)) {
          ☃.b(nk.x);
        }
      }
      if (!ad()) {
        this.l.a(null, ☃.p, ☃.q, ☃.r, ng.cU, nh.h, 0.2F, ((this.S.nextFloat() - this.S.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      }
      ☃.a(this, ☃);
      if (☃.b <= 0) {
        T();
      }
      ☃.a(nt.d(☃.b()), ☃);
    }
  }
  
  public String h_()
  {
    if (o_()) {
      return bf();
    }
    return di.a("item." + k().a());
  }
  
  public boolean aT()
  {
    return false;
  }
  
  public rr c(int ☃)
  {
    rr ☃ = super.c(☃);
    if ((!this.l.E) && ((☃ instanceof yd))) {
      ((yd)☃).x();
    }
    return ☃;
  }
  
  public adq k()
  {
    adq ☃ = (adq)((Optional)R().a(c)).orNull();
    if (☃ == null)
    {
      if (this.l != null) {
        b.error("Item entity " + O() + " has no item?!");
      }
      return new adq(aju.b);
    }
    return ☃;
  }
  
  public void a(adq ☃)
  {
    R().b(c, Optional.fromNullable(☃));
    R().b(c);
  }
  
  public String l()
  {
    return this.h;
  }
  
  public void d(String ☃)
  {
    this.h = ☃;
  }
  
  public String n()
  {
    return this.g;
  }
  
  public void e(String ☃)
  {
    this.g = ☃;
  }
  
  public int o()
  {
    return this.d;
  }
  
  public void q()
  {
    this.e = 10;
  }
  
  public void r()
  {
    this.e = 0;
  }
  
  public void s()
  {
    this.e = 32767;
  }
  
  public void a(int ☃)
  {
    this.e = ☃;
  }
  
  public boolean t()
  {
    return this.e > 0;
  }
  
  public void v()
  {
    this.d = 59536;
  }
  
  public void w()
  {
    s();
    this.d = 5999;
  }
}
