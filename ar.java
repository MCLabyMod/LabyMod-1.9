import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.server.MinecraftServer;

public class ar
  extends i
{
  public String c()
  {
    return "pardon-ip";
  }
  
  public int a()
  {
    return 3;
  }
  
  public boolean a(MinecraftServer ☃, m ☃)
  {
    return (☃.al().i().b()) && (super.a(☃, ☃));
  }
  
  public String b(m ☃)
  {
    return "commands.unbanip.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length != 1) || (☃[0].length() <= 1)) {
      throw new cf("commands.unbanip.usage", new Object[0]);
    }
    Matcher ☃ = q.a.matcher(☃[0]);
    if (☃.matches())
    {
      ☃.al().i().c(☃[0]);
      a(☃, this, "commands.unbanip.success", new Object[] { ☃[0] });
    }
    else
    {
      throw new cc("commands.unbanip.invalid", new Object[0]);
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.al().i().a());
    }
    return Collections.emptyList();
  }
}
