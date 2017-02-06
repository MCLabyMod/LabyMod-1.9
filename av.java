import net.minecraft.server.MinecraftServer;

public class av
  extends i
{
  public String c()
  {
    return "publish";
  }
  
  public String b(m ☃)
  {
    return "commands.publish.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    String ☃ = ☃.a(ahw.a.b, false);
    if (☃ != null) {
      a(☃, this, "commands.publish.started", new Object[] { ☃ });
    } else {
      a(☃, this, "commands.publish.failed", new Object[0]);
    }
  }
}
