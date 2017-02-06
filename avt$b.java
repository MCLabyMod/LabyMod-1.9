import java.util.List;
import java.util.Random;

public class avt$b
  extends awg
{
  private cq a;
  private boolean b;
  
  public avt$b() {}
  
  protected void a(dn ☃)
  {
    ☃.a("tf", this.b);
    ☃.a("D", this.a.b());
  }
  
  protected void b(dn ☃)
  {
    this.b = ☃.p("tf");
    this.a = cq.b(☃.h("D"));
  }
  
  public avt$b(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    this.a = ☃;
    this.l = ☃;
    this.b = (☃.d() > 3);
  }
  
  public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    avp ☃ = new avp(☃, ☃, ☃, ☃, ☃ + 2, ☃);
    if (☃.nextInt(4) == 0) {
      ☃.e += 4;
    }
    switch (avt.1.a[☃.ordinal()])
    {
    case 1: 
      ☃.a = (☃ - 1);
      ☃.d = (☃ + 3);
      ☃.c = (☃ - 4);
      break;
    case 2: 
      ☃.a = (☃ - 1);
      ☃.d = (☃ + 3);
      ☃.f = (☃ + 3 + 1);
      break;
    case 3: 
      ☃.a = (☃ - 4);
      ☃.c = (☃ - 1);
      ☃.f = (☃ + 3);
      break;
    case 4: 
      ☃.d = (☃ + 3 + 1);
      ☃.c = (☃ - 1);
      ☃.f = (☃ + 3);
    }
    if (awg.a(☃, ☃) != null) {
      return null;
    }
    return ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    int ☃ = d();
    switch (avt.1.a[this.a.ordinal()])
    {
    case 1: 
      avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, ☃);
      avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, ☃);
      avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, ☃);
      break;
    case 2: 
      avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, ☃);
      avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, ☃);
      avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, ☃);
      break;
    case 3: 
      avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, ☃);
      avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, ☃);
      avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, ☃);
      break;
    case 4: 
      avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, ☃);
      avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, ☃);
      avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, ☃);
    }
    if (this.b)
    {
      if (☃.nextBoolean()) {
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b + 3 + 1, this.l.c - 1, cq.c, ☃);
      }
      if (☃.nextBoolean()) {
        avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b + 3 + 1, this.l.c + 1, cq.e, ☃);
      }
      if (☃.nextBoolean()) {
        avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b + 3 + 1, this.l.c + 1, cq.f, ☃);
      }
      if (☃.nextBoolean()) {
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b + 3 + 1, this.l.f + 1, cq.d, ☃);
      }
    }
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    if (this.b)
    {
      a(☃, ☃, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.b + 3 - 1, this.l.f, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.b + 3 - 1, this.l.f - 1, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.l.a + 1, this.l.e - 2, this.l.c, this.l.d - 1, this.l.e, this.l.f, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.l.a, this.l.e - 2, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.l.a + 1, this.l.b + 3, this.l.c + 1, this.l.d - 1, this.l.b + 3, this.l.f - 1, aju.a.u(), aju.a.u(), false);
    }
    else
    {
      a(☃, ☃, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.e, this.l.f, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, aju.a.u(), aju.a.u(), false);
    }
    a(☃, ☃, this.l.a + 1, this.l.b, this.l.c + 1, this.l.a + 1, this.l.e, this.l.c + 1, aju.f.u(), aju.a.u(), false);
    a(☃, ☃, this.l.a + 1, this.l.b, this.l.f - 1, this.l.a + 1, this.l.e, this.l.f - 1, aju.f.u(), aju.a.u(), false);
    a(☃, ☃, this.l.d - 1, this.l.b, this.l.c + 1, this.l.d - 1, this.l.e, this.l.c + 1, aju.f.u(), aju.a.u(), false);
    a(☃, ☃, this.l.d - 1, this.l.b, this.l.f - 1, this.l.d - 1, this.l.e, this.l.f - 1, aju.f.u(), aju.a.u(), false);
    for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++) {
      for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++) {
        if (a(☃, ☃, this.l.b - 1, ☃, ☃).a() == axe.a) {
          a(☃, aju.f.u(), ☃, this.l.b - 1, ☃, ☃);
        }
      }
    }
    return true;
  }
}
