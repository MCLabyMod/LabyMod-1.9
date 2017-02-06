import java.util.Random;

public class uf
  extends tk
{
  private sb a;
  private double b;
  private double c;
  private int d;
  
  public uf(sb ☃)
  {
    this.a = ☃;
    a(3);
  }
  
  public boolean a()
  {
    return this.a.bF().nextFloat() < 0.02F;
  }
  
  public boolean b()
  {
    return this.d >= 0;
  }
  
  public void c()
  {
    double ☃ = 6.283185307179586D * this.a.bF().nextDouble();
    this.b = Math.cos(☃);
    this.c = Math.sin(☃);
    this.d = (20 + this.a.bF().nextInt(20));
  }
  
  public void e()
  {
    this.d -= 1;
    this.a.t().a(this.a.p + this.b, this.a.q + this.a.bn(), this.a.r + this.c, this.a.cE(), this.a.N());
  }
}
