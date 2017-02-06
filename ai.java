import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ai
  extends i
{
  public String c()
  {
    return "gamerule";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.gamerule.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    ahr ☃ = a(☃);
    String ☃ = ☃.length > 0 ? ☃[0] : "";
    String ☃ = ☃.length > 1 ? a(☃, 1) : "";
    switch (☃.length)
    {
    case 1: 
      if (☃.e(☃))
      {
        String ☃ = ☃.a(☃);
        ☃.a(new fa(☃).a(" = ").a(☃));
        ☃.a(n.a.e, ☃.c(☃));
      }
      else
      {
        throw new bz("commands.gamerule.norule", new Object[] { ☃ });
      }
      break;
    case 0: 
      ☃.a(new fa(a(☃.b())));
      break;
    default: 
      if ((☃.a(☃, ahr.b.b)) && (!"true".equals(☃)) && (!"false".equals(☃))) {
        throw new bz("commands.generic.boolean.invalid", new Object[] { ☃ });
      }
      ☃.a(☃, ☃);
      a(☃, ☃, ☃);
      a(☃, this, "commands.gamerule.success", new Object[] { ☃, ☃ });
    }
  }
  
  public static void a(ahr ☃, String ☃, MinecraftServer ☃)
  {
    byte ☃;
    if ("reducedDebugInfo".equals(☃))
    {
      ☃ = ☃.b(☃) ? 22 : 23;
      for (lr ☃ : ☃.al().v()) {
        ☃.a.a(new gk(☃, ☃));
      }
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, a(☃).b());
    }
    if (☃.length == 2)
    {
      ahr ☃ = a(☃);
      if (☃.a(☃[0], ahr.b.b)) {
        return a(☃, new String[] { "true", "false" });
      }
    }
    return Collections.emptyList();
  }
  
  private ahr a(MinecraftServer ☃)
  {
    return ☃.a(0).U();
  }
}
