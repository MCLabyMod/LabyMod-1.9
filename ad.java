import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ad
  extends i
{
  public String c()
  {
    return "execute";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.execute.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 5) {
      throw new cf("commands.execute.usage", new Object[0]);
    }
    final rr ☃ = a(☃, ☃, ☃[0], rr.class);
    final double ☃ = b(☃.p, ☃[1], false);
    double ☃ = b(☃.q, ☃[2], false);
    final double ☃ = b(☃.r, ☃[3], false);
    final cj ☃ = new cj(☃, ☃, ☃);
    
    int ☃ = 4;
    if (("detect".equals(☃[4])) && (☃.length > 10))
    {
      aht ☃ = ☃.e();
      double ☃ = b(☃, ☃[5], false);
      double ☃ = b(☃, ☃[6], false);
      double ☃ = b(☃, ☃[7], false);
      ajt ☃ = b(☃, ☃[8]);
      int ☃ = a(☃[9], -1, 15);
      cj ☃ = new cj(☃, ☃, ☃);
      arc ☃ = ☃.o(☃);
      if ((☃.t() != ☃) || ((☃ >= 0) && (☃.t().e(☃) != ☃))) {
        throw new bz("commands.execute.failed", new Object[] { "detect", ☃.h_() });
      }
      ☃ = 10;
    }
    String ☃ = a(☃, ☃);
    final m ☃ = ☃;
    m ☃ = new m()
    {
      public String h_()
      {
        return ☃.h_();
      }
      
      public eu i_()
      {
        return ☃.i_();
      }
      
      public void a(eu ☃)
      {
        ☃.a(☃);
      }
      
      public boolean a(int ☃, String ☃)
      {
        return ☃.a(☃, ☃);
      }
      
      public cj c()
      {
        return ☃;
      }
      
      public bbj d()
      {
        return new bbj(☃, ☃, this.f);
      }
      
      public aht e()
      {
        return ☃.l;
      }
      
      public rr f()
      {
        return ☃;
      }
      
      public boolean z_()
      {
        return (this.g == null) || (this.g.d[0].U().b("commandBlockOutput"));
      }
      
      public void a(n.a ☃, int ☃)
      {
        ☃.a(☃, ☃);
      }
      
      public MinecraftServer h()
      {
        return ☃.h();
      }
    };
    l ☃ = ☃.N();
    try
    {
      int ☃ = ☃.a(☃, ☃);
      if (☃ < 1) {
        throw new bz("commands.execute.allInvocationsFailed", new Object[] { ☃ });
      }
    }
    catch (Throwable ☃)
    {
      throw new bz("commands.execute.failed", new Object[] { ☃, ☃.h_() });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, ☃.J());
    }
    if ((☃.length > 1) && (☃.length <= 4)) {
      return a(☃, 1, ☃);
    }
    if ((☃.length > 5) && (☃.length <= 8) && ("detect".equals(☃[4]))) {
      return a(☃, 5, ☃);
    }
    if ((☃.length == 9) && ("detect".equals(☃[4]))) {
      return a(☃, ajt.h.c());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
