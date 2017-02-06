import java.util.Random;

public class acj
  extends ado
{
  private ajt a;
  
  public acj(ajt ☃)
  {
    this.j = 1;
    this.a = ☃;
    a(acq.f);
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    boolean ☃ = this.a == aju.a;
    
    bbi ☃ = a(☃, ☃, ☃);
    if (☃ == null) {
      return new qp(qo.b, ☃);
    }
    if (☃.a == bbi.a.b)
    {
      cj ☃ = ☃.a();
      if (!☃.a(☃, ☃)) {
        return new qp(qo.c, ☃);
      }
      if (☃)
      {
        if (!☃.a(☃.a(☃.b), ☃.b, ☃)) {
          return new qp(qo.c, ☃);
        }
        arc ☃ = ☃.o(☃);
        axe ☃ = ☃.a();
        if ((☃ == axe.h) && (((Integer)☃.c(amo.b)).intValue() == 0))
        {
          ☃.a(☃, aju.a.u(), 11);
          ☃.b(nt.b(this));
          ☃.a(ng.N, 1.0F, 1.0F);
          return new qp(qo.a, a(☃, ☃, ads.az));
        }
        if ((☃ == axe.i) && (((Integer)☃.c(amo.b)).intValue() == 0))
        {
          ☃.a(ng.O, 1.0F, 1.0F);
          ☃.a(☃, aju.a.u(), 11);
          ☃.b(nt.b(this));
          return new qp(qo.a, a(☃, ☃, ads.aA));
        }
        return new qp(qo.c, ☃);
      }
      boolean ☃ = ☃.o(☃).t().a(☃, ☃);
      cj ☃ = (☃) && (☃.b == cq.b) ? ☃ : ☃.a(☃.b);
      if (!☃.a(☃, ☃.b, ☃)) {
        return new qp(qo.c, ☃);
      }
      if (a(☃, ☃, ☃))
      {
        ☃.b(nt.b(this));
        if (!☃.bJ.d) {
          return new qp(qo.a, new adq(ads.ay));
        }
        return new qp(qo.a, ☃);
      }
      return new qp(qo.c, ☃);
    }
    return new qp(qo.b, ☃);
  }
  
  private adq a(adq ☃, zj ☃, ado ☃)
  {
    if (☃.bJ.d) {
      return ☃;
    }
    if (--☃.b <= 0) {
      return new adq(☃);
    }
    if (!☃.br.c(new adq(☃))) {
      ☃.a(new adq(☃), false);
    }
    return ☃;
  }
  
  public boolean a(zj ☃, aht ☃, cj ☃)
  {
    if (this.a == aju.a) {
      return false;
    }
    arc ☃ = ☃.o(☃);
    axe ☃ = ☃.a();
    boolean ☃ = !☃.a();
    boolean ☃ = ☃.t().a(☃, ☃);
    if ((☃.d(☃)) || (☃) || (☃))
    {
      if ((☃.s.l()) && (this.a == aju.i))
      {
        int ☃ = ☃.p();
        int ☃ = ☃.q();
        int ☃ = ☃.r();
        
        ☃.a(☃, ☃, ng.bv, nh.e, 0.5F, 2.6F + (☃.r.nextFloat() - ☃.r.nextFloat()) * 0.8F);
        for (int ☃ = 0; ☃ < 8; ☃++) {
          ☃.a(cy.m, ☃ + Math.random(), ☃ + Math.random(), ☃ + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
      else
      {
        if ((!☃.E) && ((☃) || (☃)) && (!☃.d())) {
          ☃.b(☃, true);
        }
        nf ☃ = this.a == aju.k ? ng.M : ng.L;
        ☃.a(☃, ☃, ☃, nh.e, 1.0F, 1.0F);
        
        ☃.a(☃, this.a.u(), 11);
      }
      return true;
    }
    return false;
  }
}
