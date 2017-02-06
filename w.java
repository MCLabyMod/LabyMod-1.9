import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class w
  extends i
{
  public String c()
  {
    return "deop";
  }
  
  public int a()
  {
    return 3;
  }
  
  public String b(m ☃)
  {
    return "commands.deop.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length != 1) || (☃[0].length() <= 0)) {
      throw new cf("commands.deop.usage", new Object[0]);
    }
    GameProfile ☃ = ☃.al().m().a(☃[0]);
    if (☃ == null) {
      throw new bz("commands.deop.failed", new Object[] { ☃[0] });
    }
    ☃.al().b(☃);
    a(☃, this, "commands.deop.success", new Object[] { ☃[0] });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.al().n());
    }
    return Collections.emptyList();
  }
}
