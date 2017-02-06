import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ba
  extends i
{
  public String c()
  {
    return "say";
  }
  
  public int a()
  {
    return 1;
  }
  
  public String b(m ☃)
  {
    return "commands.say.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length <= 0) || (☃[0].length() <= 0)) {
      throw new cf("commands.say.usage", new Object[0]);
    }
    eu ☃ = b(☃, ☃, 0, true);
    ☃.al().a(new fb("chat.type.announcement", new Object[] { ☃.i_(), ☃ }));
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length >= 1) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
}
