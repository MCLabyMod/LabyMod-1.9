import java.util.List;
import java.util.Random;

public class awc$c
  extends awc.p
{
  private boolean a;
  private boolean b;
  private boolean c;
  private boolean e;
  
  public awc$c() {}
  
  public awc$c(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
    
    this.a = ☃.nextBoolean();
    this.b = ☃.nextBoolean();
    this.c = ☃.nextBoolean();
    this.e = (☃.nextInt(3) > 0);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("leftLow", this.a);
    ☃.a("leftHigh", this.b);
    ☃.a("rightLow", this.c);
    ☃.a("rightHigh", this.e);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.p("leftLow");
    this.b = ☃.p("leftHigh");
    this.c = ☃.p("rightLow");
    this.e = ☃.p("rightHigh");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    int ☃ = 3;
    int ☃ = 5;
    
    cq ☃ = e();
    if ((☃ == cq.e) || (☃ == cq.c))
    {
      ☃ = 8 - ☃;
      ☃ = 8 - ☃;
    }
    a((awc.m)☃, ☃, ☃, 5, 1);
    if (this.a) {
      b((awc.m)☃, ☃, ☃, ☃, 1);
    }
    if (this.b) {
      b((awc.m)☃, ☃, ☃, ☃, 7);
    }
    if (this.c) {
      c((awc.m)☃, ☃, ☃, ☃, 1);
    }
    if (this.e) {
      c((awc.m)☃, ☃, ☃, ☃, 7);
    }
  }
  
  public static c a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -4, -3, 0, 10, 9, 11, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new c(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 9, 8, 10, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 4, 3, 0);
    if (this.a) {
      a(☃, ☃, 0, 3, 1, 0, 5, 3, aju.a.u(), aju.a.u(), false);
    }
    if (this.c) {
      a(☃, ☃, 9, 3, 1, 9, 5, 3, aju.a.u(), aju.a.u(), false);
    }
    if (this.b) {
      a(☃, ☃, 0, 5, 7, 0, 7, 9, aju.a.u(), aju.a.u(), false);
    }
    if (this.e) {
      a(☃, ☃, 9, 5, 7, 9, 7, 9, aju.a.u(), aju.a.u(), false);
    }
    a(☃, ☃, 5, 1, 10, 7, 3, 10, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 1, 2, 1, 8, 2, 6, false, ☃, awc.c());
    
    a(☃, ☃, 4, 1, 5, 4, 4, 9, false, ☃, awc.c());
    a(☃, ☃, 8, 1, 5, 8, 4, 9, false, ☃, awc.c());
    
    a(☃, ☃, 1, 4, 7, 3, 4, 9, false, ☃, awc.c());
    
    a(☃, ☃, 1, 3, 5, 3, 3, 6, false, ☃, awc.c());
    a(☃, ☃, 1, 3, 4, 3, 3, 4, aju.U.u(), aju.U.u(), false);
    a(☃, ☃, 1, 4, 6, 3, 4, 6, aju.U.u(), aju.U.u(), false);
    
    a(☃, ☃, 5, 1, 7, 7, 1, 8, false, ☃, awc.c());
    a(☃, ☃, 5, 1, 9, 7, 1, 9, aju.U.u(), aju.U.u(), false);
    a(☃, ☃, 5, 2, 7, 7, 2, 7, aju.U.u(), aju.U.u(), false);
    
    a(☃, ☃, 4, 5, 7, 4, 5, 9, aju.U.u(), aju.U.u(), false);
    a(☃, ☃, 8, 5, 7, 8, 5, 9, aju.U.u(), aju.U.u(), false);
    a(☃, ☃, 5, 5, 7, 7, 5, 9, aju.T.u(), aju.T.u(), false);
    a(☃, aju.aa.u().a(apf.a, cq.d), 6, 5, 6, ☃);
    
    return true;
  }
}
