import java.util.Random;

public class akx
  extends ajt
{
  protected static final bbh a = new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
  
  public akx()
  {
    super(axe.D, axf.E);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a;
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    ☃.a(☃, this, a(☃));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    ☃.a(☃, this, a(☃));
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    b(☃, ☃);
  }
  
  private void b(aht ☃, cj ☃)
  {
    if ((!alh.i(☃.o(☃.b()))) || (☃.q() < 0)) {
      return;
    }
    int ☃ = 32;
    if ((alh.f) || (!☃.a(☃.a(-☃, -☃, -☃), ☃.a(☃, ☃, ☃))))
    {
      ☃.g(☃);
      
      cj ☃ = ☃;
      while ((alh.i(☃.o(☃))) && (☃.q() > 0)) {
        ☃ = ☃.b();
      }
      if (☃.q() > 0) {
        ☃.a(☃, u(), 2);
      }
    }
    else
    {
      ☃.a(new yc(☃, ☃.p() + 0.5F, ☃.q(), ☃.r() + 0.5F, u()));
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    c(☃, ☃);
    return true;
  }
  
  public void a(aht ☃, cj ☃, zj ☃)
  {
    c(☃, ☃);
  }
  
  private void c(aht ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    if (☃.t() != this) {
      return;
    }
    for (int ☃ = 0; ☃ < 1000; ☃++)
    {
      cj ☃ = ☃.a(☃.r.nextInt(16) - ☃.r.nextInt(16), ☃.r.nextInt(8) - ☃.r.nextInt(8), ☃.r.nextInt(16) - ☃.r.nextInt(16));
      if (☃.o(☃).t().x == axe.a)
      {
        if (☃.E)
        {
          for (int ☃ = 0; ☃ < 128; ☃++)
          {
            double ☃ = ☃.r.nextDouble();
            float ☃ = (☃.r.nextFloat() - 0.5F) * 0.2F;
            float ☃ = (☃.r.nextFloat() - 0.5F) * 0.2F;
            float ☃ = (☃.r.nextFloat() - 0.5F) * 0.2F;
            
            double ☃ = ☃.p() + (☃.p() - ☃.p()) * ☃ + (☃.r.nextDouble() - 0.5D) + 0.5D;
            double ☃ = ☃.q() + (☃.q() - ☃.q()) * ☃ + ☃.r.nextDouble() - 0.5D;
            double ☃ = ☃.r() + (☃.r() - ☃.r()) * ☃ + (☃.r.nextDouble() - 0.5D) + 0.5D;
            ☃.a(cy.y, ☃, ☃, ☃, ☃, ☃, ☃, new int[0]);
          }
        }
        else
        {
          ☃.a(☃, ☃, 2);
          ☃.g(☃);
        }
        return;
      }
    }
  }
  
  public int a(aht ☃)
  {
    return 5;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return true;
  }
}
