import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class ak
  extends i
{
  private static String[] a = { "Yolo", "/achievement take achievement.understandCommands @p", "Ask for help on twitter", "/deop @p", "Scoreboard deleted, commands blocked", "Contact helpdesk for help", "/testfornoob @p", "/trigger warning", "Oh my god, it's full of stats", "/kill @p[name=!Searge]", "Have you tried turning it off and on again?", "Sorry, no help today" };
  private Random b = new Random();
  
  public String c()
  {
    return "help";
  }
  
  public int a()
  {
    return 0;
  }
  
  public String b(m ☃)
  {
    return "commands.help.usage";
  }
  
  public List<String> b()
  {
    return Arrays.asList(new String[] { "?" });
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃ instanceof ahj))
    {
      ☃.a(new fa("Searge says: ").a(a[(this.b.nextInt(a.length) % a.length)]));
      return;
    }
    List<k> ☃ = a(☃, ☃);
    int ☃ = 7;
    int ☃ = (☃.size() - 1) / 7;
    int ☃ = 0;
    try
    {
      ☃ = ☃.length == 0 ? 0 : a(☃[0], 1, ☃ + 1) - 1;
    }
    catch (cb ☃)
    {
      Map<String, k> ☃ = a(☃);
      k ☃ = (k)☃.get(☃[0]);
      if (☃ != null) {
        throw new cf(☃.b(☃), new Object[0]);
      }
      if (on.a(☃[0], -1) != -1) {
        throw ☃;
      }
      throw new ce();
    }
    int ☃ = Math.min((☃ + 1) * 7, ☃.size());
    
    fb ☃ = new fb("commands.help.header", new Object[] { Integer.valueOf(☃ + 1), Integer.valueOf(☃ + 1) });
    ☃.b().a(a.c);
    ☃.a(☃);
    for (int ☃ = ☃ * 7; ☃ < ☃; ☃++)
    {
      k ☃ = (k)☃.get(☃);
      
      fb ☃ = new fb(☃.b(☃), new Object[0]);
      ☃.b().a(new et(et.a.d, "/" + ☃.c() + " "));
      ☃.a(☃);
    }
    if ((☃ == 0) && ((☃ instanceof zj)))
    {
      fb ☃ = new fb("commands.help.footer", new Object[0]);
      ☃.b().a(a.k);
      ☃.a(☃);
    }
  }
  
  protected List<k> a(m ☃, MinecraftServer ☃)
  {
    List<k> ☃ = ☃.N().a(☃);
    Collections.sort(☃);
    return ☃;
  }
  
  protected Map<String, k> a(MinecraftServer ☃)
  {
    return ☃.N().b();
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1)
    {
      Set<String> ☃ = a(☃).keySet();
      return a(☃, (String[])☃.toArray(new String[☃.size()]));
    }
    return Collections.emptyList();
  }
}
