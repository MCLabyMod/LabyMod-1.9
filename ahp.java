import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ahp
{
  private final boolean a;
  private final boolean b;
  private final Random c = new Random();
  private final aht d;
  private final double e;
  private final double f;
  private final double g;
  private final rr h;
  private final float i;
  private final List<cj> j = Lists.newArrayList();
  private final Map<zj, bbj> k = Maps.newHashMap();
  
  public ahp(aht ☃, rr ☃, double ☃, double ☃, double ☃, float ☃, List<cj> ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃, false, true, ☃);
  }
  
  public ahp(aht ☃, rr ☃, double ☃, double ☃, double ☃, float ☃, boolean ☃, boolean ☃, List<cj> ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.j.addAll(☃);
  }
  
  public ahp(aht ☃, rr ☃, double ☃, double ☃, double ☃, float ☃, boolean ☃, boolean ☃)
  {
    this.d = ☃;
    this.h = ☃;
    this.i = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a()
  {
    Set<cj> ☃ = Sets.newHashSet();
    
    int ☃ = 16;
    for (int ☃ = 0; ☃ < 16; ☃++) {
      for (int ☃ = 0; ☃ < 16; ☃++) {
        for (int ☃ = 0; ☃ < 16; ☃++) {
          if ((☃ == 0) || (☃ == 15) || (☃ == 0) || (☃ == 15) || (☃ == 0) || (☃ == 15))
          {
            double ☃ = ☃ / 15.0F * 2.0F - 1.0F;
            double ☃ = ☃ / 15.0F * 2.0F - 1.0F;
            double ☃ = ☃ / 15.0F * 2.0F - 1.0F;
            double ☃ = Math.sqrt(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
            
            ☃ /= ☃;
            ☃ /= ☃;
            ☃ /= ☃;
            
            float ☃ = this.i * (0.7F + this.d.r.nextFloat() * 0.6F);
            double ☃ = this.e;
            double ☃ = this.f;
            double ☃ = this.g;
            
            float ☃ = 0.3F;
            while (☃ > 0.0F)
            {
              cj ☃ = new cj(☃, ☃, ☃);
              arc ☃ = this.d.o(☃);
              if (☃.a() != axe.a)
              {
                float ☃ = this.h != null ? this.h.a(this, this.d, ☃, ☃) : ☃.t().a(null);
                ☃ -= (☃ + 0.3F) * 0.3F;
              }
              if ((☃ > 0.0F) && ((this.h == null) || (this.h.a(this, this.d, ☃, ☃, ☃)))) {
                ☃.add(☃);
              }
              ☃ += ☃ * 0.30000001192092896D;
              ☃ += ☃ * 0.30000001192092896D;
              ☃ += ☃ * 0.30000001192092896D;
              ☃ -= 0.22500001F;
            }
          }
        }
      }
    }
    this.j.addAll(☃);
    
    float ☃ = this.i * 2.0F;
    
    int ☃ = on.c(this.e - ☃ - 1.0D);
    int ☃ = on.c(this.e + ☃ + 1.0D);
    int ☃ = on.c(this.f - ☃ - 1.0D);
    int ☃ = on.c(this.f + ☃ + 1.0D);
    int ☃ = on.c(this.g - ☃ - 1.0D);
    int ☃ = on.c(this.g + ☃ + 1.0D);
    List<rr> ☃ = this.d.b(this.h, new bbh(☃, ☃, ☃, ☃, ☃, ☃));
    bbj ☃ = new bbj(this.e, this.f, this.g);
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if (!☃.bq())
      {
        double ☃ = ☃.f(this.e, this.f, this.g) / ☃;
        if (☃ <= 1.0D)
        {
          double ☃ = ☃.p - this.e;
          double ☃ = ☃.q + ☃.bn() - this.f;
          double ☃ = ☃.r - this.g;
          
          double ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
          if (☃ != 0.0D)
          {
            ☃ /= ☃;
            ☃ /= ☃;
            ☃ /= ☃;
            
            double ☃ = this.d.a(☃, ☃.bl());
            double ☃ = (1.0D - ☃) * ☃;
            ☃.a(rc.a(this), (int)((☃ * ☃ + ☃) / 2.0D * 7.0D * ☃ + 1.0D));
            
            double ☃ = 1.0D;
            if ((☃ instanceof sa)) {
              ☃ = agy.a((sa)☃, ☃);
            }
            ☃.s += ☃ * ☃;
            ☃.t += ☃ * ☃;
            ☃.u += ☃ * ☃;
            if ((☃ instanceof zj))
            {
              zj ☃ = (zj)☃;
              if ((!☃.y()) && ((!☃.l_()) || (!☃.bJ.b))) {
                this.k.put(☃, new bbj(☃ * ☃, ☃ * ☃, ☃ * ☃));
              }
            }
          }
        }
      }
    }
  }
  
  public void a(boolean ☃)
  {
    this.d.a(null, this.e, this.f, this.g, ng.bD, nh.e, 4.0F, (1.0F + (this.d.r.nextFloat() - this.d.r.nextFloat()) * 0.2F) * 0.7F);
    if ((this.i < 2.0F) || (!this.b)) {
      this.d.a(cy.b, this.e, this.f, this.g, 1.0D, 0.0D, 0.0D, new int[0]);
    } else {
      this.d.a(cy.c, this.e, this.f, this.g, 1.0D, 0.0D, 0.0D, new int[0]);
    }
    if (this.b) {
      for (cj ☃ : this.j)
      {
        arc ☃ = this.d.o(☃);
        ajt ☃ = ☃.t();
        if (☃)
        {
          double ☃ = ☃.p() + this.d.r.nextFloat();
          double ☃ = ☃.q() + this.d.r.nextFloat();
          double ☃ = ☃.r() + this.d.r.nextFloat();
          
          double ☃ = ☃ - this.e;
          double ☃ = ☃ - this.f;
          double ☃ = ☃ - this.g;
          
          double ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
          
          ☃ /= ☃;
          ☃ /= ☃;
          ☃ /= ☃;
          
          double ☃ = 0.5D / (☃ / this.i + 0.1D);
          ☃ *= (this.d.r.nextFloat() * this.d.r.nextFloat() + 0.3F);
          ☃ *= ☃;
          ☃ *= ☃;
          ☃ *= ☃;
          
          this.d.a(cy.a, (☃ + this.e) / 2.0D, (☃ + this.f) / 2.0D, (☃ + this.g) / 2.0D, ☃, ☃, ☃, new int[0]);
          this.d.a(cy.l, ☃, ☃, ☃, ☃, ☃, ☃, new int[0]);
        }
        if (☃.a() != axe.a)
        {
          if (☃.a(this)) {
            ☃.a(this.d, ☃, this.d.o(☃), 1.0F / this.i, 0);
          }
          this.d.a(☃, aju.a.u(), 3);
          ☃.a(this.d, ☃, this);
        }
      }
    }
    if (this.a) {
      for (cj ☃ : this.j) {
        if ((this.d.o(☃).a() == axe.a) && (this.d.o(☃.b()).b()) && (this.c.nextInt(3) == 0)) {
          this.d.a(☃, aju.ab.u());
        }
      }
    }
  }
  
  public Map<zj, bbj> b()
  {
    return this.k;
  }
  
  public sa c()
  {
    if (this.h == null) {
      return null;
    }
    if ((this.h instanceof ye)) {
      return ((ye)this.h).j();
    }
    if ((this.h instanceof sa)) {
      return (sa)this.h;
    }
    return null;
  }
  
  public void d()
  {
    this.j.clear();
  }
  
  public List<cj> e()
  {
    return this.j;
  }
}
