import java.util.Random;

public class amu
  extends ajy
  implements ajv
{
  protected static final bbh a = new bbh(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
  
  protected amu()
  {
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.nextInt(25) == 0)
    {
      int ☃ = 5;
      int ☃ = 4;
      for (cj ☃ : cj.b(☃.a(-4, -1, -4), ☃.a(4, 1, 4))) {
        if (☃.o(☃).t() == this)
        {
          ☃--;
          if (☃ <= 0) {
            return;
          }
        }
      }
      cj ☃ = ☃.a(☃.nextInt(3) - 1, ☃.nextInt(2) - ☃.nextInt(2), ☃.nextInt(3) - 1);
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        if ((☃.d(☃)) && (f(☃, ☃, u()))) {
          ☃ = ☃;
        }
        ☃ = ☃.a(☃.nextInt(3) - 1, ☃.nextInt(2) - ☃.nextInt(2), ☃.nextInt(3) - 1);
      }
      if ((☃.d(☃)) && (f(☃, ☃, u()))) {
        ☃.a(☃, u(), 2);
      }
    }
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (super.a(☃, ☃)) && (f(☃, ☃, u()));
  }
  
  protected boolean i(arc ☃)
  {
    return ☃.b();
  }
  
  public boolean f(aht ☃, cj ☃, arc ☃)
  {
    if ((☃.q() < 0) || (☃.q() >= 256)) {
      return false;
    }
    arc ☃ = ☃.o(☃.b());
    if (☃.t() == aju.bw) {
      return true;
    }
    if ((☃.t() == aju.d) && (☃.c(akt.a) == akt.a.c)) {
      return true;
    }
    return (☃.j(☃) < 13) && (i(☃));
  }
  
  public boolean c(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    ☃.g(☃);
    
    aud ☃ = null;
    if (this == aju.P) {
      ☃ = new auj(aju.bg);
    } else if (this == aju.Q) {
      ☃ = new auj(aju.bh);
    }
    if ((☃ != null) && (☃.b(☃, ☃, ☃))) {
      return true;
    }
    ☃.a(☃, ☃, 3);
    return false;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return true;
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return ☃.nextFloat() < 0.4D;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    c(☃, ☃, ☃, ☃);
  }
}
