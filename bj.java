import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bj
  extends i
{
  public String c()
  {
    return "stats";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.stats.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.stats.usage", new Object[0]);
    }
    boolean ☃;
    if (☃[0].equals("entity"))
    {
      ☃ = false;
    }
    else
    {
      boolean ☃;
      if (☃[0].equals("block")) {
        ☃ = true;
      } else {
        throw new cf("commands.stats.usage", new Object[0]);
      }
    }
    boolean ☃;
    int ☃;
    int ☃;
    if (☃)
    {
      if (☃.length < 5) {
        throw new cf("commands.stats.block.usage", new Object[0]);
      }
      ☃ = 4;
    }
    else
    {
      if (☃.length < 3) {
        throw new cf("commands.stats.entity.usage", new Object[0]);
      }
      ☃ = 2;
    }
    String ☃ = ☃[(☃++)];
    if ("set".equals(☃))
    {
      if (☃.length < ☃ + 3)
      {
        if (☃ == 5) {
          throw new cf("commands.stats.block.set.usage", new Object[0]);
        }
        throw new cf("commands.stats.entity.set.usage", new Object[0]);
      }
    }
    else if ("clear".equals(☃))
    {
      if (☃.length < ☃ + 1)
      {
        if (☃ == 5) {
          throw new cf("commands.stats.block.clear.usage", new Object[0]);
        }
        throw new cf("commands.stats.entity.clear.usage", new Object[0]);
      }
    }
    else {
      throw new cf("commands.stats.usage", new Object[0]);
    }
    n.a ☃ = n.a.a(☃[(☃++)]);
    if (☃ == null) {
      throw new bz("commands.stats.failed", new Object[0]);
    }
    aht ☃ = ☃.e();
    n ☃;
    n ☃;
    if (☃)
    {
      cj ☃ = a(☃, ☃, 1, false);
      apv ☃ = ☃.r(☃);
      if (☃ == null) {
        throw new bz("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
      }
      n ☃;
      if ((☃ instanceof apy))
      {
        ☃ = ((apy)☃).c();
      }
      else
      {
        n ☃;
        if ((☃ instanceof aqn)) {
          ☃ = ((aqn)☃).d();
        } else {
          throw new bz("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(☃.p()), Integer.valueOf(☃.q()), Integer.valueOf(☃.r()) });
        }
      }
    }
    else
    {
      rr ☃ = b(☃, ☃, ☃[1]);
      ☃ = ☃.bp();
    }
    if ("set".equals(☃))
    {
      String ☃ = ☃[(☃++)];
      String ☃ = ☃[☃];
      if ((☃.isEmpty()) || (☃.isEmpty())) {
        throw new bz("commands.stats.failed", new Object[0]);
      }
      n.a(☃, ☃, ☃, ☃);
      a(☃, this, "commands.stats.success", new Object[] { ☃.b(), ☃, ☃ });
    }
    else if ("clear".equals(☃))
    {
      n.a(☃, ☃, null, null);
      a(☃, this, "commands.stats.cleared", new Object[] { ☃.b() });
    }
    if (☃)
    {
      cj ☃ = a(☃, ☃, 1, false);
      apv ☃ = ☃.r(☃);
      ☃.v_();
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "entity", "block" });
    }
    if ((☃.length == 2) && (☃[0].equals("entity"))) {
      return a(☃, ☃.J());
    }
    if ((☃.length >= 2) && (☃.length <= 4) && (☃[0].equals("block"))) {
      return a(☃, 1, ☃);
    }
    if (((☃.length == 3) && (☃[0].equals("entity"))) || ((☃.length == 5) && (☃[0].equals("block")))) {
      return a(☃, new String[] { "set", "clear" });
    }
    if (((☃.length == 4) && (☃[0].equals("entity"))) || ((☃.length == 6) && (☃[0].equals("block")))) {
      return a(☃, n.a.c());
    }
    if (((☃.length == 6) && (☃[0].equals("entity"))) || ((☃.length == 8) && (☃[0].equals("block")))) {
      return a(☃, a(☃));
    }
    return Collections.emptyList();
  }
  
  protected List<String> a(MinecraftServer ☃)
  {
    Collection<bbl> ☃ = ☃.a(0).ad().c();
    List<String> ☃ = Lists.newArrayList();
    for (bbl ☃ : ☃) {
      if (!☃.c().b()) {
        ☃.add(☃.b());
      }
    }
    return ☃;
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return (☃.length > 0) && (☃[0].equals("entity")) && (☃ == 1);
  }
}
