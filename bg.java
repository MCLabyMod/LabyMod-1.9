import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bg
  extends i
{
  public String c()
  {
    return "spawnpoint";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.spawnpoint.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length > 1) && (☃.length < 4)) {
      throw new cf("commands.spawnpoint.usage", new Object[0]);
    }
    lr ☃ = ☃.length > 0 ? a(☃, ☃, ☃[0]) : a(☃);
    cj ☃ = ☃.length > 3 ? a(☃, ☃, 1, true) : ☃.c();
    if (☃.l != null)
    {
      ☃.a(☃, true);
      a(☃, this, "commands.spawnpoint.success", new Object[] { ☃.h_(), Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    if ((☃.length > 1) && (☃.length <= 4)) {
      return a(☃, 1, ☃);
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
