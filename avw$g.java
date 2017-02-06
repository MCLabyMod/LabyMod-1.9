import java.util.List;
import java.util.Random;

public class avw$g
  extends avw.m
{
  public avw$g() {}
  
  public avw$g(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((avw.q)☃, ☃, ☃, 1, 0, true);
    b((avw.q)☃, ☃, ☃, 0, 1, true);
    c((avw.q)☃, ☃, ☃, 0, 1, true);
  }
  
  public static g a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, 0, 0, 5, 7, 5, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new g(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 0, 0, 0, 4, 1, 4, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 0, 2, 0, 4, 5, 4, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 0, 2, 0, 0, 5, 0, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 4, 2, 0, 4, 5, 0, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 0, 2, 4, 0, 5, 4, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 4, 2, 4, 4, 5, 4, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 0, 6, 0, 4, 6, 4, aju.by.u(), aju.by.u(), false);
    for (int ☃ = 0; ☃ <= 4; ☃++) {
      for (int ☃ = 0; ☃ <= 4; ☃++) {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
