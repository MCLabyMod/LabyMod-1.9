import java.util.Arrays;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ap
  extends i
{
  public List<String> b()
  {
    return Arrays.asList(new String[] { "w", "msg" });
  }
  
  public String c()
  {
    return "tell";
  }
  
  public int a()
  {
    return 0;
  }
  
  public String b(m ☃)
  {
    return "commands.message.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.message.usage", new Object[0]);
    }
    zj ☃ = a(☃, ☃, ☃[0]);
    if (☃ == ☃) {
      throw new cd("commands.message.sameTarget", new Object[0]);
    }
    eu ☃ = b(☃, ☃, 1, !(☃ instanceof zj));
    fb ☃ = new fb("commands.message.display.incoming", new Object[] { ☃.i_(), ☃.f() });
    fb ☃ = new fb("commands.message.display.outgoing", new Object[] { ☃.i_(), ☃.f() });
    ☃.b().a(a.h).b(Boolean.valueOf(true));
    ☃.b().a(a.h).b(Boolean.valueOf(true));
    ☃.a(☃);
    ☃.a(☃);
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    return a(☃, ☃.J());
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
