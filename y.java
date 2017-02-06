import net.minecraft.server.MinecraftServer;

public class y
  extends ah
{
  public String c()
  {
    return "defaultgamemode";
  }
  
  public String b(m ☃)
  {
    return "commands.defaultgamemode.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length <= 0) {
      throw new cf("commands.defaultgamemode.usage", new Object[0]);
    }
    ahw.a ☃ = c(☃, ☃[0]);
    a(☃, ☃);
    
    a(☃, this, "commands.defaultgamemode.success", new Object[] { new fb("gameMode." + ☃.b(), new Object[0]) });
  }
  
  protected void a(ahw.a ☃, MinecraftServer ☃)
  {
    ☃.a(☃);
    if (☃.at()) {
      for (lr ☃ : ☃.al().v()) {
        ☃.a(☃);
      }
    }
  }
}
