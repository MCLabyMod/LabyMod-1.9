import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class r
  extends i
{
  public String c()
  {
    return "ban";
  }
  
  public int a()
  {
    return 3;
  }
  
  public String b(m ☃)
  {
    return "commands.ban.usage";
  }
  
  public boolean a(MinecraftServer ☃, m ☃)
  {
    return (☃.al().h().b()) && (super.a(☃, ☃));
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length < 1) || (☃[0].length() <= 0)) {
      throw new cf("commands.ban.usage", new Object[0]);
    }
    GameProfile ☃ = ☃.aA().a(☃[0]);
    if (☃ == null) {
      throw new bz("commands.ban.failed", new Object[] { ☃[0] });
    }
    String ☃ = null;
    if (☃.length >= 2) {
      ☃ = a(☃, ☃, 1).c();
    }
    ms ☃ = new ms(☃, null, ☃.h_(), null, ☃);
    ☃.al().h().a(☃);
    
    lr ☃ = ☃.al().a(☃[0]);
    if (☃ != null) {
      ☃.a.c("You are banned from this server.");
    }
    a(☃, this, "commands.ban.success", new Object[] { ☃[0] });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length >= 1) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
}
