import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class bm
  extends i
{
  public String c()
  {
    return "tp";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.tp.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.tp.usage", new Object[0]);
    }
    int ☃ = 0;
    rr ☃;
    if ((☃.length == 2) || (☃.length == 4) || (☃.length == 6))
    {
      rr ☃ = b(☃, ☃, ☃[0]);
      ☃ = 1;
    }
    else
    {
      ☃ = a(☃);
    }
    if ((☃.length == 1) || (☃.length == 2))
    {
      rr ☃ = b(☃, ☃, ☃[(☃.length - 1)]);
      if (☃.l != ☃.l) {
        throw new bz("commands.tp.notSameDimension", new Object[0]);
      }
      ☃.p();
      if ((☃ instanceof lr)) {
        ((lr)☃).a.a(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
      } else {
        ☃.b(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
      }
      a(☃, this, "commands.tp.success", new Object[] { ☃.h_(), ☃.h_() });
      return;
    }
    if (☃.length < ☃ + 3) {
      throw new cf("commands.tp.usage", new Object[0]);
    }
    if (☃.l == null) {
      return;
    }
    int ☃ = ☃;
    i.a ☃ = a(☃.p, ☃[(☃++)], true);
    i.a ☃ = a(☃.q, ☃[(☃++)], 0, 0, false);
    i.a ☃ = a(☃.r, ☃[(☃++)], true);
    i.a ☃ = a(☃.v, ☃.length > ☃ ? ☃[(☃++)] : "~", false);
    i.a ☃ = a(☃.w, ☃.length > ☃ ? ☃[☃] : "~", false);
    if ((☃ instanceof lr))
    {
      Set<ha.a> ☃ = EnumSet.noneOf(ha.a.class);
      if (☃.c()) {
        ☃.add(ha.a.a);
      }
      if (☃.c()) {
        ☃.add(ha.a.b);
      }
      if (☃.c()) {
        ☃.add(ha.a.c);
      }
      if (☃.c()) {
        ☃.add(ha.a.e);
      }
      if (☃.c()) {
        ☃.add(ha.a.d);
      }
      float ☃ = (float)☃.b();
      if (!☃.c()) {
        ☃ = on.g(☃);
      }
      float ☃ = (float)☃.b();
      if (!☃.c()) {
        ☃ = on.g(☃);
      }
      ☃.p();
      ((lr)☃).a.a(☃.b(), ☃.b(), ☃.b(), ☃, ☃, ☃);
      ☃.h(☃);
    }
    else
    {
      float ☃ = (float)on.g(☃.a());
      float ☃ = (float)on.g(☃.a());
      
      ☃ = on.a(☃, -90.0F, 90.0F);
      
      ☃.b(☃.a(), ☃.a(), ☃.a(), ☃, ☃);
      ☃.h(☃);
    }
    a(☃, this, "commands.tp.success.coordinates", new Object[] { ☃.h_(), Double.valueOf(☃.a()), Double.valueOf(☃.a()), Double.valueOf(☃.a()) });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length == 1) || (☃.length == 2)) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 0;
  }
}
