import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ag
  extends i
{
  public String c()
  {
    return "difficulty";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.difficulty.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length <= 0) {
      throw new cf("commands.difficulty.usage", new Object[0]);
    }
    qk ☃ = e(☃[0]);
    ☃.a(☃);
    
    a(☃, this, "commands.difficulty.success", new Object[] { new fb(☃.b(), new Object[0]) });
  }
  
  protected qk e(String ☃)
    throws cb
  {
    if ((☃.equalsIgnoreCase("peaceful")) || (☃.equalsIgnoreCase("p"))) {
      return qk.a;
    }
    if ((☃.equalsIgnoreCase("easy")) || (☃.equalsIgnoreCase("e"))) {
      return qk.b;
    }
    if ((☃.equalsIgnoreCase("normal")) || (☃.equalsIgnoreCase("n"))) {
      return qk.c;
    }
    if ((☃.equalsIgnoreCase("hard")) || (☃.equalsIgnoreCase("h"))) {
      return qk.d;
    }
    return qk.a(a(☃, 0, 3));
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "peaceful", "easy", "normal", "hard" });
    }
    return Collections.emptyList();
  }
}
