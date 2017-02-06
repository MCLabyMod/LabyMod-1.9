import java.util.List;
import java.util.Random;

public class vq
{
  private aht a;
  private boolean b;
  private int c = -1;
  private int d;
  private int e;
  private vp f;
  private int g;
  private int h;
  private int i;
  
  public vq(aht ☃)
  {
    this.a = ☃;
  }
  
  public void a()
  {
    if (this.a.B())
    {
      this.c = 0;
      return;
    }
    if (this.c == 2) {
      return;
    }
    if (this.c == 0)
    {
      float ☃ = this.a.c(0.0F);
      if ((☃ < 0.5D) || (☃ > 0.501D)) {
        return;
      }
      this.c = (this.a.r.nextInt(10) == 0 ? 1 : 2);
      this.b = false;
      if (this.c == 2) {
        return;
      }
    }
    if (this.c == -1) {
      return;
    }
    if (!this.b) {
      if (b()) {
        this.b = true;
      } else {
        return;
      }
    }
    if (this.e > 0)
    {
      this.e -= 1;
      return;
    }
    this.e = 2;
    if (this.d > 0)
    {
      c();
      this.d -= 1;
    }
    else
    {
      this.c = 2;
    }
  }
  
  private boolean b()
  {
    List<zj> ☃ = this.a.i;
    for (zj ☃ : ☃) {
      if (!☃.y())
      {
        this.f = this.a.ai().a(new cj(☃), 1);
        if ((this.f != null) && 
        
          (this.f.c() >= 10) && 
          
          (this.f.d() >= 20) && 
          
          (this.f.e() >= 20))
        {
          cj ☃ = this.f.a();
          float ☃ = this.f.b();
          
          boolean ☃ = false;
          for (int ☃ = 0; ☃ < 10; ☃++)
          {
            float ☃ = this.a.r.nextFloat() * 6.2831855F;
            this.g = (☃.p() + (int)(on.b(☃) * ☃ * 0.9D));
            this.h = ☃.q();
            this.i = (☃.r() + (int)(on.a(☃) * ☃ * 0.9D));
            ☃ = false;
            for (vp ☃ : this.a.ai().b()) {
              if (☃ != this.f) {
                if (☃.a(new cj(this.g, this.h, this.i)))
                {
                  ☃ = true;
                  break;
                }
              }
            }
            if (!☃) {
              break;
            }
          }
          if (☃) {
            return false;
          }
          bbj ☃ = a(new cj(this.g, this.h, this.i));
          if (☃ != null)
          {
            this.e = 0;
            this.d = 20;
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private boolean c()
  {
    bbj ☃ = a(new cj(this.g, this.h, this.i));
    if (☃ == null) {
      return false;
    }
    za ☃;
    try
    {
      ☃ = new za(this.a);
      ☃.a(this.a.D(new cj(☃)), null);
      ☃.df();
    }
    catch (Exception ☃)
    {
      ☃.printStackTrace();
      return false;
    }
    ☃.b(☃.b, ☃.c, ☃.d, this.a.r.nextFloat() * 360.0F, 0.0F);
    this.a.a(☃);
    cj ☃ = this.f.a();
    ☃.a(☃, this.f.b());
    return true;
  }
  
  private bbj a(cj ☃)
  {
    for (int ☃ = 0; ☃ < 10; ☃++)
    {
      cj ☃ = ☃.a(this.a.r.nextInt(16) - 8, this.a.r.nextInt(6) - 3, this.a.r.nextInt(16) - 8);
      if (this.f.a(☃)) {
        if (aia.a(sb.a.a, this.a, ☃)) {
          return new bbj(☃.p(), ☃.q(), ☃.r());
        }
      }
    }
    return null;
  }
}
