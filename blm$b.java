import java.util.Random;

public class blm$b
  extends bmd
{
  private boolean a;
  private boolean G;
  private final bly H;
  private float I;
  private float J;
  private float K;
  private boolean L;
  
  public blm$b(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, bly ☃)
  {
    super(☃, ☃, ☃, ☃, 160, 8, -0.004F);
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    this.H = ☃;
    
    this.w *= 0.75F;
    
    this.v = (48 + this.p.nextInt(12));
  }
  
  public void a(boolean ☃)
  {
    this.a = ☃;
  }
  
  public void b(boolean ☃)
  {
    this.G = ☃;
  }
  
  public boolean c()
  {
    return true;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    if ((!this.G) || (this.u < this.v / 3) || ((this.u + this.v) / 3 % 2 == 0)) {
      super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public void a()
  {
    super.a();
    if ((this.a) && (this.u < this.v / 2) && ((this.u + this.v) % 2 == 0))
    {
      b ☃ = new b(this.b, this.f, this.g, this.h, 0.0D, 0.0D, 0.0D, this.H);
      ☃.e(0.99F);
      ☃.a(this.y, this.z, this.A);
      ☃.u = (☃.v / 2);
      if (this.L)
      {
        ☃.L = true;
        ☃.I = this.I;
        ☃.J = this.J;
        ☃.K = this.K;
      }
      ☃.G = this.G;
      this.H.a(☃);
    }
  }
}
