import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.server.MinecraftServer;

public class q
  extends i
{
  public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
  
  public String c()
  {
    return "ban-ip";
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
    return "commands.banip.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length < 1) || (☃[0].length() <= 1)) {
      throw new cf("commands.banip.usage", new Object[0]);
    }
    eu ☃ = ☃.length >= 2 ? a(☃, ☃, 1) : null;
    
    Matcher ☃ = a.matcher(☃[0]);
    if (☃.matches())
    {
      a(☃, ☃, ☃[0], ☃ == null ? null : ☃.c());
    }
    else
    {
      lr ☃ = ☃.al().a(☃[0]);
      if (☃ == null) {
        throw new cd("commands.banip.invalid", new Object[0]);
      }
      a(☃, ☃, ☃.A(), ☃ == null ? null : ☃.c());
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  protected void a(MinecraftServer ☃, m ☃, String ☃, String ☃)
  {
    mk ☃ = new mk(☃, null, ☃.h_(), null, ☃);
    ☃.al().i().a(☃);
    
    List<lr> ☃ = ☃.al().b(☃);
    String[] ☃ = new String[☃.size()];
    int ☃ = 0;
    for (lr ☃ : ☃)
    {
      ☃.a.c("You have been IP banned.");
      ☃[(☃++)] = ☃.h_();
    }
    if (☃.isEmpty()) {
      a(☃, this, "commands.banip.success", new Object[] { ☃ });
    } else {
      a(☃, this, "commands.banip.success.players", new Object[] { ☃, a(☃) });
    }
  }
}
