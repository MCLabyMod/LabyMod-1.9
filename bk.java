import net.minecraft.server.MinecraftServer;

public class bk
  extends i
{
  public String c()
  {
    return "stop";
  }
  
  public String b(m ☃)
  {
    return "commands.stop.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.d != null) {
      a(☃, this, "commands.stop.start", new Object[0]);
    }
    ☃.x();
  }
}
