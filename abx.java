import java.util.List;
import java.util.Random;

public class abx
  extends ado
{
  public abx()
  {
    a(acq.c);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃ == cq.a) {
      return qo.c;
    }
    boolean ☃ = ☃.o(☃).t().a(☃, ☃);
    cj ☃ = ☃ ? ☃ : ☃.a(☃);
    if (!☃.a(☃, ☃, ☃)) {
      return qo.c;
    }
    cj ☃ = ☃.a();
    boolean ☃ = (!☃.d(☃)) && (!☃.o(☃).t().a(☃, ☃));
    ☃ |= ((!☃.d(☃)) && (!☃.o(☃).t().a(☃, ☃)));
    if (☃) {
      return qo.c;
    }
    double ☃ = ☃.p();
    double ☃ = ☃.q();
    double ☃ = ☃.r();
    
    List<rr> ☃ = ☃.b(null, new bbh(☃, ☃, ☃, ☃ + 1.0D, ☃ + 2.0D, ☃ + 1.0D));
    if (!☃.isEmpty()) {
      return qo.c;
    }
    if (!☃.E)
    {
      ☃.g(☃);
      ☃.g(☃);
      
      xq ☃ = new xq(☃, ☃ + 0.5D, ☃, ☃ + 0.5D);
      float ☃ = on.d((on.g(☃.v - 180.0F) + 22.5F) / 45.0F) * 45.0F;
      ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, ☃, 0.0F);
      a(☃, ☃.r);
      aeu.a(☃, ☃, ☃, ☃);
      ☃.a(☃);
      
      ☃.a(null, ☃.p, ☃.q, ☃.r, ng.m, nh.e, 0.75F, 0.8F);
    }
    ☃.b -= 1;
    return qo.a;
  }
  
  private void a(xq ☃, Random ☃)
  {
    dc ☃ = ☃.w();
    float ☃ = ☃.nextFloat() * 5.0F;
    float ☃ = ☃.nextFloat() * 20.0F - 10.0F;
    dc ☃ = new dc(☃.b() + ☃, ☃.c() + ☃, ☃.d());
    ☃.a(☃);
    
    ☃ = ☃.x();
    ☃ = ☃.nextFloat() * 10.0F - 5.0F;
    ☃ = new dc(☃.b(), ☃.c() + ☃, ☃.d());
    ☃.b(☃);
  }
}
