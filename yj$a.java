import java.util.Random;

class yj$a
  extends tk
{
  private final yj a;
  
  public yj$a(yj ☃)
  {
    this.a = ☃;
  }
  
  public boolean a()
  {
    if (this.a.db() == null) {
      return false;
    }
    if (!this.a.l.U().b("mobGriefing")) {
      return false;
    }
    if (this.a.bF().nextInt(2000) != 0) {
      return false;
    }
    return true;
  }
  
  public void e()
  {
    Random ☃ = this.a.bF();
    aht ☃ = this.a.l;
    
    int ☃ = on.c(this.a.p - 1.0D + ☃.nextDouble() * 2.0D);
    int ☃ = on.c(this.a.q + ☃.nextDouble() * 2.0D);
    int ☃ = on.c(this.a.r - 1.0D + ☃.nextDouble() * 2.0D);
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = ☃.o(☃);
    arc ☃ = ☃.o(☃.b());
    
    arc ☃ = this.a.db();
    if ((☃ != null) && (a(☃, ☃, ☃.t(), ☃, ☃)))
    {
      ☃.a(☃, ☃, 3);
      this.a.a(null);
    }
  }
  
  private boolean a(aht ☃, cj ☃, ajt ☃, arc ☃, arc ☃)
  {
    if (!☃.a(☃, ☃)) {
      return false;
    }
    if (☃.a() != axe.a) {
      return false;
    }
    if (☃.a() == axe.a) {
      return false;
    }
    if (!☃.h()) {
      return false;
    }
    return true;
  }
}
