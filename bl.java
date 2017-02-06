import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bl
  extends i
{
  public String c()
  {
    return "summon";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.summon.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.summon.usage", new Object[0]);
    }
    String ☃ = ☃[0];
    cj ☃ = ☃.c();
    bbj ☃ = ☃.d();
    
    double ☃ = ☃.b;
    double ☃ = ☃.c;
    double ☃ = ☃.d;
    if (☃.length >= 4)
    {
      ☃ = b(☃, ☃[1], true);
      ☃ = b(☃, ☃[2], false);
      ☃ = b(☃, ☃[3], true);
      ☃ = new cj(☃, ☃, ☃);
    }
    aht ☃ = ☃.e();
    if (!☃.e(☃)) {
      throw new bz("commands.summon.outOfWorld", new Object[0]);
    }
    if ("LightningBolt".equals(☃))
    {
      ☃.d(new ya(☃, ☃, ☃, ☃, false));
      a(☃, this, "commands.summon.success", new Object[0]);
      return;
    }
    dn ☃ = new dn();
    boolean ☃ = false;
    if (☃.length >= 5)
    {
      eu ☃ = a(☃, ☃, 4);
      try
      {
        ☃ = ed.a(☃.c());
        ☃ = true;
      }
      catch (ec ☃)
      {
        throw new bz("commands.summon.tagError", new Object[] { ☃.getMessage() });
      }
    }
    ☃.a("id", ☃);
    
    rr ☃ = ass.a(☃, ☃, ☃, ☃, ☃, true);
    if (☃ == null) {
      throw new bz("commands.summon.failed", new Object[0]);
    }
    ☃.b(☃, ☃, ☃, ☃.v, ☃.w);
    if (!☃) {
      if ((☃ instanceof sb)) {
        ((sb)☃).a(☃.D(new cj(☃)), null);
      }
    }
    a(☃, this, "commands.summon.success", new Object[0]);
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, rt.b());
    }
    if ((☃.length > 1) && (☃.length <= 4)) {
      return a(☃, 1, ☃);
    }
    return Collections.emptyList();
  }
}
