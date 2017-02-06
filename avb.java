import java.util.Random;

public class avb
  extends aud
{
  private boolean a = false;
  private avb.a b = null;
  private cj c;
  
  public void a(avb.a ☃)
  {
    this.b = ☃;
  }
  
  public void a(boolean ☃)
  {
    this.a = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    if (this.b == null) {
      throw new IllegalStateException("Decoration requires priming with a spike");
    }
    int ☃ = this.b.c();
    for (cj.a ☃ : cj.b(new cj(☃.p() - ☃, 0, ☃.r() - ☃), new cj(☃.p() + ☃, this.b.d() + 10, ☃.r() + ☃))) {
      if ((☃.e(☃.p(), ☃.q(), ☃.r()) <= ☃ * ☃ + 1) && (☃.q() < this.b.d())) {
        a(☃, ☃, aju.Z.u());
      } else if (☃.q() > 65) {
        a(☃, ☃, aju.a.u());
      }
    }
    if (this.b.e()) {
      for (int ☃ = -2; ☃ <= 2; ☃++) {
        for (int ☃ = -2; ☃ <= 2; ☃++)
        {
          if ((on.a(☃) == 2) || (on.a(☃) == 2))
          {
            a(☃, new cj(☃.p() + ☃, this.b.d(), ☃.r() + ☃), aju.bi.u());
            a(☃, new cj(☃.p() + ☃, this.b.d() + 1, ☃.r() + ☃), aju.bi.u());
            a(☃, new cj(☃.p() + ☃, this.b.d() + 2, ☃.r() + ☃), aju.bi.u());
          }
          a(☃, new cj(☃.p() + ☃, this.b.d() + 3, ☃.r() + ☃), aju.bi.u());
        }
      }
    }
    wt ☃ = new wt(☃);
    ☃.a(this.c);
    ☃.h(this.a);
    ☃.b(☃.p() + 0.5F, this.b.d() + 1, ☃.r() + 0.5F, ☃.nextFloat() * 360.0F, 0.0F);
    ☃.a(☃);
    a(☃, new cj(☃.p(), this.b.d(), ☃.r()), aju.h.u());
    
    return true;
  }
  
  public void a(cj ☃)
  {
    this.c = ☃;
  }
  
  public static class a
  {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final boolean e;
    private final bbh f;
    
    public a(int ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
      
      this.f = new bbh(☃ - ☃, 0.0D, ☃ - ☃, ☃ + ☃, 256.0D, ☃ + ☃);
    }
    
    public boolean a(cj ☃)
    {
      int ☃ = this.a - this.c;
      int ☃ = this.b - this.c;
      return (☃.p() == (☃ & 0xFFFFFFF0)) && (☃.r() == (☃ & 0xFFFFFFF0));
    }
    
    public int a()
    {
      return this.a;
    }
    
    public int b()
    {
      return this.b;
    }
    
    public int c()
    {
      return this.c;
    }
    
    public int d()
    {
      return this.d;
    }
    
    public boolean e()
    {
      return this.e;
    }
    
    public bbh f()
    {
      return this.f;
    }
  }
}
