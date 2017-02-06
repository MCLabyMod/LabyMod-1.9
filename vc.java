import java.util.Random;
import java.util.UUID;

public abstract class vc
  extends tk
{
  protected final sh e;
  protected boolean f;
  private boolean a;
  private int b;
  private int c;
  private int d;
  protected sa g;
  protected int h = 60;
  
  public vc(sh ☃, boolean ☃)
  {
    this(☃, ☃, false);
  }
  
  public vc(sh ☃, boolean ☃, boolean ☃)
  {
    this.e = ☃;
    this.f = ☃;
    this.a = ☃;
  }
  
  public boolean b()
  {
    sa ☃ = this.e.A();
    if (☃ == null) {
      ☃ = this.g;
    }
    if (☃ == null) {
      return false;
    }
    if (!☃.au()) {
      return false;
    }
    bbr ☃ = this.e.aO();
    bbr ☃ = ☃.aO();
    if ((☃ != null) && (☃ == ☃)) {
      return false;
    }
    double ☃ = f();
    if (this.e.h(☃) > ☃ * ☃) {
      return false;
    }
    if (this.f) {
      if (this.e.y().a(☃)) {
        this.d = 0;
      } else if (++this.d > this.h) {
        return false;
      }
    }
    if (((☃ instanceof zj)) && 
      (((zj)☃).bJ.a)) {
      return false;
    }
    this.e.c(☃);
    return true;
  }
  
  protected double f()
  {
    sm ☃ = this.e.a(yt.b);
    return ☃ == null ? 16.0D : ☃.e();
  }
  
  public void c()
  {
    this.b = 0;
    this.c = 0;
    this.d = 0;
  }
  
  public void d()
  {
    this.e.c(null);
    this.g = null;
  }
  
  public static boolean a(sb ☃, sa ☃, boolean ☃, boolean ☃)
  {
    if (☃ == null) {
      return false;
    }
    if (☃ == ☃) {
      return false;
    }
    if (!☃.au()) {
      return false;
    }
    if (!☃.d(☃.getClass())) {
      return false;
    }
    if (☃.r(☃)) {
      return false;
    }
    if (((☃ instanceof sg)) && (((sg)☃).b() != null))
    {
      if (((☃ instanceof sg)) && (((sg)☃).b().equals(☃.bc()))) {
        return false;
      }
      if (☃ == ((sg)☃).p_()) {
        return false;
      }
    }
    else if (((☃ instanceof zj)) && 
      (!☃) && (((zj)☃).bJ.a))
    {
      return false;
    }
    if ((☃) && (!☃.y().a(☃))) {
      return false;
    }
    return true;
  }
  
  protected boolean a(sa ☃, boolean ☃)
  {
    if (!a(this.e, ☃, ☃, this.f)) {
      return false;
    }
    if (!this.e.f(new cj(☃))) {
      return false;
    }
    if (this.a)
    {
      if (--this.c <= 0) {
        this.b = 0;
      }
      if (this.b == 0) {
        this.b = (a(☃) ? 1 : 2);
      }
      if (this.b == 2) {
        return false;
      }
    }
    return true;
  }
  
  private boolean a(sa ☃)
  {
    this.c = (10 + this.e.bF().nextInt(5));
    ayp ☃ = this.e.x().a(☃);
    if (☃ == null) {
      return false;
    }
    ayn ☃ = ☃.c();
    if (☃ == null) {
      return false;
    }
    int ☃ = ☃.a - on.c(☃.p);
    int ☃ = ☃.c - on.c(☃.r);
    return ☃ * ☃ + ☃ * ☃ <= 2.25D;
  }
}
