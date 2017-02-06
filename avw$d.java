import java.util.List;
import java.util.Random;

public class avw$d
  extends avw.m
{
  public avw$d() {}
  
  public avw$d(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((avw.q)☃, ☃, ☃, 1, 0, true);
  }
  
  public static d a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -7, 0, 5, 14, 10, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new d(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    arc ☃ = aju.bA.u().a(aot.a, cq.d);
    for (int ☃ = 0; ☃ <= 9; ☃++)
    {
      int ☃ = Math.max(1, 7 - ☃);
      int ☃ = Math.min(Math.max(☃ + 5, 14 - ☃), 13);
      int ☃ = ☃;
      
      a(☃, ☃, 0, 0, ☃, 4, ☃, ☃, aju.by.u(), aju.by.u(), false);
      
      a(☃, ☃, 1, ☃ + 1, ☃, 3, ☃ - 1, ☃, aju.a.u(), aju.a.u(), false);
      if (☃ <= 6)
      {
        a(☃, ☃, 1, ☃ + 1, ☃, ☃);
        a(☃, ☃, 2, ☃ + 1, ☃, ☃);
        a(☃, ☃, 3, ☃ + 1, ☃, ☃);
      }
      a(☃, ☃, 0, ☃, ☃, 4, ☃, ☃, aju.by.u(), aju.by.u(), false);
      
      a(☃, ☃, 0, ☃ + 1, ☃, 0, ☃ - 1, ☃, aju.by.u(), aju.by.u(), false);
      a(☃, ☃, 4, ☃ + 1, ☃, 4, ☃ - 1, ☃, aju.by.u(), aju.by.u(), false);
      if ((☃ & 0x1) == 0)
      {
        a(☃, ☃, 0, ☃ + 2, ☃, 0, ☃ + 3, ☃, aju.bz.u(), aju.bz.u(), false);
        a(☃, ☃, 4, ☃ + 2, ☃, 4, ☃ + 3, ☃, aju.bz.u(), aju.bz.u(), false);
      }
      for (int ☃ = 0; ☃ <= 4; ☃++) {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
