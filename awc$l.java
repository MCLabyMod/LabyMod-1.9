import java.util.List;
import java.util.Random;

public class awc$l
  extends awc.p
{
  private boolean a;
  
  public awc$l() {}
  
  public awc$l(int ☃, Random ☃, int ☃, int ☃)
  {
    super(☃);
    
    this.a = true;
    a(cq.c.a.a(☃));
    this.d = awc.p.a.a;
    if (e().k() == cq.a.c) {
      this.l = new avp(☃, 64, ☃, ☃ + 5 - 1, 74, ☃ + 5 - 1);
    } else {
      this.l = new avp(☃, 64, ☃, ☃ + 5 - 1, 74, ☃ + 5 - 1);
    }
  }
  
  public awc$l(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    this.a = false;
    a(☃);
    this.d = a(☃);
    this.l = ☃;
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Source", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.p("Source");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    if (this.a) {
      awc.a(awc.c.class);
    }
    a((awc.m)☃, ☃, ☃, 1, 1);
  }
  
  public static l a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -7, 0, 5, 11, 5, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new l(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 4, 10, 4, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 1, 7, 0);
    
    a(☃, ☃, ☃, awc.p.a.a, 1, 1, 4);
    
    a(☃, aju.bf.u(), 2, 6, 1, ☃);
    a(☃, aju.bf.u(), 1, 5, 1, ☃);
    a(☃, aju.U.a(apa.a.a.a()), 1, 6, 1, ☃);
    a(☃, aju.bf.u(), 1, 5, 2, ☃);
    a(☃, aju.bf.u(), 1, 4, 3, ☃);
    a(☃, aju.U.a(apa.a.a.a()), 1, 5, 3, ☃);
    a(☃, aju.bf.u(), 2, 4, 3, ☃);
    a(☃, aju.bf.u(), 3, 3, 3, ☃);
    a(☃, aju.U.a(apa.a.a.a()), 3, 4, 3, ☃);
    a(☃, aju.bf.u(), 3, 3, 2, ☃);
    a(☃, aju.bf.u(), 3, 2, 1, ☃);
    a(☃, aju.U.a(apa.a.a.a()), 3, 3, 1, ☃);
    a(☃, aju.bf.u(), 2, 2, 1, ☃);
    a(☃, aju.bf.u(), 1, 1, 1, ☃);
    a(☃, aju.U.a(apa.a.a.a()), 1, 2, 1, ☃);
    a(☃, aju.bf.u(), 1, 1, 2, ☃);
    a(☃, aju.U.a(apa.a.a.a()), 1, 1, 3, ☃);
    
    return true;
  }
}
