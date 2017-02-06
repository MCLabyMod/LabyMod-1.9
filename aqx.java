import java.util.List;

public class aqx
  extends apv
  implements ky
{
  private arc a;
  private cq f;
  private boolean g;
  private boolean h;
  private float i;
  private float j;
  
  public aqx() {}
  
  public aqx(arc ☃, cq ☃, boolean ☃, boolean ☃)
  {
    this.a = ☃;
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
  }
  
  public arc b()
  {
    return this.a;
  }
  
  public int u()
  {
    return 0;
  }
  
  public boolean d()
  {
    return this.g;
  }
  
  public cq e()
  {
    return this.f;
  }
  
  public boolean g()
  {
    return this.h;
  }
  
  public float a(float ☃)
  {
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    return this.j + (this.i - this.j) * ☃;
  }
  
  public float b(float ☃)
  {
    return this.f.g() * e(a(☃));
  }
  
  public float c(float ☃)
  {
    return this.f.h() * e(a(☃));
  }
  
  public float d(float ☃)
  {
    return this.f.i() * e(a(☃));
  }
  
  private float e(float ☃)
  {
    return this.g ? ☃ - 1.0F : 1.0F - ☃;
  }
  
  public bbh a(ahx ☃, cj ☃)
  {
    return a(☃, ☃, this.i).a(a(☃, ☃, this.j));
  }
  
  public bbh a(ahx ☃, cj ☃, float ☃)
  {
    ☃ = e(☃);
    
    return this.a.c(☃, ☃).c(☃ * this.f.g(), ☃ * this.f.h(), ☃ * this.f.i());
  }
  
  private void i()
  {
    bbh ☃ = a(this.b, this.c).a(this.c);
    List<rr> ☃ = this.b.b(null, ☃);
    if (☃.isEmpty()) {
      return;
    }
    cq ☃ = this.g ? this.f : this.f.d();
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if (☃.z() != axh.d)
      {
        if (this.a.t() == aju.cE) {
          switch (aqx.1.a[☃.k().ordinal()])
          {
          case 1: 
            ☃.s = ☃.g();
            break;
          case 2: 
            ☃.t = ☃.h();
            break;
          case 3: 
            ☃.u = ☃.i();
          }
        }
        double ☃ = 0.0D;
        double ☃ = 0.0D;
        double ☃ = 0.0D;
        bbh ☃ = ☃.bl();
        switch (aqx.1.a[☃.k().ordinal()])
        {
        case 1: 
          if (☃.c() == cq.b.a) {
            ☃ = ☃.d - ☃.a;
          } else {
            ☃ = ☃.d - ☃.a;
          }
          ☃ += 0.01D;
          break;
        case 2: 
          if (☃.c() == cq.b.a) {
            ☃ = ☃.e - ☃.b;
          } else {
            ☃ = ☃.e - ☃.b;
          }
          ☃ += 0.01D;
          break;
        case 3: 
          if (☃.c() == cq.b.a) {
            ☃ = ☃.f - ☃.c;
          } else {
            ☃ = ☃.f - ☃.c;
          }
          ☃ += 0.01D;
        }
        ☃.d(☃ * ☃.g(), ☃ * ☃.h(), ☃ * ☃.i());
      }
    }
  }
  
  public void h()
  {
    if ((this.j < 1.0F) && (this.b != null))
    {
      this.j = (this.i = 1.0F);
      this.b.s(this.c);
      y();
      if (this.b.o(this.c).t() == aju.M)
      {
        this.b.a(this.c, this.a, 3);
        this.b.e(this.c, this.a.t());
      }
    }
  }
  
  public void c()
  {
    this.j = this.i;
    if (this.j >= 1.0F)
    {
      i();
      this.b.s(this.c);
      y();
      if (this.b.o(this.c).t() == aju.M)
      {
        this.b.a(this.c, this.a, 3);
        this.b.e(this.c, this.a.t());
      }
      return;
    }
    this.i += 0.5F;
    if (this.i >= 1.0F) {
      this.i = 1.0F;
    }
    i();
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.a = ajt.b(☃.h("blockId")).a(☃.h("blockData"));
    this.f = cq.a(☃.h("facing"));
    this.j = (this.i = ☃.j("progress"));
    this.g = ☃.p("extending");
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("blockId", ajt.a(this.a.t()));
    ☃.a("blockData", this.a.t().e(this.a));
    ☃.a("facing", this.f.a());
    ☃.a("progress", this.j);
    ☃.a("extending", this.g);
  }
}
