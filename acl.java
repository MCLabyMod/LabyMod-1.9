import java.util.Random;

public class acl
  extends adk
{
  public acl(int ☃, float ☃)
  {
    super(☃, ☃, false);
  }
  
  public adq a(adq ☃, aht ☃, sa ☃)
  {
    adq ☃ = super.a(☃, ☃, ☃);
    if (!☃.E)
    {
      double ☃ = ☃.p;
      double ☃ = ☃.q;
      double ☃ = ☃.r;
      for (int ☃ = 0; ☃ < 16; ☃++)
      {
        double ☃ = ☃.p + (☃.bF().nextDouble() - 0.5D) * 16.0D;
        double ☃ = on.a(☃.q + (☃.bF().nextInt(16) - 8), 0.0D, ☃.Z() - 1);
        double ☃ = ☃.r + (☃.bF().nextDouble() - 0.5D) * 16.0D;
        if (☃.k(☃, ☃, ☃))
        {
          ☃.a(null, ☃, ☃, ☃, ng.af, nh.h, 1.0F, 1.0F);
          ☃.a(ng.af, 1.0F, 1.0F);
          
          break;
        }
      }
      if ((☃ instanceof zj)) {
        ((zj)☃).da().a(this, 20);
      }
    }
    return ☃;
  }
}
