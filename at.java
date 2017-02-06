import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class at
  extends i
{
  public String c()
  {
    return "particle";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.particle.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 8) {
      throw new cf("commands.particle.usage", new Object[0]);
    }
    boolean ☃ = false;
    cy ☃ = cy.a(☃[0]);
    if (☃ == null) {
      throw new bz("commands.particle.notFound", new Object[] { ☃[0] });
    }
    String ☃ = ☃[0];
    bbj ☃ = ☃.d();
    double ☃ = (float)b(☃.b, ☃[1], true);
    double ☃ = (float)b(☃.c, ☃[2], true);
    double ☃ = (float)b(☃.d, ☃[3], true);
    double ☃ = (float)c(☃[4]);
    double ☃ = (float)c(☃[5]);
    double ☃ = (float)c(☃[6]);
    double ☃ = (float)c(☃[7]);
    
    int ☃ = 0;
    if (☃.length > 8) {
      ☃ = a(☃[8], 0);
    }
    boolean ☃ = false;
    if ((☃.length > 9) && ("force".equals(☃[9]))) {
      ☃ = true;
    }
    lr ☃;
    lr ☃;
    if (☃.length > 10) {
      ☃ = a(☃, ☃, ☃[10]);
    } else {
      ☃ = null;
    }
    int[] ☃ = new int[☃.d()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      if (☃.length > 11 + ☃) {
        try
        {
          ☃[☃] = Integer.parseInt(☃[(11 + ☃)]);
        }
        catch (NumberFormatException ☃)
        {
          throw new bz("commands.particle.invalidParam", new Object[] { ☃[(11 + ☃)] });
        }
      }
    }
    aht ☃ = ☃.e();
    if ((☃ instanceof lp))
    {
      lp ☃ = (lp)☃;
      if (☃ == null) {
        ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
      } else {
        ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
      }
      a(☃, this, "commands.particle.success", new Object[] { ☃, Integer.valueOf(Math.max(☃, 1)) });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, cy.a());
    }
    if ((☃.length > 1) && (☃.length <= 4)) {
      return a(☃, 1, ☃);
    }
    if (☃.length == 10) {
      return a(☃, new String[] { "normal", "force" });
    }
    if (☃.length == 11) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 10;
  }
}
