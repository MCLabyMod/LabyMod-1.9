import java.util.Random;

public class tc
  extends te
{
  private int g;
  private int h = -1;
  
  public tc(sb ☃)
  {
    super(☃);
  }
  
  public boolean a()
  {
    if (!super.a()) {
      return false;
    }
    if (!this.a.l.U().b("mobGriefing")) {
      return false;
    }
    return !akv.d(this.a.l, this.b);
  }
  
  public void c()
  {
    super.c();
    this.g = 0;
  }
  
  public boolean b()
  {
    double ☃ = this.a.c(this.b);
    return (this.g <= 240) && (!akv.d(this.a.l, this.b)) && (☃ < 4.0D);
  }
  
  public void d()
  {
    super.d();
    this.a.l.c(this.a.O(), this.b, -1);
  }
  
  public void e()
  {
    super.e();
    if (this.a.bF().nextInt(20) == 0) {
      this.a.l.b(1019, this.b, 0);
    }
    this.g += 1;
    
    int ☃ = (int)(this.g / 240.0F * 10.0F);
    if (☃ != this.h)
    {
      this.a.l.c(this.a.O(), this.b, ☃);
      this.h = ☃;
    }
    if ((this.g == 240) && 
      (this.a.l.ae() == qk.d))
    {
      this.a.l.g(this.b);
      this.a.l.b(1021, this.b, 0);
      this.a.l.b(2001, this.b, ajt.a(this.c));
    }
  }
}
