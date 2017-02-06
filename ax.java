import net.minecraft.server.MinecraftServer;

public class ax
  extends i
{
  public String c()
  {
    return "save-all";
  }
  
  public String b(m ☃)
  {
    return "commands.save.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    ☃.a(new fb("commands.save.start", new Object[0]));
    if (☃.al() != null) {
      ☃.al().j();
    }
    try
    {
      for (int ☃ = 0; ☃ < ☃.d.length; ☃++) {
        if (☃.d[☃] != null)
        {
          lp ☃ = ☃.d[☃];
          boolean ☃ = ☃.b;
          ☃.b = false;
          ☃.a(true, null);
          ☃.b = ☃;
        }
      }
      if ((☃.length > 0) && ("flush".equals(☃[0])))
      {
        ☃.a(new fb("commands.save.flushStart", new Object[0]));
        for (int ☃ = 0; ☃ < ☃.d.length; ☃++) {
          if (☃.d[☃] != null)
          {
            lp ☃ = ☃.d[☃];
            boolean ☃ = ☃.b;
            ☃.b = false;
            ☃.q();
            ☃.b = ☃;
          }
        }
        ☃.a(new fb("commands.save.flushEnd", new Object[0]));
      }
    }
    catch (ahu ☃)
    {
      a(☃, this, "commands.save.failed", new Object[] { ☃.getMessage() });
      return;
    }
    a(☃, this, "commands.save.success", new Object[0]);
  }
}
