import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class uw
  extends tk
{
  private static final Logger a = ;
  private sb b;
  private final Predicate<sa> c;
  private final uy.a d;
  private sa e;
  private Class<? extends sa> f;
  
  public uw(sb ☃, Class<? extends sa> ☃)
  {
    this.b = ☃;
    this.f = ☃;
    if ((☃ instanceof sh)) {
      a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
    }
    this.c = new Predicate()
    {
      public boolean a(sa ☃)
      {
        double ☃ = uw.this.f();
        if (☃.aK()) {
          ☃ *= 0.800000011920929D;
        }
        if (☃.aN()) {
          return false;
        }
        if (☃.g(uw.a(uw.this)) > ☃) {
          return false;
        }
        return vc.a(uw.a(uw.this), ☃, false, true);
      }
    };
    this.d = new uy.a(☃);
  }
  
  public boolean a()
  {
    double ☃ = f();
    List<sa> ☃ = this.b.l.a(this.f, this.b.bl().b(☃, 4.0D, ☃), this.c);
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
