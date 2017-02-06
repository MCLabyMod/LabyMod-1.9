import java.util.List;
import java.util.Random;

public class avw$a
  extends avw.m
{
  public avw$a() {}
  
  public avw$a(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
  }
  
  protected avw$a(Random ☃, int ☃, int ☃)
  {
    super(0);
    
    a(cq.c.a.a(☃));
    if (e().k() == cq.a.c) {
      this.l = new avp(☃, 64, ☃, ☃ + 19 - 1, 73, ☃ + 19 - 1);
    } else {
      this.l = new avp(☃, 64, ☃, ☃ + 19 - 1, 73, ☃ + 19 - 1);
    }
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((avw.q)☃, ☃, ☃, 8, 3, false);
    b((avw.q)☃, ☃, ☃, 3, 8, false);
    c((avw.q)☃, ☃, ☃, 3, 8, false);
  }
  
  public static a a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -8, -3, 0, 19, 10, 19, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new a(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 7, 3, 0, 11, 4, 18, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 0, 3, 7, 18, 4, 11, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 8, 5, 0, 10, 7, 18, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 0, 5, 8, 18, 7, 10, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 7, 5, 0, 7, 5, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 7, 5, 11, 7, 5, 18, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 11, 5, 0, 11, 5, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 11, 5, 11, 11, 5, 18, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 0, 5, 7, 7, 5, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 11, 5, 7, 18, 5, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 0, 5, 11, 7, 5, 11, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 11, 5, 11, 18, 5, 11, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 7, 2, 0, 11, 2, 5, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 7, 2, 13, 11, 2, 18, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 7, 0, 0, 11, 1, 3, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 7, 0, 15, 11, 1, 18, aju.by.u(), aju.by.u(), false);
    for (int ☃ = 7; ☃ <= 11; ☃++) {
      for (int ☃ = 0; ☃ <= 2; ☃++)
      {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
        b(☃, aju.by.u(), ☃, -1, 18 - ☃, ☃);
      }
    }
    a(☃, ☃, 0, 2, 7, 5, 2, 11, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 13, 2, 7, 18, 2, 11, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 0, 0, 7, 3, 1, 11, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 15, 0, 7, 18, 1, 11, aju.by.u(), aju.by.u(), false);
    for (int ☃ = 0; ☃ <= 2; ☃++) {
      for (int ☃ = 7; ☃ <= 11; ☃++)
      {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
        b(☃, aju.by.u(), 18 - ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
