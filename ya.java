import java.util.List;
import java.util.Random;

public class ya
  extends xz
{
  private int b;
  public long a;
  private int c;
  private final boolean d;
  
  public ya(aht ☃, double ☃, double ☃, double ☃, boolean ☃)
  {
    super(☃);
    b(☃, ☃, ☃, 0.0F, 0.0F);
    this.b = 2;
    this.a = this.S.nextLong();
    this.c = (this.S.nextInt(3) + 1);
    this.d = ☃;
    
    cj ☃ = new cj(this);
    if ((!☃) && (!☃.E) && (☃.U().b("doFireTick")) && ((☃.ae() == qk.c) || (☃.ae() == qk.d)) && (☃.a(☃, 10)))
    {
      if ((☃.o(☃).a() == axe.a) && (aju.ab.a(☃, ☃))) {
        ☃.a(☃, aju.ab.u());
      }
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        cj ☃ = ☃.a(this.S.nextInt(3) - 1, this.S.nextInt(3) - 1, this.S.nextInt(3) - 1);
        if ((☃.o(☃).a() == axe.a) && (aju.ab.a(☃, ☃))) {
          ☃.a(☃, aju.ab.u());
        }
      }
    }
  }
  
  public nh bz()
  {
    return nh.d;
  }
  
  public void m()
  {
    super.m();
    if (this.b == 2)
    {
      this.l.a(null, this.p, this.q, this.r, ng.dh, nh.d, 10000.0F, 0.8F + this.S.nextFloat() * 0.2F);
      this.l.a(null, this.p, this.q, this.r, ng.dg, nh.d, 2.0F, 0.5F + this.S.nextFloat() * 0.2F);
    }
    this.b -= 1;
    if (this.b < 0) {
      if (this.c == 0)
      {
        T();
      }
      else if (this.b < -this.S.nextInt(10))
      {
        this.c -= 1;
        this.b = 1;
        if ((!this.d) && (!this.l.E))
        {
          this.a = this.S.nextLong();
          cj ☃ = new cj(this);
          if ((this.l.U().b("doFireTick")) && (this.l.a(☃, 10)) && 
            (this.l.o(☃).a() == axe.a) && (aju.ab.a(this.l, ☃))) {
            this.l.a(☃, aju.ab.u());
          }
        }
      }
    }
    if (this.b >= 0) {
      if (this.l.E)
      {
        this.l.d(2);
      }
      else if (!this.d)
      {
        double ☃ = 3.0D;
        List<rr> ☃ = this.l.b(this, new bbh(this.p - ☃, this.q - ☃, this.r - ☃, this.p + ☃, this.q + 6.0D + ☃, this.r + ☃));
        for (int ☃ = 0; ☃ < ☃.size(); ☃++)
        {
          rr ☃ = (rr)☃.get(☃);
          ☃.a(this);
        }
      }
    }
  }
  
  protected void i() {}
  
  protected void a(dn ☃) {}
  
  protected void b(dn ☃) {}
}
