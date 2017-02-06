import java.util.Random;

public class aue
  extends aud
{
  private alm a;
  private arc b;
  
  public aue(alm ☃, alm.a ☃)
  {
    a(☃, ☃);
  }
  
  public void a(alm ☃, alm.a ☃)
  {
    this.a = ☃;
    this.b = ☃.u().a(☃.g(), ☃);
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    for (int ☃ = 0; ☃ < 64; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(8) - ☃.nextInt(8), ☃.nextInt(4) - ☃.nextInt(4), ☃.nextInt(8) - ☃.nextInt(8));
      if ((☃.d(☃)) && ((!☃.s.m()) || (☃.q() < 255)) && 
        (this.a.f(☃, ☃, this.b))) {
        ☃.a(☃, this.b, 2);
      }
    }
    return true;
  }
}
