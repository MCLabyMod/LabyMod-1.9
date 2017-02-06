import java.util.Random;

public class avf
  extends aud
{
  private final arc a;
  
  public avf(apc.a ☃)
  {
    this.a = aju.H.u().a(apc.a, ☃);
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    arc ☃;
    while ((((☃ = ☃.o(☃)).a() == axe.a) || (☃.a() == axe.j)) && (☃.q() > 0)) {
      ☃ = ☃.b();
    }
    for (int ☃ = 0; ☃ < 128; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(8) - ☃.nextInt(8), ☃.nextInt(4) - ☃.nextInt(4), ☃.nextInt(8) - ☃.nextInt(8));
      if ((☃.d(☃)) && 
        (aju.H.f(☃, ☃, this.a))) {
        ☃.a(☃, this.a, 2);
      }
    }
    return true;
  }
}
