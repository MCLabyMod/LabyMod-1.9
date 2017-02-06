import net.minecraft.server.MinecraftServer;

public class bt
  extends i
{
  public String c()
  {
    return "toggledownfall";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.downfall.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    a(☃);
    a(☃, this, "commands.downfall.success", new Object[0]);
  }
  
  protected void a(MinecraftServer ☃)
  {
    azh ☃ = ☃.d[0].T();
    
    ☃.b(!☃.o());
  }
}
