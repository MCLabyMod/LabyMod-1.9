import java.util.List;
import java.util.Random;

public class awc$b
  extends awc.p
{
  private int a;
  
  public awc$b() {}
  
  public awc$b(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
    this.a = ((☃ == cq.c) || (☃ == cq.d) ? ☃.e() : ☃.c());
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Steps", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.h("Steps");
  }
  
  public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    int ☃ = 3;
    
    avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 4, ☃);
    
    awg ☃ = awg.a(☃, ☃);
    if (☃ == null) {
      return null;
    }
    if (☃.c().b == ☃.b) {
      for (int ☃ = 3; ☃ >= 1; ☃--)
      {
        ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, ☃ - 1, ☃);
        if (!☃.c().a(☃)) {
          return avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, ☃, ☃);
        }
      }
    }
    return null;
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    for (int ☃ = 0; ☃ < this.a; ☃++)
    {
      a(☃, aju.bf.u(), 0, 0, ☃, ☃);
      a(☃, aju.bf.u(), 1, 0, ☃, ☃);
      a(☃, aju.bf.u(), 2, 0, ☃, ☃);
      a(☃, aju.bf.u(), 3, 0, ☃, ☃);
      a(☃, aju.bf.u(), 4, 0, ☃, ☃);
      for (int ☃ = 1; ☃ <= 3; ☃++)
      {
        a(☃, aju.bf.u(), 0, ☃, ☃, ☃);
        a(☃, aju.a.u(), 1, ☃, ☃, ☃);
        a(☃, aju.a.u(), 2, ☃, ☃, ☃);
        a(☃, aju.a.u(), 3, ☃, ☃, ☃);
        a(☃, aju.bf.u(), 4, ☃, ☃, ☃);
      }
      a(☃, aju.bf.u(), 0, 4, ☃, ☃);
      a(☃, aju.bf.u(), 1, 4, ☃, ☃);
      a(☃, aju.bf.u(), 2, 4, ☃, ☃);
      a(☃, aju.bf.u(), 3, 4, ☃, ☃);
      a(☃, aju.bf.u(), 4, 4, ☃, ☃);
    }
    return true;
  }
}
