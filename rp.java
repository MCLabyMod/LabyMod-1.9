import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class rp
  extends rr
{
  private static final ke<Float> a = kh.a(rp.class, kg.c);
  private static final ke<Integer> b = kh.a(rp.class, kg.b);
  private static final ke<Boolean> c = kh.a(rp.class, kg.h);
  private static final ke<Integer> d = kh.a(rp.class, kg.b);
  private afe e = afh.a;
  private final List<rl> f = Lists.newArrayList();
  private final Map<rr, Integer> g = Maps.newHashMap();
  private int h = 600;
  private int as = 20;
  private int at = 20;
  private boolean au;
  private int av;
  private float aw;
  private float ax;
  private sa ay;
  private UUID az;
  
  public rp(aht ☃)
  {
    super(☃);
    this.Q = true;
    this.Y = true;
    a(3.0F);
  }
  
  public rp(aht ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    b(☃, ☃, ☃);
  }
  
  protected void i()
  {
    R().a(b, Integer.valueOf(0));
    R().a(a, Float.valueOf(0.5F));
    R().a(c, Boolean.valueOf(false));
    R().a(d, Integer.valueOf(cy.p.c()));
  }
  
  public void a(float ☃)
  {
    double ☃ = this.p;
    double ☃ = this.q;
    double ☃ = this.r;
    a(☃ * 2.0F, 0.5F);
    b(☃, ☃, ☃);
    if (!this.l.E) {
      R().b(a, Float.valueOf(☃));
    }
  }
  
  public float j()
  {
    return ((Float)R().a(a)).floatValue();
  }
  
  public void a(afe ☃)
  {
    this.e = ☃;
    if (!this.au) {
      if ((☃ == afh.a) && (this.f.isEmpty())) {
        R().b(b, Integer.valueOf(0));
      } else {
        R().b(b, Integer.valueOf(afg.a(afg.a(☃, this.f))));
      }
    }
  }
  
  public void a(rl ☃)
  {
    this.f.add(☃);
    if (!this.au) {
      R().b(b, Integer.valueOf(afg.a(afg.a(this.e, this.f))));
    }
  }
  
  public int k()
  {
    return ((Integer)R().a(b)).intValue();
  }
  
  public void a(int ☃)
  {
    this.au = true;
    R().b(b, Integer.valueOf(☃));
  }
  
  public cy l()
  {
    return cy.a(((Integer)R().a(d)).intValue());
  }
  
  public void a(cy ☃)
  {
    R().b(d, Integer.valueOf(☃.c()));
  }
  
  protected void a(boolean ☃)
  {
    R().b(c, Boolean.valueOf(☃));
  }
  
  public boolean n()
  {
    return ((Boolean)R().a(c)).booleanValue();
  }
  
  public int o()
  {
    return this.h;
  }
  
  public void b(int ☃)
  {
    this.h = ☃;
  }
  
  public void m()
  {
    super.m();
    boolean ☃ = n();
    
    float ☃ = j();
    List<rl> ☃;
    if (this.l.E)
    {
      cy ☃ = l();
      if (☃)
      {
        if (this.S.nextBoolean())
        {
          int[] ☃ = new int[☃.d()];
          for (int ☃ = 0; ☃ < 2; ☃++)
          {
            float ☃ = this.S.nextFloat() * 6.2831855F;
            float ☃ = on.c(this.S.nextFloat()) * 0.2F;
            float ☃ = on.b(☃) * ☃;
            float ☃ = on.a(☃) * ☃;
            if (☃ == cy.p)
            {
              int ☃ = this.S.nextBoolean() ? 16777215 : k();
              int ☃ = ☃ >> 16 & 0xFF;
              int ☃ = ☃ >> 8 & 0xFF;
              int ☃ = ☃ & 0xFF;
              this.l.a(cy.p, this.p + ☃, this.q, this.r + ☃, ☃ / 255.0F, ☃ / 255.0F, ☃ / 255.0F, new int[0]);
            }
            else
            {
              this.l.a(☃, this.p + ☃, this.q, this.r + ☃, 0.0D, 0.0D, 0.0D, ☃);
            }
          }
        }
      }
      else
      {
        float ☃ = 3.1415927F * ☃ * ☃;
        
        int[] ☃ = new int[☃.d()];
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          float ☃ = this.S.nextFloat() * 6.2831855F;
          float ☃ = on.c(this.S.nextFloat()) * ☃;
          float ☃ = on.b(☃) * ☃;
          float ☃ = on.a(☃) * ☃;
          if (☃ == cy.p)
          {
            int ☃ = k();
            int ☃ = ☃ >> 16 & 0xFF;
            int ☃ = ☃ >> 8 & 0xFF;
            int ☃ = ☃ & 0xFF;
            this.l.a(cy.p, this.p + ☃, this.q, this.r + ☃, ☃ / 255.0F, ☃ / 255.0F, ☃ / 255.0F, new int[0]);
          }
          else
          {
            this.l.a(☃, this.p + ☃, this.q, this.r + ☃, (0.5D - this.S.nextDouble()) * 0.15D, 0.009999999776482582D, (0.5D - this.S.nextDouble()) * 0.15D, ☃);
          }
        }
      }
    }
    else
    {
      if (this.T >= this.as + this.h)
      {
        T();
        return;
      }
      boolean ☃ = this.T < this.as;
      if (☃ != ☃) {
        a(☃);
      }
      if (☃) {
        return;
      }
      if (this.ax != 0.0F)
      {
        ☃ += this.ax;
        if (☃ < 0.5F)
        {
          T();
          return;
        }
        a(☃);
      }
      if (this.T % 5 == 0)
      {
        for (Iterator<Map.Entry<rr, Integer>> ☃ = this.g.entrySet().iterator(); ☃.hasNext();)
        {
          Map.Entry<rr, Integer> ☃ = (Map.Entry)☃.next();
          if (this.T >= ((Integer)☃.getValue()).intValue()) {
            ☃.remove();
          }
        }
        ☃ = Lists.newArrayList();
        for (rl ☃ : this.e.a()) {
          ☃.add(new rl(☃.a(), ☃.b() / 4, ☃.c(), ☃.d(), ☃.e()));
        }
        ☃.addAll(this.f);
        if (☃.isEmpty())
        {
          this.g.clear();
        }
        else
        {
          List<sa> ☃ = this.l.a(sa.class, bl());
          if (!☃.isEmpty()) {
            for (sa ☃ : ☃) {
              if ((!this.g.containsKey(☃)) && (☃.cD()))
              {
                double ☃ = ☃.p - this.p;
                double ☃ = ☃.r - this.r;
                double ☃ = ☃ * ☃ + ☃ * ☃;
                if (☃ <= ☃ * ☃)
                {
                  this.g.put(☃, Integer.valueOf(this.T + this.at));
                  for (rl ☃ : ☃) {
                    if (☃.a().b()) {
                      ☃.a().a(this, w(), ☃, ☃.c(), 0.5D);
                    } else {
                      ☃.c(new rl(☃));
                    }
                  }
                  if (this.aw != 0.0F)
                  {
                    ☃ += this.aw;
                    if (☃ < 0.5F)
                    {
                      T();
                      return;
                    }
                    a(☃);
                  }
                  if (this.av != 0)
                  {
                    this.h += this.av;
                    if (this.h <= 0)
                    {
                      T();
                      return;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void b(float ☃)
  {
    this.aw = ☃;
  }
  
  public void c(float ☃)
  {
    this.ax = ☃;
  }
  
  public void e(int ☃)
  {
    this.as = ☃;
  }
  
  public void a(sa ☃)
  {
    this.ay = ☃;
    this.az = (☃ == null ? null : ☃.bc());
  }
  
  public sa w()
  {
    if ((this.ay == null) && (this.az != null) && ((this.l instanceof lp)))
    {
      rr ☃ = ((lp)this.l).a(this.az);
      if ((☃ instanceof sa)) {
        this.ay = ((sa)☃);
      }
    }
    return this.ay;
  }
  
  protected void a(dn ☃)
  {
    this.T = ☃.h("Age");
    this.h = ☃.h("Duration");
    this.as = ☃.h("WaitTime");
    this.at = ☃.h("ReapplicationDelay");
    this.av = ☃.h("DurationOnUse");
    this.aw = ☃.j("RadiusOnUse");
    this.ax = ☃.j("RadiusPerTick");
    a(☃.j("Radius"));
    this.az = ☃.a("OwnerUUID");
    if (☃.b("Particle", 8))
    {
      cy ☃ = cy.a(☃.l("Particle"));
      if (☃ != null) {
        a(☃);
      }
    }
    if (☃.b("Color", 99)) {
      a(☃.h("Color"));
    }
    if (☃.b("Potion", 8)) {
      a(afg.c(☃));
    }
    if (☃.b("Effects", 9))
    {
      du ☃ = ☃.c("Effects", 10);
      this.f.clear();
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        rl ☃ = rl.b(☃.b(☃));
        if (☃ != null) {
          a(☃);
        }
      }
    }
  }
  
  protected void b(dn ☃)
  {
    ☃.a("Age", this.T);
    ☃.a("Duration", this.h);
    ☃.a("WaitTime", this.as);
    ☃.a("ReapplicationDelay", this.at);
    ☃.a("DurationOnUse", this.av);
    ☃.a("RadiusOnUse", this.aw);
    ☃.a("RadiusPerTick", this.ax);
    ☃.a("Radius", j());
    ☃.a("Particle", l().b());
    if (this.az != null) {
      ☃.a("OwnerUUID", this.az);
    }
    if (this.au) {
      ☃.a("Color", k());
    }
    if ((this.e != afh.a) && (this.e != null)) {
      ☃.a("Potion", ((kk)afe.a.b(this.e)).toString());
    }
    if (!this.f.isEmpty())
    {
      du ☃ = new du();
      for (rl ☃ : this.f) {
        ☃.a(☃.a(new dn()));
      }
      ☃.a("Effects", ☃);
    }
  }
  
  public void a(ke<?> ☃)
  {
    if (a.equals(☃)) {
      a(j());
    }
    super.a(☃);
  }
  
  public axh z()
  {
    return axh.d;
  }
}
