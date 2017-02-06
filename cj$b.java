import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.Logger;

public final class cj$b
  extends cj
{
  private int c;
  private int d;
  private int e;
  private boolean f;
  private static final List<b> g = ;
  
  private cj$b(int ☃, int ☃, int ☃)
  {
    super(0, 0, 0);
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public static b s()
  {
    return c(0, 0, 0);
  }
  
  public static b c(double ☃, double ☃, double ☃)
  {
    return c(on.c(☃), on.c(☃), on.c(☃));
  }
  
  public static b g(df ☃)
  {
    return c(☃.p(), ☃.q(), ☃.r());
  }
  
  public static b c(int ☃, int ☃, int ☃)
  {
    synchronized (g)
    {
      if (!g.isEmpty())
      {
        b ☃ = (b)g.remove(g.size() - 1);
        if ((☃ != null) && (☃.f))
        {
          ☃.f = false;
          ☃.d(☃, ☃, ☃);
          return ☃;
        }
      }
    }
    return new b(☃, ☃, ☃);
  }
  
  public void t()
  {
    synchronized (g)
    {
      if (g.size() < 100) {
        g.add(this);
      }
      this.f = true;
    }
  }
  
  public int p()
  {
    return this.c;
  }
  
  public int q()
  {
    return this.d;
  }
  
  public int r()
  {
    return this.e;
  }
  
  public b d(int ☃, int ☃, int ☃)
  {
    if (this.f)
    {
      cj.o().error("PooledMutableBlockPosition modified after it was released.", new Throwable());
      this.f = false;
    }
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    return this;
  }
  
  public b d(double ☃, double ☃, double ☃)
  {
    return d(on.c(☃), on.c(☃), on.c(☃));
  }
  
  public b h(df ☃)
  {
    return d(☃.p(), ☃.q(), ☃.r());
  }
  
  public b c(cq ☃)
  {
    return d(this.c + ☃.g(), this.d + ☃.h(), this.e + ☃.i());
  }
}
