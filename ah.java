import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ah
  extends i
{
  public String c()
  {
    return "gamemode";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.gamemode.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length <= 0) {
      throw new cf("commands.gamemode.usage", new Object[0]);
    }
    ahw.a ☃ = c(☃, ☃[0]);
    zj ☃ = ☃.length >= 2 ? a(☃, ☃, ☃[1]) : a(☃);
    
    ☃.a(☃);
    eu ☃ = new fb("gameMode." + ☃.b(), new Object[0]);
    if (☃.e().U().b("sendCommandFeedback")) {
      ☃.a(new fb("gameMode.changed", new Object[] { ☃ }));
    }
    if (☃ != ☃) {
      a(☃, this, 1, "commands.gamemode.success.other", new Object[] { ☃.h_(), ☃ });
    } else {
      a(☃, this, 1, "commands.gamemode.success.self", new Object[] { ☃ });
    }
  }
  
  protected ahw.a c(m ☃, String ☃)
    throws cb
  {
    ahw.a ☃ = ahw.a.a(☃, ahw.a.a);
    return ☃ == ahw.a.a ? ahw.a(a(☃, 0, ahw.a.values().length - 2)) : ☃;
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "survival", "creative", "adventure", "spectator" });
    }
    if (☃.length == 2) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 1;
  }
}
