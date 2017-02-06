import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class am
  extends i
{
  public String c()
  {
    return "kill";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.kill.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length == 0)
    {
      zj ☃ = a(☃);
      ☃.Q();
      a(☃, this, "commands.kill.successful", new Object[] { ☃.i_() });
      return;
    }
    rr ☃ = b(☃, ☃, ☃[0]);
    ☃.Q();
    a(☃, this, "commands.kill.successful", new Object[] { ☃.i_() });
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
