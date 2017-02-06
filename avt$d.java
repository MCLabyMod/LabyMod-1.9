import java.util.List;
import java.util.Random;

public class avt$d
  extends awg
{
  public avt$d() {}
  
  public avt$d(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    a(☃);
    this.l = ☃;
  }
  
  protected void a(dn ☃) {}
  
  protected void b(dn ☃) {}
  
  public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    avp ☃ = new avp(☃, ☃ - 5, ☃, ☃, ☃ + 2, ☃);
    switch (avt.1.a[☃.ordinal()])
    {
    case 1: 
      ☃.d = (☃ + 2);
      ☃.c = (☃ - 8);
      break;
    case 2: 
      ☃.d = (☃ + 2);
      ☃.f = (☃ + 8);
      break;
    case 3: 
      ☃.a = (☃ - 8);
      ☃.f = (☃ + 2);
      break;
    case 4: 
      ☃.d = (☃ + 8);
      ☃.f = (☃ + 2);
    }
    if (awg.a(☃, ☃) != null) {
      return null;
    }
    return ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    int ☃ = d();
    
    cq ☃ = e();
    if (☃ != null) {
      switch (avt.1.a[☃.ordinal()])
      {
      case 1: 
        avt.a(☃, ☃, ☃, this.l.a, this.l.b, this.l.c - 1, cq.c, ☃);
        break;
      case 2: 
        avt.a(☃, ☃, ☃, this.l.a, this.l.b, this.l.f + 1, cq.d, ☃);
        break;
      case 3: 
        avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c, cq.e, ☃);
        break;
      case 4: 
        avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c, cq.f, ☃);
      }
    }
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 5, 0, 2, 7, 1, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 0, 0, 7, 2, 2, 8, aju.a.u(), aju.a.u(), false);
    for (int ☃ = 0; ☃ < 5; ☃++) {
      a(☃, ☃, 0, 5 - ☃ - (☃ < 4 ? 1 : 0), 2 + ☃, 2, 7 - ☃, 2 + ☃, aju.a.u(), aju.a.u(), false);
    }
    return true;
  }
}
