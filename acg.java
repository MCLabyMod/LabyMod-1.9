import com.google.common.base.Predicate;
import java.util.List;

public class acg
  extends ado
{
  public acg()
  {
    a(acq.k);
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    List<rp> ☃ = ☃.a(rp.class, ☃.bl().g(2.0D), new Predicate()
    {
      public boolean a(rp ☃)
      {
        return (☃ != null) && (☃.au()) && ((☃.w() instanceof wu));
      }
    });
    if (!☃.isEmpty())
    {
      rp ☃ = (rp)☃.get(0);
      ☃.a(☃.j() - 0.5F);
      
      ☃.a(null, ☃.p, ☃.q, ☃.r, ng.J, nh.g, 1.0F, 1.0F);
      return new qp(qo.a, a(☃, ☃, new adq(ads.bK)));
    }
    bbi ☃ = a(☃, ☃, true);
    if (☃ == null) {
      return new qp(qo.b, ☃);
    }
    if (☃.a == bbi.a.b)
    {
      cj ☃ = ☃.a();
      if ((!☃.a(☃, ☃)) || (!☃.a(☃.a(☃.b), ☃.b, ☃))) {
        return new qp(qo.b, ☃);
      }
      if (☃.o(☃).a() == axe.h)
      {
        ☃.a(☃, ☃.p, ☃.q, ☃.r, ng.I, nh.g, 1.0F, 1.0F);
        return new qp(qo.a, a(☃, ☃, new adq(ads.bG)));
      }
    }
    return new qp(qo.b, ☃);
  }
  
  protected adq a(adq ☃, zj ☃, adq ☃)
  {
    ☃.b -= 1;
    ☃.b(nt.b(this));
    if (☃.b <= 0) {
      return ☃;
    }
    if (!☃.br.c(☃)) {
      ☃.a(☃, false);
    }
    return ☃;
  }
}
