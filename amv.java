import java.util.Random;

public class amv
  extends ajt
{
  public static final arn a = arn.a("snowy");
  
  protected amv()
  {
    super(axe.b, axf.z);
    w(this.A.b().a(a, Boolean.valueOf(false)));
    a(true);
    a(acq.b);
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃.a()).t();
    return ☃.a(a, Boolean.valueOf((☃ == aju.aJ) || (☃ == aju.aH)));
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    if ((☃.k(☃.a()) < 4) && (☃.o(☃.a()).c() > 2))
    {
      ☃.a(☃, aju.d.u().a(akt.a, akt.a.a));
      return;
    }
    if (☃.k(☃.a()) >= 9) {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        cj ☃ = ☃.a(☃.nextInt(3) - 1, ☃.nextInt(5) - 3, ☃.nextInt(3) - 1);
        arc ☃ = ☃.o(☃);
        arc ☃ = ☃.o(☃.a());
        if ((☃.t() == aju.d) && (☃.c(akt.a) == akt.a.a) && (☃.k(☃.a()) >= 4) && (☃.c() <= 2)) {
          ☃.a(☃, u());
        }
      }
    }
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    if (☃.nextInt(10) == 0) {
      ☃.a(cy.w, ☃.p() + ☃.nextFloat(), ☃.q() + 1.1F, ☃.r() + ☃.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return aju.d.a(aju.d.u().a(akt.a, akt.a.a), ☃, ☃);
  }
  
  public int e(arc ☃)
  {
    return 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
