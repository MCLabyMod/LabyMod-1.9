import java.util.List;
import java.util.Random;

public class awc$o
  extends awc.p
{
  public awc$o() {}
  
  public awc$o(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((awc.m)☃, ☃, ☃, 1, 1);
  }
  
  public static o a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -7, 0, 5, 11, 8, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new o(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 4, 10, 7, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 1, 7, 0);
    
    a(☃, ☃, ☃, awc.p.a.a, 1, 1, 7);
    
    arc ☃ = aju.aw.u().a(aot.a, cq.d);
    for (int ☃ = 0; ☃ < 6; ☃++)
    {
      a(☃, ☃, 1, 6 - ☃, 1 + ☃, ☃);
      a(☃, ☃, 2, 6 - ☃, 1 + ☃, ☃);
      a(☃, ☃, 3, 6 - ☃, 1 + ☃, ☃);
      if (☃ < 5)
      {
        a(☃, aju.bf.u(), 1, 5 - ☃, 1 + ☃, ☃);
        a(☃, aju.bf.u(), 2, 5 - ☃, 1 + ☃, ☃);
        a(☃, aju.bf.u(), 3, 5 - ☃, 1 + ☃, ☃);
      }
    }
    return true;
  }
}
