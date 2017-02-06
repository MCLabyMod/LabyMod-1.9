import java.util.List;
import java.util.Random;

public class awc$e
  extends awc.p
{
  private boolean a;
  
  public awc$e() {}
  
  public awc$e(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.d = a(☃);
    this.l = ☃;
    this.a = (☃.d() > 6);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Tall", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.p("Tall");
  }
  
  public static e a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 14, 11, 15, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null))
    {
      ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 14, 6, 15, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
    }
    return new e(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    int ☃ = 11;
    if (!this.a) {
      ☃ = 6;
    }
    a(☃, ☃, 0, 0, 0, 13, ☃ - 1, 14, true, ☃, awc.c());
    
    a(☃, ☃, ☃, this.d, 4, 1, 0);
    
    a(☃, ☃, ☃, 0.07F, 2, 1, 1, 11, 4, 13, aju.G.u(), aju.G.u(), false);
    
    int ☃ = 1;
    int ☃ = 12;
    for (int ☃ = 1; ☃ <= 13; ☃++) {
      if ((☃ - 1) % 4 == 0)
      {
        a(☃, ☃, 1, 1, ☃, 1, 4, ☃, aju.f.u(), aju.f.u(), false);
        a(☃, ☃, 12, 1, ☃, 12, 4, ☃, aju.f.u(), aju.f.u(), false);
        
        a(☃, aju.aa.u().a(apf.a, cq.f), 2, 3, ☃, ☃);
        a(☃, aju.aa.u().a(apf.a, cq.e), 11, 3, ☃, ☃);
        if (this.a)
        {
          a(☃, ☃, 1, 6, ☃, 1, 9, ☃, aju.f.u(), aju.f.u(), false);
          a(☃, ☃, 12, 6, ☃, 12, 9, ☃, aju.f.u(), aju.f.u(), false);
        }
      }
      else
      {
        a(☃, ☃, 1, 1, ☃, 1, 4, ☃, aju.X.u(), aju.X.u(), false);
        a(☃, ☃, 12, 1, ☃, 12, 4, ☃, aju.X.u(), aju.X.u(), false);
        if (this.a)
        {
          a(☃, ☃, 1, 6, ☃, 1, 9, ☃, aju.X.u(), aju.X.u(), false);
          a(☃, ☃, 12, 6, ☃, 12, 9, ☃, aju.X.u(), aju.X.u(), false);
        }
      }
    }
    for (int ☃ = 3; ☃ < 12; ☃ += 2)
    {
      a(☃, ☃, 3, 1, ☃, 4, 3, ☃, aju.X.u(), aju.X.u(), false);
      a(☃, ☃, 6, 1, ☃, 7, 3, ☃, aju.X.u(), aju.X.u(), false);
      a(☃, ☃, 9, 1, ☃, 10, 3, ☃, aju.X.u(), aju.X.u(), false);
    }
    if (this.a)
    {
      a(☃, ☃, 1, 5, 1, 3, 5, 13, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 10, 5, 1, 12, 5, 13, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 4, 5, 1, 9, 5, 2, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 4, 5, 12, 9, 5, 13, aju.f.u(), aju.f.u(), false);
      
      a(☃, aju.f.u(), 9, 5, 11, ☃);
      a(☃, aju.f.u(), 8, 5, 11, ☃);
      a(☃, aju.f.u(), 9, 5, 10, ☃);
      
      a(☃, ☃, 3, 6, 2, 3, 6, 12, aju.aO.u(), aju.aO.u(), false);
      a(☃, ☃, 10, 6, 2, 10, 6, 10, aju.aO.u(), aju.aO.u(), false);
      a(☃, ☃, 4, 6, 2, 9, 6, 2, aju.aO.u(), aju.aO.u(), false);
      a(☃, ☃, 4, 6, 12, 8, 6, 12, aju.aO.u(), aju.aO.u(), false);
      a(☃, aju.aO.u(), 9, 6, 11, ☃);
      a(☃, aju.aO.u(), 8, 6, 11, ☃);
      a(☃, aju.aO.u(), 9, 6, 10, ☃);
      
      arc ☃ = aju.au.u().a(amk.a, cq.d);
      a(☃, ☃, 10, 1, 13, ☃);
      a(☃, ☃, 10, 2, 13, ☃);
      a(☃, ☃, 10, 3, 13, ☃);
      a(☃, ☃, 10, 4, 13, ☃);
      a(☃, ☃, 10, 5, 13, ☃);
      a(☃, ☃, 10, 6, 13, ☃);
      a(☃, ☃, 10, 7, 13, ☃);
      
      int ☃ = 7;
      int ☃ = 7;
      a(☃, aju.aO.u(), ☃ - 1, 9, ☃, ☃);
      a(☃, aju.aO.u(), ☃, 9, ☃, ☃);
      a(☃, aju.aO.u(), ☃ - 1, 8, ☃, ☃);
      a(☃, aju.aO.u(), ☃, 8, ☃, ☃);
      a(☃, aju.aO.u(), ☃ - 1, 7, ☃, ☃);
      a(☃, aju.aO.u(), ☃, 7, ☃, ☃);
      
      a(☃, aju.aO.u(), ☃ - 2, 7, ☃, ☃);
      a(☃, aju.aO.u(), ☃ + 1, 7, ☃, ☃);
      a(☃, aju.aO.u(), ☃ - 1, 7, ☃ - 1, ☃);
      a(☃, aju.aO.u(), ☃ - 1, 7, ☃ + 1, ☃);
      a(☃, aju.aO.u(), ☃, 7, ☃ - 1, ☃);
      a(☃, aju.aO.u(), ☃, 7, ☃ + 1, ☃);
      
      arc ☃ = aju.aa.u().a(apf.a, cq.b);
      a(☃, ☃, ☃ - 2, 8, ☃, ☃);
      a(☃, ☃, ☃ + 1, 8, ☃, ☃);
      a(☃, ☃, ☃ - 1, 8, ☃ - 1, ☃);
      a(☃, ☃, ☃ - 1, 8, ☃ + 1, ☃);
      a(☃, ☃, ☃, 8, ☃ - 1, ☃);
      a(☃, ☃, ☃, 8, ☃ + 1, ☃);
    }
    a(☃, ☃, ☃, 3, 3, 5, azt.h);
    if (this.a)
    {
      a(☃, aju.a.u(), 12, 9, 1, ☃);
      a(☃, ☃, ☃, 12, 8, 1, azt.h);
    }
    return true;
  }
}
