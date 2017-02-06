import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class bdd
  extends bcv
{
  private final int g;
  private final bct h;
  public int a;
  public int f;
  private final int i;
  private final int j;
  private String k = "";
  private int l = 32;
  private int m;
  private boolean n = true;
  private boolean o = true;
  private boolean p;
  private boolean q = true;
  private int r;
  private int s;
  private int t;
  private int u = 14737632;
  private int v = 7368816;
  private boolean w = true;
  private bdo.b x;
  private Predicate<String> y = Predicates.alwaysTrue();
  
  public bdd(int ☃, bct ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.g = ☃;
    this.h = ☃;
    this.a = ☃;
    this.f = ☃;
    this.i = ☃;
    this.j = ☃;
  }
  
  public void a(bdo.b ☃)
  {
    this.x = ☃;
  }
  
  public void a()
  {
    this.m += 1;
  }
  
  public void a(String ☃)
  {
    if (!this.y.apply(☃)) {
      return;
    }
    if (☃.length() > this.l) {
      this.k = ☃.substring(0, this.l);
    } else {
      this.k = ☃;
    }
    f();
  }
  
  public String b()
  {
    return this.k;
  }
  
  public String c()
  {
    int ☃ = this.s < this.t ? this.s : this.t;
    int ☃ = this.s < this.t ? this.t : this.s;
    
    return this.k.substring(☃, ☃);
  }
  
  public void a(Predicate<String> ☃)
  {
    this.y = ☃;
  }
  
  public void b(String ☃)
  {
    String ☃ = "";
    String ☃ = f.a(☃);
    int ☃ = this.s < this.t ? this.s : this.t;
    int ☃ = this.s < this.t ? this.t : this.s;
    int ☃ = this.l - this.k.length() - (☃ - ☃);
    int ☃ = 0;
    if (!this.k.isEmpty()) {
      ☃ = ☃ + this.k.substring(0, ☃);
    }
    if (☃ < ☃.length())
    {
      ☃ = ☃ + ☃.substring(0, ☃);
      ☃ = ☃;
    }
    else
    {
      ☃ = ☃ + ☃;
      ☃ = ☃.length();
    }
    if ((!this.k.isEmpty()) && (☃ < this.k.length())) {
      ☃ = ☃ + this.k.substring(☃);
    }
    if (!this.y.apply(☃)) {
      return;
    }
    this.k = ☃;
    d(☃ - this.t + ☃);
    if (this.x != null) {
      this.x.a(this.g, this.k);
    }
  }
  
  public void a(int ☃)
  {
    if (this.k.isEmpty()) {
      return;
    }
    if (this.t != this.s)
    {
      b("");
      return;
    }
    b(c(☃) - this.s);
  }
  
  public void b(int ☃)
  {
    if (this.k.isEmpty()) {
      return;
    }
    if (this.t != this.s)
    {
      b("");
      return;
    }
    boolean ☃ = ☃ < 0;
    int ☃ = ☃ ? this.s + ☃ : this.s;
    int ☃ = ☃ ? this.s : this.s + ☃;
    String ☃ = "";
    if (☃ >= 0) {
      ☃ = this.k.substring(0, ☃);
    }
    if (☃ < this.k.length()) {
      ☃ = ☃ + this.k.substring(☃);
    }
    if (!this.y.apply(☃)) {
      return;
    }
    this.k = ☃;
    if (☃) {
      d(☃);
    }
    if (this.x != null) {
      this.x.a(this.g, this.k);
    }
  }
  
  public int d()
  {
    return this.g;
  }
  
  public int c(int ☃)
  {
    return a(☃, i());
  }
  
  public int a(int ☃, int ☃)
  {
    return a(☃, ☃, true);
  }
  
  public int a(int ☃, int ☃, boolean ☃)
  {
    int ☃ = ☃;
    boolean ☃ = ☃ < 0;
    int ☃ = Math.abs(☃);
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      if (☃)
      {
        while ((☃) && (☃ > 0) && (this.k.charAt(☃ - 1) == ' ')) {
          ☃--;
        }
        while ((☃ > 0) && (this.k.charAt(☃ - 1) != ' ')) {
          ☃--;
        }
      }
      int ☃ = this.k.length();
      
      ☃ = this.k.indexOf(' ', ☃);
      if (☃ == -1) {
        ☃ = ☃;
      } else {
        while ((☃) && (☃ < ☃) && (this.k.charAt(☃) == ' ')) {
          ☃++;
        }
      }
    }
    return ☃;
  }
  
  public void d(int ☃)
  {
    e(this.t + ☃);
  }
  
  public void e(int ☃)
  {
    this.s = ☃;
    
    int ☃ = this.k.length();
    this.s = on.a(this.s, 0, ☃);
    
    i(this.s);
  }
  
  public void e()
  {
    e(0);
  }
  
  public void f()
  {
    e(this.k.length());
  }
  
  public boolean a(char ☃, int ☃)
  {
    if (!this.p) {
      return false;
    }
    if (bfb.g(☃))
    {
      f();
      i(0);
      return true;
    }
    if (bfb.f(☃))
    {
      bfb.e(c());
      return true;
    }
    if (bfb.e(☃))
    {
      if (this.q) {
        b(bfb.o());
      }
      return true;
    }
    if (bfb.d(☃))
    {
      bfb.e(c());
      if (this.q) {
        b("");
      }
      return true;
    }
    switch (☃)
    {
    case 203: 
      if (bfb.r())
      {
        if (bfb.q()) {
          i(a(-1, o()));
        } else {
          i(o() - 1);
        }
      }
      else if (bfb.q()) {
        e(c(-1));
      } else {
        d(-1);
      }
      return true;
    case 205: 
      if (bfb.r())
      {
        if (bfb.q()) {
          i(a(1, o()));
        } else {
          i(o() + 1);
        }
      }
      else if (bfb.q()) {
        e(c(1));
      } else {
        d(1);
      }
      return true;
    case 14: 
      if (bfb.q())
      {
        if (this.q) {
          a(-1);
        }
      }
      else if (this.q) {
        b(-1);
      }
      return true;
    case 211: 
      if (bfb.q())
      {
        if (this.q) {
          a(1);
        }
      }
      else if (this.q) {
        b(1);
      }
      return true;
    case 199: 
      if (bfb.r()) {
        i(0);
      } else {
        e();
      }
      return true;
    case 207: 
      if (bfb.r()) {
        i(this.k.length());
      } else {
        f();
      }
      return true;
    }
    if (f.a(☃))
    {
      if (this.q) {
        b(Character.toString(☃));
      }
      return true;
    }
    return false;
  }
  
  public void a(int ☃, int ☃, int ☃)
  {
    boolean ☃ = (☃ >= this.a) && (☃ < this.a + this.i) && (☃ >= this.f) && (☃ < this.f + this.j);
    if (this.o) {
      b(☃);
    }
    if ((this.p) && (☃) && (☃ == 0))
    {
      int ☃ = ☃ - this.a;
      if (this.n) {
        ☃ -= 4;
      }
      String ☃ = this.h.a(this.k.substring(this.r), p());
      e(this.h.a(☃, ☃).length() + this.r);
    }
  }
  
  public void g()
  {
    if (!r()) {
      return;
    }
    if (j())
    {
      a(this.a - 1, this.f - 1, this.a + this.i + 1, this.f + this.j + 1, -6250336);
      a(this.a, this.f, this.a + this.i, this.f + this.j, -16777216);
    }
    int ☃ = this.q ? this.u : this.v;
    int ☃ = this.s - this.r;
    int ☃ = this.t - this.r;
    String ☃ = this.h.a(this.k.substring(this.r), p());
    boolean ☃ = (☃ >= 0) && (☃ <= ☃.length());
    boolean ☃ = (this.p) && (this.m / 6 % 2 == 0) && (☃);
    int ☃ = this.n ? this.a + 4 : this.a;
    int ☃ = this.n ? this.f + (this.j - 8) / 2 : this.f;
    int ☃ = ☃;
    if (☃ > ☃.length()) {
      ☃ = ☃.length();
    }
    if (!☃.isEmpty())
    {
      String ☃ = ☃ ? ☃.substring(0, ☃) : ☃;
      ☃ = this.h.a(☃, ☃, ☃, ☃);
    }
    boolean ☃ = (this.s < this.k.length()) || (this.k.length() >= h());
    int ☃ = ☃;
    if (!☃)
    {
      ☃ = ☃ > 0 ? ☃ + this.i : ☃;
    }
    else if (☃)
    {
      ☃--;
      ☃--;
    }
    if ((!☃.isEmpty()) && (☃) && (☃ < ☃.length())) {
      ☃ = this.h.a(☃.substring(☃), ☃, ☃, ☃);
    }
    if (☃) {
      if (☃) {
        bcv.a(☃, ☃ - 1, ☃ + 1, ☃ + 1 + this.h.a, -3092272);
      } else {
        this.h.a("_", ☃, ☃, ☃);
      }
    }
    if (☃ != ☃)
    {
      int ☃ = ☃ + this.h.a(☃.substring(0, ☃));
      c(☃, ☃ - 1, ☃ - 1, ☃ + 1 + this.h.a);
    }
  }
  
  private void c(int ☃, int ☃, int ☃, int ☃)
  {
    if (☃ < ☃)
    {
      int ☃ = ☃;
      ☃ = ☃;
      ☃ = ☃;
    }
    if (☃ < ☃)
    {
      int ☃ = ☃;
      ☃ = ☃;
      ☃ = ☃;
    }
    if (☃ > this.a + this.i) {
      ☃ = this.a + this.i;
    }
    if (☃ > this.a + this.i) {
      ☃ = this.a + this.i;
    }
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    bni.c(0.0F, 0.0F, 255.0F, 255.0F);
    bni.z();
    bni.w();
    bni.a(bni.o.n);
    
    ☃.a(7, bvp.e);
    ☃.b(☃, ☃, 0.0D).d();
    ☃.b(☃, ☃, 0.0D).d();
    ☃.b(☃, ☃, 0.0D).d();
    ☃.b(☃, ☃, 0.0D).d();
    ☃.b();
    
    bni.x();
    bni.y();
  }
  
  public void f(int ☃)
  {
    this.l = ☃;
    if (this.k.length() > ☃) {
      this.k = this.k.substring(0, ☃);
    }
  }
  
  public int h()
  {
    return this.l;
  }
  
  public int i()
  {
    return this.s;
  }
  
  public boolean j()
  {
    return this.n;
  }
  
  public void a(boolean ☃)
  {
    this.n = ☃;
  }
  
  public void g(int ☃)
  {
    this.u = ☃;
  }
  
  public void h(int ☃)
  {
    this.v = ☃;
  }
  
  public void b(boolean ☃)
  {
    if ((☃) && (!this.p)) {
      this.m = 0;
    }
    this.p = ☃;
  }
  
  public boolean m()
  {
    return this.p;
  }
  
  public void c(boolean ☃)
  {
    this.q = ☃;
  }
  
  public int o()
  {
    return this.t;
  }
  
  public int p()
  {
    return j() ? this.i - 8 : this.i;
  }
  
  public void i(int ☃)
  {
    int ☃ = this.k.length();
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ < 0) {
      ☃ = 0;
    }
    this.t = ☃;
    if (this.h != null)
    {
      if (this.r > ☃) {
        this.r = ☃;
      }
      int ☃ = p();
      String ☃ = this.h.a(this.k.substring(this.r), ☃);
      int ☃ = ☃.length() + this.r;
      if (☃ == this.r) {
        this.r -= this.h.a(this.k, ☃, true).length();
      }
      if (☃ > ☃) {
        this.r += ☃ - ☃;
      } else if (☃ <= this.r) {
        this.r -= this.r - ☃;
      }
      this.r = on.a(this.r, 0, ☃);
    }
  }
  
  public void d(boolean ☃)
  {
    this.o = ☃;
  }
  
  public boolean r()
  {
    return this.w;
  }
  
  public void e(boolean ☃)
  {
    this.w = ☃;
  }
}
