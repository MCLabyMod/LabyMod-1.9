import com.google.common.collect.Sets;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class aia
{
  private static final int a = (int)Math.pow(17.0D, 2.0D);
  private final Set<ahn> b = Sets.newHashSet();
  
  public int a(lp ☃, boolean ☃, boolean ☃, boolean ☃)
  {
    if ((!☃) && (!☃)) {
      return 0;
    }
    this.b.clear();
    int ☃ = 0;
    for (zj ☃ : ☃.i) {
      if (!☃.y())
      {
        int ☃ = on.c(☃.p / 16.0D);
        int ☃ = on.c(☃.r / 16.0D);
        int ☃ = 8;
        for (int ☃ = -☃; ☃ <= ☃; ☃++) {
          for (int ☃ = -☃; ☃ <= ☃; ☃++)
          {
            boolean ☃ = (☃ == -☃) || (☃ == ☃) || (☃ == -☃) || (☃ == ☃);
            ahn ☃ = new ahn(☃ + ☃, ☃ + ☃);
            if (!this.b.contains(☃))
            {
              ☃++;
              if ((!☃) && (☃.aj().a(☃)))
              {
                lu ☃ = ☃.w().b(☃.a, ☃.b);
                if ((☃ != null) && (☃.e())) {
                  this.b.add(☃);
                }
              }
            }
          }
        }
      }
    }
    int ☃ = 0;
    cj ☃ = ☃.R();
    sc ☃;
    cj.a ☃;
    label902:
    label908:
    for (☃ : sc.values()) {
      if ((!☃.d()) || (☃)) {
        if ((☃.d()) || (☃)) {
          if ((!☃.e()) || (☃))
          {
            int ☃ = ☃.a(☃.a());
            int ☃ = ☃.b() * ☃ / a;
            if (☃ <= ☃)
            {
              ☃ = new cj.a();
              for (ahn ☃ : this.b)
              {
                cj ☃ = a(☃, ☃.a, ☃.b);
                int ☃ = ☃.p();
                int ☃ = ☃.q();
                int ☃ = ☃.r();
                
                arc ☃ = ☃.o(☃);
                if (!☃.l())
                {
                  int ☃ = 0;
                  for (int ☃ = 0;; ☃++)
                  {
                    if (☃ >= 3) {
                      break label908;
                    }
                    int ☃ = ☃;
                    int ☃ = ☃;
                    int ☃ = ☃;
                    int ☃ = 6;
                    
                    aig.c ☃ = null;
                    sd ☃ = null;
                    
                    int ☃ = on.f(Math.random() * 4.0D);
                    for (int ☃ = 0;; ☃++)
                    {
                      if (☃ >= ☃) {
                        break label902;
                      }
                      ☃ += ☃.r.nextInt(☃) - ☃.r.nextInt(☃);
                      ☃ += ☃.r.nextInt(1) - ☃.r.nextInt(1);
                      ☃ += ☃.r.nextInt(☃) - ☃.r.nextInt(☃);
                      ☃.c(☃, ☃, ☃);
                      
                      float ☃ = ☃ + 0.5F;
                      float ☃ = ☃ + 0.5F;
                      if (!☃.a(☃, ☃, ☃, 24.0D)) {
                        if (☃.e(☃, ☃, ☃) >= 576.0D)
                        {
                          if (☃ == null)
                          {
                            ☃ = ☃.a(☃, ☃);
                            if (☃ == null) {
                              break label902;
                            }
                          }
                          if (☃.a(☃, ☃, ☃)) {
                            if (a(se.a(☃.b), ☃, ☃))
                            {
                              sb ☃;
                              try
                              {
                                ☃ = (sb)☃.b.getConstructor(new Class[] { aht.class }).newInstance(new Object[] { ☃ });
                              }
                              catch (Exception ☃)
                              {
                                ☃.printStackTrace();
                                return ☃;
                              }
                              ☃.b(☃, ☃, ☃, ☃.r.nextFloat() * 360.0F, 0.0F);
                              if ((☃.cF()) && (☃.cG()))
                              {
                                ☃ = ☃.a(☃.D(new cj(☃)), ☃);
                                if (☃.cG())
                                {
                                  ☃++;
                                  ☃.a(☃);
                                }
                                else
                                {
                                  ☃.T();
                                }
                                if (☃ >= ☃.cJ()) {
                                  break;
                                }
                              }
                              ☃ += ☃;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return ☃;
  }
  
  protected static cj a(aht ☃, int ☃, int ☃)
  {
    ase ☃ = ☃.a(☃, ☃);
    int ☃ = ☃ * 16 + ☃.r.nextInt(16);
    int ☃ = ☃ * 16 + ☃.r.nextInt(16);
    int ☃ = on.c(☃.e(new cj(☃, 0, ☃)) + 1, 16);
    int ☃ = ☃.r.nextInt(☃ > 0 ? ☃ : ☃.g() + 16 - 1);
    
    return new cj(☃, ☃, ☃);
  }
  
  public static boolean a(arc ☃)
  {
    if (☃.k()) {
      return false;
    }
    if (☃.m()) {
      return false;
    }
    if (☃.a().d()) {
      return false;
    }
    if (ajp.i(☃)) {
      return false;
    }
    return true;
  }
  
  public static boolean a(sb.a ☃, aht ☃, cj ☃)
  {
    if (☃.aj().a(☃))
    {
      arc ☃ = ☃.o(☃);
      if (☃ == sb.a.c) {
        return (☃.a().d()) && (☃.o(☃.b()).a().d()) && (!☃.o(☃.a()).l());
      }
      cj ☃ = ☃.b();
      if (!☃.o(☃).q()) {
        return false;
      }
      ajt ☃ = ☃.o(☃).t();
      boolean ☃ = (☃ != aju.h) && (☃ != aju.cv);
      return (☃) && (a(☃)) && (a(☃.o(☃.a())));
    }
    return false;
  }
  
  public static void a(aht ☃, aig ☃, int ☃, int ☃, int ☃, int ☃, Random ☃)
  {
    List<aig.c> ☃ = ☃.a(sc.b);
    if (☃.isEmpty()) {
      return;
    }
    while (☃.nextFloat() < ☃.f())
    {
      aig.c ☃ = (aig.c)ov.a(☃.r, ☃);
      int ☃ = ☃.c + ☃.nextInt(1 + ☃.d - ☃.c);
      sd ☃ = null;
      
      int ☃ = ☃ + ☃.nextInt(☃);
      int ☃ = ☃ + ☃.nextInt(☃);
      int ☃ = ☃;
      int ☃ = ☃;
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        boolean ☃ = false;
        for (int ☃ = 0; (!☃) && (☃ < 4); ☃++)
        {
          cj ☃ = ☃.q(new cj(☃, 0, ☃));
          if (a(sb.a.a, ☃, ☃))
          {
            sb ☃;
            try
            {
              ☃ = (sb)☃.b.getConstructor(new Class[] { aht.class }).newInstance(new Object[] { ☃ });
            }
            catch (Exception ☃)
            {
              ☃.printStackTrace();
              continue;
            }
            ☃.b(☃ + 0.5F, ☃.q(), ☃ + 0.5F, ☃.nextFloat() * 360.0F, 0.0F);
            
            ☃.a(☃);
            ☃ = ☃.a(☃.D(new cj(☃)), ☃);
            ☃ = true;
          }
          ☃ += ☃.nextInt(5) - ☃.nextInt(5);
          ☃ += ☃.nextInt(5) - ☃.nextInt(5);
          while ((☃ < ☃) || (☃ >= ☃ + ☃) || (☃ < ☃) || (☃ >= ☃ + ☃))
          {
            ☃ = ☃ + ☃.nextInt(5) - ☃.nextInt(5);
            ☃ = ☃ + ☃.nextInt(5) - ☃.nextInt(5);
          }
        }
      }
    }
  }
}
