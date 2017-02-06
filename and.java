import com.google.common.collect.Lists;
import java.util.List;

public class and
  extends ajn
{
  private static final List<nf> a = Lists.newArrayList(new nf[] { ng.dJ, ng.dH, ng.dM, ng.dK, ng.dI });
  
  public and()
  {
    super(axe.d);
    a(acq.d);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    boolean ☃ = ☃.y(☃);
    
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aql))
    {
      aql ☃ = (aql)☃;
      if (☃.f != ☃)
      {
        if (☃) {
          ☃.a(☃, ☃);
        }
        ☃.f = ☃;
      }
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aql))
    {
      aql ☃ = (aql)☃;
      
      ☃.b();
      ☃.a(☃, ☃);
      ☃.b(nt.U);
    }
    return true;
  }
  
  public void a(aht ☃, cj ☃, zj ☃)
  {
    if (☃.E) {
      return;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aql))
    {
      ((aql)☃).a(☃, ☃);
      ☃.b(nt.T);
    }
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aql();
  }
  
  private nf e(int ☃)
  {
    if ((☃ < 0) || (☃ >= a.size())) {
      ☃ = 0;
    }
    return (nf)a.get(☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, int ☃, int ☃)
  {
    float ☃ = (float)Math.pow(2.0D, (☃ - 12) / 12.0D);
    
    ☃.a(null, ☃, e(☃), nh.e, 3.0F, ☃);
    ☃.a(cy.x, ☃.p() + 0.5D, ☃.q() + 1.2D, ☃.r() + 0.5D, ☃ / 24.0D, 0.0D, 0.0D, new int[0]);
    return true;
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
}
