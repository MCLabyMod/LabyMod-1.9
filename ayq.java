import java.util.HashSet;
import java.util.Set;

public class ayq
{
  private final ayl a = new ayl();
  private final Set<ayn> b = new HashSet();
  private final ayn[] c = new ayn[32];
  private final ayo d;
  
  public ayq(ayo ☃)
  {
    this.d = ☃;
  }
  
  public ayp a(ahx ☃, sb ☃, rr ☃, float ☃)
  {
    return a(☃, ☃, ☃.p, ☃.bl().b, ☃.r, ☃);
  }
  
  public ayp a(ahx ☃, sb ☃, cj ☃, float ☃)
  {
    return a(☃, ☃, ☃.p() + 0.5F, ☃.q() + 0.5F, ☃.r() + 0.5F, ☃);
  }
  
  private ayp a(ahx ☃, sb ☃, double ☃, double ☃, double ☃, float ☃)
  {
    this.a.a();
    this.d.a(☃, ☃);
    ayn ☃ = this.d.b();
    ayn ☃ = this.d.a(☃, ☃, ☃);
    
    ayp ☃ = a(☃, ☃, ☃);
    
    this.d.a();
    return ☃;
  }
  
  private ayp a(ayn ☃, ayn ☃, float ☃)
  {
    ☃.e = 0.0F;
    ☃.f = ☃.c(☃);
    ☃.g = ☃.f;
    
    this.a.a();
    this.b.clear();
    this.a.a(☃);
    
    ayn ☃ = ☃;
    int ☃ = 0;
    while (!this.a.e())
    {
      ☃++;
      if (☃ >= 2000) {
        break;
      }
      ayn ☃ = this.a.c();
      if (☃.equals(☃))
      {
        ☃ = ☃;
        break;
      }
      if (☃.c(☃) < ☃.c(☃)) {
        ☃ = ☃;
      }
      ☃.i = true;
      
      int ☃ = this.d.a(this.c, ☃, ☃, ☃);
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        ayn ☃ = this.c[☃];
        
        float ☃ = ☃.c(☃);
        ☃.j += ☃;
        ☃.k = (☃ + ☃.l);
        
        float ☃ = ☃.e + ☃.k;
        if ((☃.j < ☃) && ((!☃.a()) || (☃ < ☃.e)))
        {
          ☃.h = ☃;
          ☃.e = ☃;
          ☃.f = (☃.c(☃) + ☃.l);
          if (☃.a())
          {
            this.a.a(☃, ☃.e + ☃.f);
          }
          else
          {
            ☃.g = (☃.e + ☃.f);
            this.a.a(☃);
          }
        }
      }
    }
    if (☃ == ☃) {
      return null;
    }
    ayp ☃ = a(☃, ☃);
    
    return ☃;
  }
  
  private ayp a(ayn ☃, ayn ☃)
  {
    int ☃ = 1;
    ayn ☃ = ☃;
    while (☃.h != null)
    {
      ☃++;
      ☃ = ☃.h;
    }
    ayn[] ☃ = new ayn[☃];
    ☃ = ☃;
    ☃[(--☃)] = ☃;
    while (☃.h != null)
    {
      ☃ = ☃.h;
      ☃[(--☃)] = ☃;
    }
    return new ayp(☃);
  }
}
