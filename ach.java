import java.util.Random;

public class ach
  extends ado
{
  public ach()
  {
    this.j = 1;
    e(384);
    a(acq.j);
    
    a(new kk("pull"), new adr()
    {
      public float a(adq ☃, aht ☃, sa ☃)
      {
        if (☃ == null) {
          return 0.0F;
        }
        adq ☃ = ☃.cv();
        if ((☃ == null) || (☃.b() != ads.f)) {
          return 0.0F;
        }
        return (☃.l() - ☃.cw()) / 20.0F;
      }
    });
    a(new kk("pulling"), new adr()
    {
      public float a(adq ☃, aht ☃, sa ☃)
      {
        return (☃ != null) && (☃.cs()) && (☃.cv() == ☃) ? 1.0F : 0.0F;
      }
    });
  }
  
  private adq a(zj ☃)
  {
    if (h_(☃.b(qm.b))) {
      return ☃.b(qm.b);
    }
    if (h_(☃.b(qm.a))) {
      return ☃.b(qm.a);
    }
    for (int ☃ = 0; ☃ < ☃.br.u_(); ☃++)
    {
      adq ☃ = ☃.br.a(☃);
      if (h_(☃)) {
        return ☃;
      }
    }
    return null;
  }
  
  protected boolean h_(adq ☃)
  {
    return (☃ != null) && ((☃.b() instanceof aby));
  }
  
  public void a(adq ☃, aht ☃, sa ☃, int ☃)
  {
    if (!(☃ instanceof zj)) {
      return;
    }
    zj ☃ = (zj)☃;
    boolean ☃ = (☃.bJ.d) || (ago.a(agq.x, ☃) > 0);
    adq ☃ = a(☃);
    if ((☃ == null) && (!☃)) {
      return;
    }
    if (☃ == null) {
      ☃ = new adq(ads.g);
    }
    int ☃ = e(☃) - ☃;
    float ☃ = b(☃);
    if (☃ < 0.1D) {
      return;
    }
    boolean ☃ = (☃) && (☃.b() == ads.g);
    if (!☃.E)
    {
      aby ☃ = (aby)((☃.b() instanceof aby) ? ☃.b() : ads.g);
      
      zm ☃ = ☃.a(☃, ☃, ☃);
      ☃.a(☃, ☃.w, ☃.v, 0.0F, ☃ * 3.0F, 1.0F);
      if (☃ == 1.0F) {
        ☃.a(true);
      }
      int ☃ = ago.a(agq.u, ☃);
      if (☃ > 0) {
        ☃.c(☃.k() + ☃ * 0.5D + 0.5D);
      }
      int ☃ = ago.a(agq.v, ☃);
      if (☃ > 0) {
        ☃.a(☃);
      }
      if (ago.a(agq.w, ☃) > 0) {
        ☃.g(100);
      }
      ☃.a(1, ☃);
      if (☃) {
        ☃.c = zm.a.c;
      }
      ☃.a(☃);
    }
    ☃.a(null, ☃.p, ☃.q, ☃.r, ng.v, nh.g, 1.0F, 1.0F / (i.nextFloat() * 0.4F + 1.2F) + ☃ * 0.5F);
    if (!☃)
    {
      ☃.b -= 1;
      if (☃.b == 0) {
        ☃.br.d(☃);
      }
    }
    ☃.b(nt.b(this));
  }
  
  public static float b(int ☃)
  {
    float ☃ = ☃ / 20.0F;
    ☃ = (☃ * ☃ + ☃ * 2.0F) / 3.0F;
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    return ☃;
  }
  
  public int e(adq ☃)
  {
    return 72000;
  }
  
  public afa f(adq ☃)
  {
    return afa.e;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    boolean ☃ = a(☃) != null;
    if ((☃.bJ.d) || (☃))
    {
      ☃.c(☃);
      return new qp(qo.a, ☃);
    }
    if (!☃) {
      return new qp(qo.c, ☃);
    }
    return new qp(qo.b, ☃);
  }
  
  public int c()
  {
    return 1;
  }
}
