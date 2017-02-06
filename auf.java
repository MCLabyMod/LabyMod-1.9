import java.util.Random;

public class auf
  extends avg
{
  private final arc a;
  private final arc b;
  
  public auf(arc ☃, arc ☃)
  {
    super(false);
    this.b = ☃;
    this.a = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    arc ☃;
    while ((((☃ = ☃.o(☃)).a() == axe.a) || (☃.a() == axe.j)) && (☃.q() > 0)) {
      ☃ = ☃.b();
    }
    ajt ☃ = ☃.o(☃).t();
    if ((☃ == aju.d) || (☃ == aju.c))
    {
      ☃ = ☃.a();
      a(☃, ☃, this.b);
      for (int ☃ = ☃.q(); ☃ <= ☃.q() + 2; ☃++)
      {
        int ☃ = ☃ - ☃.q();
        int ☃ = 2 - ☃;
        for (int ☃ = ☃.p() - ☃; ☃ <= ☃.p() + ☃; ☃++)
        {
          int ☃ = ☃ - ☃.p();
          for (int ☃ = ☃.r() - ☃; ☃ <= ☃.r() + ☃; ☃++)
          {
            int ☃ = ☃ - ☃.r();
            if ((Math.abs(☃) != ☃) || (Math.abs(☃) != ☃) || (☃.nextInt(2) != 0))
            {
              cj ☃ = new cj(☃, ☃, ☃);
              axe ☃ = ☃.o(☃).a();
              if ((☃ == axe.a) || (☃ == axe.j)) {
                a(☃, ☃, this.a);
              }
            }
          }
        }
      }
    }
    return true;
  }
}
