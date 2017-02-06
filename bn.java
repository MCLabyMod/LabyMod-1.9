import com.google.gson.JsonParseException;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bn
  extends i
{
  public String c()
  {
    return "tellraw";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.tellraw.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.tellraw.usage", new Object[0]);
    }
    zj ☃ = a(☃, ☃, ☃[0]);
    String ☃ = a(☃, 1);
    try
    {
      eu ☃ = eu.a.a(☃);
      ☃.a(ev.a(☃, ☃, ☃));
    }
    catch (JsonParseException ☃)
    {
      throw a(☃);
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
