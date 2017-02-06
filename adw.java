import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.List;

public class adw
  extends acp
{
  protected adw()
  {
    a(true);
  }
  
  public static ayz a(int ☃, aht ☃)
  {
    String ☃ = "map_" + ☃;
    ayz ☃ = (ayz)☃.a(ayz.class, ☃);
    if (☃ == null)
    {
      ☃ = new ayz(☃);
      
      ☃.a(☃, ☃);
    }
    return ☃;
  }
  
  public ayz a(adq ☃, aht ☃)
  {
    String ☃ = "map_" + ☃.i();
    ayz ☃ = (ayz)☃.a(ayz.class, ☃);
    if ((☃ == null) && (!☃.E))
    {
      ☃.b(☃.b("map"));
      ☃ = "map_" + ☃.i();
      ☃ = new ayz(☃);
      
      ☃.f = 3;
      ☃.a(☃.T().b(), ☃.T().d(), ☃.f);
      ☃.d = ((byte)☃.s.p().a());
      
      ☃.c();
      
      ☃.a(☃, ☃);
    }
    return ☃;
  }
  
  public void a(aht ☃, rr ☃, ayz ☃)
  {
    if ((☃.s.p().a() != ☃.d) || (!(☃ instanceof zj))) {
      return;
    }
    int ☃ = 1 << ☃.f;
    int ☃ = ☃.b;
    int ☃ = ☃.c;
    
    int ☃ = on.c(☃.p - ☃) / ☃ + 64;
    int ☃ = on.c(☃.r - ☃) / ☃ + 64;
    int ☃ = 128 / ☃;
    if (☃.s.m()) {
      ☃ /= 2;
    }
    ayz.a ☃ = ☃.a((zj)☃);
    ☃.b += 1;
    
    boolean ☃ = false;
    for (int ☃ = ☃ - ☃ + 1; ☃ < ☃ + ☃; ☃++) {
      if (((☃ & 0xF) == (☃.b & 0xF)) || (☃))
      {
        ☃ = false;
        double ☃ = 0.0D;
        for (int ☃ = ☃ - ☃ - 1; ☃ < ☃ + ☃; ☃++) {
          if ((☃ >= 0) && (☃ >= -1) && (☃ < 128) && (☃ < 128))
          {
            int ☃ = ☃ - ☃;
            int ☃ = ☃ - ☃;
            
            boolean ☃ = ☃ * ☃ + ☃ * ☃ > (☃ - 2) * (☃ - 2);
            
            int ☃ = (☃ / ☃ + ☃ - 64) * ☃;
            int ☃ = (☃ / ☃ + ☃ - 64) * ☃;
            
            Multiset<axf> ☃ = HashMultiset.create();
            
            ase ☃ = ☃.f(new cj(☃, 0, ☃));
            if (!☃.f())
            {
              int ☃ = ☃ & 0xF;
              int ☃ = ☃ & 0xF;
              int ☃ = 0;
              
              double ☃ = 0.0D;
              if (☃.s.m())
              {
                int ☃ = ☃ + ☃ * 231871;
                ☃ = ☃ * ☃ * 31287121 + ☃ * 11;
                if ((☃ >> 20 & 0x1) == 0) {
                  ☃.add(aju.d.u().a(akt.a, akt.a.a).g(), 10);
                } else {
                  ☃.add(aju.b.u().a(aox.a, aox.a.a).g(), 100);
                }
                ☃ = 100.0D;
              }
              else
              {
                cj.a ☃ = new cj.a();
                for (int ☃ = 0; ☃ < ☃; ☃++) {
                  for (int ☃ = 0; ☃ < ☃; ☃++)
                  {
                    int ☃ = ☃.b(☃ + ☃, ☃ + ☃) + 1;
                    arc ☃ = aju.a.u();
                    if (☃ > 1)
                    {
                      do
                      {
                        ☃--;
                        ☃ = ☃.a(☃.c(☃ + ☃, ☃, ☃ + ☃));
                      } while ((☃.g() == axf.b) && (☃ > 0));
                      if ((☃ > 0) && (☃.a().d()))
                      {
                        int ☃ = ☃ - 1;
                        arc ☃;
                        do
                        {
                          ☃ = ☃.a(☃ + ☃, ☃--, ☃ + ☃);
                          ☃++;
                        } while ((☃ > 0) && (☃.a().d()));
                      }
                    }
                    ☃ += ☃ / (☃ * ☃);
                    
                    ☃.add(☃.g());
                  }
                }
              }
              ☃ /= ☃ * ☃;
              
              double ☃ = (☃ - ☃) * 4.0D / (☃ + 4) + ((☃ + ☃ & 0x1) - 0.5D) * 0.4D;
              int ☃ = 1;
              if (☃ > 0.6D) {
                ☃ = 2;
              }
              if (☃ < -0.6D) {
                ☃ = 0;
              }
              axf ☃ = (axf)Iterables.getFirst(Multisets.copyHighestCountFirst(☃), axf.b);
              if (☃ == axf.n)
              {
                ☃ = ☃ * 0.1D + (☃ + ☃ & 0x1) * 0.2D;
                ☃ = 1;
                if (☃ < 0.5D) {
                  ☃ = 2;
                }
                if (☃ > 0.9D) {
                  ☃ = 0;
                }
              }
              ☃ = ☃;
              if (☃ >= 0) {
                if (☃ * ☃ + ☃ * ☃ < ☃ * ☃) {
                  if ((!☃) || ((☃ + ☃ & 0x1) != 0))
                  {
                    byte ☃ = ☃.g[(☃ + ☃ * 128)];
                    byte ☃ = (byte)(☃.M * 4 + ☃);
                    if (☃ != ☃)
                    {
                      ☃.g[(☃ + ☃ * 128)] = ☃;
                      ☃.a(☃, ☃);
                      ☃ = true;
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
  
  public void a(adq ☃, aht ☃, rr ☃, int ☃, boolean ☃)
  {
    if (☃.E) {
      return;
    }
    ayz ☃ = a(☃, ☃);
    if ((☃ instanceof zj))
    {
      zj ☃ = (zj)☃;
      ☃.a(☃, ☃);
    }
    if ((☃) || (((☃ instanceof zj)) && (((zj)☃).cc() == ☃))) {
      a(☃, ☃, ☃);
    }
  }
  
  public ff<?> a(adq ☃, aht ☃, zj ☃)
  {
    return a(☃, ☃).a(☃, ☃, ☃);
  }
  
  public void b(adq ☃, aht ☃, zj ☃)
  {
    dn ☃ = ☃.o();
    if (☃ != null) {
      if (☃.b("map_scale_direction", 99))
      {
        a(☃, ☃, ☃.h("map_scale_direction"));
        ☃.q("map_scale_direction");
      }
      else if (☃.p("map_tracking_position"))
      {
        b(☃, ☃);
        ☃.q("map_tracking_position");
      }
    }
  }
  
  protected static void a(adq ☃, aht ☃, int ☃)
  {
    ayz ☃ = ads.bk.a(☃, ☃);
    ☃.b(☃.b("map"));
    ayz ☃ = new ayz("map_" + ☃.i());
    
    ☃.f = ((byte)on.a(☃.f + ☃, 0, 4));
    
    ☃.e = ☃.e;
    ☃.a(☃.b, ☃.c, ☃.f);
    ☃.d = ☃.d;
    ☃.c();
    ☃.a("map_" + ☃.i(), ☃);
  }
  
  protected static void b(adq ☃, aht ☃)
  {
    ayz ☃ = ads.bk.a(☃, ☃);
    ☃.b(☃.b("map"));
    ayz ☃ = new ayz("map_" + ☃.i());
    
    ☃.e = true;
    
    ☃.b = ☃.b;
    ☃.c = ☃.c;
    ☃.f = ☃.f;
    ☃.d = ☃.d;
    ☃.c();
    ☃.a("map_" + ☃.i(), ☃);
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    ayz ☃ = a(☃, ☃.l);
    if (☃) {
      if (☃ == null)
      {
        ☃.add("Unknown map");
      }
      else
      {
        ☃.add("Scaling at 1:" + (1 << ☃.f));
        ☃.add("(Level " + ☃.f + "/" + 4 + ")");
      }
    }
  }
}
