import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bu
  extends i
{
  public String c()
  {
    return "trigger";
  }
  
  public int a()
  {
    return 0;
  }
  
  public String b(m ☃)
  {
    return "commands.trigger.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 3) {
      throw new cf("commands.trigger.usage", new Object[0]);
    }
    lr ☃;
    if ((☃ instanceof lr))
    {
      ☃ = (lr)☃;
    }
    else
    {
      rr ☃ = ☃.f();
      lr ☃;
      if ((☃ instanceof lr)) {
        ☃ = (lr)☃;
      } else {
        throw new bz("commands.trigger.invalidPlayer", new Object[0]);
      }
    }
    lr ☃;
    bbp ☃ = ☃.a(0).ad();
    bbl ☃ = ☃.b(☃[0]);
    if ((☃ == null) || (☃.c() != bbv.c)) {
      throw new bz("commands.trigger.invalidObjective", new Object[] { ☃[0] });
    }
    int ☃ = a(☃[2]);
    if (!☃.b(☃.h_(), ☃)) {
      throw new bz("commands.trigger.invalidObjective", new Object[] { ☃[0] });
    }
    bbn ☃ = ☃.c(☃.h_(), ☃);
    if (☃.g()) {
      throw new bz("commands.trigger.disabled", new Object[] { ☃[0] });
    }
    if ("set".equals(☃[1])) {
      ☃.c(☃);
    } else if ("add".equals(☃[1])) {
      ☃.a(☃);
    } else {
      throw new bz("commands.trigger.invalidMode", new Object[] { ☃[1] });
    }
    ☃.a(true);
    if (☃.c.d()) {
      a(☃, this, "commands.trigger.success", new Object[] { ☃[0], ☃[1], ☃[2] });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1)
    {
      bbp ☃ = ☃.a(0).ad();
      List<String> ☃ = Lists.newArrayList();
      for (bbl ☃ : ☃.c()) {
        if (☃.c() == bbv.c) {
          ☃.add(☃.b());
        }
      }
      return a(☃, (String[])☃.toArray(new String[☃.size()]));
    }
    if (☃.length == 2) {
      return a(☃, new String[] { "add", "set" });
    }
    return Collections.emptyList();
  }
}
