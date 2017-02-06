import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class as
  extends i
{
  public String c()
  {
    return "pardon";
  }
  
  public int a()
  {
    return 3;
  }
  
  public String b(m ☃)
  {
    return "commands.unban.usage";
  }
  
  public boolean a(MinecraftServer ☃, m ☃)
  {
    return (☃.al().h().b()) && (super.a(☃, ☃));
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length != 1) || (☃[0].length() <= 0)) {
      throw new cf("commands.unban.usage", new Object[0]);
    }
    GameProfile ☃ = ☃.al().h().a(☃[0]);
    if (☃ == null) {
      throw new bz("commands.unban.failed", new Object[] { ☃[0] });
    }
    ☃.al().h().c(☃);
    a(☃, this, "commands.unban.success", new Object[] { ☃[0] });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.al().h().a());
    }
    return Collections.emptyList();
  }
}
