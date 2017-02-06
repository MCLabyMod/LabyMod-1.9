import java.util.Arrays;

public class bnb
  extends aic
{
  private static final arc f = aju.a.u();
  private final cj g;
  private int[] h;
  private arc[] i;
  
  public bnb(aht ☃, cj ☃, cj ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃);
    this.g = ☃.b(new df(☃, ☃, ☃));
    
    int ☃ = 8000;
    this.h = new int['ὀ'];
    Arrays.fill(this.h, -1);
    this.i = new arc['ὀ'];
  }
  
  public apv r(cj ☃)
  {
    int ☃ = (☃.p() >> 4) - this.a;
    int ☃ = (☃.r() >> 4) - this.b;
    
    return this.c[☃][☃].a(☃, ase.a.b);
  }
  
  public int b(cj ☃, int ☃)
  {
    int ☃ = f(☃);
    int ☃ = this.h[☃];
    if (☃ == -1)
    {
      ☃ = super.b(☃, ☃);
      this.h[☃] = ☃;
    }
    return ☃;
  }
  
  public arc o(cj ☃)
  {
    int ☃ = f(☃);
    arc ☃ = this.i[☃];
    if (☃ == null)
    {
      ☃ = e(☃);
      this.i[☃] = ☃;
    }
    return ☃;
  }
  
  private arc e(cj ☃)
  {
    if ((☃.q() >= 0) && (☃.q() < 256))
    {
      int ☃ = (☃.p() >> 4) - this.a;
      int ☃ = (☃.r() >> 4) - this.b;
      
      return this.c[☃][☃].a(☃);
    }
    return f;
  }
  
  private int f(cj ☃)
  {
    int ☃ = ☃.p() - this.g.p();
    int ☃ = ☃.q() - this.g.q();
    int ☃ = ☃.r() - this.g.r();
    return ☃ * 400 + ☃ * 20 + ☃;
  }
}
