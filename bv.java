import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class bv
  extends i
{
  public String c()
  {
    return "weather";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.weather.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length < 1) || (☃.length > 2)) {
      throw new cf("commands.weather.usage", new Object[0]);
    }
    int ☃ = (300 + new Random().nextInt(600)) * 20;
    if (☃.length >= 2) {
      ☃ = a(☃[1], 1, 1000000) * 20;
    }
    aht ☃ = ☃.d[0];
    azh ☃ = ☃.T();
    if ("clear".equalsIgnoreCase(☃[0]))
    {
      ☃.i(☃);
      ☃.g(0);
      ☃.f(0);
      ☃.b(false);
      ☃.a(false);
      a(☃, this, "commands.weather.clear", new Object[0]);
    }
    else if ("rain".equalsIgnoreCase(☃[0]))
    {
      ☃.i(0);
      ☃.g(☃);
      ☃.f(☃);
      ☃.b(true);
      ☃.a(false);
      a(☃, this, "commands.weather.rain", new Object[0]);
    }
    else if ("thunder".equalsIgnoreCase(☃[0]))
    {
      ☃.i(0);
      ☃.g(☃);
      ☃.f(☃);
      ☃.b(true);
      ☃.a(true);
      a(☃, this, "commands.weather.thunder", new Object[0]);
    }
    else
    {
      throw new cf("commands.weather.usage", new Object[0]);
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "clear", "rain", "thunder" });
    }
    return Collections.emptyList();
  }
}
