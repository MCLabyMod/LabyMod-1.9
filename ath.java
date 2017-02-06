import com.google.common.collect.Lists;
import java.util.List;

public class ath
  implements ary
{
  private static final List<arc> c = ;
  protected static final arc a = aju.a.u();
  protected static final arc b = aju.cv.u();
  
  static
  {
    for (ajt ☃ : ajt.h) {
      c.addAll(☃.t().a());
    }
  }
  
  private static final int d = on.f(on.c(c.size()));
  private static final int e = on.f(c.size() / d);
  private final aht f;
  
  public ath(aht ☃)
  {
    this.f = ☃;
  }
  
  public ase a(int ☃, int ☃)
  {
    atf ☃ = new atf();
    for (int ☃ = 0; ☃ < 16; ☃++) {
      for (int ☃ = 0; ☃ < 16; ☃++)
      {
        int ☃ = ☃ * 16 + ☃;
        int ☃ = ☃ * 16 + ☃;
        
        ☃.a(☃, 60, ☃, b);
        
        arc ☃ = c(☃, ☃);
        if (☃ != null) {
          ☃.a(☃, 70, ☃, ☃);
        }
      }
    }
    ase ☃ = new ase(this.f, ☃, ☃, ☃);
    ☃.b();
    
    aig[] ☃ = this.f.A().b(null, ☃ * 16, ☃ * 16, 16, 16);
    byte[] ☃ = ☃.l();
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ((byte)aig.a(☃[☃]));
    }
    ☃.b();
    
    return ☃;
  }
  
  public static arc c(int ☃, int ☃)
  {
    arc ☃ = a;
    if ((☃ > 0) && (☃ > 0) && (☃ % 2 != 0) && (☃ % 2 != 0))
    {
      ☃ /= 2;
      ☃ /= 2;
      if ((☃ <= d) && (☃ <= e))
      {
        int ☃ = on.a(☃ * d + ☃);
        if (☃ < c.size()) {
          ☃ = (arc)c.get(☃);
        }
      }
    }
    return ☃;
  }
  
  public boolean a(ase ☃, int ☃, int ☃)
  {
    return false;
  }
  
  public List<aig.c> a(sc ☃, cj ☃)
  {
    aig ☃ = this.f.b(☃);
    return ☃.a(☃);
  }
  
  public cj a(aht ☃, String ☃, cj ☃)
  {
    return null;
  }
  
  public void b(int ☃, int ☃) {}
  
  public void b(ase ☃, int ☃, int ☃) {}
}
