import net.minecraft.server.MinecraftServer;

public class lq
  implements ahv
{
  private MinecraftServer a;
  private lp b;
  
  public lq(MinecraftServer ☃, lp ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(int ☃, boolean ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃) {}
  
  public void a(rr ☃)
  {
    this.b.v().a(☃);
    if ((☃ instanceof lr)) {
      this.b.s.a((lr)☃);
    }
  }
  
  public void b(rr ☃)
  {
    this.b.v().b(☃);
    this.b.ad().a(☃);
    if ((☃ instanceof lr)) {
      this.b.s.b((lr)☃);
    }
  }
  
  public void a(zj ☃, nf ☃, nh ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    this.a.al().a(☃, ☃, ☃, ☃, ☃ > 1.0F ? 16.0F * ☃ : 16.0D, this.b.s.p().a(), new hz(☃, ☃, ☃, ☃, ☃, ☃, ☃));
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃) {}
  
  public void a(aht ☃, cj ☃, arc ☃, arc ☃, int ☃)
  {
    this.b.w().a(☃);
  }
  
  public void a(cj ☃) {}
  
  public void a(nf ☃, cj ☃) {}
  
  public void a(zj ☃, int ☃, cj ☃, int ☃)
  {
    this.a.al().a(☃, ☃.p(), ☃.q(), ☃.r(), 64.0D, this.b.s.p().a(), new gq(☃, ☃, ☃, false));
  }
  
  public void a(int ☃, cj ☃, int ☃)
  {
    this.a.al().a(new gq(☃, ☃, ☃, true));
  }
  
  public void b(int ☃, cj ☃, int ☃)
  {
    for (lr ☃ : this.a.al().v()) {
      if ((☃ != null) && (☃.l == this.b) && (☃.O() != ☃))
      {
        double ☃ = ☃.p() - ☃.p;
        double ☃ = ☃.q() - ☃.q;
        double ☃ = ☃.r() - ☃.r;
        if (☃ * ☃ + ☃ * ☃ + ☃ * ☃ < 1024.0D) {
          ☃.a.a(new fr(☃, ☃, ☃));
        }
      }
    }
  }
}
