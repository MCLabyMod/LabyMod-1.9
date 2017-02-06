import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bw
  extends i
{
  public String c()
  {
    return "whitelist";
  }
  
  public int a()
  {
    return 3;
  }
  
  public String b(m ☃)
  {
    return "commands.whitelist.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.whitelist.usage", new Object[0]);
    }
    if (☃[0].equals("on"))
    {
      ☃.al().a(true);
      a(☃, this, "commands.whitelist.enabled", new Object[0]);
    }
    else if (☃[0].equals("off"))
    {
      ☃.al().a(false);
      a(☃, this, "commands.whitelist.disabled", new Object[0]);
    }
    else if (☃[0].equals("list"))
    {
      ☃.a(new fb("commands.whitelist.list", new Object[] { Integer.valueOf(☃.al().l().length), Integer.valueOf(☃.al().q().length) }));
      String[] ☃ = ☃.al().l();
      ☃.a(new fa(a(☃)));
    }
    else if (☃[0].equals("add"))
    {
      if (☃.length < 2) {
        throw new cf("commands.whitelist.add.usage", new Object[0]);
      }
      GameProfile ☃ = ☃.aA().a(☃[1]);
      if (☃ == null) {
        throw new bz("commands.whitelist.add.failed", new Object[] { ☃[1] });
      }
      ☃.al().d(☃);
      a(☃, this, "commands.whitelist.add.success", new Object[] { ☃[1] });
    }
    else if (☃[0].equals("remove"))
    {
      if (☃.length < 2) {
        throw new cf("commands.whitelist.remove.usage", new Object[0]);
      }
      GameProfile ☃ = ☃.al().k().a(☃[1]);
      if (☃ == null) {
        throw new bz("commands.whitelist.remove.failed", new Object[] { ☃[1] });
      }
      ☃.al().c(☃);
      a(☃, this, "commands.whitelist.remove.success", new Object[] { ☃[1] });
    }
    else if (☃[0].equals("reload"))
    {
      ☃.al().a();
      a(☃, this, "commands.whitelist.reloaded", new Object[0]);
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "on", "off", "list", "add", "remove", "reload" });
    }
    if (☃.length == 2)
    {
      if (☃[0].equals("remove")) {
        return a(☃, ☃.al().l());
      }
      if (☃[0].equals("add")) {
        return a(☃, ☃.aA().a());
      }
    }
    return Collections.emptyList();
  }
}
