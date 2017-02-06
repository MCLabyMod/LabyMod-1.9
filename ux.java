import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ux
  extends tk
{
  private static final Logger a = ;
  private sb b;
  private final Predicate<rr> c;
  private final uy.a d;
  private sa e;
  
  public ux(sb ☃)
  {
    this.b = ☃;
    if ((☃ instanceof sh)) {
      a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
    }
    this.c = new Predicate()
    {
      public boolean a(rr ☃)
      {
        if (!(☃ instanceof zj)) {
          return false;
        }
        if (((zj)☃).bJ.a) {
          return false;
        }
        double ☃ = ux.this.f();
        if (☃.aK()) {
          ☃ *= 0.800000011920929D;
        }
        if (☃.aN())
        {
          float ☃ = ((zj)☃).cG();
          if (☃ < 0.1F) {
            ☃ = 0.1F;
          }
          ☃ *= 0.7F * ☃;
        }
        if (☃.g(ux.a(ux.this)) > ☃) {
          return false;
        }
        return vc.a(ux.a(ux.this), (sa)☃, false, true);
      }
    };
    this.d = new uy.a(☃);
  }
  
  public boolean a()
  {
    double ☃ = f();
    List<zj> ☃ = this.b.l.a(zj.class, this.b.bl().b(☃, 4.0D, ☃), this.c);
    Collections.sort(☃, this.d);
    if (☃.isEmpty()) {
      return false;
    }
    this.e = ((sa)☃.get(0));
    return true;
  }
  
  public boolean b()
  {
    sa ☃ = this.b.A();
    if (☃ == null) {
      return false;
    }
    if (!☃.au()) {
      return false;
    }
    if (((☃ instanceof zj)) && (((zj)☃).bJ.a)) {
      return false;
    }
    bbr ☃ = this.b.aO();
    bbr ☃ = ☃.aO();
    if ((☃ != null) && (☃ == ☃)) {
      return false;
    }
    double ☃ = f();
    if (this.b.h(☃) > ☃ * ☃) {
      return false;
    }
    if (((☃ instanceof lr)) && 
      (((lr)☃).c.d())) {
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.b.c(this.e);
    super.c();
  }
  
  public void d()
  {
    this.b.c(null);
    super.c();
  }
  
  protected double f()
  {
    sm ☃ = this.b.a(yt.b);
    return ☃ == null ? 16.0D : ☃.e();
  }
}
