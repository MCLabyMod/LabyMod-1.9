import net.minecraft.server.MinecraftServer;

public class bh
  extends i
{
  public boolean a(MinecraftServer ☃, m ☃)
  {
    return (☃.R()) || (super.a(☃, ☃));
  }
  
  public String c()
  {
    return "seed";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.seed.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    aht ☃ = (☃ instanceof zj) ? ((zj)☃).l : ☃.a(0);
    ☃.a(new fb("commands.seed.success", new Object[] { Long.valueOf(☃.O()) }));
  }
}
