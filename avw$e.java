import java.util.List;
import java.util.Random;

public class avw$e
  extends avw.m
{
  public avw$e() {}
  
  public avw$e(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    int ☃ = 1;
    
    cq ☃ = e();
    if ((☃ == cq.e) || (☃ == cq.c)) {
      ☃ = 5;
    }
    b((avw.q)☃, ☃, ☃, 0, ☃, ☃.nextInt(8) > 0);
    c((avw.q)☃, ☃, ☃, 0, ☃, ☃.nextInt(8) > 0);
  }
  
  public static e a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -3, 0, 0, 9, 7, 9, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new e(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 0, 0, 0, 8, 1, 8, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 0, 2, 0, 8, 5, 8, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 0, 6, 0, 8, 6, 5, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 0, 2, 0, 2, 5, 0, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 6, 2, 0, 8, 5, 0, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 3, 0, 1, 4, 0, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 7, 3, 0, 7, 4, 0, aju.bz.u(), aju.bz.u(), false);
    
    a(☃, ☃, 0, 2, 4, 8, 2, 8, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 1, 4, 2, 2, 4, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 6, 1, 4, 7, 2, 4, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 0, 3, 8, 8, 3, 8, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 0, 3, 6, 0, 3, 7, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 8, 3, 6, 8, 3, 7, aju.bz.u(), aju.bz.u(), false);
    
    a(☃, ☃, 0, 3, 4, 0, 5, 5, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 8, 3, 4, 8, 5, 5, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 3, 5, 2, 5, 5, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 6, 3, 5, 7, 5, 5, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 4, 5, 1, 5, 5, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 7, 4, 5, 7, 5, 5, aju.bz.u(), aju.bz.u(), false);
    for (int ☃ = 0; ☃ <= 5; ☃++) {
      for (int ☃ = 0; ☃ <= 8; ☃++) {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
