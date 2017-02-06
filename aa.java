import java.util.List;
import net.minecraft.server.MinecraftServer;

public class aa
  extends i
{
  public String c()
  {
    return "me";
  }
  
  public int a()
  {
    return 0;
  }
  
  public String b(m ☃)
  {
    return "commands.me.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length <= 0) {
      throw new cf("commands.me.usage", new Object[0]);
    }
    eu ☃ = b(☃, ☃, 0, !(☃ instanceof zj));
    ☃.al().a(new fb("chat.type.emote", new Object[] { ☃.i_(), ☃ }));
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    return a(☃, ☃.J());
  }
}
