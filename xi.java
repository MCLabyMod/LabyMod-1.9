import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class xi
  extends ww
{
  private static final Logger b = ;
  private int c;
  private ayp d;
  private bbj e;
  private sa f;
  private boolean g;
  
  public xi(wu ☃)
  {
    super(☃);
  }
  
  public void c()
  {
    if (this.f == null)
    {
      b.warn("Skipping player strafe phase because no player was found");
      this.a.cT().a(xk.a);
      return;
    }
    if ((this.d != null) && (this.d.b()))
    {
      double ☃ = this.f.p;
      double ☃ = this.f.r;
      
      double ☃ = ☃ - this.a.p;
      double ☃ = ☃ - this.a.r;
      double ☃ = on.a(☃ * ☃ + ☃ * ☃);
      double ☃ = Math.min(0.4000000059604645D + ☃ / 80.0D - 1.0D, 10.0D);
      
      this.e = new bbj(☃, this.f.q + ☃, ☃);
    }
    double ☃ = this.e == null ? 0.0D : this.e.c(this.a.p, this.a.q, this.a.r);
    if ((☃ < 100.0D) || (☃ > 22500.0D)) {
      j();
    }
    double ☃ = 64.0D;
    if (this.f.h(this.a) < ☃ * ☃)
    {
      if (this.a.D(this.f))
      {
        this.c += 1;
        bbj ☃ = new bbj(this.f.p - this.a.p, 0.0D, this.f.r - this.a.r).a();
        bbj ☃ = new bbj(on.a(this.a.v * 0.017453292F), 0.0D, -on.b(this.a.v * 0.017453292F)).a();
        float ☃ = (float)☃.b(☃);
        float ☃ = (float)(Math.acos(☃) * 57.2957763671875D);
        ☃ += 0.5F;
        if ((this.c >= 5) && (☃ >= 0.0F) && (☃ < 10.0F))
        {
          double ☃ = 1.0D;
          bbj ☃ = this.a.f(1.0F);
          double ☃ = this.a.bu.p - ☃.b * ☃;
          double ☃ = this.a.bu.q + this.a.bu.H / 2.0F + 0.5D;
          double ☃ = this.a.bu.r - ☃.d * ☃;
          
          double ☃ = this.f.p - ☃;
          double ☃ = this.f.q + this.f.H / 2.0F - (☃ + this.a.bu.H / 2.0F);
          double ☃ = this.f.r - ☃;
          
          this.a.l.a(null, 1017, new cj(this.a), 0);
          zn ☃ = new zn(this.a.l, this.a, ☃, ☃, ☃);
          ☃.p = ☃;
          ☃.q = ☃;
          ☃.r = ☃;
          this.a.l.a(☃);
          this.c = 0;
          if (this.d != null) {
            while (!this.d.b()) {
              this.d.a();
            }
          }
          this.a.cT().a(xk.a);
        }
      }
      else if (this.c > 0)
      {
        this.c -= 1;
      }
    }
    else if (this.c > 0) {
      this.c -= 1;
    }
  }
  
  private void j()
  {
    if ((this.d == null) || (this.d.b()))
    {
      int ☃ = this.a.o();
      int ☃ = ☃;
      if (this.a.bF().nextInt(8) == 0)
      {
        this.g = (!this.g);
        ☃ += 6;
      }
      if (this.g) {
        ☃++;
      } else {
        ☃--;
      }
      if ((this.a.cU() == null) || (this.a.cU().c() < 0))
      {
        ☃ -= 12;
        ☃ &= 0x7;
        ☃ += 12;
      }
      else
      {
        ☃ %= 12;
        if (☃ < 0) {
          ☃ += 12;
        }
      }
      this.d = this.a.a(☃, ☃, null);
      if (this.d != null) {
        this.d.a();
      }
    }
    k();
  }
  
  private void k()
  {
    if ((this.d != null) && (!this.d.b()))
    {
      bbj ☃ = this.d.f();
      
      this.d.a();
      double ☃ = ☃.b;
      
      double ☃ = ☃.d;
      double ☃;
      do
      {
        ☃ = ☃.c + this.a.bF().nextFloat() * 20.0F;
      } while (☃ < ☃.c);
      this.e = new bbj(☃, ☃, ☃);
    }
  }
  
  public void d()
  {
    this.c = 0;
    this.e = null;
    this.d = null;
    this.f = null;
  }
  
  public void a(sa ☃)
  {
    this.f = ☃;
    
    int ☃ = this.a.o();
    int ☃ = this.a.l(this.f.p, this.f.q, this.f.r);
    
    int ☃ = on.c(this.f.p);
    int ☃ = on.c(this.f.r);
    
    double ☃ = ☃ - this.a.p;
    double ☃ = ☃ - this.a.r;
    double ☃ = on.a(☃ * ☃ + ☃ * ☃);
    double ☃ = Math.min(0.4000000059604645D + ☃ / 80.0D - 1.0D, 10.0D);
    int ☃ = on.c(this.f.q + ☃);
    
    ayn ☃ = new ayn(☃, ☃, ☃);
    
    this.d = this.a.a(☃, ☃, ☃);
    if (this.d != null)
    {
      this.d.a();
      
      k();
    }
  }
  
  public bbj g()
  {
    return this.e;
  }
  
  public xk<xi> i()
  {
    return xk.b;
  }
}
