import java.util.List;
import java.util.Random;

public class awk$b
  extends awk.n
{
  private ajt a;
  private ajt b;
  private ajt c;
  private ajt d;
  
  public awk$b() {}
  
  public awk$b(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃, ☃);
    
    a(☃);
    this.l = ☃;
    
    this.a = a(☃);
    this.b = a(☃);
    this.c = a(☃);
    this.d = a(☃);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("CA", ajt.h.a(this.a));
    ☃.a("CB", ajt.h.a(this.b));
    ☃.a("CC", ajt.h.a(this.c));
    ☃.a("CD", ajt.h.a(this.d));
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ajt.b(☃.h("CA"));
    this.b = ajt.b(☃.h("CB"));
    this.c = ajt.b(☃.h("CC"));
    this.d = ajt.b(☃.h("CD"));
    if (!(this.a instanceof akn)) {
      this.a = aju.aj;
    }
    if (!(this.b instanceof akn)) {
      this.b = aju.cb;
    }
    if (!(this.c instanceof akn)) {
      this.c = aju.cc;
    }
    if (!(this.d instanceof akn)) {
      this.d = aju.cZ;
    }
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
  
  public static b a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 13, 4, 9, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new b(☃, ☃, ☃, ☃, ☃);
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
    a(☃, ☃, 0, 1, 0, 12, 4, 8, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 1, 0, 1, 2, 0, 7, aju.ak.u(), aju.ak.u(), false);
    a(☃, ☃, 4, 0, 1, 5, 0, 7, aju.ak.u(), aju.ak.u(), false);
    a(☃, ☃, 7, 0, 1, 8, 0, 7, aju.ak.u(), aju.ak.u(), false);
    a(☃, ☃, 10, 0, 1, 11, 0, 7, aju.ak.u(), aju.ak.u(), false);
    
    a(☃, ☃, 0, 0, 0, 0, 0, 8, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 6, 0, 0, 6, 0, 8, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 12, 0, 0, 12, 0, 8, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 1, 0, 0, 11, 0, 0, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 1, 0, 8, 11, 0, 8, aju.r.u(), aju.r.u(), false);
    
    a(☃, ☃, 3, 0, 1, 3, 0, 7, aju.j.u(), aju.j.u(), false);
    a(☃, ☃, 9, 0, 1, 9, 0, 7, aju.j.u(), aju.j.u(), false);
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
      int ☃ = ((akn)this.c).g();
      int ☃ = ☃ / 3;
      a(☃, this.c.a(on.a(☃, ☃, ☃)), 7, 1, ☃, ☃);
      a(☃, this.c.a(on.a(☃, ☃, ☃)), 8, 1, ☃, ☃);
      int ☃ = ((akn)this.d).g();
      int ☃ = ☃ / 3;
      a(☃, this.d.a(on.a(☃, ☃, ☃)), 10, 1, ☃, ☃);
      a(☃, this.d.a(on.a(☃, ☃, ☃)), 11, 1, ☃, ☃);
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      for (int ☃ = 0; ☃ < 13; ☃++)
      {
        b(☃, ☃, 4, ☃, ☃);
        b(☃, aju.d.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
