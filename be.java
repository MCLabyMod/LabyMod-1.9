import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class be
  extends i
{
  public String c()
  {
    return "setworldspawn";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.setworldspawn.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    cj ☃;
    if (☃.length == 0)
    {
      ☃ = a(☃).c();
    }
    else
    {
      cj ☃;
      if ((☃.length == 3) && (☃.e() != null)) {
        ☃ = a(☃, ☃, 0, true);
      } else {
        throw new cf("commands.setworldspawn.usage", new Object[0]);
      }
    }
    cj ☃;
    ☃.e().A(☃);
    ☃.al().a(new hv(☃));
    a(☃, this, "commands.setworldspawn.success", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length > 0) && (☃.length <= 3)) {
      return a(☃, 0, ☃);
    }
    return Collections.emptyList();
  }
}
