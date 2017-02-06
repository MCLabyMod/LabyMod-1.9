import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class yc
  extends rr
{
  private arc e;
  public int a;
  public boolean b = true;
  private boolean f;
  private boolean g;
  private int h = 40;
  private float as = 2.0F;
  public dn c;
  protected static final ke<cj> d = kh.a(yc.class, kg.j);
  
  public yc(aht ☃)
  {
    super(☃);
  }
  
  public yc(aht ☃, double ☃, double ☃, double ☃, arc ☃)
  {
    super(☃);
    this.e = ☃;
    this.i = true;
    a(0.98F, 0.98F);
    b(☃, ☃ + (1.0F - this.H) / 2.0F, ☃);
    
    this.s = 0.0D;
    this.t = 0.0D;
    this.u = 0.0D;
    
    this.m = ☃;
    this.n = ☃;
    this.o = ☃;
    
    a(new cj(this));
  }
  
  public void a(cj ☃)
  {
    this.Z.b(d, ☃);
  }
  
  public cj j()
  {
    return (cj)this.Z.a(d);
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  protected void i()
  {
    this.Z.a(d, cj.a);
  }
  
  public boolean ap()
  {
    return !this.F;
  }
  
  public void m()
  {
    ajt ☃ = this.e.t();
    if (this.e.a() == axe.a)
    {
      T();
      return;
    }
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    if (this.a++ == 0)
    {
      cj ☃ = new cj(this);
      if (this.l.o(☃).t() == ☃)
      {
        this.l.g(☃);
      }
      else if (!this.l.E)
      {
        T();
        return;
      }
    }
    this.t -= 0.03999999910593033D;
    d(this.s, this.t, this.u);
    this.s *= 0.9800000190734863D;
    this.t *= 0.9800000190734863D;
    this.u *= 0.9800000190734863D;
    if (!this.l.E)
    {
      cj ☃ = new cj(this);
      if (this.z)
      {
        arc ☃ = this.l.o(☃);
        if (alh.i(this.l.o(new cj(this.p, this.q - 0.009999999776482582D, this.r))))
        {
          this.z = false;
          return;
        }
        this.s *= 0.699999988079071D;
        this.u *= 0.699999988079071D;
        this.t *= -0.5D;
        if (☃.t() != aju.M)
        {
          T();
          if (!this.f) {
            if ((this.l.a(☃, ☃, true, cq.b, null, null)) && (!alh.i(this.l.o(☃.b()))) && (this.l.a(☃, this.e, 3)))
            {
              if ((☃ instanceof alh)) {
                ((alh)☃).a_(this.l, ☃);
              }
              if ((this.c != null) && ((☃ instanceof alg)))
              {
                apv ☃ = this.l.r(☃);
                if (☃ != null)
                {
                  dn ☃ = new dn();
                  ☃.b(☃);
                  for (String ☃ : this.c.c())
                  {
                    eb ☃ = this.c.c(☃);
                    if ((!☃.equals("x")) && (!☃.equals("y")) && (!☃.equals("z"))) {
                      ☃.a(☃, ☃.b());
                    }
                  }
                  ☃.a(☃);
                  ☃.v_();
                }
              }
            }
            else if ((this.b) && (this.l.U().b("doEntityDrops")))
            {
              a(new adq(☃, 1, ☃.d(this.e)), 0.0F);
            }
          }
        }
      }
      else if (((this.a > 100) && (!this.l.E) && ((☃.q() < 1) || (☃.q() > 256))) || (this.a > 600))
      {
        if ((this.b) && (this.l.U().b("doEntityDrops"))) {
          a(new adq(☃, 1, ☃.d(this.e)), 0.0F);
        }
        T();
      }
    }
  }
  
  public void e(float ☃, float ☃)
  {
    ajt ☃ = this.e.t();
    if (this.g)
    {
      int ☃ = on.f(☃ - 1.0F);
      if (☃ > 0)
      {
        List<rr> ☃ = Lists.newArrayList(this.l.b(this, bl()));
        boolean ☃ = ☃ == aju.cf;
        rc ☃ = ☃ ? rc.o : rc.p;
        for (rr ☃ : ☃) {
          ☃.a(☃, Math.min(on.d(☃ * this.as), this.h));
        }
        if ((☃) && (this.S.nextFloat() < 0.05000000074505806D + ☃ * 0.05D))
        {
          int ☃ = ((Integer)this.e.c(ajk.b)).intValue();
          
          ☃++;
          if (☃ > 2) {
            this.f = true;
          } else {
            this.e = this.e.a(ajk.b, Integer.valueOf(☃));
          }
        }
      }
    }
  }
  
  protected void b(dn ☃)
  {
    ajt ☃ = this.e != null ? this.e.t() : aju.a;
    kk ☃ = (kk)ajt.h.b(☃);
    ☃.a("Block", ☃ == null ? "" : ☃.toString());
    ☃.a("Data", (byte)☃.e(this.e));
    ☃.a("Time", this.a);
    ☃.a("DropItem", this.b);
    ☃.a("HurtEntities", this.g);
    ☃.a("FallHurtAmount", this.as);
    ☃.a("FallHurtMax", this.h);
    if (this.c != null) {
      ☃.a("TileEntityData", this.c);
    }
  }
  
  protected void a(dn ☃)
  {
    int ☃ = ☃.f("Data") & 0xFF;
    if (☃.b("Block", 8)) {
      this.e = ajt.b(☃.l("Block")).a(☃);
    } else if (☃.b("TileID", 99)) {
      this.e = ajt.b(☃.h("TileID")).a(☃);
    } else {
      this.e = ajt.b(☃.f("Tile") & 0xFF).a(☃);
    }
    this.a = ☃.h("Time");
    
    ajt ☃ = this.e.t();
    if (☃.b("HurtEntities", 99))
    {
      this.g = ☃.p("HurtEntities");
      this.as = ☃.j("FallHurtAmount");
      this.h = ☃.h("FallHurtMax");
    }
    else if (☃ == aju.cf)
    {
      this.g = true;
    }
    if (☃.b("DropItem", 99)) {
      this.b = ☃.p("DropItem");
    }
    if (☃.b("TileEntityData", 10)) {
      this.c = ☃.o("TileEntityData");
    }
    if ((☃ == null) || (☃.u().a() == axe.a)) {
      this.e = aju.m.u();
    }
  }
  
  public aht k()
  {
    return this.l;
  }
  
  public void a(boolean ☃)
  {
    this.g = ☃;
  }
  
  public boolean bb()
  {
    return false;
  }
  
  public void a(c ☃)
  {
    super.a(☃);
    if (this.e != null)
    {
      ajt ☃ = this.e.t();
      ☃.a("Immitating block ID", Integer.valueOf(ajt.a(☃)));
      ☃.a("Immitating block data", Integer.valueOf(☃.e(this.e)));
    }
  }
  
  public arc l()
  {
    return this.e;
  }
  
  public boolean br()
  {
    return true;
  }
}
