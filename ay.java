import net.minecraft.server.MinecraftServer;

public class ay
  extends i
{
  public String c()
  {
    return "save-off";
  }
  
  public String b(m ☃)
  {
    return "commands.save-off.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < ☃.d.length; ☃++) {
      if (☃.d[☃] != null)
      {
        lp ☃ = ☃.d[☃];
        if (!☃.b)
        {
          ☃.b = true;
          ☃ = true;
        }
      }
    }
    if (☃) {
      a(☃, this, "commands.save.disabled", new Object[0]);
    } else {
      throw new bz("commands.save-off.alreadyOff", new Object[0]);
    }
  }
}
