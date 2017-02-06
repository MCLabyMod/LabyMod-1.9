import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public class ac
  extends i
{
  public String c()
  {
    return "entitydata";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.entitydata.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.entitydata.usage", new Object[0]);
    }
    rr ☃ = b(☃, ☃, ☃[0]);
    if ((☃ instanceof zj)) {
      throw new bz("commands.entitydata.noPlayers", new Object[] { ☃.i_() });
    }
    dn ☃ = a(☃);
    dn ☃ = (dn)☃.b();
    dn ☃;
    try
    {
      ☃ = ed.a(a(☃, ☃, 1).c());
    }
    catch (ec ☃)
    {
      throw new bz("commands.entitydata.tagError", new Object[] { ☃.getMessage() });
    }
    UUID ☃ = ☃.bc();
    ☃.a(☃);
    ☃.a(☃);
    if (☃.equals(☃)) {
      throw new bz("commands.entitydata.failed", new Object[] { ☃.toString() });
    }
    ☃.f(☃);
    
    a(☃, this, "commands.entitydata.success", new Object[] { ☃.toString() });
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
