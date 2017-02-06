import java.util.List;
import java.util.Random;

public class awc$d
  extends awc.p
{
  public awc$d() {}
  
  public awc$d(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    cq ☃ = e();
    if ((☃ == cq.c) || (☃ == cq.f)) {
      b((awc.m)☃, ☃, ☃, 1, 1);
    } else {
      c((awc.m)☃, ☃, ☃, 1, 1);
    }
  }
  
  public static d a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 5, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new d(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 4, 4, 4, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 1, 1, 0);
    
    cq ☃ = e();
    if ((☃ == cq.c) || (☃ == cq.f)) {
      a(☃, ☃, 0, 1, 1, 0, 3, 3, aju.a.u(), aju.a.u(), false);
    } else {
      a(☃, ☃, 4, 1, 1, 4, 3, 3, aju.a.u(), aju.a.u(), false);
    }
    return true;
  }
}
