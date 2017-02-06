import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bq
  extends i
{
  public String c()
  {
    return "testfor";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.testfor.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.testfor.usage", new Object[0]);
    }
    rr ☃ = b(☃, ☃, ☃[0]);
    dn ☃ = null;
    if (☃.length >= 2) {
      try
      {
        ☃ = ed.a(a(☃, 1));
      }
      catch (ec ☃)
      {
        throw new bz("commands.testfor.tagError", new Object[] { ☃.getMessage() });
      }
    }
    if (☃ != null)
    {
      dn ☃ = a(☃);
      if (!dy.a(☃, ☃, true)) {
        throw new bz("commands.testfor.failure", new Object[] { ☃.h_() });
      }
    }
    a(☃, this, "commands.testfor.success", new Object[] { ☃.h_() });
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
}
