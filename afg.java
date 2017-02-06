import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class afg
{
  public static List<rl> a(adq ☃)
  {
    return a(☃.o());
  }
  
  public static List<rl> a(afe ☃, Collection<rl> ☃)
  {
    List<rl> ☃ = Lists.newArrayList();
    
    ☃.addAll(☃.a());
    ☃.addAll(☃);
    
    return ☃;
  }
  
  public static List<rl> a(dn ☃)
  {
    List<rl> ☃ = Lists.newArrayList();
    
    ☃.addAll(c(☃).a());
    a(☃, ☃);
    
    return ☃;
  }
  
  public static List<rl> b(adq ☃)
  {
    return b(☃.o());
  }
  
  public static List<rl> b(dn ☃)
  {
    List<rl> ☃ = Lists.newArrayList();
    a(☃, ☃);
    return ☃;
  }
  
  public static void a(dn ☃, List<rl> ☃)
  {
    if ((☃ != null) && (☃.b("CustomPotionEffects", 9)))
    {
      du ☃ = ☃.c("CustomPotionEffects", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        rl ☃ = rl.b(☃);
        if (☃ != null) {
          ☃.add(☃);
        }
      }
    }
  }
  
  public static int a(afe ☃)
  {
    return a(☃.a());
  }
  
  public static int a(Collection<rl> ☃)
  {
    int ☃ = 3694022;
    if (☃.isEmpty()) {
      return 3694022;
    }
    float ☃ = 0.0F;
    float ☃ = 0.0F;
    float ☃ = 0.0F;
    int ☃ = 0;
    for (rl ☃ : ☃) {
      if (☃.e())
      {
        int ☃ = ☃.a().g();
        int ☃ = ☃.c() + 1;
        ☃ += ☃ * (☃ >> 16 & 0xFF) / 255.0F;
        ☃ += ☃ * (☃ >> 8 & 0xFF) / 255.0F;
        ☃ += ☃ * (☃ >> 0 & 0xFF) / 255.0F;
        ☃ += ☃;
      }
    }
    if (☃ == 0) {
      return 0;
    }
    ☃ = ☃ / ☃ * 255.0F;
    ☃ = ☃ / ☃ * 255.0F;
    ☃ = ☃ / ☃ * 255.0F;
    
    return (int)☃ << 16 | (int)☃ << 8 | (int)☃;
  }
  
  public static afe c(adq ☃)
  {
    return c(☃.o());
  }
  
  public static afe c(dn ☃)
  {
    if (☃ == null) {
      return afh.b;
    }
    return afe.a(☃.l("Potion"));
  }
  
  public static adq a(adq ☃, afe ☃)
  {
    kk ☃ = (kk)afe.a.b(☃);
    if (☃ != null)
    {
      dn ☃ = ☃.n() ? ☃.o() : new dn();
      ☃.a("Potion", ☃.toString());
      ☃.d(☃);
    }
    return ☃;
  }
  
  public static adq a(adq ☃, Collection<rl> ☃)
  {
    if (☃.isEmpty()) {
      return ☃;
    }
    dn ☃ = (dn)Objects.firstNonNull(☃.o(), new dn());
    du ☃ = ☃.c("CustomPotionEffects", 9);
    for (rl ☃ : ☃) {
      ☃.a(☃.a(new dn()));
    }
    ☃.a("CustomPotionEffects", ☃);
    ☃.d(☃);
    
    return ☃;
  }
  
  public static void a(adq ☃, List<String> ☃, float ☃)
  {
    List<rl> ☃ = a(☃);
    List<ou<String, sn>> ☃ = Lists.newArrayList();
    if (☃.isEmpty())
    {
      String ☃ = di.a("effect.none").trim();
      ☃.add(a.h + ☃);
    }
    else
    {
      for (rl ☃ : ☃)
      {
        String ☃ = di.a(☃.f()).trim();
        rk ☃ = ☃.a();
        
        Map<sl, sn> ☃ = ☃.h();
        if (!☃.isEmpty()) {
          for (Map.Entry<sl, sn> ☃ : ☃.entrySet())
          {
            sn ☃ = (sn)☃.getValue();
            sn ☃ = new sn(☃.b(), ☃.a(☃.c(), ☃), ☃.c());
            ☃.add(new ou(((sl)☃.getKey()).a(), ☃));
          }
        }
        if (☃.c() > 0) {
          ☃ = ☃ + " " + di.a(new StringBuilder().append("potion.potency.").append(☃.c()).toString()).trim();
        }
        if (☃.b() > 20) {
          ☃ = ☃ + " (" + rk.a(☃, ☃) + ")";
        }
        if (☃.e()) {
          ☃.add(a.m + ☃);
        } else {
          ☃.add(a.j + ☃);
        }
      }
    }
    if (!☃.isEmpty())
    {
      ☃.add("");
      ☃.add(a.f + di.a("potion.whenDrank"));
      for (ou<String, sn> ☃ : ☃)
      {
        sn ☃ = (sn)☃.b();
        double ☃ = ☃.d();
        double ☃;
        double ☃;
        if ((☃.c() == 1) || (☃.c() == 2)) {
          ☃ = ☃.d() * 100.0D;
        } else {
          ☃ = ☃.d();
        }
        if (☃ > 0.0D)
        {
          ☃.add(a.j + di.a(new StringBuilder().append("attribute.modifier.plus.").append(☃.c()).toString(), new Object[] { adq.a.format(☃), di.a("attribute.name." + (String)☃.a()) }));
        }
        else if (☃ < 0.0D)
        {
          ☃ *= -1.0D;
          ☃.add(a.m + di.a(new StringBuilder().append("attribute.modifier.take.").append(☃.c()).toString(), new Object[] { adq.a.format(☃), di.a("attribute.name." + (String)☃.a()) }));
        }
      }
    }
  }
}
