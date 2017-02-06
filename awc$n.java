import java.util.List;
import java.util.Random;

public class awc$n
  extends awc.p
{
  private boolean a;
  private boolean b;
  
  public awc$n() {}
  
  public awc$n(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
    
    this.a = (☃.nextInt(2) == 0);
    this.b = (☃.nextInt(2) == 0);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Left", this.a);
    ☃.a("Right", this.b);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.p("Left");
    this.b = ☃.p("Right");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((awc.m)☃, ☃, ☃, 1, 1);
    if (this.a) {
      b((awc.m)☃, ☃, ☃, 1, 2);
    }
    if (this.b) {
      c((awc.m)☃, ☃, ☃, 1, 2);
    }
  }
  
  public static n a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 7, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new n(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 4, 4, 6, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 1, 1, 0);
    
    a(☃, ☃, ☃, awc.p.a.a, 1, 1, 6);
    
    arc ☃ = aju.aa.u().a(apf.a, cq.f);
    arc ☃ = aju.aa.u().a(apf.a, cq.e);
    
    a(☃, ☃, ☃, 0.1F, 1, 2, 1, ☃);
    a(☃, ☃, ☃, 0.1F, 3, 2, 1, ☃);
    a(☃, ☃, ☃, 0.1F, 1, 2, 5, ☃);
    a(☃, ☃, ☃, 0.1F, 3, 2, 5, ☃);
    if (this.a) {
      a(☃, ☃, 0, 1, 2, 0, 3, 4, aju.a.u(), aju.a.u(), false);
    }
    if (this.b) {
      a(☃, ☃, 4, 1, 2, 4, 3, 4, aju.a.u(), aju.a.u(), false);
    }
    return true;
  }
}
