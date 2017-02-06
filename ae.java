import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ae
  extends i
{
  public String c()
  {
    return "xp";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.xp.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length <= 0) {
      throw new cf("commands.xp.usage", new Object[0]);
    }
    String ☃ = ☃[0];
    boolean ☃ = (☃.endsWith("l")) || (☃.endsWith("L"));
    if ((☃) && (☃.length() > 1)) {
      ☃ = ☃.substring(0, ☃.length() - 1);
    }
    int ☃ = a(☃);
    
    boolean ☃ = ☃ < 0;
    if (☃) {
      ☃ *= -1;
    }
    zj ☃ = ☃.length > 1 ? a(☃, ☃, ☃[1]) : a(☃);
    if (☃)
    {
      ☃.a(n.a.e, ☃.bK);
      if (☃)
      {
        ☃.a(-☃);
        a(☃, this, "commands.xp.success.negative.levels", new Object[] { Integer.valueOf(☃), ☃.h_() });
      }
      else
      {
        ☃.a(☃);
        a(☃, this, "commands.xp.success.levels", new Object[] { Integer.valueOf(☃), ☃.h_() });
      }
    }
    else
    {
      ☃.a(n.a.e, ☃.bL);
      if (☃) {
        throw new bz("commands.xp.failure.widthdrawXp", new Object[0]);
      }
      ☃.n(☃);
      a(☃, this, "commands.xp.success", new Object[] { Integer.valueOf(☃), ☃.h_() });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 2) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 1;
  }
}
