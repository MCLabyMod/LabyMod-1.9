import java.util.List;
import java.util.Random;

public class awc$a
  extends awc.p
{
  private boolean a;
  
  public awc$a() {}
  
  public awc$a(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Chest", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.p("Chest");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((awc.m)☃, ☃, ☃, 1, 1);
  }
  
  public static a a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 7, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new a(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 4, 4, 6, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 1, 1, 0);
    
    a(☃, ☃, ☃, awc.p.a.a, 1, 1, 6);
    
    a(☃, ☃, 3, 1, 2, 3, 1, 4, aju.bf.u(), aju.bf.u(), false);
    a(☃, aju.U.a(apa.a.f.a()), 3, 1, 1, ☃);
    a(☃, aju.U.a(apa.a.f.a()), 3, 1, 5, ☃);
    a(☃, aju.U.a(apa.a.f.a()), 3, 2, 2, ☃);
    a(☃, aju.U.a(apa.a.f.a()), 3, 2, 4, ☃);
    for (int ☃ = 2; ☃ <= 4; ☃++) {
      a(☃, aju.U.a(apa.a.f.a()), 2, 1, ☃, ☃);
    }
    if ((!this.a) && 
      (☃.b(new cj(a(3, 3), d(2), b(3, 3)))))
    {
      this.a = true;
      a(☃, ☃, ☃, 3, 2, 3, azt.j);
    }
    return true;
  }
}
