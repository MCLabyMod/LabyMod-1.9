import com.google.common.collect.ComparisonChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class rl
  implements Comparable<rl>
{
  private static final Logger a = ;
  private final rk b;
  private int c;
  private int d;
  private boolean e;
  private boolean f;
  private boolean g;
  private boolean h;
  
  public rl(rk ☃)
  {
    this(☃, 0, 0);
  }
  
  public rl(rk ☃, int ☃)
  {
    this(☃, ☃, 0);
  }
  
  public rl(rk ☃, int ☃, int ☃)
  {
    this(☃, ☃, ☃, false, true);
  }
  
  public rl(rk ☃, int ☃, int ☃, boolean ☃, boolean ☃)
  {
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.f = ☃;
    this.h = ☃;
  }
  
  public rl(rl ☃)
  {
    this.b = ☃.b;
    this.c = ☃.c;
    this.d = ☃.d;
    this.f = ☃.f;
    this.h = ☃.h;
  }
  
  public void a(rl ☃)
  {
    if (this.b != ☃.b) {
      a.warn("This method should only be called for matching effects!");
    }
    if (☃.d > this.d)
    {
      this.d = ☃.d;
      this.c = ☃.c;
    }
    else if ((☃.d == this.d) && (this.c < ☃.c))
    {
      this.c = ☃.c;
    }
    else if ((!☃.f) && (this.f))
    {
      this.f = ☃.f;
    }
    this.h = ☃.h;
  }
  
  public rk a()
  {
    return this.b;
  }
  
  public int b()
  {
    return this.c;
  }
  
  public int c()
  {
    return this.d;
  }
  
  public boolean d()
  {
    return this.f;
  }
  
  public boolean e()
  {
    return this.h;
  }
  
  public boolean a(sa ☃)
  {
    if (this.c > 0)
    {
      if (this.b.a(this.c, this.d)) {
        b(☃);
      }
      h();
    }
    return this.c > 0;
  }
  
  private int h()
  {
    return --this.c;
  }
  
  public void b(sa ☃)
  {
    if (this.c > 0) {
      this.b.a(☃, this.d);
    }
  }
  
  public String f()
  {
    return this.b.a();
  }
  
  public String toString()
  {
    String ☃ = "";
    if (this.d > 0) {
      ☃ = f() + " x " + (this.d + 1) + ", Duration: " + this.c;
    } else {
      ☃ = f() + ", Duration: " + this.c;
    }
    if (this.e) {
      ☃ = ☃ + ", Splash: true";
    }
    if (!this.h) {
      ☃ = ☃ + ", Particles: false";
    }
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof rl))
    {
      rl ☃ = (rl)☃;
      
      return (this.c == ☃.c) && (this.d == ☃.d) && (this.e == ☃.e) && (this.f == ☃.f) && (this.b.equals(☃.b));
    }
    return false;
  }
  
  public int hashCode()
  {
    int ☃ = this.b.hashCode();
    ☃ = 31 * ☃ + this.c;
    ☃ = 31 * ☃ + this.d;
    ☃ = 31 * ☃ + (this.e ? 1 : 0);
    ☃ = 31 * ☃ + (this.f ? 1 : 0);
    return ☃;
  }
  
  public dn a(dn ☃)
  {
    ☃.a("Id", (byte)rk.a(a()));
    ☃.a("Amplifier", (byte)c());
    ☃.a("Duration", b());
    ☃.a("Ambient", d());
    ☃.a("ShowParticles", e());
    return ☃;
  }
  
  public static rl b(dn ☃)
  {
    int ☃ = ☃.f("Id");
    rk ☃ = rk.a(☃);
    if (☃ == null) {
      return null;
    }
    int ☃ = ☃.f("Amplifier");
    int ☃ = ☃.h("Duration");
    boolean ☃ = ☃.p("Ambient");
    boolean ☃ = true;
    if (☃.b("ShowParticles", 1)) {
      ☃ = ☃.p("ShowParticles");
    }
    return new rl(☃, ☃, ☃, ☃, ☃);
  }
  
  public void b(boolean ☃)
  {
    this.g = ☃;
  }
  
  public boolean g()
  {
    return this.g;
  }
  
  public int b(rl ☃)
  {
    int ☃ = 32147;
    if (((b() > 32147) && (☃.b() > 32147)) || ((d()) && (☃.d()))) {
      return ComparisonChain.start().compare(Boolean.valueOf(d()), Boolean.valueOf(☃.d())).compare(a().g(), ☃.a().g()).result();
    }
    return ComparisonChain.start().compare(Boolean.valueOf(d()), Boolean.valueOf(☃.d())).compare(b(), ☃.b()).compare(a().g(), ☃.a().g()).result();
  }
}
