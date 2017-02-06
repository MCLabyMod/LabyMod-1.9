import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class awa$b
  extends awa.d
{
  private static final kk e = new kk("igloo/igloo_top");
  private static final kk f = new kk("igloo/igloo_middle");
  private static final kk g = new kk("igloo/igloo_bottom");
  
  public awa$b() {}
  
  public awa$b(Random ☃, int ☃, int ☃)
  {
    super(☃, ☃, 64, ☃, 7, 5, 8);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (!a(☃, ☃, -1)) {
      return false;
    }
    avp ☃ = c();
    cj ☃ = new cj(☃.a, ☃.b, ☃.c);
    aoe[] ☃ = aoe.values();
    MinecraftServer ☃ = ☃.u();
    awm ☃ = ☃.S().h();
    awn ☃ = new awn(amr.a, ☃[☃.nextInt(☃.length)], false, aju.cv, ☃);
    
    awo ☃ = ☃.a(☃, e);
    ☃.a(☃, ☃, ☃);
    if (☃.nextDouble() < 0.5D)
    {
      awo ☃ = ☃.a(☃, f);
      awo ☃ = ☃.a(☃, g);
      
      int ☃ = ☃.nextInt(8) + 4;
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        cj ☃ = ☃.a(☃, new cj(3, -1 - ☃ * 3, 5), ☃, new cj(1, 2, 1));
        ☃.a(☃, ☃.a(☃), ☃);
      }
      cj ☃ = ☃.a(☃.a(☃, new cj(3, -1 - ☃ * 3, 5), ☃, new cj(3, 5, 7)));
      ☃.a(☃, ☃, ☃);
      
      Map<cj, String> ☃ = ☃.a(☃, ☃);
      for (Map.Entry<cj, String> ☃ : ☃.entrySet()) {
        if ("chest".equals(☃.getValue()))
        {
          cj ☃ = (cj)☃.getKey();
          ☃.a(☃, aju.a.u(), 3);
          apv ☃ = ☃.r(☃.b());
          if ((☃ instanceof apx)) {
            ((apx)☃).a(azt.m, ☃.nextLong());
          }
        }
      }
    }
    else
    {
      cj ☃ = awo.a(☃, new cj(3, 0, 5));
      ☃.a(☃.a(☃), aju.aJ.u(), 3);
    }
    return true;
  }
}
