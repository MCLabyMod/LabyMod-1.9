import net.minecraft.server.MinecraftServer;

public class az
  extends i
{
  public String c()
  {
    return "save-on";
  }
  
  public String b(m ☃)
  {
    return "commands.save-on.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < ☃.d.length; ☃++) {
      if (☃.d[☃] != null)
      {
        lp ☃ = ☃.d[☃];
        if (☃.b)
        {
          ☃.b = false;
          ☃ = true;
        }
      }
    }
    if (☃) {
      a(☃, this, "commands.save.enabled", new Object[0]);
    } else {
      throw new bz("commands.save-on.alreadyOn", new Object[0]);
    }
  }
}
