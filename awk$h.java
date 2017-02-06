import java.util.List;
import java.util.Random;

public class awk$h
  extends awk.n
{
  private boolean a;
  private int b;
  
  public awk$h() {}
  
  public awk$h(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃, ☃);
    
    a(☃);
    this.l = ☃;
    this.a = ☃.nextBoolean();
    this.b = ☃.nextInt(3);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("T", this.b);
    ☃.a("C", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.b = ☃.h("T");
    this.a = ☃.p("C");
  }
  
  public static h a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 4, 6, 5, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new h(☃, ☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (this.h < 0)
    {
      this.h = b(☃, ☃);
      if (this.h < 0) {
        return true;
      }
      this.l.a(0, this.h - this.l.e + 6 - 1, 0);
    }
    a(☃, ☃, 1, 1, 1, 3, 5, 4, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 0, 0, 0, 3, 0, 4, aju.e.u(), aju.e.u(), false);
    a(☃, ☃, 1, 0, 1, 2, 0, 3, aju.d.u(), aju.d.u(), false);
    if (this.a) {
      a(☃, ☃, 1, 4, 1, 2, 4, 3, aju.r.u(), aju.r.u(), false);
    } else {
      a(☃, ☃, 1, 5, 1, 2, 5, 3, aju.r.u(), aju.r.u(), false);
    }
    a(☃, aju.r.u(), 1, 4, 0, ☃);
    a(☃, aju.r.u(), 2, 4, 0, ☃);
    a(☃, aju.r.u(), 1, 4, 4, ☃);
    a(☃, aju.r.u(), 2, 4, 4, ☃);
    a(☃, aju.r.u(), 0, 4, 1, ☃);
    a(☃, aju.r.u(), 0, 4, 2, ☃);
    a(☃, aju.r.u(), 0, 4, 3, ☃);
    a(☃, aju.r.u(), 3, 4, 1, ☃);
    a(☃, aju.r.u(), 3, 4, 2, ☃);
    a(☃, aju.r.u(), 3, 4, 3, ☃);
    
    a(☃, ☃, 0, 1, 0, 0, 3, 0, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 3, 1, 0, 3, 3, 0, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 0, 1, 4, 0, 3, 4, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 3, 1, 4, 3, 3, 4, aju.r.u(), aju.r.u(), false);
    
    a(☃, ☃, 0, 1, 1, 0, 3, 3, aju.f.u(), aju.f.u(), false);
    a(☃, ☃, 3, 1, 1, 3, 3, 3, aju.f.u(), aju.f.u(), false);
    a(☃, ☃, 1, 1, 0, 2, 3, 0, aju.f.u(), aju.f.u(), false);
    a(☃, ☃, 1, 1, 4, 2, 3, 4, aju.f.u(), aju.f.u(), false);
    
    a(☃, aju.bj.u(), 0, 2, 2, ☃);
    a(☃, aju.bj.u(), 3, 2, 2, ☃);
    if (this.b > 0)
    {
      a(☃, aju.aO.u(), this.b, 1, 3, ☃);
      a(☃, aju.aB.u(), this.b, 2, 3, ☃);
    }
    a(☃, aju.a.u(), 1, 1, 0, ☃);
    a(☃, aju.a.u(), 1, 2, 0, ☃);
    a(☃, ☃, ☃, 1, 1, 0, cq.c);
    if ((a(☃, 1, 0, -1, ☃).a() == axe.a) && (a(☃, 1, -1, -1, ☃).a() != axe.a)) {
      a(☃, aju.aw.u().a(aot.a, cq.c), 1, 0, -1, ☃);
    }
    for (int ☃ = 0; ☃ < 5; ☃++) {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        b(☃, ☃, 6, ☃, ☃);
        b(☃, aju.e.u(), ☃, -1, ☃, ☃);
      }
    }
    a(☃, ☃, 1, 1, 2, 1);
    
    return true;
  }
}
