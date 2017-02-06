import java.util.Random;

public class vm
{
  private static bbj a = bbj.a;
  
  public static bbj a(sh ☃, int ☃, int ☃)
  {
    return c(☃, ☃, ☃, null);
  }
  
  public static bbj a(sh ☃, int ☃, int ☃, bbj ☃)
  {
    a = ☃.a(☃.p, ☃.q, ☃.r);
    return c(☃, ☃, ☃, a);
  }
  
  public static bbj b(sh ☃, int ☃, int ☃, bbj ☃)
  {
    a = new bbj(☃.p, ☃.q, ☃.r).d(☃);
    return c(☃, ☃, ☃, a);
  }
  
  private static bbj c(sh ☃, int ☃, int ☃, bbj ☃)
  {
    vf ☃ = ☃.x();
    Random ☃ = ☃.bF();
    boolean ☃ = false;
    int ☃ = 0;int ☃ = 0;int ☃ = 0;
    float ☃ = -99999.0F;
    boolean ☃;
    boolean ☃;
    if (☃.cY())
    {
      double ☃ = ☃.cV().e(on.c(☃.p), on.c(☃.q), on.c(☃.r)) + 4.0D;
      double ☃ = ☃.cW() + ☃;
      ☃ = ☃ < ☃ * ☃;
    }
    else
    {
      ☃ = false;
    }
    for (int ☃ = 0; ☃ < 10; ☃++)
    {
      int ☃ = ☃.nextInt(2 * ☃ + 1) - ☃;
      int ☃ = ☃.nextInt(2 * ☃ + 1) - ☃;
      int ☃ = ☃.nextInt(2 * ☃ + 1) - ☃;
      if ((☃ == null) || (☃ * ☃.b + ☃ * ☃.d >= 0.0D))
      {
        if ((☃.cY()) && (☃ > 1))
        {
          cj ☃ = ☃.cV();
          if (☃.p > ☃.p()) {
            ☃ -= ☃.nextInt(☃ / 2);
          } else {
            ☃ += ☃.nextInt(☃ / 2);
          }
          if (☃.r > ☃.r()) {
            ☃ -= ☃.nextInt(☃ / 2);
          } else {
            ☃ += ☃.nextInt(☃ / 2);
          }
        }
        ☃ += on.c(☃.p);
        ☃ += on.c(☃.q);
        ☃ += on.c(☃.r);
        
        cj ☃ = new cj(☃, ☃, ☃);
        if (((!☃) || (☃.f(☃))) && (☃.b(☃)))
        {
          float ☃ = ☃.a(☃);
          if (☃ > ☃)
          {
            ☃ = ☃;
            ☃ = ☃;
            ☃ = ☃;
            ☃ = ☃;
            ☃ = true;
          }
        }
      }
    }
    if (☃) {
      return new bbj(☃, ☃, ☃);
    }
    return null;
  }
}
