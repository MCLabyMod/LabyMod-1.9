import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class uy<T extends sa>
  extends vc
{
  protected final Class<T> a;
  private final int i;
  protected final uy.a b;
  protected final Predicate<? super T> c;
  protected T d;
  
  public uy(sh ☃, Class<T> ☃, boolean ☃)
  {
    this(☃, ☃, ☃, false);
  }
  
  public uy(sh ☃, Class<T> ☃, boolean ☃, boolean ☃)
  {
    this(☃, ☃, 10, ☃, ☃, null);
  }
  
  public uy(sh ☃, Class<T> ☃, int ☃, boolean ☃, boolean ☃, final Predicate<? super T> ☃)
  {
    super(☃, ☃, ☃);
    this.a = ☃;
    this.i = ☃;
    this.b = new uy.a(☃);
    a(1);
    
    this.c = new Predicate()
    {
      public boolean a(T ☃)
      {
        if (☃ == null) {
          return false;
        }
        if ((☃ != null) && (!☃.apply(☃))) {
          return false;
        }
        if (!rv.e.apply(☃)) {
          return false;
        }
        return uy.this.a(☃, false);
      }
    };
  }
  
  public boolean a()
  {
    if ((this.i > 0) && (this.e.bF().nextInt(this.i) != 0)) {
      return false;
    }
    if ((this.a == zj.class) || (this.a == lr.class))
    {
      this.d = this.e.l.a(this.e.p, this.e.q + this.e.bn(), this.e.r, f(), f(), new Function()
      {
        public Double a(zj ☃)
        {
          adq ☃ = ☃.a(rw.f);
          if ((☃ != null) && (☃.b() == ads.ch))
          {
            int ☃ = ☃.h();
            boolean ☃ = ((uy.this.e instanceof yw)) && (((yw)uy.this.e).da() == 0) && (☃ == 0);
            boolean ☃ = ((uy.this.e instanceof za)) && (☃ == 2);
            boolean ☃ = ((uy.this.e instanceof yi)) && (☃ == 4);
            if ((☃) || (☃) || (☃)) {
              return Double.valueOf(0.5D);
            }
          }
          return Double.valueOf(1.0D);
        }
      }, this.c);
      
      return this.d != null;
    }
    List<T> ☃ = this.e.l.a(this.a, a(f()), this.c);
    if (☃.isEmpty()) {
      return false;
    }
    Collections.sort(☃, this.b);
    this.d = ((sa)☃.get(0));
    return true;
  }
  
  protected bbh a(double ☃)
  {
    return this.e.bl().b(☃, 4.0D, ☃);
  }
  
  public void c()
  {
    this.e.c(this.d);
    super.c();
  }
  
  public static class a
    implements Comparator<rr>
  {
    private final rr a;
    
    public a(rr ☃)
    {
      this.a = ☃;
    }
    
    public int a(rr ☃, rr ☃)
    {
      double ☃ = this.a.h(☃);
      double ☃ = this.a.h(☃);
      if (☃ < ☃) {
        return -1;
      }
      if (☃ > ☃) {
        return 1;
      }
      return 0;
    }
  }
}
