import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class s
  extends i
{
  public String c()
  {
    return "blockdata";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.blockdata.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 4) {
      throw new cf("commands.blockdata.usage", new Object[0]);
    }
    ☃.a(n.a.b, 0);
    
    cj ☃ = a(☃, ☃, 0, false);
    
    aht ☃ = ☃.e();
    if (!☃.e(☃)) {
      throw new bz("commands.blockdata.outOfWorld", new Object[0]);
    }
    arc ☃ = ☃.o(☃);
    apv ☃ = ☃.r(☃);
    if (☃ == null) {
      throw new bz("commands.blockdata.notValid", new Object[0]);
    }
    dn ☃ = new dn();
    ☃.b(☃);
    dn ☃ = (dn)☃.b();
    dn ☃;
    try
    {
      ☃ = ed.a(a(☃, ☃, 3).c());
    }
    catch (ec ☃)
    {
      throw new bz("commands.blockdata.tagError", new Object[] { ☃.getMessage() });
    }
    ☃.a(☃);
    
    ☃.a("x", ☃.p());
    ☃.a("y", ☃.q());
    ☃.a("z", ☃.r());
    if (☃.equals(☃)) {
      throw new bz("commands.blockdata.failed", new Object[] { ☃.toString() });
    }
    ☃.a(☃);
    ☃.v_();
    ☃.a(☃, ☃, ☃, 3);
    
    ☃.a(n.a.b, 1);
    a(☃, this, "commands.blockdata.success", new Object[] { ☃.toString() });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length > 0) && (☃.length <= 3)) {
      return a(☃, 0, ☃);
    }
    return Collections.emptyList();
  }
}
