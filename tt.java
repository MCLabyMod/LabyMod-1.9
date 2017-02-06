import java.util.Random;

public class tt
  extends tk
{
  private int d = -1;
  private int c = -1;
  private vo b;
  private sh a;
  
  public tt(sh ☃)
  {
    this.a = ☃;
    a(1);
  }
  
  public boolean a()
  {
    cj ☃ = new cj(this.a);
    if (((this.a.l.B()) && ((!this.a.l.W()) || (this.a.l.b(☃).d()))) || (this.a.l.s.m())) {
      return false;
    }
    if (this.a.bF().nextInt(50) != 0) {
      return false;
    }
    if ((this.c != -1) && (this.a.e(this.c, this.a.q, this.d) < 4.0D)) {
      return false;
    }
    vp ☃ = this.a.l.ai().a(☃, 14);
    if (☃ == null) {
      return false;
    }
    this.b = ☃.c(☃);
    return this.b != null;
  }
  
  public boolean b()
  {
    return !this.a.x().n();
  }
  
  public void c()
  {
    this.c = -1;
    cj ☃ = this.b.e();
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    if (this.a.c(☃) > 256.0D)
    {
      bbj ☃ = vm.a(this.a, 14, 3, new bbj(☃ + 0.5D, ☃, ☃ + 0.5D));
      if (☃ != null) {
        this.a.x().a(☃.b, ☃.c, ☃.d, 1.0D);
      }
    }
    else
    {
      this.a.x().a(☃ + 0.5D, ☃, ☃ + 0.5D, 1.0D);
    }
  }
  
  public void d()
  {
    this.c = this.b.e().p();
    this.d = this.b.e().r();
    this.b = null;
  }
}
