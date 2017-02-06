import java.util.Random;

public class qj
{
  private static final Random a = new Random();
  
  public static void a(aht ☃, cj ☃, qg ☃)
  {
    a(☃, ☃.p(), ☃.q(), ☃.r(), ☃);
  }
  
  public static void a(aht ☃, rr ☃, qg ☃)
  {
    a(☃, ☃.p, ☃.q, ☃.r, ☃);
  }
  
  private static void a(aht ☃, double ☃, double ☃, double ☃, qg ☃)
  {
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        a(☃, ☃, ☃, ☃, ☃);
      }
    }
  }
  
  public static void a(aht ☃, double ☃, double ☃, double ☃, adq ☃)
  {
    float ☃ = a.nextFloat() * 0.8F + 0.1F;
    float ☃ = a.nextFloat() * 0.8F + 0.1F;
    float ☃ = a.nextFloat() * 0.8F + 0.1F;
    while (☃.b > 0)
    {
      int ☃ = a.nextInt(21) + 10;
      if (☃ > ☃.b) {
        ☃ = ☃.b;
      }
      ☃.b -= ☃;
      
      yd ☃ = new yd(☃, ☃ + ☃, ☃ + ☃, ☃ + ☃, new adq(☃.b(), ☃, ☃.i()));
      if (☃.n()) {
        ☃.k().d((dn)☃.o().b());
      }
      float ☃ = 0.05F;
      ☃.s = (a.nextGaussian() * ☃);
      ☃.t = (a.nextGaussian() * ☃ + 0.20000000298023224D);
      ☃.u = (a.nextGaussian() * ☃);
      
      ☃.a(☃);
    }
  }
}
