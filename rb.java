import com.google.common.collect.Lists;
import java.util.List;

public class rb
{
  private final List<qz> a = Lists.newArrayList();
  private final sa b;
  private int c;
  private int d;
  private int e;
  private boolean f;
  private boolean g;
  private String h;
  
  public rb(sa ☃)
  {
    this.b = ☃;
  }
  
  public void a()
  {
    k();
    if (this.b.n_())
    {
      ajt ☃ = this.b.l.o(new cj(this.b.p, this.b.bl().b, this.b.r)).t();
      if (☃ == aju.au) {
        this.h = "ladder";
      } else if (☃ == aju.bn) {
        this.h = "vines";
      }
    }
    else if (this.b.ai())
    {
      this.h = "water";
    }
  }
  
  public void a(rc ☃, float ☃, float ☃)
  {
    g();
    a();
    
    qz ☃ = new qz(☃, this.b.T, ☃, ☃, this.h, this.b.L);
    
    this.a.add(☃);
    this.c = this.b.T;
    this.g = true;
    if ((☃.f()) && (!this.f) && (this.b.au()))
    {
      this.f = true;
      this.d = this.b.T;
      this.e = this.d;
      this.b.j();
    }
  }
  
  public eu b()
  {
    if (this.a.isEmpty()) {
      return new fb("death.attack.generic", new Object[] { this.b.i_() });
    }
    qz ☃ = j();
    qz ☃ = (qz)this.a.get(this.a.size() - 1);
    
    eu ☃ = ☃.h();
    rr ☃ = ☃.a().j();
    eu ☃;
    eu ☃;
    if ((☃ != null) && (☃.a() == rc.i))
    {
      eu ☃ = ☃.h();
      eu ☃;
      if ((☃.a() == rc.i) || (☃.a() == rc.k))
      {
        ☃ = new fb("death.fell.accident." + a(☃), new Object[] { this.b.i_() });
      }
      else
      {
        eu ☃;
        if ((☃ != null) && ((☃ == null) || (!☃.equals(☃))))
        {
          rr ☃ = ☃.a().j();
          adq ☃ = (☃ instanceof sa) ? ((sa)☃).cb() : null;
          eu ☃;
          if ((☃ != null) && (☃.s())) {
            ☃ = new fb("death.fell.assist.item", new Object[] { this.b.i_(), ☃, ☃.B() });
          } else {
            ☃ = new fb("death.fell.assist", new Object[] { this.b.i_(), ☃ });
          }
        }
        else
        {
          eu ☃;
          if (☃ != null)
          {
            adq ☃ = (☃ instanceof sa) ? ((sa)☃).cb() : null;
            eu ☃;
            if ((☃ != null) && (☃.s())) {
              ☃ = new fb("death.fell.finish.item", new Object[] { this.b.i_(), ☃, ☃.B() });
            } else {
              ☃ = new fb("death.fell.finish", new Object[] { this.b.i_(), ☃ });
            }
          }
          else
          {
            ☃ = new fb("death.fell.killer", new Object[] { this.b.i_() });
          }
        }
      }
    }
    else
    {
      ☃ = ☃.a().c(this.b);
    }
    return ☃;
  }
  
  public sa c()
  {
    sa ☃ = null;
    zj ☃ = null;
    float ☃ = 0.0F;
    float ☃ = 0.0F;
    for (qz ☃ : this.a)
    {
      if (((☃.a().j() instanceof zj)) && ((☃ == null) || (☃.c() > ☃)))
      {
        ☃ = ☃.c();
        ☃ = (zj)☃.a().j();
      }
      if (((☃.a().j() instanceof sa)) && ((☃ == null) || (☃.c() > ☃)))
      {
        ☃ = ☃.c();
        ☃ = (sa)☃.a().j();
      }
    }
    if ((☃ != null) && (☃ >= ☃ / 3.0F)) {
      return ☃;
    }
    return ☃;
  }
  
  private qz j()
  {
    qz ☃ = null;
    qz ☃ = null;
    float ☃ = 0.0F;
    float ☃ = 0.0F;
    for (int ☃ = 0; ☃ < this.a.size(); ☃++)
    {
      qz ☃ = (qz)this.a.get(☃);
      qz ☃ = ☃ > 0 ? (qz)this.a.get(☃ - 1) : null;
      if (((☃.a() == rc.i) || (☃.a() == rc.k)) && (☃.j() > 0.0F) && ((☃ == null) || (☃.j() > ☃)))
      {
        if (☃ > 0) {
          ☃ = ☃;
        } else {
          ☃ = ☃;
        }
        ☃ = ☃.j();
      }
      if ((☃.g() != null) && ((☃ == null) || (☃.c() > ☃)))
      {
        ☃ = ☃;
        ☃ = ☃.c();
      }
    }
    if ((☃ > 5.0F) && (☃ != null)) {
      return ☃;
    }
    if ((☃ > 5.0F) && (☃ != null)) {
      return ☃;
    }
    return null;
  }
  
  private String a(qz ☃)
  {
    return ☃.g() == null ? "generic" : ☃.g();
  }
  
  public int f()
  {
    if (this.f) {
      return this.b.T - this.d;
    }
    return this.e - this.d;
  }
  
  private void k()
  {
    this.h = null;
  }
  
  public void g()
  {
    int ☃ = this.f ? 300 : 100;
    if ((this.g) && ((!this.b.au()) || (this.b.T - this.c > ☃)))
    {
      boolean ☃ = this.f;
      this.g = false;
      this.f = false;
      this.e = this.b.T;
      if (☃) {
        this.b.k();
      }
      this.a.clear();
    }
  }
  
  public sa h()
  {
    return this.b;
  }
}
