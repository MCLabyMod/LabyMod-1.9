import java.util.Random;

public class atu
  extends aud
{
  private ajy a;
  
  public atu(ajy ☃)
  {
    this.a = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    for (int ☃ = 0; ☃ < 64; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(8) - ☃.nextInt(8), ☃.nextInt(4) - ☃.nextInt(4), ☃.nextInt(8) - ☃.nextInt(8));
      if ((☃.d(☃)) && ((!☃.s.m()) || (☃.q() < 255)) && 
        (this.a.f(☃, ☃, this.a.u()))) {
        ☃.a(☃, this.a.u(), 2);
      }
    }
    return true;
  }
}
