import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class anz
  extends apf
{
  private static Map<aht, List<anz.a>> g = ;
  private final boolean B;
  
  static class a
  {
    cj a;
    long b;
    
    public a(cj ☃, long ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
  }
  
  private boolean a(aht ☃, cj ☃, boolean ☃)
  {
    if (!g.containsKey(☃)) {
      g.put(☃, Lists.newArrayList());
    }
    List<anz.a> ☃ = (List)g.get(☃);
    if (☃) {
      ☃.add(new anz.a(☃, ☃.P()));
    }
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      anz.a ☃ = (anz.a)☃.get(☃);
      if (☃.a.equals(☃))
      {
        ☃++;
        if (☃ >= 8) {
          return true;
        }
      }
    }
    return false;
  }
  
  protected anz(boolean ☃)
  {
    this.B = ☃;
    a(true);
    a(null);
  }
  
  public int a(aht ☃)
  {
    return 2;
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    if (this.B) {
      for (cq ☃ : cq.values()) {
        ☃.d(☃.a(☃), this);
      }
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    if (this.B) {
      for (cq ☃ : cq.values()) {
        ☃.d(☃.a(☃), this);
      }
    }
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if ((this.B) && (☃.c(a) != ☃)) {
      return 15;
    }
    return 0;
  }
  
  private boolean g(aht ☃, cj ☃, arc ☃)
  {
    cq ☃ = ((cq)☃.c(a)).d();
    
    return ☃.b(☃.a(☃), ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    boolean ☃ = g(☃, ☃, ☃);
    
    List<anz.a> ☃ = (List)g.get(☃);
    while ((☃ != null) && (!☃.isEmpty()) && (☃.P() - ((anz.a)☃.get(0)).b > 60L)) {
      ☃.remove(0);
    }
    if (this.B)
    {
      if (☃)
      {
        ☃.a(☃, aju.aE.u().a(a, ☃.c(a)), 3);
        if (a(☃, ☃, true))
        {
          ☃.a(null, ☃, ng.eF, nh.e, 0.5F, 2.6F + (☃.r.nextFloat() - ☃.r.nextFloat()) * 0.8F);
          for (int ☃ = 0; ☃ < 5; ☃++)
          {
            double ☃ = ☃.p() + ☃.nextDouble() * 0.6D + 0.2D;
            double ☃ = ☃.q() + ☃.nextDouble() * 0.6D + 0.2D;
            double ☃ = ☃.r() + ☃.nextDouble() * 0.6D + 0.2D;
            
            ☃.a(cy.l, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
          }
          ☃.a(☃, ☃.o(☃).t(), 160);
        }
      }
    }
    else if ((!☃) && 
      (!a(☃, ☃, false))) {
      ☃.a(☃, aju.aF.u().a(a, ☃.c(a)), 3);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (e(☃, ☃, ☃)) {
      return;
    }
    if (this.B == g(☃, ☃, ☃)) {
      ☃.a(☃, this, a(☃));
    }
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (☃ == cq.a) {
      return ☃.a(☃, ☃, ☃);
    }
    return 0;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.aF);
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (!this.B) {
      return;
    }
    double ☃ = ☃.p() + 0.5D + (☃.nextDouble() - 0.5D) * 0.2D;
    double ☃ = ☃.q() + 0.7D + (☃.nextDouble() - 0.5D) * 0.2D;
    double ☃ = ☃.r() + 0.5D + (☃.nextDouble() - 0.5D) * 0.2D;
    
    cq ☃ = (cq)☃.c(a);
    if (☃.k().c())
    {
      cq ☃ = ☃.d();
      
      double ☃ = 0.27D;
      
      ☃ += 0.27D * ☃.g();
      ☃ += 0.22D;
      ☃ += 0.27D * ☃.i();
    }
    ☃.a(cy.E, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.aF);
  }
  
  public boolean b(ajt ☃)
  {
    return (☃ == aju.aE) || (☃ == aju.aF);
  }
}
