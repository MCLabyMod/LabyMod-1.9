import java.util.Random;

public class agz
  extends agm
{
  public agz(agm.a ☃, rw... ☃)
  {
    super(☃, agn.e, ☃);
    
    c("thorns");
  }
  
  public int a(int ☃)
  {
    return 10 + 20 * (☃ - 1);
  }
  
  public int b(int ☃)
  {
    return super.a(☃) + 50;
  }
  
  public int b()
  {
    return 3;
  }
  
  public boolean a(adq ☃)
  {
    if ((☃.b() instanceof abw)) {
      return true;
    }
    return super.a(☃);
  }
  
  public void b(sa ☃, rr ☃, int ☃)
  {
    Random ☃ = ☃.bF();
    adq ☃ = ago.b(agq.h, ☃);
    if (a(☃, ☃))
    {
      if (☃ != null) {
        ☃.a(rc.a(☃), b(☃, ☃));
      }
      if (☃ != null) {
        ☃.a(3, ☃);
      }
    }
    else if (☃ != null)
    {
      ☃.a(1, ☃);
    }
  }
  
  public static boolean a(int ☃, Random ☃)
  {
    if (☃ <= 0) {
      return false;
    }
    return ☃.nextFloat() < 0.15F * ☃;
  }
  
  public static int b(int ☃, Random ☃)
  {
    if (☃ > 10) {
      return ☃ - 10;
    }
    return 1 + ☃.nextInt(4);
  }
}
