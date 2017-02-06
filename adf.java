import java.util.List;

public class adf
  extends ado
{
  public static eb a(adq ☃, String ☃)
  {
    if (☃.n())
    {
      dn ☃ = ☃.o().o("Explosion");
      if (☃ != null) {
        return ☃.c(☃);
      }
    }
    return null;
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    if (☃.n())
    {
      dn ☃ = ☃.o().o("Explosion");
      if (☃ != null) {
        a(☃, ☃);
      }
    }
  }
  
  public static void a(dn ☃, List<String> ☃)
  {
    byte ☃ = ☃.f("Type");
    if ((☃ >= 0) && (☃ <= 4)) {
      ☃.add(di.a("item.fireworksCharge.type." + ☃).trim());
    } else {
      ☃.add(di.a("item.fireworksCharge.type").trim());
    }
    int[] ☃ = ☃.n("Colors");
    if (☃.length > 0)
    {
      boolean ☃ = true;
      String ☃ = "";
      for (int ☃ : ☃)
      {
        if (!☃) {
          ☃ = ☃ + ", ";
        }
        ☃ = false;
        
        boolean ☃ = false;
        for (int ☃ = 0; ☃ < acu.a.length; ☃++) {
          if (☃ == acu.a[☃])
          {
            ☃ = true;
            ☃ = ☃ + di.a(new StringBuilder().append("item.fireworksCharge.").append(act.a(☃).d()).toString());
            break;
          }
        }
        if (!☃) {
          ☃ = ☃ + di.a("item.fireworksCharge.customColor");
        }
      }
      ☃.add(☃);
    }
    int[] ☃ = ☃.n("FadeColors");
    if (☃.length > 0)
    {
      boolean ☃ = true;
      String ☃ = di.a("item.fireworksCharge.fadeTo") + " ";
      for (int ☃ : ☃)
      {
        if (!☃) {
          ☃ = ☃ + ", ";
        }
        ☃ = false;
        
        boolean ☃ = false;
        for (int ☃ = 0; ☃ < 16; ☃++) {
          if (☃ == acu.a[☃])
          {
            ☃ = true;
            ☃ = ☃ + di.a(new StringBuilder().append("item.fireworksCharge.").append(act.a(☃).d()).toString());
            break;
          }
        }
        if (!☃) {
          ☃ = ☃ + di.a("item.fireworksCharge.customColor");
        }
      }
      ☃.add(☃);
    }
    boolean ☃ = ☃.p("Trail");
    if (☃) {
      ☃.add(di.a("item.fireworksCharge.trail"));
    }
    boolean ☃ = ☃.p("Flicker");
    if (☃) {
      ☃.add(di.a("item.fireworksCharge.flicker"));
    }
  }
}
