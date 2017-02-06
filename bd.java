import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bd
  extends i
{
  public String c()
  {
    return "setblock";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.setblock.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 4) {
      throw new cf("commands.setblock.usage", new Object[0]);
    }
    ☃.a(n.a.b, 0);
    
    cj ☃ = a(☃, ☃, 0, false);
    ajt ☃ = i.b(☃, ☃[3]);
    
    int ☃ = 0;
    if (☃.length >= 5) {
      ☃ = a(☃[4], 0, 15);
    }
    aht ☃ = ☃.e();
    if (!☃.e(☃)) {
      throw new bz("commands.setblock.outOfWorld", new Object[0]);
    }
    dn ☃ = new dn();
    boolean ☃ = false;
    if ((☃.length >= 7) && (☃.m()))
    {
      String ☃ = a(☃, ☃, 6).c();
      try
      {
        ☃ = ed.a(☃);
        ☃ = true;
      }
      catch (ec ☃)
      {
        throw new bz("commands.setblock.tagError", new Object[] { ☃.getMessage() });
      }
    }
    if (☃.length >= 6) {
      if (☃[5].equals("destroy"))
      {
        ☃.b(☃, true);
        if (☃ == aju.a) {
          a(☃, this, "commands.setblock.success", new Object[0]);
        }
      }
      else if ((☃[5].equals("keep")) && 
        (!☃.d(☃)))
      {
        throw new bz("commands.setblock.noChange", new Object[0]);
      }
    }
    apv ☃ = ☃.r(☃);
    if (☃ != null)
    {
      if ((☃ instanceof qg)) {
        ((qg)☃).l();
      }
      ☃.a(☃, aju.a.u(), ☃ == aju.a ? 2 : 4);
    }
    arc ☃ = ☃.a(☃);
    if (!☃.a(☃, ☃, 2)) {
      throw new bz("commands.setblock.noChange", new Object[0]);
    }
    if (☃)
    {
      apv ☃ = ☃.r(☃);
      if (☃ != null)
      {
        ☃.a("x", ☃.p());
        ☃.a("y", ☃.q());
        ☃.a("z", ☃.r());
        
        ☃.a(☃);
      }
    }
    ☃.c(☃, ☃.t());
    ☃.a(n.a.b, 1);
    a(☃, this, "commands.setblock.success", new Object[0]);
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length > 0) && (☃.length <= 3)) {
      return a(☃, 0, ☃);
    }
    if (☃.length == 4) {
      return a(☃, ajt.h.c());
    }
    if (☃.length == 6) {
      return a(☃, new String[] { "replace", "destroy", "keep" });
    }
    return Collections.emptyList();
  }
}
