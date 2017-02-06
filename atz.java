import java.util.Random;

public class atz
  extends aud
{
  private akw.b a;
  
  public void a(akw.b ☃)
  {
    this.a = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < 64; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(8) - ☃.nextInt(8), ☃.nextInt(4) - ☃.nextInt(4), ☃.nextInt(8) - ☃.nextInt(8));
      if ((☃.d(☃)) && ((!☃.s.m()) || (☃.q() < 254)) && 
        (aju.cF.a(☃, ☃)))
      {
        aju.cF.a(☃, ☃, this.a, 2);
        ☃ = true;
      }
    }
    return ☃;
  }
}
