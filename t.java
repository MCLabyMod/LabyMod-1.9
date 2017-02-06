import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class t
  extends i
{
  public String c()
  {
    return "clear";
  }
  
  public String b(m ☃)
  {
    return "commands.clear.usage";
  }
  
  public int a()
  {
    return 2;
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    lr ☃ = ☃.length == 0 ? a(☃) : a(☃, ☃, ☃[0]);
    ado ☃ = ☃.length >= 2 ? a(☃, ☃[1]) : null;
    int ☃ = ☃.length >= 3 ? a(☃[2], -1) : -1;
    int ☃ = ☃.length >= 4 ? a(☃[3], -1) : -1;
    
    dn ☃ = null;
    if (☃.length >= 5) {
      try
      {
        ☃ = ed.a(a(☃, 4));
      }
      catch (ec ☃)
      {
        throw new bz("commands.clear.tagError", new Object[] { ☃.getMessage() });
      }
    }
    if ((☃.length >= 2) && (☃ == null)) {
      throw new bz("commands.clear.failure", new Object[] { ☃.h_() });
    }
    int ☃ = ☃.br.a(☃, ☃, ☃, ☃);
    ☃.bs.b();
    if (!☃.bJ.d) {
      ☃.r();
    }
    ☃.a(n.a.d, ☃);
    if (☃ == 0) {
      throw new bz("commands.clear.failure", new Object[] { ☃.h_() });
    }
    if (☃ == 0) {
      ☃.a(new fb("commands.clear.testing", new Object[] { ☃.h_(), Integer.valueOf(☃) }));
    } else {
      a(☃, this, "commands.clear.success", new Object[] { ☃.h_(), Integer.valueOf(☃) });
    }
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
