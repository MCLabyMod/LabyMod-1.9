import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ab
  extends i
{
  public String c()
  {
    return "enchant";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.enchant.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.enchant.usage", new Object[0]);
    }
    sa ☃ = (sa)a(☃, ☃, ☃[0], sa.class);
    ☃.a(n.a.d, 0);
    agm ☃;
    try
    {
      ☃ = agm.c(a(☃[1], 0));
    }
    catch (cb ☃)
    {
      ☃ = agm.b(☃[1]);
    }
    if (☃ == null) {
      throw new cb("commands.enchant.notFound", new Object[] { Integer.valueOf(agm.b(☃)) });
    }
    int ☃ = 1;
    
    adq ☃ = ☃.cb();
    if (☃ == null) {
      throw new bz("commands.enchant.noItem", new Object[0]);
    }
    if (!☃.a(☃)) {
      throw new bz("commands.enchant.cantEnchant", new Object[0]);
    }
    if (☃.length >= 3) {
      ☃ = a(☃[2], ☃.d(), ☃.b());
    }
    if (☃.n())
    {
      du ☃ = ☃.p();
      if (☃ != null) {
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          int ☃ = ☃.b(☃).g("id");
          if (agm.c(☃) != null)
          {
            agm ☃ = agm.c(☃);
            if (!☃.a(☃)) {
              throw new bz("commands.enchant.cantCombine", new Object[] { ☃.d(☃), ☃.d(☃.b(☃).g("lvl")) });
            }
          }
        }
      }
    }
    ☃.a(☃, ☃);
    a(☃, this, "commands.enchant.success", new Object[0]);
    ☃.a(n.a.d, 1);
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    if (☃.length == 2) {
      return a(☃, agm.b.c());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
