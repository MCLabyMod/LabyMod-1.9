import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lt
{
  private static final Logger c = ;
  private rr d;
  private int e;
  private int f;
  private int g;
  private long h;
  private long i;
  private long j;
  private int k;
  private int l;
  private int m;
  private double n;
  private double o;
  private double p;
  public int a;
  private double q;
  private double r;
  private double s;
  private boolean t;
  private boolean u;
  private int v;
  private List<rr> w = Collections.emptyList();
  private boolean x;
  private boolean y;
  public boolean b;
  private Set<lr> z = Sets.newHashSet();
  
  public lt(rr ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.u = ☃;
    
    this.h = lm.a(☃.p);
    this.i = lm.a(☃.q);
    this.j = lm.a(☃.r);
    
    this.k = on.d(☃.v * 256.0F / 360.0F);
    this.l = on.d(☃.w * 256.0F / 360.0F);
    
    this.m = on.d(☃.aS() * 256.0F / 360.0F);
    this.y = ☃.z;
  }
  
  public boolean equals(Object ☃)
  {
    if ((☃ instanceof lt)) {
      return ((lt)☃).d.O() == this.d.O();
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.d.O();
  }
  
  public void a(List<zj> ☃)
  {
    this.b = false;
    if ((!this.t) || (this.d.e(this.q, this.r, this.s) > 16.0D))
    {
      this.q = this.d.p;
      this.r = this.d.q;
      this.s = this.d.r;
      this.t = true;
      this.b = true;
      b(☃);
    }
    List<rr> ☃ = this.d.bu();
    if (!☃.equals(this.w))
    {
      this.w = ☃;
      a(new hs(this.d));
    }
    if (((this.d instanceof xs)) && (this.a % 10 == 0))
    {
      xs ☃ = (xs)this.d;
      adq ☃ = ☃.r();
      ayz ☃;
      if ((☃ != null) && ((☃.b() instanceof adw)))
      {
        ☃ = ads.bk.a(☃, this.d.l);
        for (zj ☃ : ☃)
        {
          lr ☃ = (lr)☃;
          ☃.a(☃, ☃);
          
          ff<?> ☃ = ads.bk.a(☃, this.d.l, ☃);
          if (☃ != null) {
            ☃.a.a(☃);
          }
        }
      }
      d();
    }
    if ((this.a % this.g == 0) || (this.d.ai) || (this.d.R().a()))
    {
      if (!this.d.aI())
      {
        this.v += 1;
        long ☃ = lm.a(this.d.p);
        long ☃ = lm.a(this.d.q);
        long ☃ = lm.a(this.d.r);
        int ☃ = on.d(this.d.v * 256.0F / 360.0F);
        int ☃ = on.d(this.d.w * 256.0F / 360.0F);
        
        long ☃ = ☃ - this.h;
        long ☃ = ☃ - this.i;
        long ☃ = ☃ - this.j;
        
        ff<?> ☃ = null;
        
        boolean ☃ = (☃ * ☃ + ☃ * ☃ + ☃ * ☃ >= 128L) || (this.a % 60 == 0);
        boolean ☃ = (Math.abs(☃ - this.k) >= 1) || (Math.abs(☃ - this.l) >= 1);
        if ((this.a > 0) || ((this.d instanceof zm))) {
          if ((☃ < -32768L) || (☃ >= 32768L) || (☃ < -32768L) || (☃ >= 32768L) || (☃ < -32768L) || (☃ >= 32768L) || (this.v > 400) || (this.x) || (this.y != this.d.z))
          {
            this.y = this.d.z;
            this.v = 0;
            c();
            ☃ = new ic(this.d);
          }
          else if (((☃) && (☃)) || ((this.d instanceof zm)))
          {
            ☃ = new gu.b(this.d.O(), ☃, ☃, ☃, (byte)☃, (byte)☃, this.d.z);
          }
          else if (☃)
          {
            ☃ = new gu.a(this.d.O(), ☃, ☃, ☃, this.d.z);
          }
          else if (☃)
          {
            ☃ = new gu.c(this.d.O(), (byte)☃, (byte)☃, this.d.z);
          }
        }
        boolean ☃ = this.u;
        if (((this.d instanceof sa)) && (((sa)this.d).cB())) {
          ☃ = true;
        }
        if (☃)
        {
          double ☃ = this.d.s - this.n;
          double ☃ = this.d.t - this.o;
          double ☃ = this.d.u - this.p;
          
          double ☃ = 0.02D;
          
          double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
          if ((☃ > 4.0E-4D) || ((☃ > 0.0D) && (this.d.s == 0.0D) && (this.d.t == 0.0D) && (this.d.u == 0.0D)))
          {
            this.n = this.d.s;
            this.o = this.d.t;
            this.p = this.d.u;
            a(new hn(this.d.O(), this.n, this.o, this.p));
          }
        }
        if (☃ != null) {
          a(☃);
        }
        d();
        if (☃)
        {
          this.h = ☃;
          this.i = ☃;
          this.j = ☃;
        }
        if (☃)
        {
          this.k = ☃;
          this.l = ☃;
        }
        this.x = false;
      }
      else
      {
        int ☃ = on.d(this.d.v * 256.0F / 360.0F);
        int ☃ = on.d(this.d.w * 256.0F / 360.0F);
        boolean ☃ = (Math.abs(☃ - this.k) >= 1) || (Math.abs(☃ - this.l) >= 1);
        if (☃)
        {
          a(new gu.c(this.d.O(), (byte)☃, (byte)☃, this.d.z));
          this.k = ☃;
          this.l = ☃;
        }
        this.h = lm.a(this.d.p);
        this.i = lm.a(this.d.q);
        this.j = lm.a(this.d.r);
        
        d();
        
        this.x = true;
      }
      int ☃ = on.d(this.d.aS() * 256.0F / 360.0F);
      if (Math.abs(☃ - this.m) >= 1)
      {
        a(new hg(this.d, (byte)☃));
        this.m = ☃;
      }
      this.d.ai = false;
    }
    this.a += 1;
    if (this.d.D)
    {
      b(new hn(this.d));
      this.d.D = false;
    }
  }
  
  private void d()
  {
    kh ☃ = this.d.R();
    if (☃.a()) {
      b(new hl(this.d.O(), ☃, false));
    }
    if ((this.d instanceof sa))
    {
      sr ☃ = (sr)((sa)this.d).bZ();
      Set<sm> ☃ = ☃.b();
      if (!☃.isEmpty()) {
        b(new id(this.d.O(), ☃));
      }
      ☃.clear();
    }
  }
  
  public void a(ff<?> ☃)
  {
    for (lr ☃ : this.z) {
      ☃.a.a(☃);
    }
  }
  
  public void b(ff<?> ☃)
  {
    a(☃);
    if ((this.d instanceof lr)) {
      ((lr)this.d).a.a(☃);
    }
  }
  
  public void a()
  {
    for (lr ☃ : this.z)
    {
      this.d.c(☃);
      ☃.c(this.d);
    }
  }
  
  public void a(lr ☃)
  {
    if (this.z.contains(☃))
    {
      this.d.c(☃);
      ☃.c(this.d);
      this.z.remove(☃);
    }
  }
  
  public void b(lr ☃)
  {
    if (☃ == this.d) {
      return;
    }
    if (c(☃))
    {
      if ((!this.z.contains(☃)) && ((e(☃)) || (this.d.k)))
      {
        this.z.add(☃);
        ff<?> ☃ = e();
        ☃.a.a(☃);
        if (!this.d.R().d()) {
          ☃.a.a(new hl(this.d.O(), this.d.R(), true));
        }
        boolean ☃ = this.u;
        if ((this.d instanceof sa))
        {
          sr ☃ = (sr)((sa)this.d).bZ();
          Collection<sm> ☃ = ☃.c();
          if (!☃.isEmpty()) {
            ☃.a.a(new id(this.d.O(), ☃));
          }
          if (((sa)this.d).cB()) {
            ☃ = true;
          }
        }
        this.n = this.d.s;
        this.o = this.d.t;
        this.p = this.d.u;
        if ((☃) && (!(☃ instanceof fm))) {
          ☃.a.a(new hn(this.d.O(), this.d.s, this.d.t, this.d.u));
        }
        if ((this.d instanceof sa)) {
          for (rw ☃ : rw.values())
          {
            adq ☃ = ((sa)this.d).a(☃);
            if (☃ != null) {
              ☃.a.a(new ho(this.d.O(), ☃, ☃));
            }
          }
        }
        if ((this.d instanceof zj))
        {
          zj ☃ = (zj)this.d;
          if (☃.cl()) {
            ☃.a.a(new hb(☃, new cj(this.d)));
          }
        }
        if ((this.d instanceof sa))
        {
          sa ☃ = (sa)this.d;
          for (rl ☃ : ☃.bO()) {
            ☃.a.a(new ie(this.d.O(), ☃));
          }
        }
        this.d.b(☃);
        ☃.d(this.d);
      }
    }
    else if (this.z.contains(☃))
    {
      this.z.remove(☃);
      this.d.c(☃);
      ☃.c(this.d);
    }
  }
  
  public boolean c(lr ☃)
  {
    double ☃ = ☃.p - this.h / 4096.0D;
    double ☃ = ☃.r - this.j / 4096.0D;
    int ☃ = Math.min(this.e, this.f);
    return (☃ >= -☃) && (☃ <= ☃) && (☃ >= -☃) && (☃ <= ☃) && (this.d.a(☃));
  }
  
  private boolean e(lr ☃)
  {
    return ☃.x().w().a(☃, this.d.ab, this.d.ad);
  }
  
  public void b(List<zj> ☃)
  {
    for (int ☃ = 0; ☃ < ☃.size(); ☃++) {
      b((lr)☃.get(☃));
    }
  }
  
  private ff<?> e()
  {
    if (this.d.F) {
      c.warn("Fetching addPacket for removed entity");
    }
    if ((this.d instanceof yd)) {
      return new fj(this.d, 2, 1);
    }
    if ((this.d instanceof lr)) {
      return new fo((zj)this.d);
    }
    if ((this.d instanceof aah))
    {
      aah ☃ = (aah)this.d;
      return new fj(this.d, 10, ☃.v().a());
    }
    if ((this.d instanceof aag)) {
      return new fj(this.d, 1);
    }
    if ((this.d instanceof rq))
    {
      this.m = on.d(this.d.aS() * 256.0F / 360.0F);
      return new fm((sa)this.d);
    }
    if ((this.d instanceof xw))
    {
      rr ☃ = ((xw)this.d).a;
      return new fj(this.d, 90, ☃ != null ? ☃.O() : this.d.O());
    }
    if ((this.d instanceof zx))
    {
      rr ☃ = ((zx)this.d).e;
      return new fj(this.d, 91, 1 + (☃ != null ? ☃.O() : this.d.O()));
    }
    if ((this.d instanceof aad))
    {
      rr ☃ = ((zm)this.d).e;
      return new fj(this.d, 60, 1 + (☃ != null ? ☃.O() : this.d.O()));
    }
    if ((this.d instanceof zw)) {
      return new fj(this.d, 61);
    }
    if ((this.d instanceof aac)) {
      return new fj(this.d, 73);
    }
    if ((this.d instanceof aab)) {
      return new fj(this.d, 75);
    }
    if ((this.d instanceof aaa)) {
      return new fj(this.d, 65);
    }
    if ((this.d instanceof zo)) {
      return new fj(this.d, 72);
    }
    if ((this.d instanceof zq)) {
      return new fj(this.d, 76);
    }
    if ((this.d instanceof zp))
    {
      zp ☃ = (zp)this.d;
      fj ☃ = null;
      int ☃ = 63;
      if ((this.d instanceof zv)) {
        ☃ = 64;
      } else if ((this.d instanceof zn)) {
        ☃ = 93;
      } else if ((this.d instanceof aae)) {
        ☃ = 66;
      }
      if (☃.a != null) {
        ☃ = new fj(this.d, ☃, ((zp)this.d).a.O());
      } else {
        ☃ = new fj(this.d, ☃, 0);
      }
      ☃.a((int)(☃.b * 8000.0D));
      ☃.b((int)(☃.c * 8000.0D));
      ☃.c((int)(☃.d * 8000.0D));
      return ☃;
    }
    if ((this.d instanceof zu))
    {
      fj ☃ = new fj(this.d, 67, 0);
      ☃.a((int)(this.d.s * 8000.0D));
      ☃.b((int)(this.d.t * 8000.0D));
      ☃.c((int)(this.d.u * 8000.0D));
      return ☃;
    }
    if ((this.d instanceof zz)) {
      return new fj(this.d, 62);
    }
    if ((this.d instanceof ye)) {
      return new fj(this.d, 50);
    }
    if ((this.d instanceof wt)) {
      return new fj(this.d, 51);
    }
    if ((this.d instanceof yc))
    {
      yc ☃ = (yc)this.d;
      return new fj(this.d, 70, ajt.j(☃.l()));
    }
    if ((this.d instanceof xq)) {
      return new fj(this.d, 78);
    }
    if ((this.d instanceof xu)) {
      return new fn((xu)this.d);
    }
    if ((this.d instanceof xs))
    {
      xs ☃ = (xs)this.d;
      fj ☃ = new fj(this.d, 71, ☃.b.b(), ☃.q());
      return ☃;
    }
    if ((this.d instanceof xt))
    {
      xt ☃ = (xt)this.d;
      fj ☃ = new fj(this.d, 77, 0, ☃.q());
      return ☃;
    }
    if ((this.d instanceof rx)) {
      return new fk((rx)this.d);
    }
    if ((this.d instanceof rp)) {
      return new fj(this.d, 3);
    }
    throw new IllegalArgumentException("Don't know how to add " + this.d.getClass() + "!");
  }
  
  public void d(lr ☃)
  {
    if (this.z.contains(☃))
    {
      this.z.remove(☃);
      this.d.c(☃);
      ☃.c(this.d);
    }
  }
  
  public rr b()
  {
    return this.d;
  }
  
  public void a(int ☃)
  {
    this.f = ☃;
  }
  
  public void c()
  {
    this.t = false;
  }
}
