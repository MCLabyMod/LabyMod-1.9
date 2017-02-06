import java.util.Random;

public class avy$t
  extends avy.r
{
  public avy$t() {}
  
  public avy$t(cq ☃, avy.v ☃, Random ☃)
  {
    super(1, ☃, ☃, 1, 1, 1);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (this.k.a / 25 > 0) {
      a(☃, ☃, 0, 0, this.k.c[cq.a.a()]);
    }
    if (this.k.b[cq.b.a()] == null) {
      a(☃, ☃, 1, 4, 1, 6, 4, 6, a);
    }
    for (int ☃ = 1; ☃ <= 6; ☃++) {
      for (int ☃ = 1; ☃ <= 6; ☃++) {
        if (☃.nextInt(3) != 0)
        {
          int ☃ = 2 + (☃.nextInt(4) == 0 ? 0 : 1);
          a(☃, ☃, ☃, ☃, ☃, ☃, 3, ☃, aju.v.a(1), aju.v.a(1), false);
        }
      }
    }
    a(☃, ☃, 0, 1, 0, 0, 1, 7, b, b, false);
    a(☃, ☃, 7, 1, 0, 7, 1, 7, b, b, false);
    a(☃, ☃, 1, 1, 0, 6, 1, 0, b, b, false);
    a(☃, ☃, 1, 1, 7, 6, 1, 7, b, b, false);
    
    a(☃, ☃, 0, 2, 0, 0, 2, 7, c, c, false);
    a(☃, ☃, 7, 2, 0, 7, 2, 7, c, c, false);
    a(☃, ☃, 1, 2, 0, 6, 2, 0, c, c, false);
    a(☃, ☃, 1, 2, 7, 6, 2, 7, c, c, false);
    
    a(☃, ☃, 0, 3, 0, 0, 3, 7, b, b, false);
    a(☃, ☃, 7, 3, 0, 7, 3, 7, b, b, false);
    a(☃, ☃, 1, 3, 0, 6, 3, 0, b, b, false);
    a(☃, ☃, 1, 3, 7, 6, 3, 7, b, b, false);
    
    a(☃, ☃, 0, 1, 3, 0, 2, 4, c, c, false);
    a(☃, ☃, 7, 1, 3, 7, 2, 4, c, c, false);
    a(☃, ☃, 3, 1, 0, 4, 2, 0, c, c, false);
    a(☃, ☃, 3, 1, 7, 4, 2, 7, c, c, false);
    if (this.k.c[cq.d.a()] != 0) {
      a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
    }
    return true;
  }
}
