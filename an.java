import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class an
  extends i
{
  public String c()
  {
    return "banlist";
  }
  
  public int a()
  {
    return 3;
  }
  
  public boolean a(MinecraftServer ☃, m ☃)
  {
    return ((☃.al().i().b()) || (☃.al().h().b())) && (super.a(☃, ☃));
  }
  
  public String b(m ☃)
  {
    return "commands.banlist.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length >= 1) && (☃[0].equalsIgnoreCase("ips")))
    {
      ☃.a(new fb("commands.banlist.ips", new Object[] { Integer.valueOf(☃.al().i().a().length) }));
      ☃.a(new fa(a(☃.al().i().a())));
    }
    else
    {
      ☃.a(new fb("commands.banlist.players", new Object[] { Integer.valueOf(☃.al().h().a().length) }));
      ☃.a(new fa(a(☃.al().h().a())));
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "players", "ips" });
    }
    return Collections.emptyList();
  }
}
