import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lm
{
  private static final Logger a = ;
  private final lp b;
  private Set<lt> c = Sets.newHashSet();
  private oh<lt> d = new oh();
  private int e;
  
  public lm(lp ☃)
  {
    this.b = ☃;
    this.e = ☃.u().al().d();
  }
  
  public static long a(double ☃)
  {
    return on.d(☃ * 4096.0D);
  }
  
  public static void a(rr ☃, double ☃, double ☃, double ☃)
  {
    ☃.ae = a(☃);
    ☃.af = a(☃);
    ☃.ag = a(☃);
  }
  
  public void a(rr ☃)
  {
    lr ☃;
    if ((☃ instanceof lr))
    {
      a(☃, 512, 2);
      ☃ = (lr)☃;
      for (lt ☃ : this.c) {
        if (☃.b() != ☃) {
          ☃.b(☃);
        }
      }
    }
    else if ((☃ instanceof xw))
    {
      a(☃, 64, 5, true);
    }
    else if ((☃ instanceof zm))
    {
      a(☃, 64, 20, false);
    }
    else if ((☃ instanceof zv))
    {
      a(☃, 64, 10, false);
    }
    else if ((☃ instanceof zp))
    {
      a(☃, 64, 10, false);
    }
    else if ((☃ instanceof zw))
    {
      a(☃, 64, 10, true);
    }
    else if ((☃ instanceof aaa))
    {
      a(☃, 64, 10, true);
    }
    else if ((☃ instanceof zo))
    {
      a(☃, 64, 4, true);
    }
    else if ((☃ instanceof zz))
    {
      a(☃, 64, 10, true);
    }
    else if ((☃ instanceof aac))
    {
      a(☃, 64, 10, true);
    }
    else if ((☃ instanceof aab))
    {
      a(☃, 64, 10, true);
    }
    else if ((☃ instanceof zq))
    {
      a(☃, 64, 10, true);
    }
    else if ((☃ instanceof yd))
    {
      a(☃, 64, 20, true);
    }
    else if ((☃ instanceof aah))
    {
      a(☃, 80, 3, true);
    }
    else if ((☃ instanceof aag))
    {
      a(☃, 80, 3, true);
    }
    else if ((☃ instanceof wg))
    {
      a(☃, 64, 3, true);
    }
    else if ((☃ instanceof xo))
    {
      a(☃, 80, 3, false);
    }
    else if ((☃ instanceof zu))
    {
      a(☃, 80, 3, true);
    }
    else if ((☃ instanceof vu))
    {
      a(☃, 80, 3, false);
    }
    else if ((☃ instanceof wu))
    {
      a(☃, 160, 3, true);
    }
    else if ((☃ instanceof rq))
    {
      a(☃, 80, 3, true);
    }
    else if ((☃ instanceof ye))
    {
      a(☃, 160, 10, true);
    }
    else if ((☃ instanceof yc))
    {
      a(☃, 160, 20, true);
    }
    else if ((☃ instanceof xr))
    {
      a(☃, 160, Integer.MAX_VALUE, false);
    }
    else if ((☃ instanceof xq))
    {
      a(☃, 160, 3, true);
    }
    else if ((☃ instanceof rx))
    {
      a(☃, 160, 20, true);
    }
    else if ((☃ instanceof rp))
    {
      a(☃, 160, Integer.MAX_VALUE, true);
    }
    else if ((☃ instanceof wt))
    {
      a(☃, 256, Integer.MAX_VALUE, false);
    }
  }
  
  public void a(rr ☃, int ☃, int ☃)
  {
    a(☃, ☃, ☃, false);
  }
  
  public void a(rr ☃, int ☃, final int ☃, boolean ☃)
  {
    try
    {
      if (this.d.b(☃.O())) {
        throw new IllegalStateException("Entity is already tracked!");
      }
      lt ☃ = new lt(☃, ☃, this.e, ☃, ☃);
      this.c.add(☃);
      this.d.a(☃.O(), ☃);
      ☃.b(this.b.i);
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Adding entity to track");
      c ☃ = ☃.a("Entity To Track");
      
      ☃.a("Tracking range", ☃ + " blocks");
      ☃.a("Update interval", new Callable()
      {
        public String a()
          throws Exception
        {
          String ☃ = "Once per " + ☃ + " ticks";
          if (☃ == Integer.MAX_VALUE) {
            ☃ = "Maximum (" + ☃ + ")";
          }
          return ☃;
        }
      });
      ☃.a(☃);
      
      ((lt)this.d.a(☃.O())).b().a(☃.a("Entity That Is Already Tracked"));
      try
      {
        throw new e(☃);
      }
      catch (e ☃)
      {
        a.error("\"Silently\" catching entity tracking error.", ☃);
      }
    }
  }
  
  public void b(rr ☃)
  {
    lr ☃;
    if ((☃ instanceof lr))
    {
      ☃ = (lr)☃;
      for (lt ☃ : this.c) {
        ☃.a(☃);
      }
    }
    lt ☃ = (lt)this.d.d(☃.O());
    if (☃ != null)
    {
      this.c.remove(☃);
      ☃.a();
    }
  }
  
  public void a()
  {
    List<lr> ☃ = Lists.newArrayList();
    for (lt ☃ : this.c)
    {
      ☃.a(this.b.i);
      if (☃.b)
      {
        rr ☃ = ☃.b();
        if ((☃ instanceof lr)) {
          ☃.add((lr)☃);
        }
      }
    }
    lr ☃;
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      ☃ = (lr)☃.get(☃);
      for (lt ☃ : this.c) {
        if (☃.b() != ☃) {
          ☃.b(☃);
        }
      }
    }
  }
  
  public void a(lr ☃)
  {
    for (lt ☃ : this.c) {
      if (☃.b() == ☃) {
        ☃.b(this.b.i);
      } else {
        ☃.b(☃);
      }
    }
  }
  
  public void a(rr ☃, ff<?> ☃)
  {
    lt ☃ = (lt)this.d.a(☃.O());
    if (☃ != null) {
      ☃.a(☃);
    }
  }
  
  public void b(rr ☃, ff<?> ☃)
  {
    lt ☃ = (lt)this.d.a(☃.O());
    if (☃ != null) {
      ☃.b(☃);
    }
  }
  
  public void b(lr ☃)
  {
    for (lt ☃ : this.c) {
      ☃.d(☃);
    }
  }
  
  public void a(lr ☃, ase ☃)
  {
    List<rr> ☃ = Lists.newArrayList();
    List<rr> ☃ = Lists.newArrayList();
    for (lt ☃ : this.c)
    {
      rr ☃ = ☃.b();
      if ((☃ != ☃) && (☃.ab == ☃.b) && (☃.ad == ☃.c))
      {
        ☃.b(☃);
        if (((☃ instanceof sb)) && (((sb)☃).cQ() != null)) {
          ☃.add(☃);
        }
        if (!☃.bu().isEmpty()) {
          ☃.add(☃);
        }
      }
    }
    if (!☃.isEmpty()) {
      for (rr ☃ : ☃) {
        ☃.a.a(new hm(☃, ((sb)☃).cQ()));
      }
    }
    if (!☃.isEmpty()) {
      for (rr ☃ : ☃) {
        ☃.a.a(new hs(☃));
      }
    }
  }
  
  public void a(int ☃)
  {
    this.e = ((☃ - 1) * 16);
    for (lt ☃ : this.c) {
      ☃.a(this.e);
    }
  }
}
