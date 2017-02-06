import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Random;

public class tf
  extends tk
{
  private static final Predicate<arc> b = ark.a(aju.H).a(apc.a, Predicates.equalTo(apc.a.b));
  private sb c;
  private aht d;
  int a;
  
  public tf(sb ☃)
  {
    this.c = ☃;
    this.d = ☃.l;
    a(7);
  }
  
  public boolean a()
  {
    if (this.c.bF().nextInt(this.c.m_() ? 50 : 1000) != 0) {
      return false;
    }
    cj ☃ = new cj(this.c.p, this.c.q, this.c.r);
    if (b.apply(this.d.o(☃))) {
      return true;
    }
    if (this.d.o(☃.b()).t() == aju.c) {
      return true;
    }
    return false;
  }
  
  public void c()
  {
    this.a = 40;
    this.d.a(this.c, (byte)10);
    this.c.x().o();
  }
  
  public void d()
  {
    this.a = 0;
  }
  
  public boolean b()
  {
    return this.a > 0;
  }
  
  public int f()
  {
    return this.a;
  }
  
  public void e()
  {
    this.a = Math.max(0, this.a - 1);
    if (this.a != 4) {
      return;
    }
    cj ☃ = new cj(this.c.p, this.c.q, this.c.r);
    if (b.apply(this.d.o(☃)))
    {
      if (this.d.U().b("mobGriefing")) {
        this.d.b(☃, false);
      }
      this.c.B();
    }
    else
    {
      cj ☃ = ☃.b();
      if (this.d.o(☃).t() == aju.c)
      {
        if (this.d.U().b("mobGriefing"))
        {
          this.d.b(2001, ☃, ajt.a(aju.c));
          this.d.a(☃, aju.d.u(), 2);
        }
        this.c.B();
      }
    }
  }
}
