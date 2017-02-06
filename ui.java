import java.util.Random;

public class ui
  extends tk
{
  private final yw a;
  private final double b;
  private final int c;
  private final float d;
  private int e = -1;
  private int f;
  private boolean g;
  private boolean h;
  private int i = -1;
  
  public ui(yw ☃, double ☃, int ☃, float ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = (☃ * ☃);
    a(3);
  }
  
  public boolean a()
  {
    if (this.a.A() == null) {
      return false;
    }
    return f();
  }
  
  protected boolean f()
  {
    return (this.a.cb() != null) && (this.a.cb().b() == ads.f);
  }
  
  public boolean b()
  {
    return ((a()) || (!this.a.x().n())) && (f());
  }
  
  public void c()
  {
    super.c();
    
    this.a.a(true);
  }
  
  public void d()
  {
    super.c();
    
    this.a.a(false);
    this.f = 0;
    this.e = -1;
    this.a.cz();
  }
  
  public void e()
  {
    sa ☃ = this.a.A();
    if (☃ == null) {
      return;
    }
    double ☃ = this.a.e(☃.p, ☃.bl().b, ☃.r);
    boolean ☃ = this.a.y().a(☃);
    boolean ☃ = this.f > 0;
    if (☃ != ☃) {
      this.f = 0;
    }
    if (☃) {
      this.f += 1;
    } else {
      this.f -= 1;
    }
    if ((☃ > this.d) || (this.f < 20))
    {
      this.a.x().a(☃, this.b);
      this.i = -1;
    }
    else
    {
      this.a.x().o();
      this.i += 1;
    }
    if (this.i >= 20)
    {
      if (this.a.bF().nextFloat() < 0.3D) {
        this.g = (!this.g);
      }
      if (this.a.bF().nextFloat() < 0.3D) {
        this.h = (!this.h);
      }
      this.i = 0;
    }
    if (this.i > -1)
    {
      if (☃ > this.d * 0.75F) {
        this.h = false;
      } else if (☃ < this.d * 0.25F) {
        this.h = true;
      }
      this.a.u().a(this.h ? -0.5F : 0.5F, this.g ? 0.5F : -0.5F);
      this.a.a(☃, 30.0F, 30.0F);
    }
    else
    {
      this.a.t().a(☃, 30.0F, 30.0F);
    }
    if (this.a.cs())
    {
      if ((!☃) && (this.f < -60))
      {
        this.a.cz();
      }
      else if (☃)
      {
        int ☃ = this.a.cx();
        if (☃ >= 20)
        {
          this.a.cz();
          this.a.a(☃, ach.b(☃));
          this.e = this.c;
        }
      }
    }
    else if ((--this.e <= 0) && (this.f >= -60)) {
      this.a.c(qm.a);
    }
  }
}
