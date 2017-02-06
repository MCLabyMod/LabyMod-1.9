import java.util.List;
import java.util.Random;

public class apx
  extends aqm
  implements ky, qg
{
  private adq[] o = new adq[27];
  public boolean a;
  public apx f;
  public apx g;
  public apx h;
  public apx i;
  public float j;
  public float k;
  public int l;
  private int p;
  private ake.a q;
  private String r;
  
  public apx() {}
  
  public apx(ake.a ☃)
  {
    this.q = ☃;
  }
  
  public int u_()
  {
    return 27;
  }
  
  public adq a(int ☃)
  {
    d(null);
    return this.o[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    d(null);
    
    adq ☃ = qh.a(this.o, ☃, ☃);
    if (☃ != null) {
      v_();
    }
    return ☃;
  }
  
  public adq b(int ☃)
  {
    d(null);
    
    return qh.a(this.o, ☃);
  }
  
  public void a(int ☃, adq ☃)
  {
    d(null);
    this.o[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
    v_();
  }
  
  public String h_()
  {
    return o_() ? this.r : "container.chest";
  }
  
  public boolean o_()
  {
    return (this.r != null) && (!this.r.isEmpty());
  }
  
  public void a(String ☃)
  {
    this.r = ☃;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.o = new adq[u_()];
    if (☃.b("CustomName", 8)) {
      this.r = ☃.l("CustomName");
    }
    if (!c(☃))
    {
      du ☃ = ☃.c("Items", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        int ☃ = ☃.f("Slot") & 0xFF;
        if ((☃ >= 0) && (☃ < this.o.length)) {
          this.o[☃] = adq.a(☃);
        }
      }
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (!d(☃))
    {
      du ☃ = new du();
      for (int ☃ = 0; ☃ < this.o.length; ☃++) {
        if (this.o[☃] != null)
        {
          dn ☃ = new dn();
          ☃.a("Slot", (byte)☃);
          this.o[☃].b(☃);
          ☃.a(☃);
        }
      }
      ☃.a("Items", ☃);
    }
    if (o_()) {
      ☃.a("CustomName", this.r);
    }
  }
  
  public int w_()
  {
    return 64;
  }
  
  public boolean a(zj ☃)
  {
    if (this.b.r(this.c) != this) {
      return false;
    }
    if (☃.e(this.c.p() + 0.5D, this.c.q() + 0.5D, this.c.r() + 0.5D) > 64.0D) {
      return false;
    }
    return true;
  }
  
  public void A()
  {
    super.A();
    this.a = false;
  }
  
  private void a(apx ☃, cq ☃)
  {
    if (☃.x()) {
      this.a = false;
    } else if (this.a) {
      switch (apx.1.a[☃.ordinal()])
      {
      case 1: 
        if (this.f != ☃) {
          this.a = false;
        }
        break;
      case 2: 
        if (this.i != ☃) {
          this.a = false;
        }
        break;
      case 3: 
        if (this.g != ☃) {
          this.a = false;
        }
        break;
      case 4: 
        if (this.h != ☃) {
          this.a = false;
        }
        break;
      }
    }
  }
  
  public void m()
  {
    if (this.a) {
      return;
    }
    this.a = true;
    this.h = a(cq.e);
    this.g = a(cq.f);
    this.f = a(cq.c);
    this.i = a(cq.d);
  }
  
  protected apx a(cq ☃)
  {
    cj ☃ = this.c.a(☃);
    if (b(☃))
    {
      apv ☃ = this.b.r(☃);
      if ((☃ instanceof apx))
      {
        apx ☃ = (apx)☃;
        ☃.a(this, ☃.d());
        
        return ☃;
      }
    }
    return null;
  }
  
  private boolean b(cj ☃)
  {
    if (this.b == null) {
      return false;
    }
    ajt ☃ = this.b.o(☃).t();
    
    return ((☃ instanceof ake)) && (((ake)☃).g == o());
  }
  
  public void c()
  {
    m();
    
    int ☃ = this.c.p();
    int ☃ = this.c.q();
    int ☃ = this.c.r();
    
    this.p += 1;
    if ((!this.b.E) && (this.l != 0) && ((this.p + ☃ + ☃ + ☃) % 200 == 0))
    {
      this.l = 0;
      
      float ☃ = 5.0F;
      List<zj> ☃ = this.b.a(zj.class, new bbh(☃ - ☃, ☃ - ☃, ☃ - ☃, ☃ + 1 + ☃, ☃ + 1 + ☃, ☃ + 1 + ☃));
      for (zj ☃ : ☃) {
        if ((☃.bt instanceof abb))
        {
          qg ☃ = ((abb)☃.bt).e();
          if ((☃ == this) || (((☃ instanceof qf)) && (((qf)☃).a(this)))) {
            this.l += 1;
          }
        }
      }
    }
    this.k = this.j;
    
    float ☃ = 0.1F;
    if ((this.l > 0) && (this.j == 0.0F) && 
      (this.f == null) && (this.h == null))
    {
      double ☃ = ☃ + 0.5D;
      double ☃ = ☃ + 0.5D;
      if (this.i != null) {
        ☃ += 0.5D;
      }
      if (this.g != null) {
        ☃ += 0.5D;
      }
      this.b.a(null, ☃, ☃ + 0.5D, ☃, ng.X, nh.e, 0.5F, this.b.r.nextFloat() * 0.1F + 0.9F);
    }
    if (((this.l == 0) && (this.j > 0.0F)) || ((this.l > 0) && (this.j < 1.0F)))
    {
      float ☃ = this.j;
      if (this.l > 0) {
        this.j += ☃;
      } else {
        this.j -= ☃;
      }
      if (this.j > 1.0F) {
        this.j = 1.0F;
      }
      float ☃ = 0.5F;
      if ((this.j < ☃) && (☃ >= ☃) && 
        (this.f == null) && (this.h == null))
      {
        double ☃ = ☃ + 0.5D;
        double ☃ = ☃ + 0.5D;
        if (this.i != null) {
          ☃ += 0.5D;
        }
        if (this.g != null) {
          ☃ += 0.5D;
        }
        this.b.a(null, ☃, ☃ + 0.5D, ☃, ng.V, nh.e, 0.5F, this.b.r.nextFloat() * 0.1F + 0.9F);
      }
      if (this.j < 0.0F) {
        this.j = 0.0F;
      }
    }
  }
  
  public boolean c(int ☃, int ☃)
  {
    if (☃ == 1)
    {
      this.l = ☃;
      return true;
    }
    return super.c(☃, ☃);
  }
  
  public void b(zj ☃)
  {
    if (!☃.y())
    {
      if (this.l < 0) {
        this.l = 0;
      }
      this.l += 1;
      this.b.c(this.c, w(), 1, this.l);
      this.b.d(this.c, w());
      this.b.d(this.c.b(), w());
    }
  }
  
  public void c(zj ☃)
  {
    if ((!☃.y()) && 
      ((w() instanceof ake)))
    {
      this.l -= 1;
      this.b.c(this.c, w(), 1, this.l);
      this.b.d(this.c, w());
      this.b.d(this.c.b(), w());
    }
  }
  
  public boolean b(int ☃, adq ☃)
  {
    return true;
  }
  
  public void y()
  {
    super.y();
    A();
    m();
  }
  
  public ake.a o()
  {
    if (this.q == null) {
      if ((this.b != null) && ((w() instanceof ake))) {
        this.q = ((ake)w()).g;
      } else {
        return ake.a.a;
      }
    }
    return this.q;
  }
  
  public String k()
  {
    return "minecraft:chest";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    d(☃);
    return new abb(☃, this, ☃);
  }
  
  public int c_(int ☃)
  {
    return 0;
  }
  
  public void b(int ☃, int ☃) {}
  
  public int g()
  {
    return 0;
  }
  
  public void l()
  {
    d(null);
    for (int ☃ = 0; ☃ < this.o.length; ☃++) {
      this.o[☃] = null;
    }
  }
  
  public void a(kk ☃, long ☃)
  {
    this.m = ☃;
    this.n = ☃;
  }
}
