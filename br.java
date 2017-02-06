import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class br
  extends i
{
  public String c()
  {
    return "time";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.time.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length > 1)
    {
      if (☃[0].equals("set"))
      {
        int ☃;
        int ☃;
        if (☃[1].equals("day"))
        {
          ☃ = 1000;
        }
        else
        {
          int ☃;
          if (☃[1].equals("night")) {
            ☃ = 13000;
          } else {
            ☃ = a(☃[1], 0);
          }
        }
        a(☃, ☃);
        a(☃, this, "commands.time.set", new Object[] { Integer.valueOf(☃) });
        return;
      }
      if (☃[0].equals("add"))
      {
        int ☃ = a(☃[1], 0);
        b(☃, ☃);
        a(☃, this, "commands.time.added", new Object[] { Integer.valueOf(☃) });
        return;
      }
      if (☃[0].equals("query"))
      {
        if (☃[1].equals("daytime"))
        {
          int ☃ = (int)(☃.e().Q() % 24000L);
          ☃.a(n.a.e, ☃);
          a(☃, this, "commands.time.query", new Object[] { Integer.valueOf(☃) });
          return;
        }
        if (☃[1].equals("day"))
        {
          int ☃ = (int)(☃.e().Q() / 24000L % 2147483647L);
          ☃.a(n.a.e, ☃);
          a(☃, this, "commands.time.query", new Object[] { Integer.valueOf(☃) });
          return;
        }
        if (☃[1].equals("gametime"))
        {
          int ☃ = (int)(☃.e().P() % 2147483647L);
          ☃.a(n.a.e, ☃);
          a(☃, this, "commands.time.query", new Object[] { Integer.valueOf(☃) });
          return;
        }
      }
    }
    throw new cf("commands.time.usage", new Object[0]);
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "set", "add", "query" });
    }
    if ((☃.length == 2) && (☃[0].equals("set"))) {
      return a(☃, new String[] { "day", "night" });
    }
    if ((☃.length == 2) && (☃[0].equals("query"))) {
      return a(☃, new String[] { "daytime", "gametime", "day" });
    }
    return Collections.emptyList();
  }
  
  protected void a(MinecraftServer ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < ☃.d.length; ☃++) {
      ☃.d[☃].b(☃);
    }
  }
  
  protected void b(MinecraftServer ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < ☃.d.length; ☃++)
    {
      lp ☃ = ☃.d[☃];
      ☃.b(☃.Q() + ☃);
    }
  }
}
