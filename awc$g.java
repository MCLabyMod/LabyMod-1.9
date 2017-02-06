import java.util.List;
import java.util.Random;

public class awc$g
  extends awc.p
{
  private boolean a;
  
  public awc$g() {}
  
  public awc$g(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Mob", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.p("Mob");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    if (☃ != null) {
      ((awc.m)☃).b = this;
    }
  }
  
  public static g a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 11, 8, 16, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new g(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 0, 0, 0, 10, 7, 15, false, ☃, awc.c());
    
    a(☃, ☃, ☃, awc.p.a.c, 4, 1, 0);
    
    int ☃ = 6;
    a(☃, ☃, 1, ☃, 1, 1, ☃, 14, false, ☃, awc.c());
    a(☃, ☃, 9, ☃, 1, 9, ☃, 14, false, ☃, awc.c());
    a(☃, ☃, 2, ☃, 1, 8, ☃, 2, false, ☃, awc.c());
    a(☃, ☃, 2, ☃, 14, 8, ☃, 14, false, ☃, awc.c());
    
    a(☃, ☃, 1, 1, 1, 2, 1, 4, false, ☃, awc.c());
    a(☃, ☃, 8, 1, 1, 9, 1, 4, false, ☃, awc.c());
    a(☃, ☃, 1, 1, 1, 1, 1, 3, aju.k.u(), aju.k.u(), false);
    a(☃, ☃, 9, 1, 1, 9, 1, 3, aju.k.u(), aju.k.u(), false);
    
    a(☃, ☃, 3, 1, 8, 7, 1, 12, false, ☃, awc.c());
    a(☃, ☃, 4, 1, 9, 6, 1, 11, aju.k.u(), aju.k.u(), false);
    for (int ☃ = 3; ☃ < 14; ☃ += 2)
    {
      a(☃, ☃, 0, 3, ☃, 0, 4, ☃, aju.bi.u(), aju.bi.u(), false);
      a(☃, ☃, 10, 3, ☃, 10, 4, ☃, aju.bi.u(), aju.bi.u(), false);
    }
    for (int ☃ = 2; ☃ < 9; ☃ += 2) {
      a(☃, ☃, ☃, 3, 15, ☃, 4, 15, aju.bi.u(), aju.bi.u(), false);
    }
    arc ☃ = aju.bv.u().a(aot.a, cq.c);
    a(☃, ☃, 4, 1, 5, 6, 1, 7, false, ☃, awc.c());
    a(☃, ☃, 4, 2, 6, 6, 2, 7, false, ☃, awc.c());
    a(☃, ☃, 4, 3, 7, 6, 3, 7, false, ☃, awc.c());
    for (int ☃ = 4; ☃ <= 6; ☃++)
    {
      a(☃, ☃, ☃, 1, 4, ☃);
      a(☃, ☃, ☃, 2, 5, ☃);
      a(☃, ☃, ☃, 3, 6, ☃);
    }
    arc ☃ = aju.bG.u().a(ald.a, cq.c);
    arc ☃ = aju.bG.u().a(ald.a, cq.d);
    arc ☃ = aju.bG.u().a(ald.a, cq.f);
    arc ☃ = aju.bG.u().a(ald.a, cq.e);
    
    boolean ☃ = true;
    boolean[] ☃ = new boolean[12];
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      ☃[☃] = (☃.nextFloat() > 0.9F ? 1 : false);
      ☃ &= ☃[☃];
    }
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[0])), 4, 3, 8, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[1])), 5, 3, 8, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[2])), 6, 3, 8, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[3])), 4, 3, 12, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[4])), 5, 3, 12, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[5])), 6, 3, 12, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[6])), 3, 3, 9, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[7])), 3, 3, 10, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[8])), 3, 3, 11, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[9])), 7, 3, 9, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[10])), 7, 3, 10, ☃);
    a(☃, ☃.a(ald.b, Boolean.valueOf(☃[11])), 7, 3, 11, ☃);
    if (☃)
    {
      arc ☃ = aju.bF.u();
      
      a(☃, ☃, 4, 3, 9, ☃);
      a(☃, ☃, 5, 3, 9, ☃);
      a(☃, ☃, 6, 3, 9, ☃);
      a(☃, ☃, 4, 3, 10, ☃);
      a(☃, ☃, 5, 3, 10, ☃);
      a(☃, ☃, 6, 3, 10, ☃);
      a(☃, ☃, 4, 3, 11, ☃);
      a(☃, ☃, 5, 3, 11, ☃);
      a(☃, ☃, 6, 3, 11, ☃);
    }
    if (!this.a)
    {
      ☃ = d(3);
      cj ☃ = new cj(a(5, 6), ☃, b(5, 6));
      if (☃.b(☃))
      {
        this.a = true;
        ☃.a(☃, aju.ac.u(), 2);
        
        apv ☃ = ☃.r(☃);
        if ((☃ instanceof aqk)) {
          ((aqk)☃).b().a("Silverfish");
        }
      }
    }
    return true;
  }
}
