import com.google.common.collect.Lists;
import java.util.List;

public class adg
  extends ado
{
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.E)
    {
      zq ☃ = new zq(☃, ☃.p() + ☃, ☃.q() + ☃, ☃.r() + ☃, ☃);
      ☃.a(☃);
      if (!☃.bJ.d) {
        ☃.b -= 1;
      }
    }
    return qo.a;
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    if (!☃.n()) {
      return;
    }
    dn ☃ = ☃.o().o("Fireworks");
    if (☃ == null) {
      return;
    }
    if (☃.b("Flight", 99)) {
      ☃.add(di.a("item.fireworks.flight") + " " + ☃.f("Flight"));
    }
    du ☃ = ☃.c("Explosions", 10);
    if ((☃ != null) && (!☃.c_())) {
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        
        List<String> ☃ = Lists.newArrayList();
        adf.a(☃, ☃);
        if (!☃.isEmpty())
        {
          for (int ☃ = 1; ☃ < ☃.size(); ☃++) {
            ☃.set(☃, "  " + (String)☃.get(☃));
          }
          ☃.addAll(☃);
        }
      }
    }
  }
}
