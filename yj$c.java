import java.util.Random;
import java.util.Set;

class yj$c
  extends tk
{
  private final yj a;
  
  public yj$c(yj ☃)
  {
    this.a = ☃;
  }
  
  public boolean a()
  {
    if (this.a.db() != null) {
      return false;
    }
    if (!this.a.l.U().b("mobGriefing")) {
      return false;
    }
    if (this.a.bF().nextInt(20) != 0) {
      return false;
    }
    return true;
  }
  
  public void e()
  {
    Random ☃ = this.a.bF();
    aht ☃ = this.a.l;
    
    int ☃ = on.c(this.a.p - 2.0D + ☃.nextDouble() * 4.0D);
    int ☃ = on.c(this.a.q + ☃.nextDouble() * 3.0D);
    int ☃ = on.c(this.a.r - 2.0D + ☃.nextDouble() * 4.0D);
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    
    bbi ☃ = ☃.a(new bbj(on.c(this.a.p) + 0.5F, ☃ + 0.5F, on.c(this.a.r) + 0.5F), new bbj(☃ + 0.5F, ☃ + 0.5F, ☃ + 0.5F), false, true, false);
    boolean ☃ = (☃ != null) && (☃.a().equals(☃));
    if ((yj.dd().contains(☃)) && (☃))
    {
      this.a.a(☃);
      ☃.g(☃);
    }
  }
}
