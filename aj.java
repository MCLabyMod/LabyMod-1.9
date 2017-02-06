import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class aj
  extends i
{
  public String c()
  {
    return "give";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.give.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.give.usage", new Object[0]);
    }
    zj ☃ = a(☃, ☃, ☃[0]);
    ado ☃ = a(☃, ☃[1]);
    int ☃ = ☃.length >= 3 ? a(☃[2], 1, 64) : 1;
    int ☃ = ☃.length >= 4 ? a(☃[3]) : 0;
    adq ☃ = new adq(☃, ☃, ☃);
    if (☃.length >= 5)
    {
      String ☃ = a(☃, ☃, 4).c();
      try
      {
        ☃.d(ed.a(☃));
      }
      catch (ec ☃)
      {
        throw new bz("commands.give.tagError", new Object[] { ☃.getMessage() });
      }
    }
    boolean ☃ = ☃.br.c(☃);
    if (☃)
    {
      ☃.l.a(null, ☃.p, ☃.q, ☃.r, ng.cU, nh.h, 0.2F, ((☃.bF().nextFloat() - ☃.bF().nextFloat()) * 0.7F + 1.0F) * 2.0F);
      ☃.bs.b();
    }
    if ((!☃) || (☃.b > 0))
    {
      ☃.a(n.a.d, ☃ - ☃.b);
      yd ☃ = ☃.a(☃, false);
      if (☃ != null)
      {
        ☃.r();
        ☃.d(☃.h_());
      }
    }
    else
    {
      ☃.b = 1;
      ☃.a(n.a.d, ☃);
      yd ☃ = ☃.a(☃, false);
      if (☃ != null) {
        ☃.w();
      }
    }
    a(☃, this, "commands.give.success", new Object[] { ☃.B(), Integer.valueOf(☃), ☃.h_() });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    if (☃.length == 2) {
      return a(☃, ado.f.c());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
