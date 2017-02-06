import java.util.List;
import java.util.Random;

public class awk$c
  extends awk.n
{
  private ajt a;
  private ajt b;
  
  public awk$c() {}
  
  public awk$c(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃, ☃);
    
    a(☃);
    this.l = ☃;
    
    this.a = a(☃);
    this.b = a(☃);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("CA", ajt.h.a(this.a));
    ☃.a("CB", ajt.h.a(this.b));
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ajt.b(☃.h("CA"));
    this.b = ajt.b(☃.h("CB"));
  }
  
  private ajt a(Random ☃)
  {
    switch (☃.nextInt(10))
    {
    default: 
      return aju.aj;
    case 0: 
    case 1: 
      return aju.cb;
    case 2: 
    case 3: 
      return aju.cc;
    }
    return aju.cZ;
  }
  
  public static c a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 7, 4, 9, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new c(☃, ☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (this.h < 0)
    {
      this.h = b(☃, ☃);
      if (this.h < 0) {
        return true;
      }
      this.l.a(0, this.h - this.l.e + 4 - 1, 0);
    }
    a(☃, ☃, 0, 1, 0, 6, 4, 8, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 1, 0, 1, 2, 0, 7, aju.ak.u(), aju.ak.u(), false);
    a(☃, ☃, 4, 0, 1, 5, 0, 7, aju.ak.u(), aju.ak.u(), false);
    
    a(☃, ☃, 0, 0, 0, 0, 0, 8, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 6, 0, 0, 6, 0, 8, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 1, 0, 0, 5, 0, 0, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 1, 0, 8, 5, 0, 8, aju.r.u(), aju.r.u(), false);
    
    a(☃, ☃, 3, 0, 1, 3, 0, 7, aju.j.u(), aju.j.u(), false);
    for (int ☃ = 1; ☃ <= 7; ☃++)
    {
      int ☃ = ((akn)this.a).g();
      int ☃ = ☃ / 3;
      a(☃, this.a.a(on.a(☃, ☃, ☃)), 1, 1, ☃, ☃);
      a(☃, this.a.a(on.a(☃, ☃, ☃)), 2, 1, ☃, ☃);
      int ☃ = ((akn)this.b).g();
      int ☃ = ☃ / 3;
      a(☃, this.b.a(on.a(☃, ☃, ☃)), 4, 1, ☃, ☃);
      a(☃, this.b.a(on.a(☃, ☃, ☃)), 5, 1, ☃, ☃);
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      for (int ☃ = 0; ☃ < 7; ☃++)
      {
        b(☃, ☃, 4, ☃, ☃);
        b(☃, aju.d.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
