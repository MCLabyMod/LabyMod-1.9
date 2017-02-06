import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class atq
  extends atp
{
  private Random k;
  private aht l;
  
  static class a
    extends cj
  {
    private final int c;
    
    public a(cj ☃, int ☃)
    {
      super(☃.q(), ☃.r());
      this.c = ☃;
    }
    
    public int s()
    {
      return this.c;
    }
  }
  
  private cj m = cj.a;
  int a;
  int b;
  double c = 0.618D;
  double d = 0.381D;
  double e = 1.0D;
  double f = 1.0D;
  int g = 1;
  int h = 12;
  int i = 4;
  List<atq.a> j;
  
  public atq(boolean ☃)
  {
    super(☃);
  }
  
  void a()
  {
    this.b = ((int)(this.a * this.c));
    if (this.b >= this.a) {
      this.b = (this.a - 1);
    }
    int ☃ = (int)(1.382D + Math.pow(this.f * this.a / 13.0D, 2.0D));
    if (☃ < 1) {
      ☃ = 1;
    }
    int ☃ = this.m.q() + this.b;
    int ☃ = this.a - this.i;
    
    this.j = Lists.newArrayList();
    this.j.add(new atq.a(this.m.b(☃), ☃));
    for (; ☃ >= 0; ☃--)
    {
      float ☃ = a(☃);
      if (☃ >= 0.0F) {
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          double ☃ = this.e * ☃ * (this.k.nextFloat() + 0.328D);
          double ☃ = this.k.nextFloat() * 2.0F * 3.141592653589793D;
          
          double ☃ = ☃ * Math.sin(☃) + 0.5D;
          double ☃ = ☃ * Math.cos(☃) + 0.5D;
          
          cj ☃ = this.m.a(☃, ☃ - 1, ☃);
          cj ☃ = ☃.b(this.i);
          if (a(☃, ☃) == -1)
          {
            int ☃ = this.m.p() - ☃.p();
            int ☃ = this.m.r() - ☃.r();
            
            double ☃ = ☃.q() - Math.sqrt(☃ * ☃ + ☃ * ☃) * this.d;
            int ☃ = ☃ > ☃ ? ☃ : (int)☃;
            cj ☃ = new cj(this.m.p(), ☃, this.m.r());
            if (a(☃, ☃) == -1) {
              this.j.add(new atq.a(☃, ☃.q()));
            }
          }
        }
      }
    }
  }
  
  void a(cj ☃, float ☃, arc ☃)
  {
    int ☃ = (int)(☃ + 0.618D);
    for (int ☃ = -☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃; ☃++) {
        if (Math.pow(Math.abs(☃) + 0.5D, 2.0D) + Math.pow(Math.abs(☃) + 0.5D, 2.0D) <= ☃ * ☃)
        {
          cj ☃ = ☃.a(☃, 0, ☃);
          
          axe ☃ = this.l.o(☃).a();
          if ((☃ == axe.a) || (☃ == axe.j)) {
            a(this.l, ☃, ☃);
          }
        }
      }
    }
  }
  
  float a(int ☃)
  {
    if (☃ < this.a * 0.3F) {
      return -1.0F;
    }
    float ☃ = this.a / 2.0F;
    float ☃ = ☃ - ☃;
    
    float ☃ = on.c(☃ * ☃ - ☃ * ☃);
    if (☃ == 0.0F) {
      ☃ = ☃;
    } else if (Math.abs(☃) >= ☃) {
      return 0.0F;
    }
    return ☃ * 0.5F;
  }
  
  float b(int ☃)
  {
    if ((☃ < 0) || (☃ >= this.i)) {
      return -1.0F;
    }
    if ((☃ == 0) || (☃ == this.i - 1)) {
      return 2.0F;
    }
    return 3.0F;
  }
  
  void a(cj ☃)
  {
    for (int ☃ = 0; ☃ < this.i; ☃++) {
      a(☃.b(☃), b(☃), aju.t.u().a(aml.b, Boolean.valueOf(false)));
    }
  }
  
  void a(cj ☃, cj ☃, ajt ☃)
  {
    cj ☃ = ☃.a(-☃.p(), -☃.q(), -☃.r());
    
    int ☃ = b(☃);
    
    float ☃ = ☃.p() / ☃;
    float ☃ = ☃.q() / ☃;
    float ☃ = ☃.r() / ☃;
    for (int ☃ = 0; ☃ <= ☃; ☃++)
    {
      cj ☃ = ☃.a(0.5F + ☃ * ☃, 0.5F + ☃ * ☃, 0.5F + ☃ * ☃);
      amp.a ☃ = b(☃, ☃);
      
      a(this.l, ☃, ☃.u().a(amp.a, ☃));
    }
  }
  
  private int b(cj ☃)
  {
    int ☃ = on.a(☃.p());
    int ☃ = on.a(☃.q());
    int ☃ = on.a(☃.r());
    if ((☃ > ☃) && (☃ > ☃)) {
      return ☃;
    }
    if (☃ > ☃) {
      return ☃;
    }
    return ☃;
  }
  
  private amp.a b(cj ☃, cj ☃)
  {
    amp.a ☃ = amp.a.b;
    int ☃ = Math.abs(☃.p() - ☃.p());
    int ☃ = Math.abs(☃.r() - ☃.r());
    int ☃ = Math.max(☃, ☃);
    if (☃ > 0) {
      if (☃ == ☃) {
        ☃ = amp.a.a;
      } else if (☃ == ☃) {
        ☃ = amp.a.c;
      }
    }
    return ☃;
  }
  
  void b()
  {
    for (atq.a ☃ : this.j) {
      a(☃);
    }
  }
  
  boolean c(int ☃)
  {
    return ☃ >= this.a * 0.2D;
  }
  
  void c()
  {
    cj ☃ = this.m;
    cj ☃ = this.m.b(this.b);
    ajt ☃ = aju.r;
    
    a(☃, ☃, ☃);
    if (this.g == 2)
    {
      a(☃.f(), ☃.f(), ☃);
      a(☃.f().d(), ☃.f().d(), ☃);
      a(☃.d(), ☃.d(), ☃);
    }
  }
  
  void d()
  {
    for (atq.a ☃ : this.j)
    {
      int ☃ = ☃.s();
      cj ☃ = new cj(this.m.p(), ☃, this.m.r());
      if ((!☃.equals(☃)) && 
        (c(☃ - this.m.q()))) {
        a(☃, ☃, aju.r);
      }
    }
  }
  
  int a(cj ☃, cj ☃)
  {
    cj ☃ = ☃.a(-☃.p(), -☃.q(), -☃.r());
    
    int ☃ = b(☃);
    
    float ☃ = ☃.p() / ☃;
    float ☃ = ☃.q() / ☃;
    float ☃ = ☃.r() / ☃;
    if (☃ == 0) {
      return -1;
    }
    for (int ☃ = 0; ☃ <= ☃; ☃++)
    {
      cj ☃ = ☃.a(0.5F + ☃ * ☃, 0.5F + ☃ * ☃, 0.5F + ☃ * ☃);
      if (!a(this.l.o(☃).t())) {
        return ☃;
      }
    }
    return -1;
  }
  
  public void e()
  {
    this.i = 5;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    this.l = ☃;
    this.m = ☃;
    
    this.k = new Random(☃.nextLong());
    if (this.a == 0) {
      this.a = (5 + this.k.nextInt(this.h));
    }
    if (!f()) {
      return false;
    }
    a();
    b();
    c();
    d();
    
    return true;
  }
  
  private boolean f()
  {
    ajt ☃ = this.l.o(this.m.b()).t();
    if ((☃ != aju.d) && (☃ != aju.c) && (☃ != aju.ak)) {
      return false;
    }
    int ☃ = a(this.m, this.m.b(this.a - 1));
    if (☃ == -1) {
      return true;
    }
    if (☃ < 6) {
      return false;
    }
    this.a = ☃;
    return true;
  }
}
