import java.util.List;
import java.util.Random;

public class avw$l
  extends avw.m
{
  private boolean a;
  
  public avw$l() {}
  
  public avw$l(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    
    this.a = ☃.p("Mob");
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    
    ☃.a("Mob", this.a);
  }
  
  public static l a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -2, 0, 0, 7, 8, 9, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new l(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 0, 2, 0, 6, 7, 7, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 1, 0, 0, 5, 1, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 2, 1, 5, 2, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 3, 2, 5, 3, 7, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 4, 3, 5, 4, 7, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 1, 2, 0, 1, 4, 2, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 5, 2, 0, 5, 4, 2, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 5, 2, 1, 5, 3, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 5, 5, 2, 5, 5, 3, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 0, 5, 3, 0, 5, 8, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 6, 5, 3, 6, 5, 8, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 5, 8, 5, 5, 8, aju.by.u(), aju.by.u(), false);
    
    a(☃, aju.bz.u(), 1, 6, 3, ☃);
    a(☃, aju.bz.u(), 5, 6, 3, ☃);
    a(☃, ☃, 0, 6, 3, 0, 6, 8, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 6, 6, 3, 6, 6, 8, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 1, 6, 8, 5, 7, 8, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 2, 8, 8, 4, 8, 8, aju.bz.u(), aju.bz.u(), false);
    if (!this.a)
    {
      cj ☃ = new cj(a(3, 5), d(5), b(3, 5));
      if (☃.b(☃))
      {
        this.a = true;
        ☃.a(☃, aju.ac.u(), 2);
        
        apv ☃ = ☃.r(☃);
        if ((☃ instanceof aqk)) {
          ((aqk)☃).b().a("Blaze");
        }
      }
    }
    for (int ☃ = 0; ☃ <= 6; ☃++) {
      for (int ☃ = 0; ☃ <= 6; ☃++) {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
