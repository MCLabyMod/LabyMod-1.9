import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bx
  extends i
{
  public String c()
  {
    return "worldborder";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.worldborder.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.worldborder.usage", new Object[0]);
    }
    arv ☃ = a(☃);
    if (☃[0].equals("set"))
    {
      if ((☃.length != 2) && (☃.length != 3)) {
        throw new cf("commands.worldborder.set.usage", new Object[0]);
      }
      double ☃ = ☃.j();
      double ☃ = a(☃[1], 1.0D, 6.0E7D);
      long ☃ = ☃.length > 2 ? a(☃[2], 0L, 9223372036854775L) * 1000L : 0L;
      if (☃ > 0L)
      {
        ☃.a(☃, ☃, ☃);
        if (☃ > ☃) {
          a(☃, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }), Long.toString(☃ / 1000L) });
        } else {
          a(☃, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }), Long.toString(☃ / 1000L) });
        }
      }
      else
      {
        ☃.a(☃);
        a(☃, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }) });
      }
    }
    else if (☃[0].equals("add"))
    {
      if ((☃.length != 2) && (☃.length != 3)) {
        throw new cf("commands.worldborder.add.usage", new Object[0]);
      }
      double ☃ = ☃.h();
      double ☃ = ☃ + a(☃[1], -☃, 6.0E7D - ☃);
      long ☃ = ☃.i() + (☃.length > 2 ? a(☃[2], 0L, 9223372036854775L) * 1000L : 0L);
      if (☃ > 0L)
      {
        ☃.a(☃, ☃, ☃);
        if (☃ > ☃) {
          a(☃, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }), Long.toString(☃ / 1000L) });
        } else {
          a(☃, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }), Long.toString(☃ / 1000L) });
        }
      }
      else
      {
        ☃.a(☃);
        a(☃, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }) });
      }
    }
    else if (☃[0].equals("center"))
    {
      if (☃.length != 3) {
        throw new cf("commands.worldborder.center.usage", new Object[0]);
      }
      cj ☃ = ☃.c();
      double ☃ = b(☃.p() + 0.5D, ☃[1], true);
      double ☃ = b(☃.r() + 0.5D, ☃[2], true);
      
      ☃.c(☃, ☃);
      a(☃, this, "commands.worldborder.center.success", new Object[] { Double.valueOf(☃), Double.valueOf(☃) });
    }
    else if (☃[0].equals("damage"))
    {
      if (☃.length < 2) {
        throw new cf("commands.worldborder.damage.usage", new Object[0]);
      }
      if (☃[1].equals("buffer"))
      {
        if (☃.length != 3) {
          throw new cf("commands.worldborder.damage.buffer.usage", new Object[0]);
        }
        double ☃ = a(☃[2], 0.0D);
        double ☃ = ☃.m();
        ☃.b(☃);
        a(☃, this, "commands.worldborder.damage.buffer.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(☃) }), String.format("%.1f", new Object[] { Double.valueOf(☃) }) });
      }
      else if (☃[1].equals("amount"))
      {
        if (☃.length != 3) {
          throw new cf("commands.worldborder.damage.amount.usage", new Object[0]);
        }
        double ☃ = a(☃[2], 0.0D);
        double ☃ = ☃.n();
        ☃.c(☃);
        a(☃, this, "commands.worldborder.damage.amount.success", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(☃) }), String.format("%.2f", new Object[] { Double.valueOf(☃) }) });
      }
    }
    else if (☃[0].equals("warning"))
    {
      if (☃.length < 2) {
        throw new cf("commands.worldborder.warning.usage", new Object[0]);
      }
      if (☃[1].equals("time"))
      {
        if (☃.length != 3) {
          throw new cf("commands.worldborder.warning.time.usage", new Object[0]);
        }
        int ☃ = a(☃[2], 0);
        int ☃ = ☃.p();
        ☃.b(☃);
        a(☃, this, "commands.worldborder.warning.time.success", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃) });
      }
      else if (☃[1].equals("distance"))
      {
        if (☃.length != 3) {
          throw new cf("commands.worldborder.warning.distance.usage", new Object[0]);
        }
        int ☃ = a(☃[2], 0);
        int ☃ = ☃.q();
        ☃.c(☃);
        a(☃, this, "commands.worldborder.warning.distance.success", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃) });
      }
    }
    else if (☃[0].equals("get"))
    {
      double ☃ = ☃.h();
      ☃.a(n.a.e, on.c(☃ + 0.5D));
      ☃.a(new fb("commands.worldborder.get.success", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(☃) }) }));
    }
    else
    {
      throw new cf("commands.worldborder.usage", new Object[0]);
    }
  }
  
  protected arv a(MinecraftServer ☃)
  {
    return ☃.d[0].aj();
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "set", "center", "damage", "warning", "add", "get" });
    }
    if ((☃.length == 2) && (☃[0].equals("damage"))) {
      return a(☃, new String[] { "buffer", "amount" });
    }
    if ((☃.length >= 2) && (☃.length <= 3) && (☃[0].equals("center"))) {
      return b(☃, 1, ☃);
    }
    if ((☃.length == 2) && (☃[0].equals("warning"))) {
      return a(☃, new String[] { "time", "distance" });
    }
    return Collections.emptyList();
  }
}
