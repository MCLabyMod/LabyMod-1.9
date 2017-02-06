import java.util.List;

public final class zt
{
  public static bbi a(rr ☃, boolean ☃, boolean ☃, rr ☃)
  {
    double ☃ = ☃.p;
    double ☃ = ☃.q;
    double ☃ = ☃.r;
    double ☃ = ☃.s;
    double ☃ = ☃.t;
    double ☃ = ☃.u;
    aht ☃ = ☃.l;
    
    bbj ☃ = new bbj(☃, ☃, ☃);
    bbj ☃ = new bbj(☃ + ☃, ☃ + ☃, ☃ + ☃);
    bbi ☃ = ☃.a(☃, ☃, false, true, false);
    if (☃)
    {
      if (☃ != null) {
        ☃ = new bbj(☃.c.b, ☃.c.c, ☃.c.d);
      }
      rr ☃ = null;
      List<rr> ☃ = ☃.b(☃, ☃.bl().a(☃, ☃, ☃).g(1.0D));
      double ☃ = 0.0D;
      for (int ☃ = 0; ☃ < ☃.size(); ☃++)
      {
        rr ☃ = (rr)☃.get(☃);
        if ((☃.ap()) && ((☃) || (!☃.s(☃))) && (!☃.Q))
        {
          bbh ☃ = ☃.bl().g(0.30000001192092896D);
          bbi ☃ = ☃.a(☃, ☃);
          if (☃ != null)
          {
            double ☃ = ☃.g(☃.c);
            if ((☃ < ☃) || (☃ == 0.0D))
            {
              ☃ = ☃;
              ☃ = ☃;
            }
          }
        }
      }
      if (☃ != null) {
        ☃ = new bbi(☃);
      }
    }
    return ☃;
  }
  
  public static final void a(rr ☃, float ☃)
  {
    double ☃ = ☃.s;
    double ☃ = ☃.t;
    double ☃ = ☃.u;
    
    float ☃ = on.a(☃ * ☃ + ☃ * ☃);
    ☃.v = ((float)(on.b(☃, ☃) * 57.2957763671875D) + 90.0F);
    ☃.w = ((float)(on.b(☃, ☃) * 57.2957763671875D) - 90.0F);
    while (☃.w - ☃.y < -180.0F) {
      ☃.y -= 360.0F;
    }
    while (☃.w - ☃.y >= 180.0F) {
      ☃.y += 360.0F;
    }
    while (☃.v - ☃.x < -180.0F) {
      ☃.x -= 360.0F;
    }
    while (☃.v - ☃.x >= 180.0F) {
      ☃.x += 360.0F;
    }
    ☃.w = (☃.y + (☃.w - ☃.y) * ☃);
    ☃.v = (☃.x + (☃.v - ☃.x) * ☃);
  }
}
