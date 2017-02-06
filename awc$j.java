import java.util.List;
import java.util.Random;

public class awc$j
  extends awc.p
{
  protected int a;
  
  public awc$j() {}
  
  public awc$j(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
    this.a = ☃.nextInt(5);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Type", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.h("Type");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    a((awc.m)☃, ☃, ☃, 4, 1);
    b((awc.m)☃, ☃, ☃, 1, 4);
    c((awc.m)☃, ☃, ☃, 1, 4);
  }
  
  public static j a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 11, 7, 11, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new j(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    a(☃, ☃, 0, 0, 0, 10, 6, 10, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 4, 1, 0);
    
    a(☃, ☃, 4, 1, 10, 6, 3, 10, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 0, 1, 4, 0, 3, 6, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 10, 1, 4, 10, 3, 6, aju.a.u(), aju.a.u(), false);
    switch (this.a)
    {
    default: 
      break;
    case 0: 
      a(☃, aju.bf.u(), 5, 1, 5, ☃);
      a(☃, aju.bf.u(), 5, 2, 5, ☃);
      a(☃, aju.bf.u(), 5, 3, 5, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.e), 4, 3, 5, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.f), 6, 3, 5, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.d), 5, 3, 4, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.c), 5, 3, 6, ☃);
      a(☃, aju.U.u(), 4, 1, 4, ☃);
      a(☃, aju.U.u(), 4, 1, 5, ☃);
      a(☃, aju.U.u(), 4, 1, 6, ☃);
      a(☃, aju.U.u(), 6, 1, 4, ☃);
      a(☃, aju.U.u(), 6, 1, 5, ☃);
      a(☃, aju.U.u(), 6, 1, 6, ☃);
      a(☃, aju.U.u(), 5, 1, 4, ☃);
      a(☃, aju.U.u(), 5, 1, 6, ☃);
      break;
    case 1: 
      for (int ☃ = 0; ☃ < 5; ☃++)
      {
        a(☃, aju.bf.u(), 3, 1, 3 + ☃, ☃);
        a(☃, aju.bf.u(), 7, 1, 3 + ☃, ☃);
        a(☃, aju.bf.u(), 3 + ☃, 1, 3, ☃);
        a(☃, aju.bf.u(), 3 + ☃, 1, 7, ☃);
      }
      a(☃, aju.bf.u(), 5, 1, 5, ☃);
      a(☃, aju.bf.u(), 5, 2, 5, ☃);
      a(☃, aju.bf.u(), 5, 3, 5, ☃);
      a(☃, aju.i.u(), 5, 4, 5, ☃);
      break;
    case 2: 
      for (int ☃ = 1; ☃ <= 9; ☃++)
      {
        a(☃, aju.e.u(), 1, 3, ☃, ☃);
        a(☃, aju.e.u(), 9, 3, ☃, ☃);
      }
      for (int ☃ = 1; ☃ <= 9; ☃++)
      {
        a(☃, aju.e.u(), ☃, 3, 1, ☃);
        a(☃, aju.e.u(), ☃, 3, 9, ☃);
      }
      a(☃, aju.e.u(), 5, 1, 4, ☃);
      a(☃, aju.e.u(), 5, 1, 6, ☃);
      a(☃, aju.e.u(), 5, 3, 4, ☃);
      a(☃, aju.e.u(), 5, 3, 6, ☃);
      a(☃, aju.e.u(), 4, 1, 5, ☃);
      a(☃, aju.e.u(), 6, 1, 5, ☃);
      a(☃, aju.e.u(), 4, 3, 5, ☃);
      a(☃, aju.e.u(), 6, 3, 5, ☃);
      for (int ☃ = 1; ☃ <= 3; ☃++)
      {
        a(☃, aju.e.u(), 4, ☃, 4, ☃);
        a(☃, aju.e.u(), 6, ☃, 4, ☃);
        a(☃, aju.e.u(), 4, ☃, 6, ☃);
        a(☃, aju.e.u(), 6, ☃, 6, ☃);
      }
      a(☃, aju.aa.u(), 5, 3, 5, ☃);
      for (int ☃ = 2; ☃ <= 8; ☃++)
      {
        a(☃, aju.f.u(), 2, 3, ☃, ☃);
        a(☃, aju.f.u(), 3, 3, ☃, ☃);
        if ((☃ <= 3) || (☃ >= 7))
        {
          a(☃, aju.f.u(), 4, 3, ☃, ☃);
          a(☃, aju.f.u(), 5, 3, ☃, ☃);
          a(☃, aju.f.u(), 6, 3, ☃, ☃);
        }
        a(☃, aju.f.u(), 7, 3, ☃, ☃);
        a(☃, aju.f.u(), 8, 3, ☃, ☃);
      }
      arc ☃ = aju.au.u().a(amk.a, cq.e);
      a(☃, ☃, 9, 1, 3, ☃);
      a(☃, ☃, 9, 2, 3, ☃);
      a(☃, ☃, 9, 3, 3, ☃);
      
      a(☃, ☃, ☃, 3, 4, 8, azt.i);
    }
    return true;
  }
}
