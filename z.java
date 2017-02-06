import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class z
  extends i
{
  public String c()
  {
    return "effect";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.effect.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.effect.usage", new Object[0]);
    }
    sa ☃ = (sa)a(☃, ☃, ☃[0], sa.class);
    if (☃[1].equals("clear"))
    {
      if (☃.bO().isEmpty()) {
        throw new bz("commands.effect.failure.notActive.all", new Object[] { ☃.h_() });
      }
      ☃.bN();
      a(☃, this, "commands.effect.success.removed.all", new Object[] { ☃.h_() }); return;
    }
    rk ☃;
    try
    {
      ☃ = rk.a(a(☃[1], 1));
    }
    catch (cb ☃)
    {
      ☃ = rk.b(☃[1]);
    }
    if (☃ == null) {
      throw new cb("commands.effect.notFound", new Object[] { ☃[1] });
    }
    int ☃ = 600;
    int ☃ = 30;
    int ☃ = 0;
    if (☃.length >= 3)
    {
      ☃ = a(☃[2], 0, 1000000);
      if (☃.b()) {
        ☃ = ☃;
      } else {
        ☃ = ☃ * 20;
      }
    }
    else if (☃.b())
    {
      ☃ = 1;
    }
    if (☃.length >= 4) {
      ☃ = a(☃[3], 0, 255);
    }
    boolean ☃ = true;
    if ((☃.length >= 5) && 
      ("true".equalsIgnoreCase(☃[4]))) {
      ☃ = false;
    }
    if (☃ > 0)
    {
      rl ☃ = new rl(☃, ☃, ☃, false, ☃);
      ☃.c(☃);
      a(☃, this, "commands.effect.success", new Object[] { new fb(☃.f(), new Object[0]), Integer.valueOf(rk.a(☃)), Integer.valueOf(☃), ☃.h_(), Integer.valueOf(☃) });
      return;
    }
    if (☃.a(☃))
    {
      ☃.d(☃);
      a(☃, this, "commands.effect.success.removed", new Object[] { new fb(☃.a(), new Object[0]), ☃.h_() });
    }
    else
    {
      throw new bz("commands.effect.failure.notActive", new Object[] { new fb(☃.a(), new Object[0]), ☃.h_() });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    if (☃.length == 2) {
      return a(☃, rk.b.c());
    }
    if (☃.length == 5) {
      return a(☃, new String[] { "true", "false" });
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
