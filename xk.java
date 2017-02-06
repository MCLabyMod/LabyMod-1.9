import java.lang.reflect.Constructor;
import java.util.Arrays;

public class xk<T extends xe>
{
  private static xk<?>[] l = new xk[0];
  public static final xk<xa> a = a(xa.class, "HoldingPattern");
  public static final xk<xi> b = a(xi.class, "StrafePlayer");
  public static final xk<xc> c = a(xc.class, "LandingApproach");
  public static final xk<xd> d = a(xd.class, "Landing");
  public static final xk<xj> e = a(xj.class, "Takeoff");
  public static final xk<xg> f = a(xg.class, "SittingFlaming");
  public static final xk<xh> g = a(xh.class, "SittingScanning");
  public static final xk<xf> h = a(xf.class, "SittingAttacking");
  public static final xk<wy> i = a(wy.class, "ChargingPlayer");
  public static final xk<wz> j = a(wz.class, "Dying");
  public static final xk<xb> k = a(xb.class, "Hover");
  private final Class<? extends xe> m;
  private final int n;
  private final String o;
  
  private xk(int ☃, Class<? extends xe> ☃, String ☃)
  {
    this.n = ☃;
    this.m = ☃;
    this.o = ☃;
  }
  
  public xe a(wu ☃)
  {
    try
    {
      Constructor<? extends xe> ☃ = a();
      return (xe)☃.newInstance(new Object[] { ☃ });
    }
    catch (Exception ☃)
    {
      throw new Error(☃);
    }
  }
  
  protected Constructor<? extends xe> a()
    throws NoSuchMethodException
  {
    return this.m.getConstructor(new Class[] { wu.class });
  }
  
  public int b()
  {
    return this.n;
  }
  
  public String toString()
  {
    return this.o + " (#" + this.n + ")";
  }
  
  public static xk<?> a(int ☃)
  {
    if ((☃ < 0) || (☃ >= l.length)) {
      return a;
    }
    return l[☃];
  }
  
  public static int c()
  {
    return l.length;
  }
  
  private static <T extends xe> xk<T> a(Class<T> ☃, String ☃)
  {
    xk<T> ☃ = new xk(l.length, ☃, ☃);
    l = (xk[])Arrays.copyOf(l, l.length + 1);
    l[☃.b()] = ☃;
    return ☃;
  }
}
