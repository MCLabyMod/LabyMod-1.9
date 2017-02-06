import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class np
{
  public final String e;
  private final eu a;
  public boolean f;
  private final nq b;
  private final bbv c;
  private Class<? extends ns> d;
  
  public np(String ☃, eu ☃, nq ☃)
  {
    this.e = ☃;
    this.a = ☃;
    this.b = ☃;
    this.c = new bbx(this);
    
    bbv.a.put(this.c.a(), this.c);
  }
  
  public np(String ☃, eu ☃)
  {
    this(☃, ☃, g);
  }
  
  public np i()
  {
    this.f = true;
    return this;
  }
  
  public np h()
  {
    if (nt.a.containsKey(this.e)) {
      throw new RuntimeException("Duplicate stat id: \"" + ((np)nt.a.get(this.e)).a + "\" and \"" + this.a + "\" at id " + this.e);
    }
    nt.b.add(this);
    nt.a.put(this.e, this);
    
    return this;
  }
  
  public boolean d()
  {
    return false;
  }
  
  public String a(int ☃)
  {
    return this.b.a(☃);
  }
  
  private static NumberFormat k = NumberFormat.getIntegerInstance(Locale.US);
  public static nq g = new nq()
  {
    public String a(int ☃)
    {
      return np.m().format(☃);
    }
  };
  private static DecimalFormat l = new DecimalFormat("########0.00");
  public static nq h = new nq()
  {
    public String a(int ☃)
    {
      double ☃ = ☃ / 20.0D;
      double ☃ = ☃ / 60.0D;
      double ☃ = ☃ / 60.0D;
      double ☃ = ☃ / 24.0D;
      double ☃ = ☃ / 365.0D;
      if (☃ > 0.5D) {
        return np.n().format(☃) + " y";
      }
      if (☃ > 0.5D) {
        return np.n().format(☃) + " d";
      }
      if (☃ > 0.5D) {
        return np.n().format(☃) + " h";
      }
      if (☃ > 0.5D) {
        return np.n().format(☃) + " m";
      }
      return ☃ + " s";
    }
  };
  public static nq i = new nq()
  {
    public String a(int ☃)
    {
      double ☃ = ☃ / 100.0D;
      double ☃ = ☃ / 1000.0D;
      if (☃ > 0.5D) {
        return np.n().format(☃) + " km";
      }
      if (☃ > 0.5D) {
        return np.n().format(☃) + " m";
      }
      return ☃ + " cm";
    }
  };
  public static nq j = new nq()
  {
    public String a(int ☃)
    {
      return np.n().format(☃ * 0.1D);
    }
  };
  
  public eu e()
  {
    eu ☃ = this.a.f();
    ☃.b().a(a.h);
    ☃.b().a(new ew(ew.a.b, new fa(this.e)));
    return ☃;
  }
  
  public eu j()
  {
    eu ☃ = e();
    eu ☃ = new fa("[").a(☃).a("]");
    ☃.a(☃.b());
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ == null) || (getClass() != ☃.getClass())) {
      return false;
    }
    np ☃ = (np)☃;
    
    return this.e.equals(☃.e);
  }
  
  public int hashCode()
  {
    return this.e.hashCode();
  }
  
  public String toString()
  {
    return "Stat{id=" + this.e + ", nameId=" + this.a + ", awardLocallyOnly=" + this.f + ", formatter=" + this.b + ", objectiveCriteria=" + this.c + '}';
  }
  
  public bbv k()
  {
    return this.c;
  }
  
  public Class<? extends ns> l()
  {
    return this.d;
  }
  
  public np b(Class<? extends ns> ☃)
  {
    this.d = ☃;
    return this;
  }
}
