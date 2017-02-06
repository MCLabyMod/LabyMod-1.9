import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class af
  extends i
{
  public String c()
  {
    return "fill";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.fill.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 7) {
      throw new cf("commands.fill.usage", new Object[0]);
    }
    ☃.a(n.a.b, 0);
    
    cj ☃ = a(☃, ☃, 0, false);
    cj ☃ = a(☃, ☃, 3, false);
    ajt ☃ = i.b(☃, ☃[6]);
    
    int ☃ = 0;
    if (☃.length >= 8) {
      ☃ = a(☃[7], 0, 15);
    }
    cj ☃ = new cj(Math.min(☃.p(), ☃.p()), Math.min(☃.q(), ☃.q()), Math.min(☃.r(), ☃.r()));
    cj ☃ = new cj(Math.max(☃.p(), ☃.p()), Math.max(☃.q(), ☃.q()), Math.max(☃.r(), ☃.r()));
    
    int ☃ = (☃.p() - ☃.p() + 1) * (☃.q() - ☃.q() + 1) * (☃.r() - ☃.r() + 1);
    if (☃ > 32768) {
      throw new bz("commands.fill.tooManyBlocks", new Object[] { Integer.valueOf(☃), Integer.valueOf(32768) });
    }
    if ((☃.q() < 0) || (☃.q() >= 256)) {
      throw new bz("commands.fill.outOfWorld", new Object[0]);
    }
    aht ☃ = ☃.e();
    for (int ☃ = ☃.r(); ☃ <= ☃.r(); ☃ += 16) {
      for (int ☃ = ☃.p(); ☃ <= ☃.p(); ☃ += 16) {
        if (!☃.e(new cj(☃, ☃.q() - ☃.q(), ☃))) {
          throw new bz("commands.fill.outOfWorld", new Object[0]);
        }
      }
    }
    dn ☃ = new dn();
    boolean ☃ = false;
    if ((☃.length >= 10) && (☃.m()))
    {
      String ☃ = a(☃, ☃, 9).c();
      try
      {
        ☃ = ed.a(☃);
        ☃ = true;
      }
      catch (ec ☃)
      {
        throw new bz("commands.fill.tagError", new Object[] { ☃.getMessage() });
      }
    }
    List<cj> ☃ = Lists.newArrayList();
    
    ☃ = 0;
    for (int ☃ = ☃.r(); ☃ <= ☃.r(); ☃++) {
      for (int ☃ = ☃.q(); ☃ <= ☃.q(); ☃++) {
        for (int ☃ = ☃.p(); ☃ <= ☃.p(); ☃++)
        {
          cj ☃ = new cj(☃, ☃, ☃);
          if (☃.length >= 9) {
            if ((☃[8].equals("outline")) || (☃[8].equals("hollow")))
            {
              if ((☃ != ☃.p()) && (☃ != ☃.p()) && (☃ != ☃.q()) && (☃ != ☃.q()) && (☃ != ☃.r()) && (☃ != ☃.r()))
              {
                if (!☃[8].equals("hollow")) {
                  continue;
                }
                ☃.a(☃, aju.a.u(), 2);
                ☃.add(☃); continue;
              }
            }
            else if (☃[8].equals("destroy")) {
              ☃.b(☃, true);
            } else if (☃[8].equals("keep"))
            {
              if (!☃.d(☃)) {
                continue;
              }
            }
            else if ((☃[8].equals("replace")) && (!☃.m())) {
              if (☃.length > 9)
              {
                ajt ☃ = i.b(☃, ☃[9]);
                if (☃.o(☃).t() != ☃) {}
              }
              else if (☃.length > 10)
              {
                int ☃ = i.a(☃[10]);
                arc ☃ = ☃.o(☃);
                if (☃.t().e(☃) != ☃) {
                  continue;
                }
              }
            }
          }
          apv ☃ = ☃.r(☃);
          if (☃ != null)
          {
            if ((☃ instanceof qg)) {
              ((qg)☃).l();
            }
            ☃.a(☃, aju.cv.u(), ☃ == aju.cv ? 2 : 4);
          }
          arc ☃ = ☃.a(☃);
          if (☃.a(☃, ☃, 2))
          {
            ☃.add(☃);
            ☃++;
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
          }
        }
      }
    }
    for (cj ☃ : ☃)
    {
      ajt ☃ = ☃.o(☃).t();
      ☃.c(☃, ☃);
    }
    if (☃ <= 0) {
      throw new bz("commands.fill.failed", new Object[0]);
    }
    ☃.a(n.a.b, ☃);
    a(☃, this, "commands.fill.success", new Object[] { Integer.valueOf(☃) });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length > 0) && (☃.length <= 3)) {
      return a(☃, 0, ☃);
    }
    if ((☃.length > 3) && (☃.length <= 6)) {
      return a(☃, 3, ☃);
    }
    if (☃.length == 7) {
      return a(☃, ajt.h.c());
    }
    if (☃.length == 9) {
      return a(☃, new String[] { "replace", "destroy", "keep", "hollow", "outline" });
    }
    if ((☃.length == 10) && ("replace".equals(☃[8]))) {
      return a(☃, ajt.h.c());
    }
    return Collections.emptyList();
  }
}
