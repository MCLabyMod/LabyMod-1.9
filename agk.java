import java.util.Random;

public class agk
  extends agm
{
  protected agk(agm.a ☃, rw... ☃)
  {
    super(☃, agn.j, ☃);
    
    c("durability");
  }
  
  public int a(int ☃)
  {
    return 5 + (☃ - 1) * 8;
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
    if (☃.e()) {
      return true;
    }
    return super.a(☃);
  }
  
  public static boolean a(adq ☃, int ☃, Random ☃)
  {
    if (((☃.b() instanceof abw)) && (☃.nextFloat() < 0.6F)) {
      return false;
    }
    return ☃.nextInt(☃ + 1) > 0;
  }
}
