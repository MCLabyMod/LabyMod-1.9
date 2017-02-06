import java.util.Random;

public class alb
  extends ajn
{
  protected alb(axe ☃)
  {
    super(☃);
    a(1.0F);
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aqq();
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    arc ☃ = ☃.o(☃.a(☃));
    ajt ☃ = ☃.t();
    return (!☃.p()) && (☃ != aju.db);
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    apv ☃ = ☃.r(☃);
    if (!(☃ instanceof aqq)) {
      return;
    }
    int ☃ = ((aqq)☃).i();
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      double ☃ = ☃.p() + ☃.nextFloat();
      double ☃ = ☃.q() + ☃.nextFloat();
      double ☃ = ☃.r() + ☃.nextFloat();
      double ☃ = (☃.nextFloat() - 0.5D) * 0.5D;
      double ☃ = (☃.nextFloat() - 0.5D) * 0.5D;
      double ☃ = (☃.nextFloat() - 0.5D) * 0.5D;
      
      int ☃ = ☃.nextInt(2) * 2 - 1;
      if (☃.nextBoolean())
      {
        ☃ = ☃.r() + 0.5D + 0.25D * ☃;
        ☃ = ☃.nextFloat() * 2.0F * ☃;
      }
      else
      {
        ☃ = ☃.p() + 0.5D + 0.25D * ☃;
        ☃ = ☃.nextFloat() * 2.0F * ☃;
      }
      ☃.a(cy.y, ☃, ☃, ☃, ☃, ☃, ☃, new int[0]);
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return null;
  }
  
  public axf r(arc ☃)
  {
    return axf.E;
  }
}
