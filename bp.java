import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bp
  extends i
{
  public String c()
  {
    return "testforblock";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.testforblock.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 4) {
      throw new cf("commands.testforblock.usage", new Object[0]);
    }
    ☃.a(n.a.b, 0);
    
    cj ☃ = a(☃, ☃, 0, false);
    ajt ☃ = ajt.b(☃[3]);
    if (☃ == null) {
      throw new cb("commands.setblock.notFound", new Object[] { ☃[3] });
    }
    int ☃ = -1;
    if (☃.length >= 5) {
      ☃ = a(☃[4], -1, 15);
    }
    aht ☃ = ☃.e();
    if (!☃.e(☃)) {
      throw new bz("commands.testforblock.outOfWorld", new Object[0]);
    }
    dn ☃ = new dn();
    boolean ☃ = false;
    if ((☃.length >= 6) && (☃.m()))
    {
      String ☃ = a(☃, ☃, 5).c();
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
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (☃ != ☃) {
      throw new bz("commands.testforblock.failed.tile", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()), ☃.c(), ☃.c() });
    }
    if (☃ > -1)
    {
      int ☃ = ☃.t().e(☃);
      if (☃ != ☃) {
        throw new bz("commands.testforblock.failed.data", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()), Integer.valueOf(☃), Integer.valueOf(☃) });
      }
    }
    if (☃)
    {
      apv ☃ = ☃.r(☃);
      if (☃ == null) {
        throw new bz("commands.testforblock.failed.tileEntity", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
      }
      dn ☃ = new dn();
      ☃.b(☃);
      if (!dy.a(☃, ☃, true)) {
        throw new bz("commands.testforblock.failed.nbt", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
      }
    }
    ☃.a(n.a.b, 1);
    a(☃, this, "commands.testforblock.success", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length > 0) && (☃.length <= 3)) {
      return a(☃, 0, ☃);
    }
    if (☃.length == 4) {
      return a(☃, ajt.h.c());
    }
    return Collections.emptyList();
  }
}
