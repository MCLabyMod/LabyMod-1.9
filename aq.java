import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class aq
  extends i
{
  public String c()
  {
    return "op";
  }
  
  public int a()
  {
    return 3;
  }
  
  public String b(m ☃)
  {
    return "commands.op.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if ((☃.length != 1) || (☃[0].length() <= 0)) {
      throw new cf("commands.op.usage", new Object[0]);
    }
    GameProfile ☃ = ☃.aA().a(☃[0]);
    if (☃ == null) {
      throw new bz("commands.op.failed", new Object[] { ☃[0] });
    }
    ☃.al().a(☃);
    a(☃, this, "commands.op.success", new Object[] { ☃[0] });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1)
    {
      String ☃ = ☃[(☃.length - 1)];
      List<String> ☃ = Lists.newArrayList();
      for (GameProfile ☃ : ☃.K()) {
        if ((!☃.al().h(☃)) && (a(☃, ☃.getName()))) {
          ☃.add(☃.getName());
        }
      }
      return ☃;
    }
    return Collections.emptyList();
  }
}
