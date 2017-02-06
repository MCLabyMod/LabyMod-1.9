import com.google.gson.JsonParseException;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bs
  extends i
{
  private static final Logger a = ;
  
  public String c()
  {
    return "title";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.title.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.title.usage", new Object[0]);
    }
    if (☃.length < 3)
    {
      if (("title".equals(☃[1])) || ("subtitle".equals(☃[1]))) {
        throw new cf("commands.title.usage.title", new Object[0]);
      }
      if ("times".equals(☃[1])) {
        throw new cf("commands.title.usage.times", new Object[0]);
      }
    }
    lr ☃ = a(☃, ☃, ☃[0]);
    hx.a ☃ = hx.a.a(☃[1]);
    if ((☃ == hx.a.d) || (☃ == hx.a.e))
    {
      if (☃.length != 2) {
        throw new cf("commands.title.usage", new Object[0]);
      }
      hx ☃ = new hx(☃, null);
      ☃.a.a(☃);
      a(☃, this, "commands.title.success", new Object[0]);
      return;
    }
    if (☃ == hx.a.c)
    {
      if (☃.length != 5) {
        throw new cf("commands.title.usage", new Object[0]);
      }
      int ☃ = a(☃[2]);
      int ☃ = a(☃[3]);
      int ☃ = a(☃[4]);
      hx ☃ = new hx(☃, ☃, ☃);
      ☃.a.a(☃);
      a(☃, this, "commands.title.success", new Object[0]);
      return;
    }
    if (☃.length < 3) {
      throw new cf("commands.title.usage", new Object[0]);
    }
    String ☃ = a(☃, 2);
    eu ☃;
    try
    {
      ☃ = eu.a.a(☃);
    }
    catch (JsonParseException ☃)
    {
      throw a(☃);
    }
    hx ☃ = new hx(☃, ev.a(☃, ☃, ☃));
    ☃.a.a(☃);
    a(☃, this, "commands.title.success", new Object[0]);
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    if (☃.length == 2) {
      return a(☃, hx.a.a());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
