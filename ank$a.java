public class ank$a
{
  private final aht a;
  private final cq.a b;
  private final cq c;
  private final cq d;
  private int e = 0;
  private cj f;
  private int g;
  private int h;
  
  public ank$a(aht ☃, cj ☃, cq.a ☃)
  {
    this.a = ☃;
    this.b = ☃;
    if (☃ == cq.a.a)
    {
      this.d = cq.f;
      this.c = cq.e;
    }
    else
    {
      this.d = cq.c;
      this.c = cq.d;
    }
    cj ☃ = ☃;
    while ((☃.q() > ☃.q() - 21) && (☃.q() > 0) && (a(☃.o(☃.b()).t()))) {
      ☃ = ☃.b();
    }
    int ☃ = a(☃, this.d) - 1;
    if (☃ >= 0)
    {
      this.f = ☃.a(this.d, ☃);
      
      this.h = a(this.f, this.c);
      if ((this.h < 2) || (this.h > 21))
      {
        this.f = null;
        this.h = 0;
      }
    }
    if (this.f != null) {
      this.g = c();
    }
  }
  
  protected int a(cj ☃, cq ☃)
  {
    for (int ☃ = 0; ☃ < 22; ☃++)
    {
      cj ☃ = ☃.a(☃, ☃);
      if (!a(this.a.o(☃).t())) {
        break;
      }
      if (this.a.o(☃.b()).t() != aju.Z) {
        break;
      }
    }
    ajt ☃ = this.a.o(☃.a(☃, ☃)).t();
    if (☃ == aju.Z) {
      return ☃;
    }
    return 0;
  }
  
  public int a()
  {
    return this.g;
  }
  
  public int b()
  {
    return this.h;
  }
  
  protected int c()
  {
    for (this.g = 0; this.g < 21; this.g += 1) {
      for (int ☃ = 0; ☃ < this.h; ☃++)
      {
        cj ☃ = this.f.a(this.c, ☃).b(this.g);
        
        ajt ☃ = this.a.o(☃).t();
        if (!a(☃)) {
          break label181;
        }
        if (☃ == aju.aY) {
          this.e += 1;
        }
        if (☃ == 0)
        {
          ☃ = this.a.o(☃.a(this.d)).t();
          if (☃ != aju.Z) {
            break label181;
          }
        }
        else if (☃ == this.h - 1)
        {
          ☃ = this.a.o(☃.a(this.c)).t();
          if (☃ != aju.Z) {
            break label181;
          }
        }
      }
    }
    label181:
    for (int ☃ = 0; ☃ < this.h; ☃++) {
      if (this.a.o(this.f.a(this.c, ☃).b(this.g)).t() != aju.Z)
      {
        this.g = 0;
        break;
      }
    }
    if ((this.g > 21) || (this.g < 3))
    {
      this.f = null;
      this.h = 0;
      this.g = 0;
      return 0;
    }
    return this.g;
  }
  
  protected boolean a(ajt ☃)
  {
    return (☃.x == axe.a) || (☃ == aju.ab) || (☃ == aju.aY);
  }
  
  public boolean d()
  {
    return (this.f != null) && (this.h >= 2) && (this.h <= 21) && (this.g >= 3) && (this.g <= 21);
  }
  
  public void e()
  {
    for (int ☃ = 0; ☃ < this.h; ☃++)
    {
      cj ☃ = this.f.a(this.c, ☃);
      for (int ☃ = 0; ☃ < this.g; ☃++) {
        this.a.a(☃.b(☃), aju.aY.u().a(ank.a, this.b), 2);
      }
    }
  }
}
