import java.util.List;
import java.util.Random;

public class awk$p
  extends awk.n
{
  public awk$p() {}
  
  public awk$p(awk.k ☃, int ☃, Random ☃, int ☃, int ☃)
  {
    super(☃, ☃);
    
    a(cq.c.a.a(☃));
    if (e().k() == cq.a.c) {
      this.l = new avp(☃, 64, ☃, ☃ + 6 - 1, 78, ☃ + 6 - 1);
    } else {
      this.l = new avp(☃, 64, ☃, ☃ + 6 - 1, 78, ☃ + 6 - 1);
    }
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    awk.b((awk.k)☃, ☃, ☃, this.l.a - 1, this.l.e - 4, this.l.c + 1, cq.e, d());
    awk.b((awk.k)☃, ☃, ☃, this.l.d + 1, this.l.e - 4, this.l.c + 1, cq.f, d());
    awk.b((awk.k)☃, ☃, ☃, this.l.a + 1, this.l.e - 4, this.l.c - 1, cq.c, d());
    awk.b((awk.k)☃, ☃, ☃, this.l.a + 1, this.l.e - 4, this.l.f + 1, cq.d, d());
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (this.h < 0)
    {
      this.h = b(☃, ☃);
      if (this.h < 0) {
        return true;
      }
      this.l.a(0, this.h - this.l.e + 3, 0);
    }
    a(☃, ☃, 1, 0, 1, 4, 12, 4, aju.e.u(), aju.i.u(), false);
    a(☃, aju.a.u(), 2, 12, 2, ☃);
    a(☃, aju.a.u(), 3, 12, 2, ☃);
    a(☃, aju.a.u(), 2, 12, 3, ☃);
    a(☃, aju.a.u(), 3, 12, 3, ☃);
    
    a(☃, aju.aO.u(), 1, 13, 1, ☃);
    a(☃, aju.aO.u(), 1, 14, 1, ☃);
    a(☃, aju.aO.u(), 4, 13, 1, ☃);
    a(☃, aju.aO.u(), 4, 14, 1, ☃);
    a(☃, aju.aO.u(), 1, 13, 4, ☃);
    a(☃, aju.aO.u(), 1, 14, 4, ☃);
    a(☃, aju.aO.u(), 4, 13, 4, ☃);
    a(☃, aju.aO.u(), 4, 14, 4, ☃);
    a(☃, ☃, 1, 15, 1, 4, 15, 4, aju.e.u(), aju.e.u(), false);
    for (int ☃ = 0; ☃ <= 5; ☃++) {
      for (int ☃ = 0; ☃ <= 5; ☃++) {
        if ((☃ == 0) || (☃ == 5) || (☃ == 0) || (☃ == 5))
        {
          a(☃, aju.n.u(), ☃, 11, ☃, ☃);
          b(☃, ☃, 12, ☃, ☃);
        }
      }
    }
    return true;
  }
}
