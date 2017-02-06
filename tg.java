import java.util.Random;

public class tg
  extends tk
{
  private sh a;
  private double b;
  private double c;
  private double d;
  private double e;
  private aht f;
  
  public tg(sh ☃, double ☃)
  {
    this.a = ☃;
    this.e = ☃;
    this.f = ☃.l;
    a(1);
  }
  
  public boolean a()
  {
    if (!this.f.B()) {
      return false;
    }
    if (!this.a.aH()) {
      return false;
    }
    if (!this.f.h(new cj(this.a.p, this.a.bl().b, this.a.r))) {
      return false;
    }
    if (this.a.a(rw.f) != null) {
      return false;
    }
    bbj ☃ = f();
    if (☃ == null) {
      return false;
    }
    this.b = ☃.b;
    this.c = ☃.c;
    this.d = ☃.d;
    return true;
  }
  
  public boolean b()
  {
    return !this.a.x().n();
  }
  
  public void c()
  {
    this.a.x().a(this.b, this.c, this.d, this.e);
  }
  
  private bbj f()
  {
    Random ☃ = this.a.bF();
    cj ☃ = new cj(this.a.p, this.a.bl().b, this.a.r);
    for (int ☃ = 0; ☃ < 10; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(20) - 10, ☃.nextInt(6) - 3, ☃.nextInt(20) - 10);
      if ((!this.f.h(☃)) && (this.a.a(☃) < 0.0F)) {
        return new bbj(☃.p(), ☃.q(), ☃.r());
      }
    }
    return null;
  }
}
