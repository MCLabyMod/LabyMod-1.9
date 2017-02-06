import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public final class rv
{
  public static final Predicate<rr> a = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ☃.au();
    }
  };
  public static final Predicate<rr> b = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return (☃.au()) && (!☃.aJ()) && (!☃.aI());
    }
  };
  public static final Predicate<rr> c = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ((☃ instanceof qg)) && (☃.au());
    }
  };
  public static final Predicate<rr> d = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return (!(☃ instanceof zj)) || ((!((zj)☃).y()) && (!((zj)☃).l_()));
    }
  };
  public static final Predicate<rr> e = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return (!(☃ instanceof zj)) || (!((zj)☃).y());
    }
  };
  public static final Predicate<rr> f = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ((☃ instanceof yu)) && (☃.au());
    }
  };
  
  public static class a
    implements Predicate<rr>
  {
    private final adq a;
    
    public a(adq ☃)
    {
      this.a = ☃;
    }
    
    public boolean a(rr ☃)
    {
      if (!☃.au()) {
        return false;
      }
      if (!(☃ instanceof sa)) {
        return false;
      }
      sa ☃ = (sa)☃;
      if (☃.a(sb.d(this.a)) != null) {
        return false;
      }
      if ((☃ instanceof sb)) {
        return ((sb)☃).cM();
      }
      if ((☃ instanceof xq)) {
        return true;
      }
      if ((☃ instanceof zj)) {
        return true;
      }
      return false;
    }
  }
  
  public static <T extends rr> Predicate<T> a(double ☃, double ☃, final double ☃, double ☃)
  {
    double ☃ = ☃ * ☃;
    new Predicate()
    {
      public boolean a(T ☃)
      {
        return (☃ != null) && (☃.e(this.a, ☃, this.c) <= this.d);
      }
    };
  }
  
  public static <T extends rr> Predicate<T> a(rr ☃)
  {
    final bbr ☃ = ☃.aO();
    final bbr.a ☃ = ☃ == null ? bbr.a.a : ☃.k();
    if (☃ == bbr.a.b) {
      return Predicates.alwaysFalse();
    }
    Predicates.and(e, new Predicate()
    {
      public boolean a(rr ☃)
      {
        if (!☃.aq()) {
          return false;
        }
        if ((this.a.l.E) && ((!(☃ instanceof zj)) || (!((zj)☃).cJ()))) {
          return false;
        }
        bbr ☃ = ☃.aO();
        bbr.a ☃ = ☃ == null ? bbr.a.a : ☃.k();
        if (☃ == bbr.a.b) {
          return false;
        }
        boolean ☃ = (☃ != null) && (☃.a(☃));
        if (((☃ == bbr.a.d) || (☃ == bbr.a.d)) && (☃)) {
          return false;
        }
        if (((☃ == bbr.a.c) || (☃ == bbr.a.c)) && (!☃)) {
          return false;
        }
        return true;
      }
    });
  }
}
